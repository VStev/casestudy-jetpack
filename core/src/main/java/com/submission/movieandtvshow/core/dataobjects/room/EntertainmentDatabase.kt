package com.submission.movieandtvshow.core.dataobjects.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.MovieEntity
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.TVShowEntity

@Database(entities = [MovieEntity::class, TVShowEntity::class], version = 1, exportSchema = false)
abstract class EntertainmentDatabase : RoomDatabase() {
    abstract fun entertainmentDao(): EntertainmentDAO
}