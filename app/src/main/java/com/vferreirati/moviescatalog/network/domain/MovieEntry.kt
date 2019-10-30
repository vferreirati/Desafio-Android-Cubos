package com.vferreirati.moviescatalog.network.domain

import com.squareup.moshi.Json

data class MovieEntry(
    @Json(name = "id") val entryId: Int,
    @Json(name = "original_title") val title: String,
    @Json(name = "poster_path") val posterUrl: String,
    @Json(name = "overview") val synopsis: String,
    @Json(name = "release_date") val releaseDateString: String,
    @Json(name = "vote_average") val voteAverage: Double
)