package com.example.moviesapp.di

import android.content.Context
import com.example.moviesapp.domain.repository.HomePageRepository
import com.example.moviesapp.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeModule {

    @Provides
    @ActivityRetainedScoped
    fun providesHomePageRepository(apiService: ApiService, @ApplicationContext appContext : Context): HomePageRepository{
        return HomePageRepository(apiService, appContext)
    }

}