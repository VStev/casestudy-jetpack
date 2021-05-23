package com.submission.movieandtvshow.core.utilities

import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.MovieEntity
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.MovieResponse
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.ShowResponse
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.TVShowEntity
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

    fun mapMovieResponseToEntity(input: List<MovieResponse>): List<MovieEntity>{
        val returnValue = ArrayList<MovieEntity>()
        input.map{
            returnValue.add(
                MovieEntity(
                    it.movieID,
                    it.title,
                    it.releaseYear,
                    it.details,
                    it.poster
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

    fun mapShowResponseToEntity(input: List<ShowResponse>): List<TVShowEntity>{
        val returnValue = ArrayList<TVShowEntity>()
        input.map{
            returnValue.add(
                TVShowEntity(
                    it.showID,
                    it.title,
                    it.releaseYear,
                    it.details,
                    it.ongoing,
                    it.episodes,
                    it.seasons,
                    it.poster
                )
            )
        }
        return returnValue
    }

/*    fun mapShowResponseToEntity(input: ShowResponse): TVShowEntity{
        return TVShowEntity(
            input.showID,
            input.title,
            input.releaseYear,
            input.details,
            input.ongoing,
            input.episodes,
            input.seasons,
            input.poster
        )
    }

    fun mapMovieResponseToEntity(input: MovieResponse): MovieEntity {
        return MovieEntity(
            input.movieID,
            input.title,
            input.releaseYear,
            input.details,
            input.poster
        )
    }*/
}