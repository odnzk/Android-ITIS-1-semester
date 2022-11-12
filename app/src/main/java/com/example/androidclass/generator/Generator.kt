package com.example.androidclass.generator

import com.example.androidclass.R
import com.example.androidclass.model.Action
import com.example.androidclass.model.Artist
import com.example.androidclass.model.MusicInfo
import com.example.androidclass.model.Playlist
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object Generator {
    const val TYPE_PLAYLIST = 0
    const val TYPE_ACTION = 1
    const val TYPE_ARTIST = 2

    private val imageUrlList = listOf(
        "https://www.nasa.gov/sites/default/files/thumbnails/image/uranus.jpg",
        "https://www.nasa.gov/sites/default/files/images/729223main_728322main_messenger_orbit_image20130218_2_full_full_full.jpg",
        "https://www.nasa.gov/sites/default/files/thumbnails/image/imagesvenus20191211venus20191211-16.jpeg",
        "https://www.nasa.gov/sites/default/files/thumbnails/image/stsci-h-p1936a-m-1999x2000.png"
    )

    fun generateRandomUrl() = imageUrlList[(imageUrlList.indices).random()]

    fun generateDuration() = (1..200).random().toDuration(DurationUnit.MINUTES)

    fun generateSongCount() = (1..600).random()

    fun generateRandomItem(title: String, type: Int): MusicInfo {
        return when (type) {
            0 -> Playlist(title, generateSongCount(), generateRandomUrl(), generateDuration())
            1 -> Action(title, R.drawable.ic_baseline_add_24)
            else -> Artist(title, generateSongCount(), generateRandomUrl(), generateDuration())
        }
    }

}
