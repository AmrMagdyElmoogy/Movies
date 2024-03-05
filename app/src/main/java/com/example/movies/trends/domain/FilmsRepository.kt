package com.example.movies.trends.domain

import com.example.movies.trends.data.api.NewReleasesService
import com.example.movies.trends.data.api.TopRatedService
import com.example.movies.trends.data.db.MoviesServiceDAO
import com.example.movies.trends.data.models.dto.FilmsResponse
import com.example.movies.trends.data.models.dto.toMovieEntity
import com.example.movies.trends.data.models.dto.toMovieTableEntity
import com.example.movies.trends.data.models.entity.MovieItem
import com.example.movies.utils.NEW_RELEASES_GENERA
import com.example.movies.utils.TOP_RATED_GENERA
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

const val TAG = "NewReleasesRepository"

@Singleton
class MoviesTrendsRepository
    @Inject
    constructor(
        private val newReleasesClient: NewReleasesService,
        private val topRatedClient: TopRatedService,
        private val dao: MoviesServiceDAO,
    ) {
        fun getAllFilmsLocally(): Flow<List<MovieItem>> {
            return dao.selectAllFilms().map { list ->
                list.map {
                    it.toMovieEntity()
                }
            }
        }

        suspend fun getNewReleasesFilmsRemotely(): Result<List<MovieItem>?> {
            return try {
                val response = newReleasesClient.fetchNewReleasesFilms()
                if (response.isSuccessful) {
                    onSuccessfulResponse(response, NEW_RELEASES_GENERA)
                } else {
                    Result.failure(Throwable("Error happened!"))
                }
            } catch (e: IOException) {
                Result.failure(Throwable("The connection is lost, check out your connection"))
            } catch (e: Exception) {
                Result.failure(Throwable("Try again in another time"))
            }
        }

        suspend fun getTopRatedFilmsRemotely(): Result<List<MovieItem>?> {
            return try {
                val response = topRatedClient.fetchTopRatedFilms()
                if (response.isSuccessful) {
                    onSuccessfulResponse(response, TOP_RATED_GENERA)
                } else {
                    Result.failure(Throwable("Error happened!"))
                }
            } catch (e: IOException) {
                Result.failure(Throwable("The connection is lost, check out your connection"))
            } catch (e: Exception) {
                Result.failure(Throwable("Try again in another time"))
            }
        }

        private fun parseResponseToListOfMovies(response: Response<FilmsResponse>): List<MovieItem>? {
            val body =
                response.body()?.let { res ->
                    res.results.map {
                        it.toMovieEntity()
                    }
                }
            return body
        }

        private suspend fun onSuccessfulResponse(
            response: Response<FilmsResponse>,
            type: String,
        ): Result<List<MovieItem>?> {
            val body =
                parseResponseToListOfMovies(response)
            when (type) {
                NEW_RELEASES_GENERA ->
                    insertNewItemsToDB(body, NEW_RELEASES_GENERA)

                TOP_RATED_GENERA ->
                    insertNewItemsToDB(body, TOP_RATED_GENERA)
            }

            return Result.success(body)
        }

        private suspend fun insertNewItemsToDB(
            body: List<MovieItem>?,
            type: String,
        ) {
            body?.let {
                it.map {
                    when (type) {
                        NEW_RELEASES_GENERA ->
                            dao.insertNewFilms(
                                it.toMovieTableEntity().copy(state = NEW_RELEASES_GENERA),
                            )

                        TOP_RATED_GENERA ->
                            dao.insertNewFilms(
                                it.toMovieTableEntity().copy(state = TOP_RATED_GENERA),
                            )
                    }
                }
            }
        }
    }
