package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.plhery.peekdeliverytracker.core.AccountInvitationMemory

/**
 * FirstOpenView.swift's `AccountInvitationVisit`: the guest's invitation to an account as a visit to the list shows it
 * ([DeviceAccountRow]). Whether it stands is decided as the visit begins, and its × as the row comes on screen: both
 * hold until the app is in use again ([resume]), so that the row doesn't come and go under its reader. One for the
 * process, for every window it makes.
 */
class AccountInvitationVisit(private val memory: AccountInvitationMemory) {
    var stands: Boolean by mutableStateOf(memory.shows)
        private set

    var closable: Boolean by mutableStateOf(memory.closable)
        private set

    private var onScreen = false

    /** The app is in use again: a visit may begin, which decides afresh, and counts the row if it is on screen. */
    fun resume() {
        stands = memory.shows
        if (stands && onScreen) seen()
    }

    fun appeared() {
        onScreen = true
        seen()
    }

    fun disappeared() {
        onScreen = false
    }

    fun close() {
        memory.close()
        stands = false
    }

    private fun seen() {
        memory.seen()
        closable = memory.closable
    }
}
