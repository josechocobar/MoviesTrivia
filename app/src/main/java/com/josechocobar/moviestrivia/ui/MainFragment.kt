package com.josechocobar.moviestrivia.ui

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.josechocobar.moviestrivia.R
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.databinding.FragmentMainBinding
import com.josechocobar.moviestrivia.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var binding: FragmentMainBinding? = null
    val viewModel: MainViewModel by viewModels()
    var tvInternetChecker: TextView? = null
    var upgradeButton: Button? = null


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
        tvInternetChecker = binding!!.tvInternetChecker
        upgradeButton = binding!!.buUpgradeDb
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.internetStatus().catch { }.collect { value ->
                Log.d(ContentValues.TAG, "The value is $value")
                when (value) {
                    true -> {
                        upgradeDb()
                        delay(1800000)
                    }
                    false -> {
                        Log.d(ContentValues.TAG, "User db")
                        tvInternetChecker?.visibility = View.VISIBLE
                        tvInternetChecker?.text = "no connection"
                    }
                }
            }
            viewModel.fetchPopulatMoviesList
                .catch { }
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            Log.d(TAG, "Loading...")
                        }
                        is List<*> -> {
                            Log.d(TAG, result.toString())
                        }
                        is Resource.Failure -> {
                            Log.d(TAG, "error ${result.exception.message}")
                        }
                    }
                }

        }
        setButtons()

    }


    fun setButtons() {
        upgradeButton?.setOnClickListener {
            viewModel.viewModelScope.launch {
                viewModel.internetStatus().catch { }.collect { value ->
                    Log.d(ContentValues.TAG, "The value is $value")
                    when (value) {
                        true -> {
                            upgradeDb()
                        }
                    }
                }

            }
        }
    }
    suspend fun upgradeDb(){
        tvInternetChecker?.text = "connection"
        tvInternetChecker?.visibility = View.GONE
        Log.d(ContentValues.TAG, "actualizar db")
        Log.d(TAG, "db upgrade on")
        viewModel.actualDb()
    }


}