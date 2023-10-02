package com.example.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.repository.HomePageRepository
import com.example.moviesapp.domain.response.MovieData
import com.example.moviesapp.domain.response.SearchResponse
import com.example.moviesapp.view.intent.HomeIntent
import com.example.moviesapp.view.viewstate.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(private val repository: HomePageRepository) : ViewModel(){

    val movies : LiveData<SearchResponse> = MutableLiveData()
    private val viewModelJob = SupervisorJob()

    private val homeIntent : Channel<HomeIntent> = Channel(Channel.UNLIMITED)
    private val _viewState = MutableStateFlow<HomeViewState>(HomeViewState.Idle)

    val viewState : StateFlow<HomeViewState> = _viewState

    fun searchMovie(query: String) : LiveData<List<MovieData>>{

        viewModelScope.run {
            repository.searchMovie(query)
        }
    }


    fun handleIntent(){
        viewModelScope.launch {
            homeIntent.consumeAsFlow().collect{
                when(it){
                    is HomeIntent.LoadAllMovies -> repository.searchMovie("")
                    is HomeIntent.SearchMovie -> repository.searchMovie(it.query)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}