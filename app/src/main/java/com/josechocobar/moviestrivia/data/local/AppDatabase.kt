package com.josechocobar.moviestrivia.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.josechocobar.moviestrivia.data.model.Movie

@Database(entities = [Movie::class],version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun localDatabaseDao():LocalDatabaseDao
}