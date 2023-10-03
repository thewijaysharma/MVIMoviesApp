package com.example.moviesapp.view.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentHomepageBinding
import com.example.moviesapp.domain.response.MovieData
import com.example.moviesapp.view.adapter.MovieRowAdapter
import com.example.moviesapp.view.intent.HomeIntent
import com.example.moviesapp.view.viewstate.HomeViewState
import com.example.moviesapp.viewmodel.HomepageViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private val viewModel by viewModels<HomepageViewModel>()
    private lateinit var moviesAdapter: MovieRowAdapter

    private lateinit var binding: FragmentHomepageBinding
    private val moviesList : ArrayList<MovieData> = ArrayList()

    companion object {
        fun newInstance(): HomePageFragment {
            return HomePageFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomepageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        observeViewModels()
        fetchAllMovies()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun fetchAllMovies() {
        lifecycleScope.launch {
            viewModel.homeIntent.send(HomeIntent.LoadAllMovies)
        }
    }

    private fun initViews() {
        moviesAdapter = MovieRowAdapter(moviesList)
        binding.recyclerView.adapter = moviesAdapter
    }

    private fun observeViewModels() {
        lifecycleScope.launch { // We need to use a coroutine scope to collect a flow
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) { // this will be called when the fragment view comes to the started state.
                viewModel.viewState.collect {
                    when (it) {
                        is HomeViewState.Loading -> showProgress(it.message)
                        is HomeViewState.Success -> loadMovies(it.movies)
                        is HomeViewState.Error -> showSnackbar(it.errorMessage)
                        is HomeViewState.NoResults -> showNoResults(it.message)
                    }
                }
            }
        }
    }

    private fun showProgress(progressMessage: String) {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    private fun showSnackbar(errorMessage: String) {
        Snackbar.make(requireActivity().findViewById(R.id.rootView), errorMessage, Snackbar.LENGTH_SHORT).show() // todo add a retry functionality
    }

    private fun showNoResults(noResultsMessage : String){
        binding.apply {
            noResultsText.visibility = View.VISIBLE
            noResultsText.text = noResultsMessage
            recyclerView.visibility = View.GONE
            progressBar.visibility = View.GONE
        }
    }

    private fun loadMovies(movies: List<MovieData>) {
        binding.apply {
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            moviesList.clear()
            moviesList.addAll(movies)
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

}