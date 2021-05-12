package com.submission.movieandtvshow.webapi

import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dataobjects.TVShow
import com.submission.movieandtvshow.dataobjects.remote.dataentities.MovieDiscoverContainer
import com.submission.movieandtvshow.dataobjects.remote.dataentities.TVDiscoverContainer
import com.submission.movieandtvshow.utilities.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallback(private val retrofit: RetrofitGenerator) {

    companion object {
        @Volatile
        private var instance: RetrofitCallback? = null

        fun getInstance(retrofit: RetrofitGenerator): RetrofitCallback =
            instance ?: synchronized(this) {
                instance ?: RetrofitCallback(retrofit).apply { instance = this }
            }
    }

    fun getMovies(callbackInterfaces: CallbackInterfaces<List<Movie>>){
        EspressoIdlingResource.increment()
        val call = retrofit.tvMovieRetrofit().create(RetrofitInterfaces::class.java).discoverMovie()
        call.enqueue(object : Callback<MovieDiscoverContainer> {
            override fun onResponse(
                call: Call<MovieDiscoverContainer>,
                response: Response<MovieDiscoverContainer>
            ) {
                if (response.isSuccessful){
                    response.body()?.result?.let { callbackInterfaces.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<MovieDiscoverContainer>, t: Throwable) {
                callbackInterfaces.onFailure(t)
            }
        })
        EspressoIdlingResource.decrement()
    }

    fun getShows(callbackInterfaces: CallbackInterfaces<List<TVShow>>) {
        EspressoIdlingResource.increment()
        val call = retrofit.tvMovieRetrofit().create(RetrofitInterfaces::class.java).discoverTv()
        call.enqueue(object : Callback<TVDiscoverContainer> {
            override fun onResponse(
                call: Call<TVDiscoverContainer>,
                response: Response<TVDiscoverContainer>
            ) {
                if (response.isSuccessful){
                    response.body()?.result?.let { callbackInterfaces.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<TVDiscoverContainer>, t: Throwable) {
                callbackInterfaces.onFailure(t)
            }
        })
        EspressoIdlingResource.decrement()
    }

    fun getShowDetails(showId: String, callbackInterfaces: CallbackInterfaces<TVShow>){
        EspressoIdlingResource.increment()
        val call = retrofit.tvMovieRetrofit().create(RetrofitInterfaces::class.java).getShowDetails(showId)
        call.enqueue(object : Callback<TVShow> {
            override fun onResponse(
                call: Call<TVShow>,
                response: Response<TVShow>
            ) {
                response.body()?.let { callbackInterfaces.onSuccess(it) }
            }

            override fun onFailure(call: Call<TVShow>, t: Throwable) {
                callbackInterfaces.onFailure(t)
            }
        })
        EspressoIdlingResource.decrement()
    }

    fun getMovieDetail(showId: String, callbackInterfaces: CallbackInterfaces<Movie>){
        EspressoIdlingResource.increment()
        val call = retrofit.tvMovieRetrofit().create(RetrofitInterfaces::class.java).getMovieDetails(showId)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                response.body()?.let { callbackInterfaces.onSuccess(it) }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                callbackInterfaces.onFailure(t)
            }
        })
        EspressoIdlingResource.decrement()
    }
}