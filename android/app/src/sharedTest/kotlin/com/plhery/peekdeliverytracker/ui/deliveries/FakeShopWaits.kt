package com.plhery.peekdeliverytracker.ui.deliveries

import com.plhery.peekdeliverytracker.core.ShopWaitsClient
import com.plhery.peekdeliverytracker.core.api.ShopPlatform
import com.plhery.peekdeliverytracker.core.api.ShopWait
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred

/**
 * Shop waits as a test sets them: each read answers [waits], or fails with [failure]; a removal waits for [removing]
 * when a test holds it, then fails with [removeFailure] if a test sets one, or takes the wait out of [waits].
 * [requests] notes what was asked, in order. Nothing leaves the test.
 */
class FakeShopWaitsClient : ShopWaitsClient {
    var waits: List<ShopWait> = emptyList()
    var failure: Exception? = null
    var removeFailure: Exception? = null
    var removing: CompletableDeferred<Unit>? = null
    val requests = mutableListOf<String>()

    override suspend fun list(): List<ShopWait> {
        requests += "list"
        failure?.let { throw it }
        return waits
    }

    override suspend fun remove(id: UUID) {
        requests += "remove $id"
        removing?.await()
        removeFailure?.let { throw it }
        waits = waits.filterNot { it.id == id }
    }
}

/** Synthetic waits at the tests' fixed moment (2026-10-29 10:11 UTC). */
object ShopWaitFixtures {
    /** A shop that named itself, asked about until Wed 11 Nov. */
    val coat = ShopWait(
        id = UUID.fromString("3a1e0c3a-7b2d-4c8e-9a61-0d4b8e2f7c15"), platform = ShopPlatform.Parcellab, platformName = "parcelLab",
        shopName = "Example Shop", label = "Wool coat", createdAt = "2026-10-28T10:11:00.000Z", expiresAt = "2026-11-11T10:11:00.000Z",
    )

    /** One whose 14 days passed without a parcel. */
    val expired = ShopWait(
        id = UUID.fromString("4b1e0c3a-7b2d-4c8e-9a61-0d4b8e2f7c15"), platform = ShopPlatform.Parcellab, platformName = "parcelLab",
        shopName = "Example Shop", label = "Boots", createdAt = "2026-10-14T09:00:00.000Z", expiresAt = "2026-10-28T09:00:00.000Z",
        expiredAt = "2026-10-28T09:00:00.000Z",
    )
}
