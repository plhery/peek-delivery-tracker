---
title: Whistl tracking: who delivers your parcel and what to do
description: Track a Whistl parcel: where the reference comes from, what each status means, which carrier delivers it after Whistl and who to ask when it goes wrong.
slug: whistl-tracking
published: 2026-10-13
updated: 2026-10-26
---

Whistl, formerly TNT Post UK, is a UK delivery company that ships parcels for shops and hands them to carriers such as [Royal Mail](carrier:royal-mail), Evri or DPD for the last mile. Track it on Whistl's Parcelhub tracker with the reference from the shop's dispatch email or tracking link, or paste its tracking link in the box above. Once the parcel is handed over, the delivering carrier's own number and tracking usually tell you more.

## Whistl tracking number: where to find it

Whistl publishes no number format. Its guides say the tracker searches "by UPI (typically the item barcode) or Consignment Number": the UPI identifies one parcel, a consignment is everything sent to you together. References vary by shop and carrier, from a letter and many digits to the delivering carrier's own barcode.

You find it in the shop's dispatch email, often as a link to Whistl's tracker. For fully tracked parcels, the delivering carrier also emails and texts you on the day of delivery, under its own brand and with its own number and tracking.

The tracker asks for a "Tracking Reference" and an optional postcode, then shows each parcel's "Tracking Number:", its scans and the "Carrier:" and "Service:" it was booked with.

A reference can have another carrier's shape, such as the delivering carrier's own barcode:

| Number shape (invented) | Whose it looks like |
| --- | --- |
| `RR000000005GB` | Royal Mail (two letters, nine digits, `GB`) |
| `H00ABC0000000000` | Evri's 16-character barcode, see [Evri tracking](carrier:evri) |
| `00000000000000` | 14 digits: DPD UK among several others, see [DPD UK](carrier:dpd-uk) |
| `ZDBABC123456` | XDP, see [XDP tracking](carrier:xdp) |

More on reading a number in [which carrier a tracking number belongs to](guide:tracking-number-formats).

## Whistl tracking statuses explained

Whistl's tracker, in English only, turns every carrier's scans into the same "normalised events"; fully tracked parcels also show the carrier's comments. In the order a parcel meets them:

| Whistl says | What it means for you |
| --- | --- |
| `Shipment Created` | The shop has booked it. Nothing has moved yet. |
| `Item receipted and processed by Whistl` | Whistl has it at one of its depots. |
| `Handed over to Delivery Carrier` | The carrier named on the page now delivers it. |
| `Handed over to Royal Mail` | Royal Mail now has it for the last mile. |
| `Arrived At Depot` | At a depot on the way. |
| `Shipment is Out for Delivery` | Out for delivery today. |
| `DELIVERED - Left as authorised` | Left where you allowed, without a signature. |
| `Left with Neighbour` | Delivered to a neighbour. |
| `Delivered col. point` | At a collection point, waiting for you. |
| `No-one to receive` | Nobody was in: look for a card or a message from the carrier. |
| `Held by customs` | [Customs](guide:customs) is holding it. |
| `Return - Delivered` | It went back and the sender has it. |

Three that confuse people:

- **`Shipment Created` on its own** only means booked. If nothing follows for days, the shop may not have sent it yet.
- **`Handed over to …`** is often the last thing Whistl shows. For part-tracked items Whistl says it is "not possible to track items after handover". Follow the parcel with the delivering carrier's number instead.
- **Problems** such as `Delayed`, `Bad Address`, `Refused`, `Lost` or `Item damaged` mean something has gone wrong; `Shipment Deleted` means the sender cancelled it. Lines like `Item Weight Changed` or `Label reprinted` are notes and don't move the parcel.

## Whistl parcel delivery and missed deliveries

The delivering carrier, not Whistl, brings the parcel, and its rules apply. After a missed delivery, the carrier leaves a card or electronic notice saying who called and when it will try again.

Whistl's Parcelhub carrier guide (August 2025) lists, per carrier:

| Carrier | Attempts and what happens next |
| --- | --- |
| Royal Mail | 1 attempt and a "something for you" card; pick it up from a delivery office or post office, or it goes back after 18 days. Since May 2023, if a parcel won't fit the letterbox or needs a signature and you're out, it leaves it safe or with a neighbour. |
| Evri | Up to three attempts; set preferences in the Evri app; 2-hour window by email or text. |
| DPD | Card plus text or email to choose a neighbour, safe place, new date or pickup shop; one-hour window. |
| Yodel | Up to three attempts; offshore addresses may get only one before you're asked to pick it up from a post office. |
| [Amazon Shipping](carrier:amazon-shipping) | Up to three attempts over 3 consecutive days. |
| DHL Parcel UK | Up to 2 attempts. |
| [Parcelforce](carrier:parcelforce) | 1 attempt, with leave-safe and divert options. |
| UPS | 1 attempt, then a digital notification with redelivery instructions. |

Part-tracked items abroad go to international partners, so the local post's rules apply. Whistl has no pickup points of its own: `Delivered col. point` means the carrier's shop or locker. A refused parcel goes back to the sender and can't be redelivered.

Marked delivered but not there? Whistl's guides say to check the calling card, safe places and neighbours first. Then see [delivered but not received](guide:delivered-not-received).

## Whistl parcel not delivered, delayed or lost

Whistl's contact page tells the public to "contact the retailer from which you purchased it". The shop holds the contract, raises a "Where Is My Item" query with Whistl and makes any claim.

What Whistl's guides say:

- A UK parcel that can't be delivered goes back to the sender within 5 working days of reaching Whistl's network.
- For part-tracked items, no compensation is paid for delays, and Whistl can't help once an item has been handed over and not delivered.
- Senders' claim deadlines depend on the carrier, such as within 28 days of despatch for Evri, or for Royal Mail a wait of at least 10 calendar days before claiming and a claim within 80 days. Ask the shop early.

When tracking stalls, start with [why tracking stops updating](guide:tracking-not-updating).

## Whistl contact number and customer service

Whistl's contact page, read on 13 October 2026:

- **About your parcel:** the shop you bought from.
- **Tracking:** the "Whistl Tracking Portal" button on the contact page.
- **General enquiries:** 01628 702 965, between 8.30am and 5.30pm. This line serves Whistl's business customers.
- **Customer Services email:** packagescustomerservices@whistl.co.uk, which "cannot assist the public with item-related tracking queries".
- **Head office:** Network House, Third Avenue, Marlow, Buckinghamshire, SL7 1EY.

In Greece, Cyprus, Luxembourg and Malta, Whistl has no office or phone line. Ask the shop, and once the parcel is handed over, the carrier or local post named on the tracker.

## Tracking Whistl parcels with Peek

Whistl's references have no shape, so a typed number never names Whistl. Paste a Whistl link instead (web.parcelhub.net or despatch.whistl.co.uk `/Tracking/reference/…`, or trackmyitem.whistl.co.uk `/tracking/…`), or add the reference and pick "Whistl" with "Change". No postcode is needed. A reference shaped like another carrier's number, such as the delivering carrier's own (a Royal Mail `…GB` number, say), tracks that carrier, not Whistl.

[Peek](/) reads Whistl's Parcelhub tracker. Its times carry no time zone: Peek reads them on the clock of the scan's place, UK time for scans in the UK, and lists Whistl's own history. Peek can show the service and the carrier that delivers it with that carrier's number, and can notify you when the status changes. There's no expected delivery day, weight or pickup point. Statuses keep Whistl's English wording, except plain `Delivered`, `In transit` and `Returned to sender`, shown in Peek's words.

When Whistl names Royal Mail, Parcelforce Worldwide, DPD UK, DHL Parcel UK, Amazon Shipping, [InPost](carrier:inpost), [UPS](carrier:ups) or [FedEx](carrier:fedex), Peek can follow the parcel there. A parcel named only as Evri, DPD or Yodel isn't followed: add the carrier's own number, or use Yodel's site, as Peek can't track Yodel. A reference covering several parcels isn't tracked: add each parcel number.

## Questions about Whistl tracking

### Is Whistl part of Royal Mail?

No. Whistl UK Ltd is a separate company, the former TNT Post UK. It hands many items to Royal Mail for the last mile, which is why `Handed over to Royal Mail` appears.

### Who delivers Whistl parcels?

Usually another carrier: DPD, Evri, Yodel, Royal Mail, Amazon Shipping, DHL Parcel UK, Parcelforce or UPS in the UK, and partners abroad.

### Is Whistl tracking legit?

Yes. Whistl's own tracking runs on web.parcelhub.net, despatch.whistl.co.uk and trackmyitem.whistl.co.uk, and Parcelhub is part of the Whistl Group.

### Why does my Whistl tracking number not work?

The tracker answers "Unable to find shipment" when it doesn't know the reference. Check it with the shop, or track the delivering carrier's number from its text or email.

:::sources
- [Whistl: Contact Us](https://www.whistl.co.uk/contact) – public told to ask the retailer, phone and hours, Customer Services email, tracking portal, head office
- [Whistl: home page](https://www.whistl.co.uk/) – Whistl UK Ltd, business services, Parcelhub part of the Whistl Group
- [Whistl: Domestic Fully Tracked Customer Guide (October 2025)](https://www.whistl.co.uk/1817/6062/1711/Whistl_Domestic_Fully_Tracked_Customer_Guide_-_V2.2_October_2025.pdf) – UPI and consignment, normalised events, carrier texts and emails, missed delivery notice, refused, lost, 5 working days, sender's queries and claims
- [Whistl: Part Tracked Services Customer Guide v1.7](https://www.whistl.co.uk/download_file/force/3171/250) – handover to Royal Mail and partners, 18 days, no compensation for delay, tracking links, branded emails
- [Parcelhub: Domestic Carrier Guide (August 2025)](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Carrier_Guide_Domestic_-_V1.1_Aug_2025.pdf) – carriers used, attempts and options per carrier, sender claim deadlines
- [Parcelhub: Part Tracked Services Customer Guide V1.0](https://www.whistlparcels.co.uk/hubfs/Parcels/Documents/Parcelhub_Part_Tracked_Customer_Guide_-_V1_September_2024.pdf) – tracking portals, search by UPI or consignment, no help after handover
- [Parcelhub: Tracking](https://web.parcelhub.net/Tracking) – form fields, result layout, English only
- [Whistl Despatch: Tracking](https://despatch.whistl.co.uk/tracking) – same tracker under Whistl's name
- [Wikipedia: Whistl](https://en.wikipedia.org/wiki/Whistl) – rebrand from TNT Post UK, Royal Mail final mile
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads, recorded statuses, number detection
:::
