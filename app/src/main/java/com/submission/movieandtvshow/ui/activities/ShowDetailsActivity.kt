package com.submission.movieandtvshow.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.submission.movieandtvshow.R
import com.submission.movieandtvshow.databinding.ActivityShowDetailsBinding
import com.submission.movieandtvshow.viewmodelproviders.ShowDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowDetailsActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityShowDetailsBinding
    private val detailViewModel: ShowDetailsViewModel by viewModel()
    private lateinit var showID: String
    private var argument: Int = 0

    companion object {
        const val EXTRA_ID = "id"
        const val EXTRA_ARG = "args"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        argument = intent.getIntExtra(EXTRA_ARG, 1)
        showID = intent.getStringExtra(EXTRA_ID).toString()
        viewBind = ActivityShowDetailsBinding.inflate(layoutInflater)
        detailViewModel.setShowID(showID)
        setContentView(viewBind.root)
        showLayout()
    }

    private fun showLayout() {
        val contentDesc: TextView = findViewById(R.id.description)
        val seasonText: TextView = findViewById(R.id.season_and_episode_count)
        val ongoingText: TextView = findViewById(R.id.director_or_ongoing)
        when (argument) {
            1 -> {
                detailViewModel.getShow().observe(this, { TVShow ->
                    val imageUrl = "https://image.tmdb.org/t/p/w500"
                    val url = imageUrl + TVShow.poster
                    supportActionBar?.title = TVShow.title
                    Glide.with(this)
                        .load(url)
                        .into(viewBind.posterImage)
                    viewBind.titleText.text = TVShow.title
                    viewBind.releaseYear.text = TVShow.releaseYear
                    val ongoing =
                        if (TVShow.ongoing == true) getString(R.string.ongoing_text) else getString(
                            R.string.season_completed_text
                        )
                    viewBind.directorOrOngoing.text = ongoing
                    val season =
                        if (TVShow.seasons as Int > 1) "${TVShow.seasons} Seasons" else "${TVShow.seasons} Season"
                    val episodes =
                        if (TVShow.episodes as Int > 1) "${TVShow.episodes} Episodes" else "${TVShow.episodes} Episode"
                    val displayText = "$season / $episodes"
                    seasonText.text = displayText
                    contentDesc.text = TVShow.details
                })
            }
            2 -> {
                detailViewModel.getMovie().observe(this, { Movie ->
                    val imageUrl = "https://image.tmdb.org/t/p/w500"
                    val url = imageUrl + Movie.poster
                    supportActionBar?.title = Movie.title
                    seasonText.visibility = View.GONE
                    ongoingText.visibility = View.GONE
                    Glide.with(this)
                        .load(url)
                        .into(viewBind.posterImage)
                    viewBind.titleText.text = Movie.title
                    viewBind.releaseYear.text = Movie.releaseYear
                    contentDesc.text = Movie.details
                })
            }
        }
    }
}