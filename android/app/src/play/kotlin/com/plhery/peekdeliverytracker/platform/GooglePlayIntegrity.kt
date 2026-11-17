package com.plhery.peekdeliverytracker.platform

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.android.play.core.integrity.IntegrityManagerFactory
import com.google.android.play.core.integrity.StandardIntegrityException
import com.google.android.play.core.integrity.StandardIntegrityManager.PrepareIntegrityTokenRequest
import com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenProvider
import com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenRequest
import com.plhery.peekdeliverytracker.core.PlayIntegrityError
import com.plhery.peekdeliverytracker.core.PlayIntegrityTokens
import com.plhery.peekdeliverytracker.core.StandardIntegrity
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.suspendCancellableCoroutine

/**
 * Play Integrity's standard requests (StandardIntegrity): a token provider readied for the Cloud project, then a token
 * for each request hash. Its failures are StandardIntegrityException's codes, as PlayIntegrityError.
 */
internal class GooglePlayIntegrity(context: Context) : StandardIntegrity {
    private val application = context.applicationContext
    private val manager by lazy { IntegrityManagerFactory.createStandard(application) }

    @Volatile
    private var provider: StandardIntegrityTokenProvider? = null

    override suspend fun prepare(projectNumber: Long) {
        provider = null
        provider = manager.prepareIntegrityToken(PrepareIntegrityTokenRequest.builder().setCloudProjectNumber(projectNumber).build()).awaited()
    }

    override suspend fun request(requestHash: String): String {
        val provider = provider ?: throw PlayIntegrityError(PlayIntegrityTokens.PROVIDER_INVALID)
        return provider.request(StandardIntegrityTokenRequest.builder().setRequestHash(requestHash).build()).awaited().token()
    }
}

/** The task's result once it completes; a StandardIntegrityException as PlayIntegrityError. */
private suspend fun <T> Task<T>.awaited(): T = suspendCancellableCoroutine { continuation ->
    addOnCompleteListener { task ->
        val error = task.exception
        when {
            error is StandardIntegrityException -> continuation.resumeWithException(PlayIntegrityError(error.errorCode))
            error != null -> continuation.resumeWithException(error)
            task.isCanceled -> continuation.cancel()
            else -> continuation.resume(task.result)
        }
    }
}
