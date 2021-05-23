package com.submission.movieandtvshow.webapi

import com.submission.movieandtvshow.BuildConfig
import com.submission.movieandtvshow.dataobjects.MovieEntity
import com.submission.movieandtvshow.dataobjects.TVShowEntity
import com.submission.movieandtvshow.dataobjects.remote.dataentities.MovieDiscoverContainer
import com.submission.movieandtvshow.dataobjects.remote.dataentities.TVDiscoverContainer
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RetrofitInterfaces {
    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("discover/movie")
    fun discoverMovie(): Flowable<MovieDiscoverContainer>

    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("discover/tv")
    fun discoverTv(): Flowable<TVDiscoverContainer>

    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("movie/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId: String): Flowable<MovieEntity>

    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("tv/{tvId}")
    fun getShowDetails(@Path("tvId") tvId: String): Flowable<TVShowEntity>
}