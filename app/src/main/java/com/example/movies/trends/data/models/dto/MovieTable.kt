package com.example.movies.trends.data.models.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieTable")
data class MovieTable(
    @PrimaryKey @ColumnInfo(name = "ID") val id: String,
    @ColumnInfo(name = "Title") val movieTitle: String,
    @ColumnInfo(name = "IMDB_Rating") val rating: String,
    @ColumnInfo(name = "Release_Date") val releaseDate: String,
    @ColumnInfo(name = "Overview") val overview: String,
    @ColumnInfo(name = "Image_url") val image: String,
    @ColumnInfo(name = "FilmState") val state: String,
)
