package com.submission.movieandtvshow.dataobjects.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dataobjects.TVShow

@Dao
interface EntertainmentDAO {
    @Query("SELECT * FROM movies")
    fun getMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM shows")
    fun getShows(): LiveData<List<TVShow>>

    @Query("SELECT * FROM movies WHERE id = :showId")
    fun getMovieDetails(showId: String): LiveData<Movie>

    @Query("SELECT * FROM shows WHERE id = :showId")
    fun getShowDetails(showId: String): LiveData<TVShow>
}