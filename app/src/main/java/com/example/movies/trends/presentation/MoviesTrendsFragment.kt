package com.example.movies.trends.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.databinding.FragmentMoviesTrendsBinding
import com.example.movies.trends.domain.entity.MovieItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

const val TAG = "TrendsFragment"

@AndroidEntryPoint
class MoviesTrendsFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var viewModel: MoviesViewModel
    private val newReleaseAdapter =
        MoviesAdapter {
            val action = MoviesTrendsFragmentDirections.toDetailsScreen(it)
            navController.navigate(action)
        }

    private val topRatedAdapter: MoviesAdapter =
        MoviesAdapter {
            val action = MoviesTrendsFragmentDirections.toDetailsScreen(it)
            navController.navigate(action)
        }
    private lateinit var newReleasesRV: RecyclerView
    private lateinit var topRatedRV: RecyclerView

    @Suppress("ktlint:standard:property-naming")
    private var _moviesTrendsBinding: FragmentMoviesTrendsBinding? = null
    private val moviesTrendsBinding: FragmentMoviesTrendsBinding
        get() =
            checkNotNull(_moviesTrendsBinding) {
                "You cannot access "
            }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        val navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHost.navController
        newReleasesRV = moviesTrendsBinding.upperPart.nowPlayingRecycle
        topRatedRV = moviesTrendsBinding.lowerPart.nowPlayingRecycle

        // Collect Now Releases Films
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newReleasesUiState.collect {
                    if (it.isLoading) {
                        moviesTrendsBinding.animation1.loading.visibility = View.VISIBLE
                    } else if (it.isError) {
                        moviesTrendsBinding.animation1.loading.visibility = View.GONE
                        moviesTrendsBinding.animation2.error.visibility = View.VISIBLE
                    } else {
                        moviesTrendsBinding.animation1.loading.visibility = View.GONE
                        moviesTrendsBinding.animation2.error.visibility = View.GONE
                        val list = it.success as List<MovieItem>
                        newReleaseAdapter.submitList(list)
                    }
                }
            }
        }

        // Collect Top Rated Films
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.topRatedState.collect {
                    if (it.isLoading) {
                        moviesTrendsBinding.animation1.loading.visibility = View.VISIBLE
                    } else if (it.isError) {
                        moviesTrendsBinding.animation1.loading.visibility = View.GONE
                        moviesTrendsBinding.animation2.error.visibility = View.VISIBLE
                    } else {
                        moviesTrendsBinding.animation1.loading.visibility = View.GONE
                        moviesTrendsBinding.animation2.error.visibility = View.GONE
                        val list = it.success as List<MovieItem>
                        topRatedAdapter.submitList(list)
                    }
                }
            }
        }
        newReleasesRV.adapter = newReleaseAdapter
        topRatedRV.adapter = topRatedAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _moviesTrendsBinding = FragmentMoviesTrendsBinding.inflate(inflater, container, false)
        return moviesTrendsBinding.root
    }

    override fun onDestroyView() {
        _moviesTrendsBinding = null
        super.onDestroyView()
    }
}
