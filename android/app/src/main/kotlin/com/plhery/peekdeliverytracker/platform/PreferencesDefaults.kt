package com.plhery.peekdeliverytracker.platform

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import androidx.core.content.edit
import com.plhery.peekdeliverytracker.core.UserDefaults
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore

/**
 * UserDefaults over SharedPreferences, under the iPhone app's keys. Booleans and strings are SharedPreferences' own;
 * bytes go as Base64 in a set of one string, a type no boolean or string shares, so each getter finds only what was
 * saved as its own type (`bool` of a string is false, `string` of bytes is nil), as UserDefaults' typed getters do.
 *
 * Writes are `apply()`: read back at once, written to disk soon after, as UserDefaults writes its own.
 */
class PreferencesDefaults(private val preferences: SharedPreferences) : UserDefaults {
    override fun bool(forKey: String): Boolean = try {
        preferences.getBoolean(forKey, false)
    } catch (_: ClassCastException) {
        false
    }

    override fun set(value: Boolean, forKey: String) {
        preferences.edit { putBoolean(forKey, value) }
    }

    override fun data(forKey: String): ByteArray? = try {
        preferences.getStringSet(forKey, null)?.singleOrNull()?.let { Base64.decode(it, Base64.NO_WRAP) }
    } catch (_: ClassCastException) {
        null
    } catch (_: IllegalArgumentException) {
        null
    }

    override fun set(value: ByteArray, forKey: String) {
        preferences.edit { putStringSet(forKey, setOf(Base64.encodeToString(value, Base64.NO_WRAP))) }
    }

    override fun string(forKey: String): String? = try {
        preferences.getString(forKey, null)
    } catch (_: ClassCastException) {
        null
    }

    override fun set(value: String, forKey: String) {
        preferences.edit { putString(forKey, value) }
    }

    override fun removeObject(forKey: String) {
        preferences.edit { remove(forKey) }
    }

    override fun hasObject(forKey: String): Boolean = preferences.contains(forKey)

    companion object {
        /** `UserDefaults.standard`: the file the appearance is kept in too. */
        fun standard(context: Context): PreferencesDefaults =
            PreferencesDefaults(context.getSharedPreferences(AppearanceStore.PREFERENCES, Context.MODE_PRIVATE))

        /**
         * `UserDefaults(suiteName:)` of the app group, which the iPhone shares with its widget: a file of its own, under
         * the group's name, so the keys the widget reads stay apart from the app's.
         */
        fun appGroup(context: Context, identifier: String): PreferencesDefaults =
            PreferencesDefaults(context.getSharedPreferences(identifier, Context.MODE_PRIVATE))
    }
}
