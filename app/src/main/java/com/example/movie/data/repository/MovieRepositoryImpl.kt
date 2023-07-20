package com.example.movie.data.repository

import com.example.movie.common.Resource
import com.example.movie.data.source.remote.MovieDataSource
import com.example.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val movieDataSource: MovieDataSource) : MovieRepository {

    override fun getMovies() = flow {
        emit(Resource.Loading)
        emit(Resource.Success(movieDataSource.getMovies()))
    }.catch {
        emit(Resource.Error(it.message.orEmpty()))
    }

}