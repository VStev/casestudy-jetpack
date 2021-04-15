package com.submission.movieandtvshow.dataobjects

data class TVShow(
    val showID: String? = "",
    val title: String = "",
    val releaseYear: String = "",
    val details: String = "",
    val ongoing: Boolean = false,
    val episodes: Int = 0,
    val seasons: Int = 1,
    val poster: Int = 0
)
