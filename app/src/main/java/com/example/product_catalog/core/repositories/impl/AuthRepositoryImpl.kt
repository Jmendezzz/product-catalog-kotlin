package com.example.product_catalog.core.repositories.impl

import android.util.Log
import com.example.product_catalog.core.models.User
import com.example.product_catalog.core.repositories.AuthRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl : AuthRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val usersCollection = firestore.collection("users")

    override suspend fun login(username: String, password: String): User? = suspendCoroutine { continuation ->
        Log.d("AuthRepository", "Attempting to login user: $username with password: $password")
        usersCollection
            .whereEqualTo("username", username)
            .whereEqualTo("password", password)
            .get()
            .addOnSuccessListener { result ->
                Log.d("AuthRepository", "Login query successful: ${result.size()} documents found")
                val document = result.firstOrNull()
                if (document != null) {
                    val user = document.toObject(User::class.java)
                    Log.d("AuthRepository", "User found: ${user.username}")
                    continuation.resume(user)
                } else {
                    continuation.resume(null)
                }
            }
            .addOnFailureListener { exception ->
                continuation.resumeWithException(exception)
            }
    }

}