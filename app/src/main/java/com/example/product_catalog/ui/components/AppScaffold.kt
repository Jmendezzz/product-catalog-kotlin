package com.example.product_catalog.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AppScaffold(
    title: String,
    onLogout: (() -> Unit)? = null,
    showBottomBar: Boolean = false,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
        ,
        topBar = {
            AppTopBar(
                title = title,
                actionText = if (onLogout != null) "Cerrar sesión" else null,
                onActionClick = onLogout
            )
        },
        bottomBar = {
            if (showBottomBar) {
                BottomAppBar {
                    Text(
                        text = "© 2025 CatálogoApp",
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        content = content
    )
}
