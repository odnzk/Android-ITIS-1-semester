package com.example.androidclass.rv.viewholders

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.androidclass.R
import com.example.androidclass.databinding.ItemPlanetBinding
import com.example.androidclass.model.ui.UiPlanet

class UiPlanetHolder(
    private val binding: ItemPlanetBinding,
    private val glide: RequestManager?,
    private val onPlanetClickListener: ((String) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(uiplanet: UiPlanet) {
        with(binding) {
            val color = if (uiplanet.hasBeenVisited) R.color.light_grey else R.color.white
            root.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    color
                )
            )
            uiplanet.planet.run {
                tvTitle.text = title
                tvDesc.text = description

                glide?.load(imgUrl)?.listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBarForImage.isVisible = false
                        return false
                    }

                })?.into(ivPlanet)

                root.setOnClickListener {
                    onPlanetClickListener?.invoke(title)
                }
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager?,
            onPlanetClickListener: ((String) -> Unit)?
        ) =
            UiPlanetHolder(
                ItemPlanetBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                glide,
                onPlanetClickListener
            )
    }

}
