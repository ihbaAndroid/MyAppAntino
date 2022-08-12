package com.android.antino

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.antino.databinding.ActivityMainBinding
import com.android.antino.ui.gallery.GalleryFragment
import com.android.antino.ui.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        binding.bottomNavigation.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.nav_radio -> {
                   Toast.makeText(this,"BottomNavigation Clicked1 !!",Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_my_music -> {
                    Toast.makeText(this,"BottomNavigation Clicked2 !!",Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_library -> {
                    Toast.makeText(this,"BottomNavigation Clicked3 !!",Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }

        }

       binding.bottomNavigation.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home Item reselected", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_radio -> {
                    Toast.makeText(this, "Radio Item reselected", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_my_music -> {
                    Toast.makeText(this, "My Music Item reselected", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_library -> {
                    Toast.makeText(this, "Library Item reselected", Toast.LENGTH_SHORT).show()

                }
            }
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}