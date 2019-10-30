package com.vferreirati.moviescatalog.ui.movies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vferreirati.moviescatalog.R

class MovieShimmerAdapter : RecyclerView.Adapter<MovieShimmerAdapter.ShimmerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_shimmer_item, parent, false)
        return ShimmerHolder(view)
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: ShimmerHolder, position: Int) {
    }

    inner class ShimmerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}