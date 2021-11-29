package com.josechocobar.moviestrivia.presentation

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.josechocobar.moviestrivia.application.InternetConnectionChecker
import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.domain.RepoImplementation
import com.josechocobar.moviestrivia.utils.DateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.lang.Exception
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repoImplementation: RepoImplementation
) : ViewModel() {
    var date: LocalDateTime = LocalDateTime.now()
    val fetchPopulatMoviesList = flow<Any> {
        emit(Resource.Loading)
        try{
            emit(repoImplementation.localDao.getMovieList())
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }


    suspend fun internetStatus() = flow<Boolean> {
        while (true) {
            emit(InternetConnectionChecker().internetIsConnected())
            delay(5000)
        }
    }

    suspend fun observeDataSource() {
        internetStatus().catch { }.collect { value ->
            Log.d(ContentValues.TAG, "The value is $value")
            when (value) {
                true -> {
                    Log.d(ContentValues.TAG, "actualizar db")
                    val nowDate = LocalDateTime.now()
                    if (DateHandler().isLessThanT(date, nowDate)) {
                        Log.d(TAG, "db upgrade on")
                        actualDb()
                    }
                }
                false -> {
                    Log.d(ContentValues.TAG, "User db")
                }
            }
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
        }
        Log.d(TAG,"upgraded db")
    }


}