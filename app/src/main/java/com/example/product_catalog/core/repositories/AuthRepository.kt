package com.example.product_catalog.core.repositories

import com.example.product_catalog.core.models.User

interface AuthRepository {
    suspend fun login(username: String, password: String): User?
}