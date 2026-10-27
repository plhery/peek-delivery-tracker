---
title: What does my tracking status mean? Every stage explained
description: What each tracking status means, from "label created" to "delivered", international parcels included, and what Royal Mail's "Internal exception occurred" means.
slug: tracking-status-meanings
picture: A winding route with ticked stops (label, depot, plane), a van at the current stop, a house ahead and Pip hurrying.
published: 2026-10-09
updated: 2026-10-27
---

A tracking status is the carrier's latest scan in a few words, and most ask nothing of you. "Label created" means the carrier doesn't have your parcel yet, "in transit" means it's somewhere between two scans, and "out for delivery" means it's on today's van. Only a few need you: a missed delivery, a parcel waiting for pickup, a customs charge and a "delivered" with nothing at the door.

## What are the stages of package tracking?

Every parcel passes the same stops, and one from abroad meets three more.

:::journey
- label | Label created | The carrier has the details, not the parcel.
- warehouse | In transit | Picked up, then hub to hub, with silence on the road.
- plane | Left the country | Abroad only. No scans in the air.
- customs | Customs | Abroad only. Sometimes a charge to pay.
- handover | Local carrier | Abroad only. Its scans may show only on its own site.
- truck | Out for delivery | On today's van.
- locker | Ready for pickup | Kept at a post office, shop or locker.
- home | Delivered | Handed over, or left where the carrier counts as yours.
:::

## What do USPS, Royal Mail and Australia Post statuses mean?

Same stages, different words:

| Stage | USPS | Royal Mail | Australia Post |
| --- | --- | --- | --- |
| Label created | `Shipping Label Created, USPS Awaiting Item` | `We're expecting it` | `Processing` |
| Picked up | `USPS in Possession of Item` | `We've got it` | `We've got it` |
| In transit | `In Transit` | `In transit` | `It's on its way` |
| Out for delivery | `Out for Delivery` | `Due to be delivered today` | `It's coming today` |
| Ready for pickup | `Available for Pickup` | `Available for Redelivery or Collection` | `Awaiting collection` |
| Delivered | `Delivered` | `Delivered` | `Delivered` |

## "Label created" or "pre-shipment": does the carrier have it?

No: the seller has printed a label and sent the details, nothing more. [USPS](carrier:usps) says the parcel "has not yet been given to USPS", and [UPS](carrier:ups)'s first scan reads `Shipper created a label, UPS has not received the package yet.` [An Post](carrier:an-post) words it `We have received information about your incoming item from the sender`. Some US parcels show `Picked Up by Shipping Partner, USPS Awaiting Item`: a partner company has it and will hand it to USPS.

[Australia Post](carrier:australia-post)'s `Pending` (nothing scanned yet) should update within 24 to 48 hours. If nothing moves, ask the seller, not the carrier: USPS and [Evri](carrier:evri) say so.

## What does "in transit" mean?

The carrier has your parcel, somewhere in its network. That doesn't mean it's moving: [FedEx](carrier:fedex)'s `On the way` can mean in a vehicle or at a facility. `Arrived at` and `Departed` lines are hub scans, which USPS says can repeat from one facility on different days.

Between hubs, silence is normal: FedEx calls more than 24 hours without a scan "not unusual".

### How long will a parcel be in transit?

Evri typically delivers 1 to 3 days after receiving a parcel, while Australia Post's `It's on its way` can last "a while" on long trips. Quiet past the carrier's own waiting time? See [why tracking stops updating](guide:tracking-not-updating).

## Parcels from abroad: left the country, customs, local carrier

- **Left the country.** It's booked onto a flight, ship or train. For a parcel heading to Germany, DHL warns the next update can be several days away. On an item from abroad, Australia Post's `It's on its way` means it's in Australia.
- **Customs.** `In customs` ([Royal Mail](carrier:royal-mail)) or `Item held by customs` ([Canada Post](carrier:canada-post)) is usually routine: Canada Post calls it standard for every international parcel, screened by the Canada Border Services Agency, and can't step in. DHL speaks of several business days; none of these carriers gives a general maximum. Once released, Canada Post's tracking says it has cleared customs. Act only on a request, such as Royal Mail's `Customs charges raised`; see [parcels held at customs](guide:customs).
- **Local carrier.** In the US, UPS's `Package transferred to post office` means a Ground Saver parcel has gone to USPS for the last leg. DHL points you to the destination carrier's tracking. The local carrier can give the parcel [a number of its own](guide:tracking-number-formats). Ordered from AliExpress, Temu or Shein? See [tracking a parcel from China](guide:tracking-from-china), and [Cainiao's own wordings](carrier:cainiao) for AliExpress parcels.

## Does "out for delivery" mean it arrives today?

Usually, yes:

- **USPS:** `Out for Delivery` means today, perhaps [at a different time from your usual mail](guide:delivery-times). No delivery recorded by 9 pm? It becomes `Awaiting Delivery Scan` (or `Delivery Status Not Updated`), and USPS expects it the next business day.
- **FedEx:** generally 8 a.m. to 8 p.m.
- **Australia Post:** `It's coming today` once the postie has loaded it, and it may text you a 2-hour window.

Two look-alikes: Peek counts Royal Mail's `Ready for delivery` as in transit, not out for delivery. And at [DHL](carrier:dhl) in Germany, a loading scan shown more than once means the round wasn't finished: a delay of 1 to 2 days is possible.

[Peek](/) checks up to every 2 minutes once a parcel is out for delivery, and its notifications can be set to delivery day only.

## "Delivery attempted" or "notice left": what now?

This one needs you: [nobody could take the parcel](guide:missed-delivery), or the driver couldn't get in.

:::steps
- Read the notice | Card, text or email: where the parcel went, and a number to quote.
- Check the deadline | From 2 days to 3 weeks, counted in calendar, working or business days.
- Rebook or redirect | On the carrier's own site or app: another day, a neighbour, a pickup point.
- Collect with ID | Bring photo ID and the card.
:::

| Carrier | After a missed delivery | Kept for pickup |
| --- | --- | --- |
| USPS (US) | `Notice Left`: no automatic second try; book a redelivery or collect | Signature mail "usually 15 days"; Priority Mail Express 5 calendar days |
| UPS (US) | The scan says you weren't available or the business was closed, and UPS tries again; after the final attempt it holds the parcel, takes it to an Access Point or sends it back | – |
| FedEx (US) | Next-day reattempt, or a nearby FedEx location | 7 days |
| Royal Mail (UK) | `Due for Redelivery the Next Working Day`, or a red card naming where to collect | About 3 weeks with a customs bill to pay |
| Evri (UK) | Three attempts and a calling card; no depot collection | If sent to a ParcelShop, 10 days; a locker, 3 |
| An Post (IE) | `We attempted to deliver your item` | – |
| Canada Post | `Notice left`: a card naming the post office | 15 calendar days |
| Australia Post | A card or message; a Post Office, or maybe a Parcel Locker if your mobile number is on the item | Locker 2 business days; returned after 10 business days |

> A message asking you to pay to get your parcel or reschedule the delivery [is usually a scam](guide:delivery-scam-texts), says Citizens Advice in England: delivery companies won't ask you to pay through a link in an email or text. Rebook only on the carrier's own site or app.

## "Ready for pickup" or "awaiting collection": where is it?

It's at a post office, shop or locker, [until the deadline](guide:uncollected-parcel) in the table above. Canada Post, like USPS, says `Available for Pickup`, and UPS's `Delivered to UPS Access Point™` means it's ready for you there.

Wait for this status or the carrier's message before going: USPS makes items available from the next business day, and Australia Post's notification gives the date and time. Canada Post prints the pickup place on the card, and it can change from one delivery to the next.

## Does "delivered" mean it's in your hands?

Not always. It means the driver scanned it as handed over or left:

- **With a neighbour.** Royal Mail's `Delivered to Neighbour/Reception by` goes on to name who took it in.
- **Somewhere safe.** UPS's page can show where the parcel was left (`Left at: Front Door`, for example). USPS's spots at home include the mailbox and the back entrance; a key in your mail compartment means a parcel locker. Canada Post's `Delivered as a safe drop` usually comes with a photo.

Australia Post also shows `Delivered` once you've collected it from a Post Office or Parcel Locker. Nothing there? Check the full tracking for a photo, a name or a place, then see [delivered but not received](guide:delivered-not-received).

## Does a delivery exception mean it's lost?

No. FedEx says a `Delivery exception` "doesn't necessarily mean" the parcel will be late, and asks nothing of you; its examples include a customs delay and "recipient wasn't home". UPS's `Exception` is a generic status and can mean a mere delay: read the scan under it.

FedEx's `Running late` means it will likely miss its estimated date. As for how long, Australia Post says most items flagged `Delayed` arrive within 5 business days of the expected date.

Look closer when the reason is an address or a payment: Canada Post's `Verifying recipient's address; Possible delay` ends in a return if the address can't be confirmed. Royal Mail's `Internal exception occurred` is a website error, not your parcel.

## Why was my parcel returned to sender?

The carriers' reasons: not collected in time, a wrong or incomplete address, a refusal, unpaid customs charges, damage. Some returns don't say so:

- **USPS** `Insufficient Address`: it's already heading back, and the sender must post it again with new postage.
- **Australia Post** `Contact sender`: a return, after wrong details or 10 business days uncollected.

Don't count on stopping it: "Once the return process starts, we can't stop it," says Canada Post. Ask the seller to [send it again or refund you](guide:returned-to-sender).

:::sources
- [USPS: Where is my package?](https://faq.usps.com/s/article/Where-is-my-package) – statuses, 9 pm rule
- [USPS: Redelivery, the basics](https://faq.usps.com/s/article/Redelivery-The-Basics) – holding periods
- [FedEx: Tracking status meanings](https://www.fedex.com/en-gb/customer-support/faq/receiving/tracking-questions/fedex-tracking-status-meaning.html) – statuses
- [FedEx: Delivery exception](https://www.fedex.com/en-us/customer-support/faqs/receiving/tracking-questions/delivery-exception.html) – not necessarily late
- [FedEx: Package not moving](https://www.fedex.com/en-us/customer-support/faqs/receiving/tracking-questions/package-not-moving.html) – 24 hours
- [FedEx: Missed delivery](https://www.fedex.com/en-us/customer-support/faqs/receiving/tracking-questions/missed-delivery.html) – 7 days
- [FedEx: Reattempt delivery](https://www.fedex.com/en-us/customer-support/faqs/receiving/tracking-questions/reattempt-delivery.html) – next day, hours
- [Royal Mail: Tracking terms explained](https://www.royalmail.com/help/postage-tracking-terms-explained) – Internal exception occurred
- [Universal Parcel Scraper: carrier data](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – Royal Mail, UPS and An Post status wordings
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – Royal Mail's red card
- [Citizens Advice: If something you ordered hasn't been delivered](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – pay-by-link messages
- [GOV.UK: Goods sent from abroad, tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – about 3 weeks with a bill to pay
- [Evri: How do I track my parcel?](https://www.evri.com/faqs/receiving-a-parcel/how-do-i-track-my-parcel) – 1 to 3 days, card
- [Evri: Can I collect my parcel?](https://www.evri.com/faqs/receiving-a-parcel/can-i-collect-my-parcel) – three attempts
- [Evri: ParcelShop delivery](https://www.evri.com/our-services/parcelshop-delivery) – 10 days
- [Evri: Locker delivery](https://www.evri.com/faqs/lockers/can-i-get-my-parcel-delivered-to-a-locker) – 3 days
- [Canada Post: Tracking statuses](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-statuses.page) – statuses
- [Canada Post: Delivery options](https://www.canadapost-postescanada.ca/cpc/en/support/articles/parcel-services-shipping-in-canada/delivery-options.page) – 15 calendar days
- [Canada Post: Parcel in customs](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/delivery-faq/the-parcel-i-am-expecting-is-in-customs-what-can-i-do.page) – CBSA
- [Canada Post: Where to get your delivery](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/delivery-faq/where-to-get-your-delivery.page) – pickup place
- [Australia Post: What your status means](https://auspost.com.au/help/parcel-tracking/tracking-support/what-your-status-means) – statuses, notifications
- [Australia Post: Missed parcel deliveries](https://auspost.com.au/personal/receiving/parcel-deliveries/missed-parcel-deliveries) – lockers, 10 business days
- [DHL: What does my shipment status mean?](https://www.dhl.de/en/privatkunden/hilfe-kundenservice/sendungsverfolgung/was-bedeutet-mein-sendungsstatus.html) – loading scan
- [DHL: International shipment status](https://www.dhl.de/en/privatkunden/hilfe-kundenservice/themen/international/sendungsverfolgung/was-bedeutet-mein-sendungsstatus.html) – flights, customs
:::
