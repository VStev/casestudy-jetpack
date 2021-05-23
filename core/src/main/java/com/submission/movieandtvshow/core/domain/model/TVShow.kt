package com.submission.movieandtvshow.core.domain.model

data class TVShow (
    var showID: String = "",
    var title: String = "",
    var releaseYear: String = "",
    var details: String = "",
    var ongoing: Boolean? = false,
    var episodes: Int? = 0,
    var seasons: Int? = 1,
    var poster: String = "",
    var fav: Boolean = false
)