package com.plhery.peekdeliverytracker.ui.map

import android.content.Context
import android.content.res.AssetManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.plhery.peekdeliverytracker.core.MapDetail
import com.plhery.peekdeliverytracker.core.WorldDetailPack
import com.plhery.peekdeliverytracker.core.WorldDetailStore
import java.nio.channels.FileChannel
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async

/**
 * `ParcelMapScreen`'s `@StateObject WorldDetailStore` and its `detail:` (A-S84): what an opened map shows up close,
 * rivers, lakes, built-up areas, main roads and towns, from the tiles of the bundled pack. The store is the opened
 * map's own, made as it opens and gone as it closes; the pack is read off the main thread as it first opens. Null
 * until the pack is read, as the iPhone's `tiles.pack.map { … }`; then the tiles read so far, more read off the main
 * thread as the map asks for them ([WorldMapView]'s `detail`), and the map drawn again as each one comes. The map reads
 * the tiles where it draws them, so a tile that comes composes the map again, not the screen that holds it.
 */
@Composable
internal fun rememberWorldDetail(): MapDetail? {
    val context = LocalContext.current.applicationContext
    val scope = rememberCoroutineScope()
    val store = remember { WorldDetailStore(scope, bundled = { WorldDetailPacks.load(context).await() }) }
    DisposableEffect(store) {
        WorldDetails.opened(store)
        onDispose { WorldDetails.closed(store) }
    }
    // `.task { await tiles.open() }`.
    LaunchedEffect(store) { WorldDetails.opening { store.open() } }
    val pack by store.packFlow.collectAsState()
    val need = remember(store) { { keys: List<String> -> store.need(keys) } }
    return remember(pack) { pack?.let { MapDetail(it, store.tilesFlow, need) } }
}

/**
 * `WorldDetailPack.bundled`: the pack, read once per process. The app stores it uncompressed (`noCompress`), so it is
 * mapped into memory rather than read into it, as the iPhone maps it, and only the tiles a close-up shows are ever
 * copied out of it. Where the asset can't be mapped, it is read whole.
 */
internal object WorldDetailPacks {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val busy = AtomicInteger()

    @Volatile
    private var loading: Deferred<WorldDetailPack?>? = null

    /** Whether the pack isn't being read, for tests that wait for the maps. */
    val isIdle: Boolean get() = busy.get() == 0

    fun load(context: Context): Deferred<WorldDetailPack?> = loading ?: synchronized(this) {
        loading ?: run {
            val assets = context.applicationContext.assets
            busy.incrementAndGet()
            scope.async {
                try {
                    mapped(assets) ?: WorldDetailPack.bundled()
                } finally {
                    busy.decrementAndGet()
                }
            }.also { loading = it }
        }
    }

    private fun mapped(assets: AssetManager): WorldDetailPack? = try {
        assets.openFd("peek/WorldDetail.pack").use { file ->
            file.createInputStream().use { stream ->
                // The mapping outlives the file it was mapped from.
                WorldDetailPack(stream.channel.map(FileChannel.MapMode.READ_ONLY, file.startOffset, file.length))
            }
        }
    } catch (_: Exception) {
        null
    }
}

/**
 * The opened maps' detail stores, and whether any of them is still reading its pack or a tile: for tests, which wait
 * for the tiles a map asked for before they look at it, as they wait for the maps' pictures ([MapScenes]).
 */
internal object WorldDetails {
    private val stores = LinkedHashSet<WorldDetailStore>()
    private val busy = AtomicInteger()

    val isIdle: Boolean get() = busy.get() == 0 && WorldDetailPacks.isIdle && synchronized(stores) { stores.all { it.isIdle } }

    /** The stores of the maps open now. */
    val open: List<WorldDetailStore> get() = synchronized(stores) { stores.toList() }

    fun opened(store: WorldDetailStore) = synchronized(stores) { stores.add(store) }

    fun closed(store: WorldDetailStore) = synchronized(stores) { stores.remove(store) }

    /** `open`, a store reading its pack, counted until the pack is in it. */
    suspend fun opening(open: suspend () -> Unit) {
        busy.incrementAndGet()
        try {
            open()
        } finally {
            busy.decrementAndGet()
        }
    }
}
