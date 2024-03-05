package com.example.movies.trends.presentation

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movies.databinding.NowPlayingItemBinding
import com.example.movies.trends.data.models.entity.MovieItem
import com.example.movies.utils.PREFIX_IMAGE_URL

class MoviesViewHolder(
    private val binding: NowPlayingItemBinding,
    private val onNavigateToDetailsScreen: (MovieItem) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieItem) {
        val newItem =
            item.copy(
                image = PREFIX_IMAGE_URL + item.image,
            )
        binding.imageItem.load(newItem.image)
        binding.movieTrendTitle.text = newItem.title
        binding.nowPlayingCard.setOnClickListener {
            onNavigateToDetailsScreen(newItem)
        }
    }
}
