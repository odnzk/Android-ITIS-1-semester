package com.example.androidclass.rv.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.androidclass.R
import com.example.androidclass.databinding.ItemArtistBinding
import com.example.androidclass.model.Artist
import com.example.androidclass.rv.helper.convertDurationToString
import com.example.androidclass.rv.helper.load

class ArtistViewHolder(
    private val binding: ItemArtistBinding,
    private val glide: RequestManager? = null,
    private val onArtistClickListener: ((Int) -> Unit)? = null
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onArtistClickListener?.invoke(adapterPosition)
        }
    }

    fun bind(artist: Artist) {
        with(binding) {
            itemArtist.run {
                tvTitle.text = artist.title
                tvDuration.text =
                    artist.duration.convertDurationToString(itemView.context)

                glide?.let {
                    itemArtist.iv.load(
                        imgUrl = artist.imageUrl,
                        glide = it,
                        progressBar = itemArtist.progressBar
                    )
                }

                tvTracksCount.text = itemView.context.resources.getString(R.string.count_of_tracks, artist.songsCount)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager? = null,
            onArtistListener: ((Int) -> Unit)? = null
        ): ArtistViewHolder = ArtistViewHolder(
            ItemArtistBinding
                .inflate(LayoutInflater.from(parent.context), parent, false),
            glide,
            onArtistListener
        )
    }
}
