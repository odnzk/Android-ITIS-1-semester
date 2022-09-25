package com.example.androidclass.fragments

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.androidclass.R
import com.example.androidclass.databinding.FragmentFirstBinding
import com.example.androidclass.util.MyPhoneNumberFormattingTextWatcher

class FirstFragment : Fragment(R.layout.fragment_first) {
    private val viewBinding: FragmentFirstBinding by viewBinding(FragmentFirstBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTextChangeListener()

        with(viewBinding) {
            btnNavigateToSecondFragment.setOnClickListener {
                parentFragmentManager.beginTransaction().run {
                    replace(R.id.fragments_container, SecondFragment.getInstance())
                    setReorderingAllowed(true)
                    commit()
                }
            }
        }
    }

    private fun initTextChangeListener() {
        with(viewBinding) {
            PhoneNumberFormattingTextWatcher()
            etPhone.addTextChangedListener(
                MyPhoneNumberFormattingTextWatcher(
                    etPhone,
                    etAnything,
                    btnNavigateToSecondFragment
                )
            )
        }
    }

    companion object {
        fun getInstance() = FirstFragment()
    }
}
