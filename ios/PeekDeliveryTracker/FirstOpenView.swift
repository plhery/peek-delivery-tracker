import SwiftUI
#if os(iOS)
import UIKit
#endif
import VisionKit

/// How someone starts following their first parcel. The deliveries list owns the sheet that adds it.
enum FirstParcelRequest: Equatable {
    /// Whatever is on the clipboard, read once the person asked for it, or text dropped on the deliveries.
    case paste(String)
    case type
    case scan

    /// As much pasted text as Add takes in.
    static let pasteLimit = 2000

    /// Text or a link dragged onto the deliveries from another app is taken as a paste: each piece once, one to a
    /// line, as the share extension joins them. Nil when nothing in it reads as a tracking number, or as a shop's
    /// order number, which Add explains.
    static func drop(_ items: [String], catalog: CarrierCatalog = .shared) -> Self? {
        let pieces = items.compactMap { $0.trimmingCharacters(in: .whitespacesAndNewlines).nonEmpty }
            .reduce(into: [String]()) { pieces, piece in if !pieces.contains(piece) { pieces.append(piece) } }
        let text = String(pieces.joined(separator: "\n").prefix(pasteLimit))
        return catalog.read(text).trackingNumber.isEmpty && catalog.order(in: text) == nil ? nil : .paste(text)
    }
}

/// The made-up parcel the first screen follows: a pair of trainers, from Shenzhen to a kiosk in Zürich.
enum FirstOpenJourney {
    /// Its scans; the journey is told one more scan at a time.
    static let steps = 5
    /// What someone who asked for less motion sees: the parcel a day away, its route drawn.
    static let stillStep = 3
    /// The number says what the parcel is, where a note would have had to.
    static let trackingNumber = "123EXAMPLE"
    /// The same parcel all along, from the first screen's picture to the page it opens.
    static let parcelID = UUID(uuidString: "5A3B1E00-0000-4000-8000-000000000000")!
    private static let kiosk = "Kiosk im Hauptbahnhof"

    private struct Stop {
        let name: String
        /// Written out, so the history shows its flag and its name in the reader's language.
        let country: String
        let code: String
        let latitude: Double
        let longitude: Double

        var location: String { "\(name), \(country)" }
        var place: EventPlace { EventPlace(latitude: latitude, longitude: longitude, precision: .city, country: code, name: name) }
    }

    private static let shenzhen = Stop(name: "Shenzhen", country: "China", code: "CN", latitude: 22.543, longitude: 114.058)
    private static let leipzig = Stop(name: "Leipzig", country: "Germany", code: "DE", latitude: 51.340, longitude: 12.375)
    private static let basel = Stop(name: "Basel", country: "Switzerland", code: "CH", latitude: 47.558, longitude: 7.573)
    private static let zurich = Stop(name: "Zürich", country: "Switzerland", code: "CH", latitude: 47.367, longitude: 8.55)

    private static let scans: [(stage: TrackingStage, hoursAgo: Double, stop: Stop)] = [
        (.accepted, 80, shenzhen),
        (.inTransit, 26, leipzig),
        (.inTransit, 6, basel),
        (.outForDelivery, 3, zurich),
        (.delivered, 0, zurich),
    ]

    /// The whole story, as the parcel opens on it: a missed doorbell, and the kiosk it was picked up from.
    private static let story: [(stage: TrackingStage, hoursAgo: Double, line: String, stop: Stop?)] = [
        (.registered, 84, "sample.journey.packed", nil),
        (.accepted, 80, "sample.journey.collected", shenzhen),
        (.inTransit, 30, "sample.journey.flight", leipzig),
        (.customs, 9, "sample.journey.customs", basel),
        (.outForDelivery, 6, "sample.journey.courier", zurich),
        (.failedAttempt, 4.5, "sample.journey.missed", zurich),
        (.readyForPickup, 3.5, "sample.journey.waiting", zurich),
        (.delivered, 0.4, "sample.journey.pickedUp", zurich),
    ]

    @MainActor static func name(_ localizer: Localizer) -> String { "\(localizer.text("landing.parcel.sneakers")) 👟" }

    /// The parcel once `step` of its scans are in.
    static func parcel(step: Int, name: String, now: Date = Date()) -> Parcel {
        let step = max(1, min(step, steps))
        let events = scans.prefix(step).enumerated().map { index, scan in
            TrackingEvent(
                id: UUID(uuidString: "5A3B1E00-0000-4000-8000-00000000000\(index + 1)")!, packageID: parcelID,
                stage: scan.stage, description: "", location: scan.stop.name,
                occurredAt: DateParser.isoString(now.addingTimeInterval(-scan.hoursAgo * 3600)),
                place: scan.stop.place
            )
        }
        // It arrives in three days, then two, then tomorrow, then today.
        let daysLeft = Double(max(0, 4 - step))
        return Parcel(
            id: parcelID, trackingNumber: trackingNumber, label: name, carrier: .dhl,
            createdAt: DateParser.isoString(now.addingTimeInterval(-96 * 3600)),
            expectedDelivery: ParcelOrganizer.dayKey(now.addingTimeInterval(daysLeft * 86_400)),
            lastSyncedAt: DateParser.isoString(now), syncStatus: .ok,
            carrierData: CarrierData(destinationCountry: "CH"),
            notificationsMuted: false, trackingEvents: Array(events)
        )
    }

    /// The parcel as it opens: picked up, every scan of its story told in the app's language.
    @MainActor static func delivered(localizer: Localizer, now: Date = Date()) -> Parcel {
        let events = story.enumerated().map { index, scan in
            TrackingEvent(
                id: UUID(uuidString: String(format: "5A3B1E00-0000-4000-8000-%012X", 0x100 + index))!, packageID: parcelID,
                stage: scan.stage, description: localizer.text(scan.line), location: scan.stop?.location,
                occurredAt: DateParser.isoString(now.addingTimeInterval(-scan.hoursAgo * 3600)),
                place: scan.stop?.place
            )
        }
        return Parcel(
            id: parcelID, trackingNumber: trackingNumber, label: name(localizer), carrier: .dhl,
            createdAt: DateParser.isoString(now.addingTimeInterval(-96 * 3600)),
            expectedDelivery: ParcelOrganizer.dayKey(now),
            lastSyncedAt: DateParser.isoString(now), syncStatus: .ok,
            carrierData: CarrierData(senderName: "Sole Studio", pickupPoint: "\(kiosk)\nBahnhofplatz 15, 8001 Zürich",
                                     weightKg: 0.9, destinationCountry: "CH"),
            notificationsMuted: false, trackingEvents: events
        )
    }

    /// How long a scan stays before the next one comes in: the last mile and the arrival get a longer look.
    static func hold(_ step: Int) -> Double {
        switch step {
        case 4: 3.4
        case 5: 5.2
        default: 2.7
        }
    }

    /// The alert that lands with a scan worth one.
    @MainActor static func ping(step: Int, localizer: Localizer) -> String? {
        switch step {
        case 4: "\(localizer.text("stage.out_for_delivery")) · \(localizer.text("time.today")), 13:00–17:00"
        case 5: "\(localizer.text("stage.delivered")) · \(kiosk), 18:05"
        default: nil
        }
    }
}

/// The first screen of an iPhone nobody has followed a parcel on: a sample parcel crossing the
/// world on its map, scan by scan, over the question and the field that answers it. On an iPad or an
/// open iPhone Duo the journey fills the screen and the question stands over it, as on the landing;
/// a Duo standing half open gives each its own side of the fold. It stands over the deliveries and
/// leaves once the first parcel is in.
struct FirstOpenView: View {
    /// A sheet covers the screen: the journey waits.
    let covered: Bool
    let onAdd: (FirstParcelRequest) -> Void

    @EnvironmentObject private var session: SessionStore
    @EnvironmentObject private var store: ParcelStore
    @EnvironmentObject private var localizer: Localizer
    @EnvironmentObject private var links: ParcelLinkStore
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @Environment(\.scenePhase) private var scenePhase
    @State private var step = FirstOpenJourney.stillStep
    @State private var appeared = false
    @State private var atlas: WorldAtlas?
    /// The parcel the picture opens on, picked up, as its page shows it.
    @State private var sample: Parcel?
    @State private var showingSample = false
    /// Where an iPad's question ends and its parcel's name starts: the route keeps between the two.
    @State private var posterTop: CGFloat = 0
    @State private var posterFloor: CGFloat = 0
    @Namespace private var transition
    @ObservedObject private var catalog = CarrierCatalog.shared

    private var running: Bool {
        !reduceMotion && scenePhase == .active && !covered && atlas != nil && links.route == nil && !showingSample
    }
    /// A page zoomed open under a hidden bar is laid out without it and drops once the bar shows, so the screen keeps
    /// its empty bar where that bar draws nothing and lets taps through to the header under it.
    private static var keepsBar: Bool {
        if #available(iOS 26.0, *) { true } else { false }
    }
    private var identity: CarrierVisualIdentity { CarrierVisualIdentity.of(.dhl, catalog: catalog, language: localizer.language) }

    var body: some View {
        // The screen is measured from the window, so the bar the parcel's page needs does not move it.
        GeometryReader { window in
            let top = window.safeAreaInsets.top
            let screen = window.size.height + top + window.safeAreaInsets.bottom
            // The map takes what the question and its field leave, within reason: a phone's worth, or half a taller window.
            let heroHeight = min(screen >= 1000 ? screen / 2 : 446, max(300, screen - 404))
            // The picture opens on the parcel's own page, which goes back to it as any parcel's does.
            NavigationStack {
                let page = VStack(spacing: 0) {
                    hero(height: heroHeight, top: top)
                        .offset(y: appeared ? 0 : -18)
                        .opacity(appeared ? 1 : 0)
                    ask(roomy: screen >= 800)
                        .offset(y: appeared ? 0 : 14)
                        .opacity(appeared ? 1 : 0)
                }
                Group {
                    if let fold = window.fold {
                        spread(fold: fold, screen: screen, top: top, bottom: window.safeAreaInsets.bottom)
                    } else {
                        // A screen too short for the map and the question, a phone on its side, scrolls; any other holds still.
                        ViewThatFits(in: .vertical) {
                            // An iPad, an open iPhone Duo or a large window has room for the question over the whole journey.
                            if min(window.size.width, screen) >= 600 {
                                poster(size: CGSize(width: window.size.width, height: screen), top: top)
                            }
                            page.frame(maxHeight: .infinity, alignment: .top)
                            ScrollView { page }.scrollIndicators(.hidden)
                        }
                    }
                }
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
                .ignoresSafeArea(edges: .top)
                .background(Brand.background.ignoresSafeArea())
                .toolbar(Self.keepsBar ? .visible : .hidden, for: .navigationBar)
                .navigationDestination(isPresented: $showingSample) {
                    ParcelDetailView(parcelID: FirstOpenJourney.parcelID, transition: transition, sample: sample)
                        .environment(\.keyboardInFront, !covered && links.route == nil)
                }
            }
        }
        .dynamicTypeSize(...DynamicTypeSize.accessibility1)
        .task {
            let loaded = await WorldAtlas.bundled.value
            // The story starts from its first scan; a still picture shows the parcel a day away.
            if !reduceMotion { step = 1 }
            atlas = loaded
            withAnimation(reduceMotion ? nil : .spring(duration: 0.7, bounce: 0.12)) { appeared = true }
        }
        .task(id: running) {
            guard running else { return }
            while !Task.isCancelled {
                do { try await Task.sleep(for: .seconds(FirstOpenJourney.hold(step))) } catch { return }
                withAnimation(.easeInOut(duration: 0.5)) { step = step % FirstOpenJourney.steps + 1 }
            }
        }
        .onChange(of: reduceMotion) { _, reduce in if reduce { step = FirstOpenJourney.stillStep } }
    }

    // MARK: - The sample parcel on its map

    /// `beside` stands it as a page beside the question, its trailing corners rounded, over the whole height.
    private func hero(height: CGFloat, top: CGFloat, beside: Bool = false) -> some View {
        let parcel = FirstOpenJourney.parcel(step: step, name: FirstOpenJourney.name(localizer))
        let corners = beside
            ? UnevenRoundedRectangle(bottomTrailingRadius: 38, topTrailingRadius: 38, style: .continuous)
            : UnevenRoundedRectangle(bottomLeadingRadius: 38, bottomTrailingRadius: 38, style: .continuous)
        return Button(action: openSample) {
            ZStack(alignment: .bottom) {
                identity.surface
                if let atlas {
                    RouteEngraving(
                        atlas: atlas, route: ParcelRoute(parcel: parcel, atlas: atlas, language: localizer.language),
                        stage: parcel.currentStage, identity: identity, peek: false,
                        insets: EdgeInsets(top: top + 112, leading: 88, bottom: 128, trailing: 54), floor: height - 118
                    )
                    .transition(.opacity)
                }
                HStack(alignment: .bottom, spacing: 16) {
                    VStack(alignment: .leading, spacing: 9) {
                        CarrierFleetMark(identity: identity)
                        Text(parcel.label).font(.title2.weight(.semibold)).foregroundStyle(Color.primary)
                        Text(status(parcel))
                            .font(.caption)
                            .foregroundStyle(identity.ink)
                            .id(step)
                            .transition(.opacity)
                    }
                    Spacer(minLength: 0)
                    DeliveryPostageStamp(parcel: parcel, identity: identity, width: 50, appeared: appeared)
                }
                .multilineTextAlignment(.leading)
                .padding(.horizontal, 26)
                .padding(.bottom, 24)
            }
            .frame(height: height)
            .clipShape(corners)
            .contentShape(Rectangle())
            .matchedTransitionSource(id: FirstOpenJourney.parcelID, in: transition)
        }
        .buttonStyle(FirstOpenHeroStyle())
        .accessibilityLabel(localizer.text("landing.pip.open"))
        .overlay(alignment: .top) {
            VStack(spacing: 12) {
                masthead
                if let ping = FirstOpenJourney.ping(step: step, localizer: localizer) {
                    FirstOpenPing(detail: ping, identity: identity)
                        .id(step)
                        .transition(.offset(y: -6).combined(with: .opacity))
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .allowsHitTesting(false)
                }
            }
            .padding(.horizontal, 24)
            .padding(.top, top + 10)
        }
    }

    // MARK: - An iPhone Duo standing half open

    /// The fold parts the screen in two: the journey takes the first part, as it leads a phone's screen, and the
    /// question the second, so nothing lies across the fold.
    private func spread(fold: CGRect, screen: CGFloat, top: CGFloat, bottom: CGFloat) -> some View {
        let beside = fold.height > fold.width
        let layout = beside ? AnyLayout(HStackLayout(spacing: 0)) : AnyLayout(VStackLayout(spacing: 0))
        return layout {
            hero(height: beside ? screen : fold.minY + top, top: top, beside: beside)
                .frame(width: beside ? fold.minX : nil)
                .offset(x: beside && !appeared ? -18 : 0, y: !beside && !appeared ? -18 : 0)
                .opacity(appeared ? 1 : 0)
            Color.clear.frame(width: beside ? fold.width : nil, height: beside ? nil : fold.height)
            ask(roomy: true, centred: true)
                .padding(.top, beside ? top : 0)
                .padding(.bottom, bottom)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .offset(y: appeared ? 0 : 14)
                .opacity(appeared ? 1 : 0)
        }
        .ignoresSafeArea(edges: .bottom)
    }

    // MARK: - An iPad's whole screen

    /// The landing's own picture: the journey fills the screen and the question stands over it, its field and button
    /// on one line. The parcel is named at the foot of its map, over the carriers rolling by.
    private func poster(size: CGSize, top: CGFloat) -> some View {
        let parcel = FirstOpenJourney.parcel(step: step, name: FirstOpenJourney.name(localizer))
        let portrait = size.height > size.width
        let title = min(76, size.width * 0.072, size.height * 0.085)
        // Until they are measured, the question and the parcel's name take about what they will.
        let routeTop = posterTop > 0 ? posterTop + 40 : top + 400
        let routeFloor = posterFloor > 0 ? posterFloor : size.height - 280
        return ZStack {
            Button(action: openSample) {
                ZStack {
                    identity.surface
                    if let atlas {
                        RouteEngraving(
                            atlas: atlas, route: ParcelRoute(parcel: parcel, atlas: atlas, language: localizer.language),
                            stage: parcel.currentStage, identity: identity, peek: false,
                            insets: EdgeInsets(top: routeTop, leading: 110, bottom: size.height - routeFloor + 40, trailing: 100),
                            floor: routeFloor - 12
                        )
                        .transition(.opacity)
                    }
                    // The question reads over a quiet sky: a route that leaves the screen fades out under it.
                    LinearGradient(colors: [identity.surface.opacity(0.9), identity.surface.opacity(0.6), identity.surface.opacity(0)],
                                   startPoint: .top, endPoint: .bottom)
                        .frame(height: routeTop)
                        .frame(maxHeight: .infinity, alignment: .top)
                }
                .contentShape(Rectangle())
                .matchedTransitionSource(id: FirstOpenJourney.parcelID, in: transition)
            }
            .buttonStyle(FirstOpenHeroStyle())
            .accessibilityLabel(localizer.text("landing.pip.open"))
            // It runs under an open iPhone Duo's status bar at the side, as a phone's map runs under its bar at the top.
            .ignoresSafeArea(edges: [.bottom, .horizontal])

            VStack(spacing: 0) {
                masthead
                    .padding(.horizontal, 28)
                    .padding(.top, top + 10)
                VStack(spacing: 0) {
                    Text(localizer.text("peek.title"))
                        .font(.system(size: title, weight: .bold, design: .rounded))
                        .tracking(-title * 0.035)
                        .foregroundStyle(Brand.ink)
                        .multilineTextAlignment(.center)
                        .minimumScaleFactor(0.6)
                        .lineLimit(2)
                        .accessibilityAddTraits(.isHeader)
                    Text(localizer.text(store.tracksWithoutAccount ? "native.pasteLead" : "arrival.welcomeSubtitle"))
                        .font(.title3)
                        .foregroundStyle(.secondary)
                        .multilineTextAlignment(.center)
                        .fixedSize(horizontal: false, vertical: true)
                        .padding(.top, 10)
                    Group {
                        if store.tracksWithoutAccount {
                            // On one line while the field keeps room for a number; one under the other at large type.
                            ViewThatFits(in: .horizontal) {
                                HStack(spacing: 12) {
                                    field(large: true).frame(minWidth: 320)
                                    pasteButton(large: true).fixedSize()
                                }
                                VStack(spacing: 12) {
                                    field(large: true)
                                    pasteButton(large: true)
                                }
                            }
                        } else {
                            demoButton(large: true).frame(maxWidth: 420)
                        }
                    }
                    .padding(.top, 30)
                }
                .frame(maxWidth: 780)
                .padding(.horizontal, 40)
                .padding(.top, portrait ? 72 : 28)
                .onGeometryChange(for: CGFloat.self) { $0.frame(in: .named(Self.posterSpace)).maxY } action: { posterTop = $0 }
                .offset(y: appeared ? 0 : 14)
                Spacer(minLength: 24)
                posterCaption(parcel)
                    .padding(.horizontal, 44)
                    .padding(.bottom, 26)
                    .onGeometryChange(for: CGFloat.self) { $0.frame(in: .named(Self.posterSpace)).minY } action: { posterFloor = $0 }
                    // It belongs to the picture: a tap on it opens the parcel as one on the map does.
                    .allowsHitTesting(false)
                    .accessibilityHidden(true)
                CarrierFleet(rolling: running)
                    .padding(.horizontal, 44)
                    .padding(.bottom, 20)
            }
        }
        .coordinateSpace(.named(Self.posterSpace))
        .opacity(appeared ? 1 : 0)
    }

    private static let posterSpace = "FirstOpenView.poster"

    /// The sample parcel, named in the corner of its map, with the alert that lands with a scan worth one.
    private func posterCaption(_ parcel: Parcel) -> some View {
        HStack(alignment: .bottom, spacing: 18) {
            VStack(alignment: .leading, spacing: 10) {
                if let ping = FirstOpenJourney.ping(step: step, localizer: localizer) {
                    FirstOpenPing(detail: ping, identity: identity)
                        .id(step)
                        .transition(.offset(y: 6).combined(with: .opacity))
                        .padding(.bottom, 8)
                }
                CarrierFleetMark(identity: identity)
                Text(parcel.label).font(.largeTitle.weight(.semibold)).foregroundStyle(Color.primary)
                Text(status(parcel))
                    .font(.subheadline)
                    .foregroundStyle(identity.ink)
                    .id(step)
                    .transition(.opacity)
            }
            Spacer(minLength: 0)
            DeliveryPostageStamp(parcel: parcel, identity: identity, width: 64, appeared: appeared)
        }
        .multilineTextAlignment(.leading)
    }

    // MARK: - The question

    /// `centred` stands the question in the middle of its room rather than under the picture.
    @ViewBuilder private func ask(roomy: Bool, centred: Bool = false) -> some View {
        VStack(spacing: 0) {
            if centred { Spacer(minLength: 0) }
            Text(localizer.text("peek.title"))
                .font(.system(.largeTitle, design: .rounded, weight: .bold))
                .tracking(-1.2)
                .foregroundStyle(Brand.ink)
                .multilineTextAlignment(.center)
                .minimumScaleFactor(0.7)
                .lineLimit(2)
                .accessibilityAddTraits(.isHeader)
                .padding(.top, roomy ? 26 : 18)
            Text(localizer.text(store.tracksWithoutAccount ? "native.pasteLead" : "arrival.welcomeSubtitle"))
                .font(.subheadline)
                .foregroundStyle(.secondary)
                .multilineTextAlignment(.center)
                .fixedSize(horizontal: false, vertical: true)
                .padding(.top, 8)
            if store.tracksWithoutAccount {
                VStack(spacing: 10) {
                    field(large: false)
                    pasteButton(large: false)
                }
                .padding(.top, roomy ? 20 : 14)
            } else {
                demoButton(large: false).padding(.top, 20)
            }
            Spacer(minLength: 0)
            if roomy { CarrierFleet(rolling: running).padding(.bottom, 14) }
        }
        .padding(.horizontal, 24)
        .frame(maxWidth: 560)
    }

    /// It reads as the field it leads to: the sheet that takes a number, a link or a whole message.
    private func field(large: Bool) -> some View {
        HStack(spacing: 6) {
            Button { onAdd(.type) } label: {
                // A long placeholder takes a second line rather than being cut off.
                Text(localizer.text("add.trackingPlaceholder"))
                    .font(large ? .title3 : .body)
                    .foregroundStyle(.tertiary)
                    .lineLimit(2)
                    .fixedSize(horizontal: false, vertical: true)
                    .frame(maxWidth: .infinity, minHeight: large ? 64 : 58, alignment: .leading)
                    .contentShape(Rectangle())
            }
            .accessibilityLabel(localizer.text("add.tracking"))
            .accessibilityIdentifier("welcome.field")
            if LabelScanner.isAvailable {
                Button { onAdd(.scan) } label: {
                    Image(systemName: "barcode.viewfinder")
                        .font(large ? .title2 : .title3)
                        .foregroundStyle(Brand.ink)
                        .frame(width: large ? 48 : 44, height: large ? 48 : 44)
                }
                .accessibilityLabel(localizer.text("add.scan"))
            }
        }
        .buttonStyle(.plain)
        .padding(.leading, large ? 22 : 18)
        .padding(.trailing, large ? 8 : 7)
        .background(Brand.paper, in: RoundedRectangle(cornerRadius: large ? 20 : 18, style: .continuous))
        .overlay { RoundedRectangle(cornerRadius: large ? 20 : 18, style: .continuous).strokeBorder(Brand.separator.opacity(0.3), lineWidth: 1) }
    }

    private func pasteButton(large: Bool) -> some View {
        Button { paste() } label: {
            FirstParcelPrimaryLabel(title: localizer.text("landing.pasteAndTrack"), symbol: "doc.on.clipboard", large: large)
        }
        .buttonStyle(TactileButtonStyle())
        .accessibilityIdentifier("welcome.paste")
    }

    /// A build without a server has only its demo to show.
    private func demoButton(large: Bool) -> some View {
        Button { session.enterDemo() } label: {
            FirstParcelPrimaryLabel(title: localizer.text("welcome.demo"), symbol: nil, large: large)
        }
        .buttonStyle(TactileButtonStyle())
        .accessibilityIdentifier("welcome.demo")
    }

    /// The app's name, and the way back into an account.
    private var masthead: some View {
        HStack {
            PeekLockup().clearOfWindowControls()
            Spacer()
            Button(localizer.text("arrival.signInTitle")) { session.showSignIn() }
                .font(.subheadline.weight(.medium))
                .foregroundStyle(Brand.ink)
                .padding(.horizontal, 14)
                .frame(minHeight: 44)
                .background(Brand.paper.opacity(0.55), in: RoundedRectangle(cornerRadius: 12))
                .accessibilityIdentifier("welcome.signIn")
        }
    }

    private func status(_ parcel: Parcel) -> String {
        [localizer.parcelStatus(parcel), localizer.parcelDeliveryEstimate(parcel)].compactMap { $0 }.joined(separator: " · ")
    }

    /// The picture opens on the parcel's own page, picked up.
    private func openSample() {
        sample = FirstOpenJourney.delivered(localizer: localizer)
        showingSample = true
    }

    private func paste() {
        DeliveryAnalytics.shared.action("parcel-paste")
        onAdd(.paste(Clipboard.string ?? ""))
    }
}

/// The picture presses in a little, as a card does.
private struct FirstOpenHeroStyle: ButtonStyle {
    @Environment(\.accessibilityReduceMotion) private var reduceMotion

    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .brightness(configuration.isPressed ? -0.02 : 0)
            .animation(reduceMotion ? nil : .easeOut(duration: 0.18), value: configuration.isPressed)
    }
}

/// The app's main button: postal yellow, as wide as its column.
struct FirstParcelPrimaryLabel: View {
    let title: String
    let symbol: String?
    /// An iPad's, beside its field: larger, with room either side of its words.
    var large = false

    var body: some View {
        HStack(spacing: large ? 10 : 9) {
            if let symbol { Image(systemName: symbol).font((large ? Font.title3 : .body).weight(.medium)) }
            Text(title).font((large ? Font.title3 : .body).weight(.semibold))
        }
        .foregroundStyle(Brand.onAccent)
        .padding(.horizontal, large ? 30 : 0)
        .frame(maxWidth: .infinity)
        .frame(minHeight: large ? 64 : 54)
        .background(Brand.accent, in: RoundedRectangle(cornerRadius: large ? 20 : 16, style: .continuous))
    }
}

/// What an alert would say, as a small mark on the map in the card's own ink: the parcel is
/// nearly there, or there. It belongs to the picture and does not ask to be read first.
private struct FirstOpenPing: View {
    let detail: String
    let identity: CarrierVisualIdentity

    var body: some View {
        HStack(spacing: 6) {
            Image(systemName: "bell.fill").font(.system(size: 9))
            Text(detail).font(.caption.weight(.medium)).lineLimit(1).minimumScaleFactor(0.8)
        }
        .foregroundStyle(identity.ink)
        .padding(.horizontal, 10)
        .frame(height: 26)
        .background(identity.ink.opacity(0.1), in: Capsule())
        .accessibilityHidden(true)
    }
}

/// The carriers roll by in their own liveries, as on the site: the line stands twice in its
/// lane, and the second takes over where the first leaves.
struct CarrierFleet: View {
    let rolling: Bool
    @EnvironmentObject private var localizer: Localizer
    @ObservedObject private var catalog = CarrierCatalog.shared
    @State private var lap: CGFloat = 0
    @State private var started = Date()

    private static let carriers: [CarrierID] = [.swissPost, .dhl, .ups, .dpd, .glsDe, .fedex, .laPoste, .royalMail, .chronopost, .usps]
    private static let gap: CGFloat = 26
    /// Points a second: slow enough to read a name as it passes.
    private static let speed: CGFloat = 16

    var body: some View {
        let name = { (carrier: CarrierID) in self.catalog.info(for: carrier, language: self.localizer.language).displayName }
        VStack(spacing: 12) {
            Text(localizer.text("landing.ribbon.caption", ["first": name(.swissPost), "last": name(.usps)]))
                .font(.caption)
                .foregroundStyle(.secondary)
                .multilineTextAlignment(.center)
                .fixedSize(horizontal: false, vertical: true)
            // The line is wider than the screen and must not widen it.
            Color.clear
                .frame(height: 20)
                .overlay(alignment: .leading) {
                    TimelineView(.animation(paused: !rolling || lap == 0)) { timeline in
                        let travelled = rolling && lap > 0
                            ? CGFloat(timeline.date.timeIntervalSince(started)) * Self.speed : 0
                        HStack(spacing: Self.gap) {
                            line.onGeometryChange(for: CGFloat.self) { $0.size.width } action: { lap = $0 + Self.gap }
                            line
                        }
                        .fixedSize()
                        .offset(x: -42 - travelled.truncatingRemainder(dividingBy: max(lap, 1)))
                    }
                }
                .clipped()
                .mask {
                    LinearGradient(stops: [
                        .init(color: .clear, location: 0), .init(color: .black, location: 0.1),
                        .init(color: .black, location: 0.9), .init(color: .clear, location: 1),
                    ], startPoint: .leading, endPoint: .trailing)
                }
        }
        .accessibilityElement(children: .ignore)
        .accessibilityLabel(localizer.text("landing.ribbon.label", ["carriers": Self.carriers.prefix(5).map(name).joined(separator: ", ")]))
    }

    private var line: some View {
        HStack(spacing: Self.gap) {
            ForEach(Self.carriers) { carrier in
                CarrierFleetMark(identity: CarrierVisualIdentity.of(carrier, catalog: catalog, language: localizer.language)).fixedSize()
            }
        }
    }
}

// MARK: - Nothing followed yet

/// The deliveries with nothing in them: Pip waits by the question, and the ways to answer it. Tapped, he opens the
/// sample parcel's page, which grows out of him.
struct DeliveriesEmptyState: View {
    let transition: Namespace.ID
    let onAdd: (FirstParcelRequest) -> Void
    let onSample: () -> Void

    @EnvironmentObject private var session: SessionStore
    @EnvironmentObject private var store: ParcelStore
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @State private var pressed = false

    private var canScan: Bool { LabelScanner.isAvailable }

    var body: some View {
        VStack(spacing: 0) {
            Button(action: onSample) {
                UnwrappingParcel(open: 0, pressed: pressed)
                    .frame(width: 204, height: 204 * 31 / 30)
                    .animation(reduceMotion ? nil : .spring(response: 0.3, dampingFraction: 0.64), value: pressed)
                    .matchedTransitionSource(id: FirstOpenJourney.parcelID, in: transition)
            }
            .buttonStyle(PipPressStyle { pressed = $0 })
            .accessibilityLabel(localizer.text("landing.pip.open"))
            .accessibilityIdentifier("deliveries.empty.sample")
            Text(localizer.text("peek.title"))
                .font(.system(.title, design: .rounded, weight: .bold))
                .tracking(-0.9)
                .foregroundStyle(Brand.ink)
                .multilineTextAlignment(.center)
                .accessibilityAddTraits(.isHeader)
            Text(localizer.text("app.emptyDescription"))
                .font(.subheadline)
                .foregroundStyle(.secondary)
                .multilineTextAlignment(.center)
                .fixedSize(horizontal: false, vertical: true)
                .padding(.top, 8)
            Button { onAdd(.paste(Clipboard.string ?? "")) } label: {
                FirstParcelPrimaryLabel(title: localizer.text("landing.pasteAndTrack"), symbol: "doc.on.clipboard")
            }
            .accessibilityIdentifier("deliveries.empty.paste")
            .padding(.top, 22)
            // Side by side while both fit; otherwise one under the other, each across the column.
            ViewThatFits(in: .horizontal) {
                HStack(spacing: 10) { quietButtons(wraps: false) }
                VStack(spacing: 10) { quietButtons(wraps: true) }
            }
            .padding(.top, 10)
            hint.padding(.top, 14)
        }
        .buttonStyle(TactileButtonStyle())
        .padding(.horizontal, 8)
        .padding(.top, 34)
        .padding(.bottom, 12)
        .frame(maxWidth: 420)
        .frame(maxWidth: .infinity)
    }

    /// "No number handy? Tap Pip to open a sample.", the words that name the tap set apart.
    private var hint: some View {
        let action = localizer.text("landing.pip.action")
        let parts = localizer.text("landing.pip.hint", ["action": "\u{0}"]).components(separatedBy: "\u{0}")
        return (Text(parts.first ?? "") + Text(action).fontWeight(.semibold).foregroundColor(Brand.ink) + Text(parts.count > 1 ? parts[1] : ""))
            .font(.footnote)
            .foregroundStyle(.secondary)
            .multilineTextAlignment(.center)
            .fixedSize(horizontal: false, vertical: true)
            .accessibilityHidden(true)
    }

    @ViewBuilder private func quietButtons(wraps: Bool) -> some View {
        if canScan {
            Button { onAdd(.scan) } label: { quiet(localizer.text("add.scan"), symbol: "barcode.viewfinder", wraps: wraps) }
        }
        Button { onAdd(.type) } label: { quiet(localizer.text("app.addParcel"), symbol: "keyboard", wraps: wraps) }
            .accessibilityIdentifier("deliveries.empty.add")
    }

    /// One under the other, a button has the column's width and its words may take a second line.
    private func quiet(_ title: String, symbol: String, wraps: Bool) -> some View {
        HStack(spacing: 8) {
            Image(systemName: symbol).font(.subheadline)
            Text(title).font(.subheadline.weight(.medium)).lineLimit(wraps ? nil : 1).minimumScaleFactor(0.8).multilineTextAlignment(.center)
        }
        .foregroundStyle(Brand.ink)
        .frame(maxWidth: .infinity)
        .frame(minHeight: 48)
        .background(Brand.paper, in: RoundedRectangle(cornerRadius: 14, style: .continuous))
        .overlay { RoundedRectangle(cornerRadius: 14, style: .continuous).strokeBorder(Brand.separator.opacity(0.3), lineWidth: 0.75) }
    }
}

/// Pip gives under the finger; the press is told to the drawing, which squashes.
private struct PipPressStyle: ButtonStyle {
    let onPressChanged: (Bool) -> Void

    func makeBody(configuration: Configuration) -> some View {
        configuration.label.onChange(of: configuration.isPressed) { _, pressed in onPressChanged(pressed) }
    }
}

/// Under the parcels someone follows without an account: the account, offered once and quietly.
struct DeviceAccountRow: View {
    @EnvironmentObject private var session: SessionStore
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dynamicTypeSize) private var dynamicTypeSize

    var body: some View {
        Button { session.showSignIn() } label: {
            // At the largest text sizes the button goes under the words, which keep the row's width.
            let layout = dynamicTypeSize.isAccessibilitySize
                ? AnyLayout(VStackLayout(alignment: .leading, spacing: 10))
                : AnyLayout(HStackLayout(spacing: 12))
            layout {
                VStack(alignment: .leading, spacing: 2) {
                    Text(localizer.text("link.account.title"))
                        .font(.subheadline.weight(.medium))
                        .foregroundStyle(Brand.ink)
                    Text(localizer.text("native.guest.kept"))
                        .font(.caption)
                        .foregroundStyle(.secondary)
                }
                .multilineTextAlignment(.leading)
                .fixedSize(horizontal: false, vertical: true)
                if !dynamicTypeSize.isAccessibilitySize { Spacer(minLength: 8) }
                Text(localizer.text("arrival.signInTitle"))
                    .font(.footnote.weight(.medium))
                    .foregroundStyle(Brand.ink)
                    .padding(.horizontal, 12)
                    .frame(minHeight: 34)
                    .background(Brand.cream, in: Capsule())
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(.horizontal, 15)
            .padding(.vertical, 10)
            .frame(minHeight: 66)
            .background(Brand.paper, in: RoundedRectangle(cornerRadius: 16, style: .continuous))
            .overlay { RoundedRectangle(cornerRadius: 16, style: .continuous).strokeBorder(Brand.separator.opacity(0.3), lineWidth: 0.75) }
        }
        .buttonStyle(TactileButtonStyle())
        .accessibilityIdentifier("deliveries.account")
    }
}

/// Friends compare passports between accounts: without one, the tab says what it is for.
struct FriendsSignedOutView: View {
    @EnvironmentObject private var session: SessionStore
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dynamicTypeSize) private var dynamicTypeSize
    /// The title keeps its size until the largest text sizes, then grows with them.
    @ScaledMetric(relativeTo: .title) private var titleSize: CGFloat = 26
    @State private var showingAccount = false

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(spacing: 22) {
                    FriendsPostagePair().padding(.bottom, 12)
                    Text(localizer.text("friends.title")).font(.system(size: dynamicTypeSize.isAccessibilitySize ? titleSize : 26, weight: .semibold))
                    Text(localizer.text("friends.introSummary"))
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                        .multilineTextAlignment(.center)
                    Button { session.showSignIn() } label: {
                        Text(localizer.text("arrival.signInTitle")).frame(maxWidth: .infinity, minHeight: 46)
                    }
                    .buttonStyle(.borderedProminent)
                    .tint(Brand.accent)
                    .foregroundStyle(Brand.onAccent)
                    .accessibilityIdentifier("friends.signIn")
                }
                .padding(.vertical, 70)
                .padding(.horizontal, 20)
                .frame(maxWidth: 680)
                .frame(maxWidth: .infinity)
            }
            .scrollIndicators(.hidden)
            .background(Brand.background)
            .navigationTitle(localizer.text("friends.title"))
            .navigationBarTitleDisplayMode(.inline)
            .toolbar { AccountToolbarItem { showingAccount = true } }
            .sheet(isPresented: $showingAccount) { AccountView().formOnPad() }
        }
    }
}
