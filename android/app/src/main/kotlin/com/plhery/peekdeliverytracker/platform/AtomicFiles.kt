package com.plhery.peekdeliverytracker.platform

import android.content.Context
import android.net.Uri
import android.util.AtomicFile
import androidx.core.content.FileProvider
import com.plhery.peekdeliverytracker.core.DeviceParcelFile
import com.plhery.peekdeliverytracker.core.ExportFiles
import com.plhery.peekdeliverytracker.core.ParcelCacheFiles
import java.io.File
import java.io.IOException
import java.util.Locale
import java.util.UUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * A file replaced whole, as Swift's `.atomic` write replaces one: written beside it, synced, then renamed over it
 * (`AtomicFile`), so a reader finds the old bytes or the new ones, never part of either.
 */
class AtomicBytesFile(val file: File) {
    private val atomic = AtomicFile(file)

    /** The bytes, or nil when there is no file or it cannot be read. */
    fun read(): ByteArray? = try {
        atomic.readFully()
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
        val stream = atomic.startWrite()
        try {
            stream.write(data)
        } catch (e: IOException) {
            atomic.failWrite(stream)
            throw e
        }
        atomic.finishWrite(stream)
    }

    /** Removes the file, and any write left half-done beside it. */
    fun delete() {
        atomic.delete()
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
