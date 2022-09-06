package com.josechocobar.moviestrivia.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import androidx.core.os.bundleOf
import com.josechocobar.moviestrivia.R
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.josechocobar.moviestrivia.data.model.Movie
import com.josechocobar.moviestrivia.databinding.FragmentMainBinding
import com.josechocobar.moviestrivia.presentation.MainViewModel
import com.josechocobar.moviestrivia.ui.animations.Turn
import com.josechocobar.moviestrivia.ui.animations.Renderizator
import com.josechocobar.moviestrivia.ui.recyclerView.PopularAdapter
import com.josechocobar.moviestrivia.utils.DateHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime

@AndroidEntryPoint
class MainFragment : Fragment(), PopularAdapter.OnMovieItemClickListener {


    private var binding: FragmentMainBinding? = null
    private val viewModel: MainViewModel by viewModels()
    var tvInternetChecker: TextView? = null
    var upgradeButton: Button? = null
    //var loadingDialog: LoadingDialog? = null
    private var date: LocalDateTime? = null
    var dbPull = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        tvInternetChecker = binding!!.tvInternetChecker
        upgradeButton = binding!!.buUpgradeDb
        //loadingDialog = LoadingDialog(requireActivity())
        date = LocalDateTime.now()
        setUpRecyclerView()
        setButtons()
        setUpObserver()
        observeInternet()
        setupSearchView()
    }

    private fun observeInternet() {
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.internetStatus().catch { }.collect { value ->
                Log.d(TAG, "The value is $value")
                when (value) {
                    true -> {
                        stateConnectionChanged()
                        checkDate()
                        if (dbPull) {
                            Log.d(TAG, "db upgrade first time")
                            upgradeDb()
                            dbPull = false
                        }
                    }
                    false -> {
                        Log.d(TAG, "User db")
                        tvInternetChecker?.visibility = View.VISIBLE
                        tvInternetChecker?.text = getString(R.string.no_connection)
                    }
                }
            }
        }
    }

    private fun setupSearchView() {
        binding?.svSearchMovie?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(name: String): Boolean {
                viewModel.getMovieByName(name)
                setUpObserver()
                return false
            }

            override fun onQueryTextChange(name: String): Boolean {
                if (name.equals("")) {
                    viewModel.getMovieByName(name)
                    setUpObserver()
                }
                viewModel.getMovieByName(name)
                setUpObserver()
                return false
            }
        })

    }

    private fun setUpObserver() {
        viewModel.viewModelScope.launch {
            viewModel.getMovies()
                .catch { }
                .map {
                    binding?.rvPopular?.adapter = PopularAdapter(
                        requireContext(),
                        it,
                        this@MainFragment
                    )
                }
                .collect { }

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
    }

    suspend fun checkDate() {
        val nowDate = LocalDateTime.now()
        if (!DateHandler().isLessThanT(date!!, nowDate)) {
            Log.d(TAG, "db upgrade on")
            upgradeDb()
            date = LocalDateTime.now()
        }
    }

    private fun setButtons() {
        upgradeButton?.setOnClickListener {
            viewModel.viewModelScope.launch {
                viewModel.internetStatus().map { value ->
                    Log.d(TAG, "The value  upb is $value")

                    when (value) {
                        true -> {
                            stateConnectionChanged()
                            checkDate()
                        }
                    }
                }
                    .collect { }

            }
        }
    }

    private suspend fun upgradeDb() {
        Log.d(TAG, "actualizar db")
        viewModel.actualDb()
    }

    private fun stateConnectionChanged() {
        tvInternetChecker?.text = getString(R.string.connection)
        tvInternetChecker?.visibility = View.GONE
    }

    override fun onMovieClick(item: Movie, position: Int) {
        animateButton()
        lifecycleScope.launch {
            delay(1000)
            try {
                findNavController().navigate(R.id.itemDetailFragment, bundleOf("idroom" to item))
            } catch (e: Exception) {
                Log.d(TAG, "Failure cause ${e.message}")
            }
        }


    }

    private fun animateButton() {
        val render = Renderizator()
        render.setAnimation(Turn().getOutByLeft(binding?.rvPopular!!))
        render.setDuration(900)
        render.start()
    }

}