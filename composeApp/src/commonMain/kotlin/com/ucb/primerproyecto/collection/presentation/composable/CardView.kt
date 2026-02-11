package com.ucb.primerproyecto.collection.presentation.composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun CardView(
    text: String,
    image: DrawableResource
) {
    Box(
        modifier = Modifier.fillMaxWidth().background(Color.Red)
    ) {
        Image(
            modifier = Modifier.size(347.dp, 222.dp),
            painter = painterResource(image),
            contentDescription = null
        )
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {

            }

        ) {
            Text(text)
        }

    }
}