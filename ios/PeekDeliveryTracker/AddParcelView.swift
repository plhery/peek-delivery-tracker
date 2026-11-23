import SwiftUI
#if os(iOS)
import UIKit
#endif
import VisionKit

struct ParcelPhoneInput {
    private var number = ""
    private var carrier: CarrierID?
    private var digits = ""

    subscript(number: String, carrier: CarrierID) -> String {
        get { self.number == number && self.carrier == carrier ? digits : "" }
        set {
            self.number = number
            self.carrier = carrier
            digits = newValue
        }
    }
}

struct AddParcelView: View {
    let onOpenParcel: (UUID) -> Void
    let onAdded: (UUID) -> Void
    /// What was shared, linked or pasted from the menu bar to open it.
    private let draft: SharedParcelDraft?
    /// Opens on the camera, for someone who asked to scan a label.
    private let scanning: Bool

    @EnvironmentObject private var store: ParcelStore
    @EnvironmentObject private var session: SessionStore
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dismiss) private var dismiss
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @Environment(\.dynamicTypeSize) private var dynamicTypeSize

    @State private var label: String
    @State private var trackingInput: String
    @State private var trackingURL = ""
    /// The delivery postcode typed, accepted or filled for each carrier in this sheet, and the country chosen beside it.
    @State private var postcodes = CarrierPostcodes()
    /// The last 4 digits of a phone, for a carrier that shows a parcel only with them; sent only while its field shows.
    @State private var phoneInput = ParcelPhoneInput()
    private var phoneDigits: String {
        get { phoneInput[normalizedNumber, resolvedCarrier] }
        nonmutating set { phoneInput[normalizedNumber, resolvedCarrier] = newValue }
    }
    @State private var showingScanner = false
    @State private var showingCarrierPicker = false
    @State private var didFocusTracking = false
    @State private var saving = false
    @State private var errorMessage: String?
    @State private var duplicateParcelID: UUID?
    /// The error shown is the day's lookups without an account running out: signing in carries on.
    @State private var lookupsSpent = false
    /// What a guest asked for that signing in unlocks, asked over this sheet so what they typed stays.
    @State private var unlock: UnlockReason?
    @State private var carrierOverride: CarrierID?
    @State private var lookupAttempt = 0
    @State private var verifiedCarrier: CarrierDetectionResponse?
    @State private var recognition: CarrierRecognition
    /// The carriers are asked as from the device's region; the reader can pick another country for a number, and the
    /// parcel added from here keeps it. It is never saved as a setting.
    @State private var pickedCountry: (number: String, country: String)?
    @State private var refusedQuestion: NumberRefusalRequest?
    /// The number whose check digit was questioned and then added as typed.
    @State private var asTyped: String?
    /// The number chosen among several in one text.
    @State private var pick: String?
    @FocusState private var focusedField: Field?

    @ObservedObject private var catalog = CarrierCatalog.shared

    private enum Field: Hashable {
        case label
        case tracking
        case trackingURL
        case deliveryPostcode
        case phoneDigits
    }

    init(draft: SharedParcelDraft?, scanning: Bool = false, onOpenParcel: @escaping (UUID) -> Void = { _ in }, onAdded: @escaping (UUID) -> Void = { _ in }) {
        self.draft = draft
        self.scanning = scanning
        self.onOpenParcel = onOpenParcel
        self.onAdded = onAdded
        _label = State(initialValue: draft?.label ?? "")
        _trackingInput = State(initialValue: draft?.trackingInput ?? "")
        // A shared text is settled as soon as the sheet opens.
        _recognition = State(initialValue: CarrierRecognition(
            settledNumber: draft.map { Self.trackingNumber(in: $0.trackingInput) }
        ))
    }

    var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                if !dynamicTypeSize.isAccessibilitySize { heading }

                ScrollView {
                    VStack(spacing: 0) {
                        // At the largest text sizes the heading wraps and scrolls with the form, leaving room above the keyboard.
                        if dynamicTypeSize.isAccessibilitySize { heading }
                        VStack(alignment: .leading, spacing: 20) {
                            VStack(alignment: .leading, spacing: 6) {
                                trackingField
                                if !parsed.trackingNumber.isEmpty {
                                    carrierFeedback
                                        .transition(reduceMotion ? .opacity : .offset(y: 6).combined(with: .opacity))
                                }
                            }

                            if needsRequiredDetails {
                                requiredDetails
                                    .transition(reduceMotion ? .opacity : .offset(y: 6).combined(with: .opacity))
                            }

                            nameField

                            if let errorMessage {
                                errorBanner(errorMessage)
                                    .transition(.opacity)
                            }
                        }
                        .disabled(saving)
                        .padding(.horizontal, 20)
                        .padding(.top, 4)
                        .padding(.bottom, 24)
                        .frame(maxWidth: 560)
                        .frame(maxWidth: .infinity)
                    }
                }
                .scrollIndicators(.hidden)
                .scrollDismissesKeyboard(.interactively)
            }
            .sheetGround()
            .toolbar(.hidden, for: .navigationBar)
            .safeAreaInset(edge: .bottom, spacing: 0) {
                primaryAction
            }
            .interactiveDismissDisabled(saving)
            .animation(reduceMotion ? nil : .snappy(duration: 0.28), value: parsed.trackingNumber.isEmpty)
            .animation(reduceMotion ? nil : .snappy(duration: 0.3), value: requirements)
            .animation(reduceMotion ? nil : .snappy(duration: 0.3), value: errorMessage)
            .animation(reduceMotion ? nil : .snappy(duration: 0.3), value: recognitionStatus)
            .sensoryFeedback(.selection, trigger: resolvedCarrier) { oldValue, newValue in
                oldValue != newValue && newValue != .unknown
            }
            .sheet(isPresented: $showingCarrierPicker) {
                NavigationStack {
                    CarrierPickerView(
                        selection: carrierOverride,
                        automatic: .init(description: automaticDescription, recommended: !choosingCarrier, busy: checking),
                        sections: pickerSections,
                        tags: pickerTags,
                        onSelect: selectCarrier
                    )
                    .toolbar {
                        ToolbarItem(placement: .cancellationAction) {
                            Button(localizer.text("common.cancel")) { showingCarrierPicker = false }
                        }
                    }
                }
                .environmentObject(localizer)
                .formOnPad()
            }
            #if os(iOS)
            .fullScreenCover(isPresented: $showingScanner) {
                TrackingScannerView { value in
                    withAnimation(reduceMotion ? nil : .snappy(duration: 0.34)) {
                        trackingInput = value
                        showingScanner = false
                    }
                    focusedField = nil
                    recognition.settledNumber = Self.trackingNumber(in: value)
                }
                .environmentObject(localizer)
            }
            #endif
            .task {
                guard !didFocusTracking else { return }
                didFocusTracking = true
                // Wait for the full-screen presentation before opening the keyboard, or the camera.
                do { try await Task.sleep(for: .milliseconds(300)) } catch { return }
                if scanning && scannerAvailable { showingScanner = true }
                else { focusedField = .tracking }
            }
            .onChange(of: inputCarrier, initial: true) { _, carrier in
                prepareRequiredDetails(for: carrier)
            }
            // A number that arrives while Add is open takes the field's place, as a paste does.
            .onChange(of: draft) { _, draft in
                if let draft { fill(draft) }
            }
            .onChange(of: focusedField) { previous, current in
                // Leaving the tracking field settles the number, unless it left to save.
                guard previous == .tracking, current != .tracking, !saving else { return }
                recognition.settledNumber = normalizedNumber
            }
            // What went wrong, and the note under the field, are read out as they appear.
            .onChange(of: errorMessage) { _, message in
                if let message { AccessibilityNotification.Announcement(message).post() }
            }
            .unlockSheet($unlock)
            // Signed in over this sheet, what they typed is still here, and the guest's limit no longer is.
            .onChange(of: session.user?.id) { _, user in
                if user != nil, lookupsSpent { errorMessage = nil; lookupsSpent = false }
            }
            .onChange(of: spokenNotice) { _, notice in
                if let notice { AccessibilityNotification.Announcement(notice).post() }
            }
            // VoiceOver's escape closes Add as its close button does, which a full-screen cover doesn't do by itself.
            .accessibilityAction(.escape) { if !saving { dismiss() } }
            .task(id: "\(lookupTrackingNumber ?? ""):\(lookupAttempt)") {
                guard let number = lookupTrackingNumber else { return }
                do {
                    try await Task.sleep(for: .milliseconds(350))
                    let result = try await store.detectCarrier(trackingNumber: number, typed: parsed.trackingNumber)
                    guard !Task.isCancelled else { return }
                    guard result.trackingNumber == number else { throw DeliveryAPIError.invalidResponse }
                    verifiedCarrier = result
                } catch {
                    guard !Task.isCancelled else { return }
                    verifiedCarrier = CarrierDetectionResponse(trackingNumber: number, amazonShippingStatus: .unavailable, carrier: .amazonLogistics)
                }
            }
            .task(id: recognitionQuestion) {
                guard let question = recognitionQuestion else { return }
                let number = question.trackingNumber
                recognition.failedNumber = nil
                recognition.answer = nil
                recognition.askedSpelling = question.typedNumber
                recognition.askedCountry = question.lookupCountryHint
                do {
                    // As typed: its dashes and spaces tell some carriers' numbers apart.
                    let result = try await store.detectCarrier(trackingNumber: number, typed: question.typedNumber,
                                                               lookupCountry: question.lookupCountryHint)
                    guard !Task.isCancelled else { return }
                    guard result.trackingNumber == number else { throw DeliveryAPIError.invalidResponse }
                    recognition.answer = result
                } catch {
                    guard !Task.isCancelled else { return }
                    recognition.failedNumber = number
                }
            }
            .task(id: refusalQuestion) {
                guard let question = refusalQuestion else { return }
                let refuses = await NumberRefusals.refuses(question)
                guard !Task.isCancelled else { return }
                refusedQuestion = refuses ? question : nil
            }
        }
    }

    private var heading: some View {
        HStack(spacing: 12) {
            Text(localizer.text("add.title"))
                .font(.title2.weight(.semibold))
                .tracking(-0.5)
                .accessibilityAddTraits(.isHeader)
            Spacer(minLength: 0)
            Button { dismiss() } label: {
                Image(systemName: "xmark")
                    .font(.body.weight(.medium))
                    .frame(width: 36, height: 36)
                    .background(Brand.sheetControl, in: Circle())
                    .frame(width: 44, height: 44)
                    .contentShape(Rectangle())
            }
            .buttonStyle(.plain)
            .accessibilityLabel(localizer.text("common.close"))
            .accessibilityIdentifier("addParcel.close")
            // Escape closes Add, but not from under the carrier picker or the scanner, which it would close too.
            .keyboardShortcut(showingCarrierPicker || showingScanner ? nil : KeyboardShortcut(.escape, modifiers: []))
            .disabled(saving)
        }
        .foregroundStyle(Brand.ink)
        .padding(.horizontal, 20)
        .padding(.top, 8)
        .padding(.bottom, 18)
        .frame(maxWidth: 560)
        .frame(maxWidth: .infinity)
    }

    private var nameField: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack(alignment: .firstTextBaseline, spacing: 6) {
                Text(localizer.text("design.parcelTitle"))
                    .font(.subheadline.weight(.medium))
                Text(localizer.text("add.optional"))
                    .font(.caption)
                    .foregroundStyle(Brand.secondaryText)
            }
            TextField(placeholder: localizer.text("add.contentsPlaceholder"), text: $label)
                .drawnField()
                .font(.body)
                .accessibilityLabel(localizer.text("design.parcelTitle"))
                .accessibilityIdentifier("addParcel.name")
                .submitLabel(.done)
                .focused($focusedField, equals: .label)
                .onSubmit {
                    if canSave { save() } else { focusedField = .tracking }
                }
                .onChange(of: label) { _, value in
                    if value.count > 80 { label = String(value.prefix(80)) }
                }
                .padding(14)
                .background(Brand.raised, in: RoundedRectangle(cornerRadius: 14))
                .overlay {
                    RoundedRectangle(cornerRadius: 14)
                        .strokeBorder(focusedField == .label ? ExperimentalPalette.ochre : Brand.sheetLine(0.3), lineWidth: 1)
                }
        }
        .foregroundStyle(Brand.ink)
    }

    private var trackingField: some View {
        VStack(alignment: .leading, spacing: 8) {
            ViewThatFits(in: .horizontal) {
                HStack(spacing: 8) {
                    trackingLabel
                    Spacer(minLength: 4)
                    captureActions
                }
                VStack(alignment: .leading, spacing: 0) {
                    trackingLabel
                    captureActions
                }
            }
            // The field's own words keep to one line, cut short at the largest text sizes. There they are drawn in its
            // place on the lines the field has, and the unseen original is still read as the field's placeholder.
            let placeholder = localizer.text("add.trackingPlaceholder")
            let drawsPlaceholder = dynamicTypeSize.isAccessibilitySize
            TextField(
                placeholder,
                text: $trackingInput,
                prompt: Text(placeholder).foregroundStyle(drawsPlaceholder ? Color.clear : Brand.secondaryText),
                axis: .vertical
            )
            .drawnField()
            .font(.body)
            .lineLimit(2...4)
            .overlay(alignment: .topLeading) {
                if drawsPlaceholder && trackingInput.isEmpty {
                    Text(placeholder)
                        .font(.body)
                        .foregroundStyle(Brand.secondaryText)
                        .lineLimit(2)
                        .allowsHitTesting(false)
                        .accessibilityHidden(true)
                }
            }
            .textInputAutocapitalization(.never)
            .autocorrectionDisabled()
            .accessibilityLabel(localizer.text("add.tracking"))
            .accessibilityIdentifier("addParcel.tracking")
            .submitLabel(.next)
            .focused($focusedField, equals: .tracking)
            .onSubmit(submitTracking)
            .onChange(of: trackingInput) { _, _ in
                duplicateParcelID = nil
                errorMessage = nil
                carrierOverride = nil
                // A number chosen among several stays chosen while the text still names it.
                if !several.contains(where: { $0.normalized == pick }) { pick = nil }
            }
            .padding(14)
            .background(Brand.raised, in: RoundedRectangle(cornerRadius: 14))
            .overlay {
                RoundedRectangle(cornerRadius: 14)
                    .strokeBorder(focusedField == .tracking ? ExperimentalPalette.ochre : Brand.sheetLine(0.3), lineWidth: 1)
            }

            if parsed.trackingNumber.isEmpty && !cleanedInput.isEmpty && order == nil {
                Text(localizer.text("add.notFound"))
                    .font(.caption)
                    .foregroundStyle(Brand.warning)
                    .fixedSize(horizontal: false, vertical: true)
            }
            // The start of a longer number is still being typed: the notice waits for the number to settle.
            if pickupCode && recognition.settledNumber == normalizedNumber {
                heldBackNote(title: localizer.text("add.pickupCode.title"), body: localizer.text("add.pickupCode.body"))
            }
            if placeholderNumber && recognition.settledNumber == normalizedNumber {
                Text(localizer.text("tracking.failure.placeholderNumber"))
                    .font(.caption)
                    .foregroundStyle(Brand.warning)
                    .fixedSize(horizontal: false, vertical: true)
            }
            // An order number is held back at once, and its notice waits in the same way.
            if let order, recognition.settledNumber == normalizedNumber {
                heldBackNote(title: localizer.text("door.order.\(order.rawValue).title"),
                             body: localizer.text("door.order.\(order.rawValue).body"))
                // Only Amazon has an orders page to link to: for Mercado Libre, Shein, Shopee and Temu the note says where to look instead.
                if order == .amazon {
                    Link(localizer.text("add.openAmazonOrders"), destination: CarrierCatalog.amazonOrdersURL(""))
                        .font(.subheadline)
                    amazonGuideLink
                }
            }
            // A check digit that doesn't add up is a question, never a refusal: adding the parcel takes it as typed.
            if let typo, recognition.settledNumber == normalizedNumber {
                heldBackNote(title: localizer.text("door.typo.check"),
                             body: localizer.text(typo.suggestion == nil ? "door.typo.compare" : "door.typo.letter"))
                if let suggestion = typo.suggestion {
                    Button { use(suggestion) } label: {
                        Text(Self.suggestionLabel(suggestion, typed: normalizedNumber) {
                            localizer.text("door.typo.suggest", ["number": $0])
                        })
                    }
                    .font(.subheadline)
                    .accessibilityLabel(localizer.text("door.typo.suggest", ["number": suggestion]))
                }
                Button(localizer.text("door.typo.asTyped")) {
                    asTyped = normalizedNumber
                    save()
                }
                .font(.subheadline)
            }
        }
        .foregroundStyle(Brand.ink)
    }

    /// The note under the field once the number has settled, as VoiceOver reads it out: none found, held back, or
    /// questioned.
    private var spokenNotice: String? {
        guard recognition.settledNumber == normalizedNumber else { return nil }
        // A note's title and its words are read one after the other, as two lines.
        let note = { (title: String, body: String) in "\(localizer.text(title))\n\(localizer.text(body))" }
        if parsed.trackingNumber.isEmpty && !cleanedInput.isEmpty && order == nil { return localizer.text("add.notFound") }
        if pickupCode { return note("add.pickupCode.title", "add.pickupCode.body") }
        if placeholderNumber { return localizer.text("tracking.failure.placeholderNumber") }
        if let order { return note("door.order.\(order.rawValue).title", "door.order.\(order.rawValue).body") }
        if let typo { return note("door.typo.check", typo.suggestion == nil ? "door.typo.compare" : "door.typo.letter") }
        return nil
    }

    /// Why the field holds no number to add, and what to paste instead.
    private func heldBackNote(title: String, body: String) -> some View {
        VStack(alignment: .leading, spacing: 2) {
            Text(title).font(.caption.weight(.semibold))
            Text(body).font(.caption)
        }
        .foregroundStyle(Brand.warning)
        .fixedSize(horizontal: false, vertical: true)
        .accessibilityElement(children: .combine)
    }

    private var trackingLabel: some View {
        Text(localizer.text("add.tracking"))
            .font(.subheadline.weight(.medium))
            .fixedSize(horizontal: false, vertical: true)
    }

    private var captureActions: some View {
        HStack(spacing: 12) {
            Button(action: paste) {
                Label(localizer.text("add.paste"), systemImage: "doc.on.clipboard")
                    .font(.caption)
                    .fixedSize()
                    .frame(minHeight: 44)
            }
            .accessibilityIdentifier("addParcel.paste")
            if scannerAvailable {
                Button {
                    DeliveryAnalytics.shared.action("parcel-scan")
                    focusedField = nil
                    showingScanner = true
                } label: {
                    Image(systemName: "barcode.viewfinder")
                        .font(.body)
                        .frame(width: 44, height: 44)
                }
                .accessibilityLabel(localizer.text("add.scan"))
            }
        }
        .buttonStyle(.plain)
        .foregroundStyle(Brand.secondaryText)
    }

    private var carrierFeedback: some View {
        let definition = catalog.info(for: resolvedCarrier, language: localizer.language)
        let automatic = catalog.tracksAutomatically(resolvedCarrier)

        return VStack(alignment: .leading, spacing: 10) {
            if several.isEmpty {
                carrierLine

                if parsed.source == .link || parsed.source == .text {
                    Label(CarrierCatalog.format(parsed.trackingNumber, carrier: resolvedCarrier), systemImage: "barcode")
                        .font(.caption.monospaced())
                        .textSelection(.enabled)
                        .accessibilityLabel(localizer.text(parsed.source == .link ? "add.foundLink" : "add.foundText", [
                            "number": CarrierCatalog.format(parsed.trackingNumber, carrier: resolvedCarrier),
                        ]))
                }
            } else {
                numberChoice
            }

            if !automatic || shippingConfirmed {
                Text(localizer.text(shippingConfirmed ? (currentVerification?.amazonShippingStatus == .expired ? "add.amazonHistoryExpired" : "add.amazonShippingConfirmed") : catalog.trackingHintKey(for: resolvedCarrier), ["carrier": definition.displayName]))
                    .font(.caption)
                    .foregroundStyle(Brand.secondaryText)
                    .fixedSize(horizontal: false, vertical: true)
            }
            if let known = knownElsewhere {
                let name = catalog.info(for: known, language: localizer.language).displayName
                Text(localizer.text("add.carrierKnownElsewhere", ["carrier": name]))
                    .font(.caption)
                    .foregroundStyle(Brand.secondaryText)
                    .fixedSize(horizontal: false, vertical: true)
                Button(localizer.text("add.useCarrier", ["carrier": name])) {
                    carrierOverride = known
                }
                .font(.subheadline)
            }
            if amazonNumber && currentVerification?.amazonShippingStatus == .unavailable {
                Text(localizer.text("add.amazonCheckUnavailable")).font(.caption).foregroundStyle(Brand.secondaryText)
                Button(localizer.text("add.amazonRetry")) {
                    verifiedCarrier = nil
                    lookupAttempt += 1
                }.font(.subheadline)
            }
            if accountRequired {
                Link(localizer.text("add.openAmazonOrders"), destination: CarrierCatalog.amazonOrdersURL(parsed.trackingNumber))
                    .font(.subheadline)
                amazonGuideLink
            }
        }
        .foregroundStyle(Brand.ink)
    }

    /// Amazon's parcels can be tracked: the guide shows where an order's carrier and tracking number are, in Safari,
    /// when the site serves it.
    @ViewBuilder private var amazonGuideLink: some View {
        if let guide = catalog.amazonGuideURL(base: session.configuration.apiBaseURL, language: localizer.language) {
            Link(localizer.text("add.amazonGuide"), destination: guide)
                .font(.subheadline)
        }
    }

    /// Several numbers in one text, in the order written: the first is added unless another is chosen, as on the
    /// front door. Each one's carrier is certain, so the carrier line gives way to the list.
    private var numberChoice: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(localizer.text("door.several.title.many", ["count": several.count]))
                .font(.subheadline.weight(.semibold))
                .fixedSize(horizontal: false, vertical: true)
                .accessibilityAddTraits(.isHeader)
            ForEach(several, id: \.normalized) { number in
                numberRow(number, chosen: number.normalized == picked?.normalized)
            }
            // Someone signed in keeps them together already.
            if store.isGuest {
                let question = Text(localizer.text("door.several.question")).foregroundStyle(Brand.secondaryText)
                let signIn = Button(localizer.text("door.several.signIn")) { unlock = .several }
                .fontWeight(.medium)
                .underline()
                .frame(minHeight: 44)
                ViewThatFits(in: .horizontal) {
                    HStack(alignment: .firstTextBaseline, spacing: 4) { question; signIn }
                    VStack(alignment: .leading, spacing: 0) { question.padding(.top, 6); signIn }
                }
                .font(.caption)
            }
        }
    }

    private func numberRow(_ number: NumberInText, chosen: Bool) -> some View {
        let carrier = number.match.carrier
        let name = catalog.info(for: carrier, language: localizer.language).displayName
        let printed = CarrierCatalog.format(number.match.trackingNumber, carrier: carrier)
        let shape = RoundedRectangle(cornerRadius: 14, style: .continuous)
        return Button { choose(number) } label: {
            HStack(spacing: 12) {
                CarrierTruckMark(identity: CarrierVisualIdentity.of(carrier, language: localizer.language))
                VStack(alignment: .leading, spacing: 1) {
                    Text(name).font(.subheadline.weight(.semibold))
                    Text(printed)
                        .font(.caption.monospaced().weight(.medium))
                        .foregroundStyle(Brand.inkSoft)
                }
                .fixedSize(horizontal: false, vertical: true)
                Spacer(minLength: 8)
                Image(systemName: chosen ? "checkmark.circle.fill" : "circle")
                    .font(.title3)
                    .foregroundStyle(chosen ? ExperimentalPalette.ochre : Brand.separator)
            }
            .padding(.horizontal, 14)
            .padding(.vertical, 10)
            .frame(minHeight: 56)
            .background(chosen ? ExperimentalPalette.ochreSurface : Brand.raised, in: shape)
            .overlay(shape.strokeBorder(chosen ? ExperimentalPalette.ochre : Brand.sheetLine(0.3), lineWidth: chosen ? 1.5 : 1))
            .contentShape(shape)
        }
        .buttonStyle(TactileButtonStyle(scale: 0.98))
        .foregroundStyle(Brand.ink)
        .readsAsItsLabel()
        .accessibilityLabel(name)
        .accessibilityValue(printed)
        .accessibilityAddTraits(chosen ? [.isButton, .isSelected] : .isButton)
        .accessibilityIdentifier("addParcel.number.\(number.normalized)")
    }

    /// One quiet line under the number: the carrier once one is known, and
    /// otherwise what the carrier check has found so far. It opens the picker.
    /// While the line names the country the carriers are asked for, that country's menu sits beside its words.
    private var carrierLine: some View {
        let carrier = lineCarrier
        let name = carrier.map { catalog.info(for: $0, language: localizer.language).displayName }
            ?? localizer.text("add.detect")
        let detail = lineDetail
        let country = detail == nil ? nil : lineCountry
        return Button {
            // Leaving the field settles the number, so the check starts as the picker opens.
            focusedField = nil
            showingCarrierPicker = true
        } label: {
            HStack(spacing: 8) {
                Group {
                    if let carrier {
                        CarrierTruckMark(identity: CarrierVisualIdentity.of(carrier, language: localizer.language))
                    } else if checking {
                        ProgressView().controlSize(.mini)
                    } else {
                        Image(systemName: "wand.and.sparkles")
                            .font(.caption)
                            .foregroundStyle(ExperimentalPalette.ochre)
                    }
                }
                .frame(width: 27)
                // At the largest text sizes the words have the line to themselves, and the way to change them goes under.
                if dynamicTypeSize.isAccessibilitySize {
                    VStack(alignment: .leading, spacing: 2) {
                        Text(name).font(.subheadline.weight(.medium))
                        lineDetailText(detail, country: country)
                        lineAction
                    }
                } else if country != nil {
                    // With the country's menu beside them, the words always go under the name: a line fitted one of
                    // two ways would leave the menu's place uncertain.
                    VStack(alignment: .leading, spacing: 2) {
                        Text(name).font(.subheadline.weight(.medium))
                        lineDetailText(detail, country: country)
                    }
                } else {
                    ViewThatFits(in: .horizontal) {
                        HStack(alignment: .firstTextBaseline, spacing: 8) {
                            Text(name).font(.subheadline.weight(.medium))
                            lineDetailText(detail)
                        }
                        VStack(alignment: .leading, spacing: 2) {
                            Text(name).font(.subheadline.weight(.medium))
                            lineDetailText(detail)
                        }
                    }
                }
                Spacer(minLength: 0)
                if !dynamicTypeSize.isAccessibilitySize { lineAction }
            }
            .frame(minHeight: 44)
            .contentShape(Rectangle())
        }
        .buttonStyle(TactileButtonStyle())
        .accessibilityLabel([name, detail].compactMap { $0 }.joined(separator: ", "))
        .accessibilityHint(localizer.text(choosingCarrier ? "add.line.choose" : "add.line.change"))
        // Voice Control answers to the word that says the line opens the picker, and to the carrier's name.
        .accessibilityInputLabels([
            amazonNumber ? nil : localizer.text(choosingCarrier ? "add.line.choose" : "add.line.change"),
            name,
            [name, detail].compactMap { $0 }.joined(separator: ", "),
        ].compactMap { $0 })
        .accessibilityIdentifier("addParcel.carrier")
        .disabled(amazonNumber)
        // VoiceOver reads the line, then the menu of the country it names.
        .accessibilitySortPriority(1)
        // The menu is drawn over the button, at the place kept for it beside the words, rather than inside its label:
        // a tap on it opens the menu, and anywhere else on the line the picker.
        .overlayPreferenceValue(LookupCountryPlace.self) { place in
            if let place, let country {
                GeometryReader { geometry in
                    let frame = geometry[place]
                    LookupCountryMenu(country: country, home: ParcelLookupCountry.hint(), choose: pickCountry)
                        .equatable()
                        .position(x: frame.midX, y: frame.midY)
                }
            }
        }
        .accessibilityElement(children: .contain)
    }

    /// Says that the line opens the picker. An Amazon number keeps its carrier.
    @ViewBuilder private var lineAction: some View {
        if !amazonNumber {
            Text(localizer.text(choosingCarrier ? "add.line.choose" : "add.line.change"))
                .font(.caption)
                .foregroundStyle(Brand.secondaryText)
                .underline()
                .fixedSize(horizontal: false, vertical: true)
        }
    }

    /// The line's words, and before them the place kept for the menu of the `country` they name.
    @ViewBuilder
    private func lineDetailText(_ detail: String?, country: String? = nil) -> some View {
        if let detail {
            let text = Text(detail)
                .font(.caption)
                .foregroundStyle(choosingCarrier || refused ? ExperimentalPalette.ochre : Brand.secondaryText)
                .fixedSize(horizontal: false, vertical: true)
            if let country {
                HStack(alignment: .firstTextBaseline, spacing: 4) {
                    LookupCountryMenu.label(country)
                        .anchorPreference(key: LookupCountryPlace.self, value: .bounds) { $0 }
                        .opacity(0)
                        .accessibilityHidden(true)
                    text
                }
            } else {
                text
            }
        }
    }

    private var requiredDetails: some View {
        VStack(alignment: .leading, spacing: 20) {
            if let requirement = trackingURLRequirement,
               parsed.trackingURL == nil {
                requirementField(
                    title: localizer.text("add.requirement.trackingUrl"),
                    help: localizer.text("add.requirement.trackingUrlHelp")
                ) {
                    TextField(placeholder: requirement.placeholder ?? "https://…", text: $trackingURL)
                        .drawnField()
                        .font(.body)
                        .keyboardType(.URL)
                        .textContentType(.URL)
                        .textInputAutocapitalization(.never)
                        .autocorrectionDisabled()
                        .focused($focusedField, equals: .trackingURL)
                        .accessibilityLabel(localizer.text("add.requirement.trackingUrl"))
                        .submitLabel(.next)
                        .onSubmit(submitTracking)
                        .onChange(of: trackingURL) { _, value in
                            trackingURL = requirement.normalizedValue(value)
                        }
                        .padding(14)
                }
            }

            if let requirement = postcodeRequirement {
                let carrierName = catalog.info(for: inputCarrier, language: localizer.language).displayName
                VStack(alignment: .leading, spacing: 10) {
                    requirementField(
                        title: localizer.text("add.requirement.dpdPostcode"),
                        // A suggested carrier's field says who may have the parcel, as its page does once it is added.
                        help: suggestion != nil
                            ? localizer.text("detail.inputSuggested", ["carrier": carrierName])
                            : requirement.isOptional
                            ? localizer.text(catalog.optionalPostcodeHelpKey(inputCarrier), ["carrier": carrierName])
                            : localizer.text("add.requirement.dpdPostcodeHelp"),
                        optional: requirement.isOptional
                    ) {
                        // Where no country is named beside the field, the example is the reader's.
                        let example = catalog.postcodeExample(for: inputCarrier, requirement: requirement,
                                                              region: requirement.readsCountry == true ? postcodeCountry : CarrierCatalog.deviceCountry())
                        HStack(spacing: 0) {
                            if let country = offeredPostcodeCountry, let carrierRequirement = carrierPostcodeRequirement {
                                PostcodeCountryMenu(carrier: inputCarrier, requirement: carrierRequirement, country: country,
                                                    inset: EdgeInsets(top: 14, leading: 14, bottom: 14, trailing: 10)) {
                                    postcodes.choose($0, for: inputCarrier)
                                }
                                .equatable()
                                Divider().padding(.vertical, 12)
                            }
                            TextField(placeholder: example ?? "", text: Binding(get: { deliveryPostcode }, set: { deliveryPostcode = $0 }))
                                .drawnField()
                                .font(.body.monospacedDigit())
                                .keyboardType(requirement.inputMode == "numeric" ? .numberPad
                                    : requirement.startsWithNumberKeys(example: example) ? .numbersAndPunctuation : .asciiCapable)
                                .textInputAutocapitalization(.characters)
                                .autocorrectionDisabled()
                                .textContentType(.postalCode)
                                .focused($focusedField, equals: .deliveryPostcode)
                                .accessibilityLabel(localizer.text("add.requirement.dpdPostcode"))
                                .onChange(of: deliveryPostcode) { _, value in
                                    deliveryPostcode = requirement.typedValue(value)
                                }
                                .padding(14)
                        }
                    }
                    // An optional postcode is offered, never filled in for the user.
                    if let base = carrierPostcodeRequirement,
                       let given = postcodes.offering(inputCarrier, requirement: base, among: store.givenPostcodes, catalog: catalog) {
                        Button(localizer.text("add.usePostcode", ["postcode": given.postcode])) {
                            postcodes.use(given.postcode, for: inputCarrier, country: given.country)
                        }
                        .font(.subheadline)
                    }
                    // Without an account every suggestion is this iPhone's own, so it can forget them: no other carrier's
                    // field keeps it either.
                    if store.isGuest, postcodes.holdsFill(inputCarrier) {
                        Button(localizer.text("add.forgetPostcode")) {
                            if let postcode = postcodes.forget(inputCarrier) { store.forgetPostcode(postcode) }
                        }
                        .font(.subheadline)
                    }
                }
            }

            if let requirement = phoneDigitsRequirement {
                VStack(alignment: .leading, spacing: 10) {
                    requirementField(
                        title: localizer.text("add.requirement.phoneDigits"),
                        help: localizer.text("add.requirement.phoneDigitsHelp", [
                            "carrier": catalog.info(for: resolvedCarrier, language: localizer.language).displayName,
                        ]),
                        optional: requirement.isOptional
                    ) {
                        // Number keys, and no content type: nothing on the iPhone fills in these digits.
                        TextField(placeholder: requirement.placeholder ?? "", text: Binding(get: { phoneDigits }, set: { phoneDigits = $0 }))
                            .drawnField()
                            .font(.body.monospacedDigit())
                            .keyboardType(requirement.inputMode == "numeric" ? .numberPad : .asciiCapable)
                            .autocorrectionDisabled()
                            .focused($focusedField, equals: .phoneDigits)
                            .accessibilityLabel(localizer.text("add.requirement.phoneDigits"))
                            .onChange(of: phoneDigits) { _, value in
                                phoneDigits = requirement.normalizedValue(value)
                            }
                            .padding(14)
                    }
                    // Without an account the parcel has a link, which never shows the digits.
                    if store.isGuest {
                        HStack(alignment: .top, spacing: 7) {
                            Image(systemName: "lock").font(.caption2).padding(.top, 2)
                            Text(localizer.text("door.phoneDigits.private"))
                                .font(.caption)
                                .fixedSize(horizontal: false, vertical: true)
                        }
                        .foregroundStyle(Brand.secondaryText)
                        .accessibilityElement(children: .combine)
                    }
                }
            }
        }
    }

    private func requirementField<FieldContent: View>(
        title: String,
        help: String,
        optional: Bool = false,
        @ViewBuilder field: () -> FieldContent
    ) -> some View {
        VStack(alignment: .leading, spacing: 7) {
            HStack(alignment: .firstTextBaseline, spacing: 6) {
                Text(title)
                    .font(.subheadline.weight(.semibold))
                if optional {
                    Text(localizer.text("add.optional"))
                        .font(.caption)
                        .foregroundStyle(Brand.secondaryText)
                }
            }
            field()
                .background(Brand.raised, in: RoundedRectangle(cornerRadius: 12))
                .overlay(RoundedRectangle(cornerRadius: 12).strokeBorder(Brand.sheetLine(0.3), lineWidth: 0.7))
            Text(help)
                .font(.caption)
                .foregroundStyle(Brand.secondaryText)
                .fixedSize(horizontal: false, vertical: true)
        }
    }

    private func errorBanner(_ message: String) -> some View {
        VStack(alignment: .leading, spacing: 10) {
            HStack(alignment: .top, spacing: 12) {
                Image(systemName: "exclamationmark.triangle.fill")
                    .foregroundStyle(.red)
                Text(message)
                    .font(.subheadline.weight(.medium))
                    .fixedSize(horizontal: false, vertical: true)
                Spacer(minLength: 0)
            }
            if let duplicateParcelID {
                Button(localizer.text("add.openExisting")) {
                    dismiss()
                    onOpenParcel(duplicateParcelID)
                }
                .font(.subheadline.weight(.semibold))
                .buttonStyle(.plain)
                .foregroundStyle(Brand.ink)
                .underline()
            } else if lookupsSpent, store.isGuest {
                Button(localizer.text("arrival.signInTitle")) { unlock = .daily }
                    .font(.subheadline.weight(.semibold))
                    .buttonStyle(.plain)
                    .foregroundStyle(Brand.ink)
                    .underline()
                    .accessibilityIdentifier("add.dailySignIn")
            }
        }
        .padding(16)
        .experimentalSurface(fill: ExperimentalPalette.roseSurface, cornerRadius: 20, shadow: false)
    }

    private var primaryAction: some View {
        addButton
            .padding(.horizontal, 20)
            .padding(.vertical, 16)
            .frame(maxWidth: 560)
            .frame(maxWidth: .infinity)
            .background(Brand.sheet)
    }

    private var addButton: some View {
        Button(action: save) {
            HStack(spacing: 10) {
                if saving {
                    ProgressView().tint(Brand.onAccent)
                    Text(localizer.text("add.adding"))
                } else {
                    Text(localizer.text(several.isEmpty ? "app.addParcel" : "door.several.track"))
                }
            }
            .font(.headline)
            // Pinned above the keyboard, it keeps to two lines at the largest text sizes, and only a longer title
            // than those hold is made smaller.
            .lineLimit(dynamicTypeSize.isAccessibilitySize ? 2 : nil)
            .minimumScaleFactor(dynamicTypeSize.isAccessibilitySize ? 0.7 : 1)
            .multilineTextAlignment(.center)
            .foregroundStyle(Brand.onAccent)
            .frame(maxWidth: .infinity, minHeight: 50)
            .padding(.horizontal, 16)
            .background(Brand.accent, in: RoundedRectangle(cornerRadius: 14))
            .opacity(canSave || saving ? 1 : 0.45)
            .contentTransition(.opacity)
        }
        .buttonStyle(TactileButtonStyle())
        .accessibilityShowsLargeContentViewer()
        .accessibilityIdentifier("addParcel.save")
        .animation(reduceMotion ? nil : .easeOut(duration: 0.2), value: canSave)
        .disabled(!canSave || saving)
    }

    private var cleanedInput: String {
        trackingInput.trimmingCharacters(in: .whitespacesAndNewlines)
    }

    private var scannerAvailable: Bool {
        LabelScanner.isAvailable
    }

    /// Every number the field names with certainty, when it names two or more: one is to be chosen.
    private var several: [NumberInText] { catalog.numbers(in: trackingInput) }

    /// The number chosen among several: the first, unless another was chosen.
    private var picked: NumberInText? { several.first { $0.normalized == pick } ?? several.first }

    /// The shop whose order number the field holds. No carrier tracks one, so nothing asks about it and it is not saved.
    /// A text that names several numbers is about them.
    private var order: OrderShop? { picked == nil ? catalog.order(in: trackingInput) : nil }

    /// The number in play: the one chosen among several, or none for an order number.
    private var parsed: TrackingInputMatch {
        picked?.match ?? (order == nil ? catalog.read(trackingInput) : CarrierCatalog.emptyMatch)
    }

    private var normalizedNumber: String { CarrierCatalog.normalize(parsed.trackingNumber) }

    private static func trackingNumber(in input: String) -> String {
        let catalog = CarrierCatalog.shared
        if let first = catalog.numbers(in: input).first { return first.normalized }
        return catalog.order(in: input) == nil ? CarrierCatalog.normalize(catalog.read(input).trackingNumber) : ""
    }

    private var amazonNumber: Bool { catalog.isAmazonTrackingNumber(parsed.trackingNumber) }

    /// A postal number whose check digit doesn't add up, until it is added as typed. A number chosen among several
    /// is certain of its carrier.
    private var typo: CheckDigitTrouble? {
        normalizedNumber.isEmpty || picked != nil || asTyped == normalizedNumber ? nil : catalog.checkDigitTrouble(normalizedNumber)
    }

    /// "Did you mean …?", with the number set in monospace and each character the suggestion changes marked, as on
    /// the front door.
    static func suggestionLabel(_ suggestion: String, typed: String, sentence: (String) -> String) -> AttributedString {
        let parts = sentence("\u{0}").components(separatedBy: "\u{0}")
        var number = AttributedString()
        let typed = Array(typed)
        for (index, character) in suggestion.enumerated() {
            var piece = AttributedString(String(character))
            if index >= typed.count || typed[index] != character { piece.backgroundColor = ExperimentalPalette.ochreSurface }
            number += piece
        }
        number.font = Font.subheadline.monospaced()
        return AttributedString(parts.first ?? "") + number + AttributedString(parts.dropFirst().joined())
    }

    /// Six digits are a pickup code unless a carrier picked by hand takes them as its number.
    private var pickupCode: Bool { carrierOverride == nil && CarrierCatalog.isPickupCode(normalizedNumber) }

    /// A placeholder, such as the first screen's demo number, is held back in the same way, whichever the carrier:
    /// every lookup refuses it. The demo looks nothing up.
    private var placeholderNumber: Bool { !store.isDemo && !pickupCode && CarrierCatalog.isPlaceholderNumber(normalizedNumber) }

    private var currentVerification: CarrierDetectionResponse? {
        verifiedCarrier?.trackingNumber == normalizedNumber ? verifiedCarrier : nil
    }

    private var recognizable: Bool {
        CarrierRecognition.applies(to: parsed, amazon: amazonNumber, demo: store.isDemo)
    }

    /// The number as typed, when its dashes or spaces spell it otherwise: detection reads them.
    private var typedSpelling: String? {
        CarrierDetectionRequest.typedNumber(parsed.trackingNumber, for: normalizedNumber)
    }

    /// The country picked for the number in the field, which the parcel added from here keeps.
    private var pickedLookupCountry: String? {
        pickedCountry?.number == normalizedNumber ? pickedCountry?.country : nil
    }

    /// The country the carriers are asked for: the one picked for this number, else the device's region.
    private var sentCountry: String? { pickedLookupCountry ?? ParcelLookupCountry.hint() }

    /// The check to run: the number, its spelling and its country, so that the same number typed otherwise or asked
    /// for another country is asked about again and an earlier answer never lands on it.
    private var recognitionQuestion: CarrierDetectionRequest? {
        recognition.request(for: normalizedNumber, spelling: typedSpelling, country: sentCountry, applies: recognizable)
            .map { CarrierDetectionRequest(trackingNumber: $0, lookupCountryHint: sentCountry, typedNumber: typedSpelling) }
    }

    private var recognitionStatus: CarrierRecognition.Status {
        recognition.status(for: normalizedNumber, spelling: typedSpelling, country: sentCountry, applies: recognizable)
    }

    private var checking: Bool {
        guard carrierOverride == nil, case .asking = recognitionStatus else { return false }
        return true
    }

    /// Unrelated carriers all know the number: automatic detection can still save it, but the user should choose.
    private var choosingCarrier: Bool {
        guard carrierOverride == nil, case .several = recognitionStatus else { return false }
        return true
    }

    private var lineCarrier: CarrierID? {
        resolvedCarrier == .unknown ? nil : resolvedCarrier
    }

    /// The carrier a high-confidence rule gives the number, before any check.
    private var numberCarrier: CarrierID? {
        parsed.confidence == .high && parsed.carrier != .unknown && parsed.carrier != .internationalPost ? parsed.carrier : nil
    }

    /// What tracking services said about the number in the field while no carrier knows it.
    private var preflightDetail: String? {
        guard recognizable, let answer = recognition.answer, answer.trackingNumber == normalizedNumber,
              recognition.askedSpelling == typedSpelling, recognition.askedCountry == sentCountry,
              answer.carrier == .unknown else { return nil }
        if answer.trackingFound == true { return localizer.text("add.historyFound") }
        if answer.providers?.contains(where: { $0.outcome == .inputRequired }) == true { return localizer.text("add.providerInputNeeded") }
        return nil
    }

    /// A carrier picked by hand, once the number is settled.
    private var refusalQuestion: NumberRefusalRequest? {
        guard !amazonNumber, recognition.settledNumber == normalizedNumber else { return nil }
        return NumberRefusals.question(carrier: carrierOverride, trackingNumber: normalizedNumber, demo: store.isDemo)
    }

    /// A carrier picked by hand that issues no number of this shape is pointed out before the parcel is added.
    private var refused: Bool {
        refusalQuestion != nil && refusedQuestion == refusalQuestion
    }

    /// The country the carriers are asked for while it decides what the check says, which the line and the picker name.
    private var countryAskedFor: String? {
        recognition.countryAsked(for: normalizedNumber, spelling: typedSpelling, country: sentCountry, applies: recognizable,
                                 preflight: preflightDetail != nil)
    }

    /// The country the line names, with its menu to pick another: while the carrier is left automatic and the line shows
    /// no carrier from the number's shape.
    private var lineCountry: String? {
        carrierOverride == nil && !amazonNumber && !(lineCarrier != nil && lineCarrier != .internationalPost) ? countryAskedFor : nil
    }

    /// The words with the country the carriers are asked for before them, when one is known.
    private func withCountry(_ text: String, _ country: String?) -> String {
        guard let country else { return text }
        return localizer.text("add.line.country", [
            "country": TrackingLocation.countryName(country, language: localizer.language), "text": text,
        ])
    }

    private var lineDetail: String? {
        if carrierOverride != nil { return localizer.text(refused ? "add.line.refused" : "add.line.chosen") }
        if case .recognized = recognitionStatus { return localizer.text("add.line.found") }
        if let preflightDetail { return preflightDetail }
        if amazonNumber || (lineCarrier != nil && lineCarrier != .internationalPost) { return localizer.text("add.detectedCarrier") }
        let detail: String? = switch recognitionStatus {
        case .asking: localizer.text("add.checkingServices")
        case .several(let carriers): localizer.text("add.line.several", ["carriers": carrierNames(carriers)])
        case .notFound: localizer.text("add.line.none")
        case .failed: localizer.text("add.line.failed")
        case .unasked: localizer.text("add.line.later")
        case .idle, .recognized: nil
        }
        return detail.map { withCountry($0, lineCountry) }
    }

    private var automaticDescription: String { withCountry(statusDescription, countryAskedFor) }

    private var statusDescription: String {
        if let preflightDetail { return preflightDetail }
        switch recognitionStatus {
        case .asking: return localizer.text("add.checkingServices")
        case .recognized(let carrier):
            return localizer.text("add.recognized", ["carrier": catalog.info(for: carrier, language: localizer.language).displayName])
        case .several(let carriers): return localizer.text("picker.auto.several", ["carriers": carrierNames(carriers)])
        case .notFound(_, services: true): return localizer.text("picker.auto.servicesNone")
        case .notFound(let carriers, _): return localizer.text("picker.auto.none", ["carriers": carrierNames(carriers)])
        case .failed(_, services: true): return localizer.text("picker.auto.servicesFailed")
        case .failed(let carriers, _):
            return carriers.isEmpty ? localizer.text("add.line.failed")
                : localizer.text("picker.auto.failed", ["carriers": carrierNames(carriers)])
        case .idle, .unasked:
            if let numberCarrier {
                return localizer.text("picker.auto.detected", ["carrier": catalog.info(for: numberCarrier, language: localizer.language).displayName])
            }
            return localizer.text("picker.auto.later")
        }
    }

    /// The picker leads with the carriers that fit the number (the reader's country's first), then the ones used before.
    private var pickerSections: [CarrierPickerView.PickerSection] {
        let knowing: [CarrierID] = if case .several(let carriers) = recognitionStatus { carriers } else { [] }
        let fitting = CarrierPickerSearch.inReaderCountryFirst(
            (numberCarrier.map { [$0] } ?? parsed.candidates).filter { catalog.info(for: $0).selectable },
            country: ParcelLookupCountry.hint(), catalog: catalog)
        return [
            .init(id: "known", title: localizer.text("picker.section.known"), carriers: knowing),
            .init(id: "fits", title: localizer.text(numberCarrier == nil ? "picker.section.fits" : "picker.section.detected"),
                  carriers: fitting.filter { !knowing.contains($0) }),
            .init(id: "used", title: localizer.text("picker.section.used"),
                  carriers: CarrierPickerSearch.usedCarriers(store.parcels, catalog: catalog)
                    .filter { !knowing.contains($0) && !fitting.contains($0) }),
        ]
    }

    private var pickerTags: [CarrierID: CarrierPickerView.Tag] {
        switch recognitionStatus {
        case .recognized(let carrier):
            return [carrier: .init(label: localizer.text("picker.tag.found"), found: true)]
        case .several(let carriers):
            return Dictionary(uniqueKeysWithValues: carriers.map { ($0, .init(label: localizer.text("picker.tag.knows"), found: true)) })
        default:
            return [:]
        }
    }

    private func carrierNames(_ carriers: [CarrierID]) -> String {
        let formatter = ListFormatter()
        formatter.locale = localizer.language.locale
        let names = carriers.map { catalog.info(for: $0, language: localizer.language).displayName }
        return formatter.string(from: names) ?? names.joined(separator: ", ")
    }

    private var shippingConfirmed: Bool {
        amazonNumber && currentVerification?.carrier == .amazonShipping && (currentVerification?.amazonShippingStatus == .available || currentVerification?.amazonShippingStatus == .expired)
    }

    private var accountRequired: Bool {
        amazonNumber ? !shippingConfirmed : catalog.requiresAmazonAccount(carrierOverride ?? parsed.carrier)
    }

    private var resolvedCarrier: CarrierID {
        if amazonNumber { return shippingConfirmed ? .amazonShipping : .amazonLogistics }
        if let carrierOverride { return carrierOverride }
        if case .recognized(let carrier) = recognitionStatus { return carrier }
        return parsed.carrier
    }

    /// Amazon numbers must be verified before they can be added.
    private var lookupTrackingNumber: String? {
        guard !store.isDemo, amazonNumber else { return nil }
        return normalizedNumber
    }

    /// For a carrier picked by hand that the number cannot belong to (a forwarder
    /// such as Asendia for a DPD number), the carrier recognition found stays a hint.
    private var knownElsewhere: CarrierID? {
        guard let carrierOverride, case .recognized(let carrier) = recognitionStatus,
              carrier != carrierOverride else { return nil }
        return carrier
    }

    /// The carrier the settled check suggests for a number nobody knew, which cannot be asked without the delivery
    /// postcode: its postcode field shows while the carrier is left automatic.
    private var suggestion: SuggestedPostcodeCarrier? {
        SuggestedPostcodeCarrier(picked: carrierOverride, shape: parsed.carrier, recognition: recognition, number: normalizedNumber,
                                 spelling: typedSpelling, country: sentCountry, applies: recognizable, catalog: catalog)
    }

    /// The carrier the fields are for: the suggested one, otherwise the parcel's.
    private var inputCarrier: CarrierID { suggestion?.carrier ?? resolvedCarrier }

    /// The fields to show, before a country shapes the postcode's: the suggested carrier's postcode alone, otherwise
    /// the carrier's own.
    private var carrierRequirements: [CarrierRequirement] {
        suggestion.map { [$0.requirement] } ?? catalog.requirements(for: resolvedCarrier, trackingNumber: parsed.trackingNumber)
    }

    /// The carrier's fields, the postcode's shaped for the country beside it.
    private var requirements: [CarrierRequirement] {
        let country = postcodeCountry
        return carrierRequirements.map { catalog.postcodeRequirement($0, for: inputCarrier, country: country) }
    }

    private var trackingURLRequirement: CarrierRequirement? {
        requirements.first(where: { $0.field == .trackingURL })
    }

    /// The carrier's postcode field, before the country beside it shapes it.
    private var carrierPostcodeRequirement: CarrierRequirement? {
        carrierRequirements.first(where: { $0.field == .dpdPostcode })
    }

    /// The country the postcode is from: the one chosen for this carrier while its field takes it, otherwise where
    /// the field starts.
    private var postcodeCountry: String? {
        carrierPostcodeRequirement.flatMap { postcodes.country(inputCarrier, requirement: $0, catalog: catalog) }
    }

    /// The postcode's country, offered beside the field when it takes more than one.
    private var offeredPostcodeCountry: String? {
        guard let requirement = carrierPostcodeRequirement,
              catalog.postcodeCountries(for: inputCarrier, requirement: requirement).count > 1 else { return nil }
        return postcodeCountry
    }

    private var postcodeRequirement: CarrierRequirement? {
        requirements.first(where: { $0.field == .dpdPostcode })
    }

    private var phoneDigitsRequirement: CarrierRequirement? {
        requirements.first(where: { $0.field == .phoneDigits })
    }

    private var needsRequiredDetails: Bool {
        (trackingURLRequirement != nil && parsed.trackingURL == nil)
            || postcodeRequirement != nil
            || phoneDigitsRequirement != nil
    }

    private var canSave: Bool {
        guard !accountRequired, !pickupCode, !placeholderNumber, !parsed.trackingNumber.isEmpty else { return false }
        if let number = lookupTrackingNumber, verifiedCarrier?.trackingNumber != number { return false }
        for requirement in requirements {
            switch requirement.field {
            case .trackingURL:
                let value = requirement.normalizedValue(parsed.trackingURL ?? trackingURL)
                guard requirement.accepts(value),
                      let url = URL(string: value),
                      url.scheme == "https",
                      url.host != nil else { return false }
            case .dpdPostcode:
                // An optional postcode, a suggested carrier's too, holds the parcel back only while what is typed isn't one.
                guard requirement.isSatisfied(by: deliveryPostcode) else { return false }
            case .phoneDigits:
                guard requirement.isSatisfied(by: phoneDigits) else { return false }
            }
        }
        return true
    }

    /// The postcode in the field: the current carrier's own, or the suggested one's, the one sent with the parcel.
    private var deliveryPostcode: String {
        get { postcodes[inputCarrier] }
        nonmutating set { postcodes[inputCarrier] = newValue }
    }

    /// A required postcode starts from the last one that fits; an optional one is
    /// only offered (see `requiredDetails`).
    private func prepareRequiredDetails(for carrier: CarrierID) {
        postcodes.prepare(carrier, requirement: carrierPostcodeRequirement, among: store.givenPostcodes, catalog: catalog)
    }

    private func paste() {
        DeliveryAnalytics.shared.action("parcel-paste")
        guard let value = Clipboard.string?.trimmingCharacters(
            in: .whitespacesAndNewlines
        ), !value.isEmpty else {
            errorMessage = localizer.text("add.pasteFailed")
            lookupsSpent = false
            return
        }
        withAnimation(reduceMotion ? nil : .snappy(duration: 0.34)) {
            errorMessage = nil
            trackingInput = value
        }
        focusedField = .tracking
        recognition.settledNumber = Self.trackingNumber(in: value)
    }

    /// A number shared, linked or asked for while Add is open is another parcel: nothing typed for the one
    /// before, its name, link, postcode, phone digits or carrier, goes with it.
    private func fill(_ draft: SharedParcelDraft) {
        withAnimation(reduceMotion ? nil : .snappy(duration: 0.34)) {
            errorMessage = nil
            duplicateParcelID = nil
            trackingInput = draft.trackingInput
            label = draft.label
            trackingURL = ""
            carrierOverride = nil
            asTyped = nil
            pick = nil
            pickedCountry = nil
            postcodes = CarrierPostcodes()
            phoneInput = ParcelPhoneInput()
        }
        recognition.settledNumber = Self.trackingNumber(in: draft.trackingInput)
        prepareRequiredDetails(for: inputCarrier)
    }

    /// Asks the carriers about the number in the field again as from another country, for this number only.
    private func pickCountry(_ country: String) {
        pickedCountry = (normalizedNumber, country)
    }

    private func selectCarrier(_ carrier: CarrierID?) {
        carrierOverride = carrier
        focusedField = nil
        errorMessage = nil
        duplicateParcelID = nil
    }

    /// Chooses one of several numbers in the text: the carriers are asked about it at once, and the keyboard
    /// leaves the list and the button in view.
    private func choose(_ number: NumberInText) {
        withAnimation(reduceMotion ? nil : .snappy(duration: 0.2)) {
            pick = number.normalized
        }
        focusedField = nil
        carrierOverride = nil
        errorMessage = nil
        duplicateParcelID = nil
        recognition.settledNumber = number.normalized
    }

    /// Puts the number the check digit suggests in the field, as a paste would.
    private func use(_ suggestion: String) {
        withAnimation(reduceMotion ? nil : .snappy(duration: 0.34)) {
            trackingInput = suggestion
        }
        recognition.settledNumber = Self.trackingNumber(in: suggestion)
    }

    private func submitTracking() {
        if let requirement = trackingURLRequirement,
           parsed.trackingURL == nil,
           !requirement.accepts(trackingURL) {
            focusedField = .trackingURL
        } else if let requirement = postcodeRequirement,
                  !requirement.accepts(deliveryPostcode) {
            focusedField = .deliveryPostcode
        } else if let requirement = phoneDigitsRequirement,
                  !requirement.accepts(phoneDigits) {
            focusedField = .phoneDigits
        } else {
            focusedField = .label
        }
    }

    private func save() {
        guard canSave, !saving else { return }
        focusedField = nil
        saving = true
        errorMessage = nil
        duplicateParcelID = nil

        Task {
            do {
                let postcode = postcodeRequirement?.normalizedValue(deliveryPostcode)
                // A suggested carrier goes only with its postcode; without one the parcel is added as it would be.
                let added: SuggestedPostcodeCarrier.Added = suggestion?.added(
                    postcode: deliveryPostcode, country: postcodeCountry, otherwise: resolvedCarrier, catalog: catalog
                ) ?? .init(carrier: resolvedCarrier, postcode: postcode,
                           country: catalog.sentPostcodeCountry(postcodeCountry, postcode: postcode, for: resolvedCarrier))
                let parcel = try await store.add(
                    trackingNumber: parsed.trackingNumber,
                    label: label,
                    carrier: added.carrier,
                    trackingURL: trackingURLRequirement != nil
                        ? (parsed.trackingURL ?? trackingURL) : nil,
                    dpdPostcode: added.postcode,
                    dpdPostcodeCountry: added.country,
                    // Only while the carrier asks for them.
                    phoneDigits: phoneDigitsRequirement.flatMap { $0.normalizedValue(phoneDigits).nonEmpty },
                    lookupCountry: pickedLookupCountry
                )
                onAdded(parcel.id)
                dismiss()
            } catch where error.isCancellation {
                saving = false
            } catch {
                if let apiError = error as? DeliveryAPIError,
                   case .duplicateTracking(let packageID) = apiError {
                    duplicateParcelID = packageID
                }
                errorMessage = localizer.errorMessage(error)
                lookupsSpent = error is DeviceLookupsSpent
                saving = false
            }
        }
    }
}

/// The postcode a field was filled with from those given before. While the field still holds
/// it, it can be forgotten; then no other fills the field until its details are prepared again.
struct PostcodeFill: Equatable {
    private(set) var postcode: String?
    private(set) var forgotten = false

    /// What may fill the field: nothing just after a postcode was forgotten in it.
    func offering<Suggestion>(_ suggestion: Suggestion?) -> Suggestion? { forgotten ? nil : suggestion }

    mutating func fill(_ suggestion: String) -> String {
        postcode = suggestion
        return suggestion
    }

    func holds(_ field: String) -> Bool { !field.isEmpty && postcode == field }

    /// The postcode to forget, when the field still holds it.
    mutating func forget(_ field: String) -> String? {
        guard holds(field) else { return nil }
        defer { postcode = nil; forgotten = true }
        return postcode
    }

    /// Another carrier's details: what was given before may fill the field again.
    mutating func prepare() { forgotten = false }
}

/// The delivery postcode the Add sheet holds for each carrier, and the country chosen beside it. One typed,
/// accepted, filled or chosen for a carrier stays with it: another carrier's field shows its own, and switching
/// back shows the first one's again. The Change carrier sheet starts one afresh for each carrier picked.
/// `given` is the postcodes given before, from which the last one that fits is suggested, the carrier's own first.
struct CarrierPostcodes {
    /// What each carrier's field holds; nothing until a postcode is typed, accepted or filled in it.
    private var values: [CarrierID: String] = [:]
    /// What each carrier's field was filled with from the postcodes given before.
    private var fills: [CarrierID: PostcodeFill] = [:]
    /// The country chosen beside each carrier's field.
    private var countries: [CarrierID: String] = [:]
    /// The country of the postcode each carrier's field was filled with, where the field reads it.
    private var filledCountries: [CarrierID: String] = [:]

    init() {}

    /// The parcel's own postcode in its carrier's field, which nothing given before replaces.
    init(parcel: Parcel) {
        if let postcode = parcel.dpdPostcode?.nonEmpty { values[parcel.carrier] = postcode }
    }

    /// What `carrier`'s field holds. Writing what it already shows changes nothing, so a field never given
    /// a postcode, written back as it is shown, can still be filled when it is prepared.
    subscript(carrier: CarrierID) -> String {
        get { values[carrier] ?? "" }
        set { if newValue != self[carrier] { values[carrier] = newValue } }
    }

    /// The carrier's field as it is shown: a required postcode starts from the last one given that fits, with its
    /// country, unless the field already holds one from earlier in the sheet; an optional one is only offered.
    /// `requirement` is the field before a country shapes it.
    mutating func prepare(_ carrier: CarrierID, requirement: CarrierRequirement?, among given: [GivenPostcode],
                          catalog: CarrierCatalog = .shared) {
        fills[carrier]?.prepare()
        guard let requirement, !requirement.isOptional, values[carrier] == nil,
              let suggestion = suggestion(carrier, requirement: requirement, among: given, catalog: catalog) else { return }
        use(suggestion.postcode, for: carrier)
        filledCountries[carrier] = suggestion.country
    }

    /// The postcode offered for an optional field while it is empty, with the country it was given from when the field
    /// reads it: nothing just after a postcode was forgotten in it.
    func offering(_ carrier: CarrierID, requirement: CarrierRequirement, among given: [GivenPostcode],
                  catalog: CarrierCatalog = .shared) -> GivenPostcode? {
        guard requirement.isOptional, self[carrier].isEmpty else { return nil }
        return (fills[carrier] ?? PostcodeFill()).offering(suggestion(carrier, requirement: requirement, among: given, catalog: catalog))
    }

    /// The postcode given before that fits the field, from the country chosen beside it once one is.
    private func suggestion(_ carrier: CarrierID, requirement: CarrierRequirement, among given: [GivenPostcode],
                            catalog: CarrierCatalog) -> GivenPostcode? {
        let chosen = countries[carrier].flatMap { catalog.postcodeCountries(for: carrier, requirement: requirement).contains($0) ? $0 : nil }
        return catalog.rememberedPostcode(for: carrier, requirement: requirement, among: given, chosen: chosen)
    }

    /// Fills the carrier's field with a postcode given before; the country it was given from, when the field reads
    /// it, is chosen beside it, as the reader took it.
    mutating func use(_ postcode: String, for carrier: CarrierID, country: String? = nil) {
        values[carrier] = fills[carrier, default: PostcodeFill()].fill(postcode)
        if let country { countries[carrier] = country }
    }

    /// The carrier's field still holds the postcode it was filled with.
    func holdsFill(_ carrier: CarrierID) -> Bool { fills[carrier]?.holds(self[carrier]) == true }

    /// Empties the carrier's field and returns the postcode it was filled with, to forget. Every other field
    /// holding it, typed or filled, its capitals and spaces aside as this iPhone forgets it, empties too and
    /// fills again from what is left when its carrier comes up.
    mutating func forget(_ carrier: CarrierID) -> String? {
        let field = self[carrier]
        guard let postcode = fills[carrier]?.forget(field) else { return nil }
        values[carrier] = nil
        let forgotten = PostcodeMemory.bare(postcode)
        for (other, value) in values where PostcodeMemory.bare(value) == forgotten {
            values[other] = nil
            fills[other] = nil
        }
        return postcode
    }

    /// The country beside the carrier's field: the one chosen for it while the field takes it, otherwise the one the
    /// postcode it was filled with was given from while it holds it, otherwise where the field starts (see
    /// `CarrierCatalog.initialPostcodeCountry`).
    func country(_ carrier: CarrierID, requirement: CarrierRequirement, parcel: Parcel? = nil,
                 region: String? = CarrierCatalog.deviceCountry(), catalog: CarrierCatalog = .shared) -> String? {
        let takes = catalog.postcodeCountries(for: carrier, requirement: requirement)
        if let chosen = countries[carrier], takes.contains(chosen) { return chosen }
        if holdsFill(carrier), let filled = filledCountries[carrier], takes.contains(filled) { return filled }
        return catalog.initialPostcodeCountry(for: carrier, requirement: requirement, parcel: parcel, region: region)
    }

    /// Chooses the country beside the carrier's field; the other carriers' stay as they are.
    mutating func choose(_ country: String, for carrier: CarrierID) { countries[carrier] = country }
}

/// The carrier the carrier check suggests for a number nobody knew: one that serves the reader's country and takes the
/// delivery postcode and nothing else for this number (Mondial Relay's 8-digit numbers), so nobody could ask it. The add
/// sheet shows its postcode field, optional, and the parcel goes to it only with a postcode the field takes, as if it
/// had been picked and its field filled; otherwise the parcel is added as it would be without. Mirrors `suggested`
/// and `lookupInput` in `src/peek/lookup/machine.ts`.
struct SuggestedPostcodeCarrier: Equatable {
    /// The carrier, and the postcode and its country sent with the parcel.
    struct Added: Equatable {
        var carrier: CarrierID
        var postcode: String?
        var country: String?
    }

    let carrier: CarrierID
    /// The carrier's postcode field for the number, optional, before a country shapes it.
    let requirement: CarrierRequirement

    /// Only while the carrier is left automatic (`picked` nil) for a number whose shape names none (`shape`), once the
    /// check has settled without a carrier: nobody knew the number, nothing could be asked, or the check failed.
    /// `number`, `spelling`, `country` and `applies` are as `CarrierRecognition.status` takes them.
    init?(picked: CarrierID?, shape: CarrierID, recognition: CarrierRecognition, number: String, spelling: String? = nil,
          country: String? = nil, applies: Bool, catalog: CarrierCatalog = .shared) {
        guard picked == nil, shape == .unknown, let answer = recognition.answer, answer.trackingNumber == number,
              let suggested = answer.inputSuggested, suggested.field == .dpdPostcode else { return nil }
        switch recognition.status(for: number, spelling: spelling, country: country, applies: applies) {
        case .unasked, .notFound, .failed: break
        case .idle, .asking, .recognized, .several: return nil
        }
        guard var field = catalog.requirements(for: suggested.carrier, trackingNumber: number)
            .first(where: { $0.field == .dpdPostcode }) else { return nil }
        field.optional = true
        carrier = suggested.carrier
        requirement = field
    }

    /// What the parcel is added with: this carrier, the postcode as its field takes it from `country` and that country
    /// as it is sent, once the field takes what was typed; otherwise `otherwise` alone.
    func added(postcode typed: String, country: String?, otherwise: CarrierID, catalog: CarrierCatalog = .shared) -> Added {
        let field = catalog.postcodeRequirement(requirement, for: carrier, country: country)
        guard field.accepts(typed) else { return Added(carrier: otherwise) }
        let postcode = field.normalizedValue(typed)
        return Added(carrier: carrier, postcode: postcode,
                     country: catalog.sentPostcodeCountry(country, postcode: postcode, for: carrier))
    }
}

/// The country a delivery postcode is from, beside its field: its flag and code, opening on the countries the
/// field takes, the carrier's own first.
struct PostcodeCountryMenu: View, Equatable {
    let carrier: CarrierID
    let requirement: CarrierRequirement
    let country: String
    /// Inside the label, so that all of it opens the menu.
    var inset = EdgeInsets()
    let choose: (String) -> Void

    @EnvironmentObject private var localizer: Localizer

    /// The same carrier, field and country make the same menu, whatever `choose`. Used with `.equatable()`, the menu is
    /// left as it is when its screen redraws, as when a check of the parcel comes back: built again, an open menu would
    /// scroll back to its top under the reader's finger.
    static func == (lhs: Self, rhs: Self) -> Bool {
        lhs.carrier == rhs.carrier && lhs.requirement == rhs.requirement && lhs.country == rhs.country && lhs.inset == rhs.inset
    }

    var body: some View {
        let choices = CarrierCatalog.shared.postcodeCountryChoices(for: carrier, requirement: requirement, language: localizer.language)
        let label = localizer.text("add.requirement.postcodeCountry")
        let selection = Binding(get: { country }, set: choose)
        Menu {
            // Two lists, so that a line sets the carrier's own countries apart from the others.
            if !choices.own.isEmpty {
                Picker(label, selection: selection) { options(choices.own) }
            }
            if !choices.others.isEmpty {
                Picker(label, selection: selection) { options(choices.others) }
            }
        } label: {
            HStack(spacing: 4) {
                Text(TrackingLocation.flag(country))
                Text(country).fontWeight(.medium)
                Image(systemName: "chevron.up.chevron.down")
                    .font(.caption2.weight(.semibold))
                    .foregroundStyle(Brand.secondaryText)
            }
            .foregroundStyle(Brand.ink)
            .padding(inset)
            .contentShape(Rectangle())
        }
        .tint(Brand.ink)
        #if os(macOS)
        .menuStyle(.button)
        .buttonStyle(.plain)
        .menuIndicator(.hidden)
        #endif
        .accessibilityLabel(localizer.text("add.requirement.postcodeCountry"))
        .accessibilityValue(TrackingLocation.countryName(country, language: localizer.language))
    }

    private func options(_ countries: [String]) -> some View {
        ForEach(countries, id: \.self) { code in
            Text(TrackingLocation.flag(code) + " " + TrackingLocation.countryName(code, language: localizer.language)).tag(code)
        }
    }
}

/// The country the carriers are asked for, beside the words of the Add sheet's carrier line that name it: its flag,
/// opening on the device's country, then every other by name. Picking one asks again, for this number only.
struct LookupCountryMenu: View, Equatable {
    let country: String
    /// The device's country, first in the list.
    let home: String?
    let choose: (String) -> Void

    @EnvironmentObject private var localizer: Localizer

    /// The same country and home make the same menu, whatever `choose`. Used with `.equatable()`, the menu is left as it
    /// is when the sheet redraws, as when the carriers' answer comes in: built again, an open menu would scroll back to
    /// its top under the reader's finger.
    static func == (lhs: Self, rhs: Self) -> Bool {
        lhs.country == rhs.country && lhs.home == rhs.home
    }

    var body: some View {
        let label = localizer.text("add.lookupCountry")
        let selection = Binding(get: { country }, set: choose)
        Menu {
            // Two lists, so that a line sets the device's country apart from the others.
            if let home {
                Picker(label, selection: selection) { option(home) }
            }
            Picker(label, selection: selection) {
                ForEach(CarrierCatalog.countriesByName(in: localizer.language).filter { $0 != home }, id: \.self) { option($0) }
            }
        } label: {
            // A larger target than the flag, centred on the place kept for it.
            Self.label(country)
                .padding(.horizontal, 6)
                .padding(.vertical, 8)
                .contentShape(Rectangle())
        }
        .tint(Brand.secondaryText)
        #if os(macOS)
        .menuStyle(.button)
        .buttonStyle(.plain)
        .menuIndicator(.hidden)
        #endif
        .accessibilityLabel(localizer.text("add.lookupCountry"))
        .accessibilityValue(TrackingLocation.countryName(country, language: localizer.language))
    }

    /// The country's flag and the sign that it opens a list, as small and quiet as the words beside it.
    static func label(_ country: String) -> some View {
        HStack(spacing: 2) {
            Text(TrackingLocation.flag(country))
            Image(systemName: "chevron.up.chevron.down")
                .font(.caption2.weight(.semibold))
        }
        .font(.caption)
        .foregroundStyle(Brand.secondaryText)
    }

    private func option(_ code: String) -> some View {
        Text(TrackingLocation.flag(code) + " " + TrackingLocation.countryName(code, language: localizer.language)).tag(code)
    }
}

/// Where the carrier line keeps room for the country's menu, which is drawn over the line rather than inside its button.
private struct LookupCountryPlace: PreferenceKey {
    static var defaultValue: Anchor<CGRect>? { nil }

    static func reduce(value: inout Anchor<CGRect>?, nextValue: () -> Anchor<CGRect>?) {
        value = value ?? nextValue()
    }
}

/// Applied outside the card's swipe clipping so the little parcels can leave its edge.
struct ParcelArrivalCelebration: ViewModifier {
    let active: Bool
    var stubInset: CGFloat = 30
    let onFinished: () -> Void
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @State private var progress = 0.0

    func body(content: Content) -> some View {
        content
            .overlay {
                if active {
                    ParcelAddedBurst(stubInset: stubInset, onFinished: onFinished)
                }
            }
            .modifier(ParcelArrivalEffect(progress: progress, reduced: reduceMotion))
            .onChange(of: active, initial: true) { _, active in
                if active {
                    withAnimation(.linear(duration: reduceMotion ? 0.75 : 0.48)) { progress = 1 }
                } else {
                    var transaction = Transaction(animation: nil)
                    transaction.disablesAnimations = true
                    withTransaction(transaction) { progress = 0 }
                }
            }
            .onDisappear { if active { onFinished() } }
    }
}

private struct ParcelArrivalEffect: ViewModifier, Animatable {
    var progress: Double
    let reduced: Bool
    var animatableData: Double {
        get { progress }
        set { progress = newValue }
    }

    func body(content: Content) -> some View {
        let wobble = sin(progress * .pi * 6) * (1 - progress)
        let pulse = sin(progress * .pi)
        content
            .offset(x: reduced ? 0 : wobble * 3)
            .rotationEffect(.degrees(reduced ? 0 : wobble * 1.1))
            .scaleEffect(reduced ? 1 : 1 + pulse * 0.012)
            .brightness(reduced ? pulse * 0.08 : 0)
    }
}

/// Small Pips fly, unevenly, from the new card's stamp; they follow its position while scrolling.
struct ParcelAddedBurst: View {
    let stubInset: CGFloat
    let onFinished: () -> Void
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    @State private var started = Date()

    // Horizontal spread, upward impulse, rotation, size, launch delay.
    private static let parcels: [(Double, Double, Double, Double, Double)] = [
        (-1, 90, -80, 19, 0.02), (-0.7, 145, 65, 24, 0),
        (-0.35, 115, -100, 21, 0.05), (0, 155, 85, 25, 0.03),
        (0.35, 120, -55, 20, 0.06), (0.6, 95, 100, 22, 0.01),
    ]

    var body: some View {
        GeometryReader { geometry in
            if !reduceMotion {
                TimelineView(.animation) { timeline in
                    let elapsed = max(0, timeline.date.timeIntervalSince(started))
                    let origin = CGPoint(x: geometry.size.width - stubInset, y: geometry.size.height / 2)
                    let spread = min(geometry.size.width * 0.25, 80)
                    ForEach(Self.parcels.indices, id: \.self) { index in
                        particle(index, elapsed: elapsed, origin: origin, spread: spread)
                    }
                }
            }
        }
        .allowsHitTesting(false)
        .accessibilityHidden(true)
        .task {
            do { try await Task.sleep(for: .seconds(reduceMotion ? 0.75 : 0.95)) }
            catch { return }
            onFinished()
        }
        .onChange(of: reduceMotion) { _, _ in onFinished() }
    }

    private func particle(_ index: Int, elapsed: TimeInterval, origin: CGPoint, spread: CGFloat) -> some View {
        let parcel = Self.parcels[index]
        let progress = max(0, min(1, (elapsed - parcel.4) / 0.8))
        let rotation = parcel.2 * progress + sin(progress * .pi * 2) * 8
        let scale = min(1, progress / 0.12) * (1 - progress * 0.25)
        let opacity = min(1, progress / 0.05) * min(1, (1 - progress) / 0.3)
        let x = origin.x + CGFloat(parcel.0 * (1 - pow(1 - progress, 2))) * spread
        let y = origin.y + CGFloat(-parcel.1 * progress + 120 * progress * progress)
        return SmallPip().frame(width: CGFloat(parcel.3) * 1.25)
            .rotationEffect(.degrees(rotation)).scaleEffect(scale).opacity(opacity)
            .position(x: x, y: y)
    }
}

/// Whether this device scans a label's barcode. The Mac has no scanner.
enum LabelScanner {
    @MainActor static var isAvailable: Bool {
        #if os(iOS)
        DataScannerViewController.isSupported && DataScannerViewController.isAvailable
        #else
        false
        #endif
    }
}

#if os(iOS)
struct TrackingScannerView: View {
    let onScan: (String) -> Void
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dismiss) private var dismiss

    var body: some View {
        NavigationStack {
            TrackingDataScanner(onScan: onScan)
                .ignoresSafeArea(edges: .bottom)
                .navigationTitle(localizer.text("add.scanTitle"))
                .navigationBarTitleDisplayMode(.inline)
                .toolbar {
                    ToolbarItem(placement: .cancellationAction) {
                        Button(localizer.text("common.cancel")) { dismiss() }
                    }
                }
        }
        .accessibilityAction(.escape) { dismiss() }
    }
}

private struct TrackingDataScanner: UIViewControllerRepresentable {
    let onScan: (String) -> Void

    func makeCoordinator() -> Coordinator { Coordinator(onScan: onScan) }

    func makeUIViewController(context: Context) -> DataScannerViewController {
        let scanner = DataScannerViewController(
            recognizedDataTypes: [.barcode()],
            qualityLevel: .balanced,
            recognizesMultipleItems: false,
            isHighFrameRateTrackingEnabled: true,
            isPinchToZoomEnabled: true,
            isGuidanceEnabled: true,
            isHighlightingEnabled: true
        )
        scanner.delegate = context.coordinator
        Task { @MainActor in try? scanner.startScanning() }
        return scanner
    }

    func updateUIViewController(_ uiViewController: DataScannerViewController, context: Context) {}

    static func dismantleUIViewController(
        _ uiViewController: DataScannerViewController,
        coordinator: Coordinator
    ) {
        uiViewController.stopScanning()
    }

    @MainActor
    final class Coordinator: NSObject, DataScannerViewControllerDelegate {
        private let onScan: (String) -> Void
        private var delivered = false

        init(onScan: @escaping (String) -> Void) { self.onScan = onScan }

        func dataScanner(
            _ dataScanner: DataScannerViewController,
            didAdd addedItems: [RecognizedItem],
            allItems: [RecognizedItem]
        ) {
            guard !delivered else { return }
            for item in addedItems {
                guard case .barcode(let barcode) = item,
                      let value = barcode.payloadStringValue?.trimmingCharacters(
                        in: .whitespacesAndNewlines
                      ), !value.isEmpty else { continue }
                delivered = true
                onScan(value)
                return
            }
        }
    }
}
#endif

private extension View {
    /// The choices alone, under their section's title, as iOS shows an inline picker. A Mac would title them again.
    @ViewBuilder func choicesOnly() -> some View {
        #if os(macOS)
        labelsHidden()
        #else
        self
        #endif
    }
}

struct ParcelFilterView: View {
    @Binding var status: ParcelStatusFilter
    @Binding var carrier: CarrierID?
    @Binding var sort: ParcelSort
    let carriers: [CarrierID]
    @EnvironmentObject private var localizer: Localizer
    @Environment(\.dismiss) private var dismiss
    @ObservedObject private var catalog = CarrierCatalog.shared

    var body: some View {
        NavigationStack {
            Form {
                Section {
                    Picker(localizer.text("view.status"), selection: $status) {
                        ForEach(ParcelStatusFilter.allCases) { value in
                            Text(localizer.text(value.localizationKey)).tag(value)
                        }
                    }
                    .pickerStyle(.inline)
                    .choicesOnly()
                } header: { Text(localizer.text("view.status")).foregroundStyle(Brand.secondaryText) }
                Section {
                    Picker(localizer.text("view.carrier"), selection: $carrier) {
                        Text(localizer.text("view.allCarriers")).tag(Optional<CarrierID>.none)
                        ForEach(carriers) { value in
                            Text(catalog.info(for: value, language: localizer.language).displayName).tag(Optional(value))
                        }
                    }
                } header: { Text(localizer.text("view.carrier")).foregroundStyle(Brand.secondaryText) }
                Section {
                    Picker(localizer.text("view.sort"), selection: $sort) {
                        ForEach(ParcelSort.allCases) { value in
                            Text(localizer.text(value.localizationKey)).tag(value)
                        }
                    }
                    .pickerStyle(.inline)
                    .choicesOnly()
                } header: { Text(localizer.text("view.sort")).foregroundStyle(Brand.secondaryText) }
                Section {
                    Button(localizer.text("view.clear"), role: .destructive) {
                        status = .all
                        carrier = nil
                        sort = .standard
                    }
                }
            }
            .navigationTitle(localizer.text("view.sortFilter"))
            .navigationBarTitleDisplayMode(.inline)
            #if os(macOS)
            // Sections as iOS draws them, rather than a label beside each picker, on the app's paper rather than the
            // popover's glass; a popover closes on a click outside it.
            .formStyle(.grouped)
            .presentationBackground(Brand.sheet)
            #else
            .toolbar {
                ToolbarItem(placement: .confirmationAction) {
                    Button(localizer.text("native.done")) { dismiss() }.fontWeight(.semibold)
                }
            }
            #endif
        }
        .presentationDetents([.medium, .large])
        .presentationDragIndicator(.visible)
    }
}
