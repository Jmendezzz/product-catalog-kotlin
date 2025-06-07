package com.example.product_catalog.core.models

data class Product(
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
)
