package com.example.movie.di

import com.example.movie.data.source.local.FavoritesDataSource
import com.example.movie.data.source.local.FavoritesDataSourceImpl
import com.example.movie.data.source.local.MovieDao
import com.example.movie.data.source.remote.MovieDataSource
import com.example.movie.data.source.remote.MovieDataSourceImpl
import com.example.movie.data.source.remote.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideMovieDataSource(movieService: MovieService): MovieDataSource =
        MovieDataSourceImpl(movieService)

    @Provides
    @Singleton
    fun provideFavoriteDataSource(movieDao: MovieDao): FavoritesDataSource =
        FavoritesDataSourceImpl(movieDao)
}