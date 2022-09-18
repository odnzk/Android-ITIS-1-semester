package com.example.androidclass.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import com.example.androidclass.R
import com.example.androidclass.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {
    private var _binging: FragmentSecondBinding? = null
    private val binding get() = _binging!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binging = FragmentSecondBinding.inflate(inflater, container, false)

        arguments?.run {
            with(binding) {
                view.setBackgroundColor(
                    getInt(KEY_BG_COLOR_ID, BG_COLOR_ID_DEFAULT)
                )
                val counter = getInt(KEY_COUNTER, 0)
                if (counter != 0) {
                    tvCounter.isInvisible = false
                    tvCounter.text = getString(R.string.tv_counter, counter)
                }
            }
        }
        return binding.root
    }

    companion object {
        private const val KEY_COUNTER = "counter"
        private const val KEY_BG_COLOR_ID = "backgroundColorId"
        private const val BG_COLOR_ID_DEFAULT = android.R.color.holo_red_light

        fun newInstance(
            counter: Int,
            backgroundColorId: Int = BG_COLOR_ID_DEFAULT
        ): SecondFragment =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_COUNTER, counter)
                    putInt(KEY_BG_COLOR_ID, backgroundColorId)
                }
            }
    }
}
