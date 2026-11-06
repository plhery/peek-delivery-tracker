---
title: GlobalPost tracking: GP numbers and who delivers
description: Track a GlobalPost parcel: what a GP number looks like, what GlobalPost's tracking lines mean and who delivers it in your country.
slug: globalpost-tracking
published: 2026-11-02
updated: 2026-11-02
---

GlobalPost, part of Auctane, is an international shipping service that online sellers buy through platforms such as ShipStation and Stamps.com. The parcel first goes to one of GlobalPost's shipping centres, where GlobalPost puts on the international label and customs form. GlobalPost's partners, postal and commercial carriers, take it on from there, and a post or courier in your country delivers it. The seller gives you the number, which usually starts with GP: paste it in the box above. GlobalPost's own tracker is at goglobalpost.com/tracking.

## GlobalPost tracking number: what it looks like

Peek recognises GlobalPost's current numbers by their shape: GP, four letters for the service, ten digits, then US or GB for the country the parcel was sent from. With invented letters and digits:

| Number (invented) | What it is |
| --- | --- |
| `GPXXXX0000000000US` | GP, four letters, ten digits and US: a GlobalPost parcel sent from the United States. |
| `GPXXXX0000000000GB` | The same, ending in GB: sent from the United Kingdom. |
| `SDC000000000000000` | SDC and 15 digits: an older GlobalPost label. Peek doesn't name a carrier from the number alone: choose GlobalPost with "Change". |
| `GPXXXX000000000US` | Only nine digits: Peek recognises no carrier. Check for a missing digit. |
| `LZ000000005US` | A US postal number, not GlobalPost's shape: Peek names [USPS](carrier:usps). If you choose GlobalPost for it, Peek says it "doesn’t issue numbers like this one". |

Peek ignores spaces, dashes and lower case.

**Where to find it.** In the seller's shipping email or on your order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number) and [tracking number formats](guide:tracking-number-formats).

**A second number.** Under "Final Mile Tracking By", GlobalPost's tracker can give the delivery company's own number for the parcel, and can open the parcel by that number too.

## GlobalPost tracking statuses explained

GlobalPost's tracker lists the scans newest first, each with a time, a wording and mostly a place, and tells its partners' scans in its own words. In the order a parcel meets them:

| GlobalPost says | What it means for you | Peek shows |
| --- | --- | --- |
| `Label Created` | The seller has created the label. Nothing has moved yet. | "Label created" |
| `Data Received` | GlobalPost has the parcel's details from the seller, not the parcel. | "Label created" |
| `Arrived at Partner Facility`, `Departed Partner Facility` | A partner has scanned it: USPS on the way to GlobalPost, or the post in your country. | "In transit" |
| `Arrived at GlobalPost Facility` | A GlobalPost shipping centre has the parcel. USPS scans on the way there can come first. | "Received by carrier" |
| `Departed GlobalPost Facility` | It has left that centre. | "In transit" |
| `Departed from Origin Country` | It has left the United States or the United Kingdom. | "In transit" |
| `In Transit`, `In-Transit` | On its way. | "In transit" |
| `Arrived in Destination Country` | It has reached your country. | "In transit" |
| `In Customs` | Customs in your country has it. | "At customs" |
| `Cleared Customs` | Customs has let it go on. | "In transit" |
| `Out for Delivery` | On today's delivery round. | "Out for delivery" |
| `Attempted Delivery` | The delivery company tried; nobody could take it. | "Delivery attempted" |
| `Exception, No Action Required` | A problem on the delivery in your country that you don't need to act on. | "Needs attention" |
| `Delayed, No Action Required` | Running late; nothing to do on your side. | "Needs attention" |
| `Delivered` | Delivered. | "Delivered" |

A line can end with GlobalPost's note that tracking updates may take time: the wording before it is the status. Peek reads any other line by its wording, with the rules it uses for every carrier; a line it doesn't know shows as written.

**Returns.** GlobalPost's tracker shows a return as a delivery: a parcel sent back ends with `Delivered` too, placed at the sender's town. When that town is in another country than yours, Peek shows "Returned to sender".

## Who delivers GlobalPost parcels in your country

GlobalPost hands Economy and Standard parcels to a postal carrier, such as USPS, for the trip abroad. Its pages name no delivery partner by country, but its tracker can, with the partner's number.

**What the tracking shows depends on the service** the seller chose:

| Service | Tracking | Duties and taxes | Time GlobalPost quotes the seller |
| --- | --- | --- | --- |
| Economy | into your country, until it clears customs | you pay | 10 to 14 business days |
| Standard | to your door | you pay | 6 to 10 business days |
| Plus, Plus – Duties & Taxes Included | to your door | the seller pays | 5 to 8 business days |
| Flat Rate (formerly Go) | to your door | in the seller's price | 6 to 10 business days |

From the United States, Plus reaches more than 30 countries, among them the UK, Ireland, Canada and Australia; Flat Rate covers Canada, Australia, New Zealand, Hong Kong and Mexico. Not sure which? Ask the seller.

**Missed delivery and pickup.** The company delivering in your country makes the attempts and keeps a missed parcel: ask it, with its number. See [missed delivery](guide:missed-delivery), [uncollected parcels](guide:uncollected-parcel) and the posts' pages: [Royal Mail](carrier:royal-mail), [An Post](carrier:an-post), [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post), [USPS](carrier:usps).

**Customs.** On Economy and Standard, the duties and taxes are yours to pay ("Delivered Duties Unpaid"). In Ireland and the rest of the EU, goods bought online from outside the EU carry a €3 customs duty per item on parcels worth up to €150, since 1 July 2026, and a €2 Union handling fee per item, whatever the value, since 1 November 2026. GlobalPost's service pages don't say how the €2 fee is charged: ask the seller whether your price included it. Elsewhere, see [parcels held at customs](guide:customs).

## GlobalPost parcel not updating, lost or returned

**Nothing yet.** Until GlobalPost's tracker has a line for the number, Peek shows "Waiting for the carrier" and keeps checking. With `Label Created` alone, the parcel may not have reached GlobalPost. If nothing comes after a few days, ask the seller whether it was sent.

**No update for days.** GlobalPost says updates may take time on international shipments, and that weather, holidays, strikes and customs can delay them. On Economy, tracking ends after customs in your country. When a parcel still on its way has had no new scan for four days, Peek flags it ("No tracking update for four days") with a note that parcels often go quiet on long flights, at customs or over a weekend. The note links no GlobalPost help page; once Peek follows the parcel at the delivery company, it links that company's, if Peek knows one. See [why tracking stops updating](guide:tracking-not-updating).

**Not there after 30 days.** GlobalPost asks you to contact the seller if the parcel hasn't arrived within 30 days of the shipping date: the seller can look into it and claim from GlobalPost, within 90 days of shipping. A claim takes up to three weeks. See [lost parcels](guide:lost-parcel).

**Delivered, but nothing came.** Ask the delivery company named in the tracking, then the seller. See [delivered but not received](guide:delivered-not-received).

**Undeliverable or returned.** GlobalPost names the usual causes: a wrong or incomplete address, unpaid duties or taxes, missed customs notifications, restricted items, or a parcel that can't be cleared into the country. The destination country's post then handles it: in most cases it goes back to the seller or waits at the local post. GlobalPost can't give a date for a return, and gets no new tracking number for it from the post. See [returned to sender](guide:returned-to-sender).

## GlobalPost customer service and contact

As read on 1 November 2026, GlobalPost's customer support answers on info@goglobalpost.com and on (888) 899-1255, Monday to Friday from 6 am to 6 pm Pacific time. Its [contact form](https://www.goglobalpost.com/contact-support/) takes recipients too: choose "Recipient" and give the tracking number. GlobalPost says its support team sees the same information as its tracking page.

For a parcel that hasn't come, GlobalPost sends you to the seller first: the seller booked the delivery and files any claim. A parcel from China sent with China Post is not a GlobalPost shipment, GlobalPost says: ask the sender.

## Track a GlobalPost parcel with Peek

[Peek](/) names GlobalPost at once from a GP number ("Detected carrier") and asks GlobalPost's tracker by that number alone: no postcode or account. A link from GlobalPost's tracker works too. For an older SDC number, choose GlobalPost with "Change". If all you have is the delivery company's number, paste it all the same: Peek follows more than 3,500 carriers.

Peek shows the status, each scan with its place, the destination country, and the delivery company with its number when GlobalPost names them. It leaves out the progress bar, the sender's and your towns, and postcodes. GlobalPost gives no expected delivery date, weight or proof of delivery, so Peek shows none. "Open the GlobalPost website" opens the number on GlobalPost's tracker.

GlobalPost's times carry no time zone: Peek reads each scan on the clock of its place, or of the leg the parcel was on, and shows it in your device's time zone.

When GlobalPost names a delivery company Peek knows by that name, the parcel card says "Delivery with" that company and lists its number after the GP number, and Peek asks that company too. While that company has only the announcement of the parcel, with no scan of its own, the card says "Announced to …, not received yet" instead. With a number but no company Peek knows, Peek asks your country's post for a postal number, or the one carrier the number's shape points to. Once that company has a scan at least as recent as GlobalPost's newest, Peek follows the parcel there and keeps GlobalPost's history.

Peek checks every 10 minutes, and every 2 minutes (every 5 when it has to open GlobalPost's tracker in a browser) from 6 a.m. local time on the day the parcel goes out for delivery, while GlobalPost's own tracker answers; through tracking services, every 15 minutes. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". Peek can't contact GlobalPost or the seller for you.

## Questions about GlobalPost tracking

### Why did my GlobalPost tracking stop after customs?

With GlobalPost Economy, tracking ends once the parcel reaches your country and clears customs, and GlobalPost says some shipments have limited tracking there. The parcel is still on its way and can arrive with no new line. If the tracker gives the delivery company's number, Peek asks that company, and so can you.

### Who delivers GlobalPost parcels?

A post or courier in your country. GlobalPost's tracker can name it, with that company's number for your parcel.

### How long does GlobalPost take to deliver?

GlobalPost says 5 to 6 days for nearby countries and up to 20 days for farther ones. It quotes sellers 10 to 14 business days for Economy, 6 to 10 for Standard and 5 to 8 for Plus, as an estimate from when its shipping centre has processed the parcel.

### Is GlobalPost legit?

Yes. GlobalPost is part of Auctane and is sold through shipping platforms such as ShipStation and Stamps.com. It ships to more than 220 countries and says it has delivered more than 60 million shipments.

### How do I contact GlobalPost?

Email info@goglobalpost.com or call (888) 899-1255, weekdays from 6 am to 6 pm Pacific time. If the parcel hasn't arrived 30 days after shipping, GlobalPost asks you to go to the seller first.

:::sources
- [GlobalPost: FAQs](https://www.goglobalpost.com/faqs/) – Auctane, how it works, tracking, delays, delivery times, returns, claims
- [GlobalPost: Standard](https://www.goglobalpost.com/standard/) – services compared: tracking, duties, transit times
- [GlobalPost: Contact Support](https://www.goglobalpost.com/contact-support/) – email, phone, hours, form, China Post
- [GlobalPost: Home](https://www.goglobalpost.com/) – countries, shipments delivered
- [GlobalPost: Track your package](https://www.goglobalpost.com/tracking/) – the tracker
- [GlobalPost: Terms and Conditions](https://www.goglobalpost.com/conditions/) – shipping centres, transit time as an estimate
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty per item up to €150, €2 Union handling fee, dates
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item whatever the value
- [Universal Parcel Scraper: GlobalPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/globalpost) – number shapes, wordings, delivery partner, returns, clocks
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – USPS number shape, hand-off to the delivery company
:::
