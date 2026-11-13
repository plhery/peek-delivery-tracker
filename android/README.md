# Android app

The app reads the data the iPhone app bundles (brand, carrier catalog, copy, map) from its assets: `:app` packages
the files `:core` reads, synced from `ios/PeekDeliveryTracker/Resources` along with its own country names, under
`peek/`, and `SharedResources` reads them there. The contract fixtures, read only by debug previews, ship in debug
builds alone.

## App tests and screenshots

`./gradlew :app:testDebugUnitTest` runs the app's tests on the JVM with Robolectric. The design system's screenshots
are kept in `app/src/test/snapshots`: `./gradlew :app:verifyRoborazziDebug` fails when more than a thousandth of a
shot's pixels differ (see `Screenshots.kt`), and `./gradlew :app:recordRoborazziDebug` records them again after a
change meant to show. The tests run with the app's clock (`AppClock`, which every "now" in the app and
`:core` reads) stopped at one Thursday morning in Zurich's time (`TestPeekApplication`), so the screenshots are the same
whatever the day, the hour or the computer's zone.

## CI

`.github/workflows/android.yml` runs for the changes [docs/DEPLOYMENT.md](../docs/DEPLOYMENT.md) lists. On Linux it
checks that the generated Kotlin contract is current, runs `:core:test` in UTC and in Europe/Zurich, then lints and
builds the app and `:coredevice`. The app's tests and `:app:verifyRoborazziDebug` run on a Mac with Apple silicon, where the
screenshots are recorded: Robolectric draws text edges and blurs a little differently on Linux x86-64. A failed run
keeps the reports, and each differing screenshot's `_actual` and `_compare` images, as artifacts.

## Pushes

Pushes come through Firebase Cloud Messaging, without the google-services plugin or a `google-services.json`. The
build reads the Firebase project's Android app from four xcconfig settings that only Android reads and
`ios/Configuration/Shared.xcconfig` leaves empty: `SDT_FIREBASE_PROJECT_ID` (`project_id` in `google-services.json`),
`SDT_FIREBASE_APP_ID` (`mobilesdk_app_id`), `SDT_FIREBASE_API_KEY` (`current_key`) and `SDT_FIREBASE_SENDER_ID`
(`project_number`). Debug builds take them from `Local.xcconfig`, a release from the `peek.releaseXcconfig` file. A
build without all four has no pushes: nothing offers them, and a parcel's alerts are its email. Neither has a phone
without Google Play services, which Firebase gets its tokens from. The server sends
through the same Firebase project ([docs/DEPLOYMENT.md](../docs/DEPLOYMENT.md)).

As on the iPhone, signing out, an expired session or a deleted account takes the phone's token off the server but
keeps the person's own on or off: the same account signing in again gets its notifications back by itself, and another
account sees the prompt first.

## Shortcuts on the app's icon

Long-pressing the icon offers the iPhone's two App Shortcuts (`ShortcutIntents.swift`) as launcher shortcuts:

- **Add a parcel** (`res/xml/shortcuts.xml`), there from install on, opens Add with its field empty.
- **Next delivery** (`LauncherShortcuts.kt`) follows what the app publishes for the widget. It is named after the
  first parcel on its way in the widget's order and opens its page. It goes when nothing is on the way, the widget's
  switch is off or nobody is signed in; a copy pinned to the home screen stays, greyed, as "Next delivery".

## Home screen widgets

Both read only what the app publishes for them (`DeliveryWidgetSharedStore`): the parcels on their way in the widgets'
order, 15 at most. They are drawn again whenever it publishes, and at least every 20 minutes while either is placed.

- **Next delivery** (`NextDeliveryWidget.kt`) shows the first of them as a card, or two side by side. Like the
  iPhone's, which has no large size, it grows no taller than two rows of cells (`maxResizeHeight`).
- **Parcels** (`ParcelsWidget.kt`) lists them, as many rows as its height holds, then a line counting the rest. Under
  250 dp wide a row takes two lines, the name over the stage and day; wider, one, the stage and day at its end. Where
  both don't fit, the day stays. A phone's two cells by two show 3 rows, four by two 6, four by four all 15
  (`ListLayout`). Each row opens its parcel; the rest of the widget opens the list (`peekdeliverytracker://parcels`).

Their gallery pictures show invented parcels in the phone's language (`DeliveryWidgetSample`,
`DeliveryWidgetListSample`), never the reader's.

## Release bundle

```sh
./gradlew :app:bundleRelease -Ppeek.releaseXcconfig=<path> -Ppeek.uploadProperties=<path>
```

makes the bundle for Google Play, shrunk by R8 with its mapping inside, at
`app/build/outputs/bundle/release/app-release.aab`. Both files stay outside the repository; their paths are absolute
or from `android/`:

- `peek.releaseXcconfig` names an xcconfig with the release's server settings, the ones `ios/Configuration/Local.xcconfig`
  holds. They replace the release build type's alone: debug builds and tests keep the iPhone build's. The bundle and
  the APK refuse to build without `SDT_USE_API = YES` and an `SDT_SUPABASE_URL`, as `scripts/upload-ios-app.sh` does.
- `peek.uploadProperties` names a properties file with `storeFile` (absolute, or from the file's folder),
  `storePassword`, `keyAlias` and `keyPassword`. Without it the release is unsigned, as on CI.

The app uses Play App Signing: Google holds the key that signs the app phones install, and ours is only the upload
key, which Google can replace if it is lost.

Before a bundle goes to Google Play, open the APK built with it on an emulator or phone:

```sh
scripts/release-smoke.sh app/build/outputs/apk/release/app-release.apk [serial]
```

It installs the release in place of any build there, opens it and fails unless it is still up 15 seconds later: what R8
removes shows only when the shrunk app runs. CI's "Release build opens" job does the same on each change, with a key
and settings made up for the run.

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
