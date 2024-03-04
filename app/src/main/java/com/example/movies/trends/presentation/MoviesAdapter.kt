package com.example.movies.trends.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.movies.databinding.NowPlayingItemBinding
import com.example.movies.trends.data.entity.MovieItem

class MoviesAdapter(
    private val onNavigateToDetailsScreen: (MovieItem) -> Unit,
) : ListAdapter<MovieItem, MoviesViewHolder>(DIFF_UTIL) {
    object DIFF_UTIL : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(
            oldItem: MovieItem,
            newItem: MovieItem,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieItem,
            newItem: MovieItem,
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NowPlayingItemBinding.inflate(layoutInflater)
        return MoviesViewHolder(binding, onNavigateToDetailsScreen)
    }

    override fun onBindViewHolder(
        holder: MoviesViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        holder.bind(item)
    }
}
