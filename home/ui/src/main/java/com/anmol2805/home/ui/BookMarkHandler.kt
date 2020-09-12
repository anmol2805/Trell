package com.anmol2805.home.ui

import android.view.View
import android.widget.Button
import com.anmol2805.home.domain.model.VideoDetails

class BookMarkHandler {
    fun onBookMark(view: View, videoDetails: VideoDetails, viewModel: VideoPlayerViewModel) {
        viewModel.bookMarkVideo(videoDetails)
        val bookmark: Button = view as Button
        bookmark.text = if (videoDetails.isBookMarked) "Bookmark" else "Remove Bookmark"
    }
}