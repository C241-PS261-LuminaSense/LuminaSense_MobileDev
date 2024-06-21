package com.bangkit.luminasense.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bangkit.luminasense.backend.preferences.SharedPrefHelper
import com.bangkit.luminasense.components.CustomButton
import com.bangkit.luminasense.components.CustomTextFormField
import com.bangkit.luminasense.components.ValidationType
import com.bangkit.luminasense.ui.theme.kAccentColor
import com.bangkit.luminasense.ui.theme.kWhiteColor

@Composable
fun IpInputScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPrefHelper = SharedPrefHelper(context)

    val (ipAddress, setIpAddress) = rememberSaveable {
        mutableStateOf(sharedPrefHelper.getIpAddress() ?: "")
    }
    var isSaved by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextFormField(
            title = "IP Address",
            hintText = "Masukkan IP Address",
            value = ipAddress,
            validationType = ValidationType.IpAddress ,
            onValueChange = setIpAddress,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ){
            CustomButton(
                title = "Save IP Address",
                color = kAccentColor,
                titleColor = kWhiteColor,
                iconResId = null,
                onTap = {
                    sharedPrefHelper.saveIpAddress(ipAddress)
                    isSaved = true
                })
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Display Snackbar
        SnackbarHost(hostState = snackbarHostState)

        // Launch snackbar when isSaved changes
        LaunchedEffect(isSaved) {
            if (isSaved) {
                snackbarHostState.showSnackbar(
                    message = "IP Address berhasil disimpan",
                    duration = SnackbarDuration.Short
                )
                // Navigate to home after showing snackbar
                navController.navigate("home") {
                    popUpTo(0) { inclusive = true }
                }
            }
        }
    }
}



@Preview
@Composable
fun Test() {
    val navController = rememberNavController()
    IpInputScreen(navController = navController)
}
