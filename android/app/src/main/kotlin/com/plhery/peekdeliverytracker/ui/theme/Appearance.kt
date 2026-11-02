package com.plhery.peekdeliverytracker.ui.theme

import android.app.UiModeManager
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.isSystemInDarkTheme
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

    /**
     * The app's night mode (`UiModeManager.setApplicationNightMode`): the phone's for [System], so that the
     * configuration still says the phone's then; light or dark otherwise, as the window's override on the iPhone.
     */
    val nightMode: Int
        get() = when (this) {
            System -> UiModeManager.MODE_NIGHT_AUTO
            Light -> UiModeManager.MODE_NIGHT_NO
            Dark -> UiModeManager.MODE_NIGHT_YES
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
 * first read is synchronous, so the first frame and the window already have the right appearance. [follow] hears the
 * appearance as the store is made and each time it is set: the process's store makes the app's night mode follow it.
 */
class AppearanceStore(private val preferences: SharedPreferences, private val follow: (AppAppearance) -> Unit = {}) {
    private val state = MutableStateFlow(AppAppearance.of(preferences.getString(AppAppearance.STORAGE_KEY, null)))

    val appearance: StateFlow<AppAppearance> = state.asStateFlow()

    init {
        follow(state.value)
    }

    fun set(appearance: AppAppearance) {
        preferences.edit { putString(AppAppearance.STORAGE_KEY, appearance.rawValue) }
        state.value = appearance
        follow(appearance)
    }

    companion object {
        /** The preferences file standing in for UserDefaults. */
        const val PREFERENCES = "user_defaults"

        @Volatile
        private var shared: AppearanceStore? = null

        /**
         * The one store of the process, so every screen and the window follow the same setting, and the platform's
         * own pieces with them: the app's night mode follows it ([AppAppearance.nightMode]), which the system keeps and
         * applies before the app's first frame. Set again as the store is made, it holds a pick saved before the night
         * mode followed it, or brought back from a backup.
         */
        fun of(context: Context): AppearanceStore = shared ?: synchronized(this) {
            shared ?: context.applicationContext.let { app ->
                val modes = app.getSystemService(UiModeManager::class.java)
                AppearanceStore(app.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)) { modes?.setApplicationNightMode(it.nightMode) }
            }.also { shared = it }
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

/**
 * Whether the phone itself is dark, for what follows it whatever the app's appearance (the share sheet over the app that
 * shared). [configured] is the configuration's night, the phone's while the app's appearance is [AppAppearance.System];
 * with a light or dark of the app's own, it is the app's, and the phone's dark theme set on or off says it instead. A
 * schedule (sunset to sunrise, set hours, bedtime) can't be read then, and the configuration stands in for it.
 */
fun phoneIsDark(context: Context, configured: Boolean = context.resources.configuration.isNightModeActive): Boolean {
    if (AppearanceStore.of(context).appearance.value == AppAppearance.System) return configured
    return when (context.getSystemService(UiModeManager::class.java)?.nightMode) {
        UiModeManager.MODE_NIGHT_YES -> true
        UiModeManager.MODE_NIGHT_NO -> false
        else -> configured
    }
}

/** [phoneIsDark], followed as the configuration changes. */
@Composable
fun isPhoneInDarkTheme(): Boolean = phoneIsDark(LocalContext.current, isSystemInDarkTheme())
