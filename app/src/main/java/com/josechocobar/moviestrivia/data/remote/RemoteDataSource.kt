package com.josechocobar.moviestrivia.data.remote

import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.data.model.MovieRequest

class RemoteDataSource() :RemoteDataSourceInt {
    override suspend fun getPopularMovies() : Resource<MovieRequest>{
        return Resource.Success(RetrofitService.webService.getPopularMovies())
    }
}