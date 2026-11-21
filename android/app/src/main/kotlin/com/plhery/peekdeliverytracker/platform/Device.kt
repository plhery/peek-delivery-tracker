package com.plhery.peekdeliverytracker.platform

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.icu.util.LocaleData
import android.icu.util.ULocale
import android.os.Build
import android.os.LocaleList
import android.text.format.DateFormat
import com.plhery.peekdeliverytracker.BuildConfig
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.ClockPreference
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.DistanceUnit
import com.plhery.peekdeliverytracker.core.WeightUnit
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

    /** Whether the phone's clock runs to 24 hours: its "Use 24-hour format", else its language's own. */
    fun uses24HourTime(context: Context): Boolean = DateFormat.is24HourFormat(context)

    /**
     * Whether times are written on a 24-hour clock where the app isn't open to say so, as a push is posted or a widget
     * drawn: the clock chosen in Settings, else the phone's.
     */
    fun appUses24HourTime(context: Context): Boolean =
        ClockPreference.from(PreferencesDefaults.standard(context).string(ClockPreference.storageKey)).uses24HourTime(uses24HourTime(context))

    /** The unit the phone's [region] measures roads in: kilometres where it is metric, miles elsewhere (the US, the UK). */
    fun distanceUnit(locale: Locale = region()): DistanceUnit =
        if (measurementSystem(locale) == LocaleData.MeasurementSystem.SI) DistanceUnit.Kilometres else DistanceUnit.Miles

    /**
     * The unit the phone's [region] weighs in: pounds where it measures in US units (the US and Liberia), else kilograms,
     * the UK's included, and Myanmar's, which ICU's data measures as the UK does: miles, but kilograms. The iPhone and
     * the web do the same.
     */
    fun weightUnit(locale: Locale = region()): WeightUnit =
        if (measurementSystem(locale) == LocaleData.MeasurementSystem.US) WeightUnit.Pounds else WeightUnit.Kilograms

    /**
     * The measurement system the iPhone's `Locale.measurementSystem` gives: the one the locale's `-u-ms-` keyword names,
     * as Android 16's Measurement system setting writes it (a UK phone set to metric is `en-GB-u-ms-metric`), else its
     * region's, which ICU looks up without reading the keyword.
     */
    private fun measurementSystem(locale: Locale): LocaleData.MeasurementSystem = when (locale.getUnicodeLocaleType("ms")) {
        "metric" -> LocaleData.MeasurementSystem.SI
        "ussystem" -> LocaleData.MeasurementSystem.US
        "uksystem" -> LocaleData.MeasurementSystem.UK
        else -> LocaleData.getMeasurementSystem(ULocale.forLocale(locale))
    }

    /**
     * `UIDevice.current.name`, which the iPhone gives apps as its model alone ("iPhone") without the entitlement for
     * the name its owner chose: the model here too, never the device name from Settings.
     */
    val name: String get() = Build.MODEL.orEmpty().ifEmpty { "Android" }

    /** A Chromebook, then a tablet by its smallest width, else a phone, as DeliveryAnalytics.Platform says. */
    fun analyticsPlatform(context: Context, configuration: Configuration = context.resources.configuration): DeliveryAnalytics.Platform = when {
        context.packageManager.hasSystemFeature("org.chromium.arc") -> DeliveryAnalytics.Platform.Chromebook
        configuration.smallestScreenWidthDp >= 600 -> DeliveryAnalytics.Platform.AndroidTablet
        else -> DeliveryAnalytics.Platform.Android
    }

    /**
     * `DeviceIdiom.isPad`: a Chromebook or a tablet, told from a phone as [analyticsPlatform] tells them, where the
     * iPhone app's iPad build has a menu bar (`MenuBar.shown`).
     */
    fun isPad(context: Context, configuration: Configuration = context.resources.configuration): Boolean =
        analyticsPlatform(context, configuration) != DeliveryAnalytics.Platform.Android

    /**
     * Whether the system confirms a copy itself: from Android 13 its clipboard overlay shows what was copied at the
     * screen's bottom-start and TalkBack hears "Text copied" (`ClipboardOverlayController`), so the app says nothing of
     * its own, no toast and no announcement, as Android's guidance has it. Up to Android 12L nothing does, and the app
     * says so as the iPhone does. A button that turns to "Copied" says it on every version: it is the button's own
     * state, which TalkBack reads when it comes to the button.
     */
    val confirmsCopies: Boolean get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

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
