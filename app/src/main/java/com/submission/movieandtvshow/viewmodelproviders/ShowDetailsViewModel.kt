package com.submission.movieandtvshow.viewmodelproviders

import androidx.lifecycle.ViewModel
import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dataobjects.TVShow
import com.submission.movieandtvshow.dummydatas.Dummy

class ShowDetailsViewModel: ViewModel() {
    private var showID : String = ""
    private var movie : Movie? = null
    private var show : TVShow? = null

    fun setShowID(showID: String){
        this.showID = showID
    }

    fun getMovie(): Movie? {
        val movies = Dummy.generateMovies()
        for (movie in movies){
            if (movie.movieID == showID) {
                this.movie = movie
            }
        }
        return movie
    }

    fun getShow(): TVShow? {
        val shows = Dummy.generateShows()
        for (show in shows){
            if (show.showID == showID){
                this.show = show
            }
        }
        return show
    }
}