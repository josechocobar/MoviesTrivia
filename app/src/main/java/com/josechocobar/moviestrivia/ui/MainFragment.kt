package com.josechocobar.moviestrivia.ui

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.josechocobar.moviestrivia.R
import androidx.fragment.app.viewModels
import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.databinding.FragmentMainBinding
import com.josechocobar.moviestrivia.presentation.MainViewModel
import com.josechocobar.moviestrivia.utils.DateHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var binding:FragmentMainBinding? = null
    val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        val tvInternetChecker : TextView = binding!!.tvInternetChecker

        GlobalScope.launch(Dispatchers.Main) {
            observeDataSource()
            viewModel.fetchPopulatMoviesList
                .catch {  }
                .collect {
                    result->
                    when(result){
                        is Resource.Loading ->{Log.d(TAG,"Loading...")}
                        is List<*> ->{ Log.d(TAG, result.toString()) }
                        is Resource.Failure ->{Log.d(TAG,"error ${result.exception.message}")}
                    }
                }

        }

    }
    suspend fun observeDataSource() {
        viewModel.internetStatus().catch { }.collect { value ->
            Log.d(ContentValues.TAG, "The value is $value")
            when (value) {
                true -> {
                    Log.d(ContentValues.TAG, "actualizar db")
                    val nowDate = LocalDateTime.now()
                    if (DateHandler().isLessThanT(viewModel.date, nowDate)) {
                        Log.d(TAG, "db upgrade on")
                        viewModel.actualDb()
                    }
                }
                false -> {
                    Log.d(ContentValues.TAG, "User db")
                }
            }
        }
    }

}