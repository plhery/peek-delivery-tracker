package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.Radii
import com.plhery.peekdeliverytracker.ui.theme.sfLines

/**
 * `NoticeBanner` (DesignSystem.swift): [symbol] in semibold body in [tint], in a circle 28 across of the tint at 12 %,
 * 12 from the [title] in semibold subheadline over the [message] in the secondary grey caption (none when it is
 * empty), 3 apart, then the action ([actionTitle]) in semibold caption in the tint, a plain button 3 under them; 14 in
 * from the edges of a tint at 9 % with corners of 14, as wide as it is let be. The words stop 12 short of the end, as
 * the HStack spaces its closing `Spacer(minLength: 0)` from them.
 */
@Composable
fun NoticeBanner(
    symbol: String,
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    tint: Color = PeekTheme.colors.accent,
    actionTitle: String? = null,
    action: (() -> Unit)? = null,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Row(
        modifier
            .fillMaxWidth()
            .background(tint.copy(alpha = tint.alpha * 0.09f), ContinuousRoundedShape(Radii.noticeBanner))
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Box(Modifier.size(28.dp).background(tint.copy(alpha = tint.alpha * 0.12f), CircleShape), contentAlignment = Alignment.Center) {
            PeekSymbol(symbol, font = type.style(IosTextStyle.Body, FontWeight.SemiBold), tint = tint)
        }
        Column(Modifier.weight(1f).padding(end = 12.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(title, color = colors.label, style = type.style(IosTextStyle.Subheadline, FontWeight.SemiBold).sfLines())
            if (message.isNotEmpty()) Text(message, color = colors.secondaryLabel, style = type.caption.sfLines())
            if (actionTitle != null && action != null) {
                Text(
                    actionTitle,
                    Modifier
                        .padding(top = 3.dp)
                        .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = action),
                    color = tint,
                    style = type.style(IosTextStyle.Caption, FontWeight.SemiBold).sfLines(),
                )
            }
        }
    }
}
