package com.submission.movieandtvshow.dataobjects.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dataobjects.TVShow
import com.submission.movieandtvshow.webapi.CallbackInterfaces
import com.submission.movieandtvshow.webapi.RetrofitCallback

//singleton obj
class RemoteRepository(private val retrofit: RetrofitCallback) {

    companion object {
        @Volatile
        private var instance: RemoteRepository? = null

        fun getInstance(retrofit: RetrofitCallback): RemoteRepository =
            instance ?: synchronized(this) {
                instance ?: RemoteRepository(retrofit).apply { instance = this }
            }
    }

    fun getMovies(): LiveData<List<Movie>> {
        val returnValue = MutableLiveData<List<Movie>>()
        retrofit.getMovies(object : CallbackInterfaces<List<Movie>>{
            override fun onSuccess(item: List<Movie>) {
                returnValue.postValue(item)
            }

            override fun onFailure(throwable: Throwable) {

            }
        })
        return returnValue
    }

    fun getShows(): LiveData<List<TVShow>> {
        val returnValue = MutableLiveData<List<TVShow>>()
        retrofit.getShows(object : CallbackInterfaces<List<TVShow>>{
            override fun onSuccess(item: List<TVShow>) {
                returnValue.postValue(item)
            }

            override fun onFailure(throwable: Throwable) {

            }

        })
        return returnValue
    }

    fun getShowDetails(showId: String): LiveData<TVShow> {
        val container = MutableLiveData<TVShow>()
        retrofit.getShowDetails(showId, object : CallbackInterfaces<TVShow> {
            override fun onSuccess(item: TVShow) {
                container.postValue(item)
            }

            override fun onFailure(throwable: Throwable) {
            }
        })
        return container
    }

    fun getMovieDetail(showId: String): LiveData<Movie> {
        val container = MutableLiveData<Movie>()
        retrofit.getMovieDetail(showId, object : CallbackInterfaces<Movie> {
            override fun onSuccess(item: Movie) {
                container.postValue(item)
            }

            override fun onFailure(throwable: Throwable) {
            }
        })
        return container
    }
}