package com.josechocobar.moviestrivia.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.josechocobar.moviestrivia.application.InternetConnectionChecker
import com.josechocobar.moviestrivia.data.model.Movie
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
    fun getMovieByName(name:String):List<Movie>{
        var filterList = mutableListOf<Movie>()
        repoImplementation.localDao.getMovieList().catch {  }.map {
            filterList = it.filter { title-> title.title.contains(name) } as MutableList<Movie>
        }
        return filterList
    }

    fun getMovies(): Flow<List<Movie>> {
     return repoImplementation.localDao.getMovieList()
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
}