package com.example.androidclass.data

import com.example.androidclass.model.MusicInfo

interface Repository<T> {
    fun loadData(): List<MusicInfo>
    fun insertToRandomPosition(item: MusicInfo): List<MusicInfo>
}
