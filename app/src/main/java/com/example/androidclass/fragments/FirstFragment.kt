package com.example.androidclass.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.androidclass.R
import com.example.androidclass.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var counter = 0
    private var backgroundColorId = Color.BLACK

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        with(binding) {

            btnNavigateToSecondFragment.setOnClickListener {
                makeTransaction(true)
            }

            btnPlus.setOnClickListener {
                counter++
                makeTransaction()
            }

            btnChange.setOnClickListener {
                backgroundColorId = generateColor()
                makeTransaction()
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun makeTransaction(navigate: Boolean = false) {
        val slidingPaneLayout: SlidingPaneLayout =
            requireActivity().findViewById(R.id.sliding_pane_layout)
        parentFragmentManager.beginTransaction().run {
            replace(R.id.container_second, SecondFragment.newInstance(counter, backgroundColorId))
            setReorderingAllowed(true)
            if (slidingPaneLayout.isOpen) {
                // swap
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            }
            commit()
        }
        // update sliding pane
        if (navigate) {
            slidingPaneLayout.openPane()
        }
    }

    private fun generateColor(): Int {
        val allowedChars = ('A'..'F') + ('a'..'f') + ('0'..'9')
        return Color.parseColor("#" + (1..6)
            .map { allowedChars.random() }
            .joinToString(""))
    }
}
