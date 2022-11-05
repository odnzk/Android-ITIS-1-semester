package com.example.androidclass.fragments.b

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidclass.R
import com.example.androidclass.databinding.FragmentSecondB2Binding

class SecondB2Fragment : Fragment(R.layout.fragment_second_b2) {
    private var _binding: FragmentSecondB2Binding? = null
    private val binding: FragmentSecondB2Binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondB2Binding.inflate(inflater, container, false)

        val textFromB = arguments?.getString(TEXT_KEY, "")
        binding.tv.text = textFromB

        return binding.root
    }

    companion object {
        const val TEXT_KEY = "text-key"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
