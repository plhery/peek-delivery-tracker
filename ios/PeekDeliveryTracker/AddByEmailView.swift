import SwiftUI

/// The forwarding address's page in Settings. Forwarding one email by hand is all it asks: the address to copy, then
/// what the last emails did, and whether each parcel an email adds is notified (on unless switched off). Having the
/// mail service forward by itself, with its steps, stays folded away until wanted, or until Gmail's confirmation is
/// waiting.
struct AddByEmailPage: View {
    @ObservedObject var model: AddByEmailModel
    /// The account's address, which tells the mail service the steps start on.
    let email: String?
    /// The site the guide to adding parcels by email is read on.
    let linkBase: URL

    @EnvironmentObject private var localizer: Localizer
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @State private var copied = false
    @State private var confirmingRenew = false
    @State private var forwarding = false
    /// The notification switch's position while it is saved: nil once the server has answered.
    @State private var notifyChoice: Bool?

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 24) {
                VStack(alignment: .leading, spacing: 10) {
                    SettingsBadge(text: localizer.text("inbox.experimental"))
                    Text(localizer.text("inbox.intro"))
                        .font(.subheadline).foregroundStyle(Brand.secondaryText)
                        .fixedSize(horizontal: false, vertical: true)
                    OutLink(url: CarrierCatalog.guideURL("add-parcels-by-email", base: linkBase, language: localizer.language), key: "inbox.guide")
                        .accessibilityIdentifier("addByEmail.guide")
                }
                if let failure = model.failure {
                    Text(localizer.errorMessage(failure, fallback: "inbox.failed"))
                        .font(.subheadline).foregroundStyle(Brand.warning)
                        .fixedSize(horizontal: false, vertical: true)
                        .accessibilityIdentifier("addByEmail.error")
                }
                if let offer = model.offer {
                    if let inbox = offer.inbox {
                        address(inbox.address)
                        recent(inbox, offer: offer)
                        automatic(inbox)
                        manage(inbox)
                    } else {
                        Button { Task { await model.turnOn() } } label: {
                            FirstParcelPrimaryLabel(title: localizer.text("inbox.start"), symbol: "envelope")
                        }
                        .buttonStyle(TactileButtonStyle())
                        .disabled(model.working)
                        .accessibilityIdentifier("addByEmail.start")
                    }
                }
            }
            .padding(24)
            .frame(maxWidth: 520)
            .frame(maxWidth: .infinity)
        }
        .sheetGround()
        .navigationTitle(localizer.text("inbox.row"))
        .navigationBarTitleDisplayMode(.inline)
        .toolbar { if model.working { ToolbarItem(placement: .topBarTrailing) { ProgressView() } } }
        .interactiveDismissDisabled(model.working)
        // Gmail's confirmation waits in its steps: they open to it, here or on coming back from Gmail.
        .onChange(of: model.offer?.inbox?.confirmation != nil, initial: true) { _, waiting in
            if waiting { forwarding = true }
        }
        .onChange(of: model.offer?.inbox?.address) { copied = false; confirmingRenew = false }
        .onChange(of: model.failure != nil) { _, failed in
            if failed, let failure = model.failure {
                AccessibilityNotification.Announcement(localizer.errorMessage(failure, fallback: "inbox.failed")).post()
            }
        }
    }

    private func address(_ address: String) -> some View {
        SettingsGroup(title: localizer.text("inbox.address")) {
            Text(verbatim: address)
                .font(.body.weight(.medium)).foregroundStyle(Brand.ink)
                .textSelection(.enabled)
                .fixedSize(horizontal: false, vertical: true)
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(15)
                .accessibilityIdentifier("addByEmail.address")
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
            .accessibilityIdentifier("addByEmail.copy")
            settingsDivider
            ShareLink(item: address) {
                SettingsRow(title: localizer.text("link.share"), symbol: "square.and.arrow.up")
            }
            .buttonStyle(.plain)
            .accessibilityIdentifier("addByEmail.share")
        }
    }

    /// What the last emails did, with what a few of them call for.
    @ViewBuilder private func recent(_ inbox: AddByEmail, offer: AddByEmailResponse) -> some View {
        let emails = inbox.recentEmails
        if !emails.isEmpty {
            VStack(alignment: .leading, spacing: 9) {
                SettingsGroup(title: localizer.text("inbox.recent")) {
                    ForEach(Array(emails.enumerated()), id: \.offset) { index, entry in
                        if index > 0 { settingsDivider }
                        AddByEmailRow(entry: entry)
                    }
                }
                if emails.contains(where: \.heldNoNumberUnexplained) { note(localizer.text("inbox.noNumber")) }
                if emails.contains(where: \.heldNumberInAmazonAccount) {
                    OutLink(url: CarrierCatalog.amazonGuideURL(base: linkBase, language: localizer.language), key: "add.amazonGuide")
                        .accessibilityIdentifier("addByEmail.amazonGuide")
                }
                if offer.showsUsage {
                    note(localizer.text("inbox.usage", ["used": inbox.usedToday, "limit": offer.dailyLimit]))
                }
            }
        }
    }

    private func automatic(_ inbox: AddByEmail) -> some View {
        SettingsGroup {
            Button {
                withAnimation(reduceMotion ? nil : .easeOut(duration: 0.2)) { forwarding.toggle() }
            } label: {
                HStack(spacing: 11) {
                    SettingsRow(title: localizer.text("inbox.auto.title"), symbol: "gearshape", padded: false)
                    Image(systemName: "chevron.right").font(.system(size: 10, weight: .light))
                        .foregroundStyle(Brand.secondaryText)
                        .rotationEffect(.degrees(forwarding ? 90 : 0))
                        .accessibilityHidden(true)
                }
                .padding(15)
                .frame(minHeight: 54)
                .contentShape(Rectangle())
            }
            .buttonStyle(.plain)
            .accessibilityIdentifier("addByEmail.automatic")
            if forwarding {
                settingsDivider
                ForwardingSteps(email: email, confirmation: inbox.confirmation)
                    .padding(15)
            }
        }
    }

    /// A new address, the notification switch on one line, then turning it off.
    private func manage(_ inbox: AddByEmail) -> some View {
        SettingsGroup {
            if confirmingRenew {
                VStack(alignment: .leading, spacing: 12) {
                    Text(localizer.text("inbox.renewNote")).font(.subheadline).foregroundStyle(Brand.ink)
                        .fixedSize(horizontal: false, vertical: true)
                    ViewThatFits(in: .horizontal) {
                        HStack(spacing: 8) { renewButtons }
                        VStack(spacing: 8) { renewButtons }
                    }
                }
                .padding(15)
            } else {
                Button { confirmingRenew = true } label: {
                    SettingsRow(title: localizer.text("inbox.renew"), symbol: "arrow.clockwise")
                }
                .accessibilityIdentifier("addByEmail.renew")
            }
            settingsDivider
            notification(inbox)
            settingsDivider
            Button { Task { await model.turnOff() } } label: {
                SettingsRow(title: localizer.text("inbox.off"), symbol: "trash")
            }
            .accessibilityIdentifier("addByEmail.off")
        }
        .buttonStyle(.plain)
        .disabled(model.working)
    }

    /// The switch shows the choice while it is saved, then the server's answer; a failure puts it back.
    private func notification(_ inbox: AddByEmail) -> some View {
        Toggle(isOn: Binding(get: { notifyChoice ?? inbox.notifies }, set: { on in
            guard notifyChoice == nil else { return }
            notifyChoice = on
            Task {
                await model.setNotify(on)
                notifyChoice = nil
            }
        })) {
            SettingsRow(title: localizer.text("inbox.notify"), symbol: "bell", padded: false)
        }
        .rowToggle()
        .padding(15)
        .tint(settingsGreen)
        .disabled(model.working || notifyChoice != nil)
        .accessibilityIdentifier("addByEmail.notify")
    }

    @ViewBuilder private var renewButtons: some View {
        Button { confirmingRenew = false } label: {
            Text(localizer.text("common.cancel"))
                .font(.subheadline.weight(.semibold)).foregroundStyle(Brand.ink)
                .frame(maxWidth: .infinity, minHeight: 44)
                .background(Brand.sheetButton, in: RoundedRectangle(cornerRadius: 12, style: .continuous))
        }
        .buttonStyle(TactileButtonStyle())
        Button { Task { if await model.renew() { confirmingRenew = false } } } label: {
            Text(localizer.text("inbox.renew"))
                .font(.subheadline.weight(.semibold)).foregroundStyle(Brand.onAccent)
                .frame(maxWidth: .infinity, minHeight: 44)
                .background(Brand.accent, in: RoundedRectangle(cornerRadius: 12, style: .continuous))
        }
        .buttonStyle(TactileButtonStyle())
        .accessibilityIdentifier("addByEmail.renew.confirm")
    }

    private func note(_ text: String) -> some View {
        Text(text).font(.caption).foregroundStyle(Brand.secondaryText)
            .fixedSize(horizontal: false, vertical: true)
            .padding(.leading, 2)
    }
}

/// One of the last emails: who sent it, what it did and when. An email that wasn't read has no sender, and what it did
/// stands in its place.
private struct AddByEmailRow: View {
    let entry: AddByEmailEntry

    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dynamicTypeSize) private var dynamicTypeSize

    var body: some View {
        let when = Text(localizer.relativeTime(from: entry.at)).font(.caption).foregroundStyle(Brand.secondaryText)
        let layout = dynamicTypeSize.isAccessibilitySize
            ? AnyLayout(VStackLayout(alignment: .leading, spacing: 4))
            : AnyLayout(HStackLayout(alignment: .firstTextBaseline, spacing: 11))
        layout {
            VStack(alignment: .leading, spacing: 3) {
                if entry.sender.isEmpty {
                    Text(entry.resultText(localizer)).font(.subheadline.weight(.semibold)).foregroundStyle(Brand.ink)
                } else {
                    Text(verbatim: entry.sender).font(.subheadline.weight(.semibold)).foregroundStyle(Brand.ink)
                    Text(entry.resultText(localizer)).font(.caption).foregroundStyle(Brand.secondaryText)
                }
            }
            .fixedSize(horizontal: false, vertical: true)
            if !dynamicTypeSize.isAccessibilitySize { Spacer(minLength: 4) }
            when
        }
        .padding(15)
        .frame(maxWidth: .infinity, alignment: .leading)
        .accessibilityElement(children: .combine)
    }
}

/// How to have a mail service forward shipping emails by itself, each service's steps in the order it asks for them,
/// with its own names for its buttons. The reader names their service, which starts on the one their account's
/// address belongs to, and the languages their shops and carriers write in, which start with the language Peek shows,
/// the device's and English.
/// - Each service's first step names the way to its settings as well as linking there, and says what to check when
///   they don't show what the step speaks of.
/// - Gmail forwards only to an address it has confirmed, and then only the emails a filter picks: the address is added
///   and confirmed first (Peek shows Gmail's confirmation in the step), then the filter is made from a search to copy.
/// - A service whose rule takes words one at a time is given the main ones, each copied by a tap.
/// - Yahoo, whose filters only move emails and whose forwarding takes every email, says so.
private struct ForwardingSteps: View {
    let confirmation: ForwardingConfirmation?

    @EnvironmentObject private var localizer: Localizer
    @State private var service: MailService
    /// The languages chosen, or nil until one is: those the reader's language and device start with.
    @State private var languages: [String]?
    @State private var copied: String?
    private let filter = EmailFilter.bundled

    init(email: String?, confirmation: ForwardingConfirmation?) {
        self.confirmation = confirmation
        // Gmail is the one service whose confirmation Peek shows: while one waits, its steps are the ones to follow.
        _service = State(initialValue: confirmation != nil ? .gmail : MailService.of(email))
    }

    private var chosen: [String] {
        languages ?? filter.languages(for: [localizer.language.rawValue] + Locale.preferredLanguages)
    }

    var body: some View {
        VStack(alignment: .leading, spacing: 18) {
            VStack(alignment: .leading, spacing: 8) {
                label(localizer.text("inbox.service.title"))
                PillFlow(spacing: 6) {
                    ForEach(MailService.allCases) { choice in
                        AddByEmailChoice(title: choice.name ?? localizer.text("inbox.service.other"), selected: service == choice) {
                            service = choice
                            copied = nil
                        }
                        .accessibilityIdentifier("addByEmail.service.\(choice.rawValue)")
                    }
                }
            }
            switch service {
            case .gmail: gmail
            case .outlook:
                step(1, "inbox.outlook.rule") {
                    stepLink(MailService.outlookRules, "inbox.outlook.link")
                    missing("inbox.outlook.work")
                }
                step(2, "inbox.outlook.condition") { words }
                step(3, "inbox.outlook.action")
            case .icloud:
                step(1, "inbox.icloud.rule") {
                    stepLink(MailService.iCloudMail, "inbox.icloud.link")
                    missing("inbox.icloud.noMail")
                }
                step(2, "inbox.icloud.words") { words }
                step(3, "inbox.icloud.more")
            case .yahoo:
                Text(localizer.text("inbox.yahoo")).font(.subheadline).foregroundStyle(Brand.ink)
                    .fixedSize(horizontal: false, vertical: true)
            case .other:
                step(1, "inbox.other.rule") { words }
                Text(localizer.text("inbox.other.link")).font(.caption).foregroundStyle(Brand.secondaryText)
                    .fixedSize(horizontal: false, vertical: true)
            }
        }
    }

    @ViewBuilder private var gmail: some View {
        step(1, "inbox.gmail.settings") { stepLink(MailService.gmailSettings, "inbox.gmail.settingsLink") }
        step(2, "inbox.gmail.add") { missing("inbox.gmail.noButton") }
        step(3, "inbox.gmail.confirm") {
            if let confirmation, confirmationLink(confirmation) != nil || confirmation.code != nil {
                VStack(alignment: .leading, spacing: 0) {
                    if let link = confirmationLink(confirmation) {
                        Link(destination: link) {
                            SettingsRow(title: localizer.text("inbox.confirm.link"), symbol: "envelope", chevron: true)
                        }
                        .buttonStyle(.plain)
                        .accessibilityIdentifier("addByEmail.confirmation")
                        if confirmation.code != nil { settingsDivider }
                    }
                    if let code = confirmation.code {
                        Text(localizer.text("inbox.confirm.code", ["code": code]))
                            .font(.subheadline).foregroundStyle(Brand.ink)
                            .textSelection(.enabled)
                            .fixedSize(horizontal: false, vertical: true)
                            .padding(15)
                    }
                }
                .stepBox()
            }
        }
        step(4, "inbox.gmail.keepOff")
        step(5, "inbox.gmail.search") {
            let search = filter.gmailFilter(chosen)
            VStack(alignment: .leading, spacing: 12) {
                languagePicker
                VStack(alignment: .leading, spacing: 0) {
                    Text(verbatim: search)
                        .font(.footnote.monospaced()).foregroundStyle(Brand.ink)
                        .textSelection(.enabled)
                        .fixedSize(horizontal: false, vertical: true)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .padding(12)
                        .accessibilityIdentifier("addByEmail.filter")
                    settingsDivider
                    Button { copy(search) } label: {
                        SettingsRow(title: localizer.text(copied == search ? "inbox.copied" : "inbox.filter.copy"),
                                    symbol: copied == search ? "checkmark" : "doc.on.doc")
                    }
                    .buttonStyle(.plain)
                    .accessibilityIdentifier("addByEmail.filter.copy")
                }
                .background(Brand.raised, in: RoundedRectangle(cornerRadius: 12, style: .continuous))
                .overlay(RoundedRectangle(cornerRadius: 12, style: .continuous).stroke(Brand.sheetLine(0.25), lineWidth: 0.5))
            }
            .stepBox(padded: true)
        }
        step(6, "inbox.gmail.filter")
        step(7, "inbox.gmail.forward")
    }

    /// Gmail's confirmation link, which the server takes only from a google.com host: opened only over https.
    private func confirmationLink(_ confirmation: ForwardingConfirmation) -> URL? {
        confirmation.link.flatMap(URL.init(string:)).flatMap { $0.scheme == "https" ? $0 : nil }
    }

    private var languagePicker: some View {
        VStack(alignment: .leading, spacing: 8) {
            label(localizer.text("inbox.filter.languages"))
            PillFlow(spacing: 6) {
                ForEach(filter.languages, id: \.self) { language in
                    AddByEmailChoice(title: languageName(language), selected: chosen.contains(language)) { toggle(language) }
                        .accessibilityIdentifier("addByEmail.language.\(language)")
                }
            }
        }
    }

    /// The main words of the chosen languages, each copied by a tap.
    private var words: some View {
        VStack(alignment: .leading, spacing: 12) {
            languagePicker
            PillFlow(spacing: 6) {
                ForEach(filter.subjectWords(chosen), id: \.self) { word in
                    Button { copy(word) } label: {
                        HStack(spacing: 6) {
                            Text(verbatim: word)
                            Image(systemName: copied == word ? "checkmark" : "doc.on.doc").font(.caption2)
                                .foregroundStyle(Brand.secondaryText)
                                .accessibilityHidden(true)
                        }
                        .font(.footnote.weight(.medium)).foregroundStyle(Brand.ink)
                        .padding(.horizontal, 12)
                        .frame(minHeight: 36)
                        .background(Brand.raised, in: RoundedRectangle(cornerRadius: 12, style: .continuous))
                        .overlay(RoundedRectangle(cornerRadius: 12, style: .continuous).strokeBorder(Brand.sheetLine(0.4), lineWidth: 0.5))
                        // 44 points to the touch, in the room of 36.
                        .padding(.vertical, 4)
                        .contentShape(Rectangle())
                        .padding(.vertical, -4)
                    }
                    .buttonStyle(TactileButtonStyle(scale: 0.96))
                    .accessibilityValue(copied == word ? localizer.text("inbox.copied") : "")
                }
            }
            .accessibilityElement(children: .contain)
            .accessibilityLabel(localizer.text("inbox.words.title"))
            Text(localizer.text(copied == nil ? "inbox.words.copy" : "inbox.copied"))
                .font(.caption).foregroundStyle(Brand.secondaryText)
                .fixedSize(horizontal: false, vertical: true)
        }
        .stepBox(padded: true)
    }

    /// A numbered step: its words, then what goes with it.
    private func step(_ number: Int, _ key: String, @ViewBuilder extra: () -> some View) -> some View {
        HStack(alignment: .top, spacing: 10) {
            StepNumber(number: number)
            VStack(alignment: .leading, spacing: 10) {
                StepText(text: localizer.text(key, ["options": StepText.optionsMark]))
                extra()
            }
            .frame(maxWidth: .infinity, alignment: .leading)
        }
        .accessibilityElement(children: .contain)
    }

    private func step(_ number: Int, _ key: String) -> some View {
        step(number, key) { EmptyView() }
    }

    /// A step's link to the page of the mail service it speaks of.
    private func stepLink(_ url: URL, _ key: String) -> some View {
        OutLink(url: url, key: key)
    }

    /// What to check when the service doesn't show what the step speaks of.
    private func missing(_ key: String) -> some View {
        StepText(text: localizer.text(key), font: .caption, color: Brand.secondaryText)
    }

    private func label(_ text: String) -> some View {
        Text(text).font(.caption.weight(.medium)).foregroundStyle(Brand.secondaryText)
            .fixedSize(horizontal: false, vertical: true)
            .accessibilityAddTraits(.isHeader)
    }

    /// The language's name in the language Peek shows, capitalised as a choice is.
    private func languageName(_ code: String) -> String {
        let locale = localizer.language.locale
        let name = locale.localizedString(forLanguageCode: code) ?? code.uppercased()
        return String(name.prefix(1)).uppercased(with: locale) + name.dropFirst()
    }

    private func toggle(_ language: String) {
        // A filter of no language would forward nothing: the last one stays chosen.
        let current = chosen
        let next = current.contains(language) ? current.filter { $0 != language } : filter.languages.filter { current.contains($0) || $0 == language }
        guard !next.isEmpty else { return }
        Haptics.selection()
        languages = next
        copied = nil
    }

    private func copy(_ text: String) {
        Clipboard.string = text
        Haptics.success()
        AccessibilityNotification.Announcement(localizer.text("inbox.copied")).post()
        copied = text
    }
}

/// A link that leaves the app, to a mail service's page or to the whole guide: subheadline semibold underlined, with ↗,
/// 44 tall.
private struct OutLink: View {
    let url: URL
    let key: String

    @EnvironmentObject private var localizer: Localizer

    var body: some View {
        Link(destination: url) {
            HStack(spacing: 5) {
                Text(localizer.text(key))
                Image(systemName: "arrow.up.right").font(.caption.weight(.semibold)).accessibilityHidden(true)
            }
            .font(.subheadline.weight(.semibold)).foregroundStyle(Brand.ink)
            .underline(color: Brand.sheetLine(0.6))
            .frame(minHeight: 44)
            .contentShape(Rectangle())
        }
        .buttonStyle(.plain)
    }
}

/// One of a few choices, chosen or not: a mail service, a language.
private struct AddByEmailChoice: View {
    let title: String
    let selected: Bool
    let action: () -> Void

    var body: some View {
        let shape = RoundedRectangle(cornerRadius: 12, style: .continuous)
        Button(action: action) {
            HStack(spacing: 5) {
                if selected { Image(systemName: "checkmark").font(.caption2.weight(.bold)).accessibilityHidden(true) }
                Text(title)
            }
            .font(.footnote.weight(.medium)).foregroundStyle(Brand.ink)
            .fixedSize(horizontal: false, vertical: true)
            .padding(.horizontal, 12)
            .padding(.vertical, 6)
            .frame(minHeight: 36)
            .background(selected ? settingsGreen.opacity(0.14) : Brand.raised, in: shape)
            .overlay(shape.strokeBorder(selected ? settingsGreen : Brand.sheetLine(0.4), lineWidth: selected ? 1.5 : 0.5))
            // 44 points to the touch, in the room of 36.
            .padding(.vertical, 4)
            .contentShape(Rectangle())
            .padding(.vertical, -4)
        }
        .buttonStyle(TactileButtonStyle(scale: 0.96))
        .accessibilityAddTraits(selected ? .isSelected : [])
    }
}

/// A step's number in its small round.
private struct StepNumber: View {
    let number: Int
    @ScaledMetric(relativeTo: .caption) private var size: CGFloat = 22

    var body: some View {
        Text(verbatim: "\(number)")
            .font(.caption.weight(.semibold).monospacedDigit()).foregroundStyle(Brand.ink)
            .frame(minWidth: size, minHeight: size)
            .background(Brand.sheetControl, in: Circle())
    }
}

/// A step's words, with the mail service's own button and field names, which the translation marks `**…**`, in bold,
/// and Gmail's options button drawn where the translation puts `{{options}}`: it has no written name on screen.
struct StepText: View {
    /// What `{{options}}` is given, for the glyph to stand in.
    static let optionsMark = "[[options]]"

    enum Piece: Equatable {
        case plain(String)
        case bold(String)
        case options
    }

    let text: String
    var font: Font = .subheadline
    var color: Color = Brand.ink

    var body: some View {
        Self.pieces(text).reduce(Text(verbatim: "")) { line, piece in
            switch piece {
            case .plain(let words): Text("\(line)\(Text(verbatim: words))")
            case .bold(let words): Text("\(line)\(Text(verbatim: words).bold())")
            // Gmail's "Show search options" button: three sliders.
            case .options: Text("\(line)\(Image(systemName: "slider.horizontal.3"))")
            }
        }
        .font(font).foregroundStyle(color)
        .fixedSize(horizontal: false, vertical: true)
        .frame(maxWidth: .infinity, alignment: .leading)
        .accessibilityLabel(Self.spoken(text))
    }

    /// The words as the translation marks them: `**name**` and the options mark.
    static func pieces(_ text: String) -> [Piece] {
        var pieces: [Piece] = []
        var plain = ""
        var rest = Substring(text)
        func flush() { if !plain.isEmpty { pieces.append(.plain(plain)); plain = "" } }
        while let first = rest.first {
            if rest.hasPrefix(optionsMark) {
                flush()
                pieces.append(.options)
                rest = rest.dropFirst(optionsMark.count)
                continue
            }
            if rest.hasPrefix("**") {
                let inner = rest.dropFirst(2).prefix { $0 != "*" }
                let after = rest.dropFirst(2 + inner.count)
                if !inner.isEmpty, after.hasPrefix("**") {
                    flush()
                    pieces.append(.bold(String(inner)))
                    rest = after.dropFirst(2)
                    continue
                }
            }
            plain.append(first)
            rest = rest.dropFirst()
        }
        flush()
        return pieces
    }

    /// What VoiceOver reads: the words without their marks. The options button is named in words beside its glyph.
    static func spoken(_ text: String) -> String {
        pieces(text).map { piece in
            switch piece {
            case .plain(let words), .bold(let words): words
            case .options: ""
            }
        }
        .joined()
        .replacingOccurrences(of: "  ", with: " ")
        .trimmingCharacters(in: .whitespaces)
    }
}

private extension View {
    /// A box within a step, on the sheet's ground inside the group's raised one.
    func stepBox(padded: Bool = false) -> some View {
        padding(padded ? 12 : 0)
            .frame(maxWidth: .infinity, alignment: .leading)
            .background(Brand.sheet, in: RoundedRectangle(cornerRadius: 12, style: .continuous))
            .overlay(RoundedRectangle(cornerRadius: 12, style: .continuous).stroke(Brand.sheetLine(0.25), lineWidth: 0.5))
    }
}
