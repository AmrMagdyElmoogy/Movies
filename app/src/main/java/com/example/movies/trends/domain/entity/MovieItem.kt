package com.example.movies.trends.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItem(
    val id: String,
    val title: String,
    val rating: String,
    val releaseDate: String,
    val overview: String,
    val image: String,
    val state: String,
) : Parcelable
