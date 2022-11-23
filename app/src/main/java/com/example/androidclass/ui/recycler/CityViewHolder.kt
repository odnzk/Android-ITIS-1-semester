package com.example.androidclass.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclass.databinding.ItemCityBinding
import com.example.androidclass.model.City

class CityViewHolder(private val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(city: City) {
        with(binding) {
            tvPosition.text = city.id.toString()
            tvTitle.text = city.name
        }
    }

    companion object {
        fun create(parent: ViewGroup): CityViewHolder =
            CityViewHolder(
                ItemCityBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }
}
