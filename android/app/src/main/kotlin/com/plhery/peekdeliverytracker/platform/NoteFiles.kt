package com.plhery.peekdeliverytracker.platform

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import androidx.core.content.FileProvider
import com.plhery.peekdeliverytracker.core.AppClock
import com.plhery.peekdeliverytracker.core.NoteFileError
import com.plhery.peekdeliverytracker.core.NoteFileProblem
import com.plhery.peekdeliverytracker.core.NoteFileUpload
import com.plhery.peekdeliverytracker.core.ParcelNotes
import com.plhery.peekdeliverytracker.core.api.PackageFileType
import com.plhery.peekdeliverytracker.core.chosenNoteFileType
import com.plhery.peekdeliverytracker.core.noteFileName
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.nio.ByteBuffer
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.UUID
import kotlin.math.max
import kotlin.math.roundToInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * A photo or PDF chosen for a note, as the web's `prepareNoteFile` readies one: a PDF as it is; a photo that fits
 * 2,560 px and 10 MB as it is, a larger one made to fit and written again as JPEG at 85 (a PNG kept as PNG while that
 * stays under 10 MB, so that a screenshot's text stays sharp), and a HEIC or HEIF one always as JPEG, which the service
 * takes where it refuses HEIC. Anything else, or anything still over 10 MB, is refused with its reason.
 */
internal object NoteFilePreparation {
    /** More than any photo a phone takes: one over it is said too big before it is read whole. */
    private const val READ_LIMIT = 64L * 1024 * 1024

    /** Reads the file at `uri` and readies it, off the main thread. */
    suspend fun prepare(context: Context, uri: Uri): NoteFileUpload = withContext(Dispatchers.IO) {
        val resolver = context.contentResolver
        var name = uri.lastPathSegment?.substringAfterLast('/').orEmpty()
        var size: Long? = null
        try {
            resolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE), null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    cursor.getString(0)?.let { name = it }
                    if (!cursor.isNull(1)) size = cursor.getLong(1)
                }
            }
        } catch (_: Exception) {
            // A provider that can't say keeps the address's own name.
        }
        val type = resolver.getType(uri)
        val known = size
        if (known != null && known > READ_LIMIT) throw NoteFileError(NoteFileProblem.TooBig, size = known)
        val bytes = try {
            resolver.openInputStream(uri)?.use { input -> input.readBytes() } ?: throw NoteFileError(NoteFileProblem.Unreadable)
        } catch (error: IOException) {
            throw NoteFileError(NoteFileProblem.Unreadable)
        } catch (error: SecurityException) {
            throw NoteFileError(NoteFileProblem.Unreadable)
        }
        prepare(bytes, name, type)
    }

    /** Readies `bytes`, named `name` and of the type `type` says, if it says one. */
    fun prepare(bytes: ByteArray, name: String, type: String?): NoteFileUpload {
        val kind = chosenNoteFileType(type, name) ?: throw NoteFileError(NoteFileProblem.Type)
        if (kind == PackageFileType.ApplicationPdf.rawValue) {
            if (bytes.size > ParcelNotes.FILE_MAX_BYTES) throw NoteFileError(NoteFileProblem.TooBig, size = bytes.size.toLong())
            return NoteFileUpload(bytes, noteFileName(name, PackageFileType.ApplicationPdf), PackageFileType.ApplicationPdf)
        }
        val heic = kind == "image/heic" || kind == "image/heif"
        val photo = PackageFileType.fromRawValue(kind)
        if (photo == null && !heic) throw NoteFileError(NoteFileProblem.Type)
        var width = 0
        var height = 0
        var scale = 1.0
        val bitmap = try {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(ByteBuffer.wrap(bytes))) { decoder, info, _ ->
                width = info.size.width
                height = info.size.height
                scale = minOf(1.0, ParcelNotes.PHOTO_MAX_SIDE.toDouble() / max(width, height).coerceAtLeast(1))
                decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
                if (scale < 1.0) decoder.setTargetSize((width * scale).roundToInt().coerceAtLeast(1), (height * scale).roundToInt().coerceAtLeast(1))
            }
        } catch (_: Exception) {
            null
        }
        if (bitmap == null) {
            // One the phone can't read goes as it is, for the service to read; a HEIC one the service would refuse.
            if (heic || photo == null) throw NoteFileError(NoteFileProblem.Unreadable)
            if (bytes.size > ParcelNotes.FILE_MAX_BYTES) throw NoteFileError(NoteFileProblem.TooBig, size = bytes.size.toLong())
            return NoteFileUpload(bytes, noteFileName(name, photo), photo)
        }
        try {
            if (!heic && photo != null && scale == 1.0 && bytes.size <= ParcelNotes.FILE_MAX_BYTES) {
                return NoteFileUpload(bytes, noteFileName(name, photo), photo, width, height)
            }
            if (photo == PackageFileType.ImagePng) {
                val png = encode(bitmap, Bitmap.CompressFormat.PNG)
                if (png.size <= ParcelNotes.FILE_MAX_BYTES) {
                    return NoteFileUpload(png, noteFileName(name, PackageFileType.ImagePng), PackageFileType.ImagePng, bitmap.width, bitmap.height)
                }
            }
            val jpeg = encode(bitmap, Bitmap.CompressFormat.JPEG)
            if (jpeg.size > ParcelNotes.FILE_MAX_BYTES) throw NoteFileError(NoteFileProblem.TooBig, size = jpeg.size.toLong())
            return NoteFileUpload(jpeg, noteFileName(name, PackageFileType.ImageJpeg), PackageFileType.ImageJpeg, bitmap.width, bitmap.height)
        } finally {
            bitmap.recycle()
        }
    }

    private fun encode(bitmap: Bitmap, format: Bitmap.CompressFormat): ByteArray {
        val out = ByteArrayOutputStream()
        if (!bitmap.compress(format, ParcelNotes.PHOTO_QUALITY, out)) throw NoteFileError(NoteFileProblem.Unreadable)
        return out.toByteArray()
    }

    /** A photo's picture no wider or taller than `side` pixels, for a tile or the viewer; null for one that can't be read. */
    fun picture(bytes: ByteArray, side: Int): Bitmap? = try {
        ImageDecoder.decodeBitmap(ImageDecoder.createSource(ByteBuffer.wrap(bytes))) { decoder, info, _ ->
            val longest = max(info.size.width, info.size.height).coerceAtLeast(1)
            if (longest > side) {
                decoder.setTargetSize((info.size.width * side / longest).coerceAtLeast(1), (info.size.height * side / longest).coerceAtLeast(1))
            }
            decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
        }
    } catch (_: Exception) {
        BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
}

/**
 * A note's files handed to other apps through the app's FileProvider (res/xml/exports.xml): each written to a folder of
 * its own in `cacheDir/notes`, for the system's PDF viewer, the share sheet or the camera. Those over a day old go as
 * the next is written; a camera's photo goes once it has been read.
 */
internal object NoteFileHandOut {
    private const val KEPT_MILLIS = 24 * 60 * 60 * 1000L

    private fun folder(context: Context) = File(context.cacheDir, "notes")

    private fun uri(context: Context, file: File): Uri = FileProvider.getUriForFile(context, "${context.packageName}.exports", file)

    /** `bytes` as `name`, readable through the FileProvider. */
    suspend fun write(context: Context, name: String, bytes: ByteArray): Uri = withContext(Dispatchers.IO) {
        val root = folder(context)
        val cutoff = AppClock.now().toEpochMilli() - KEPT_MILLIS
        root.listFiles()?.filter { it.lastModified() < cutoff }?.forEach { it.deleteRecursively() }
        val own = File(root, UUID.randomUUID().toString())
        if (!own.mkdirs()) throw IOException("No folder for the file")
        val safe = File(name).name.ifEmpty { "file" }
        val file = File(own, safe)
        file.writeBytes(bytes)
        uri(context, file)
    }

    /** Where the camera writes the photo it takes, through the FileProvider; and the file, to read it back. */
    fun cameraTarget(context: Context): Pair<File, Uri> {
        val own = File(folder(context), "camera").apply { mkdirs() }
        val file = File(own, "${UUID.randomUUID()}.jpg")
        return file to uri(context, file)
    }

    /** The camera's photo's name: when it was taken, as the phone's camera names its own. */
    fun cameraName(now: LocalDateTime = LocalDateTime.ofInstant(AppClock.now(), ZoneId.systemDefault())): String = "IMG_${now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))}.jpg"

    /** Whether the phone has a camera to take a photo with. */
    fun hasCamera(context: Context): Boolean = context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)

    /** Opens a PDF in the system's viewer: whether one took it. */
    fun view(context: Context, uri: Uri, type: String): Boolean {
        val intent = Intent(Intent.ACTION_VIEW).setDataAndType(uri, type).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        return try {
            context.startActivity(intent)
            true
        } catch (_: ActivityNotFoundException) {
            false
        }
    }

    /** Hands the file to the system's share sheet (`ACTION_SEND`). */
    fun share(context: Context, uri: Uri, type: String, name: String) {
        val send = Intent(Intent.ACTION_SEND).setType(type).putExtra(Intent.EXTRA_STREAM, uri).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        send.clipData = ClipData.newRawUri(name, uri)
        try {
            context.startActivity(Intent.createChooser(send, null).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION))
        } catch (_: ActivityNotFoundException) {
            // No app to take it: nothing opens, as an empty share sheet would offer nothing.
        }
    }

    /** Saves the file in Downloads (MediaStore, which needs no permission): whether it was saved. */
    suspend fun saveToDownloads(context: Context, name: String, type: String, bytes: ByteArray): Boolean = withContext(Dispatchers.IO) {
        val resolver = context.contentResolver
        val values = ContentValues().apply {
            put(MediaStore.Downloads.DISPLAY_NAME, name)
            put(MediaStore.Downloads.MIME_TYPE, type)
            put(MediaStore.Downloads.IS_PENDING, 1)
        }
        val uri = try {
            resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values)
        } catch (_: Exception) {
            null
        } ?: return@withContext false
        try {
            resolver.openOutputStream(uri)?.use { it.write(bytes) } ?: throw IOException("No stream")
            resolver.update(uri, ContentValues().apply { put(MediaStore.Downloads.IS_PENDING, 0) }, null, null)
            true
        } catch (_: Exception) {
            resolver.delete(uri, null, null)
            false
        }
    }
}
