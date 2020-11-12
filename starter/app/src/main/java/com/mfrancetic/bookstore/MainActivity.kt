package com.mfrancetic.bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.mfrancetic.bookstore.databinding.ActivityMainBinding
import com.mfrancetic.bookstore.utils.SharedPreferencesHelper
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var topLevelDestinations: Set<Int>
    private val viewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.app_name)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupNavControllerAndToolbar()
        getLoginStatus()
        setupObserver()

        Timber.plant(Timber.DebugTree())
    }

    private fun getLoginStatus() {
        viewModel.getLoginStateFromSharedPreferences(this)
    }

    private fun setupObserver() {
        viewModel.isUserLoggedIn.observe(this, { isUserLoggedIn ->
            if (isUserLoggedIn) {
                navigateToBookListFragment()
            } else {
                logoutUser()
            }
        })
    }

    private fun setupNavControllerAndToolbar() {
        navController = this.findNavController(R.id.nav_host_fragment)

        topLevelDestinations = setOf(R.id.loginFragment, R.id.welcomeFragment,
                R.id.bookListFragment)
        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations)
                .build()

        setSupportActionBar(binding.toolbar)
        setupWithNavController(binding.toolbar, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (topLevelDestinations.contains(navController.currentDestination?.id)) {
            finish()
        } else {
            onSupportNavigateUp()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            logoutUser()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logoutUser() {
        SharedPreferencesHelper.addLoginStatusToSharedPreferences(this, false)
        navController.navigate(R.id.loginFragment)
    }

    private fun navigateToBookListFragment() {
        navController.navigate(R.id.bookListFragment)
    }
}