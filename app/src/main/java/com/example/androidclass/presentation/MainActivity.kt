package com.example.androidclass.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.androidclass.R
import com.example.androidclass.data.repository.AppSettingsImpl
import com.example.androidclass.databinding.ActivityMainBinding
import com.example.androidclass.domain.repository.AppSettings

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val appSettings: AppSettings by lazy {
        AppSettingsImpl(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        val graph = navController.navInflater.inflate(R.navigation.main_graph)
        if (appSettings.getCurrentAccountId() != AppSettings.NO_ACCOUNT_ID) {
            graph.setStartDestination(R.id.tabsFragment)
        } else {
            graph.setStartDestination(R.id.loginFragment)
        }
        navController.graph = graph
    }
}
