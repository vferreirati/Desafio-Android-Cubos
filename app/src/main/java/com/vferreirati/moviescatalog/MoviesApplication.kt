package com.vferreirati.moviescatalog

import android.app.Application
import com.vferreirati.moviescatalog.di.components.ApplicationComponent
import com.vferreirati.moviescatalog.di.components.DaggerApplicationComponent

class MoviesApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().build(this)
    }
}