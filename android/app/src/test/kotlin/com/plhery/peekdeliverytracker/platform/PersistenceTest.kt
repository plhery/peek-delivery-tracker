package com.plhery.peekdeliverytracker.platform

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.DeviceParcel
import com.plhery.peekdeliverytracker.core.DeviceParcelStorage
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Collections
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith

/** The UserDefaults stand-in and the files the parcels, caches and exports are kept in. */
@RunWith(AndroidJUnit4::class)
class PersistenceTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    private fun defaults(name: String = "defaults-test") =
        PreferencesDefaults(context.getSharedPreferences(name, Context.MODE_PRIVATE))

    @Test
    fun eachGetterFindsOnlyWhatWasSavedAsItsOwnType() {
        val defaults = defaults()
        defaults.set("demo", forKey = "sdt.native.experience.v1")
        defaults.set(true, forKey = "sdt.flag")
        defaults.set(byteArrayOf(0, 1, -1, 127), forKey = "sdt.data")

        assertEquals("demo", defaults.string("sdt.native.experience.v1"))
        assertTrue(defaults.bool("sdt.flag"))
        assertArrayEquals(byteArrayOf(0, 1, -1, 127), defaults.data("sdt.data"))

        // A bool of another type is false; a string or data of another type is nil.
        assertFalse(defaults.bool("sdt.native.experience.v1"))
        assertFalse(defaults.bool("sdt.data"))
        assertNull(defaults.string("sdt.flag"))
        assertNull(defaults.string("sdt.data"))
        assertNull(defaults.data("sdt.flag"))
        assertNull(defaults.data("sdt.native.experience.v1"))

        // Nothing saved: false and nil, and no object.
        assertFalse(defaults.bool("missing"))
        assertNull(defaults.string("missing"))
        assertNull(defaults.data("missing"))
        assertFalse(defaults.hasObject("missing"))
        // A false is an object, as `object(forKey:) != nil` sees it.
        defaults.set(false, forKey = "sdt.off")
        assertTrue(defaults.hasObject("sdt.off"))
        assertFalse(defaults.bool("sdt.off"))
    }

    @Test
    fun aValueReplacesAnyOtherUnderItsKeyAndRemovalForgetsIt() {
        val defaults = defaults()
        defaults.set(byteArrayOf(1, 2), forKey = "key")
        defaults.set("text", forKey = "key")
        assertNull(defaults.data("key"))
        assertEquals("text", defaults.string("key"))
        defaults.set(ByteArray(0), forKey = "key")
        assertArrayEquals(ByteArray(0), defaults.data("key"))
        assertNull(defaults.string("key"))

        defaults.removeObject("key")
        assertFalse(defaults.hasObject("key"))
        assertNull(defaults.data("key"))
        defaults.removeObject("key")
    }

    @Test
    fun valuesOutliveTheInstanceAndTheStandardFileIsTheAppearances() {
        defaults("kept").set("fr", forKey = "deliveryTrackerLocale")
        assertEquals("fr", defaults("kept").string("deliveryTrackerLocale"))

        AppearanceStore(context.getSharedPreferences(AppearanceStore.PREFERENCES, Context.MODE_PRIVATE)).set(AppAppearance.Dark)
        assertEquals("dark", PreferencesDefaults.standard(context).string("sdt.appearance.v1"))
        // The app group's file is apart from it.
        assertNull(PreferencesDefaults.appGroup(context, "group.test").string("sdt.appearance.v1"))
    }

    @Test
    fun aFileIsReplacedWholeAndMadeWithItsFolder() {
        val file = File(context.filesDir, "Atomic/inner/value.json")
        val atomic = AtomicBytesFile(file)
        assertNull(atomic.read())
        atomic.write("first".toByteArray())
        assertEquals("first", file.readText())
        atomic.write("second, longer".toByteArray())
        assertEquals("second, longer", atomic.read()!!.decodeToString())
        // Nothing left beside it.
        assertEquals(listOf("value.json"), file.parentFile!!.list()!!.toList())

        atomic.delete()
        assertFalse(file.exists())
        assertNull(atomic.read())
        atomic.delete()
    }

    @Test
    fun aWriteThatFailsAtAnyStepThrowsAndLeavesTheOldFile() {
        val file = File(context.filesDir, "Failing/parcels.json")
        AtomicBytesFile(file).write("old".toByteArray())
        for (step in listOf("open", "sync", "close", "rename")) {
            val failing = object : FileCalls {
                fun fail(name: String) {
                    if (name == step) throw IOException("$name failed")
                }

                override fun open(file: File): FileOutputStream {
                    fail("open")
                    return FileCalls.System.open(file)
                }

                override fun sync(stream: FileOutputStream) {
                    fail("sync")
                    FileCalls.System.sync(stream)
                }

                override fun close(stream: FileOutputStream) {
                    fail("close")
                    FileCalls.System.close(stream)
                }

                override fun rename(from: File, to: File) {
                    fail("rename")
                    FileCalls.System.rename(from, to)
                }
            }
            try {
                AtomicBytesFile(file, failing).write("new".toByteArray())
                fail("Expected a failed $step to throw")
            } catch (error: IOException) {
                assertEquals("$step failed", error.message)
            }
            assertEquals(step, "old", AtomicBytesFile(file).read()!!.decodeToString())
            // Nothing half-written is left beside it.
            assertEquals(step, listOf("parcels.json"), file.parentFile!!.list()!!.toList())
        }

        // A folder that cannot be made is a failure too.
        val blocked = File(context.filesDir, "blocked").apply { writeText("a file") }
        try {
            AtomicBytesFile(File(blocked, "inner.json")).write("x".toByteArray())
            fail("Expected the write to fail")
        } catch (_: IOException) {
        }
    }

    @Test
    fun aWriteLeftHalfDoneIsReplacedByTheNextAndGoesWithADelete() {
        val file = File(context.filesDir, "Half/parcels.json")
        val atomic = AtomicBytesFile(file)
        atomic.write("old".toByteArray())
        File(file.path + ".new").writeText("half")
        assertEquals("old", atomic.read()!!.decodeToString())
        atomic.write("new".toByteArray())
        assertEquals(listOf("parcels.json"), file.parentFile!!.list()!!.toList())
        File(file.path + ".new").writeText("half")
        atomic.delete()
        assertEquals(emptyList<String>(), file.parentFile!!.list()!!.toList())
    }

    @Test
    fun theParcelsGoIntoBackupsAndTheCachesStayBehind() {
        val device = DeviceParcelsFile.of(context)
        device.write("[]".toByteArray())
        assertEquals("[]", File(context.filesDir, "DeviceParcels/parcels.json").readText())
        assertEquals("[]", device.read()!!.decodeToString())
        device.delete()
        assertNull(device.read())

        val user = UUID.fromString("0a1b2c3d-4e5f-6071-8293-a4b5c6d7e8f9")
        val caches = ParcelCacheFolder.of(context)
        assertNull(caches.read(user))
        caches.write(user, "[1]".toByteArray())
        assertEquals("[1]", File(context.filesDir, "ParcelCache/0A1B2C3D-4E5F-6071-8293-A4B5C6D7E8F9.json").readText())
        assertEquals("[1]", caches.read(user)!!.decodeToString())
        caches.delete(user)
        assertNull(caches.read(user))

        val rules = context.resources.getXml(com.plhery.peekdeliverytracker.R.xml.data_extraction_rules)
        val excluded = mutableListOf<String>()
        while (rules.next() != org.xmlpull.v1.XmlPullParser.END_DOCUMENT) {
            if (rules.eventType == org.xmlpull.v1.XmlPullParser.START_TAG && rules.name == "exclude") {
                excluded += rules.getAttributeValue(null, "domain") + ":" + rules.getAttributeValue(null, "path")
            }
        }
        // In the cloud backup and in a transfer to a new phone. The device's parcels go, as the iPhone's
        // Application Support does: those followed through someone else's link need no key.
        val folders = listOf("file:Keychain/", "file:ParcelCache/")
        assertEquals(folders + folders, excluded)
    }

    @Test
    fun theDeviceParcelsAreSavedOnAThreadOfTheirOwnInTheOrderGiven() {
        val saves = Collections.synchronizedList(mutableListOf<Pair<String?, Thread>>())
        val storage = object : DeviceParcelStorage {
            override fun load() = DeviceParcelStorage.Contents(emptyList(), mapOf("link" to "key"))

            override fun save(parcels: List<DeviceParcel>, keys: Map<String, String>) {
                saves += keys["link"] to Thread.currentThread()
            }
        }
        val queued = QueuedDeviceParcelStorage(storage)
        assertEquals(mapOf("link" to "key"), queued.load().keys)
        for (index in 1..50) queued.save(emptyList(), mapOf("link" to "$index"))
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(10)
        while (saves.size < 50 && System.nanoTime() < deadline) Thread.sleep(10)
        val made = synchronized(saves) { saves.toList() }
        assertEquals((1..50).map { "$it" }, made.map { it.first })
        assertEquals(1, made.map { it.second }.toSet().size)
        assertTrue(made.none { it.second == Thread.currentThread() })
    }

    @Test
    fun anExportIsWrittenWholeInTheCacheAndHandedOutThroughTheFileProvider() = runBlocking {
        val exports = CacheExportFiles.of(context)
        val file = exports.write("peek-export-2026-10-29.json", """{"packages":[]}""".toByteArray())
        assertEquals(File(context.cacheDir, "exports/peek-export-2026-10-29.json"), file)
        assertEquals("""{"packages":[]}""", file.readText())
        exports.write("peek-export-2026-10-29.json", "{}".toByteArray())
        assertEquals("{}", file.readText())

        for (name in listOf("../escape.json", "exports/inner.json", "", "..")) {
            try {
                exports.write(name, "{}".toByteArray())
                fail("Expected $name to be refused")
            } catch (_: IOException) {
            }
        }

        val uri = CacheExportFiles.uri(context, file)
        assertEquals("content", uri.scheme)
        assertEquals("${context.packageName}.exports", uri.authority)
        assertEquals(listOf("exports", "peek-export-2026-10-29.json"), uri.pathSegments)
    }
}
