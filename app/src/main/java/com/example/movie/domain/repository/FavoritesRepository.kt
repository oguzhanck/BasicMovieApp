package com.example.movie.domain.repository

import com.example.movie.common.Resource
import com.example.movie.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun getFavoritesMovies(): Flow<Resource<List<Movie>>>

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    suspend fun isMovieFavorite(movieId: Int) : Boolean
}