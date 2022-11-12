package com.example.androidclass

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.androidclass.databinding.ItemGridBlockBinding
import kotlin.math.roundToInt

class GridManagerAdapter(
    private val dimension: GridItemDimensionsModel,
    private val onClick: () -> Unit
) :
    RecyclerView.Adapter<GridManagerAdapter.GridLayoutViewHolder>() {

    private var gridItems: MutableList<Int> = mutableListOf()

    fun setData(newItems: List<Int>) {
        gridItems.clear()
        gridItems.addAll(newItems)
        notifyDataSetChanged()
    }

    fun insertItem() {
        gridItems.add((Math.random() * itemCount).roundToInt())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridLayoutViewHolder {
        return GridLayoutViewHolder(
            viewBinding = ItemGridBlockBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GridLayoutViewHolder, position: Int) {
        val ost = itemCount % 6
        if (position < itemCount - ost || (ost == 5 && position >= itemCount - ost)) {
            when (position % 6) {
                0, 5 -> {
                    holder.bindHorizontal()
                }
                1, 4 -> {
                    holder.bindVerticalSquare(position)
                }
                2, 3 -> {
                    holder.bindVerticalRectangle(position)
                }
            }
        } else {
            if (itemCount <= 4) {
                when (position % 4) {
                    0, 3 -> holder.bindHorizontal()
                    1, 2 -> holder.bindVerticalRectangle(position)
                }
            } else {
                when ((position + 1) % 6) {
                    1, 4 -> holder.bindHorizontal()
                    2, 3 -> holder.bindVerticalRectangle(position)
                }
            }
        }
    }

    override fun getItemCount(): Int = gridItems.size

    inner class GridLayoutViewHolder(val viewBinding: ItemGridBlockBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bindHorizontal() {
            val halfMargin = dimension.halfMargin
            (viewBinding.root.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.apply {
                width = dimension.horizontalItemWidth
                height = dimension.horizontalItemHeight
                isFullSpan = true
                setMargins(halfMargin * 2, halfMargin, halfMargin * 2, halfMargin)
            }
            viewBinding.root.run {
                setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.purple_700
                    )
                )
                setOnClickListener { onClick() }
            }
        }

        fun bindVerticalRectangle(position: Int) {
            val halfMargin = dimension.halfMargin
            (viewBinding.root.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.apply {
                width = dimension.verticalRectangleItemWidth
                height = dimension.verticalRectangleItemHeight
                isFullSpan = false
                if (itemCount >= 5) {
                    when (position % 6) {
                        1 -> setMargins(
                            halfMargin * 2,
                            halfMargin,
                            halfMargin,
                            halfMargin
                        )
                        2 -> setMargins(
                            halfMargin,
                            halfMargin,
                            halfMargin * 2,
                            halfMargin
                        )
                        3 -> setMargins(
                            halfMargin * 2,
                            halfMargin,
                            halfMargin,
                            halfMargin
                        )
                    }
                } else {
                    when (position % 4) {
                        1 -> setMargins(halfMargin * 2, halfMargin, halfMargin, halfMargin)
                        2 -> setMargins(halfMargin, halfMargin, halfMargin * 2, halfMargin)
                    }
                }
            }
            viewBinding.root.run {
                setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.teal_200
                    )
                )
                setOnClickListener { onClick() }
            }
        }

        fun bindVerticalSquare(position: Int) {
            val halfMargin = dimension.halfMargin
            (viewBinding.root.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.apply {
                width = dimension.verticalSquareItemWidth
                height = dimension.verticalSquareItemHeight
                isFullSpan = false
                when (position % 6) {
                    1 -> setMargins(halfMargin * 2, halfMargin, halfMargin, halfMargin) // left side
                    4 -> setMargins(
                        halfMargin,
                        halfMargin,
                        halfMargin * 2,
                        halfMargin
                    )
                }
            }
            viewBinding.root.run {
                setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.teal_700
                    )
                )
                setOnClickListener { onClick() }
            }
        }
    }
}
