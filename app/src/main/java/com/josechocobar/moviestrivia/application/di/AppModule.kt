package com.josechocobar.moviestrivia.application.di

import android.content.Context
import androidx.room.Room
import com.josechocobar.moviestrivia.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app:Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "movie_table"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(appDatabase:AppDatabase) = appDatabase.localDatabaseDao()

}