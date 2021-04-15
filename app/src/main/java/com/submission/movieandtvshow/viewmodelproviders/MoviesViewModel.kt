package com.submission.movieandtvshow.viewmodelproviders

import androidx.lifecycle.ViewModel
import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dummydatas.Dummy

class MoviesViewModel: ViewModel() {
    private val movieList = arrayListOf<Movie>()

    fun getMovies(): ArrayList<Movie> {
        movieList.addAll(Dummy.generateMovies())
        return movieList
    }
}