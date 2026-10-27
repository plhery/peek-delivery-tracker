package com.plhery.peekdeliverytracker.core

import java.time.DateTimeException
import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.UUID

// From APIClient.swift, the errors the localizer explains and how long a refusal asks to wait. The client itself
// comes with the store port.

sealed class DeliveryApiError(message: String) : Exception(message) {
    class AuthenticationExpired : DeliveryApiError("Your sign-in expired. Please sign in again.")
    class DuplicateTracking(val parcelId: UUID) : DeliveryApiError("This tracking number is already in your delivery box.")
    class InvalidResponse : DeliveryApiError("The delivery service returned an invalid response.")
    class LabelTooLong : DeliveryApiError("Parcel names can be at most 80 characters.")
    class NotificationsDenied : DeliveryApiError("Notifications were not allowed.")
    class ParcelMissing : DeliveryApiError("This parcel is no longer in your delivery box.")
    class PushTokenUnavailable :
        DeliveryApiError("Apple did not return a notification token. Try again on a signed development build.")
    class RefreshFailed : DeliveryApiError("Tracking refresh failed. Try again.")
    class RefreshTimeout : DeliveryApiError("The tracking refresh is taking longer than expected.")
    /** `retryAfter` in seconds. */
    class RateLimited(val retryAfter: Double) : DeliveryApiError("Too many requests. Try again shortly.")
    class Service(val serviceMessage: String) : DeliveryApiError(serviceMessage)
    class ServiceFailed(val status: Int) : DeliveryApiError("Delivery service failed ($status).")
}

object DeliveryApiClient {
    // ASCII digits, as `TimeInterval(_:)` reads them: Android's regular expressions are ICU's, whose `\d` is any digit.
    private val decimal = Regex("^[+-]?(?:[0-9]+\\.?[0-9]*|\\.[0-9]+)(?:[eE][+-]?[0-9]+)?$")
    private val httpDate = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US)

    /** A `Retry-After` header in seconds, given either as seconds or as an HTTP date. */
    fun retryAfterSeconds(value: String?, now: Instant = Instant.now()): Double {
        if (value == null) return 0.0
        val seconds = if (decimal.matches(value)) value.toDouble() else null
        if (seconds != null && seconds.isFinite()) return maxOf(0.0, seconds)
        val date = try {
            ZonedDateTime.parse(value, httpDate).toInstant()
        } catch (error: DateTimeException) {
            null
        }
        return maxOf(0.0, date?.timeIntervalSince(now) ?: 0.0)
    }
}
