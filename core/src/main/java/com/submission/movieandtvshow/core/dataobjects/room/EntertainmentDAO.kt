package com.submission.movieandtvshow.core.dataobjects.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.submission.movieandtvshow.core.dataobjects.MovieEntity
import com.submission.movieandtvshow.core.dataobjects.TVShowEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface EntertainmentDAO {
    @Query("SELECT * FROM movies")
    fun getMovies(): Flowable<List<MovieEntity>>

    @Query("SELECT * FROM shows")
    fun getShows(): Flowable<List<TVShowEntity>>

    @Query("SELECT * FROM movies WHERE fav = :fav")
    fun getFavMovie(fav: Boolean): Flowable<List<MovieEntity>>

    @Query("SELECT * FROM shows WHERE fav = :fav")
    fun getFavShow(fav: Boolean): Flowable<List<TVShowEntity>>

    @Query("SELECT * FROM movies WHERE id = :showId")
    fun getMovieDetails(showId: String): Flowable<MovieEntity>

    @Query("SELECT * FROM shows WHERE id = :showId")
    fun getShowDetails(showId: String): Flowable<TVShowEntity>

    @Query("UPDATE movies SET fav = :fav WHERE id = :showId")
    fun setFavouriteMovie(fav: Boolean, showId: String)

    @Query("UPDATE shows SET fav = :fav WHERE id = :showId")
    fun setFavouriteShow(fav: Boolean, showId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(show: List<MovieEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShows(show: List<TVShowEntity>): Completable
}