package com.anmol2805.home.domain

import com.anmol2805.base.domain.CoroutineSchedulers
import com.anmol2805.base.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import com.anmol2805.base.domain.Result
import com.anmol2805.home.domain.model.BookmarkRequestDraft


interface BookmarkVideo : SuspendUseCase<BookmarkRequestDraft, Unit>


class BookmarkVideoImpl @Inject constructor(
    private val videoPlayerRepository: VideoPlayerRepository,
    private val coroutineDispatcherUse: CoroutineSchedulers
): BookmarkVideo {
    override var coroutineDispatcher: CoroutineDispatcher
        get() = coroutineDispatcherUse.default
        set(value) {}

    override suspend fun execute(parameters: BookmarkRequestDraft) {
        return videoPlayerRepository.setBookMark(parameters)
    }
}

