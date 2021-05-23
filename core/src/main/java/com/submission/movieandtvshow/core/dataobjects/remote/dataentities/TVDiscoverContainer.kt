package com.submission.movieandtvshow.core.dataobjects.remote.dataentities

import com.google.gson.annotations.SerializedName

data class TVDiscoverContainer (
    @SerializedName("results")
    val result:List<ShowResponse>
)