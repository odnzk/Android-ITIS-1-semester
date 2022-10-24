package com.example.androidclass.rv.helper

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.androidclass.R
import kotlin.time.Duration


fun Duration.convertDurationToString(context: Context): String {
    val minutes = this.inWholeMinutes.toInt()
    return context.resources.getQuantityString(R.plurals.count_minutes, minutes, minutes)
}

fun ImageView.load(
    imgUrl: String,
    glide: RequestManager,
    progressBar: ProgressBar
) {
    glide.load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
        .listener(object : RequestListener<Drawable?> {
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable?>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.isVisible = false
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable?>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        }).into(this)
}

