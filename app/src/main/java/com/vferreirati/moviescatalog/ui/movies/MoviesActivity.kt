package com.vferreirati.moviescatalog.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.vferreirati.moviescatalog.R
import com.vferreirati.moviescatalog.ui.movies.adapters.MoviesByGenrePageAdapter
import com.vferreirati.moviescatalog.ui.search.SearchMoviesActivity
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        setToolbar()
        initViews()
    }

    private fun setToolbar() {
        setSupportActionBar(appToolbar)
    }

    private fun initViews() {
        viewPager.adapter = MoviesByGenrePageAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.actionSearchMovies -> {
            startActivity(Intent(this@MoviesActivity, SearchMoviesActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
