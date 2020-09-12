package com.anmol2805.trell.di.module

import com.anmol2805.base.domain.CoroutineSchedulers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object CoroutinesSchedulerModule {

    @Provides
    @Singleton
    @JvmStatic
    fun coroutinesSchedulers() = CoroutineSchedulers(
        default = Dispatchers.Default,
        main = Dispatchers.Main,
        io = Dispatchers.IO
    )

}