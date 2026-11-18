package com.plhery.peekdeliverytracker.platform

import android.annotation.SuppressLint
import android.os.Looper
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.impl.WorkManagerImpl
import androidx.work.testing.WorkManagerTestInitHelper
import com.plhery.peekdeliverytracker.PeekApplication
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf

/**
 * A token Firebase makes in a process no window has made the stores in: kept in work of its own (never run: WorkManager's
 * test driver holds it until its network is met, so nothing leaves the machine), and handed on until the service takes it.
 */
@RunWith(AndroidJUnit4::class)
class PushTokenHandoverTest {
    private val app = ApplicationProvider.getApplicationContext<PeekApplication>()

    @Before
    fun start() {
        WorkManagerTestInitHelper.initializeTestWorkManager(app)
    }

    /** The test driver goes with its database, so the tests after these run without WorkManager. */
    @After
    @SuppressLint("RestrictedApi")
    fun stop() {
        WorkManagerTestInitHelper.closeWorkDatabase()
        WorkManagerImpl.setDelegate(null)
    }

    private fun handovers(): List<WorkInfo.State> =
        WorkManager.getInstance(app).getWorkInfosForUniqueWork(PushTokenHandover.NAME).get().map { it.state }

    @Test
    fun aTokenThatComesBeforeTheStoresIsKeptForThem() {
        assertNull(app.container.value)
        val service = Robolectric.buildService(PeekMessagingService::class.java).create().get()
        service.onNewToken("token-a")
        service.onNewToken("token-b")
        shadowOf(Looper.getMainLooper()).idle()
        // The second replaces the first: one handover waits, for the network.
        assertEquals(listOf(WorkInfo.State.ENQUEUED), handovers().filter { it == WorkInfo.State.ENQUEUED })
    }

    @Test
    fun theHandoverIsTriedAgainUntilTheServiceTakesTheToken() = runBlocking {
        val handed = mutableListOf<String>()
        assertEquals(ListenableWorker.Result.retry(), PushTokenHandover.hand("token-b") { handed += it; false })
        assertEquals(ListenableWorker.Result.success(), PushTokenHandover.hand("token-b") { handed += it; true })
        assertEquals(ListenableWorker.Result.success(), PushTokenHandover.hand(null) { handed += it; true })
        assertEquals(listOf("token-b", "token-b"), handed)
    }
}
