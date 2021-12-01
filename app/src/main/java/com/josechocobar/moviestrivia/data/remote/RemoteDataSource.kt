package com.josechocobar.moviestrivia.data.remote

import com.josechocobar.moviestrivia.data.model.MovieRequest

class RemoteDataSource() :RemoteDataSourceInt {
    override suspend fun getPopularMovies() : MovieRequest{
        return RetrofitService.webService.getPopularMovies()
    }
}