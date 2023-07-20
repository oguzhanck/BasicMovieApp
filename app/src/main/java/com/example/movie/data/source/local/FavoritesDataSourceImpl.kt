package com.example.movie.data.source.local

import com.example.movie.data.model.Movie

class FavoritesDataSourceImpl(private val movieDao: MovieDao) : FavoritesDataSource {

    override suspend fun getFavoriteMovies() = movieDao.getAllMovies()

    override suspend fun insertMovie(movie: Movie) = movieDao.insertMovie(movie)

    override suspend fun deleteMovie(movie: Movie) = movieDao.removeMovie(movie)

    override suspend fun isMovieFavorite(movieId: Int?): Boolean {
        return movieDao.getFavoriteMovieById(movieId ?: -1) != null
    }
}