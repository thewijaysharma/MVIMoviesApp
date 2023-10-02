package com.example.moviesapp.view.viewstate

import com.example.moviesapp.domain.response.MovieData

sealed class HomeViewState {
    object Idle : HomeViewState()
    class Loading(val message : String) : HomeViewState()
    class Error(val errorMessage : String) : HomeViewState()
    class Success(val movies : List<MovieData>) : HomeViewState()
}