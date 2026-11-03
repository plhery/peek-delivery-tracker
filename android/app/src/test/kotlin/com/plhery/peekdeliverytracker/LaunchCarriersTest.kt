package com.plhery.peekdeliverytracker

import android.os.Looper
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.asExecutor
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

/**
 * The carriers' brand data, which the first window waits for with the stores (LaunchFramesTest), is decoded while the
 * stores are made rather than once they are: the launch window stays for the longer of the two, not for both in turn.
 */
@RunWith(AndroidJUnit4::class)
@Config(qualifiers = PHONE)
class LaunchCarriersTest {
    private val app = ApplicationProvider.getApplicationContext<PeekApplication>()

    /** The stores, made only when the test says. */
    private val stores = CompletableDeferred<AppContainer>()

    /** How many times something was set to decode on [CarrierIdentities.decoding]. */
    private val decodes = AtomicInteger()

    @Before
    fun counted() {
        app.makeStores = { stores.await() }
        val io = Dispatchers.IO.asExecutor()
        CarrierIdentities.decoding = Executor { work -> decodes.incrementAndGet(); io.execute(work) }.asCoroutineDispatcher()
    }

    @After
    fun stop() {
        stores.cancel()
        shadowOf(Looper.getMainLooper()).idle()
        CarrierIdentities.decoding = Dispatchers.IO
    }

    @Test
    fun theBrandDataIsDecodedWhileTheStoresAreMade() {
        app.makeContainer()
        shadowOf(Looper.getMainLooper()).idle()
        assertNull("The stores are still being made", app.container.value)
        assertEquals("The brand data is being decoded meanwhile", 1, decodes.get())
    }
}
