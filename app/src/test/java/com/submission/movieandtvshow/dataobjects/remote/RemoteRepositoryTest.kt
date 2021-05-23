package com.submission.movieandtvshow.dataobjects.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.MovieEntity
import com.submission.movieandtvshow.core.dataobjects.remote.dataentities.TVShowEntity
import com.submission.movieandtvshow.utilities.AppExecutors
import com.submission.movieandtvshow.utilities.JsonFilesInKt
import com.submission.movieandtvshow.utilities.LiveDataUtility
import com.submission.movieandtvshow.utilities.PagedListUtility
import com.submission.movieandtvshow.vo.Resource
import com.submission.movieandtvshow.webapi.RemoteDataSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner.Silent::class)
class RemoteRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: com.submission.movieandtvshow.core.dataobjects.repository.RemoteRepository

    @Mock
    private lateinit var retrofitCallback: RemoteDataSource

    @Mock
    private lateinit var localDataSource: com.submission.movieandtvshow.core.dataobjects.repository.LocalDataSource

    @Mock
    private lateinit var appExecutors: AppExecutors

    @Before
    fun setUp() {
        repository = com.submission.movieandtvshow.core.dataobjects.repository.RemoteRepository(
            retrofitCallback,
            localDataSource,
            appExecutors
        )
    }

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        val movieLists: List<MovieEntity> = Gson().fromJson(JsonFilesInKt.discoverMovie, com.submission.movieandtvshow.core.dataobjects.remote.dataentities.MovieDiscoverContainer::class.java).result
        val result = Resource.success(PagedListUtility.mockPagedList(Gson().fromJson(JsonFilesInKt.discoverMovie, com.submission.movieandtvshow.core.dataobjects.remote.dataentities.MovieDiscoverContainer::class.java).result))
        `when`(localDataSource.getMovies()).thenReturn(dataSourceFactory)
        repository.getMovies()
        verify(localDataSource).getMovies()
        assertNotNull(result)
        assertEquals(movieLists.size, result.data?.size)
    }

    @Test
    fun getShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        val showLists: List<TVShowEntity> = Gson().fromJson(JsonFilesInKt.discoverShow, com.submission.movieandtvshow.core.dataobjects.remote.dataentities.TVDiscoverContainer::class.java).result
        val result =  Resource.success(PagedListUtility.mockPagedList(Gson().fromJson(JsonFilesInKt.discoverShow, com.submission.movieandtvshow.core.dataobjects.remote.dataentities.TVDiscoverContainer::class.java).result))
        `when`(localDataSource.getShows()).thenReturn(dataSourceFactory)
        repository.getShows()
        verify(localDataSource).getShows()
        assertNotNull(result)
        assertEquals(showLists.size, result.data?.size)
    }

    @Test
    fun getShowDetails() {
        val showPreset: TVShowEntity = Gson().fromJson(JsonFilesInKt.showPreset, TVShowEntity::class.java)
        val result =  MutableLiveData<TVShowEntity>()
        result.value = showPreset
        `when`(localDataSource.getShowDetails(eq("78204"))).thenReturn(result)
        val shows = LiveDataUtility.getValue(repository.getShowDetails(eq("78204")))
        verify(localDataSource).getShowDetails(eq("78204"))
        assertNotNull(shows)
        assertEquals("78204", shows.data?.showID)
    }

    @Test
    fun getMovieDetail() {
        val moviePreset: MovieEntity = Gson().fromJson(JsonFilesInKt.moviePreset, MovieEntity::class.java)
        val result =  MutableLiveData<MovieEntity>()
        result.value = moviePreset
        `when`(localDataSource.getMovieDetails(eq("458576"))).thenReturn(result)
        val shows = LiveDataUtility.getValue(repository.getMovieDetail(eq("458576")))
        verify(localDataSource).getMovieDetails(eq("458576"))
        assertNotNull(shows)
        assertEquals("458576", shows.data?.movieID)
    }

    @Test
    fun setFavouriteMovie() {
        localDataSource.setFavouriteMovie("458576", true)
        verify(localDataSource).setFavouriteMovie("458576", true)
    }

    @Test
    fun setFavouriteShow(){
        localDataSource.setFavouriteShow("78204", true)
        verify(localDataSource).setFavouriteShow("78204", true)
    }
}