package com.example.movie.presentation.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.data.model.Movie
import com.example.movie.databinding.FragmentMovieDetailBinding
import com.example.movie.presentation.BaseFragment
import com.example.movie.util.Constant
import com.example.movie.util.showErrorToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    private var movieDetail: Movie? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addFavoriteImageButton.setOnClickListener {
            movieDetailViewModel.viewModelScope.launch(Dispatchers.IO) {
                movieDetail?.let { safeMovie ->
                    movieDetailViewModel.insertOrDelete(safeMovie)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                movieDetailViewModel.uiState.collect { uiState ->
                    if (uiState.errorMessage.isNullOrEmpty().not())
                        showErrorToast(uiState.errorMessage!!)

                    uiState.inserted?.let {
                        changeIcon(uiState.inserted)
                    }
                }
            }
        }

        movieDetail = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("movie", Movie::class.java)
        } else {
            arguments?.getParcelable("movie")
        }
        setViews(movieDetail)
    }

    private fun setViews(movieDetail: Movie?) {
        movieDetail?.let {
            Glide.with(requireContext()).load(Constant.posterUrl + movieDetail.posterPath).into(binding.posterImageView)
            binding.titleTextView.text = movieDetail.name ?: ""
            binding.movieDetailTextView.text = movieDetail.overview ?: ""
            binding.voteTextView.text = movieDetail.votePoint ?: ""

            checkMovieFavorite(movieDetail)
        }
    }

    private fun checkMovieFavorite(movie: Movie) {
        movieDetailViewModel.viewModelScope.launch(Dispatchers.IO) {
            changeIcon(movieDetailViewModel.isMovieFavorite(movie))
        }
    }

    private fun changeIcon(checkFavorite: Boolean) {
        binding.addFavoriteImageButton.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                if (checkFavorite) R.drawable.favorite else R.drawable.unfavorite
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}