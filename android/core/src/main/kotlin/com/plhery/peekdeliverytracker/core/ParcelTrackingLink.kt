package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId

/** A carrier's or tracking service's page for a parcel. `url` is the link's absolute string. */
data class ParcelTrackingLink(
    val carrier: CarrierId,
    val name: String,
    val url: String,
    val role: Role,
) {
    enum class Role(val rawValue: String) { Active("active"), Waiting("waiting"), History("history") }

    val id: String get() = "${role.rawValue}:$url"

    /** The pages under each number, and the ones with no number of their own. */
    data class Pages(val own: List<List<ParcelTrackingLink>>, val loose: List<ParcelTrackingLink>)

    companion object {
        /**
         * Each carrier's page under its own tracking number: the one the parcel is followed on under the first
         * number, an earlier carrier's under its own. `loose` are the pages with no number of their own, such
         * as a carrier not ready yet.
         */
        fun byNumber(links: List<ParcelTrackingLink>, numbers: List<ParcelTrackingNumber>): Pages {
            val own = numbers.map { mutableListOf<ParcelTrackingLink>() }
            val loose = mutableListOf<ParcelTrackingLink>()
            for (link in links) {
                val row = if (link.role == Role.Active) {
                    numbers.indices.firstOrNull()
                } else {
                    numbers.indices.drop(1).firstOrNull { !numbers[it].tracksAs && numbers[it].carrier == link.carrier }
                }
                if (row != null) own[row].add(link) else loose.add(link)
            }
            return Pages(own, loose)
        }
    }
}
