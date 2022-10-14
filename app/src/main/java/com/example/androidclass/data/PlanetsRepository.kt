package com.example.androidclass.data

import com.example.androidclass.model.domain.Planet
import com.example.androidclass.model.ui.PlanetCategory

object PlanetsRepository {
    private var planets: MutableList<Any> = mutableListOf()

    init {
        planets.addAll(
            arrayOf(
                PlanetCategory("Solar System"),
                Planet(
                    "Mercury",
                    "Mercury—the smallest planet in our solar system and closest to the Sun—is only slightly larger than Earth's Moon. Mercury is the fastest planet, zipping around the Sun every 88 Earth days.",
                    "https://www.nasa.gov/sites/default/files/images/729223main_728322main_messenger_orbit_image20130218_2_full_full_full.jpg"
                ),
                Planet(
                    "Venus",
                    "Venus spins slowly in the opposite direction from most planets. A thick atmosphere traps heat in a runaway greenhouse effect, making it the hottest planet in our solar system.",
                    "https://www.nasa.gov/sites/default/files/thumbnails/image/imagesvenus20191211venus20191211-16.jpeg"
                ),
                Planet(
                    "Earth",
                    "Earth—our home planet—is the only place we know of so far that’s inhabited by living things. It's also the only planet in our solar system with liquid water on the surface.",
                    "https://www.nasa.gov/sites/default/files/thumbnails/image/2022-05_geocolor_20220505180018_logos.png"
                ),
                Planet(
                    "Mars",
                    "Mars is a dusty, cold, desert world with a very thin atmosphere. There is strong evidence Mars was—billions of years ago—wetter and warmer, with a thicker atmosphere.",
                    "https://media.cnn.com/api/v1/images/stellar/prod/181115180453-01-mars-best-moments-mars-globe-valles-marineris-enhanced.jpg"
                ),
                Planet(
                    "Jupiter",
                    "Jupiter is more than twice as massive than the other planets of our solar system combined. The giant planet's Great Red spot is a centuries-old storm bigger than Earth.",
                    "https://www.nasa.gov/sites/default/files/thumbnails/image/stsci-h-p1936a-m-1999x2000.png"
                ),
                Planet(
                    "Saturn",
                    "Adorned with a dazzling, complex system of icy rings, Saturn is unique in our solar system. The other giant planets have rings, but none are as spectacular as Saturn's.",
                    "https://www.nasa.gov/sites/default/files/thumbnails/image/edu_ring_a-round_the_saturn.jpg"
                ),
                Planet(
                    "Uranus",
                    "Uranus—seventh planet from the Sun—rotates at a nearly 90-degree angle from the plane of its orbit. This unique tilt makes Uranus appear to spin on its side.",
                    "https://www.nasa.gov/sites/default/files/thumbnails/image/uranus.jpg"
                ),
                Planet(
                    "Neptune",
                    "Neptune—the eighth and most distant major planet orbiting our Sun—is dark, cold and whipped by supersonic winds. It was the first planet located through mathematical calculations, rather than by telescope.",
                    "https://en.wikipedia.org/wiki/Neptune#/media/File:Neptune_-_Voyager_2_(29347980845)_flatten_crop.jpg"
                ),
                PlanetCategory("Beyond the Solar System"),
                Planet(
                    "Mercury2",
                    "Mercury—the smallest planet in our solar system and closest to the Sun—is only slightly larger than Earth's Moon. Mercury is the fastest planet, zipping around the Sun every 88 Earth days.",
                    "https://www.nasa.gov/sites/default/files/images/729223main_728322main_messenger_orbit_image20130218_2_full_full_full.jpg"
                ),
                Planet(
                    "Venus2",
                    "Venus spins slowly in the opposite direction from most planets. A thick atmosphere traps heat in a runaway greenhouse effect, making it the hottest planet in our solar system.",
                    "https://www.nasa.gov/sites/default/files/thumbnails/image/imagesvenus20191211venus20191211-16.jpeg"
                ),
                Planet(
                    "Earth2",
                    "Earth—our home planet—is the only place we know of so far that’s inhabited by living things. It's also the only planet in our solar system with liquid water on the surface.",
                    "https://www.nasa.gov/sites/default/files/thumbnails/image/2022-05_geocolor_20220505180018_logos.png"
                ),
                Planet(
                    "Mars2",
                    "Mars is a dusty, cold, desert world with a very thin atmosphere. There is strong evidence Mars was—billions of years ago—wetter and warmer, with a thicker atmosphere.",
                    "https://media.cnn.com/api/v1/images/stellar/prod/181115180453-01-mars-best-moments-mars-globe-valles-marineris-enhanced.jpg"
                ),
                Planet(
                    "Jupiter2",
                    "Jupiter is more than twice as massive than the other planets of our solar system combined. The giant planet's Great Red spot is a centuries-old storm bigger than Earth.",
                    "https://www.nasa.gov/sites/default/files/thumbnails/image/stsci-h-p1936a-m-1999x2000.png"
                )

            )
        )
    }

    fun getPlanets(): List<Any> {
        return planets
    }

    fun getPlanet(title: String): Planet {
        return planets.filterIsInstance<Planet>().first { it.title == title }
    }

}
