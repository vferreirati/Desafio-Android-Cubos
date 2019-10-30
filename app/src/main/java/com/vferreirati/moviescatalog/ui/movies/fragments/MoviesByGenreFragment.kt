package com.vferreirati.moviescatalog.ui.movies.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vferreirati.moviescatalog.R
import com.vferreirati.moviescatalog.enums.MovieGenres
import com.vferreirati.moviescatalog.extensions.injector
import com.vferreirati.moviescatalog.extensions.viewModel
import com.vferreirati.moviescatalog.ui.movies.ErrorLoadingMovies
import com.vferreirati.moviescatalog.ui.movies.LoadingMovies
import com.vferreirati.moviescatalog.ui.movies.MoviesListState
import com.vferreirati.moviescatalog.ui.movies.MoviesLoaded
import com.vferreirati.moviescatalog.ui.movies.adapters.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movies_by_genre.*

class MoviesByGenreFragment : Fragment() {

    private val viewModel by viewModel { activity!!.injector.moviesViewModel() }
    private val adapter by lazy { activity!!.injector.movieAdapter() }

    private lateinit var movieGenre: MovieGenres

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.fragment_movies_by_genre, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = arguments ?: throw IllegalArgumentException("O ID do gênero deve ser fornecido")
        val genreCode = arguments.getString(GENRE_ID_KEY, "")
        movieGenre = MovieGenres.getByApiCode(genreCode)

        moviesList.adapter = adapter
        moviesList.layoutManager = GridLayoutManager(context, 2)

        viewModel.state.observe(this, Observer { state -> mapStateToUI(state) })
        viewModel.getMovies(movieGenre)
    }

    private fun mapStateToUI(state: MoviesListState) = when(state) {
        is LoadingMovies -> onLoadingMovies(state)
        is MoviesLoaded -> onMoviesLoaded(state)
        is ErrorLoadingMovies -> onErrorLoadingMovies(state)
        else -> throw IllegalStateException("State $state desconhecido")
    }

    private fun onLoadingMovies(state: LoadingMovies) {

    }

    private fun onMoviesLoaded(state: MoviesLoaded) = adapter.setMovies(state.movies)

    private fun onErrorLoadingMovies(state: ErrorLoadingMovies) {
        if(state.movies.isEmpty()) {
            txtErrorMessage.text = state.errorMessage
            errorLoadingLayout.visibility = View.VISIBLE
            moviesList.visibility = View.VISIBLE
        } else {
            // TODO: Exibir snackbar informando o erro
        }
    }

    companion object {
        private const val GENRE_ID_KEY = "GENRE_ID"
        fun newInstance(genre: MovieGenres): MoviesByGenreFragment {
            val bundle = Bundle()
            bundle.putString(GENRE_ID_KEY, genre.apiCode)

            val fragment = MoviesByGenreFragment()
            fragment.arguments = bundle

            return fragment
        }
    }
}