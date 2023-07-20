package com.example.movie.di

import android.content.Context
import androidx.room.Room
import com.example.movie.data.source.local.MovieDao
import com.example.movie.data.source.local.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideMovieDataBase(@ApplicationContext context: Context): MovieDataBase =
        Room.databaseBuilder(
            context,
            MovieDataBase::class.java,
            "movieDb"
        ).build()

    @Provides
    @Singleton
    fun provideMovieDAO(movieDataBase: MovieDataBase): MovieDao =
        movieDataBase.movieDao()
}