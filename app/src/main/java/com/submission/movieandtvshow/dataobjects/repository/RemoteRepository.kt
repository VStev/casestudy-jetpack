package com.submission.movieandtvshow.dataobjects.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dataobjects.TVShow
import com.submission.movieandtvshow.utilities.AppExecutors
import com.submission.movieandtvshow.vo.Resource
import com.submission.movieandtvshow.webapi.ApiResponse
import com.submission.movieandtvshow.webapi.RetrofitCallback

//singleton obj
class RemoteRepository(
    private val retrofit: RetrofitCallback,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors) {

    companion object {
        @Volatile
        private var instance: RemoteRepository? = null

        fun getInstance(retrofit: RetrofitCallback, localData: LocalDataSource, appExecutors: AppExecutors): RemoteRepository =
            instance ?: synchronized(this) {
                instance ?: RemoteRepository(retrofit, localData, appExecutors).apply { instance = this }
            }
    }

    fun getMovies(): LiveData<Resource<List<Movie>>> {
        return object: NetworkBoundResource<List<Movie>, List<Movie>>(appExecutors){
            override fun loadFromDB(): LiveData<List<Movie>> = localDataSource.getMovies()

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> = retrofit.getMovies()

            override fun saveCallResult(data: List<Movie>) {
                localDataSource.insertMovies(data)
            }
        }.asLiveData()
    }

    fun getShows(): LiveData<Resource<List<TVShow>>> {
        return object: NetworkBoundResource<List<TVShow>, List<TVShow>>(appExecutors){
            override fun loadFromDB(): LiveData<List<TVShow>> = localDataSource.getShows()

            override fun shouldFetch(data: List<TVShow>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TVShow>>> = retrofit.getShows()

            override fun saveCallResult(data: List<TVShow>) {
                localDataSource.insertShows(data)
            }
        }.asLiveData()
    }

    fun getShowDetails(showId: String): LiveData<Resource<TVShow>> {
        return object: NetworkBoundResource<TVShow, TVShow>(appExecutors){
            override fun loadFromDB(): LiveData<TVShow> = localDataSource.getShowDetails(showId)

            override fun shouldFetch(data: TVShow?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<TVShow>> = retrofit.getShowDetails(showId)

            override fun saveCallResult(data: TVShow) {
                localDataSource.insertSingleShow(data)
            }
        }.asLiveData()
    }

    fun getMovieDetail(showId: String): LiveData<Resource<Movie>> {
        return object: NetworkBoundResource<Movie, Movie>(appExecutors){
            override fun loadFromDB(): LiveData<Movie> = localDataSource.getMovieDetails(showId)

            override fun shouldFetch(data: Movie?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<Movie>> = retrofit.getMovieDetail(showId)

            override fun saveCallResult(data: Movie) {
                localDataSource.insertSingleMovie(data)
            }
        }.asLiveData()
    }

    fun getFavouriteMovies(): LiveData<List<Movie>> = localDataSource.getFavMovie(true)

    fun getFavouriteShows(): LiveData<List<TVShow>> = localDataSource.getFavShow(true)

    fun setFavouriteMovie(show: String, state: Boolean){
        Log.d("RAISHUU", "setFavouriteMovie: CALLED $state")
        appExecutors.diskIO().execute { localDataSource.setFavouriteMovie(show, state) }
    }

    fun setFavouriteShow(show: String, state: Boolean){
        Log.d("RAISHUU", "setFavouriteShow: CALLED $state")
        appExecutors.diskIO().execute { localDataSource.setFavouriteShow(show, state) }
    }
}