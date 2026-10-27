package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.TrackingProviderCheckOutcome
import java.util.concurrent.ConcurrentHashMap
import kotlin.coroutines.cancellation.CancellationException

/**
 * Carrier recognition in the Add sheet: a shape several carriers share is checked with them once the number
 * is settled (the field loses focus, a paste, a scan, a shared number), never on each keystroke. It never
 * holds the Add button: tracking services check the number again after saving. Mirrors `carrierCheck` in
 * `src/lib/carrierPicker.ts`.
 */
data class CarrierRecognition(
    /** The normalized number as it stood when it was last settled. */
    val settledNumber: String? = null,
    /** The server's answer for a settled number. */
    val answer: CarrierDetectionResponse? = null,
    /** A check that failed before a server answer; it has no known carrier attempts. */
    val failedNumber: String? = null,
) {
    sealed interface Status {
        /** Nothing to report: the number is not settled, or needs no check. */
        data object Idle : Status

        /** No carrier can be asked about this shape; routing looks it up after saving. */
        data object Unasked : Status

        /** Services are checking; which carriers were asked is known only in their answer. */
        data class Asking(val carriers: List<CarrierId>) : Status

        /** One carrier knows the number. */
        data class Recognized(val carrier: CarrierId) : Status

        /** Unrelated carriers all know it: the user chooses. */
        data class Several(val carriers: List<CarrierId>) : Status

        /**
         * Every carrier answered and none knows it yet, which is normal for a new label. `services` when
         * tracking services gave this answer rather than the carriers asked.
         */
        data class NotFound(val carriers: List<CarrierId>, val services: Boolean = false) : Status

        /** No carrier could answer, or no tracking service with `services`; the first sync asks again. */
        data class Failed(val carriers: List<CarrierId>, val services: Boolean = false) : Status
    }

    /**
     * The number to ask about: the settled one, while it is still the number in the field and has no answer
     * yet.
     */
    fun request(number: String, applies: Boolean): String? {
        if (!applies || number.isEmpty() || settledNumber != number ||
            answer?.trackingNumber == number || failedNumber == number
        ) return null
        return number
    }

    /** Carrier attempt lists come from the server's answer. */
    fun status(number: String, applies: Boolean): Status {
        if (!applies || number.isEmpty()) return Status.Idle
        if (failedNumber == number) return Status.Failed(emptyList())
        val answer = answer
        if (answer == null || answer.trackingNumber != number) {
            if (settledNumber != number) return Status.Idle
            return Status.Asking(emptyList())
        }
        if (answer.carrier != CarrierId.Unknown && answer.carrier != CarrierId.InternationalPost) {
            return Status.Recognized(answer.carrier)
        }
        val choices = answer.recognized
        if (choices != null && choices.size > 1) return Status.Several(choices)
        val answered = answer.asked ?: emptyList()
        val providers = answer.providers
        if (!providers.isNullOrEmpty()) {
            return if (answer.trackingFound == true || providers.any {
                    it.outcome == TrackingProviderCheckOutcome.InputRequired ||
                        it.outcome == TrackingProviderCheckOutcome.NoHistory
                }
            ) Status.NotFound(answered, services = true) else Status.Failed(answered, services = true)
        }
        if (answered.isEmpty()) return Status.Unasked
        if ((answer.unanswered?.size ?: 0) >= answered.size) return Status.Failed(answered)
        return Status.NotFound(answered)
    }

    companion object {
        /**
         * Only a number no direct carrier claims with confidence is worth asking about. The check keeps
         * running when a carrier is picked by hand meanwhile: its answer then only says when another carrier
         * has the parcel.
         */
        fun applies(input: TrackingInputMatch, amazon: Boolean, demo: Boolean): Boolean =
            !demo && !amazon && ((input.confidence == CarrierMatch.Confidence.Low && input.carrier == CarrierId.Unknown) ||
                input.carrier == CarrierId.InternationalPost)
    }
}

/**
 * Whether a carrier picked by hand refuses a number as one it does not issue, so the pickers can say so
 * before tracking starts. A carrier's refusal of a number does not change: each pair is asked once while the
 * app runs. Nothing is refused until the server answers, or when it cannot.
 */
object NumberRefusals {
    private val answers = ConcurrentHashMap<NumberRefusalRequest, Boolean>()

    /** The pair worth asking about: a carrier and a number the server accepts, outside the demo. */
    fun question(carrier: CarrierId?, trackingNumber: String, demo: Boolean): NumberRefusalRequest? {
        if (demo || carrier == null || carrier == CarrierId.Unknown ||
            !CatalogExpressions.foundationMatches(trackingNumber, "^[A-Z0-9]{4,40}$")
        ) return null
        return NumberRefusalRequest(trackingNumber = trackingNumber, carrier = carrier)
    }

    /** `ask` is the server's answer (`DeliveryAPIClient.numberRefusal` on iOS), which the app supplies. */
    suspend fun refuses(question: NumberRefusalRequest, ask: suspend (NumberRefusalRequest) -> Boolean): Boolean {
        answers[question]?.let { return it }
        val answer = try {
            ask(question)
        } catch (cancellation: CancellationException) {
            throw cancellation
        } catch (_: Exception) {
            return false
        }
        answers[question] = answer
        return answer
    }
}
