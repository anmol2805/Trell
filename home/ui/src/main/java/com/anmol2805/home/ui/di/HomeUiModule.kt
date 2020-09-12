package com.anmol2805.home.ui.di

import androidx.lifecycle.ViewModel
import com.anmol2805.base.ui.di.ViewModelKey
import com.anmol2805.home.ui.VideoPlayerFragment
import com.anmol2805.home.ui.VideoPlayerViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class HomeUiModule {
    @ContributesAndroidInjector
    abstract fun videoPlayerFragment(): VideoPlayerFragment

    @Binds
    @IntoMap
    @ViewModelKey(VideoPlayerViewModel::class)
    abstract fun videoPlayerViewModel(viewModel: VideoPlayerViewModel): ViewModel
}