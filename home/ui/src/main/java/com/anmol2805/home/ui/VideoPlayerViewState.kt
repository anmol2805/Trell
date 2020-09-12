package com.anmol2805.home.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.anmol2805.base.ui.binding.bind
import com.anmol2805.home.domain.model.VideoDetails

class VideoPlayerViewState(
    initVideos: List<VideoDetails> = emptyList()
) : BaseObservable() {
    @get:Bindable
    var videosList by bind(BR.videosList, initVideos)
}