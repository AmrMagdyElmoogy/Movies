package com.example.movies.trends.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.trends.data.models.dto.MovieTable

@Database(entities = [MovieTable::class], version = 1)
abstract class MovieDB : RoomDatabase() {
    abstract fun dao(): MoviesServiceDAO
}
