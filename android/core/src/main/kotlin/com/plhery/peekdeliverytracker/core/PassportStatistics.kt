package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import kotlinx.serialization.Serializable

// The helper from PassportStatistics.swift that the carrier catalog uses.

object TrackingLocation {
    // Apple calls CN "China mainland" in every language, never what carriers or the web write.
    private val chinaNames = mapOf(
        AppLanguage.En to "China", AppLanguage.De to "China", AppLanguage.Fr to "Chine", AppLanguage.It to "Cina",
        AppLanguage.Es to "China", AppLanguage.Pt to "China", AppLanguage.Pl to "Chiny",
    )

    /** Foundation's regions and their names in each language, which android/tools/CountryNames.swift writes. */
    @Serializable
    private class RegionNames(val regions: List<String>, val names: Map<String, Map<String, String>>)

    private val regionNames: RegionNames by lazy {
        ApiJson.decodeFromString(RegionNames.serializer(), SharedResources.text("CountryNames.json"))
    }

    /** `Locale.Region.isoRegions`' codes. */
    val regions: List<String> get() = regionNames.regions

    /** `locale.localizedString(forRegionCode:)`, which reads the code in either case. */
    fun countryName(code: String, language: AppLanguage): String {
        if (code == "CN") chinaNames[language]?.let { return it }
        val key = if (code.all { it in 'a'..'z' || it in 'A'..'Z' || it in '0'..'9' }) code.uppercase() else code
        return regionNames.names[language.rawValue]?.get(key) ?: code
    }
}
