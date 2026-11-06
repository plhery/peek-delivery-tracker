---
title: Border Express tracking: consignment numbers and statuses
description: Track a Border Express (BEX) parcel in Australia: what its consignment numbers and label barcodes look like, what each status means and how Peek follows it.
slug: border-express-tracking
published: 2026-11-02
updated: 2026-11-02
---

Border Express, or BEX, is a carrier that Peek's catalog lists in Australia only. If an online shop sent your order with Border Express, the number to track is its consignment number: paste it in the box above. The shop gives it in its shipping email or on your order page. Border Express's own tracker is Quick Track on BEX Online, at bexonline.borderexpress.com.au. Peek couldn't check Border Express's website for this page, so what follows is what Peek's code knows about that tracker.

## Border Express tracking number: what it looks like

Border Express tracks a delivery by its consignment number. Peek's code knows three shapes. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `00000000` | 8 digits: a consignment number. |
| `ABCD000000` | 4 letters, the sender's prefix, then digits: also a consignment number. Peek takes 5 to 8 digits after the letters. |
| `ABCDBX00000000000001` | 20 characters: the barcode on one item's label. The sender's 4 letters, BX, the consignment's digits padded with zeros to 11, and a 3-digit item number. |

A consignment can hold several items, each with a label barcode of its own. Every label starts with the same letters and digits, and the consignment number ties them together: that's what Border Express's tracker looks up, and it doesn't take a label barcode. Peek does take one, and works the consignment number out from it (see below).

Peek can't tell a Border Express number by its shape alone. Its catalog has no rule for Border Express's consignment numbers, and its rule for the label barcode is only a weak match. Other carriers use the same shapes: for 8 digits, Peek suggests [Mondial Relay](carrier:mondial-relay), [Colisweb](carrier:colisweb) and [Heppner](carrier:heppner), all in Europe, and TForce Freight, a North American freight carrier, when the last digit passes its check. Four letters with 8 digits also fit [Colis Privé](carrier:colis-prive)'s 12-character numbers. If the shop said Border Express, pick Border Express instead (see below). Spaces and lower case don't matter when you paste it. No number at all? See [how to find your tracking number](guide:find-tracking-number) and [tracking number formats](guide:tracking-number-formats).

## Border Express tracking statuses explained

Border Express's tracker shows a progress bar and a list of steps, newest first, each with a suburb and state. The steps Peek's code knows, in the order a parcel meets them:

| Border Express says | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipment Data Received` | The sender has sent Border Express the parcel's details. Border Express doesn't have the parcel yet. The progress bar calls this `Data Received`. | "Label created" |
| `Picked Up` | Border Express has collected the parcel from the sender. The tracker usually lists this step without a time, and Peek leaves out a step with no time. | "Received by carrier", only when the step has a time |
| `In Transit` | On its way through Border Express's network. | "In transit" |
| `On Board For Delivery` | On the delivery vehicle. The progress bar shows `On Board`. | "Out for delivery" |
| `Partially Delivered` | Some items of the consignment are delivered; the rest are still on their way. | "In transit" |
| `Partially Delivered - Final` | Delivery is over: the consignment closes with the items that arrived. If any are missing, ask the sender. | "Delivered" |
| `Delivered` | Delivered. | "Delivered" |

Peek keeps the steps in Border Express's English, with its own status above them. A step that isn't in this table shows as written, and Peek reads its status from the wording where it can.

## Border Express delivery in Australia

Border Express's tracker names no other carrier for a parcel, so Peek follows the parcel on Border Express from the first step to delivery: it doesn't hand it over to another carrier.

This page gives no rules for delivery attempts, cards, safe drop, collecting from a depot or how long a parcel is held there: Peek couldn't check them on Border Express's website. Ask the shop that sent it, or Border Express. See [missed delivery](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

## Border Express parcel not updating, lost or delivered but not received

**Number not found.** Until Border Express's tracker lists the number, a parcel filed under Border Express shows "Waiting for the carrier", and Peek keeps checking. After two days the parcel is flagged "Still waiting for the carrier’s first update". Check the number against the shop's email: 8 digits, or 4 letters and 5 to 8 digits. If nothing comes after a few days, ask the shop whether the parcel was really sent.

**Stuck on "Label created".** Border Express has the parcel's details but no dated scan yet. Its tracker may show `Picked Up` without a time, which Peek leaves out, so look there first. If it still says only `Shipment Data Received`, ask the shop when it handed the parcel over.

**No update for days.** When a parcel at "Label created", "Received by carrier" or "In transit" has had no new step for four days, Peek flags it: "No tracking update for four days". It keeps asking Border Express and shows the next step as soon as there is one. See [why tracking stops updating](guide:tracking-not-updating).

**Partially delivered.** Check what came against your order. The rest of the consignment is still on its way, and Border Express's tracker shows each item's scans, which Peek leaves out.

**Delivered, but nothing came.** Look around your address and ask the neighbours. Border Express's tracker shows the name of whoever signed for the parcel, which Peek leaves out. Then ask the shop. More in [delivered but not received](guide:delivered-not-received).

**Lost, damaged or returned.** The shop booked Border Express, so the shop is the one to ask for a refund or a new parcel. See [lost parcels](guide:lost-parcel), [damaged parcels](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

## Border Express contact and customer service

As of 31 October 2026, Peek couldn't check Border Express's website, so this page lists no phone number or email: Peek gives only contacts read on a carrier's own pages. The shop booked the delivery, so ask it first.

On the parcel's page in Peek, "Open the Border Express website" opens your number on Border Express's tracker. Before you pay anything a parcel text asks for, read [delivery scam texts](guide:delivery-scam-texts).

## Track a Border Express parcel with Peek

Paste the number in [Peek](/), which follows more than 3,500 carriers. It may show "Checking tracking services…" while it asks around. Then press "Change" and pick Border Express: type Border Express in the search. BEX finds it too, but lists BEX Express, a carrier in Serbia, first. For a label barcode, Border Express is listed under "Fits this number". On a parcel you've already added, use "Change carrier". You need no account, postcode or phone number.

If you add the number without picking a carrier, Peek asks only tracking services, never Border Express's tracker, and shows "No tracking history yet" until one of them knows the parcel. Only if a tracking service names Border Express does Peek go on to ask Border Express. Picking Border Express yourself is the way to get its tracker's steps.

A tracking link from Border Express works too and names Border Express at once ("Detected carrier"): borderexpress.com.au/tracking with `cn=` in it, or a bexonline.borderexpress.com.au/bxo/track link. If you pick Border Express for a number of another shape, Peek says it "doesn’t issue numbers like this one", and once added, unless a tracking service has the parcel, it shows "Check the tracking number": look for a missing or extra character.

**Label barcodes.** Border Express's tracker doesn't take them, so Peek works the consignment number out of the barcode: the four letters, then the 11 digits after BX without the zeros in front, kept to at least six digits. The last 3 digits, the item number, drop out. The parcel's page shows that number under "Border Express tracks this as". The "Open the Border Express website" link opens Border Express's tracker with that consignment number too. If Border Express doesn't find the parcel by it, use the consignment number from the shop's email.

**What you see.** Peek shows the status, each step with its suburb and state, the expected delivery day until the parcel is delivered, and the time of delivery. It leaves out the progress bar, the despatch date, the number of items and each item's scans, the sender's and your town and postcode, and the name of whoever signed. Peek shows no weight or proof of delivery for Border Express. Each step carries the clock of its state, so steps in states on different time zones fall in the right order.

**How often.** Peek checks every 10 minutes, or every 2 minutes from 6 a.m. local time on the day Border Express scans the parcel out for delivery, while Border Express's own tracker answers (through tracking services, every 15 minutes). After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. If Border Express's tracker can't be reached, Peek asks tracking services instead and shows what they hold, in their words. It can notify you of "Every scan", "Important steps" or "Delivery only". Peek can't contact Border Express or the shop for you.

## Questions about Border Express tracking

### What is a Border Express consignment number?

The number Border Express tracks a delivery by: 8 digits, or the sender's 4 letters followed by digits. Every item in the delivery comes under it. The shop gives it in its shipping email or on your order page.

### Can I track Border Express with the barcode on the label?

In Peek, yes: paste the 20-character barcode (4 letters, BX and 14 digits) and pick Border Express. Peek works out the consignment number from it. Border Express's own tracker takes the consignment number only.

### What does "On Board For Delivery" mean on Border Express tracking?

The parcel is on the delivery vehicle. Peek shows it as "Out for delivery".

### Why does my Border Express tracking say "Partially Delivered"?

Your consignment has several items, and only some have been delivered. The rest are still on their way, so Peek shows "In transit". `Partially Delivered - Final` is different: Border Express has closed the consignment with the items that arrived, and Peek shows "Delivered".

### What does "Shipment Data Received" mean on Border Express tracking?

The sender has sent Border Express the parcel's details, but Border Express hasn't scanned the parcel yet. Peek shows "Label created".

:::sources
- [Universal Parcel Scraper: Border Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/border-express) – names, Australia, consignment numbers and label barcodes, tracking links, the tracker, wordings and their stages, steps without a time, state clocks, estimate and delivery time, what Peek leaves out, no other carrier named
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – other carriers' number shapes (Mondial Relay, Colisweb, Heppner, Colis Privé), BEX Express's name, no hand-off to or from Border Express
:::
