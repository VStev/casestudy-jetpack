package com.submission.movieandtvshow.core.dataobjects.remote.dataentities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    var movieID: String = "",

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "release_date")
    var releaseYear: String = "",

    @ColumnInfo(name = "overview")
    var details: String = "",

    @ColumnInfo(name = "poster_path")
    var poster: String = "",

    @ColumnInfo(name = "fav")
    var fav: Boolean = false
)
