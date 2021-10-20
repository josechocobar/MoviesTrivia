package com.josechocobar.moviestrivia.data.remote

import com.josechocobar.moviestrivia.data.model.Movie
import com.josechocobar.moviestrivia.data.model.MovieRequest
import retrofit2.http.GET
import retrofit2.http.Headers

interface WebService {

    @GET("popular?api_key=0a8956c5ce74c43236f60c0fe039e3c1&language=en-US&page=1")
    suspend fun getPopularMovies():MovieRequest

    //@GET("/3/movie/")
    //suspend fun getPopulars(@Query(value = "$API_KEY"))
}