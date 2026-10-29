package com.plhery.peekdeliverytracker

import android.app.Application
import com.plhery.peekdeliverytracker.core.SharedResources
import java.io.FileNotFoundException

/** The process: :core reads the shared data (brand, catalog, copy, map) from the app's assets. */
class PeekApplication : Application() {
    /** The app's stores, made and started when the first window asks for them, not for every test's process. */
    val container: AppContainer by lazy { AppContainer(this).also(AppContainer::start) }

    override fun onCreate() {
        super.onCreate()
        val assets = assets
        SharedResources.install { name ->
            try {
                assets.open("peek/$name")
            } catch (_: FileNotFoundException) {
                null
            }
        }
    }
}
