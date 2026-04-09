package com.ucb.primerproyecto.di

import com.ucb.primerproyecto.dollar.data.datasource.DollarLocalDataSource
import com.ucb.primerproyecto.dollar.data.repository.DollarRepositoryImpl
import com.ucb.primerproyecto.dollar.data.service.DbService
import com.ucb.primerproyecto.dollar.domain.repository.DollarRepository
import com.ucb.primerproyecto.github.data.datasource.GithubRemoteDataSource
import com.ucb.primerproyecto.github.data.repository.GithubRepositoryImpl
import com.ucb.primerproyecto.github.data.service.GithubApiService
import com.ucb.primerproyecto.github.domain.repository.GithubRepository
import com.ucb.primerproyecto.movie.data.datasource.MovieRemoteDataSource
import com.ucb.primerproyecto.movie.data.repository.MovieRepositoryImpl
import com.ucb.primerproyecto.movie.data.service.MovieApiService
import com.ucb.primerproyecto.movie.domain.repository.MovieRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::GithubApiService).bind<GithubRemoteDataSource>()
    singleOf(::GithubRepositoryImpl).bind<GithubRepository>()
    singleOf(::MovieRepositoryImpl).bind<MovieRepository>()
    singleOf(::MovieApiService).bind<MovieRemoteDataSource>()

    singleOf(::DollarRepositoryImpl).bind<DollarRepository>()
    singleOf(::DbService).bind<DollarLocalDataSource>()
}
