package com.example.movie.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.usecase.GetFavoritesUseCase
import com.example.movie.presentation.common.MovieState
import com.example.movie.presentation.common.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val getFavoritesUseCase: GetFavoritesUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieUiState(isLoading = true))
    val uiState: StateFlow<MovieUiState> = _uiState

    fun getFavorites() {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.IO) {
            getFavoritesUseCase.invoke().collect { state ->
                when (state) {
                    is MovieState.Success -> {
                        _uiState.update { currentState ->
                            currentState.copy(isLoading = false, movieList = state.movieList, errorMessage = null)
                        }
                    }
                    is MovieState.Error -> {
                        _uiState.update { currentState ->
                            currentState.copy(isLoading = false, errorMessage = state.errorMessage)
                        }
                    }
                    is MovieState.Loading -> {
                        _uiState.update { currentState ->
                            currentState.copy(isLoading = true, errorMessage = null)
                        }
                    }
                }
            }
        }
    }
}