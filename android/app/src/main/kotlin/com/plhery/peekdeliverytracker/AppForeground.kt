package com.plhery.peekdeliverytracker

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * The app in front or left (`scenePhase`), which the stores follow ([AppContainer.start]): started while one of the
 * app's windows is, stopped once none is. The share card ([ShareActivity]) isn't one of them: the iPhone runs its share
 * extension in a process of its own, over the app that shared, and the app's scene stays as it was, in front or not.
 * As ProcessLifecycleOwner, it stops only [LEAVE_DELAY] ms after the last window stopped, so a window made again (a
 * turn, a foldable opened) isn't the app leaving and coming back. It is started and stopped alone, never resumed.
 */
internal class AppForeground : LifecycleOwner, Application.ActivityLifecycleCallbacks {
    private val registry = LifecycleRegistry(this).apply { currentState = Lifecycle.State.CREATED }
    override val lifecycle: Lifecycle get() = registry

    private val handler = Handler(Looper.getMainLooper())

    /** The app's windows started and not yet stopped. */
    private var started = 0

    private val leave = Runnable {
        if (started == 0 && registry.currentState.isAtLeast(Lifecycle.State.STARTED)) registry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    override fun onActivityStarted(activity: Activity) {
        if (activity is ShareActivity) return
        started += 1
        handler.removeCallbacks(leave)
        if (!registry.currentState.isAtLeast(Lifecycle.State.STARTED)) registry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onActivityStopped(activity: Activity) {
        if (activity is ShareActivity || started == 0) return
        started -= 1
        if (started == 0) handler.postDelayed(leave, LEAVE_DELAY)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) = Unit

    override fun onActivityResumed(activity: Activity) = Unit

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit

    companion object {
        /** ProcessLifecycleOwner's `TIMEOUT_MS`. */
        const val LEAVE_DELAY = 700L
    }
}
