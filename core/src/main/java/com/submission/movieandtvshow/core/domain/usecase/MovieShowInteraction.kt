package com.submission.movieandtvshow.core.domain.usecase

import com.submission.movieandtvshow.core.dataobjects.repository.RemoteRepository
import com.submission.movieandtvshow.core.domain.model.Movie
import com.submission.movieandtvshow.core.domain.model.TVShow
import com.submission.movieandtvshow.core.vo.Resource
import io.reactivex.Flowable

class MovieShowInteraction(private val repository: RemoteRepository): MovieShowUseCase {
    override fun getMovies(): Flowable<Resource<List<Movie>>> = repository.getMovies()

    override fun getShows(): Flowable<Resource<List<TVShow>>> = repository.getShows()

    override fun getShowDetails(showId: String): Flowable<Resource<TVShow>> = repository.getShowDetails(showId)

    override fun getMovieDetail(showId: String): Flowable<Resource<Movie>> = repository.getMovieDetail(showId)

    override fun getFavouriteMovies(): Flowable<List<Movie>> = repository.getFavouriteMovies()

    override fun getFavouriteShows(): Flowable<List<TVShow>> = repository.getFavouriteShows()

    override fun setFavouriteMovie(show: String, state: Boolean) {
        repository.setFavouriteMovie(show, state)
    }

    override fun setFavouriteShow(show: String, state: Boolean) {
        repository.setFavouriteShow(show, state)
    }
}