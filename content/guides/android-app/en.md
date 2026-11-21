---
title: Peek for Android: get the beta, add the Parcels widget
description: Peek for Android is in beta: join the Google Play test or install it from F-Droid or GitHub. No account needed. Add the widget, share links into Peek.
slug: parcel-tracker-app-android
picture: An Android phone with the Parcels widget on its home screen, and Pip, the parcel with a face, peeking over the top of the phone.
published: 2026-11-19
updated: 2026-11-19
---

Peek for Android is a beta, not public on Google Play: you get it from [peektracker.com/android](https://peektracker.com/android), by joining the Google Play test or by installing it from Peek's own F-Droid repository or from GitHub. It needs Android 12 or later and no account, and it adds a "Parcels" widget to your home screen and "Add to Peek" to your share sheet.

It follows parcels from more than 4,500 carriers: paste a tracking number, a carrier's link or a whole shipping email, and [Peek](/) finds the carrier and shows the journey on a map. It's free, with no in-app purchases and no ads. For an iPhone, iPad or Mac, see [Peek for iPhone, iPad and Mac](guide:iphone-app).

## Is Peek on Google Play?

Only for testers. Peek is in a closed test, and its Google Play page doesn't open for anyone outside it. Google opens it to everyone once at least 12 testers have kept it for 14 days in a row, and anyone can join the test. On your Android phone, open [peektracker.com/android](https://peektracker.com/android), "Try Peek for Android":

:::steps
- "Join the testers’ group" | Tap "Join the group", join the Google Group on the page that opens, then come back.
- "Become a tester on Google Play" | Tap "Open Google Play", then **Become a tester** on the page that opens.
- "Install it, and keep it two weeks" | Tap "Install Peek", and keep the app for 14 days.
:::

Until you've followed the group's link in that browser, the next two buttons read "Join the group first". You can leave the test any time, on the same Google Play page.

### How do I install Peek without Google Play?

Add Peek's own repository to the F-Droid app by hand, or download the app from GitHub. Peek isn't in F-Droid's main catalog.

:::steps
- Copy the address | On [peektracker.com/android](https://peektracker.com/android), under "Without Google Play", tap "Copy the address".
- Add the repository | In the F-Droid app, go to **Settings** › **Repositories** › **+**, then paste the whole address.
- Install Peek | Tap the new repository to see its apps, then install "Peek". F-Droid installs nothing until you choose it.
:::

Or tap "Open GitHub" on the same page: in the newest release on [Peek's GitHub releases](https://github.com/plhery/peek-delivery-tracker/releases), tap the .apk file, then let your browser install it. Peek's releases there are marked "Pre-release".

This build is signed by Peek, not Google, so it can't update over a Google Play copy, or the other way round: if you have Peek from Google Play, or an early copy from the repository or GitHub, uninstall it first. Updates come through Peek's F-Droid repository. Neither route makes you a Google Play tester.

### What's the difference between the Google Play and F-Droid builds?

| What | Google Play build | F-Droid and GitHub build |
| --- | --- | --- |
| Google's services | Firebase, ML Kit, Play Integrity | None |
| Barcode scanner | Yes | No, and no camera permission |
| Notifications | Through Google Play services | Through a UnifiedPush app such as ntfy, or Peek checks about every 15 minutes |
| Signed and updated by | Google, through Google Play | Peek, through its F-Droid repository |

Notifications in each build are explained in [parcel delivery notifications](guide:parcel-notifications).

## Which Android version does Peek need?

Android 12 or later, in both builds. A device without a camera on its back can still install it, just without the scanner.

It speaks ten languages: English, German, French, Italian, Spanish, Portuguese, Brazilian Portuguese, Polish, Russian and Simplified Chinese. Choose one in "Settings" › "Preferences" › "Language" (Settings opens from the Account button at the top of Deliveries), or in Android's own per-app language setting. Choosing it in Peek's Settings leaves "Add to Peek", the widget's name in the picker and the "Add a parcel" shortcut in your phone's language; the widget's own words follow Peek's.

## Can I use Peek on Android without an account?

Yes. Paste a tracking number on the first screen and Peek tracks it, listed on that phone only; the widget and the next-parcel shortcut work too. Deliveries shows "All your parcels in one place", with "On this device only, for now." and a "Sign in" button.

A parcel is forgotten 30 days after delivery; "Settings" › "On this device" › "Forget all" forgets them all at once. The phone's own backups carry the parcels it follows and Peek's preferences.

An account adds the same list on every device and on peektracker.com, the passport and friends, the notification settings and "Add parcels by email" ([adding parcels by email](guide:add-parcels-by-email)). Sign in with "Continue with Google" or "Sign in with email"; there's no Sign in with Apple on Android. "Explore the demo", on the Sign in screen, needs no account either. [Using Peek without an account](guide:account-or-guest) compares the two.

## How do I add the Peek widget to my home screen?

Add "Parcels", Peek's one widget, from the widget picker, where it reads "See the parcels on their way at a glance, the next one first." Google's steps:

:::steps
- Open the widgets | On a home screen, touch and hold an empty space, then tap **Widgets**.
- Find Peek | Tap Peek to see its widget, "Parcels".
- Place it | Touch and hold the widget, slide it where you want it, and lift your finger.
:::

Google warns that "Some of these steps only work on Android 14 and up."

"Parcels" starts at four cells by two and shrinks to two by two. It lists the parcels on their way, then those delivered within a day, in Peek's order. Each parcel opens its page; the rest of the widget opens your list. It's redrawn at least every 20 minutes, and its days change at midnight even with the app closed.

| The widget shows | What it means |
| --- | --- |
| "Nothing on the way right now" | No parcel is on its way: "Add a tracking number or shipping link to get started." |

"Parcels" lives on the home screen only: there's no lock screen widget, and nothing like Live Activities.

### Does Peek have shortcuts on its app icon?

Yes. Touch and hold Peek's icon for "Add a parcel", which opens Add, and a shortcut named after your next parcel on its way, which opens it. To pin one to your home screen, touch and hold it and slide it into place. Peek has no voice phrases on Android: these shortcuts stand in for the iPhone's Siri actions.

## How do I share a tracking link to Peek on Android?

Share it and choose "Add to Peek", which takes any shared text or link.

:::steps
- Share | Share the tracking number, link or page from any app, then choose "Add to Peek".
- Save | On the card that opens, tap "Save tracking details". It says "Saved. Open Peek within 10 minutes to review and add your parcel."
- Open Peek | Within those 10 minutes, Add opens with the details filled in.
:::

Peek's parcel links (peektracker.com/p/…) open in the app once it's installed. To send a parcel to someone else, see [sharing a parcel's tracking](guide:share-parcel).

### Can Peek scan a barcode on Android?

Only the Google Play build can. Tap the barcode icon at the end of the tracking field (read out as "Scan barcode"), on the first screen or in Add; with an empty list, there's also a "Scan barcode" button. "Scan a tracking barcode" opens with the back camera and follows the barcode nearest the middle. If you refuse the camera, the scanner goes away until you allow it in Android's settings. Not sure which number is the tracking number? See [how to find your tracking number](guide:find-tracking-number).

:::sources
- [Peek: Try Peek for Android](https://peektracker.com/android) – the test's three steps, Android 12, the F-Droid repository, GitHub, signing
- [Peek: Google Play notes](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/google-play/README.md) – closed test, 12 testers for 14 days, the testers' group
- [Peek: Android app](https://github.com/plhery/peek-delivery-tracker/blob/main/android/README.md) – the two builds, widget contents and refresh, shortcuts, signing keys, pre-releases
- [Peek: F-Droid entry](https://github.com/plhery/peek-delivery-tracker/blob/main/android/fdroid/com.plhery.peekdeliverytracker.yml) – beta, name, which earlier copies to uninstall
- [F-Droid: How to Add a Repo to F-Droid](https://f-droid.org/en/tutorials/add-repo/) – Settings › Repositories › +, then choosing the apps to install
- [Peek: GitHub releases](https://github.com/plhery/peek-delivery-tracker/releases) – the .apk files, marked Pre-release
- [Peek: English copy](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/en.json) – every Peek label quoted here
- [Peek: Android manifest](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/AndroidManifest.xml) – Add to Peek, parcel links, camera, backups
- [Peek: the Parcels widget](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/res/xml/parcels_widget.xml) – home screen only, its sizes
- [Peek: Settings](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – Forget all, Language, no Live Activities
- [Peek: the Play build's scanner](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/play/kotlin/com/plhery/peekdeliverytracker/platform/CameraLabelScanner.kt) – barcodes, camera permission
- [Peek: Support](https://peektracker.com/support.html) – no account needed, Forget all
- [Android Help: Add apps, shortcuts and widgets to your Home screens](https://support.google.com/android/answer/9450271?hl=en) – adding a widget, pinning a shortcut, Android 14
:::
