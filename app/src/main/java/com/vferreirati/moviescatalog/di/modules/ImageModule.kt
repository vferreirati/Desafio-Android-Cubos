package com.vferreirati.moviescatalog.di.modules

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
object ImageModule {

    @Provides
    @Singleton
    fun picasso(
        context: Context,
        downloader: OkHttp3Downloader
    ): Picasso = Picasso.Builder(context)
        .downloader(downloader)
        .build()
}