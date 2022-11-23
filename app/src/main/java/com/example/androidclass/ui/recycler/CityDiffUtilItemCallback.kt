package com.example.androidclass.ui.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.androidclass.model.City

class CityDiffUtilItemCallback : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean =
        oldItem == newItem
}
