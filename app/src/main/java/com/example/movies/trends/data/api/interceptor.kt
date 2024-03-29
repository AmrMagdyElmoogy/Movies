package com.example.movies.trends.data.api

import okhttp3.Interceptor
import okhttp3.Response

object MovieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest =
            request.newBuilder()
                .addHeader(
                    "accept",
                    "application/json",
                )
                .build()

        return chain.proceed(newRequest)
    }
}
