# Google Play

What Play Console asks for Peek's Android app, where it differs from the
[App Store](../app-store/README.md).

## Listing

[`listing/`](listing) has the title, short description and full description for each language of
the App Store listing. `en-US.md` is English (United States), the default language, with
“package”; `en-GB.md` has “parcel”. The titles are the App Store names. The descriptions are the
App Store's, for what the Android app does: it signs in with Google or a code sent by email, and its
notifications come through Firebase Cloud Messaging.

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

## Closed test

Play opens an app from a personal developer account to everyone only after a closed test with at
least 12 testers opted in for 14 days in a row. Peek's closed track takes its testers from the
Google Group `peek-android-testers`, which anyone can join: a tester joins it, becomes a tester on
Play's opt-in page with the same Google account, and installs the app from Play.

The site invites Android visitors to it while the switch in
[`src/lib/androidTest.ts`](../../src/lib/androidTest.ts) is on, with the group's and Play's links
beside it. It invites a browser on Android 12 or later (the version Chrome's client hints give;
without them, any Android but a version below 12 the user agent states), not an app's WebView, where
Google allows no sign-in. It stands on the parcel page under its actions, in its "Notify me" and as
a banner on the home page, and leads to the steps at `/android`, which are not found while the
switch is off. Play lets in only the group's members, so the steps page keeps its last two steps
locked until the group's link was followed. × hides it for a week and offers "Never show"; the
steps page has "Don't ask me again". Once the install link was followed after the group's, the
parcel page or the home page thanks the visitor on that page load, and nothing shows after. A browser that opened a page from the app is not invited.
What the browser did stays in its storage; the events are in [ANALYTICS.md](../ANALYTICS.md).
