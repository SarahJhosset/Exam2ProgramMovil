package com.ucb.primerproyecto.profile.presentation.screen
/*
*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucb.primerproyecto.profile.presentation.state.ProfileEvent
import com.ucb.primerproyecto.profile.presentation.viewmodel.ProfileViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import proyectoucb.composeapp.generated.resources.Res
import proyectoucb.composeapp.generated.resources.women

@Composable

fun ProfileScreen(viewModel: ProfileViewModel= koinViewModel ()){
    Box(){
        Box(){
            Image(
                painterResource(resource=Res.drawable.women),
                contentDescription = null,
                modifier = Modifier.size(347.dp, 222.dp)
            )
        }
        Text(stringResource(Res.string.name))
        Text(stringResource(Res.string.email))
        Text(stringResource(Res.string.phone))
        Text(stringResource(Res.string.summary))

        Button(
            onClick = {
                viewModel.onEvent(ProfileEvent.OnClick)
            }) {

            Text(stringResource(Res.string.edit_btn))
        }

    }
}*/