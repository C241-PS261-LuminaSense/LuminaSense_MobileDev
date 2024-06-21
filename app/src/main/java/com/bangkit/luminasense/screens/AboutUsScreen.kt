package com.bangkit.luminasense.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bangkit.luminasense.R
import com.bangkit.luminasense.backend.preferences.SharedPrefHelper
import com.bangkit.luminasense.backend.service.ApiConfig
import com.bangkit.luminasense.backend.service.LoginRequest
import com.bangkit.luminasense.backend.service.LoginResponse
import com.bangkit.luminasense.components.CustomButton
import com.bangkit.luminasense.components.CustomTextFormField
import com.bangkit.luminasense.components.LoadingAnimation
import com.bangkit.luminasense.components.ValidationType
import com.bangkit.luminasense.ui.theme.TextStyles
import com.bangkit.luminasense.ui.theme.kAccentColor
import com.bangkit.luminasense.ui.theme.kBlackColor
import com.bangkit.luminasense.ui.theme.kDarkColor
import com.bangkit.luminasense.ui.theme.kLightColor
import com.bangkit.luminasense.ui.theme.kSilverColor
import com.bangkit.luminasense.ui.theme.kWhiteColor
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun AboutUsScreen(navController: NavController) {

    Scaffold(

        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = kLightColor
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.TopCenter)
                            .padding(top = 50.dp, start = 20.dp, end = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "ABOUT US",
                            style = TextStyles.boldTextStyle.copy(
                                fontSize = 20.sp,
                                color = kBlackColor,
                            )
                        )
                        Spacer(modifier = Modifier.height(48.dp))
                        Surface (
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Transparent,
                            shape = RoundedCornerShape(20.dp),
                            border = BorderStroke(1.dp, kSilverColor)
                        ) {
                            Column (
                                modifier = Modifier
                                    .padding(vertical = 26.dp, horizontal = 12.dp)
                            ){
                                Text(
                                    text = "Our project proposes a Human Presence Monitoring System Using Cameras for Light Automation to tackle the urgent need for efficient indoor human presence monitoring, emphasizing sustainability and energy efficiency. By employing machine learning algorithms, it detects human presence from camera images captured by IoT devices like the ESP32Cam, triggering light automation accordingly. Our core aim is to harmonize humanity and the environment by promoting sustainable practices.\n" +
                                            "\n" +
                                            "We've identified energy wastage indoors due to inefficient lighting control as a critical issue. Our solution aims to alleviate this by automatically adjusting lighting based on human occupancy, reducing unnecessary energy consumption.\n" +
                                            "\n" +
                                            "Key research questions include effective application of machine learning for presence detection, optimal integration of IoT devices, cloud computing, and mobile development, and ensuring scalability, reliability, and security.\n" +
                                            "\n" +
                                            "We strive to deliver a robust, user-friendly system fostering energy efficiency and sustainable living. Our commitment lies in offering a practical solution addressing current lighting automation inefficiencies. Through empathetic design and iterative development, we aim to empower individuals and organizations towards sustainable futures, nurturing harmony between humanity and the environment.",
                                    style = TextStyles.mediumTextStyle.copy(
                                        fontSize = 14.sp,
                                        color = kBlackColor,
                                    ),
                                    textAlign = TextAlign.Justify

                                )

                            }
                        }

                        
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun tesAbout(){
    val navController = rememberNavController()
    AboutUsScreen(navController = navController)
}