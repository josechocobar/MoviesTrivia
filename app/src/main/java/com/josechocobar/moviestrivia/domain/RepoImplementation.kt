package com.josechocobar.moviestrivia.domain

import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.data.model.MovieRequest
import com.josechocobar.moviestrivia.data.remote.RemoteDataSource
import javax.inject.Inject


class RepoImplementation @Inject constructor(val remoteDataSource: RemoteDataSource):IRepo{
    override suspend fun getMovieList(): Resource<MovieRequest> {
        return remoteDataSource.getPopularMovies()
    }
}