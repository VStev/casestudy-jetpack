package com.submission.movieandtvshow.viewmodelproviders

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dataobjects.TVShow
import com.submission.movieandtvshow.dataobjects.repository.RemoteRepository
import com.submission.movieandtvshow.utilities.JsonFilesInKt
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner.Silent::class)
class ShowDetailsViewModelTest {
    private lateinit var viewModel: ShowDetailsViewModel
    private val showID : String = "78204"
    private val movieID : String = "458576"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: RemoteRepository

    @Mock
    private lateinit var observerTv: Observer<TVShow>

    @Mock
    private lateinit var observerMovie: Observer<Movie>

    @Before
    fun setUp() {
        viewModel = ShowDetailsViewModel(repository)
    }

    @Test
    fun getMovie() {
        viewModel.setShowID(movieID)
        val moviePreset: Movie = Gson().fromJson(JsonFilesInKt.moviePreset, Movie::class.java)
        val result = MutableLiveData<Movie>()
        result.value = moviePreset
        `when`(repository.getMovieDetail(movieID)).thenReturn(result)
        val viewResult = viewModel.getMovie().value
        verify(repository).getMovieDetail(movieID)
        assertNotNull(viewResult)
        if (viewResult != null) {
            assertEquals(movieID, viewResult.movieID)
        }

        viewModel.getMovie().observeForever(observerMovie)
        verify(observerMovie).onChanged(moviePreset)
    }

    @Test
    fun getShow() {
        viewModel.setShowID(showID)
        val showPreset: TVShow = Gson().fromJson(JsonFilesInKt.showPreset, TVShow::class.java)
        val result = MutableLiveData<TVShow>()
        result.value = showPreset
        `when`(repository.getShowDetails(showID)).thenReturn(result)
        val viewResult = viewModel.getShow().value
        verify(repository).getShowDetails(showID)
        assertNotNull(viewResult)
        if (viewResult != null) {
            assertEquals(showID, viewResult.showID)
        }

        viewModel.getShow().observeForever(observerTv)
        verify(observerTv).onChanged(showPreset)
    }
}