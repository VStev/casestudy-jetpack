package com.submission.movieandtvshow.viewmodelproviders

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dataobjects.TVShow
import com.submission.movieandtvshow.dataobjects.repository.RemoteRepository

class ShowDetailsViewModel(RemoteRepository: RemoteRepository) : ViewModel() {
    private var showID : String = ""
    private val repository = RemoteRepository

    fun setShowID(showID: String?){
        if (showID != null) {
            this.showID = showID
        }
    }

    fun getMovie(): LiveData<Movie> {
        return repository.getMovieDetail(showID)
    }

    fun getShow(): LiveData<TVShow> {
        return repository.getShowDetails(showID)
    }
}