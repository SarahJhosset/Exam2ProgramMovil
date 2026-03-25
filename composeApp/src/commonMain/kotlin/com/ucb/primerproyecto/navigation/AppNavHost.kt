package com.ucb.primerproyecto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ucb.primerproyecto.github.presentation.screen.GithubScreen
import com.ucb.primerproyecto.movie.domain.model.MovieModel
import com.ucb.primerproyecto.movie.presentation.screen.MovieScreen
import com.ucb.primerproyecto.moviedetail.presentation.screen.MovieDetailScreen

@Composable
fun AppNavHost() {


    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Movies) {
        composable<NavRoute.Profile> {
        }
        composable<NavRoute.ProfileEdit> {

        }
        composable<NavRoute.Github> {
            GithubScreen()
        }

        composable<NavRoute.Movies> {
            MovieScreen(navController = navController)
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

    }
}
