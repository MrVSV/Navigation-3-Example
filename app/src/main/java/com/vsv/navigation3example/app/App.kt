package com.vsv.navigation3example.app

import android.app.Application
import com.vsv.navigation3example.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(level = Level.WARNING)
            androidContext(androidContext = this@App)
            modules(appModule)
        }
    }
}