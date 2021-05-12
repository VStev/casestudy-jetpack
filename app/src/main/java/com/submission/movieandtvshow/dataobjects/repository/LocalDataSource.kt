package com.submission.movieandtvshow.dataobjects.repository

import androidx.lifecycle.LiveData
import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dataobjects.TVShow
import com.submission.movieandtvshow.dataobjects.room.EntertainmentDAO

class LocalDataSource private constructor(private val entertainmentDAO: EntertainmentDAO){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(entertainmentDAO: EntertainmentDAO): LocalDataSource =
            INSTANCE ?: LocalDataSource(entertainmentDAO)
    }

    fun getMovies(): LiveData<List<Movie>> = entertainmentDAO.getMovies()

    fun getShows(): LiveData<List<TVShow>> = entertainmentDAO.getShows()

    fun getMovieDetails(showId: String): LiveData<Movie> = entertainmentDAO.getMovieDetails(showId)

    fun getShowDetails(showId: String): LiveData<TVShow> = entertainmentDAO.getShowDetails(showId)

    fun getFavMovie(fav: Boolean): LiveData<List<Movie>> = entertainmentDAO.getFavMovie(fav)

    fun getFavShow(fav: Boolean): LiveData<List<TVShow>> = entertainmentDAO.getFavShow(fav)

    fun insertMovies(movies: List<Movie>) = entertainmentDAO.insertMovies(movies)

    fun insertShows(shows: List<TVShow>) = entertainmentDAO.insertShows(shows)

    fun insertSingleShow(show: TVShow) = entertainmentDAO.insertSingleShow(show)

    fun insertSingleMovie(show: Movie) = entertainmentDAO.insertSingleMovie(show)

    fun setFavouriteMovie(show: String, state: Boolean){
        entertainmentDAO.setFavouriteMovie(state, show)
    }

    fun setFavouriteShow(show: String, state: Boolean){
        entertainmentDAO.setFavouriteShow(state, show)
    }
}