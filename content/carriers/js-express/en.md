---
title: JS Express tracking: JSE…YQ numbers, scans and who delivers
description: Track a JS Express (急速国际) parcel from China: what a JSE…YQ number looks like, what its scans mean and who delivers it in the US, Canada and beyond.
slug: js-express-tracking
published: 2026-10-30
updated: 2026-11-12
---

JS Express (急速国际, also JS EXP) is a Chinese logistics company for cross-border online shops, run by 急创速供应链管理（苏州）有限公司 in Suzhou. If a shop sent your order with JS Express, its number starts with JSE and ends with YQ: paste it in the box above. JS Express gets the parcel out of China and a courier in your country delivers it: its history shows USPS, GOFO or SpeedX in the United States, Straightship in Canada, Yodel in the UK, DHL, iMile or J&T Express in Germany and Colissimo in France, and FedEx on some lines.

## JS Express tracking number: what it looks like

JS Express's waybills are JSE, two letters, ten digits and YQ, 17 characters in all. With invented digits:

| Number | What it is |
| --- | --- |
| `JSEAA0000000000YQ` | JSE, two letters, ten digits and YQ: a JS Express waybill. |
| `JSEAA000000000YQ` | Only nine digits: not JS Express's layout. Check for a missing digit. |
| `JXCAA0000000000YQ` | Also ends in YQ, but opens with JXC: a [Juxi Express](carrier:juxi-express) number. |

Other Chinese forwarders use the same layout with their own letters in front: FEI for 17FEIA, WNB for WanbExpress, SFC for SFC, CJP for CJ Packet. JSE marks JS Express. Spaces and lower case don't matter in Peek. It reads a zero typed for the second of the two letters as an O, and an O among the ten digits as a zero. JS Express's tracker needs the whole number: part of one finds nothing.

**Where to find it.** The shop passes the number on in its shipping email or on your order page. JS Express says it works closely with Amazon, Walmart, Shopify, eBay, AliExpress, Wish and Shopee. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**No link to share.** JS Express's tracking pages take the number in a form, so no link opens one parcel: copy the number. The English page, "Express Query", answers straight away; the Chinese one asks for a picture code (验证码) first.

## JS Express tracking statuses explained

JS Express's page lists each scan as a time, a place in 【brackets】 and a wording, newest first, with "Latest" on top. Its wordings are mostly English. In the order a parcel gets them:

| JS Express says | What it means for you | Peek shows |
| --- | --- | --- |
| `Electronic notification of order creation received.` | The shop has sent JS Express the order's details. JS Express doesn't have the parcel yet. | "Label created" |
| `PRE-ADVICE` | A partner has the parcel's details, before JS Express's warehouse has the parcel. | "Label created" |
| `Item has been collected` | JS Express's warehouse in China has the parcel: its first real scan. | "Received by carrier" |
| `Item inbound in sorting center.`, `Item outbound in sorting center.` | Sorted at JS Express in China. | "In transit" |
| `Domestic allocation has begun`, `Under domestic allocation` | Still at JS Express's warehouse in China, after sorting and before it leaves. | "In transit" |
| `Waybill Generated for` | The courier in your country has made its waybill, while JS Express's warehouse in China still has the parcel. | "Label created", or the stage the parcel already had, usually "In transit" |
| `Arrive at transit center` | At JS Express's transit centre in China, between sorting and departure. | "In transit" |
| `Item departed from origin.`, `Arrive at international airport to abroad` | On its way out, then at the airport in China. | "In transit" |
| `Arrived at export center`, `Exports Center Sorting and Packing`, `Arrived at Origin Port` | On some lines, between those two: an export centre in China sorts and packs the parcel, then it reaches the port it leaves China from. | "In transit" |
| `Shipment arrived at facility and measured.`, `Depart from facility to service provider.` | A facility in China weighs and measures the parcel, then sends it on to the line that flies it. | "In transit" |
| `Released From Export Customs` | China's export customs are done. Not your country's customs. | "In transit" |
| `DEPART ORIGIN AIRPORT`, `Departure from the original airport`, `Item Flight departure`, `Initial landing`, `Connecting flights flight takeoff` | The flight has left, sometimes with a stop and a connecting flight. | "In transit" |
| `ARRIVED AT DESTINATION AIRPORT`, `Arrival to the destination airport`, `Item Flight landing`, `NOA-Notification of arrival from GHA` | Landed in your country. NOA is the airport's ground handler reporting it. | "In transit" |
| `The customs clearance agent is queuing up to pick up the goods` | The clearance agent in your country is waiting at the airport's cargo terminal to collect the shipment: neither the agent nor customs has it yet. | "In transit" |
| `The customs clearance agent has picked up the goods` | The agent has collected the shipment from the cargo terminal for import clearance. | "At customs" |
| `The goods have been picked up abroad` | The agent in your country has taken the shipment from the airport. Not a new pickup from the seller. | "In transit" |
| `Customs clearance agent received the shipment in destnation country.` | The customs agent in your country has taken the shipment for import clearance, after the flight. "destnation" is JS Express's own spelling. | "At customs" |
| `Inbound to customs temporary warehouse` | In a customs warehouse, waiting for import clearance. | "At customs" |
| `Released From Import Customs`, `Released from customs: customs cleared.` | Your country's customs have let it through. | "In transit" |
| `Depart from the destination distribution center.` | A distribution centre in your country sends the parcel on, after customs. | "In transit" |
| `collection by overseas warehouse` | JS Express's warehouse in your country has taken the parcel, after customs. | "In transit" |
| `Load cargo overseas`, `Vehicles depart from` | That warehouse loads the parcel for the courier, then the truck leaves from the place shown with the scan. | "In transit" |
| `Delivery to local courier`, `Last Mile Delivery` | Handed to the courier that delivers in your country, before its first scan. Not delivered to you yet. | "In transit" |
| `Delivery in progress` | On some routes: the carrier in your country has the parcel, after it reached that carrier's station and before any delivery round. Despite the words, not out for delivery yet. | "In transit" |
| `到达待取` | JS Express's own status, in Chinese: arrived, awaiting collection. Its place is only the country. | "Ready for pickup" |
| `The delivery address is incorrect. Please ask the recipient to contact their local area?` | JS Express's own notice after the courier failed to deliver: the address is unclear or wrong. It asks you to contact the local office, whose phone number JS Express's page gives; Peek leaves phone numbers out. | "Delivery attempted" |

**The courier's scans.** After `Delivery to local courier`, the history goes on in the courier's words. In the US, USPS's: `Accepted at USPS Origin Facility` or `Accepted at USPS Facility` (its first scan, not a new shipment), `Out for Delivery`, `Delivered, Front Door/Porch`. SpeedX's come without its name: `Processing at Transit Facility`, `In transit to destination facility`, `Arrival at Destination Facility` and `Processing at Designated Facility`, all "In transit". Another US partner's scans come unnamed too: `Arrived at Origin Hub` (its first hub, by the airport the parcel flew to), `Departed Destination Hub` and `Arrived At Warehouse` (its last-mile warehouse, before the delivery round), all "In transit". In Canada, Straightship's `Package is received at a Straightship station`, then `Package will be delivered before 9:00pm`, the driver's round that day ("Out for delivery": a delivery or an attempt follows), and `Delivered` with the town in brackets. In Germany, DHL's, in English and sometimes with a full stop at the end: `The instruction data for this shipment have been provided by the sender to DHL electronically`, its record of the parcel's data ("Label created", or "In transit" if a scan came before it), then `The shipment is in transit to DHL` ("In transit"); a small parcel can also get `Die Sendung wurde im Briefzentrum bearbeitet.`, in German, from a DHL mail centre ("In transit"). iMile's driver can end a round with `Route not completed` ("Delivery attempted"). J&T Express Germany's come in German, with the places inside the 【】 brackets: `【】【】Ihr Sendung wurde abgeholt.` is its first scan, when it takes the parcel over after the flight and customs; `【】Die Sendung hat【】verlassen. Weiterversand nach【】` (it left one site for the next) and `【】Die Sendung ist bei【】 angekommen.` (at a hub, or at the depot that delivers, before its round) follow, all "In transit". Where FedEx delivers, `Shipment information sent to FedEx` is its label, written before JS Express's warehouse has the parcel ("Label created"); `Arrived at FedEx location`, `Left FedEx origin facility` and `On the way` follow ("In transit"). In Hungary, the courier's `Arrived to HU HUB`, `Linehaul Transit` (the trip from its hub to a sorting centre) and `Shipment arrived to sorting centre / Shipment in the sorting centre` follow, all "In transit". In Spain, CTT Express's `Enviado`, in Spanish, means it has sent the parcel on, after taking it in and before its delivery round ("In transit").

**A courier without a name.** On some lines, JS Express relays a delivering courier's scans in capitals without naming the courier or its country. `SHIPMENT RECEIVED`, `PROCESSED AT HUB` and `SHIPMENT PROCESSED` are the courier's hub taking the parcel in, and `DEPARTED FROM DEPOT` the parcel leaving it for the depot that delivers, all "In transit". `RECEIVED AT LOCAL DEPOT` is that depot, before each round and again after a missed one: the courier's own depot, not a pickup point, so Peek keeps the status of the line before it. `DELIVERY ATTEMPTED: 1ST DELIVERY ATTEMPT`, `DELIVERY ATTEMPTED: 2ND DELIVERY ATTEMPT` and `DELIVERY ATTEMPTED: 3RD DELIVERY ATTEMPT` are missed deliveries ("Delivery attempted"). With the third, the courier asks you to book a delivery; `SHIPMENT REDIRECTED: ON CONSIGNEE'S REQUEST` then means the parcel is on its way to the delivery you arranged ("In transit"). `DELIVERY EXCEPTION` is a problem at the courier's hub before any round, and `DELIVERY EXCEPTION, WAIT FOR INFORMATION CONFIRMATION`, after `ARRIVE AT THE WAREHOUSE` ("In transit"), means the courier holds the parcel until its information is confirmed: Peek shows both as "Needs attention". If Peek shows no courier's number, ask the seller who delivers and what the courier needs.

**Lines that aren't scans.** `SHIPPING INFORMATION RECEIVED`, `SHIPMENT CREATED`, `Data Received`, USPS's `Shipping Label Created, USPS Awaiting Item`, `Package information is created in the system` and, at a European hub, `Shipment Data Uploaded` then `Processed` are a partner's record of the parcel's details, sometimes days before it has the parcel. `Consignee Contacted`, written at the moment the delivering courier records a missed delivery, is its call or message to you: it doesn't change the status either.

**Times out of order.** The times carry no time zone and mix clocks: Beijing time in China, local time at airports and most couriers, and UTC for Straightship, hours ahead of Canada's clocks. Go by the order of the rows, not the times. In Peek, J&T Express Germany's German rows show the times J&T Express Germany's own tracker gives.

## Who delivers JS Express parcels

**United States.** JS Express's history shows [USPS](carrier:usps) delivering, with USPS's scans up to the delivery, or [GOFO](carrier:gofo), with GOFO's scans from its sorting centre to the delivery round, or [SpeedX](carrier:speedx), whose scans don't name it. Once USPS has the parcel, USPS takes a Missing Mail search from the sender or the recipient, starting 7 days from mailing.

**Canada.** [Straightship](carrier:straightship), a Canadian courier, delivers some JS Express parcels; its scans show in JS Express's history.

**Germany and France.** JS Express's Europe line serves countries such as the UK, France, Germany, Italy, Spain, Switzerland and Denmark, though not some remote postcodes. For Germany, JS Express names DPD and other couriers for the last mile and says over 98% of parcels are delivered within 8 working days; its history there has shown [DHL Paket](carrier:dhl)'s scans, [iMile](carrier:imile)'s from a German centre, and [J&T Express Germany](carrier:j-and-t-de)'s, in German. In France it has shown [Colissimo](carrier:la-poste)'s, in French.

**UK, Ireland, Australia and elsewhere.** On some lines the history shows [FedEx](carrier:fedex)'s scans. In the UK, JS Express's history shows Yodel's scans; two of them, `DESPATCHED AND ON ROUTE TO YODEL` and `PARCEL PICKED UP`, come while the parcel is still at JS Express's warehouse in China. For Australia, JS Express names Australia Post and Fastway, now [Aramex Australia](carrier:aramex-au), below 4 kg, and DPEX above. JS Express's record can hold the local courier's number, but not its name, and JS Express's page shows neither. Peek names the courier when that number fits one carrier in your country, and asks your country's post about a [postal number](guide:tracking-number-formats) (two letters, nine digits, two letters); more below. If Peek names no courier, ask the seller who delivers.

Delivery attempts, notice cards and pickup points follow the local courier's rules: JS Express publishes none. A `到达待取` row names only the country, so check the courier's tracking or ask the seller where the parcel waits.

## JS Express parcel not arriving or tracking stuck

**"未获取到任何数据,请提供有效单号!"** JS Express's English page answers this, in Chinese, for a number it doesn't know: "no data found, please give a valid waybill number". `No track data for now!` means no scans yet. Peek shows "Waiting for the carrier" for both and keeps checking. Check the number character by character; if nothing comes after a few days, ask the seller whether the parcel was sent.

**Stuck on `Electronic notification of order creation received.`** JS Express hasn't collected the parcel yet. Ask the shop when it was handed over.

**Stuck on `Inbound to customs temporary warehouse`.** It's waiting for your country's customs. On some routes the rows read `Start Custom clearance Process`, then `customs clearance completed`; an earlier `Under customs clearance, please wait patiently.` is China's export customs at the departure airport, so Peek shows "In transit" for it. JS Express sells its Europe line and its standard US line with duties paid (DDP): it collects the import taxes from the shop in advance, so on those lines you shouldn't be asked for any. If a courier asks, check with the seller before paying. In the EU, a €2 handling fee per item has been due since 1 November 2026; the seller or the delivering company pays it and may pass it on. See [parcels held at customs](guide:customs); if the parcel sits there for days, tell the seller. More in [why tracking stops updating](guide:tracking-not-updating) and [tracking a parcel from China](guide:tracking-from-china).

**Nothing after `Delivery to local courier`.** The local courier has it. If Peek shows the courier's number, look it up on that courier's tracking; if not, ask the seller who delivers.

**Delivered, but nothing came.** Ask the local courier first (USPS in the US), then the seller. On its US line, JS Express helps check a delivery the courier shows but you didn't get, and pays no compensation for it, so the seller's own policy decides. See [delivered but not received](guide:delivered-not-received).

**Lost, damaged or returned.** The seller is JS Express's customer. Under JS Express's terms, the customer claims in writing within 30 days of JS Express receiving the parcel, and claims for insured parcels through its own account. So ask the shop or marketplace for a refund or a new parcel, and don't wait. A parcel that can't be delivered (nobody home, refused, wrong address, customs) is sent back for a fee if the sender asks, or destroyed; on its US line, JS Express neither brings a returned parcel back to China nor sends it out again. On some routes, `Refused` then `Returned` mean the parcel was refused on the delivery round and is back at the courier's sorting centre, not yet with the seller: Peek shows "Needs attention". See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## JS Express contact number and customer service

As read on 30 October 2026, JS Express's contact page in Chinese lists the following. The English contact page is older and still gives other numbers and addresses.

- **Hotline**: 400-0353-518, a Chinese service number, also given for international queries. JS Express publishes no hours and doesn't say whether it works from abroad.
- **QQ and WeChat**: enterprise QQ 4000353518 and the WeChat official account js-express.
- **Complaints**: a form on the English complaint page, and the same hotline.
- **Offices**: a head office and warehouse in Suzhou, more warehouses across China, and offices of its network in Hong Kong and in Walnut, California. The US office's only phone is the Chinese hotline.

JS Express's claims go through the seller who booked the parcel. For your order, ask the seller; for the delivery, the local courier.

## Track a JS Express parcel with Peek

[Peek](/) recognises a JS Express number by its shape: JSE, two letters, ten digits and YQ, which no other carrier in its catalog uses, and asks JS Express's tracker by it. If all you have is the local courier's number, paste that instead: Peek follows more than 3,500 carriers.

Peek shows the history of JS Express's English page, each scan with its place, and the destination country, which that page leaves out. `到达待取` shows as "Arrived, awaiting collection". Names of who took a delivery and phone numbers are dropped. Peek leaves out the delivery photos and the shop's order number that JS Express's record can hold. As the expected delivery, it shows the day of JS Express's estimate; once the parcel is out for delivery, Straightship's `Package will be delivered before 9:00pm` included, it shows no day, since "Out for delivery" already means today. Lines that aren't scans don't change the status.

Peek reads rows in China on Beijing time, Straightship's on UTC and J&T Express Germany's German rows at the offset each row states, works out the others' clocks from their place or the destination, and shows them all in your device's time zone, newest first.

When the courier's number fits one carrier that works in your country, such as a USPS barcode in the US or a Straightship number in Canada, Peek shows "Delivery with" that carrier, with its number, and asks it too; an international postal number goes to your country's post. While that carrier has only the announcement of the parcel, with no scan of its own, it shows "Announced to …, not received yet" instead. Once that carrier shows a scan at least as recent as JS Express's latest, Peek follows the parcel there, showing both numbers and keeping JS Express's scans. Until then it stays with JS Express, whose history often carries the courier's scans too.

Peek checks every 10 minutes, and every 2 minutes (every 5 when it has to open JS Express's tracker in a browser) from 6 a.m. local time on the day the parcel goes out for delivery, while JS Express's own tracker answers (through tracking services, every 15 minutes). When another carrier takes over the delivery, Peek follows the pace of the carrier delivering the parcel: hourly for Royal Mail, every 30 minutes for Poste Italiane, PostNL and USPS, out for delivery too. A parcel GOFO delivers stays with JS Express's tracker, which carries GOFO's scans: a GOFO number doesn't point to GOFO surely enough for Peek to switch. With SpeedX's number for a parcel to the US, Peek can follow it on [SpeedX](carrier:speedx), which doesn't set a longer interval. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of every scan, important steps only (customs, problems, "Ready for pickup", out for delivery, the delivery and a return) or "Delivery only". It can't contact JS Express or the seller for you.

## Questions about JS Express tracking

### What is JS Express (急速国际)?

A Chinese logistics company for cross-border online sellers, run by 急创速供应链管理（苏州）有限公司. It says it started in Hong Kong in 2010 and opened its Suzhou base in 2011.

### Where can I track a JS Express parcel?

In the box above, or on JS Express's English "Express Query" page at js-exp.com. Its Chinese page asks for a picture code first.

### What does 未获取到任何数据 mean on JS Express tracking?

"No data found": JS Express's tracker doesn't know the number, because of a typo or because JS Express hasn't received it yet. Check it with the seller.

### Who delivers JS Express parcels in my country?

A courier in your country. JS Express's history shows USPS, GOFO or SpeedX delivering in the United States, Straightship in Canada, Yodel in the UK, DHL, iMile or J&T Express in Germany and Colissimo in France, and FedEx on some lines. Elsewhere, ask the seller which company delivers.

### Who do I contact about a missing JS Express parcel?

The seller you ordered from: it booked the parcel with JS Express and makes the claims. JS Express's hotline, 400-0353-518, is a Chinese number.

:::sources
- [JS Express: 急速国际 (home page)](https://www.js-exp.com/) – operating company
- [JS Express: Introduction](https://www.js-exp.com/EN/index.php?page=6) – Hong Kong 2010, Suzhou 2011, cross-border e-commerce
- [JS Express: 企业介绍 (introduction)](https://www.js-exp.com/index.php?page=6) – Suzhou 2011, branches, platforms
- [JS Express: Express Query](https://www.js-exp.com/EN/index.php?page=19) – English tracker, what it shows, its answers
- [JS Express: 快件查询 (tracking)](https://www.js-exp.com/index.php?page=19) – picture code
- [JS Express: Insurance Claims](https://www.js-exp.com/EN/index.php?page=29) – customer's claims, 30 days, undeliverable parcels
- [JS Express: 投保理赔 (insurance claims)](https://www.js-exp.com/index.php?page=29) – 30 days from JS Express receiving the parcel, claims from the customer's account
- [JS Express: Complaint](https://www.js-exp.com/EN/index.php?page=32) – form, hotline
- [JS Express: Contact Us](https://www.js-exp.com/EN/index.php?page=35) – hotline, QQ, WeChat, older numbers and addresses
- [JS Express: 联系我们 (contact us)](https://www.js-exp.com/index.php?page=35) – hotline for international queries, QQ, WeChat, offices
- [JS Express: 国际专线 (international lines)](https://www.js-exp.com/index.php?catid=13) – Germany line: DPD and others, over 98% within 8 working days; Australia line: Australia Post, Fastway, DPEX
- [JS Express: 急速美国专线 (US line)](https://www.js-exp.com/index.php?aid=27) – duties paid (DDP), returns neither brought back to China nor sent out again, delivered but not received
- [JS Express: 急速欧洲专线 (Europe line)](https://www.js-exp.com/index.php?aid=35) – countries, duties paid (DDP), remote postcodes
- [Aramex Australia: About Aramex Australia](https://www.aramex.com.au/why-aramex/about-us/) – Fastway became Aramex Australia
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – €2 per item from 1 November 2026, paid by the seller or the delivering business, may be passed on
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – senders or recipients, from 7 days
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shapes, scan wordings and stages, clocks (J&T Express Germany's rows at their stated offset since 9 November), USPS, GOFO, SpeedX, Straightship, Yodel, DHL, iMile, J&T Express Germany, FedEx, Colissimo and CTT Express scans, an unnamed courier's hub, depot, missed-delivery, redirection and exception scans, an unnamed US partner's hub scans, iMile's German centres, the browser fallback, customs, refusals and returns, the courier's number, what Peek shows and how it hands a parcel over
:::
