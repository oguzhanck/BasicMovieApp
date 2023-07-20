package com.example.movie.domain.usecase

import com.example.movie.data.model.Movie
import com.example.movie.domain.repository.FavoritesRepository
import javax.inject.Inject

class isMovieFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) {

    suspend operator fun invoke(movie: Movie) = favoritesRepository.isMovieFavorite(movie.id ?: -1)

}