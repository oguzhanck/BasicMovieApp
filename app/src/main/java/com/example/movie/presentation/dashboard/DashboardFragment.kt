package com.example.movie.presentation.dashboard

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
import com.example.movie.databinding.FragmentDashboardBinding
import com.example.movie.presentation.BaseFragment
import com.example.movie.util.showErrorToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : BaseFragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewModel.getMovies()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                dashboardViewModel.uiState.collect { uiState ->

                    binding.progressBar.isVisible = uiState.isLoading

                    if (uiState.errorMessage.isNullOrEmpty().not()) {
                        showErrorToast(uiState.errorMessage!!)
                    }
                    if (uiState.movieList.isEmpty().not()) {
                        val adapter = DashboardAdapter(requireContext(), uiState.movieList) { movie ->
                            moveToDetail(view, movie, R.id.action_main_to_detail)
                        }
                        binding.movieRecyclerView.adapter = adapter
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}