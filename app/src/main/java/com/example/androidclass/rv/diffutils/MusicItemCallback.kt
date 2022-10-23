package com.example.androidclass.rv.diffutils

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.androidclass.model.MusicInfo
import com.example.androidclass.model.Playlist

class MusicItemCallback : DiffUtil.ItemCallback<MusicInfo>() {

    companion object {
        const val KEY_WAS_IMAGE_CHANGED = "was image changed"
    }

    override fun areItemsTheSame(oldItem: MusicInfo, newItem: MusicInfo): Boolean {
        return oldItem.uuid == newItem.uuid && oldItem::class == newItem::class
    }

    override fun areContentsTheSame(oldItem: MusicInfo, newItem: MusicInfo) = oldItem == newItem

    override fun getChangePayload(oldItem: MusicInfo, newItem: MusicInfo): Any? {
        if (oldItem is Playlist && newItem is Playlist) {
            if (oldItem.imageUrl != newItem.imageUrl) {
                return Bundle().apply {
                    putBoolean(KEY_WAS_IMAGE_CHANGED, true)
                }
            }
        }
        return null
    }
}
