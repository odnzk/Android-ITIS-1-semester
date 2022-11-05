package com.example.androidclass.fragments.c

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidclass.R
import com.example.androidclass.databinding.FragmentThirdC2Binding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ThirdC2Fragment : Fragment(R.layout.fragment_third_c2) {
    private var _binding: FragmentThirdC2Binding? = null
    private val binding: FragmentThirdC2Binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentThirdC2Binding.bind(view)

        changeBnvVisibility(false)

        with(binding) {
            btnToC3.setOnClickListener {
                findNavController().navigate(R.id.action_thirdC2Fragment_to_thirdC3Fragment)
            }
        }
    }

    private fun changeBnvVisibility(isVisible: Boolean) {
        activity?.findViewById<BottomNavigationView>(R.id.bnv)?.let {
            it.isVisible = isVisible
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        changeBnvVisibility(true)
    }
}
