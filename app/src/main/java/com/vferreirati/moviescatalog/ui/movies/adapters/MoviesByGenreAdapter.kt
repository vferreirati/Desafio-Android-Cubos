package com.vferreirati.moviescatalog.ui.movies.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.vferreirati.moviescatalog.enums.MovieGenres
import com.vferreirati.moviescatalog.ui.movies.fragments.MoviesByGenreFragment

class MoviesByGenreAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 4

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> MoviesByGenreFragment.newInstance(MovieGenres.ACTION)
        1 -> MoviesByGenreFragment.newInstance(MovieGenres.DRAMA)
        2 -> MoviesByGenreFragment.newInstance(MovieGenres.FANTASY)
        else -> MoviesByGenreFragment.newInstance(MovieGenres.FICTION)
    }

    override fun getPageTitle(position: Int): CharSequence = when(position) {
        0 -> "Ação"
        1 -> "Drama"
        2 -> "Fantasia"
        else -> "Ficção"
    }
}