package com.example.movie.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movie.data.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<Movie>

    @Insert
    fun insertMovie(movie: Movie)

    @Delete
    fun removeMovie(movie: Movie)

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getFavoriteMovieById(id: Int): Movie?
}