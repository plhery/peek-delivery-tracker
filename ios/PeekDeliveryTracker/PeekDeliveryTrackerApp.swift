import SwiftUI

@main
struct PeekDeliveryTrackerApp: App {
    #if os(macOS)
    @NSApplicationDelegateAdaptor(AppDelegate.self) private var appDelegate
    #else
    @UIApplicationDelegateAdaptor(AppDelegate.self) private var appDelegate
    #endif
    @StateObject private var session: SessionStore
    @StateObject private var parcels: ParcelStore
    @StateObject private var localizer: Localizer
    @StateObject private var invitation = FriendInvitationStore()
    @StateObject private var friendsActivity = FriendsActivityStore()
    @StateObject private var links: ParcelLinkStore
    @AppStorage(AppAppearance.storageKey) private var appearance = AppAppearance.defaultValue
    #if os(macOS)
    @AppStorage(MenuBarExtraSettings.shownKey) private var menuBarShown = true
    #endif

    init() {
        let localizer = Localizer()
        let session = SessionStore(configuration: .current)
        let parcels = ParcelStore(
            configuration: .current,
            session: session,
            localizer: localizer
        )
        _localizer = StateObject(wrappedValue: localizer)
        _session = StateObject(wrappedValue: session)
        _parcels = StateObject(wrappedValue: parcels)
        #if os(macOS)
        parcels.keepMenuBarCurrent()
        #endif
        #if DEBUG
        _links = StateObject(wrappedValue: ParcelLinkStore.debugPreview() ?? ParcelLinkStore())
        #else
        _links = StateObject(wrappedValue: ParcelLinkStore())
        #endif
        // Build lookup tables off the main thread before the first card or parcel needs them.
        Task.detached(priority: .utility) {
            _ = CarrierBrandAssets.shared
            TrackingLocation.prepare()
        }
    }

    var body: some Scene {
        #if os(macOS)
        // One window: links, notifications and the shared draft all go to the one set of deliveries. It is never
        // narrower than the list beside a parcel.
        Window("Peek", id: MacWindow.id) {
            stores(RootView().background { AnalyticsLifecycleObserver() })
                .frame(minWidth: MacWindow.minimumWidth, minHeight: MacWindow.minimumHeight)
        }
        .defaultSize(width: 1180, height: 820)
        .windowResizability(.contentMinSize)
        .commands { PeekCommands(localizer: localizer) }

        Settings {
            stores(SettingsWindow())
        }
        .windowResizability(.contentSize)

        MenuBarExtra(isInserted: menuBarInserted) {
            stores(MenuBarPanel())
        } label: {
            MenuBarLabel()
        }
        .menuBarExtraStyle(.window)
        #else
        WindowGroup {
            stores(RootView().background { AnalyticsLifecycleObserver() })
        }
        .commands { PeekCommands(localizer: localizer) }
        #endif
    }

    #if os(macOS)
    /// Whether Peek is in the menu bar, which taking it out of there turns off too. SwiftUI sets it again as the
    /// scene updates, so only a change is stored: storing the same value would update the scene again, without end.
    private var menuBarInserted: Binding<Bool> {
        Binding(get: { menuBarShown }, set: { if $0 != menuBarShown { menuBarShown = $0 } })
    }
    #endif

    private func stores(_ content: some View) -> some View {
        content
            .environmentObject(session)
            .environmentObject(parcels)
            .environmentObject(localizer)
            .environmentObject(invitation)
            .environmentObject(friendsActivity)
            .environmentObject(links)
            .environment(\.locale, localizer.language.locale)
            .background { AppWindowAppearance(appearance: appearance) }
            .tint(Brand.accent)
    }
}

struct RootView: View {
    @EnvironmentObject private var session: SessionStore
    @EnvironmentObject private var parcels: ParcelStore
    @EnvironmentObject private var localizer: Localizer
    @EnvironmentObject private var invitation: FriendInvitationStore
    @EnvironmentObject private var friendsActivity: FriendsActivityStore
    @EnvironmentObject private var links: ParcelLinkStore
    @Environment(\.scenePhase) private var scenePhase
    /// Kept with the window, so the tab is still there after the system closes the app and opens it again.
    @SceneStorage("tab") private var selectedTab = 0
    @State private var deliveries = LinkDeliveries()
    private let carrierCatalog = CarrierCatalog.shared

    private var sessionContent: some View {
        Group {
            if invitation.isPresenting, sessionIdentity != "loading" {
                ArrivalView().id(invitation.presentationID)
            } else {
            switch session.state {
            case .loading:
                LaunchView()
            case .unconfigured, .signedOut:
                ArrivalView()
            // Nobody is signed in: the deliveries this iPhone follows itself, under the first screen until there is one.
            case .welcome, .demo, .signedIn:
                ParcelListView(selection: $selectedTab)
            }
            }
        }
        // Once a launch: a window opened again finds the session as it was left.
        .task { if case .loading = session.state { await session.bootstrap() } }
        .onChange(of: sessionIdentity) { _, _ in friendsActivity.clear() }
        .task(id: sessionIdentity + (scenePhase == .active ? "-active" : "-hidden")) {
            guard session.user != nil else { friendsActivity.clear(); return }
            guard scenePhase == .active else { friendsActivity.hidePrivateContent(); return }
            if let friendID = AppDelegate.consumePendingFriendID() { friendsActivity.reveal(friendID) }
            while !Task.isCancelled {
                await friendsActivity.refresh(session: session)
                do { try await Task.sleep(for: .seconds(30)) } catch { return }
            }
        }
    }

    private var lifecycleContent: some View {
        sessionContent
        .overlay(alignment: .top) {
            if !invitation.isPresenting, session.user != nil, let update = friendsActivity.updates.first {
                FriendAcceptedNotice(update: update) {
                    friendsActivity.reveal(update.friendID)
                    Task { await friendsActivity.acknowledge(update.friendID, session: session) }
                } dismiss: { Task { await friendsActivity.acknowledge(update.friendID, session: session) } }
                .padding(.horizontal, 16).padding(.top, 6)
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .task {
            if await carrierCatalog.refresh(from: AppConfiguration.current.apiBaseURL) == .updated {
                parcels.refreshDeliverySurfaces()
            }
        }
        .task(id: sessionIdentity + (invitation.isPresenting ? "-invitation" : "")) {
            guard session.isAuthenticated || session.isGuest else {
                switch session.state {
                case .loading: break
                default: parcels.clearDeliverySurfaces()
                }
                return
            }
            await parcels.start()
        }
        .onChange(of: scenePhase) { _, phase in
            parcels.setActive(phase == .active)
            if phase != .active { invitation.clearPreview() }
            guard phase == .active else { return }
            Task {
                if await carrierCatalog.refresh(
                    from: AppConfiguration.current.apiBaseURL
                ) == .updated {
                    parcels.refreshDeliverySurfaces()
                }
            }
        }
    }

    private var routedContent: some View {
        lifecycleContent
        .onOpenURL(perform: open)
        .onContinueUserActivity(NSUserActivityTypeBrowsingWeb) { if let url = $0.webpageURL { open(url) } }
        .onChange(of: invitation.completed) { _, _ in selectedTab = 2 }
        .onChange(of: friendsActivity.presentationID) { _, _ in
            if friendsActivity.focusID != nil { selectedTab = 2 }
        }
        .onReceive(NotificationCenter.default.publisher(for: .didOpenFriendNotification)) { notification in
            guard session.user != nil, let friendID = notification.object as? UUID else { return }
            _ = AppDelegate.consumePendingFriendID()
            friendsActivity.reveal(friendID)
        }
        .onReceive(NotificationCenter.default.publisher(for: .didReceiveFriendActivity)) { _ in
            guard session.user != nil, scenePhase == .active else { return }
            Task { await friendsActivity.refresh(session: session) }
        }
        .onReceive(NotificationCenter.default.publisher(for: .didOpenLinkNotification)) { _ in openLinkNotification() }
        .onChange(of: sessionIdentity, initial: true) { _, _ in openLinkNotification() }
    }

    /// A notification about a parcel followed through its link: the parcel opens among the ones
    /// this iPhone follows itself, or the link opens when this iPhone no longer has it there.
    private func openLinkNotification() {
        guard sessionIdentity != "loading", let linkID = AppDelegate.consumePendingLinkID() else { return }
        if session.user == nil, let parcelID = parcels.followedHere(linkID: linkID) {
            parcels.showDeviceParcels()
            AppDelegate.openParcel(parcelID)
        } else {
            DeliveryAnalytics.shared.action("notification-open")
            links.open(ParcelLinkRoute(id: linkID))
        }
    }

    /// A parcel link opens as a sheet over whatever is on screen, signed in or not.
    private var linkedContent: some View {
        routedContent
        .background {
            FrontmostSheet(isPresented: links.route != nil, onClose: links.close, onDismiss: links.dismissed) {
                ParcelLinkSheet()
                    .environmentObject(links)
                    .environmentObject(session)
                    .environmentObject(parcels)
                    .environmentObject(localizer)
            }
            .accessibilityHidden(true)
        }
        // A link opened before signing in comes back once someone is signed in.
        .onChange(of: session.user?.id, initial: true) { _, user in
            if user != nil { links.resume() }
        }
        .onChange(of: links.arrival) { _, arrival in
            if arrival != nil { selectedTab = 0 }
        }
    }

    private func open(_ url: URL) {
        guard !deliveries.isRepeat(url) else { return }
        if let link = ParcelLinkRoute(url: url) { links.open(link) }
        else if case .friend(let friendID) = NativeRoute(url: url), session.user != nil { friendsActivity.reveal(friendID) }
        else { invitation.open(url) }
    }

    var body: some View {
        linkedContent
        .onReceive(NotificationCenter.default.publisher(for: .didReceiveAPNSToken)) { notification in
            guard let token = notification.object as? String else { return }
            Task {
                await parcels.forwardNativePushToken(token, language: localizer.language)
                await parcels.registerDeviceAlerts(token: token, language: localizer.language)
            }
        }
        .task {
            let config = AppConfiguration.current
            if config.mode == .api { await NativeVerification.shared.prepare(baseURL: config.apiBaseURL) }
        }
        .task(id: "\(sessionIdentity)-\(localizer.language.rawValue)") {
            await session.saveEmailLanguage(localizer.language)
        }
        .onChange(of: localizer.language) { _, language in
            parcels.demoLanguageChanged()
            parcels.refreshDeliverySurfaces()
            guard let token = AppDelegate.currentDeviceToken else { return }
            Task {
                await parcels.forwardNativePushToken(token, language: language)
                await parcels.registerDeviceAlerts(token: token, language: language)
            }
        }
    }

    private var sessionIdentity: String {
        switch session.state {
        case .loading: "loading"
        case .welcome: "welcome"
        case .demo: "demo"
        case .unconfigured: "unconfigured"
        case .signedOut: "signed-out"
        case .signedIn(let user): user.id.uuidString
        }
    }
}

/// Restoring the saved session takes a frame or two. Match the launch screen and
/// show progress only if it takes longer, instead of flashing a spinner.
private struct LaunchView: View {
    @EnvironmentObject private var localizer: Localizer
    @State private var slow = false

    var body: some View {
        ZStack {
            Brand.background.ignoresSafeArea()
            if slow {
                VStack(spacing: 20) {
                    PeekMark(size: 78)
                    ProgressView()
                        .controlSize(.large)
                        .tint(Brand.ink)
                    Text(localizer.text("auth.loading"))
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                }
                .transition(.opacity)
            }
        }
        .task {
            do { try await Task.sleep(for: .milliseconds(600)) } catch { return }
            withAnimation(.easeOut(duration: 0.2)) { slow = true }
        }
    }
}
