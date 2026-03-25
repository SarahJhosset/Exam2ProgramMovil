package com.ucb.primerproyecto.moviedetail.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.ucb.primerproyecto.movie.domain.model.MovieModel

@Composable
fun MovieDetailScreen(movie: MovieModel) {

    Column {

        AsyncImage(
            model = movie.pathUrl,
            contentDescription = movie.title,
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = movie.title)

        Text(text = movie.description)
    }
}