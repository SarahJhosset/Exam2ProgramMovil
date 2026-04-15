package com.ucb.primerproyecto.portafolio.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucb.primerproyecto.portafolio.presentation.state.Holding
import com.ucb.primerproyecto.portafolio.presentation.state.PortafolioEvent

@Composable
fun HoldingItem(
    holding: Holding,
    onEvent: (PortafolioEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clickable {
                onEvent(PortafolioEvent.OnCoinClick(holding.name))
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = holding.name)
            Text(text = "Cantidad: ${holding.amount}")
            Text(text = "Precio: $${holding.price}")
        }
    }
}