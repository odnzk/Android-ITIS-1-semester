package com.example.androidclass.rv.helper

import com.example.androidclass.model.*


class MusicInfoComparator : Comparator<MusicInfo> {

    companion object {
        private const val PLAYLIST_ORDER = 0
        private const val ACTION_ORDER = 1
        private const val ARTIST_HEADER_ORDER = 2
        private const val ARTIST_ORDER = 3
    }

    override fun compare(o1: MusicInfo, o2: MusicInfo): Int {
        // playlist -> action -> artist header -> artist
        val order1 = order(o1)
        val order2 = order(o2)
        return order1.compareTo(order2)
    }

    private fun order(musicInfo: MusicInfo): Int {
        return when (musicInfo) {
            is Playlist -> PLAYLIST_ORDER
            is Action -> ACTION_ORDER
            is Artist -> ARTIST_ORDER
            is SimpleHeader -> ARTIST_HEADER_ORDER
        }
    }
}
