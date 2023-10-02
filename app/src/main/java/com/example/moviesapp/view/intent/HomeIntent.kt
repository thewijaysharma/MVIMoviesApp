package com.example.moviesapp.view.intent

sealed class HomeIntent {

    class SearchMovie(val query : String) : HomeIntent()
    object LoadAllMovies : HomeIntent()
}