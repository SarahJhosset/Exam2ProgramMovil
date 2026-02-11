package com.ucb.primerproyecto

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.ucb.primerproyecto.collection.presentation.screen.CollectionScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        CollectionScreen()
    }
}