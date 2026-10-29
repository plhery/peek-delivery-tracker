package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.italic
import com.plhery.peekdeliverytracker.ui.theme.sfLines

/**
 * `CarrierFleetMark` (A-S75): the carrier's truck and its name in caption bold, in the carrier's colour: DHL's in
 * italics as its own mark is, GLS's with its full stop. It reads as the carrier's full name.
 */
@Composable
fun CarrierFleetMark(identity: CarrierVisualIdentity, modifier: Modifier = Modifier) {
    val type = PeekTheme.type
    val brand = PeekTheme.colors.resolve(identity.brand)
    Row(
        modifier.clearAndSetSemantics { contentDescription = identity.fullName },
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CarrierTruck(identity)
        val style = type.style(IosTextStyle.Caption, FontWeight.Bold).sfLines()
        Text(
            identity.name + if (identity.family == "gls") "." else "",
            color = brand,
            style = if (identity.family == "dhl") style.italic() else style,
        )
    }
}
