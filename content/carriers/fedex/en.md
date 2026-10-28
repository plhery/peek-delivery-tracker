---
title: FedEx tracking: numbers, statuses and missed deliveries
description: Track a FedEx parcel: what the 12-digit tracking number looks like, what Delivery exception and other scans mean, customs charges and your rights if it's lost.
slug: fedex-tracking
published: 2026-10-13
updated: 2026-10-28
---

Paste a FedEx tracking number, or a FedEx tracking link, in the box above to follow the parcel. FedEx's own tracking is on fedex.com and in the FedEx Mobile app.

## FedEx tracking number: where to find it and how many digits

Look in the shop's shipping email or on your order page. A fedex.com tracking link carries the number too, after `trknbr=` or `tracknumbers=`. Still nothing? See [where else to find a tracking number](guide:find-tracking-number).

| Shape (invented) | What it is |
| --- | --- |
| `123456789012` | 12 digits: a FedEx tracking number. The last digit is a check digit worked out from the 11 before it. |
| `123456789012343` | 15 digits: a shape Peek also files under FedEx. |
| `61290000000000000014` | 20 digits starting `6129` or `7489`: FedEx Ground Economy (formerly SmartPost) in the US. |

Twelve digits also fit [DPD France](carrier:dpd-fr), [Mondial Relay](carrier:mondial-relay), [BRT](carrier:brt) and several Asian couriers, and fifteen fit DPD France and BRT too. If fedex.com doesn't know the number, check which carrier the shop's email names.

## What FedEx tracking statuses mean, including delivery exception

These are scan wordings Peek reads from FedEx, in FedEx's English, roughly in the order a parcel meets them, with the status Peek shows for each.

| FedEx's scan | Peek shows | What it means |
| --- | --- | --- |
| `Shipment information sent to FedEx` | Label created | The sender has sent FedEx the shipment's details. |
| `Picked up`, `Tendered at FedEx location` | Received by carrier | FedEx has it: a courier collected it, or the sender handed it in at a FedEx location. |
| `Arrived at FedEx location`, `Departed FedEx location`, `At local FedEx facility`, `On the way` | In transit | It's moving through FedEx's network. |
| `Clearance in progress`, `Clearance delay` | At customs | It's going through customs (see below). |
| `International shipment release - Import` | In transit | Customs has released it into the destination country. |
| `On FedEx vehicle for delivery` | Out for delivery | It's on the van for delivery. |
| `Delivery exception`, `Customer not available or business closed` | Delivery attempted | The delivery didn't go ahead. |
| `Ready for pickup` | Ready for pickup | It's waiting for you at a pickup location. |
| `Delivered` | Delivered | It's been delivered. |
| `Shipment exception` | Needs attention | FedEx reports a problem with the shipment. |
| `Returning to shipper` | Returned to sender | It's going back to the sender. |

Peek files `Delivery exception` with failed delivery attempts. The scans can say why: `Customer not available or business closed` means nobody could take the parcel, and `Local delivery restriction - Delivery not attempted` means a local restriction kept the driver from trying. When FedEx flags a parcel as delayed, Peek shows "Needs attention", except while the latest scan is `Package received after FedEx cutoff`: FedEx got the parcel after the day's cutoff, and it leaves on the next business day.

## Missed FedEx delivery and pickup locations

If you ask FedEx to hold the parcel at a pickup location, a scan can read `Delivery option requested` while it travels there, then `Ready for pickup` once it has arrived. Peek then shows the location's name and address if FedEx gives them: for a hold asked for after the parcel was sent, FedEx's page shows visitors who aren't signed in only the town. FedEx's page may label the last leg `Heading to pickup location`, which Peek counts as out for delivery. You arrange such changes with FedEx, not in Peek. More in [missed deliveries](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

## FedEx parcel held at customs

A parcel from outside the UK, or from outside the EU for Ireland, may owe import charges. The tracking shows `Clearance in progress` or `Clearance delay` while it's at customs, and `International shipment release - Import` once customs lets it go.

- **UK**: GOV.UK says the courier contacts you with a bill for any VAT, duty or delivery charges, and normally holds the parcel for about 3 weeks. Unpaid, it goes back to the sender.
- **Ireland**: since 1 July 2026, most parcels worth €150 or less from outside the EU owe €3 customs duty per item. If the shop didn't collect it at checkout, Revenue says the courier will ask you for it before delivering, and couriers charge their own administration fee on top.

More in [parcels held at customs](guide:customs).

## FedEx parcel late, lost or marked delivered

If the tracking has stopped moving, see [tracking that isn't updating](guide:tracking-not-updating).

If you live in Great Britain or Ireland and bought from a shop, the shop is responsible for getting the parcel to you, even when FedEx carries it:

- **England, Scotland and Wales**: Citizens Advice says the seller should chase the courier, not you. If the item hasn't arrived within 30 days of buying it, you can cancel and ask for your money back. If it was left somewhere you didn't agree to and went missing, the seller has to sort it out.
- **Ireland**: the CCPC says that without an agreed date, the order must arrive within 30 days of purchase. If it doesn't, arrange a new delivery date with the business; if that passes too, you're entitled to a full refund. A parcel damaged or lost in transit should be replaced, and the business stays responsible until you, or someone you nominate, have the parcel in hand.

Marked `Delivered` but not there? FedEx's delivery scan names who signed, which Peek doesn't keep. Check the tracking on fedex.com, then contact the shop. More in [delivered but not received](guide:delivered-not-received) and [lost parcels](guide:lost-parcel).

## Tracking FedEx parcels with Peek

[Peek](/) recognises FedEx's 12- and 15-digit numbers when their check digit is right. Other carriers share those shapes, so the parcel is often named by a tracking service or after a short wait. A number that fails the check isn't offered to FedEx: pick the carrier with "Change". Ground Economy's 20-digit numbers are named FedEx at once, scanned label barcodes are read for the number inside, and fedex.com links with `trknbr=` or `tracknumbers=` work too. FedEx's 13-digit domestic numbers from Poland are followed as [FedEx Poland](carrier:fedex-pl).

Peek opens fedex.com's tracking page in a browser on its own server. When FedEx refuses, or asks for recipient verification, tracking services fill in.

It shows the status, each scan with its place and exact time on a map, the expected delivery day, the delivered time and, while the parcel waits, the FedEx location's name and address when FedEx gives them. It checks every 10 minutes by day, every 5 minutes once the parcel is out for delivery, and every hour or two overnight, and can notify you at every scan, at important steps or on delivery day only. It drops your name and address, the shipper, the signature, weight and dimensions. Scans show in FedEx's English, except the delivered one: FedEx's line names who signed, so Peek shows "Delivered".

Most Ground Economy parcels are delivered by FedEx itself. When FedEx says the [US Postal Service](carrier:usps) has one, Peek follows it there ("Delivery with USPS").

## Questions about FedEx tracking

### Do FedEx tracking numbers start with 1Z?

No. A number starting `1Z` is a [UPS](carrier:ups) number. FedEx's are digits only.

### Why does my FedEx tracking only say Shipment information sent to FedEx?

That first scan only means the sender has sent FedEx the shipment's details. The tracking moves on once FedEx has the parcel: `Picked up` or `Tendered at FedEx location`. If it was handed in after the day's cutoff, the scan reads `Package received after FedEx cutoff` and the parcel leaves on the next business day.

### Is a FedEx delivery text a scam?

It can be. Citizens Advice says a message asking you to pay to get a parcel or to reschedule its delivery is usually a scam, and delivery companies won't ask you to pay through a link in a text or email. Don't tap the link: check the tracking number on fedex.com or in Peek instead. In the UK, forward the text to 7726 for free. In the US, the FTC says to contact the shipping company through a website or phone number you know is real. More in [delivery scam texts](guide:delivery-scam-texts).

:::sources
- [Citizens Advice: If something you ordered hasn't arrived](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – seller's responsibility, 30 days, agreed place, scam messages (same advice for England, Scotland and Wales)
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – the courier's bill, about 3 weeks, return to sender
- [GOV.UK: Report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – forward scam texts to 7726
- [CCPC: Your consumer rights if your order is not delivered](https://www.ccpc.ie/consumer-advice/consumer-rights/buying-goods/non-delivery-of-goods) – 30 days, new date, refund, responsible until you have it (Ireland)
- [Revenue: Buying goods from outside the EU](https://www.revenue.ie/en/customs/individuals/buying-online-personal/outside-eu.aspx) – €3 per item since 1 July 2026, paid to the courier before delivery
- [Revenue: Buying goods online for personal use](https://www.revenue.ie/en/customs/individuals/buying-online-personal/index.aspx) – couriers' administration fee
- [FTC: Fake shipping notification emails and text messages](https://consumer.ftc.gov/consumer-alerts/2023/12/fake-shipping-notification-emails-text-messages-what-you-need-know-holiday-season) – contact the shipping company through a website or number you know
- [Universal Parcel Scraper: FedEx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex) – number shapes, check digits, FedEx's scan wordings, what Peek reads from FedEx
:::
