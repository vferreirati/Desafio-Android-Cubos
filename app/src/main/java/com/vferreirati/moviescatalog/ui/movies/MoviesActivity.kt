package com.vferreirati.moviescatalog.ui.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vferreirati.moviescatalog.R
import com.vferreirati.moviescatalog.ui.movies.adapters.MoviesByGenreAdapter
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
        viewPager.adapter = MoviesByGenreAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}
