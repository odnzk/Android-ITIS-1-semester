package com.example.androidclass.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidclass.R
import com.example.androidclass.databinding.FragmentTabsBinding

class TabsFragment : Fragment() {
    private var _binding: FragmentTabsBinding? = null
    private val binding: FragmentTabsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabsBinding.inflate(inflater, container, false)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
