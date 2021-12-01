package com.josechocobar.moviestrivia.domain

import com.josechocobar.moviestrivia.data.model.Movie
import com.josechocobar.moviestrivia.data.model.MovieRequest
import com.josechocobar.moviestrivia.data.model.Video
import kotlinx.coroutines.flow.Flow

interface IRepo {
    suspend fun getMovieList() : MovieRequest
    suspend fun getMovieLocalList() : Flow<List<Movie>>
    suspend fun getMovieById(idroom:Int):Movie
    suspend fun getTrailer(id:Int):Video
}