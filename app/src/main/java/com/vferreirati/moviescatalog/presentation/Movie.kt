package com.vferreirati.moviescatalog.presentation

import java.util.*

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val synopsis: String,
    val releaseDate: String,
    val voteAverage: Double
)