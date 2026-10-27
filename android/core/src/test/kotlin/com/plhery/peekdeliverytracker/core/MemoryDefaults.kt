package com.plhery.peekdeliverytracker.core

/** A UserDefaults suite in memory: a second instance on the same values reads what the first one saved. */
internal class MemoryDefaults(val values: MutableMap<String, Any> = mutableMapOf()) : UserDefaults {
    override fun bool(forKey: String): Boolean = values[forKey] as? Boolean ?: false

    override fun set(value: Boolean, forKey: String) {
        values[forKey] = value
    }

    override fun data(forKey: String): ByteArray? = values[forKey] as? ByteArray

    override fun set(value: ByteArray, forKey: String) {
        values[forKey] = value
    }

    override fun removeObject(forKey: String) {
        values.remove(forKey)
    }
}
