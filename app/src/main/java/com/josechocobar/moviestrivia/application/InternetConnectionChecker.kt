package com.josechocobar.moviestrivia.application

import android.content.Context
import android.net.ConnectivityManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow


class InternetConnectionChecker {
    suspend fun repeater(): Boolean {
            return internetIsConnected()
    }
    private fun internetIsConnected(): Boolean {
        return try {
            val command = "ping -c 1 google.com"
            Runtime.getRuntime().exec(command).waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }
}