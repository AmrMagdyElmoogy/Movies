package com.example.movies.trends.domain

import android.util.Log
import com.example.movies.trends.data.api.TopRatedApi
import com.example.movies.trends.data.dto.toMovieEntity
import com.example.movies.trends.data.entity.MovieItem
import java.io.IOException

class TopRatedRepository {
    private val api = TopRatedApi.client

    suspend fun getTopRatedFilmsFromDB(): Result<List<MovieItem>?>  {
        return try {
            val response = api.fetchTopRatedFilms()
            if (response.isSuccessful) {
                val body =
                    response.body()?.let { res ->
                        res.results.map {
                            it.toMovieEntity()
                        }
                    }
                Result.success(body)
            } else {
                Log.d(TAG, response.errorBody().toString())
                Result.failure(Throwable("Error happened!"))
            }
        } catch (e: IOException) {
            Log.d(TAG, e.toString())
            Result.failure(Throwable("The connection is lost, check out your connection"))
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            Result.failure(Throwable("Try again in another time"))
        }
    }
}
