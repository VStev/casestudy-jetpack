package com.submission.movieandtvshow.core.domain.repository

import com.submission.movieandtvshow.core.domain.model.Movie
import com.submission.movieandtvshow.core.domain.model.TVShow
import com.submission.movieandtvshow.core.vo.Resource
import io.reactivex.Flowable

interface RemoteRepoInterface {
    fun getMovies(): Flowable<Resource<List<Movie>>>

    fun getShows(): Flowable<Resource<List<TVShow>>>

    fun getShowDetails(showId: String): Flowable<Resource<TVShow>>

    fun getMovieDetail(showId: String): Flowable<Resource<Movie>>

    fun getFavouriteMovies(): Flowable<List<Movie>>

    fun getFavouriteShows(): Flowable<List<TVShow>>

    fun setFavouriteMovie(show: String, state: Boolean)

    fun setFavouriteShow(show: String, state: Boolean)
}