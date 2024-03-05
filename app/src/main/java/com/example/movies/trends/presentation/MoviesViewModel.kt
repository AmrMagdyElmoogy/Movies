package com.example.movies.trends.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.trends.data.models.entity.MovieItem
import com.example.movies.trends.domain.MoviesTrendsRepository
import com.example.movies.utils.NEW_RELEASES_GENERA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel
    @Inject
    constructor(
        private val filmsRepository: MoviesTrendsRepository,
    ) : ViewModel() {
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

        init {
            viewModelScope.launch {
                filmsRepository.getAllFilmsLocally().collect {
                    val pair: Pair<List<MovieItem>, List<MovieItem>> =
                        it.partition { movie -> movie.state == NEW_RELEASES_GENERA }
                    _moviesUiState.update { state ->
                        state.copy(
                            isLoading = false,
                            success = pair.first,
                        )
                    }
                    _topRatedState.update { state ->
                        state.copy(
                            isLoading = false,
                            success = pair.second,
                        )
                    }
                }
            }
        }
    }
