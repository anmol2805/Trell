package com.anmol2805.home.domain

import com.anmol2805.base.domain.Result
import com.anmol2805.home.domain.model.BookmarkRequestDraft

interface VideoPlayerRepository {
    suspend fun fetchVideos() : Result<VideosResponse>
    suspend fun setBookMark(bookmarkRequestDraft: BookmarkRequestDraft)
}