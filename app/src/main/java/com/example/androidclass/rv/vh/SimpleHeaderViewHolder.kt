package com.example.androidclass.rv.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclass.databinding.ItemHeaderBinding
import com.example.androidclass.model.SimpleHeader

class SimpleHeaderViewHolder(
    private val binding: ItemHeaderBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(simpleHeader: SimpleHeader) {
        with(binding) {
            tvTitle.text = simpleHeader.title
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
        ): SimpleHeaderViewHolder = SimpleHeaderViewHolder(
            ItemHeaderBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}
