package com.plhery.peekdeliverytracker.platform

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.plhery.peekdeliverytracker.core.DeviceParcel
import com.plhery.peekdeliverytracker.core.DeviceParcelFile
import com.plhery.peekdeliverytracker.core.DeviceParcelStorage
import com.plhery.peekdeliverytracker.core.ExportFiles
import com.plhery.peekdeliverytracker.core.ParcelCacheFiles
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.Locale
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * A file replaced whole, as Swift's `.atomic` write replaces one: written to `<name>.new` beside it, synced, closed,
 * then renamed over it, so a reader finds the old bytes or the new ones, never part of either. Each step that fails
 * throws, where `android.util.AtomicFile.finishWrite` only logs a failed sync, close or rename. Writes to one path
 * take turns.
 */
class AtomicBytesFile(val file: File, private val calls: FileCalls = FileCalls.System) {
    private val temporary = File(file.path + ".new")

    /** The bytes, or nil when there is no file or it cannot be read. */
    fun read(): ByteArray? = try {
        file.readBytes()
    } catch (_: IOException) {
        null
    } catch (_: SecurityException) {
        null
    }

    /** Throws when the bytes could not be written, leaving the old file; makes the folder the first time. */
    fun write(data: ByteArray) {
        val folder = file.parentFile
        if (folder != null && !folder.isDirectory && !folder.mkdirs() && !folder.isDirectory) {
            throw IOException("Could not make ${folder.name}")
        }
        synchronized(lock(file)) {
            try {
                val stream = calls.open(temporary)
                try {
                    stream.write(data)
                    calls.sync(stream)
                } catch (e: IOException) {
                    try {
                        stream.close()
                    } catch (_: IOException) {
                    }
                    throw e
                }
                calls.close(stream)
                calls.rename(temporary, file)
            } catch (e: IOException) {
                temporary.delete()
                throw e
            }
        }
    }

    /** Removes the file, and any write left half-done beside it. */
    fun delete() {
        synchronized(lock(file)) {
            file.delete()
            temporary.delete()
        }
    }

    private companion object {
        val locks = ConcurrentHashMap<String, Any>()

        fun lock(file: File): Any = locks.computeIfAbsent(file.absolutePath) { Any() }
    }
}

/** The system calls a write is made of, each throwing when it fails; a test makes one fail. */
interface FileCalls {
    fun open(file: File): FileOutputStream

    /** fsync(2): the bytes are on the disk once it returns. */
    fun sync(stream: FileOutputStream)

    fun close(stream: FileOutputStream)

    /** rename(2), which replaces `to` at once. */
    fun rename(from: File, to: File)

    object System : FileCalls {
        override fun open(file: File): FileOutputStream = FileOutputStream(file)

        override fun sync(stream: FileOutputStream) = stream.fd.sync()

        override fun close(stream: FileOutputStream) = stream.close()

        override fun rename(from: File, to: File) {
            Files.move(from.toPath(), to.toPath(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING)
        }
    }
}

/** The device's parcels, in `filesDir/DeviceParcels/parcels.json` (DeviceParcelFile). */
class DeviceParcelsFile(file: File) : DeviceParcelFile {
    private val atomic = AtomicBytesFile(file)

    override fun read(): ByteArray? = atomic.read()

    override fun write(data: ByteArray) = atomic.write(data)

    override fun delete() = atomic.delete()

    companion object {
        fun of(context: Context): DeviceParcelsFile = DeviceParcelsFile(File(File(context.filesDir, "DeviceParcels"), "parcels.json"))
    }
}

/**
 * DeviceParcels' storage with each save handed to one thread of its own, in the order they come, so that a follow
 * never seals its keys and syncs the file on the main thread. Each save is the whole list with its keys, so the last
 * one made is the one kept, and the keys still go before the file within it; nobody waits for one, as
 * DeviceParcelFiles drops a failure the way the iPhone's `try?` does. `load` reads at once: DeviceParcels reads once,
 * as it is made, off the main thread.
 */
class QueuedDeviceParcelStorage(
    private val storage: DeviceParcelStorage,
    private val queue: Executor = Executors.newSingleThreadExecutor { task -> Thread(task, "Peek device parcels").apply { isDaemon = true } },
) : DeviceParcelStorage {
    override fun load(): DeviceParcelStorage.Contents = storage.load()

    override fun save(parcels: List<DeviceParcel>, keys: Map<String, String>) {
        queue.execute { storage.save(parcels, keys) }
    }
}

/** Each account's list, in `filesDir/ParcelCache/<user ID>.json` with the ID in upper case, as `uuidString` writes it. */
class ParcelCacheFolder(private val folder: File) : ParcelCacheFiles {
    private fun file(userId: UUID) = AtomicBytesFile(File(folder, "${userId.toString().uppercase(Locale.ROOT)}.json"))

    override fun read(userId: UUID): ByteArray? = file(userId).read()

    override fun write(userId: UUID, data: ByteArray) = file(userId).write(data)

    override fun delete(userId: UUID) = file(userId).delete()

    companion object {
        fun of(context: Context): ParcelCacheFolder = ParcelCacheFolder(File(context.filesDir, "ParcelCache"))
    }
}

/**
 * The account's export, written whole in `cacheDir/exports/<name>` over any of the same name, for the share sheet,
 * which takes it through the app's FileProvider (`uri`).
 */
class CacheExportFiles(private val folder: File) : ExportFiles {
    override suspend fun write(name: String, data: ByteArray): File = withContext(Dispatchers.IO) {
        if (name.isEmpty() || name != File(name).name || name == "." || name == "..") throw IOException("Not a file name: $name")
        val file = File(folder, name)
        AtomicBytesFile(file).write(data)
        file
    }

    companion object {
        fun of(context: Context): CacheExportFiles = CacheExportFiles(exportsFolder(context))

        private fun exportsFolder(context: Context) = File(context.cacheDir, "exports")

        /** The address the share intent hands out for an export: the FileProvider the manifest declares (res/xml/exports.xml). */
        fun uri(context: Context, file: File): Uri = FileProvider.getUriForFile(context, "${context.packageName}.exports", file)
    }
}
