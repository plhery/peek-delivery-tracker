---
title: Metropolitan Warehouse tracking (MWD): furniture delivery
description: Track a Metropolitan Warehouse & Delivery (MWD) order: the 12-digit number, what each status means, delivery appointments, damage deadlines and contacts.
slug: metropolitan-warehouse-tracking
published: 2026-10-28
updated: 2026-10-28
---

Metropolitan Warehouse & Delivery (MWD) is a family-owned furniture carrier founded in 1989 in Perth Amboy, New Jersey. It delivers furniture into homes for online shops, furniture retailers and designers across the 48 contiguous states. Track an order with its 12-digit tracking number on [MWD's tracking page](https://www.gomwd.com/track-a-shipment), or paste the number in the box above.

## MWD tracking number: 12 digits

MWD's tracker has a single field, "Tracking #", and takes 12 digits and nothing else (digits invented):

| Number | What it is |
| --- | --- |
| `500000000013` | The MWD tracking number. MWD's order page prints it in groups of four, `5000 0000 0013`. |
| `POMS #`, `Client REF# 1`, `Client REF# 2`, `Carrier PRO #` | Other references on MWD's order page: MWD's own, the shop's, and a partner carrier's. You don't track with them. |

The tracker asks for no zip code or phone number, but it may ask you to type a captcha. Anything other than 12 digits gets "Please enter a valid tracking number of 12 digits." A number MWD doesn't hold gets "Tracking id does not exist." Check it against the shop's email, and ask the shop if you have no number at all ([how to find your tracking number](guide:find-tracking-number)). MWD offers shops branded tracking pages, so yours may send you one under its own name instead.

## What MWD's tracking statuses mean

MWD's tracker writes its history in English, newest first, with a date, the line and a town and state. In the order they usually appear:

| MWD says | In Peek | What it means for you |
| --- | --- | --- |
| `Order registered` | Label created | The order is registered with MWD. |
| `Pickup document attached` | no change | Paperwork for the pickup. Nothing moved. |
| `Pickup scheduled for 11/02/2026` | Label created | MWD has booked the collection from the vendor. It doesn't have your item yet. |
| `Pickup completed, No exceptions` | Received by carrier | MWD has collected it, with no problem noted. |
| `Order enroute to Metro-Hub` | In transit | On its way to an MWD hub. |
| `Furniture Received by Metro-Hub` | In transit | Scanned into an MWD hub, named by its town. |
| `Reached Delivery Hub` | In transit | At the hub that will deliver it to you. |
| `Delivery Scheduled for WEEK OF 11/09/2026` | no change | Your delivery is planned for that week. It's still to come. |
| `Delivery Rescheduled for WEEK OF 11/16/2026` | no change | The delivery has moved to another week. |
| `Delivery Appointment Canceled` | no change | The appointment was cancelled. A new one normally follows; if none does, ask the shop or MWD. |
| `Arrived at Delivery Location` | Out for delivery | The crew is at your address. |
| `Delivery Completed, No Exceptions` | Delivered | Delivered, with no problem noted (see damage below). |

MWD's page writes the hub as `Metro-<Warehouse>-Hub`, a name it leaves blank; Peek shows `Metro-Hub`. No line Peek knows says "out for delivery": in the history, the first delivery-day line is `Arrived at Delivery Location`, with the crew already at your door.

## MWD delivery: appointment, service level and the crew

**The appointment.** MWD says customers book a delivery window through self-service scheduling and get automated reminders. On delivery day, a live tracking link shows the crew's location and estimated arrival. Its tracking page also shows the appointment window and the proof of delivery.

**What the crew does.** It depends on the service the shop bought. MWD's order page names it under "Service Type", and MWD lists these levels:

| Service level | What the crew does |
| --- | --- |
| Full White Glove | Places the item in the room you choose, does light assembly and takes away all the packaging. |
| White Glove (No Assembly) | The same for pre-assembled furniture and rugs, without assembly. |
| Room of Choice | Brings it inside to the room you designate. No assembly. |
| Threshold Delivery | Leaves it in the first dry area of your home, such as the entryway or garage. No unpacking or assembly. |
| Doorstep Delivery | Leaves it outside your door, with no signature, for lighter shipments. |
| Curbside Delivery | Leaves it at the curb or in the driveway. You take it from there. |

Assembly or debris removal you don't see in your level is a question for the shop that sold the service.

**Not at home.** MWD's pages don't say what happens after a missed appointment. If you can't be there, contact MWD or the shop before the day. More in [missed deliveries](guide:missed-delivery).

## MWD delivery damaged, late or missing

**Damaged.** Check every piece before the crew leaves. MWD's terms of carriage, the bill-of-lading terms on its client portal, set 72 hours after delivery for a written claim for loss or damage, and let MWD repair or replace the item. The shop is MWD's client, so tell it at once. More in [damaged parcels](guide:damaged-parcel).

**Late.** While the history stops at `Pickup scheduled for`, MWD hasn't collected your item. The FTC counts an order as shipped once it's in the carrier's hands. Under its Mail, Internet, or Telephone Order Merchandise Rule, an online, phone or mail-order seller that can't ship within the time it stated, or within 30 days if it stated none, must ask for your consent to the delay, and its notice must say you can cancel instead for a full and prompt refund. Nothing new for days after the pickup? See [tracking that isn't updating](guide:tracking-not-updating).

**Missing or delivered but not received.** The same terms give 30 days, after a reasonable time for delivery has passed, for a written claim for non-delivery. Ask the shop and MWD, and see [delivered but not received](guide:delivered-not-received) and [lost parcels](guide:lost-parcel).

## MWD contact and phone number

As read on MWD's contact page on 28 October 2026:

- **Support line:** (800) 300-7417. MWD says its team is available Monday to Friday, without giving hours.
- **Email:** comments@gomwd.com, for general inquiries.
- **Corporate office:** 960 High Street, Perth Amboy, NJ 08861.

The contact page's "Info About My Order" route is for clients, the businesses that ship with MWD: the shop that sold you the item can resolve an issue with MWD's customer success team.

## Track an MWD order with Peek

[Peek](/) can't tell MWD from the number alone: twelve digits fit many carriers ([why lengths overlap](guide:tracking-number-formats)). Paste it all the same: Peek follows more than 3,500 carriers. It asks the carriers whose numbers look like it whether they know it, MWD among the first when you're in the US, and shows "Metropolitan Warehouse & Delivery has this parcel." when MWD does. Otherwise press "Change" and pick Metropolitan Warehouse & Delivery (searching "MWD" finds it too). Spaces are fine. Paste the number itself: Peek has no rule for MWD's links.

Peek reads MWD's own tracker: the status, the history in MWD's words, each line's town and state on a map, the service level (such as Room of Choice) and the weight. It shows each time in your device's time zone. Peek leaves out your town and zip code, the order and client references, the partner carrier's PRO number, the item count and volume, and the progress steps MWD draws above the history. It shows no expected delivery date, no proof of delivery and no live crew tracking, and it doesn't follow a partner carrier.

Peek checks every 10 minutes from 8 am to 10 pm Swiss time (about 2 am to 4 pm Eastern, 11 pm to 1 pm Pacific), every 2 minutes in those hours once the parcel is out for delivery, and every hour or two the rest of the time. It can notify you of every scan, important steps only or "Delivery day only", which with MWD starts at `Arrived at Delivery Location`. Peek can't book or change the appointment, file a claim, or contact MWD or the shop for you.

## Questions about Metropolitan Warehouse tracking

### What is Metropolitan Warehouse & Delivery?

A family-owned furniture carrier and warehouse company based in Perth Amboy, New Jersey, since 1989. Shops, furniture brands and designers hire it to deliver into homes; you don't book it yourself.

### What does "Delivery Scheduled for WEEK OF" mean?

MWD has planned your delivery for the week of that date. It's still to come: the exact day and window come with your appointment. `Delivery Rescheduled for WEEK OF` means it moved to another week.

### Does Metropolitan Warehouse assemble furniture?

Only with Full White Glove, which includes light assembly and debris removal. The other levels, White Glove (No Assembly) included, don't assemble. MWD's order page shows your level under "Service Type".

### How do I reschedule my Metropolitan Warehouse delivery?

MWD's pages describe booking the window through self-service scheduling but not changing it. Call MWD's support line, (800) 300-7417, Monday to Friday, or ask the shop.

### How long do I have to report damage to MWD?

MWD's terms of carriage, on its client portal, set 72 hours after delivery for a written claim for loss or damage. The shop is MWD's client, so tell it right away too.

:::sources
- [Metropolitan Warehouse & Delivery: Home](https://www.gomwd.com/) – since 1989, who it delivers for, support line, address
- [MWD: About Us](https://www.gomwd.com/about-metropolitan) – family-owned, Perth Amboy, 48 contiguous states
- [MWD: Track a Shipment](https://www.gomwd.com/track-a-shipment) – where to track, appointment window, proof of delivery
- [MWD: Track Your Order](https://main.gomwd.com/client/wixtrackorder) – the tracker: one 12-digit field, error message, captcha
- [MWD: White Glove Delivery](https://www.gomwd.com/services/white-glove-delivery) – service levels, self-service scheduling, reminders, live tracking link
- [MWD: Services](https://www.gomwd.com/services) – Doorstep Delivery, live tracking links on delivery day, branded tracking pages
- [MWD: Contact Us](https://www.gomwd.com/contact-us) – support line, Monday to Friday, email, corporate office, clients' order route
- [MWD: Terms & Conditions](https://sso.gomwd.com/Account/TermsandConditions) – terms of carriage: 72-hour claims for loss or damage, repair or replacement, 30-day non-delivery claims
- [FTC: Business Guide to the Mail, Internet, or Telephone Order Merchandise Rule](https://www.ftc.gov/business-guidance/resources/business-guide-ftcs-mail-internet-or-telephone-order-merchandise-rule) – shipment, 30 days, consent to a delay, cancelling for a refund
- [Universal Parcel Scraper: Metropolitan Warehouse & Delivery](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/metropolitan-warehouse) – number shape, unknown-number message, order page fields, statuses and stages, what Peek keeps
:::
