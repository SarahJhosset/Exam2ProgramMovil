package com.ucb.primerproyecto.movie.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun CardMovie(
    model: MovieModel ,//datos de la pelicula
    onRate: (Int) -> Unit,//funcion que se ejecuta cuando el usuario califica
    onClick: () -> Unit//funcion q se ejecuta cuando el usuario hace click
) {
    val colors = MaterialTheme.colorScheme

    Card(
        modifier = Modifier
            .fillMaxWidth(),//la tarjeta ocupa  el ancho disponible
        shape = RoundedCornerShape(16.dp),//bordes redondeados
        elevation = CardDefaults.cardElevation(6.dp)//sombra para dar efecto de tarjeta
    ) {
        Column {//organiza los elementos en vertical
            AsyncImage(
                model = model.pathUrl,//url de la imagen
                contentDescription = model.title,//descripcion para accesibilidad
                contentScale = ContentScale.Crop,//recorta las img para que queden bien
                modifier = Modifier
                    .fillMaxWidth()//ocupa todo el ancho
                    .aspectRatio(2f / 3f) // formato poster
                    .clickable{ onClick()}//al hacer click ejecuta navegacion
            )

            Column (
                modifier = Modifier.padding(8.dp)//espacio interno
            ) {
                Text(
                    text = model.title,//titulo de la pelicula
                    style = MaterialTheme.typography.bodyMedium,//estilo de texto
                    fontWeight = FontWeight.SemiBold,//un poco mas grueso
                    maxLines = 2,//maximo 2 lineas
                    overflow = TextOverflow.Ellipsis //... si esq el texto es muy largo
                )
                Row {//contenedor horizontal
                    for (i in 1..5) {//repite 5 veces
                        Text(
                            text = if (i <= model.rating) "★" else "☆",
                            // ⭐ Si ya está calificada → estrella llena
                            // ☆ Si no → estrella vacía
                            fontSize = 24.sp,//tama;o grande
                            modifier = Modifier.clickable {
                                onRate(i)//al hhacer click envia el numero de estrella
                            }
                        )
                    }
                }
            }
        }
    }
}