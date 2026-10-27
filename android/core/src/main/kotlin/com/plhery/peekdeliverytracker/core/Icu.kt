package com.plhery.peekdeliverytracker.core

import java.lang.reflect.Method
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.IDN
import java.text.Collator
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap

/**
 * The ICU services Foundation reads host names and sorts with, which a JVM has its own version of. A phone has
 * ICU (android.icu); a plain JVM has java.net.IDN, which is IDNA 2003, not UTS #46, and java.text.Collator,
 * which passes over spaces and punctuation. The tests install ICU4J here, so they see what a phone does. Characters
 * are Swift's own, not ICU's (GraphemeBreaking.kt).
 */
object Icu {
    /** UTS #46 host names, as Foundation's URL parser converts them. */
    @Volatile
    var hostNames: HostNameConverter = IcuHostNames.from("android.icu.text") ?: JdkHostNames

    /** Collation, which Foundation's localized comparisons run on. */
    @Volatile
    var collators: CollatorFactory = IcuCollators.from("android.icu.text") ?: JdkCollators

    /** Amounts of money, which Foundation's currency format style writes with ICU. */
    @Volatile
    var currencies: CurrencyFormatter = IcuCurrencies.from("android.icu") ?: JdkCurrencies

    /** Distances, which Foundation's measurement format style writes with ICU. */
    @Volatile
    var lengths: LengthFormatter = IcuLengths.from("android.icu") ?: JdkLengths
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

/**
 * ICU's `NumberFormatter` with a currency for a locale. `code` is three ASCII capitals; `fractionDigits`, when
 * given, replaces the currency's own.
 */
fun interface CurrencyFormatter {
    fun format(value: Double, code: String, locale: Locale, fractionDigits: Int?): String
}

/** ICU's `NumberFormatter` (android.icu.number, com.ibm.icu.number), found by name. */
internal class IcuCurrencies private constructor(packageName: String) : CurrencyFormatter {
    private val localized = Class.forName("$packageName.number.LocalizedNumberFormatter")
    private val precisionClass = Class.forName("$packageName.number.Precision")
    private val withLocale = Class.forName("$packageName.number.NumberFormatter").getMethod("withLocale", Locale::class.java)
    private val unit = localized.getMethod("unit", Class.forName("$packageName.util.MeasureUnit"))
    private val precision = localized.getMethod("precision", precisionClass)
    private val fixedFraction = precisionClass.getMethod("fixedFraction", Int::class.javaPrimitiveType)
    private val format = localized.getMethod("format", Double::class.javaPrimitiveType)
    private val currency = Class.forName("$packageName.util.Currency").getMethod("getInstance", String::class.java)

    override fun format(value: Double, code: String, locale: Locale, fractionDigits: Int?): String {
        var formatter = unit.invoke(withLocale.invoke(null, locale), currency.invoke(null, code))
        if (fractionDigits != null) formatter = precision.invoke(formatter, fixedFraction.invoke(null, fractionDigits))
        return format.invoke(formatter, value).toString()
    }

    companion object {
        fun from(packageName: String): IcuCurrencies? = try {
            IcuCurrencies(packageName)
        } catch (_: ReflectiveOperationException) {
            null
        } catch (_: LinkageError) {
            null
        }
    }
}

/** java.text's currency format, which groups every thousand and writes a code it does not know before the number. */
private object JdkCurrencies : CurrencyFormatter {
    override fun format(value: Double, code: String, locale: Locale, fractionDigits: Int?): String {
        val currency = runCatching { Currency.getInstance(code) }.getOrNull()
        val format = if (currency != null) {
            NumberFormat.getCurrencyInstance(locale).also { it.currency = currency }
        } else {
            NumberFormat.getNumberInstance(locale).also { it.minimumFractionDigits = 2; it.maximumFractionDigits = 2 }
        }
        if (fractionDigits != null) {
            format.minimumFractionDigits = fractionDigits
            format.maximumFractionDigits = fractionDigits
        }
        val text = format.format(value)
        return if (currency != null) text else "$code\u00A0$text"
    }
}

/**
 * `Measurement(value:unit: .kilometers).formatted(.measurement(width: .abbreviated, usage: .asProvided,
 * numberFormatStyle: .number.precision(.fractionLength(0))).locale(_:))`: ICU's short unit width, in whole numbers.
 */
fun interface LengthFormatter {
    fun kilometres(value: Double, locale: Locale): String
}

/** ICU's `NumberFormatter` with the kilometre unit, found by name like `IcuCurrencies`. */
internal class IcuLengths private constructor(packageName: String) : LengthFormatter {
    private val localized = Class.forName("$packageName.number.LocalizedNumberFormatter")
    private val precisionClass = Class.forName("$packageName.number.Precision")
    private val unitWidthClass = Class.forName("$packageName.number.NumberFormatter\$UnitWidth")
    private val measureUnitClass = Class.forName("$packageName.util.MeasureUnit")
    private val withLocale = Class.forName("$packageName.number.NumberFormatter").getMethod("withLocale", Locale::class.java)
    private val unit = localized.getMethod("unit", measureUnitClass)
    private val unitWidth = localized.getMethod("unitWidth", unitWidthClass)
    private val precision = localized.getMethod("precision", precisionClass)
    private val format = localized.getMethod("format", Double::class.javaPrimitiveType)
    private val kilometre = measureUnitClass.getField("KILOMETER").get(null)
    private val short = unitWidthClass.getField("SHORT").get(null)
    private val whole = precisionClass.getMethod("integer").invoke(null)

    /** A formatter for each locale met: ICU's are immutable, so threads share them. */
    private val formatters = ConcurrentHashMap<Locale, Any>()

    override fun kilometres(value: Double, locale: Locale): String {
        val formatter = formatters.getOrPut(locale) {
            precision.invoke(unitWidth.invoke(unit.invoke(withLocale.invoke(null, locale), kilometre), short), whole)
        }
        return format.invoke(formatter, value).toString()
    }

    companion object {
        fun from(packageName: String): IcuLengths? = try {
            IcuLengths(packageName)
        } catch (_: ReflectiveOperationException) {
            null
        } catch (_: LinkageError) {
            null
        }
    }
}

/**
 * The app's seven languages written out as Foundation writes them, for a JVM with no ICU, whose own locale data groups
 * differently. Italian, Spanish, Portuguese (pt-PT, the app's) and Polish leave a four-digit number whole ("1200 km");
 * French puts a narrow no-break space between the groups and before the unit, the others a plain space before it.
 * Another language is written as English is.
 */
internal object JdkLengths : LengthFormatter {
    private class Style(val separator: String, val groupsFourDigits: Boolean, val space: String)

    private val styles = mapOf(
        "en" to Style(",", groupsFourDigits = true, space = " "),
        "de" to Style(".", groupsFourDigits = true, space = " "),
        "fr" to Style("\u202F", groupsFourDigits = true, space = "\u202F"),
        "it" to Style(".", groupsFourDigits = false, space = " "),
        "es" to Style(".", groupsFourDigits = false, space = " "),
        "pt" to Style("\u00A0", groupsFourDigits = false, space = " "),
        "pl" to Style("\u00A0", groupsFourDigits = false, space = " "),
    )

    override fun kilometres(value: Double, locale: Locale): String {
        val style = styles[locale.language] ?: styles.getValue("en")
        if (!value.isFinite()) return (if (value.isNaN()) "NaN" else if (value > 0) "\u221E" else "-\u221E") + style.space + "km"
        // Whole kilometres, halves to even, as ICU rounds.
        val whole = BigDecimal(value).setScale(0, RoundingMode.HALF_EVEN)
        val digits = whole.toBigInteger().abs().toString()
        val grouped = if (digits.length < 4 || (digits.length == 4 && !style.groupsFourDigits)) {
            digits
        } else {
            digits.reversed().chunked(3).joinToString(style.separator).reversed()
        }
        return (if (whole.signum() < 0) "-" else "") + grouped + style.space + "km"
    }
}
