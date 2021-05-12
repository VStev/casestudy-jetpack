package com.submission.movieandtvshow.viewmodelproviders

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dataobjects.TVShow
import com.submission.movieandtvshow.dataobjects.repository.RemoteRepository

class MainViewModel(RemoteRepository: RemoteRepository) : ViewModel()  {
    private val repository = RemoteRepository

    fun getMovies(): LiveData<List<Movie>> {
        return repository.getMovies()
    }

    fun getShows(): LiveData<List<TVShow>> {
        return repository.getShows()
    }
}