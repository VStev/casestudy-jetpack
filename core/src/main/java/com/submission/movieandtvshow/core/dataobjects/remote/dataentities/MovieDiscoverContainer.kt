package com.submission.movieandtvshow.core.dataobjects.remote.dataentities

import com.google.gson.annotations.SerializedName

data class MovieDiscoverContainer(
    @SerializedName("results")
    val result:List<MovieResponse>
)
