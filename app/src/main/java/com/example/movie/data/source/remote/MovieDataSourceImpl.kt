package com.example.movie.data.source.remote

import com.example.movie.data.model.MovieResult
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : MovieDataSource {

    override suspend fun getMovies(): MovieResult = movieService.getMovie()
}