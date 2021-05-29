package com.submission.movieandtvshow.core.dataobjects.repository

import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.MovieResponse
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.ShowResponse
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
        return object: NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors){
            override fun loadFromDB(): Flowable<List<Movie>> {
                return localDataSource.getMovies().map{ClassMapper.mapMovieEntityToDomain(it)}
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<MovieResponse>>> = retrofit.getMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val remappedData = ClassMapper.mapMovieResponseToEntity(data)
                localDataSource.insertMovies(remappedData)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
    }

    override fun getShows(): Flowable<Resource<List<TVShow>>> {
        return object: NetworkBoundResource<List<TVShow>, List<ShowResponse>>(appExecutors){
            override fun loadFromDB(): Flowable<List<TVShow>> {
                return localDataSource.getShows().map{ ClassMapper.mapShowEntityToDomain(it) }
            }

            override fun shouldFetch(data: List<TVShow>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<ShowResponse>>> = retrofit.getShows()

            override fun saveCallResult(data: List<ShowResponse>) {
                val remappedData = ClassMapper.mapShowResponseToEntity(data)
                localDataSource.insertShows(remappedData)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
    }

    override fun getShowDetails(showId: String): Flowable<Resource<TVShow>> {
        return object: NetworkBoundResource<TVShow, ShowResponse>(appExecutors){
            override fun loadFromDB(): Flowable<TVShow> = localDataSource.getShowDetails(showId).map{ ClassMapper.mapShowEntityToDomain(it) }

            override fun shouldFetch(data: TVShow?): Boolean = true

            override fun createCall(): Flowable<ApiResponse<ShowResponse>> = retrofit.getShowDetails(showId)

            override fun saveCallResult(data: ShowResponse) {
                val show = data.showID
                val episode = data.episodes
                val season = data.seasons
                appExecutors.diskIO().execute { localDataSource.updateShow(show, episode, season) }
            }
        }.asFlowable()
    }

    override fun getMovieDetail(showId: String): Flowable<Resource<Movie>> {
        return object: NetworkBoundResource<Movie, MovieResponse>(appExecutors){
            override fun loadFromDB(): Flowable<Movie> = localDataSource.getMovieDetails(showId).map{ ClassMapper.mapMovieEntityToDomain(it) }

            override fun shouldFetch(data: Movie?): Boolean = false

            override fun createCall(): Flowable<ApiResponse<MovieResponse>> = retrofit.getMovieDetail(showId)

            override fun saveCallResult(data: MovieResponse) {

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