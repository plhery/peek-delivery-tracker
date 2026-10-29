package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.Radii
import com.plhery.peekdeliverytracker.ui.theme.carrierIdentity
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** The mark, its eyes, the carriers' trucks and the corners, light and dark, against their recorded baselines. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class DrawingScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    private fun shoot(name: String, dark: Boolean, content: @Composable () -> Unit) {
        val identities = runBlocking { CarrierIdentities.load(CarrierCatalog.shared) }
        compose.setContent {
            PeekTheme(dark = dark, highContrast = false, reduceMotion = true, carrierIdentities = identities) {
                Box(Modifier.testTag(SHOT).background(PeekTheme.colors.background).padding(12.dp)) { content() }
            }
        }
        compose.screenshot(name + if (dark) "_dark" else "_light")
    }

    @Test
    fun marksLight() = shoot("marks", dark = false) { Marks() }

    @Test
    fun marksDark() = shoot("marks", dark = true) { Marks() }

    @Test
    @Config(qualifiers = "w600dp-h844dp-mdpi")
    fun mark512Light() = shoot("mark512", dark = false) { PeekMark(512.dp) }

    @Test
    @Config(qualifiers = "w600dp-h844dp-mdpi")
    fun mark512Dark() = shoot("mark512", dark = true) { PeekMark(512.dp) }

    @Test
    fun trucksLight() = shoot("trucks", dark = false) { Trucks() }

    @Test
    fun trucksDark() = shoot("trucks", dark = true) { Trucks() }

    @Test
    fun shapesLight() = shoot("shapes", dark = false) { Shapes() }

    @Test
    fun shapesDark() = shoot("shapes", dark = true) { Shapes() }
}

@Composable
private fun Marks() {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
        PeekMark(18.dp)
        PeekMark(30.dp)
        PeekMark(78.dp)
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            PeekEyes(PeekTheme.colors.ink)
            PeekEyes(PeekTheme.colors.ink, height = 30.dp)
            // The cut pupils show what is behind them.
            Box(Modifier.background(PeekTheme.colors.accent).padding(4.dp)) { PeekEyes(PeekTheme.colors.onAccent, height = 24.dp) }
        }
    }
}

/** c3x's decal turns sharply, where CoreGraphics' mitre limit of 10 keeps the points that Android's 4 would cut. */
private val carriers = listOf("c3x", "dhl", "ups", "fedex", "dpd", "swiss-post", "unknown")

@Composable
private fun Trucks() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            for (carrier in carriers) {
                carrierIdentity(CarrierId(carrier), AppLanguage.En)?.let { CarrierTruck(it) }
            }
        }
        // Four times as large, to show the decals' joins.
        for (row in carriers.chunked(3)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                for (carrier in row) {
                    val identity = carrierIdentity(CarrierId(carrier), AppLanguage.En) ?: continue
                    Box(Modifier.size(108.dp, 72.dp)) {
                        CarrierTruck(
                            identity,
                            Modifier.graphicsLayer {
                                scaleX = 4f
                                scaleY = 4f
                                transformOrigin = TransformOrigin(0f, 0f)
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Shapes() {
    val colors = PeekTheme.colors
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.Bottom) {
            for (radius in listOf(Radii.mapLabelPill, Radii.toastAnswer, Radii.noticeBanner, Radii.card, Radii.shelf, Radii.primaryButton)) {
                Corner(radius, 48.dp)
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.Bottom) {
            for (radius in listOf(Radii.nextUp, Radii.experimentalSurface, Radii.heroBottom)) Corner(radius, 96.dp)
        }
        // SwiftUI's continuous corner over Compose's circular one, at the radii X-AD37 compares with the iPhone: the
        // circle shows past it where the continuous corner has already begun to turn.
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            for (radius in listOf(Radii.nextUp, Radii.heroBottom)) {
                Box(Modifier.size(120.dp, 96.dp)) {
                    Box(Modifier.matchParentSize().clip(RoundedCornerShape(radius)).background(colors.warning))
                    Box(Modifier.matchParentSize().clip(ContinuousRoundedShape(radius)).background(colors.cream))
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(72.dp, 26.dp).clip(CapsuleShape).background(colors.accent))
            Box(Modifier.size(140.dp, 44.dp).clip(CapsuleShape).background(colors.ink))
            Box(Modifier.size(26.dp, 60.dp).clip(CapsuleShape).background(colors.inkSoft))
            Box(Modifier.size(80.dp, 26.dp).clip(ContinuousRoundedShape(Radii.pointerChip)).background(colors.cream))
        }
    }
}

@Composable
private fun Corner(radius: Dp, side: Dp) {
    val colors = PeekTheme.colors
    Box(
        Modifier.size(side).clip(ContinuousRoundedShape(radius)).background(colors.paper)
            .border(1.dp, colors.separator, ContinuousRoundedShape(radius)),
        contentAlignment = Alignment.Center,
    ) {
        Text("${radius.value.toInt()}", style = PeekTheme.type.caption2, color = colors.inkSoft)
    }
}
