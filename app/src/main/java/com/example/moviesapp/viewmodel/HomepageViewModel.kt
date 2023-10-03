package com.example.moviesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.repository.HomePageRepository
import com.example.moviesapp.view.intent.HomeIntent
import com.example.moviesapp.view.viewstate.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(private val repository: HomePageRepository) : ViewModel(){

    private val _viewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading("Loading.. Please wait")) // todo power from strings.xml

    val viewState : StateFlow<HomeViewState> = _viewState
    val homeIntent : Channel<HomeIntent> = Channel(Channel.UNLIMITED)

    init {
        handleIntent()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            homeIntent.consumeAsFlow().collect{
                when(it){
                    is HomeIntent.LoadAllMovies -> searchMovie("")
                    is HomeIntent.SearchMovie -> searchMovie(it.query)
                }
            }
        }
    }

    private fun searchMovie(query: String){
        viewModelScope.launch {
            _viewState.value = HomeViewState.Loading("Loading.. Please wait") // todo power this from strings.xml
            val response = repository.searchMovie(query)
            if(response.isSuccessful && response.body() != null){
                val responseBody = response.body()!!
                if(responseBody.results.isNotEmpty()){
                    _viewState.value = HomeViewState.Success(responseBody.results)
                }else{
                    _viewState.value = HomeViewState.NoResults("No results found") // todo power this from strings.xml
                }
            }else{
                _viewState.value = HomeViewState.Error(response.message())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}