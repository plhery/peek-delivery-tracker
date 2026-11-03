package com.plhery.peekdeliverytracker.ui

import android.util.Log
import androidx.compose.ui.test.ComposeTimeoutException
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.printToString
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage

/**
 * Waits as [ComposeTestRule.waitUntil] does; on a timeout, first logs under [logTag] the activities resumed and every
 * window's semantics tree, so a device run's log shows what stood there instead.
 */
internal fun ComposeTestRule.waitUntilOrLogTree(logTag: String, millis: Long, condition: () -> Boolean) {
    try {
        waitUntil(millis, condition)
    } catch (timeout: ComposeTimeoutException) {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            val resumed = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
            Log.i(logTag, "Resumed: ${resumed.joinToString { it.javaClass.name }}")
        }
        runCatching { onAllNodes(isRoot()).printToString(maxDepth = Int.MAX_VALUE) }
            .onSuccess { tree -> tree.chunked(3_000).forEach { Log.i(logTag, it) } }
            .onFailure { Log.i(logTag, "No tree: $it") }
        throw timeout
    }
}
