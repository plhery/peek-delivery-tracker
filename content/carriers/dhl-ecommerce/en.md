---
title: DHL eCommerce tracking: GM numbers, statuses and contacts
description: Track a DHL eCommerce parcel (formerly DHL Global Mail): the GM number, what each status means, who delivers it and who to ask when it stalls.
slug: dhl-ecommerce-tracking
published: 2026-10-11
updated: 2026-10-27
---

DHL eCommerce, formerly DHL Global Mail, is DHL's parcel division for online shops. It sorts your parcel at its own distribution centres, then hands it to a delivery company in your country: [USPS](carrier:usps) in the US, elsewhere the local post or another carrier. Track it with the number in the shop's shipping email on DHL eCommerce's Webtrack site, or paste it in the box above.

## DHL eCommerce tracking number format

DHL eCommerce numbers have at least ten characters and typically start with GM. Webtrack takes up to 10 numbers at once, separated by commas. No number at all? Ask the shop.

| Shape (invented) | What it is |
| --- | --- |
| `GM0000000000000000` | GM and 16 to 18 digits: the GM number, DHL eCommerce's usual shape. |
| `9261234567890123456789` | 22 digits starting 9261 or 9361: the USPS number a US parcel carries once it's handed to USPS. |
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

**No scans yet.** Webtrack's FAQ says tracking events typically show up within 24 to 48 hours; if there are none after 48 hours, ask the shop. When Webtrack finds nothing, it suggests checking the number, then contacting the shop.

**No update for days.** Webtrack's FAQ names weather, holidays, customs, strikes and other events outside DHL's control. For US parcels, the handover scan itself allows 1 to 3 days for updates. See [why tracking stops updating](guide:tracking-not-updating).

**Not arrived.** If the parcel hasn't come by the end of the expected delivery window, Webtrack's FAQ says to contact the shop. In the EU, Your Europe adds that the trader is responsible for any damage until you receive the goods and should deliver within 30 days unless you agreed otherwise; if it doesn't, remind it and give it extra time, then you can end the contract and get a refund.

**Customs.** `CUSTOMS CLEARANCE` means the parcel is in customs. [Parcels held at customs](guide:customs) explains what you may pay.

**Returned.** `RETURNED TO SENDER` means it's going back to the shop: ask the shop for a refund or a new delivery.

## DHL eCommerce customer service and contact number

Ask the shop first, as Webtrack's FAQ advises. Webtrack's "Contact Customer Service" page, read on 12 October 2026, lists a card per country:

- **United States and Canada** (parcels sent from the US): +1 317-554-5191, Monday to Friday 8 AM to 8 PM EST (Canada's card says 8 AM to 9 PM ET).
- **Germany:** the card sends you to [DHL's German customer service](https://www.dhl.de/de/privatkunden/hilfe-kundenservice.html), Monday to Sunday 7 AM to 8 PM.
- **Ireland:** 818 370050 (from within Ireland), Monday to Friday 8 AM to 6 PM.
- **Australia:** +61 37 0356818, Monday to Friday 8 AM to 6 PM.
- **Austria and Switzerland:** +49 228 92 95 95 95, Monday to Friday 9 AM to 6 PM (Austria's card says 8 AM).
- **United Kingdom:** the card lists DHL eCommerce UK's line, 024 7693 7770. See the [DHL eCommerce UK](carrier:dhl-ecommerce-uk) page.

Webtrack's "Contact Support" form says someone should be in touch "within 24-72 hours". Call costs aren't stated.

## Tracking a DHL eCommerce parcel with Peek

[Peek](/) names DHL eCommerce at once for a GM number. For shared shapes, it asks the likely carriers at the same time (TNT for 16 digits, plus Evri UK, Canada Post and Correos Express when the last digit checks out; Colis Privé for 17 digits; Austrian Post for a 9261 or 9361 number, then USPS's page last if neither knows it). If only DHL eCommerce knows it, you're told. If several brands do, you pick with "Change", which accepts any 5 to 40 letters and digits for DHL eCommerce. Links from webtrack.dhlglobalmail.com, ecommerceportal.dhl.com work too, and dhl.com tracking links when the number has a DHL eCommerce shape.

Webtrack is asked first. When it doesn't list the parcel, Peek opens DHL's global tracking page on dhl.com, which takes longer and can fail when DHL blocks automated visits.

You see the status, each scan with its town and country on a map, the expected delivery day when DHL gives one, the sender and the delivery time; from Webtrack, also the service, the weight and the delivering carrier. Peek leaves out your address, the shop's reference and the name of whoever signed. Scans keep DHL's US English wording, and every delivered scan shows as "Delivered".

When Webtrack names USPS, the parcel can be followed on USPS with its number, and its page says "Delivery with USPS". Parcels answered by DHL's global page (Europe, Asia) aren't followed to a local post. You can be notified of every scan, important steps or "Delivery only".

## Questions about DHL eCommerce tracking

### Is DHL eCommerce the same as DHL Express?

No. DHL eCommerce is DHL's parcel division for online shops and usually leaves the final delivery to a local post or carrier. DHL Express has its own tracking, and Peek tracks the two as separate carriers.

### Is DHL eCommerce delivered by USPS?

Within the US, yes: DHL's Webtrack FAQ says USPS makes the final delivery. Abroad, the local post or another delivery company does.

### Why is there no DHL eCommerce tracking information yet?

Webtrack's FAQ says tracking events typically appear within 24 to 48 hours. If there are still none after 48 hours, contact the shop that sent the parcel.

### Is DHL eCommerce legit?

Yes. It's DHL's parcel division for online shops, formerly known as DHL Global Mail.

:::sources
- [DHL eCommerce: Webtrack tracking](https://webtrack.dhlecs.com/) – tracking site, status labels, details box, 10 numbers, no-result advice, contact form, email updates
- [DHL eCommerce: Webtrack Contact Customer Service](https://webtrack.dhlecs.com/support/customer-service) – country cards, phone numbers, hours, FAQ on tracking after 24 to 48 hours, USPS delivery, delays, missing parcels, address changes, instructions
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – trader responsible for damage until delivery, 30 days, extra time, refund
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formerly DHL Global Mail, number shapes and the USPS number, scan wordings and stages, DHL Express's own tracking, what Peek reads and shows
:::
