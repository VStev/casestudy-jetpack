package com.submission.movieandtvshow.viewmodelproviders

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.submission.movieandtvshow.dataobjects.MovieEntity
import com.submission.movieandtvshow.dataobjects.TVShowEntity
import com.submission.movieandtvshow.domain.model.Movie
import com.submission.movieandtvshow.domain.model.TVShow
import com.submission.movieandtvshow.domain.usecase.MovieShowUseCase
import com.submission.movieandtvshow.vo.Resource

class ShowDetailsViewModel(private val repository: MovieShowUseCase) : ViewModel() {
    private var showID : String = ""

    fun setShowID(showID: String?){
        if (showID != null) {
            this.showID = showID
        }
    }

    fun getMovie(): LiveData<Resource<Movie>> {
        return LiveDataReactiveStreams.fromPublisher(repository.getMovieDetail(showID))
    }

    fun getShow(): LiveData<Resource<TVShow>> {
        return LiveDataReactiveStreams.fromPublisher(repository.getShowDetails(showID))
    }

    fun setFav(argument: Int, state: Boolean) {
        when (argument){
            1 -> {
                repository.setFavouriteShow(showID, state)
            }
            2 -> {
                repository.setFavouriteMovie(showID, state)
            }
        }
    }
}