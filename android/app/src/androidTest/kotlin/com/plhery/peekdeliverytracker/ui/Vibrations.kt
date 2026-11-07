package com.plhery.peekdeliverytracker.ui

import android.os.Build
import android.os.ParcelFileDescriptor
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.HapticFeedbackConstants
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import java.io.File
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.rules.ExternalResource

/**
 * What the app asks the phone's vibrator for, step by step, as the device tests check it against the iPhone's feedback
 * (HapticsDeviceTest, InvitationDeviceTest): [mark] before a step, then one of the asserts with what the step should
 * have asked for, made for this device by [selection], [success], [medium], [light] or [soft]. Emulators don't vibrate,
 * but the vibrator service keeps each request in its history ([VibratorRecords]); each step's records are saved as
 * [folder]/<step>.txt. An impact below full intensity is a primitive where the device's vibrator has it
 * ([Vibrator.areAllPrimitivesSupported]), as Haptics.kt chooses, and its constant otherwise.
 */
internal class Vibrations(compose: AndroidComposeTestRule<*, *>, folder: File, private val vibrator: Vibrator) {
    private val records = VibratorRecords(compose, folder, vibrator)

    /** What the vibrator's history holds now, once it stands still: the next step's records are those after it. */
    fun mark() = records.mark()

    /** `.selection`: SEGMENT_TICK from Android 14, CONTEXT_CLICK before; a prebaked tick on Android 12. */
    fun selection(): Asked = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        Asked.Constant(HapticFeedbackConstants.SEGMENT_TICK, "SEGMENT_TICK", "TICK")
    } else {
        Asked.Constant(HapticFeedbackConstants.CONTEXT_CLICK, "CONTEXT_CLICK", "TICK")
    }

    /** `Haptics.success()`: CONFIRM, a prebaked click on Android 12. */
    fun success(): Asked = Asked.Constant(HapticFeedbackConstants.CONFIRM, "CONFIRM", "CLICK")

    /** A medium impact below full intensity: a click at that scale where the vibrator has it, VIRTUAL_KEY otherwise. */
    fun medium(intensity: Float): Asked = impact(VibrationEffect.Composition.PRIMITIVE_CLICK, "CLICK", intensity) {
        Asked.Constant(HapticFeedbackConstants.VIRTUAL_KEY, "VIRTUAL_KEY", "CLICK")
    }

    /** A light impact below full intensity: a tick at that scale where the vibrator has it, CONTEXT_CLICK otherwise. */
    fun light(intensity: Float): Asked = impact(VibrationEffect.Composition.PRIMITIVE_TICK, "TICK", intensity) {
        Asked.Constant(HapticFeedbackConstants.CONTEXT_CLICK, "CONTEXT_CLICK", "TICK")
    }

    /** A soft impact below full intensity: a low tick at that scale where the vibrator has it, CONTEXT_CLICK otherwise. */
    fun soft(intensity: Float): Asked = impact(VibrationEffect.Composition.PRIMITIVE_LOW_TICK, "LOW_TICK", intensity) {
        Asked.Constant(HapticFeedbackConstants.CONTEXT_CLICK, "CONTEXT_CLICK", "TICK")
    }

    private fun impact(primitive: Int, name: String, intensity: Float, otherwise: () -> Asked): Asked =
        if (vibrator.areAllPrimitivesSupported(primitive)) Asked.Primitive(name, intensity) else otherwise()

    private fun raw(added: List<RecordedVibration>): String = added.joinToString("\n") { it.line }.ifEmpty { "(nothing)" }

    /** The step asked the vibrator for nothing. */
    fun assertNone(step: String) {
        val added = records.step(step, "nothing", 0)
        assertTrue("$step: nothing asked for, but:\n${raw(added)}", added.isEmpty())
    }

    /** The step asked the vibrator for `asked`, once, in a record this test can read. */
    fun assertOne(step: String, asked: Asked) = assertInOrder(step, asked)

    /** The step asked the vibrator for each of `asked`, once and in this order, in records this test can read. */
    fun assertInOrder(step: String, vararg asked: Asked) {
        val description = asked.joinToString(", then ") { it.description }
        val added = records.step(step, if (asked.size == 1) "one $description" else description, asked.size)
        val what = if (asked.size == 1) "one vibration for $description" else "${asked.size} vibrations: $description"
        assertEquals("$step: $what, not:\n${raw(added)}", asked.size, added.size)
        added.zip(asked).forEach { (vibration, wanted) ->
            assertTrue("$step: a record this test can't read:\n${raw(added)}", vibration.read)
            assertTrue("$step: $description, not:\n${raw(added)}", wanted.playedBy(vibration))
        }
    }

    /** Nothing the step asked for plays: no request, or one the vibrator service ignored. */
    fun assertNothingPlays(step: String) {
        val added = records.step(step, "nothing that plays: no request, or one ignored for the settings", 0)
        assertTrue("$step: at most the tap's request, not:\n${raw(added)}", added.size <= 1)
        assertTrue("$step: nothing played, but:\n${raw(added)}", added.all { it.ignored })
    }
}

/** How long the vibrator's history stands still before a step's vibrations are taken as all there. */
private const val QUIET_MILLIS = 1_000L

/** `command`'s output, run by the shell through UiAutomation, once it has ended. */
private fun shell(command: String): String = ParcelFileDescriptor.AutoCloseInputStream(
    InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand(command),
).use { it.readBytes().decodeToString() }

/** What a step should have asked the vibrator for. */
internal sealed interface Asked {
    val description: String

    fun playedBy(vibration: RecordedVibration): Boolean

    /**
     * `View.performHapticFeedback(constant)`: the constant where the record names it (Android 17), else, with no
     * primitive, the prebaked `effect` the window manager plays for it (Android 12).
     */
    data class Constant(val constant: Int, val name: String, val effect: String) : Asked {
        override val description: String get() = "$name (constant $constant, prebaked $effect)"

        override fun playedBy(vibration: RecordedVibration): Boolean =
            vibration.constant?.let { it == constant } ?: (vibration.primitives.isEmpty() && effect in vibration.effects)
    }

    /** A composition of the primitive `name` at `scale`, the scale asked for being in the record. */
    data class Primitive(val name: String, val scale: Float) : Asked {
        override val description: String get() = "PRIMITIVE_$name at $scale"

        override fun playedBy(vibration: RecordedVibration): Boolean =
            vibration.constant == null && vibration.primitives.any { (primitive, at) -> primitive == name && abs(at - scale) < 0.01f }
    }
}

/**
 * One record of the vibrator's history, as [VibratorDump.read] makes it out: the constant it names, the prebaked
 * effects and the primitives with their scale in what was played and what was asked (both, where the touch-feedback
 * strength scaled it), and its status (finished, ignored_for_settings...).
 */
internal data class RecordedVibration(
    val line: String,
    val constant: Int?,
    val effects: Set<String>,
    val primitives: List<Pair<String, Float>>,
    val status: String?,
) {
    val read: Boolean get() = constant != null || effects.isNotEmpty() || primitives.isNotEmpty()
    val ignored: Boolean get() = status?.startsWith("ignored") == true
}

/**
 * `dumpsys vibrator_manager` read without knowing each release's wording: the lines under a history's header
 * ("Previous vibrations for usage TOUCH:" on Android 12, "Recent vibrations:" and a usage's on 17, not the aggregated
 * history, which repeats them), kept where they name the app's package, each by its time; and in each, the names and
 * numbers both forms give: `Prebaked{effect=TICK` or `Prebaked=TICK(`, `Primitive{primitive=PRIMITIVE_CLICK, scale=0.9`
 * or `Primitive=CLICK(scale=0.90`, an effect's or primitive's id in place of its name, and `constant=N`.
 */
internal object VibratorDump {
    private val time = Regex("""\d{2}-\d{2} \d{2}:\d{2}:\d{2}\.\d{3}""")
    private val history = Regex("""(?i)^m?(previous|recent)\b""")
    private val constant = Regex("""\bconstant=(-?\d+)""")
    private val prebaked = Regex("""Prebaked(?:Segment)?\W{1,2}(?:m?[Ee]ffect(?:Id)?=)?(?:EFFECT_)?([A-Z][A-Z_0-9]*|\d+)""")
    private val primitive =
        Regex("""Primitive(?:Segment)?\W{1,2}(?:primitive=|mPrimitiveId=|id=)?(?:PRIMITIVE_)?([A-Z][A-Z_]*|\d+)\W{1,2}(?:m?[sS]cale[=:]\s*)?(\d+(?:[.,]\d+)?)""")

    /** A primitive named as the vibrator's info lists them, `CLICK(0.90`, where no segment says so. */
    private val shortPrimitive = Regex("""\b(NOOP|CLICK|THUD|SPIN|QUICK_RISE|SLOW_RISE|QUICK_FALL|TICK|LOW_TICK)\(\s*(?:scale=)?(\d+(?:\.\d+)?)""")
    private val status = Regex("""(?i)\b(running|finished\w*|forwarded\w*|cancelled\w*|ignored\w*)\b""")
    private val effects = mapOf(0 to "CLICK", 1 to "DOUBLE_CLICK", 2 to "TICK", 3 to "THUD", 4 to "POP", 5 to "HEAVY_CLICK", 21 to "TEXTURE_TICK")
    private val primitives =
        mapOf(0 to "NOOP", 1 to "CLICK", 2 to "THUD", 3 to "SPIN", 4 to "QUICK_RISE", 5 to "SLOW_RISE", 6 to "QUICK_FALL", 7 to "TICK", 8 to "LOW_TICK")

    /** The records of `app`'s vibrations in the histories of `dump`, by their time (the whole line where none shows). */
    fun records(dump: String, app: String): Map<String, String> {
        val own = Regex("""(?<![\w.])${Regex.escape(app)}(?![\w.])""")
        val headers = mutableListOf<Pair<Int, String>>()
        val found = LinkedHashMap<String, String>()
        for (line in dump.lineSequence()) {
            val text = line.trim()
            if (text.isEmpty()) continue
            val indent = line.length - line.trimStart().length
            val header = text.endsWith(":") && text.none { it in "|{},=" }
            while (headers.isNotEmpty() && (headers.last().first > indent || (headers.last().first == indent && header))) {
                headers.removeAt(headers.lastIndex)
            }
            if (header) {
                headers += indent to text
                continue
            }
            val inHistory = headers.any { history.containsMatchIn(it.second) } && headers.none { it.second.contains("aggregat", ignoreCase = true) }
            if (inHistory && own.containsMatchIn(text)) found[time.find(text)?.value ?: text] = text
        }
        return found
    }

    fun read(line: String): RecordedVibration {
        val asked = constant.find(line)?.groupValues?.get(1)?.toIntOrNull()
        val named = prebaked.findAll(line).map { name(it.groupValues[1], effects) }.toSet()
        var composed = primitive.findAll(line).mapNotNull { match ->
            match.groupValues[2].replace(',', '.').toFloatOrNull()?.let { name(match.groupValues[1], primitives) to it }
        }.toList()
        if (composed.isEmpty()) {
            composed = shortPrimitive.findAll(line).mapNotNull { match -> match.groupValues[2].toFloatOrNull()?.let { match.groupValues[1] to it } }.toList()
        }
        return RecordedVibration(line, asked, named, composed, status.find(line)?.groupValues?.get(1)?.lowercase())
    }

    private fun name(value: String, ids: Map<Int, String>): String = value.toIntOrNull()?.let { ids[it] ?: value } ?: value
}

/**
 * The app's vibrations in the vibrator service's history, read around each step of a test: [mark] takes what the
 * history holds once nothing new has come for [QUIET_MILLIS]; [step] waits, up to the device tests' time, for the
 * vibrations the step should have asked for, then for the history to stand still, and returns the records it added
 * in the order they came, their lines saved as [folder]/<step>.txt after what was asked and what the device has.
 * Written by the shell, as [DeviceShots] writes its shots.
 */
internal class VibratorRecords(
    private val compose: AndroidComposeTestRule<*, *>,
    private val folder: File,
    private val vibrator: Vibrator,
) {
    private val app = InstrumentationRegistry.getInstrumentation().targetContext.packageName
    private var marked: Set<String>? = null

    private fun records(): Map<String, String> = VibratorDump.records(shell("dumpsys vibrator_manager"), app)

    private fun quiet(): Map<String, String> {
        var last = records()
        var since = SystemClock.uptimeMillis()
        runCatching {
            compose.waitUntil(LONG_MILLIS) {
                val now = records()
                if (now.keys != last.keys) {
                    last = now
                    since = SystemClock.uptimeMillis()
                }
                SystemClock.uptimeMillis() - since >= QUIET_MILLIS
            }
        }
        return last
    }

    fun mark() {
        marked = quiet().keys
    }

    fun step(name: String, asked: String, expected: Int): List<RecordedVibration> {
        val before = checkNotNull(marked) { "No mark before $name" }
        if (expected > 0) runCatching { compose.waitUntil(LONG_MILLIS) { (records().keys - before).size >= expected } }
        val now = quiet()
        marked = now.keys
        // By their time, which each record starts with.
        val added = now.filterKeys { it !in before }.toSortedMap().values.toList()
        save(name, asked, added)
        return added.map(VibratorDump::read)
    }

    private fun save(name: String, asked: String, lines: List<String>) {
        val click = vibrator.areAllPrimitivesSupported(VibrationEffect.Composition.PRIMITIVE_CLICK)
        val tick = vibrator.areAllPrimitivesSupported(VibrationEffect.Composition.PRIMITIVE_TICK)
        val text = buildString {
            appendLine("# $name: asked for $asked")
            appendLine("# Android SDK ${Build.VERSION.SDK_INT}; PRIMITIVE_CLICK ${if (click) "supported" else "missing"}, PRIMITIVE_TICK ${if (tick) "supported" else "missing"}")
            lines.forEach { appendLine(it) }
        }
        val bytes = text.toByteArray()
        val file = File(folder, "$name.txt")
        shell("mkdir -p $folder")
        val (output, input) = InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommandRw("dd of=$file")
        ParcelFileDescriptor.AutoCloseOutputStream(input).use { it.write(bytes) }
        // dd has ended once its output closes.
        ParcelFileDescriptor.AutoCloseInputStream(output).use { it.readBytes() }
        assertEquals("$file written", bytes.size.toString(), shell("stat -c %s $file").trim())
    }
}

/**
 * The phone's touch feedback (`haptic_feedback_enabled`) on for each test, as phones come, a test switching it off as it
 * needs, and what the phone had put back after it; set by the shell, which may write the system's settings. From
 * Android 13 the vibrator service follows the setting a moment later (touch vibrations OFF in its dump), which [switch]
 * waits for, as far as the dump says.
 */
internal class TouchFeedback(private val compose: AndroidComposeTestRule<*, *>) : ExternalResource() {
    private var before = ""

    override fun before() {
        before = shell("settings get system $SETTING").trim()
        switch(on = true)
    }

    override fun after() {
        if (before.isEmpty() || before == "null") shell("settings delete system $SETTING") else shell("settings put system $SETTING $before")
        if (before != "0") awaitTouch(on = true)
    }

    fun switch(on: Boolean) {
        shell("settings put system $SETTING ${if (on) 1 else 0}")
        awaitTouch(on)
    }

    private fun awaitTouch(on: Boolean) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return
        runCatching { compose.waitUntil(LONG_MILLIS) { TOUCH_OFF.containsMatchIn(shell("dumpsys vibrator_manager")) != on } }
    }

    private companion object {
        const val SETTING = "haptic_feedback_enabled"
        val TOUCH_OFF = Regex("""\bTOUCH\s*=\s*OFF\b""")
    }
}
