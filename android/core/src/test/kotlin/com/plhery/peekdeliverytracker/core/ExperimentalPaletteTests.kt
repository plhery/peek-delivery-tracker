package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.util.UUID
import kotlin.math.roundToInt
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * ParcelPassDesignSystem.swift's `ExperimentalPalette` (A-S47, A-S48, A-S50): the six stage tints and their surfaces,
 * light and dark, and the tint and surface `tint(for:)` and `surface(for:)` give each stage and a parcel without one.
 */
class ExperimentalPaletteTests {
    @Test
    fun theTintsAreTheIPhonesInLightAndDark() {
        assertEquals(
            listOf(
                "transit #22588A #9BCBFF",
                "pickup #963E19 #FFB184",
                "delivered #24613E #9BE0B5",
                "lilac #654299 #D1B5FF",
                "rose #9A3D5C #FFADC8",
                "ochre #745300 #FFD65F",
            ),
            listOf(
                "transit" to ExperimentalPalette.transit,
                "pickup" to ExperimentalPalette.pickup,
                "delivered" to ExperimentalPalette.delivered,
                "lilac" to ExperimentalPalette.lilac,
                "rose" to ExperimentalPalette.rose,
                "ochre" to ExperimentalPalette.ochre,
            ).map { (name, color) -> "$name ${color.light.hexString()} ${color.dark.hexString()}" },
        )
    }

    @Test
    fun pickupIsTheBrandsWarning() {
        assertEquals(Brand.warning, ExperimentalPalette.pickup)
    }

    @Test
    fun theSurfacesAreTheIPhonesInLightAndDark() {
        assertEquals(
            listOf(
                "transit #DAEAFE #152636",
                "pickup #FFDDC7 #341F14",
                "delivered #DCF0D7 #142A1D",
                "lilac #E9DEFF #272034",
                "rose #FADDE7 #331D27",
                "ochre #FFEBAB #2F220E",
            ),
            listOf(
                "transit" to ExperimentalPalette.transitSurface,
                "pickup" to ExperimentalPalette.pickupSurface,
                "delivered" to ExperimentalPalette.deliveredSurface,
                "lilac" to ExperimentalPalette.lilacSurface,
                "rose" to ExperimentalPalette.roseSurface,
                "ochre" to ExperimentalPalette.ochreSurface,
            ).map { (name, color) -> "$name ${color.light.hexString()} ${color.dark.hexString()}" },
        )
    }

    @Test
    fun eachStageHasTheIPhonesTintAndSurface() {
        val tints = mapOf(
            ExperimentalPalette.transit to "transit",
            ExperimentalPalette.pickup to "pickup",
            ExperimentalPalette.delivered to "delivered",
            ExperimentalPalette.lilac to "lilac",
            ExperimentalPalette.ochre to "ochre",
        )
        val surfaces = mapOf(
            ExperimentalPalette.transitSurface to "transit",
            ExperimentalPalette.pickupSurface to "pickup",
            ExperimentalPalette.deliveredSurface to "delivered",
            ExperimentalPalette.lilacSurface to "lilac",
            ExperimentalPalette.ochreSurface to "ochre",
        )
        val expected = mapOf(
            null to ("lilac" to "lilac"),
            TrackingStage.Pending to ("lilac" to "lilac"),
            TrackingStage.Registered to ("lilac" to "lilac"),
            TrackingStage.Accepted to ("transit" to "transit"),
            TrackingStage.InTransit to ("transit" to "transit"),
            TrackingStage.OutForDelivery to ("ochre" to "ochre"),
            TrackingStage.Delivered to ("delivered" to "delivered"),
            TrackingStage.Customs to ("pickup" to "pickup"),
            TrackingStage.FailedAttempt to ("pickup" to "pickup"),
            TrackingStage.ReadyForPickup to ("pickup" to "pickup"),
            TrackingStage.Returned to ("pickup" to "pickup"),
            TrackingStage.Exception to ("pickup" to "pickup"),
        )
        assertEquals(TrackingStage.entries.toSet() + null, expected.keys)
        val given = expected.keys.associateWith { stage ->
            val parcel = parcel(stage)
            assertEquals(stage, parcel.currentStage)
            tints[ExperimentalPalette.tint(parcel)] to surfaces[ExperimentalPalette.surface(parcel)]
        }
        assertEquals(expected, given)
    }

    private fun parcel(stage: TrackingStage?) = ParcelLogicTestFixtures.makeParcel(id = UUID(0, 1)).let { parcel ->
        if (stage == null) parcel else parcel.copy(trackingEvents = listOf(ParcelLogicTestFixtures.event(parcel.id, stage, "2026-08-02T09:00:00Z")))
    }

    private fun RgbaColor.hexString(): String =
        "#%02X%02X%02X".format((red * 255).roundToInt(), (green * 255).roundToInt(), (blue * 255).roundToInt())
}
