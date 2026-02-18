package com.ucb.primerproyecto.di

import com.ucb.primerproyecto.increment.presentation.viewmodel.IncrementViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::IncrementViewModel)


}
