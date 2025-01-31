package com.bangkit.luminasense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bangkit.luminasense.screens.AboutUsScreen
import com.bangkit.luminasense.screens.HomeScreen
import com.bangkit.luminasense.screens.IpInputScreen
import com.bangkit.luminasense.screens.LiveLandscapeScreen
import com.bangkit.luminasense.screens.LivePortraitScreen
import com.bangkit.luminasense.screens.LoginScreen
import com.bangkit.luminasense.screens.OnBoardingScreen
import com.bangkit.luminasense.screens.PetunjukScreen
import com.bangkit.luminasense.screens.ProfileScreen
import com.bangkit.luminasense.screens.RegisterScreen
import com.bangkit.luminasense.screens.SplashScreen
import com.bangkit.luminasense.ui.theme.LuminaSenseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LuminaSenseTheme {

                 // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "splash"
                    ) {
                        composable("splash"){
                            SplashScreen(navController= navController)
                        }
                        composable("onBoarding"){
                            OnBoardingScreen(navController= navController)
                        }
                        composable("home"){
                            HomeScreen(navController= navController)
                        }
                        composable("login"){
                            LoginScreen(navController= navController)
                        }
                        composable("register"){
                            RegisterScreen(navController= navController)
                        }
                        composable("livePortrait"){
                            LivePortraitScreen(navController= navController, context = this@MainActivity)
                        }
                        composable("liveLandscape"){
                            LiveLandscapeScreen(navController= navController, context = this@MainActivity)
                        }
                        composable("ipInput"){
                            IpInputScreen(navController= navController)
                        }
                        composable("profile"){
                           ProfileScreen(navController = navController)
                        }
                        composable("petunjuk"){
                            PetunjukScreen(navController = navController)
                        }
                        composable("aboutUs"){
                            AboutUsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

