package com.example.movies.trends.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.trends.data.models.dto.MovieTable
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesServiceDAO {
    @Query("Select * From MovieTable")
    fun selectAllFilms(): Flow<List<MovieTable>>

    @Insert(entity = MovieTable::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewFilms(movie: MovieTable)
}
