package com.ucb.primerproyecto.movie.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ucb.primerproyecto.movie.presentation.composable.CardMovie
import com.ucb.primerproyecto.movie.presentation.state.MovieEffect
import com.ucb.primerproyecto.movie.presentation.state.MovieEvent
import com.ucb.primerproyecto.movie.presentation.viewmodel.MovieViewModel
import com.ucb.primerproyecto.navigation.NavRoute
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun MovieScreen(viewModel: MovieViewModel = koinViewModel(),navController: NavController ) {

    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when(effect) {
                is MovieEffect.NavigateToDetail -> {
                    navController.navigate(
                        NavRoute.MovieDetail(
                            title = effect.movie.title,
                            image = effect.movie.pathUrl,
                            description = effect.movie.description
                        )
                    )
                }
                is MovieEffect.ShowError -> {
                    // puedes mostrar un snackbar
                    println(effect.message)
                }
            }
        }
    }
    if(state.value.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.value.list.size) { index ->

                val movie = state.value.list[index]
                CardMovie(
                    model = movie,
                    onRate = { rating ->
                        viewModel.onEvent(
                            MovieEvent.OnRateMovie(index, rating)
                        )
                    },
                    onClick = {
                        viewModel.onEvent(
                            MovieEvent.OnClickMovie(movie)
                        )
                    }
                )
            }
        }
    }
}