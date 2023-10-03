package com.example.moviesapp.view.viewstate

import com.example.moviesapp.domain.response.MovieData

sealed class HomeViewState {
    class Loading(val message : String) : HomeViewState()
    class Error(val errorMessage : String) : HomeViewState()
    class Success(val movies : List<MovieData>) : HomeViewState()
    class NoResults(val message: String) : HomeViewState()
}