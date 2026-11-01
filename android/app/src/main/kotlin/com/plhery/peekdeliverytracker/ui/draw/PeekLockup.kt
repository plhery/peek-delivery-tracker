package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.theme.CappedDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme

/**
 * `PeekLockup` (SignInView.swift): the mark, 28 wide, 9 from the app's name in bold 16 points over the tagline in 10,
 * both growing with the text size as subheadline and caption 2 do (`@ScaledMetric`) up to accessibility 1's, the name
 * in the ink, tightened 0.4, and the tagline in the soft ink, each on one line and shrinking to 70 % rather than be cut.
 * TalkBack reads it as one element, the name then the tagline.
 */
@Composable
fun PeekLockup(modifier: Modifier = Modifier) {
    CappedDynamicTypeSize(DynamicTypeSize.Accessibility1) {
        val colors = PeekTheme.colors
        val type = PeekTheme.type
        Row(
            modifier.semantics(mergeDescendants = true) {},
            horizontalArrangement = Arrangement.spacedBy(9.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PeekMark(28.dp)
            Column {
                val name = type.tracking(type.fixed(type.scaled(16f, IosTextStyle.Subheadline), FontWeight.Bold), -0.4f)
                LockupLine(localized("app.title"), name.copy(color = colors.ink))
                val tagline = type.fixed(type.scaled(10f, IosTextStyle.Caption2))
                LockupLine(localized("native.tagline"), tagline.copy(color = colors.inkSoft))
            }
        }
    }
}

/** One line of the lockup, shrinking to 70 % rather than be cut (`.lineLimit(1).minimumScaleFactor(0.7)`). */
@Composable
private fun LockupLine(text: String, style: TextStyle) {
    Text(
        text, style = style, maxLines = 1, softWrap = false,
        autoSize = TextAutoSize.StepBased(minFontSize = style.fontSize * LOCKUP_MINIMUM_SCALE, maxFontSize = style.fontSize),
    )
}

private const val LOCKUP_MINIMUM_SCALE = 0.7f
