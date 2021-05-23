package com.submission.movieandtvshow.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.submission.movieandtvshow.favourite.databinding.FragmentShowFavouriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class ShowFavouriteFragment : Fragment() {
    private var binding : FragmentShowFavouriteBinding? = null
    private val viewBind get() = binding as FragmentShowFavouriteBinding
    private lateinit var recyclerView: RecyclerView
    private val movieViewModel: FavouriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(favouriteModule)
        binding = FragmentShowFavouriteBinding.inflate(inflater, container, false)
        return viewBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = viewBind.recyclerView
        showLayout()
    }

    private fun showLayout() {
        val dataAdapter = TVAdapter()
        movieViewModel.getFavouriteShows().observe(viewLifecycleOwner, { TVShow ->
            if (TVShow.isNotEmpty()){
                dataAdapter.setData(TVShow)
            }else{
                viewBind.recyclerView.visibility = View.GONE
                viewBind.notFound.visibility = View.VISIBLE
            }
        })
        with(recyclerView){
            layoutManager = LinearLayoutManager(context)
            adapter = dataAdapter
        }
    }
}