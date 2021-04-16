package com.submission.movieandtvshow.viewmodelproviders

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

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
        assertEquals(11, shows.size)
    }
}