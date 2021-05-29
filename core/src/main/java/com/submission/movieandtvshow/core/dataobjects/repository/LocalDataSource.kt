package com.submission.movieandtvshow.core.dataobjects.repository

import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.MovieEntity
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.TVShowEntity
import com.submission.movieandtvshow.core.dataobjects.room.EntertainmentDAO
import io.reactivex.Flowable

class LocalDataSource(private val entertainmentDAO: EntertainmentDAO){

    fun getMovies(): Flowable<List<MovieEntity>> = entertainmentDAO.getMovies()

    fun getShows(): Flowable<List<TVShowEntity>> = entertainmentDAO.getShows()

    fun getMovieDetails(showId: String): Flowable<MovieEntity> = entertainmentDAO.getMovieDetails(showId)

    fun getShowDetails(showId: String): Flowable<TVShowEntity> = entertainmentDAO.getShowDetails(showId)

    fun getFavMovie(fav: Boolean): Flowable<List<MovieEntity>> = entertainmentDAO.getFavMovie(fav)

    fun getFavShow(fav: Boolean): Flowable<List<TVShowEntity>> = entertainmentDAO.getFavShow(fav)

    fun insertMovies(movies: List<MovieEntity>) = entertainmentDAO.insertMovies(movies)

    fun insertShows(shows: List<TVShowEntity>) = entertainmentDAO.insertShows(shows)

    fun setFavouriteMovie(show: String, state: Boolean) = entertainmentDAO.setFavouriteMovie(state, show)

    fun setFavouriteShow(show: String, state: Boolean) = entertainmentDAO.setFavouriteShow(state, show)

    fun updateShow(showId: String, episode: Int, season: Int) = entertainmentDAO.updateShow(showId, episode, season)
}