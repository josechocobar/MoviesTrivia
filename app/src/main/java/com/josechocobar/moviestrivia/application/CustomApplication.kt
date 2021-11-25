package com.josechocobar.moviestrivia.application

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.*

@HiltAndroidApp
class CustomApplication : Application()