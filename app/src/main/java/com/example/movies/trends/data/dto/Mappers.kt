package com.example.movies.trends.data.dto

import com.example.movies.trends.data.entity.MovieItem

fun SingleMovieResponse.toMovieEntity() =
    MovieItem(
        id = id,
        title = title,
        releaseDate = releaseDate,
        rating = rating,
        overview = overview,
        image = image,
    )
