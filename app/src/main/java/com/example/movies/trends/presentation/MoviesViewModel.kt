package com.example.movies.trends.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.trends.domain.NewReleasesRepository
import com.example.movies.trends.domain.TopRatedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    // State Holder
    private val _moviesUiState = MutableStateFlow(ScreenStates())

    // For UI purposes
    val moviesUiState: StateFlow<ScreenStates>
        get() = _moviesUiState

    // State Holder
    private val _topRatedState = MutableStateFlow(ScreenStates())

    // For UI purposes
    val topRatedState: StateFlow<ScreenStates>
        get() = _topRatedState

    val newReleasesRepo = NewReleasesRepository()
    val topRatedRepo = TopRatedRepository()

    init {
        viewModelScope.launch {
            val result = newReleasesRepo.getNewReleasesFromDB()
            if (result.isSuccess) {
                _moviesUiState.update {
                    it.copy(
                        isLoading = false,
                        success = result.getOrDefault(emptyList()),
                    )
                }
            } else {
                _moviesUiState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                    )
                }
            }
        }

        viewModelScope.launch {
            val result = topRatedRepo.getTopRatedFilmsFromDB()
            if (result.isSuccess) {
                _topRatedState.update {
                    it.copy(
                        isLoading = false,
                        success = result.getOrDefault(emptyList()),
                    )
                }
            } else {
                _topRatedState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                    )
                }
            }
        }
    }
}
