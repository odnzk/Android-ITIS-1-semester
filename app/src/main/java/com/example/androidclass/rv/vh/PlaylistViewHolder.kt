package com.example.androidclass.rv.vh

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.androidclass.R
import com.example.androidclass.databinding.ItemPlaylistBinding
import com.example.androidclass.model.Playlist
import com.example.androidclass.rv.diffutils.MusicItemCallback
import com.example.androidclass.rv.helper.convertDurationToString
import com.example.androidclass.rv.helper.load

class PlaylistViewHolder(
    private val binding: ItemPlaylistBinding,
    private val glide: RequestManager? = null,
    private val onImageViewClickListener: ((Int) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onImageViewClickListener?.invoke(adapterPosition)
        }
    }

    fun bind(playlist: Playlist) {
        with(binding) {
            playlist.run {
                tvTitle.text = title
                glide?.let {
                    iv.load(
                        imgUrl = imageUrl,
                        glide = it,
                        progressBar = progressBar,
                    )
                }
                tvDuration.text = duration.toString()
                tvTracksCount.text =
                    itemView.context.resources.getString(R.string.count_of_tracks, songsCount)

                tvDuration.text = duration.convertDurationToString(itemView.context)

//                iv.setOnClickListener {
//                    onImageViewClickListener?.invoke(uuid)
//                }
            }
        }
    }

    fun updateFields(item: Playlist, bundle: Bundle) {
        val wasImageChanged = bundle.getBoolean(MusicItemCallback.KEY_WAS_IMAGE_CHANGED, false)
        if (wasImageChanged) {
            glide?.let {
                binding.iv.load(item.imageUrl, glide = it, progressBar = binding.progressBar)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager? = null,
            onImageViewClickListener: ((Int) -> Unit)? = null
        ): PlaylistViewHolder = PlaylistViewHolder(
            ItemPlaylistBinding
                .inflate(LayoutInflater.from(parent.context), parent, false),
            glide,
            onImageViewClickListener
        )
    }
}
