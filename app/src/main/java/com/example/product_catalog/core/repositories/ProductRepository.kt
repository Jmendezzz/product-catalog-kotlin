package com.example.product_catalog.core.repositories

import com.example.product_catalog.core.models.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}