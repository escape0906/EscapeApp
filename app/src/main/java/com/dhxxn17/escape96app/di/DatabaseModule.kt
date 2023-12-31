package com.dhxxn17.escape96app.di

import android.app.Application
import androidx.room.Room
import com.dhxxn17.data.local.AppDatabase
import com.dhxxn17.data.local.AppDatabase.Companion.DB_NAME
import com.dhxxn17.data.local.LikeDao
import com.dhxxn17.data.local.SuccessDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideLikeDao(appDatabase: AppDatabase): LikeDao = appDatabase.likeDao()

    @Provides
    @Singleton
    fun provideSuccessDao(appDatabase: AppDatabase): SuccessDao = appDatabase.successDao()
}