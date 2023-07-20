package com.example.movie.di

import com.example.movie.data.repository.FavoritesRepositoryImpl
import com.example.movie.data.repository.MovieRepositoryImpl
import com.example.movie.data.source.local.FavoritesDataSource
import com.example.movie.data.source.remote.MovieDataSource
import com.example.movie.domain.repository.FavoritesRepository
import com.example.movie.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(
        movieDataSource: MovieDataSource,
    ): MovieRepository = MovieRepositoryImpl(movieDataSource)

    @Provides
    @Singleton
    fun provideFavoritesRepository(favoritesDataSource: FavoritesDataSource): FavoritesRepository
    = FavoritesRepositoryImpl(favoritesDataSource)
}