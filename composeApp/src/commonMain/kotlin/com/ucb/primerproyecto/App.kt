package com.ucb.primerproyecto

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.ucb.primerproyecto.collection.presentation.screen.CollectionScreen
import com.ucb.primerproyecto.collection.presentation.screen.WomenCollectionScreen
import com.ucb.primerproyecto.increment.presentation.screen.IncrementScreen
import com.ucb.primerproyecto.increment.presentation.viewmodel.IncrementViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {

        //WomenCollectionScreen()
        IncrementScreen()
    }
}