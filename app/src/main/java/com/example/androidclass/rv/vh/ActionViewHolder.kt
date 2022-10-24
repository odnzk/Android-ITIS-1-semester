package com.example.androidclass.rv.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclass.databinding.ItemActionBinding
import com.example.androidclass.model.Action

class ActionViewHolder(private val binding: ItemActionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(action: Action) {
        with(binding) {
            tvTitle.text = action.title
            iv.setImageResource(action.iconResId)
        }
    }

    companion object {
        fun create(parent: ViewGroup): ActionViewHolder = ActionViewHolder(
            ItemActionBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}