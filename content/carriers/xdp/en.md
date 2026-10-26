---
title: XDP tracking: consignment numbers, statuses and delivery
description: Track an XDP parcel in the UK: where the consignment number comes from, why XDP asks for your postcode, what its statuses mean and how to reach XDP.
slug: xdp-tracking
published: 2026-10-13
updated: 2026-10-26
---

XDP (XDP Express) is a UK parcel and freight network that delivers large and heavy items for shops: furniture, mattresses, garden and gym equipment, often with one or two drivers. Track a parcel on [XDP's tracking page](https://xdp.co.uk/track/) or in the XDP app with the consignment number and the delivery postcode, or paste the number in the box above. The number comes from the shop that sent the item, not from XDP.

## XDP tracking number: the consignment number

XDP calls it the consignment number. Its tracking page puts it plainly: you receive it "from the company who is sending your item for delivery via XDP", so look in the shop's shipping email or your order page. XDP's own texts and emails come later, once the parcel is on its way to you.

XDP publishes no format; its numbers look like this:

| Shape (invented) | What it is |
| --- | --- |
| `ZDBABC123456` | `ZDB`, three letters and six digits: an XDP consignment number. |
| `0ZDBABC123456` | The same number with a leading `0`, which can be dropped. |

XDP's tracker also asks for the delivery or collection postcode. A wrong postcode and a mistyped number give the same answer, "Consignment not found for consignment number", so check both.

No other UK carrier uses the `ZDB` shape. A [DPD UK](carrier:dpd-uk) number is 14 digits and an [APC Overnight](carrier:apc-overnight) waybill 22 digits, so if your email names one of them, track it there. More in [tracking number formats](guide:tracking-number-formats).

## What XDP's tracking statuses mean

XDP's tracker, app and FAQ are in English only. Route numbers and times change from parcel to parcel.

| XDP's status | What it means for you |
| --- | --- |
| `Order received` | The shop has sent XDP the order details, but XDP doesn't have the item yet. |
| `At Collection Depot` | XDP has your item and it's heading to your delivery depot. |
| `At Delivery Depot` | It's at the depot that will deliver it. |
| `Allocated to Delivery Vehicle - Route 03` | It's been put on a delivery round. |
| `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm` | It should arrive today, within this two-hour window. |
| `Next stop: On time` | You're the driver's next stop. XDP shows `On time`, "Late by …" or "Early by …" against the window; Peek adds "Next stop:". |
| `Delivered` | Delivered. |
| `Carded` | The driver tried, but nobody was in to take it. |
| `Consignment Split` | The depot has only part of your order from the shop. |
| `Check Address` | The driver can't find the property and needs more information. |
| `Service Fail` | Something outside XDP's control, such as a breakdown, traffic or bad weather, delayed it. |
| `Return` | It's gone back to the sender: contact the shop. |

The XDP app words some differently (`Delivery scheduled`). After `Consignment Split`, XDP says the remaining items follow "within the next few days". `RETURN LABEL CREATED` starts the way back to the sender, and `Damaged` means the item appears damaged (see below).

## XDP delivery: time slots, missed deliveries and collection

XDP sends a two-hour delivery window on the day of delivery once the driver has loaded the van, usually by 12 noon. At busy times a depot may plan ahead and send it 24 hours or more in advance. A "You Are Next" message follows by text, email and the app. For two-person (2Man) deliveries, XDP first contacts you with a date, then sends the two-hour slot on the day. With 1+ and 2Man services the driver can take the item to a room of your choice, after you sign a waiver form XDP sends beforehand.

In the XDP app you can:

- choose **Change Delivery Date** to rearrange the delivery or, in some cases, pick a date;
- add delivery instructions, such as leaving the item with a neighbour or in a porch, shed, garage or behind a gate;
- leave notes for the driver and see the proof of delivery.

XDP warns that by asking for an item to be "Left Safe" you accept full responsibility for it from the moment it's delivered.

**Missed deliveries.** `Carded` means the driver found nobody in. XDP says it will try to deliver three times before the item goes back to the sender. If no card was left, the driver can't try again that day: rearrange it with Change Delivery Date.

**No pickup points or depot collection.** XDP has no parcel shops or lockers, and its app says it doesn't offer collection from its depots or hubs. The FAQ sends you to the shop if you want to collect.

## XDP parcel not delivered, late or damaged

XDP works for the sender, not for you. Its advice for any delivery issue is to track the item first in the app or on the website, then contact your supplier, the shop, for anything further. The shop holds the contract and makes any claim.

**Late or not updating.** XDP doesn't guarantee next-day delivery, least of all at peak times: around bank holidays, Black Friday and Cyber Monday. Drivers held up by traffic may not always update you if the window changes. See [tracking that isn't updating](guide:tracking-not-updating).

**Damaged.** XDP asks you to accept the delivery rather than refuse it, and to contact the sender at once. Note the damage on the proof of delivery when you sign: XDP's procedure guide for senders says a claim is denied if the signature notes no discrepancy, and remarks like "unchecked" or "unseen" don't count.

**Lost.** The sender's deadlines with XDP are tight: 33 days from dispatch to claim a lost or part-lost consignment and 7 days from delivery for damage, weekends included. Tell the shop quickly. If tracking says `Delivered` and you have nothing, see [delivered but not received](guide:delivered-not-received).

**Returned.** After `Return`, XDP says to contact your supplier.

## XDP contact number and customer service

XDP's channels for recipients, as read on 13 October 2026:

- **Delivery Query form** on XDP's [contact page](https://xdp.co.uk/contact/). The "New Business Enquiry" form there is for sales only.
- **Email** trackingupdates@xdp.co.uk.
- **Live chat** on xdp.co.uk and in the XDP app; XDP publishes no hours.
- **App contact form**, asking for your name, postcode, email and, optionally, the consignment number.

XDP's website gives no phone number. Its app mentions calls charged at a premium rate of 7p a minute, without saying which number; numbers listed on other websites aren't published by XDP.

## Tracking XDP parcels with Peek

[Peek](/) recognises a `ZDB` number straight away as XDP's. Links from xdp.co.uk (`/track/` followed by the number, or `track.php?c=`) work too, though a postcode inside the link isn't taken.

Peek shows an optional "Delivery or collection postcode" field for XDP. Without a postcode, XDP isn't asked at all and the parcel is followed only through [universal tracking services](guide:universal-tracker). With it, Peek reads XDP's tracking page; the postcode is sent to XDP and, when needed, to tracking services. A wrong postcode reads as not found, just like on XDP's site: fix it with "Change".

XDP's scan times carry no time zone: Peek reads them on UK time and lists XDP's own history. Statuses keep XDP's wording, except `Delivered`, which Peek writes in its own words; the weight and service can be shown, and Peek can notify you when the status changes. There's no expected delivery day, no places for the scans and no window, except inside a wording like `Scheduled for Delivery - Route 11 - ETA: 10:51am to 12:51pm`.

## Questions about XDP tracking

### Why is my XDP tracking not working?

Usually the postcode: XDP's tracker needs the consignment number and the delivery or collection postcode, and a wrong postcode shows "Consignment not found". Check both against the shop's email.

### How long does XDP take to deliver?

XDP tells senders 1 to 3 days for its 1Man service and 3 to 5 days for 1+ and 2Man, subject to shipping for offshore addresses. The day itself comes with a two-hour window.

### Can I collect my parcel from an XDP depot?

No. XDP's app says it doesn't offer collection from its depots or hubs, and its FAQ refers you to the shop. You can rearrange the delivery or ask for it to go to a neighbour.

### Is XDP a real delivery company?

Yes. X D P Limited has been registered at Companies House since 1995, with its office in Curdworth, near Sutton Coldfield, and delivers across mainland Britain, Northern Ireland, the Channel Islands and the Isle of Wight.

:::sources
- [XDP: Track](https://xdp.co.uk/track/) – consignment number from the sender, postcode, tracker notices
- [XDP: FAQs](https://xdp.co.uk/faqs/) – status meanings, contact the supplier, depot collection, app rescheduling
- [XDP: Get In Touch](https://xdp.co.uk/contact/) – Delivery Query form, emails
- [XDP: About Us](https://xdp.co.uk/about-us/) – formed 1995, coverage, two-hour slot, proof of delivery
- [XDP: Home](https://xdp.co.uk/) – large and non-standard freight, services, app
- [XDP: 1Man Delivery](https://xdp.co.uk/services/1man-delivery/) – 1 to 3 days, notifications, proxy phone
- [XDP: 1+ Delivery](https://xdp.co.uk/services/1-plus-delivery-service/) – 3 to 5 days, room of choice, You Are Next, live chat and email
- [XDP: 2Man Delivery](https://xdp.co.uk/services/2-man-delivery/) – pre-booked date, two-hour slot
- [XDP: App (web version)](https://app.xdp.co.uk/) – delivery window, three attempts, Change Delivery Date, left safe, no depot collection, damage, returns, calls, contact form
- [XDP: Terms and Conditions of Carriage](https://xdp.co.uk/wp-content/uploads/2024/02/XDP-TCFinal.2.24.V1.pdf) – no next-day guarantee, peak periods, claim deadlines
- [XDP: Procedure Guide](https://xdp.co.uk/wp-content/uploads/2026/03/Procedure-Guide-V.5.pdf) – 33 and 7 days, noting damage on the proof of delivery
- [XDP: Tracking (track.php)](https://www.xdp.co.uk/track.php) – postcode and not-found notices
- [Companies House: X D P LIMITED](https://find-and-update.company-information.service.gov.uk/company/03139661) – registration, office
- [Apple App Store: XDP](https://apps.apple.com/gb/app/xdp/id1485292140) – the XDP app
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
