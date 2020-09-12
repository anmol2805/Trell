package com.anmol2805.home.domain.di

import com.anmol2805.home.domain.BookmarkVideo
import com.anmol2805.home.domain.BookmarkVideoImpl
import com.anmol2805.home.domain.GetVideos
import com.anmol2805.home.domain.GetVideosImpl
import dagger.Binds
import dagger.Module

@Module
abstract class HomeDomainModule {
    @Binds
    abstract fun getVideos(getVideos: GetVideosImpl): GetVideos

    @Binds
    abstract fun bookmarkVideo(bookmarkVideo: BookmarkVideoImpl): BookmarkVideo
}