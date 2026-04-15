package com.ucb.primerproyecto.portafolio.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ucb.primerproyecto.portafolio.presentation.composable.PortafolioContent
import com.ucb.primerproyecto.portafolio.presentation.viewmodel.PortafolioViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PortafolioScreen(
    viewModel: PortafolioViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    PortafolioContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}
