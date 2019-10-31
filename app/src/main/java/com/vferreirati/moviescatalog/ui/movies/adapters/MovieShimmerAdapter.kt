package com.vferreirati.moviescatalog.ui.movies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieShimmerAdapter(
    private val layoutId: Int,
    private val itemCount: Int
) : RecyclerView.Adapter<MovieShimmerAdapter.ShimmerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ShimmerHolder(view)
    }

    override fun getItemCount(): Int = itemCount

    override fun onBindViewHolder(holder: ShimmerHolder, position: Int) { }

    inner class ShimmerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}