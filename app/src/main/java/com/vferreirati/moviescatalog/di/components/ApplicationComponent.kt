package com.vferreirati.moviescatalog.di.components

import android.content.Context
import com.vferreirati.moviescatalog.di.modules.ImageModule
import com.vferreirati.moviescatalog.di.modules.MoviesDatabaseModule
import com.vferreirati.moviescatalog.di.scopes.ApplicationScope
import com.vferreirati.moviescatalog.network.services.MoviesService
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [MoviesDatabaseModule::class, ImageModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun build(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun moviesService(): MoviesService
}