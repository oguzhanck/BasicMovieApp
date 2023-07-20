package com.example.movie.domain.usecase

import com.example.movie.common.Resource
import com.example.movie.domain.repository.MovieRepository
import com.example.movie.presentation.common.MovieState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(): Flow<MovieState> = flow {
        movieRepository.getMovies().collect {
            when (it) {
                is Resource.Success -> {
                    if (it.data.movies.isNullOrEmpty().not()) {
                        emit(MovieState.Success(it.data.movies!!))
                    }
                }
                is Resource.Error -> emit(MovieState.Error(it.message))

                is Resource.Loading -> emit(MovieState.Loading)
            }
        }
    }
}