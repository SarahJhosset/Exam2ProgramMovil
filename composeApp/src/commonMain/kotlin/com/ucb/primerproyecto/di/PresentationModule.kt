package com.ucb.primerproyecto.di
import com.ucb.primerproyecto.deposit.presentation.viewmodel.DepositViewModel
import com.ucb.primerproyecto.dollar.presentation.viewmodel.DollarViewModel
import com.ucb.primerproyecto.login.presentation.viewmodel.LoginViewModel
import com.ucb.primerproyecto.movie.presentation.viewmodel.MovieViewModel
import com.ucb.primerproyecto.portafolio.presentation.viewmodel.PortafolioViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::MovieViewModel)

    viewModelOf(::DollarViewModel)

    viewModelOf(::PortafolioViewModel)
    viewModelOf(::DepositViewModel)
}
