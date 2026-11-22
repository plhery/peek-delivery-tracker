package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.Parcel

// From ParcelMapView.swift: the route a parcel's cards and page draw.

/**
 * A parcel's route, with the town it is addressed to when the server placed one (for its owner alone): "To Lyon,
 * 340 km to go". Else, once the parcel is in the country of the town where the account's parcels are usually
 * delivered, that town, which the server names for the owner alone and the map heads for as an estimate: "Usually
 * delivered near Lyon". Else its destination country when the carrier gave one.
 */
fun ParcelRoute(parcel: Parcel, atlas: WorldAtlas, language: AppLanguage): ParcelRoute {
    val name = { code: String -> TrackingLocation.countryName(code, language) }
    val near = ExtensionLocalizer(language.rawValue).text("map.usualPlace")
    val destination = parcel.destinationPlace?.let { RoutePlace(it) }
        ?: parcel.usualPlace?.let { RoutePlace(it, estimate = near.replace("{{place}}", it.name)) }
        ?: parcel.carrierData?.destinationCountry?.let { code ->
            atlas.label(code)?.let { RoutePlace.country(code, name = name(code), at = it) }
        }
    return ParcelRoute(parcel.trackingEvents, destination = destination, countryName = name)
}
