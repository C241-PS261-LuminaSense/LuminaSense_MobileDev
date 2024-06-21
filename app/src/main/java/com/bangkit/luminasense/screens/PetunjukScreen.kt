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
fun PetunjukScreen(navController: NavController) {

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
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                        ){
                            Box(
                                modifier = Modifier
                                    .width(31.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_info),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(31.dp),
                                    alignment = Alignment.Center
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "PETUNJUK PENGGUNAAN",
                                style = TextStyles.boldTextStyle.copy(
                                    fontSize = 20.sp,
                                    color = kBlackColor,
                                )
                            )
                        }
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
                                //ESP32CAM
                                Row{
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(

                                        text = "PERANGKAT ESP32CAM",
                                        style = TextStyles.boldTextStyle.copy(
                                            fontSize = 20.sp,
                                            color = kBlackColor,
                                        )
                                    )
                                }
                                Row(){
                                    Text(
                                        text = "1.  ",
                                        style = TextStyles.mediumTextStyle.copy(
                                            fontSize = 14.sp,
                                            color = kBlackColor,
                                        )
                                    )
                                    Text(
                                        text = "Set SSID dan Password Wifi pada konfigurasi ESP32CAM",
                                        style = TextStyles.mediumTextStyle.copy(
                                            fontSize = 14.sp,
                                            color = kBlackColor,
                                        )
                                    )
                                }
                                Row(){
                                    Text(
                                        text = "2.  ",
                                        style = TextStyles.mediumTextStyle.copy(
                                            fontSize = 14.sp,
                                            color = kBlackColor,
                                        )
                                    )
                                    Text(
                                        text = "Jalankan ESP32CAM dan dapatkan IP Address dari serial monitor",
                                        style = TextStyles.mediumTextStyle.copy(
                                            fontSize = 14.sp,
                                            color = kBlackColor,
                                        )
                                    )
                                }
                                
                                Spacer(modifier = Modifier.height(35.dp))

                                //Aplikasi Luminasense
                                Row{
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(

                                        text = "APLIKASI LuminaSense",
                                        style = TextStyles.boldTextStyle.copy(
                                            fontSize = 20.sp,
                                            color = kBlackColor,
                                        )
                                    )
                                }
                                Row(){
                                    Text(
                                        text = "1.  ",
                                        style = TextStyles.mediumTextStyle.copy(
                                            fontSize = 14.sp,
                                            color = kBlackColor,
                                        )
                                    )
                                    Text(
                                        text = "Hubungkan perangkat Smartphone dengan Wifi yang sama dengan ESP32CAM atau berada di satu jaringan yang sama",
                                        style = TextStyles.mediumTextStyle.copy(
                                            fontSize = 14.sp,
                                            color = kBlackColor,
                                        )
                                    )
                                }
                                Row(){
                                    Text(
                                        text = "2.  ",
                                        style = TextStyles.mediumTextStyle.copy(
                                            fontSize = 14.sp,
                                            color = kBlackColor,
                                        )
                                    )
                                    Text(
                                        text = "Dapatkan IP Address dari hasil berjalannya ESP32CAM",
                                        style = TextStyles.mediumTextStyle.copy(
                                            fontSize = 14.sp,
                                            color = kBlackColor,
                                        )
                                    )
                                }
                                Row(){
                                    Text(
                                        text = "3.  ",
                                        style = TextStyles.mediumTextStyle.copy(
                                            fontSize = 14.sp,
                                            color = kBlackColor,
                                        )
                                    )
                                    Text(
                                        text = "Input atau set IP Address ke dalam aplikasi dengan menekan button “Set IP Address” seperti pada button di bawah atau pada halaman LiveCamera saat membuka aplikasi",
                                        style = TextStyles.mediumTextStyle.copy(
                                            fontSize = 14.sp,
                                            color = kBlackColor,
                                        )
                                    )
                                }

                            }
                        }

                        Spacer(modifier = Modifier.height(80.dp))

                        CustomButton(
                            title = "Set IP Address",
                            color = kAccentColor,
                            titleColor = kWhiteColor,
                            iconResId = null,
                            onTap = {
                                navController.navigate("ipInput")
                            })
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun tesPetunjuk(){
    val navController = rememberNavController()
    PetunjukScreen(navController = navController)
}

