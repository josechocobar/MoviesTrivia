package com.josechocobar.moviestrivia.application.di

import com.josechocobar.moviestrivia.data.local.LocalDatabaseDao
import com.josechocobar.moviestrivia.data.remote.RemoteDataSource
import com.josechocobar.moviestrivia.data.remote.RemoteDataSourceInt
import com.josechocobar.moviestrivia.domain.RepoImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ActivityModule {
    @Provides
    @ViewModelScoped
    fun bindRepoImpl(dataSource: RemoteDataSourceInt,localDao:LocalDatabaseDao):RepoImplementation  {
        return RepoImplementation(dataSource,localDao)
    }


    @Provides
    @ViewModelScoped
    fun bindDatasourceImpl(): RemoteDataSourceInt {
        return RemoteDataSource()
    }




}