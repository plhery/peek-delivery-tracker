---
title: Straightship tracking: STRTD numbers, statuses and delivery
description: Track a Straightship parcel in Canada: what STRTD, STRCA and STSH numbers look like, what each status means, missed deliveries, customs and who to contact.
slug: straightship-tracking
published: 2026-10-23
updated: 2026-10-27
---

Straightship (Straightship Solutions Limited) is a logistics company based in Burnaby, British Columbia, that ships goods from Asia to North America and delivers parcels across Canada with its own drivers. Its site lists SHEIN among its partners. If an online order came with a number starting `STRTD` or `STRCA`, Straightship has it; one starting `STSH` can be Straightship's too. Track it on [straightship.com](https://www.straightship.com/new/), in the "Track Your Shipment" box, or in the box above. The shop gives the number in its shipping email or on the order page.

## Straightship tracking number: what it looks like

Straightship publishes no number format. The numbers Peek knows look like this, with invented digits:

| Number | What it is |
| --- | --- |
| `STRTD000000000` | STRTD and 9 digits. |
| `STRTD0000000000000000` | STRTD and 16 digits (17 also occur). |
| `STRCA0000000000000` | STRCA and 13 digits. |
| `STSHYW00000000000` | STSH, two letters and 11 digits. |

Lower case and spaces don't matter in Peek. Straightship's tracker answers a number it doesn't hold with `No results found!`: check the number against the shop's email. Can't find the number at all? See [how to find your tracking number](guide:find-tracking-number) and, for a SHEIN order, [tracking SHEIN orders](guide:shein-orders).

## Straightship tracking statuses explained

Straightship's tracker is in English. It shows a summary, with a "Package Status", above a history with the time, the status and the town of each scan. The lines people see, in the order a parcel usually meets them:

| Straightship says | What it means for you |
| --- | --- |
| `Package information is created in the system` | The shop made the label. Straightship doesn't have the parcel yet: it is still with the seller or on its way from Asia. |
| `Package Received with Origin Scan warehouse` | Straightship's first scan in Canada, with the town and province. |
| `Package is received at a Straightship station` | It reached one of Straightship's depots. Older pages say `Arrived at Facility`. |
| `Package arrived at transit station` | At a transit depot, on its way to your local one. |
| `Loading` | Being loaded at the depot before the delivery round. It can appear twice, seconds apart. |
| `Package will be delivered before 9:00pm` | Out for delivery today. A delivery or an attempt follows the same day. |
| `Attempted Delivery` | The driver tried and couldn't deliver. |
| `Shipment delayed` | Late. Straightship gives no reason on the tracker. |
| `Delivered` | Delivered, with the town and province. |
| `Package is being returned to shipper` | On its way back to the seller. |

Two attempt lines tell you what to do. `An attempt was made to deliver your package-consignee closed (please e-mail SS@straightship.com to make arrangement)` means the place it was addressed to was closed; the same line ending in `unable to leave package at safe location` means the driver found nowhere safe to leave it. Both ask you to email Straightship to arrange the next delivery.

Three things confuse people:

- **The summary lags.** The "Package Status" can stay `ReadyForShipping` (or `Ready For Shipping`) while the parcel moves between depots. Read the history: its newest line is what counts. Later values are `Shipped`, `Delivered` and `ReturnedToSender`.
- **"In progress" isn't out for delivery.** `Delivery is in progress` and `Your order is in Progress` turn up after a delivery that didn't happen, sometimes days later and before a return. Email Straightship if you see them.
- **The times are UTC.** The tracker says under the history that every time is in Coordinated Universal Time. An evening scan in Canada shows the next day's date.

A parcel being returned can still be scanned at a depot and sent out again: `Package is being returned to shipper` isn't always the last line.

## Straightship delivery in Canada: who brings it

Straightship's tracker is for Canada: its heading reads "Canada B2C Shipment Tracking". Straightship says its own delivery network covers 95% of Canada's population, with over 85 delivery hubs, and its out-for-delivery line promises delivery before 9 pm.

For parcels it brings into Canada from the US, Straightship offers "different options on last-mile carriers": its own network, [Canada Post](carrier:canada-post) or [UPS](carrier:ups), plus [Purolator](carrier:purolator) on its English page but FedEx on its Chinese one. Which one depends on the shipment. Its tracker shows only Straightship's own depots and names no other carrier or second number.

Straightship publishes no rules for how many attempts it makes, whether it leaves a notice card, or how long it keeps an undelivered parcel at the depot. What it does say:

- **After a failed attempt**, email SS@straightship.com, as its tracker line asks, with your tracking number.
- **To change your address or phone number**, use its contact form and pick "Address and/or contact details update".
- **Proof of delivery**: its tracker shows the latest status "and POD image (Where available)", a photo of the delivery.

## Customs on a Straightship parcel from Asia

Straightship clears customs for the businesses it ships for, and its pages don't say whether it ever bills recipients. If you're asked to pay for a Straightship parcel, check with the seller first.

For a parcel coming by courier, the Canada Border Services Agency (CBSA) lets the courier account for duty and taxes on your behalf. The CBSA says the courier "will reach out to you to organize the duties and taxes payment prior to the delivery" and holds the parcel until you pay, and that a courier's clearance fees are separate from duty and taxes and outside the CBSA's control. The CBSA never contacts you by phone, text or email to ask for your Social Insurance Number or card number: such a message is a scam. What you owe, the exemptions and refunds are in [parcels held at customs](guide:customs).

## Straightship parcel not arriving, lost or returned

**Who acts.** Straightship's customers are the sellers and platforms that ship with it, and it publishes no claim route for recipients. For a refund or a new parcel, go to the shop: for SHEIN, see [tracking SHEIN orders](guide:shein-orders).

**Stuck before Canada.** `Package information is created in the system` with nothing after it means the parcel hasn't reached Straightship in Canada. It may still be on its way from Asia. Ask the seller when it shipped; see [tracking a parcel from China](guide:tracking-from-china).

**No update in Canada.** If nothing moves for several days after a depot scan, or `Shipment delayed` shows, use Straightship's contact form with "Delivery Status Enquiry". More in [why tracking stops updating](guide:tracking-not-updating).

**Delivered but not there.** Look for a delivery photo on Straightship's tracker (there isn't always one), then check the neighbours, your building's mailroom and any safe spot around your door. Then use the form's "Delivered Order Not Received" and tell the seller. See [delivered but not received](guide:delivered-not-received).

**Returned.** `Package is being returned to shipper` means it's going back to the seller. Ask the seller to refund you or ship it again; see [returned to sender](guide:returned-to-sender).

## Straightship contact number and customer service

As read on 23 October 2026, Straightship lists:

- **Phone**: +1 (800) 452-3304, Monday to Friday 08:00 to 21:00 and Saturday 08:00 to 16:00, Eastern time.
- **Email**: SS@straightship.com, the address its failed-attempt lines give.
- **Contact form**: on [its contact page](https://www.straightship.com/new/contact.html), with the tracking number required. Straightship asks you to allow 2 business days for a reply.

For the order itself, a refund or a return, contact the seller.

## Track a Straightship parcel with Peek

[Peek](/) names Straightship at once from a `STRTD` or `STRCA` number of the shapes above, which no other carrier in its catalog shares. From an `STSH` number it can't, as some also fit Colis Privé's numbers: Peek asks Straightship whether it has the parcel and picks it when it does. If Straightship doesn't come up, tap "Change" and pick it under "Fits this number". A straightship.com tracking link (`/track-your-shipment/tracking-id/` and the number) can be pasted too. Peek tracks only these shapes with Straightship: if you pick Straightship with "Change" for another number, Peek says it "doesn’t issue numbers like this one". For a Straightship number of another shape, use Straightship's own tracker.

Peek reads Straightship's own tracker and shows the status, the history with the town and province of each scan, and the delivery time. It reads the tracker's times as UTC, as the tracker states. `Package will be delivered before 9:00pm` counts as out for delivery, the attempt lines as failed attempts, and `Delivery is in progress` as in transit. Peek shows no expected delivery date, and it leaves out the customer reference and the delivery photo: see those on Straightship's tracker. As Straightship names no other carrier, Peek follows the Straightship number to the end.

Peek checks every 10 minutes from 8:00 to 22:00 Swiss time (about 2:00 to 16:00 Eastern, 23:00 to 13:00 Pacific), every 2 in those hours once the parcel is out for delivery, and every hour or two the rest of the time, evening deliveries included. It can notify you with "Every scan", "Important steps" or "Delivery only". It can't contact Straightship or the seller for you.

## Questions about Straightship tracking

### Is Straightship legit?

Yes. Straightship Solutions Limited is a logistics company in Burnaby, British Columbia, founded by Justin Thompson, William Qu and Kevin O'Byrne. Its site lists SHEIN, Canada Post, FedEx, COSCO and YTO among its partners and says it has over 85 delivery hubs.

### What does "Package will be delivered before 9:00pm" mean?

Your parcel is out for delivery today. The driver delivers it, or records an attempt, the same day. If nobody can take it, an attempt line follows and asks you to email SS@straightship.com.

### Why does Straightship still say ReadyForShipping?

The "Package Status" summary can keep `ReadyForShipping` while the parcel moves between Straightship's depots. Go by the newest line of the history below it.

### How long does Straightship take to deliver?

Straightship publishes no delivery time for parcels from Asia. Go by the estimate the shop gives for your order. Once a line says `Package will be delivered before 9:00pm`, the driver comes that day.

### Who delivers Straightship parcels?

Straightship's own drivers, from its depots across Canada, as far as its tracker shows: it names no other carrier. For parcels from the US, Straightship also lists Canada Post and UPS for the last mile, and Purolator on its English page but FedEx on its Chinese one.

:::sources
- [Straightship: Home](https://www.straightship.com/new/) – Asia to North America, own fleet, tracking box, POD image, partners, phone, email, hours
- [Straightship: About](https://www.straightship.com/new/about.html) – founders, door-to-door from Asia to North America
- [Straightship: Services](https://www.straightship.com/new/services.html) – own network covering 95% of Canada's population; US-to-Canada service with last-mile options of its own network, Canada Post, Purolator or UPS; customs clearance
- [Straightship: 服务 (services, Chinese)](https://www.straightship.com/new/cn/services.html) – the same US-to-Canada service with its own network, Canada Post, FedEx or UPS for the last mile
- [Straightship: Resources](https://www.straightship.com/new/resources.html) – over 85 delivery hubs
- [Straightship: Contact](https://www.straightship.com/new/contact.html) – contact reasons, tracking number required, 2 business days
- [Straightship: Privacy Policy](https://www.straightship.com/new/privacy.html) – Straightship Solutions Limited, Burnaby
- [Straightship: Canada B2C Shipment Tracking](https://www.straightship.com/track-your-shipment/tracking-id/STRTD000000000/) – tracker heading, "No results found!", customer service hours
- [CBSA: Importing casual goods by courier](https://www.cbsa-asfc.gc.ca/import/courier/lvs-efv/prsn-eng.html) – couriers account for duty and taxes, clearance fees outside the CBSA's control
- [CBSA: Protect yourself against fraud](https://www.cbsa-asfc.gc.ca/security-securite/pyaf-pvcf-eng.html) – the courier organizes payment before delivery and holds the parcel, no SIN or card requests
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek shows, number shapes, scan wordings, UTC note, summary values
:::
