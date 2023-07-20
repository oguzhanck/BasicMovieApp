package com.example.movie.data.repository

import com.example.movie.common.Resource
import com.example.movie.data.model.Movie
import com.example.movie.data.source.local.FavoritesDataSource
import com.example.movie.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FavoritesRepositoryImpl(private val favoritesDataSource: FavoritesDataSource) : FavoritesRepository {

    override suspend fun getFavoritesMovies() = flow {
        emit(Resource.Loading)
        emit(Resource.Success(favoritesDataSource.getFavoriteMovies()))
    }.catch {
        emit(Resource.Error(it.message.orEmpty()))
    }

    override suspend fun insertMovie(movie: Movie) = favoritesDataSource.insertMovie(movie)

    override suspend fun deleteMovie(movie: Movie) = favoritesDataSource.deleteMovie(movie)

    override suspend fun isMovieFavorite(movieId: Int) = favoritesDataSource.isMovieFavorite(movieId)
}