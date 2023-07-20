package com.example.movie.presentation

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.movie.data.model.Movie

open class BaseFragment : Fragment() {

    fun moveToDetail(view: View, movie: Movie, action: Int) {
        val bundle = bundleOf("movie" to movie)
        view.findNavController().navigate(action, bundle)
    }
}