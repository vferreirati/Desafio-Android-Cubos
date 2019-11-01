package com.vferreirati.moviescatalog.repository

import com.vferreirati.moviescatalog.enums.MovieGenres
import com.vferreirati.moviescatalog.network.domain.MovieEntry
import com.vferreirati.moviescatalog.network.services.MoviesService
import com.vferreirati.moviescatalog.presentation.Movie
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesService: MoviesService
) {
    suspend fun queryMovies(page: Int, genre: MovieGenres): List<Movie> {
        val response = moviesService.queryMovies(page = page, genre = genre.apiCode)

        return response.entries.map { m -> mapDomainToPresentation(m) }.toList()
    }

    suspend fun getMoviesRecommendations(movieId: Int): List<Movie> {
        val response = moviesService.getMoviesRecommendation(movieId)
        return response.recommendations.map { m -> mapDomainToPresentation(m) }.toList()
    }

    suspend fun searchMovies(query: String): List<Movie> {
        val response = moviesService.searchMovies(query)
        return response.entries.map { m -> mapDomainToPresentation(m) }.toList()
    }

    private fun mapDomainToPresentation(movieEntry: MovieEntry) : Movie = Movie(
        id = movieEntry.entryId,
        posterUrl = "https://image.tmdb.org/t/p/w500${movieEntry.posterUrl}",
        releaseDate = movieEntry.releaseDateString,
        synopsis = movieEntry.synopsis,
        title = movieEntry.title,
        voteAverage = movieEntry.voteAverage
    )
}