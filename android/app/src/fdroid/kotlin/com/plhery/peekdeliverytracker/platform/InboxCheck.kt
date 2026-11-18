package com.plhery.peekdeliverytracker.platform

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.PushAddress
import com.plhery.peekdeliverytracker.core.PushInbox
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.NativePushTransport
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

/**
 * The inbox's check in the background, for a phone with no UnifiedPush distributor ([PushRoute]): about every quarter
 * of an hour, WorkManager's shortest period, whenever the phone is online, the notifications waiting are taken and
 * posted as pushes are ([AppNotifications.post]). A check that fails waits for the next one. With notifications turned
 * off on the phone, nothing is taken, as nothing could be posted, and the checks stop until the app asks for the
 * address again with them on ([PushRoute.asked]); the server keeps what waits for a day.
 */
class InboxCheck(context: Context, parameters: WorkerParameters) : CoroutineWorker(context, parameters) {
    override suspend fun doWork(): Result {
        if (!NotificationManagerCompat.from(applicationContext).areNotificationsEnabled()) {
            cancel(applicationContext)
            return Result.success()
        }
        val app = applicationContext as PeekApplication
        val route = PushRoute.existingAddress(app) ?: return Result.success()
        return reconcile(app, route, register = {
            withContext(Dispatchers.Main) {
                app.makeContainer()
                val stores = app.container.filterNotNull().first()
                stores.session.stateFlow.first { it != AuthenticationState.Loading }
                val language = stores.localizer.language
                val account = stores.parcels.forwardNativePushToken(route.text, language)
                val guests = stores.parcels.registerDeviceAlerts(route.text, language)
                account && guests
            }
        }, drain = {
            PushInbox.take(BuildSettings.configuration, PushRoute.transport, route.token).forEach(app.posted::post)
        })
    }

    companion object {
        private const val NAME = "push-inbox"

        internal suspend fun reconcile(
            context: Context,
            route: PushAddress,
            register: suspend () -> Boolean,
            drain: suspend () -> Unit,
        ): Result {
            val changing = !PushRoute.acknowledged(context, route)
            if (!changing && route.transport != NativePushTransport.Inbox) return Result.success()
            val registered = !changing || register()
            if (PushRoute.existingAddress(context) != route) return Result.retry()
            // A previously registered inbox may still receive messages while the new route is being registered.
            if (!registered && !PushRoute.acknowledged(context, PushAddress(route.token, NativePushTransport.Inbox))) return Result.retry()
            try {
                drain()
            } catch (error: CancellationException) {
                throw error
            } catch (_: Exception) {
                return Result.retry()
            }
            if (!registered) return Result.retry()
            if (changing) PushRoute.acknowledge(context, route)
            return Result.success()
        }

        fun schedule(context: Context) {
            val request = PeriodicWorkRequestBuilder<InboxCheck>(15, TimeUnit.MINUTES)
                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                .build()
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(NAME, ExistingPeriodicWorkPolicy.KEEP, request)
        }

        fun handover(context: Context) {
            val request = OneTimeWorkRequestBuilder<InboxCheck>()
                .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
                .build()
            WorkManager.getInstance(context).enqueueUniqueWork("push-handover", ExistingWorkPolicy.REPLACE, request)
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork("push-handover")
            WorkManager.getInstance(context).cancelUniqueWork(NAME)
        }
    }
}
