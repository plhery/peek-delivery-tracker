---
title: Track a parcel without knowing the carrier
description: Paste a tracking number, a carrier's link or the whole shipping email: Peek finds the number and the carrier, and asks you when several carriers know it.
slug: track-parcel-without-knowing-carrier
picture: Pip, the parcel with a face, reading a long shipping email pasted into a search field, while three delivery vans wait behind a big question mark.
published: 2026-11-19
updated: 2026-11-19
---

You don't need to know the carrier to track a parcel: paste its tracking number, the carrier's tracking link or the whole shipping email or text into the field on [Peek](/), and Peek picks out the number, works out the carrier and opens the parcel's page. On peektracker.com there's no account to make, and Peek follows parcels across more than 4,000 carriers.

:::steps
- Open peektracker.com | The field under "Where’s my parcel?" says "Paste a number, link or message".
- Paste what you have | Choose "Paste" in the field when your browser offers it, or paste as usual. On a phone's first visit, "Paste and track" does both at once.
- Check what Peek read | From a text or link, Peek shows the number it took; the line under the field says what it knows about the carrier.
- Choose "Track" | The parcel opens on its own page, and the home page lists it on this device.
:::

The apps read a pasted text the same way: the + button opens "Add a parcel", with "Paste", and you can share a text or link to "Add to Peek" (see [Peek for iPhone, iPad and Mac](guide:iphone-app) and [Peek for Android](guide:android-app)). Signed in on the website, "Add parcel" at the top of your deliveries opens a sheet that reads a pasted text or link too, but without the list of several numbers, the typo suggestion or the notes on shop order numbers.

## Can I track a parcel without knowing the carrier?

Yes. Peek works the carrier out from the number, or asks the carriers it could belong to.

A number whose shape proves its carrier, such as a UPS number starting `1Z`, names it at once: the carrier line says "Detected carrier". Otherwise Peek asks the likeliest carriers five at a time, bringing forward those that serve your country, while the line says "Checking tracking services…". Only an answer about a recent parcel counts, because carriers reuse old numbers. See [tracking number formats](guide:tracking-number-formats) for the shapes, and [universal parcel tracking](guide:universal-tracker) for how trackers work.

Without an account, the website looks up a few parcels a day for each network, then says "No lookups left for today", with "Sign in" to keep going.

## Can I paste the whole shipping email or a tracking link?

Yes. Paste the whole email or text message, or only the carrier's link, and Peek says which number it found "in the pasted text" or "in the pasted link". On the home page and in the apps:

- A carrier's link counts through its tracking number; a number elsewhere in a link, such as a shop's help page, doesn't.
- A number whose shape proves its carrier is always picked out. Each line is read on its own.
- A plainer number is read when it's the message's only number, the only one on a line about tracking, or one the email calls a tracking number in English, German, French, Italian, Spanish, Portuguese, Polish, Russian or Chinese.
- No part of an order number is taken for the tracking number, and two unlabelled numbers with nothing to tell them apart aren't read.

Otherwise Peek says "Peek couldn’t find a tracking number. Paste the number or a tracking link." [How to find your tracking number](guide:find-tracking-number) shows where to look.

## What if the email has several tracking numbers?

Peek lists them, and you track them one at a time. Numbers whose carriers are certain appear under, say, "3 tracking numbers in this text", each once, with its carrier, in the order written; one whose check digit doesn't add up is left out. The first is selected: pick another if you like, then choose "Track this one", which in the apps takes the place of "Add parcel".

Without an account, the list ends with "Following all of them?" and a "Sign in and Peek keeps them together" button. With an account, you can forward the shipping email instead and Peek adds every parcel in it: see [adding parcels by email](guide:add-parcels-by-email). The signed-in website's sheet shows no list, so paste the number you want there on its own.

## What if more than one carrier knows my number?

You choose the one your shipping email names. On the home page, the carrier line reads, say, "3 carriers know this number" and "choose yours", above the carriers, each tagged "Knows this number", and the hint "Carriers reuse numbers after a while. Choose the one your shipping message names." In the website's signed-in sheet, the line names the carriers that "know it" and ends in "Choose"; if you don't choose, Peek asks again after you add the parcel. AliExpress, which follows some parcels under their delivery carrier's number, is picked without asking.

To pick a carrier yourself, select "Change" at the end of the carrier line. The "Carrier" list starts with "Detect automatically", then sections such as "Know this number", "Fits this number" and "You’ve used", then "All carriers" from A to Z. Search by name, another name such as Colissimo, or country.

## Why does Peek ask for my postcode or phone number?

Because some carriers show a parcel only with more than its number. Peek then adds a field under the carrier line: in the apps, "Delivery postcode", "Last 4 digits of the phone number" or "Complete tracking link"; on the English website, each carrier's own label, most often "Delivery postcode". Paste the carrier's full tracking link in the first place and Peek doesn't need to ask for it.

A required postcode says "The carrier needs the delivery postcode to show your parcel’s updates." An "Optional" one says what it adds, such as DPD's verified scans and delivery window. The postcode goes to the carrier and, when needed, to tracking services; phone digits go only to that carrier. Neither is shown on shared links.

## Why does Peek say it's an order number or a pickup code?

Because what you entered isn't a tracking number, and Peek says where to find the real one. On the home page and in the apps, it holds back five shops' order numbers: it asks no carrier about them and won't add them. A note says, for example, "That looks like a Shein order number":

| Shop | Order number | Where Peek sends you |
| --- | --- | --- |
| [Amazon](guide:amazon-orders) | 3-7-7 digits, or D01-… for digital orders | **Your Orders**, with "Open my Amazon orders" |
| [Shein](guide:shein-orders) | GSH, GSO or GSU and 12 letters or digits | **My orders**, then **Track** |
| [Temu](guide:temu-orders) | PO, 3 digits and 17 digits | **Your orders**, then **Track** |
| Shopee | The order date and 8 letters and digits | The order's shipping information |
| Mercado Libre | 16 digits starting 20000 | **My purchases**, its shipping section |

Six digits on their own get "That looks like a pickup code", since such a code "usually opens a parcel locker or collects a parcel at a pickup point" (see [parcel lockers](guide:parcel-lockers)). A placeholder such as `123456789` gets "This doesn’t look like a real tracking number." An Amazon tracking number, such as one starting TBA, is added only when Amazon Shipping's public tracking confirms it; otherwise Peek offers "Open my Amazon orders".

## What if Peek says the check digit doesn't add up?

The number may hold a typo. A number in the 13-character postal format (two letters, nine digits, two letters) whose check digit doesn't add up gets "This number’s check digit doesn’t add up." It's a question, not a refusal: some carriers use that shape without a check digit.

If a letter stands where a digit belongs, or the other way round, and its lookalike makes the number add up, Peek adds "A letter may have been read as a digit." and offers the fix, the changed character highlighted: for `RR12345678SCH`, "Did you mean RR123456785CH?". A wrong digit has too many possible fixes, so Peek says "Compare it with your shipping message." Either way, "Track it as typed" keeps the number as it is.

:::sources
- [Peek: homepage](https://peektracker.com/) – the field, no account needed, 4,000+ carriers
- [Peek: the home page's field](https://github.com/plhery/peek-delivery-tracker/blob/main/src/peek/FrontDoor.tsx) – "Paste", "Paste and track" on a phone's first visit, the button's wording
- [Peek: what the field says](https://github.com/plhery/peek-delivery-tracker/blob/main/src/peek/lookup/LookupFeedback.tsx) – the carrier line, several numbers, several carriers, the typo, order and pickup notes, the extra fields, the daily limit
- [Peek: reading a pasted text](https://github.com/plhery/peek-delivery-tracker/blob/main/src/peek/lookup/reading.ts) – links, numbers an email labels, check digits and lookalikes
- [Peek: reading rules and examples](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/reading.json) – shop order numbers, several numbers in a text, the typo example
- [Peek: the signed-in Add sheet](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/AddParcelSheet.tsx) – what it shows and lacks, "know it" and "Choose"
- [Peek: the carrier list](https://github.com/plhery/peek-delivery-tracker/blob/main/src/lib/carrierPicker.ts) – its sections and search
- [Peek: routing](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ROUTING.md) – five carriers at a time, recent parcels only, AliExpress, placeholder numbers
- [Peek: carriers that need more](https://github.com/plhery/peek-delivery-tracker/blob/main/src/lib/carriers.ts) – postcode, phone digits, full link, their help lines
- [Peek: Amazon Shipping check](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/amazonShippingEligibility.ts) – Amazon numbers
- [Peek: iPhone Add screen](https://github.com/plhery/peek-delivery-tracker/blob/main/ios/PeekDeliveryTracker/AddParcelView.swift) – "Paste", several numbers, the typo check, order notes, the extra fields' labels
- [Peek: Android Add screen](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/kotlin/com/plhery/peekdeliverytracker/ui/deliveries/AddParcel.kt) – the same on Android
- [Peek: iPhone app README](https://github.com/plhery/peek-delivery-tracker/blob/main/ios/README.md) – the share extension, the same reading as the website
- [Peek: Android manifest](https://github.com/plhery/peek-delivery-tracker/blob/main/android/app/src/main/AndroidManifest.xml) – "Add to Peek" in the share sheet
- [Peek: English copy](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/en.json) – every label quoted here
:::
