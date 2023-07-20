package com.example.movie.presentation.details


sealed class DetailState {
    object Inserted : DetailState()
    object Deleted : DetailState()
    data class Error(val errorMessage: String?) : DetailState()
}