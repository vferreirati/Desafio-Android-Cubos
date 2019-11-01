package com.vferreirati.moviescatalog.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.vferreirati.moviescatalog.R
import kotlinx.android.synthetic.main.activity_search_movies.*

class SearchMoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movies)

        initViews()
    }

    private fun initViews() {
        ibUp.setOnClickListener { finish() }
        svMovieSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // TODO: Realizar chamada ao viewmodel com consulta
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true
        })
    }
}
