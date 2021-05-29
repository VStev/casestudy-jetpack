package com.submission.movieandtvshow.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.submission.movieandtvshow.favourite.databinding.FragmentMovieFavouriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MovieFavouriteFragment : Fragment() {
    private var binding : FragmentMovieFavouriteBinding? = null
    private val viewBind get() = binding as FragmentMovieFavouriteBinding
    private lateinit var recyclerView: RecyclerView
    private val movieViewModel: FavouriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(favouriteModule)
        binding = FragmentMovieFavouriteBinding.inflate(inflater, container, false)
        return viewBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = viewBind.recyclerView
        showLayout()
    }

    private fun showLayout() {
        val dataAdapter = MovieAdapter()
        movieViewModel.getFavouriteMovies().observe(viewLifecycleOwner, { Movie ->
            if (Movie.isNotEmpty()){
                dataAdapter.setData(Movie)
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