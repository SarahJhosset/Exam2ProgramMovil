package com.ucb.primerproyecto.collection.presentation.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ucb.primerproyecto.collection.presentation.composable.CardView
import org.jetbrains.compose.resources.painterResource
import proyectoucb.composeapp.generated.resources.Res
import proyectoucb.composeapp.generated.resources.children
import proyectoucb.composeapp.generated.resources.men
import proyectoucb.composeapp.generated.resources.shopping_cart
import proyectoucb.composeapp.generated.resources.women

@Composable
fun CollectionScreen() {
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.background(Color.Blue)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text("Explore", fontSize = 14.sp)
                Text("Collections", fontWeight = FontWeight.Bold)
            }
            Image(
                modifier = Modifier.size(28.dp),
                painter = painterResource(Res.drawable.shopping_cart),
                contentDescription = null
            )
        }
        CardView("Children", Res.drawable.children)
        CardView("Women", Res.drawable.women)
        CardView("Men", Res.drawable.men)

    }
}