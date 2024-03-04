package com.example.movies.trends.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.databinding.NowPlayingItemBinding
import com.example.movies.trends.data.entity.MovieItem

class MoviesViewHolder(
    private val binding: NowPlayingItemBinding,
    private val onNavigateToDetailsScreen: (MovieItem) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieItem) {
        binding.imageItem.setImageResource(R.drawable.juju)
        binding.movieTrendTitle.text = item.title
        binding.nowPlayingCard.setOnClickListener {
            onNavigateToDetailsScreen(item)
        }
    }
}
