package com.example.androidclass.rv.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclass.databinding.ItemPlanetCategoryBinding
import com.example.androidclass.model.ui.PlanetCategory

class PlanetCategoryHolder(private val binding: ItemPlanetCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(planetCategory: PlanetCategory) {
        with(binding) {
            tvPlanetCategory.text = planetCategory.title
        }
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) =
            PlanetCategoryHolder(
                ItemPlanetCategoryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }
}
