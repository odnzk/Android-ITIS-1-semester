package com.example.androidclass.ui.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.androidclass.model.City

class CityAdapter : ListAdapter<City, CityViewHolder>(CityDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
        CityViewHolder.create(parent)

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) =
        holder.bind(getItem(position))

}
