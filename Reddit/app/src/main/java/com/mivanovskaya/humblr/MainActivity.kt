package com.mivanovskaya.humblr

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mivanovskaya.humblr.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView = binding.bottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_onboarding || destination.id == R.id.navigation_auth) {
                navView.visibility = View.GONE
                window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                /**off.documentation offers to use this instead of deprecated:
                 * WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE, which requires API 30+ */
            } else navView.visibility = View.VISIBLE
        }
    }
}