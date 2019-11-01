package com.vferreirati.moviescatalog.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.vferreirati.moviescatalog.R
import com.vferreirati.moviescatalog.extensions.injector
import com.vferreirati.moviescatalog.extensions.viewModel
import com.vferreirati.moviescatalog.presentation.Movie
import com.vferreirati.moviescatalog.ui.details.DetailsActivity
import com.vferreirati.moviescatalog.ui.movies.adapters.MovieAdapter
import com.vferreirati.moviescatalog.ui.movies.adapters.MovieShimmerAdapter
import kotlinx.android.synthetic.main.activity_search_movies.*

class SearchMoviesActivity : AppCompatActivity(), MovieAdapter.MovieListener {

    private val viewModel by viewModel { injector.searchMoviesViewModel() }
    private val moviesAdapter by lazy { injector.movieAdapter() }
    private val shimmerAdapter = MovieShimmerAdapter(R.layout.movie_list_shimmer_item, 10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movies)

        initViews()

        viewModel.state.observe(this, Observer { state -> mapStateToUI(state) })
    }

    private fun initViews() {
        moviesList.layoutManager = GridLayoutManager(this@SearchMoviesActivity, 2)
        ibUp.setOnClickListener { finish() }
        svMovieSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchMovies(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true
        })
        moviesAdapter.setListener(this)
    }

    private fun mapStateToUI(state: SearchState) = when(state) {
        is SearchingMovies -> onLoadingMovies()
        is MoviesFound -> onMoviesLoaded(state.movies)
        is NoMoviesFound -> onNoMoviesFound()
        is ErrorSearchingMovies -> onErrorSearchingMovies(state.errorMessage)
        else -> throw IllegalStateException("State $state desconhecido")
    }

    private fun onErrorSearchingMovies(errorMessage: String) {
        txtErrorMessage.text = errorMessage
        moviesList.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
    }

    private fun onNoMoviesFound() {
        errorLayout.visibility = View.VISIBLE
        moviesList.visibility = View.GONE
        txtErrorMessage.text = resources.getText(R.string.noMoviesFound)
    }

    private fun onMoviesLoaded(movies: List<Movie>) {
        errorLayout.visibility = View.GONE
        moviesList.visibility = View.VISIBLE

        moviesAdapter.setMovies(movies)
        moviesList.adapter = moviesAdapter
    }

    private fun onLoadingMovies() {
        errorLayout.visibility = View.GONE
        moviesList.visibility = View.VISIBLE
        moviesList.adapter = shimmerAdapter
    }

    override fun onMovieSelected(movie: Movie) = startActivity(DetailsActivity.getIntent(this@SearchMoviesActivity, movie))

    /**
     * Paginação não é suportada na pesquisa
     * */
    override fun onLoadNextPage() { }
}
