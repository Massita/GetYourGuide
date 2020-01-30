package com.massita.getyourguide

import android.app.Application
import com.massita.getyourguide.di.dataModule
import com.massita.getyourguide.di.serviceModule
import com.massita.getyourguide.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(serviceModule, dataModule, viewModelModule))
        }
    }
}