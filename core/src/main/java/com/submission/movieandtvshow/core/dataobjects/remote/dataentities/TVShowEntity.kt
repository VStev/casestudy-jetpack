package com.submission.movieandtvshow.core.dataobjects.remote.dataentities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "shows")
data class TVShowEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    var showID: String = "",

    @ColumnInfo(name = "name")
    var title: String = "",

    @ColumnInfo(name = "first_air_date")
    var releaseYear: String = "",

    @ColumnInfo(name = "overview")
    var details: String = "",

    @ColumnInfo(name = "in_production")
    var ongoing: Boolean? = false,

    @ColumnInfo(name = "number_of_episodes")
    var episodes: Int? = 0,

    @ColumnInfo(name = "number_of_seasons")
    var seasons: Int? = 1,

    @ColumnInfo(name = "poster_path")
    var poster: String = "",

    @ColumnInfo(name = "fav")
    var fav: Boolean = false
)
