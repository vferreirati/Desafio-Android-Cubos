package com.vferreirati.moviescatalog.ui.details.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vferreirati.moviescatalog.R
import com.vferreirati.moviescatalog.presentation.Movie
import javax.inject.Inject

class RecommendationAdapter @Inject constructor(
    private val picasso: Picasso
) : RecyclerView.Adapter<RecommendationAdapter.RecommendationHolder>() {

    private val recommendations = mutableListOf<Movie>()
    private lateinit var listener: Listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recommendation_list_item, parent, false)
        return RecommendationHolder(view)
    }

    override fun getItemCount(): Int = recommendations.size

    override fun onBindViewHolder(holder: RecommendationHolder, position: Int) {
        val recommendation = recommendations[position]
        picasso.load(recommendation.posterUrl).into(holder.imgMoviePoster)
        holder.itemView.setOnClickListener { listener.onRecommendationSelected(recommendation) }
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun setRecommedations(recommendations: List<Movie>) {
        this.recommendations.addAll(recommendations)
    }

    inner class RecommendationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgMoviePoster: AppCompatImageView = itemView.findViewById(R.id.imgMoviePoster)
    }
}

interface Listener {
    fun onRecommendationSelected(movie: Movie)
}