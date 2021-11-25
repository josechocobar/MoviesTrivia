package com.josechocobar.moviestrivia.application.di

import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.data.remote.RemoteDataSource
import com.josechocobar.moviestrivia.data.remote.RemoteDataSourceInt
import com.josechocobar.moviestrivia.domain.IRepo
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
    fun bindRepoImpl(dataSource: RemoteDataSourceInt):RepoImplementation  {
        return RepoImplementation(dataSource)
    }


    @Provides
    @ViewModelScoped
    fun bindDatasourceImpl(): RemoteDataSourceInt {
        return RemoteDataSource()
    }



}