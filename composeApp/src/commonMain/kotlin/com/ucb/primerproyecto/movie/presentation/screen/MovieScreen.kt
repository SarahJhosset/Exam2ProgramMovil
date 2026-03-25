package com.ucb.primerproyecto.movie.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
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
//esta pantalla principal tiene acceso a viewModel y la navegacion
fun MovieScreen(
    snackbarHostState: SnackbarHostState,//manejo de errores
    viewModel: MovieViewModel = koinViewModel(),
    navController: NavController
) {

    val state = viewModel.state.collectAsState()
    // 🔄 Observa el estado (StateFlow)
    // 👉 Cada vez que cambia, la UI se vuelve a dibujar automáticamente

    //LOS EFECTOS
    LaunchedEffect(Unit) {//ejecuta esto SOLO UNA VEZ cuando entra la pantalla
        viewModel.effects.collect { effect ->//escucha los EFECTOS del viewModel, osea espera acciones
            when(effect) {//decide que hacer segun el efecto
                is MovieEffect.NavigateToDetail -> {//navegacion
                    //en esat parte se navega a otra pantalla, usa los datos de la pelicula
                    navController.navigate(
                        NavRoute.MovieDetail(
                            title = effect.movie.title,
                            image = effect.movie.pathUrl,
                            description = effect.movie.description
                        )
                    )
                }
                is MovieEffect.ShowError -> {//manejo de errores
                    snackbarHostState.showSnackbar(
                        effect.message,
                        withDismissAction = true
                    )
                }
            }
        }
    }
    if(state.value.isLoading) {//si esta cargando muestra el spinner
        CircularProgressIndicator()
    } else {//si ya cargo, muestra lista de peliculas
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),//2 columnas
            contentPadding = PaddingValues(8.dp),//espacio interno
            horizontalArrangement = Arrangement.spacedBy(8.dp),//separacion de items
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.value.list.size) { index ->//recorre la lista

                val movie = state.value.list[index]//obtiene cada pelicula
                CardMovie(//pasa los datos a cardmovie
                    model = movie,
                    onRate = { rating ->
                        viewModel.onEvent(
                            MovieEvent.OnRateMovie(index, rating)
                        )
                    },
                    onClick = {//click en imagen
                        viewModel.onEvent(//manda evento
                            MovieEvent.OnClickMovie(movie)//view model emite effect
                        )
                    }
                )
            }
        }
    }
}