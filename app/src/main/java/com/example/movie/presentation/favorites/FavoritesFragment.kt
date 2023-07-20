package com.example.movie.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movie.R
import com.example.movie.databinding.FragmentFavoritesBinding
import com.example.movie.presentation.BaseFragment
import com.example.movie.presentation.dashboard.DashboardAdapter
import com.example.movie.util.showErrorToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FavoritesFragment : BaseFragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                favoritesViewModel.uiState.collect { uiState ->

                    binding.progressBar.isVisible = uiState.isLoading

                    if (uiState.errorMessage.isNullOrEmpty().not())
                        showErrorToast(uiState.errorMessage!!)

                    if (uiState.movieList.isEmpty().not()) {
                        val adapter = DashboardAdapter(requireContext(), uiState.movieList) { movie ->
                            moveToDetail(binding.root, movie, R.id.action_favoritesFragment_to_detailFragment)
                        }
                        binding.favoritesRecyclerView.adapter = adapter
                    } else {
                        binding.favoritesRecyclerView.adapter = null
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        favoritesViewModel.getFavorites()
    }
}