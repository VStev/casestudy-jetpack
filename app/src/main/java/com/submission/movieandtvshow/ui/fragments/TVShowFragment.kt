package com.submission.movieandtvshow.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.submission.movieandtvshow.R
import com.submission.movieandtvshow.core.vo.Status
import com.submission.movieandtvshow.databinding.FragmentTVShowBinding
import com.submission.movieandtvshow.viewmodelproviders.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVShowFragment : Fragment() {
    private var binding : FragmentTVShowBinding? = null
    private val viewBind get() = binding as FragmentTVShowBinding
    private lateinit var recyclerView: RecyclerView
    private val showViewModel : MainViewModel by viewModel()

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
        showLayout()
    }

    private fun showLayout() {
        val dataAdapter = TVAdapter()
        showViewModel.getShows().observe(viewLifecycleOwner, { TVShow ->
            if (TVShow != null){
                when(TVShow.status){
                    Status.LOADING -> viewBind.loadingbar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        viewBind.loadingbar.visibility = View.GONE
                        TVShow.data?.let { dataAdapter.setData(it) }
                        dataAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        viewBind.loadingbar.visibility = View.GONE
                        viewBind.recyclerView.visibility = View.GONE
                        viewBind.notFound.visibility = View.VISIBLE
                    }
                }
                with(recyclerView){
                    layoutManager = LinearLayoutManager(context)
                    adapter = dataAdapter
                }
            }
        })
    }

}