package com.josechocobar.moviestrivia.presentation

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.josechocobar.moviestrivia.application.InternetConnectionChecker
import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.domain.RepoImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainVievModel @Inject constructor(
    private val repoImplementation: RepoImplementation,

) : ViewModel() {
    val fetchPopulatMoviesList = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(repoImplementation.remoteDataSource.getPopularMovies())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    suspend fun internetStatus() = flow<Boolean> {
        while (true) {
            emit(InternetConnectionChecker().internetIsConnected())
            delay(2000)
        }
    }

    suspend fun observeDataSource() {
        internetStatus().catch { }.collect { value ->
            Log.d(ContentValues.TAG, "The value is $value")
            when (value) {
                true -> {
                    Log.d(ContentValues.TAG, "actualizar db")
                }
                false -> {
                    Log.d(ContentValues.TAG, "User db")
                }
            }
        }
    }



}