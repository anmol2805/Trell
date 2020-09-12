package com.anmol2805.home.domain

import com.anmol2805.base.domain.CoroutineSchedulers
import com.anmol2805.base.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import com.anmol2805.base.domain.Result


interface GetVideos : SuspendUseCase<Unit, Result<VideosResponse>>


class GetVideosImpl @Inject constructor(
    private val videoPlayerRepository: VideoPlayerRepository,
    private val coroutineDispatcherUse: CoroutineSchedulers
): GetVideos {
    override var coroutineDispatcher: CoroutineDispatcher
        get() = coroutineDispatcherUse.default
        set(value) {}

    override suspend fun execute(parameters: Unit): Result<VideosResponse> {
        return videoPlayerRepository.fetchVideos()
    }
}

