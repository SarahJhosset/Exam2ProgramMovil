package com.ucb.primerproyecto.portafolio.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ucb.primerproyecto.portafolio.presentation.state.PortafolioEvent
import com.ucb.primerproyecto.portafolio.presentation.state.PortafolioUiState
import androidx.compose.foundation.lazy.items

@Composable
fun PortafolioContent(
    state: PortafolioUiState,
    onEvent: (PortafolioEvent) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {

        Text("Mi Portafolio Cripto", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Balance Total")
                Text("$${state.balanceTotal}", style = MaterialTheme.typography.headlineMedium)
                Text("+${state.changePercentage}% hoy", color = Color.Green)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Stables: $${state.stables}")
                Text("Ganancias: $${state.ganancias}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Holding", style = MaterialTheme.typography.titleMedium)

        LazyColumn {
            items(state.holdings) { coin ->
                HoldingItem(coin, onEvent)
            }
        }

        FloatingActionButton(
            onClick = { onEvent(PortafolioEvent.OnAddClick) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("+")
        }
    }
}

