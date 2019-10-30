package com.vferreirati.moviescatalog.repository

import com.vferreirati.moviescatalog.enums.MovieGenres
import com.vferreirati.moviescatalog.network.services.MoviesService
import com.vferreirati.moviescatalog.presentation.Movie
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesService: MoviesService
) {
    suspend fun queryMovies(page: Int, genre: MovieGenres): List<Movie> {
        val response = moviesService.queryMovies(page = page, genre = genre.apiCode)

        return response.entries.map { m ->
            Movie(
                id = m.entryId,
                posterUrl = m.posterUrl,
                releaseDate = m.releaseDateString,
                synopsis = m.synopsis,
                title = m.title,
                voteAverage = m.voteAverage
            )
        }.toList()
    }

    suspend fun getMoviesRecommendations(movieId: Int): List<Movie> {
        val response = moviesService.getMoviesRecommendation(movieId)

        return response.recommendations.map { m ->
            Movie(
                id = m.entryId,
                posterUrl = m.posterUrl,
                releaseDate = m.releaseDateString,
                synopsis = m.synopsis,
                title = m.title,
                voteAverage = m.voteAverage
            )
        }.toList()
    }
}