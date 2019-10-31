package com.vferreirati.moviescatalog.ui.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vferreirati.moviescatalog.R
import com.vferreirati.moviescatalog.extensions.injector
import com.vferreirati.moviescatalog.extensions.viewModel
import com.vferreirati.moviescatalog.presentation.Movie
import com.vferreirati.moviescatalog.ui.movies.adapters.MovieShimmerAdapter
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private val picasso by lazy { injector.picasso() }
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        movie = intent.getParcelableExtra(MOVIE_EXTRA) ?: throw IllegalStateException("Nenhum filme fornecido")
        initViews()
    }

    private fun initViews() {
        setSupportActionBar(appToolbar)
        supportActionBar?.title = movie.title

        picasso.load(movie.posterUrl)
            .into(imgMovieBackdrop)
        txtMovieSynopsis.text = movie.synopsis

        relatedMoviesList.layoutManager = LinearLayoutManager(this@DetailsActivity, RecyclerView.HORIZONTAL, false)
        relatedMoviesList.adapter = MovieShimmerAdapter(R.layout.related_movie_list_shimmer_item, 10)
    }

    companion object {
        private const val MOVIE_EXTRA = "com.vferreirati.moviescatalog.ui.details.MOVIE"
        fun getIntent(context: Context, movie: Movie): Intent = Intent(context, DetailsActivity::class.java)
            .apply { putExtra(MOVIE_EXTRA, movie) }
    }
}
