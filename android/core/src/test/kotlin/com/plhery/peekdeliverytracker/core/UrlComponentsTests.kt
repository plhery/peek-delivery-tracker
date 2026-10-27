package com.plhery.peekdeliverytracker.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.BeforeClass
import org.junit.Test

/** What iOS 27's URLComponents(string:) makes of links people paste. Every expected value is Foundation's. */
class UrlComponentsTests {
    companion object {
        @JvmStatic
        @BeforeClass
        fun installIcu() = TestIcu.install()
    }

    private fun url(string: String) = UrlComponents.parse(string)?.url

    @Test
    fun encodesWhatALinkCannotHold() {
        assertEquals(
            "https://www.gls-pakete.de/sendungsverfolgung?trackingNumber=0.10.2.40193547%7C0.10.2.40193547",
            url("https://www.gls-pakete.de/sendungsverfolgung?trackingNumber=0.10.2.40193547|0.10.2.40193547"),
        )
        assertEquals(
            listOf(UrlComponents.QueryItem("trackingNumber", "0.10.2.40193547|0.10.2.40193547")),
            UrlComponents.parse("https://www.gls-pakete.de/sendungsverfolgung?trackingNumber=0.10.2.40193547|0.10.2.40193547")?.queryItems,
        )
        assertEquals("https://a.com/%7Bx%7D%5E%60%5C", url("https://a.com/{x}^`\\"))
        assertEquals("https://a.com/%F0%9F%91%8D?q=%F0%9F%91%8D#%F0%9F%91%8D", url("https://a.com/👍?q=👍#👍"))
        // Brackets outside the host, and a second "#".
        val brackets = UrlComponents.parse("https://a.com/[x]?q=[1]#a#b")
        assertEquals("https://a.com/%5Bx%5D?q=%5B1%5D#a%23b", brackets?.url)
        assertEquals("/[x]", brackets?.path)
        assertEquals("a#b", brackets?.fragment)
    }

    @Test
    fun encodesEveryPercentOfAComponentWithAStrayOne() {
        assertEquals("https://a.com/%25", url("https://a.com/%"))
        assertEquals("/%", UrlComponents.parse("https://a.com/%")?.path)
        assertEquals("https://a.com/x?y=%25zz", url("https://a.com/x?y=%zz"))
        val mixed = UrlComponents.parse("https://a.com/x?q=%E2%82%AC|")
        assertEquals("https://a.com/x?q=%25E2%2582%25AC%7C", mixed?.url)
        assertEquals("q=%E2%82%AC|", mixed?.query)
        assertEquals(listOf(UrlComponents.QueryItem("q", "%E2%82%AC|")), mixed?.queryItems)
        // Only the component that needed it.
        val path = UrlComponents.parse("https://a.com/p%41?q=%41|")!!
        assertEquals("https://a.com/p%41?q=%2541%7C", path.url)
        path.path = path.path
        assertEquals("https://a.com/pA?q=%2541%7C", path.url)
    }

    @Test
    fun aValueThatIsNotUtf8IsNil() {
        val link = UrlComponents.parse("https://www.ups.com/track?tracknum=1Z%20x%FF")!!
        assertEquals("https://www.ups.com/track?tracknum=1Z%20x%FF", link.url)
        assertEquals(listOf(UrlComponents.QueryItem("tracknum", null)), link.queryItems)
        assertNull(link.query)
        link.queryItems = link.queryItems!! + UrlComponents.QueryItem("lang", "de")
        assertEquals("https://www.ups.com/track?tracknum&lang=de", link.url)
    }

    @Test
    fun hostsTakeTheirIdnaForm() {
        val munich = UrlComponents.parse("https://münchen.de/t/0000000000000000000000")
        assertEquals("https://xn--mnchen-3ya.de/t/0000000000000000000000", munich?.url)
        assertEquals("münchen.de", munich?.host)
        assertEquals("münchen.de", UrlComponents.parse("https://xn--mnchen-3ya.de/t/1")?.host)
        // ẞ is ß, as on the iPhone, whatever the phone's ICU.
        assertEquals("https://xn--strae-oqa.example/", url("https://stra\u1E9Ee.example/"))
        assertEquals("xn--strae-oqa.example", Url.parse("https://stra\u1E9Ee.example/")?.host)
        assertEquals("straße.example", UrlComponents.parse("https://stra\u1E9Ee.example/")?.host)
        assertEquals("https://dhl.de/x", url("https://ｄｈｌ.ｄｅ/x"))
        assertEquals("dhl.de", UrlComponents.parse("https://ｄｈｌ.ｄｅ/x")?.host)
        assertEquals("WWW.UPS.COM", UrlComponents.parse("HTTPS://WWW.UPS.COM/track?tracknum=1")?.host)
        // A host IDNA refuses, or cannot decode.
        assertNull(UrlComponents.parse("https://a|b.com/x"))
        assertNull(UrlComponents.parse("http://a b.com/"))
        assertEquals("https://xn--zz.de/t/1", url("https://xn--zz.de/t/1"))
        assertNull(UrlComponents.parse("https://xn--zz.de/t/1")?.host)
        // ICU's UTF-8 reading, which Foundation uses, sees an empty last label after ASCII ones.
        assertNull(UrlComponents.parse("https://a.\uFEFF/"))
        assertNull(UrlComponents.parse("https://DHL.DE.\uFEFF#"))
        assertEquals("ü.", UrlComponents.parse("https://ü.\uFEFF/")?.host)
        assertEquals("a.com", UrlComponents.parse("https://a\uFEFF.com/")?.host)
    }

    @Test
    fun readsAuthoritiesAsFoundationDoes() {
        val zone = UrlComponents.parse("https://[::1%en0]/x")
        assertEquals("https://[::1%25en0]/x", zone?.url)
        assertEquals("[::1%en0]", zone?.host)
        assertNull(UrlComponents.parse("https://h.com:8a/p"))
        // An empty port is kept as written, and dropped once the URL changes.
        val emptyPort = UrlComponents.parse("https://h.com:/p")!!
        assertEquals("https://h.com:/p", emptyPort.url)
        emptyPort.queryItems = listOf(UrlComponents.QueryItem("lang", "de"))
        assertEquals("https://h.com/p?lang=de", emptyPort.url)
    }

    @Test
    fun decodingDropsAByteOrderMarkThatStartsTheResult() {
        assertEquals("abc", UrlComponents.parse("https://h.com/x?%EF%BB%BFabc")?.query)
        assertEquals("\uFEFFabc", UrlComponents.parse("https://h.com/x?%EF%BB%BF%EF%BB%BFabc")?.query)
        assertEquals("%41", UrlComponents.parse("https://h.com/x?\uFEFF%41")?.query)
        assertEquals("é", UrlComponents.parse("HTTPS://%EF%BB%BF%C3%A9/")?.host)
        // String's own removingPercentEncoding keeps it.
        assertEquals("\uFEFFabc", "%EF%BB%BFabc".removingPercentEncoding())
    }

    @Test
    fun queryItemsSetLikeFoundation() {
        val link = UrlComponents.parse("https://a.com/x?a=b=c&&d")!!
        assertEquals(
            listOf(UrlComponents.QueryItem("a", "b=c"), UrlComponents.QueryItem("", null), UrlComponents.QueryItem("d", null)),
            link.queryItems,
        )
        link.queryItems = link.queryItems!!.filter { it.name != "lang" } + UrlComponents.QueryItem("lang", "de")
        assertEquals("https://a.com/x?a=b%3Dc&&d&lang=de", link.url)
    }
}
