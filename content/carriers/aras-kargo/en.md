---
title: Aras Kargo tracking: numbers and statuses in English
description: Track an Aras Kargo parcel: what its 13-digit number looks like, what Turkish statuses such as TESLİMAT ŞUBESİNDE mean in English, and how Peek follows it.
slug: aras-kargo-tracking
published: 2026-11-02
updated: 2026-11-02
---

Aras Kargo (also written Aras Cargo) is a courier in Turkey. Aras's tracker, as Peek reads it, writes every step in Turkish. The shop or the sender gives you the tracking number: paste it in the box above. Aras Kargo's own tracker is at kargotakip.araskargo.com.tr. The tables below explain its Turkish statuses in English.

## Aras Kargo tracking number: what it looks like

Peek's code knows Aras Kargo's tracking numbers as 13 digits. The ones it links to Aras start with 60 or 65. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `6000000000000` | 13 digits starting with 60: the shape Peek links to Aras Kargo. [Delhivery](carrier:delhivery), Correos Chile, Yunda and Korea Post have numbers of this shape too. |
| `6500000000000` | 13 digits starting with 65: the same. |
| `1000000000000` | 13 digits starting with other digits: Peek doesn't link them to Aras. If the shop says Aras Kargo, pick Aras Kargo yourself (see below). |
| `600000000000` | Only 12 digits. If you choose Aras Kargo for it, Peek says it "doesn’t issue numbers like this one". Check for a missing digit. |

Spaces don't matter when you paste a number into Peek. A link to Aras's tracker with the number in it (kargotakip.araskargo.com.tr, with `code=` and the number) works too.

**Where to find it.** In the shop's shipping email or on your order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number) and [tracking number formats](guide:tracking-number-formats).

## Aras Kargo tracking statuses in English

Aras's tracker shows the parcel's current status (`Son Durum`), a planned delivery day and a list of vehicle movements (`Araç Hareketleri`). Each movement has a date and time (`İşlem Tarihi`), a province (`İl`), a unit (`Birim`), which is a branch or a transfer centre, and a description (`İşlem Açıklaması`). When they happen, the tracker also lists the days a delivery was not made and any redirection to another branch.

**The current status.** One of these, at the top:

| Aras says | What it means for you | Peek shows |
| --- | --- | --- |
| `YOLDA` | On the way, between Aras's transfer centres. | "In transit" |
| `TESLİMAT ŞUBESİNDE` | At the delivery branch, the one that brings the parcel to you. It stays like this after a failed delivery too. | "In transit" |
| `TESLİM EDİLDİ` | Delivered. Aras gives the day and time. | "Delivered" |

**The vehicle movements.** Each scan as the parcel is loaded, unloaded or bagged:

| Aras says | What it means for you | Peek shows |
| --- | --- | --- |
| `ŞUBEDE ARAÇ YÜKLEME` | Loaded onto a vehicle at a branch. | "In transit" |
| `ŞUBEDE ARAÇ İNDİRME` | Unloaded from a vehicle at a branch. | "In transit" |
| `AKTARMADA HAT ARACI YÜKLEME` | Loaded onto a long-distance vehicle at a transfer centre. | "In transit" |
| `AKTARMADA HAT ARACI İNDİRME` | Unloaded from a long-distance vehicle at a transfer centre. | "In transit" |
| `AKTARMADA SUBE ARACI YUKLEME` | Loaded onto a branch's vehicle at a transfer centre. | "In transit" |
| `AKTARMADA SUBE ARACI INDIRME` | Unloaded at a transfer centre from a branch's vehicle. | "In transit" |
| `AKTARMADA TORBA DOLDURMA` | Put in a bag with other parcels at a transfer centre. | "In transit" |

The tracker writes the two lines with `SUBE` without Turkish letters, as above.

**A delivery that was not made** (`Teslim Edilememe Nedeni`, the reason). One line for each day:

| Aras says | What it means for you | Peek shows |
| --- | --- | --- |
| `TESLİMAT GÜN İÇERİSİNDE YAPILAMADI` | The parcel could not be delivered that day. | "Delivery attempted" |
| `TATİL DOLAYISIYLA TESLİMAT YAPILAMADI` | No delivery because of a holiday. | "Delivery attempted" |
| `UĞRAMA NOTU BIRAKILDI (1-2)` | The courier came and left a notice. | "Delivery attempted" |
| `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` | Your address is outside the area the branch delivers to. | "Delivery attempted" |

**A redirection.** `YÖNLENDİRİLDİ` means the parcel was sent on to another branch. Aras gives the reason, and Peek adds it in brackets after the word. Peek shows "In transit".

These two kinds of line have a date but no time. Aras's tracker can also show a note from the courier beside a missed delivery: Peek leaves it out. More about the stages in [what tracking statuses mean](guide:tracking-statuses).

## Aras Kargo delivery in Turkey

A parcel goes from the Aras branch that takes it in, through transfer centres (the lines with `AKTARMADA`), to the delivery branch (`TESLİMAT ŞUBESİNDE`), which brings it to your address.

**Planned delivery day.** Aras's tracker gives one. Peek shows it as the expected delivery date while the parcel is on its way. It drops the date after a failed delivery, and once the day has passed.

**Outside the delivery area.** `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` means the branch doesn't deliver to your address: ask the shop what happens next.

**What this page can't tell you.** Peek couldn't read Aras Kargo's own pages, so this page gives no delivery times, number of attempts, holding periods or pickup rules. Aras's tracker, as Peek reads it, names no pickup point. For a missed parcel, see [missed delivery](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

## Aras Kargo tracking not updating, lost or returned

**No result.** When Aras doesn't know the number, its tracker answers `Girdiğiniz bilgiler için bir sonuç bulunamamıştır.`: no result was found for what you entered. The shop may not have handed the parcel over yet. Peek keeps asking: a parcel filed under Aras Kargo shows "Waiting for the carrier". Check the number with the shop: 13 digits. If nothing comes after a few days, ask the shop whether it really gave the parcel to Aras.

**Stuck at `TESLİMAT ŞUBESİNDE`.** The delivery branch has the parcel, and the status stays the same after a failed delivery. If a reason is listed for a day (the table above), the parcel wasn't delivered that day, and the reason says why. Otherwise, it is waiting there for delivery. Peek knows no out-for-delivery step from Aras, so the parcel shows "In transit" until it is delivered or a delivery fails.

**No update for days.** When a parcel on its way has had no new scan for four days, Peek flags it ("No tracking update for four days") and keeps asking Aras. Parcels often go quiet over a weekend. See [why tracking stops updating](guide:tracking-not-updating).

**Delivered, but nothing came.** Check with neighbours and anyone at your address. Aras's tracker shows who signed for the parcel: Peek leaves that name out, so look on Aras's tracker. Then contact the shop and Aras. More in [delivered but not received](guide:delivered-not-received).

**Lost or returned.** The shop booked the delivery with Aras, so ask the shop first for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## Aras Kargo customer service and contact

As of 31 October 2026, Peek couldn't read Aras Kargo's website, so this page lists no phone number or email: Peek gives only contacts read on a carrier's own pages.

For an order from a shop, ask the shop first: it booked the delivery. On Peek, the place of each movement names the branch or transfer centre, and the delivery's place is the branch that delivered it: give that name when you ask.

## Track an Aras Kargo parcel with Peek

[Peek](/) can't be sure of an Aras Kargo number by its shape alone: other carriers use 13 digits too. Paste it all the same: Peek follows more than 3,500 carriers.

- **13 digits starting with 60 or 65.** Peek asks Aras Kargo and Delhivery at once whether they have the parcel. While it asks, you see "Checking tracking services…". If Aras has it, Peek says "Aras Kargo has this parcel." and files it there.
- **Other 13 digits, or no carrier has it yet.** Press "Change" and pick Aras Kargo (type Aras). Peek then asks Aras's tracker for that number. On a parcel you've already added, use "Change carrier".
- **A link to Aras's tracker.** Peek takes the number from it and names Aras Kargo at once ("Detected carrier").

Added without a carrier, a parcel shows "No tracking history yet" while Peek keeps asking carriers and tracking services. If a tracking service names Aras Kargo as the parcel's only carrier, with dated steps, Peek files it under Aras.

**What Peek shows.** The current status, each movement with its time and place (the branch or transfer centre and its province), the days a delivery was not made, redirections, the planned delivery day, and the delivery time with the branch. It leaves out the names, who signed, the delivery code, payment and invoice details, the parcel's volume and count, the progress bar, and the notes and messages. Peek reads Aras's times as Turkish time. The link on the parcel's page, "Open the Aras Kargo website", opens the number on Aras's tracker.

When Aras's tracker can't be reached, Peek asks tracking services instead and shows the history they hold, in their own words.

Peek checks Aras every 10 minutes. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), it checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". Peek can't contact Aras Kargo or the shop for you.

## Questions about Aras Kargo tracking

### What does TESLİMAT ŞUBESİNDE mean on Aras Kargo tracking?

It means at the delivery branch: the Aras branch that brings the parcel to you has it. The status stays the same after a failed delivery: look for a line with a reason, which Peek shows as "Delivery attempted".

### What does YOLDA mean on Aras Kargo?

It means on the way: the parcel is travelling between Aras's transfer centres, towards the delivery branch. Peek shows "In transit".

### What does an Aras Kargo tracking number look like?

13 digits, in Peek's code. Peek links the ones starting with 60 or 65 to Aras Kargo, such as `6000000000000`. For other 13 digits, pick Aras Kargo yourself.

### Why can't Aras Kargo find my tracking number?

`Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` means Aras has no result for the number yet. The shop may not have handed the parcel over. Check the 13 digits, wait a day, then ask the shop.

### How do I contact Aras Kargo?

Ask the shop first: it booked the delivery. Peek couldn't read Aras Kargo's website, so this page gives no phone number or email.

:::sources
- [Universal Parcel Scraper: Aras Kargo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/aras-kargo) – names, the 13-digit number and the 60 and 65 shape, the tracker and its link, its sections and Turkish wordings with their stages, the not-found message, planned delivery day, times on Turkish time, what Peek leaves out
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – other carriers sharing the shape, which carriers Peek asks about it, tracking services as a fallback
:::
