package com.plhery.peekdeliverytracker.platform

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.pm.PackageManager
import android.util.Size
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.resolutionselector.AspectRatioStrategy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.plhery.peekdeliverytracker.ui.deliveries.LabelScanner

/**
 * The label scanner on a device (`DataScannerViewController`): CameraX's back camera filling the scanner's room, as the
 * iPhone's fills its view, pinched to zoom between its widest and its nearest (`isPinchToZoomEnabled`), and ML Kit's
 * barcode model, bundled in the app, reading every format it knows (`recognizedDataTypes: [.barcode()]`) in the newest
 * frame at 1280 × 720, the middle of the resolutions the analysis takes (`qualityLevel: .balanced`). It reads on the
 * device, with no account, key or download, and the app doesn't send the logs ML Kit keeps of its use
 * (AndroidManifest.xml). Of a frame's barcodes it follows the one nearest the middle, which it tells first, as
 * `recognizesMultipleItems: false` follows one; a tap doesn't focus, as DataScanner's doesn't. Bound to the window's
 * lifecycle, the camera closes while the app is away and opens again as it comes back, as DataScanner pauses and
 * resumes; a window made again (a turn, a fold) opens it again in the new window.
 *
 * What DataScanner draws that this doesn't: its guidance (`isGuidanceEnabled`), Apple's own words, which the app's copy
 * doesn't hold.
 */
object CameraLabelScanner : LabelScanner {
    /**
     * Whether the camera was refused without the system remembering a refusal: its question turned away unanswered, or
     * refused for good with no question asked. Kept until the app's process ends.
     */
    private var refusedUnasked = false

    /** Each answer the system gave, which availability is read again for. */
    private var answers by mutableIntStateOf(0)

    @Composable
    override fun available(): Boolean {
        val context = LocalContext.current
        val activity = LocalActivity.current
        var front by remember { mutableIntStateOf(0) }
        LifecycleResumeEffect(Unit) {
            front++
            onPauseOrDispose {}
        }
        val heard = answers
        return remember(front, heard, context, activity) { available(context, activity) }
    }

    /**
     * `isSupported && isAvailable`: a camera on the device's back, not disabled by its policy (iOS's restricted), and
     * the camera allowed or not yet refused. Android asks a second time where the iPhone asks once, so a refusal takes
     * the scanner away as the iPhone's denied does, until the reader allows the camera in the system's settings and
     * comes back: one the system remembers (`shouldShowRequestPermissionRationale`, which the camera denied in settings
     * gives too), or the question turned away or refused with none asked ([refusedUnasked]). A permission given only
     * once and gone again leaves the scanner, whose system asks again as the reader chose.
     */
    // The back camera only, as DataScanner's: a front camera alone (a Chromebook's) scans nothing.
    @SuppressLint("UnsupportedChromeOsCameraSystemFeature")
    internal fun available(context: Context, activity: Activity?): Boolean {
        if (!context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) return false
        if (context.getSystemService(DevicePolicyManager::class.java)?.getCameraDisabled(null) == true) return false
        if (cameraAllowed(context)) {
            refusedUnasked = false
            return true
        }
        return !refusedUnasked && activity?.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) != true
    }

    /** The system's answer, once it asked as the scanner opened. */
    internal fun answered(allowed: Boolean, activity: Activity?) {
        if (!allowed && activity?.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) != true) refusedUnasked = true
        answers++
    }

    private fun cameraAllowed(context: Context) =
        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    /**
     * The camera, black until its first frame, and black where it isn't allowed. The system asks for it as the scanner
     * first opens, with no word of the app's before it, as the iPhone's asks when DataScanner starts: once each time
     * the scanner opens, through a window made again, whose answer comes to the new window.
     */
    @Composable
    override fun Camera(onRead: (List<String?>) -> Unit, modifier: Modifier) {
        val context = LocalContext.current
        val activity = LocalActivity.current
        val read by rememberUpdatedState(onRead)
        var allowed by remember { mutableStateOf(cameraAllowed(context)) }
        var asked by rememberSaveable { mutableStateOf(false) }
        val ask = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            allowed = granted
            answered(granted, activity)
        }
        LaunchedEffect(Unit) {
            if (allowed || asked) return@LaunchedEffect
            asked = true
            ask.launch(Manifest.permission.CAMERA)
        }
        // Allowed in the system's settings meanwhile.
        LifecycleResumeEffect(Unit) {
            if (cameraAllowed(context)) allowed = true
            onPauseOrDispose {}
        }
        var highlight by remember { mutableStateOf(emptyList<Offset>()) }
        val room = remember { arrayOf(IntSize.Zero) }
        Box(modifier.background(Color.Black).clipToBounds().onSizeChanged { room[0] = it }) {
            if (allowed) {
                val lifecycle = LocalLifecycleOwner.current
                val controller = remember { scannerController(context) }
                val detector = remember { BarcodeScanning.getClient() }
                AndroidView(
                    factory = { PreviewView(it).apply { this.controller = controller } },
                    modifier = Modifier.fillMaxSize(),
                )
                DisposableEffect(controller, detector, lifecycle) {
                    val main = ContextCompat.getMainExecutor(context)
                    val analyzer = MlKitAnalyzer(listOf(detector), ImageAnalysis.COORDINATE_SYSTEM_VIEW_REFERENCED, main) { result ->
                        val middle = Offset(room[0].width / 2f, room[0].height / 2f)
                        val barcodes = result.getValue(detector).orEmpty().sortedBy { it.distance(middle) }
                        highlight = barcodes.firstOrNull()?.cornerPoints?.map { Offset(it.x.toFloat(), it.y.toFloat()) }.orEmpty()
                        if (barcodes.isNotEmpty()) read(barcodes.map { it.rawValue })
                    }
                    controller.setImageAnalysisAnalyzer(main, analyzer)
                    controller.bindToLifecycle(lifecycle)
                    onDispose {
                        controller.clearImageAnalysisAnalyzer()
                        controller.unbind()
                        detector.close()
                    }
                }
            }
            Canvas(Modifier.fillMaxSize()) { drawHighlight(highlight) }
        }
    }

    /**
     * The back camera's preview and analysis, the newest frame only, pinched to zoom; no photo, no tap to focus. Both
     * 16:9, so a phone with a 720-wide screen still analyses 1280 x 720, the pixels a label's Code 128 needs at arm's
     * length (ML Kit asks two for each bar): with CameraX's 4:3 it gets 640 x 480 there, as 1280 x 960 is more than
     * such a screen lets the two streams share.
     */
    private fun scannerController(context: Context) = LifecycleCameraController(context).apply {
        cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
        isPinchToZoomEnabled = true
        isTapToFocusEnabled = false
        imageAnalysisBackpressureStrategy = ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
        previewResolutionSelector = ResolutionSelector.Builder()
            .setAspectRatioStrategy(AspectRatioStrategy.RATIO_16_9_FALLBACK_AUTO_STRATEGY)
            .build()
        imageAnalysisResolutionSelector = ResolutionSelector.Builder()
            .setAspectRatioStrategy(AspectRatioStrategy.RATIO_16_9_FALLBACK_AUTO_STRATEGY)
            .setResolutionStrategy(ResolutionStrategy(ANALYSIS_SIZE, ResolutionStrategy.FALLBACK_RULE_CLOSEST_HIGHER_THEN_LOWER))
            .build()
    }

    private fun Barcode.distance(point: Offset): Float {
        val box = boundingBox ?: return Float.MAX_VALUE
        return (Offset(box.exactCenterX(), box.exactCenterY()) - point).getDistanceSquared()
    }

    /**
     * `isHighlightingEnabled`: the barcode followed, its four corners rounded, washed and outlined in white. The
     * simulator has no camera and Apple's documentation doesn't draw it, so this is DataScanner's default highlight as
     * far as it is known: its look is to be checked against an iPhone's.
     */
    private fun DrawScope.drawHighlight(corners: List<Offset>) {
        if (corners.size < 3) return
        val radius = HIGHLIGHT_RADIUS.toPx()
        val path = Path()
        corners.forEachIndexed { index, corner ->
            val before = corners[(index + corners.size - 1) % corners.size]
            val after = corners[(index + 1) % corners.size]
            val round = minOf(radius, (before - corner).getDistance() / 2, (after - corner).getDistance() / 2)
            val start = corner + (before - corner).toward(round)
            val end = corner + (after - corner).toward(round)
            if (index == 0) path.moveTo(start.x, start.y) else path.lineTo(start.x, start.y)
            path.quadraticTo(corner.x, corner.y, end.x, end.y)
        }
        path.close()
        drawPath(path, Color.White.copy(alpha = HIGHLIGHT_WASH))
        drawPath(path, Color.White, style = Stroke(HIGHLIGHT_LINE.toPx()))
    }

    /** This offset's direction, `length` long. */
    private fun Offset.toward(length: Float): Offset {
        val distance = getDistance()
        return if (distance == 0f) Offset.Zero else this * (length / distance)
    }

    private val ANALYSIS_SIZE = Size(1280, 720)
    private val HIGHLIGHT_RADIUS = 6.dp
    private val HIGHLIGHT_LINE = 2.dp
    private const val HIGHLIGHT_WASH = 0.25f
}
