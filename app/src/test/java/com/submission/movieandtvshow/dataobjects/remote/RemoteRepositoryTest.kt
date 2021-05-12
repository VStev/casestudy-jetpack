package com.submission.movieandtvshow.dataobjects.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dataobjects.TVShow
import com.submission.movieandtvshow.dataobjects.remote.dataentities.MovieDiscoverContainer
import com.submission.movieandtvshow.dataobjects.remote.dataentities.TVDiscoverContainer
import com.submission.movieandtvshow.dataobjects.repository.RemoteRepository
import com.submission.movieandtvshow.utilities.JsonFilesInKt
import com.submission.movieandtvshow.utilities.LiveDataUtility
import com.submission.movieandtvshow.webapi.CallbackInterfaces
import com.submission.movieandtvshow.webapi.RetrofitCallback
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner.Silent::class)
class RemoteRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: RemoteRepository

    @Mock
    private lateinit var retrofitCallback: RetrofitCallback

    @Before
    fun setUp() {
        repository = RemoteRepository(retrofitCallback)
    }

    @Test
    fun getMovies() {
        val movieLists: List<Movie> = Gson().fromJson(JsonFilesInKt.discoverMovie, MovieDiscoverContainer::class.java).result
        doAnswer { invocation ->
            (invocation.arguments[0] as CallbackInterfaces<List<Movie>>).apply{
                onSuccess(movieLists)
                onFailure(NullPointerException("E41"))
            }
            null
        }.`when`(retrofitCallback).getMovies(any())
        val movies = LiveDataUtility.getValue(repository.getMovies())
        verify(retrofitCallback).getMovies(any())
        assertNotNull(movies)
        assertEquals(movieLists.size, movies.size)
    }

    @Test
    fun getShows() {
        val showLists: List<TVShow> = Gson().fromJson(JsonFilesInKt.discoverShow, TVDiscoverContainer::class.java).result
        doAnswer { invocation ->
            (invocation.arguments[0] as CallbackInterfaces<List<TVShow>>).apply{
                onSuccess(showLists)
                onFailure(NullPointerException("E41"))
            }
            null
        }.`when`(retrofitCallback).getShows(any())
        val shows = LiveDataUtility.getValue(repository.getShows())
        verify(retrofitCallback).getShows(any())
        assertNotNull(shows)
        assertEquals(showLists.size, shows.size)
    }

    @Test
    fun getShowDetails() {
        val showPreset: TVShow = Gson().fromJson(JsonFilesInKt.showPreset, TVShow::class.java)
        doAnswer { invocation ->
            (invocation.arguments[1] as CallbackInterfaces<TVShow>).apply{
                onSuccess(showPreset)
                onFailure(NullPointerException("E41"))
            }
            null
        }.`when`(retrofitCallback).getShowDetails(eq("78204"), any())
        val showDetail = LiveDataUtility.getValue(repository.getShowDetails("78204"))
        verify(retrofitCallback).getShowDetails(eq("78204"), any())
        assertNotNull(showDetail)
        assertEquals("78204", showDetail.showID)
    }

    @Test
    fun getMovieDetail() {
        val moviePreset: Movie = Gson().fromJson(JsonFilesInKt.moviePreset, Movie::class.java)
        doAnswer { invocation ->
            (invocation.arguments[1] as CallbackInterfaces<Movie>).apply{
                onSuccess(moviePreset)
                onFailure(NullPointerException("E41"))
            }
            null
        }.`when`(retrofitCallback).getMovieDetail(eq("458576"), any())
        val movieDetail = LiveDataUtility.getValue(repository.getMovieDetail("458576"))
        verify(retrofitCallback).getMovieDetail(eq("458576"), any())
        assertNotNull(movieDetail)
        assertEquals("458576", movieDetail.movieID)
    }
}