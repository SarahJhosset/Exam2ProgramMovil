package com.ucb.primerproyecto.signin.presentation.screen


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ucb.primerproyecto.signin.presentation.state.SigninEvent
import com.ucb.primerproyecto.signin.presentation.viewmodel.SigninViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import proyectoucb.composeapp.generated.resources.Res
import proyectoucb.composeapp.generated.resources.sign_in_btn
import proyectoucb.composeapp.generated.resources.sign_in_email
import proyectoucb.composeapp.generated.resources.sign_in_password
import proyectoucb.composeapp.generated.resources.sign_in_title

@Composable
fun SigninScreen(viewModel: SigninViewModel = koinViewModel()) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("")}
    Column {
        Text(stringResource(Res.string.sign_in_title))
        TextField(
            onValueChange = {
                email = it
                viewModel.onEvent(SigninEvent.OnEmailChanged(it))
            },
            value = email,
            label = {
                Text(stringResource(Res.string.sign_in_email))
            }
        )
        TextField(
            onValueChange = {
                password = it
                viewModel.onEvent(SigninEvent.OnPasswordChanged(it))
            },
            value = password,
            label = {
                Text(stringResource(Res.string.sign_in_password))
            }
        )
        Button(
            onClick = {
                viewModel.onEvent(SigninEvent.OnClick)
            }
        ) {
            Text(stringResource(Res.string.sign_in_btn))
        }
    }
}