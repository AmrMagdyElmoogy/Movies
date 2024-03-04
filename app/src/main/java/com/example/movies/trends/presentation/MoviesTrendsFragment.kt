package com.example.movies.trends.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.databinding.FragmentMoviesTrendsBinding
import com.example.movies.trends.data.entity.MovieItem

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MoviesTrendsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesTrendsFragment : Fragment() {
    private val movies =
        mutableListOf(
            MovieItem(
                "1",
                "Inception",
                "4.5",
                "22 May 2022",
                "A mind-bending thriller",
                "inception_image_url",
            ),
            MovieItem(
                "2",
                "The Shawshank Redemption",
                "4.8",
                "22 May 2022",
                "A tale of hope and redemption",
                "shawshank_image_url",
            ),
            MovieItem(
                "3",
                "The Dark Knight",
                "4.7",
                "22 May 2022",
                "The epic battle of Batman and Joker",
                "dark_knight_image_url",
            ),
            MovieItem(
                "4",
                "Pulp Fiction",
                "4.3",
                "22 May 2022",
                "A nonlinear journey through crime",
                "pulp_fiction_image_url",
            ),
            MovieItem(
                "5",
                "Forrest Gump",
                "4.6",
                "22 May 2022",
                "Life is like a box of chocolates",
                "forrest_gump_image_url",
            ),
            MovieItem(
                "6",
                "The Matrix",
                "4.4",
                "22 May 2022",
                "Welcome to the real world",
                "matrix_image_url",
            ),
            MovieItem(
                "7",
                "Interstellar",
                "4.6",
                "22 May 2022",
                "A space odyssey beyond imagination",
                "interstellar_image_url",
            ),
        )
    private lateinit var navController: NavController
    private val adapter =
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
        val navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHost.navController
        newReleasesRV = moviesTrendsBinding.nowPlayingRecycle
        topRatedRV = moviesTrendsBinding.topRatedRecycle
        adapter.submitList(
            movies,
        )
        newReleasesRV.adapter = adapter
        topRatedRV.adapter = adapter
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
