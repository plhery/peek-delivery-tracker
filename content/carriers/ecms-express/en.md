---
title: ECMS Express tracking: numbers, scans and who delivers
description: Track an ECMS Express (易客满) parcel: what its 16-character numbers look like, what each scan means, who delivers it and how to reach ECMS.
slug: ecms-express-tracking
published: 2026-10-31
updated: 2026-10-31
---

ECMS Express (易客满, also ECMS Global) is an international express company that ships parcels for online shops. If a shop sent your order with ECMS, your number has 16 characters and starts with EC, ES, AP or AX: paste it in the box above. ECMS carries the parcel abroad and through customs, then a local carrier delivers it.

## ECMS Express tracking number: what it looks like

ECMS's numbers are 16 characters: EC, ES, AP or AX, then three to five letters, then digits for the rest. With invented characters:

| Number | What it is |
| --- | --- |
| `ECAAA00000000000` | EC, three letters and eleven digits: an ECMS number. |
| `ECAAAA0000000000` | EC, four letters and ten digits: ECMS too. |
| `ECAAAAA000000000` | EC, five letters and nine digits: ECMS too. |
| `ESAAA00000000000`, `APAAA00000000000`, `AXAAA00000000000` | The same layouts, opening with ES, AP or AX. |
| `ECAAA0000000000` | Only 15 characters: not ECMS's layout. Check for a missing digit. |

No other carrier in Peek's catalog uses these layouts. Spaces and lower case don't matter in Peek.

**Where to find it.** ECMS prints the number on the waybill on the parcel's label, and the shop passes it on in its shipping email or on your order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**A tracking link.** A link to ECMS's tracker ending in `?number=` and a number fills that number in and opens the picture check. Older ECMS links carry the number after `orderNumber=` or `trackingno=`. You can paste any of them into Peek: it finds the ECMS number in the link.

## ECMS Express tracking statuses explained

ECMS's tracker first asks you to pass a picture check ("Security verification"). It then sums the parcel up in one of eight labels, from Shipment Information Received to Delivered or Exception, and lists its scans, newest first, with date, time and place. Peek shows each scan in the words of ECMS's record. In the order a parcel gets them:

| ECMS says | What it means for you | Peek shows |
| --- | --- | --- |
| `Virtual Manifest has been received by carrier`, `Virtual Manifest has been received`, `Shipment info confirmed` | The shop has sent ECMS the parcel's details. ECMS doesn't have the parcel yet. | "Label created" |
| `Documents and information required for clearance have been received by the carrier.`, `ID information has been reviewed and verified` | ECMS has the papers customs need, or has checked the recipient's ID. Still not a scan of the parcel. | "Label created" |
| `Shipment arrived at carrier facility.`, `Shipment arrived at origin facility.` | ECMS's warehouse at the start of the journey has the parcel: its first real scan. | "Received by carrier" |
| `Shipment departed from carrier facility.`, `Shipment departed from origin facility.` | It has left that warehouse. | "In transit" |
| `Flight departure`, `Flight arrival` | The flight has left, then landed. | "In transit" |
| `Shipment started customs clearance process.` | Customs in the destination country are clearing it. | "At customs" |
| `Shipment completed customs clearance process.` | Import customs have let it through. | "In transit" |
| `Shipment arrived at destination facility`, `Parcel at destination facility, ready for next step`, `Parcel Weight` | At the facility in the destination country, where it's weighed and waits for the local carrier. | "In transit" |
| `Shipment is tendering to third party carrier for final delivery.` | ECMS is handing the parcel to the local carrier that delivers it. Its number may show from now on. | "In transit" |
| `Local carrier has accepted package for final delivery` | The local carrier has the parcel. | "In transit" |
| `Shipment is in Transit within the carrier‘s network` | Moving through the local carrier's network, as ECMS passes it on. | "In transit" |
| `Shipment is out for delivery.` | On the delivery run. | "Out for delivery" |
| `Shipment delivered to customer` | Delivered. | "Delivered" |

**In Chinese.** Some histories give these steps in Chinese, and Peek gives them the same stage as their English twins: `发货预报已收到，正在等待货物到达我们的始发站仓库` (details received, waiting for the parcel), `干线航班起飞` and `干线航班已到达` (flight left, landed), `货物已进入清关流程` (in customs), `货物已清关放行` (cleared), `货物已到达目的站仓库` (at the destination warehouse) and `货物出库前往配送中心安排最终派送` (on its way to the local carrier).

**Times.** ECMS gives its own scans the clock and UTC offset of the station that made them, and Peek reads them that way. The local carrier's scans come without an offset, so Peek works out their clock from their place, a scan beside them or your country. See also [tracking a parcel from China](guide:tracking-from-china).

## Who delivers ECMS Express parcels

For the last leg, ECMS says it partners "with trusted local carriers". Its tracker can name that carrier in a "Last Mile Delivery" box, as "LM Provider", with the carrier's own number under "LM Tracking No." and a "Track with …" link. Peek's code names no particular delivering carrier for ECMS parcels, in any country.

**How long it takes.** ECMS says it depends on the origin, the destination and the service. From the United States, its service page gives 3 to 6 business days to the UK, 3 to 11 to Canada, 3 to 8 to Australia and 4 to 8 to the European Union.

**Delivery attempts and pickup** follow the local carrier's rules, so ask that carrier: ECMS publishes none for recipients. See [missed delivery](guide:missed-delivery).

## ECMS Express parcel not arriving or tracking stuck

**"We could not find a shipment for this tracking number."** ECMS's tracker doesn't know the number: a typo, or the shop hasn't sent ECMS the details yet. Peek shows "Waiting for the carrier" and keeps checking. Check the number; if nothing comes after a few days, ask the seller whether the parcel was really sent.

**Stuck on `Virtual Manifest has been received`.** ECMS has the details, not the parcel. Ask the shop when it handed the parcel over.

**Stuck at customs.** After `Shipment started customs clearance process.`, the parcel waits for your country's customs. ECMS's FAQ says that on DDU or DAP shipments the recipient may have to pay duties and taxes before delivery; on DDP shipments the shipper usually pays them in advance. If you're asked to pay, see [parcels held at customs](guide:customs).

**Nothing after `Shipment is tendering to third party carrier for final delivery.`** The local carrier has the parcel, or soon will, and its own tracking may be ahead of ECMS's. Find its name and number in ECMS's "Last Mile Delivery" box and, unless Peek already follows the parcel there, add that number to Peek too. More in [why tracking stops updating](guide:tracking-not-updating).

**Delivered, but nothing came.** Ask the local carrier where it left the parcel, then tell the seller. See [delivered but not received](guide:delivered-not-received).

**Lost, damaged or the wrong items.** ECMS's FAQ sends you to the retailer or seller for a replacement or a refund. Only the shipper can claim from ECMS, in writing within 30 days of ECMS accepting the parcel, so tell the shop quickly. See [lost parcels](guide:lost-parcel).

**Returned.** ECMS's reasons include prohibited or restricted items, wrong or incomplete shipping details, customs problems, unpaid duties and taxes, and a recipient who can't complete the customs steps. See [returned to sender](guide:returned-to-sender).

**Cancelling or changing the order.** ECMS can't: it's only the carrier. Ask the seller.

## ECMS Express contact number and customer service

As read on 30 October 2026, ECMS's Contact Us page lists these numbers for English-speaking countries, with no time zone for the hours. Canada and Ireland have no entry of their own.

- **United States**: +1 626 956 5388, Monday to Friday, 09:00 to 18:00.
- **United Kingdom**: 01753 528888, Monday to Friday, 08:30 to 17:00.
- **Australia**: +61 2 8317 6262, Monday to Friday 11:00 to 20:00, Saturday 11:00 to 15:00.
- **Message form**: "Shipment Support" as the inquiry type, with a field for the tracking number. Each region also has an email address.

## Track an ECMS Express parcel with Peek

[Peek](/) recognises an ECMS number by its shape and asks ECMS's tracker by it. No account, postcode or phone number is needed.

ECMS's picture check is a small sum. Peek reads it itself and, if that fails, tries once more with a new picture. When ECMS still won't answer, Peek asks tracking services in the meantime and shows the history they hold, in their own words.

Peek shows the status, each scan with its town, region and country, and the destination country, newest first, with all times in your device's time zone. It leaves out the shop's reference and any payment links on ECMS's record, and shows no weight or expected delivery date.

When ECMS's record names a local carrier Peek knows, Peek shows "Delivery with" that carrier, with its number when ECMS's scans give one. If Peek reads that carrier's own tracker, it asks it too, and once that carrier shows a scan at least as recent as ECMS's latest, Peek follows the parcel there and keeps ECMS's scans. Otherwise, add the "LM Tracking No." as a parcel of its own: Peek follows more than 3,500 carriers.

Peek checks every 10 minutes by day, every 2 minutes once the parcel is out for delivery, and every hour or two overnight. It can notify you of "Every scan", "Important steps" or "Delivery only". "Important steps" covers ECMS's customs step, the delivery run and the delivery, not its flights or warehouse scans. Peek can't contact ECMS or the seller for you.

## Questions about ECMS Express tracking

### What is ECMS Express (易客满)?

An international express and logistics company, founded in 2013, with its own branches in mainland China, Hong Kong, Singapore, the US, the UK, Europe, South Korea and Japan. Its Chinese site gives its company name as 天津易客满国际物流有限公司.

### Where can I track an ECMS Express parcel?

In the box above, or on ECMS's "Track Your Shipment" page at ecmsglobal.com, once you pass its picture check.

### Who delivers ECMS Express parcels in my country?

A local carrier that ECMS works with, which ECMS's tracker can name under "LM Provider". Peek's code names none in particular for the US, the UK, Ireland, Canada or Australia, so check that box or ask the seller.

### What does "Shipment is tendering to third party carrier for final delivery" mean?

ECMS is handing your parcel to the local carrier that delivers it, whose number can then show under "LM Tracking No." on ECMS's tracker. Peek shows "In transit".

### Who do I contact about a missing ECMS Express parcel?

The seller first: ECMS's FAQ sends recipients to the retailer for refunds and replacements, and only the shipper can claim from ECMS. For the delivery itself, ask the local carrier; ECMS lists phone numbers in the US, the UK and Australia.

:::sources
- [ECMS Express: Home](https://www.ecmsglobal.com/en-us/home.html) – express for online shops
- [ECMS Express: About ECMS](https://www.ecmsglobal.com/en-us/about.html) – founded in 2013
- [易客满: 关于我们 (about us)](https://www.ecmsglobal.com/zh-cn/about.html) – 2013, own branches, company name
- [ECMS Express: Parcel Delivery Service](https://www.ecmsglobal.com/en-us/express.html) – local carriers for the last leg, transit times from the United States
- [ECMS Express: Track Your Shipment](https://www.ecmsglobal.com/en-us/tracking.html) – picture check, summary words, scans, "Last Mile Delivery" box, not-found message, link
- [ECMS Express: FAQ](https://www.ecmsglobal.com/en-us/faq.html) – waybill, duties and taxes, returns, cancelling, lost parcels, claims by the shipper within 30 days
- [ECMS Express: Contact Us](https://www.ecmsglobal.com/en-us/contact-us.html) – regional phone numbers and hours, message form
- [ECMS Express: Support (UK)](https://www.ecmsglobal.com/en-uk/support.html) – the UK number in its local form
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – names, number shapes, links, picture check and retry, scan wordings in English and Chinese and their stages, clocks, places, the local carrier and its number, what Peek leaves out, tracking services
:::
