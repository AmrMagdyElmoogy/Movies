package com.example.movies.trends.data.models.dto

import com.example.movies.trends.data.models.entity.MovieItem

fun SingleMovieResponse.toMovieEntity() =
    MovieItem(
        id = id,
        title = title,
        releaseDate = releaseDate,
        rating = rating,
        overview = overview,
        image = image,
        state = "",
    )

fun SingleMovieResponse.toMovieTableEntity() =
    MovieTable(
        id = id,
        movieTitle = title,
        rating = rating,
        releaseDate = releaseDate,
        overview = overview,
        image = image,
        state = "",
    )

fun MovieTable.toMovieEntity() =
    MovieItem(
        id = id,
        title = movieTitle,
        releaseDate = releaseDate,
        rating = rating,
        overview = overview,
        image = image,
        state = state,
    )

fun MovieItem.toMovieTableEntity() =
    MovieTable(
        id = id,
        movieTitle = title,
        releaseDate = releaseDate,
        rating = rating,
        overview = overview,
        image = image,
        state = "",
    )
