package com.josechocobar.moviestrivia.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.josechocobar.moviestrivia.application.InternetConnectionChecker
import com.josechocobar.moviestrivia.data.model.Movie
import com.josechocobar.moviestrivia.data.model.Trailer
import com.josechocobar.moviestrivia.domain.RepoImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repoImplementation: RepoImplementation
) : ViewModel() {
    var title: String =""

    fun getMovieByName(name:String){
        title=name
        Log.d(TAG,"la lista de busqueda es $title")

        //return filterList.filter { title-> title.title.contains(name) }
    }

    fun getMovies(): Flow<List<Movie>> {
        return if (title ==""){
            repoImplementation.localDao.getMovieList()
        } else{
            repoImplementation.localDao.getMovieListByName(title)
        }

    }

    suspend fun internetStatus() = flow<Boolean> {
        while (true){
            emit(InternetConnectionChecker().internetIsConnected())
            delay(5000)
        }

    }
    suspend fun actualDb(){
        val fetchPopularMovieData = try {
            repoImplementation.remoteDataSource.getPopularMovies()
        } catch (e: Exception) {
            throw Exception("remote repo error",e)
        }
        repoImplementation.localDao.deleteAll()
        fetchPopularMovieData.results?.forEach {
            repoImplementation.localDao.insertItem(it)
        } ?: throw Exception("no results")
        Log.d(TAG,"upgraded db")
    }
    suspend fun getTrailer(id: Int): List<Trailer> {
        return try {
            repoImplementation.remoteDataSource.getTrailer(id).results
        } catch (e: Exception) {
            throw Exception("remote repo error", e)
        }
    }
    suspend fun getLinkOfVideo(id:Int){
        val key = getTrailer(id)
        val link = " http://www.youtube.com/watch?v=${key.get(0).key}"
        //cargar el video
        Log.d("key","el link es $link")
    }
}