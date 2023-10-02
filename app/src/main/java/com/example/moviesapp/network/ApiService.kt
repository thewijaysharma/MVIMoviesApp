package com.example.moviesapp.network

import com.example.moviesapp.domain.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") searchQuery : String, @Query("api_key") apiKey : String): SearchResponse

}