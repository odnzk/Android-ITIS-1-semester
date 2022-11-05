package com.example.androidclass.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclass.databinding.ItemRvBinding

class MyViewHolder(private val binding: ItemRvBinding, private val actions: MyInterface?) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            when (adapterPosition) {
                0 -> {
                    actions?.toA3()
                }
                1 -> actions?.toB2()
                2 -> actions?.toC3()
            }
        }
    }

    fun bind(model: MyModel) {
        with(binding) {
            ivIcon.setImageResource(model.iconId)
            tvText.text = model.text
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            actions: MyInterface?
        ) =
            MyViewHolder(
                ItemRvBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                actions
            )
    }
}
