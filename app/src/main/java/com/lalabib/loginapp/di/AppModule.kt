package com.lalabib.loginapp.di

import com.lalabib.loginapp.data.remote.Repository
import com.lalabib.loginapp.domain.usecase.UserInteractor
import com.lalabib.loginapp.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideListUserUseCase(repository: Repository) : UserUseCase =
        UserInteractor(repository)
}