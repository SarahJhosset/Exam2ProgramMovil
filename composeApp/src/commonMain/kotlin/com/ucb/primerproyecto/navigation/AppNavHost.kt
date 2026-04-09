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
import com.ucb.primerproyecto.dollar.presentation.screen.DollarScreen
import com.ucb.primerproyecto.github.presentation.screen.GithubScreen
import com.ucb.primerproyecto.movie.domain.model.MovieModel
import com.ucb.primerproyecto.movie.presentation.screen.MovieScreen
import com.ucb.primerproyecto.moviedetail.presentation.screen.MovieDetailScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }//agregar para el manejo de errores

    Scaffold(//manejo de errores
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        NavHost(navController = navController, startDestination = NavRoute.Dollar) {
            composable<NavRoute.Profile> {
            }
            composable<NavRoute.ProfileEdit> {
            }
            composable<NavRoute.Github> {
                GithubScreen()
            }
            composable<NavRoute.Movies> {
                MovieScreen(
                    snackbarHostState = snackbarHostState,//manejo de errores
                    navController = navController)
            }
            composable<NavRoute.MovieDetail> { backStack ->
                val args = backStack.toRoute<NavRoute.MovieDetail>()

                MovieDetailScreen(
                    movie = MovieModel(
                        title = args.title,
                        pathUrl = args.image,
                        description = args.description
                    )
                )
            }

            composable<NavRoute.Dollar> {
                DollarScreen()
            }

        }
    }


}
