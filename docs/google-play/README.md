# Google Play

What Play Console asks for Peek's Android app, where it differs from the
[App Store](../app-store/README.md).

## Listing

[`listing/`](listing) has the title, short description and full description for each language of
the App Store listing. `en-US.md` is English (United States), the default language, with
“package”; `en-GB.md` has “parcel”. The titles are the App Store names. The descriptions are the
App Store's, for what the Android app does: it signs in with Google or a code sent by email, and it
sends no notifications, so an account brings an email when a parcel arrives.

Play takes 30 characters for a title, 80 for a short description and 4,000 for a full description,
which it shows as plain text with its line breaks. A short description has no emoji, capitals for
emphasis, rankings, prices or other companies' names. Play has no keywords.

## App details

- Package name `com.plhery.peekdeliverytracker`. It is permanent.
- App, category Tools.
- Free, with no in-app purchases. It contains no ads.
- Website `https://peektracker.com`, privacy policy `https://peektracker.com/privacy.html`.

## Graphics

- Icon: 512 × 512, the web's `public/icons/icon-maskable-512.png`, full bleed; Play rounds the
  corners.
- Feature graphic: 1024 × 500 for each language, from the site's link previews (`public/og.png` in
  English, `public/og-<language>.png` in the others), scaled to 500 high and centred on `#F4F5F1`.
  The en-US one says “package” where `og.png` says “parcel”.

## Screenshots

Take them in the demo, whose parcels are made up, in a debug build with the store-screenshot launch
value `sdt.debug.storeScreenshots` set, which leaves out the demo's bar: phone, 7" tablet and 10"
tablet. On Android that value is the boolean `sdt.debug.storeScreenshots` in the app's
`shared_prefs/user_defaults.xml`, written through `adb shell run-as`; SystemUI's demo mode tidies
the status bar.

Play takes only 9:16 and 16:9 screenshots, so size the emulator's screen to them with `adb shell wm
size` and `wm density`: the phone at 1080 × 1920, the 7" tablet at 1440 × 2560 (density 384) and
the 10" tablet at 2560 × 1440 (density 320). The translations show the default language's
screenshots unless they have their own.
