package com.example.movie.presentation.common

import com.example.movie.data.model.Movie

sealed class MovieState {
    data class Success(val movieList: List<Movie>) : MovieState()
    data class Error(val errorMessage: String) : MovieState()
    object Loading : MovieState()
}
