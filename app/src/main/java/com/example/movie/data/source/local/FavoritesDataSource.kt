package com.example.movie.data.source.local

import com.example.movie.data.model.Movie

interface FavoritesDataSource {

    suspend fun getFavoriteMovies(): List<Movie>

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    suspend fun isMovieFavorite(movieId: Int?): Boolean
}