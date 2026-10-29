package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.serialization.NavBackStackSerializer
import androidx.navigation3.scene.SinglePaneSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabReselection
import com.plhery.peekdeliverytracker.ui.tabs.TabReselection
import com.plhery.peekdeliverytracker.ui.tabs.TabReselectionEffect
import com.plhery.peekdeliverytracker.ui.tabs.tabContentPadding
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlinx.serialization.Serializable

/** Where the deliveries' stack stands, saved with the window and through process death (A-SE03). */
@Serializable
sealed interface DeliveriesKey : NavKey {
    /** The list, the stack's root. */
    @Serializable
    data object ParcelList : DeliveriesKey

    /** A parcel's page, by the parcel's id, as `@SceneStorage("deliveries.parcel")` keeps it. */
    @Serializable
    data class ParcelPage(val id: String) : DeliveriesKey
}

/**
 * The deliveries tab: the list and, pushed over it, a parcel's page, on a back stack that the system's back and the
 * predictive back gesture pop. Tapping the tab again goes back to the list, and at the list scrolls it to its top.
 *
 * The scenes come from [sceneStrategies]: the window wide enough for the page beside the list (ParcelListView's
 * split) takes a strategy that lays both stack entries side by side, ahead of the single pane, with no change to the
 * stack itself.
 */
@Composable
fun DeliveriesTab(
    list: @Composable (open: (id: String) -> Unit) -> Unit,
    page: @Composable (id: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val backStack = rememberSerializable(serializer = NavBackStackSerializer(DeliveriesKey.serializer())) {
        NavBackStack<DeliveriesKey>(DeliveriesKey.ParcelList)
    }
    val listReselection = remember { TabReselection() }
    TabReselectionEffect {
        if (backStack.size > 1) {
            while (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
        } else {
            listReselection.reselect()
        }
    }
    val open = { id: String ->
        val shown = DeliveriesKey.ParcelPage(id)
        if (backStack.last() != shown) backStack.add(shown)
    }
    val reduceMotion = PeekTheme.reduceMotion
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        sceneStrategies = sceneStrategies,
        transitionSpec = { Push.forward(reduceMotion) },
        popTransitionSpec = { Push.back(reduceMotion) },
        predictivePopTransitionSpec = { Push.predictiveBack(reduceMotion) },
        entryProvider = entryProvider {
            entry<DeliveriesKey.ParcelList> {
                CompositionLocalProvider(LocalTabReselection provides listReselection) { list(open) }
            }
            entry<DeliveriesKey.ParcelPage> { key -> page(key.id) }
        },
    )
}

private val sceneStrategies = listOf(SinglePaneSceneStrategy<DeliveriesKey>())

/**
 * How a parcel's page comes and goes until it zooms from its card: UINavigationController's push. The page slides in
 * from the trailing edge over the list, which moves a third of the way out behind it, on a spring of response 0.35 s
 * that doesn't bounce, and back reverses it. The stack keeps the page over the list both ways. Nothing moves under
 * Reduce Motion.
 */
private object Push {
    fun forward(reduceMotion: Boolean): ContentTransform =
        if (reduceMotion) still() else slideInHorizontally(spring) { it } togetherWith slideOutHorizontally(spring) { -it / 3 }

    fun back(reduceMotion: Boolean): ContentTransform = pop(reduceMotion, spring)

    /**
     * The predictive back gesture moves both the same way. The stack seeks the pop by the gesture's progress, so an even
     * pace keeps the page under the finger; let go, it goes the rest of the way in what is left of 0.35 s.
     */
    fun predictiveBack(reduceMotion: Boolean): ContentTransform = pop(reduceMotion, gesture)

    private fun pop(reduceMotion: Boolean, motion: FiniteAnimationSpec<IntOffset>): ContentTransform =
        if (reduceMotion) still() else slideInHorizontally(motion) { -it / 3 } togetherWith slideOutHorizontally(motion) { it }

    private fun still(): ContentTransform = EnterTransition.None togetherWith ExitTransition.None

    private val spring = Motion.springWithResponse<IntOffset>(0.35, 1.0)
    private val gesture = tween<IntOffset>(durationMillis = 350, easing = LinearEasing)
}

/** The list until it is ported: the parcels' names, each opening its page. */
@Composable
fun ParcelListPlaceholder(parcels: List<Pair<String, String>>, open: (id: String) -> Unit) {
    val state = rememberLazyListState()
    val reduceMotion = PeekTheme.reduceMotion
    TabReselectionEffect { if (reduceMotion) state.scrollToItem(0) else state.animateScrollToItem(0) }
    val colors = PeekTheme.colors
    val style = PeekTheme.type.body.copy(color = colors.label)
    LazyColumn(
        Modifier.fillMaxSize().background(colors.background).testTag(PARCEL_LIST),
        state = state,
        contentPadding = tabContentPadding(),
    ) {
        items(parcels, key = { it.first }) { (id, label) ->
            BasicText(label, Modifier.fillMaxWidth().clickable { open(id) }.padding(horizontal = 16.dp, vertical = 12.dp), style = style)
        }
    }
}

/** A parcel's page until it is ported: the parcel's name. */
@Composable
fun ParcelPagePlaceholder(label: String?) {
    val colors = PeekTheme.colors
    Box(Modifier.fillMaxSize().background(colors.background).testTag(PARCEL_PAGE)) {
        BasicText(label.orEmpty(), Modifier.safeDrawingPadding().padding(16.dp), style = PeekTheme.type.headline.copy(color = colors.label))
    }
}

/** The test tags of the placeholders. */
const val PARCEL_LIST = "parcelList"
const val PARCEL_PAGE = "parcelPage"
