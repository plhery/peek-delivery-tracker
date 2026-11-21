import Foundation
import SwiftUI

/// The answer of `/api/account/assistants`, read leniently: a connection this app can't read, such as a kind or a
/// permission a newer server adds, drops out of the list instead of failing the whole page.
struct AssistantsOffer: Decodable {
    let value: AssistantsResponse

    static func decode(_ data: Data) throws -> AssistantsResponse {
        do { return try JSONDecoder.deliveryTracker.decode(Self.self, from: data).value }
        catch { throw DeliveryAPIError.invalidResponse }
    }

    private enum Keys: String, CodingKey { case connections, mcpUrl }

    init(from decoder: any Decoder) throws {
        let container = try decoder.container(keyedBy: Keys.self)
        value = AssistantsResponse(
            connections: (try container.decode([Known].self, forKey: .connections)).compactMap(\.value),
            mcpURL: try container.decode(String.self, forKey: .mcpUrl)
        )
    }

    private struct Known: Decodable {
        let value: AssistantConnection?
        init(from decoder: any Decoder) throws { value = try? AssistantConnection(from: decoder) }
    }
}

extension AssistantConnection {
    /// What the connection may do, as Settings words it: whether it sees, adds, and edits and archives parcels.
    var scopeKey: String {
        let kinds = [(AssistantScope.parcelsRead, "read"), (.parcelsWrite, "write"), (.parcelsEdit, "edit")]
            .filter { scopes.contains($0.0) }.map(\.1).joined(separator: " ")
        return [
            "read": "assistants.scope.read",
            "write": "assistants.scope.write",
            "edit": "assistants.scope.edit",
            "read write": "assistants.scope.both",
            "read edit": "assistants.scope.readEdit",
            "write edit": "assistants.scope.writeEdit",
            "read write edit": "assistants.scope.all",
        ][kinds] ?? "assistants.scope.read"
    }
}

/// How to add Peek to each assistant, from `shared/assistant-setups.json`, which the website and Android read too: each
/// line is a copy key and the assistant's own names its `{{…}}` stand for, in English as its help pages give them.
struct AssistantSetup: Decodable, Identifiable, Sendable {
    let name: String
    /// The one thing to do, shown under the name.
    let lead: Line?
    /// Which plans and places can.
    let who: Line
    let steps: [Line]
    /// What to do where the steps don't apply.
    let fallback: Line?

    var id: String { name }

    /// The steps with signing in to Peek as the one before the last: the assistant asks for it when the connector is
    /// added or first used.
    @MainActor func stepsWithSignIn(_ localizer: Localizer) -> [Line] {
        let signIn = Line(key: "assistants.setup.signIn", labels: ["allow": .name(localizer.text("assistants.consent.allow"))])
        return steps.dropLast() + [signIn] + steps.suffix(1)
    }

    /// A line's copy key, and what each of its `{{…}}` stands for.
    struct Line: Decodable, Sendable {
        let key: String
        let labels: [String: Label]

        init(key: String, labels: [String: Label] = [:]) {
            self.key = key
            self.labels = labels
        }

        /// Written as `[key]` or `[key, {labels}]`.
        init(from decoder: any Decoder) throws {
            var container = try decoder.unkeyedContainer()
            key = try container.decode(String.self)
            labels = container.isAtEnd ? [:] : try container.decode([String: Label].self)
        }

        /// The line in the reader's language, with the assistant's menus and buttons in bold, its own pages as links
        /// and commands as code, `{{address}}` in them being Peek's address for assistants.
        @MainActor func text(_ localizer: Localizer, address: String) -> AttributedString {
            let source = localizer.text(key)
            var result = AttributedString()
            var rest = Substring(source)
            while let open = rest.range(of: "{{"), let close = rest[open.upperBound...].range(of: "}}") {
                result += AttributedString(String(rest[..<open.lowerBound]))
                let name = String(rest[open.upperBound..<close.lowerBound])
                if let label = labels[name] {
                    result += label.text(address: address)
                } else {
                    result += AttributedString(String(rest[open.lowerBound..<close.upperBound]))
                }
                rest = rest[close.upperBound...]
            }
            result += AttributedString(String(rest))
            return result
        }
    }

    /// The name of an assistant's menu or button, a page of its own, or a command to type.
    enum Label: Decodable, Sendable {
        case name(String)
        case link(text: String, url: URL)
        case command(String)

        private enum Keys: String, CodingKey { case text, href, command }

        init(from decoder: any Decoder) throws {
            if let name = try? decoder.singleValueContainer().decode(String.self) {
                self = .name(name)
                return
            }
            let container = try decoder.container(keyedBy: Keys.self)
            if let command = try container.decodeIfPresent(String.self, forKey: .command) {
                self = .command(command)
            } else if let url = URL(string: try container.decode(String.self, forKey: .href)) {
                self = .link(text: try container.decode(String.self, forKey: .text), url: url)
            } else {
                throw DecodingError.dataCorruptedError(forKey: .href, in: container, debugDescription: "Invalid link")
            }
        }

        func text(address: String) -> AttributedString {
            switch self {
            case .name(let name):
                var text = AttributedString(name)
                text.inlinePresentationIntent = .stronglyEmphasized
                return text
            case .link(let words, let url):
                var text = AttributedString(words)
                text.link = url
                text.underlineStyle = .single
                return text
            case .command(let command):
                var text = AttributedString(command.replacingOccurrences(of: "{{address}}", with: address))
                text.inlinePresentationIntent = .code
                return text
            }
        }
    }

    private struct File: Decodable { let setups: [AssistantSetup] }

    /// The setups the app bundles, or none if the file can't be read.
    static let bundled: [AssistantSetup] = {
        guard let url = Bundle.main.url(forResource: "AssistantSetups", withExtension: "json"),
              let data = try? Data(contentsOf: url) else { return [] }
        return (try? JSONDecoder().decode(File.self, from: data).setups) ?? []
    }()
}

/// The account's assistants, and Disconnect. The row shows only once the server says assistants may connect to the
/// account: a 404, an error or no account at all leave it hidden.
@MainActor
final class AssistantsModel: ObservableObject {
    /// How the model reaches the account's assistants: `ParcelStore.assistantsClient()`.
    struct Client {
        /// The offer, or nil when the server answers 404: the feature isn't set up or open to the account.
        var read: @MainActor () async throws -> AssistantsResponse?
        /// Disconnects an assistant; one already gone counts as done.
        var revoke: @MainActor (_ id: UUID) async throws -> Void
    }

    /// Nil hides the row.
    @Published private(set) var offer: AssistantsResponse?
    @Published private(set) var working = false
    /// Why the last change failed, until the next one.
    @Published private(set) var failure: (any Error)?

    private var client: Client?
    /// Counts the changes made here: a read that began before one would bring back what it replaced.
    private var changes = 0
    private var reads = 0

    init(client: Client? = nil) { self.client = client }

    /// The account to read, or none: the row goes with it.
    func configure(_ client: Client?) {
        self.client = client
        changes += 1
        if client == nil { offer = nil; failure = nil }
    }

    /// Reads the offer. A failure leaves what is shown, which before any answer is no row.
    func load() async {
        guard let client else { offer = nil; return }
        reads += 1
        let read = reads
        let change = changes
        do {
            let next = try await client.read()
            guard change == changes, read == reads, !Task.isCancelled else { return }
            offer = next
        } catch {
            // The row stays as the last answer left it: a moment offline doesn't take it away.
        }
    }

    /// The assistants connected with OAuth: personal tokens have no screen.
    var assistants: [AssistantConnection] {
        offer?.connections.filter { $0.kind == .oauth } ?? []
    }

    /// Disconnects one: it loses access to the account at once.
    @discardableResult func revoke(_ id: UUID) async -> Bool {
        guard let client, !working else { return false }
        working = true
        failure = nil
        changes += 1
        defer { working = false }
        do {
            try await client.revoke(id)
            changes += 1
            if let offer {
                self.offer = AssistantsResponse(connections: offer.connections.filter { $0.id != id }, mcpURL: offer.mcpURL)
            }
            return true
        } catch is CancellationError {
            return false
        } catch {
            failure = error
            return false
        }
    }
}
