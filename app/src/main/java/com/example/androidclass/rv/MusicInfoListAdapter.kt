package com.example.androidclass.rv

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.androidclass.R
import com.example.androidclass.model.*
import com.example.androidclass.rv.diffutils.MusicItemCallback
import com.example.androidclass.rv.helper.MusicInfoComparator
import com.example.androidclass.rv.vh.ActionViewHolder
import com.example.androidclass.rv.vh.ArtistViewHolder
import com.example.androidclass.rv.vh.PlaylistViewHolder
import com.example.androidclass.rv.vh.SimpleHeaderViewHolder


class MusicInfoListAdapter :
    ListAdapter<MusicInfo, RecyclerView.ViewHolder>(MusicItemCallback()) {

    var onItemClickListener: ((Int) -> Unit)? = null
    var glide: RequestManager? = null
//    var onPlaylistImageListener: ((String) -> Unit)? = null
//    var onArtistListener: (() -> Unit)? = null

    private val comparator = MusicInfoComparator()

    override fun submitList(list: MutableList<MusicInfo>?) {
        super.submitList(list?.sortedWith(comparator))
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty() || holder !is PlaylistViewHolder) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.last().takeIf { it is Bundle }?.let {
                (holder as? PlaylistViewHolder)?.updateFields(
                    getItem(position) as Playlist,
                    it as Bundle
                )
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Artist -> R.layout.item_artist
            is Playlist -> R.layout.item_playlist
            is Action -> R.layout.item_action
            is SimpleHeader -> R.layout.item_header
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_artist -> ArtistViewHolder.create(
                parent, glide, onItemClickListener
            )
            R.layout.item_playlist -> PlaylistViewHolder.create(
                parent, glide, onItemClickListener
            )
            R.layout.item_action -> ActionViewHolder.create(parent, onItemClickListener)
            else -> SimpleHeaderViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (val item = getItem(position)) {
            is Artist -> (holder as ArtistViewHolder).bind(item)
            is Playlist -> (holder as PlaylistViewHolder).bind(item)
            is SimpleHeader -> (holder as SimpleHeaderViewHolder).bind(item)
            is Action -> (holder as ActionViewHolder).bind(item)
        }
    }

}
