package com.example.movie.data.source.remote

import com.example.movie.data.model.MovieResult
import retrofit2.http.GET

interface MovieService {

    @GET("tv/top_rated")
    suspend fun getMovie(): MovieResult
}