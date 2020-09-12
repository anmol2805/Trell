package com.anmol2805.trell.di

import android.app.Application
import androidx.annotation.Keep
import com.anmol2805.base.data.di.BaseDataModule
import com.anmol2805.base.domain.di.BaseDomainModule
import com.anmol2805.base.ui.di.BaseUiModule
import com.anmol2805.base.ui.di.ViewModelFactoryModule
import com.anmol2805.home.data.di.HomeDataModule
import com.anmol2805.home.domain.di.HomeDomainModule
import com.anmol2805.home.ui.di.HomeUiModule
import com.anmol2805.trell.TrellApp
import com.anmol2805.trell.di.module.CoroutinesSchedulerModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        CoroutinesSchedulerModule::class,
        ViewModelFactoryModule::class,
        BaseDataModule::class,
        BaseDomainModule::class,
        BaseUiModule::class,
        HomeDataModule::class,
        HomeDomainModule::class,
        HomeUiModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<TrellApp> {

    @Keep
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): ApplicationComponent
    }
}