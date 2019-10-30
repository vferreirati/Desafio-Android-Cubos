package com.vferreirati.moviescatalog.network.domain

import com.squareup.moshi.Json

data class RecommendationResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val recommendations: List<MovieEntry>
)