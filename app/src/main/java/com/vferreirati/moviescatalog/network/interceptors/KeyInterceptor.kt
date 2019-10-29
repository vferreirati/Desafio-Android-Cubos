package com.vferreirati.moviescatalog.network.interceptors

import com.vferreirati.moviescatalog.utils.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor responsável por adicionar a chave da aplicação a toda requisição a API
 * */
class KeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalUrl = request.url

        val appendedUrl = originalUrl.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val newRequest = request.newBuilder()
            .url(appendedUrl)
            .build()

        return chain.proceed(newRequest)
    }
}