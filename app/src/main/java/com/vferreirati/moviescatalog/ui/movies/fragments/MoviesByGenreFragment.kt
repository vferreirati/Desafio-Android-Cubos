package com.vferreirati.moviescatalog.ui.movies.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vferreirati.moviescatalog.R
import com.vferreirati.moviescatalog.enums.MovieGenres
import com.vferreirati.moviescatalog.extensions.injector
import com.vferreirati.moviescatalog.extensions.viewModel

class MoviesByGenreFragment : Fragment() {

    private val viewModel by viewModel { activity!!.injector.moviesViewModel() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_by_genre, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.e("Victor", viewModel.toString())
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