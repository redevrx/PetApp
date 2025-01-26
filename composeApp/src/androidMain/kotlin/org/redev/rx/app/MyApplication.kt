package org.redev.rx.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.redev.rx.app.di.initKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MyApplication)
        }
    }
}
