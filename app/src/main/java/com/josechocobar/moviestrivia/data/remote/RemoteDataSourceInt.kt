package com.josechocobar.moviestrivia.data.remote

import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.data.model.MovieRequest

interface RemoteDataSourceInt {
    suspend fun getPopularMovies() : Resource<MovieRequest>
}