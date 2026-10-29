package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.ParcelSort
import com.plhery.peekdeliverytracker.core.ParcelStatusFilter
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.sheet.FormButtonRow
import com.plhery.peekdeliverytracker.ui.sheet.FormCheckRow
import com.plhery.peekdeliverytracker.ui.sheet.FormLabelRow
import com.plhery.peekdeliverytracker.ui.sheet.FormMenuRow
import com.plhery.peekdeliverytracker.ui.sheet.FormSection
import com.plhery.peekdeliverytracker.ui.sheet.MenuOption
import com.plhery.peekdeliverytracker.ui.sheet.Popover
import com.plhery.peekdeliverytracker.ui.sheet.PopoverSize
import com.plhery.peekdeliverytracker.ui.sheet.REGULAR_HEIGHT
import com.plhery.peekdeliverytracker.ui.sheet.REGULAR_WIDTH
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.sheet.SheetPage
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities

/**
 * The filters (A-S98, B-S36): a sheet at the medium and large detents over the list, or, while sheets show as forms
 * (side by side, settled while none is open), a popover off their button, 380 × 620 where the window has room and
 * 340 × 440 at least. A popover in a window turned compact is a sheet again, as a popover adapts on the iPhone. Once
 * they have closed, TalkBack goes back to their button.
 */
@Composable
internal fun FiltersHost(state: DeliveriesState) {
    val window = LocalWindowInfo.current.containerSize
    val density = LocalDensity.current
    val regular = with(density) { window.width.toDp() >= REGULAR_WIDTH && window.height.toDp() >= REGULAR_HEIGHT }
    val popover = state.sheetsAsForms && regular
    val title = localized("view.showControls")
    val close = { state.showingFilters = false }
    val closed = { state.filtersButton.request() }
    Sheet(
        visible = state.showingFilters && !popover,
        onDismiss = close,
        paneTitle = title,
        detents = listOf(SheetDetent.Medium, SheetDetent.Large),
        onClosed = closed,
    ) {
        ParcelFilterView(state, close, Modifier.testTag(FILTERS_SHEET))
    }
    Popover(
        visible = state.showingFilters && popover,
        anchor = state.filtersAnchor,
        onDismiss = close,
        paneTitle = title,
        size = POPOVER_SIZE,
        onClosed = closed,
    ) {
        ParcelFilterView(state, close, Modifier.testTag(FILTERS_POPOVER))
    }
}

/**
 * `ParcelFilterView`: the status, an inline picker of every status; the carrier, a menu of all carriers and those of
 * the parcels, the one chosen among them though its parcels have gone; the sort, an inline picker of every order; and
 * Clear filters, in red, which puts the three back and leaves the query. "Search & filters" in the bar, and Done, which
 * closes it. TalkBack starts at the title.
 */
@Composable
private fun ParcelFilterView(state: DeliveriesState, close: () -> Unit, modifier: Modifier = Modifier) {
    val language = LocalLanguage.current
    val identities = LocalCarrierIdentities.current
    val name = { carrier: CarrierId ->
        identities?.displayName(carrier, language) ?: CarrierCatalog.shared.info(carrier, language).displayName
    }
    val carriers = remember(state.parcels, state.carrierFilter, identities, language) { state.availableCarriers(name) }
    val titleFocus = remember { AccessibilityFocus() }
    LaunchedEffect(Unit) { titleFocus.request() }
    SheetPage(
        title = localized("view.showControls"),
        confirmation = localized("native.done"),
        onConfirm = close,
        modifier = modifier,
        titleFocus = titleFocus,
        confirmTag = FILTERS_DONE,
    ) {
        val status = localized("view.status")
        FormSection(status) {
            FormLabelRow(status)
            for (filter in ParcelStatusFilter.entries) {
                FormCheckRow(localized(filter.localizationKey), state.statusFilter == filter, { state.statusFilter = filter }, tag = "$FILTERS_STATUS.${filter.rawValue}")
            }
        }
        val carrier = localized("view.carrier")
        FormSection(carrier) {
            val options = listOf(MenuOption(localized("view.allCarriers"), "$FILTERS_CARRIER.all")) +
                carriers.map { MenuOption(name(it), "$FILTERS_CARRIER.${it.rawValue}") }
            val selected = state.carrierFilter?.let { carriers.indexOf(it) + 1 } ?: 0
            FormMenuRow(carrier, options, selected, { index ->
                state.carrierFilter = if (index == 0) null else carriers[index - 1]
            }, tag = FILTERS_CARRIER)
        }
        val sort = localized("view.sort")
        FormSection(sort) {
            FormLabelRow(sort)
            for (order in ParcelSort.entries) {
                FormCheckRow(localized(order.localizationKey), state.sort == order, { state.sort = order }, tag = "$FILTERS_SORT.${order.rawValue}")
            }
        }
        FormSection(null) {
            FormButtonRow(localized("view.clear"), destructive = true, onClick = {
                state.statusFilter = ParcelStatusFilter.All
                state.carrierFilter = null
                state.sort = ParcelSort.Priority
            }, tag = FILTERS_CLEAR)
        }
    }
}

/** `.frame(minWidth: 340, idealWidth: 380, minHeight: 440, idealHeight: 620)`. */
private val POPOVER_SIZE = PopoverSize(340.dp, 380.dp, 440.dp, 620.dp)

const val FILTERS_SHEET = "deliveries.filters.sheet"
const val FILTERS_POPOVER = "deliveries.filters.popover"
const val FILTERS_DONE = "deliveries.filters.done"
const val FILTERS_STATUS = "deliveries.filters.status"
const val FILTERS_CARRIER = "deliveries.filters.carrier"
const val FILTERS_SORT = "deliveries.filters.sort"
const val FILTERS_CLEAR = "deliveries.filters.clear"
