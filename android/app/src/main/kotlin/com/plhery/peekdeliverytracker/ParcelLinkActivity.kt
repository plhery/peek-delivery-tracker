package com.plhery.peekdeliverytracker

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle

/**
 * Where a parcel's link comes in (A-L02, B-L01): `https://<the site>/p/<id>` on the site's hosts and
 * `peekdeliverytracker://p/<id>`. The iPhone reads the URL `.onOpenURL` hands it and keeps it nowhere; an activity's
 * intent stays with its task, which the system keeps for Recents, on disk and across a reboot, and a link delivered to
 * the app's window, the root of its task, becomes that task's intent. So the link, `#k=` owner key and all, comes to
 * this window instead, which shows nothing (`Theme.NoDisplay`) in a task of its own (an empty `taskAffinity`) that
 * Recents neither shows nor keeps (`excludeFromRecents`): it hands the link to the app in memory
 * ([PeekApplication.openParcelLink], which opens it at once or once the stores are made), opens the app's window with
 * the launcher's intent, which carries no link, and goes with its task. Cold, that window starts and opens the sheet
 * once its stores are made; with the app running, its task comes forward and the sheet opens on the link over whatever
 * is up, as the iPhone's does. If the process goes before the sheet shows, the link is lost, as on the iPhone, which
 * hears a link once. Any address the app doesn't read as a parcel's link opens the app, as before.
 */
class ParcelLinkActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent?.action == Intent.ACTION_VIEW) intent.dataString?.let((application as PeekApplication)::openParcelLink)
        startActivity(Intent.makeMainActivity(ComponentName(this, MainActivity::class.java)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        // Removed from Recents with its task, which it roots when the link came in a new task, as browsers send one.
        finishAndRemoveTask()
    }
}
