package com.josechocobar.moviestrivia.data.remote

import com.josechocobar.moviestrivia.data.model.MovieRequest
import com.josechocobar.moviestrivia.data.model.Video

interface RemoteDataSourceInt {
    suspend fun getPopularMovies() : MovieRequest
    suspend fun getTrailer(id:Int):Video
}