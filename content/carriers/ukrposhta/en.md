---
title: Ukrposhta tracking: Ukraine Post numbers and statuses
description: Track a Ukrposhta parcel in English: the 13-digit and UA numbers, what each status means, branch pickup, and lost or damaged items from abroad.
slug: ukrposhta-tracking
published: 2026-10-13
updated: 2026-10-27
---

Ukrposhta (Укрпошта) is Ukraine's national post. Track a parcel on its tracker, track.ukrposhta.ua, in the Ukrposhta app or by pasting the number in the box above. The number comes from the sender: 13 digits within Ukraine, or two letters, nine digits and two letters to or from abroad.

## Ukrposhta tracking number

Ukrposhta's numbers come in two shapes, and a 13-digit one ends in a check digit.

| Shape (invented) | What it is |
| --- | --- |
| `0500000000003` | A domestic item, within Ukraine: 13 digits. |
| `RR000000005UA` | An international item posted in Ukraine: two letters, nine digits, `UA`. |
| `RR000000005US` | An item coming into Ukraine keeps the sending post's number, ending in that country's code. |

No number? Ask the sender, or see [how to find a tracking number](guide:find-tracking-number).

[Not to be confused](guide:tracking-number-formats): [Nova Poshta](carrier:nova-poshta) waybills within Ukraine have 14 digits, and many [Meest](carrier:meest) numbers end in `G`.

## What Ukrposhta tracking statuses mean

Peek asks Ukrposhta for its English lines. The usual ones, roughly in order:

| Ukrposhta's status | What it means for you |
| --- | --- |
| `Created online. Waiting acceptance` | The sender [made the label online](guide:tracking-statuses). Ukrposhta doesn't have the item yet. |
| `Registered` | Accepted by Ukrposhta. |
| `Departed from the Branch` | Left the branch where it was posted. |
| `Arrived to the Logistics Center` | Being sorted on its way across Ukraine. |
| `Arrival at inward office of exchange` | An item from abroad has reached Ukraine. |
| `Held at inward office of exchange` | Held on arrival for customs. |
| `Item presented to customs` | Handed to customs for checking. |
| `Released from customs for further delivery` | Customs has released it. |
| `Departure from inward office of exchange` | Through customs and on its way to your region. |
| `Item out for physical delivery` | Out for delivery. |
| `Arrived to the Branch` | At your branch, ready to pick up. |
| `Final delivery` | Delivered: handed over to you. |
| `Returned to Sender (Due to Recipient's Refusal)` | Refused by the recipient and going back. |
| `Return: Delivered to Sender` | Back with the sender: returned, not delivered to you. |

Steps on the way back start with `Return:`. Once a line shows the item is going back, Peek shows a later delivery as "Returned to sender", not "Delivered".

## Ukrposhta branch pickup and delivery

The lines tell you whether the item comes to you (`Item out for physical delivery`) or waits at your branch (`Arrived to the Branch`, which Peek shows as "Ready for pickup"). The Ukrposhta app finds branches on a map, with their postcodes, addresses and opening hours. Ask your branch how long it keeps an item and which ID to bring.

## Ukrposhta parcel delayed, lost or damaged

If nothing moves for days, see [tracking that isn't updating](guide:tracking-not-updating); for a hold at customs, see [parcels held at customs](guide:customs).

**Lost or late, to or from abroad.** Under the Universal Postal Union's rules, a national post must accept an inquiry about a parcel or a registered or insured item, from the sender or from you, within six months of the day after it was posted. Inquiries are free under these rules, but Ukraine reserves the right to charge for them. Compensation for a lost or damaged item goes to the sender, or to you if the sender waives it in writing. For goods bought online that never came, contact the seller first.

**Damaged on arrival.** Point out the damage before you take the item. Under the same rules, the posts stay liable for damage to such an item found before or at delivery, and the one delivering it draws up a report for you to countersign, with a copy for you.

## Ukrposhta customer service

For problems with the app, Ukrposhta's Google Play listing gives mobile-app@ukrposhta.ua. For a parcel to or from abroad that's lost or late, make an inquiry as described above: under the UPU's rules, Ukrposhta must accept it from you as well as from the sender.

## Tracking Ukrposhta with Peek

For 13 digits with Ukrposhta's check digit, [Peek](/) asks Ukrposhta, along with Delhivery for a number that also fits Delhivery's shape (first digit 1 to 9, last 0 to 6), and shows Ukrposhta, marked "has this parcel", when only Ukrposhta knows it; if Delhivery knows it too, you choose between them. A 13-digit number with a wrong check digit isn't offered to Ukrposhta: pick Ukrposhta with "Change". A number starting C, E, L or R and ending `UA` is named as Ukrposhta's straight away when its check digit is valid; for a valid one starting with another letter, Peek asks Ukrposhta and [Chronopost](carrier:chronopost), and if neither knows it yet, keeps checking after you add it. A track.ukrposhta.ua link with the number in it always names Ukrposhta.

A number from another country's post isn't offered to Ukrposhta. When the item is heading to Ukraine, Peek can follow it to Ukrposhta, or you can pick "Ukrposhta" with "Change".

It reads the service behind Ukrposhta's app; when that service fails or doesn't know an item to or from abroad, Peek opens Ukrposhta's tracking page in a browser on its server. It shows each step on a map with its place and country, the customs steps and the time it was delivered. Ukrposhta gives its times without a time zone: Peek reads a step in Ukraine on Kyiv time and works out the zone of a step abroad from its place or the steps around it, so that time can be off; it shows each in your device's time zone. Peek shows no expected arrival day and leaves out Ukrposhta's planned date and postcodes; the only pickup point named is the branch on `Arrived to the Branch`. Statuses keep Ukrposhta's English wording, `Final delivery` included.

Peek checks every 10 minutes. Once the item is out for delivery, it checks every 2 minutes, or every 5 when its last check had to open that browser. After two days without a new step (12 hours while it shows "Label created" or "Received by carrier"), it checks hourly. It can notify you when the status changes.

## Questions about Ukrposhta tracking

### How do I track a Ukrposhta parcel in the UK?

When you add a Ukrposhta item in the UK, Peek also asks [Royal Mail](carrier:royal-mail) under the same number, or [Parcelforce](carrier:parcelforce) for a number starting with E (EMS). Once that post has a scan as recent as Ukrposhta's last, Peek follows the parcel there, keeping Ukrposhta's steps.

### Why is my Ukrposhta tracking number not working?

Check it against the one the sender gave you, character by character. A 13-digit number ends in a check digit, and Peek doesn't offer Ukrposhta one whose check digit is wrong. If the number is right, Ukrposhta may not know it yet: a parcel filed under Ukrposhta then shows "Waiting for the carrier" while Peek keeps checking. If nothing changes for several days, ask the sender.

### How do I track a parcel sent to Ukraine from abroad?

Paste the number the sender gave you. Peek follows the parcel at the sending post. When that post says the parcel is going to Ukraine, or you add it in Ukraine, Peek also asks Ukrposhta and, once Ukrposhta has a scan as recent as that post's last, carries on there, keeping the earlier steps. To follow it at Ukrposhta from the start, pick "Ukrposhta" with "Change". A DHL parcel from Germany whose number starts with C is followed at [Meest](carrier:meest) instead.

:::sources
- [Universal Parcel Scraper: Ukrposhta](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ukrposhta) – number shapes and check digits, track.ukrposhta.ua links, the app's service and the tracker Peek reads, the English lines and their statuses, return lines, Kyiv time and steps abroad, what Peek leaves out
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – carriers Peek asks by number, Ukraine's national post, hand-offs to Ukrposhta (to Meest for DHL's C numbers) and from it to Royal Mail or Parcelforce, Nova Poshta and Meest numbers
- [Universal Postal Union: Convention Manual](https://www.upu.int/UPU/media/upu/files/aboutUpu/acts/06-manualsInThreeVolumes/actInThreeVolumesConventionManual202609En.pdf) – inquiries within six months, Ukraine's right to charge for them, compensation to the sender or, by written waiver, to you, damage found at delivery and its report
- [Google Play: Ukrposhta](https://play.google.com/store/apps/details?id=ua.ukrposhta.android.app) – the app tracks parcels and finds branches with their postcodes, addresses and opening hours; email for app problems
:::
