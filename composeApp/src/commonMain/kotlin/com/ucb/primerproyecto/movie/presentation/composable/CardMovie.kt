package com.ucb.primerproyecto.movie.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ucb.primerproyecto.movie.domain.model.MovieModel

@Composable
fun CardMovie(model: MovieModel ,onRate: (Int) -> Unit,
              onClick: () -> Unit) {

    val colors = MaterialTheme.colorScheme

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {

            AsyncImage(
                model = model.pathUrl,
                contentDescription = model.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f) // formato poster
                    .clickable{println("click detectado")
                        onClick()}
            )

            Column (
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = model.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row {
                    for (i in 1..5) {
                        Text(
                            text = if (i <= model.rating) "★" else "☆",
                            fontSize = 24.sp,
                            modifier = Modifier.clickable {
                                onRate(i)
                            }
                        )
                    }
                }
            }
        }
    }
}