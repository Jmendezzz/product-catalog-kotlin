package com.example.product_catalog.core.session

object SessionManager {
    private var currentUser: String? = null

    fun setUser(username: String) {
        currentUser = username
    }

    fun clearSession() {
        currentUser = null
    }

    fun getUser(): String? = currentUser
    fun isLoggedIn(): Boolean = currentUser != null
}
