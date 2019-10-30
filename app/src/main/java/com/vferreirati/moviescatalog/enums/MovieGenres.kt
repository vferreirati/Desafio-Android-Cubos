package com.vferreirati.moviescatalog.enums

enum class MovieGenres {
    ACTION,
    DRAMA,
    FANTASY,
    FICTION;

    val apiCode get() = when(this) {
        ACTION -> "28"
        DRAMA -> "18"
        FANTASY -> "14"
        FICTION -> "878"
    }
}