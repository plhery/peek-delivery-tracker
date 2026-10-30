package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.ParcelTrackingLink
import com.plhery.peekdeliverytracker.core.ParcelTrackingNumber
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.trackingNumberLabel
import com.plhery.peekdeliverytracker.core.trackingNumbers
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.sfLines

/**
 * `shipmentIdentity` (B-S06): the parcel's numbers, 2 in from the sides, each but an empty one (a number the link of
 * someone else's parcel hides). What a number is in caption2 secondary (`trackingNumberLabel`), 4 above it in
 * monospaced caption as its carrier writes it (`CarrierCatalog.format`), which a long press selects (B-G04); at the
 * line's end its copy, a 14-point copy symbol in the secondary grey in 44 square, a check while its row says it copied
 * the number (`copied`). At the accessibility sizes the number takes the line and its copy goes under it, saying what
 * it does in footnote, 44 tall. TalkBack hears the copy as "Copy tracking number" or "Copied", and which number it
 * copies where there are several. A copy calls `onCopy`, which puts the number on the clipboard (B-H03).
 *
 * Under each number its carrier's own pages, and after the numbers the pages that are none of theirs
 * (`ParcelTrackingLink.byNumber`, [CarrierLinks]), in `tint`.
 */
@Composable
internal fun ShipmentNumbers(
    parcel: Parcel,
    links: List<ParcelTrackingLink>,
    tint: Color,
    copied: String?,
    onCopy: (ParcelTrackingNumber) -> Unit,
    onLink: (ParcelTrackingLink) -> Unit,
    modifier: Modifier = Modifier,
) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val large = PeekTheme.dynamicTypeSize.isAccessibilitySize
    val numbers = parcel.trackingNumbers.filter { it.number.isNotEmpty() }
    val pages = ParcelTrackingLink.byNumber(links, numbers)
    Column(modifier.padding(horizontal = 2.dp)) {
        numbers.forEachIndexed { index, entry ->
            key(entry.number) {
                val label = parcel.trackingNumberLabel(entry, localizer)
                val copy = { onCopy(entry) }
                val says = copied == entry.number
                val copyLabel = localizer.text(if (says) "detail.copied" else "detail.copyTracking") + if (numbers.size > 1) " — $label" else ""
                val number = @Composable { numberModifier: Modifier ->
                    Column(numberModifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(label, color = colors.secondaryLabel, style = type.caption2.sfLines())
                        SelectionContainer {
                            Text(
                                CarrierCatalog.format(entry.number, entry.carrier),
                                Modifier.testTag(PAGE_NUMBER),
                                color = colors.label,
                                style = type.style(IosTextStyle.Caption, design = FontDesign.Monospaced).sfLines(),
                            )
                        }
                    }
                }
                if (large) {
                    Column {
                        number(Modifier)
                        CopyButton(copyLabel, copy) {
                            // Under the number, the button says what it does.
                            Row(
                                Modifier.heightIn(min = COPY_SIDE),
                                horizontalArrangement = Arrangement.spacedBy(LABEL_SPACING),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                PeekSymbol(if (says) "checkmark" else "doc.on.doc", font = type.footnote, tint = colors.secondaryLabel)
                                Text(
                                    localizer.text(if (says) "detail.copied" else "detail.copyTracking"),
                                    color = colors.secondaryLabel,
                                    style = type.footnote.sfLines(),
                                )
                            }
                        }
                    }
                } else {
                    // 8 either side of the spacer that pushes the copy to the line's end.
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        number(Modifier.weight(1f))
                        Spacer(Modifier.width(16.dp))
                        CopyButton(copyLabel, copy) {
                            Box(Modifier.size(COPY_SIDE), contentAlignment = Alignment.Center) {
                                PeekSymbol(if (says) "checkmark" else "doc.on.doc", font = type.fixed(14f), tint = colors.secondaryLabel)
                            }
                        }
                    }
                }
                CarrierLinks(pages.own[index], own = true, tint, onLink)
            }
        }
        CarrierLinks(pages.loose, own = false, tint, onLink)
    }
}

/** A number's copy, a plain button that TalkBack hears by `label` alone, as the iPhone's dims while pressed. */
@Composable
private fun CopyButton(label: String, action: () -> Unit, content: @Composable () -> Unit) {
    Box(
        Modifier
            .testTag(PAGE_COPY_NUMBER)
            .clearAndSetSemantics {
                contentDescription = label
                role = Role.Button
                onClick {
                    action()
                    true
                }
            }
            .clickable(interactionSource = null, indication = PlainPress, onClick = action),
    ) {
        content()
    }
}

/**
 * `carrierLinks` (B-S07, B-G05, B-L05): the carriers' own pages, each "Open the {carrier} website" and a 10-point
 * arrow 6 after it, in caption in `tint`, 10 above and below and 44 tall at least. Under its own number a page needs
 * no more words; another, but the one the parcel is followed on, says at the line's end in caption2 secondary what
 * it is to the parcel: not ready yet, or an earlier journey. A tap calls `onLink`, then opens the page in the browser,
 * as the iPhone's opens Safari.
 */
@Composable
private fun CarrierLinks(links: List<ParcelTrackingLink>, own: Boolean, tint: Color, onLink: (ParcelTrackingLink) -> Unit) {
    val localizer = LocalLocalizer.current
    val type = PeekTheme.type
    val uriHandler = LocalUriHandler.current
    for (link in links) {
        key(link.id) {
            val role = if (own || link.role == ParcelTrackingLink.Role.Active) {
                null
            } else {
                localizer.text(if (link.role == ParcelTrackingLink.Role.Waiting) "detail.sourceWaiting" else "detail.sourceHistory")
            }
            Row(
                Modifier
                    .then(if (role == null) Modifier else Modifier.fillMaxWidth())
                    .heightIn(min = LINK_HEIGHT)
                    .testTag(PAGE_CARRIER_LINK)
                    // A button to TalkBack, as the quiet note's help: Compose's link role is a text's, which would
                    // take only the words.
                    .clickable(interactionSource = null, indication = PlainPress, role = Role.Button) {
                        onLink(link)
                        try {
                            uriHandler.openUri(link.url)
                        } catch (_: IllegalArgumentException) {
                            // Where no app opens web pages, as Compose's handler says: nothing, as with the iPhone's link.
                        }
                    }
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    if (role == null) Modifier else Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(LABEL_SPACING),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        localizer.text("detail.carrierWebsite", mapOf("carrier" to link.name)),
                        Modifier.weight(1f, fill = false),
                        color = tint,
                        style = type.caption.sfLines(),
                    )
                    PeekSymbol("arrow.up.right", font = type.fixed(10f), tint = tint)
                }
                if (role != null) {
                    // 6 either side of the spacer, at least 4 long.
                    Spacer(Modifier.width(16.dp))
                    Text(role, color = PeekTheme.colors.secondaryLabel, style = type.caption2.sfLines())
                }
            }
        }
    }
}

private val COPY_SIDE = 44.dp
private val LINK_HEIGHT = 44.dp
private val LABEL_SPACING = 6.dp

/** The test tags of the page's numbers, their copies and the carriers' pages. */
const val PAGE_IDENTITY = "parcelPage.identity"
const val PAGE_NUMBER = "parcelPage.number"
const val PAGE_COPY_NUMBER = "parcelPage.copyNumber"
const val PAGE_CARRIER_LINK = "parcelPage.carrierLink"
