package com.josechocobar.moviestrivia.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.josechocobar.moviestrivia.R
import com.josechocobar.moviestrivia.data.model.Movie
import com.josechocobar.moviestrivia.databinding.FragmentItemDetailBinding
import com.josechocobar.moviestrivia.databinding.FragmentMainBinding
import com.josechocobar.moviestrivia.ui.animations.Bounce
import com.josechocobar.moviestrivia.ui.animations.Render

class ItemDetailFragment : Fragment() {
    private var binding: FragmentItemDetailBinding? = null
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
        binding?.let {
            setData()
        }
    }

    fun setData() {
        binding!!.tvItemTitle.text = movie?.title
        binding!!.tvOverviewItem.text = movie?.overview
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/original${movie?.poster_path}")
            .transform(
                RoundedCorners(200)
            ).centerCrop().into(binding!!.ivItemPoster)
        }

    fun animateSomething() {
        val render = Render()
        render.setAnimation(Bounce().In(binding?.containerDetailLayout!!))
        render.setDuration(2000)
        render.start()
    }


}