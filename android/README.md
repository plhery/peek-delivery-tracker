# Android app

The app reads the data the iPhone app bundles (brand, carrier catalog, copy, map) from its assets: `:app` packages
the files `:core` reads, synced from `ios/PeekDeliveryTracker/Resources` along with its own country names, under
`peek/`, and `SharedResources` reads them there. The contract fixtures, read only by debug previews, ship in debug
builds alone.

## Two builds

The app comes in two flavors of the `store` dimension. `play`, the default, is Google Play's, with Google's libraries:
Firebase Cloud Messaging, Play In-App Review, and ML Kit's barcode model behind the label scanner. `fdroid` has none of
them, for F-Droid's catalog: its notifications come through UnifiedPush or an inbox (see Pushes), it asks for no rating
and scans no label. Code only one build has lives in `app/src/play` or `app/src/fdroid`, each behind the same
`platform/Store.kt`. The task names without a flavor (`testDebugUnitTest`, `verifyRoborazziDebug`,
`recordRoborazziDebug`, `compareRoborazziDebug`) run Google Play's build, and `lintDebug` lints both.

## App tests and screenshots

`./gradlew :app:testDebugUnitTest` runs the app's tests on the JVM with Robolectric. The design system's screenshots
are kept in `app/src/test/snapshots`: `./gradlew :app:verifyRoborazziDebug` fails when more than a thousandth of a
shot's pixels differ (see `Screenshots.kt`), and `./gradlew :app:recordRoborazziDebug` records them again after a
change meant to show. The tests run with the app's clock (`AppClock`, which every "now" in the app and
`:core` reads) stopped at one Thursday morning in Zurich's time, on a phone set to a 24-hour clock
(`TestPeekApplication`), so the screenshots are the same whatever the day, the hour or the computer's zone.
Tests only Google Play's build has are in `app/src/testPlay`; F-Droid's are in `app/src/testFdroid`, and
`./gradlew :app:testFdroidDebugUnitTest` runs them with the shared ones.

The tests in `app/src/androidTest`, and Google Play's own in `app/src/androidTestPlay`, run on a device or emulator
(`./gradlew :app:connectedPlayDebugAndroidTest`, one class with
`-Pandroid.testInstrumentationRunnerArguments.class=<class>`). `AccessibilityChecksDeviceTest` runs the
Accessibility Test Framework's checks, Accessibility Scanner's, on the main screens through Compose's accessibility
checks: an error fails it, and the text contrast it warns of goes to the log under `PeekAccessibility`. Robolectric
skips these checks.

## CI

`.github/workflows/android.yml` runs for the changes [docs/DEPLOYMENT.md](../docs/DEPLOYMENT.md) lists. On Linux it
checks that the generated Kotlin contract is current, runs `:core:test` in UTC and in Europe/Zurich, then lints and
builds the app and `:coredevice`. The app's tests and `:app:verifyRoborazziDebug` run on a Mac with Apple silicon, where the
screenshots are recorded: Robolectric draws text edges and blurs a little differently on Linux x86-64. A failed run
keeps the reports, and each differing screenshot's `_actual` and `_compare` images, as artifacts.

## Pushes

In Google Play's build, pushes come through Firebase Cloud Messaging, without the google-services plugin or a
`google-services.json`. The build reads the Firebase project's Android app from four xcconfig settings that only
Android reads and `ios/Configuration/Shared.xcconfig` leaves empty: `SDT_FIREBASE_PROJECT_ID` (`project_id` in `google-services.json`),
`SDT_FIREBASE_APP_ID` (`mobilesdk_app_id`), `SDT_FIREBASE_API_KEY` (`current_key`) and `SDT_FIREBASE_SENDER_ID`
(`project_number`). Debug builds take them from `Local.xcconfig`, a release from the `peek.releaseXcconfig` file. A
build without all four has no pushes: nothing offers them, and a parcel's alerts are its email. Neither has a phone
without Google Play services, which Firebase gets its tokens from. The server sends
through the same Firebase project ([docs/DEPLOYMENT.md](../docs/DEPLOYMENT.md)).

F-Droid's build names the phone by an installation token it makes and reaches it in one of two ways
(`app/src/fdroid/.../platform/UnifiedPush.kt`). With a UnifiedPush distributor app on the phone (ntfy and the like),
the app registers with it, signed with the server's VAPID key from `/api/push/config`, and the server sends Web Push
messages to the endpoint it gives. Without one, the server keeps the notifications in an inbox and a WorkManager job
takes them about every 15 minutes, whenever the phone is online. Both carry the same data as FCM's and are posted the
same way. A distributor installed later is used at the next return to the app, and one that goes away sends the phone
back to the inbox. Delivery updates in Settings says which way notifications come.

As on the iPhone, signing out, an expired session or a deleted account takes the phone's token off the server but
keeps the person's own on or off: the same account signing in again gets its notifications back by itself, and another
account sees the prompt first. A parcel followed without an account takes its notifications with it when it is
forgotten, or followed through another link, even while the launch's round signs it up; someone else's that the
service can't be told about yet is told again on each refresh, and, once signed in, on each load of the account's
list, as are this phone's own links still to be forgotten.

## Shortcuts on the app's icon

Long-pressing the icon offers the iPhone's two App Shortcuts (`ShortcutIntents.swift`) as launcher shortcuts:

- **Add a parcel** (`res/xml/shortcuts.xml`), there from install on, opens Add with its field empty over the app as
  it was, or leaves Add already open as it is. Android starts a manifest shortcut in a new task, so it goes through
  `AddShortcutActivity`, which hands Add's link to the app's window in its own task.
- **Next delivery** (`LauncherShortcuts.kt`) follows what the app publishes for the widget. It is named after the
  first parcel on its way in the widget's order and opens its page. It goes when nothing is on the way, the widget's
  switch is off or nobody is signed in; a copy pinned to the home screen stays, greyed, as "Next delivery" in the
  app's language, renamed when it changes.

## Home screen widgets

Both read only what the app publishes for them (`DeliveryWidgetSharedStore`): every parcel on its way, then those
delivered within a day, in the widgets' order. They are drawn again whenever it publishes, and at least every 20
minutes while either is placed. Their days follow the clock while the app stays closed: the app also words each
parcel's day, with its section in the app's list and whether it is Next up, for the next seven midnights where one
changes (`DeliveryWidgetDays`). The widgets read them for the moment they draw, and are drawn again at those midnights
and at the one after each step they show, whose time then names its weekday. "Tomorrow" reads "today" on the day, under
Arriving today, last night's delivery "yesterday", and a passed estimate gives way to the status. When the time zone
changes while the app runs, it words the days again for the new zone's midnights and draws the widgets again.

- **Next delivery** (`NextDeliveryWidget.kt`) shows the first of them as a card, or two side by side. Like the
  iPhone's, which has no large size, it grows no taller than two rows of cells (`maxResizeHeight`).
- **Parcels** (`ParcelsWidget.kt`) shows them as the iPhone's list widget does. Its size stands for one of the
  iPhone's three (`listSize`): from 280 dp tall the large one, else from 250 dp wide the medium one, else the small
  one. Its design follows how many parcels it shows (`DeliveryWidgetList.design`), with `k` = 1, 2 or 3 for the
  small, medium or large size:
  - up to `k`, cards, the app list's Next up first: Next delivery's card or two side by side; in a large widget one
    wide card over the parcel's last four steps, worded as its page words them, a step dated by its day alone
    naming that day and no time, two wide cards, or three of the app list's cards;
  - up to `2k`, passes in each carrier's colours, in two columns in a medium widget, only where every one fits
    (`passesFit`): where two can't stand one above the other, in a shorter widget or at larger text, the parcels are
    rows, in a medium widget down one column without headings before one is left out (`DeliveryWidgetList.fits`);
  - more, rows under the app list's sections, each marked by its carrier's truck, then a line counting the rest at
    the foot of the last column, after the last row shown (`SectionsLayout`). As many rows as fit at every size, so
    that no room for another parcel is left blank: a small widget leaves the headings out, the name over the stage and
    day; a medium one has two columns about as tall as each other (`DeliveryWidgetList.columns`); a large one has a
    line each where the widget is 250 dp wide. A small or medium widget takes smaller type before it gives a row up (`DeliveryWidgetList.layouts`),
    and larger text keeps fewer rows. The type is the iPhone's, in dp. Where a line doesn't fit, the day stays; where
    neither fits, the stage does, at a smaller size where it must (down to 0.8 of it). A stage too long even then
    takes the widget's shorter words (`widget.stage.*`, which the app publishes as the stage's `short`), in rows,
    passes and cards alike; the list and the parcel's page keep their own.

  The app draws it itself (`ParcelsWidget.draw`) rather than through Glance's sessions, at each size the launcher
  gives taken to its whole dp and 2 dp under (`ParcelsWidget.fitting`): a launcher can frame a widget a little smaller
  than the size it gives, and the launcher shows the closest layout that fits the frame, which would otherwise be
  another screen's.

  Cards and passes use the pictures the app draws for Next delivery's first 8 parcels; until those are drawn, the
  widget shows rows. Each card, pass or row opens its parcel; the rest of the widget opens the list
  (`peekdeliverytracker://parcels`).

A parcel whose card in the app's list shows the new-update dot shows it in the Parcels widget too, before its stage,
and loses it when the card does: once it is opened on any of the account's devices, or when "Mark new updates" is off
(`DeliveryWidgetParcel.newUpdate`). The line keeps to the room the dot leaves, so every size keeps its rows. TalkBack
reads "New update." first. Next delivery, which shows one parcel, leaves the dot out, as the iPhone's does.

Their gallery pictures show invented parcels in the phone's language (`DeliveryWidgetSample`,
`DeliveryWidgetListSample`), never the reader's.

## Release bundle

```sh
./gradlew :app:bundlePlayRelease -Ppeek.releaseXcconfig=<path> -Ppeek.uploadProperties=<path>
```

makes the bundle for Google Play, shrunk by R8 with its mapping inside, at
`app/build/outputs/bundle/playRelease/app-play-release.aab`. Both files stay outside the repository; their paths are
absolute or from `android/`:

- `peek.releaseXcconfig` names an xcconfig with the release's server settings, the ones `ios/Configuration/Local.xcconfig`
  holds. They replace the release build type's alone: debug builds and tests keep the iPhone build's. The bundle and
  the APK refuse to build without `SDT_USE_API = YES` and an `SDT_SUPABASE_URL`, as `scripts/upload-ios-app.sh` does.
- `peek.uploadProperties` names a properties file with `storeFile` (absolute, or from the file's folder),
  `storePassword`, `keyAlias` and `keyPassword`. Without it the release is unsigned, as on CI.

The app uses Play App Signing: Google holds the key that signs the app phones install, and ours is only the upload
key, which Google can replace if it is lost.

Before a bundle goes to Google Play, open the APK built with it on an emulator or phone (`assemblePlayRelease`
copies it to `outputs/apk/release` too):

```sh
scripts/release-smoke.sh app/build/outputs/apk/release/app-release.apk [serial]
```

It waits for the device to finish starting, installs the release in place of any build there, opens it once Android
finds its window and fails unless it is still up 15 seconds later: what R8 removes shows only when the shrunk app
runs. CI's "Release build opens" job does the same on each change, with a key and settings made up for the run.

Once Google Play has a beta, its APK as Google Play signs it goes to the F-Droid repository and GitHub Releases:

```sh
PEEK_FDROID_DIR=<path> scripts/publish-fdroid.sh <play-signed.apk> <commit> [notes.md]
```

The APK is Play Console's signed universal APK (App bundle explorer, the version, Downloads). The script refuses an APK
Google Play did not sign, signs the repository's index with the repository's key, which stays outside the repository
in `PEEK_FDROID_DIR` with fdroid's `config.yml`, puts the repository alone on the `fdroid` branch, which
raw.githubusercontent.com serves, and attaches the APK to a pre-release. The app's entry in the repository is
[`fdroid/com.plhery.peekdeliverytracker.yml`](fdroid/com.plhery.peekdeliverytracker.yml).

F-Droid's own catalog builds from source and signs with its own key, from the `fdroid` flavor:
`./gradlew :app:assembleFdroidRelease` with the same release settings gives its APK in
`app/build/outputs/apk/fdroid/release/`.

## Macrobenchmarks and the baseline profile

`:baselineprofile` holds the app's macrobenchmarks, run on a device or emulator against the app's `benchmark` build
type: the release as R8 shrinks it, signed with the debug key and profileable. `StartupBenchmark` times a cold start on
the list, `ScrollBenchmark` the frames of a list of 60 parcels as it is flung to its end and back, and `PageBenchmark`
the frames of a parcel's page as it opens from the list, each with JIT alone and with the APK's baseline profile
installed as Google Play installs it; the instrumentation argument `peek.compilation` (`none` or `profile`) keeps one, to
time two builds in turn. On a fresh install they open the demo from the welcome; `ScrollBenchmark` adds synthetic
numbers to the demo's parcels through Add, which a build without a server keeps on the phone, so it needs a fresh
install for each run. `BaselineProfileGenerator` collects a profile from the unshrunk `nonMinifiedBenchmark` build.

Besides its libraries' profiles, the app ships its own, `app/src/main/baselineProfiles/baseline-prof.txt`, as
`BaselineProfileGenerator` collects it: a start on the welcome and into the demo, a start on the list, the list
scrolled and a parcel's page opened. R8 rewrites its rules for the shrunk release. Collect it again when those screens
change, and keep it while the benchmarks show it helps.

```sh
./gradlew :app:assemblePlayBenchmark :app:assemblePlayNonMinifiedBenchmark :baselineprofile:assembleBenchmark
adb install -r app/build/outputs/apk/play/benchmark/app-play-benchmark.apk
adb install -r baselineprofile/build/outputs/apk/benchmark/baselineprofile-benchmark.apk
adb shell am instrument -w -r -e class com.plhery.peekdeliverytracker.baselineprofile.StartupBenchmark \
  -e androidx.benchmark.suppressErrors EMULATOR \
  com.plhery.peekdeliverytracker.baselineprofile/androidx.test.runner.AndroidJUnitRunner
```

`suppressErrors` is for an emulator alone, whose timings vary with the computer's load. For the profile, install the
`nonMinifiedBenchmark` APK and run `BaselineProfileGenerator`. The results, traces and profile land in
`/sdcard/Android/media/com.plhery.peekdeliverytracker.baselineprofile/`.

## Core tests on a device

`./gradlew :core:test` runs the core's JUnit tests on the JVM, with ICU4J standing in for the phone's ICU.
`:coredevice` builds the same tests (`core/src/test`) into an instrumented test APK, so they also run against a
phone's own ICU, regular expressions, number parsing, normalizer and zone data. With an emulator or device connected:

```sh
./gradlew :coredevice:connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.timeZone=Europe/Zurich
```

`timeZone` takes any zone ID, such as `UTC`; without it the tests run in the device's zone. To run one class, add
`-Pandroid.testInstrumentationRunnerArguments.class=com.plhery.peekdeliverytracker.core.CoreParityTests`.

The JUnit XML lands in `coredevice/build/outputs/androidTest-results/connected/debug/` and the HTML report in
`coredevice/build/reports/androidTests/connected/debug/`; each run replaces the last.

The tests run from Android 12 (API 31) up: `./gradlew :coredevice:lintDebug` fails on a test that calls a newer API.
