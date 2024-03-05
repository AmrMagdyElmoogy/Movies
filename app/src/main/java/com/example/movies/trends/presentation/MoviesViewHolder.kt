package com.example.movies.trends.presentation

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movies.databinding.NowPlayingItemBinding
import com.example.movies.trends.data.entity.MovieItem
import com.example.movies.utils.PREFIX_IMAGE_URL

class MoviesViewHolder(
    private val binding: NowPlayingItemBinding,
    private val onNavigateToDetailsScreen: (MovieItem) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieItem) {
        val urlImage = PREFIX_IMAGE_URL + item.image
        binding.imageItem.load(urlImage)
        binding.movieTrendTitle.text = item.title
        binding.nowPlayingCard.setOnClickListener {
            onNavigateToDetailsScreen(item)
        }
    }
}
