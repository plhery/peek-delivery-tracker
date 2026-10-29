package com.plhery.peekdeliverytracker.platform

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import com.plhery.peekdeliverytracker.BuildConfig
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import java.util.Locale

/** What the app reads of the phone, where the iPhone reads UIDevice, Locale and its bundle. */
object Device {
    /**
     * The device's region, `Locale.current.region` on the iPhone, which the app's own language leaves alone: the
     * system's first locale, never `Locale.getDefault()`.
     */
    fun region(): Locale = Resources.getSystem().configuration.locales[0] ?: Locale.ROOT

    /** `Locale.preferredLanguages`: the system's languages, its first one first, whatever the app's language. */
    fun systemLanguages(): List<String> = Resources.getSystem().configuration.locales.tags()

    /** The languages `AppLanguage.current` picks from: all of `LocaleList.getDefault()`. */
    fun preferredLanguages(): List<String> = LocaleList.getDefault().tags()

    private fun LocaleList.tags(): List<String> = toLanguageTags().split(',').filter { it.isNotEmpty() }

    /**
     * `UIDevice.current.name`, which the iPhone gives apps as its model alone ("iPhone") without the entitlement for
     * the name its owner chose: the model here too, never the device name from Settings.
     */
    val name: String get() = Build.MODEL.orEmpty().ifEmpty { "Android" }

    /** A Chromebook, then a tablet by its smallest width, else a phone, as DeliveryAnalytics.Platform says. */
    fun analyticsPlatform(context: Context): DeliveryAnalytics.Platform = when {
        context.packageManager.hasSystemFeature("org.chromium.arc") -> DeliveryAnalytics.Platform.Chromebook
        context.resources.configuration.smallestScreenWidthDp >= 600 -> DeliveryAnalytics.Platform.AndroidTablet
        else -> DeliveryAnalytics.Platform.Android
    }

    /** An emulator, which stays out of the counts as the iPhone's simulator does. */
    val isEmulator: Boolean
        get() = Build.HARDWARE in setOf("goldfish", "ranchu") || Build.FINGERPRINT.orEmpty().startsWith("generic") ||
            Build.PRODUCT.orEmpty().let { it.startsWith("sdk") || it.contains("_sdk") || it.contains("emulator") }
}

/**
 * The iPhone build's settings (ios/Configuration/Shared.xcconfig, then a developer's Local.xcconfig), which the
 * build gives as BuildConfig fields under their Info.plist keys, read by `AppConfiguration.from` as the iPhone's are.
 */
object BuildSettings {
    private val values: Map<String, String> = mapOf(
        "SDTUseAPI" to BuildConfig.SDTUseAPI,
        "SDTAPIBaseURL" to BuildConfig.SDTAPIBaseURL,
        "SDTSupabaseURL" to BuildConfig.SDTSupabaseURL,
        "SDTSupabasePublishableKey" to BuildConfig.SDTSupabasePublishableKey,
        "SDTGoogleAuthEnabled" to BuildConfig.SDTGoogleAuthEnabled,
        "SDTAppleAuthEnabled" to BuildConfig.SDTAppleAuthEnabled,
        "SDTEmailOTPEnabled" to BuildConfig.SDTEmailOTPEnabled,
        "SDTAppGroupIdentifier" to BuildConfig.SDTAppGroupIdentifier,
        "SDTLinkHosts" to BuildConfig.SDTLinkHosts,
        "SDTOIDCProvider" to BuildConfig.SDTOIDCProvider,
        "SDTOIDCName" to BuildConfig.SDTOIDCName,
    )

    val configuration: AppConfiguration by lazy { AppConfiguration.from { values[it] } }
}
