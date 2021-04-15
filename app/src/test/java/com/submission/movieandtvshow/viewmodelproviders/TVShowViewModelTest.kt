package com.submission.movieandtvshow.viewmodelproviders

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TVShowViewModelTest {
    private lateinit var viewModel: TVShowViewModel

    @Before
    fun setUp() {
        viewModel = TVShowViewModel()
    }

    @Test
    fun getShows() {
        val shows = viewModel.getShows()
        assertNotNull(shows)
        assertEquals(10, shows.size)
    }
}