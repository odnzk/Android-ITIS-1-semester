package com.example.androidclass.fragments.a

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidclass.R
import com.example.androidclass.databinding.FragmentFirstA2Binding

class FirstA2Fragment : Fragment(R.layout.fragment_first_a2) {
    private var _binding: FragmentFirstA2Binding? = null
    private val binding: FragmentFirstA2Binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstA2Binding.inflate(inflater, container, false)

        with(binding) {
            btnToA3.setOnClickListener {
                findNavController().navigate(R.id.action_firstA2Fragment_to_firstA3Fragment)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
