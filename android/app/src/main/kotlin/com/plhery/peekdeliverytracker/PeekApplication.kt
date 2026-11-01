package com.plhery.peekdeliverytracker

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ProcessLifecycleOwner
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.SharedResources
import com.plhery.peekdeliverytracker.core.UserDefaults
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
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

    /** How the stores are made: [AppContainer.make], or a test's own, set before the first window asks for them. */
    @VisibleForTesting
    internal var makeStores: suspend (PeekApplication) -> AppContainer = { AppContainer.make(it) }

    /**
     * Where what another app shared waits for Add (`ShareInbox.defaults`): the app's own defaults, which [ShareActivity]
     * saves to, reading the app's language there, and the window takes from; or a test's.
     */
    @VisibleForTesting
    internal var shareDefaults: () -> UserDefaults = { PreferencesDefaults.standard(this) }

    /** A parcel's link that came before the stores were made, which opens once they are. */
    private var waitingLink: ParcelLinkRoute? = null

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
            val container = makeStores(this@PeekApplication)
            container.start(ProcessLifecycleOwner.get().lifecycle)
            waitingLink?.let(container.links::open)
            waitingLink = null
            containerState.value = container
        }
    }

    /**
     * `open(_:)`'s first route (A-L02): a parcel's link, on the site (`https://<the site>/p/<id>`, or a host it
     * answered on before) or in the app's scheme (`peekdeliverytracker://p/<id>`), opens its sheet over whatever is up,
     * signed in or not; false for any other address. [ParcelLinkActivity] hands each one here. One that comes before
     * the stores are made waits for them, in the process rather than in a window's saved state, so the owner key a
     * handed-over link carries is never written down; it goes with the process, as the iPhone's does. On the main thread.
     */
    fun openParcelLink(url: String): Boolean {
        val configuration = BuildSettings.configuration
        val route = ParcelLinkRoute(url, configuration.apiBaseUrl, configuration.linkHosts) ?: return false
        val container = container.value
        if (container != null) container.links.open(route) else waitingLink = route
        return true
    }
}
