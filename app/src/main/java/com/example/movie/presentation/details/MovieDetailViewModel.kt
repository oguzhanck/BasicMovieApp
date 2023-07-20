package com.example.movie.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.model.Movie
import com.example.movie.domain.usecase.InsertOrDeleteFavoriteUseCase
import com.example.movie.domain.usecase.isMovieFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val insertOrDeleteFavoriteUseCase: InsertOrDeleteFavoriteUseCase,
    private val isMovieFavoriteUseCase: isMovieFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState

    fun insertOrDelete(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            insertOrDeleteFavoriteUseCase.invoke(movie).collect { state ->
                when (state) {
                    is DetailState.Inserted -> {
                        _uiState.update { currentState ->
                            currentState.copy(inserted = true, errorMessage = null)
                        }
                    }
                    is DetailState.Deleted -> {
                        _uiState.update { currentState ->
                            currentState.copy(inserted = false, errorMessage = null)
                        }
                    }
                    is DetailState.Error -> {
                        _uiState.update { currentState ->
                            currentState.copy(errorMessage = state.errorMessage)
                        }
                    }
                }
            }
        }
    }

    suspend fun isMovieFavorite(movie: Movie): Boolean {
        return isMovieFavoriteUseCase.invoke(movie)
    }

}