package com.lalabib.loginapp.di

import com.lalabib.loginapp.data.remote.Repository
import com.lalabib.loginapp.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, PreferencesModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(listUserRepository: Repository) : IUserRepository
}