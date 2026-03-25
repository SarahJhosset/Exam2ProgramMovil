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
): ViewModel() {
    private val _state = MutableStateFlow(MovieUiState())
    val state = _state.asStateFlow()
    private val _effects = MutableSharedFlow<MovieEffect>()
    val effects = _effects.asSharedFlow()

    private fun emit(effect: MovieEffect) {
        viewModelScope.launch {
            _effects.emit(effect)
        }
    }
    init {
        onEvent(MovieEvent.OnLoadMovies)
    }
    fun onEvent(event: MovieEvent) {
        when(event) {

            MovieEvent.OnLoadMovies -> loadMovies()

            is MovieEvent.OnRateMovie -> {
                rateMovie(event.index, event.rating)
            }

            is MovieEvent.OnClickMovie -> {
                viewModelScope.launch {
                    _effects.emit(
                        MovieEffect.NavigateToDetail(event.movie)
                    )
                }
            }
        }
    }

    private fun loadMovies() {
        _state.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            val list = moviesUseCase.invoke()
            _state.update {
                it.copy(list = list, isLoading = false)
            }
        }

    }
    private fun rateMovie(index: Int, rating: Int) {
        _state.update { current ->

            val newList = current.list.toMutableList()
            val movie = newList[index]

            val newRating =
                if (movie.rating == rating) 0 else rating

            newList[index] = movie.copy(rating = newRating)

            current.copy(list = newList)
        }
    }
}
