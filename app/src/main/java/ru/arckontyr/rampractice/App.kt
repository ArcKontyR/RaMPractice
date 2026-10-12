package ru.arckontyr.rampractice

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import ru.arckontyr.rampractice.di.characterModule
import ru.arckontyr.rampractice.di.networkModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                networkModule,
                characterModule
            )
        }
    }
}