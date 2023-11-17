package com.dhxxn17.escape96app.di

import com.dhxxn17.data.datasource.DetailThemeDataSource
import com.dhxxn17.data.datasource.DetailThemeDataSourceImpl
import com.dhxxn17.data.datasource.HomeThemeDataSource
import com.dhxxn17.data.datasource.HomeThemeDataSourceImpl
import com.dhxxn17.data.datasource.SearchDataSource
import com.dhxxn17.data.datasource.SearchDataSourceImpl
import com.dhxxn17.data.repository.DetailRepositoryImpl
import com.dhxxn17.data.repository.HomeRepositoryImpl
import com.dhxxn17.data.repository.SearchRepositoryImpl
import com.dhxxn17.domain.repository.DetailRepository
import com.dhxxn17.domain.repository.HomeRepository
import com.dhxxn17.domain.repository.SearchRepository
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

    @Binds
    @Singleton
    abstract fun bindDetailDataSource(
        source: DetailThemeDataSourceImpl
    ): DetailThemeDataSource

    @Binds
    @Singleton
    abstract fun bindDetailRepository(
        repository: DetailRepositoryImpl
    ): DetailRepository

    @Binds
    @Singleton
    abstract fun bindSearchRepository(
        repository: SearchRepositoryImpl
    ): SearchRepository

    @Binds
    @Singleton
    abstract fun bindSearchDataSource(
        source: SearchDataSourceImpl
    ): SearchDataSource
}