package com.vferreirati.moviescatalog.network.domain

import com.squareup.moshi.Json

data class QueryResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "results") val entries: List<MovieEntry>
)