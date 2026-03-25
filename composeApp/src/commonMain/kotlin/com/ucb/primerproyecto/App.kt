package com.ucb.primerproyecto

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.ucb.primerproyecto.collection.presentation.screen.CollectionScreen
import com.ucb.primerproyecto.increment.presentation.screen.IncrementScreen
import com.ucb.primerproyecto.increment.presentation.viewmodel.IncrementViewModel
import com.ucb.primerproyecto.navigation.AppNavHost

@Composable
@Preview
fun App() {
    val snackbarHostState = remember { SnackbarHostState() }
    MaterialTheme(
        //colorScheme = darkColorScheme()
    ) {
        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { paddingVaues ->
            AppNavHost()
        }

    }
}
