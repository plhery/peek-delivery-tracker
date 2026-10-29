package com.plhery.peekdeliverytracker

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.plhery.peekdeliverytracker.core.SharedResources
import java.io.FileNotFoundException
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/** The process: :core reads the shared data (brand, catalog, copy, map) from the app's assets. */
class PeekApplication : Application() {
    private val containerState = MutableStateFlow<AppContainer?>(null)

    /** The app's stores once made and started ([makeContainer]); null until then. */
    val container: StateFlow<AppContainer?> = containerState.asStateFlow()

    private var making = false

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

    /**
     * Makes the app's stores off the main thread and starts them on it: once, when the first window asks, as the
     * iPhone makes its stores with the app, and not for every test's process. On the main thread.
     */
    fun makeContainer() {
        if (making) return
        making = true
        MainScope().launch {
            val container = AppContainer.make(this@PeekApplication)
            container.start(ProcessLifecycleOwner.get().lifecycle)
            containerState.value = container
        }
    }
}
