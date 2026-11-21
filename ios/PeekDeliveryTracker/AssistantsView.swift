import SwiftUI

/// The assistants' page in Settings, as the website's: the address to give an assistant, how to add Peek to each one,
/// what to ask one, and the ones connected, each with Disconnect. Its first lines lead to the public page on
/// connecting an assistant, which says the same and more.
struct AssistantsPage: View {
    @ObservedObject var model: AssistantsModel

    @EnvironmentObject private var session: SessionStore
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @State private var copied = false
    /// The assistant whose steps are open.
    @State private var open: String?
    @State private var showingGuide = false
    private let setups = AssistantSetup.bundled

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 24) {
                VStack(alignment: .leading, spacing: 10) {
                    SettingsBadge(text: localizer.text("assistants.experimental"))
                    Text(localizer.text("assistants.intro"))
                        .font(.subheadline).foregroundStyle(Brand.secondaryText)
                        .fixedSize(horizontal: false, vertical: true)
                    Button(localizer.text("assistants.page.link")) { showingGuide = true }
                        .font(.subheadline.weight(.medium)).foregroundStyle(Brand.ink)
                        .accessibilityIdentifier("assistants.guide")
                }
                if let failure = model.failure {
                    Text(localizer.errorMessage(failure, fallback: "assistants.failed"))
                        .font(.subheadline).foregroundStyle(Brand.warning)
                        .fixedSize(horizontal: false, vertical: true)
                        .accessibilityIdentifier("assistants.error")
                }
                if let offer = model.offer {
                    address(offer.mcpURL)
                    steps(offer.mcpURL)
                    uses
                    connected
                }
            }
            .padding(24)
            .frame(maxWidth: 520)
            .frame(maxWidth: .infinity)
        }
        .sheetGround()
        .navigationTitle(localizer.text("assistants.row"))
        #if os(iOS)
        .navigationBarTitleDisplayMode(.inline)
        .toolbar { if model.working { ToolbarItem(placement: .topBarTrailing) { ProgressView() } } }
        .sheet(isPresented: $showingGuide) { SafariPage(url: guideURL).ignoresSafeArea() }
        #else
        .sheet(isPresented: $showingGuide) { SafariPage(url: guideURL) }
        #endif
        .interactiveDismissDisabled(model.working)
        .onChange(of: model.failure != nil) { _, failed in
            if failed, let failure = model.failure {
                AccessibilityNotification.Announcement(localizer.errorMessage(failure, fallback: "assistants.failed")).post()
            }
        }
    }

    /// The public page on connecting an assistant, in the reader's language. English has no prefix.
    private var guideURL: URL {
        let language = localizer.language.rawValue
        return session.configuration.apiBaseURL.appending(path: language == "en" ? "assistants" : "\(language)/assistants")
    }

    private func address(_ address: String) -> some View {
        SettingsGroup(title: localizer.text("assistants.address")) {
            Text(verbatim: address)
                .font(.body.weight(.medium)).foregroundStyle(Brand.ink)
                .textSelection(.enabled)
                .fixedSize(horizontal: false, vertical: true)
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(15)
                .accessibilityIdentifier("assistants.address")
            settingsDivider
            Button {
                Clipboard.string = address
                Haptics.success()
                AccessibilityNotification.Announcement(localizer.text("inbox.copied")).post()
                copied = true
            } label: {
                SettingsRow(title: localizer.text(copied ? "inbox.copied" : "inbox.copy"), symbol: copied ? "checkmark" : "doc.on.doc")
            }
            .buttonStyle(.plain)
            .accessibilityIdentifier("assistants.copy")
        }
    }

    /// Each assistant's name with the one thing to do under it, its steps folded beneath, then a line for any other
    /// assistant.
    private func steps(_ address: String) -> some View {
        VStack(alignment: .leading, spacing: 9) {
            SettingsGroup(title: localizer.text("assistants.setup")) {
                ForEach(Array(setups.enumerated()), id: \.element.id) { index, setup in
                    if index > 0 { settingsDivider }
                    setupRow(setup, address: address)
                }
            }
            note(localizer.text("assistants.how"))
        }
    }

    @ViewBuilder private func setupRow(_ setup: AssistantSetup, address: String) -> some View {
        let shown = open == setup.name
        Button {
            withAnimation(reduceMotion ? nil : .easeOut(duration: 0.2)) { open = shown ? nil : setup.name }
        } label: {
            HStack(spacing: 11) {
                VStack(alignment: .leading, spacing: 3) {
                    Text(verbatim: setup.name).font(.subheadline).foregroundStyle(Brand.ink)
                    if let lead = setup.lead {
                        Text(lead.text(localizer, address: address)).font(.caption).foregroundStyle(Brand.secondaryText)
                            .multilineTextAlignment(.leading)
                            .fixedSize(horizontal: false, vertical: true)
                    }
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                Image(systemName: "chevron.right").font(.system(size: 10, weight: .light))
                    .foregroundStyle(Brand.secondaryText)
                    .rotationEffect(.degrees(shown ? 90 : 0))
                    .accessibilityHidden(true)
            }
            .padding(15)
            .frame(minHeight: 54)
            .contentShape(Rectangle())
        }
        .buttonStyle(.plain)
        .accessibilityAddTraits(shown ? .isSelected : [])
        .accessibilityIdentifier("assistants.setup.\(setup.name)")
        if shown {
            VStack(alignment: .leading, spacing: 12) {
                line(setup.who, address: address, secondary: true)
                ForEach(Array(setup.stepsWithSignIn(localizer).enumerated()), id: \.offset) { number, step in
                    HStack(alignment: .firstTextBaseline, spacing: 8) {
                        Text(verbatim: "\(number + 1).").font(.subheadline.weight(.semibold)).foregroundStyle(Brand.secondaryText)
                            .accessibilityHidden(true)
                        line(step, address: address)
                    }
                }
                if let fallback = setup.fallback { line(fallback, address: address, secondary: true) }
            }
            .padding(.horizontal, 15).padding(.bottom, 15)
            .frame(maxWidth: .infinity, alignment: .leading)
        }
    }

    private func line(_ line: AssistantSetup.Line, address: String, secondary: Bool = false) -> some View {
        Text(line.text(localizer, address: address))
            .font(secondary ? .caption : .subheadline)
            .foregroundStyle(secondary ? Brand.secondaryText : Brand.ink)
            .tint(Brand.ink)
            .textSelection(.enabled)
            .fixedSize(horizontal: false, vertical: true)
            .frame(maxWidth: .infinity, alignment: .leading)
    }

    /// What to ask a connected assistant: first, to add the tracking numbers in the reader's emails.
    private var uses: some View {
        SettingsGroup(title: localizer.text("assistants.uses")) {
            VStack(alignment: .leading, spacing: 10) {
                (Text(localizer.text("assistants.uses.email")).bold() + Text(verbatim: " ") + Text(localizer.text("assistants.uses.emailNote")))
                ForEach(["assistants.uses.week", "assistants.uses.where", "assistants.uses.archive"], id: \.self) { key in
                    Text(localizer.text(key))
                }
            }
            .font(.subheadline).foregroundStyle(Brand.ink)
            .fixedSize(horizontal: false, vertical: true)
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(15)
        }
    }

    private var connected: some View {
        SettingsGroup(title: localizer.text("assistants.connected")) {
            let assistants = model.assistants
            if assistants.isEmpty {
                Text(localizer.text("assistants.none"))
                    .font(.subheadline).foregroundStyle(Brand.secondaryText)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(15)
                    .accessibilityIdentifier("assistants.none")
            }
            ForEach(Array(assistants.enumerated()), id: \.element.id) { index, connection in
                if index > 0 { settingsDivider }
                AssistantRow(connection: connection, working: model.working) {
                    Task { await model.revoke(connection.id) }
                }
            }
        }
    }

    private func note(_ text: String) -> some View {
        Text(text).font(.caption).foregroundStyle(Brand.secondaryText)
            .fixedSize(horizontal: false, vertical: true)
            .padding(.horizontal, 2)
    }
}

/// A connected assistant: its name, where it sends the reader back, what it may do and when it was last used, and
/// Disconnect.
private struct AssistantRow: View {
    let connection: AssistantConnection
    let working: Bool
    let onRevoke: () -> Void

    @EnvironmentObject private var localizer: Localizer

    var body: some View {
        HStack(spacing: 11) {
            VStack(alignment: .leading, spacing: 4) {
                Text(verbatim: connection.name).font(.subheadline.weight(.semibold)).foregroundStyle(Brand.ink)
                Text(detail).font(.caption).foregroundStyle(Brand.secondaryText)
                    .fixedSize(horizontal: false, vertical: true)
            }
            Spacer(minLength: 4)
            Button(localizer.text("assistants.disconnect"), role: .destructive, action: onRevoke)
                .font(.subheadline.weight(.medium))
                .buttonStyle(.borderless)
                .disabled(working)
                .accessibilityLabel(localizer.text("assistants.revokeNamed", ["name": connection.name]))
                .accessibilityIdentifier("assistants.disconnect")
        }
        .padding(15)
        .frame(minHeight: 54, alignment: .leading)
        .accessibilityElement(children: .contain)
    }

    private var detail: String {
        let used = connection.lastUsedAt.map { localizer.text("assistants.lastUsed", ["time": localizer.relativeTime(from: $0)]) }
            ?? localizer.text("assistants.neverUsed")
        return [connection.redirectHost, localizer.text(connection.scopeKey), used].compactMap { $0 }.joined(separator: " · ")
    }
}
