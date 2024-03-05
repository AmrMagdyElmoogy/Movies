package com.example.movies.trends.data.models.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingleMovieResponse(
    val id: String,
    @Json(name = "original_title") val title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val rating: String,
    @Json(name = "poster_path") val image: String,
)
