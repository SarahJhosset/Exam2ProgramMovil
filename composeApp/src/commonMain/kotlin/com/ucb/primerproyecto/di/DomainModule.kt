package com.ucb.primerproyecto.di

import com.ucb.primerproyecto.deposit.domain.usecase.SaveDepositUseCase
import com.ucb.primerproyecto.dollar.domain.usecase.CreateDollarUseCase
import com.ucb.primerproyecto.dollar.domain.usecase.GetDollarListUsecase
import com.ucb.primerproyecto.github.domain.usecase.GetAvatarUseCase
import com.ucb.primerproyecto.movie.domain.usecase.GetMoviesUseCase
import com.ucb.primerproyecto.portafolio.domain.usecase.CheckDepositEnabledUseCase
import com.ucb.primerproyecto.portafolio.domain.usecase.CheckMaintenanceUseCase
import com.ucb.primerproyecto.portafolio.domain.usecase.GetPortafolioUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetAvatarUseCase)
    singleOf(::GetMoviesUseCase)

    singleOf(::GetDollarListUsecase)
    singleOf(::CreateDollarUseCase)

    singleOf(::GetPortafolioUseCase)

    singleOf(::CheckDepositEnabledUseCase)
    singleOf(::CheckMaintenanceUseCase)

    singleOf(::SaveDepositUseCase)

}
