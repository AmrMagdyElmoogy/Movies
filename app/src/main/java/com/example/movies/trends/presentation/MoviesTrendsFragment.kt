package com.example.movies.trends.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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
import com.example.movies.trends.data.models.entity.MovieItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MoviesTrendsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moviesUiState.collect {
                    if (it.isLoading) {
                        moviesTrendsBinding.animation1.loading.isVisible = true
                    } else if (it.isError) {
                        moviesTrendsBinding.animation1.loading.isVisible = false
                        moviesTrendsBinding.animation2.error.isVisible = true
                    } else {
                        moviesTrendsBinding.animation1.loading.isVisible = false
                        moviesTrendsBinding.animation2.error.isVisible = false
                        val list = it.success as List<MovieItem>
                        newReleaseAdapter.submitList(list)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.topRatedState.collect {
                    if (it.isLoading) {
                        moviesTrendsBinding.animation3.loading.isVisible = true
                    } else if (it.isError) {
                        moviesTrendsBinding.animation3.loading.isVisible = false
                        moviesTrendsBinding.animation4.error.isVisible = true
                    } else {
                        moviesTrendsBinding.animation3.loading.isVisible = false
                        moviesTrendsBinding.animation4.error.isVisible = false
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
