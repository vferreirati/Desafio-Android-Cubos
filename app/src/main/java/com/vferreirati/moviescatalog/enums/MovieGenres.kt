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

    companion object {
        fun getByApiCode(apiCode: String) = when(apiCode) {
            "28" -> ACTION
            "18" -> DRAMA
            "14" -> FANTASY
            "878" -> FICTION
            else -> throw IllegalArgumentException("Código de API inválido")
        }
    }
}