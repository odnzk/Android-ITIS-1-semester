package com.example.androidclass.data

import com.example.androidclass.R
import com.example.androidclass.generator.Generator
import com.example.androidclass.model.*

object MusicRepository : Repository<List<MusicInfo>> {
    private var data: MutableList<MusicInfo> = mutableListOf()

    override fun loadData(): List<MusicInfo> {
        return ArrayList(data)
    }

    override fun insertToRandomPosition(item: MusicInfo): List<MusicInfo> {
        data.add(item)
        return ArrayList(data)
    }

    fun changePlaylistImage(uuid: String): List<MusicInfo> {
        data.find { it.uuid == uuid }?.let {
            (it as Playlist).imageUrl = Generator.generateRandomUrl()
        }
        return ArrayList(data)
    }


    init {
        data.addAll(
            listOf(
                SimpleHeader("Artists"),
                Action("Albums", R.drawable.ic_baseline_album_24),
                Action("Podcasts", R.drawable.ic_baseline_podcasts_24),
                Playlist(
                    "My favorites",
                    Generator.generateSongCount(),
                    Generator.generateRandomUrl(),
                    Generator.generateDuration()
                ),
                Artist(
                    "Billie Eilish",
                    Generator.generateSongCount(),
                    Generator.generateRandomUrl(),
                    Generator.generateDuration()
                ),
                Action("Music", R.drawable.ic_baseline_music_note_24),
                Action("Download", R.drawable.ic_baseline_download_24),
                Artist(
                    "Tailor Swift",
                    Generator.generateSongCount(),
                    Generator.generateRandomUrl(),
                    Generator.generateDuration()
                ),
                Action("Music", R.drawable.ic_baseline_music_note_24),
                Artist(
                    "ITZY",
                    Generator.generateSongCount(),
                    Generator.generateRandomUrl(),
                    Generator.generateDuration()
                ),
                Action("Kids", R.drawable.ic_baseline_cruelty_free_24),
                Artist(
                    "Ariana Grande",
                    Generator.generateSongCount(),
                    Generator.generateRandomUrl(),
                    Generator.generateDuration()
                ),
                Artist(
                    "BTS",
                    Generator.generateSongCount(),
                    Generator.generateRandomUrl(),
                    Generator.generateDuration()
                ),
                Artist(
                    "BlackPink",
                    Generator.generateSongCount(),
                    Generator.generateRandomUrl(),
                    Generator.generateDuration()
                ),
                Artist(
                    "Lana del Rey",
                    Generator.generateSongCount(),
                    Generator.generateRandomUrl(),
                    Generator.generateDuration()
                ),
            )
        )
    }

}
