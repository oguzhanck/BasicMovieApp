package com.example.movie.data.source.remote

import com.example.movie.data.model.MovieResult

interface MovieDataSource {

    suspend fun getMovies(): MovieResult

}