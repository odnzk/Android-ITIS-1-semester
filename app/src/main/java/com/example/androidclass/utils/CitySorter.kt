package com.example.androidclass.utils

import com.example.androidclass.enum.CityFilters
import com.example.androidclass.model.City

object CitySorter {

    fun sort(list: List<City>, filter: String): List<City> {
        return when (filter) {
            CityFilters.BY_ID.name -> sortById(list)
            CityFilters.BY_ALPHABET.name -> sortByAlphabet(list)
            CityFilters.BY_ID_DESC.name -> sortByIdDesc(list)
            CityFilters.BY_ALPHABET_DESC.name -> sortByAlphabetDesc(list)
            else -> emptyList()
        }
    }

    private fun sortByAlphabet(list: List<City>): List<City> {
        return list.sortedBy { it.name.first() }
    }

    private fun sortByAlphabetDesc(list: List<City>): List<City> {
        return list.sortedByDescending { it.name.first() }
    }

    private fun sortById(list: List<City>): List<City> {
        return list.sortedBy { it.id }
    }

    private fun sortByIdDesc(list: List<City>): List<City> {
        return list.sortedByDescending { it.id }
    }
}
