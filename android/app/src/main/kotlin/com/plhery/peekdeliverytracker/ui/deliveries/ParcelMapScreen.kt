package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.EdgeInsets
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.MapPalette
import com.plhery.peekdeliverytracker.core.ParcelRoute
import com.plhery.peekdeliverytracker.core.PipMood
import com.plhery.peekdeliverytracker.core.PipRequest
import com.plhery.peekdeliverytracker.core.RoutePlace
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.core.WorldAtlas
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.map.LocalMapsAtOnce
import com.plhery.peekdeliverytracker.ui.map.WorldMapView
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.tabs.WindowHinge
import com.plhery.peekdeliverytracker.ui.tabs.WindowLevelHinge
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.monospacedDigit
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import kotlin.math.max

/**
 * `ParcelMapScreen` (A-S84): the whole window over the parcel, the journey on a globe or the last mile up close. The
 * map fills the window, in the carrier's colour (`accent`), names a facility as such, shades the night side as it was
 * when it opened, pulses at the parcel's place until the journey is over, and keeps the route and Pip clear of the
 * summary, the close button and the system's bars ([MapCoverLayout]). Over it, on glass: the summary at the foot, no
 * wider than 420, with the ends of the journey, how far it came, has to go and through how many countries, and the
 * switch between the journey and close by where both differ; and the close button at the top trailing corner. At the
 * accessibility sizes the ends stand one under the other, names wrap, and the summary scrolls past half the window's
 * height. Beside an upright hinge the summary keeps to the second part of the window and the route to the first; with
 * a level hinge (`levelHinge`), the route keeps above it and the summary under it.
 *
 * The map turns under a finger and zooms under two (A-G12), and flies between the views (A-AN46). Once moved, neither
 * view is selected, and either brings it back; the view shown brings it back too (A-G14). TalkBack hears the map as
 * one image named by its ends, as VoiceOver does, with nothing to adjust, reaches it through `mapFocus`, and hears the
 * views as a group, "Map view" as it comes to them, the one shown selected. The detail close up (rivers, roads and
 * towns from the detail tiles) comes with its step.
 */
@Composable
internal fun ParcelMapScreen(
    atlas: WorldAtlas,
    route: ParcelRoute,
    stage: TrackingStage?,
    accent: AdaptiveColor,
    hinge: WindowHinge?,
    levelHinge: WindowLevelHinge?,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    mapFocus: AccessibilityFocus? = null,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val density = LocalDensity.current
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val now = LocalNow.current
    // The night side as it is when the map opens.
    val time = remember { now() }
    // Kept through the window made again, as the iPhone keeps its state through a turn; the map keeps its camera.
    var chosen by rememberSaveable(saver = CHOSEN_SAVER) { mutableStateOf<ParcelRoute.Mode?>(null) }
    // Moved by hand since it last flew: then neither view is selected, and either flies it back.
    var free by rememberSaveable(saver = FREE_SAVER) { mutableStateOf(false) }
    var recenter by remember { mutableIntStateOf(0) }
    var barHeight by remember { mutableFloatStateOf(BAR_HEIGHT_GUESS) }
    val mode = chosen ?: route.defaultMode(stage)
    val backdrop = rememberMaterialBackdrop()
    val palette = remember(accent) { MapPalette.map(accent) }
    val system = WindowInsets.safeDrawing
    val label = mapLabel(route, localizer)

    BoxWithConstraints(modifier.fillMaxSize().background(colors.background).testTag(MAP_COVER)) {
        val safe = with(density) {
            EdgeInsets(
                top = system.getTop(this).toDp().value.toDouble(),
                leading = system.getLeft(this, LayoutDirection.Ltr).toDp().value.toDouble(),
                bottom = system.getBottom(this).toDp().value.toDouble(),
                trailing = system.getRight(this, LayoutDirection.Ltr).toDp().value.toDouble(),
            )
        }
        val split = hinge?.let { with(density) { it.start.toDp().value.toDouble()..it.end.toDp().value.toDouble() } }
        val level = levelHinge?.let { with(density) { it.top.toDp().value.toDouble()..it.bottom.toDp().value.toDouble() } }
        val layout = MapCoverLayout.of(maxWidth.value.toDouble(), maxHeight.value.toDouble(), safe, barHeight.toDouble(), split, level)

        // The route is framed in the space the summary leaves, under the system's bars too.
        Box(
            Modifier
                .fillMaxSize()
                .materialBackdrop(backdrop)
                .testTag(MAP_COVER_MAP)
                .then(if (mapFocus != null) Modifier.accessibilityFocus(mapFocus) else Modifier)
                .clearAndSetSemantics {
                    contentDescription = label
                    role = Role.Image
                },
        ) {
            // Drawn with the cover's first frame, as the iPhone's is, rather than coming in partway up its slide.
            CompositionLocalProvider(LocalMapsAtOnce provides true) {
                WorldMapView(
                    atlas = atlas, route = route, mode = mode, palette = palette, modifier = Modifier.fillMaxSize(), sites = true,
                    interactive = true, night = time, live = stage != TrackingStage.Delivered && stage != TrackingStage.Returned,
                    // Pip came along from the card. Here he keeps clear of what lies over the map: the summary and the
                    // button that closes it.
                    pip = PipMood(stage)?.let { PipRequest(it, inset = true) },
                    insets = layout.insets, recenter = recenter, language = language, onFreeChange = { free = it },
                )
            }
        }

        Box(
            Modifier
                .fillMaxSize()
                .padding(start = safe.leading.dp, top = safe.top.dp, end = safe.trailing.dp, bottom = safe.bottom.dp),
            contentAlignment = if (layout.barWidth != null) Alignment.BottomEnd else Alignment.BottomCenter,
        ) {
            // Over a large screen the summary keeps to about a phone's width, centred, or on its own side of an upright
            // hinge.
            Box(
                Modifier
                    .then(layout.barWidth?.let { Modifier.width(it.dp) } ?: Modifier)
                    .padding(start = BAR_SIDE, end = BAR_SIDE, bottom = BAR_SIDE),
                contentAlignment = Alignment.BottomCenter,
            ) {
                val shape = ContinuousRoundedShape(BAR_RADIUS)
                GlassSurface(
                    shape,
                    Modifier
                        .widthIn(max = BAR_WIDTH)
                        .fillMaxWidth()
                        .then(if (large) Modifier.heightIn(max = layout.barMaxHeight.dp) else Modifier)
                        .onSizeChanged { barHeight = it.height / density.density }
                        .testTag(MAP_BAR)
                        .then(TakesTouches),
                    backdrop = backdrop,
                ) {
                    // At the largest text sizes the summary scrolls, so the map stays in view.
                    Column(
                        Modifier
                            .then(if (large) Modifier.clip(shape).verticalScroll(rememberScrollState()) else Modifier)
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                    ) {
                        MapSummary(route, stage, large)
                        // With every place close by there is only one view: no buttons.
                        if (route.hasNearView) {
                            Row(
                                Modifier
                                    .background(colors.label.copy(alpha = colors.label.alpha * 0.06f), CapsuleShape)
                                    .padding(3.dp)
                                    .testTag(MAP_VIEWS)
                                    .selectableGroup()
                                    .semantics { isTraversalGroup = true },
                                horizontalArrangement = Arrangement.spacedBy(2.dp),
                            ) {
                                // A view is selected while the map shows it as it framed it; the one shown recentres it.
                                // TalkBack reads no group's name of its own: the first view says it, as VoiceOver says
                                // the group's as it comes to its views.
                                VIEWS.forEachIndexed { index, (target, key, symbol) ->
                                    val group = localizer.text("map.view").takeIf { index == 0 }
                                    ViewButton(localizer.text(key), symbol, selected = mode == target && !free, group = group) {
                                        if (target == mode) recenter++
                                        chosen = target
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        GlassSurface(
            CapsuleShape,
            Modifier
                .align(Alignment.TopEnd)
                .padding(top = (safe.top + layout.closeTop).dp, end = safe.trailing.dp + CLOSE_SIDE)
                .size(CLOSE_BUTTON),
            backdrop = backdrop,
        ) {
            Box(
                Modifier
                    .matchParentSize()
                    .testTag(MAP_CLOSE)
                    .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = onClose)
                    .semantics { contentDescription = localizer.text("map.close") },
                contentAlignment = Alignment.Center,
            ) {
                PeekSymbol("xmark", font = type.fixed(15f, FontWeight.SemiBold), tint = colors.label)
            }
        }
    }
}

/**
 * What TalkBack hears the map as: from where to where, or the one place, by a facility's own name ("Zürich-Mülligen"
 * where the card says "Zürich").
 */
private fun mapLabel(route: ParcelRoute, localizer: Localizer): String {
    val origin = route.origin?.place?.name(sites = true) ?: ""
    val end = (route.destination ?: route.current?.place)?.name(sites = true) ?: origin
    return if (end == origin) {
        localizer.text("map.labelOne", mapOf("place" to origin))
    } else {
        localizer.text("map.label", mapOf("from" to origin, "to" to end))
    }
}

/**
 * The summary: where the journey started and where it ends, or where the parcel was last seen, side by side or, at the
 * accessibility sizes, one under the other; then how far it has come, how far it has to go and through how many
 * countries, in caption figures, one under the other where they don't fit on a line.
 */
@Composable
private fun MapSummary(route: ParcelRoute, stage: TrackingStage?, large: Boolean) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val origin = route.origin ?: return
    val current = route.current ?: return
    val delivered = stage == TrackingStage.Delivered
    // A finished journey has a length, not a distance "so far".
    val finished = delivered || stage == TrackingStage.Returned
    val end = route.destination ?: current.place
    // A delivery scan with no place happened at the last stop only when that stop is in the destination country.
    val endLabel = when {
        delivered && (route.latestLocated || route.destination != null) -> "map.delivered"
        route.destination != null -> "map.to"
        route.latestLocated -> "map.now"
        else -> "map.lastSeen"
    }
    val single = route.stops.size == 1 && route.destination == null
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        if (large) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                if (!single) MapEndpoint(localizer.text("map.from"), origin.place, trailing = false, large = true)
                MapEndpoint(localizer.text(endLabel), end, trailing = false, large = true)
            }
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(14.dp), verticalAlignment = Alignment.Top) {
                if (!single) MapEndpoint(localizer.text("map.from"), origin.place, trailing = false, large = false, Modifier.weight(1f))
                MapEndpoint(localizer.text(endLabel), end, trailing = !single, large = false, Modifier.weight(1f))
            }
        }
        if (!single) {
            fun kilometres(value: Double) = ParcelRoute.formattedKilometres(value, language.locale)
            val figures = buildList {
                if (route.kilometres >= 1) {
                    val distance = kilometres(route.kilometres)
                    add(if (finished) distance else localizer.text("map.soFar", mapOf("distance" to distance)))
                }
                val remaining = route.remainingKilometres
                if (!finished && remaining != null) add(localizer.text("map.toGo", mapOf("distance" to kilometres(remaining))))
                if (route.countries.size > 1) add(localizer.text("map.countries.many", mapOf("count" to route.countries.size)))
            }
            if (figures.isNotEmpty()) {
                val style = PeekTheme.type.caption.monospacedDigit().sfLines()
                val color = PeekTheme.colors.secondaryLabel
                val lines = @Composable { figures.forEach { Text(it, color = color, style = style) } }
                // The figures go one under the other when they don't fit on one line.
                FirstThatFits(
                    Modifier,
                    wide = { Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) { lines() } },
                    narrow = { Column(verticalArrangement = Arrangement.spacedBy(4.dp)) { lines() } },
                )
            }
        }
    }
}

/**
 * One end of the journey: its title in tracked caption2 capitals, the place's name in title2 semibold and its flag and
 * country in caption, on a line each that ends in "…" except at the accessibility sizes, where they wrap. TalkBack
 * hears it as one: the title, the name, and the country where it isn't the name.
 */
@Composable
private fun MapEndpoint(title: String, place: RoutePlace, trailing: Boolean, large: Boolean, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val language = LocalLanguage.current
    val country = TrackingLocation.countryName(place.country, language)
    val name = place.name(sites = true)
    val lines = if (large) Int.MAX_VALUE else 1
    val align = if (trailing) TextAlign.End else TextAlign.Start
    val spoken = if (name == country) "$title, $country" else "$title, $name, $country"
    Column(
        modifier
            .fillMaxWidth()
            .clearAndSetSemantics { contentDescription = spoken },
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = if (trailing) Alignment.End else Alignment.Start,
    ) {
        Text(
            title.uppercase(language.locale),
            // CoreText tracks after each letter, Android half before it: the words end where the iPhone's end.
            Modifier.offset(x = (-0.7).dp),
            color = colors.secondaryLabel,
            style = type.tracking(type.style(IosTextStyle.Caption2, FontWeight.Medium), 1.4f).sfLines(),
            textAlign = align,
        )
        Text(
            name,
            color = colors.label,
            style = type.style(IosTextStyle.Title2, FontWeight.SemiBold).sfLines(),
            textAlign = align,
            maxLines = lines,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            // A colour emoji draws solid on the iPhone whatever the text's grey; Android would lay the grey's alpha on it.
            buildAnnotatedString {
                withStyle(SpanStyle(color = colors.label)) { append(TrackingLocation.flag(place.country)) }
                append(" $country")
            },
            color = colors.secondaryLabel,
            style = type.caption.sfLines(),
            textAlign = align,
            maxLines = lines,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/**
 * A button of the switch between the journey and close by: its symbol and words in medium caption, in the paper on
 * the ink while it is the view shown, secondary otherwise, 36 tall and 12 in from its sides. The space between the
 * symbol and the words is `Label`'s, estimated. TalkBack hears `group`, the switch's name, before its words.
 */
@Composable
private fun ViewButton(title: String, symbol: String, selected: Boolean, group: String?, action: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val tint = if (selected) colors.paper else colors.secondaryLabel
    val style = type.style(IosTextStyle.Caption, FontWeight.Medium)
    Row(
        Modifier
            .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = action)
            .semantics {
                this.selected = selected
                // Compose gives TalkBack a description of a node with words of its own as a child before them.
                if (group != null) contentDescription = group
            }
            .background(if (selected) colors.ink else Color.Transparent, CapsuleShape)
            .heightIn(min = 36.dp)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(LABEL_SPACING),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PeekSymbol(symbol, font = style, tint = tint)
        Text(title, color = tint, style = style.sfLines())
    }
}

/**
 * Where the map screen's parts stand, in points, in a window `width` by `height` whose system bars and cutouts take
 * `safe`, with the summary `barHeight` tall and, where a hinge splits the window, an upright one from `hinge`'s start
 * to its end from the window's left, or a level one from `level`'s start to its end from the window's top:
 *
 * - `closeTop`: how far below the safe area's top the close button stands, keeping off the window's edge where nothing
 *   is above the screen (a phone on its side).
 * - `insets`: what the route and Pip keep clear of: the close button's row, the system's sides, and the summary, or,
 *   beside a hinge or above a level one, the hinge and all past it.
 * - `barMaxHeight`: how tall the summary grows at the accessibility sizes before it scrolls: half the safe area, or
 *   under a level hinge, the room between it and the summary's foot.
 * - `barWidth`: beside an upright hinge, the room past it to the safe area's trailing side, which the summary keeps
 *   to; null elsewhere, where it is centred.
 */
internal data class MapCoverLayout(val closeTop: Double, val insets: EdgeInsets, val barMaxHeight: Double, val barWidth: Double?) {
    companion object {
        fun of(
            width: Double,
            height: Double,
            safe: EdgeInsets,
            barHeight: Double,
            hinge: ClosedFloatingPointRange<Double>?,
            level: ClosedFloatingPointRange<Double>? = null,
        ): MapCoverLayout {
            val closeTop = max(4.0, 14 - safe.top)
            // A window split both ways keeps to its upright hinge.
            val levelFold = level.takeIf { hinge == null }
            return MapCoverLayout(
                closeTop = closeTop,
                insets = EdgeInsets(
                    top = safe.top + closeTop + 48,
                    leading = safe.leading,
                    bottom = when {
                        hinge != null -> safe.bottom + 24
                        levelFold != null -> height - levelFold.start + 24
                        else -> safe.bottom + barHeight + 24
                    },
                    trailing = if (hinge != null) width - hinge.start + 24 else safe.trailing,
                ),
                barMaxHeight = levelFold?.let { height - safe.bottom - it.endInclusive - 24 } ?: ((height - safe.top - safe.bottom) / 2),
                barWidth = hinge?.let { width - safe.trailing - it.endInclusive },
            )
        }
    }
}

/**
 * Takes the touches that land on it from what lies under it, as the iPhone's glass takes its hits: a drag or a pinch
 * that starts on the summary leaves the map alone. It consumes none, so the views and, at the accessibility sizes, the
 * summary's scroll have every one.
 */
private val TakesTouches = Modifier.pointerInput(Unit) { awaitPointerEventScope { while (true) awaitPointerEvent() } }

/** The view chosen and whether the map was moved, kept by their plain values: the new window shares nothing with the old. */
private val CHOSEN_SAVER = Saver<MutableState<ParcelRoute.Mode?>, String>(save = { it.value?.name }, restore = { mutableStateOf(ParcelRoute.Mode.valueOf(it)) })
private val FREE_SAVER = Saver<MutableState<Boolean>, Boolean>(save = { it.value }, restore = { mutableStateOf(it) })

/** The views: the whole journey, and close by. */
private val VIEWS = listOf(
    Triple(ParcelRoute.Mode.Journey, "map.journey", "globe"),
    Triple(ParcelRoute.Mode.Now, "map.nearby", "location"),
)

/** Until the summary is laid out, the map keeps clear of about as much as it takes. */
private const val BAR_HEIGHT_GUESS = 220f
private val BAR_WIDTH = 420.dp
private val BAR_SIDE = 12.dp
private val BAR_RADIUS = 24.dp
private val CLOSE_BUTTON = 44.dp
private val CLOSE_SIDE = 14.dp
private val LABEL_SPACING = 6.dp

/** The test tags of the map screen, its map, its summary, its views and its close button. */
const val MAP_COVER = "parcelMap.cover"
const val MAP_COVER_MAP = "parcelMap.map"
const val MAP_BAR = "parcelMap.bar"
const val MAP_VIEWS = "parcelMap.views"
const val MAP_CLOSE = "parcelMap.close"
