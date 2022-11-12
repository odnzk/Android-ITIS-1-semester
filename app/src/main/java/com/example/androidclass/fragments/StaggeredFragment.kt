package com.example.androidclass.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.androidclass.GridItemDimensionsModel
import com.example.androidclass.GridManagerAdapter
import com.example.androidclass.R
import com.example.androidclass.databinding.FragmentStaggeredBinding
import com.example.androidclass.extensions.dpToPx

class StaggeredFragment : Fragment(R.layout.fragment_staggered) {
    private var _binding: FragmentStaggeredBinding? = null
    private val binding: FragmentStaggeredBinding get() = _binding!!

    private var adapter: GridManagerAdapter? = null
    private var gridDimensions: GridItemDimensionsModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStaggeredBinding.bind(view)

        arguments?.run {
            val countElements = getInt(KEY_COUNT_STAGGERED_ELEMENTS, 1)
            gridDimensions = calculateViewSizes()
            initRecyclerView(countElements)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView(itemsCount: Int) {
        val layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            }
        adapter = GridManagerAdapter(
            dimension = gridDimensions
                ?: throw IllegalArgumentException("Grid dimensions model is null")
        ) {
            adapter?.insertItem()
        }
        adapter?.setData((1..itemsCount).toList())
        with(binding) {
            rvStaggered.layoutManager = layoutManager
            rvStaggered.adapter = adapter
        }
    }

    private fun calculateViewSizes(): GridItemDimensionsModel {
        val metrics = resources.displayMetrics

        val horizontalItemWidth = metrics.widthPixels
        val horizontalItemHeight = horizontalItemWidth / 2f
        val verticalItemHeight = horizontalItemWidth / 1.2615f

        return GridItemDimensionsModel(
            horizontalItemWidth = horizontalItemWidth,
            horizontalItemHeight = horizontalItemHeight.toInt(),
            verticalRectangleItemWidth = horizontalItemHeight.toInt(),
            verticalRectangleItemHeight = verticalItemHeight.toInt(),
            verticalSquareItemWidth = horizontalItemHeight.toInt(), // 1 : 1 (HorizontalHeight : SquareWidth)
            verticalSquareItemHeight = horizontalItemHeight.toInt(), // 1 : 1 (HorizontalHeight : SquareHeight)
            halfMargin = requireContext().dpToPx(8f).toInt()
        )
    }

    companion object {
        const val KEY_COUNT_STAGGERED_ELEMENTS = "count-staggered-elems"
    }
}
