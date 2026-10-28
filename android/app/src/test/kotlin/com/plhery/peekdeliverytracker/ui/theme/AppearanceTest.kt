package com.plhery.peekdeliverytracker.ui.theme

import android.content.Context
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** The appearance picked in Settings (DesignSystem.swift `AppAppearance`, `@AppStorage("sdt.appearance.v1")`). */
@RunWith(AndroidJUnit4::class)
class AppearanceTest {
    @get:Rule
    val compose = createComposeRule()

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @After
    fun backToTheDefault() {
        AppearanceStore.of(context).set(AppAppearance.DEFAULT)
    }

    @Test
    fun theAppIsLightUnlessTheReaderPicksSystemOrDark() {
        assertEquals(AppAppearance.Light, AppAppearance.DEFAULT)
        assertFalse(AppAppearance.Light.isDark(systemDark = true))
        assertTrue(AppAppearance.Dark.isDark(systemDark = false))
        assertTrue(AppAppearance.System.isDark(systemDark = true))
        assertFalse(AppAppearance.System.isDark(systemDark = false))
    }

    @Test
    fun savedValuesReadAsTheIPhoneWritesThem() {
        assertEquals(AppAppearance.System, AppAppearance.of("system"))
        assertEquals(AppAppearance.Light, AppAppearance.of("light"))
        assertEquals(AppAppearance.Dark, AppAppearance.of("dark"))
        // Nothing saved, or a value this version doesn't know: the default.
        assertEquals(AppAppearance.Light, AppAppearance.of(null))
        assertEquals(AppAppearance.Light, AppAppearance.of("sepia"))
    }

    @Test
    fun theStoreKeepsThePickUnderTheIPhonesKey() {
        val preferences = context.getSharedPreferences("appearance-test", Context.MODE_PRIVATE)
        val store = AppearanceStore(preferences)
        assertEquals(AppAppearance.Light, store.appearance.value)
        store.set(AppAppearance.Dark)
        assertEquals(AppAppearance.Dark, store.appearance.value)
        assertEquals("dark", preferences.getString("sdt.appearance.v1", null))
        assertEquals(AppAppearance.Dark, AppearanceStore(preferences).appearance.value)
        preferences.edit().putString("sdt.appearance.v1", "sepia").commit()
        assertEquals(AppAppearance.Light, AppearanceStore(preferences).appearance.value)
    }

    @Test
    @Config(qualifiers = "night")
    fun aDarkPhoneShowsTheAppLightUntilTheReaderPicksSystem() {
        var colors: PeekColors? = null
        compose.setContent { PeekTheme(carrierIdentities = null) { colors = PeekTheme.colors } }
        compose.waitForIdle()
        assertFalse(colors!!.dark)

        compose.runOnUiThread { AppearanceStore.of(context).set(AppAppearance.System) }
        compose.waitForIdle()
        assertTrue(colors.dark)

        compose.runOnUiThread { AppearanceStore.of(context).set(AppAppearance.Light) }
        compose.waitForIdle()
        assertFalse(colors.dark)
    }

    @Test
    fun aLightPhoneShowsTheAppDarkWhenTheReaderPicksDark() {
        var colors: PeekColors? = null
        compose.setContent { PeekTheme(carrierIdentities = null) { colors = PeekTheme.colors } }
        compose.runOnUiThread { AppearanceStore.of(context).set(AppAppearance.Dark) }
        compose.waitForIdle()
        assertTrue(colors!!.dark)
    }
}
