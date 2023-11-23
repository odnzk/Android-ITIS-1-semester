package com.example.androidclass.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.androidclass.databinding.DialogFiltersBinding
import com.example.androidclass.enum.CityFilters
import com.example.androidclass.fragments.FirstFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FiltersBottomSheetDialog : BottomSheetDialogFragment() {
    private var _binding: DialogFiltersBinding? = null
    private val binding: DialogFiltersBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val currentFilter: String =
                arguments?.getString(CURRENT_FILTER) ?: CityFilters.BY_ID.name

            btnSortById.isChecked = currentFilter == CityFilters.BY_ID.name ||
                    currentFilter == CityFilters.BY_ID_DESC.name
            btnSortByAlphabet.isChecked =
                currentFilter == CityFilters.BY_ALPHABET.name ||
                        currentFilter == CityFilters.BY_ALPHABET_DESC.name

            btnSortById.setOnClickListener {
                val newFilter: String =
                    if (currentFilter == CityFilters.BY_ID.name)
                        CityFilters.BY_ID_DESC.name else CityFilters.BY_ID.name
                sendDataToFragment(newFilter)
            }
            btnSortByAlphabet.setOnClickListener {
                val newFilter: String =
                    if (currentFilter == CityFilters.BY_ALPHABET.name)
                        CityFilters.BY_ALPHABET_DESC.name else CityFilters.BY_ALPHABET.name
                sendDataToFragment(newFilter)
            }
        }

    }

    private fun sendDataToFragment(newFilter: String) {
        parentFragmentManager.setFragmentResult(
            FirstFragment.CUR_SORTING_REQUEST_KEY,
            bundleOf(FirstFragment.CUR_SORT_TYPE_KEY to newFilter)
        )
        dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val CURRENT_FILTER = "current_filter"
        fun getInstance(currFilter: String) = FiltersBottomSheetDialog()
            .apply { arguments = bundleOf(CURRENT_FILTER to currFilter) }
    }
}
