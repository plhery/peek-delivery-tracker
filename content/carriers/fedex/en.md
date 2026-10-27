---
title: FedEx tracking: numbers, statuses and missed deliveries
description: Track a FedEx parcel: where to find the 12-digit tracking number, what statuses like Delivery exception mean, missed deliveries, customs bills and contacts.
slug: fedex-tracking
published: 2026-10-13
updated: 2026-10-27
---

FedEx is an express and parcel carrier. Track a parcel on fedex.com's tracking page, in the FedEx Mobile App or by pasting the number in the box above. FedEx says the number usually comes in the email or SMS it sends about your shipment.

## FedEx tracking number: where to find it and how many digits

Look in the shop's shipping email or order page, in FedEx's own email or SMS, and at the bottom of the shipping label. FedEx's help box calls it "a 12-digit number"; its tracking FAQ says 12 or 13 digits.

| Shape (invented) | What it is |
| --- | --- |
| `123456789012` | 12 digits: the usual FedEx tracking number. |
| `123456789012343` | 15 digits: a shape Peek also files under FedEx. |
| `61290000000000000014` | 20 digits starting `6129` or `7489`: FedEx Ground Economy (formerly SmartPost) in the US. |

No number? On fedex.com, "Track by reference" works with the shipper's reference. Without either, FedEx says to contact the sender; see [where else to find a tracking number](guide:find-tracking-number).

Twelve digits also fit [DPD France](carrier:dpd-fr), [Mondial Relay](carrier:mondial-relay), [BRT](carrier:brt) and several Asian couriers, and fifteen fit DPD France and BRT too. If fedex.com doesn't know the number, check which carrier the shop's email names.

## What FedEx tracking statuses mean, including delivery exception

These are the English names on fedex.com. Its other country sites show them in their own language.

| FedEx's status | What it means for you |
| --- | --- |
| `Label created` | The shipper printed the label and is preparing the parcel. FedEx doesn't have it yet. |
| `We have your package` | It reached a FedEx facility or a FedEx courier picked it up. |
| `On the way` | It's moving towards you, in a vehicle or at a FedEx facility. |
| `At our facility` | It's safe at a FedEx location. The status changes when it moves again. |
| `Clearance in progress` | It's going through customs. |
| `Out for delivery` | It's on the van for delivery today. |
| `Delivery updated` | The date or address changed, at the shipper's or your request, or because of an exceptional event. |
| `Delivery exception` | Something unexpected is delaying delivery. |
| `Running late` | It probably won't arrive by the first estimate. |
| `Ready for pickup` | It's waiting for you at a FedEx location. |
| `Returning to FedEx facility` | It was turned away at the door and is going back to a FedEx location. |
| `DELIVERED` | Delivered, with the date, time and who signed. |

`Delivery exception` sounds worse than it is: FedEx's examples are a customs delay, bad weather, strikes, nobody at home or security restrictions, and it says there's nothing you need to do at that point. The detailed history may give the reason, such as `Customer not available or business closed`.

`Returning to FedEx facility` isn't a return to the shop yet. It means the recipient refused it, for example as damaged, and FedEx asks the shipper what to do.

## Missed FedEx delivery, door tags and Delivery Manager

**UK, Ireland and Europe.** If nobody is in, FedEx may leave the parcel in a secure place. Otherwise the driver leaves a Missed Delivery Card with a QR code that opens your options: a later date, a neighbour, a safe place, a pickup point, another address or instructions for the driver, depending on your country and the shipment. After a missed delivery, you need to pick a new date in FedEx Delivery Manager for another attempt. Before the first attempt, use "Manage delivery" on the tracking page or the link in FedEx's email.

You can make changes until 4:00 am on the scheduled delivery day, after two-step authentication. The new date must fall within 5 business days of the original one; a parcel held longer may cost storage fees. High-value parcels, those needing a direct or adult signature and those whose sender doesn't allow it may not qualify. Redirecting can cost extra and never crosses a border.

In Europe only the sender can switch on Delivery Manager; its SMS or email then lets you change the date or place for free.

**United States.** A missed delivery leaves a door tag. If it was the first attempt, sign the tag and leave it on the door to authorise a next-business-day delivery; if the driver ticked "Final delivery attempt", the parcel is at a FedEx location. To pick it up, bring the door tag or tracking number and government-issued photo ID. Or redirect it to a FedEx location such as FedEx Office or Walgreens.

FedEx's finder at local.fedex.com lists pickup points and their hours. FedEx doesn't say how long a parcel waits there.

## FedEx parcel delayed, held at customs or lost

**No movement.** For `Delivery exception` and `Running late`, FedEx says there's nothing you need to do. If it drags on, "Ask FedEx" offers "Report a delayed delivery".

**Customs.** When paperwork or information is missing, FedEx contacts you or the shipper; reply to its email with the documents. Duties and taxes fall on the recipient unless the shipper chose to pay them. FedEx may pay them for you and add a disbursement fee, listed on its rates page for each country. The bill can come after delivery, or the parcel may be held until you pay. Pay on FedEx's payment portal or with the unique link in its email or SMS. See [parcels held at customs](guide:customs).

**Delivered but not there?** Check with neighbours, look at the proof of delivery on the tracking page, then choose "Manage delivery" and report the parcel missing. More in [delivered but not received](guide:delivered-not-received).

**Damage or loss.** FedEx's Conditions of Carriage set 21 days after delivery for damage, delay or missing contents, and nine months after FedEx took the parcel for loss or non-delivery. The shop or sender holds the contract with FedEx, so tell them first. Keep the contents, box and packaging: FedEx may want to inspect them.

## How to contact FedEx customer service

FedEx's contact pages, read on 13 October 2026:

- **UK, Ireland, Germany, Austria, Switzerland, Belgium, Portugal and Poland**: "Ask FedEx" on the customer support page, with guest access for private users, answers and a way to speak to a person. These pages show no phone number. Claims and complaints go through FedEx's contact form.
- **United States and Canada**: 1.800.GoFedEx (1.800.463.3339).

> FedEx says a delivery notification never asks for payment, bank details or passport numbers, and that a real Delivery Manager link shows the full fedex.com domain. A duty bill can come by email or SMS, though: check it on fedex.com first. FedEx's US site also warns about texts linking to .shop addresses; forward suspicious messages to abuse@fedex.com.

## Tracking FedEx parcels with Peek

[Peek](/) recognises FedEx's 12- and 15-digit numbers when their check digit is right. Other carriers share those shapes, so the parcel is often named by a tracking service or after a short wait. A number that fails the check isn't offered to FedEx: pick the carrier with "Change". Ground Economy's 20-digit numbers are named FedEx at once, scanned label barcodes are read for the number inside, and fedex.com links with `trknbr=` or `tracknumbers=` work too. FedEx's 13-digit domestic numbers from Poland are followed as [FedEx Poland](carrier:fedex-pl).

Peek opens fedex.com's tracking page in a browser on its own server. When FedEx refuses, or asks for recipient verification, tracking services fill in.

It shows the status, each scan with its place and exact time on a map, the expected delivery day, the delivered time and, while the parcel waits, the FedEx location's name and address. It checks every 10 minutes by day, every 5 minutes once the parcel is out for delivery, and every hour or two overnight, and can notify you at every scan, at important steps or on delivery day only. It drops your name and address, the shipper, the signature, weight and dimensions. Scans show in FedEx's English, except the delivered one: FedEx's line names who signed, so Peek shows "Delivered".

Most Ground Economy parcels are delivered by FedEx itself. When FedEx says the [US Postal Service](carrier:usps) has one, Peek follows it there ("Delivery with USPS").

## Questions about FedEx tracking

### Do FedEx tracking numbers start with 1Z?

No. A number starting `1Z` is a [UPS](carrier:ups) number. FedEx's are digits only.

### Why does FedEx tracking show no delivery date?

FedEx shows an estimated date and time once its courier has the parcel, so `Label created` alone often has none. If a date disappears later, FedEx is updating it.

### Can I get proof of delivery for a FedEx parcel?

Yes, from the tracking page, without an account. FedEx Express keeps signed proof for up to 18 months; without a signature, a home delivery shows a photo instead.

:::sources
- [FedEx UK: What does my tracking status mean?](https://www.fedex.com/en-gb/customer-support/faq/receiving/tracking-questions/fedex-tracking-status-meaning.html) – status names and meanings, local languages
- [FedEx UK: What does the status 'Exception' mean?](https://www.fedex.com/en-gb/customer-support/faq/sending/tracking-questions/what-is-a-fedex-delivery-exception.html) – exception causes
- [FedEx: Track a package](https://www.fedex.com/fedextrack/) – DELIVERED headline, Report missing package
- [FedEx UK: Customer Support](https://www.fedex.com/en-gb/customer-support.html) – 12 digits on the label, Ask FedEx, no phone
- [FedEx UK: How can I track my parcel without a tracking number?](https://www.fedex.com/en-gb/customer-support/faq/receiving/tracking-questions/how-to-track-a-package-without-a-tracking-number.html) – 12 or 13 digits, email or SMS, track by reference
- [FedEx UK: Where is my package?](https://www.fedex.com/en-gb/customer-support/faq/receiving/tracking-questions/where-is-my-package.html) – tracking page, app
- [FedEx UK: When will my package arrive?](https://www.fedex.com/en-gb/customer-support/faq/sending/tracking-questions/when-will-my-fedex-package-arrive.html) – estimated date, disappearing estimate
- [FedEx UK: What happens when no one is at my home or office?](https://www.fedex.com/en-gb/customer-support/faq/receiving/delivery-options-fedex/fedex-not-home.html) – Missed Delivery Card, options, restrictions
- [FedEx UK: How can I reschedule a delivery?](https://www.fedex.com/en-gb/customer-support/faq/receiving/delivery-options-fedex/reschedule-fedex-delivery.html) – Manage delivery, 4:00 am, 5 business days, storage fees
- [FedEx UK: Can I change the delivery address of my package?](https://www.fedex.com/en-gb/customer-support/faq/receiving/delivery-options-fedex/change-fedex-delivery-address.html) – redirecting, fees, no other country
- [FedEx UK: FedEx Delivery Manager](https://www.fedex.com/en-gb/shipping-tools/deliverymanager.html) – sender enables it, free changes, scam warning
- [FedEx UK: Where can I find the nearest FedEx location?](https://www.fedex.com/en-gb/customer-support/faq/sending/pickups/nearest-drop-off.html) – location finder, opening hours
- [FedEx UK: Can I get a proof of delivery?](https://www.fedex.com/en-gb/customer-support/faq/receiving/tracking-questions/fedex-proof-of-delivery.html) – 18 months, photo, no account
- [FedEx UK: What happens if my package was delivered to the wrong location?](https://www.fedex.com/en-gb/customer-support/faq/receiving/delivery-options-fedex/wrong-address.html) – neighbours, report missing
- [FedEx UK: Why is my package stuck at customs?](https://www.fedex.com/en-gb/customer-support/faq/receiving/tracking-questions/why-is-my-package-stuck-at-customs.html) – customs holds, documents
- [FedEx UK: Who pays duties and taxes?](https://www.fedex.com/en-gb/customer-support/faq/duties-taxes-imported-goods/paying-duties-taxes/who-pays-custom-duties-taxes.html) – recipient pays by default
- [FedEx UK: Why didn't FedEx advise me of duties and taxes at the time of delivery?](https://www.fedex.com/en-gb/customer-support/faq/duties-taxes-imported-goods/problems-with-customs/fedex-advise-duties-taxes-delivery.html) – bill after delivery
- [FedEx UK: Customs duties and taxes guide](https://www.fedex.com/en-gb/billing/duty-tax.html) – disbursement fee, held until payment
- [FedEx UK: How can I pay the duties and taxes?](https://www.fedex.com/en-gb/customer-support/faq/duties-taxes-imported-goods/paying-duties-taxes/how-to-pay-duties-taxes.html) – payment portal, unique link
- [FedEx UK: Conditions of Carriage, January 2026](https://www.fedex.com/en-gb/conditions-of-carriage.html) – neighbour, safe place, claim deadlines, inspection
- [FedEx UK: Where can I make a complaint?](https://www.fedex.com/en-gb/customer-support/faq/complaints-and-refunds/fedex-complaint.html) – contact form
- [FedEx US: I got a FedEx door tag, now what?](https://www.fedex.com/en-us/delivery-manager/door-tag.html) – door tag, redelivery, pickup with ID, redirecting
- [FedEx US: Contact Us](https://www.fedex.com/en-us/customer-support/contact-us.html) – 1.800.463.3339
- [FedEx US: How to Recognize and Help Prevent Fraud and Scams](https://www.fedex.com/en-us/trust-center/report-fraud.html) – .shop warning, duty links, abuse@fedex.com
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads from FedEx
:::
