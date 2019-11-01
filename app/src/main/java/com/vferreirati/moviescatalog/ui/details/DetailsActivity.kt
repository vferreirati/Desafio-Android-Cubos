package com.vferreirati.moviescatalog.ui.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.vferreirati.moviescatalog.R
import com.vferreirati.moviescatalog.extensions.injector
import com.vferreirati.moviescatalog.extensions.viewModel
import com.vferreirati.moviescatalog.presentation.Movie
import com.vferreirati.moviescatalog.ui.details.adapters.Listener
import com.vferreirati.moviescatalog.ui.movies.adapters.MovieShimmerAdapter
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), Listener {

    private val picasso by lazy { injector.picasso() }
    private val adapter by lazy { injector.recommendationAdapter() }
    private val viewModel by viewModel { injector.detailsViewModel() }
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        movie = intent.getParcelableExtra(MOVIE_EXTRA) ?: throw IllegalStateException("Nenhum filme fornecido")
        initViews()

        viewModel.state.observe(this, Observer { state -> mapStateToUI(state) })
        viewModel.getRelatedMovies(movie.id)
    }

    private fun initViews() {
        setSupportActionBar(appToolbar)
        supportActionBar?.title = movie.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter.setListener(this)

        picasso.load(movie.posterUrl)
            .into(imgMovieBackdrop)
        txtMovieSynopsis.text = if(movie.synopsis.isNotEmpty()) movie.synopsis else resources.getString(R.string.noSynnopsisProvided)

        relatedMoviesList.layoutManager = LinearLayoutManager(this@DetailsActivity, RecyclerView.HORIZONTAL, false)
    }

    private fun mapStateToUI(state: DetailsState) = when(state) {
        is RelatedMoviesLoded -> onMoviesLoded(state.relatedMovies)
        is ErrorLoadingRelatedMovies -> onError(state.errorMessage)
        is LoadingRelatedMovies -> onLoadingRelatedMovies()
        else -> throw IllegalStateException("State $state desconhecido")
    }

    private fun onLoadingRelatedMovies() {
        relatedMoviesList.adapter = MovieShimmerAdapter(R.layout.related_movie_list_shimmer_item, 10)
    }

    private fun onError(errorMessage: String) {
        Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    private fun onMoviesLoded(relatedMovies: List<Movie>) {
        adapter.setRecommedations(relatedMovies)
        relatedMoviesList.adapter = adapter
    }

    override fun onRecommendationSelected(movie: Movie) = startActivity(getIntent(this@DetailsActivity, movie))

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val MOVIE_EXTRA = "com.vferreirati.moviescatalog.ui.details.MOVIE"
        fun getIntent(context: Context, movie: Movie): Intent = Intent(context, DetailsActivity::class.java)
            .apply { putExtra(MOVIE_EXTRA, movie) }
    }
}
