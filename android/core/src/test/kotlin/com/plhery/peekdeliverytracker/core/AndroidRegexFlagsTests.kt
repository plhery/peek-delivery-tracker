package com.plhery.peekdeliverytracker.core

import java.io.File
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Android's java.util.regex is ICU, and it throws on `Pattern.UNICODE_CHARACTER_CLASS` and on an inline `(?U)`, which the
 * JVM these tests run on takes: a pattern compiled with either at a file's top level stops the app the first time
 * anything in that file is used. The sources ask for the flag through [unicodeCharacterClass], which is 0 on Android.
 */
class AndroidRegexFlagsTests {
    @Test
    fun noSourceGivesAndroidsRegexAFlagItRefuses() {
        val core = File(System.getProperty("user.dir"))
        val sources = listOf("src/main/kotlin", "../app/src/main/kotlin", "../app/src/play/kotlin", "../app/src/fdroid/kotlin")
            .map(core::resolve)
            .filter { it.isDirectory }
            .flatMap { directory -> directory.walk().filter { it.extension == "kt" }.toList() }
        assertTrue("No Kotlin sources under $core", sources.size > 10)
        val refused = Regex("""Pattern\.UNICODE_CHARACTER_CLASS|\(\?[a-zA-Z]*U""")
        val found = sources.flatMap { file ->
            file.readLines().mapIndexedNotNull { index, line ->
                val code = line.substringBefore("//")
                val probe = file.name == "CatalogExpressions.kt" && "UNICODE_CHARACTER_CLASS" in code
                if (refused.containsMatchIn(code) && !code.trimStart().startsWith("*") && !probe) "${file.name}:${index + 1}" else null
            }
        }
        assertEquals(emptyList<String>(), found)
    }
}
