---
title: UPS tracking: numbers, statuses and missed deliveries
description: Track a UPS parcel: what the 1Z tracking number looks like, what Exception and other UPS scans mean, Access Points, customs charges and your rights if it's lost.
slug: ups-tracking
published: 2026-10-13
updated: 2026-10-27
---

UPS (United Parcel Service) tracking numbers usually start with `1Z`. Paste the number, or a UPS tracking link, in the box above to follow the parcel; UPS's own tracking page is on ups.com.

## UPS tracking number: where to find it and what it looks like

The shop that sent the parcel gives you the number, usually in its shipping email or on your order page. A ups.com tracking link carries it too, after `tracknum=`. Still nothing? See [where else to find a tracking number](guide:find-tracking-number).

| Shape (invented) | What it is |
| --- | --- |
| `1Z1234567812345674` | UPS's parcel number: `1Z` and 16 letters and digits, 18 characters in all. The last digit is a check digit worked out from the 15 characters before it. |
| `H1234567890` | A letter (H, J, K or V) and 10 digits. It can be a UPS number, but other carriers use the same shape. |

## What UPS tracking statuses mean, including Exception

UPS's tracking page has a progress bar (`Label Created`, `We Have Your Package`, `On the Way`, `Out for Delivery`, `Delivered`) and the scans underneath. These are the scan wordings Peek reads from UPS, in English, roughly in the order a parcel meets them, with the status Peek shows for each.

| UPS's scan | Peek shows | What it means |
| --- | --- | --- |
| `Shipper created a label, UPS has not received the package yet.` | Label created | The sender has made a label. UPS doesn't have the parcel yet. |
| `Drop-Off`, `Pickup Scan` | Received by carrier | UPS has it: the sender handed it in at an access point, or UPS collected it. The first `Arrived at Facility` counts here too. |
| `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility` | In transit | It's moving through UPS's network. `Export Scan` and `Import Scan` mark a border. |
| `Your package is prepared for clearance.` | At customs | It's going through customs. |
| `Duties or taxes are due on this package.` | At customs | You have to pay import charges before UPS delivers it (see below). |
| `Loaded on Delivery Vehicle`, `Out For Delivery Today` | Out for delivery | A driver has it. |
| `The receiver was not available at the time of the final delivery attempt.` | Delivery attempted | The last try failed. |
| `Delivered to UPS Access Point™`, `UPS Access Point™ possession` | Ready for pickup | It's waiting for you at a UPS Access Point. |
| `DELIVERED` | Delivered | Delivered, or collected by you from an access point. |
| `Return to Sender` | Returned to sender | It's going back to the sender. |

`Exception` is UPS's generic status, and it can mean a mere delay. Read the scan under it: `We're sorry this package may experience a temporary delay.` is just that, while `The company or receiver name is incorrect. This will delay delivery.` means the delivery waits on your details (Peek shows "Needs attention"). Peek goes by the newest scan, not the generic status.

## Missed UPS delivery and Access Points

When the driver can't deliver, the scan says the receiver wasn't available or the business was closed, and UPS tries again. After the final attempt, a later scan shows what happens next: UPS holds the parcel, takes it to an access point or sends it back to the sender. More in [missed deliveries](guide:missed-delivery).

UPS can also take the parcel to a UPS Access Point. Once it's there, the scans read `Delivered to UPS Access Point™`, then `UPS Access Point™ possession`, and Peek shows the access point's name and address. Don't leave it too long: when an uncollected parcel goes back, the scan reads `The UPS Access Point™ location has prepared the package for return to UPS or pickup by UPS.` If an access point closes, UPS collects its parcels and the scan says delivery to another access point is pending. See [uncollected parcels](guide:uncollected-parcel).

## Customs charges on UPS parcels

A parcel from outside the UK, or from outside the EU for Ireland, may need import charges paid before UPS delivers it; the tracking then shows `Duties or taxes are due on this package.` and `Your package is being held until duties and taxes are paid. Awaiting customer action.`

- **UK**: GOV.UK says the courier contacts you with a bill for any VAT, duty or delivery charges, and normally holds the parcel for about 3 weeks. Unpaid, it goes back to the sender.
- **Ireland**: since 1 July 2026, most parcels worth €150 or less from outside the EU owe €3 customs duty per item. If the shop didn't collect it at checkout, Revenue says the courier will ask you for it before delivering, and couriers charge their own administration fee on top.

More in [customs charges](guide:customs).

## UPS parcel late, lost or marked delivered

If the tracking has stopped moving, see [tracking that isn't updating](guide:tracking-not-updating).

If you live in Great Britain or Ireland and bought from a shop, the shop is responsible for getting the parcel to you, even when UPS carries it:

- **England, Scotland and Wales**: Citizens Advice says the seller should chase the courier, not you. If the item hasn't arrived within 30 days of buying it, you can cancel and ask for your money back. If it was left somewhere you didn't agree to and went missing, the seller has to sort it out.
- **Ireland**: the CCPC says that without an agreed date, the order must arrive within 30 days of purchase. If it doesn't, arrange a new delivery date with the business; if that passes too, you're entitled to a full refund. A parcel damaged or lost in transit should be replaced, and the business stays responsible until you, or someone you nominate, have the parcel in hand, even if the courier says it was delivered.

Marked `DELIVERED` but not there? UPS's page can show where the parcel was left (`Left at: Front Door`, for example) and a proof of delivery, which Peek doesn't keep. Check ups.com, then contact the shop. More in [delivered but not received](guide:delivered-not-received) and [lost parcels](guide:lost-parcel).

## Tracking UPS parcels with Peek

[Peek](/) names UPS at once for a `1Z` number whose check digit is right; with a wrong one, UPS refuses it as mistyped. Links holding a `1Z` number work too. Peek looks up only `1Z` numbers at UPS: other shapes go to tracking services.

Peek opens ups.com's tracking page in a browser on its own server and reads the reply, every 10 minutes by day, every 5 minutes once the parcel is out for delivery, and every hour or two overnight. If UPS refuses, or only the status can be read, tracking services fill in the history. It shows the status, each scan on a map with its exact time, the scheduled delivery day (never a time window), the delivered time and, while the parcel waits, the Access Point's name and address. It can notify you at every scan, at important steps or on delivery day only.

It drops the signatory, your address, proof of delivery and where the parcel was left, and shows no delivery photo or weight. Scans appear in UPS's English; delivery reads "DELIVERED". When UPS hands a Ground Saver parcel to the [US Postal Service](carrier:usps) and names its number, Peek follows it there once USPS shows progress ("Delivery with USPS").

## Questions about UPS tracking

### Why is my UPS tracking number invalid?

Most often it was mistyped: one wrong character in a `1Z` number usually breaks its check digit, and UPS rejects it. Or the number belongs to another carrier: check which one the shop's email names.

### How long does UPS tracking take to show up?

The first scan, `Shipper created a label, UPS has not received the package yet.`, appears once the sender has made the label. It only moves on when the sender drops the parcel off or UPS picks it up (`Drop-Off`, `Pickup Scan`).

### Can I change a UPS delivery in Peek?

No: Peek only reads UPS's tracking. Changes you make with UPS show up in the scans, such as `The package will be delivered to the UPS Access Point™ location requested by the receiver.`

### Is a UPS delivery text a scam?

It can be. Citizens Advice says a message asking you to pay to get a parcel or to reschedule its delivery is usually a scam, and delivery companies won't ask you to pay through a link in a text or email. Real import charges show up in the tracking as customs scans, so check the number on ups.com or in Peek instead of tapping the link. In the UK, forward the text to 7726 for free. In the US, the FTC says to contact the shipping company through a website or phone number you know is real. More in [delivery scam texts](guide:delivery-scam-texts).

:::sources
- [Citizens Advice: If something you ordered hasn't arrived](https://www.citizensadvice.org.uk/consumer/somethings-gone-wrong-with-a-purchase/if-something-you-ordered-hasnt-been-delivered/) – seller's responsibility, 30 days, agreed place, scam messages (same advice for England, Scotland and Wales)
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – the courier's bill, about 3 weeks, return to sender
- [GOV.UK: Report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – forward scam texts to 7726
- [CCPC: Your consumer rights if your order is not delivered](https://www.ccpc.ie/consumer-advice/consumer-rights/buying-goods/non-delivery-of-goods) – 30 days, new date, refund, responsible until you have it (Ireland)
- [Revenue: Buying goods from outside the EU](https://www.revenue.ie/en/customs/individuals/buying-online-personal/outside-eu.aspx) – €3 per item since 1 July 2026, paid to the courier before delivery
- [Revenue: Buying goods online for personal use](https://www.revenue.ie/en/customs/individuals/buying-online-personal/index.aspx) – couriers' administration fee
- [FTC: Fake shipping notification emails and text messages](https://consumer.ftc.gov/consumer-alerts/2023/12/fake-shipping-notification-emails-text-messages-what-you-need-know-holiday-season) – contact the shipping company through a website or number you know
- [Universal Parcel Scraper: UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – number shapes, check digit, UPS's scan wordings, what Peek reads from UPS
:::
