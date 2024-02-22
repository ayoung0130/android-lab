package com.android.mediapipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.android.mediapipe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        activityMainBinding.navigation.setOnNavigationItemSelectedListener { item ->
            // Handle navigation item selection
            when (item.itemId) {
                R.id.camera_fragment -> {
                    // Navigate to the CameraFragment
                    navController.navigate(R.id.camera_fragment)
                    true
                }
                // Ignore other menu items
                else -> false
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}