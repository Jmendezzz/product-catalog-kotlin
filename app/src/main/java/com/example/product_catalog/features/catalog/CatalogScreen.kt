package com.example.product_catalog.features.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.product_catalog.core.session.SessionManager
import com.example.product_catalog.features.product.ProductList
import com.example.product_catalog.ui.components.AppScaffold
import com.example.product_catalog.ui.components.AppSubtitle

@Composable
fun CatalogScreen(
    onLogout: () -> Unit,
    viewModel: CatalogViewModel = viewModel()
) {
    val username = SessionManager.getUser() ?: "Invitado"
    val products by remember { derivedStateOf{ viewModel.products } }

    AppScaffold(
        title = "Catálogo",
        onLogout = {
            SessionManager.clearSession()
            onLogout()
        },
        showBottomBar = true
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxSize()
        ) {
            AppSubtitle(text = "Bienvenido, $username")

            if (products.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                ProductList(products = products)
            }
        }
    }
}
