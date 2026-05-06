package com.ucb.primerproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ucb.primerproyecto.di.getModules
import com.ucb.primerproyecto.worker.LogScheduler
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(//permiso de notificaciones
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                100
            )
        }
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    // KoinApplication initializes Koin for the preview environment, 
    // ensuring that koinViewModel() calls within App() can resolve dependencies.
    KoinApplication(application = {
        modules(getModules())
    }) {
        App()
    }
}