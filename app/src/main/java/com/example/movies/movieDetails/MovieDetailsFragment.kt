package com.example.movies.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.movies.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.movies.movieDetails.MoviesDetailsFragmentArgs as MoviesDetailsFragmentArgs1

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class MoviesDetailsFragment : Fragment() {
    @Suppress("ktlint:standard:property-naming")
    private var _movieDetailsBinding: FragmentMovieDetailsBinding? = null

    private val movieDetailsBinding: FragmentMovieDetailsBinding
        get() =
            checkNotNull(_movieDetailsBinding) {
                "You cannot access movie details binding right now "
            }

    val args by navArgs<MoviesDetailsFragmentArgs1>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsBinding.apply {
            movieImage.load(args.movie.image)
            movieTitle.text = args.movie.title
            movieOveriew.text = args.movie.overview
            ratingImdb.text = args.movie.rating
            movieReleaseDate.text = args.movie.releaseDate
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _movieDetailsBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return movieDetailsBinding.root
    }

    override fun onDestroyView() {
        _movieDetailsBinding = null
        super.onDestroyView()
    }
}
