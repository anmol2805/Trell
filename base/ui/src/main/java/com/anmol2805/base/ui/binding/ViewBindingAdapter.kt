package com.anmol2805.base.ui.binding

import android.content.Context
import android.net.Uri
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

@BindingAdapter("items")
fun <T> RecyclerView.setItems(items: List<T>) {
    val tempAdapter = adapter
    if (tempAdapter is BindableAdapter<*>) {
        @Suppress("UNCHECKED_CAST")
        (tempAdapter as BindableAdapter<T>).items = items
        tempAdapter.notifyDataSetChanged()
    } else {
        throw IllegalStateException("Your adapter should implement BindableAdapter")
    }
}

@BindingAdapter("initializePlayer")
fun PlayerView.initializePlayer(uri: String) {
    try {
        val player = ExoPlayerFactory.newSimpleInstance(context)
        this.player = player
        val mediaSource = buildMediaSource(Uri.parse(uri), context)
        player.playWhenReady = true
        player.prepare(mediaSource)
    } catch (e: ExoPlaybackException) {
        e.printStackTrace()
    }
}

private fun buildMediaSource(uri: Uri, context: Context): MediaSource? {
    val dataSourceFactory: DataSource.Factory =
        DefaultDataSourceFactory(context, "exoplayer-codelab")
    return ProgressiveMediaSource.Factory(dataSourceFactory)
        .createMediaSource(uri)
}

@BindingAdapter("bookmarkText")
fun Button.setBookMarkText(isBookMarked: Boolean) {
    this.text = if (isBookMarked) "Remove Bookmark" else "Bookmark"
}