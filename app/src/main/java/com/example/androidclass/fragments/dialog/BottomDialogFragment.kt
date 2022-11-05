package com.example.androidclass.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidclass.R
import com.example.androidclass.databinding.FragmentDialogBinding
import com.example.androidclass.utils.MyAdapter
import com.example.androidclass.utils.MyInterface
import com.example.androidclass.utils.MyModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentDialogBinding? = null
    private val binding: FragmentDialogBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogBinding.bind(inflater.inflate(R.layout.fragment_dialog, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val adapter = MyAdapter()
            adapter.setData(
                listOf(
                    MyModel(R.drawable.ic_baseline_brightness_1_24, "A3"),
                    MyModel(R.drawable.ic_baseline_brightness_2_24, "B2"),
                    MyModel(R.drawable.ic_baseline_brightness_3_24, "C2")
                )
            )
            val layoutManager = LinearLayoutManager(context)
            val dividerItemDecoration = DividerItemDecoration(
                context,
                layoutManager.orientation
            )
            adapter.myInterface = object : MyInterface {
                override fun toA3() {
                    findNavController().apply {
                        navigate(R.id.action_bottomDialogFragment_to_graph_a)
                        navigate(R.id.action_firstFragment_to_firstA3Fragment)
                    }
                }

                override fun toB2() {
                    findNavController().apply {
                        navigate(R.id.action_bottomDialogFragment_to_graph_b)
                        navigate(R.id.action_secondFragment_to_secondB2Fragment)
                    }
                }

                override fun toC3() {
                    findNavController().apply {
                        navigate(R.id.action_bottomDialogFragment_to_graph_c)
                        navigate(R.id.action_thirdFragment_to_thirdC3Fragment)
                    }
                }

            }
            rvDialog.layoutManager = layoutManager
            rvDialog.addItemDecoration(dividerItemDecoration)
            rvDialog.adapter = adapter
        }
    }
}
