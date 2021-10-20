package com.josechocobar.moviestrivia.domain

import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.data.model.Movie
import com.josechocobar.moviestrivia.data.model.MovieRequest

interface IRepo {
    suspend fun getMovieList() : Resource<MovieRequest>
}