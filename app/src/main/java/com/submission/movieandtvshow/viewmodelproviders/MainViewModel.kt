package com.submission.movieandtvshow.viewmodelproviders

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.submission.movieandtvshow.core.domain.model.Movie
import com.submission.movieandtvshow.core.domain.model.TVShow
import com.submission.movieandtvshow.core.domain.usecase.MovieShowUseCase
import com.submission.movieandtvshow.core.vo.Resource

class MainViewModel(private val repository: MovieShowUseCase) : ViewModel()  {

    fun getMovies(): LiveData<Resource<List<Movie>>> {
        return LiveDataReactiveStreams.fromPublisher(repository.getMovies())
    }

    fun getShows(): LiveData<Resource<List<TVShow>>> {
        return LiveDataReactiveStreams.fromPublisher(repository.getShows())
    }
}