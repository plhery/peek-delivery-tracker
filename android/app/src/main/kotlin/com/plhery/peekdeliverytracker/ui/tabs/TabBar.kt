package com.plhery.peekdeliverytracker.ui.tabs

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.MaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/** The test tag of the tab bar's capsule. */
const val TAB_BAR = "tabBar"

/** The test tag of the pill behind the chosen tab. */
const val TAB_PILL = "tabPill"

/** The pill's slide from one tab to another: iOS 26's tab bar, a spring of response 0.35 s and damping 0.85. */
private val PillMotion: FiniteAnimationSpec<Float> = Motion.springWithResponse(0.35, 0.85)

/**
 * The iPhone's floating tab bar: a glass capsule 62 tall of three items of one width, each the widest label's
 * medium-weight width and [BOTTOM_LABEL_ROOM], at least 86, inside 8 on either side. An item shows its symbol centred
 * 24 under the capsule's top and its label's baseline 48.7 under it, both of a fixed size, as UIKit keeps a tab bar's
 * out of Dynamic Type. The pill reaches 4 past the item on either side.
 */
@Composable
internal fun BottomTabBar(
    labels: List<String>,
    selection: Int,
    onTap: (Int) -> Unit,
    backdrop: MaterialBackdrop?,
    modifier: Modifier = Modifier,
) {
    val type = PeekTheme.type
    val widths = labelWidths(labels, type.fixed(BOTTOM_LABEL, FontWeight.Medium))
    val geometry = remember(widths) {
        val item = maxOf(widths.max() + BOTTOM_LABEL_ROOM, BOTTOM_MIN_ITEM)
        TabBarGeometry(List(widths.size) { item }, padding = 8f, height = BOTTOM_BAR_HEIGHT.value, pillOutset = 4f)
    }
    val ink = PeekTheme.colors.ink
    TabBar(geometry, labels, selection, onTap, backdrop, BOTTOM_PILL_SHADE, modifier) { tab, selected ->
        val icon = type.fixed(BOTTOM_ICON, FontWeight.Medium)
        val label = type.fixed(BOTTOM_LABEL, if (selected) FontWeight.SemiBold else FontWeight.Medium).copy(color = ink)
        Layout(
            content = {
                PeekSymbol(tab.symbol, font = icon, tint = ink)
                BasicText(labels[tab.ordinal], Modifier.clearAndSetSemantics {}, style = label, maxLines = 1, softWrap = false)
            },
            modifier = Modifier.fillMaxSize(),
        ) { measurables, constraints ->
            val symbol = measurables[0].measure(Constraints())
            val text = measurables[1].measure(Constraints())
            val width = constraints.maxWidth
            layout(width, constraints.maxHeight) {
                symbol.place((width - symbol.width) / 2, BOTTOM_ICON_BASELINE.dp.roundToPx() - symbol[FirstBaseline])
                text.place((width - text.width) / 2, BOTTOM_LABEL_BASELINE.dp.roundToPx() - text[FirstBaseline])
            }
        }
    }
}

/**
 * iPadOS's tab bar at the top of the window: a glass capsule 44 tall of the labels alone, each item its label's
 * medium-weight width and [TOP_LABEL_ROOM] inside 4 on either side, the label's baseline 28 under the capsule's top.
 * The pill is the item, 4 in from the capsule's top and bottom.
 */
@Composable
internal fun TopTabBar(
    labels: List<String>,
    selection: Int,
    onTap: (Int) -> Unit,
    backdrop: MaterialBackdrop?,
    modifier: Modifier = Modifier,
) {
    val type = PeekTheme.type
    val widths = labelWidths(labels, type.fixed(TOP_LABEL, FontWeight.Medium))
    val geometry = remember(widths) {
        TabBarGeometry(widths.map { it + TOP_LABEL_ROOM }, padding = 4f, height = TOP_BAR_HEIGHT.value, pillOutset = 0f)
    }
    val ink = PeekTheme.colors.ink
    TabBar(geometry, labels, selection, onTap, backdrop, TOP_PILL_SHADE, modifier) { tab, selected ->
        val label = type.fixed(TOP_LABEL, if (selected) FontWeight.SemiBold else FontWeight.Medium).copy(color = ink)
        Layout(
            content = { BasicText(labels[tab.ordinal], Modifier.clearAndSetSemantics {}, style = label, maxLines = 1, softWrap = false) },
            modifier = Modifier.fillMaxSize(),
        ) { measurables, constraints ->
            val text = measurables[0].measure(Constraints())
            val width = constraints.maxWidth
            layout(width, constraints.maxHeight) {
                text.place((width - text.width) / 2, TOP_LABEL_BASELINE.dp.roundToPx() - text[FirstBaseline])
            }
        }
    }
}

/**
 * The capsule, the pill and the items, each item a tab to accessibility (its label, whether it is chosen, its place
 * among the three), the whole of its frame taking the tap and giving under the finger as the app's buttons do.
 */
@Composable
private fun TabBar(
    geometry: TabBarGeometry,
    labels: List<String>,
    selection: Int,
    onTap: (Int) -> Unit,
    backdrop: MaterialBackdrop?,
    pillShade: Float,
    modifier: Modifier,
    item: @Composable (tab: PeekTab, selected: Boolean) -> Unit,
) {
    GlassSurface(CapsuleShape, modifier.testTag(TAB_BAR).size(geometry.width.dp, geometry.height.dp), backdrop) {
        TabPill(geometry, selection, pillShade)
        Row(
            Modifier
                .padding(horizontal = geometry.padding.dp)
                .selectableGroup()
                .semantics { collectionInfo = CollectionInfo(rowCount = 1, columnCount = PeekTab.entries.size) },
        ) {
            for (tab in PeekTab.entries) {
                val index = tab.ordinal
                val selected = index == selection
                Box(
                    Modifier
                        .width(geometry.itemWidths[index].dp)
                        .fillMaxHeight()
                        .selectable(selected, interactionSource = null, indication = PressStyle.Tactile, role = Role.Tab) { onTap(index) }
                        .semantics {
                            contentDescription = labels[index]
                            collectionItemInfo = CollectionItemInfo(rowIndex = 0, rowSpan = 1, columnIndex = index, columnSpan = 1)
                        },
                ) {
                    item(tab, selected)
                }
            }
        }
    }
}

/**
 * The pill behind the chosen item. It takes `shade` off each channel of the glass under it, as the iPhone's does in
 * light and dark alike: a grey of that shade drawn as a difference, straight onto the glass with no layer between.
 */
@Composable
private fun TabPill(geometry: TabBarGeometry, selection: Int, shade: Float) {
    val target = geometry.pill(selection)
    val left = remember { Animatable(target.first) }
    val right = remember { Animatable(target.second) }
    val shown = remember { LastTab(selection) }
    val reduceMotion = PeekTheme.reduceMotion
    LaunchedEffect(target) {
        // Only a change of tab slides; new labels or a new language move it at once.
        val slide = shown.tab != selection && !reduceMotion
        shown.tab = selection
        if (slide) {
            coroutineScope {
                launch { left.animateTo(target.first, PillMotion) }
                launch { right.animateTo(target.second, PillMotion) }
            }
        } else {
            left.snapTo(target.first)
            right.snapTo(target.second)
        }
    }
    val grey = Color(shade, shade, shade)
    Box(
        Modifier
            .layout { measurable, _ ->
                val x = left.value.dp.roundToPx()
                val width = right.value.dp.roundToPx() - x
                val height = (geometry.height - 2 * PILL_INSET).dp.roundToPx()
                val placeable = measurable.measure(Constraints.fixed(width.coerceAtLeast(0), height))
                layout(placeable.width, placeable.height) { placeable.place(x, PILL_INSET.dp.roundToPx()) }
            }
            .testTag(TAB_PILL)
            .drawBehind { drawRoundRect(grey, cornerRadius = CornerRadius(size.height / 2), blendMode = BlendMode.Difference) },
    )
}

/** A bar's lengths in dp: its items' widths, the room either side of them, its height, how far the pill reaches past. */
@Immutable
private class TabBarGeometry(val itemWidths: List<Float>, val padding: Float, val height: Float, val pillOutset: Float) {
    val width = itemWidths.sum() + 2 * padding

    /** The pill's left and right edges behind the item at `index`. */
    fun pill(index: Int): Pair<Float, Float> {
        val left = padding + itemWidths.take(index).sum()
        return left - pillOutset to left + itemWidths[index] + pillOutset
    }
}

/** Each label's width in dp in `style`, unrounded, as the bar sizes its items from them. */
@Composable
private fun labelWidths(labels: List<String>, style: TextStyle): List<Float> {
    val measurer = rememberTextMeasurer()
    val density = LocalDensity.current
    return remember(labels, style, density) {
        labels.map { label ->
            val width = measurer.measure(label, style, maxLines = 1, softWrap = false).multiParagraph.intrinsics.maxIntrinsicWidth
            width / density.density
        }
    }
}

internal val BOTTOM_BAR_HEIGHT = 62.dp
internal val TOP_BAR_HEIGHT = 44.dp

/**
 * What an item adds to its label's width. UIKit adds 32.2 to SF's width of the label; Roboto, spaced to SF's widths on
 * average, comes out narrower or wider than SF word by word, so these are what make the bars here the iPhone's and the
 * iPad's from Roboto's widths: the iPhone's 280 in German (its 274 in English is three of the narrowest items), the
 * iPad's 308 in English. The glass's edge is the shape's, inside the dark line drawn just outside it.
 */
private const val BOTTOM_LABEL_ROOM = 33.75f
private const val TOP_LABEL_ROOM = 31.25f

/** The narrowest item of the bottom bar. */
private const val BOTTOM_MIN_ITEM = 86f

private const val BOTTOM_LABEL = 10f
private const val TOP_LABEL = 17f

/** The symbols' size: the point size at which SF draws the three as large as the iPhone's tab bar does. */
private const val BOTTOM_ICON = 23.3f

/** The symbols' baseline under the capsule's top, which puts each symbol's middle 24 under it. */
private const val BOTTOM_ICON_BASELINE = 32.3f
private const val BOTTOM_LABEL_BASELINE = 48.7f
private const val TOP_LABEL_BASELINE = 28f

/** The pill's room above and below it, in either bar. */
private const val PILL_INSET = 4f

/** What the pill takes off the glass, measured on the iPhone's bar and the iPad's. */
private const val BOTTOM_PILL_SHADE = 0.09f
private const val TOP_PILL_SHADE = 0.075f
