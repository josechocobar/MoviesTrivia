package com.josechocobar.moviestrivia.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class Video(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("results")
    @Expose
    val results: List<Trailer>
)

