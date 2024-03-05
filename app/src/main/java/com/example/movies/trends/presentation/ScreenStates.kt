package com.example.movies.trends.presentation

import com.example.movies.trends.data.models.entity.MovieItem

data class ScreenStates(
    val isLoading: Boolean = true,
    val success: List<MovieItem>? = emptyList(),
    val isError: Boolean = false,
)
