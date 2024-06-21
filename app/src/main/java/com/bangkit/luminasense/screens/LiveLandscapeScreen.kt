package com.bangkit.luminasense.screens

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.bangkit.luminasense.MainActivity
import com.bangkit.luminasense.backend.preferences.SharedPrefHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.tensorflow.lite.Interpreter
import java.nio.ByteBuffer
import java.nio.ByteOrder

@Composable
fun LiveLandscapeScreen(navController: NavController, context: Context) {
    // Get the current activity
    val activity = context as MainActivity

    // Set orientation to landscape
    DisposableEffect(Unit) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        onDispose {
            // Reset orientation to sensor when leaving the screen
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
        }
    }

    Log.d("ObjectDetection", "ObjectDetection Composable called")
    val model = remember { ObjectDetectionModel(context) }
    var detectedPeopleCount by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    var webView: WebView? by remember { mutableStateOf(null) }

    val sharedPrefHelper = SharedPrefHelper(context)
    val ipAddress = sharedPrefHelper.getIpAddress()

    Scaffold(
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = MaterialTheme.colorScheme.background
            ) {
                if (ipAddress != null) {
                    LaunchedEffect(Unit) {
                        webView = WebView(context).apply {
                            settings.javaScriptEnabled = true
                            settings.loadWithOverviewMode = true
                            settings.useWideViewPort = true
                            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                            loadUrl("http://$ipAddress:81/stream")
                        }

                        scope.launch(Dispatchers.IO) {
                            while (true) {
                                try {
                                    withContext(Dispatchers.Main) {
                                        if (webView != null) {
                                            // Setelah memperoleh gambar dari WebView
                                            webView?.capturePicture()?.let { picture ->
                                                // Mendapatkan gambar asli dari WebView
                                                val originalBitmap = Bitmap.createBitmap(picture.width, picture.height, Bitmap.Config.ARGB_8888)
                                                Canvas(originalBitmap).apply { drawPicture(picture) }

                                                Log.d("ObjectDetection", "Original image obtained: Width = ${originalBitmap.width}, Height = ${originalBitmap.height}")

                                                // Menyesuaikan gambar dengan resolusi yang diharapkan (640x640)
                                                val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 640, 640, true)

                                                Log.d("ObjectDetection", "Image resized to: Width = ${scaledBitmap.width}, Height = ${scaledBitmap.height}")

                                                // Konversi Bitmap ke ByteBuffer untuk input model
                                                val inputBuffer = ByteBuffer.allocateDirect(1 * 640 * 640 * 3 * 4).apply {
                                                    order(ByteOrder.nativeOrder())
                                                    for (y in 0 until 640) {
                                                        for (x in 0 until 640) {
                                                            val pixel = scaledBitmap.getPixel(x, y)

                                                            // Normalisasi nilai piksel ke rentang 0 hingga 1
                                                            val red = (pixel shr 16 and 0xFF) / 255.0f
                                                            val green = (pixel shr 8 and 0xFF) / 255.0f
                                                            val blue = (pixel and 0xFF) / 255.0f

                                                            // Setiap saluran warna (R, G, B) ditambahkan ke buffer
                                                            putFloat(red)
                                                            putFloat(green)
                                                            putFloat(blue)
                                                        }
                                                    }
                                                }

                                                Log.d("ObjectDetection", "ByteBuffer created: Capacity = ${inputBuffer.capacity()}, Remaining = ${inputBuffer.remaining()}")

                                                // Jalankan inferensi dengan model
                                                val output = model.runInference(inputBuffer)

                                                Log.d("ObjectDetection", "Inference completed, processing output")

                                                // Proses output model
                                                val peopleCount = processModelOutput(output)

                                                // Update UI dengan jumlah orang yang terdeteksi
                                                detectedPeopleCount = peopleCount
                                                Log.d("ObjectDetection", "People detected: $detectedPeopleCount")
                                            }
                                        }
                                    }
                                } catch (e: Exception) {
                                    Log.e("ObjectDetection", "Error during object detection", e)
                                }
                                delay(1000)  // Tambahkan delay untuk memberikan waktu antar frame
                            }
                        }
                    }

                    AndroidView(factory = { webView!! }, modifier = Modifier.fillMaxSize())

                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Tidak ada IP Address yang tersimpan",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Button(onClick = {
                            navController.navigate("ipInput")
                        }) {
                            Text("Set IP Address")
                        }
                    }
                }
            }
        }
    )
}
