package com.example.movie.domain.usecase

import com.example.movie.data.model.Movie
import com.example.movie.domain.repository.FavoritesRepository
import com.example.movie.presentation.details.DetailState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertOrDeleteFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(movie: Movie): Flow<DetailState> = flow {
        if (favoritesRepository.isMovieFavorite(movie.id ?: -1)) {
            favoritesRepository.deleteMovie(movie)
            emit(DetailState.Deleted)
        } else {
            favoritesRepository.insertMovie(movie)
            emit(DetailState.Inserted)
        }
    }.catch {
        emit(DetailState.Error(it.message))
    }
}