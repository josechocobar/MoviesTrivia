package com.josechocobar.moviestrivia.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.josechocobar.moviestrivia.R
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.presentation.MainVievModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {


    val viewModel: MainVievModel by activityViewModels<MainVievModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    viewModel.fetchPopulatMoviesList.observe(viewLifecycleOwner, Observer {
        result ->

        when(result){
            is Resource.Loading->{
                Log.d("VIEWMODEL","Cargando")
            }
            is Resource.Success->{
                Log.d("VIEWMODEL","Correcto!")
                Log.d("VIEWMODEL","como es correcto las pelis son ${result.data.results}")
            }
            is Resource.Failure->{
                Log.d("VIEWMODEL","Fail!")
                Log.d("VIEWMODEL","${result.exception}!")
            }
        }

    })
    }

}