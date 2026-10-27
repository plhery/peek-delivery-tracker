package com.plhery.peekdeliverytracker.core

// From FirstOpenView.swift, what isn't the view.

/** How someone starts following their first parcel. The deliveries list owns the sheet that adds it. */
sealed class FirstParcelRequest {
    /** Whatever is on the clipboard, read once the person asked for it, or text dropped on the deliveries. */
    data class Paste(val text: String) : FirstParcelRequest()
    data object Type : FirstParcelRequest()
    data object Scan : FirstParcelRequest()

    companion object {
        /** As much pasted text as Add takes in. */
        const val pasteLimit = 2000

        /**
         * Text or a link dragged onto the deliveries from another app is taken as a paste: each piece once, one to a
         * line, as the share extension joins them. Null when nothing in it reads as a tracking number, or as a shop's
         * order number, which Add explains.
         */
        fun drop(items: List<String>, catalog: CarrierCatalog = CarrierCatalog.shared): FirstParcelRequest? {
            val pieces = mutableListOf<String>()
            for (item in items) {
                val piece = item.trimmingWhitespacesAndNewlines().nonEmpty ?: continue
                if (piece !in pieces) pieces.add(piece)
            }
            val text = pieces.joinToString("\n").characterPrefix(pasteLimit)
            return if (catalog.read(text).trackingNumber.isEmpty() && catalog.order(text) == null) null else Paste(text)
        }
    }
}
