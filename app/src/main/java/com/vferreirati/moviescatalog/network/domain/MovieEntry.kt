package com.vferreirati.moviescatalog.network.domain

import com.squareup.moshi.Json

data class MovieEntry(
    @Json(name = "id") val entryId: Int,
    @Json(name = "original_title") val title: String,
    @Json(name = "poster_path") val posterUrl: String?,
    @Json(name = "overview") val synopsis: String
)