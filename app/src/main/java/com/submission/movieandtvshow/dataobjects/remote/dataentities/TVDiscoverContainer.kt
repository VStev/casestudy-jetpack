package com.submission.movieandtvshow.dataobjects.remote.dataentities

import com.google.gson.annotations.SerializedName
import com.submission.movieandtvshow.dataobjects.TVShowEntity

data class TVDiscoverContainer (
    @SerializedName("results")
    val result:List<TVShowEntity>
)