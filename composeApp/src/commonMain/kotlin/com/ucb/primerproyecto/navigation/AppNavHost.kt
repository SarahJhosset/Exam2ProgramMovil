package com.ucb.primerproyecto.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ucb.primerproyecto.deposit.presentation.screen.DepositScreen
import com.ucb.primerproyecto.portafolio.presentation.screen.PortafolioScreen
import com.ucb.primerproyecto.dollar.presentation.screen.DollarScreen
import com.ucb.primerproyecto.movie.presentation.screen.MovieScreen
import com.ucb.primerproyecto.debug.DebugScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavHost() {
    val navController    = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
        NavHost(navController = navController, startDestination = NavRoute.Portafolio) {

            composable<NavRoute.Profile> { }
            composable<NavRoute.ProfileEdit> { }

            composable<NavRoute.Movies> {
                MovieScreen(
                    snackbarHostState = snackbarHostState,
                    navController     = navController
                )
            }
            composable<NavRoute.Dollar> {
                DollarScreen()
            }
            composable<NavRoute.Portafolio> {
                PortafolioScreen(
                    snackbarHostState = snackbarHostState,
                    viewModel         = koinViewModel(),
                    navController     = navController
                )
            }
            composable<NavRoute.Deposit> {
                DepositScreen(
                    snackbarHostState = snackbarHostState,
                    viewModel         = koinViewModel(),
                    navController     = navController
                )
            }

            composable<NavRoute.Debug> {
                DebugScreen()
            }
        }
    }
}
