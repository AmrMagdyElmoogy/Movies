package com.example.movies.trends.data.api

import com.example.movies.trends.data.models.dto.FilmsResponse
import com.example.movies.utils.API_KEY
import com.example.movies.utils.NOW_PLAYING
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewReleasesService {
    @GET(NOW_PLAYING)
    suspend fun fetchNewReleasesFilms(
        @Query("api_key") key: String = API_KEY,
    ): Response<FilmsResponse>
}
