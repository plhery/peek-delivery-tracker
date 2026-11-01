---
title: Pitney Bowes tracking: UPAA, UPBE and PBX numbers explained
description: Track a Pitney Bowes parcel, such as an eBay order sent abroad: what UPAA, UPBE and PBX numbers look like, what its tracker's lines mean and who delivers it.
slug: pitney-bowes-tracking
published: 2026-11-06
updated: 2026-11-06
---

Pitney Bowes ships parcels to other countries: in Peek's code, its numbers belong to eBay parcels from the United States and the United Kingdom, and to its other parcels going abroad. In the histories Peek's code holds, the parcel goes through a Pitney Bowes shipping centre, then on to the buyer's country, where a partner, such as that country's post, delivers it. The number starts with UPAA, UPBE or PBX and comes from the seller or the marketplace: paste it in the box above. Pitney Bowes's own tracker is at trackpb.shipment.co.

## Pitney Bowes tracking number: what it looks like

Peek knows three shapes for Pitney Bowes, each 14 to 20 characters long. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `UPAAA000000000000000` | UPAA, one letter and 15 digits: in Peek's code, an eBay parcel from the US or the UK. |
| `UPBE0000000000000000` | UPBE and 16 characters, digits or the letters A to F: an eBay parcel from the UK. |
| `PBXAA000000000` | PBX, two letters and 9 digits: another Pitney Bowes parcel going abroad. |
| `PBXAA000000000000000` | PBX, two letters and 15 characters, digits or the letters A to F: the same, in its longer form. |
| `UPAA000000000000000` | UPAA without its letter: not Pitney Bowes's shape, and Peek recognises no carrier. If you choose Pitney Bowes for it, Peek says it "doesn’t issue numbers like this one". Check for a missing character. |

Spaces, dashes and lower case don't matter in Peek. Its catalog also knows Pitney Bowes as Pitney Bowes Global Ecommerce, Pitney Bowes Expedited Parcel Service, eBay Global Shipping Program and PBI.

**Where to find it.** In the seller's shipping email or on your order page. For eBay, see [eBay orders](guide:ebay-orders); otherwise, [how to find your tracking number](guide:find-tracking-number).

## Pitney Bowes tracking statuses explained

Pitney Bowes's tracker lists each line with a date, a time and, mostly, a place, newest first. Its lines carry no code: Peek reads their wording. In the order a parcel meets them:

**At the shipping centre.** Pitney Bowes's own lines:

| Pitney Bowes says | What it means for you | Peek shows |
| --- | --- | --- |
| `Tracking details uploaded`, `Label Created` | The seller has sent Pitney Bowes the parcel's details. Pitney Bowes doesn't have the parcel yet. | "Label created" |
| `Package not received by Hub or First Mile Carrier yet` | The label exists, but neither the shipping centre nor the carrier bringing the parcel there has scanned it. | "Label created" |
| `Processing at Shipping Center` | Pitney Bowes's shipping centre has the parcel and is processing it. | "In transit" |
| `First Dimension Event`, `Second Dimension Event` | The centre measuring the parcel. | "In transit" |
| `Parcel Documentation and Labeling`, `Customs Documentation and Labeling` | The centre adds export paperwork and labels. Not customs itself. | "In transit" |
| `Container Assignment`, `Container Closed` | Put in a container for the journey, then the container is closed. | "In transit" |
| `Shipped from Global Shipping Center to Destination`, `Departed for Destination` | It has left the centre for the destination country. | "In transit" |
| `International Carrier` | Handed to the carrier that takes it abroad. | "In transit" |
| `In Transit` | On its way. It can come with no place. | "In transit" |

**Onward, customs and the hand-over.** In the histories Peek's code holds, parcels leaving Pitney Bowes's UK centre go on with Interpost, a partner that lodges the import declaration and hands them to the destination country's post. The lines of this leg, the first two written with no place as the UK centre processes the parcel:

| Pitney Bowes says | What it means for you | Peek shows |
| --- | --- | --- |
| `Item processed at Interpost` | The UK centre is processing the parcel for Interpost. | "In transit" |
| `Interpost handed item over to postal company` | Interpost's hand-over to the destination country's post, which delivers the parcel. It comes at the same moment as the line above, while the parcel may still be at the centre. | "In transit" |
| `Customs Clearance` | The import declaration, lodged in the destination country. Paperwork: it can come within hours of the parcel leaving the UK, before the parcel is there. | "At customs" |
| `B2C ClearanceStatus4` | Cleared by import customs. Peek writes it `Customs import cleared`, as Interpost's own tracker captions that code. | "In transit" |
| `B2C ClearanceStatus` and another number | A clearance step Peek's code doesn't know the meaning of. Peek writes it `Customs clearance update`. | "At customs" |
| `Cleared customs` | Through customs. | "In transit" |
| `Package is created in the system` | The next carrier opening its record for the parcel. It moves nothing. | no change |

**The post's lines.** After the hand-over, the tracker passes on the delivering post's scans:

| Pitney Bowes says | What it means for you | Peek shows |
| --- | --- | --- |
| `Item in transit by postal company` | The post is moving the parcel towards you. | "In transit" |
| `Parcel is out for delivery` | On the delivery round. | "Out for delivery" |
| `Item is waiting for addressee at local post office` | Waiting for you at your local post office: go and collect it. | "Ready for pickup" |
| `Delivered by Consignee Pickup` | You, the addressee, collected it. | "Delivered" |
| `Item has been delivered` | Delivered. | "Delivered" |

`Departed carrier facility` and `Processed by Carrier` also show as "In transit". Other lines go by the rules Peek uses for every carrier; one it doesn't know shows as written and counts as "In transit", unless the parcel is already out for delivery or past that point: then the status stays as it was.

**The overall status.** The tracker also gives the parcel an overall status. When it says delivered, Peek shows "Delivered" even if the newest line doesn't. Its other statuses count only while no line has one. Its problem status, which has turned up on a parcel the addressee simply collected, never overrides a line.

## Who delivers Pitney Bowes parcels

Peek couldn't read Pitney Bowes's website, so this page has no list of its partners or countries. Peek's code shows Interpost taking parcels on from the UK centre and handing them to the destination country's post, and one history shows [Poste Italiane](carrier:poste-italiane) delivering in Italy. Pitney Bowes's tracker names the carrier that takes the parcel on and can link a line to that carrier's own tracking page, with its number for the parcel.

**Delivery attempts and pickup.** Attempts, notice cards and how long a missed parcel waits follow the delivering post's rules: ask the post, or read its page on Peek if it has one. See also [missed delivery](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

**Customs.** Whether you pay anything on arrival depends on your country and on what the seller or the marketplace charged at checkout: ask them first. Peek couldn't check how Pitney Bowes handles import charges. See [parcels held at customs](guide:customs).

## Pitney Bowes parcel not updating, lost or returned

**Nothing yet.** For a number it doesn't know, or a parcel whose history has expired, Pitney Bowes's tracker gives the answer it gives when it can't read a parcel. Peek can't tell that from an outage: it asks tracking services, and if none has the parcel, it shows "Update unavailable" after two checks. It keeps checking. Check the number against the table above. If nothing comes after a few days, ask the seller whether the parcel was really sent.

**Stuck on `Package not received by Hub or First Mile Carrier yet`.** The seller has made the label, but neither Pitney Bowes's shipping centre nor the carrier bringing the parcel there has scanned it. Ask the seller when they sent it.

**Stuck at `Customs Clearance`.** The declaration is lodged, and the parcel may not even be in the country yet. Wait for the clearance line, which Peek shows as `Customs import cleared`. If it stays there for long, tell the seller.

**Nothing new once the post has it.** After the post's first lines, its own tracking may be ahead of what Pitney Bowes passes on, and Peek doesn't switch to it by itself. Open the post's page from the line on Pitney Bowes's tracker, or add the post's number to Peek as a parcel of its own.

**No update for days.** After four days without a new line, a parcel showing "Label created" or "In transit" gets a flag ("No tracking update for four days"), and Peek notes that parcels often go quiet on long flights, at customs or over a weekend. See [why tracking stops updating](guide:tracking-not-updating).

**Delivered, but nothing came.** Check where it was left and ask neighbours, then the delivering post and the seller. See [delivered but not received](guide:delivered-not-received).

**Lost or returned.** Ask the seller, or the marketplace, for a refund or a new parcel: they booked the delivery. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## Pitney Bowes customer service and contact

As of 1 November 2026, Peek couldn't read Pitney Bowes's website, so this page lists no phone number or email: Peek gives only contacts read on a carrier's own pages.

Pitney Bowes ships the parcel for the seller or the marketplace, so ask them first: for an eBay order, through eBay. Once the post has the parcel, ask the post about the delivery itself.

## Track a Pitney Bowes parcel with Peek

[Peek](/) names Pitney Bowes at once from a UPAA, UPBE or PBX number ("Detected carrier") and asks its tracker by that number alone: no postcode or account. A tracking link from trackpb.shipment.co or parceltracking.pb.com works too. If all you have is the seller's first-leg number or the post's number, paste it all the same: Peek follows more than 3,500 carriers. Picking Pitney Bowes with "Change" for another shape doesn't help: Peek says it "doesn’t issue numbers like this one".

Peek shows the status, each line with its place, and the weight in kilograms (the tracker gives pounds and ounces). It leaves out the next carrier's link and number. The tracker gives no expected delivery date or proof of delivery, so Peek shows neither. "Open the Pitney Bowes website", on the parcel's page, opens the number on Pitney Bowes's tracker.

Peek doesn't hand the parcel over to the post: the post's scans reach you through Pitney Bowes's tracker. When that tracker can't be reached or can't read the parcel, Peek asks tracking services and shows their history, in their own words.

The tracker prints each time on the clock of whoever scanned the parcel, with no time zone. Peek reads each line on the clock of its place, or of the leg the parcel was on. Without a place, `In Transit` and `Item processed at Interpost` are on UTC, `Interpost handed item over to postal company` on UTC+2, and `Customs Clearance` on the clock of the country it names. Every time is shown in your device's time zone.

Peek checks every 10 minutes, and every 2 minutes once the parcel is out for delivery. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". Peek can't contact Pitney Bowes, the seller or the post for you.

## Questions about Pitney Bowes tracking

### What is a UPAA tracking number?

A Pitney Bowes number: UPAA, one letter and 15 digits. Peek's code reads UPAA numbers as eBay parcels from the US and the UK, UPBE numbers as eBay parcels from the UK, and PBX numbers as Pitney Bowes's other parcels going abroad. Its catalog also lists eBay Global Shipping Program as another name for Pitney Bowes.

### What does "Interpost handed item over to postal company" mean?

It's the hand-over of your parcel by Interpost, which takes parcels on from Pitney Bowes's UK centre, to the destination country's post, which delivers it. Pitney Bowes writes it as the UK centre processes the parcel, so it can come before the parcel has left. The post's scans follow later on Pitney Bowes's tracker.

### Why is my Pitney Bowes tracking stuck on "Customs Clearance"?

It's the import declaration, which can be lodged within hours of the parcel leaving the UK, before it arrives. Wait for the clearance line, `Customs import cleared` in Peek. If nothing comes for long, ask the seller.

### How do I contact Pitney Bowes about my parcel?

Ask the seller or the marketplace first: they booked the delivery. Peek couldn't read Pitney Bowes's website, so this page gives no phone number or email.

:::sources
- [Universal Parcel Scraper: Pitney Bowes](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pitney-bowes) – names, the three number shapes and what they cover, tracking links, the tracker, its wordings and their stages, Interpost's lines and clearance code, the post's relayed lines, Poste Italiane on a parcel to Italy, the overall status, clocks, weight, what Peek leaves out
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – no hand-off to the delivering post, tracking services as a fallback
:::
