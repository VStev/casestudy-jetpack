package com.submission.movieandtvshow.core.utilities

import com.submission.movieandtvshow.core.dataobjects.MovieEntity
import com.submission.movieandtvshow.core.dataobjects.TVShowEntity
import com.submission.movieandtvshow.core.domain.model.Movie
import com.submission.movieandtvshow.core.domain.model.TVShow

object ClassMapper {
    fun mapMovieEntityToDomain(input: MovieEntity): Movie {
        return Movie(
            input.movieID,
            input.title,
            input.releaseYear,
            input.details,
            input.poster,
            input.fav
        )
    }

    fun mapMovieEntityToDomain(input: List<MovieEntity>): List<Movie>{
        val returnValue = ArrayList<Movie>()
        input.map{
            returnValue.add(
                Movie(
                    it.movieID,
                    it.title,
                    it.releaseYear,
                    it.details,
                    it.poster,
                    it.fav
                )
            )
        }
        return returnValue
    }

    fun mapShowEntityToDomain(input: TVShowEntity): TVShow {
        return TVShow(
            input.showID,
            input.title,
            input.releaseYear,
            input.details,
            input.ongoing,
            input.episodes,
            input.seasons,
            input.poster,
            input.fav
        )
    }

    fun mapShowEntityToDomain(input: List<TVShowEntity>): List<TVShow>{
        val returnValue = ArrayList<TVShow>()
        input.map{
            returnValue.add(
                TVShow(
                    it.showID,
                    it.title,
                    it.releaseYear,
                    it.details,
                    it.ongoing,
                    it.episodes,
                    it.seasons,
                    it.poster,
                    it.fav
                )
            )
        }
        return returnValue
    }
}