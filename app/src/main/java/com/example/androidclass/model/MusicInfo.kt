package com.example.androidclass.model

import androidx.annotation.DrawableRes
import java.util.*
import kotlin.time.Duration

sealed class MusicInfo(val uuid: String) // id for diff utils comparison
data class Action(
    val title: String,
    @DrawableRes val iconResId: Int
) : MusicInfo(UUID.randomUUID().toString())

data class Artist(
    val title: String,
    val songsCount: Int,
    val imageUrl: String,
    val duration: Duration,
) : MusicInfo(UUID.randomUUID().toString())

data class Playlist(
    val title: String,
    val songsCount: Int,
    var imageUrl: String,
    val duration: Duration
) : MusicInfo(UUID.randomUUID().toString())

class SimpleHeader(val title: String) : MusicInfo(UUID.randomUUID().toString())
