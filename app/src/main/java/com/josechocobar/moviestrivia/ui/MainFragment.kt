package com.josechocobar.moviestrivia.ui

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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var binding:FragmentMainBinding? = null
    val viewModel: MainViewModel by viewModels<MainViewModel>()


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

        GlobalScope.launch() {
            viewModel.observeDataSource()
            viewModel.fetchPopulatMoviesList
                .catch {  }
                .collect {
                    result->
                    when(result){
                        is Resource.Loading ->{}
                        is List<*> ->{ }
                        is Resource.Failure ->{Log.d(TAG,"error ${result.exception.message}")}
                    }
                }

        }

    }

}