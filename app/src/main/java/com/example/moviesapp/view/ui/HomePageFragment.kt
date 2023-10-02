package com.example.moviesapp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.moviesapp.databinding.FragmentHomepageBinding
import com.example.moviesapp.domain.response.MovieData
import com.example.moviesapp.view.adapter.MovieRowAdapter
import com.example.moviesapp.view.intent.HomeIntent
import com.example.moviesapp.view.viewstate.HomeViewState
import com.example.moviesapp.viewmodel.HomepageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private val viewModel by viewModels<HomepageViewModel>()
    private lateinit var moviesAdapter : MovieRowAdapter

    private lateinit var binding: FragmentHomepageBinding

    companion object{
        fun newInstance() : HomePageFragment {
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
    private fun fetchAllMovies(){
        lifecycleScope.launch {
            viewModel.homeIntent.send(HomeIntent.LoadAllMovies)
        }
    }

    private fun initViews(){
        moviesAdapter = MovieRowAdapter(ArrayList())
        binding.recyclerView.adapter = moviesAdapter
    }

    private fun observeViewModels(){
        lifecycleScope.launch {
            viewModel.viewState.collect{
                when (it){
                    is HomeViewState.Loading -> showProgress(it.message)
                    is HomeViewState.Success -> loadMovies(it.movies)
                    is HomeViewState.Error -> showSnackbar(it.errorMessage)
                    is HomeViewState.Idle -> {}
                }
            }
        }
    }

    private fun showProgress(progressMessage : String){

    }

    private fun showSnackbar(errorMessage: String) {

    }

    private fun loadMovies(movies : List<MovieData>){

    }

}