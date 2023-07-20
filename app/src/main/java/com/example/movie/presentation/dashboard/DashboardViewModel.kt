package com.example.movie.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.usecase.GetMoviesUseCase
import com.example.movie.presentation.common.MovieState
import com.example.movie.presentation.common.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val moviesUseCase: GetMoviesUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieUiState(isLoading = true))
    val uiState: StateFlow<MovieUiState> = _uiState

    fun getMovies() {
        viewModelScope.launch {
            moviesUseCase.invoke().collect { state ->
                when (state) {
                    is MovieState.Success -> {
                        _uiState.update { currentState ->
                            currentState.copy(isLoading = false, movieList = state.movieList)
                        }
                    }
                    is MovieState.Error -> {
                        _uiState.update { currentState ->
                            currentState.copy(isLoading = false, errorMessage = state.errorMessage)
                        }
                    }
                    is MovieState.Loading -> {
                        _uiState.update { currentState ->
                            currentState.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }
}