---
title: DHL eCommerce tracking: GM numbers, statuses and contacts
description: Track a DHL eCommerce parcel (formerly DHL Global Mail): the GM number, what each status means, who delivers it and who to ask when it stalls.
slug: dhl-ecommerce-tracking
published: 2026-10-11
updated: 2026-10-27
---

DHL eCommerce is the DHL division that ships parcels for high-volume online shops. It sorts your parcel at its own distribution centres, then hands it to a delivery company in your country: [USPS](carrier:usps) in the US, elsewhere the local post or another carrier. Track it with the number in the shop's shipping email on DHL eCommerce's Webtrack site, or paste it in the box above.

## DHL eCommerce tracking number format

DHL says its tracking IDs are 10 to 39 letters and digits, and that the shop or sender gives you one, usually in the order confirmation or shipping notification. If the shop sent you a tracking link, DHL asks you to use it. No number at all? Ask the shop.

| Shape (invented) | What it is |
| --- | --- |
| `GM0000000000000000` | GM and 16 to 18 digits: the GM number, which DHL also calls the customer confirmation number. |
| `9261234567890123456789` | 22 digits starting 9261 or 9361: a USPS-style number, used for parcels within the US. |
| `0000000000000000` | 16 or 17 digits: another DHL eCommerce shape. |
| `0031000000000000000000000000000` | 0031 and 27 digits: another DHL eCommerce shape. |

Webtrack's "Delivery by" line names the last-mile carrier, whose own number may appear later.

Lookalikes: 16 digits can also be TNT's, Evri's, Canada Post's or Correos Express's, and a 9261 or 9361 number USPS's. A 14-digit number belongs to [DHL eCommerce UK](carrier:dhl-ecommerce-uk), a separate network with its own tracking, a `JVGL` number to [DHL eCommerce Netherlands](carrier:dhl-ecommerce-nl), and [DHL Express](carrier:dhl-express) and [DHL Paket](carrier:dhl) use other shapes again. More in [tracking number formats](guide:tracking-number-formats).

## DHL eCommerce tracking statuses explained

Webtrack shows a one-line status (`En Route`, `Delivered`, `Exception`…) above a "Tracking History" of scans in capitals. The scans people see most, in the order a parcel meets them:

| DHL eCommerce says | What it means for you |
| --- | --- |
| `LABEL CREATED` | The shop has made the label. DHL eCommerce doesn't have the parcel yet. |
| `EN ROUTE TO DHL ECOMMERCE DISTRIBUTION CENTER OR AWAITING PROCESSING` | It's on its way to DHL, or still waiting at the shop. |
| `PACKAGE RECEIVED AT DHL ECOMMERCE DISTRIBUTION CENTER` | DHL eCommerce has it. |
| `PROCESSED` | It has been sorted at the distribution centre. |
| `DEPARTURE ORIGIN DHL ECOMMERCE FACILITY` | It has left the first DHL eCommerce facility. |
| `ARRIVAL DESTINATION DHL ECOMMERCE FACILITY` | It has reached the DHL eCommerce facility near you. |
| `CUSTOMS CLEARANCE` | It's in customs. |
| `TENDERED TO DELIVERY SERVICE PROVIDER, ALLOW 1-3 DAYS FOR UPDATES FOR PACKAGES WITHIN THE US` | It has been handed to the carrier that delivers it. For US parcels, updates can pause for up to 3 days. |
| `OUT FOR DELIVERY` | It's with the driver or mail carrier today. |
| `DELIVERY ATTEMPT FAILED` | Nobody could take it. Look for a notice from the delivering carrier. |
| `DELIVERED` | Delivered. `[IN/AT MAILBOX]` after it means it went in your mailbox. |
| `RETURNED TO SENDER` | It's going back to the shop. |

Webtrack's English (UK) setting words a few scans differently, such as `PROCESSED THROUGH SORT FACILITY`. US parcels also show USPS's scans after the handover, such as `ARRIVAL AT POST OFFICE`.

Two scans confuse people. `DELIVERED TO RETURNS AGENT OR COLLEGE/UNIVERSITY FOR FINAL DELIVERY` means it went to a go-between, such as a campus mailroom, which passes it on to you. `Picked up at parcelshop` means the sender dropped it off at a shop. Nothing is waiting there for you.

## How DHL eCommerce delivers: USPS, local posts and missed parcels

Within the US, DHL eCommerce's Webtrack FAQ says USPS delivers the parcel at the time your mail usually comes. For international parcels, the "local delivery service provider" takes over, which "can be either the local post office or another delivery company".

So notices, pickup counters, holding times and redelivery follow that carrier's rules. DHL asks you to check your mailbox for the parcel or a notice, and to give any delivery instructions to USPS or the local carrier.

Changing the address: Webtrack's FAQ says that once a parcel leaves the shop, DHL eCommerce can't change it, so ask the shop.

## DHL eCommerce parcel delayed, lost or returned

**No scans yet.** DHL says tracking events show up 24 to 48 hours after the parcel is processed at its facility. If Webtrack still finds nothing, it suggests checking the number, then asking the shop.

**No update for days.** DHL names weather, holidays, customs, strikes and backlogs. For US parcels, DHL allows 1 to 3 days for updates after the handover to USPS. See [why tracking stops updating](guide:tracking-not-updating).

**Not arrived.** DHL's rule: if the parcel hasn't come within 10 days of the expected delivery date, contact the shop or sender. They hold the contract with DHL and can open an investigation for a lost item. DHL publishes no claim deadline for recipients.

**Customs.** DHL says clearance times vary by country and DHL can't influence them. If customs needs more information, it contacts you. [Parcels held at customs](guide:customs) explains what you may pay.

**Returned.** DHL's Asian portal names the usual reasons: nobody home, a parcel unclaimed at the post office or an incomplete address.

## DHL eCommerce customer service and contact number

DHL says to ask the shop first: only the sender can give you a number or explain why it isn't moving. Webtrack's "Contact Customer Service" page, read on 12 October 2026, lists a card per country:

- **United States and Canada** (parcels sent from the US): +1 317-554-5191, Monday to Friday 8 AM to 8 PM EST (Canada's card says 8 AM to 9 PM ET).
- **Germany:** the card sends you to [DHL's German customer service](https://www.dhl.de/de/privatkunden/hilfe-kundenservice.html), Monday to Sunday 7 AM to 8 PM.
- **Ireland:** 818 370050 (from within Ireland), Monday to Friday 8 AM to 6 PM.
- **Australia:** +61 37 0356818, Monday to Friday 8 AM to 6 PM.
- **Austria, Switzerland and the rest of the world:** +49 228 92 95 95 95, Monday to Friday 9 AM to 6 PM (Austria's card says 8 AM).
- **United Kingdom:** the card lists DHL eCommerce UK's line, 024 7693 7770. See the [DHL eCommerce UK](carrier:dhl-ecommerce-uk) page.

Webtrack's "Contact Support" form says someone should be in touch "within 24-72 hours". Call costs aren't stated.

Doubtful message? DHL eCommerce says "DHL does not request direct payment from online consumers". If a number in an email gives no result on DHL's tracking, DHL says that email was not sent by DHL.

## Tracking a DHL eCommerce parcel with Peek

[Peek](/) names DHL eCommerce at once for a GM number. For shared shapes, it asks the likely carriers at the same time (TNT for 16 digits, plus Evri UK, Canada Post and Correos Express when the last digit checks out; Colis Privé for 17 digits; Austrian Post for a 9261 or 9361 number, then USPS's page last if neither knows it). If only DHL eCommerce knows it, you're told. If several brands do, you pick with "Change", which accepts any 5 to 40 letters and digits for DHL eCommerce. Links from webtrack.dhlglobalmail.com, ecommerceportal.dhl.com work too, and dhl.com tracking links when the number has a DHL eCommerce shape.

Webtrack is asked first. When it doesn't list the parcel, Peek opens DHL's global tracking page on dhl.com, which takes longer and can fail when DHL blocks automated visits.

You see the status, each scan with its town and country on a map, the expected delivery day when DHL gives one, the sender and the delivery time; from Webtrack, also the service, the weight and the delivering carrier. Peek leaves out your address, the shop's reference and the name of whoever signed. Scans keep DHL's US English wording, and every delivered scan shows as "Delivered".

When Webtrack names USPS, the parcel can be followed on USPS with its number, and its page says "Delivery with USPS". Parcels answered by DHL's global page (Europe, Asia) aren't followed to a local post. You can be notified of every scan, important steps or "Delivery day only".

## Questions about DHL eCommerce tracking

### Is DHL eCommerce the same as DHL Express?

No. DHL lists them as separate divisions, and DHL eCommerce usually leaves the final delivery to a local post or carrier.

### Is DHL eCommerce delivered by USPS?

Within the US, yes: DHL's Webtrack FAQ says USPS makes the final delivery. Abroad, the local post or another delivery company does.

### How long does DHL eCommerce take to deliver from the US to the UK?

It depends on the service the shop chose. DHL quotes an average of 4 to 6 days for Parcel International Direct to the UK, and 4 to 8 business days for Parcel International Standard to Europe.

### Is DHL eCommerce legit?

Yes, it's one of DHL's four divisions, formerly known as DHL Global Mail. DHL says it doesn't ask online shoppers to pay.

:::sources
- [DHL eCommerce: Webtrack tracking](https://webtrack.dhlecs.com/) – tracking site, status labels, details box, 10 numbers, contact form, email updates
- [DHL eCommerce: Webtrack Contact Customer Service](https://webtrack.dhlecs.com/support/customer-service) – country cards, phone numbers, hours, FAQ on USPS delivery, delays, address changes, instructions
- [DHL: Track eCommerce, FAQ](https://www.dhl.com/global-en/home/tracking/tracking-ecommerce.html) – 10 to 39 characters, where to find the number, 24 to 48 hours, 10-day rule, customs
- [DHL eCommerce: Customer Service Global](https://www.dhl.com/global-en/home/our-divisions/ecommerce/customer-service.html) – country list, US and UK contacts
- [DHL eCommerce: Contact DHL eCommerce About A Shipment](https://www.dhl.com/ch-en/home/customer-service/contact-dhl-ecommerce-about-a-shipment.html) – only the sender can give a number or explain a stalled one
- [DHL eCommerce: United States](https://www.dhl.com/us-en/home/ecommerce.html) – what DHL eCommerce is, DHL's divisions
- [DHL eCommerce: International Shipping Service](https://www.dhl.com/us-en/home/ecommerce/shipping-services/international-shipping-services.html) – products, transit times to Europe and Canada
- [DHL eCommerce: Parcel International Direct](https://www.dhl.com/content/dam/dhl/local/us/dhl-ecommerce/documents/pdf/us-parcel-international-direct_2026.pdf.coredownload.pdf) – 4 to 6 days to the UK
- [DHL Developer: References (DHL eCommerce Americas)](https://developer.dhl.com/api-reference/references-dhl-ecommerce-americas) – GM number, customer confirmation number, USPS number for US parcels
- [DHL eCommerce: Tracking (Asia)](https://ecommerceportal.dhl.com/track/pages/customer/trackItNowPublic.xhtml?locale=en) – reasons for returns
- [DHL eCommerce: Portal login](https://ecommerceportal.dhl.com/) – no payment requests, checking a doubtful number
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads and shows
:::
