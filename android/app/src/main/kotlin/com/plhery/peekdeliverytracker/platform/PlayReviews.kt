package com.plhery.peekdeliverytracker.platform

import android.app.Activity
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.ktx.requestReview
import com.plhery.peekdeliverytracker.core.ReviewRequester

/**
 * The system's prompt for a rating over `activity`: Play In-App Review, where the iPhone asks StoreKit's
 * `requestReview`. Play decides whether anything shows, and says nothing of it. Without the Play Store, an outdated one
 * or an app not installed from Play, the review info doesn't come: [prepare] throws, and nothing is asked.
 */
class PlayReviews(
    private val activity: Activity,
    private val manager: ReviewManager = ReviewManagerFactory.create(activity),
) : ReviewRequester {
    /** The review info [prepare] fetched, which one launch uses. */
    private var info: ReviewInfo? = null

    override suspend fun prepare(): Boolean {
        info = manager.requestReview()
        return true
    }

    override fun launch() {
        val info = info ?: return
        this.info = null
        if (activity.isFinishing || activity.isDestroyed) return
        manager.launchReviewFlow(activity, info)
    }
}
