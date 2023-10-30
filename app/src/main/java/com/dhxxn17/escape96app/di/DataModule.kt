package com.dhxxn17.escape96app.di

import com.dhxxn17.data.datasource.HomeThemeDataSource
import com.dhxxn17.data.datasource.HomeThemeDataSourceImpl
import com.dhxxn17.data.repository.HomeRepositoryImpl
import com.dhxxn17.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindHomeDataSource(
        source: HomeThemeDataSourceImpl
    ): HomeThemeDataSource

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        repositoryImpl: HomeRepositoryImpl
    ): HomeRepository
}