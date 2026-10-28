package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.Parcel
import java.util.UUID
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask
import kotlinx.serialization.builtins.ListSerializer

// From ParcelStore.swift: the account's list kept on the device, so that it opens on its parcels offline.

/**
 * The files each account's list is kept in, which the app implements: on the iPhone `ParcelCache/<user ID>.json` in
 * Application Support, readable once the phone was first unlocked after a restart. On Android a file per account in
 * the app's own storage (`filesDir`, which the phone encrypts with the user's credentials, not the device-protected
 * storage), such as `filesDir/ParcelCache/<user ID>.json` with the ID as `uuidString` writes it, its folder made when
 * the first one is written. None of them goes into a backup (`dataExtractionRules`): they hold what an account
 * showed, and leave the device with it.
 *
 * Called on the cache's own thread, never the main one, one call at a time.
 */
interface ParcelCacheFiles {
    /** The bytes of the account's file, or nil when there is none or it cannot be read (`AtomicFile.readFully`). */
    fun read(userId: UUID): ByteArray?

    /**
     * Replaces the account's file with `data` at once: written beside it, then renamed over it (`AtomicFile`), so a
     * reader finds the old bytes or the new ones, never part of either. Throws when it could not be written.
     */
    fun write(userId: UUID, data: ByteArray)

    /** Removes the account's file, if there is one. */
    fun delete(userId: UUID)
}

/**
 * Encoding and writing happen on one serial queue, off the main thread and in call order, so a sign-out's delete
 * always runs after any earlier save. The lists are written as the iPhone's JSONEncoder.deliveryTracker writes its
 * models (`JsonEncoder` with `swiftKeys`), and read as JSONDecoder.deliveryTracker reads them.
 */
internal class ParcelCache(private val files: ParcelCacheFiles, private val queue: Executor = sharedQueue) {
    fun save(parcels: List<Parcel>, userId: UUID) {
        queue.execute {
            try {
                files.write(userId, encoder.encode(format, parcels))
            } catch (_: Exception) {
                remove(userId)
            }
        }
    }

    /** Waits for pending writes, so it returns the latest saved list. */
    fun load(userId: UUID): List<Parcel>? {
        val task = FutureTask {
            val data = try {
                files.read(userId)
            } catch (_: Exception) {
                null
            }
            data?.let {
                try {
                    decodeSwiftKeys(format, it)
                } catch (_: Exception) {
                    null
                }
            }
        }
        queue.execute(task)
        return try {
            task.get()
        } catch (_: ExecutionException) {
            null
        } catch (_: InterruptedException) {
            Thread.currentThread().interrupt()
            null
        }
    }

    fun delete(userId: UUID) {
        queue.execute { remove(userId) }
    }

    private fun remove(userId: UUID) {
        try {
            files.delete(userId)
        } catch (_: Exception) {
        }
    }

    companion object {
        private val format = ListSerializer(Parcel.serializer())
        private val encoder = JsonEncoder(sortedKeys = true, swiftKeys = true)

        /** The iPhone's one queue for every cache: a single daemon thread, which nothing waits for at exit. */
        private val sharedQueue: Executor = Executors.newSingleThreadExecutor { task ->
            Thread(task, "com.plhery.PeekDeliveryTracker.parcel-cache").apply { isDaemon = true }
        }
    }
}
