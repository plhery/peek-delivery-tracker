package com.plhery.peekdeliverytracker.core

import java.io.InputStream

/** The data the iPhone app bundles, read from the same generated files (see core/build.gradle.kts). */
object SharedResources {
    fun open(name: String): InputStream =
        SharedResources::class.java.getResourceAsStream("/peek/$name")
            ?: error("Missing shared resource $name; is ios/PeekDeliveryTracker/Resources generated?")

    fun text(name: String): String = open(name).use { it.readBytes().decodeToString() }
}
