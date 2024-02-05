package com.anshabunin.weatherapplication.di

import com.anshabunin.weatherapplication.domain.Impl.NetworkRepositoryImpl
import com.anshabunin.weatherapplication.domain.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    @Named("weatherApi")
    abstract fun networkRepository(
        repositoryImpl: NetworkRepositoryImpl
    ): NetworkRepository

}