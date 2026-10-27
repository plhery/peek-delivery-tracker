package com.plhery.peekdeliverytracker.core

import java.util.Locale

// The helper from PassportStatistics.swift that the carrier catalog uses.

object TrackingLocation {
    // Apple calls CN "China mainland" in every language, never what carriers or the web write.
    private val chinaNames = mapOf(
        AppLanguage.En to "China", AppLanguage.De to "China", AppLanguage.Fr to "Chine", AppLanguage.It to "Cina",
        AppLanguage.Es to "China", AppLanguage.Pt to "China", AppLanguage.Pl to "Chiny",
    )

    fun countryName(code: String, language: AppLanguage): String {
        if (code == "CN") chinaNames[language]?.let { return it }
        val region = runCatching { Locale.Builder().setRegion(code).build() }.getOrNull() ?: return code
        return region.getDisplayCountry(language.locale).ifEmpty { code }
    }
}
