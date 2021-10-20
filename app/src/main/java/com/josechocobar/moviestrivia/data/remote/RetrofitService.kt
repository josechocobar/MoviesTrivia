package com.josechocobar.moviestrivia.data.remote

import com.google.gson.GsonBuilder
import com.josechocobar.moviestrivia.application.Constants.API_URL
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    private val client : OkHttpClient  = OkHttpClient.Builder()
        .connectTimeout(2,TimeUnit.MINUTES)
        .writeTimeout(2,TimeUnit.MINUTES)
        .readTimeout(2,TimeUnit.MINUTES)
        .callTimeout(2,TimeUnit.MINUTES)
        .build()

    val webService : WebService by lazy {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}

