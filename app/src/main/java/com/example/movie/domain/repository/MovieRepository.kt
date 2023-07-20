package com.example.movie.domain.repository

import com.example.movie.common.Resource
import com.example.movie.data.model.MovieResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(): Flow<Resource<MovieResult>>
}