---
title: GLS US tracking (GSO): numbers and statuses explained
description: Track a GLS US parcel, also listed as GSO or Golden State Overnight: what its SH number looks like, what each scan means and how Peek follows it.
slug: gls-us-tracking
published: 2026-11-13
updated: 2026-11-13
---

GLS US is a parcel carrier in the United States. Peek's catalog also knows it as GLS USA, GSO and Golden State Overnight. To track a GLS US parcel, paste its number in the box above. The number comes from the shop that shipped your order. GLS's own tracker for US parcels is on gls-group.com, under /US/.

This page covers what Peek does with a GLS US parcel, not GLS US's own delivery rules, times or contacts: for those, see GLS's tracker for US parcels or ask the shop.

## GLS US tracking number: what it looks like

Peek's catalog knows one shape for GLS US numbers. A GLS US number of any other shape still works: you pick the carrier. With invented digits:

| What you paste (invented) | What Peek does |
| --- | --- |
| `SH000000000000000000` | SH and 18 digits, 20 characters in all. Only GLS US uses this shape in Peek's catalog, but the match is weak, so Peek asks GLS US before naming it. |
| `sh 0000 0000 0000 0000 00` | The same number. Lower case, spaces and dashes don't matter. |
| A GLS US number of another shape | Peek doesn't recognize it. Pick GLS US yourself (see "Track a GLS US parcel with Peek" below). |
| A tracking link from gls-group.com with `/US/` in it, or from gls-us.com or gso.com | Peek takes the number out of the link and names GLS US at once ("Detected carrier"). |

**Where to find it.** In the shop's shipping email or on your order page. No number at all? See [how to find a tracking number](guide:find-tracking-number). An order number won't work.

**Not the European GLS.** GLS US is a separate network from GLS in Europe, with its own tracker. A gls-group.com link with `/EU/` or `/DE/` in it leads Peek to [GLS Germany](carrier:gls-de), not to GLS US. If the shop's email names a GLS network in Europe, or another carrier altogether, track it there. [Tracking number formats](guide:tracking-number-formats) shows what other carriers' numbers look like.

## What GLS US tracking statuses mean

GLS US's tracker gives the shipment a status of its own, above a list of scans. Each scan has a date and a time, and most name a place: the town, the state and the country. The scans Peek's code knows are in English, in capitals. In the order a parcel meets them:

| GLS US says | What it means for you | Peek shows |
| --- | --- | --- |
| `LABEL CREATED` | The shipper has made a label. GLS US may not have the parcel yet. This scan names no place. | "Label created" |
| `ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` | GLS US has scanned the parcel on arrival. The date at the end, month first, is the day GLS US has scheduled the delivery, so it changes from parcel to parcel. | "In transit" |
| `SCANNED AT DELIVERY DEPOT` | It has been scanned at the depot that delivers it. | "In transit" |
| `ON ROUTE FOR DELIVERY` | It's on a delivery route today. | "Out for delivery" |
| `SHIPMENT DELIVERED` | Delivered. | "Delivered" |

**The shipment's status.** Above the scans, GLS US's tracker sums the parcel up in a few words: `label created`, `in transit`, `out for delivery`, `delivered` or `returned`. When that status is further along than the scans, Peek goes by it: a parcel summed up as `delivered` shows "Delivered" even if the newest scan doesn't say so, and one summed up as `returned` shows "Returned to sender".

**Other lines.** Peek's code knows no GLS US wording for a missed delivery, a problem, a hold or a pickup point. A line it doesn't know still shows as GLS US writes it, and Peek sorts it by the rules it uses for every carrier. More in [tracking statuses explained](guide:tracking-statuses).

If nothing follows `LABEL CREATED` for a day or more, Peek says "The sender hasn’t handed it to GLS US yet. Tracking starts when they do." Ask the shop.

## GLS US delivery

When GLS US's tracker gives an expected delivery day, Peek shows it until the parcel is delivered or returned. The arrival scan names the scheduled day too. Peek's code hands GLS US parcels over to no other carrier.

This page gives no delivery hours, weekend deliveries, attempts, signature rules, pickup points or holding times. Ask the shop how and when the parcel comes: it booked the delivery with GLS US. If a delivery fails or the parcel waits somewhere for you, see [missed delivery](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

## GLS US parcel not found, not updating, lost or returned

**Not found.** GLS US's tracker says when it doesn't know a number. Peek then shows "Waiting for GLS US’s first scan" for an SH number you left to it, or "Waiting for the carrier" when you picked GLS US yourself, and keeps asking. A new label often takes a while to show up. If there's still nothing two days after you added the parcel, Peek flags it: "Still waiting for the carrier’s first update". Check the number against the shop's email, then ask the shop whether the parcel has really been sent.

**No news for days.** After four days without a new scan, a parcel still showing "Label created", "Received by carrier" or "In transit" gets a flag: "No tracking update for four days". Peek adds that parcels often go quiet over a weekend, and keeps asking GLS US. See [why tracking stops updating](guide:tracking-not-updating).

**Delivered, but nothing came.** `SHIPMENT DELIVERED` with no parcel at your door? Check around the door, the mailroom and with neighbors, then ask the shop. See [delivered but not received](guide:delivered-not-received).

**Lost or damaged.** Tell the shop first: it booked the delivery and can take it up with GLS US or send you a new parcel. See [lost parcels](guide:lost-parcel) and [damaged parcels](guide:damaged-parcel).

**Returned.** "Returned to sender" means the parcel went back to the shop. Ask it for a refund or a new delivery. See [returned to sender](guide:returned-to-sender).

## GLS US customer service and contact

As of November 13, 2026, Peek couldn't read GLS US's website, so this page lists no phone number or email: Peek gives only contacts read on a carrier's own pages.

The shop booked the delivery with GLS US, so ask it first, with your order and tracking numbers to hand: it can take the matter up with GLS US.

## Track a GLS US parcel with Peek

**An SH number.** Paste it in the box above. While Peek asks GLS US, the carrier line reads "Checking tracking services…". When GLS US knows the number, the line names GLS US and says "has this parcel", and Peek files the parcel under it. When GLS US doesn't know it yet, the line says "not found yet · Peek keeps checking". Add the parcel anyway: it shows "Probably GLS US" as its carrier until GLS US scans it.

**Another number.** Paste it all the same: Peek follows more than 3,500 carriers. Then pick GLS US yourself:

1. Press "Change" beside the carrier line and search for GLS US. "GSO" or "Golden State Overnight" finds it too, with "Also called GSO" or "Also called Golden State Overnight" under the name, and so does "United States".
2. Pick GLS US: the line then reads "Chosen by you". Track the parcel.

On a parcel you've already added, use "Change carrier". Peek asks GLS US only about numbers of 5 to 20 letters and digits.

**What you see.** Peek asks GLS US's tracker by the number alone: no account and no ZIP code. It shows the status, each scan with its town and state on a map, and the expected delivery day. It leaves out the recipient's name and address, who signed, the signature and proof of delivery, and the shipper's reference. GLS US's tracker gives no weight. If GLS US files several shipments under one number, Peek follows the one with the newest scan. "Open the GLS US website", on the parcel's page, opens the number on GLS's US tracker.

**Times.** GLS US gives each scan's time without a time zone. Peek reads it on the clock of the scan's town, else of the scan before or after it, else on Eastern time, and shows every time in your device's time zone.

**When GLS US doesn't answer.** If GLS US's tracker can't be reached, Peek asks [tracking services](guide:universal-tracker) and shows their history, in their own words.

**How often.** While someone waits for the parcel (notifications on, or you looked in the last hour) and GLS US's tracker answers, Peek checks every 10 minutes, or every 2 minutes from 6 a.m. local time on the day GLS US puts the parcel on a delivery route; through tracking services, every 15 minutes. After two days without news (12 hours while it still shows "Label created" or "Received by carrier"), it checks hourly. Notifications can cover "Every scan", "Important steps" or "Delivery only".

Peek can't contact GLS US or the shop for you, change the delivery or file a claim.

## Questions about GLS US tracking

### Is GSO the same as GLS US?

In Peek, yes. Its catalog lists GSO, Golden State Overnight and GLS USA as other names for GLS US, and reads tracking links from gso.com and gls-us.com as GLS US's. Searching for any of these names in Peek's carrier list finds GLS US.

### What does a GLS US tracking number look like?

The shape Peek knows is SH followed by 18 digits, such as `SH000000000000000000` (invented). GLS US may use other shapes: paste them all the same and pick GLS US with "Change".

### What does the GLS US arrival scan mean?

`ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` means GLS US has scanned the parcel on arrival and scheduled its delivery for the date at the end, month first. Peek shows "In transit".

### Why does Peek say "Waiting for GLS US’s first scan"?

Your number has GLS US's shape, and Peek has asked GLS US, but GLS US doesn't list it yet. That's normal for a new label, and Peek keeps asking. If GLS US still doesn't list it after a week, Peek says so and suggests checking the number with the sender or choosing another carrier. Ask the shop for the right number.

### Is GLS US the same as GLS in Europe?

Not in Peek. It treats GLS US as a carrier of its own, with its own tracker. GLS's networks in Europe, such as [GLS Germany](carrier:gls-de), have theirs: track a parcel with the network the shop's email names.

:::sources
- [Universal Parcel Scraper: GLS US](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-us) – names, the SH shape and its weak match, tracking links, the tracker's scans and shipment statuses with their stages, not-found answer, expected delivery day, several shipments under one number, scan clocks, what Peek leaves out
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – GLS's European networks and their links, tracking services as a fallback, scans dated on the clock of their town
:::
