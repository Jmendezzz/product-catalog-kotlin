package com.example.product_catalog.features.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.product_catalog.ui.components.AppButton
import com.example.product_catalog.ui.components.AppOutlinedTextField
import com.example.product_catalog.ui.components.AppTitle

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    val viewModel: LoginViewModel = viewModel()
    val context = LocalContext.current

    val loginResult = viewModel.loginSuccess

    LaunchedEffect (loginResult) {
        if (loginResult == true) {
            onLoginSuccess()
        } else if (loginResult == false) {
            Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        AppTitle(text = "Iniciar Sesión")

        AppOutlinedTextField(
            value = viewModel.username,
            onValueChange = { viewModel.username = it },
            label = "Usuario"
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppOutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = "Contraseña",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppButton(
            text = "Entrar",
            onClick = { viewModel.login() }
        )
    }
}

