package com.plhery.peekdeliverytracker.platform

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.PushInbox
import java.util.concurrent.TimeUnit

/**
 * The inbox's check in the background, for a phone with no UnifiedPush distributor ([PushRoute]): about every quarter
 * of an hour, WorkManager's shortest period, whenever the phone is online, the notifications waiting are taken and
 * posted as pushes are ([AppNotifications.post]). A check that fails waits for the next one.
 */
class InboxCheck(context: Context, parameters: WorkerParameters) : CoroutineWorker(context, parameters) {
    override suspend fun doWork(): Result {
        val token = PushRoute.inboxToken(applicationContext) ?: return Result.success()
        val messages = try {
            PushInbox.take(BuildSettings.configuration, PushRoute.transport, token)
        } catch (_: Exception) {
            return Result.success()
        }
        val posted = (applicationContext as PeekApplication).posted
        messages.forEach(posted::post)
        return Result.success()
    }

    companion object {
        private const val NAME = "push-inbox"

        fun schedule(context: Context) {
            val request = PeriodicWorkRequestBuilder<InboxCheck>(15, TimeUnit.MINUTES)
                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                .build()
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(NAME, ExistingPeriodicWorkPolicy.KEEP, request)
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(NAME)
        }
    }
}
