package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.event
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

/** ParcelLogicTests.swift: the deliveries' list, its cards, the parcel shown beside it, its keys and menus. */
class ParcelLogicListTests {
    @Test
    fun deliveredHoldKeepsTheListAsItWasUntilItsCardsLeave() {
        val tea = UUID.randomUUID()
        val lamp = UUID.randomUUID()
        // Scanned in the last hours: neither parcel has been still for long enough to be flagged.
        val earlier = DateParser.isoString(Instant.now().minusSeconds(7_200))
        val lately = DateParser.isoString(Instant.now().minusSeconds(600))
        val shown = listOf(
            makeParcel(id = tea, events = listOf(event(tea, TrackingStage.OutForDelivery, earlier))),
            makeParcel(id = lamp, events = listOf(event(lamp, TrackingStage.InTransit, earlier))),
        )
        val now = shown.toMutableList()
        now[0] = now[0].copy(trackingEvents = now[0].trackingEvents + event(tea, TrackingStage.Delivered, lately))
        // The same news makes the lamp the most urgent parcel: on its own, that would move the tea out of its place.
        now[1] = now[1].copy(trackingEvents = now[1].trackingEvents + event(lamp, TrackingStage.ReadyForPickup, lately))
        fun layout(parcels: List<Parcel>): DeliveryListLayout =
            DeliveryListLayout(parcels, query = "", status = ParcelStatusFilter.All, carrier = null, sort = ParcelSort.Priority, featuresNext = true)

        val hold = DeliveredHold()
        hold.receive(shown = shown, next = now, watching = true)
        assertEquals(setOf(tea), hold.ids)
        assertTrue(hold.holds(tea))
        val held = layout(hold.arranged(now))
        assertEquals(tea, held.next?.id)
        assertEquals(DeliveryCardKind.OnTheWay, held.kind(of = lamp))
        assertEquals(2, held.active.size)

        // Let go, the tea joins the past deliveries and the lamp becomes Next up: those two cards are others.
        val after = layout(now)
        val changes = held.changing(into = after)
        assertEquals(setOf(tea, lamp), changes.keys)
        assertEquals(DeliveryCardKind.Next, changes[tea]?.was)
        assertEquals(DeliveryCardKind.Past, changes[tea]?.becomes)
        assertEquals(DeliveryCardKind.Next, changes[lamp]?.becomes)

        // More news for a held parcel keeps it held; archiving it lets it go, and it is arranged as it is.
        hold.receive(shown = now, next = now, watching = true)
        assertEquals(setOf(tea), hold.ids)
        val archived = now.toMutableList()
        archived[0] = archived[0].copy(archivedAt = lately)
        assertTrue(hold.arranged(archived)[0].isArchived)
        hold.receive(shown = now, next = archived, watching = true)
        assertTrue(hold.isEmpty)
        assertEquals(now, hold.arranged(now))

        // A list that nobody is looking at holds nothing, and one that stops being looked at lets go.
        val unseen = DeliveredHold()
        unseen.receive(shown = shown, next = now, watching = false)
        assertTrue(unseen.isEmpty)
        unseen.receive(shown = shown, next = now, watching = true)
        assertFalse(unseen.isEmpty)
        unseen.release()
        assertTrue(unseen.isEmpty)
        assertEquals(DeliveredHold(), unseen)
    }

    @Test
    fun deliveryListLayoutFeaturesOneArrivalAndKeepsIssuesOnTheirOwnCards() {
        val now = DateParser.date("2026-09-09T12:00:00Z")!!
        val parcels = DemoRepository.seed(now = now)
        val layout = DeliveryListLayout(parcels, query = "", status = ParcelStatusFilter.All, carrier = null, sort = ParcelSort.Priority, featuresNext = true, now = now)
        val next = layout.next
        assertNotNull(next)
        assertEquals("New trainers 👟", next?.label)
        assertEquals(parcels.filter { it.isActive }.map { it.id }.toSet(), layout.active.map { it.id }.toSet())
        assertEquals(
            layout.active.map { it.id }.toSet(),
            (layout.attention.map { it.id } + layout.remaining.map { it.id } + listOfNotNull(next?.id)).toSet(),
        )
        assertFalse(layout.attention.any { it.id == next?.id } || layout.remaining.any { it.id == next?.id })
        assertTrue(layout.attention.all { it.attention(now = now) != null })
        assertTrue(layout.remaining.all { it.attention(now = now) == null })
        assertEquals(listOf(ParcelSectionKind.Delivered, ParcelSectionKind.Archived), layout.sections.map { it.kind })
        val searched = DeliveryListLayout(parcels, query = "trainers", status = ParcelStatusFilter.All, carrier = null, sort = ParcelSort.Priority, featuresNext = false, now = now)
        assertNull(searched.next)
        assertEquals(listOf("New trainers 👟"), searched.visible.map { it.label })
    }

    @Test
    fun theDeliveriesStandBesideTheParcelOnlyInAWindowLargeEnoughForBoth() {
        fun arrangement(
            width: Double,
            regularWidth: Boolean = true,
            regularHeight: Boolean = true,
            largeText: Boolean = false,
            from: DeliveriesArrangement = DeliveriesArrangement.Stacked,
        ): DeliveriesArrangement = DeliveriesArrangement.resolve(
            width = width, regularWidth = regularWidth, regularHeight = regularHeight, largeText = largeText, current = from,
        )
        // An iPad, and an open iPhone Duo held like a book, less its bar.
        assertEquals(DeliveriesArrangement.SideBySide, arrangement(1376.0))
        assertEquals(DeliveriesArrangement.SideBySide, arrangement(887.0))
        // An open Duo turned tall is regular both ways, but two columns there would each be narrower than a phone.
        assertEquals(DeliveriesArrangement.Stacked, arrangement(669.0))
        // A phone on its side, a narrow iPad window and the Duo's cover keep the list.
        assertEquals(DeliveriesArrangement.Stacked, arrangement(956.0, regularHeight = false))
        assertEquals(DeliveriesArrangement.Stacked, arrangement(1024.0, regularWidth = false))
        assertEquals(DeliveriesArrangement.Stacked, arrangement(678.0, regularWidth = false, regularHeight = false))
        // A window dragged across the line turns once, past the slack, and not back until past it the other way.
        assertEquals(DeliveriesArrangement.Stacked, arrangement(720.0))
        assertEquals(DeliveriesArrangement.SideBySide, arrangement(731.0))
        assertEquals(DeliveriesArrangement.SideBySide, arrangement(700.0, from = DeliveriesArrangement.SideBySide))
        assertEquals(DeliveriesArrangement.Stacked, arrangement(689.0, from = DeliveriesArrangement.SideBySide))
        // Size classes rule before width: side by side ends at once when the window becomes compact.
        assertEquals(DeliveriesArrangement.Stacked, arrangement(1376.0, regularWidth = false, from = DeliveriesArrangement.SideBySide))
        // At the accessibility text sizes each column needs more: the open Duo keeps one column, an iPad still has room for two.
        assertEquals(DeliveriesArrangement.Stacked, arrangement(887.0, largeText = true))
        assertEquals(DeliveriesArrangement.Stacked, arrangement(887.0, largeText = true, from = DeliveriesArrangement.SideBySide))
        assertEquals(DeliveriesArrangement.SideBySide, arrangement(1032.0, largeText = true))
    }

    @Test
    fun aParcelsPageBesideTheListPutsItsHistoryBesideTheCardWhereTwoPhoneWideColumnsFit() {
        fun columns(width: Double, besideList: Boolean = true, largeText: Boolean = false, history: Boolean = true): Boolean =
            ParcelPageColumns.fit(width = width, besideList = besideList, largeText = largeText, history = history)
        // Beside the list on a 13-inch iPad on its side, and on an 11-inch one.
        assertTrue(columns(996.0))
        assertTrue(columns(800.0))
        // The 13-inch upright and an open Duo leave the page a phone's column or a little more.
        assertFalse(columns(652.0))
        assertFalse(columns(507.0))
        assertTrue(columns(776.0))
        assertFalse(columns(775.0))
        // Over the list, as on a phone on its side, the page keeps one column whatever its width.
        assertFalse(columns(956.0, besideList = false))
        // With nothing to tell yet, the history has no column of its own.
        assertFalse(columns(996.0, history = false))
        // At the accessibility text sizes each column needs more: the 13-inch still has room, the 11-inch not.
        assertTrue(columns(996.0, largeText = true))
        assertFalse(columns(800.0, largeText = true))
    }

    @Test
    fun thePassportLiesOpenOnTwoPagesWhereAWideAndTallWindowHasRoomForTwoPhoneWidePages() {
        fun spread(width: Double, regularWidth: Boolean = true, regularHeight: Boolean = true, largeText: Boolean = false): Boolean =
            PassportSpread.fits(width = width, regularWidth = regularWidth, regularHeight = regularHeight, largeText = largeText)
        // An iPad upright, 13-inch and 11-inch, and an open Duo lying flat, less its bar.
        assertTrue(spread(1032.0))
        assertTrue(spread(820.0))
        assertTrue(spread(887.0))
        assertTrue(spread(760.0))
        assertFalse(spread(759.0))
        // A phone on its side is wide but short; a narrow iPad window and the Duo's cover are compact.
        assertFalse(spread(956.0, regularHeight = false))
        assertFalse(spread(1024.0, regularWidth = false))
        assertFalse(spread(678.0, regularWidth = false, regularHeight = false))
        // At the accessibility text sizes each page needs more: the 13-inch still has room, the 11-inch not.
        assertTrue(spread(1032.0, largeText = true))
        assertFalse(spread(820.0, largeText = true))
    }

    @Test
    fun theListsCardsRunFromNextUpToThePastDeliveriesWithoutTheArchive() {
        val now = DateParser.date("2026-09-09T12:00:00Z")!!
        val parcels = DemoRepository.seed(now = now)
        val layout = DeliveryListLayout(parcels, query = "", status = ParcelStatusFilter.All, carrier = null, sort = ParcelSort.Priority, featuresNext = true, now = now)
        val delivered = layout.sections.first { it.kind == ParcelSectionKind.Delivered }
        assertEquals((listOfNotNull(layout.next) + layout.attention + layout.remaining + delivered.parcels).map { it.id }, layout.cards)
        assertEquals(layout.next?.id, layout.cards.firstOrNull())
        assertEquals(parcels.filter { !it.isArchived }.map { it.id }.toSet(), layout.cards.toSet())
    }

    @Test
    fun everyWayAParcelOpensShowsItAsChosen() {
        val first = UUID.randomUUID()
        val second = UUID.randomUUID()
        var navigation = DeliveriesNavigation()
        navigation.open(first)
        assertEquals(listOf(first), navigation.path)
        assertEquals(first, navigation.chosen)
        // Another opened from beside the list, or from a notification over another, takes its place.
        navigation.open(second)
        assertEquals(listOf(second), navigation.path)
        // A parcel the app showed by itself becomes the person's once they open it.
        navigation = DeliveriesNavigation()
        navigation.settle(DeliveriesArrangement.SideBySide, cards = listOf(first, second))
        assertTrue(navigation.shownAutomatically)
        navigation.open(first)
        assertFalse(navigation.shownAutomatically)
        assertEquals(first, navigation.chosen)
    }

    @Test
    fun besideTheListTheFirstCardShowsUntilSomeoneChoosesAnother() {
        val next = UUID.randomUUID()
        val other = UUID.randomUUID()
        val later = UUID.randomUUID()
        val navigation = DeliveriesNavigation()
        // Nothing to show while the list shows no card.
        navigation.settle(DeliveriesArrangement.SideBySide, cards = emptyList())
        assertNull(navigation.shown)
        navigation.settle(DeliveriesArrangement.SideBySide, cards = listOf(next, other))
        assertEquals(next, navigation.shown)
        assertTrue(navigation.shownAutomatically)
        assertNull(navigation.chosen)
        // It stays while its card stays, wherever the card goes, and gives way to the first once it leaves.
        navigation.settle(DeliveriesArrangement.SideBySide, cards = listOf(other, next))
        assertEquals(next, navigation.shown)
        navigation.settle(DeliveriesArrangement.SideBySide, cards = listOf(other, later))
        assertEquals(other, navigation.shown)
        assertTrue(navigation.shownAutomatically)
        navigation.settle(DeliveriesArrangement.SideBySide, cards = emptyList())
        assertNull(navigation.shown)
        // A parcel someone chose stays, even once a search hides its card.
        navigation.open(later)
        navigation.settle(DeliveriesArrangement.SideBySide, cards = listOf(next))
        assertEquals(later, navigation.shown)
        assertEquals(later, navigation.chosen)
        // Over the list, nothing opens by itself.
        val stacked = DeliveriesNavigation()
        stacked.settle(DeliveriesArrangement.Stacked, cards = listOf(next, other))
        assertNull(stacked.shown)
    }

    @Test
    fun aFoldKeepsTheParcelSomeoneChoseAndPutsBackOneShownByItself() {
        val next = UUID.randomUUID()
        val other = UUID.randomUUID()
        val navigation = DeliveriesNavigation()
        navigation.settle(DeliveriesArrangement.SideBySide, cards = listOf(next, other))
        navigation.settle(DeliveriesArrangement.Stacked, cards = listOf(next, other))
        assertEquals(emptyList<UUID>(), navigation.path)
        assertFalse(navigation.shownAutomatically)
        // Unfolded again, the first card shows by itself once more.
        navigation.settle(DeliveriesArrangement.SideBySide, cards = listOf(next, other))
        assertEquals(next, navigation.shown)
        navigation.open(other)
        navigation.settle(DeliveriesArrangement.Stacked, cards = listOf(next, other))
        assertEquals(listOf(other), navigation.path)
        navigation.settle(DeliveriesArrangement.SideBySide, cards = listOf(next, other))
        assertEquals(other, navigation.chosen)
    }

    @Test
    fun theParcelOpenWhenTheAppWasClosedOpensAgainOnceItIsBack() {
        val open = UUID.randomUUID()
        val other = UUID.randomUUID()
        var navigation = DeliveriesNavigation()
        // The parcels aren't back yet: it waits.
        assertFalse(navigation.restore(open, among = emptyList()))
        assertNull(navigation.shown)
        assertTrue(navigation.restore(open, among = listOf(other, open)))
        assertEquals(open, navigation.chosen)
        // It takes the place of the one shown by itself beside the list.
        navigation = DeliveriesNavigation()
        navigation.settle(DeliveriesArrangement.SideBySide, cards = listOf(other, open))
        assertTrue(navigation.restore(open, among = listOf(other, open)))
        assertEquals(open, navigation.chosen)
        // Gone since, it opens nothing.
        navigation = DeliveriesNavigation()
        assertTrue(navigation.restore(open, among = listOf(other)))
        assertNull(navigation.shown)
        // A parcel someone opened first, from a notification or a link, stays.
        navigation = DeliveriesNavigation()
        navigation.open(other)
        assertTrue(navigation.restore(open, among = listOf(other, open)))
        assertEquals(other, navigation.chosen)
    }

    @Test
    fun aParcelThatLeavesTheListFromBesideItGivesWayToTheCardAfterIt() {
        val a = UUID.randomUUID()
        val b = UUID.randomUUID()
        val c = UUID.randomUUID()
        val cards = listOf(a, b, c)
        val navigation = DeliveriesNavigation()
        navigation.open(b)
        navigation.leave(b, cards = cards)
        assertEquals(c, navigation.shown)
        // Nobody chose the next one: a fold puts it back into the list, and no parcel view is counted.
        assertTrue(navigation.shownAutomatically)
        navigation.open(c)
        navigation.leave(c, cards = cards)
        assertEquals("the last card gives way to the one before it", b, navigation.shown)
        // One brought back from the archive had no card: the first shows.
        val archived = UUID.randomUUID()
        navigation.open(archived)
        navigation.leave(archived, cards = cards)
        assertEquals(a, navigation.shown)
        navigation.open(a)
        navigation.leave(a, cards = listOf(a))
        assertNull("the only card leaves nothing to show", navigation.shown)
        // A page that is no longer the one shown changes nothing.
        navigation.open(a)
        navigation.leave(b, cards = cards)
        assertEquals(a, navigation.chosen)
    }

    @Test
    fun besideTheListAnArchiveThatShowsItsRowsCountsAsTheListsOwn() {
        val onTheWay = makeParcel()
        val older = makeParcel().copy(archivedAt = "2026-08-09T12:00:00Z")
        val newer = makeParcel().copy(archivedAt = "2026-08-10T12:00:00Z")
        val parcels = listOf(onTheWay, older, newer)
        val all = DeliveryListLayout(parcels, query = "", status = ParcelStatusFilter.All, carrier = null, sort = ParcelSort.Priority, featuresNext = true)
        val archive = all.sections.first { it.kind == ParcelSectionKind.Archived }.parcels.map { it.id }
        assertEquals(setOf(older.id, newer.id), archive.toSet())
        // A closed shelf shows no row; an open one, or a filter, shows them after the cards.
        assertEquals(listOf(onTheWay.id), all.rows(archiveOpen = false))
        assertEquals(listOf(onTheWay.id) + archive, all.rows(archiveOpen = true))
        // The Archived filter shows rows and no card: the first row shows beside the list by itself.
        val archived = DeliveryListLayout(parcels, query = "", status = ParcelStatusFilter.Archived, carrier = null, sort = ParcelSort.Priority, featuresNext = false)
        assertEquals(emptyList<UUID>(), archived.cards)
        val rows = archived.rows(archiveOpen = true)
        assertEquals(setOf(older.id, newer.id), rows.toSet())
        val navigation = DeliveriesNavigation()
        navigation.settle(DeliveriesArrangement.SideBySide, cards = rows)
        assertEquals(rows.firstOrNull(), navigation.shown)
        assertTrue(navigation.shownAutomatically)
        // Leaving the filter, the first card takes its place.
        navigation.settle(DeliveriesArrangement.SideBySide, cards = all.rows(archiveOpen = false))
        assertEquals(onTheWay.id, navigation.shown)
    }

    @Test
    fun theArrowKeysGoThroughTheCardsBesideTheList() {
        val a = UUID.randomUUID()
        val b = UUID.randomUUID()
        val c = UUID.randomUUID()
        val archived = UUID.randomUUID()
        val cards = listOf(a, b, c)
        var navigation = DeliveriesNavigation()
        navigation.open(b)
        assertEquals(c, navigation.step(1, through = cards))
        // It opens, as a tap on its card does: it stays open through a fold, and comes back after a relaunch.
        assertEquals(c, navigation.chosen)
        assertEquals(b, navigation.step(-1, through = cards))
        assertEquals(a, navigation.step(-1, through = cards))
        assertEquals(a, navigation.chosen)
        // From the first card shown by itself, ↓ opens the second.
        navigation = DeliveriesNavigation()
        navigation.settle(DeliveriesArrangement.SideBySide, cards = cards)
        assertTrue(navigation.shownAutomatically)
        assertEquals(b, navigation.step(1, through = cards))
        assertEquals(b, navigation.chosen)
        navigation.open(a)
        // Past either end, the parcel shown stays.
        assertNull(navigation.step(-1, through = cards))
        assertEquals(a, navigation.chosen)
        navigation.open(c)
        assertNull(navigation.step(1, through = cards))
        assertEquals(c, navigation.chosen)
        // From a parcel with no card, or none, to the first card going down and the last going up.
        navigation.open(archived)
        assertEquals(a, navigation.step(1, through = cards))
        navigation.open(archived)
        assertEquals(c, navigation.step(-1, through = cards))
        navigation = DeliveriesNavigation()
        assertEquals(c, navigation.step(-1, through = cards))
        // No card, nowhere to go.
        navigation = DeliveriesNavigation()
        assertNull(navigation.step(1, through = emptyList()))
        assertNull(navigation.shown)
    }

    @Test
    fun theDeliveriesKeysActOnlyOnWhatIsInFront() {
        val a = UUID.randomUUID()
        val b = UUID.randomUUID()
        val automatic = DeliveriesNavigation()
        automatic.settle(DeliveriesArrangement.SideBySide, cards = listOf(a, b))
        val chosen = DeliveriesNavigation()
        chosen.open(a)
        fun keys(
            free: Boolean = true,
            searching: Boolean = false,
            typing: Boolean = false,
            arrangement: DeliveriesArrangement = DeliveriesArrangement.Stacked,
            navigation: DeliveriesNavigation = DeliveriesNavigation(),
            cards: List<UUID> = listOf(a, b),
            hasParcels: Boolean = true,
            canUndo: Boolean = false,
        ): Set<DeliveriesKey> = DeliveriesKey.available(
            free = free, searching = searching, typing = typing, arrangement = arrangement,
            navigation = navigation, cards = cards, hasParcels = hasParcels, canUndo = canUndo,
        )
        val stacked = DeliveriesArrangement.Stacked
        val sideBySide = DeliveriesArrangement.SideBySide
        // The list by itself: Add and Find, and Undo while an archive's toast shows.
        assertEquals(setOf(DeliveriesKey.Add, DeliveriesKey.Find), keys())
        assertEquals(setOf(DeliveriesKey.Add, DeliveriesKey.Find, DeliveriesKey.Undo), keys(canUndo = true))
        assertEquals(setOf(DeliveriesKey.Add), keys(hasParcels = false))
        // A parcel open over the list, which has its own keys: the list's toast and search are under it.
        assertEquals(setOf(DeliveriesKey.Add), keys(arrangement = stacked, navigation = chosen, canUndo = true))
        // Beside a parcel, the arrows, and Return while it shows by itself.
        assertEquals(
            setOf(DeliveriesKey.Add, DeliveriesKey.Find, DeliveriesKey.Previous, DeliveriesKey.Next, DeliveriesKey.Open),
            keys(arrangement = sideBySide, navigation = automatic),
        )
        assertEquals(
            setOf(DeliveriesKey.Add, DeliveriesKey.Find, DeliveriesKey.Previous, DeliveriesKey.Next),
            keys(arrangement = sideBySide, navigation = chosen),
        )
        assertEquals(setOf(DeliveriesKey.Add, DeliveriesKey.Find), keys(arrangement = sideBySide, navigation = chosen, cards = emptyList()))
        // Typing in the search, the field keeps the keys it edits with; Escape closes the search, typing or not.
        assertEquals(
            setOf(DeliveriesKey.Add, DeliveriesKey.Find, DeliveriesKey.CloseSearch),
            keys(searching = true, typing = true, arrangement = sideBySide, navigation = automatic, canUndo = true),
        )
        assertEquals(setOf(DeliveriesKey.Add, DeliveriesKey.Find, DeliveriesKey.CloseSearch), keys(searching = true))
        // A parcel open over the list keeps Escape for going back, though the search under it is open.
        assertEquals(setOf(DeliveriesKey.Add), keys(searching = true, arrangement = stacked, navigation = chosen))
        // Another tab, or a sheet, an alert or a question over the deliveries or their parcel: none.
        assertEquals(emptySet<DeliveriesKey>(), keys(free = false, arrangement = sideBySide, navigation = automatic, canUndo = true))
    }

    @Test
    fun onlyAParcelSomeoneOpensAndTheListTheyGoBackToCountAsScreens() {
        val a = UUID.randomUUID()
        val b = UUID.randomUUID()
        val list = DeliveriesNavigation()
        val opened = list.copy()
        opened.open(a)
        assertEquals("parcel", opened.screen(after = list))
        assertNull(opened.screen(after = opened))
        assertEquals("deliveries", list.screen(after = opened))
        val other = opened.copy()
        other.open(b)
        assertEquals("parcel", other.screen(after = opened))
        // Shown by itself, beside the list.
        val automatic = list.copy()
        automatic.settle(DeliveriesArrangement.SideBySide, cards = listOf(a, b))
        assertNull(automatic.screen(after = list))
        val chosen = automatic.copy()
        chosen.open(a)
        assertEquals("parcel", chosen.screen(after = automatic))
        // After archiving, the next parcel shows by itself beside the list, which was there all along.
        val next = chosen.copy()
        next.leave(a, cards = listOf(a, b))
        assertNull(next.screen(after = chosen))
        // Folded, it goes back into the list, which was in view already.
        val folded = next.copy()
        folded.settle(DeliveriesArrangement.Stacked, cards = listOf(b))
        assertNull(folded.screen(after = next))
    }

    @Test
    fun aCardsMenuOffersWhatItsPageDoesAndOpensAndSharesTheParcelToo() {
        val parcel = makeParcel()
        assertEquals(
            listOf(
                listOf(ParcelMenuItem.Open),
                listOf(ParcelMenuItem.Rename, ParcelMenuItem.Share, ParcelMenuItem.CopyTrackingNumber, ParcelMenuItem.ChangeCarrier),
                listOf(ParcelMenuItem.Archive),
                listOf(ParcelMenuItem.Delete),
            ),
            ParcelMenuItem.menu(parcel, onCard = true),
        )
        // The page has its own share button, and is open already.
        assertEquals(
            listOf(
                listOf(ParcelMenuItem.Rename, ParcelMenuItem.CopyTrackingNumber, ParcelMenuItem.ChangeCarrier),
                listOf(ParcelMenuItem.Archive),
                listOf(ParcelMenuItem.Delete),
            ),
            ParcelMenuItem.menu(parcel, onCard = false),
        )
        // An archived parcel is brought back rather than archived again.
        val archived = parcel.copy(archivedAt = "2026-08-02T10:00:00Z")
        assertEquals(listOf(ParcelMenuItem.Restore), ParcelMenuItem.menu(archived, onCard = true)[2])
        assertEquals(listOf(ParcelMenuItem.Restore), ParcelMenuItem.menu(archived, onCard = false)[1])
    }

    /**
     * Adapted: the menu bar's Parcel menu (MenuBar.swift's `ParcelMenu`) belongs to an iPad and a Mac. What it
     * shows is the card's menu without Open, for a parcel of one's own and for a reader's, which this checks;
     * its dimmed placeholder has no Android counterpart.
     */
    @Test
    fun theMenuBarsParcelMenuIsTheCardsWithoutOpenAndDimsTheSameItemsWithoutAParcel() {
        fun items(parcel: Parcel, reader: Boolean): List<List<ParcelMenuItem>> =
            ParcelMenuItem.menu(parcel, onCard = true, reader = reader).filter { it != listOf(ParcelMenuItem.Open) }
        val parcel = makeParcel()
        assertEquals(
            listOf(
                listOf(ParcelMenuItem.Rename, ParcelMenuItem.Share, ParcelMenuItem.CopyTrackingNumber, ParcelMenuItem.ChangeCarrier),
                listOf(ParcelMenuItem.Archive),
                listOf(ParcelMenuItem.Delete),
            ),
            items(parcel, reader = false),
        )
        // Someone else's parcel, followed through its link: no carrier to change, nothing to copy the link hides.
        val hidden = parcel.copy(trackingNumber = "")
        assertEquals(
            listOf(listOf(ParcelMenuItem.Rename), listOf(ParcelMenuItem.Archive), listOf(ParcelMenuItem.Delete)),
            items(hidden, reader = true),
        )
    }

    @Test
    fun aCopiedTrackingNumberIsTheOneItsCarriersSiteTakes() {
        assertEquals("1Z999AA10123456784", TrackingNumberCopy.text("1Z999AA10123456784", carrier = CarrierId.Ups))
        // PostLogistics prints its references with a dash, and its site searches for them so.
        assertEquals("12345678-001", TrackingNumberCopy.text("12345678001", carrier = CarrierId.Postlogistics))
    }

    @Test
    fun textDroppedOnTheDeliveriesOpensAddOnlyWhenItHoldsATrackingOrOrderNumber() {
        val catalog = CarrierCatalog(cacheFile = null)
        assertEquals(FirstParcelRequest.Paste("1Z999AA10123456784"), FirstParcelRequest.drop(listOf(" 1Z999AA10123456784\n"), catalog = catalog))
        val message = "Your order has shipped: 1Z999AA10123456784"
        assertEquals(FirstParcelRequest.Paste(message), FirstParcelRequest.drop(listOf(message), catalog = catalog))
        // A link and the words that came with it are read together, each once, one to a line, as the share extension reads them.
        val link = "https://www.ups.com/track?tracknum=1Z999AA10123456784"
        assertEquals(
            FirstParcelRequest.Paste("$link\nTrack your parcel"),
            FirstParcelRequest.drop(listOf(link, "Track your parcel", link), catalog = catalog),
        )
        // An order number opens Add, which says where to find the tracking number.
        val order = "Order #302-4571983-2294617 is confirmed"
        assertEquals(FirstParcelRequest.Paste(order), FirstParcelRequest.drop(listOf(order), catalog = catalog))
        // So does a message's one number whose shape proves no carrier: Add asks the carriers.
        val uncertain = "Your order has shipped!\nTrack it with DHL: 1234567899"
        assertEquals(FirstParcelRequest.Paste(uncertain), FirstParcelRequest.drop(listOf(uncertain), catalog = catalog))
        // Nothing to track: the drop is refused, and Add stays closed.
        assertNull(FirstParcelRequest.drop(emptyList(), catalog = catalog))
        assertNull(FirstParcelRequest.drop(listOf(" ", "\n"), catalog = catalog))
        assertNull(FirstParcelRequest.drop(listOf("Your shipment arrives Monday"), catalog = catalog))
        // No more than Add takes in from a paste.
        val long = "Your order has shipped: 1Z999AA10123456784. " + "More news follows. ".repeat(200)
        val dropped = FirstParcelRequest.drop(listOf(long), catalog = catalog) as? FirstParcelRequest.Paste
            ?: return fail("a long message with a number is refused")
        assertEquals(FirstParcelRequest.pasteLimit, dropped.text.characterCount)
    }
}
