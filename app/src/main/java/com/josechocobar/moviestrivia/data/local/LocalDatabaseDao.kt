package com.josechocobar.moviestrivia.data.local

import androidx.room.*
import com.josechocobar.moviestrivia.data.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDatabaseDao {
    @Query("SELECT * FROM movie_table ORDER BY id")
    fun getMovieList(): Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(movie: Movie)

    @Delete
    suspend fun deleteItem(item:Movie)

}