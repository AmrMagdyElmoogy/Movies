package com.example.movies.trends.presentation

import com.example.movies.trends.domain.entity.MovieItem

data class ScreenStates(
    val isLoading: Boolean = true,
    val success: List<MovieItem>? = emptyList(),
    val isError: Boolean = false,
)
