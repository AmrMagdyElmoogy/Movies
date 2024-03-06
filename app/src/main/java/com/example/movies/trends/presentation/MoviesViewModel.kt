package com.example.movies.trends.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.trends.domain.MoviesTrendsRepository
import com.example.movies.trends.domain.entity.MovieItem
import com.example.movies.utils.NEW_RELEASES_GENERA
import com.example.movies.utils.errorState
import com.example.movies.utils.successState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel
    @Inject
    constructor(
        private val filmsRepository: MoviesTrendsRepository,
    ) : ViewModel() {
        @Suppress("ktlint:standard:property-naming")
        private val _newReleasedUiState = MutableStateFlow(ScreenStates())

        val newReleasesUiState: StateFlow<ScreenStates>
            get() = _newReleasedUiState

        private val _topRatedState = MutableStateFlow(ScreenStates())

        val topRatedState: StateFlow<ScreenStates>
            get() = _topRatedState

        init {
            viewModelScope.launch {
                filmsRepository.getAllFilmsLocally().collect {
                    val pair: Pair<List<MovieItem>, List<MovieItem>> =
                        it.partition { movie -> movie.state == NEW_RELEASES_GENERA }
                    if (pair.first.isEmpty() && pair.second.isEmpty()) {
                        _newReleasedUiState.errorState()
                        _topRatedState.errorState()
                    } else {
                        _newReleasedUiState.successState(pair.first)
                        _topRatedState.successState(pair.second)
                    }
                }
            }
        }
    }
