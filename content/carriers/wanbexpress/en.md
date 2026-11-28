---
title: WanbExpress tracking: WNB…YQ numbers, scans and who delivers
description: Track a WanbExpress (Wanb Express / WANB) parcel from China: WNB…YQ numbers, scan meanings, last-mile carriers (iMile, InPost, Poste Italiane) and customs.
slug: wanbexpress-tracking
published: 2026-10-30
updated: 2026-11-27
---

WanbExpress (Wanb Express, WanBang Express or WANB) is a Chinese cross-border carrier whose scans follow parcels out of China, through the flight and customs, before it hands them to local carriers such as iMile (Germany), InPost (UK) or Poste Italiane (Italy). Paste your 17-character `WNB…YQ` waybill in the box above or check `tracking.wanbexpress.com` (reCAPTCHA-protected) to see scans and the delivering carrier's tracking number.

## WanbExpress tracking number: what it looks like

WanbExpress's waybills are WNB, two letters, ten digits and YQ, 17 characters in all. With invented digits:

| Number | What it is |
| --- | --- |
| `WNBKG4829105736YQ` | WNB, two letters, ten digits and YQ: a WanbExpress waybill. |
| `WNBKG482910573YQ` | Only nine digits: not WanbExpress's layout. Check for a missing digit. |
| `JSEKG4829105736YQ` | Also ends in YQ, but opens with JSE: a [JS Express](carrier:js-express) number. |
| `JXCKG4829105736YQ` | JXC in front: a [Juxi Express](carrier:juxi-express) number. |

Several Chinese forwarders end their waybills in YQ, each with its own letters in front: WNB is what marks WanbExpress. Spaces, dashes and lower case don't matter when you paste the number into Peek.

**Where to find it.** The shop gives the number in its shipping email or on your order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**A tracking link.** A link to WanbExpress's tracker that ends in `?trackingNumbers=` and a number opens that one parcel. The WNB number is the part after the equals sign: copy it from there into Peek.

**A second number.** WanbExpress's tracker can also show the number the delivering carrier uses for the parcel. That number belongs to the other carrier: look it up on that carrier's tracking, or paste it into Peek as a parcel of its own.

## WanbExpress tracking statuses explained

WanbExpress's scans are in English, each with a time and sometimes a place. Here they are in the order Peek's code lists them:

| WanbExpress says | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipping Order Created.`, `Parcel Data Received` | The shop has booked the parcel and sent its details. WanbExpress doesn't have the parcel yet. | "Label created" |
| `Parcel received at Shenzhen Sorting Centre.` | The sorting centre in Shenzhen, China, has the parcel: the first scan of the parcel itself. | "Received by carrier" |
| `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Sorted, then on its way out of that centre. | "In transit" |
| `Customs declaration completed` | A customs declaration for the parcel is done. The wording doesn't say which country's customs, and it isn't the clearance: that's `Custom clearance completed`. | "In transit" |
| `Parcel departure in Transit Facility, Parcel Departure` | It has left a transit facility. | "In transit" |
| `Parcel is leaving Airport, Parcel Leaving Port` | Leaving on the flight to the destination country. | "In transit" |
| `Parcel arrives at Airport, Parcel Arrival` | Landed in your country, or near it. Customs come next. | "In transit" |
| `Custom clearance completed` | Import customs have let it through. | "In transit" |
| `Parcel received by in JFK01.` | WanbExpress files it as a receipt for the last leg. The wording leaves out who received it. | "In transit" |
| `Your package is being prepared by the sender and will be handed over to us soon`, `Your parcel will soon be handed over to us! It is being prepared by the sender.`, `Order information received. We're expecting your parcel to arrive with us.` | A partner's notice in its own words, passed on without a WanbExpress code: the parcel hasn't been handed over to it yet. | "Label created", or a later stage if a scan has already moved the parcel |
| `In transit along Poste Italiane’s logistic network` | On its way to you through the delivering carrier's network, here [Poste Italiane](carrier:poste-italiane)'s. | "In transit" |
| `Parcel is out for delivery.` | On the delivery run. | "Out for delivery" |
| `Consignee absent, Consignee could not be found.` | The delivering partner's run found no one. | "Delivery attempted" |
| `Placed in Parcelshop/Parcel locker` | After the missed run, the partner left the parcel at a parcel shop or locker for you to collect. | "Ready for pickup" |
| `Parcel has been delivered.(Front door)` | Delivered. `(Front door)` says where it was left. | "Delivered" |

**The delivering carrier's scans.** After the flight, WanbExpress's tracker passes on the delivering carrier's own scans in that carrier's words, mostly without a WanbExpress code. On a parcel to Germany, WanbExpress's history shows [iMile](carrier:imile) delivering, each iMile line after the name of its centre or station in 【brackets】:

| iMile says | What it means for you | Peek shows |
| --- | --- | --- |
| `Received.` | iMile's centre in the destination country has the parcel. | "In transit" |
| `Arrived.` | At the delivery station, the depot that delivers your area. | "In transit" |
| `Dispatch task assigned to DA.` | A driver (DA, delivery associate) is named for the run, which can be hours before it starts. | "In transit" |
| `Shipment out for delivery.` | On the delivery run. | "Out for delivery" |
| `<Route not completed>`, `Back To DS.` | The run ended without the delivery, and the parcel went back to the delivery station (DS). | "Delivery attempted" |
| `Scheduled to <2026-11-02> .` | The day booked for the next run, after the parcel came back to the station. | Still "Delivery attempted" |

Other partners' moves come through the same way. On parcels to the United States, `Parcel received by Transit Facility.` is the last-mile partner's receipt at a transit facility, `In Processing at Transit Facility.` its sort once it has the parcel, `In Transit to Next Facility.` its move towards the facility that delivers, and `Parcel received by local Facility.` that facility taking the parcel in before the delivery run. `It is currently in transit to the next facility` and `The package is being transferred to the next facility` are a partner's moves between its facilities after the flight, and `Shipment arrived to sorting centre / Shipment in the sorting centre` is a partner's sorting centre taking the parcel in before its run. Peek shows all seven as "In transit". On a parcel to the UK, [InPost](carrier:inpost)'s lines can come through before WanbExpress's sorting centre has scanned the parcel: `Your parcel is on its way to InPost. Please check back for updates`, which Peek shows as "Label created", then `We have your parcel. Please check back for updates`, "Received by carrier". `We'll apply your My Account preferences to this parcel delivery` is InPost's note that the recipient's account settings apply, not a scan: Peek keeps it in the history, but it doesn't change the stage. Nor does `Consignee Contacted`, a partner's courier calling you, logged at the same minute as a run that found no one.

**Times.** Each scan's time comes with its offset from UTC. Peek reads them that way and shows them all in your device's time zone, newest first. For the gaps and handovers that parcels from China go through, see [tracking a parcel from China](guide:tracking-from-china).

## Who delivers WanbExpress parcels

WanbExpress's scans follow the parcel out of China, through customs and on to a local carrier, which delivers it. WanbExpress's tracker can name that carrier, give its number for the parcel and show a delivery photo. Peek leaves out the delivery photo. When it can tell whose the delivering carrier's number is, it shows "Delivery with …" and follows that carrier; otherwise, look at WanbExpress's tracker, or ask the seller.

**What Peek's code shows.** On a parcel to Germany, WanbExpress's history shows iMile delivering, and one WanbExpress scan names Poste Italiane's network. On a parcel to the UK, InPost's lines come through.

**Delivery attempts and pickup.** Whoever delivers, its own rules apply: how many attempts it makes, whether it leaves a card, where a missed parcel waits and for how long. Peek found no WanbExpress rules for any of these. Once you know the carrier and its number, check its own tracking and ask it how to rearrange a delivery. See [missed delivery](guide:missed-delivery).

## WanbExpress parcel not arriving or tracking stuck

**Number not found.** If WanbExpress's tracker doesn't know the number, Peek shows "Waiting for WanbExpress" and keeps checking. If WanbExpress still doesn't know the number two days later and nothing else has answered for it, Peek shows "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." Check the number against the seller's message, character by character: WNB, two letters, ten digits, YQ. If nothing comes after a few days, ask the seller whether the parcel was really sent.

**Stuck on `Shipping Order Created.` or `Parcel Data Received`.** The shop has booked the shipment, but WanbExpress hasn't scanned the parcel yet. Ask the shop when it handed the parcel over.

**Stuck after `Parcel arrives at Airport, Parcel Arrival`.** The parcel is most likely waiting for import customs. Into the EU (Germany or Italy, for example), goods bought online owe €3 customs duty per kind of product on parcels worth up to €150 and, since 1 November 2026, a €2 Union handling fee per kind of product, whatever the value. If you're asked to pay import charges, see [parcels held at customs](guide:customs); if it sits there for long, tell the seller. More in [why tracking stops updating](guide:tracking-not-updating).

**Nothing after `Custom clearance completed`.** The delivering carrier has the parcel, or soon will, and its own tracking may be ahead of WanbExpress's. Find its name and number on WanbExpress's tracker, or ask the seller which carrier delivers and under which number.

**Delivery attempted.** After a line such as `<Route not completed>`, the delivering carrier decides when it tries again and where the parcel waits. Ask that carrier, not WanbExpress.

**Delivered, but nothing came.** Look for a delivery photo on WanbExpress's tracker, then ask the delivering carrier and the seller. More in [delivered but not received](guide:delivered-not-received).

**Lost or returned.** The seller booked the parcel with WanbExpress, so the seller, or the marketplace, is the one to ask for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## WanbExpress contact number and customer service

Peek couldn't check WanbExpress's website, so this page lists no contact details for WanbExpress. The seller booked the parcel with WanbExpress and is the one to ask about your order, and the marketplace if the seller doesn't answer. For the delivery itself, ask the delivering carrier.

## Track a WanbExpress parcel with Peek

[Peek](/) recognises a WanbExpress number by its shape: WNB, two letters, ten digits and YQ, which no other carrier in its catalog uses, and asks WanbExpress's tracker by that number only. No account, postcode or phone number is needed.

WanbExpress's tracker won't answer until Google's reCAPTCHA has checked that a person is asking, so Peek asks it from a browser. When the tracker still won't answer, Peek turns to tracking services and shows the history they hold, in their words. That history can lag behind WanbExpress's own.

From WanbExpress's tracker, Peek shows the status, each scan with its place, and the origin and destination countries on WanbExpress's record. When that record marks the parcel in transit, out for delivery or delivered, Peek goes by it, even if the last scan's words say less. Peek shows no expected delivery date.

Peek leaves out the delivery photos and the recipient's address that WanbExpress's tracker can show. When WanbExpress's tracker gives the delivering carrier's number and Peek can tell whose it is, Peek can show "Delivery with …" and follow the parcel on that carrier's tracking; otherwise, for that carrier's own scans, add its number to Peek as well. Peek follows more than 4,500 carriers.

While you have the parcel open, or on its delivery day with notifications on, and WanbExpress's tracker answers, Peek checks up to every 10 minutes, and up to every 2 minutes from 6 a.m. on WanbExpress's clock on the day the parcel goes out for delivery (every 5 when the check opens a browser); otherwise hourly. While tracking services answer instead, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on that day while you have it open or notifications are on. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of every scan, important steps only or "Delivery only". "Important steps" (delivery, pickup, customs, missed attempts and returns) covers the delivery run, a missed attempt and the delivery, but not WanbExpress's customs lines, which Peek counts as "In transit". Peek can't contact WanbExpress or the seller for you.

## Questions about WanbExpress tracking

### What is WanbExpress (Wanb Express / WANB)?

A Chinese cross-border carrier, also called Wanb Express, WanBang Express or WANB. In Peek's code, its scans begin at a Shenzhen sorting centre, cover the flight and customs, and continue with the local carrier's scans in your country.

### Where can I track a WanbExpress WNB…YQ parcel?

Paste the full 17-character `WNB…YQ` number (`WNB`, two letters, ten digits, `YQ`) in the box above or on tracking.wanbexpress.com, which uses Google reCAPTCHA and can also show the delivering carrier's name and number.

### What does "Customs declaration completed" mean on WanbExpress?

That a customs declaration for your parcel is done — it isn't import clearance yet, which appears as `Custom clearance completed`. Peek shows both lines as "In transit".

### Who delivers WanbExpress parcels in Germany, the UK, Italy or the US?

A local carrier that WanbExpress's tracker can name alongside a second tracking number. Peek's code shows iMile in Germany, InPost lines in the UK, Poste Italiane's network in Italy and local US transit/delivery facilities; check WanbExpress's tracker or ask the seller.

### Who do I contact about a stuck or missing WanbExpress parcel?

Contact the seller or marketplace first, as the shop booked the shipment with WanbExpress; for last-mile delivery problems, contact the local carrier once you have its tracking number.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – names, WNB number shape, the tracker, its reCAPTCHA check and the link that opens one parcel, scan wordings and their stages, iMile's relayed scans on a parcel to Germany and its next-run line, the US last-mile partner's and other partners' relayed moves, InPost's relayed lines on a parcel to the UK and its account-settings note that moves nothing, the scan naming Poste Italiane's network, times with UTC offsets, what the tracker shows (delivering carrier, its number, delivery photos, recipient's address), what Peek keeps and leaves out, and the tracking services it turns to
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty per kind of product up to €150 since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per kind of product like the €3 duty, whatever the value
:::
