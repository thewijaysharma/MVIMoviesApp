package com.example.moviesapp.view.viewstate

import com.example.moviesapp.domain.response.MovieData

sealed class HomeViewState {
    object Idle : HomeViewState()
    class Loading(message : String) : HomeViewState()
    class Error(errorMessage : String) : HomeViewState()
    class Success(movies : List<MovieData>) : HomeViewState()
}