package com.example.androidclass.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidclass.R
import com.example.androidclass.databinding.FragmentFirstBinding
import com.example.androidclass.dialogs.FiltersBottomSheetDialog
import com.example.androidclass.enum.CityFilters
import com.example.androidclass.repository.CityRepository
import com.example.androidclass.ui.recycler.CityAdapter
import com.example.androidclass.utils.CitySorter

class FirstFragment : Fragment(R.layout.fragment_first) {
    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding get() = _binding!!

    private var currentFilter = CityFilters.BY_ID.name
    private val cityAdapter = CityAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setDataToRecycler()

        with(binding) {
            btnShowFilters.setOnClickListener {
                FiltersBottomSheetDialog.getInstance(currentFilter)
                    .show(childFragmentManager, DIALOG_TAG)
            }
        }

        initObservers()
    }

    private fun initObservers() {
        childFragmentManager.setFragmentResultListener(
            CUR_SORTING_REQUEST_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            currentFilter = bundle.getString(CUR_SORT_TYPE_KEY) ?: CityFilters.BY_ID.name
            setDataToRecycler()
        }
    }

    private fun initRecyclerView() {
        with(binding) {
            rvItems.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvItems.adapter = cityAdapter
        }
    }

    private fun setDataToRecycler() {
        val cities = CityRepository.cities
        cityAdapter.submitList(CitySorter.sort(cities, currentFilter)) {
            binding.rvItems.scrollToPosition(0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val CUR_SORTING_REQUEST_KEY = "cur_sort_rk"
        const val CUR_SORT_TYPE_KEY = "cur_sort_type"
        private const val DIALOG_TAG = "dialog tag"
    }
}
