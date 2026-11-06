---
title: Quantium Solutions tracking: numbers, statuses and help
description: Track a Quantium Solutions parcel: the numbers its tracker takes, what each status means, who delivers it, customs and how to reach Quantium's support.
slug: quantium-solutions-tracking
published: 2026-11-02
updated: 2026-11-02
---

Quantium Solutions is an eCommerce logistics company with a presence in 10 countries in Asia Pacific. It carries online orders into, within and out of the region. Track a parcel on Quantium's [Track Your Shipments](https://track.quantiumworldwide.com/) page, or paste the number in the box above. The shop gives you the number, in its shipping email or on your order page.

## Quantium Solutions tracking number: what it looks like

Quantium's tracker asks for a "Tracking/Reference number" and takes several at once, one per line. Peek's code knows two shapes of Quantium number, and neither is Quantium's alone. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `0000000000` | Ten digits: in Peek's code, Quantium's number for a shipment in Malaysia. Ten digits fit several carriers' numbers, DHL's among them, so Peek can't name Quantium from it. |
| `UQ000000005TH` | UQ, nine digits and TH: in Peek's code, Quantium's number for a shipment in Thailand. It has the international postal layout, so Peek names Thailand Post. |
| `RA000000005SG` | Two letters, nine digits and SG: a [Singapore Post](carrier:singapore-post) number. Peek's code notes that Quantium carries some parcels under SingPost's numbers. Peek names Singapore Post. |

Peek's Quantium lookup takes only these two shapes. Spaces and lower case don't matter. For a reference of any other shape, use Quantium's tracker. Can't find your number? See [how to find a tracking number](guide:find-tracking-number) and [tracking number formats](guide:tracking-number-formats).

## Quantium Solutions tracking statuses explained

Each line on Quantium's tracker has a milestone and often a remark, the step as the partner handling the parcel calls it. Peek writes the milestone, then the remark after a colon when it says more. The lines Peek knows, in the order a parcel meets them:

| Quantium says | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipment created` | The shop has booked the shipment. Quantium doesn't have the parcel yet. | "Label created" |
| `Shipment received` | Quantium has the parcel. | "Received by carrier" |
| `Shipment under processing at origin hub` | Being sorted at a hub in the sender's country. | "In transit" |
| `Shipment despatched to transshipment hub` | On its way to a hub where it switches to its next leg. | "In transit" |
| `DEPART ORIGIN` | It has left its starting point. | "In transit" |
| `Shipment received at transshipment hub: Arrive Hub` | At the transshipment hub. | "In transit" |
| `Shipment despatched to overseas: Depart Hub` | It has left the hub for your country. | "In transit" |
| `READY FOR DEPARTURE`, `FLIGHT DEPARTURE TO DESTINATION` | Waiting for its flight, then flying to your country. | "In transit" |
| `Shipment arrived at destination port: Arrive Destination` | It has arrived in your country. | "In transit" |
| `IN TRANSIT: Customs Clearance Begins` | Customs in your country has started clearing it. | "At customs" |
| `Shipment released from customs/ government check point: Customs Cleared` | Customs has let it through. | "In transit" |
| `Shipment received at delivery branch: Handed Over for Delivery` | The local delivery branch has it. | "In transit" |
| `IN TRANSIT` | Moving. The sorting scans of the partner in your country come this way. | "In transit" |
| `Shipment out for delivery` | Out for delivery. | "Out for delivery" |
| `Shipment delivered` | Delivered. Peek leaves out who took the parcel. | "Delivered" |

A line Peek doesn't know shows as Quantium writes it, and Peek reads a stage from its words where it can. For a number it doesn't know, Quantium's tracker says "No record!". More in [what tracking statuses mean](guide:tracking-statuses).

**Times.** Quantium's tracker gives each line a date and time but no time zone, on the clock of whoever scanned the parcel: the sender's country for the first lines, then the partner in your country. Two lines from different countries can't be compared hour for hour.

## Who delivers Quantium Solutions parcels

Quantium sells shops four cross-border services:

| Service | Tracking | Delivery channel | Transit time | Duties and taxes |
| --- | --- | --- | --- | --- |
| Q-Simple | none | postal | 4 to 17 days | the receiver's (DDU) |
| Q-Smart | tracked | postal | 4 to 10 days | the receiver's (DDU) |
| Q-Speed | tracked, priority | postal | 3 to 7 days | the receiver's (DDU) |
| Q-Sure | tracked | commercial | 2 to 5 days | the shipper's or the receiver's (DDP or DDU) |

Peek doesn't show which service your parcel uses: ask the shop.

**In Asia.** Quantium's own last-mile services offer doorstep delivery, pickup at post offices, parcel lockers and more than 3,000 pickup points across Asia, and cash or electronic payment on delivery. Its Regular delivery takes 2 to 4 days.

**In Australia.** Quantium's Australian company, Quantium Solutions (Australia) Pty Ltd, sends parcels within Australia by courier, [CouriersPlease](carrier:couriersplease) among them, and by [Australia Post](carrier:australia-post) eParcel and express eParcel, as its fuel-surcharge page shows. Quantium also lists CouriersPlease among its official partners. The carrier that delivers yours sets the delivery attempts and where a missed parcel waits.

If nobody was in, see [missed delivery](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

## Customs and duties on a Quantium parcel

On Q-Simple, Q-Smart and Q-Speed, Quantium lists duties and taxes as the receiver's to pay: delivered duty unpaid (DDU). Q-Sure can go DDP, with the sender paying, or DDU. Ask the shop which applies to your order. What you owe, if anything, depends on your country's rules: see [parcels held at customs](guide:customs).

**Into the EU.** Goods bought online from outside the EU carry a temporary €3 customs duty per item in parcels worth up to €150, since 1 July 2026, and a €2 Union handling fee per item, whatever the value, since 1 November 2026. An item is the goods in a parcel that share a tariff classification, description and, where required, origin. Quantium tells the businesses that ship with it that the €2 fee is charged for each customs declaration line, under both IOSS and DAP clearance and whatever the shipment's value, with VAT on top where the destination country requires it. Ask the shop whether your price included it.

## Quantium Solutions tracking not updating, lost or returned

**Only `Shipment created`.** The shop has booked the shipment but Quantium hasn't scanned the parcel yet. If it stays that way for days, ask the shop whether it really sent it.

**Quiet in transit.** After four days without a new line, Peek flags the parcel ("No tracking update for four days") and notes that parcels often go quiet on long flights, at customs or over a weekend. Quantium posts delay notices on its News page, such as the one on severe haze in Malaysia and Singapore of 7 October 2026. Its tracker can also list the partner's number for the parcel, under "Tracking No.": track it with that partner too. See [why tracking stops updating](guide:tracking-not-updating).

**Delivered, but not there.** Check around your home and with neighbours, then contact the shop and Quantium. See [delivered but not received](guide:delivered-not-received).

**Lost or damaged.** Quantium's site gives no claims process for recipients. The shop is Quantium's customer, so ask it for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [damaged parcels](guide:damaged-parcel).

**Returned.** Quantium offers shops a return to origin. See [returned to sender](guide:returned-to-sender).

## Quantium Solutions customer service and contact

Quantium's channels, as read on 1 November 2026:

- **Online:** the [Customer Support Form](https://www.quantiumsolutions.com/contact-support/), for item tracking, general help and feedback. It asks for your name, your email, the country the item was sent from and the tracking reference number; its Customer ID field is marked "Corporate Customer only". Quantium says its local customer service officer replies within the next working day.
- **Malaysia:** (603) 5569 6901, customerservice.my@quantiumworldwide.com.
- **Thailand:** (662) 712 2999, customerservice.th@quantiumworldwide.com.
- **Elsewhere:** the [Contact Us](https://www.quantiumsolutions.com/contact-us/) page lists support mailboxes for Hong Kong, Indonesia, Taiwan and Vietnam. For Singapore it gives only a sales address, and it lists no Australian contact. No opening hours are given.

For an online order, ask the shop first: it booked the delivery and is Quantium's customer.

## Track a Quantium Solutions parcel with Peek

[Peek](/) can't tell a Quantium number from its shape alone. Paste it all the same: Peek follows more than 3,500 carriers. For ten digits, Peek asks the carriers whose numbers look like it, then tracking services ("Checking tracking services…"). For a number ending in SG or TH, it names Singapore Post or Thailand Post ("Detected carrier"). To follow Quantium's own lines, tap "Change" and pick Quantium Solutions; on a parcel you've already added, use "Change carrier". Pick Quantium for a number of another shape and Peek says it "doesn’t issue numbers like this one".

A link to Quantium's tracker with `trackno=` works too: Peek takes the number from it and names Quantium Solutions straight away.

Peek shows Quantium's status, each line in Quantium's words as in the table above, and the destination country. It leaves out the customer reference, the partner's tracking number and the origin the tracker prints. Quantium gives no place for each line, no expected delivery date, weight or proof of delivery, so Peek shows none. "Open the Quantium Solutions website" opens the number on Quantium's tracker.

Quantium's times carry no time zone, so Peek reads them all on one clock: the destination's for a parcel to Singapore, Malaysia, Thailand or Australia (Sydney time for Australia), otherwise Singapore time. It shows every time in your device's time zone, so a line scanned on another country's clock can be a few hours off.

When the number has the postal shape with a valid check digit and Quantium names the destination country, Peek also asks that country's post with the same number, such as [Australia Post](carrier:australia-post), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [An Post](carrier:an-post), [Canada Post](carrier:canada-post) or [NZ Post](carrier:nz-post). It follows the parcel there once that post shows fresh scans, and keeps Quantium's history. Not for every country: not for parcels to Singapore, Malaysia, Thailand, Hong Kong or Vietnam, for example, and never for ten-digit numbers.

Peek checks every 10 minutes, or every 2 minutes from 6 a.m. local time on the day Quantium scans the parcel out for delivery, while Quantium's own tracker answers (through tracking services, every 15 minutes). It can notify you of "Every scan", "Important steps" or "Delivery only". After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It has no help page to link for Quantium and can't contact Quantium or the shop for you: use the contacts above.

## Questions about Quantium Solutions tracking

### Is Quantium Solutions the same as Quantium Worldwide?

Yes: its tracker and support mailboxes are on quantiumworldwide.com, and Peek also knows it as Quantium or Quantium Solutions International.

### Why does Peek say Singapore Post or Thailand Post for my Quantium number?

Your number has the international postal layout, which names a post: SG for Singapore Post, TH for Thailand Post. To see Quantium's own lines, tap "Change" and pick Quantium Solutions.

### What does Customs Clearance Begins mean?

Customs in your country has started clearing the parcel, and Peek shows "At customs". When customs lets it through, the line reads `Shipment released from customs/ government check point: Customs Cleared`. On a DDU service, any duties and taxes are yours to pay: see [parcels held at customs](guide:customs).

### How long does Quantium Solutions take to deliver?

Quantium gives 2 to 5 days for Q-Sure and up to 17 for Q-Simple (table above). Within Asia, its Regular delivery takes 2 to 4 days.

### Can I track a Q-Simple parcel?

Not along the way. Quantium sells Q-Simple as its untracked economical service, so don't count on scans. Ask the shop when it was sent.

:::sources
- [Quantium Solutions: Track Your Shipments](https://track.quantiumworldwide.com/) – the tracker, its "Tracking No." column, "No record!"
- [Quantium Solutions: Home](https://www.quantiumsolutions.com/) – presence in 10 countries in Asia Pacific
- [Quantium Solutions: About Us](https://www.quantiumsolutions.com/about-us/) – what Quantium does, CouriersPlease among its partners
- [Quantium Solutions: Cross Border Shipping](https://www.quantiumsolutions.com/cross-border-shipping/) – the four services, return to origin
- [Quantium Solutions: Last Mile Delivery](https://www.quantiumsolutions.com/last-mile-delivery/) – delivery options in Asia
- [Quantium Solutions: Australia Fuel Surcharges](https://www.quantiumsolutions.com/australia-fuel-surcharges/) – CouriersPlease, Australia Post eParcel
- [Quantium Solutions: Service Impact Notice (haze)](https://www.quantiumsolutions.com/news/13/) – haze delays
- [Quantium Solutions: Service Update (EU customs handling fee)](https://www.quantiumsolutions.com/news/14/) – €2 fee per declaration line
- [Quantium Solutions: Customer Support Form](https://www.quantiumsolutions.com/contact-support/) – what the form asks, reply time
- [Quantium Solutions: Contact Us](https://www.quantiumsolutions.com/contact-us/) – phones and mailboxes
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty, €2 handling fee
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – what an item is
- [Universal Parcel Scraper: Quantium Solutions](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/quantium-solutions) – number shapes, links, wordings, clocks, fields
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – detection, the clock Quantium's times are read on, hand-off to the destination post
:::
