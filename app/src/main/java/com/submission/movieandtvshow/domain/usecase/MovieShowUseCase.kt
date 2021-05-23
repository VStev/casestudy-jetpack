package com.submission.movieandtvshow.domain.usecase

import com.submission.movieandtvshow.domain.model.Movie
import com.submission.movieandtvshow.domain.model.TVShow
import com.submission.movieandtvshow.vo.Resource
import io.reactivex.Flowable

interface MovieShowUseCase {
    fun getMovies(): Flowable<Resource<List<Movie>>>

    fun getShows(): Flowable<Resource<List<TVShow>>>

    fun getShowDetails(showId: String): Flowable<Resource<TVShow>>

    fun getMovieDetail(showId: String): Flowable<Resource<Movie>>

    fun getFavouriteMovies(): Flowable<List<Movie>>

    fun getFavouriteShows(): Flowable<List<TVShow>>

    fun setFavouriteMovie(show: String, state: Boolean)

    fun setFavouriteShow(show: String, state: Boolean)
}