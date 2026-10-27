package com.plhery.peekdeliverytracker.core

import java.lang.reflect.Method
import java.net.IDN
import java.text.Collator
import java.util.Locale

/**
 * The ICU services Foundation reads host names and sorts with, which a JVM has its own version of. A phone has
 * ICU (android.icu); a plain JVM has java.net.IDN, which is IDNA 2003, not UTS #46, and java.text.Collator,
 * which passes over spaces and punctuation. The tests install ICU4J here, so they see what a phone does.
 */
object Icu {
    /** UTS #46 host names, as Foundation's URL parser converts them. */
    @Volatile
    var hostNames: HostNameConverter = IcuHostNames.from("android.icu.text") ?: JdkHostNames

    /** Collation, which Foundation's localized comparisons run on. */
    @Volatile
    var collators: CollatorFactory = IcuCollators.from("android.icu.text") ?: JdkCollators
}

/** ICU's UTS #46 processing (`IDNA.nameToASCII`, `nameToUnicode`): the converted name and the `IDNA.Error`s met, by name. */
fun interface HostNameConverter {
    fun convert(name: String, toAscii: Boolean): Pair<String, Set<String>>
}

/**
 * ICU's IDNA with Foundation's options, nontransitional and with the bidi and CONTEXTJ checks, found by name:
 * android.icu and ICU4J (com.ibm.icu) have the same API.
 */
internal class IcuHostNames private constructor(packageName: String) : HostNameConverter {
    private val idnaClass = Class.forName("$packageName.IDNA")
    private val infoClass = Class.forName("$packageName.IDNA\$Info")
    private val idna: Any = idnaClass.getMethod("getUTS46Instance", Int::class.javaPrimitiveType).invoke(
        null,
        listOf("CHECK_BIDI", "CHECK_CONTEXTJ", "NONTRANSITIONAL_TO_ASCII", "NONTRANSITIONAL_TO_UNICODE")
            .sumOf { idnaClass.getField(it).getInt(null) },
    )
    private val toAscii: Method =
        idnaClass.getMethod("nameToASCII", CharSequence::class.java, StringBuilder::class.java, infoClass)
    private val toUnicode: Method =
        idnaClass.getMethod("nameToUnicode", CharSequence::class.java, StringBuilder::class.java, infoClass)
    private val errors: Method = infoClass.getMethod("getErrors")
    private val newInfo = infoClass.getConstructor()

    override fun convert(name: String, toAscii: Boolean): Pair<String, Set<String>> {
        val info = newInfo.newInstance()
        val result = StringBuilder()
        (if (toAscii) this.toAscii else toUnicode).invoke(idna, name, result, info)
        return result.toString() to (errors.invoke(info) as Set<*>).map { (it as Enum<*>).name }.toSet()
    }

    companion object {
        fun from(packageName: String): IcuHostNames? = try {
            IcuHostNames(packageName)
        } catch (_: ReflectiveOperationException) {
            null
        } catch (_: LinkageError) {
            null
        }
    }
}

/** IDNA 2003, which maps a few characters UTS #46 keeps (ß) and checks less. */
private object JdkHostNames : HostNameConverter {
    override fun convert(name: String, toAscii: Boolean): Pair<String, Set<String>> = try {
        (if (toAscii) IDN.toASCII(name, IDN.ALLOW_UNASSIGNED) else IDN.toUnicode(name, IDN.ALLOW_UNASSIGNED)) to emptySet()
    } catch (_: IllegalArgumentException) {
        name to setOf("DISALLOWED")
    }
}

/**
 * ICU's collation for a locale. `strength` is `Collator.PRIMARY`, `SECONDARY` or `TERTIARY`, which ICU numbers
 * alike; `caseLevel` adds a level that tells case apart and nothing else; `numeric` reads runs of digits as
 * numbers, so 2 comes before 10.
 */
fun interface CollatorFactory {
    fun collator(locale: Locale, strength: Int, caseLevel: Boolean, numeric: Boolean): Comparator<String>
}

/** ICU's `Collator`, found by name like `IcuHostNames`. A frozen collator can be shared between threads. */
internal class IcuCollators private constructor(packageName: String) : CollatorFactory {
    private val collatorClass = Class.forName("$packageName.Collator")
    private val ruleBasedClass = Class.forName("$packageName.RuleBasedCollator")
    private val getInstance = collatorClass.getMethod("getInstance", Locale::class.java)
    private val setStrength = collatorClass.getMethod("setStrength", Int::class.javaPrimitiveType)
    private val setCaseLevel = ruleBasedClass.getMethod("setCaseLevel", Boolean::class.javaPrimitiveType)
    private val setNumeric = ruleBasedClass.getMethod("setNumericCollation", Boolean::class.javaPrimitiveType)
    private val freeze = collatorClass.getMethod("freeze")

    override fun collator(locale: Locale, strength: Int, caseLevel: Boolean, numeric: Boolean): Comparator<String> {
        val collator = getInstance.invoke(null, locale)
        setStrength.invoke(collator, strength)
        if (ruleBasedClass.isInstance(collator)) {
            setCaseLevel.invoke(collator, caseLevel)
            setNumeric.invoke(collator, numeric)
        }
        freeze.invoke(collator)
        // ICU's Collator is a Comparator of the strings it is given.
        @Suppress("UNCHECKED_CAST")
        val comparator = collator as Comparator<Any>
        return Comparator { left, right -> comparator.compare(left, right) }
    }

    companion object {
        fun from(packageName: String): IcuCollators? = try {
            IcuCollators(packageName)
        } catch (_: ReflectiveOperationException) {
            null
        } catch (_: LinkageError) {
            null
        }
    }
}

/** java.text.Collator, which has no case level, reads digits one by one and passes over spaces and punctuation. */
private object JdkCollators : CollatorFactory {
    override fun collator(locale: Locale, strength: Int, caseLevel: Boolean, numeric: Boolean): Comparator<String> {
        val collator = Collator.getInstance(locale)
        collator.strength = if (caseLevel) Collator.TERTIARY else strength
        return Comparator { left, right -> collator.compare(left, right) }
    }
}
