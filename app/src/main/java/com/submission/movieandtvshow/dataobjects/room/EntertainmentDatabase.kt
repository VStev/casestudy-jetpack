package com.submission.movieandtvshow.dataobjects.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.submission.movieandtvshow.dataobjects.MovieEntity
import com.submission.movieandtvshow.dataobjects.TVShowEntity

@Database(entities = [MovieEntity::class, TVShowEntity::class], version = 1, exportSchema = false)
abstract class EntertainmentDatabase : RoomDatabase() {
    abstract fun entertainmentDao(): EntertainmentDAO
}