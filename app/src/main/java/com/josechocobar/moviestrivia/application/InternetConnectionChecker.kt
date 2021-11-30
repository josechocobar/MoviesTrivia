package com.josechocobar.moviestrivia.application

import android.content.Context
import android.net.ConnectivityManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow


class InternetConnectionChecker {
    fun internetIsConnected(): Boolean {
        return try {
            val command = "/system/bin/ping -c 1 google.com"
            Runtime.getRuntime().exec(command).waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }
}