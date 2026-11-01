package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import com.plhery.peekdeliverytracker.core.AddParcelView as AddParcelLogic
import com.plhery.peekdeliverytracker.ui.sheet.BarCancel
import com.plhery.peekdeliverytracker.ui.sheet.FullScreenCover
import com.plhery.peekdeliverytracker.ui.sheet.InlineBar
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme

/**
 * `LabelScanner` and `TrackingDataScanner`'s camera: whether this device scans a label's barcode ([available]), and the
 * camera that reads them ([Camera]). On a device, CameraX and ML Kit's bundled barcode model
 * (platform/CameraLabelScanner.kt); elsewhere none ([NoLabelScanner]); a fake in the tests.
 */
@Stable
interface LabelScanner {
    /**
     * `LabelScanner.isAvailable` (`DataScannerViewController.isSupported && isAvailable`): a camera on the device's back,
     * which the reader hasn't refused the app, read again each time the window comes back to the front, as the iPhone's
     * is each time its views are drawn.
     */
    @Composable
    fun available(): Boolean

    /**
     * The camera across [modifier]'s room for as long as it is drawn, telling [onRead] the payloads of the barcodes in
     * each frame it reads, the one it follows first (null for one whose payload isn't text). It draws what DataScanner
     * draws over what it reads.
     */
    @Composable
    fun Camera(onRead: (List<String?>) -> Unit, modifier: Modifier)
}

/** No scanner: the Mac's `LabelScanner`, and any window not given one. */
object NoLabelScanner : LabelScanner {
    @Composable
    override fun available(): Boolean = false

    @Composable
    override fun Camera(onRead: (List<String?>) -> Unit, modifier: Modifier) {
        Box(modifier.background(Color.Black))
    }
}

/** The window's label scanner. */
val LocalLabelScanner = staticCompositionLocalOf<LabelScanner> { NoLabelScanner }

/**
 * `TrackingScannerView` in Add's `.fullScreenCover(isPresented: $showingScanner)` (B-S32): over the whole window, Add's
 * form included in a regular window, as UIKit's full-screen presentation covers the iPad too, coming up and going down
 * on UIKit's cover spring ([FullScreenCover]). The first barcode read with a payload goes to [onScan], once
 * ([AddParcelLogic.FirstScan]); Cancel and the back go to [onCancel]. The camera runs until the cover has gone, as
 * `dismantleUIViewController` stops it.
 */
@Composable
internal fun TrackingScannerCover(visible: Boolean, onScan: (String) -> Unit, onCancel: () -> Unit) {
    val title = localized("add.scanTitle")
    FullScreenCover(visible = visible, onDismiss = onCancel, paneTitle = title) {
        TrackingScannerView(title, onScan, onCancel)
    }
}

/**
 * The scanner's `NavigationStack`: under the status bar its inline bar, "Scan a tracking barcode" in its middle and
 * Cancel at its leading end, on the system background a full-screen presentation stands on; under the bar the camera,
 * down to the window's foot (`.ignoresSafeArea(edges: .bottom)`), across its width but clear of a cutout or the system's
 * bar on its sides, as the iPhone's keeps to the safe area there. TalkBack reads the bar first.
 */
@Composable
private fun TrackingScannerView(title: String, onScan: (String) -> Unit, cancel: () -> Unit) {
    val scanner = LocalLabelScanner.current
    val ground = scannerGround(PeekTheme.colors.dark)
    val first = remember { AddParcelLogic.FirstScan() }
    val scanned by rememberUpdatedState(onScan)
    Column(
        Modifier
            .fillMaxSize()
            .background(ground)
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
            .testTag(SCANNER)
            .semantics { isTraversalGroup = true },
    ) {
        InlineBar(
            title,
            leading = { BarCancel(cancel, SCANNER_CANCEL, behind = ground) },
            Modifier
                .semantics { traversalIndex = -1f }
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top)),
        )
        scanner.Camera(
            onRead = { read -> first.read(read)?.let { scanned(it) } },
            modifier = Modifier.fillMaxWidth().weight(1f).testTag(SCANNER_CAMERA),
        )
    }
}

/** UIKit's `systemBackground` at a full-screen presentation's level: white, black in dark. */
private fun scannerGround(dark: Boolean): Color = if (dark) Color.Black else Color.White

internal const val SCANNER = "addParcel.scanner"
internal const val SCANNER_CANCEL = "addParcel.scanner.cancel"
internal const val SCANNER_CAMERA = "addParcel.scanner.camera"
