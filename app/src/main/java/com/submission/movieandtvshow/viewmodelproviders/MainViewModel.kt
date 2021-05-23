package com.submission.movieandtvshow.viewmodelproviders

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.submission.movieandtvshow.dataobjects.MovieEntity
import com.submission.movieandtvshow.dataobjects.TVShowEntity
import com.submission.movieandtvshow.domain.model.Movie
import com.submission.movieandtvshow.domain.model.TVShow
import com.submission.movieandtvshow.domain.usecase.MovieShowUseCase
import com.submission.movieandtvshow.vo.Resource

class MainViewModel(private val repository: MovieShowUseCase) : ViewModel()  {

    fun getMovies(): LiveData<Resource<List<Movie>>> {
        return LiveDataReactiveStreams.fromPublisher(repository.getMovies())
    }

    fun getShows(): LiveData<Resource<List<TVShow>>> {
        return LiveDataReactiveStreams.fromPublisher(repository.getShows())
    }

    fun getFavouriteMovies(): LiveData<List<Movie>> {
        return LiveDataReactiveStreams.fromPublisher(repository.getFavouriteMovies())
    }

    fun getFavouriteShows(): LiveData<List<TVShow>> {
        return LiveDataReactiveStreams.fromPublisher(repository.getFavouriteShows())
    }
}