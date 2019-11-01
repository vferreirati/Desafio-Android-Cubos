package com.vferreirati.moviescatalog.ui.movies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vferreirati.moviescatalog.R
import com.vferreirati.moviescatalog.presentation.Movie
import javax.inject.Inject

class MovieAdapter @Inject constructor(
    private val picasso: Picasso
) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private val movies = mutableListOf<Movie>()
    private lateinit var listener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = movies[position]

        holder.txtTitle.text = movie.title
        if(movie.posterUrl != null)
            picasso.load(movie.posterUrl)
                .into(holder.imgPoster)
        else
            holder.imgPoster.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_movie_poster_placeholder))
        holder.itemView.setOnClickListener { listener.onMovieSelected(movie) }

        val currentSize = movies.size
        if(currentSize - position <= 6)
            listener.onLoadNextPage()
    }

    fun setMovies(list: List<Movie>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    fun setListener(listener: MovieListener) {
        this.listener = listener
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: AppCompatTextView = itemView.findViewById(R.id.txtMovieName)
        val imgPoster: AppCompatImageView = itemView.findViewById(R.id.imgMoviePoster)
    }

    interface MovieListener {
        fun onMovieSelected(movie: Movie)
        fun onLoadNextPage()
    }
}

