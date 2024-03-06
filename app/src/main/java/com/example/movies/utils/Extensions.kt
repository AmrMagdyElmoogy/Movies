package com.example.movies.utils

import com.example.movies.trends.domain.entity.MovieItem
import com.example.movies.trends.presentation.ScreenStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

fun MutableStateFlow<ScreenStates>.loadingState() {
    update {
        it.copy(
            isLoading = true,
        )
    }
}

fun MutableStateFlow<ScreenStates>.errorState() {
    update {
        it.copy(
            isLoading = false,
            isError = true,
        )
    }
}

fun MutableStateFlow<ScreenStates>.successState(list: List<MovieItem>) {
    update {
        it.copy(
            isLoading = false,
            isError = false,
            success = list,
        )
    }
}
