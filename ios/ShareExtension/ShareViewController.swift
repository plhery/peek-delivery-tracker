#if os(iOS)
import UIKit
#else
import AppKit
import SwiftUI
#endif
import UniformTypeIdentifiers

/// What was shared: the item's title for the parcel's name, and its links and text, each once, one to a line.
enum SharedContent {
    struct Read: Equatable {
        let label: String
        let trackingInput: String
    }

    /// Calls back on the main queue, with nil when nothing shared reads as text.
    static func read(_ context: NSExtensionContext?, completion: @escaping (Read?) -> Void) {
        let items = context?.inputItems.compactMap { $0 as? NSExtensionItem } ?? []
        let providers = items.flatMap { $0.attachments ?? [] }
        let label = items.compactMap { $0.attributedTitle?.string.trimmed.nonEmpty }
            .first.map { String($0.prefix(80)) } ?? ""
        let itemText = items.compactMap { $0.attributedContentText?.string.trimmed.nonEmpty }
        let candidates = providers.compactMap { provider -> (NSItemProvider, String)? in
            if provider.hasItemConformingToTypeIdentifier(UTType.url.identifier) {
                return (provider, UTType.url.identifier)
            }
            if provider.hasItemConformingToTypeIdentifier(UTType.plainText.identifier) {
                return (provider, UTType.plainText.identifier)
            }
            return nil
        }
        guard !candidates.isEmpty || !itemText.isEmpty else {
            completion(nil)
            return
        }

        var loaded = Array<String?>(repeating: nil, count: candidates.count)
        let group = DispatchGroup()
        for (index, candidate) in candidates.enumerated() {
            group.enter()
            candidate.0.loadItem(forTypeIdentifier: candidate.1) { value, _ in
                let text: String?
                if let url = value as? URL { text = url.absoluteString }
                else if let textValue = value as? String { text = textValue }
                else if let data = value as? Data { text = String(data: data, encoding: .utf8) }
                else { text = nil }
                DispatchQueue.main.async {
                    loaded[index] = text?.trimmed.nonEmpty
                    group.leave()
                }
            }
        }
        group.notify(queue: .main) {
            let parts = (loaded.compactMap { $0 } + itemText).reduce(into: [String]()) { result, value in
                if !result.contains(value) { result.append(value) }
            }
            let trackingInput = String(parts.joined(separator: "\n").prefix(10_000))
            completion(trackingInput.isEmpty ? nil : Read(label: label, trackingInput: trackingInput))
        }
    }
}

#if os(iOS)
final class ShareViewController: UIViewController {
    private let statusLabel = UILabel()
    private var finished = false
    private var saved = false
    private let cancelButton = UIButton(type: .system)
    private let openButton = UIButton(type: .system)
    private var parcelLabel = ""
    private var trackingInput = ""
    // The app saves its language to the App Group whenever it is set.
    private let copy = ExtensionLocalizer(savedLanguageCode: ShareInbox.defaults?.string(forKey: "deliveryTrackerLocale"))

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = UIColor(red: 0.969, green: 0.953, blue: 0.918, alpha: 1)
        configureInterface()
        loadSharedContent()
    }

    private func configureInterface() {
        let markSize: CGFloat = 76
        let mark = UIImageView(image: UIGraphicsImageRenderer(size: CGSize(width: markSize, height: markSize)).image { renderer in
            PeekMarkArtwork.draw(in: renderer.cgContext, size: markSize)
        })
        mark.translatesAutoresizingMaskIntoConstraints = false

        let title = UILabel()
        title.text = copy.text("shareExtension.title")
        title.font = .preferredFont(forTextStyle: .title2).withWeight(.bold)
        title.textAlignment = .center

        statusLabel.text = copy.text("shareExtension.reading")
        statusLabel.font = .preferredFont(forTextStyle: .subheadline)
        statusLabel.textColor = .secondaryLabel
        statusLabel.numberOfLines = 0
        statusLabel.textAlignment = .center

        var openConfiguration = UIButton.Configuration.filled()
        openConfiguration.title = copy.text("shareExtension.save")
        openConfiguration.image = UIImage(systemName: "tray.and.arrow.down")
        openConfiguration.imagePadding = 8
        openConfiguration.cornerStyle = .large
        openConfiguration.baseBackgroundColor = UIColor(red: 1, green: 0.81, blue: 0, alpha: 1)
        openConfiguration.baseForegroundColor = UIColor(red: 0.09, green: 0.09, blue: 0.08, alpha: 1)
        openButton.configuration = openConfiguration
        openButton.isEnabled = false
        openButton.addTarget(self, action: #selector(saveShare), for: .touchUpInside)

        cancelButton.setTitle(copy.text("common.cancel"), for: .normal)
        cancelButton.addTarget(self, action: #selector(cancelShare), for: .touchUpInside)

        let stack = UIStackView(arrangedSubviews: [mark, title, statusLabel, openButton, cancelButton])
        stack.axis = .vertical
        stack.alignment = .center
        stack.spacing = 18
        stack.setCustomSpacing(26, after: statusLabel)
        stack.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(stack)

        NSLayoutConstraint.activate([
            mark.widthAnchor.constraint(equalToConstant: markSize),
            mark.heightAnchor.constraint(equalToConstant: markSize),
            openButton.widthAnchor.constraint(equalTo: stack.widthAnchor),
            openButton.heightAnchor.constraint(equalToConstant: 52),
            stack.leadingAnchor.constraint(equalTo: view.layoutMarginsGuide.leadingAnchor, constant: 8),
            stack.trailingAnchor.constraint(equalTo: view.layoutMarginsGuide.trailingAnchor, constant: -8),
            stack.centerYAnchor.constraint(equalTo: view.centerYAnchor),
        ])
    }

    private func loadSharedContent() {
        SharedContent.read(extensionContext) { [weak self] content in
            guard let self, !self.finished else { return }
            guard let content else {
                self.statusLabel.text = self.copy.text("shareExtension.notFound")
                return
            }
            self.parcelLabel = content.label
            self.trackingInput = content.trackingInput
            self.statusLabel.text = self.copy.text("shareExtension.ready")
            self.openButton.isEnabled = true
        }
    }

    @objc private func saveShare() {
        guard !finished, !saved, !trackingInput.isEmpty else { return }
        guard ShareInbox.save(SharedParcelDraft(label: parcelLabel, trackingInput: trackingInput)) else {
            statusLabel.text = copy.text("shareExtension.saveFailed")
            return
        }
        saved = true
        statusLabel.text = copy.text("shareExtension.saved")
        openButton.isHidden = true
        cancelButton.setTitle(copy.text("native.done"), for: .normal)
    }

    @objc private func cancelShare() {
        finished = true
        if saved {
            extensionContext?.completeRequest(returningItems: nil)
        } else {
            extensionContext?.cancelRequest(withError: NSError(domain: NSCocoaErrorDomain, code: NSUserCancelledError))
        }
    }

}

private extension UIFont {
    func withWeight(_ weight: UIFont.Weight) -> UIFont {
        let descriptor = fontDescriptor.addingAttributes([
            .traits: [UIFontDescriptor.TraitKey.weight: weight],
        ])
        return UIFont(descriptor: descriptor, size: pointSize)
    }
}
#else
/// "Add to Peek" in a Mac's Share menu: what was shared waits in the App Group for Peek's Add, as on an iPhone, and
/// Add opens with it once Peek comes forward.
final class ShareViewController: NSViewController {
    private let model = ShareModel()
    // The app saves its language to the App Group whenever it is set.
    private let copy = ExtensionLocalizer(savedLanguageCode: ShareInbox.defaults?.string(forKey: "deliveryTrackerLocale"))

    override func loadView() {
        view = NSHostingView(rootView: ShareView(model: model, copy: copy, save: { [weak self] in self?.save() }, close: { [weak self] in self?.close() }))
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        SharedContent.read(extensionContext) { [model] content in
            guard model.state == .reading else { return }
            model.content = content
            model.state = content == nil ? .notFound : .ready
        }
    }

    private func save() {
        guard model.state == .ready || model.state == .saveFailed, let content = model.content else { return }
        model.state = ShareInbox.save(SharedParcelDraft(label: content.label, trackingInput: content.trackingInput))
            ? .saved : .saveFailed
    }

    private func close() {
        if model.state == .saved {
            extensionContext?.completeRequest(returningItems: nil)
        } else {
            model.state = .closed
            extensionContext?.cancelRequest(withError: NSError(domain: NSCocoaErrorDomain, code: NSUserCancelledError))
        }
    }
}

@MainActor
private final class ShareModel: ObservableObject {
    enum State { case reading, ready, notFound, saved, saveFailed, closed }
    @Published var state = State.reading
    var content: SharedContent.Read?
}

/// The iPhone's sheet as a Mac's popover: Peek's mark, what it found, and Save.
private struct ShareView: View {
    @ObservedObject var model: ShareModel
    let copy: ExtensionLocalizer
    let save: () -> Void
    let close: () -> Void

    private static let markSize: CGFloat = 64
    private static let mark = NSImage(size: NSSize(width: markSize, height: markSize), flipped: true) { _ in
        guard let context = NSGraphicsContext.current?.cgContext else { return false }
        PeekMarkArtwork.draw(in: context, size: markSize)
        return true
    }

    var body: some View {
        VStack(spacing: 14) {
            Image(nsImage: Self.mark)
            Text(copy.text("shareExtension.title"))
                .font(.title2.bold())
            Text(status)
                .font(.subheadline)
                .foregroundStyle(.secondary)
                .multilineTextAlignment(.center)
                .fixedSize(horizontal: false, vertical: true)
            if model.state == .ready || model.state == .saveFailed {
                Button(action: save) {
                    Label(copy.text("shareExtension.save"), systemImage: "tray.and.arrow.down")
                        .font(.body.weight(.semibold))
                        .frame(maxWidth: .infinity, minHeight: 38)
                        .foregroundStyle(Color(red: 0.09, green: 0.09, blue: 0.08))
                        .background(Color(red: 1, green: 0.81, blue: 0), in: RoundedRectangle(cornerRadius: 10, style: .continuous))
                        .contentShape(Rectangle())
                }
                .buttonStyle(.plain)
                .keyboardShortcut(.defaultAction)
                .padding(.top, 6)
            }
            Button(copy.text(model.state == .saved ? "native.done" : "common.cancel"), action: close)
                .keyboardShortcut(.cancelAction)
        }
        .padding(24)
        .frame(width: 320)
        .background(Color(red: 0.969, green: 0.953, blue: 0.918))
        // On the iPhone's paper, in light and dark.
        .environment(\.colorScheme, .light)
    }

    private var status: String {
        switch model.state {
        case .reading, .closed: copy.text("shareExtension.reading")
        case .ready: copy.text("shareExtension.ready")
        case .notFound: copy.text("shareExtension.notFound")
        case .saved: copy.text("shareExtension.saved")
        case .saveFailed: copy.text("shareExtension.saveFailed")
        }
    }
}
#endif

private extension String {
    var trimmed: String { trimmingCharacters(in: .whitespacesAndNewlines) }
    var nonEmpty: String? { isEmpty ? nil : self }
}
