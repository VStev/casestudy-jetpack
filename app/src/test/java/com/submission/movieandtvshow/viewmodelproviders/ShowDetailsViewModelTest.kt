package com.submission.movieandtvshow.viewmodelproviders

import com.submission.movieandtvshow.dummydatas.Dummy
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ShowDetailsViewModelTest {
    private lateinit var viewModel: ShowDetailsViewModel
    private val dummyShow = Dummy.generateShows()[7]
    private val dummyMovie = Dummy.generateMovies()[4]
    private lateinit var showID : String
    private lateinit var movieID : String

    @Before
    fun setUp() {
        viewModel = ShowDetailsViewModel()
        showID = dummyShow.showID
        movieID = dummyMovie.movieID
    }

    @Test
    fun getMovie() {
        viewModel.setShowID(movieID)
        val test1 = viewModel.getMovie()
        assertNotNull(test1)
        assertEquals(movieID, test1.movieID)
        assertEquals(dummyMovie.title, test1.title)
        assertEquals(dummyMovie.releaseYear, test1.releaseYear)
        assertEquals(dummyMovie.director, test1.director)
        assertEquals(dummyMovie.details, test1.details)
        assertEquals(dummyMovie.poster, test1.poster)
    }

    @Test
    fun getShow() {
        viewModel.setShowID(showID)
        val test1 = viewModel.getShow()
        assertNotNull(test1)
        assertEquals(showID, test1.showID)
        assertEquals(dummyShow.title, test1.title)
        assertEquals(dummyShow.releaseYear, test1.releaseYear)
        assertEquals(dummyShow.details, test1.details)
        assertEquals(dummyShow.ongoing, test1.ongoing)
        assertEquals(dummyShow.episodes, test1.episodes)
        assertEquals(dummyShow.seasons, test1.seasons)
        assertEquals(dummyShow.poster, test1.poster)
    }
}