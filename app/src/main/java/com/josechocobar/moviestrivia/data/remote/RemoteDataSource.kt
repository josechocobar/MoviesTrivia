package com.josechocobar.moviestrivia.data.remote

import com.josechocobar.moviestrivia.data.model.MovieRequest
import com.josechocobar.moviestrivia.data.model.Video

class RemoteDataSource() :RemoteDataSourceInt {
    override suspend fun getPopularMovies() : MovieRequest{
        return RetrofitService.webService.getPopularMovies()
    }
    override suspend fun getTrailer(id:Int): Video{
        return RetrofitService.webService.getVideos(id)
    }
}