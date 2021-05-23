package com.submission.movieandtvshow.dataobjects.remote.dataentities

import com.google.gson.annotations.SerializedName
import com.submission.movieandtvshow.dataobjects.MovieEntity

data class MovieDiscoverContainer(
    @SerializedName("results")
    val result:List<MovieEntity>
)
