package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.PeekMarkArtwork

/**
 * The iPhone app's corner radii (A-S57), all drawn with continuous corners (`ContinuousRoundedShape`). Status badges,
 * count pills and chips are capsules (`CapsuleShape`) rather than a radius.
 */
object Radii {
    /** A parcel card in the list, and `parcelCardSurface`. */
    val card = 16.dp

    /** The Next up card. */
    val nextUp = 24.dp

    /** `experimentalSurface`'s default. */
    val experimentalSurface = 26.dp

    /** The archive shelf. */
    val shelf = 18.dp

    val noticeBanner = 14.dp
    val inlineToast = 16.dp

    /** The answer buttons in a toast. */
    val toastAnswer = 12.dp

    val parcelFlag = 12.dp

    /** Quiet (secondary) buttons. */
    val quietButton = 14.dp

    val deviceAccountRow = 16.dp

    /** The first-open field, at regular and at compact width. */
    val firstOpenField = 20.dp
    val firstOpenFieldCompact = 18.dp

    /** Primary buttons, at regular and at compact width. */
    val primaryButton = 20.dp
    val primaryButtonCompact = 16.dp

    val mastheadButton = 12.dp

    /** The bottom corners of the parcel hero. */
    val heroBottom = 38.dp

    val passportCover = 20.dp

    /** The world map's summary card. */
    val mapSummary = 24.dp

    /** A place's name on the map. */
    val mapLabelPill = 7.dp

    /** The map's pointer chip, 26 tall. */
    val pointerChip = 13.dp

    /** A confetti sheet. */
    val confetti = 1.5.dp

    /** The mark's tile: 116 of its 512 units. */
    fun peekMark(size: Dp): Dp = size * (PeekMarkArtwork.cornerRadius / PeekMarkArtwork.frame).toFloat()
}
