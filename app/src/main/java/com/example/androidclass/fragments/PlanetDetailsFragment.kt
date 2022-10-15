package com.example.androidclass.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.androidclass.R
import com.example.androidclass.data.PlanetsRepository
import com.example.androidclass.databinding.FragmentPlanetDetailsBinding

class PlanetDetailsFragment : Fragment(R.layout.fragment_planet_details) {
    private var _binding: FragmentPlanetDetailsBinding? = null
    private val binding: FragmentPlanetDetailsBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPlanetDetailsBinding.bind(view)

        with(binding) {
            arguments?.let {
                val planetTitle: String? = it.getString(KEY_PLANET_UNIQUE_TITLE)

                planetTitle?.let { title ->
                    PlanetsRepository.getPlanet(title).run {
                        loadImgWithListeners(imgUrl)
                        toolbar.title = title
                        // for visual collapsing toolbar effect
                        tvPlanetDescription.text =
                            "$description $description $description $description $description $description $description $description $description"
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_PLANET_UNIQUE_TITLE = "planet-title"

        fun getInstance(planetTitle: String) = PlanetDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_PLANET_UNIQUE_TITLE, planetTitle)
            }
        }
    }

    private fun loadImgWithListeners(imgUrl: String) {
        Glide
            .with(this@PlanetDetailsFragment)
            .load(imgUrl).listener(object : RequestListener<Drawable?> {
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
                    binding.progressBarForImage.isVisible = false
                    return false
                }

            })
            .into(binding.ivPlanet)
    }
}
