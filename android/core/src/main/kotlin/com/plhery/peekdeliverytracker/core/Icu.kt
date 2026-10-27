package com.plhery.peekdeliverytracker.core

import java.lang.reflect.Method
import java.net.IDN

/**
 * The ICU services Foundation reads host names with, which a JVM has its own version of. A phone has ICU
 * (android.icu); a plain JVM has java.net.IDN, which is IDNA 2003, not UTS #46. The tests install ICU4J here,
 * so they see what a phone does.
 */
object Icu {
    /** UTS #46 host names, as Foundation's URL parser converts them. */
    @Volatile
    var hostNames: HostNameConverter = IcuHostNames.from("android.icu.text") ?: JdkHostNames
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
