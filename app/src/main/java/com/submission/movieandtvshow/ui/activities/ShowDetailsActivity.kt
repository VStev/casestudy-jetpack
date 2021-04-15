package com.submission.movieandtvshow.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.submission.movieandtvshow.R
import com.submission.movieandtvshow.databinding.ActivityShowDetailsBinding
import com.submission.movieandtvshow.viewmodelproviders.ShowDetailsViewModel

class ShowDetailsActivity : AppCompatActivity() {

    private lateinit var viewBind : ActivityShowDetailsBinding
    private lateinit var viewModel : ShowDetailsViewModel
    private lateinit var showID : String
    private var argument : Int = 0

    companion object {
        const val EXTRA_ID = "id"
        const val EXTRA_ARG = "args"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        argument = intent.getIntExtra(EXTRA_ARG, 1)
        showID = intent.getStringExtra(EXTRA_ID).toString()
        viewBind = ActivityShowDetailsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ShowDetailsViewModel::class.java]
        viewModel.setShowID(showID)
        setContentView(viewBind.root)
        showLayout()
    }

    private fun showLayout() {
        val contentDesc : TextView = findViewById(R.id.description)
        val seasonText : TextView = findViewById(R.id.season_and_episode_count)
        when (argument){
            1 -> {
                val details = viewModel.getShow()
                if (details != null) {
                    supportActionBar?.title = details.title
                    Glide.with(this)
                        .load(details.poster)
                        .into(viewBind.posterImage)
                    viewBind.titleText.text = details.title
                    viewBind.releaseYear.text = details.releaseYear
                    val ongoing = if (details.ongoing) getString(R.string.ongoing_text) else getString(
                                            R.string.season_completed_text)
                    viewBind.directorOrOngoing.text = ongoing
                    val season =
                        if (details.seasons > 1) "${details.seasons} Seasons" else "${details.seasons} Season"
                    val episodes =
                        if (details.episodes > 1) "${details.episodes} Episodes" else "${details.episodes} Episode"
                    val displayText = "$season / $episodes"
                    seasonText.text = displayText
                    contentDesc.text = details.details
                } else {
                    supportActionBar?.title =  getString(R.string.not_found_header)
                    seasonText.visibility = View.GONE
                    contentDesc.visibility = View.GONE
                    viewBind.dataFound.visibility = View.GONE
                    viewBind.notFound.visibility = View.VISIBLE
                }
            }
            2 -> {
                val details = viewModel.getMovie()
                if (details != null){
                    supportActionBar?.title = details.title
                    seasonText.visibility = View.GONE
                    Glide.with(this)
                        .load(details.poster)
                        .into(viewBind.posterImage)
                    viewBind.titleText.text = details.title
                    viewBind.releaseYear.text = details.releaseYear
                    viewBind.directorOrOngoing.text = details.director
                    contentDesc.text = details.details
                }else{
                    supportActionBar?.title = getString(R.string.not_found_header)
                    seasonText.visibility = View.GONE
                    contentDesc.visibility = View.GONE
                    viewBind.dataFound.visibility = View.GONE
                    viewBind.notFound.visibility = View.VISIBLE
                }
            }
        }
    }
}