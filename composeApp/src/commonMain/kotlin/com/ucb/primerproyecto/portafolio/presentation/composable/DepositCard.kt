package com.ucb.primerproyecto.portafolio.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucb.primerproyecto.deposit.domain.model.DepositModel
import org.jetbrains.compose.resources.stringResource
import proyectoucb.composeapp.generated.resources.Res
import proyectoucb.composeapp.generated.resources.deposit_card_title
import proyectoucb.composeapp.generated.resources.deposit_timestamp

@Composable
fun DepositCard(deposit: DepositModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(stringResource(Res.string.deposit_card_title))

            Text(
                text = "${deposit.amount} ${deposit.currency}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "${stringResource(Res.string.deposit_timestamp)}: ${deposit.timestamp}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}