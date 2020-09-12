package com.anmol2805.base.ui.di

import androidx.lifecycle.ViewModelProvider
import com.anmol2805.base.ui.base.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}