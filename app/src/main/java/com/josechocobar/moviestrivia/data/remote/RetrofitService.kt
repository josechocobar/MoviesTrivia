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
        .connectTimeout(60,TimeUnit.SECONDS)
        .writeTimeout(60,TimeUnit.SECONDS)
        .readTimeout(60,TimeUnit.SECONDS)
        .callTimeout(60,TimeUnit.SECONDS)
        .build()

    val webService : WebService by lazy {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}

