package com.submission.movieandtvshow.viewmodelproviders

import androidx.lifecycle.ViewModel
import com.submission.movieandtvshow.dataobjects.TVShow
import com.submission.movieandtvshow.dummydatas.Dummy

class TVShowViewModel: ViewModel() {
    private val showList = arrayListOf<TVShow>()

    fun getShows(): ArrayList<TVShow> {
        showList.addAll(Dummy.generateShows())
        return showList
    }
}