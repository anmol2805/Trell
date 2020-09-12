package com.anmol2805.trell

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.anmol2805.trell.di.DaggerApplicationComponent


class TrellApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<TrellApp> {
        return DaggerApplicationComponent.factory()
            .create(this)
    }
}