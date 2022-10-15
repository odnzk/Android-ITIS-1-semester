package com.example.androidclass.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.androidclass.model.ui.PlanetCategory
import com.example.androidclass.model.ui.PlanetInfo
import com.example.androidclass.model.ui.UiPlanet
import com.example.androidclass.rv.viewholders.PlanetCategoryHolder
import com.example.androidclass.rv.viewholders.UiPlanetHolder

class PlanetsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var glide: RequestManager? = null
    var onPlanetClickListener: ((String) -> Unit)? = null

    private var data: MutableList<PlanetInfo> = mutableListOf()

    fun updateVisitedPlanetStatus(title: String) {
        data.filterIsInstance<UiPlanet>().first { it.planet.title == title }.run {
            hasBeenVisited = true
            notifyItemChanged(data.indexOf(this))
        }
    }

    fun setData(newData: List<PlanetInfo>) {
        data.clear()
        data.addAll(newData)

        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is UiPlanet -> VIEW_TYPE_UIPLANET
            is PlanetCategory -> VIEW_TYPE_PLANET_CATEGORY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_UIPLANET -> UiPlanetHolder.create(parent, glide, onPlanetClickListener)
            else -> PlanetCategoryHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UiPlanetHolder -> holder.bind(data[position] as UiPlanet)
            is PlanetCategoryHolder -> holder.bind(data[position] as PlanetCategory)
        }
    }

    override fun getItemCount(): Int = data.size


    companion object {
        private const val VIEW_TYPE_UIPLANET = 0
        private const val VIEW_TYPE_PLANET_CATEGORY = 1
    }
}
