import SwiftUI
#if os(iOS)
import UIKit
#endif

struct ParcelDetailView: View {
    let parcelID: UUID
    let transition: Namespace.ID
    /// A made-up parcel, shown as a followed one would be. Nobody follows it, so nothing on it can be changed, shared or
    /// checked, and it asks nothing.
    var sample: Parcel?
    /// Set when the page stands beside the list rather than over it, and lets the parcel go when it leaves the list.
    var release: (() -> Void)?
    /// Told whether the page has something of its own open over it, which the list's keys then leave alone.
    var covering: Binding<Bool>?
    /// The deliveries are in front and the list has nothing open over it. Beside the list, the page asks for no rating
    /// while the list has something open, as the list asks for none while the page has.
    var listFree = true

    @EnvironmentObject private var store: ParcelStore
    @EnvironmentObject private var localizer: Localizer
    @EnvironmentObject private var session: SessionStore
    @EnvironmentObject private var links: ParcelLinkStore
    @EnvironmentObject private var invitation: FriendInvitationStore
    @Environment(\.dismiss) private var dismiss
    @Environment(\.openURL) private var openURL
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @Environment(\.dynamicTypeSize) private var typeSize
    @Environment(\.keyboardInFront) private var keyboardInFront
    @Environment(\.scenePhase) private var scenePhase

    @State private var showingTitleEditor = false
    @State private var editedTitle = ""
    @State private var copiedNumber: String?
    @State private var copiedPickupPoint = false
    @State private var working = false
    @State private var errorMessage: String?
    @State private var carrierEditor: CarrierEditorRequest?
    @State private var showingDeleteConfirmation = false
    @State private var notificationAnimation = 0
    @State private var showingMap = false
    @State private var showingShare = false
    @State private var showingAlerts = false
    @State private var emailOffer = DeliveryEmailOffer.Phase.open
    @State private var atlas: WorldAtlas?
    /// Where the delivering carrier's mark stands on the card, which the map keeps clear of.
    @State private var deliveryMark: CGRect?
    /// Where the globe, share and bell buttons stand on the card, which the map's names keep clear of.
    @State private var buttonRow: CGRect?
    /// How tall the card's top row stands, which at the largest text sizes holds the buttons on a line of their own.
    @State private var headerHeight: CGFloat = 0
    /// How wide the page stands, which decides whether the history goes beside the card.
    @State private var pageWidth: CGFloat = 0

    @ObservedObject private var catalog = CarrierCatalog.shared
    @StateObject private var feedback = ParcelFeedbackModel()

    var body: some View {
        ZStack {
            ExperimentalBackdrop()
            if let parcel {
                let columns = ParcelPageColumns.fit(width: pageWidth, besideList: release != nil,
                                                    largeText: typeSize.isAccessibilitySize, history: showsHistory(parcel))
                ScrollView {
                    VStack(spacing: 26) {
                        // Given the room, the history stands beside the card rather than under it.
                        let layout = columns
                            ? AnyLayout(HStackLayout(alignment: .top, spacing: ParcelPageColumns.gap))
                            : AnyLayout(VStackLayout(alignment: .leading, spacing: 20))
                        layout {
                            liveParcelPass(parcel, divided: !columns)
                                .frame(maxWidth: .infinity, alignment: .leading)
                            VStack(alignment: .leading, spacing: 20) {
                                if showsHistory(parcel) {
                                    ParcelJournal(parcel: parcel, tint: identity(parcel).ink)
                                }
                                syncStatus(parcel, tint: identity(parcel).ink)
                            }
                            .frame(maxWidth: .infinity, alignment: .leading)
                        }
                        .animation(reduceMotion ? nil : .easeInOut(duration: 0.2), value: emailOffer)
                        .padding(18)
                        .background(Brand.paper, in: RoundedRectangle(cornerRadius: 26))
                        // Below the page, on the ground it lies on, Pip asks whether it is right.
                        if feedback.standing != nil {
                            ParcelFeedbackQuestionView(model: feedback)
                                .padding(.horizontal, 8)
                                .frame(maxWidth: 640 - 32)
                        }
                    }
                    .padding(.horizontal, 16)
                    .frame(maxWidth: columns ? ParcelPageColumns.widest : 640)
                    .frame(maxWidth: .infinity)
                    .padding(.top, 10)
                    .padding(.bottom, 36)
                }
                .scrollIndicators(.hidden)
                .onGeometryChange(for: CGFloat.self) { $0.size.width } action: { pageWidth = $0 }
                .softTopEdge()
                #if DEBUG
                // The question stands where the page ends: a look at it starts there.
                .defaultScrollAnchor(ParcelFeedbackPreview.variant == nil ? nil : .bottom)
                #endif
            } else {
                ContentUnavailableView(
                    localizer.text("common.parcel"),
                    systemImage: "shippingbox",
                    description: Text(localizer.text("native.parcelMissing"))
                )
            }
        }
        .background { if keyboard { keys } }
        .focusedSceneValue(\.parcelMenu, MenuBar.shown && keyboard ? parcelMenu : nil)
        .onChange(of: covered, initial: true) { _, covered in covering?.wrappedValue = covered }
        .onDisappear { covering?.wrappedValue = false }
        .parcelFeedback(feedback, busy: showingShare || showingAlerts || showingMap || carrierEditor != nil
            || showingTitleEditor || showingDeleteConfirmation || errorMessage != nil)
        .onChange(of: parcel.flatMap(feedbackPage), initial: true) { _, page in
            feedback.show(page, through: parcel.map { store.feedbackClient(for: $0) })
        }
        // A "Yes" that was the moment to ask for a rating: once its thanks has gone and nothing else is over the page.
        .askingForReview(due: feedback.reviewDue, ready: reviewReady) { feedback.takeReviewMoment() }
        .navigationTitle(windowTitle)
        .navigationBarTitleDisplayMode(.inline)
        // The card says which parcel this is: no bar names the page over it.
        .toolbar(removing: .title)
        .navigationTransition(.zoom(sourceID: parcelID, in: transition))
        // Over the list, the page has the screen to itself; beside it, the tabs stay at hand.
        .toolbar(release == nil ? .hidden : .automatic, for: .tabBar)
        .toolbar {
            if let parcel, sample == nil {
                ToolbarItem(placement: .topBarTrailing) {
                    Menu {
                        ParcelMenuButtons(
                            items: ParcelMenuItem.menu(for: parcel, onCard: false, reader: store.viewerLink(for: parcel) != nil),
                            busy: working, shortcuts: keyboard
                        ) { chose($0, on: parcel) }
                    } label: {
                        Image(systemName: "ellipsis")
                    }
                    #if os(macOS)
                    .menuIndicator(.hidden)
                    #endif
                    // On an iPad the question is a popover: off the menu, not off the page, where it covered the list.
                    .parcelDeleteConfirmation(isPresented: $showingDeleteConfirmation) { delete(parcel) }
                }
            }
        }
        .alert(localizer.text("native.errorTitle"), isPresented: Binding(
            get: { errorMessage != nil },
            set: { if !$0 { errorMessage = nil } }
        )) {
            Button(localizer.text("common.close"), role: .cancel) { errorMessage = nil }
        } message: {
            Text(errorMessage ?? "")
        }
        .task(id: needsMap) {
            guard needsMap, atlas == nil else { return }
            let loaded = await WorldAtlas.bundled.value
            withAnimation(.easeOut(duration: 0.5)) { atlas = loaded }
        }
        .fullScreenCover(isPresented: $showingMap) {
            if let parcel, let atlas {
                ParcelMapScreen(
                    atlas: atlas, route: ParcelRoute(parcel: parcel, atlas: atlas, language: localizer.language),
                    stage: parcel.currentStage, accent: identity(parcel).ink
                )
                .environmentObject(localizer)
            }
        }
        .sheet(item: $carrierEditor) { request in
            if let parcel {
                ChangeCarrierView(parcel: parcel, initialCarrier: request.initialCarrier)
                    .environmentObject(store)
                    .environmentObject(localizer)
            }
        }
        .sheet(isPresented: $showingShare) {
            if let parcel {
                ParcelShareSheet(parcel: parcel, client: store.shareClient(for: parcel), demo: store.isDemo)
                    .environmentObject(localizer)
            }
        }
        .sheet(isPresented: $showingAlerts) {
            if let parcel {
                ParcelAlertsSheet(parcelID: parcel.id)
                    .environmentObject(store)
                    .environmentObject(localizer)
            }
        }
        #if DEBUG
        .task {
            if ParcelSharePreview.variant != nil { showingShare = true }
        }
        #endif
        .parcelRenameAlert(isPresented: $showingTitleEditor, name: $editedTitle) { label in
            if let parcel { run { try await store.rename(parcel, label: label) } }
        }
    }

    private var parcel: Parcel? { sample ?? store.parcels.first { $0.id == parcelID || $0.carrierData?.originalPackageID == parcelID } }

    /// The page shows what the carrier answered: a carrier is being asked, or one has answered.
    private func showsHistory(_ parcel: Parcel) -> Bool {
        catalog.tracksAutomatically(parcel.activeTrackingCarrier) || parcel.hasCarrierUpdate
    }

    /// What the question at the end of the page is asked about, or nil while it asks nothing.
    private func feedbackPage(_ parcel: Parcel) -> ParcelFeedbackModel.Page? {
        // The demo's stories and the sample are made up: nothing is asked of them.
        var madeUp = store.isDemo || sample != nil
        #if DEBUG
        if ParcelFeedbackPreview.variant != nil { madeUp = false }
        #endif
        return ParcelFeedbackModel.Page(
            parcel: parcel, subject: .parcel(parcel.id), showsHistory: showsHistory(parcel), madeUp: madeUp,
            carrier: catalog.info(for: parcel.displayedCarrier, language: localizer.language).displayName,
            language: localizer.language
        )
    }

    /// The page could ask for a rating now (`ReviewPrompt`): it is in front of the reader with nothing over it, nothing
    /// said at its foot and no offer of the delivery email, and nothing in the app stands in the way.
    private var reviewReady: Bool {
        guard let parcel, sample == nil, listFree, scenePhase == .active, !covered, feedback.word == nil else { return false }
        if let email = store.deliveryEmail, emailOffer == .accepted || email.isOffered(on: parcel) { return false }
        return ReviewPrompt.settled(session: session, store: store, links: links, invitation: invitation)
    }

    /// The card, from its top left corner: where its map is drawn from.
    private static let cardSpace = "parcel-card"

    /// A scan has a place, so the card shows the route once the map data has loaded.
    private var needsMap: Bool { parcel?.trackingEvents.contains { $0.place != nil } ?? false }

    private func openMap() {
        DeliveryAnalytics.shared.action("parcel-map-open")
        showingMap = true
    }

    private func identity(_ parcel: Parcel) -> CarrierVisualIdentity {
        CarrierVisualIdentity.of(parcel.displayedCarrier, catalog: catalog, language: localizer.language)
    }

    /// The card and what is known of the shipment, ruled off from the history under it when `divided`.
    private func liveParcelPass(_ parcel: Parcel, divided: Bool) -> some View {
        let branding = identity(parcel)
        let carrier = catalog.info(for: parcel.activeTrackingCarrier, language: localizer.language)
        // Handed from one carrier to another, or about to be, the card carries both marks and says who delivers, as its card in the list does.
        let delivering = parcel.deliveringCarrier
        let delivery = delivering.map { CarrierVisualIdentity.of($0, catalog: catalog, language: localizer.language) }
        // With a second mark over its map, the card is two lines taller, all of them map.
        let room = delivery == nil ? 0 : RouteEngraving.handoverRoom
        // At the largest text sizes the buttons' line under the marks pushes the map down by as much as it adds.
        let lower = typeSize.isAccessibilitySize ? max(0, headerHeight - 44) : 0
        let deliveryLine = delivering.map {
            localizer.text("parcel.deliveryCarrier", ["carrier": catalog.info(for: $0, language: localizer.language).displayName])
        }
        // A made-up number has no page at its carrier, and neither has one a link hides.
        let trackingLinks = sample == nil && !parcel.trackingNumber.isEmpty ? catalog.trackingLinks(for: parcel, language: localizer.language) : []
        let placed = parcel.hasMappedPlace
        let route = placed ? atlas.map { ParcelRoute(parcel: parcel, atlas: $0, language: localizer.language) } : nil
        let email = store.deliveryEmail
        // While the account's email is on, the bell opens the parcel's alerts instead of muting.
        let opensAlerts = email?.bellOpensAlerts(for: parcel) == true
        // Someone else's parcel, followed through its link: the link is shared as it is, and the carrier is theirs to change.
        let readerLink = store.viewerLink(for: parcel)
        let editable = sample == nil && readerLink == nil

        return VStack(alignment: .leading, spacing: 12) {
            VStack(alignment: .leading, spacing: 16) {
                // The buttons sit a tap apart, as on the web, which leaves a long carrier name its line. At the
                // largest text sizes the name takes the whole line, and the buttons go under it.
                let header = typeSize.isAccessibilitySize
                    ? AnyLayout(VStackLayout(alignment: .leading, spacing: 4)) : AnyLayout(HStackLayout(spacing: 0))
                header {
                    let marks = CarrierFleetMark(identity: branding)
                        .overlay(alignment: .topLeading) {
                            if let delivery {
                                // Under the first mark, which stays where every card has it: an unseen copy of it, as wide
                                // and on as many lines, holds its room.
                                VStack(alignment: .leading, spacing: 7) {
                                    CarrierFleetMark(identity: branding).hidden()
                                    CarrierFleetMark(identity: delivery)
                                        .fixedSize()
                                        .onGeometryChange(for: CGRect.self) { $0.frame(in: .named(Self.cardSpace)) } action: { deliveryMark = $0 }
                                }
                                .fixedSize(horizontal: false, vertical: true)
                            }
                        }
                    if editable {
                        Button { carrierEditor = CarrierEditorRequest() } label: { marks }
                            .buttonStyle(.plain)
                            .accessibilityLabel([
                                localizer.text("detail.changeCarrierFrom", [
                                    "carrier": catalog.info(for: parcel.displayedCarrier, language: localizer.language).displayName,
                                ]),
                                deliveryLine,
                            ].compactMap { $0 }.joined(separator: ". "))
                    } else {
                        marks
                    }
                    if !typeSize.isAccessibilitySize { Spacer(minLength: 8) }
                    HStack(spacing: 0) {
                        if placed {
                            Button(action: openMap) {
                                Image(systemName: "globe")
                                    .font(.system(size: 17))
                                    .frame(width: 44, height: 44)
                                    .contentShape(Rectangle())
                            }
                            .buttonStyle(.plain)
                            .foregroundStyle(branding.ink.opacity(0.75))
                            .disabled(route == nil)
                            .accessibilityLabel(localizer.text("map.open"))
                        }
                        if let readerLink {
                            ShareLink(item: ParcelLinkRoute.address(id: readerLink)) {
                                Image(systemName: "square.and.arrow.up")
                                    .font(.system(size: 16))
                                    .frame(width: 44, height: 44)
                                    .contentShape(Rectangle())
                            }
                            .buttonStyle(.plain)
                            .foregroundStyle(branding.ink.opacity(0.75))
                            .accessibilityLabel(localizer.text("link.shareAria"))
                        } else if sample == nil {
                            Button { showingShare = true } label: {
                                Image(systemName: "square.and.arrow.up")
                                    .font(.system(size: 16))
                                    .frame(width: 44, height: 44)
                                    .contentShape(Rectangle())
                            }
                            .buttonStyle(.plain)
                            .foregroundStyle(branding.ink.opacity(0.75))
                            .accessibilityLabel(localizer.text("link.shareAria"))
                        }
                        // Alerts need an account: a parcel followed without one has no bell.
                        if !store.isGuest, sample == nil {
                            Button {
                                if opensAlerts {
                                    showingAlerts = true
                                    return
                                }
                                run {
                                    try await store.setMuted(parcel, muted: !parcel.notificationsMuted)
                                    notificationAnimation += 1
                                    Haptics.selection()
                                }
                            } label: {
                                ParcelNotificationBell(muted: opensAlerts ? parcel.allAlertsMuted : parcel.notificationsMuted,
                                                       trigger: notificationAnimation)
                                    .frame(width: 44, height: 44)
                                    .contentShape(Rectangle())
                            }
                            .buttonStyle(.plain)
                            .foregroundStyle(branding.ink.opacity(0.75))
                            .disabled(working)
                            .accessibilityLabel(localizer.text(opensAlerts ? "email.parcel.open"
                                : parcel.notificationsMuted ? "detail.unmute" : "detail.mute"))
                        }
                    }
                    .onGeometryChange(for: CGRect.self) { $0.frame(in: .named(Self.cardSpace)) } action: { buttonRow = $0 }
                }
                .onGeometryChange(for: CGFloat.self) { $0.size.height } action: { headerHeight = $0 }
                if placed {
                    // Room for the route engraved behind this part of the card.
                    Color.clear.frame(height: 68 + room).allowsHitTesting(false)
                }
                AutomaticCarrierNotice(parcel: parcel)
                if editable, let needed = parcel.carrierData?.routing?.providerInputNeeded, !parcel.isArchived {
                    VStack(alignment: .leading, spacing: 8) {
                        Text(localizer.text("detail.providerInputNeeded", ["provider": needed.provider]))
                        Button(localizer.text("detail.inputNeededAction")) { carrierEditor = CarrierEditorRequest() }
                    }.font(.footnote)
                }
                if editable {
                    if let needed = parcel.inputNeededPrompt {
                        inputNeededPrompt(needed.carrier, text: needed.carrier == parcel.carrier
                            ? "detail.carrierInputNeeded" : "detail.inputNeeded", tint: branding.ink)
                    } else if let suggested = parcel.inputSuggestedPrompt {
                        inputNeededPrompt(suggested.carrier, text: "detail.inputSuggested", tint: branding.ink)
                    }
                }
                HStack(alignment: .center, spacing: 18) {
                    let name = Text(parcel.label.nonEmpty ?? localizer.text("common.parcel"))
                        .font(.title2.weight(.semibold))
                        .fixedSize(horizontal: false, vertical: true)
                    // The name is tapped to change it, as the ••• menu's item does.
                    Group {
                        if sample == nil {
                            Button { chose(.rename, on: parcel) } label: { name.multilineTextAlignment(.leading) }
                                .buttonStyle(.plain)
                                .accessibilityHint(localizer.text("detail.editTitle"))
                        } else {
                            name
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                    DeliveryPostageStamp(parcel: parcel, identity: branding, width: 50, appeared: true)
                }
                let sender = parcel.carrierData?.senderName?.nonEmpty
                if sender != nil || deliveryLine != nil {
                    VStack(alignment: .leading, spacing: 2) {
                        if let sender { Text(localizer.text("parcel.sender", ["sender": sender])) }
                        if let deliveryLine { Text(deliveryLine) }
                    }
                    .font(.caption).foregroundStyle(.secondary)
                    .fixedSize(horizontal: false, vertical: true)
                }
                if let postcode = parcel.refusedPostcode ?? (parcel.carrier == .dpd
                    && parcel.carrierData?.dpdPostcodeVerified == false ? parcel.dpdPostcode?.nonEmpty : nil) {
                    VStack(alignment: .leading, spacing: 4) {
                        Text(localizer.text(parcel.refusedPostcode == nil ? "detail.postcodeNotVerified" : "detail.postcodeRefused",
                                            ["carrier": carrier.displayName, "postcode": postcode]))
                            .font(.caption).foregroundStyle(.secondary)
                            .fixedSize(horizontal: false, vertical: true)
                        if parcel.archivedAt == nil, editable {
                            Button(localizer.text("detail.editPostcode")) { carrierEditor = CarrierEditorRequest() }
                                .font(.caption.weight(.semibold))
                        }
                    }
                }
                VStack(alignment: .leading, spacing: 6) {
                    Text(localizer.parcelStatus(parcel))
                        .font(.subheadline)
                    if let date = localizer.parcelCompletionDate(parcel) ?? localizer.parcelDeliveryEstimate(parcel) {
                        Text(date).font(.caption)
                    }
                }
                .foregroundStyle(branding.ink)
                .fixedSize(horizontal: false, vertical: true)
                // A label a day old or more: the sender still has the parcel, and the carrier can tell nothing until they hand it over.
                if !parcel.isArchived, parcel.awaitingHandover() {
                    Text(localizer.text("parcel.awaitingHandover",
                                        ["carrier": catalog.info(for: parcel.displayedCarrier, language: localizer.language).displayName]))
                        .font(.caption).foregroundStyle(.secondary)
                        .fixedSize(horizontal: false, vertical: true)
                }
                if let waits = carrierWaits(parcel) {
                    ParcelFlag(text: localizer.text(waits.stage == .failedAttempt ? "attention.failed_attempt" : "attention.exception"),
                               symbol: "exclamationmark.triangle")
                }
                if parcel.attention() == .generated {
                    ParcelFlag(text: localizer.text("attention.generated"), symbol: "exclamationmark.triangle")
                    Text(localizer.text("link.generated",
                                        ["carrier": catalog.info(for: parcel.displayedCarrier, language: localizer.language).displayName]))
                        .font(.caption).foregroundStyle(.secondary)
                        .fixedSize(horizontal: false, vertical: true)
                }
                ExperimentalJourneyRail(stage: parcel.currentStage, tint: branding.ink.opacity(0.45), compact: true)
            }
            .padding(18)
            .coordinateSpace(.named(Self.cardSpace))
            .background(alignment: .top) {
                // A map that keeps clear of the second mark and the buttons waits to know where they stand, rather than moving
                // once it does.
                if let atlas, let route, delivery == nil || deliveryMark != nil, buttonRow != nil,
                   !typeSize.isAccessibilitySize || headerHeight > 0 {
                    // The title is written over the bottom of the map, so Pip stays above it.
                    RouteEngraving(atlas: atlas, route: route, stage: parcel.currentStage, identity: branding, floor: 160 + room + lower,
                                   covered: delivery == nil ? nil : deliveryMark, buttons: buttonRow, room: room + lower)
                        .frame(height: 176 + room + lower)
                        .clipShape(UnevenRoundedRectangle(topLeadingRadius: 18, topTrailingRadius: 18))
                        .contentShape(Rectangle())
                        .onTapGesture(perform: openMap)
                        .transition(.opacity)
                }
            }
            .background(branding.surface, in: RoundedRectangle(cornerRadius: 18))

            // Offered once, on a delivered parcel. Accepted, it stays as one line until the parcel is closed.
            if sample == nil, let email, emailOffer == .accepted || email.isOffered(on: parcel) {
                DeliveryEmailOffer(address: email.address, phase: emailOffer,
                                   turnOn: { answerEmailOffer(true) }, notNow: { answerEmailOffer(false) })
                    .transition(DeliveryEmailOffer.leaving)
            }

            // While the parcel waits, its pickup point gets a card; afterwards it is a plain fact.
            let waitingAt = parcel.currentStage == .readyForPickup ? PickupPoint(parcel.carrierData?.pickupPoint) : nil
            if let waitingAt {
                pickupPointCard(waitingAt, identity: branding)
            }
            if let words = carrierWaits(parcel)?.description.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty {
                carrierSaysNote(words, carrier: carrier.displayName)
            }
            shipmentIdentity(parcel, links: trackingLinks, tint: branding.ink)
            if let details = parcel.carrierData {
                VStack(alignment: .leading, spacing: 10) {
                    if let charges = localizer.importCharges(parcel) {
                        shipmentFact("detail.importCharges", value: charges.text)
                    }
                    if waitingAt == nil, let value = details.pickupPoint?.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty {
                        shipmentFact(parcel.currentStage == .delivered ? "detail.collectedAt" : "detail.pickupPoint", value: value)
                    }
                    if let value = details.receiverName?.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty {
                        shipmentFact("detail.recipient", value: value)
                    }
                    if let weight = details.weightKg, weight.isFinite, weight > 0 {
                        shipmentFact("detail.weight", value: weight.formatted(.number.locale(localizer.language.locale).precision(.fractionLength(0...3))) + " kg")
                    }
                    if let value = details.dimensionsText?.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty {
                        shipmentFact("detail.dimensions", value: value)
                    }
                }
            }
            if !catalog.tracksAutomatically(parcel.activeTrackingCarrier) || parcel.amazonShippingHistoryExpired {
                VStack(alignment: .leading, spacing: 10) {
                    Text(localizer.text(parcel.amazonShippingHistoryExpired ? "add.amazonHistoryExpired" : catalog.trackingHintKey(for: parcel.activeTrackingCarrier), ["carrier": carrier.displayName]))
                        .font(.footnote).foregroundStyle(.secondary)
                    if editable, !catalog.requiresAmazonAccount(parcel.activeTrackingCarrier) && !parcel.amazonShippingHistoryExpired {
                        Button(localizer.text("detail.changeCarrier")) { carrierEditor = CarrierEditorRequest() }
                            .font(.footnote).foregroundStyle(branding.ink)
                    }
                }
            } else if let error = parcel.syncError {
                Text(localizer.trackingFailureMessage(error))
                    .font(.footnote).foregroundStyle(Brand.warning)
                if editable, ["carrier:input_required", "carrier:invalid_input"].contains(error), parcel.activeTrackingCarrier == parcel.carrier,
                   !catalog.requirements(for: parcel.carrier, trackingNumber: parcel.trackingNumber).isEmpty {
                    Button(localizer.text("detail.updateTrackingDetails")) { carrierEditor = CarrierEditorRequest() }
                        .font(.footnote).foregroundStyle(branding.ink)
                }
                if editable, error == "carrier:refused_number" {
                    Button(localizer.text("detail.changeCarrier")) { carrierEditor = CarrierEditorRequest() }
                        .font(.footnote).foregroundStyle(branding.ink)
                }
            }
            if divided { Divider() }
        }
        .accessibilityElement(children: .contain)
    }

    /// Recognition found the carrier, the number points to one, or the parcel's own carrier needs the postcode to find it.
    private func inputNeededPrompt(_ carrier: CarrierID, text key: String, tint: Color) -> some View {
        VStack(alignment: .leading, spacing: 2) {
            Text(localizer.text(key, [
                "carrier": catalog.info(for: carrier, language: localizer.language).displayName,
            ]))
            .font(.footnote)
            .fixedSize(horizontal: false, vertical: true)
            Button {
                carrierEditor = CarrierEditorRequest(initialCarrier: carrier)
            } label: {
                Text(localizer.text("detail.inputNeededAction"))
                    .font(.footnote.weight(.semibold))
                    .frame(minHeight: 44, alignment: .leading)
                    .contentShape(Rectangle())
            }
            .buttonStyle(TactileButtonStyle())
        }
        .foregroundStyle(tint)
        .accessibilityElement(children: .contain)
    }

    /// Where the parcel waits for collection, with a way to get there.
    /// After a missed delivery or a problem, the carrier waits on the reader: the page says so and quotes it.
    private func carrierWaits(_ parcel: Parcel) -> TrackingEvent? {
        guard !parcel.isArchived, let event = parcel.currentEvent, [.failedAttempt, .exception].contains(event.stage) else { return nil }
        return event
    }

    private func carrierSaysNote(_ words: String, carrier: String) -> some View {
        HStack(alignment: .top, spacing: 12) {
            Image(systemName: "info.circle")
                .font(.system(size: 17))
                .foregroundStyle(.secondary)
                .accessibilityHidden(true)
            VStack(alignment: .leading, spacing: 3) {
                Text(localizer.text("link.carrierSays", ["carrier": carrier])).font(.footnote.weight(.semibold))
                Text(verbatim: "“\(words)”").font(.footnote).foregroundStyle(.secondary)
            }
            .fixedSize(horizontal: false, vertical: true)
            .frame(maxWidth: .infinity, alignment: .leading)
        }
        .padding(.vertical, 14)
        .padding(.horizontal, 16)
        .overlay(RoundedRectangle(cornerRadius: 16).strokeBorder(Color.secondary.opacity(0.3)))
        .accessibilityElement(children: .combine)
    }

    private func pickupPointCard(_ point: PickupPoint, identity: CarrierVisualIdentity) -> some View {
        VStack(alignment: .leading, spacing: 14) {
            HStack(alignment: .top, spacing: 12) {
                if !typeSize.isAccessibilitySize {
                    Image(systemName: "storefront")
                        .font(.system(size: 17))
                        .foregroundStyle(identity.ink)
                        .frame(width: 40, height: 40)
                        .background(identity.surface, in: RoundedRectangle(cornerRadius: 12))
                        .accessibilityHidden(true)
                }
                VStack(alignment: .leading, spacing: 2) {
                    Text(localizer.text("detail.pickupPoint")).font(.caption).foregroundStyle(.secondary)
                    Text(point.name).font(.callout.weight(.semibold))
                    if let address = point.address {
                        Text(address).font(.footnote)
                    }
                }
                .fixedSize(horizontal: false, vertical: true)
                .accessibilityElement(children: .combine)
            }
            // Side by side while each label reads on one line in its half; otherwise, as at the largest text sizes, one
            // under the other, each across the card.
            ViewThatFits(in: .horizontal) {
                if !typeSize.isAccessibilitySize {
                    EqualWidthRow(spacing: 8) { pickupButtons(point, identity: identity, oneLine: true) }
                }
                VStack(spacing: 8) { pickupButtons(point, identity: identity, oneLine: false) }
            }
        }
        .padding(16)
        .overlay(RoundedRectangle(cornerRadius: 16).strokeBorder(identity.ink.opacity(0.18)))
        .accessibilityElement(children: .contain)
    }

    @ViewBuilder private func pickupButtons(_ point: PickupPoint, identity: CarrierVisualIdentity, oneLine: Bool) -> some View {
        Button {
            DeliveryAnalytics.shared.action("parcel-pickup-directions")
            if let url = point.mapsURL { openURL(url) }
        } label: {
            Label(localizer.text(point.address == nil ? "detail.pickupShowOnMap" : "detail.pickupDirections"),
                  systemImage: point.address == nil ? "magnifyingglass" : "location.fill")
                .font(.subheadline.weight(.semibold))
                .lineLimit(oneLine ? 1 : nil)
                .padding(.horizontal, 10)
                .frame(maxWidth: .infinity, minHeight: 44)
                .foregroundStyle(identity.surface)
                .background(identity.ink, in: RoundedRectangle(cornerRadius: 12))
                .contentShape(RoundedRectangle(cornerRadius: 12))
        }
        .buttonStyle(TactileButtonStyle())
        if point.address != nil {
            Button { copyPickupPoint(point) } label: {
                // It keeps the size of its own words while it says it copied them, so the buttons stay where they are.
                Label(localizer.text("detail.pickupCopyAddress"), systemImage: "doc.on.doc")
                    .opacity(copiedPickupPoint ? 0 : 1)
                    .overlay {
                        if copiedPickupPoint { Label(localizer.text("detail.copied"), systemImage: "checkmark").fixedSize() }
                    }
                    .font(.subheadline)
                    .lineLimit(oneLine ? 1 : nil)
                    .padding(.horizontal, 10)
                    .frame(maxWidth: .infinity, minHeight: 44)
                    .foregroundStyle(.primary)
                    .overlay(RoundedRectangle(cornerRadius: 12).strokeBorder(Color.secondary.opacity(0.3)))
                    .contentShape(RoundedRectangle(cornerRadius: 12))
            }
            .buttonStyle(TactileButtonStyle())
            .accessibilityLabel(localizer.text(copiedPickupPoint ? "detail.copied" : "detail.pickupCopyAddress"))
        }
    }

    private func copyPickupPoint(_ point: PickupPoint) {
        DeliveryAnalytics.shared.action("parcel-pickup-copy", .success)
        Clipboard.string = point.query
        Haptics.success()
        copiedPickupPoint = true
        Task {
            try? await Task.sleep(for: .seconds(2))
            copiedPickupPoint = false
        }
    }

    private func shipmentFact(_ key: String, value: String) -> some View {
        VStack(alignment: .leading, spacing: 3) {
            Text(localizer.text(key)).font(.caption2).foregroundStyle(.secondary)
            Text(value).font(.footnote).fixedSize(horizontal: false, vertical: true)
        }
    }

    private func shipmentIdentity(_ parcel: Parcel, links: [ParcelTrackingLink], tint: Color) -> some View {
        // A number the link of someone else's parcel hides is not shown.
        let numbers = parcel.trackingNumbers.filter { !$0.number.isEmpty }
        let pages = ParcelTrackingLink.byNumber(links, numbers: numbers)
        return VStack(alignment: .leading, spacing: 0) {
            // At the largest text sizes the number takes the line, and its button goes under it.
            let row = typeSize.isAccessibilitySize
                ? AnyLayout(VStackLayout(alignment: .leading, spacing: 0)) : AnyLayout(HStackLayout(alignment: .center, spacing: 8))
            ForEach(Array(numbers.enumerated()), id: \.element.number) { index, entry in
                row {
                    VStack(alignment: .leading, spacing: 4) {
                        Text(parcel.trackingNumberLabel(entry, localizer: localizer))
                            .font(.caption2).foregroundStyle(.secondary)
                        Text(CarrierCatalog.format(entry.number, carrier: entry.carrier))
                            .font(.system(.caption, design: .monospaced))
                            .fixedSize(horizontal: false, vertical: true)
                            .textSelection(.enabled)
                    }
                    if !typeSize.isAccessibilitySize { Spacer(minLength: 0) }
                    Button { copy(entry.number, carrier: entry.carrier) } label: {
                        let symbol = copiedNumber == entry.number ? "checkmark" : "doc.on.doc"
                        if typeSize.isAccessibilitySize {
                            // Under the number, the button says what it does.
                            Label(localizer.text(copiedNumber == entry.number ? "detail.copied" : "detail.copyTracking"), systemImage: symbol)
                                .font(.footnote)
                                .frame(minHeight: 44).contentShape(Rectangle())
                        } else {
                            Image(systemName: symbol)
                                .font(.system(size: 14, weight: .regular))
                                .frame(width: 44, height: 44).contentShape(Rectangle())
                        }
                    }
                    .buttonStyle(.plain).foregroundStyle(.secondary)
                    .accessibilityLabel(localizer.text(copiedNumber == entry.number ? "detail.copied" : "detail.copyTracking")
                        + (numbers.count > 1 ? " — " + parcel.trackingNumberLabel(entry, localizer: localizer) : ""))
                }
                carrierLinks(pages.own[index], own: true, tint: tint)
            }
            carrierLinks(pages.loose, own: false, tint: tint)
        }
        .padding(.horizontal, 2)
    }

    /// The carriers' own pages. Under its own number a link needs no more words; another says what it is to this parcel.
    private func carrierLinks(_ links: [ParcelTrackingLink], own: Bool, tint: Color) -> some View {
        ForEach(links) { link in
            let role = own || link.role == .active ? nil
                : localizer.text(link.role == .waiting ? "detail.sourceWaiting" : "detail.sourceHistory")
            Link(destination: link.url) {
                HStack(spacing: 6) {
                    Text(localizer.text("detail.carrierWebsite", ["carrier": link.name]))
                    Image(systemName: "arrow.up.right").font(.system(size: 10, weight: .regular))
                    if let role {
                        Spacer(minLength: 4)
                        Text(role).font(.caption2).foregroundStyle(.secondary)
                    }
                }
                .font(.caption)
                .padding(.vertical, 10)
                .frame(minHeight: 44, alignment: .leading)
            }
            .buttonStyle(.plain)
            .foregroundStyle(tint)
            .simultaneousGesture(TapGesture().onEnded {
                DeliveryAnalytics.shared.action("parcel-carrier-link")
                feedback.visited(link.name)
            })
        }
    }

    private func syncStatus(_ parcel: Parcel, tint: Color) -> some View {
        HStack(spacing: 7) {
            // When the carrier was last asked and last said something, as the web says them.
            VStack(alignment: .leading, spacing: 3) {
                if let next = parcel.carrierData?.routing?.nextCheckAt.flatMap(DateParser.date), !parcel.isArchived {
                    Text(localizer.text("detail.nextCheckAfter", ["date": next.formatted(Date.FormatStyle(date: .abbreviated, time: .shortened).locale(localizer.language.locale))]))
                        .font(.caption)
                }
                if let lastSyncedAt = parcel.lastSyncedAt {
                    Text(localizer.text("detail.lastChecked", ["date": localizer.relativeTime(from: lastSyncedAt)]))
                }
                if let current = parcel.currentEvent, current.stage != .pending {
                    Text(localizer.text("detail.lastUpdate", ["date": localizer.relativeTime(of: current)]))
                }
            }

            if sample == nil && !parcel.isArchived && catalog.tracksAutomatically(parcel.activeTrackingCarrier) {
                Button {
                    run { try await store.refresh(parcel) }
                } label: {
                    // The arrow turns while it checks, as the list's Refresh does.
                    Image(systemName: "arrow.clockwise")
                        .font(.caption)
                        .symbolEffect(.rotate, isActive: working && !reduceMotion)
                        .symbolEffect(.pulse, isActive: working && reduceMotion)
                        .frame(width: 26, height: 26)
                        .contentShape(Circle())
                }
                .buttonStyle(.plain)
                .foregroundStyle(tint)
                .disabled(working)
                .accessibilityLabel(localizer.text("detail.checkNow"))
            }
        }
        .font(.caption.weight(.medium))
        .foregroundStyle(.secondary)
    }

    /// Something of the page's own stands over it: a sheet, the map, an alert or a question.
    private var covered: Bool {
        showingTitleEditor || showingDeleteConfirmation || showingShare || showingAlerts || showingMap
            || carrierEditor != nil || errorMessage != nil || feedback.words != nil || feedback.namingCarrier
    }

    /// Whether the keyboard reaches the page: its screen is in front, and nothing stands over the page.
    private var keyboard: Bool { keyboardInFront && !covered }

    /// The keys of the ••• menu's items that have one, and Esc back to the list from a page over it.
    private var keys: some View {
        KeyboardShortcuts {
            if let parcel, sample == nil {
                ForEach(ParcelMenuItem.menu(for: parcel, onCard: false, reader: store.viewerLink(for: parcel) != nil)
                    .joined().filter { $0.shortcut != nil }, id: \.self) { item in
                    Button(localizer.text(item.titleKey)) { chose(item, on: parcel) }
                        .keyboardShortcut(item.shortcut)
                        .disabled(working && item == .delete)
                }
            }
            if release == nil {
                Button(localizer.text("detail.back")) { dismiss() }.keyboardShortcut(.escape, modifiers: [])
            }
        }
    }

    /// What the page is called where no bar shows it: a Mac's window names the parcel, in the Window menu and to
    /// VoiceOver, as Mail's names the message.
    private var windowTitle: String {
        #if os(macOS)
        parcel.map { $0.label.nonEmpty ?? localizer.text("common.parcel") } ?? localizer.text("detail.label")
        #else
        localizer.text("detail.label")
        #endif
    }

    /// The menu bar's Parcel menu, which the page answers.
    private var parcelMenu: ParcelMenu? {
        guard let parcel, sample == nil else { return nil }
        return ParcelMenu(items: ParcelMenu.items(for: parcel, reader: store.viewerLink(for: parcel) != nil), busy: working) {
            chose($0, on: parcel)
        }
    }

    /// What the ••• menu asked of the parcel. The page is open already.
    private func chose(_ item: ParcelMenuItem, on parcel: Parcel) {
        switch item {
        case .open: break
        case .rename:
            editedTitle = parcel.label
            showingTitleEditor = true
        case .share: showingShare = true
        case .copyTrackingNumber:
            let entry = parcel.trackingNumbers[0]
            copy(entry.number, carrier: entry.carrier)
        case .changeCarrier: carrierEditor = CarrierEditorRequest()
        case .archive: archive(parcel)
        case .restore: restore(parcel)
        case .delete: showingDeleteConfirmation = true
        }
    }

    private func copy(_ value: String, carrier: CarrierID) {
        TrackingNumberCopy.copy(value, carrier: carrier)
        copiedNumber = value
        Task {
            try? await Task.sleep(for: .seconds(2))
            if copiedNumber == value { copiedNumber = nil }
        }
    }

    /// Back to the list: off it, or from beside it.
    private func leave() {
        if let release { release() } else { dismiss() }
    }

    private func archive(_ parcel: Parcel) {
        run {
            try await store.archive(parcel)
            leave()
        }
    }

    private func restore(_ parcel: Parcel) {
        run {
            try await store.restore(parcel)
            leave()
        }
    }

    private func delete(_ parcel: Parcel) {
        run {
            try await store.permanentlyDelete(parcel)
            leave()
        }
    }

    private func run(_ operation: @escaping @MainActor () async throws -> Void) {
        guard !working else { return }
        working = true
        errorMessage = nil
        Task {
            do { try await operation() }
            catch {
                errorMessage = localizer.errorMessage(error)
                // What failed in front of the reader keeps a rating from being asked for today.
                ReviewPrompt().noteBadMoment(at: Date())
            }
            working = false
        }
    }

    /// Saves the answer to the email offer. The server remembers either answer, so the
    /// offer leaves once declined, says the email is on once accepted, and stays, saying
    /// so, when the answer could not be saved.
    private func answerEmailOffer(_ accepted: Bool) {
        if case .saving = emailOffer { return }
        DeliveryAnalytics.shared.action(accepted ? "email-offer-accept" : "email-offer-decline")
        emailOffer = .saving(accepted)
        Task {
            do {
                try await store.setEmailOnDelivery(accepted)
                emailOffer = accepted && store.deliveryEmail?.isOn == true ? .accepted : .open
                if emailOffer == .accepted {
                    Haptics.success()
                    AccessibilityNotification.Announcement(
                        localizer.text("email.offer.on", ["place": localizer.deliveryUpdatesPlace])
                    ).post()
                }
            } catch {
                emailOffer = .failed
                AccessibilityNotification.Announcement(localizer.text("email.setting.failed")).post()
            }
        }
    }
}

private struct ParcelNotificationBell: View {
    let muted: Bool
    let trigger: Int
    @Environment(\.accessibilityReduceMotion) private var reduceMotion

    private struct Pose {
        var angle = 0.0
        var scale = 1.0
        var waves = 0.0
    }

    var body: some View {
        let reduceMotion = self.reduceMotion
        return Color.clear
            .frame(width: 18, height: 18)
            .keyframeAnimator(initialValue: Pose(), trigger: trigger) { _, pose in
                ZStack {
                    Image(systemName: "bell")
                        .font(.system(size: 16, weight: .light))
                        .frame(width: 18, height: 18)
                        .rotationEffect(.degrees(reduceMotion ? 0 : pose.angle), anchor: UnitPoint(x: 0.5, y: 0.16))
                        .scaleEffect(reduceMotion ? 1 : pose.scale)
                    Path { path in
                        path.move(to: CGPoint(x: 2, y: 2))
                        path.addLine(to: CGPoint(x: 16, y: 16))
                    }
                    .trim(from: 0, to: muted ? 1 : 0)
                    .stroke(style: StrokeStyle(lineWidth: 1, lineCap: .round))
                    .opacity(muted ? 1 : 0)
                    .animation(reduceMotion ? nil : .easeOut(duration: 0.22), value: muted)
                    Path { path in
                        path.move(to: CGPoint(x: 0, y: 4))
                        path.addQuadCurve(to: CGPoint(x: 0, y: 11), control: CGPoint(x: -2, y: 7.5))
                        path.move(to: CGPoint(x: 18, y: 4))
                        path.addQuadCurve(to: CGPoint(x: 18, y: 11), control: CGPoint(x: 20, y: 7.5))
                    }
                    .stroke(style: StrokeStyle(lineWidth: 0.9, lineCap: .round))
                    .opacity(reduceMotion || muted ? 0 : pose.waves)
                }
                .frame(width: 18, height: 18)
            } keyframes: { _ in
                KeyframeTrack(\.angle) {
                    if muted {
                        CubicKeyframe(-7, duration: 0.112)
                        CubicKeyframe(3, duration: 0.112)
                        CubicKeyframe(0, duration: 0.096)
                    } else {
                        CubicKeyframe(17, duration: 0.115)
                        CubicKeyframe(-14, duration: 0.131)
                        CubicKeyframe(10, duration: 0.131)
                        CubicKeyframe(-6, duration: 0.131)
                        CubicKeyframe(3, duration: 0.132)
                        CubicKeyframe(0, duration: 0.18)
                    }
                }
                KeyframeTrack(\.scale) {
                    CubicKeyframe(muted ? 0.94 : 1, duration: 0.112)
                    CubicKeyframe(muted ? 1.02 : 1, duration: 0.112)
                    CubicKeyframe(1, duration: 0.096)
                }
                KeyframeTrack(\.waves) {
                    CubicKeyframe(muted ? 0 : 0.65, duration: 0.148)
                    CubicKeyframe(muted ? 0 : 0.15, duration: 0.114)
                    CubicKeyframe(muted ? 0 : 0.65, duration: 0.115)
                    CubicKeyframe(0, duration: 0.279)
                }
            }
            .accessibilityHidden(true)
    }
}

/// A parcel's menu, a line between its groups: the page's •••, and its card's in the deliveries.
struct ParcelMenuButtons: View {
    let items: [[ParcelMenuItem]]
    /// Delete waits while another change is saved.
    var busy = false
    /// The items show their keys, on the parcel the keyboard acts on, while a keyboard is connected.
    var shortcuts = false
    let choose: (ParcelMenuItem) -> Void

    @EnvironmentObject private var localizer: Localizer
    @ObservedObject private var keyboard = ConnectedKeyboard.shared

    var body: some View {
        ForEach(Array(items.enumerated()), id: \.offset) { index, group in
            if index > 0 { Divider() }
            ForEach(group, id: \.self) { item in
                Button(localizer.text(item.titleKey), systemImage: item.symbol) { choose(item) }
                    .keyboardShortcut(shortcuts && keyboard.isConnected ? item.shortcut : nil)
                    .disabled(busy && item == .delete)
            }
        }
    }
}

/// Copies a tracking number as its carrier's site takes it.
enum TrackingNumberCopy {
    static func text(_ number: String, carrier: CarrierID) -> String {
        carrier == .postlogistics ? CarrierCatalog.format(number, carrier: carrier) : number
    }

    @MainActor static func copy(_ number: String, carrier: CarrierID) {
        DeliveryAnalytics.shared.action("parcel-copy-tracking")
        Clipboard.string = text(number, carrier: carrier)
        Haptics.success()
    }
}

extension View {
    /// Asks for a parcel's new name, starting from `name`; `save` gets it trimmed, at most 80 characters.
    func parcelRenameAlert(isPresented: Binding<Bool>, name: Binding<String>, save: @escaping (String) -> Void) -> some View {
        modifier(ParcelRenameAlert(isPresented: isPresented, name: name, save: save))
    }

    /// Asks before a parcel is deleted for good.
    func parcelDeleteConfirmation(isPresented: Binding<Bool>, delete: @escaping () -> Void) -> some View {
        modifier(ParcelDeleteConfirmation(isPresented: isPresented, delete: delete))
    }
}

private struct ParcelRenameAlert: ViewModifier {
    @Binding var isPresented: Bool
    @Binding var name: String
    let save: (String) -> Void

    @EnvironmentObject private var localizer: Localizer

    func body(content: Content) -> some View {
        content.alert(localizer.text("detail.editTitle"), isPresented: $isPresented) {
            TextField(localizer.text("common.parcel"), text: $name)
            Button(localizer.text("common.cancel"), role: .cancel) {}
            Button(localizer.text("detail.saveTitle")) {
                save(String(name.trimmingCharacters(in: .whitespacesAndNewlines).prefix(80)))
            }
        }
    }
}

private struct ParcelDeleteConfirmation: ViewModifier {
    @Binding var isPresented: Bool
    let delete: () -> Void

    @EnvironmentObject private var localizer: Localizer

    func body(content: Content) -> some View {
        content.confirmationDialog(
            localizer.text("detail.deleteQuestion"), isPresented: $isPresented, titleVisibility: .visible
        ) {
            Button(localizer.text("detail.delete"), action: delete)
            Button(localizer.text("common.cancel"), role: .cancel) {}
        } message: {
            Text(localizer.text("detail.deleteDescription"))
        }
    }
}

struct ChangeCarrierView: View {
    let parcel: Parcel

    @EnvironmentObject private var store: ParcelStore
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dismiss) private var dismiss

    /// Nil while automatic detection is chosen.
    @State private var chosenCarrier: CarrierID?
    @State private var trackingURL: String
    @State private var deliveryPostcode: String
    /// The delivery country chosen beside the postcode, until another carrier is chosen.
    @State private var chosenPostcodeCountry: String?
    @State private var providerPostcode = ""
    @State private var saving = false
    @State private var errorMessage: String?
    @State private var detent: PresentationDetent = .medium
    @State private var refusedQuestion: NumberRefusalRequest?

    @ObservedObject private var catalog = CarrierCatalog.shared

    /// `initialCarrier` preselects a carrier, such as one that recognized the number and needs a postcode.
    init(parcel: Parcel, initialCarrier: CarrierID? = nil) {
        self.parcel = parcel
        let carrier = initialCarrier ?? parcel.carrier
        let unfiled = parcel.carrier == .unknown || parcel.carrier == .internationalPost
        _chosenCarrier = State(initialValue: unfiled && carrier == parcel.carrier
            && !CarrierCatalog.shared.isAmazonTrackingNumber(parcel.trackingNumber) ? nil : carrier)
        _trackingURL = State(initialValue: carrier == parcel.carrier ? parcel.trackingURL ?? "" : "")
        _deliveryPostcode = State(initialValue: carrier == parcel.carrier ? parcel.dpdPostcode ?? "" : "")
    }

    var body: some View {
        NavigationStack {
            Form {
                Section {
                    Text(CarrierCatalog.format(parcel.trackingNumber, carrier: parcel.carrier))
                        .font(.system(.body, design: .monospaced, weight: .semibold))
                    Text(localizer.text("detail.changeCarrierDescription", [
                        "number": CarrierCatalog.format(parcel.trackingNumber, carrier: parcel.carrier),
                    ]))
                    .font(.footnote)
                    .foregroundStyle(.secondary)
                }

                Section(localizer.text("add.carrier")) {
                    NavigationLink {
                        CarrierPickerView(
                            selection: chosenCarrier,
                            automatic: automaticCarrier == nil ? nil : .init(description: automaticDescription, recommended: false, busy: false),
                            sections: pickerSections
                        ) { carrier in
                            chosenCarrier = carrier
                        }
                        // The full list, and room for its letter rail.
                        .onAppear { detent = .large }
                    } label: {
                        HStack(spacing: 10) {
                            if automatic {
                                Image(systemName: "wand.and.sparkles")
                                    .foregroundStyle(ExperimentalPalette.ochre)
                                    .frame(width: 27)
                            } else {
                                CarrierTruckMark(identity: CarrierVisualIdentity.of(selectedCarrier, language: localizer.language))
                            }
                            VStack(alignment: .leading, spacing: 2) {
                                Text(carrierName)
                                if refused {
                                    Text(localizer.text("add.line.refused"))
                                        .font(.caption)
                                        .foregroundStyle(ExperimentalPalette.ochre)
                                }
                            }
                        }
                    }
                    .readsAsItsLabel()
                    .accessibilityLabel(localizer.text("add.carrier"))
                    .accessibilityValue([carrierName,
                                         refused ? localizer.text("add.line.refused") : nil].compactMap { $0 }.joined(separator: ", "))
                    .accessibilityAddTraits(.isButton)
                    .onChange(of: selectedCarrier) { _, carrier in
                        trackingURL = carrier == parcel.carrier ? parcel.trackingURL ?? "" : ""
                        deliveryPostcode = carrier == parcel.carrier ? parcel.dpdPostcode ?? "" : ""
                        chosenPostcodeCountry = nil
                        errorMessage = nil
                    }
                    Text(automatic ? automaticDescription : localizer.text(catalog.trackingHintKey(for: selectedCarrier), [
                        "carrier": catalog.info(for: selectedCarrier, language: localizer.language).displayName,
                    ]))
                    .font(.footnote)
                    .foregroundStyle(.secondary)
                }

                if !requirements.isEmpty {
                    Section {
                        ForEach(requirements, id: \.field) { requirement in
                            requirementField(requirement)
                        }
                    } footer: {
                        if postcodeRequirement?.isOptional == true {
                            Text(localizer.text("add.requirement.dpdPostcodeOptionalHelp", [
                                "carrier": catalog.info(for: selectedCarrier, language: localizer.language).displayName,
                            ]))
                        }
                    }
                }

                if needsProviderPostcode {
                    Section {
                        TextField(localizer.text("add.requirement.dpdPostcode"), text: $providerPostcode)
                            .textContentType(.postalCode).textInputAutocapitalization(.characters).autocorrectionDisabled()
                    } footer: {
                        Text(localizer.text("detail.providerInputNeeded", ["provider": parcel.carrierData!.routing!.providerInputNeeded!.provider]))
                    }
                }
                if let errorMessage {
                    Section {
                        Label(errorMessage, systemImage: "exclamationmark.triangle.fill")
                            .foregroundStyle(.red)
                    }
                }
            }
            .navigationTitle(localizer.text("detail.changeCarrier"))
            .navigationBarTitleDisplayMode(.inline)
            #if os(macOS)
            // Sections as iOS draws them, rather than labels in a column beside their fields.
            .formStyle(.grouped)
            #endif
            .interactiveDismissDisabled(saving)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    Button(localizer.text("common.cancel")) { dismiss() }
                        .disabled(saving)
                }
                ToolbarItem(placement: .confirmationAction) {
                    Button(saving
                        ? localizer.text("detail.changingCarrier")
                        : localizer.text("detail.saveCarrier")) {
                        save()
                    }
                    .fontWeight(.semibold)
                    .disabled(!canSave || saving)
                }
            }
        }
        // Inputs and their help sit below the half-height sheet: open fully for them.
        .formOnPad(phoneDetents: [.medium, .large], selection: $detent)
        .presentationDragIndicator(.visible)
        .onChange(of: requirements.isEmpty, initial: true) { _, empty in
            if !empty { detent = .large }
        }
        .task(id: refusalQuestion) {
            guard let question = refusalQuestion else { return }
            let refuses = await NumberRefusals.refuses(question)
            guard !Task.isCancelled else { return }
            refusedQuestion = refuses ? question : nil
        }
    }

    private var unfiled: Bool { parcel.carrier == .unknown || parcel.carrier == .internationalPost }

    /// Automatic detection files the parcel as adding it would: under the carrier its number's
    /// shape names, else under none, and Peek looks for it. A parcel filed under none already is.
    /// An Amazon number is tracked through Amazon Shipping alone.
    private var automaticCarrier: CarrierID? {
        if catalog.isAmazonTrackingNumber(parcel.trackingNumber) { return nil }
        if unfiled { return parcel.carrier }
        let match = catalog.detect(parcel.trackingNumber)
        return match.confidence == .high ? match.carrier : .unknown
    }

    private var automatic: Bool { chosenCarrier == nil }

    /// The carrier the change files the parcel under.
    private var selectedCarrier: CarrierID { chosenCarrier ?? automaticCarrier ?? parcel.carrier }

    private var carrierName: String {
        automatic ? localizer.text("add.detect") : catalog.info(for: selectedCarrier, language: localizer.language).displayName
    }

    private var automaticDescription: String {
        guard let carrier = automaticCarrier, carrier != .unknown, carrier != .internationalPost else {
            return localizer.text("picker.auto.change")
        }
        return localizer.text("picker.auto.detected", ["carrier": catalog.info(for: carrier, language: localizer.language).displayName])
    }

    /// The carrier's fields, the postcode's shaped for the country beside it.
    private var requirements: [CarrierRequirement] {
        let country = postcodeCountry
        return catalog.requirements(for: selectedCarrier, trackingNumber: parcel.trackingNumber).map {
            catalog.postcodeRequirement($0, for: selectedCarrier, country: country)
        }
    }

    private var refusalQuestion: NumberRefusalRequest? {
        NumberRefusals.question(carrier: automatic ? nil : selectedCarrier, trackingNumber: CarrierCatalog.normalize(parcel.trackingNumber), demo: store.isDemo)
    }

    /// A carrier that issues no number of this shape is pointed out before the change is saved.
    private var refused: Bool {
        refusalQuestion != nil && refusedQuestion == refusalQuestion
    }

    /// After automatic detection, the parcel's own carrier unless it is filed automatically or
    /// among the carriers its number fits, which come next, then the ones used before. So the
    /// carrier it has is in view without scrolling, and one no longer offered is there at all.
    private var pickerSections: [CarrierPickerView.PickerSection] {
        let match = catalog.detect(parcel.trackingNumber)
        let fitting = (match.confidence == .high ? [match.carrier] : match.candidates)
            .filter { catalog.info(for: $0).selectable }
        let own = (automaticCarrier != nil && unfiled) || fitting.contains(parcel.carrier) ? [] : [parcel.carrier]
        return [
            .init(id: "current", title: localizer.text("picker.section.current"), carriers: own),
            .init(id: "fits", title: localizer.text(match.confidence == .high ? "picker.section.detected" : "picker.section.fits"),
                  carriers: fitting),
            .init(id: "used", title: localizer.text("picker.section.used"),
                  carriers: CarrierPickerSearch.usedCarriers(store.parcels, catalog: catalog).filter { !fitting.contains($0) && !own.contains($0) }),
        ]
    }

    private var trackingURLRequirement: CarrierRequirement? {
        requirements.first(where: { $0.field == .trackingURL })
    }

    /// The carrier's postcode field, before the country beside it shapes it.
    private var carrierPostcodeRequirement: CarrierRequirement? {
        catalog.requirements(for: selectedCarrier, trackingNumber: parcel.trackingNumber).first(where: { $0.field == .dpdPostcode })
    }

    /// Where the postcode's country starts: the one saved with the parcel's postcode, or the field's own start.
    private var initialPostcodeCountry: String? {
        carrierPostcodeRequirement.flatMap { catalog.initialPostcodeCountry(for: selectedCarrier, requirement: $0, parcel: parcel) }
    }

    /// The country the postcode is from: the one chosen while this field takes it, otherwise where it starts.
    private var postcodeCountry: String? {
        guard let requirement = carrierPostcodeRequirement else { return nil }
        if let chosenPostcodeCountry,
           catalog.postcodeCountries(for: selectedCarrier, requirement: requirement).contains(chosenPostcodeCountry) {
            return chosenPostcodeCountry
        }
        return initialPostcodeCountry
    }

    /// The postcode's country, offered beside the field when it takes more than one.
    private var offeredPostcodeCountry: String? {
        guard let requirement = carrierPostcodeRequirement,
              catalog.postcodeCountries(for: selectedCarrier, requirement: requirement).count > 1 else { return nil }
        return postcodeCountry
    }

    private var postcodeRequirement: CarrierRequirement? {
        requirements.first(where: { $0.field == .dpdPostcode })
    }

    private var needsProviderPostcode: Bool { selectedCarrier == parcel.carrier && parcel.carrierData?.routing?.providerInputNeeded != nil }
    private var normalizedProviderPostcode: String? {
        guard needsProviderPostcode else { return nil }
        let value = providerPostcode.trimmingCharacters(in: .whitespacesAndNewlines).uppercased()
        return value.range(of: "^(?=.{3,12}$)(?=.*[0-9])[A-Z0-9]+(?:[ -][A-Z0-9]+)*$", options: .regularExpression) != nil ? value : nil
    }
    private var canSave: Bool {
        if needsProviderPostcode && normalizedProviderPostcode == nil { return false }
        guard changed else { return false }
        for requirement in requirements {
            switch requirement.field {
            case .trackingURL:
                let value = requirement.normalizedValue(trackingURL)
                guard requirement.accepts(value),
                      let url = URL(string: value),
                      url.scheme == "https",
                      url.host != nil else { return false }
            case .dpdPostcode:
                guard requirement.isSatisfied(by: deliveryPostcode) else { return false }
            }
        }
        return true
    }

    private var changed: Bool {
        normalizedProviderPostcode != nil || selectedCarrier != parcel.carrier
            || normalizedTrackingURL != (parcel.trackingURL ?? "")
            || normalizedPostcode != (parcel.dpdPostcode ?? "")
            || (!normalizedPostcode.isEmpty && postcodeCountry != initialPostcodeCountry)
    }

    private var normalizedTrackingURL: String {
        guard let trackingURLRequirement else { return "" }
        return trackingURLRequirement.normalizedValue(trackingURL)
    }

    private var normalizedPostcode: String {
        guard let postcodeRequirement else { return "" }
        return postcodeRequirement.normalizedValue(deliveryPostcode)
    }

    @ViewBuilder
    private func requirementField(_ requirement: CarrierRequirement) -> some View {
        switch requirement.field {
        case .trackingURL:
            TextField(
                localizer.text("add.requirement.trackingUrl"),
                text: $trackingURL,
                axis: .vertical
            )
            .drawnField()
            .keyboardType(.URL)
            .textInputAutocapitalization(.never)
            .autocorrectionDisabled()
        case .dpdPostcode:
            HStack(alignment: .firstTextBaseline, spacing: 6) {
                if let country = offeredPostcodeCountry, let carrierRequirement = carrierPostcodeRequirement {
                    PostcodeCountryMenu(carrier: selectedCarrier, requirement: carrierRequirement, country: country) {
                        chosenPostcodeCountry = $0
                    }
                }
                TextField(
                    localizer.text("add.requirement.dpdPostcode"),
                    text: $deliveryPostcode
                )
                .drawnField()
                .keyboardType(requirement.inputMode == "numeric" ? .numberPad
                    : requirement.startsWithNumberKeys(
                        example: catalog.postcodeExample(for: selectedCarrier, requirement: requirement, region: postcodeCountry)
                    ) ? .numbersAndPunctuation : .asciiCapable)
                .textInputAutocapitalization(.characters)
                .autocorrectionDisabled()
                .textContentType(.postalCode)
                .onChange(of: deliveryPostcode) { _, value in
                    deliveryPostcode = requirement.typedValue(value)
                }
                if requirement.isOptional {
                    Text(localizer.text("add.optional"))
                        .font(.caption)
                        .foregroundStyle(.secondary)
                }
            }
        }
    }

    private func save() {
        guard canSave, !saving else { return }
        saving = true
        errorMessage = nil
        Task {
            do {
                try await store.changeCarrier(
                    parcel,
                    carrier: selectedCarrier,
                    trackingURL: normalizedTrackingURL.nonEmpty,
                    dpdPostcode: normalizedPostcode.nonEmpty,
                    dpdPostcodeCountry: catalog.sentPostcodeCountry(postcodeCountry, postcode: normalizedPostcode, for: selectedCarrier),
                    providerPostcode: normalizedProviderPostcode
                )
                dismiss()
            } catch {
                errorMessage = localizer.errorMessage(error)
                saving = false
            }
        }
    }
}

/// A parcel's tracking history, newest first and grouped by day.
struct ParcelJournal: View {
    let parcel: Parcel
    let tint: Color

    @EnvironmentObject private var localizer: Localizer
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @State private var showingFullJourney = true

    var body: some View {
        let entries = parcel.journalEntries
        let groups = journalDays(entries)
        let eventCount = entries.count
        // A copy the journal shows under its scan is current through that scan.
        let currentEventID = parcel.currentEvent.flatMap { current in
            entries.first { $0.id == current.id || $0.relays.contains(current) }?.id
        }
        let syncing = parcel.displayStatus.syncing
        return VStack(alignment: .leading, spacing: 0) {
            Button {
                withAnimation(reduceMotion ? nil : .easeInOut(duration: 0.2)) { showingFullJourney.toggle() }
            } label: {
                HStack(spacing: 12) {
                    Text(localizer.text("timeline.label")).font(.subheadline.weight(.semibold))
                    Spacer(minLength: 4)
                    Text(localizer.text(eventCount == 1 ? "detail.updateCount.one" : "detail.updateCount.many", ["count": eventCount]))
                        .font(.caption2).foregroundStyle(.secondary)
                    Image(systemName: showingFullJourney ? "chevron.up" : "chevron.down")
                        .font(.system(size: 10, weight: .regular)).foregroundStyle(.secondary)
                }
                .frame(minHeight: 36)
                .contentShape(Rectangle())
            }
            .buttonStyle(.plain)
            .accessibilityValue(localizer.text(showingFullJourney ? "design.lessJourney" : "design.fullJourney"))
            if showingFullJourney {
                if groups.isEmpty {
                    Text(localizer.text(syncing ? "timeline.emptySyncing" : "timeline.empty"))
                        .font(.footnote).foregroundStyle(.secondary).padding(.vertical, 12)
                }
                ForEach(groups) { group in
                    Text(group.label)
                        .font(.caption2).textCase(.uppercase).tracking(1)
                        .foregroundStyle(.secondary).padding(.top, 22).padding(.bottom, 13)
                        .accessibilityAddTraits(.isHeader)
                    VStack(alignment: .leading, spacing: 18) {
                        ForEach(group.entries) { entry in
                            JournalEventRow(event: entry.event, relays: entry.relays, tint: tint,
                                isCurrent: entry.id == currentEventID,
                                syncing: syncing)
                        }
                    }
                }
            }
        }
        .padding(.horizontal, 2)
    }

    private func journalDays(_ entries: [JournalEntry]) -> [JournalDay] {
        var groups: [JournalDay] = []
        let calendar = Calendar.current
        for entry in entries {
            let event = entry.event
            // A scan dated by its day alone sits under that day, wherever the reader is.
            let date = event.displayDate
            let key = date.map { String(calendar.startOfDay(for: $0).timeIntervalSince1970) } ?? event.occurredAt
            if let index = groups.firstIndex(where: { $0.id == key }) {
                groups[index].entries.append(entry)
            } else {
                let label: String
                if let date {
                    if calendar.isDateInToday(date) { label = localizer.text("time.today") }
                    else if calendar.isDateInYesterday(date) { label = localizer.text("time.yesterday") }
                    else {
                        let year = calendar.component(.year, from: date)
                        label = localizer.shortDate(date) + (year == calendar.component(.year, from: Date()) ? "" : " \(year)")
                    }
                } else { label = event.occurredAt }
                groups.append(JournalDay(id: key, label: label, start: date.map { calendar.startOfDay(for: $0) }, entries: [entry]))
            }
        }
        // A scan dated by its day alone can sort among the day before's scans, west of the carrier's zone.
        return groups.enumerated().sorted { a, b in
            let (first, second) = (a.element.start ?? .distantPast, b.element.start ?? .distantPast)
            return first != second ? first > second : a.offset < b.offset
        }.map(\.element)
    }
}

/// The carrier editor opens on a recognized carrier when a prompt asks for its input.
/// Views side by side in equal widths, each as wide as the widest one needs. Short of that room it asks for more than it
/// has, so a `ViewThatFits` passes it by rather than cutting a label.
private struct EqualWidthRow: Layout {
    var spacing: CGFloat

    func sizeThatFits(proposal: ProposedViewSize, subviews: Subviews, cache: inout ()) -> CGSize {
        let widest = subviews.map { $0.sizeThatFits(.unspecified).width }.max() ?? 0
        let width = max(proposal.width ?? 0, widest * CGFloat(subviews.count) + gaps(subviews))
        let column = ProposedViewSize(width: columnWidth(width, subviews), height: proposal.height)
        return CGSize(width: width, height: subviews.map { $0.sizeThatFits(column).height }.max() ?? 0)
    }

    func placeSubviews(in bounds: CGRect, proposal: ProposedViewSize, subviews: Subviews, cache: inout ()) {
        let column = columnWidth(bounds.width, subviews)
        for (index, subview) in subviews.enumerated() {
            subview.place(at: CGPoint(x: bounds.minX + CGFloat(index) * (column + spacing), y: bounds.minY),
                          proposal: ProposedViewSize(width: column, height: bounds.height))
        }
    }

    private func gaps(_ subviews: Subviews) -> CGFloat { spacing * CGFloat(max(subviews.count - 1, 0)) }

    private func columnWidth(_ width: CGFloat, _ subviews: Subviews) -> CGFloat {
        subviews.isEmpty ? 0 : (width - gaps(subviews)) / CGFloat(subviews.count)
    }
}

private struct CarrierEditorRequest: Identifiable {
    let id = UUID()
    var initialCarrier: CarrierID?
}

private struct JournalDay: Identifiable {
    let id: String
    let label: String
    /// The start of the day; nil for scans without a readable date, which stay last.
    let start: Date?
    var entries: [JournalEntry]
}

private struct JournalEventRow: View {
    let event: TrackingEvent
    var relays: [TrackingEvent] = []
    let tint: Color
    let isCurrent: Bool
    let syncing: Bool
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dynamicTypeSize) private var typeSize
    /// The time's column grows with its text; at the largest sizes the time goes above the scan instead.
    @ScaledMetric(relativeTo: .caption2) private var timeWidth: CGFloat = 38

    var body: some View {
        let layout = typeSize.isAccessibilitySize
            ? AnyLayout(VStackLayout(alignment: .leading, spacing: 2)) : AnyLayout(HStackLayout(alignment: .top, spacing: 12))
        layout {
            Text(time)
                .font(.caption2).monospacedDigit()
                .foregroundStyle(isCurrent ? tint : Color.secondary)
                .frame(width: typeSize.isAccessibilitySize ? nil : timeWidth, alignment: .leading)
                .padding(.top, 2)
            VStack(alignment: .leading, spacing: 4) {
                Text(syncing && isCurrent && event.stage == .pending
                     ? localizer.text("timeline.syncing")
                     : wording(event))
                    .font(.footnote)
                    .fixedSize(horizontal: false, vertical: true)
                if let location = event.location?.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty {
                    EventLocation(location: location, placed: event.place?.country)
                        .font(.caption2).foregroundStyle(.secondary)
                }
                // A copy's words stay in sight, in case it was a scan of its own.
                ForEach(relayed, id: \.self) { line in
                    Text(line)
                        .font(.caption2).foregroundStyle(.secondary)
                        .fixedSize(horizontal: false, vertical: true)
                }
            }
            .frame(maxWidth: .infinity, alignment: .leading)
        }
        .accessibilityElement(children: .combine)
    }

    /// No clock time for a scan the carrier dated by its day alone: its day heads the group.
    private var time: String {
        if event.calendarDay != nil { return "" }
        guard let date = DateParser.date(event.occurredAt) else { return "—" }
        return localizer.clockTime(date)
    }

    private func wording(_ event: TrackingEvent) -> String {
        localizer.eventDescription(event.description.nonEmpty ?? localizer.text(event.stage.localizationKey))
    }

    /// The copies' words, once each, when they differ from the scan's.
    private var relayed: [String] {
        var seen: Set<String> = [wording(event)]
        return relays.map(wording).filter { seen.insert($0).inserted }
    }
}

/// The flag leads and the country closes the place, named in the reader's language.
private struct EventLocation: View {
    let location: String
    /// The country the scan was placed in, which settles a final code that is also a state or canton.
    let placed: String?
    @EnvironmentObject private var localizer: Localizer

    var body: some View {
        let place = TrackingLocation.place(location, placedIn: placed)
        if let country = place.country {
            let name = TrackingLocation.countryName(country, language: localizer.language)
            let text = place.name.isEmpty ? name : "\(place.name), \(name)"
            HStack(alignment: .firstTextBaseline, spacing: 4) {
                Text(TrackingLocation.flag(country)).font(.caption)
                Text(text).fixedSize(horizontal: false, vertical: true)
            }
            .accessibilityElement(children: .ignore)
            .accessibilityLabel(text)
        } else {
            Text(place.name)
        }
    }
}

struct AutomaticCarrierNotice: View {
    let parcel: Parcel
    @EnvironmentObject private var localizer: Localizer

    var body: some View {
        // Only automatic carrier changes need a clock; most cards have none.
        if parcel.carrierData?.autoChangedFrom != nil {
            TimelineView(.periodic(from: .now, by: 60)) { context in
                if let from = parcel.automaticallyChangedFrom(at: context.date) {
                    Text(localizer.text("parcel.autoChangedCarrier", [
                        "carrier": CarrierCatalog.shared.info(for: from, language: localizer.language).displayName,
                    ]))
                    .font(.caption).foregroundStyle(.secondary)
                    .fixedSize(horizontal: false, vertical: true)
                }
            }
        }
    }
}
