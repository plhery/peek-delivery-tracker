package com.plhery.peekdeliverytracker.core

import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sin

// AddParcelView.swift's arrival of a parcel just added, as numbers: how its card wobbles (`ParcelArrivalEffect`) and
// where each of the little parcels that fly out of its stamp is (`ParcelAddedBurst`). The screen draws them.

object ParcelArrival {
    /** `.linear(duration: reduceMotion ? 0.75 : 0.48)`: the card's wobble, or its brightness pulse under Reduce Motion. */
    const val WOBBLE_DURATION = 0.48
    const val REDUCED_WOBBLE_DURATION = 0.75

    /** The burst is over this long after its first frame, or under Reduce Motion, where nothing flies, this long. */
    const val BURST_DURATION = 0.95
    const val REDUCED_BURST_DURATION = 0.75

    /**
     * How far in from the card's end its stamp is, which the little parcels fly out of (`arrivalCelebration(for:
     * stubInset:)`): 30 on most cards, 43 on Next up and 49 on Next up's pass in a section.
     */
    const val STUB_INSET = 30.0
    const val NEXT_STUB_INSET = 43.0
    const val SECTION_NEXT_STUB_INSET = 49.0

    /** The wobble at `progress`, 0 to 1: three swings each way, dying away. */
    fun wobble(progress: Double): Double = sin(progress * PI * 6) * (1 - progress)

    /** The swell at `progress`: up and back down once. */
    fun pulse(progress: Double): Double = sin(progress * PI)

    /** `.offset(x:)`, in points: none under Reduce Motion. */
    fun offsetX(progress: Double, reduced: Boolean): Double = if (reduced) 0.0 else wobble(progress) * 3

    /** `.rotationEffect(.degrees(_:))`, in degrees: none under Reduce Motion. */
    fun rotation(progress: Double, reduced: Boolean): Double = if (reduced) 0.0 else wobble(progress) * 1.1

    /** `.scaleEffect(_:)`: none under Reduce Motion. */
    fun scale(progress: Double, reduced: Boolean): Double = if (reduced) 1.0 else 1 + pulse(progress) * 0.012

    /** `.brightness(_:)`, added to each of red, green and blue: only under Reduce Motion. */
    fun brightness(progress: Double, reduced: Boolean): Double = if (reduced) pulse(progress) * 0.08 else 0.0

    /** A little parcel: how far it goes across, how hard it is thrown up, its turn in degrees, its size and its delay. */
    data class Pip(val spread: Double, val impulse: Double, val rotation: Double, val size: Double, val delay: Double) {
        /** `SmallPip().frame(width: size * 1.25)`, in points. */
        val width: Double get() = size * 1.25
    }

    /** The six little parcels, unevenly thrown. */
    val pips: List<Pip> = listOf(
        Pip(-1.0, 90.0, -80.0, 19.0, 0.02), Pip(-0.7, 145.0, 65.0, 24.0, 0.0),
        Pip(-0.35, 115.0, -100.0, 21.0, 0.05), Pip(0.0, 155.0, 85.0, 25.0, 0.03),
        Pip(0.35, 120.0, -55.0, 20.0, 0.06), Pip(0.6, 95.0, 100.0, 22.0, 0.01),
    )

    /**
     * Where a little parcel is: its centre in points from the card's top left, its turn in degrees, its scale and its
     * opacity. Set again for each frame, so that drawing the burst makes nothing new.
     */
    class PipPose {
        var x = 0.0
        var y = 0.0
        var rotation = 0.0
        var scale = 0.0
        var opacity = 0.0
    }

    /**
     * `particle(_:elapsed:origin:spread:)`: where `pip` is `elapsed` seconds into the burst on a card `width` by
     * `height` points, out of its stamp `stubInset` in from its end, at mid-height, set into `into`. Each takes 0.8 s
     * once its delay has passed, flying out at most a quarter of the card's width, 80 points at most, up and back down.
     */
    fun pose(pip: Pip, elapsed: Double, width: Double, height: Double, stubInset: Double, into: PipPose): PipPose {
        val progress = swiftMax(0.0, swiftMin(1.0, (elapsed - pip.delay) / 0.8))
        val spread = swiftMin(width * 0.25, 80.0)
        into.rotation = pip.rotation * progress + sin(progress * PI * 2) * 8
        into.scale = swiftMin(1.0, progress / 0.12) * (1 - progress * 0.25)
        into.opacity = swiftMin(1.0, progress / 0.05) * swiftMin(1.0, (1 - progress) / 0.3)
        into.x = width - stubInset + pip.spread * (1 - (1 - progress).pow(2)) * spread
        into.y = height / 2 + (-pip.impulse * progress + 120 * progress * progress)
        return into
    }
}
