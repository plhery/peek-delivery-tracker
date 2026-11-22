package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.Parcel

// From ParcelMapView.swift: the route a parcel's cards and page draw.

/**
 * A parcel's route, with the town it is addressed to when the server placed one (for its owner alone), else its
 * destination country when the carrier gave one: "To Lyon, 340 km to go".
 */
fun ParcelRoute(parcel: Parcel, atlas: WorldAtlas, language: AppLanguage): ParcelRoute {
    val name = { code: String -> TrackingLocation.countryName(code, language) }
    val destination = parcel.destinationPlace?.let { RoutePlace(it) } ?: parcel.carrierData?.destinationCountry?.let { code ->
        atlas.label(code)?.let { RoutePlace.country(code, name = name(code), at = it) }
    }
    return ParcelRoute(parcel.trackingEvents, destination = destination, countryName = name)
}
