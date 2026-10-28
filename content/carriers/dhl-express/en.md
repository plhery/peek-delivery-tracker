---
title: DHL Express tracking: waybill numbers and statuses
description: Track a DHL Express parcel: where to find the 10-digit waybill number, what DHL Express scans mean, customs charges, missed deliveries and lost parcels.
slug: dhl-express-tracking
published: 2026-10-13
updated: 2026-10-27
---

Paste a DHL Express waybill number, or a MyDHL+ tracking link, in the box above to follow the parcel. DHL's own tracking is on its website (MyDHL+) and in the DHL Express mobile app.

## DHL Express tracking number: where to find the waybill

DHL Express calls its tracking number the waybill number. Look in the shop's shipping email or on your order page; a MyDHL+ tracking link carries it too, after `id=`. Still nothing? See [where to find a tracking number](guide:find-tracking-number).

| Shape (invented) | What it is |
| --- | --- |
| `1234567891` | The waybill: 10 digits, the number that tracks the whole shipment. The last digit is a check digit. |
| `JD014600001234567890` | A piece number: `JD01` and 16 digits, printed as `(J)JD…` under the label's lower barcode. Each parcel in a shipment has its own, and it leads back to the waybill. |

Ten digits also fit [Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis), [TIPSA](carrier:tipsa), Estafeta, [Packeta](carrier:packeta), [DHL eCommerce Iberia](carrier:dhl-ecommerce-es) and [DHL Paket](carrier:dhl), so check which carrier the shop's email names.

DHL reuses waybill numbers: a number can come with an earlier shipment's scans, which Peek ignores.

## What DHL Express tracking statuses mean

These are scan wordings Peek reads from DHL Express, in DHL's English, roughly in the order a parcel meets them, with the status Peek shows for each. Many end with the facility, for example `Processed at` followed by a town and country.

| DHL Express's scan | Peek shows | What it means |
| --- | --- | --- |
| `Shipment information received`, `Supplementary information has been received and merged with applicable shipment data` | Label created | The sender has sent DHL the shipment's details. |
| `Shipment picked up`, `Shipment accepted` | Received by carrier | DHL Express has the parcel. |
| `Processed at`, `Arrived at DHL Sort Facility`, `Shipment has departed from a DHL facility`, `Shipment is in transit to destination` | In transit | It's moving through DHL's network. |
| `Scheduled to depart on the next planned movement` | In transit | It's waiting at a facility for its next truck or flight. |
| `Customs clearance status updated. Note - The Customs clearance process` …, `Processed for clearance at`, `Clearance event` | At customs | It's going through customs (see below). |
| `Payment is received and recorded for shipment related fees` | At customs | The duties or fees have been paid; it stays at customs until clearance is complete. |
| `Clearance processing complete at` | In transit | Customs has finished with it and it's moving again. |
| `Arrived at DHL Delivery Facility` | In transit | It has reached the DHL facility that delivers it. |
| `Shipment is out with courier for delivery` | Out for delivery | A courier is bringing it to you. |
| `Delivery attempted but no response at Consignee address`, `Delivery attempt could not be completed` | Delivery attempted | The delivery didn't go ahead. The consignee is you, the recipient. |
| `Shipment is scheduled for delivery` | In transit | A delivery is booked for a later round, for example after a missed attempt. |
| `Awaiting collection by the consignee` | Ready for pickup | It's held at a DHL facility for you to collect. |
| `Delivered` | Delivered | It's been delivered. |
| `Shipment is on hold` | Needs attention | DHL has stopped the shipment for now. |
| `Shipment arrived at incorrect facility. It will be sent to the correct destination` | Needs attention | It was sorted to the wrong facility and goes on to the right one, late. |
| `Returned to shipper` | Returned to sender | It's going back to the sender. |

## Missed DHL Express delivery

After a missed attempt the tracking reads `Delivery attempted but no response at Consignee address` or `Delivery attempt could not be completed`. A later `Shipment is scheduled for delivery` means a new attempt is booked; `Awaiting collection by the consignee` means the parcel is waiting at a DHL facility for you to collect it. To rearrange the delivery, contact DHL Express through its website or app: Peek can't change a delivery. More in [missed deliveries](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

## DHL Express parcel held at customs

A parcel from outside the UK, or from outside the EU for Ireland, may owe import charges. The tracking shows `Customs clearance status updated…`, `Processed for clearance at` or `Clearance event` while it's at customs, `Payment is received and recorded for shipment related fees` once the charges are paid, and `Clearance processing complete at` when customs lets it go.

- **UK**: GOV.UK says the courier contacts you with a bill for any VAT, duty or delivery charges, and normally holds the parcel for about 3 weeks. Unpaid, it goes back to the sender.
- **Ireland**: since 1 July 2026, most parcels worth €150 or less from outside the EU owe €3 customs duty per item. If the shop didn't collect it at checkout, Revenue says the courier will ask you for it before delivering. The courier can add its own administration fee, so check that cost with it.

More in [parcels held at customs](guide:customs).

## DHL Express parcel late, lost or marked delivered

If the tracking has stopped moving, or shows `Shipment is on hold`, see [tracking that isn't updating](guide:tracking-not-updating).

If you live in Great Britain or Ireland and bought from a shop, the shop is responsible for getting the parcel to you, even when DHL Express carries it:

- **England, Scotland and Wales**: Citizens Advice says the seller should chase the courier, not you. If the item hasn't arrived within 30 days of buying it, you can cancel and ask for your money back. If it was left somewhere you didn't agree to and went missing, the seller has to sort it out.
- **Ireland**: the CCPC says that without an agreed date, the order must arrive within 30 days of purchase. If it doesn't, arrange a new delivery date with the business; if that passes too, you're entitled to a full refund. A parcel damaged or lost in transit should be replaced, and the business stays responsible until you, or someone you nominate, have the parcel in hand.

Marked `Delivered` but not there? DHL's tracking page can show the signature, which Peek doesn't keep. Check the tracking on DHL's website, then contact the shop. More in [delivered but not received](guide:delivered-not-received) and [lost parcels](guide:lost-parcel).

## Tracking DHL Express parcels with Peek

[Peek](/) recognises a 10-digit waybill that passes DHL Express's check digit, puts DHL Express first and asks it at once along with other carriers whose numbers fit, such as [ACS Courier](carrier:acs-courier), Geniki Taxydromiki and DHL eCommerce Iberia, naming whoever has the parcel. ACS and Geniki share the check digit, so if one of them knows the number too, you choose. A 10-digit number that fails the check isn't offered to DHL Express: pick it with "Change". `JD01` piece numbers and `JJD0146` labels (`JJD014612345678901234`) are named DHL Express at once and traced to their waybill. Links from mydhl.express.dhl work too. For a dhl.com link, paste the waybill itself instead; if a parcel ends up under "DHL", switch it to "DHL Express" with "Change".

Peek reads DHL Express through the service behind its mobile app, or else opens dhl.com's tracking page in a browser on its server, which is slower and can be refused; then tracking services fill in. It checks every 10 minutes by day, every 2 to 5 minutes once out for delivery, and every hour or two overnight, and can notify you at every scan, at important steps or on delivery day only.

It shows the status, each scan with its facility on a map and the destination country. Scan times are read on the clock of the facility's town or, when that isn't enough, of the scans around it. Scans read in DHL's English, except `Delivered`, `Shipment picked up` and `Shipment information received`, which appear in Peek's own words. Peek drops the signature, ignores old scans from a recycled waybill, and shows no expected delivery day, weight or Service Point. It can't pay duties or change a delivery.

## Questions about DHL Express tracking

### Is the DHL waybill number the same as the tracking number?

Yes. For DHL Express the 10-digit waybill number tracks the whole shipment; a `JD01` piece number belongs to a single parcel and leads back to the waybill.

### Is DHL the same as DHL Express?

Not in Peek: DHL Express is its own carrier, separate from [DHL Paket](carrier:dhl) and the DHL eCommerce carriers, each with its own numbers and tracking. A parcel without a 10-digit waybill or a `JD01` piece number usually belongs to one of those.

### Why is my DHL waybill number invalid?

Often it's another DHL carrier's number: DHL Express waybills have exactly 10 digits, and the last one is a check digit, so a typo usually fails it. A new waybill can also show only `Shipment information received` until DHL has picked up the parcel.

### Can I track DHL Express without a waybill number?

Not in Peek: it needs the 10-digit waybill or a `JD01` piece number from the label. Ask the shop or the sender for the waybill.

### Is a DHL Express text asking me to pay a scam?

It can be. Citizens Advice says a message asking you to pay to get a parcel or to reschedule its delivery is usually a scam. Don't tap the link: look the waybill up on DHL's website or in Peek instead. In the UK, forward the text to 7726 for free. In the US, the FTC says to contact the shipping company through a website or phone number you know is real. More in [delivery scam texts](guide:delivery-scam-texts).

:::sources
- [Citizens Advice: If something you ordered hasn't arrived](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – seller's responsibility, 30 days, agreed place, scam messages (same advice for England, Scotland and Wales)
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – the courier's bill, about 3 weeks, return to sender
- [GOV.UK: Report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – forward scam texts to 7726
- [CCPC: Your consumer rights if your order is not delivered](https://www.ccpc.ie/consumer-advice/consumer-rights/buying-goods/non-delivery-of-goods) – 30 days, new date, refund, responsible until you have it (Ireland)
- [Revenue: Buying goods from outside the EU](https://www.revenue.ie/en/customs/individuals/buying-online-personal/outside-eu.aspx) – €3 per item since 1 July 2026, paid to the courier before delivery
- [Revenue: Buying goods online for personal use](https://www.revenue.ie/en/customs/individuals/buying-online-personal/index.aspx) – couriers' administration fee, which can vary
- [FTC: Fake shipping notification emails and text messages](https://consumer.ftc.gov/consumer-alerts/2023/12/fake-shipping-notification-emails-text-messages-what-you-need-know-holiday-season) – contact the shipping company through a website or number you know
- [Universal Parcel Scraper: DHL Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-express) – waybill and piece number shapes, check digit, 10-digit lookalikes, reused waybills, DHL Express's scan wordings and Peek's status for each, signature shown by DHL, what Peek reads from DHL Express
:::
