package com.josechocobar.moviestrivia.ui

import android.content.ContentValues.TAG

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.josechocobar.moviestrivia.R
import com.josechocobar.moviestrivia.data.model.Movie
import com.josechocobar.moviestrivia.databinding.FragmentItemDetailBinding
import com.josechocobar.moviestrivia.presentation.MainViewModel
import com.josechocobar.moviestrivia.ui.animations.Rebound
import com.josechocobar.moviestrivia.ui.animations.Renderizator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class ItemDetailFragment : Fragment() {
    private var binding: FragmentItemDetailBinding? = null
    private val viewModel: MainViewModel by viewModels()
    private var movie: Movie? = null
    var videoView : VideoView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            movie = bundle.getParcelable<Movie>("idroom")!!
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemDetailBinding.bind(view)
        animateSomething()
        setData()
    }

    fun setData() {
        binding!!.tvItemTitle.text = movie?.title
        binding!!.tvOverviewItem.text = movie?.overview
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/original${movie?.poster_path}")
            .transform(
                RoundedCorners(200)
            ).centerCrop().into(binding!!.ivItemPoster)
        //movie?.id?.let { setVideo(it) }
        }

    fun animateSomething() {
        val render = Renderizator()
        render.setAnimation(Rebound().enter(binding?.containerDetailLayout!!))
        render.setDuration(2000)
        render.start()
    }


}