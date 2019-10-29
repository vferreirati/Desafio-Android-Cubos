package com.vferreirati.moviescatalog.di.modules

import com.vferreirati.moviescatalog.di.scopes.ApplicationScope
import com.vferreirati.moviescatalog.network.services.MoviesService
import com.vferreirati.moviescatalog.utils.API_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(includes = [NetworkModule::class])
object MoviesDatabaseModule {

    @Provides
    @ApplicationScope
    fun retrofit(
        moshiFactory: MoshiConverterFactory,
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .client(client)
        .addConverterFactory(moshiFactory)
        .build()

    @Provides
    fun moviesService(retrofit: Retrofit): MoviesService = retrofit.create(MoviesService::class.java)
}