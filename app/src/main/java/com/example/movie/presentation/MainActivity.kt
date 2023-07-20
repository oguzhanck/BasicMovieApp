package com.example.movie.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.movie.R
import com.example.movie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val navController = findNavController(R.id.nav_host_fragment_content_main)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.moviesItem -> {
                    navController.navigate(R.id.action_favoritesFragment_to_dashboardFragment)
                }
                R.id.favoritesItem -> {
                    navController.currentBackStackEntry
                    navController.navigate(R.id.action_dashboardFragment_to_favoritesFragment)
                }
            }
            true
        }

        binding.bottomNavigation.setOnItemReselectedListener {}

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.detailFragment) {
                binding.bottomNavigation.visibility = View.GONE
            } else {
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
    }
}