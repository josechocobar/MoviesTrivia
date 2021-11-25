package com.josechocobar.moviestrivia.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.josechocobar.moviestrivia.application.InternetConnectionChecker
import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.domain.RepoImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainVievModel @Inject constructor(
    private val repoImplementation: RepoImplementation
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
        while (true){
            emit(InternetConnectionChecker().repeater())
            delay(2000)
        }
    }

}