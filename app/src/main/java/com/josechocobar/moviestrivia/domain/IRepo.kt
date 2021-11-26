package com.josechocobar.moviestrivia.domain

import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.data.model.Movie
import com.josechocobar.moviestrivia.data.model.MovieRequest
import kotlinx.coroutines.flow.Flow

interface IRepo {
    suspend fun getMovieList() : Resource<MovieRequest>
    suspend fun getMovieLocalList() : Flow<List<Movie>>
}