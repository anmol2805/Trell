package com.anmol2805.home.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.viewModelScope
import com.anmol2805.base.domain.data
import com.anmol2805.base.ui.base.BaseDaggerViewModel
import com.anmol2805.home.domain.BookmarkVideo
import com.anmol2805.home.domain.GetVideos
import com.anmol2805.home.domain.model.BookmarkRequestDraft
import com.anmol2805.home.domain.model.VideoDetails
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideoPlayerViewModel @Inject constructor(
    private val getVideos: GetVideos,
    private val bookmarkVideo: BookmarkVideo
) : BaseDaggerViewModel() {

    val viewState = VideoPlayerViewState()

    fun fetchVideos() {
        viewModelScope.launch {
            val response = getVideos(Unit).data
            val videos = response?.data
            response?.data?.let {
                Log.e("videos", it.toString())
                viewState.videosList = it.videos
            }
        }
    }

    fun bookMarkVideo(videoDetails: VideoDetails) {
        viewModelScope.launch {
            bookmarkVideo(
                BookmarkRequestDraft(
                    videoDetails.videoUri,
                    videoDetails.isBookMarked.not()
                )
            ).data
        }
    }
}