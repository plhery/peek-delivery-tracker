import UserNotifications

/// Writes a delivery's time on this device's clock as its push arrives (`DeviceClock`). Every other push, and one it
/// can't read, shows as the server wrote it.
final class NotificationService: UNNotificationServiceExtension {
    override func didReceive(
        _ request: UNNotificationRequest,
        withContentHandler contentHandler: @escaping (UNNotificationContent) -> Void
    ) {
        guard let body = DeviceClock.body(request.content.userInfo),
              let content = request.content.mutableCopy() as? UNMutableNotificationContent else {
            contentHandler(request.content)
            return
        }
        content.body = body
        contentHandler(content)
    }
}
