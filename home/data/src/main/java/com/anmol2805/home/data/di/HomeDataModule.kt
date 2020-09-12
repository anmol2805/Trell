package com.anmol2805.home.data.di


import com.anmol2805.home.data.VideoPlayerRepositoryImpl
import com.anmol2805.home.domain.VideoPlayerRepository
import dagger.Binds
import dagger.Module

@Module
abstract class HomeDataModule {
    @Binds
    abstract fun videoPlayerRepository(videoPlayerRepository: VideoPlayerRepositoryImpl): VideoPlayerRepository
}