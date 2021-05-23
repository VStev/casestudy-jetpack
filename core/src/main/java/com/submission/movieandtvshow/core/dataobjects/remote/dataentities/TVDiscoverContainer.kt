package com.submission.movieandtvshow.core.dataobjects.remote.dataentities

import com.google.gson.annotations.SerializedName
import com.submission.movieandtvshow.core.dataobjects.TVShowEntity

data class TVDiscoverContainer (
    @SerializedName("results")
    val result:List<TVShowEntity>
)