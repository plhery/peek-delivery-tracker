package com.plhery.peekdeliverytracker.core

import java.io.InputStream

/**
 * The data the iPhone app bundles, read from the same generated files (see core/build.gradle.kts): the app's assets
 * once it installs a reader for them, otherwise the class path (tests, :coredevice).
 */
object SharedResources {
    @Volatile
    private var reader: ((String) -> InputStream?)? = null

    /** Reads `name` from somewhere other than the class path, giving null when it isn't there. */
    fun install(reader: (String) -> InputStream?) {
        this.reader = reader
    }

    fun open(name: String): InputStream =
        reader?.invoke(name)
            ?: SharedResources::class.java.getResourceAsStream("/peek/$name")
            ?: error("Missing shared resource $name; is ios/PeekDeliveryTracker/Resources generated?")

    fun text(name: String): String = open(name).use { it.readBytes().decodeToString() }
}
