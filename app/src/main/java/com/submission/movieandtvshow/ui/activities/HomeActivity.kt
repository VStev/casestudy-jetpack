package com.submission.movieandtvshow.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.submission.movieandtvshow.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navView : BottomNavigationView = findViewById(R.id.bottom_nav_view)
        val navController = findNavController(R.id.host_fragment)
        val appBarConfig = AppBarConfiguration.Builder(R.id.nav_tv, R.id.nav_movie, R.id.nav_star_show, R.id.nav_star_movie).build()
        setupActionBarWithNavController(navController, appBarConfig)
        navView.setupWithNavController(navController)
    }
}