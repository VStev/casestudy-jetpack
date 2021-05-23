package com.submission.movieandtvshow.core.webapi

import com.submission.movieandtvshow.core.BuildConfig
import com.submission.movieandtvshow.core.dataobjects.MovieEntity
import com.submission.movieandtvshow.core.dataobjects.TVShowEntity
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.MovieDiscoverContainer
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.TVDiscoverContainer
import io.reactivex.Flowable
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