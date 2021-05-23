package com.submission.movieandtvshow.core.dataobjects.repository

import com.submission.movieandtvshow.core.dataobjects.MovieEntity
import com.submission.movieandtvshow.core.dataobjects.TVShowEntity
import com.submission.movieandtvshow.core.domain.model.Movie
import com.submission.movieandtvshow.core.domain.model.TVShow
import com.submission.movieandtvshow.core.domain.repository.RemoteRepoInterface
import com.submission.movieandtvshow.core.utilities.AppExecutors
import com.submission.movieandtvshow.core.utilities.ClassMapper
import com.submission.movieandtvshow.core.vo.Resource
import com.submission.movieandtvshow.core.webapi.ApiResponse
import com.submission.movieandtvshow.core.webapi.RemoteDataSource
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteRepository(
    private val retrofit: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): RemoteRepoInterface {

    override fun getMovies(): Flowable<Resource<List<Movie>>> {
        return object: com.submission.movieandtvshow.core.dataobjects.repository.NetworkBoundResource<List<Movie>, List<MovieEntity>>(appExecutors){
            override fun loadFromDB(): Flowable<List<Movie>> {
                return localDataSource.getMovies().map{ClassMapper.mapMovieEntityToDomain(it)}
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true

            override fun createCall(): Flowable<ApiResponse<List<MovieEntity>>> = retrofit.getMovies()

            override fun saveCallResult(data: List<MovieEntity>) {
                localDataSource.insertMovies(data)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
    }

    override fun getShows(): Flowable<Resource<List<TVShow>>> {
        return object: com.submission.movieandtvshow.core.dataobjects.repository.NetworkBoundResource<List<TVShow>, List<TVShowEntity>>(appExecutors){
            override fun loadFromDB(): Flowable<List<TVShow>> {
                return localDataSource.getShows().map{ ClassMapper.mapShowEntityToDomain(it) }
            }

            override fun shouldFetch(data: List<TVShow>?): Boolean = true

            override fun createCall(): Flowable<ApiResponse<List<TVShowEntity>>> = retrofit.getShows()

            override fun saveCallResult(data: List<TVShowEntity>) {
                localDataSource.insertShows(data)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
    }

    override fun getShowDetails(showId: String): Flowable<Resource<TVShow>> {
        return object: com.submission.movieandtvshow.core.dataobjects.repository.NetworkBoundResource<TVShow, TVShowEntity>(appExecutors){
            override fun loadFromDB(): Flowable<TVShow> = localDataSource.getShowDetails(showId).map{ ClassMapper.mapShowEntityToDomain(it) }

            override fun shouldFetch(data: TVShow?): Boolean = false

            override fun createCall(): Flowable<ApiResponse<TVShowEntity>> = retrofit.getShowDetails(showId)

            override fun saveCallResult(data: TVShowEntity) {

            }
        }.asFlowable()
    }

    override fun getMovieDetail(showId: String): Flowable<Resource<Movie>> {
        return object: com.submission.movieandtvshow.core.dataobjects.repository.NetworkBoundResource<Movie, MovieEntity>(appExecutors){
            override fun loadFromDB(): Flowable<Movie> = localDataSource.getMovieDetails(showId).map{ ClassMapper.mapMovieEntityToDomain(it) }

            override fun shouldFetch(data: Movie?): Boolean = false

            override fun createCall(): Flowable<ApiResponse<MovieEntity>> = retrofit.getMovieDetail(showId)

            override fun saveCallResult(data: MovieEntity) {

            }
        }.asFlowable()
    }

    override fun getFavouriteMovies(): Flowable<List<Movie>> {
        return localDataSource.getFavMovie(true).map{ ClassMapper.mapMovieEntityToDomain(it) }
    }

    override fun getFavouriteShows(): Flowable<List<TVShow>> {
        return localDataSource.getFavShow(true).map{ ClassMapper.mapShowEntityToDomain(it) }
    }

    override fun setFavouriteMovie(show: String, state: Boolean){
        appExecutors.diskIO().execute { localDataSource.setFavouriteMovie(show, state) }
    }

    override fun setFavouriteShow(show: String, state: Boolean){
        appExecutors.diskIO().execute { localDataSource.setFavouriteShow(show, state) }
    }
}