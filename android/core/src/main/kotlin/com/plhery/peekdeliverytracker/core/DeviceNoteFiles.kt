package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.PackageFile
import com.plhery.peekdeliverytracker.core.api.PackageFileType
import com.plhery.peekdeliverytracker.core.api.UuidSerializer
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.time.Instant
import java.util.UUID
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

/**
 * A file of a guest's note, kept on this phone beside its link (`linkId`). Once an account keeps the parcel, it is
 * marked for the account's parcel (`packageId`) and that account (`account`, none on a file marked before it was noted)
 * until it has been sent there. `refusedAt` (ms) is when the account last had no room for it, `refusals` how many times
 * in a row: it is offered again a day later, then twice as long after each refusal ([DeviceNoteFiles.due]).
 */
@Serializable
data class DeviceNoteFile(
    @Serializable(with = UuidSerializer::class) val id: UUID,
    val linkId: String,
    val name: String,
    val type: String,
    val size: Long,
    val width: Int? = null,
    val height: Int? = null,
    val createdAt: String,
    @Serializable(with = UuidSerializer::class) val packageId: UUID? = null,
    val refusedAt: Long? = null,
    @Serializable(with = UuidSerializer::class) val account: UUID? = null,
    val refusals: Int? = null,
) {
    val fileType: PackageFileType get() = PackageFileType.fromRawValue(type) ?: PackageFileType.ImageJpeg

    /** The file as the parcel's note lists it, at an address that only this phone reads. */
    val packageFile: PackageFile
        get() = PackageFile(
            id = id, name = name, contentType = fileType, size = size.toInt(), width = width, height = height,
            createdAt = createdAt, url = NoteFileAddress.DEVICE + id, thumbnailUrl = null, expiresAt = NoteFileAddress.NEVER,
        )

    /** Leaves out the link, which opens the parcel to anyone, and the name. */
    override fun toString(): String = "DeviceNoteFile(id=$id, type=$type, size=$size, sending=${packageId != null})"
}

/**
 * The files of a guest's notes, in a folder of the app's own (`filesDir/ParcelNotes`): each file under its id, and
 * `files.json` listing them. A parcel holds five at most, 10 MB each, and the phone 200 MB in all, or what room it
 * has left. Forgetting a parcel deletes its files; a file on its way to an account stays until it has arrived, or until
 * the service says it never will.
 */
class DeviceNoteFiles(
    private val directory: File,
    private val freeSpace: () -> Long = { directory.usableSpace.takeIf { it > 0 } ?: directory.parentFile?.usableSpace ?: Long.MAX_VALUE },
    private val now: () -> Instant = AppClock::now,
) {
    private val index = File(directory, INDEX)

    @Volatile
    private var files: List<DeviceNoteFile> = load()

    /** Every file kept here, the oldest first. */
    val all: List<DeviceNoteFile> get() = files

    /** The files of the note of link `linkId`. */
    fun of(linkId: String): List<DeviceNoteFile> = files.filter { it.packageId == null && it.linkId.equalsCanonically(linkId) }

    /** The files an account's parcel is still to receive. */
    val sending: List<DeviceNoteFile> get() = files.filter { it.packageId != null }

    /**
     * Those to send now to `account`'s parcels: another account's wait for it to sign in again. One the account had no
     * room for isn't sent again before a day has passed, then two, four, eight and sixteen.
     */
    fun due(account: UUID?): List<DeviceNoteFile> {
        val at = now().toEpochMilli()
        return sending.filter { file ->
            (file.account == null || file.account == account) &&
                file.refusedAt.let { it == null || at - it >= wait(file.refusals) }
        }
    }

    /**
     * Notes that the account had no room for file `id`, now. After [REFUSALS_KEPT] refusals in a row, about a month,
     * the file leaves the phone: the account's room isn't coming back.
     */
    @Synchronized
    fun refused(id: UUID) {
        val file = files.firstOrNull { it.id == id } ?: return
        val refusals = (file.refusals ?: if (file.refusedAt == null) 0 else 1) + 1
        if (refusals > REFUSALS_KEPT) return remove(id)
        val at = now().toEpochMilli()
        save(files.map { if (it.id == id) it.copy(refusedAt = at, refusals = refusals) else it })
    }

    /** Where the bytes of file `id` are, while it is kept here. */
    fun file(id: UUID): File? = files.firstOrNull { it.id == id }?.let { File(directory, it.id.toString()) }?.takeIf { it.isFile }

    fun bytes(id: UUID): ByteArray? = file(id)?.let { file ->
        try {
            file.readBytes()
        } catch (_: IOException) {
            null
        }
    }

    /** Keeps `upload` with the note of link `linkId`, or says why it can't ([NoteFileError]). */
    @Synchronized
    fun add(linkId: String, upload: NoteFileUpload): DeviceNoteFile {
        if (of(linkId).size >= ParcelNotes.FILES_PER_PARCEL) throw NoteFileError(NoteFileProblem.ParcelFull)
        val size = upload.bytes.size.toLong()
        if (size > ParcelNotes.FILE_MAX_BYTES) throw NoteFileError(NoteFileProblem.TooBig, size)
        if (files.sumOf { it.size } + size > ParcelNotes.FILES_TOTAL_BYTES || freeSpace() < size + ROOM_LEFT) {
            throw NoteFileError(NoteFileProblem.DeviceFull)
        }
        val entry = DeviceNoteFile(
            id = UUID.randomUUID(), linkId = linkId, name = noteFileName(upload.name, upload.type), type = upload.type.rawValue,
            size = size, width = upload.width, height = upload.height, createdAt = DateParser.isoString(now()),
        )
        try {
            write(File(directory, entry.id.toString()), upload.bytes)
        } catch (_: IOException) {
            File(directory, entry.id.toString()).delete()
            throw NoteFileError(NoteFileProblem.DeviceFull)
        }
        save(files + entry)
        return entry
    }

    @Synchronized
    fun remove(id: UUID) {
        if (files.none { it.id == id }) return
        File(directory, id.toString()).delete()
        save(files.filter { it.id != id })
    }

    /** Deletes the files of link `linkId`'s note, but those on their way to an account. */
    @Synchronized
    fun forget(linkId: String) {
        val gone = of(linkId)
        if (gone.isEmpty()) return
        for (file in gone) File(directory, file.id.toString()).delete()
        save(files - gone.toSet())
    }

    /** Marks link `linkId`'s files for parcel `packageId` of `account`; returns how many. */
    @Synchronized
    fun handOver(linkId: String, packageId: UUID, account: UUID? = null): Int =
        relabel(linkId) { it.copy(packageId = packageId, account = account) }

    /** Moves the files of a link that gave way to another of the same parcel. */
    @Synchronized
    fun move(from: String, to: String): Int = if (from.equalsCanonically(to)) 0 else relabel(from) { it.copy(linkId = to) }

    private fun relabel(linkId: String, change: (DeviceNoteFile) -> DeviceNoteFile): Int {
        val changed = of(linkId)
        if (changed.isEmpty()) return 0
        val ids = changed.map { it.id }.toSet()
        save(files.map { if (it.id in ids) change(it) else it })
        return changed.size
    }

    private fun save(next: List<DeviceNoteFile>) {
        files = next
        try {
            if (next.isEmpty()) {
                index.delete()
            } else {
                write(index, json.encodeToString(serializer, next).encodeToByteArray())
            }
        } catch (_: IOException) {
            // The list stays as it is in memory; the next change writes it again.
        }
    }

    private fun load(): List<DeviceNoteFile> {
        val listed = try {
            if (index.isFile) json.decodeFromString(serializer, index.readText()) else emptyList()
        } catch (_: Exception) {
            emptyList()
        }
        // A file whose bytes are gone is no file; bytes no entry names are left over from an interrupted change.
        val kept = listed.filter { File(directory, it.id.toString()).isFile }
        directory.listFiles()?.forEach { file ->
            if (file.name != INDEX && kept.none { it.id.toString() == file.name }) file.delete()
        }
        return kept
    }

    /** Writes beside `file`, then moves it over, so a reader finds the old bytes or the new ones. */
    private fun write(file: File, data: ByteArray) {
        directory.mkdirs()
        val temporary = File(directory, "${file.name}.tmp")
        temporary.writeBytes(data)
        Files.move(temporary.toPath(), file.toPath(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING)
    }

    companion object {
        /** How long a file the account had no room for waits before it is offered again, the first time. */
        const val REFUSED_WAIT_MS = 24L * 60 * 60 * 1000

        /** How many refusals in a row a file waits through. */
        const val REFUSALS_KEPT = 5

        private fun wait(refusals: Int?): Long = REFUSED_WAIT_MS shl (maxOf(refusals ?: 1, 1) - 1).coerceAtMost(REFUSALS_KEPT)

        private const val INDEX = "files.json"

        /** Room the phone keeps besides a new file, so the app's other files can still be written. */
        private const val ROOM_LEFT = 20L * 1_048_576

        private val json = Json { ignoreUnknownKeys = true }
        private val serializer = ListSerializer(DeviceNoteFile.serializer())
    }
}
