package com.example.movie.domain.usecase

import com.example.movie.common.Resource
import com.example.movie.domain.repository.FavoritesRepository
import com.example.movie.presentation.common.MovieState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) {
    operator fun invoke(): Flow<MovieState> = flow {
        favoritesRepository.getFavoritesMovies().collect {
            when (it) {
                is Resource.Success -> {
                    emit(MovieState.Success(it.data))
                }
                is Resource.Error -> emit(MovieState.Error(it.message))

                is Resource.Loading -> emit(MovieState.Loading)
            }
        }
    }
}