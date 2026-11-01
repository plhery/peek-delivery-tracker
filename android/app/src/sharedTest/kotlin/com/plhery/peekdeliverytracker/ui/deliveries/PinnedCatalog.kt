package com.plhery.peekdeliverytracker.ui.deliveries

import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.SharedResources
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import org.json.JSONObject

/**
 * The bundled catalog cut to [PINNED_CARRIERS], for the screenshots that show the picker: the same count, letters and
 * rows whatever carriers a scraper release adds. A carrier for each letter keeps the whole rail, A and M fill the rows
 * the shots show, and the rest are the demo's carriers and those the shots name, with the unknown carrier a catalog
 * needs.
 */
internal val pinnedIdentities: CarrierIdentities by lazy {
    val carriers = JSONObject(SharedResources.text("CarrierCatalog.json")).getJSONObject("x-carriers")
    val pinned = JSONObject()
    for (id in PINNED_CARRIERS) carriers.optJSONObject(id)?.let { pinned.put(id, it) }
    CarrierIdentities(CarrierCatalog(JSONObject().put("x-carriers", pinned).toString().toByteArray()))
}

private val PINNED_CARRIERS = listOf(
    "139express", "a-tu-hora-express", "ab-courier", "a-duie-pyle", "a1-post", "aaa-cooper", "abf-freight", "ace-ee",
    "ace-lv", "ace-lt", "aci-express-kr", "acs-courier", "aeropost", "aex-paraguay", "afghan-post", "air-star-xpress",
    "airmee", "bangladesh-post", "c-chez-vous", "d-express", "e-cargo", "famafutar", "gaash", "houng-ah-loun",
    "icc-worldwide", "j-and-t-cargo", "kargoist", "loomis-express", "lotte-global", "lso", "m-and-p", "m-one",
    "madhur-courier", "maersk-ecommerce", "magyar-posta", "mailamericas", "mailplus-au", "mainfreight", "major-express",
    "makati-express", "maltapost", "mandae", "maritime-ontario", "maruti-air", "matkahuolto", "mauritius-post", "nacex",
    "oak-harbor", "paack", "quality-post", "rl-carriers", "saee", "t-cat", "ubi-smart-parcel", "valmo", "wahana",
    "x-express-ba", "yalidine", "z-leading",
    "aliexpress", "an-post", "chronopost", "colis-prive", "colisweb", "dhl", "dpd", "fedex", "gls-de", "gls-fr",
    "la-poste", "mondial-relay", "planzer", "relais-colis", "spring-gds", "swiss-post", "ups", "unknown",
)
