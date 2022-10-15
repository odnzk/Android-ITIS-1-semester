package com.example.androidclass.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.androidclass.R
import com.example.androidclass.data.PlanetsRepository
import com.example.androidclass.databinding.FragmentPlanetsListBinding
import com.example.androidclass.model.domain.Planet
import com.example.androidclass.model.ui.PlanetCategory
import com.example.androidclass.model.ui.UiPlanet
import com.example.androidclass.rv.PlanetsAdapter


class PlanetsListFragment : Fragment(R.layout.fragment_planets_list) {
    private var _binding: FragmentPlanetsListBinding? = null
    private val binding: FragmentPlanetsListBinding get() = _binding!!

    private val adapter = PlanetsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPlanetsListBinding.bind(view)

        with(binding) {
            val layoutManager = LinearLayoutManager(context)
            val dividerItemDecoration = DividerItemDecoration(
                context,
                layoutManager.orientation
            )
            rvPlanets.layoutManager = layoutManager
            rvPlanets.addItemDecoration(dividerItemDecoration)
            rvPlanets.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToDetailsFragment(titlePlanet: String) {
        adapter.updateVisitedPlanetStatus(titlePlanet)

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, PlanetDetailsFragment.getInstance(titlePlanet))
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }

    private fun setUpAdapter() {
        val data = PlanetsRepository
            .getPlanets()
            .map {
                if (it is Planet) {
                    UiPlanet(planet = it)
                } else it as PlanetCategory
            }
        adapter.onPlanetClickListener = ::navigateToDetailsFragment
        adapter.glide = Glide.with(this@PlanetsListFragment)
        adapter.setData(data)
    }
}
