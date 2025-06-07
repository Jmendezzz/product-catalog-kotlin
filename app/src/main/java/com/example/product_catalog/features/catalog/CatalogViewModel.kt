package com.example.product_catalog.features.catalog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.product_catalog.core.models.Product
import com.example.product_catalog.core.repositories.ProductRepository
import com.example.product_catalog.core.repositories.impl.ProductRepositoryImpl
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val repository: ProductRepository = ProductRepositoryImpl()
) : ViewModel() {

    var products by mutableStateOf<List<Product>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            try {
                isLoading = true
                products = repository.getProducts()
            } catch (e: Exception) {
                products = emptyList()
            } finally {
                isLoading = false
            }
        }
    }
}
