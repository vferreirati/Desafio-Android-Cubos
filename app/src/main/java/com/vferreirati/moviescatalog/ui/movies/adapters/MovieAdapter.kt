package com.vferreirati.moviescatalog.ui.movies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vferreirati.moviescatalog.R
import com.vferreirati.moviescatalog.presentation.Movie
import javax.inject.Inject

class MovieAdapter @Inject constructor(
    private val picasso: Picasso
) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private val movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = movies[position]
        holder.txtTitle.text = movie.title
        picasso.load(movie.posterUrl)
            .into(holder.imgPoster)
    }

    fun setMovies(list: List<Movie>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: AppCompatTextView = itemView.findViewById(R.id.txtMovieName)
        val imgPoster: AppCompatImageView = itemView.findViewById(R.id.imgMoviePoster)
    }
}