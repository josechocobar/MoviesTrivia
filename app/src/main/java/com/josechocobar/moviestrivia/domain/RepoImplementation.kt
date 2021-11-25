package com.josechocobar.moviestrivia.domain

import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.data.model.MovieRequest
import com.josechocobar.moviestrivia.data.remote.RemoteDataSourceInt
import javax.inject.Inject


class RepoImplementation @Inject constructor(val remoteDataSource: RemoteDataSourceInt):IRepo{
    override suspend fun getMovieList(): Resource<MovieRequest> {
        return remoteDataSource.getPopularMovies()
    }
}