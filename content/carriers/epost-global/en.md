---
title: ePost Global tracking: EPG numbers and who delivers
description: Track an ePost Global parcel: what an EPG number looks like, what the lines on its tracker mean and how Peek follows the partner that delivers it.
slug: epost-global-tracking
published: 2026-11-02
updated: 2026-11-21
---

ePost Global is a US shipping company whose tracking numbers start with EPG. In the histories Peek's code holds, one of its processing centres in the United States scans the parcel in, then ePost Global hands it to a partner, a post or a courier, that takes it on. The shop or the sender gives you the number: paste it in the box above. ePost Global's own tracker is at epgtrack.com. Peek's catalog also knows ePost Global as PPX Track, and as RR Donnelley, RRD Donnelley or RRD International Logistics: searching any of these names when you pick a carrier in Peek finds ePost Global.

## ePost Global tracking number: what it looks like

Peek knows one shape for ePost Global: EPG and 10 to 15 digits. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `EPG482910573649182` | EPG and 15 digits: an ePost Global number. |
| `EPG4829105736` | EPG and 10 digits: also ePost Global's, the shortest Peek takes. |
| `EPG482910573` | Only nine digits: not ePost Global's shape. Three letters and nine digits fit [Purolator](carrier:purolator) and other carriers, TNT Australia among them, so Peek asks Purolator, [Colis Privé](carrier:colis-prive) and TNT Australia which one knows the number. If you choose ePost Global for it, Peek says it "doesn’t issue numbers like this one". Check for a missing digit. |
| `EPG4829105736491827` | 16 digits, one too many: Peek recognises no carrier. Check for an extra digit. |
| `LZ482910576US` | Two letters, nine digits and US: a US postal number. Peek names [USPS](carrier:usps) for most starting with C, E, L or R. To see ePost Global's own lines as well, add the EPG number. |

Spaces, dashes and lower case don't matter when you paste a number into Peek.

**Where to find it.** In the shop's shipping email or on your order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number) and [tracking number formats](guide:tracking-number-formats).

## ePost Global tracking statuses explained

ePost Global's tracker lists each line with a time, a wording and, mostly, a place. Its own lines, in the order a parcel meets them:

| ePost Global says | What it means for you | Peek shows |
| --- | --- | --- |
| `Data Received` | The shop has sent ePost Global the parcel's details. ePost Global doesn't have the parcel yet. | "Label created" |
| `Shipment in Transit to the ePost Global Processing Center` | Added a minute after the details, every time: part of the label, not a sign that the parcel is moving. | "Label created" |
| `Parcel Received and Processing` | One of ePost Global's processing centres has the parcel. The line gives the centre's town and state. | "Received by carrier" |
| `Parcel Shipped` | The parcel has left the processing centre. | "In transit" |
| `In transit to … tracking #: …` | Handed to the partner that carries it on, a minute after shipping. The line names the partner's service and gives the partner's own number for the parcel. It can also read `In transit via …` or `tracking#:`. | "In transit" |
| `In transit` | The same handover, without the service or the number. | "In transit" |

**The partner's lines.** After the handover, ePost Global's tracker passes on the partner's own lines. Those Peek's code knows:

| After the handover | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipping label created - OSM awaiting item` | OSM Worldwide, the partner, has the parcel's details. | no change |
| `Arrived at OSM facility`, `Departed OSM facility` | At an OSM Worldwide facility, then on its way out. | "In transit" |
| `Package accepted at courier partner`, `Package processed by courier partner` | OSM Worldwide's own delivery partner has the parcel. | "In transit" |
| `Parcel has arrived at export distribution warehouse` | [ECMS Express](carrier:ecms-express), the partner, has the parcel at its export warehouse. | "In transit" |
| `Customer Information Manifested` | DAI Post, the partner for New Zealand, has the parcel's details. | no change |
| `Checked in at origin hub` | At DAI Post's hub in the United States. | "In transit" |
| `Airline Delay` | The flight is late: a delay, not a problem with the parcel. | "In transit" |
| `Collected from the airline for processing` | Off the flight, in the destination country. | "In transit" |
| `Consignment received at the PostNL Acceptance Centre`, `The item has been manifested and ready to transport` | PostNL, through Spring, has the parcel and has listed it for transport. | "In transit" |
| `The item is tied to a MAWB and ready for shipment` | PostNL, through Spring, has added it to an air shipment. | "In transit" |
| `Your item has been dropped off by the sender with our postal partner in the country of origin.`, `Your item is ready to be forwarded from its foreign territory. It will be given to the carrier for forwarding.` | La Poste's notices for a parcel PostNL brings to France: it is still on its way there. | "In transit" |
| `The parcel has left the parcel center.`, `The parcel has reached the parcel center.` | GLS's European network: the parcel has left its hub, then reached a parcel centre in your country. | "In transit" |
| `The parcel is expected to be delivered during the day.` | GLS's European network: the parcel is out with the driver today. | "Out for delivery" |
| `The parcel has been delivered.` | GLS's European network: delivered. | "Delivered" |
| `Departed Hub`, `Shipment Received in Depot`, `Shipment Departed Depot` | For a parcel [Royal Mail](carrier:royal-mail) delivers, its partner in the UK: on its way to the UK, at a depot, before and after customs, then on to the delivery office. | "In transit" |
| `Arrived in customs` | The same partner: at UK customs, in the minute the depot scans the parcel in. | "At customs" |
| `Ready for Collection` | The same partner: the parcel waits for you at a collection point. | "Ready for pickup" |

A partner's receipt after `Parcel Shipped` counts as in transit, not as a new start. Peek reads other partner lines, such as a delivery, by their wording, with the rules it uses for every carrier. A line it doesn't know shows as written and counts as "In transit", unless the parcel is already out for delivery or past that point: then the status stays as it was.

## Who delivers ePost Global parcels

ePost Global says its postal and final-mile carrier relationships span six continents. In the histories Peek's code holds, its partners include OSM Worldwide, DAI Post for New Zealand, ECMS Express, GLS's European network, Royal Mail in the UK and [PostNL](carrier:postnl) through Spring, with [La Poste](carrier:la-poste) taking over in France after PostNL. When ePost Global names no partner but gives a postal number (two letters, nine digits, two letters), Peek asks the post of the destination country, such as [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [An Post](carrier:an-post), [Canada Post](carrier:canada-post) or [Australia Post](carrier:australia-post): see below.

**Delivery attempts and pickup.** Attempts and where a missed parcel waits follow the partner's rules: ask the partner, with its number. See [missed delivery](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

**Customs in the EU.** Goods bought online from outside the EU carry a €3 customs duty per item on parcels worth up to €150, since 1 July 2026, and a €2 Union handling fee per item, whatever the value, since 1 November 2026. An item is the goods sharing a tariff classification, description and, where required, origin. In the EU's VAT area, import VAT is due as well, unless it was charged at purchase through IOSS. ePost Global says DDP and DDU shipments owe the duty and the fee alike: under DDP the shipper pays the charges before the parcel arrives, so you pay nothing at delivery; under DDU you pay them on delivery. Germany no longer accepts DDU shipments, and ePost turned off DDU routing there on 26 October. Elsewhere, what you owe depends on your country. See [parcels held at customs](guide:customs).

## ePost Global parcel not updating, lost or returned

**Nothing yet.** When ePost Global's tracker has no update for the number, Peek shows "Waiting for ePost Global" and keeps checking. Check the number: EPG and 10 to 15 digits. If ePost Global still doesn't know the number after two days and no tracking service finds it either, Peek says: "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." If nothing comes after a few days, ask the shop whether the parcel was really sent.

**Nothing new after the handover.** The partner has the parcel, and its own tracking may be ahead of ePost Global's. Peek follows the parcel there when it can (see below). Otherwise, find the partner's name and number in the handover line.

**No update for days.** After four days without a new line, Peek flags the parcel with the day of its latest line ("No tracking update since Mon 12 Oct"), notes that parcels often go quiet on long flights, at customs or over a weekend (after three weeks with nothing new, it says to ask the seller instead), and adds a "Get help from ePost Global" link, or the partner's own once Peek follows the parcel with a partner whose help page it knows. See [why tracking stops updating](guide:tracking-not-updating).

**Delivered, but nothing came.** Ask the partner, then the shop. See [delivered but not received](guide:delivered-not-received).

**Lost or returned.** Ask the shop, or the marketplace, for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## ePost Global customer service and contact

On epostglobalshipping.com, ePost Global gives a toll-free number, +1 866-784-8444, and a local one, +1 310-784-8485, with no hours and no support email. Its online assistant, ePost Assist, is at epostassist.epgtrack.com, and its contact-support page has a support form. For a parcel that has stopped moving, Peek links ePost Global's contact-support page as "Get help from ePost Global".

For an order from a shop, ask the shop first: it booked the delivery. Once a partner has the parcel, ask the partner about the delivery itself.

## Track an ePost Global parcel with Peek

[Peek](/) names ePost Global at once from EPG and 10 to 15 digits ("Detected carrier"), and asks ePost Global's tracker by that number alone: no postcode or account is needed. A tracking link from ePost Global's tracker or from PPX Track works too: Peek takes the number from it, the first one if the link holds several. If all you have is the partner's number, paste it all the same: Peek follows more than 3,500 carriers.

Peek shows the status, each line with its place, the destination country, and the partner with its number when ePost Global gives them. It leaves out the tracker's progress bar, postcodes and air waybill numbers. ePost Global's tracker gives no expected delivery date, weight or proof of delivery, so Peek shows none. The link on the parcel's page, "Open the ePost Global website", opens the number on ePost Global's tracker.

The tracker prints times without a time zone. Peek reads `Parcel Received and Processing` and `Parcel Shipped` on the clock of the centre's state, when the whole state keeps one time, GLS's lines on Central European time and Royal Mail's on UK time, and other lines on the clock of their place or of the leg the parcel was on. Every time is shown in your device's time zone; on peektracker.com and in the iPhone and Android apps, the history ends with "Times are in your time zone:" and its name.

When ePost Global names a partner Peek knows, the parcel card says so ("Delivery with PostNL", for example, or "Announced to PostNL, not received yet" while that partner has only the announcement of the parcel, with no scan of its own) and lists the partner's number after the EPG number, if ePost Global gives one. Peek then asks that partner too: under its number, or under the EPG number if ePost Global gives none. With no partner named, Peek asks the destination country's post for a postal number, as above, and for another number the one carrier its shape points to. Peek asks only carriers whose own tracking it reads. Once that carrier shows a scan at least as recent as ePost Global's newest, Peek follows the parcel there and keeps ePost Global's history. Otherwise, Peek stays with ePost Global's tracker and its copies of the partner's lines. That is also the case for a parcel GLS's European network delivers: ePost Global doesn't say which national GLS company delivers it.

While you have the parcel open, or on its delivery day with notifications on, Peek checks up to every 10 minutes, and up to every 2 minutes from 6 a.m. local time on the day the parcel goes out for delivery, while ePost Global's own tracker answers (through tracking services, up to every 15 minutes); otherwise hourly. When another carrier takes over the delivery, Peek follows that carrier's pace. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". Peek can't contact ePost Global or the shop for you.

## Questions about ePost Global tracking

### What is PPX Track?

Another name Peek's catalog gives ePost Global. Peek reads a PPX Track tracking link as an ePost Global link and takes the number from it.

### What does "In transit to" mean on ePost Global tracking?

ePost Global has handed the parcel to the partner that carries it on. The line names the partner's service and, after `tracking #:`, gives the partner's own number for the parcel.

### Why is my ePost Global tracking stuck on "Data Received"?

The shop has sent ePost Global the parcel's details, but none of its processing centres has scanned the parcel yet. Ask the shop when it handed the parcel over.

### Who delivers ePost Global parcels in my country?

A partner, which ePost Global's handover line names. In the histories Peek's code holds, they include OSM Worldwide, DAI Post for New Zealand, ECMS Express, GLS's European network, Royal Mail in the UK, PostNL through Spring and La Poste in France.

### How do I contact ePost Global?

Ask the shop first: it booked the delivery. This page gives no phone number or email, but on a parcel that has stopped moving, Peek's "Get help from ePost Global" link opens ePost Global's contact-support page.

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty per item up to €150 since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item whatever the value, what an item is
- [Universal Parcel Scraper: ePost Global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/epost-global) – names, the EPG shape, tracking links, the tracker, wordings and their stages, partners' lines, clocks, the partner and its number, destination country, what is left out, help page
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – Purolator and USPS number shapes, hand-off to the partner or the post in your country
:::
