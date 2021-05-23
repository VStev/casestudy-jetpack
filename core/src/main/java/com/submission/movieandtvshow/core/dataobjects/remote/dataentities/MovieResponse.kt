package com.submission.movieandtvshow.core.dataobjects.remote.dataentities

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    var movieID: String = "",

    @SerializedName("title")
    var title: String = "",

    @SerializedName("release_date")
    var releaseYear: String = "",

    @SerializedName("overview")
    var details: String = "",

    @SerializedName("poster_path")
    var poster: String = ""
)
