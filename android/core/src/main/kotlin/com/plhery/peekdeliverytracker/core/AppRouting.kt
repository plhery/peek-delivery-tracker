package com.plhery.peekdeliverytracker.core

// From AppRouting.swift, what the app configuration checks. The routing of links comes with its own port.

object OAuthFlow {
    private val customProvider = Regex("\\Acustom:[a-z0-9:-]+\\z")

    fun validCustomProvider(identifier: String): Boolean =
        identifier.characterCount <= 50 && customProvider.containsMatchIn(identifier)
}
