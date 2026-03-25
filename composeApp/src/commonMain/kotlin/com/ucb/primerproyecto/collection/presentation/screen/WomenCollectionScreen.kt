/*
* package com.ucb.primerproyecto.collection.presentation.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ucb.primerproyecto.collection.presentation.composable.CollectionItem
import org.jetbrains.compose.resources.painterResource
import proyectoucb.composeapp.generated.resources.Res
import proyectoucb.composeapp.generated.resources.modelo1
import proyectoucb.composeapp.generated.resources.modelo2
import proyectoucb.composeapp.generated.resources.modelo3
import proyectoucb.composeapp.generated.resources.modelo4
import proyectoucb.composeapp.generated.resources.modelo5
import proyectoucb.composeapp.generated.resources.modelo6
import proyectoucb.composeapp.generated.resources.modelo7
import proyectoucb.composeapp.generated.resources.modelo8
import proyectoucb.composeapp.generated.resources.shopping_cart


@Composable
fun WomenCollectionScreen() {

    val images = listOf(
        Res.drawable.modelo1,
        Res.drawable.modelo2,
        Res.drawable.modelo3,
        Res.drawable.modelo4,
        Res.drawable.modelo5,
        Res.drawable.modelo6,
        Res.drawable.modelo7,
        Res.drawable.modelo8
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {

        // 🔹 HEADER
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(42.dp)
                    .background(Color(0xFF1E1E1E), CircleShape)
            ) {
                Image(
                    painter = painterResource(Res.drawable.shopping_cart),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Women's",
                    color = Color.White,
                    fontSize = 16.sp
                )
                Text(
                    text = "Collections",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(42.dp)
                    .background(Color(0xFF1E1E1E), CircleShape)
            ) {
                Image(
                    painter = painterResource(Res.drawable.shopping_cart),
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🔹 GRID DE IMAGENES
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(images) { image ->
                CollectionItem(image)
            }
        }
    }
}*/