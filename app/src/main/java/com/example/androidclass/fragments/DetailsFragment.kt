package com.example.androidclass.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.androidclass.R
import com.example.androidclass.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_list) {
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
