package com.submission.movieandtvshow.viewmodelproviders

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.verify
import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dataobjects.TVShow
import com.submission.movieandtvshow.dataobjects.repository.RemoteRepository
import com.submission.movieandtvshow.dataobjects.remote.dataentities.MovieDiscoverContainer
import com.submission.movieandtvshow.dataobjects.remote.dataentities.TVDiscoverContainer
import com.submission.movieandtvshow.utilities.JsonFilesInKt
import com.submission.movieandtvshow.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MainViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var televisionObserver: Observer<in Resource<List<TVShow>>>

    @Mock
    private lateinit var movieObserver: Observer<in Resource<List<Movie>>>

    @Mock
    private lateinit var favTvObserver: Observer<in List<TVShow>>

    @Mock
    private lateinit var favMovieObserver: Observer<in List<Movie>>

    @Mock
    private lateinit var repository: RemoteRepository

    @Before
    fun setUp() {
        viewModel = MainViewModel(repository)
    }

    @Test
    fun getMovies() {
        val movieLists: Resource<List<Movie>> = Resource.success(Gson().fromJson(JsonFilesInKt.discoverMovie, MovieDiscoverContainer::class.java).result)
        val result = MutableLiveData<Resource<List<Movie>>>()
        result.value = movieLists
        `when`(repository.getMovies()).thenReturn(result)
        val viewResult = viewModel.getMovies().value?.data
        verify(repository).getMovies()
        assertNotNull(viewResult)
        if (viewResult != null) {
            assertEquals(20, viewResult.size)
        }

        viewModel.getMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(movieLists)
    }

    @Test
    fun getFavouriteMovies() {
        val movieLists: List<Movie> = Gson().fromJson(JsonFilesInKt.discoverMovie, MovieDiscoverContainer::class.java).result
        val result = MutableLiveData<List<Movie>>()
        result.value = movieLists
        `when`(repository.getFavouriteMovies()).thenReturn(result)
        val viewResult = viewModel.getFavouriteMovies().value
        verify(repository).getFavouriteMovies()
        assertNotNull(viewResult)
        if (viewResult != null) {
            assertEquals(20, viewResult.size)
        }

        viewModel.getFavouriteMovies().observeForever(favMovieObserver)
        verify(favMovieObserver).onChanged(movieLists)
    }

    @Test
    fun getShows() {
        val showLists: Resource<List<TVShow>> = Resource.success(Gson().fromJson(JsonFilesInKt.discoverShow, TVDiscoverContainer::class.java).result)
        val result = MutableLiveData<Resource<List<TVShow>>>()
        result.value = showLists
        `when`(repository.getShows()).thenReturn(result)
        val viewResult = viewModel.getShows().value?.data
        verify(repository).getShows()
        assertNotNull(viewResult)
        if (viewResult != null) {
            assertEquals(20, viewResult.size)
        }

        viewModel.getShows().observeForever(televisionObserver)
        verify(televisionObserver).onChanged(showLists)
    }

    @Test
    fun getFavouriteShows() {
        val showLists: List<TVShow> = Gson().fromJson(JsonFilesInKt.discoverShow, TVDiscoverContainer::class.java).result
        val result = MutableLiveData<List<TVShow>>()
        result.value = showLists
        `when`(repository.getFavouriteShows()).thenReturn(result)
        val viewResult = viewModel.getFavouriteShows().value
        verify(repository).getFavouriteShows()
        assertNotNull(viewResult)
        if (viewResult != null) {
            assertEquals(20, viewResult.size)
        }

        viewModel.getFavouriteShows().observeForever(favTvObserver)
        verify(favTvObserver).onChanged(showLists)
    }
}