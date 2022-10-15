package com.example.androidclass.model.ui

import com.example.androidclass.model.domain.Planet

data class UiPlanet(val planet: Planet, var hasBeenVisited: Boolean = false) : PlanetInfo()
