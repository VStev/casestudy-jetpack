package com.submission.movieandtvshow.domain.model

data class Movie(
    var movieID: String = "",
    var title: String = "",
    var releaseYear: String = "",
    var details: String = "",
    var poster: String = "",
    var fav: Boolean = false
)