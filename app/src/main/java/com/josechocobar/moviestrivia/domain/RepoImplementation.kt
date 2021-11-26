package com.josechocobar.moviestrivia.domain

import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.data.local.LocalDatabaseDao
import com.josechocobar.moviestrivia.data.model.Movie
import com.josechocobar.moviestrivia.data.model.MovieRequest
import com.josechocobar.moviestrivia.data.remote.RemoteDataSourceInt
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RepoImplementation @Inject constructor(
    val remoteDataSource: RemoteDataSourceInt,
    private val localDao: LocalDatabaseDao
) : IRepo {
    override suspend fun getMovieList(): Resource<MovieRequest> {
        return remoteDataSource.getPopularMovies()
    }

    override suspend fun getMovieLocalList(): Flow<List<Movie>> {
        return localDao.getMovieList()
    }
}