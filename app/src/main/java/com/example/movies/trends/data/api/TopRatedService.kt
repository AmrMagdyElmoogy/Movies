package com.example.movies.trends.data.api

import com.example.movies.trends.data.dto.FilmsResponse
import com.example.movies.utils.API_KEY
import com.example.movies.utils.TOP_RATED
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedService {
    @GET(TOP_RATED)
    suspend fun fetchTopRatedFilms(
        @Query("api_key") key: String = API_KEY,
    ): Response<FilmsResponse>
}
