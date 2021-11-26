package com.josechocobar.moviestrivia.data.local

import com.josechocobar.moviestrivia.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceInterface {
    suspend fun getAllPopularMovies(): Flow<List<Movie>>
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(item: Movie)
}