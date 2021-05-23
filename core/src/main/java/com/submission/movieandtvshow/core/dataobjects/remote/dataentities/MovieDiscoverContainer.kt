package com.submission.movieandtvshow.core.dataobjects.remote.dataentities

import com.google.gson.annotations.SerializedName
import com.submission.movieandtvshow.core.dataobjects.MovieEntity

data class MovieDiscoverContainer(
    @SerializedName("results")
    val result:List<MovieEntity>
)
