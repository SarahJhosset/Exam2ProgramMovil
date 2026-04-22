package com.ucb.primerproyecto.deposit.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ucb.primerproyecto.deposit.presentation.state.DepositEffect
import com.ucb.primerproyecto.deposit.presentation.state.DepositEvent
import com.ucb.primerproyecto.deposit.presentation.viewmodel.DepositViewModel
import com.ucb.primerproyecto.navigation.NavRoute
import org.jetbrains.compose.resources.stringResource
import proyectoucb.composeapp.generated.resources.Res
import proyectoucb.composeapp.generated.resources.deposit_amount
import proyectoucb.composeapp.generated.resources.deposit_save
import proyectoucb.composeapp.generated.resources.deposit_title


@Composable
fun DepositScreen(
    snackbarHostState: SnackbarHostState,
    navController: NavController,
    viewModel: DepositViewModel
) {

    val state = viewModel.state

    // 🔥 EXACTAMENTE como tu docente
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {

                is DepositEffect.NavigateToHome -> {
                    navController.navigate(NavRoute.Portafolio)
                }

                is DepositEffect.ShowError -> {
                    snackbarHostState.showSnackbar(
                        effect.message,
                        withDismissAction = true
                    )
                }

                is DepositEffect.ShowSuccess -> {
                    snackbarHostState.showSnackbar(
                        effect.message,
                        withDismissAction = true
                    )
                }
            }
        }
    }

    // UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(stringResource(Res.string.deposit_title))
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.amount,
            onValueChange = {
                viewModel.onEvent(DepositEvent.OnAmountChange(it))
            },
            label = { Text(stringResource(Res.string.deposit_amount)) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                viewModel.onEvent(DepositEvent.OnSaveDeposit)
            }
        ) {
            Text(stringResource(Res.string.deposit_save))
        }
    }
}