package com.example.moviesapp.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openHomepage()
    }

    private fun openHomepage() {
        val homePageFragment = HomePageFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, homePageFragment).commit()
    }
}