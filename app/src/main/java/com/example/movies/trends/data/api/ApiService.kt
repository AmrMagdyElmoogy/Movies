package com.example.movies.trends.data.api

import com.example.movies.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val client = OkHttpClient.Builder().addInterceptor(MovieInterceptor).build()
val retrofit by lazy {
    Retrofit.Builder().baseUrl(
        BASE_URL,
    ).addConverterFactory(MoshiConverterFactory.create())
        .client(client).build()
}

object NewReleaseApi {
    val client by lazy { retrofit.create<NewReleasesService>() }
}

object TopRatedApi {
    val client by lazy { retrofit.create<TopRatedService>() }
}
