package com.example.product_catalog.features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.product_catalog.core.repositories.AuthRepository
import com.example.product_catalog.core.repositories.impl.AuthRepositoryImpl
import com.example.product_catalog.core.session.SessionManager
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository = AuthRepositoryImpl(),
    private val session: SessionManager = SessionManager
) : ViewModel() {

    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var loginSuccess by mutableStateOf<Boolean?>(null)

    fun login() {
        viewModelScope.launch {
            val user = authRepository.login(username, password)
            if (user != null) {
                session.setUser(user.username)
                loginSuccess = true
            } else {
                loginSuccess = false
            }
        }
    }
}
