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
import android.widget.Toast
import com.josechocobar.moviestrivia.R
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.josechocobar.moviestrivia.application.Resource
import com.josechocobar.moviestrivia.data.model.Movie
import com.josechocobar.moviestrivia.databinding.FragmentMainBinding
import com.josechocobar.moviestrivia.presentation.MainViewModel
import com.josechocobar.moviestrivia.ui.recyclerView.PopularAdapter
import com.josechocobar.moviestrivia.utils.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class MainFragment : Fragment(), PopularAdapter.OnMovieItemClickListener {


    private var binding: FragmentMainBinding? = null
    val viewModel: MainViewModel by viewModels()
    var tvInternetChecker: TextView? = null
    var upgradeButton: Button? = null
    var loadingDialog: LoadingDialog? = null


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
        loadingDialog = LoadingDialog(requireActivity())
        setUpRecyclerView()
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.internetStatus().catch { }.collect { value ->
                Log.d(ContentValues.TAG, "The value is $value")
                when (value) {
                    true -> {
                        upgradeDb()
                    }
                    false -> {
                        Log.d(ContentValues.TAG, "User db")
                        tvInternetChecker?.visibility = View.VISIBLE
                        tvInternetChecker?.text = "no connection"
                    }
                }
            }
        }
        setButtons()
        setUpObserver()

    }

    fun setUpObserver() {
        viewModel.viewModelScope.launch {
            viewModel.getMovies()
                .map {
                    binding?.rvPopular?.adapter = PopularAdapter(
                        requireContext(),
                        it,
                        this@MainFragment
                    )
                    it.forEach {
                        movie->
                        Log.d(ContentValues.TAG, "The value is $movie")
                    }

                }
                .collect {}
        }
    }


    private fun setUpRecyclerView() {
        binding?.rvPopular?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvPopular?.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        /*
        binding!!.rvCarrito.layoutManager = LinearLayoutManager(requireContext())
           binding!!.rvCarrito.addItemDecoration(DividerItemDecoration(
                   requireContext(),
                   DividerItemDecoration.VERTICAL
           ))
         */
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

    suspend fun upgradeDb() {
        tvInternetChecker?.text = "connection"
        tvInternetChecker?.visibility = View.GONE
        Log.d(ContentValues.TAG, "actualizar db")
        Log.d(TAG, "db upgrade on")
        viewModel.actualDb()
    }

    override fun onMovieClick(item: Movie, position: Int) {
        Toast.makeText(requireContext(), "no me señales", Toast.LENGTH_LONG).show()
    }


}