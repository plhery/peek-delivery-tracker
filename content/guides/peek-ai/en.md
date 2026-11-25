---
title: When will my parcel arrive? Peek AI's delivery estimate
description: Peek AI is Peek's own estimate of when your parcel will come, beside the carrier's date. When it shows, how sure it is, and how to turn it off.
slug: when-will-my-parcel-arrive
picture: Pip, the parcel with a face, peering at a phone where a parcel's card shows a small sparkle and "Peek AI by 16:00" under the carrier's date.
published: 2026-11-24
updated: 2026-11-25
---

When [Peek](/) has a strong hint about when your parcel will arrive, Peek AI says so beside the carrier's date, or in its place when the carrier gives none: "likely by 16:00", "likely today or tomorrow", "maybe sooner" or "today is unlikely". It is Peek's own estimate, never a promise from the carrier, and most parcels show none.

It's on by default, with or without an account, on peektracker.com, in the iPhone, iPad and Mac app, and in the [Android app](guide:android-app), from Google Play, Peek's own F-Droid repository or GitHub.

:::steps
- Look at your list | Under the carrier's date on a parcel's card, or beside the status on "Next up": "✦ Peek AI" and a few words, such as "by 16:00".
- Open the parcel | A short card reads, say, "✦ Peek AI likely by 16:00", with one line saying why.
- Tap (i) | The (i) button ("About Peek AI") opens the "Peek AI" sheet: the estimate, with the carrier's own word under it, such as "DPD says today".
- Read the details | Often the likely days or hours, shaded by their chance; then "Why", "How sure" and the "Show Peek AI" switch.
:::

The "Parcels" widget on [iPhone](guide:iphone-app) and Android shows it where it fits, and [connected assistants](guide:ai-assistants) get it as Peek AI's, not the carrier's. [Notifications](guide:parcel-notifications), delivery emails, friends' passports and a shared link's preview leave it out. [Peek's demo](https://peektracker.com/demo) has sample parcels with an estimate.

## When will my package arrive? What Peek AI can tell you

Peek AI speaks up only when one of five hints applies, each right at least 8 times in 10 on the parcels Peek has followed.

| The hint | Peek AI says | Its line of why, for example |
| --- | --- | --- |
| Out for delivery, no hour from the carrier | "likely by 16:00" | "Out for delivery since 09:12. DPD names no hour." |
| The carrier says today; not out for delivery by the afternoon | "today is unlikely" | "DPD says today, but it hasn’t gone out for delivery." |
| The carrier's date was yesterday; scanned in the last 24 hours | "likely today or tomorrow" | "DPD’s date was yesterday, but it’s on the move again." |
| The carrier's date is days away; parcels like it usually beat it | "maybe sooner" | "DPD says Fri 27 Nov. It’s ahead of that." |
| No date from the carrier | "likely by Thu 26 Nov", "likely this week" and others | "UPS gives no date. It reached customs yesterday." |

DPD and UPS stand in for your carrier. On a card, "today is unlikely" shortens to "not today", or to "unlikely" beside a carrier's date of today. Times follow your 12- or 24-hour setting.

Each estimate goes when it ends, even from a page left open: "today is unlikely" at midnight, "likely by 16:00" at 16:00, "maybe sooner" on the carrier's date, and a day once it's over.

### Why does my parcel show no estimate?

Usually because Peek has no strong hint. Peek AI also stays quiet:

- once the parcel is delivered, archived, at a pickup point or being returned, or after a delivery attempt (see [missed delivery](guide:missed-delivery));
- before its first real scan: a label alone isn't enough;
- when the carrier lists the delivery among its planned steps, or gives an hour or slot for today that hasn't passed;
- before the parcel is out for delivery, if Peek hasn't checked it lately (in 36 hours, except for "today is unlikely");
- once the carrier's date is more than a day past (see [when to worry](guide:tracking-not-updating));
- for "maybe sooner" and estimates without a carrier date, 45 days after the first scan, or 30 days after the newest scan.

## Is the carrier's delivery date accurate? "Maybe sooner" and "today is unlikely"

Not always, and Peek AI says so in two cases: "maybe sooner" when parcels with scans like yours usually beat the carrier's date, and "today is unlikely" when the carrier says today but the parcel isn't out for delivery by the afternoon.

"Maybe sooner" can name the first likely day ("maybe sooner, from tomorrow", "maybe sooner, from Thu 26 Nov"), and never comes when the carrier gives a range of days.

## Package says out for delivery but not delivered yet: will it still come today?

Peek AI may tell you. "Likely by 16:00" means most of that carrier's parcels Peek has followed arrived by that hour on their delivery day; it shows only when the carrier names no hour and the parcel went out before it. Late in the day, "today is unlikely" means few arrive that late: "Still out for delivery at this hour. With DPD, few arrive this late." For some carriers it says nothing while the parcel is out, because Peek doesn't trust their "out for delivery" scans.

Each carrier's usual hours, and why a van can still be out at 8 p.m., are in [what time parcels get delivered](guide:delivery-times); what the status means is in [tracking statuses](guide:tracking-statuses).

## The carrier gives no delivery date: how long will it take?

Peek AI may give a day or a span, whichever its model is sure enough of: "likely by Thu 26 Nov", "likely today or tomorrow" (only when the carrier delivers tomorrow), "likely this week" (by Sunday) or "likely by the end of next week" (by the Sunday after).

For the usual times from China, see [tracking a parcel from China](guide:tracking-from-china).

## How does Peek AI predict delivery times, and how accurate is it?

Peek AI is a trained model plus rule tables on Peek's own server, and it shows only kinds of estimate that were right at least 8 times in 10.

The model, a gradient-boosted classifier made of decision trees, gives the chance a parcel will have arrived by each of the carrier's coming delivery days. It reads the scans and where they happened, the carrier, service, weight, time zone and declared destination country, and whether the parcel was looked up without an account. The rule tables hold each carrier's usual delivery hour and how often each kind of estimate came true.

The server works the estimate out each time it sends the parcel's details to your phone or browser, calling no outside service; they only show it. Only Peek's server holds the model: neither it nor its training code is in [Peek's open-source code](guide:developers). It isn't a chatbot.

"How sure" says "Right 8 times in 10 on parcels like this one.", or "at this hour" for "today is unlikely". The number is 8 or 9: Peek AI shows nothing below 8 in 10, never claims more than 9, and rounds down.

## How do I turn Peek AI off?

Switch off "Peek AI" under "Preferences" in Peek's "Settings", or tap (i) on a parcel's Peek AI card and switch off "Show Peek AI".

:::steps
- Open Settings | On the website, the account button (your initial), or without an account the gear button "Settings". In the apps, the Account button at the top of Deliveries; on a Mac, Peek's Settings window, "General" tab.
- Find Peek AI | "Peek AI" is the last row under "Preferences", with the hint "When Peek has a strong hint, it says when it thinks a parcel will come."
- Switch it off | Peek AI goes from cards, "Next up", parcel pages and widgets on that device.
:::

With an account, the choice follows you to your other devices, and connected assistants stop getting the estimate. Without one, it stays in that browser or on that device; [Peek without an account](guide:account-or-guest) compares the two.

:::sources
- [Peek: Peek AI on the server](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAi.ts) – the five hints, 8 times in 10, 36 hours, when it says nothing
- [Peek: Peek AI's words](https://github.com/plhery/peek-delivery-tracker/blob/main/src/lib/peekAi.ts) – the words on cards and pages, when each estimate ends, How sure
- [Peek: the Peek AI card and sheet](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/PeekAi.tsx) – the card, the (i) sheet, Show Peek AI
- [Peek: the model](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/peekAiModel.ts) – decision trees, what it reads, 45 and 30 days
- [Peek: Architecture](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – how Peek AI works, where it isn't shown, the account's choice
- [Peek: English copy](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/en.json) – every Peek AI label quoted here
- [Peek: website Preferences](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Preferences.tsx) – the Peek AI switch, following the account
- [Peek: iPhone, iPad and Mac Settings](https://github.com/plhery/peek-delivery-tracker/blob/main/ios/PeekDeliveryTracker/SettingsViews.swift) – Peek AI under Preferences, General on a Mac
- [Peek: Android Settings](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/settings/SettingsSheet.kt) – Peek AI under Preferences
- [Peek: assistants' tools](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – the estimate in an assistant's parcel list
- [App Store: Peek](https://apps.apple.com/app/id6823615370) – Peek AI in the app's notes
- [Peek: Android notes](https://github.com/plhery/peek-delivery-tracker/blob/main/fastlane/metadata/android/en-US/changelogs/21.txt) – Peek AI in the Android app
- [Peek: Peek for Android](https://peektracker.com/android) – the app on Google Play, F-Droid and GitHub
- [Peek: demo](https://peektracker.com/demo) – sample parcels with an estimate
:::
