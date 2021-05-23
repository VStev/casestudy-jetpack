package com.submission.movieandtvshow.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.submission.movieandtvshow.core.domain.model.Movie
import com.submission.movieandtvshow.core.domain.model.TVShow
import com.submission.movieandtvshow.core.domain.usecase.MovieShowUseCase

class FavouriteViewModel(private val repository: MovieShowUseCase): ViewModel() {
    fun getFavouriteMovies(): LiveData<List<Movie>> {
        return LiveDataReactiveStreams.fromPublisher(repository.getFavouriteMovies())
    }

    fun getFavouriteShows(): LiveData<List<TVShow>> {
        return LiveDataReactiveStreams.fromPublisher(repository.getFavouriteShows())
    }
}