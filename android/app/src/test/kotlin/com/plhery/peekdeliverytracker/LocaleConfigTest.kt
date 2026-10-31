package com.plhery.peekdeliverytracker

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.w3c.dom.Element
import org.xmlpull.v1.XmlPullParser

/** The languages Android's Settings offers for the app (res/xml/locales_config.xml), as the iPhone's Settings does. */
@RunWith(AndroidJUnit4::class)
class LocaleConfigTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    private fun declared(): List<String> {
        val parser = context.resources.getXml(R.xml.locales_config)
        val tags = mutableListOf<String>()
        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.eventType == XmlPullParser.START_TAG && parser.name == "locale") {
                tags += parser.getAttributeValue("http://schemas.android.com/apk/res/android", "name")
            }
        }
        return tags
    }

    /** The iPhone app's CFBundleLocalizations. */
    private fun iPhoneLocalizations(): List<String> {
        val plist = File(System.getProperty("user.dir")).resolve("../../ios/PeekDeliveryTracker/Info.plist").readText()
        val array = plist.substringAfter("<key>CFBundleLocalizations</key>").substringAfter("<array>").substringBefore("</array>")
        return Regex("<string>([^<]+)</string>").findAll(array).map { it.groupValues[1] }.toList()
    }

    @Test
    fun settingsOffersEveryLanguageTheAppSpeaksInItsOrder() {
        val tags = declared()
        assertEquals(AppLanguage.entries, tags.map { AppLanguage.fromTag(it) })
        assertEquals(iPhoneLocalizations(), tags)
    }

    /** Robolectric leaves the declaration out of the application it makes, so the manifest is read as written. */
    @Test
    fun theManifestHandsSettingsTheList() {
        val manifest = File(System.getProperty("user.dir")).resolve("src/main/AndroidManifest.xml")
        val application = DocumentBuilderFactory.newInstance().apply { isNamespaceAware = true }.newDocumentBuilder()
            .parse(manifest).getElementsByTagName("application").item(0) as Element
        assertEquals("@xml/locales_config", application.getAttributeNS("http://schemas.android.com/apk/res/android", "localeConfig"))
    }
}
