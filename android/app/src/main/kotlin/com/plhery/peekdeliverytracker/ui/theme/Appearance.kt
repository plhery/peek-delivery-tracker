package com.plhery.peekdeliverytracker.ui.theme

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** The appearance picked in Settings (DesignSystem.swift `AppAppearance`): light unless the reader picks another. */
enum class AppAppearance(val rawValue: String) {
    System("system"),
    Light("light"),
    Dark("dark"),
    ;

    /** Whether the app draws dark: the phone's choice for [System] only. */
    fun isDark(systemDark: Boolean): Boolean = when (this) {
        System -> systemDark
        Light -> false
        Dark -> true
    }

    companion object {
        /** The iPhone app's UserDefaults key. */
        const val STORAGE_KEY = "sdt.appearance.v1"

        val DEFAULT = Light

        /** A saved value, or the default for none or one this version doesn't know, as `@AppStorage` reads it. */
        fun of(rawValue: String?): AppAppearance = entries.firstOrNull { it.rawValue == rawValue } ?: DEFAULT
    }
}

/**
 * Where the appearance is kept: the app's own preferences (the iPhone's standard UserDefaults) under the iOS key. The
 * first read is synchronous, so the first frame and the window already have the right appearance.
 */
class AppearanceStore(private val preferences: SharedPreferences) {
    private val state = MutableStateFlow(AppAppearance.of(preferences.getString(AppAppearance.STORAGE_KEY, null)))

    val appearance: StateFlow<AppAppearance> = state.asStateFlow()

    fun set(appearance: AppAppearance) {
        preferences.edit { putString(AppAppearance.STORAGE_KEY, appearance.rawValue) }
        state.value = appearance
    }

    companion object {
        /** The preferences file standing in for UserDefaults. */
        const val PREFERENCES = "user_defaults"

        @Volatile
        private var shared: AppearanceStore? = null

        /** The one store of the process, so every screen and the window follow the same setting. */
        fun of(context: Context): AppearanceStore = shared ?: synchronized(this) {
            shared ?: AppearanceStore(context.applicationContext.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE))
                .also { shared = it }
        }
    }
}

/** The appearance the reader picked, followed as it changes. */
@Composable
fun appearanceSetting(): AppAppearance {
    val context = LocalContext.current
    val store = remember(context) { AppearanceStore.of(context) }
    val appearance by store.appearance.collectAsState()
    return appearance
}
