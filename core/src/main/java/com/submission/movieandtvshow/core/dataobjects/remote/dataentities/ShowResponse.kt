package com.submission.movieandtvshow.core.dataobjects.remote.dataentities

import com.google.gson.annotations.SerializedName

data class ShowResponse(
    @SerializedName("id")
    var showID: String = "",

    @SerializedName("name")
    var title: String = "",

    @SerializedName("first_air_date")
    var releaseYear: String = "",

    @SerializedName("overview")
    var details: String = "",

    @SerializedName("in_production")
    var ongoing: Boolean? = false,

    @SerializedName("number_of_episodes")
    var episodes: Int = 0,

    @SerializedName("number_of_seasons")
    var seasons: Int = 1,

    @SerializedName("poster_path")
    var poster: String = ""
)
