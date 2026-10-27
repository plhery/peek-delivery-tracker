import SwiftUI
#if os(iOS)
import UIKit
#endif
import VisionKit

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
    @State private var deliveryPostcode = ""
    @State private var postcodeFill = PostcodeFill()
    /// The delivery country chosen beside the postcode, kept, as the postcode is, for each carrier whose field takes it.
    @State private var chosenPostcodeCountry: String?
    @State private var showingScanner = false
    @State private var showingCarrierPicker = false
    @State private var didFocusTracking = false
    @State private var saving = false
    @State private var errorMessage: String?
    @State private var duplicateParcelID: UUID?
    @State private var carrierOverride: CarrierID?
    @State private var lookupAttempt = 0
    @State private var verifiedCarrier: CarrierDetectionResponse?
    @State private var recognition: CarrierRecognition
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
            .background(Brand.background)
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
            .onChange(of: resolvedCarrier, initial: true) { _, carrier in
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
            .task(id: "\(lookupTrackingNumber ?? ""):\(lookupAttempt)") {
                guard let number = lookupTrackingNumber else { return }
                do {
                    try await Task.sleep(for: .milliseconds(350))
                    let result = try await store.detectCarrier(trackingNumber: number)
                    guard !Task.isCancelled else { return }
                    guard result.trackingNumber == number else { throw DeliveryAPIError.invalidResponse }
                    verifiedCarrier = result
                } catch {
                    guard !Task.isCancelled else { return }
                    verifiedCarrier = CarrierDetectionResponse(trackingNumber: number, amazonShippingStatus: .unavailable, carrier: .amazonLogistics)
                }
            }
            .task(id: recognitionRequest) {
                guard let number = recognitionRequest else { return }
                recognition.failedNumber = nil
                recognition.answer = nil
                do {
                    let result = try await store.detectCarrier(trackingNumber: number)
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
                    .background(Brand.cream, in: Circle())
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
                    .foregroundStyle(.secondary)
            }
            TextField(localizer.text("add.contentsPlaceholder"), text: $label)
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
                .background(Brand.paper, in: RoundedRectangle(cornerRadius: 14))
                .overlay {
                    RoundedRectangle(cornerRadius: 14)
                        .strokeBorder(focusedField == .label ? ExperimentalPalette.ochre : Brand.separator.opacity(0.3), lineWidth: 1)
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
            TextField(
                localizer.text("add.trackingPlaceholder"),
                text: $trackingInput,
                axis: .vertical
            )
            .drawnField()
            .font(.body)
            .lineLimit(2...4)
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
            .background(Brand.paper, in: RoundedRectangle(cornerRadius: 14))
            .overlay {
                RoundedRectangle(cornerRadius: 14)
                    .strokeBorder(focusedField == .tracking ? ExperimentalPalette.ochre : Brand.separator.opacity(0.3), lineWidth: 1)
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
                // Only Amazon has an orders page to link to: for Shein, Shopee and Temu the note says where to tap instead.
                if order == .amazon {
                    Link(localizer.text("add.openAmazonOrders"), destination: CarrierCatalog.amazonOrdersURL(""))
                        .font(.subheadline)
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
        .foregroundStyle(.secondary)
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
                    .foregroundStyle(.secondary)
                    .fixedSize(horizontal: false, vertical: true)
            }
            if let known = knownElsewhere {
                let name = catalog.info(for: known, language: localizer.language).displayName
                Text(localizer.text("add.carrierKnownElsewhere", ["carrier": name]))
                    .font(.caption)
                    .foregroundStyle(.secondary)
                    .fixedSize(horizontal: false, vertical: true)
                Button(localizer.text("add.useCarrier", ["carrier": name])) {
                    carrierOverride = known
                }
                .font(.subheadline)
            }
            if amazonNumber && currentVerification?.amazonShippingStatus == .unavailable {
                Text(localizer.text("add.amazonCheckUnavailable")).font(.caption).foregroundStyle(.secondary)
                Button(localizer.text("add.amazonRetry")) {
                    verifiedCarrier = nil
                    lookupAttempt += 1
                }.font(.subheadline)
            }
            if accountRequired {
                Link(localizer.text("add.openAmazonOrders"), destination: CarrierCatalog.amazonOrdersURL(parsed.trackingNumber))
                    .font(.subheadline)
            }
        }
        .foregroundStyle(Brand.ink)
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
                let question = Text(localizer.text("door.several.question")).foregroundStyle(.secondary)
                let signIn = Button(localizer.text("door.several.signIn")) {
                    dismiss()
                    session.showSignIn()
                }
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
            .background(chosen ? ExperimentalPalette.ochreSurface : Brand.paper, in: shape)
            .overlay(shape.strokeBorder(chosen ? ExperimentalPalette.ochre : Brand.separator.opacity(0.3), lineWidth: chosen ? 1.5 : 1))
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
    private var carrierLine: some View {
        let carrier = lineCarrier
        let name = carrier.map { catalog.info(for: $0, language: localizer.language).displayName }
            ?? localizer.text("add.detect")
        let detail = lineDetail
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
                        lineDetailText(detail)
                        lineAction
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
        .accessibilityIdentifier("addParcel.carrier")
        .disabled(amazonNumber)
    }

    /// Says that the line opens the picker. An Amazon number keeps its carrier.
    @ViewBuilder private var lineAction: some View {
        if !amazonNumber {
            Text(localizer.text(choosingCarrier ? "add.line.choose" : "add.line.change"))
                .font(.caption)
                .foregroundStyle(.secondary)
                .underline()
                .fixedSize(horizontal: false, vertical: true)
        }
    }

    @ViewBuilder
    private func lineDetailText(_ detail: String?) -> some View {
        if let detail {
            Text(detail)
                .font(.caption)
                .foregroundStyle(choosingCarrier || refused ? ExperimentalPalette.ochre : Color.secondary)
                .fixedSize(horizontal: false, vertical: true)
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
                    TextField(requirement.placeholder ?? "https://…", text: $trackingURL)
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
                VStack(alignment: .leading, spacing: 10) {
                    requirementField(
                        title: localizer.text("add.requirement.dpdPostcode"),
                        help: requirement.isOptional
                            ? localizer.text("add.requirement.dpdPostcodeOptionalHelp", [
                                "carrier": catalog.info(for: resolvedCarrier, language: localizer.language).displayName,
                            ])
                            : localizer.text("add.requirement.dpdPostcodeHelp"),
                        optional: requirement.isOptional
                    ) {
                        let example = catalog.postcodeExample(for: resolvedCarrier, requirement: requirement, region: postcodeCountry)
                        HStack(spacing: 0) {
                            if let country = offeredPostcodeCountry, let carrierRequirement = carrierPostcodeRequirement {
                                PostcodeCountryMenu(carrier: resolvedCarrier, requirement: carrierRequirement, country: country,
                                                    inset: EdgeInsets(top: 14, leading: 14, bottom: 14, trailing: 10)) {
                                    chosenPostcodeCountry = $0
                                }
                                Divider().padding(.vertical, 12)
                            }
                            TextField(example ?? "", text: $deliveryPostcode)
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
                    if requirement.isOptional, deliveryPostcode.isEmpty,
                       let suggestion = postcodeFill.offering(previousPostcode(for: resolvedCarrier, requirement: requirement)) {
                        Button(localizer.text("add.usePostcode", ["postcode": suggestion])) {
                            deliveryPostcode = postcodeFill.fill(suggestion)
                        }
                        .font(.subheadline)
                    }
                    // Without an account every suggestion is this iPhone's own, so it can forget them.
                    if store.isGuest, postcodeFill.holds(deliveryPostcode) {
                        Button(localizer.text("add.forgetPostcode")) {
                            if let postcode = postcodeFill.forget(deliveryPostcode) { store.forgetPostcode(postcode) }
                            deliveryPostcode = ""
                        }
                        .font(.subheadline)
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
                        .foregroundStyle(.secondary)
                }
            }
            field()
                .background(Brand.paper, in: RoundedRectangle(cornerRadius: 12))
                .overlay(RoundedRectangle(cornerRadius: 12).strokeBorder(Brand.separator.opacity(0.3), lineWidth: 0.7))
            Text(help)
                .font(.caption)
                .foregroundStyle(.secondary)
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
            .background(Brand.background)
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
            // Pinned above the keyboard, it keeps to one line at the largest text sizes, as the system's bars do.
            .lineLimit(dynamicTypeSize.isAccessibilitySize ? 1 : nil)
            .minimumScaleFactor(dynamicTypeSize.isAccessibilitySize ? 0.7 : 1)
            .dynamicTypeSize(...DynamicTypeSize.accessibility2)
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

    private var recognitionRequest: String? {
        recognition.request(for: normalizedNumber, applies: recognizable)
    }

    private var recognitionStatus: CarrierRecognition.Status {
        recognition.status(for: normalizedNumber, applies: recognizable)
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

    private var lineDetail: String? {
        if carrierOverride != nil { return localizer.text(refused ? "add.line.refused" : "add.line.chosen") }
        if case .recognized = recognitionStatus { return localizer.text("add.line.found") }
        if let preflightDetail { return preflightDetail }
        if amazonNumber || (lineCarrier != nil && lineCarrier != .internationalPost) { return localizer.text("add.detectedCarrier") }
        return switch recognitionStatus {
        case .asking: localizer.text("add.checkingServices")
        case .several(let carriers): localizer.text("add.line.several", ["carriers": carrierNames(carriers)])
        case .notFound: localizer.text("add.line.none")
        case .failed: localizer.text("add.line.failed")
        case .unasked: localizer.text("add.line.later")
        case .idle, .recognized: nil
        }
    }

    private var automaticDescription: String {
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

    /// The picker leads with the carriers that fit the number, then the ones used before.
    private var pickerSections: [CarrierPickerView.PickerSection] {
        let knowing: [CarrierID] = if case .several(let carriers) = recognitionStatus { carriers } else { [] }
        let fitting = (numberCarrier.map { [$0] } ?? parsed.candidates).filter { catalog.info(for: $0).selectable }
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

    /// The carrier's fields, the postcode's shaped for the country beside it.
    private var requirements: [CarrierRequirement] {
        let country = postcodeCountry
        return catalog.requirements(for: resolvedCarrier, trackingNumber: parsed.trackingNumber).map {
            catalog.postcodeRequirement($0, for: resolvedCarrier, country: country)
        }
    }

    private var trackingURLRequirement: CarrierRequirement? {
        requirements.first(where: { $0.field == .trackingURL })
    }

    /// The carrier's postcode field, before the country beside it shapes it.
    private var carrierPostcodeRequirement: CarrierRequirement? {
        catalog.requirements(for: resolvedCarrier, trackingNumber: parsed.trackingNumber).first(where: { $0.field == .dpdPostcode })
    }

    /// The country the postcode is from: the one chosen while this field takes it, otherwise where the field starts.
    private var postcodeCountry: String? {
        guard let requirement = carrierPostcodeRequirement else { return nil }
        if let chosenPostcodeCountry,
           catalog.postcodeCountries(for: resolvedCarrier, requirement: requirement).contains(chosenPostcodeCountry) {
            return chosenPostcodeCountry
        }
        return catalog.initialPostcodeCountry(for: resolvedCarrier, requirement: requirement)
    }

    /// The postcode's country, offered beside the field when it takes more than one.
    private var offeredPostcodeCountry: String? {
        guard let requirement = carrierPostcodeRequirement,
              catalog.postcodeCountries(for: resolvedCarrier, requirement: requirement).count > 1 else { return nil }
        return postcodeCountry
    }

    private var postcodeRequirement: CarrierRequirement? {
        requirements.first(where: { $0.field == .dpdPostcode })
    }

    private var needsRequiredDetails: Bool {
        (trackingURLRequirement != nil && parsed.trackingURL == nil)
            || postcodeRequirement != nil
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
                guard requirement.isSatisfied(by: deliveryPostcode) else { return false }
            }
        }
        return true
    }

    /// The last postcode given that fits the field, this carrier's first: from the parcels,
    /// then from what this iPhone remembers.
    private func previousPostcode(for carrier: CarrierID, requirement: CarrierRequirement) -> String? {
        requirement.suggestedPostcode(for: carrier, among: store.givenPostcodes)
    }

    /// A required postcode starts from the last one that fits; an optional one is
    /// only offered (see `requiredDetails`).
    private func prepareRequiredDetails(for carrier: CarrierID) {
        postcodeFill.prepare()
        guard let requirement = postcodeRequirement, !requirement.isOptional, deliveryPostcode.isEmpty,
              let suggestion = previousPostcode(for: carrier, requirement: requirement) else { return }
        deliveryPostcode = postcodeFill.fill(suggestion)
    }

    private func paste() {
        DeliveryAnalytics.shared.action("parcel-paste")
        guard let value = Clipboard.string?.trimmingCharacters(
            in: .whitespacesAndNewlines
        ), !value.isEmpty else {
            errorMessage = localizer.text("add.pasteFailed")
            return
        }
        withAnimation(reduceMotion ? nil : .snappy(duration: 0.34)) {
            errorMessage = nil
            trackingInput = value
        }
        focusedField = .tracking
        recognition.settledNumber = Self.trackingNumber(in: value)
    }

    private func fill(_ draft: SharedParcelDraft) {
        withAnimation(reduceMotion ? nil : .snappy(duration: 0.34)) {
            errorMessage = nil
            trackingInput = draft.trackingInput
            if !draft.label.isEmpty { label = draft.label }
        }
        recognition.settledNumber = Self.trackingNumber(in: draft.trackingInput)
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
                let parcel = try await store.add(
                    trackingNumber: parsed.trackingNumber,
                    label: label,
                    carrier: resolvedCarrier,
                    trackingURL: trackingURLRequirement != nil
                        ? (parsed.trackingURL ?? trackingURL) : nil,
                    dpdPostcode: postcode,
                    dpdPostcodeCountry: catalog.sentPostcodeCountry(postcodeCountry, postcode: postcode, for: resolvedCarrier)
                )
                onAdded(parcel.id)
                dismiss()
            } catch is CancellationError {
                saving = false
            } catch {
                if let apiError = error as? DeliveryAPIError,
                   case .duplicateTracking(let packageID) = apiError {
                    duplicateParcelID = packageID
                }
                errorMessage = localizer.errorMessage(error)
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
    func offering(_ suggestion: String?) -> String? { forgotten ? nil : suggestion }

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

/// The country a delivery postcode is from, beside its field: its flag and code, opening on the countries the
/// field takes, the carrier's own first.
struct PostcodeCountryMenu: View {
    let carrier: CarrierID
    let requirement: CarrierRequirement
    let country: String
    /// Inside the label, so that all of it opens the menu.
    var inset = EdgeInsets()
    let choose: (String) -> Void

    @EnvironmentObject private var localizer: Localizer

    var body: some View {
        let choices = CarrierCatalog.shared.postcodeCountryChoices(for: carrier, requirement: requirement, language: localizer.language)
        Menu {
            Picker(localizer.text("add.requirement.postcodeCountry"), selection: Binding(get: { country }, set: choose)) {
                Section { options(choices.own) }
                Section { options(choices.others) }
            }
        } label: {
            HStack(spacing: 4) {
                Text(TrackingLocation.flag(country))
                Text(country).fontWeight(.medium)
                Image(systemName: "chevron.up.chevron.down")
                    .font(.caption2.weight(.semibold))
                    .foregroundStyle(.secondary)
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
                Section(localizer.text("view.status")) {
                    Picker(localizer.text("view.status"), selection: $status) {
                        ForEach(ParcelStatusFilter.allCases) { value in
                            Text(localizer.text(value.localizationKey)).tag(value)
                        }
                    }
                    .pickerStyle(.inline)
                    .choicesOnly()
                }
                Section(localizer.text("view.carrier")) {
                    Picker(localizer.text("view.carrier"), selection: $carrier) {
                        Text(localizer.text("view.allCarriers")).tag(Optional<CarrierID>.none)
                        ForEach(carriers) { value in
                            Text(catalog.info(for: value, language: localizer.language).displayName).tag(Optional(value))
                        }
                    }
                }
                Section(localizer.text("view.sort")) {
                    Picker(localizer.text("view.sort"), selection: $sort) {
                        ForEach(ParcelSort.allCases) { value in
                            Text(localizer.text(value.localizationKey)).tag(value)
                        }
                    }
                    .pickerStyle(.inline)
                    .choicesOnly()
                }
                Section {
                    Button(localizer.text("view.clear"), role: .destructive) {
                        status = .all
                        carrier = nil
                        sort = .priority
                    }
                }
            }
            .navigationTitle(localizer.text("view.showControls"))
            .navigationBarTitleDisplayMode(.inline)
            #if os(macOS)
            // Sections as iOS draws them, rather than a label beside each picker, on the app's paper rather than the
            // popover's glass; a popover closes on a click outside it.
            .formStyle(.grouped)
            .presentationBackground(Brand.background)
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
