package com.example.movie.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movie.data.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}