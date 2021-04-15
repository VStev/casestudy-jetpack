package com.submission.movieandtvshow.viewmodelproviders

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

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
        assertEquals(10, shows.size)
    }
}