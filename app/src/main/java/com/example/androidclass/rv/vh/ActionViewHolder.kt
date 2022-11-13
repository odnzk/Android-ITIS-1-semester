package com.example.androidclass.rv.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclass.databinding.ItemActionBinding
import com.example.androidclass.model.Action

class ActionViewHolder(
    private val binding: ItemActionBinding,
    private val onActionClickListener: ((Int) -> Unit)? = null
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onActionClickListener?.invoke(adapterPosition)
        }
    }

    fun bind(action: Action) {
        with(binding) {
            tvTitle.text = action.title
            iv.setImageResource(action.iconResId)
        }
    }

    companion object {
        fun create(parent: ViewGroup, onActionClickListener: ((Int) -> Unit)?): ActionViewHolder =
            ActionViewHolder(
                ItemActionBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false),
                onActionClickListener
            )
    }
}
