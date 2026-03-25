package com.ucb.primerproyecto.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucb.primerproyecto.movie.domain.model.MovieModel
import com.ucb.primerproyecto.movie.domain.usecase.GetMoviesUseCase
import com.ucb.primerproyecto.movie.presentation.state.MovieEffect
import com.ucb.primerproyecto.movie.presentation.state.MovieEvent
import com.ucb.primerproyecto.movie.presentation.state.MovieUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.invoke

class MovieViewModel(
    val moviesUseCase: GetMoviesUseCase
    //obtiene la lista de peliculas desde el dominio
): ViewModel() {
    private val _state = MutableStateFlow(MovieUiState())
    //estado interno solo el viwmodel lo puede modificar
    val state = _state.asStateFlow()
    //estado expuesto solo lectura para la UI
    private val _effects = MutableSharedFlow<MovieEffect>()
    //flujo interno de efectos
    val effects = _effects.asSharedFlow()
    //la UI escucha estos efectos

    init {//se eejcuta automaticamente cuando se crea el view model
        onEvent(MovieEvent.OnLoadMovies)
    }
    //FUNCION PRINCIPAL
    fun onEvent(event: MovieEvent) {//aqui estaran todos los eventos
        when(event) {//decide que hacer segun el evento

            MovieEvent.OnLoadMovies -> loadMovies()//llama a la funcion que trae datos

            is MovieEvent.OnRateMovie -> {//recibe indice de la pelicula,y el rating
                rateMovie(event.index, event.rating)
            }

            is MovieEvent.OnClickMovie -> {//solo emite efecto hace el click
                emit(MovieEffect.NavigateToDetail(event.movie))
            }
        }
    }

    private fun loadMovies() {//carga peliculas desde el backend
        _state.update {//activa el loading
            it.copy(isLoading = true)
        }
        viewModelScope.launch {//ejecuta codigo en segundo plano
            val list = moviesUseCase.invoke()//llama al caso de uso, obtiene lista
            _state.update {//guarda datos y quita el loading
                it.copy(list = list, isLoading = false)
            }
        }
    }
    private fun rateMovie(index: Int, rating: Int) {//actualiza la calificacion de una pelicula
        _state.update { current ->//obtiene el extado actual

            val newList = current.list.toMutableList()//copia la lista(importante porq pasa de inmutable a mutable
            val movie = newList[index]//obtiene la pelicula

            val newRating =//aqui esta la logica de apretar el mismo boton o otro numero
                if (movie.rating == rating){
                    0
                } else{
                    rating
                }

            newList[index] = movie.copy(rating = newRating)//reemplazar la pelicula con un nuevo rating

            current.copy(list = newList)//devuelve nuevo estado actualizado
        }
    }
    private fun emit(effect: MovieEffect) {//sirve para enviar efectos
        viewModelScope.launch {//envia efecto a la UI
            _effects.emit(effect)
        }
    }
}
