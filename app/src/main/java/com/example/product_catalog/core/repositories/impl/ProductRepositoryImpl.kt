package com.example.product_catalog.core.repositories.impl

import com.example.product_catalog.core.models.Product
import com.example.product_catalog.core.repositories.ProductRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ProductRepositoryImpl : ProductRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val productsCollection = firestore.collection("products")

    override suspend fun getProducts(): List<Product> = suspendCoroutine { continuation ->
        productsCollection.get()
            .addOnSuccessListener { result ->
                val products = result.mapNotNull { it.toObject(Product::class.java) }
                continuation.resume(products)
            }
            .addOnFailureListener { exception ->
                continuation.resumeWithException(exception)
            }
    }
}
