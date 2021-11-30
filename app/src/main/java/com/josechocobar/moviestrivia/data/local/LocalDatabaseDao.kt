package com.josechocobar.moviestrivia.data.local

import androidx.room.*
import com.josechocobar.moviestrivia.data.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDatabaseDao {
    @Query("SELECT * FROM movie_table ORDER BY idroom")
    fun getMovieList(): Flow<List<Movie>>

    @Query("SELECT * FROM movie_table WHERE idroom=:idroom ")
    suspend fun getMovieById(idroom:Int):Movie

    @Query("SELECT * FROM movie_table WHERE title=:title")
    fun getMovieListByName(title: String):Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(movie: Movie)

    @Delete
    suspend fun deleteItem(item:Movie)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()

}