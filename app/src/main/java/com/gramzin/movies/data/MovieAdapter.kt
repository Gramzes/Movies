package com.gramzin.movies.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gramzin.movies.databinding.MovieItemBinding
import com.gramzin.movies.model.Movie
import java.lang.Integer.min

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movieList = arrayListOf<Movie>()

    class MovieViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) = with(binding){
            filmName.text = movie.name
            filmDesc1.text = "${movie.alternativeName}, ${movie.year}, ${movie.movieLength} мин."
            val shortList = movie.countries.subList(0, min(2, movie.countries.size)).joinToString()
            filmDesc2.text = "$shortList • ${movie.type}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount() = movieList.size

}