package com.ucb.primerproyecto.collection.presentation.composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun CollectionItem(
    image: DrawableResource
) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // cuadrado perfecto
            .clip(RoundedCornerShape(20.dp))
    )
}