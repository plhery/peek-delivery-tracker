# Android app

The app reads the data the iPhone app bundles (brand, carrier catalog, copy, map) from its assets: `:app` packages
the files `:core` reads, synced from `ios/PeekDeliveryTracker/Resources` along with its own country names, under
`peek/`, and `SharedResources` reads them there. The contract fixtures, read only by debug previews, ship in debug
builds alone.

## App tests and screenshots

`./gradlew :app:testDebugUnitTest` runs the app's tests on the JVM with Robolectric. The design system's screenshots
are kept in `app/src/test/snapshots`: `./gradlew :app:verifyRoborazziDebug` fails when more than a thousandth of a
shot's pixels differ (see `Screenshots.kt`), and `./gradlew :app:recordRoborazziDebug` records them again after a
change meant to show.

## CI

`.github/workflows/android.yml` runs for the changes [docs/DEPLOYMENT.md](../docs/DEPLOYMENT.md) lists. On Linux it
checks that the generated Kotlin contract is current, runs `:core:test` in UTC and in Europe/Zurich, then lints and
builds the app and `:coredevice`. The app's tests and `:app:verifyRoborazziDebug` run on a Mac with Apple silicon, where the
screenshots are recorded: Robolectric draws text edges and blurs a little differently on Linux x86-64. A failed run
keeps the reports, and each differing screenshot's `_actual` and `_compare` images, as artifacts.

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
