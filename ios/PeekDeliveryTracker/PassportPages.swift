import SwiftUI

/// How the passport's pages lie in `width`, the room between the page's margins: one at a time with the next one's edge
/// showing, or open on two side by side, where the spread holds the chosen page.
struct PassportPageLayout: Equatable {
    var open: Bool
    var width: CGFloat
    var count = 3

    static let gap: CGFloat = 10
    /// The page's margins, which the track reaches into so a neighbour's edge shows.
    static let margin: CGFloat = 20

    /// A page's width: the room less the next page's edge, or half the open book.
    var page: CGFloat { max(0, open ? (width - Self.gap) / 2 : width - 28) }
    /// From one page to the next on the track.
    var step: CGFloat { page + Self.gap }
    var last: Int { count - 1 }
    /// The furthest the track goes: the last page alone, or the last two together.
    var lastOffset: Int { open ? last - 1 : last }

    /// The first page in view when `page` is chosen.
    func offset(for page: Int) -> Int { open ? (page == last ? last - 1 : 0) : page }

    /// The page chosen when the track rests at `offset`.
    func page(at offset: Int) -> Int { open ? (offset == lastOffset ? last : 0) : offset }

    /// The pages in view when the track rests at `offset`.
    func shown(at offset: Int) -> Set<Int> { open ? [offset, offset + 1] : [offset] }
}

/// The passport pages' labels: a pill slides to the chosen one; a dot marks a page with news not shown yet.
struct PassportPageLabels: View {
    let label: String
    let titles: [String]
    let dots: [Bool]
    let page: Int
    let onPage: (Int) -> Void
    @Environment(\.accessibilityReduceMotion) private var reduceMotion

    var body: some View {
        HStack(spacing: 0) {
            ForEach(titles.indices, id: \.self) { index in
                Button { onPage(index) } label: {
                    Text(titles[index])
                        .font(.footnote.weight(.semibold))
                        .foregroundStyle(index == page ? Brand.ink : Brand.secondaryText)
                        .lineLimit(1)
                        .minimumScaleFactor(0.7)
                        .padding(.horizontal, 8)
                        .frame(maxWidth: .infinity, minHeight: 38)
                        .overlay(alignment: .topTrailing) {
                            if dots.indices.contains(index), dots[index] {
                                PassportPageDot()
                                    .padding(.top, 9)
                                    .padding(.trailing, 12)
                                    .transition(.opacity.animation(.linear(duration: 0.15)))
                            }
                        }
                        .contentShape(Capsule())
                }
                .buttonStyle(.plain)
                .accessibilityAddTraits(index == page ? [.isButton, .isSelected] : .isButton)
            }
        }
        .background(alignment: .leading) {
            GeometryReader { proxy in
                let width = proxy.size.width / CGFloat(max(1, titles.count))
                Capsule()
                    .fill(Brand.yellowSoft)
                    .frame(width: width)
                    .offset(x: width * CGFloat(page))
                    .animation(reduceMotion ? nil : PassportMotion.move.animation(240), value: page)
            }
        }
        .overlay(Capsule().strokeBorder(Brand.separator, lineWidth: 1))
        .accessibilityElement(children: .contain)
        .accessibilityAddTraits(.isTabBar)
        .accessibilityLabel(label)
    }
}

/// A page label's dot: it pops in, a little after the page shows (FABLE.md 2.6), and leaves with a short fade.
private struct PassportPageDot: View {
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @State private var scale: Double = 0
    @State private var opacity: Double = 0

    var body: some View {
        Circle()
            .fill(Brand.warning)
            .frame(width: 7, height: 7)
            .scaleEffect(scale)
            .opacity(opacity)
            .accessibilityHidden(true)
            .onAppear {
                if reduceMotion {
                    scale = 1
                    withAnimation(.linear(duration: 0.15).delay(0.2)) { opacity = 1 }
                    return
                }
                withAnimation(.linear(duration: 0.12).delay(0.2)) { opacity = 1 }
                withAnimation(PassportMotion.move.animation(190, delay: 200)) { scale = 1.25 }
                withAnimation(PassportMotion.settle.animation(130, delay: 390)) { scale = 1 }
            }
    }
}

/// The passport's pages on one track, the neighbour's edge showing, each a sheet of paper with its number at the foot.
/// The track follows a finger, with resistance past the first and last page, and turns past a quarter of a page or a
/// quick flick; a label turns it too. The pages out of view stand as tall as the one in view and are left out of what
/// a screen reader and a tap reach.
struct PassportPageTrack<Content: View>: View {
    let layout: PassportPageLayout
    let page: Int
    let onPage: (Int) -> Void
    @ViewBuilder let content: (Int) -> Content

    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    /// Room at the foot of a page for its number, growing with the text.
    @ScaledMetric(relativeTo: .caption2) private var foot: CGFloat = 34
    /// The first page in view, which the track moves to; it follows `page` and a swipe.
    @State private var offset: Int?
    @State private var drag: CGFloat = 0
    @State private var heights: [Int: CGFloat] = [:]
    /// With Reduce Motion, the page cross-fades in place of travelling.
    @State private var fade: Double = 1

    private var resting: Int { offset ?? layout.offset(for: page) }

    var body: some View {
        let shown = layout.shown(at: resting)
        let height = shown.compactMap { heights[$0] }.max()
        HStack(alignment: .top, spacing: PassportPageLayout.gap) {
            ForEach(0..<layout.count, id: \.self) { index in
                sheet(index, height: shown.contains(index) ? nil : height)
                    .opacity(shown.contains(index) ? fade : 1)
                    .allowsHitTesting(shown.contains(index))
                    .accessibilityHidden(!shown.contains(index))
            }
        }
        .offset(x: -CGFloat(resting) * layout.step + drag)
        .frame(width: layout.width, alignment: .leading)
        .padding(.horizontal, PassportPageLayout.margin)
        .padding(.top, 2)
        .padding(.bottom, 14)
        .clipShape(Rectangle())
        .contentShape(Rectangle())
        .modifier(PassportPageSwipe(changed: follow, ended: release, cancelled: { withAnimation(settleBack(drag)) { drag = 0 } }))
        .onChange(of: page) { _, page in turn(to: layout.offset(for: page)) }
        .onChange(of: layout.open) { offset = nil; drag = 0 }
    }

    private func sheet(_ index: Int, height: CGFloat?) -> some View {
        content(index)
            .frame(maxWidth: .infinity, alignment: .topLeading)
            .padding(.top, 18)
            .padding(.horizontal, 14)
            .padding(.bottom, foot)
            .frame(width: layout.page)
            .frame(minHeight: 360, alignment: .top)
            .fixedSize(horizontal: false, vertical: true)
            .onGeometryChange(for: CGFloat.self) { $0.size.height } action: { heights[index] = $0 }
            .frame(height: height, alignment: .top)
            .overlay(alignment: .bottom) {
                Text(index + 1, format: .number)
                    .font(.caption2.monospacedDigit())
                    .foregroundStyle(Brand.secondaryText)
                    .padding(.bottom, 12)
                    .accessibilityHidden(true)
            }
            .clipShape(RoundedRectangle(cornerRadius: 20, style: .continuous))
            .experimentalSurface(cornerRadius: 20)
    }

    /// The track under the finger, held back to 0.3 past the first and last page. With Reduce Motion it stays put
    /// until the finger lifts.
    private func follow(_ dx: CGFloat) {
        guard !reduceMotion else { return }
        let resisted = (resting == 0 && dx > 0) || (resting == layout.lastOffset && dx < 0)
        var transaction = Transaction(animation: nil)
        transaction.disablesAnimations = true
        withTransaction(transaction) { drag = resisted ? dx * 0.3 : dx }
    }

    /// The finger lifts: past a quarter of a page or faster than 400 points a second, the page turns.
    private func release(_ dx: CGFloat, _ velocity: CGFloat) {
        let turns = abs(dx) > layout.page * 0.25 || (abs(velocity) > 400 && velocity * dx > 0)
        let target = turns ? min(layout.lastOffset, max(0, resting + (dx < 0 ? 1 : -1))) : resting
        if reduceMotion {
            drag = 0
            if target != resting { turn(to: target) }
        } else {
            // What is left of the way, at the page's pace: 420 ms for a whole page, 180 at least.
            let left = target == resting ? abs(drag) : max(0, layout.step - abs(drag))
            let duration = max(180, PassportMotion.flipDuration * Double(left / max(1, layout.step)))
            withAnimation(PassportMotion.settle.animation(duration)) {
                offset = target
                drag = 0
            }
        }
        if target != layout.offset(for: page) { onPage(layout.page(at: target)) }
    }

    private func settleBack(_ from: CGFloat) -> Animation? {
        reduceMotion ? nil : PassportMotion.settle.animation(max(180, PassportMotion.flipDuration * Double(abs(from) / max(1, layout.step))))
    }

    /// Turns to `target`: the track flips there, or the page cross-fades with Reduce Motion. A turn under way retargets
    /// from where the track is.
    private func turn(to target: Int) {
        guard target != resting || drag != 0 else { return }
        if reduceMotion {
            var transaction = Transaction(animation: nil)
            transaction.disablesAnimations = true
            withTransaction(transaction) {
                offset = target
                drag = 0
                fade = 0
            }
            withAnimation(.linear(duration: 0.2)) { fade = 1 }
        } else {
            withAnimation(PassportMotion.flip.animation(PassportMotion.flipDuration)) {
                offset = target
                drag = 0
            }
        }
    }
}

/// A horizontal swipe over the pages. On iOS it waits to see the drag's direction, so the passport still scrolls under
/// a vertical one, and stops that scroll once the drag turns out sideways; a Mac takes a drag of the pointer.
private struct PassportPageSwipe: ViewModifier {
    let changed: (CGFloat) -> Void
    let ended: (CGFloat, CGFloat) -> Void
    let cancelled: () -> Void

    func body(content: Content) -> some View {
        #if os(iOS)
        content.gesture(PassportPagePan(changed: changed, ended: ended, cancelled: cancelled))
        #else
        content.gesture(DragGesture(minimumDistance: 8)
            .onChanged { value in
                if abs(value.translation.width) > abs(value.translation.height) { changed(value.translation.width) }
            }
            .onEnded { value in
                if abs(value.translation.width) > abs(value.translation.height) {
                    ended(value.translation.width, value.velocity.width)
                } else {
                    cancelled()
                }
            })
        #endif
    }
}

#if os(iOS)
/// The archive swipe's pan, which declines a vertical drag before it begins, turning pages.
private struct PassportPagePan: UIGestureRecognizerRepresentable {
    let changed: (CGFloat) -> Void
    let ended: (CGFloat, CGFloat) -> Void
    let cancelled: () -> Void

    func makeCoordinator(converter: CoordinateSpaceConverter) -> ArchivePanGestureDelegate {
        ArchivePanGestureDelegate()
    }

    func makeUIGestureRecognizer(context: Context) -> ArchivePanGestureRecognizer {
        let recognizer = ArchivePanGestureRecognizer()
        recognizer.maximumNumberOfTouches = 1
        recognizer.delegate = context.coordinator
        return recognizer
    }

    func handleUIGestureRecognizerAction(_ recognizer: ArchivePanGestureRecognizer, context: Context) {
        let translation = context.converter.localTranslation ?? recognizer.translation(in: recognizer.view)
        switch recognizer.state {
        case .began, .changed:
            changed(translation.x)
        case .ended:
            changed(translation.x)
            ended(translation.x, (context.converter.localVelocity ?? recognizer.velocity(in: recognizer.view)).x)
        case .cancelled, .failed:
            cancelled()
        default:
            break
        }
    }
}
#endif
