package com.plhery.peekdeliverytracker.ui.map

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import com.plhery.peekdeliverytracker.core.EdgeInsets
import com.plhery.peekdeliverytracker.core.MapLabels
import com.plhery.peekdeliverytracker.core.MapPalette
import com.plhery.peekdeliverytracker.core.ParcelRoute
import com.plhery.peekdeliverytracker.core.PipMood
import com.plhery.peekdeliverytracker.core.PipRequest
import com.plhery.peekdeliverytracker.core.Rect
import com.plhery.peekdeliverytracker.core.WorldAtlas
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async

/**
 * `RouteEngraving` (A-S82): the route in the card's own ink across the top of a parcel's card, the names of its ends,
 * the parcel's dot pulsing until the journey is over, and Pip beside it, kept below `ceiling` (the card's top row) and
 * above `floor` (where the card writes over the bottom of the map). `covered` is what the card writes over the map
 * below its top row, `buttons` its buttons over the top, and `room` how much lower the route starts below a second
 * carrier's mark. With `peek`, two fingers zoom it closer until they lift (A-G13); off on Next up, which is one tap
 * target in a scrolling list. Decorative.
 */
@Composable
fun RouteEngraving(
    atlas: WorldAtlas,
    route: ParcelRoute,
    stage: TrackingStage?,
    identity: CarrierVisualIdentity,
    language: AppLanguage,
    modifier: Modifier = Modifier,
    insets: EdgeInsets = EdgeInsets(top = 40.0, leading = 16.0, bottom = 44.0, trailing = 16.0),
    ceiling: Double = 52.0,
    floor: Double? = null,
    covered: Rect? = null,
    buttons: Rect? = null,
    room: Double = 0.0,
    peek: Boolean = true,
    entrance: FiniteAnimationSpec<Float>? = null,
) {
    WorldMapView(
        atlas = atlas, route = route, mode = route.defaultMode(stage),
        palette = remember(identity) { MapPalette.tint(identity.ink, identity.surface) },
        modifier = modifier, labels = MapLabels.Ends, showsContext = false, peek = peek,
        // The parcel's dot pulses until the journey is over.
        live = stage != TrackingStage.Delivered && stage != TrackingStage.Returned,
        // Pip keeps below the top row of the parcel's page, and of Next up alike, so both show the same picture.
        pip = PipMood(stage)?.let { PipRequest(it, ceiling = ceiling, floor = floor) },
        fades = true, insets = insets.copy(top = insets.top + room), covered = covered, buttons = buttons, language = language,
        entrance = entrance,
    )
}

object RouteEngravingMetrics {
    /**
     * `RouteEngraving.handoverRoom`: a second carrier's mark adds a line to the top of a card with a map. The card grows
     * by two lines, and its route starts that much lower, so it keeps the room it has on any other card. Pip keeps his
     * ceiling: beside the parcel's dot, he only keeps off the mark.
     */
    const val HANDOVER_ROOM: Double = 40.0
}

/**
 * `CardRoute` (A-S83): a parcel's whole journey, small, at the end of its card. It belongs to the card's picture and is
 * not there to be read: no names, no Pip, no pulse, and a line and dots as pale as the map they lie on. `surface` is
 * the card's own colour, paler on a past delivery. Decorative and takes no touches.
 */
@Composable
fun CardRoute(
    atlas: WorldAtlas,
    route: ParcelRoute,
    ink: AdaptiveColor,
    surface: AdaptiveColor,
    language: AppLanguage,
    modifier: Modifier = Modifier,
    entrance: FiniteAnimationSpec<Float>? = null,
) {
    WorldMapView(
        atlas = atlas, route = route, mode = ParcelRoute.Mode.Journey,
        palette = remember(ink, surface) { MapPalette.quietTint(ink, surface) }, modifier = modifier,
        labels = MapLabels.None, showsContext = false, fadesIn = true, quiet = true,
        // Room for the date in the card's top row, and for the land to fade in from under the words.
        insets = EdgeInsets(top = 36.0, leading = 78.0, bottom = 16.0, trailing = 20.0), language = language,
        entrance = entrance,
    )
}

object CardRouteMetrics {
    /** How wide the picture is: the same on every card, however wide the card. */
    fun width(card: Dp): Dp = minOf(card * 0.58f, 210.dp)

    /** How far from the card's trailing edge its words end, so they keep clear of the route. */
    val clearance: Dp = 120.dp
}

/**
 * The bundled atlas once it is read (`WorldAtlas.bundled`), read off the main thread the first time a map needs it;
 * null meanwhile and when `wanted` is false.
 */
@Composable
fun rememberWorldAtlas(wanted: Boolean = true): WorldAtlas? {
    if (!wanted) return null
    val atlas by produceState(WorldAtlases.loadedOrNull()) {
        if (value == null) MapScenes.awaiting { value = WorldAtlases.load().await() }
    }
    return atlas
}

/** The atlas, read once per process, and whether it is being read, for tests that wait for the maps. */
internal object WorldAtlases {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val busy = AtomicInteger()

    @Volatile
    private var loading: Deferred<WorldAtlas?>? = null

    val isIdle: Boolean get() = busy.get() == 0

    fun load(): Deferred<WorldAtlas?> = loading ?: synchronized(this) {
        loading ?: run {
            busy.incrementAndGet()
            scope.async {
                try {
                    WorldAtlas.bundled()
                } finally {
                    busy.decrementAndGet()
                }
            }.also { loading = it }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadedOrNull(): WorldAtlas? = load().let { if (it.isCompleted) it.getCompleted() else null }
}

/**
 * `ParcelRoute(parcel:atlas:language:)`, the same object for as long as the parcel's scans, its destination and the
 * language stay the same, across cards and recompositions, so its map's picture is found again ([MapScenes]).
 */
@Composable
fun rememberParcelRoute(parcel: Parcel, atlas: WorldAtlas, language: AppLanguage): ParcelRoute {
    val key = ParcelRoutes.Key(parcel.trackingEvents, parcel.carrierData?.destinationCountry, language, atlas)
    return remember(key) { ParcelRoutes.of(key, parcel) }
}

internal object ParcelRoutes {
    data class Key(val events: List<TrackingEvent>, val destination: String?, val language: AppLanguage, val atlas: WorldAtlas)

    private const val KEPT = 64

    private val routes = object : LinkedHashMap<Key, ParcelRoute>(KEPT, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Key, ParcelRoute>?): Boolean = size > KEPT
    }

    fun of(key: Key, parcel: Parcel): ParcelRoute = synchronized(routes) {
        routes.getOrPut(key) { ParcelRoute(parcel, key.atlas, key.language) }
    }
}
