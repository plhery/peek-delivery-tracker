import SwiftUI

extension ParcelRoute {
    /// A parcel's route, with its destination country when the carrier gave one.
    init(parcel: Parcel, atlas: WorldAtlas, language: AppLanguage) {
        let name = { (code: String) in TrackingLocation.countryName(code, language: language) }
        let destination = parcel.carrierData?.destinationCountry.flatMap { code in
            atlas.label(of: code).map { RoutePlace.country(code, name: name(code), at: $0) }
        }
        self.init(events: parcel.trackingEvents, destination: destination, countryName: name)
    }
}

/// The route, drawn in the card's own ink across the top of the parcel's card, with Pip beside the parcel's place.
struct RouteEngraving: View {
    let atlas: WorldAtlas
    let route: ParcelRoute
    let stage: TrackingStage?
    let identity: CarrierVisualIdentity
    /// Off on the Next up card, which is one tap target in a scrolling list.
    var peek = true
    var insets = EdgeInsets(top: 40, leading: 16, bottom: 44, trailing: 16)
    /// Where the card's top row ends, which Pip stays below.
    var ceiling: CGFloat = 52
    /// Where the card starts writing over the bottom of the map, which Pip stays above.
    var floor: CGFloat?
    /// What the card writes over the map below its top row, such as a second carrier's mark.
    var covered: CGRect?
    /// The card's buttons over the top of the map, which the names and Pip keep clear of.
    var buttons: CGRect?
    /// How much lower the route starts, below a second carrier's mark.
    var room: CGFloat = 0
    @EnvironmentObject private var localizer: Localizer

    /// A second carrier's mark adds a line to the top of a card with a map. The card grows by two lines, and its route
    /// starts that much lower, so it keeps the room it has on any other card. Pip keeps his ceiling: beside the
    /// parcel's dot, he only keeps off the mark.
    static let handoverRoom: CGFloat = 40

    var body: some View {
        WorldMapView(
            atlas: atlas, route: route, mode: route.defaultMode(for: stage),
            palette: .tint(ink: identity.ink, surface: identity.surface), labels: .ends, showsContext: false, peek: peek,
            // The parcel's dot pulses until the journey is over.
            live: stage != .delivered && stage != .returned,
            // Pip keeps below the top row of the parcel's page, and of Next up alike, so both show the same picture.
            pip: PipMood(stage: stage).map { PipRequest(mood: $0, ceiling: ceiling, floor: floor) },
            fades: true, insets: EdgeInsets(top: insets.top + room, leading: insets.leading, bottom: insets.bottom, trailing: insets.trailing),
            covered: covered, buttons: buttons, language: localizer.language
        )
        // The globe button beside the bell is the accessible way in.
        .accessibilityHidden(true)
    }
}

/// A parcel's whole journey, small, at the end of its card. It belongs to the card's picture and is not there to be
/// read: no names, no Pip, no pulse, and a line and dots as pale as the map they lie on.
struct CardRoute: View {
    let atlas: WorldAtlas
    let route: ParcelRoute
    let ink: Color
    /// The card's own colour, which is paler on a past delivery.
    let surface: Color
    @EnvironmentObject private var localizer: Localizer

    /// How wide the picture is: the same on every card, however wide the card.
    static func width(in card: CGFloat) -> CGFloat { min(card * 0.58, 210) }
    /// How far from the card's trailing edge its words end, so they keep clear of the route.
    static let clearance: CGFloat = 120

    var body: some View {
        WorldMapView(
            atlas: atlas, route: route, mode: .journey, palette: .quietTint(ink: ink, surface: surface), labels: .none, showsContext: false,
            fadesIn: true, quiet: true,
            // Room for the date in the card's top row, and for the land to fade in from under the words.
            insets: EdgeInsets(top: 36, leading: 78, bottom: 16, trailing: 20), language: localizer.language
        )
        .allowsHitTesting(false)
        .accessibilityHidden(true)
    }
}

/// The whole map over the parcel: the journey on a globe, or the last mile up close.
struct ParcelMapScreen: View {
    let atlas: WorldAtlas
    let route: ParcelRoute
    let stage: TrackingStage?
    /// The carrier's colour marks where the parcel is now.
    let accent: Color

    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dismiss) private var dismiss
    @Environment(\.dynamicTypeSize) private var dynamicTypeSize
    @State private var chosen: ParcelRoute.Mode?
    @State private var free = false
    @State private var recenter = 0
    @State private var barHeight: CGFloat = 220
    @State private var time = Date()
    @StateObject private var tiles = WorldDetailStore()

    private var mode: ParcelRoute.Mode { chosen ?? route.defaultMode(for: stage) }

    var body: some View {
        GeometryReader { proxy in
            let safe = proxy.safeAreaInsets
            // With nothing above the screen (an iPhone on its side, the Duo's cover) the close button keeps off its edge.
            let closeTop = max(4, 14 - safe.top)
            // An iPhone Duo standing half open keeps the route on the first part of the screen and the summary on the
            // second, as Apple's own arrangements do, so neither lies across the fold.
            let fold = proxy.fold
            let beside = fold.map { $0.height > $0.width } ?? false
            ZStack(alignment: .bottom) {
                // The route is framed in the space the summary leaves.
                WorldMapView(
                    atlas: atlas, route: route, mode: mode, palette: .map(accent: accent), sites: true, interactive: true, night: time,
                    live: stage != .delivered && stage != .returned,
                    // Pip came along from the card. Here he keeps clear of what lies over the map: the summary and the button that closes it.
                    pip: PipMood(stage: stage).map { PipRequest(mood: $0, inset: true) },
                    // The route keeps clear of the sides the system holds too: the Dynamic Island on an iPhone on its side, the cover's status bar.
                    insets: EdgeInsets(
                        top: safe.top + closeTop + 48, leading: safe.leading,
                        bottom: fold.map { beside ? safe.bottom + 24 : proxy.size.height + safe.bottom - $0.minY + 24 }
                            ?? safe.bottom + barHeight + 24,
                        trailing: fold.map { beside ? proxy.size.width + safe.trailing - $0.minX + 24 : safe.trailing } ?? safe.trailing
                    ),
                    recenter: recenter, language: localizer.language,
                    detail: tiles.pack.map { MapDetail(pack: $0, tiles: tiles.tiles, need: tiles.need) },
                    onFreeChange: { free = $0 }
                )
                .ignoresSafeArea()
                .accessibilityElement(children: .ignore)
                .accessibilityLabel(label)
                .accessibilityAddTraits(.isImage)

                // Over a large screen the summary keeps to about a phone's width, centred, or on its own side of the fold.
                bar(maxHeight: fold.map { beside ? proxy.size.height / 2 : proxy.size.height - $0.maxY - 24 } ?? proxy.size.height / 2)
                    .frame(maxWidth: 420)
                    .padding(.horizontal, 12)
                    .padding(.bottom, 12)
                    .frame(width: fold.flatMap { beside ? proxy.size.width - $0.maxX : nil })
                    .frame(maxWidth: .infinity, alignment: beside ? .trailing : .center)
            }
            .overlay(alignment: .topTrailing) {
                Button { dismiss() } label: {
                    Image(systemName: "xmark")
                        .font(.system(size: 15, weight: .semibold))
                        .frame(width: 44, height: 44)
                        .contentShape(Circle())
                }
                .buttonStyle(.plain)
                .glassSurface(in: Circle())
                .padding(.trailing, 14)
                .padding(.top, closeTop)
                .accessibilityLabel(localizer.text("map.close"))
            }
        }
        .background(Brand.background)
        .task { await tiles.open() }
    }

    /// The opened map has room for a facility's own name, so it says "Zürich-Mülligen" where the card says "Zürich".
    private var label: String {
        let origin = route.origin?.place.name(sites: true) ?? ""
        let end = (route.destination ?? route.current?.place)?.name(sites: true) ?? origin
        return end == origin ? localizer.text("map.labelOne", ["place": origin]) : localizer.text("map.label", ["from": origin, "to": end])
    }

    /// At the largest text sizes the summary keeps to `maxHeight` and scrolls, so the map stays in view.
    @ViewBuilder private func bar(maxHeight: CGFloat) -> some View {
        if dynamicTypeSize.isAccessibilitySize {
            ViewThatFits(in: .vertical) {
                surfaced(barContent)
                surfaced(
                    ScrollView { barContent }
                        .scrollBounceBehavior(.basedOnSize)
                        .clipShape(RoundedRectangle(cornerRadius: 24, style: .continuous))
                )
            }
            .frame(maxHeight: maxHeight, alignment: .bottom)
        } else {
            surfaced(barContent)
        }
    }

    /// The summary on its glass. The map keeps clear of its height.
    private func surfaced(_ content: some View) -> some View {
        content
            .glassSurface(in: RoundedRectangle(cornerRadius: 24, style: .continuous))
            .onGeometryChange(for: CGFloat.self) { $0.size.height } action: { barHeight = $0 }
    }

    private var barContent: some View {
        VStack(alignment: .leading, spacing: 14) {
            summary
            // With every place close by there is only one view: no buttons, even once the map is moved.
            if route.hasNearView {
                HStack(spacing: 2) {
                    viewButton(.journey, key: "map.journey", symbol: "globe")
                    viewButton(.now, key: "map.nearby", symbol: "location")
                }
                .padding(3)
                .background(Color.primary.opacity(0.06), in: Capsule())
                .accessibilityElement(children: .contain)
                .accessibilityLabel(localizer.text("map.view"))
            }
        }
        .padding(16)
        .frame(maxWidth: .infinity, alignment: .leading)
    }

    @ViewBuilder private var summary: some View {
        if let origin = route.origin, let current = route.current {
            let delivered = stage == .delivered
            // A finished journey has a length, not a distance "so far".
            let finished = delivered || stage == .returned
            let end = route.destination ?? current.place
            // A delivery scan with no place happened at the last stop only when that stop is in the destination country (`latestLocated`).
            let endLabel = delivered && (route.latestLocated || route.destination != nil) ? "map.delivered"
                : route.destination != nil ? "map.to" : route.latestLocated ? "map.now" : "map.lastSeen"
            let single = route.stops.count == 1 && route.destination == nil
            // At the largest text sizes the two places stand one under the other.
            let stacked = dynamicTypeSize.isAccessibilitySize
            let places = stacked
                ? AnyLayout(VStackLayout(alignment: .leading, spacing: 10))
                : AnyLayout(HStackLayout(alignment: .top, spacing: 14))
            VStack(alignment: .leading, spacing: 10) {
                places {
                    if !single { endpoint(localizer.text("map.from"), origin.place, alignment: .leading) }
                    endpoint(localizer.text(endLabel), end, alignment: single || stacked ? .leading : .trailing)
                }
                if !single {
                    // The figures go one under the other when they don't fit on one line.
                    ViewThatFits(in: .horizontal) {
                        HStack(spacing: 16) { figures(finished: finished) }
                        VStack(alignment: .leading, spacing: 4) { figures(finished: finished) }
                    }
                    .font(.caption)
                    .monospacedDigit()
                    .foregroundStyle(.secondary)
                }
            }
        }
    }

    /// How far the parcel has come, how far it has to go, and through how many countries.
    @ViewBuilder private func figures(finished: Bool) -> some View {
        if route.kilometres >= 1 {
            let distance = kilometres(route.kilometres)
            Text(finished ? distance : localizer.text("map.soFar", ["distance": distance]))
        }
        if !finished, let remaining = route.remainingKilometres {
            Text(localizer.text("map.toGo", ["distance": kilometres(remaining)]))
        }
        if route.countries.count > 1 {
            Text(localizer.text("map.countries.many", ["count": route.countries.count]))
        }
    }

    private func kilometres(_ value: Double) -> String {
        ParcelRoute.formattedKilometres(value, locale: localizer.language.locale)
    }

    private func endpoint(_ title: String, _ place: RoutePlace, alignment: HorizontalAlignment) -> some View {
        let country = TrackingLocation.countryName(place.country, language: localizer.language)
        let name = place.name(sites: true)
        // At the largest text sizes a long name wraps rather than end in "…".
        let lines: Int? = dynamicTypeSize.isAccessibilitySize ? nil : 1
        return VStack(alignment: alignment, spacing: 2) {
            Text(title)
                .font(.caption2.weight(.medium))
                .textCase(.uppercase)
                .tracking(1.4)
                .foregroundStyle(.secondary)
            Text(name)
                .font(.title2.weight(.semibold))
                .lineLimit(lines)
            Text("\(TrackingLocation.flag(place.country)) \(country)")
                .font(.caption)
                .foregroundStyle(.secondary)
                .lineLimit(lines)
        }
        .frame(maxWidth: .infinity, alignment: alignment == .leading ? .leading : .trailing)
        .multilineTextAlignment(alignment == .leading ? .leading : .trailing)
        .accessibilityElement(children: .ignore)
        .accessibilityLabel(name == country ? "\(title), \(country)" : "\(title), \(name), \(country)")
    }

    private func viewButton(_ target: ParcelRoute.Mode, key: String, symbol: String) -> some View {
        let selected = mode == target && !free
        return Button {
            if target == mode { recenter += 1 }
            chosen = target
        } label: {
            Label(localizer.text(key), systemImage: symbol)
                .font(.caption.weight(.medium))
                .padding(.horizontal, 12)
                .frame(minHeight: 36)
                .foregroundStyle(selected ? Brand.paper : Color.secondary)
                .background(selected ? Brand.ink : Color.clear, in: Capsule())
                .contentShape(Capsule())
        }
        .buttonStyle(.plain)
        .accessibilityAddTraits(selected ? .isSelected : [])
    }
}
