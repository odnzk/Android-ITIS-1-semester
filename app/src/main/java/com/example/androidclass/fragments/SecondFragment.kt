package com.example.androidclass.fragments

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidclass.R
import com.example.androidclass.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {
    private val viewBinding: FragmentSecondBinding by viewBinding(FragmentSecondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            (view1.layoutParams as ConstraintLayout.LayoutParams).run {
                endToEnd = ConstraintLayout.LayoutParams.UNSET
                bottomToTop = ConstraintLayout.LayoutParams.UNSET
                topToTop = parent.id
                endToStart = view2.id
                bottomToTop = view3.id
            }
            (view2.layoutParams as ConstraintLayout.LayoutParams).run {
                startToStart = ConstraintLayout.LayoutParams.UNSET
                bottomToTop = ConstraintLayout.LayoutParams.UNSET
                topToTop = parent.id
                startToEnd = view1.id
                bottomToTop = view4.id
            }

            (view3.layoutParams as ConstraintLayout.LayoutParams).run {
                endToEnd = ConstraintLayout.LayoutParams.UNSET
                bottomToTop = ConstraintLayout.LayoutParams.UNSET
                bottomToBottom = parent.id
                topToBottom = view1.id
                endToStart = view4.id
            }
            (view4.layoutParams as ConstraintLayout.LayoutParams).run {
                startToStart = ConstraintLayout.LayoutParams.UNSET
                topToBottom = view2.id
                startToEnd = view3.id
            }

            (parent.layoutParams as FrameLayout.LayoutParams).height =
                FrameLayout.LayoutParams.MATCH_PARENT
        }
    }

    companion object {
        fun getInstance() = SecondFragment()
    }
}
