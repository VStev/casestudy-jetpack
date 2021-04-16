package com.submission.movieandtvshow.ui.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.submission.movieandtvshow.R
import com.submission.movieandtvshow.dummydatas.Dummy
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{
    private val dummyShow = Dummy.generateShows()
    private val dummyMovie = Dummy.generateMovies()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadShows(){
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition< RecyclerView.ViewHolder>(dummyShow.size))
    }

    @Test
    fun loadShowDetail(){
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(6, click()))
        onView(withId(R.id.poster_image)).check(matches(isDisplayed()))
        onView(withId(R.id.title_text)).check(matches(isDisplayed()))
        onView(withId(R.id.title_text)).check(matches(withText(dummyShow[6].title)))
        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.director_or_ongoing)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.not_found)).check(matches(not(isDisplayed())))
        onView(withId(R.id.data_found)).check(matches(isDisplayed()))
        onView(withId(R.id.nested_scroll_view)).perform(swipeUp())
        onView(withId(R.id.season_and_episode_count)).check(matches(isDisplayed()))
    }

    @Test
    fun loadMovies(){
        onView(withId(R.id.nav_movie)).perform(click())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition< RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadMovieDetail(){
        onView(withId(R.id.nav_movie)).perform(click())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(6, click()))
        onView(withId(R.id.poster_image)).check(matches(isDisplayed()))
        onView(withId(R.id.title_text)).check(matches(isDisplayed()))
        onView(withId(R.id.title_text)).check(matches(withText(dummyMovie[6].title)))
        onView(withId(R.id.release_year)).check(matches(isDisplayed()))
        onView(withId(R.id.director_or_ongoing)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.not_found)).check(matches(not(isDisplayed())))
        onView(withId(R.id.data_found)).check(matches(isDisplayed()))
        onView(withId(R.id.nested_scroll_view)).perform(swipeUp())
        onView(withId(R.id.season_and_episode_count)).check(matches(not(isDisplayed())))
    }

    @Test
    fun loadFaultyShowData(){
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        onView(withId(R.id.poster_image)).check(matches(not(isDisplayed())))
        onView(withId(R.id.title_text)).check(matches(not(isDisplayed())))
        onView(withId(R.id.release_year)).check(matches(not(isDisplayed())))
        onView(withId(R.id.director_or_ongoing)).check(matches(not(isDisplayed())))
        onView(withId(R.id.description)).check(matches(not(isDisplayed())))
        onView(withId(R.id.not_found)).check(matches(isDisplayed()))
        onView(withId(R.id.data_found)).check(matches(not(isDisplayed())))
    }

    @Test
    fun loadFaultyMovieData(){
        onView(withId(R.id.nav_movie)).perform(click())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        onView(withId(R.id.poster_image)).check(matches(not(isDisplayed())))
        onView(withId(R.id.title_text)).check(matches(not(isDisplayed())))
        onView(withId(R.id.release_year)).check(matches(not(isDisplayed())))
        onView(withId(R.id.director_or_ongoing)).check(matches(not(isDisplayed())))
        onView(withId(R.id.description)).check(matches(not(isDisplayed())))
        onView(withId(R.id.data_found)).check(matches(not(isDisplayed())))
        onView(withId(R.id.not_found)).check(matches(isDisplayed()))
    }
}