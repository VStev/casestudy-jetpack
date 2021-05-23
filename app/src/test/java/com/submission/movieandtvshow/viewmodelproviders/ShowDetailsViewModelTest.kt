package com.submission.movieandtvshow.viewmodelproviders

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.MovieEntity
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.TVShowEntity
import com.submission.movieandtvshow.utilities.JsonFilesInKt
import com.submission.movieandtvshow.vo.Resource
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
    private lateinit var repository: com.submission.movieandtvshow.core.dataobjects.repository.RemoteRepository

    @Mock
    private lateinit var observerTv: Observer<Resource<TVShowEntity>>

    @Mock
    private lateinit var observerMovie: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = ShowDetailsViewModel(repository)
    }

    @Test
    fun setFav(){
        viewModel.setShowID(movieID)
        viewModel.setFav(2, true)
        verify(repository).setFavouriteMovie(movieID, true)
        viewModel.setShowID(showID)
        viewModel.setFav(1, true)
        verify(repository).setFavouriteShow(showID, true)
    }

    @Test
    fun getMovie() {
        viewModel.setShowID(movieID)
        val moviePreset: Resource<MovieEntity> = Resource.success(Gson().fromJson(JsonFilesInKt.moviePreset, MovieEntity::class.java))
        val result = MutableLiveData<Resource<MovieEntity>>()
        result.value = moviePreset
        `when`(repository.getMovieDetail(movieID)).thenReturn(result)
        val viewResult = viewModel.getMovie().value?.data
        verify(repository).getMovieDetail(movieID)
        assertNotNull(viewResult)
        assertEquals(movieID, viewResult?.movieID)

        viewModel.getMovie().observeForever(observerMovie)
        verify(observerMovie).onChanged(moviePreset)
    }

    @Test
    fun getShow() {
        viewModel.setShowID(showID)
        val showPreset: Resource<TVShowEntity> = Resource.success(Gson().fromJson(JsonFilesInKt.showPreset, TVShowEntity::class.java))
        val result = MutableLiveData<Resource<TVShowEntity>>()
        result.value = showPreset
        `when`(repository.getShowDetails(showID)).thenReturn(result)
        val viewResult = viewModel.getShow().value?.data
        verify(repository).getShowDetails(showID)
        assertNotNull(viewResult)
        assertEquals(showID, viewResult?.showID)

        viewModel.getShow().observeForever(observerTv)
        verify(observerTv).onChanged(showPreset)
    }
}