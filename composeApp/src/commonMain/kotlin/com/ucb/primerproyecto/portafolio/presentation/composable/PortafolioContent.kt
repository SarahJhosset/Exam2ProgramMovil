package com.ucb.primerproyecto.portafolio.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucb.primerproyecto.portafolio.presentation.state.PortafolioEvent
import com.ucb.primerproyecto.portafolio.presentation.state.PortafolioUiState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn

@Composable
fun PortafolioContent(
    state: PortafolioUiState,
    onEvent: (PortafolioEvent) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {

        Text("Mi Portafolio Cripto", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // 💰 BALANCE TOTAL
        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Balance Total")

                Text(
                    "$${state.totalBalance}",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Historial de depósitos", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        // 📋 LISTA REAL DESDE FIREBASE
        LazyColumn {
            items(state.deposits) { deposit ->
                DepositCard(deposit)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ➕ BOTÓN
        FloatingActionButton(
            onClick = { onEvent(PortafolioEvent.OnAddClick) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("+")
        }
    }
}

