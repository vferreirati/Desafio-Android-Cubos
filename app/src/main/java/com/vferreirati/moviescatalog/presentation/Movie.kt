package com.vferreirati.moviescatalog.presentation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val synopsis: String,
    val releaseDate: String,
    val voteAverage: Double
) : Parcelable