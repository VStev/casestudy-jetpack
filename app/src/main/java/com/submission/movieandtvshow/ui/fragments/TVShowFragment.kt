package com.submission.movieandtvshow.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.submission.movieandtvshow.R
import com.submission.movieandtvshow.databinding.FragmentTVShowBinding
import com.submission.movieandtvshow.ui.activities.HomeActivity
import com.submission.movieandtvshow.viewmodelproviders.TVShowViewModel

class TVShowFragment : Fragment() {
    private var binding : FragmentTVShowBinding? = null
    private val viewBind get() = binding as FragmentTVShowBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel : TVShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTVShowBinding.inflate(inflater, container, false)
        return viewBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TVShowViewModel::class.java]
        showLayout()
    }

    private fun showLayout() {
        val shows = viewModel.getShows()
        val dataAdapter = TVAdapter()
        dataAdapter.setData(shows)
        with(recyclerView){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = dataAdapter
        }
    }

}