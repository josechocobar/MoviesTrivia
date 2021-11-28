package com.josechocobar.moviestrivia.presentation

import android.content.ContentValues
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repoImplementation: RepoImplementation

    ) : ViewModel() {
    var date: LocalDateTime = LocalDateTime.now()
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
                    if (DateHandler().isLessThanT(date,nowDate)){

                    }
                }
                false -> {
                    Log.d(ContentValues.TAG, "User db")
                }
            }
        }
    }


}