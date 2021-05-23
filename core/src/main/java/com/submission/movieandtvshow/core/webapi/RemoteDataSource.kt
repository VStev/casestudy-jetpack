package com.submission.movieandtvshow.core.webapi

import android.annotation.SuppressLint
import android.util.Log
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.MovieEntity
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.MovieResponse
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.ShowResponse
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.TVShowEntity
import com.submission.movieandtvshow.core.utilities.EspressoIdlingResource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource(private val retrofit: RetrofitInterfaces) {

    @SuppressLint("CheckResult")
    fun getMovies(): Flowable<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val returnValue = PublishSubject.create<ApiResponse<List<MovieResponse>>>()
        val call = retrofit.discoverMovie()
        call
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                val dataArray = response.result
                returnValue.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                returnValue.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        EspressoIdlingResource.decrement()
        return returnValue.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getShows(): Flowable<ApiResponse<List<ShowResponse>>> {
        EspressoIdlingResource.increment()
        val returnValue = PublishSubject.create<ApiResponse<List<ShowResponse>>>()
        val call = retrofit.discoverTv()
        call
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                val dataArray = response.result
                returnValue.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                returnValue.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        EspressoIdlingResource.decrement()
        return returnValue.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getShowDetails(showId: String): Flowable<ApiResponse<ShowResponse>>{
        EspressoIdlingResource.increment()
        val returnValue = PublishSubject.create<ApiResponse<ShowResponse>>()
        val call = retrofit.getShowDetails(showId)
        call
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                returnValue.onNext(if (response.showID.isNotEmpty()) ApiResponse.Success(response) else ApiResponse.Empty)
            }, { error ->
                returnValue.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        EspressoIdlingResource.decrement()
        return returnValue.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getMovieDetail(showId: String): Flowable<ApiResponse<MovieResponse>>{
        EspressoIdlingResource.increment()
        val returnValue = PublishSubject.create<ApiResponse<MovieResponse>>()
        val call = retrofit.getMovieDetails(showId)
        call
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                returnValue.onNext(if (response.movieID.isNotEmpty()) ApiResponse.Success(response) else ApiResponse.Empty)
            }, { error ->
                returnValue.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        EspressoIdlingResource.decrement()
        return returnValue.toFlowable(BackpressureStrategy.BUFFER)
    }
}