package com.example.moviesapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.RowMovieCardBinding
import com.example.moviesapp.domain.response.MovieData

class MovieRowAdapter(private val movies : List<MovieData>) : RecyclerView.Adapter<MovieRowAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val rowBinding : RowMovieCardBinding) : RecyclerView.ViewHolder(rowBinding.root){
        fun bind(movie : MovieData){
            rowBinding.titleText.text = movie.original_title
            rowBinding.subtitleText.text = movie.title
            // todo set image
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val rowBinding = RowMovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(rowBinding)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }
}