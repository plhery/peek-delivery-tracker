package com.plhery.peekdeliverytracker.ui.theme

import com.plhery.peekdeliverytracker.core.Spring
import com.plhery.peekdeliverytracker.core.UnitCurve

/**
 * The iPhone app's keyframe animations, track for track, for `keyframeAnimator`. Each screen keeps its own Reduce
 * Motion rule, as the iPhone's does.
 */
object PeekKeyframes {
    /** Pip's hop beside the feedback bubble (ParcelFeedbackViews.swift): `rise` in points, `turn` in degrees about his feet. */
    data class Hop(val rise: Double = 0.0, val turn: Double = 0.0)

    val hop: KeyframeTimeline<Hop> = KeyframeTimeline(
        Hop(),
        listOf(
            KeyframeTrack({ it.rise }, { hop, value -> hop.copy(rise = value) }, listOf(
                CubicKeyframe(-9.0, 0.21),
                CubicKeyframe(0.0, 0.2),
                CubicKeyframe(-2.0, 0.12),
                CubicKeyframe(0.0, 0.17),
            )),
            KeyframeTrack({ it.turn }, { hop, value -> hop.copy(turn = value) }, listOf(
                CubicKeyframe(-6.0, 0.21),
                CubicKeyframe(3.0, 0.2),
                CubicKeyframe(0.0, 0.29),
            )),
        ),
    )

    /**
     * The parcel page's bell as its notifications are switched (ParcelDetailView.swift): `angle` in degrees about a
     * point 16 % down, `scale`, and `waves`, the sound lines' opacity.
     */
    data class BellPose(val angle: Double = 0.0, val scale: Double = 1.0, val waves: Double = 0.0)

    fun bell(muted: Boolean): KeyframeTimeline<BellPose> = KeyframeTimeline(
        BellPose(),
        listOf(
            KeyframeTrack({ it.angle }, { pose, value -> pose.copy(angle = value) }, if (muted) {
                listOf(CubicKeyframe(-7.0, 0.112), CubicKeyframe(3.0, 0.112), CubicKeyframe(0.0, 0.096))
            } else {
                listOf(
                    CubicKeyframe(17.0, 0.115),
                    CubicKeyframe(-14.0, 0.131),
                    CubicKeyframe(10.0, 0.131),
                    CubicKeyframe(-6.0, 0.131),
                    CubicKeyframe(3.0, 0.132),
                    CubicKeyframe(0.0, 0.18),
                )
            }),
            KeyframeTrack({ it.scale }, { pose, value -> pose.copy(scale = value) }, listOf(
                CubicKeyframe(if (muted) 0.94 else 1.0, 0.112),
                CubicKeyframe(if (muted) 1.02 else 1.0, 0.112),
                CubicKeyframe(1.0, 0.096),
            )),
            KeyframeTrack({ it.waves }, { pose, value -> pose.copy(waves = value) }, listOf(
                CubicKeyframe(if (muted) 0.0 else 0.65, 0.148),
                CubicKeyframe(if (muted) 0.0 else 0.15, 0.114),
                CubicKeyframe(if (muted) 0.0 else 0.65, 0.115),
                CubicKeyframe(0.0, 0.279),
            )),
        ),
    )

    /** How the next-up card gives as its delivery lands (ParcelListView.swift's DeliveredPress). */
    val deliveredNextUp: KeyframeTimeline<Double> = delivered(dip = 0.982, wait = 0.42)

    /** How a compact card swells as its delivery lands. */
    val deliveredCompact: KeyframeTimeline<Double> = delivered(dip = 1.022, wait = 0.01)

    /** `DeliveredPress(dip:wait:)`: the card's scale, still for `wait` seconds, then to `dip` and back. */
    fun delivered(dip: Double, wait: Double): KeyframeTimeline<Double> =
        KeyframeTimeline.of(1.0, LinearKeyframe(1.0, wait), CubicKeyframe(dip, 0.17), CubicKeyframe(1.0, 0.3))

    /** The pull-to-refresh seal's pop as a refresh starts (PullToRefresh.swift): its scale. */
    val pullPop: KeyframeTimeline<Double> = KeyframeTimeline.of(
        1.0,
        LinearKeyframe(1.06, 0.12, UnitCurve.easeOut),
        SpringKeyframe(1.0, 0.4, Spring(duration = 0.4, bounce = 0.5)),
    )

    /**
     * `.symbolEffect(.bounce, value:)`, played as its value changes: the symbol gives a little, then springs back past
     * its size. Apple doesn't publish the effect's curve; this follows it by eye.
     */
    val symbolBounce: KeyframeTimeline<Double> = KeyframeTimeline.of(
        1.0,
        LinearKeyframe(0.86, 0.1, UnitCurve.easeOut),
        SpringKeyframe(1.0, 0.45, Spring(duration = 0.35, bounce = 0.45)),
    )

    /** The welcome parcel's greeting, played every 4.6 s (SignInView.swift): `lift` in points, `angle` in degrees. */
    data class Greeting(val lift: Double = 0.0, val angle: Double = 0.0)

    val greeting: KeyframeTimeline<Greeting> = KeyframeTimeline(
        Greeting(),
        listOf(
            KeyframeTrack({ it.lift }, { pose, value -> pose.copy(lift = value) }, listOf(CubicKeyframe(-9.0, 2.3), CubicKeyframe(0.0, 2.3))),
            KeyframeTrack({ it.angle }, { pose, value -> pose.copy(angle = value) }, listOf(CubicKeyframe(1.1, 2.3), CubicKeyframe(-0.8, 2.3))),
        ),
    )
}
