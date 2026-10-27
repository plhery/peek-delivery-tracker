---
title: Purolator tracking: PIN, statuses and missed deliveries
description: Track a Purolator parcel: what the PIN looks like, what Purolator's statuses mean, pickup after a missed delivery, customs from the US and who to call.
slug: purolator-tracking
published: 2026-10-15
updated: 2026-10-27
---

Purolator is a Canadian courier for parcels and freight, majority owned by Canada Post. To track a parcel, enter its PIN in Purolator's tracker at [purolator.com](https://www.purolator.com/en/shipping/tracker) or paste it in the box above. The PIN comes from the sender: for an online order, it's in the shop's shipping confirmation.

## Purolator tracking number: the PIN and what it looks like

Purolator calls its tracking number the PIN, short for Package Identification Number. Each shipment gets one, and Purolator calls it your reference for every contact with Purolator about that shipment. So a PIN and a tracking number are the same thing.

| Shape (invented) | What it is |
| --- | --- |
| `123456789015` | 12 digits. Peek reads the last one as a check digit. |
| `ABC123456789` | 3 letters and 9 digits. Purolator's tracker labels this a PIN too. |

Purolator's tracker also takes a tracking reference of up to 15 characters, if the sender set one: an order number, a code, even a sentence. It works like a PIN.

Twelve digits is a common length. DPD France, Mondial Relay, Colis Privé, BRT, FedEx and several Chinese carriers use 12-digit numbers too, so check which carrier the shop's email names. A 16-digit number is never Purolator (it's often [Canada Post](carrier:canada-post)), and 13 characters ending in `CA` are Canada Post's. More in [tracking number formats](guide:tracking-number-formats).

A shipment can also get a new PIN on the way: the history then shows a scan with the new number.

## What Purolator tracking statuses mean

Purolator's tracker shows a headline (`Shipment Created`, then `Delivered`) above a list of scans. These are the English scans; Purolator's French site shows French ones.

| Purolator's status | What it means for you |
| --- | --- |
| `Shipper created a label` | The sender made the label. Purolator doesn't have the parcel yet. |
| `Label information electronically submitted` | Same: the label's details reached Purolator, the parcel hasn't. |
| `Picked up by Purolator` | Purolator has it. |
| `Arrived at sort facility` | It reached a Purolator terminal; the place is that terminal's town and province. |
| `Departed sort facility` | It left the terminal for the next one, or for your local depot. |
| `Delayed in transit due to missed connection` | A delay inside Purolator's network. The parcel keeps moving. |
| `Cleared customs` | From abroad: customs released it and it's back on its way. |
| `On vehicle for delivery` | It's on the truck today. |
| `Attempted delivery - receiver unavailable` | The driver came and nobody could take it. |
| `Address correction required - missing entry code` | The driver couldn't get into your building. |
| `Available for pickup for 5 business days from arrival date at the counter` | It's waiting at a counter for you to pick up. |
| `Shipment delivered` | Delivered to you, or picked up by you at the counter. |

`Delayed in transit due to rail delay` works like the missed connection: Purolator's own network is late, nothing is wrong with your parcel. `Address correction required - resolution in progress` means Purolator is fixing the address; it's often followed by `Shipment scheduled for delivery next business day`, which means the parcel is back on track.

`Shipment unavailable - En route to Purolator pickup location` means the parcel is on its way to the counter that will hold it. You can't pick it up until the `Available for pickup…` scan appears.

## Purolator delivery attempt, pickup and signatures

When nobody is home, the tracking typically shows `Attempted delivery - receiver unavailable`, then `Available for pickup for 5 business days from arrival date at the counter` in the same town, then `Shipment delivered` once you've collected it. That 5-business-day hold is in Purolator's own wording, so don't wait too long.

The counters Purolator uses for held parcels are its Purolator Shipping Centres, authorized shipping agents and PostNet stores. Purolator's Service Guide lists more than 130 Shipping Centres and more than 550 authorized agents across Canada, and its [centre locator](https://www.purolator.com/fr/expedition/trouver-un-centre-dexpedition) finds them.

Signatures depend on what the sender chose:

- **No signature** is the default for residential deliveries. "Signature required" is an option the sender picks.
- **Adult signature required**: you show government photo ID with proof of age at the door. Without it, the parcel isn't handed over and goes to a Shipping Centre or agent, where you need government photo ID with a valid address. These parcels are never left at the door.

A sender can also ship "Hold for pickup", so the parcel goes straight to a Purolator depot instead of your address.

## Purolator tracking not updating, delayed or missing

No result at all usually means Purolator hasn't scanned the parcel yet. Purolator's help says tracking shows details only once it has physically received and scanned the parcel. Check the digits too: one mistyped digit is enough to get no result.

Delays show in the history as their own scans (missed connection, rail delay); Purolator's older help also names weather and mechanical delays. The parcel keeps moving after them. More in [tracking that isn't updating](guide:tracking-not-updating).

Lost, damaged, or marked delivered but not there? Contact the shop first: the sender holds the contract with Purolator. The shop can tell you how a claim works.

Goods sent by courier from the United States or Mexico may qualify for relief from customs duties when worth CAD 150 or less, and from sales and excise taxes at CAD 40 or less, under the Canada Border Services Agency (CBSA) rules. Splitting an order into several parcels to stay under those amounts isn't allowed. The importer pays duties and taxes, and the CBSA doesn't regulate brokerage fees: they're the courier's own. More in [parcels held at customs](guide:customs).

## Purolator customer service phone number

Purolator's number in Canada, from its Service Guide in force from 30 June 2026 (read on 14 October 2026), is **1 888 SHIP-123 (1 888 744-7123)**. Have your PIN ready: Purolator asks for it in every contact about a shipment.

Got a text about a delivery attempt with a link? Don't tap it: type the PIN into Purolator's tracker yourself. The Government of Canada asks you to forward spam texts to 7726 (SPAM).

## Tracking Purolator parcels with Peek

[Peek](/) names Purolator at once for 3 letters and 9 digits. A 12-digit number is shared, so if its check digit passes, Peek asks five carriers at once, Purolator among them (first for readers in Canada, the US and the UK), and names the one that knows it. If carriers of different brands know it, or none does, pick Purolator with "Change". Links from purolator.com with `pin=`, `pins=` or `searchValue=` work too.

Peek shows the status, each scan with its town, province and country on a map, the expected delivery day, the destination country and, for single-parcel shipments, the weight. Statuses stay in Purolator's English, exactly as written. The recipient's name is dropped, and Peek doesn't show which counter holds a parcel (only the `Available for pickup…` wording) or let you change a delivery.

Scan times show in your device's time zone. On cross-border shipments, Purolator sends them without a time zone: Peek then reads them on the clock of the scan's town and still lists Purolator's own history. Purolator's website also sometimes answers Peek with a check instead of data; then tracking services fill in. You can be notified at every scan, at important steps or on delivery day only.

## Questions about Purolator tracking

### Is a Purolator PIN the same as a tracking number?

Yes. PIN is Purolator's name for its tracking number, usually 12 digits or 3 letters and 9 digits.

### Why is my Purolator tracking number not working?

Most often Purolator hasn't scanned the parcel yet: tracking starts once Purolator has it in hand. Otherwise check for a typo, ask the shop whether it's really a Purolator number, or look for a new PIN in the history.

### How long does Purolator hold a parcel for pickup?

5 business days from the day it arrives at the counter, as Purolator's status `Available for pickup for 5 business days from arrival date at the counter` says. Bring government photo ID if the parcel needs an adult signature.

### Can you track Purolator without a tracking number?

Only with the sender's tracking reference, if the sender set one: Purolator's tracker accepts it like a PIN. Otherwise ask the shop for the PIN.

:::sources
- [Purolator: Tracker](https://www.purolator.com/en/shipping/tracker) – PIN shapes, status wordings, headline statuses, 5-business-day hold (read through its tracking service)
- [Purolator: Guide des services de Purolator](https://web.purolator.com/app/guides/pdf/fr/Purolator_Service_Guide_French.pdf) – phone number, Shipping Centres and agents, hold for pickup, signatures, adult signature
- [Purolator: Tracking Details](https://eshiponline.purolator.com/ShipOnline/Help/Default/trackingdetails_E.htm) – no scans before pickup, new PIN, weather and mechanical delays
- [Purolator: Tracking - By PIN / By Reference](https://eshiponline.purolator.com/ShipOnline/Help/Default/trackingRef_E.htm) – tracking by PIN, 15-character reference
- [Purolator: Preparing a Shipment - Shipment Confirmation](https://eshiponline.purolator.com/ShipOnline/Help/Default/shipstep3_E.htm) – what a PIN is
- [CBSA: Memorandum D8-2-16](https://www.cbsa-asfc.gc.ca/publications/dm-md/d8/d8-2-16-eng.html) – CAD 150 and CAD 40 courier thresholds, no splitting
- [CBSA: Step 4, import guide](https://www.cbsa-asfc.gc.ca/import/guide-4-eng.html) – importer pays, brokerage fees not regulated
- [Government of Canada: Mobile spam](https://ised-isde.canada.ca/site/canada-anti-spam-legislation/en/hung-mobile-spam) – forward spam texts to 7726
- [Wikipedia: Purolator Inc.](https://en.wikipedia.org/wiki/Purolator_Inc.) – Canada Post ownership
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
