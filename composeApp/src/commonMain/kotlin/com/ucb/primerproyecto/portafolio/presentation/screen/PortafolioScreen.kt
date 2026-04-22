package com.ucb.primerproyecto.portafolio.presentation.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.ucb.primerproyecto.navigation.NavRoute
import com.ucb.primerproyecto.portafolio.presentation.composable.PortafolioContent
import com.ucb.primerproyecto.portafolio.presentation.state.PortafolioEffect
import com.ucb.primerproyecto.portafolio.presentation.viewmodel.PortafolioViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PortafolioScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: PortafolioViewModel= koinViewModel(),
    navController: NavController
) {

    val state = viewModel.state

    // 🔥 EFECTOS (igual que tu docente)
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {

                is PortafolioEffect.NavigateToDeposit -> {
                    navController.navigate(NavRoute.Deposit)
                }

                is PortafolioEffect.ShowError -> {
                    snackbarHostState.showSnackbar(
                        effect.message,
                        withDismissAction = true
                    )
                }
            }
        }
    }

    // 🎨 UI
    PortafolioContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}
