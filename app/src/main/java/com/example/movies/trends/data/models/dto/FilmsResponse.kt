package com.example.movies.trends.data.models.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmsResponse(
    val page: Int,
    val results: List<SingleMovieResponse>,
)
