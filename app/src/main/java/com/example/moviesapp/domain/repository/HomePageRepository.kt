package com.example.moviesapp.domain.repository

import android.content.Context
import com.example.moviesapp.domain.response.MovieData
import com.example.moviesapp.domain.response.SearchResponse
import com.example.moviesapp.network.ApiService
import com.example.moviesapp.util.Constants
import retrofit2.Response
import javax.inject.Inject

class HomePageRepository @Inject constructor(private val apiService: ApiService, val appContext : Context) {

    suspend fun searchMovie(query : String) : Response<SearchResponse>{
        return apiService.searchMovie(query, Constants.API_KEY)
    }

}