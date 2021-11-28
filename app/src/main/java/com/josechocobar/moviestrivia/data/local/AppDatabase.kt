package com.josechocobar.moviestrivia.data.local

import android.content.Context
import androidx.room.*
import com.josechocobar.moviestrivia.data.model.Movie
import com.josechocobar.moviestrivia.utils.DataTypeConverter

@Database(entities = [Movie::class],version = 1,exportSchema = false)
@TypeConverters(value = [TypeConverters::class, DataTypeConverter::class])
abstract class AppDatabase : RoomDatabase(){
    abstract fun localDatabaseDao():LocalDatabaseDao
}