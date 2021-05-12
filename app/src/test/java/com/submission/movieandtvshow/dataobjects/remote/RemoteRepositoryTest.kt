package com.submission.movieandtvshow.dataobjects.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dataobjects.TVShow
import com.submission.movieandtvshow.dataobjects.remote.dataentities.MovieDiscoverContainer
import com.submission.movieandtvshow.dataobjects.remote.dataentities.TVDiscoverContainer
import com.submission.movieandtvshow.dataobjects.repository.LocalDataSource
import com.submission.movieandtvshow.dataobjects.repository.RemoteRepository
import com.submission.movieandtvshow.utilities.AppExecutors
import com.submission.movieandtvshow.utilities.JsonFilesInKt
import com.submission.movieandtvshow.utilities.LiveDataUtility
import com.submission.movieandtvshow.webapi.RetrofitCallback
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner.Silent::class)
class RemoteRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: RemoteRepository

    @Mock
    private lateinit var retrofitCallback: RetrofitCallback

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var appExecutors: AppExecutors

    @Before
    fun setUp() {
        repository = RemoteRepository(retrofitCallback, localDataSource, appExecutors)
    }

    @Test
    fun getMovies() {
        val movieLists: List<Movie> = Gson().fromJson(JsonFilesInKt.discoverMovie, MovieDiscoverContainer::class.java).result
        val result =  MutableLiveData<List<Movie>>()
        result.value = movieLists
        `when`(localDataSource.getMovies()).thenReturn(result)
        val shows = LiveDataUtility.getValue(repository.getMovies())
        verify(localDataSource).getMovies()
        assertNotNull(shows)
        assertEquals(movieLists.size, shows.data?.size)
    }

    @Test
    fun getShows() {
        val showLists: List<TVShow> = Gson().fromJson(JsonFilesInKt.discoverShow, TVDiscoverContainer::class.java).result
        val result =  MutableLiveData<List<TVShow>>()
        result.value = showLists
        `when`(localDataSource.getShows()).thenReturn(result)
        val shows = LiveDataUtility.getValue(repository.getShows())
        verify(localDataSource).getShows()
        assertNotNull(shows)
        assertEquals(showLists.size, shows.data?.size)
    }

    @Test
    fun getShowDetails() {
        val showPreset: TVShow = Gson().fromJson(JsonFilesInKt.showPreset, TVShow::class.java)
        val result =  MutableLiveData<TVShow>()
        result.value = showPreset
        `when`(localDataSource.getShowDetails(eq("78204"))).thenReturn(result)
        val shows = LiveDataUtility.getValue(repository.getShowDetails(eq("78204")))
        verify(localDataSource).getShowDetails(eq("78204"))
        assertNotNull(shows)
        assertEquals("78204", shows.data?.showID)
    }

    @Test
    fun getMovieDetail() {
        val moviePreset: Movie = Gson().fromJson(JsonFilesInKt.moviePreset, Movie::class.java)
        val result =  MutableLiveData<Movie>()
        result.value = moviePreset
        `when`(localDataSource.getMovieDetails(eq("458576"))).thenReturn(result)
        val shows = LiveDataUtility.getValue(repository.getMovieDetail(eq("458576")))
        verify(localDataSource).getMovieDetails(eq("458576"))
        assertNotNull(shows)
        assertEquals("458576", shows.data?.movieID)
    }
}