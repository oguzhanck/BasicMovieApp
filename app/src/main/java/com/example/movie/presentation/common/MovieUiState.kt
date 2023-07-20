package com.example.movie.presentation.common

import com.example.movie.data.model.Movie

data class MovieUiState(
    val movieList: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)