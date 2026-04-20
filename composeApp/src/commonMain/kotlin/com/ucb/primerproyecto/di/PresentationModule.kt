package com.ucb.primerproyecto.di
import com.ucb.primerproyecto.deposit.presentation.viewmodel.DepositViewModel
import com.ucb.primerproyecto.dollar.presentation.viewmodel.DollarViewModel
import com.ucb.primerproyecto.github.presentation.viewmodel.GitHubViewModel
import com.ucb.primerproyecto.increment.presentation.viewmodel.IncrementViewModel
import com.ucb.primerproyecto.login.presentation.viewmodel.LoginViewModel
import com.ucb.primerproyecto.movie.presentation.viewmodel.MovieViewModel
import com.ucb.primerproyecto.portafolio.presentation.viewmodel.PortafolioViewModel
import com.ucb.primerproyecto.product_detail.presentation.screen.ProductDetailScreen
import com.ucb.primerproyecto.product_detail.presentation.viewmodel.ProductDetailViewModel
import com.ucb.primerproyecto.signin.presentation.viewmodel.SigninViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::IncrementViewModel)
    viewModelOf(::ProductDetailViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::GitHubViewModel)
    viewModelOf(::SigninViewModel)
    viewModelOf(::MovieViewModel)

    viewModelOf(::DollarViewModel)

    viewModelOf(::PortafolioViewModel)
    viewModelOf(::DepositViewModel)
}
