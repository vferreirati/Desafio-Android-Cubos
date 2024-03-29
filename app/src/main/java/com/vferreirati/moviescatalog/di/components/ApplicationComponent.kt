package com.vferreirati.moviescatalog.di.components

import android.content.Context
import com.squareup.picasso.Picasso
import com.vferreirati.moviescatalog.di.modules.ImageModule
import com.vferreirati.moviescatalog.di.modules.MoviesDatabaseModule
import com.vferreirati.moviescatalog.di.scopes.ApplicationScope
import com.vferreirati.moviescatalog.ui.details.DetailsViewModel
import com.vferreirati.moviescatalog.ui.details.adapters.RecommendationAdapter
import com.vferreirati.moviescatalog.ui.movies.MoviesViewModel
import com.vferreirati.moviescatalog.ui.movies.adapters.MovieAdapter
import com.vferreirati.moviescatalog.ui.search.SearchMoviesViewModel
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [MoviesDatabaseModule::class, ImageModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun build(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun moviesViewModel(): MoviesViewModel
    fun detailsViewModel(): DetailsViewModel
    fun searchMoviesViewModel(): SearchMoviesViewModel
    fun movieAdapter(): MovieAdapter
    fun recommendationAdapter(): RecommendationAdapter
    fun picasso(): Picasso
}