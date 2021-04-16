package com.submission.movieandtvshow.viewmodelproviders

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        val shows = viewModel.getMovies()
        assertNotNull(shows)
        assertEquals(11, shows.size)
    }
}