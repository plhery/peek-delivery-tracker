#if os(iOS)
import ActivityKit
#endif
import SwiftUI
import WidgetKit

// A Mac has no Live Activities.
#if os(iOS)
private enum WidgetPalette {
    static let accentBright = Color(red: 1.0, green: 0.84, blue: 0.34)
    static let ink = Color(red: 0.10, green: 0.10, blue: 0.09)
}

private struct DeliveryLiveActivityView: View {
    let state: DeliveryActivityAttributes.ContentState

    private var secondaryLine: String {
        state.parcel.detail == state.parcel.status
            ? state.parcel.carrier
            : "\(state.parcel.carrier) · \(state.parcel.detail)"
    }

    var body: some View {
        HStack(spacing: 13) {
            Image(systemName: state.parcel.phase.symbol)
                .font(.title3.weight(.bold))
                .frame(width: 42, height: 42)
                .background(state.parcel.phase.tint, in: Circle())
                .foregroundStyle(WidgetPalette.ink)
            VStack(alignment: .leading, spacing: 3) {
                Text(state.parcel.status)
                    .font(.caption2.weight(.heavy))
                    .textCase(.uppercase)
                    .foregroundStyle(.secondary)
                Text(state.parcel.label)
                    .font(.headline.weight(.bold))
                    .lineLimit(1)
                Text(secondaryLine)
                    .font(.caption.weight(.semibold))
                    .foregroundStyle(.secondary)
                    .lineLimit(1)
            }
            Spacer(minLength: 4)
            Image(systemName: "chevron.right")
                .font(.caption.weight(.bold))
                .foregroundStyle(.secondary)
        }
        .padding()
        .activityBackgroundTint(Color(red: 0.12, green: 0.12, blue: 0.11))
        .activitySystemActionForegroundColor(WidgetPalette.accentBright)
        .widgetURL(state.parcel.deepLink)
        .environment(\.locale, ExtensionLocalizer.locale(for: state.languageCode))
    }
}

struct DeliveryLiveActivityWidget: Widget {
    var body: some WidgetConfiguration {
        ActivityConfiguration(for: DeliveryActivityAttributes.self) { context in
            DeliveryLiveActivityView(state: context.state)
        } dynamicIsland: { context in
            let parcel = context.state.parcel
            return DynamicIsland {
                DynamicIslandExpandedRegion(.leading) {
                    Label(
                        parcel.status,
                        systemImage: parcel.phase.symbol
                    )
                    .font(.caption.weight(.bold))
                    .foregroundStyle(parcel.phase.tint)
                }
                DynamicIslandExpandedRegion(.trailing) {
                    Text(parcel.supplementalDetail)
                        .font(.caption.weight(.bold))
                        .lineLimit(1)
                }
                DynamicIslandExpandedRegion(.bottom) {
                    VStack(alignment: .leading, spacing: 3) {
                        Text(parcel.label)
                            .font(.headline.weight(.bold))
                            .lineLimit(1)
                        Text(parcel.detail == parcel.status
                            ? parcel.carrier
                            : "\(parcel.carrier) · \(parcel.detail)")
                            .font(.caption)
                            .foregroundStyle(.secondary)
                            .lineLimit(1)
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                }
            } compactLeading: {
                Image(systemName: parcel.phase.symbol)
                    .foregroundStyle(parcel.phase.tint)
            } compactTrailing: {
                Text(parcel.supplementalDetail)
                    .font(.caption2.weight(.bold))
                    .lineLimit(1)
                    .minimumScaleFactor(0.7)
                    .foregroundStyle(parcel.phase.tint)
            } minimal: {
                Image(systemName: parcel.phase.symbol)
                    .foregroundStyle(parcel.phase.tint)
            }
            .widgetURL(parcel.deepLink)
            .keylineTint(parcel.phase.tint)
        }
    }
}

private extension DeliveryActivityPhase {
    var symbol: String {
        switch self {
        case .outForDelivery: "bicycle"
        case .delivered: "checkmark.circle.fill"
        case .failedAttempt: "exclamationmark.triangle.fill"
        case .exception: "exclamationmark.circle.fill"
        case .readyForPickup: "storefront.fill"
        case .returned: "arrow.uturn.backward.circle.fill"
        case .ended: "shippingbox.fill"
        }
    }

    var tint: Color {
        switch self {
        case .delivered: .green
        case .failedAttempt, .exception, .readyForPickup, .returned: .orange
        case .outForDelivery, .ended: WidgetPalette.accentBright
        }
    }
}

private extension DeliveryActivityParcel {
    var supplementalDetail: String {
        detail == status ? carrier : detail
    }
}
#endif

@main
struct DeliveryTrackerWidgetBundle: WidgetBundle {
    var body: some Widget {
        ParcelListWidget()
        #if os(iOS)
        DeliveryLiveActivityWidget()
        #endif
    }
}
