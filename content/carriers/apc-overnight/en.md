---
title: APC Overnight tracking: numbers, statuses, missed deliveries
description: Track an APC parcel: the 7-digit consignment number and the 22-digit one, what Carded means, rearranging a missed delivery and how to reach your depot.
slug: apc-overnight-tracking
published: 2026-10-13
updated: 2026-10-26
---

APC Overnight, now calling itself The APC, is the UK's largest independent parcel network: more than 90 locally owned depots that mostly deliver on the next working day. Track a parcel on APC Choice with the consignment number and your postcode, or paste the long number or the tracking link in the box above. You'll find the number in the sender's dispatch confirmation, in an email or text from APC or the retailer, or on APC's "Sorry we missed you" card.

## APC tracking number: what it looks like

APC talks about one number: "A consignment number is the 7–digit tracking reference assigned to your parcel." Its tracking form asks for that number and the delivery postcode together.

Shops often send a longer one instead: a 22-digit number inside an APC Choice link (apcchoice.apc-overnight.com/track-parcel?id=…). It starts with the booking date and ends with the same 7-digit consignment number.

:::anatomy 20261231 0000000 1234567
- 20261231 | The booking date, 20YYMMDD: here 31 December 2026
- 0000000 | Seven more digits
- 1234567 | The 7-digit consignment number APC's pages ask for
:::

With the long number in the link, APC Choice opens the parcel's history without a postcode. The postcode is needed only to see the proof of delivery or to change the delivery.

Two things to watch:

- A 22-digit number that doesn't start with a real date (`1234567890123456789012`, for example) isn't APC's. Numbers of that length can belong to [USPS](carrier:usps), [Austrian Post](carrier:austrian-post) or Estafeta.
- APC's numbers don't look like [Royal Mail's](carrier:royal-mail) (ending in `GB`), [Parcelforce's](carrier:parcelforce), [Evri's](carrier:evri) or [DPD UK's](carrier:dpd-uk).

If nothing comes up, APC's FAQ lists the usual reasons: the sender hasn't dispatched the parcel yet, it hasn't been scanned into the network, or the number was mistyped. Try later or check the number with the sender. No number at all? See [how to find a tracking number](guide:find-tracking-number).

## APC tracking statuses explained

APC Choice is in English only. It shows a consignment summary (dispatch date, weight, service such as next business day), then each parcel's "Parcel journey" and "Delivery summary", one line per scan with the day and time in brackets.

| APC says | What it means for you |
| --- | --- |
| `At sending depot` | The sender's local depot has collected the parcel and scanned it in. |
| `At Hub` | At APC's central hub, the National Sortation Centre in Cannock. |
| `At delivery depot` | At the local depot that will bring it to you. |
| `Out for delivery` | On the van today. APC gives a 2-hour estimated time slot. |
| `Carded` | The driver tried, nobody was in, and a "Sorry we missed you" card was left. |
| `Delivery Rearranged By Customer` | You (or someone at the address) changed the day or option in APC Choice. |
| `Delivered` | Delivered. The proof of delivery sits behind your postcode. |
| `Returned To Sender` | On its way back to the shop, for example after missed deliveries and no reply. |

`Delivery Rearranged By Customer` isn't a movement: the parcel stays where it was until the new day. When tracking goes quiet between scans, APC says it may be in transit between depots, waiting to be processed at your depot, or the driver's scanner may have a problem. See [tracking that isn't updating](guide:tracking-not-updating) for when to worry.

## APC delivery times, missed deliveries and depot collection

APC says most UK deliveries arrive on the next working day after dispatch, depending on location. The sender picks the service: next day by 10:30am, by 12 noon or standard next working day; Saturday delivery is available in many areas if the sender arranges it. If the shop gave APC your contact details, you may get the 2-hour window by email or text once the parcel is scheduled, and APC Choice shows how close the driver is.

APC Choice lets you change things until the parcel is delivered, even after a card. Depending on the service and what the sender allows, you can:

- reschedule to a day that suits you;
- collect from your local APC depot;
- choose a safe place or a neighbour;
- have it sent to a Parcelly store (over 2,500 across the UK).

**Safe place:** APC may leave a parcel without a signature only with the sender's permission, or yours through APC Choice where allowed. Business addresses get no safe place or neighbour option, and attempts there start from 8am.

**After a card:** drivers can't try again the same day, and you can't collect from the depot that day either. You can reschedule once more after the first attempt. A rebooked delivery uses the same service; a timed or Saturday upgrade must come from the sender.

**Depot collection:** ask before 7pm for next-day collection, and bring proof of identity and proof of address. Check the depot's opening times first.

**Three working days:** if APC hears nothing within three working days of a missed delivery, the parcel may go back to the sender.

## Late, damaged or lost APC parcels

APC's contract is with the sender, so the shop handles claims. For damage, APC's own FAQ says: "Contact your sender directly." Tell the shop quickly, because its own deadlines with APC are short: for damage or a partly missing parcel it must write to APC within three business days after delivery. Keep the box and packaging, as APC can refuse a claim without them. APC's compensation to the sender is capped at £100 for a parcel consignment and £50 for smaller packs.

If tracking says delivered and you have nothing, the proof of delivery (photo, GPS point, date and time) is in APC Choice with your postcode. Then go to the shop, and see [delivered but not received](guide:delivered-not-received).

## Contacting APC

APC has no national phone line, chat or general email for recipients; its own pages list these channels (read on 13 October 2026):

- **Your local depot:** the [Depot Finder](https://apc.co.uk/depot-finder/) shows its contact details and opening hours from your postcode. APC Choice also names the delivering depot and its email under "APC Pinpoint".
- **Customer Service Enquiry form** on [apc.co.uk/contact-us](https://apc.co.uk/contact-us/): give the consignment number and the delivery postcode; APC forwards it to your local depot, which replies by phone or email.

> APC says it will never ask for payment or card details for a delivery. Don't click links in such a message: forward the email to phishing@apc-overnight.com and report it to Action Fraud.

## Tracking APC parcels with Peek

Paste the 22-digit number or an apcchoice.apc-overnight.com link with `id=` into [Peek](/); no postcode needed. Since the number's shape is shared, Peek asks APC Overnight and Austrian Post at once and shows "APC Overnight has this parcel." when APC knows it. A 22-digit number without a real date isn't offered to APC and isn't APC's: check it with the shop. The 7-digit consignment number on its own can't be tracked here, and apc.co.uk links aren't read.

APC's times carry no time zone: Peek reads them on UK time and lists APC's own history. It can also show the weight and the service, and can notify you when the status changes. `Delivered` and `Out for delivery` appear in Peek's own words; other statuses keep APC's wording. There's no expected delivery day, no 2-hour slot and no pickup point, and rearranging a delivery stays in APC Choice.

## Questions about APC tracking

### Where is my APC parcel?

At the last scan on APC Choice: the sending depot, the hub in Cannock, your delivery depot or the van. For anything more, your local depot knows; find it with the Depot Finder.

### How long do APC hold parcels?

APC may return a parcel to the sender if you don't respond within three working days of a missed delivery. Rearrange or book a depot collection before then.

### What time does APC deliver?

It depends on the service the sender paid for: by 10:30am, by 12 noon or any time on the next working day. When the parcel is out for delivery, APC Choice and APC's email or text give a 2-hour window.

### Is APC Parcelforce?

No. APC is The Alternative Parcels Company Limited, an independent network of local depots, while Parcelforce is a separate carrier with its own numbers ending `GB` or starting `PBAB`.

:::sources
- [APC: Receiving a Parcel](https://apc.co.uk/receiving-a-parcel/) – next working day, services, APC Choice options, safe place, neighbour, missed delivery, depot collection, three days, phishing advice
- [APC: APC Choice tracking and FAQ](https://apcchoice.apc-overnight.com/track-parcel) – 7-digit consignment number and where to find it, statuses, 2-hour slot, tracking pauses, reschedule once more, business addresses, damage via the sender
- [APC: Contact Us](https://apc.co.uk/contact-us/) – Customer Service Enquiry form forwarded to the local depot
- [APC: Depot Finder](https://apc.co.uk/depot-finder/) – depot contact details and opening hours by postcode
- [APC: Terms and Conditions of Carriage](https://apc.co.uk/terms-conditions-of-carriage/) – claim deadlines, £100 cap, packaging, three business days, proof of delivery
- [APC: Home](https://apc.co.uk/) – since 1994, more than 90 member depots, company name, National Sortation Centre
- [APC: Our Products](https://apc.co.uk/our-products/) – Parcelly stores, driver location, photo and GPS proof of delivery
- [APC: Parcelly partnership expands to 2,500 stores](https://apc.co.uk/the-apc-expands-parcelly-partnership-to-2500-stores-across-the-uk/) – Parcelly network
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what the tracker reads from APC Choice and how it recognises the number
:::
