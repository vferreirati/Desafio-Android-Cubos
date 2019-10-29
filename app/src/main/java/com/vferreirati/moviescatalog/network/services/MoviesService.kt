package com.vferreirati.moviescatalog.network.services

import com.vferreirati.moviescatalog.network.domain.QueryResponse
import com.vferreirati.moviescatalog.network.domain.RecommendationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    /**
     * Obtém uma lista de filmes ordenada de acordo com o parâmetro de ordenação e do
     * gênero fornecido
     * */
    @GET("Discover/Movie")
    suspend fun queryMovies(
        @Query("language") language: String = "pt-BR",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("with_genres") genre: String
    ): QueryResponse


    /**
     * Obtém uma lista de filmes recomendados a partir do filme informado
     * */
    @GET("Movie/{movieId}/recommendations")
    suspend fun getMoviesRecommendation(
        @Path("movieId") movieId: Int,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "pt-BR"
    ): RecommendationResponse
}