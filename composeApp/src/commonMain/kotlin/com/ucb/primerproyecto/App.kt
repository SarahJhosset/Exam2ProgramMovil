package com.ucb.primerproyecto

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.ucb.primerproyecto.di.getModules
import com.ucb.primerproyecto.navigation.AppNavHost
import org.koin.compose.KoinApplication

@Composable
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

@Preview
@Composable
fun AppPreview() {
    // We wrap the App in KoinApplication for the Preview to initialize Koin,
    // resolving the "KoinApplication has not been started" issue.
    KoinApplication(application = {
        modules(getModules())
    }) {
        App()
    }
}
