---
title: JD Logistics tracking: JD numbers, statuses and contact
description: Track a JD Logistics (京东物流) parcel: what JD and VG numbers look like, what JD's tracking steps mean, who delivers in the US and how to reach JD.
slug: jd-logistics-tracking
published: 2026-11-02
updated: 2026-11-02
---

JD Logistics (京东物流, Jingdong Logistics) is JD.com's logistics business, itself listed in Hong Kong. Abroad it works under the brands JoyLogistics and JoyExpress: it ships sellers' stock from its warehouses in the US, the UK, Australia and other countries, and carries parcels from China for online sellers. Track a parcel on [JD's international tracker](https://www.jingdonglogistics.com/Tracking), in English, or paste the number in the box above. The number comes from the shop that shipped your order.

## JD Logistics tracking number: what it looks like

The shapes Peek knows as JD Logistics, and one that isn't, with invented digits:

| Number | What it is |
| --- | --- |
| `JD0000000000000` | JD and 13 digits: a JD Logistics waybill. |
| `JDA000000000000` | JD, a letter and 12 digits: JD Logistics too. |
| `JDAA00000000000` | JD, two letters and 11 digits: JD Logistics too. |
| `VG00000000000` | VG and 11 digits: JD Logistics too, but La Poste's numbers can take this shape, so Peek doesn't name JD at once (below). |
| `JD000000000000000000` | JD and 18 digits: not JD Logistics. Peek asks [DHL Paket](carrier:dhl), which uses this shape. |

Lower case, spaces and dashes don't matter in Peek. JD's international tracker takes up to 10 numbers at once, separated by spaces: press Enter or "Track".

**Where to find it.** The shop that booked JD gives it, in its shipping email or on your order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number).

**A parcel inside China.** One on its way within China, for example to a forwarder's warehouse, is tracked on JD's Chinese site, [jdl.com](https://www.jdl.com/orderSearch). Type the waybill or package number under `运单查询` (waybill search) and press `立即查询` (search now). It takes several numbers, separated by commas, spaces or line breaks.

## JD Logistics tracking statuses explained

JD's international tracking gives each step in English, with the place where it happened. These are the steps Peek has seen in JD's words, in the order a parcel from one of JD's warehouses abroad meets them:

| JD says | Peek's status | What it means for you |
| --- | --- | --- |
| `Picking Completed.` | "Received by carrier" | Your order was picked in a JD warehouse, which already held the seller's stock. |
| `Weighed at Warehouse.` | "Received by carrier" | Weighed in the same warehouse before it leaves. |
| `Departed from Warehouse.` | "In transit" | It has left JD's warehouse. |
| `Handed over to Carrier` | "In transit" | JD handed it to the carrier that delivers it near you. |
| `Shipping Label Created` | no change | That carrier's label, passed on by JD. The parcel's status stays as it was. |
| `Arrived at GOFO Regional Hub` | "In transit" | In the US: GOFO Express, the carrier JD's history shows delivering there, scanned it at its hub. |

**A parcel from China** meets other steps, in this order. First `Shipment Picked up` (collected from the shop), then `Arrived at Warehouse`, `Weighed at Warehouse` and `Consolidation Completed` at JD's warehouse in China, which holds it until it leaves: all "Received by carrier". Consolidated means packed with other parcels for the flight, not delivered. Then, all "In transit": `Departed from Warehouse.`, `Arrive at airport`, `Customs released from origin port` (export clearance done), `Departure from origin port`, `Arrive at destination port`, `Cleanrance agency picked` (JD's spelling: the clearance agent collected the cleared parcel, not you) and `Handover to local carrier`. In the US, GOFO's steps follow under JD's codes: `Arrived at GOFO Regional Hub`, `In Transit to Next Facility` and `Arrived at GOFO Delivery Station` ("In transit"), then `Out for Delivery` ("Out for delivery").

**The local carrier's number.** On JD's tracker, `Departed from Warehouse.` on a parcel from China ends with "Tracking no" and the number the carrier delivering near you gave the parcel (in the US, a GOFO number starting with GFUS). Peek leaves that number out of the line and doesn't follow the parcel on that carrier's tracker: to see the carrier's own steps, look the number up on its site.

Peek reads JD's other steps by the code JD gives each: the order created ("Label created"); customs clearance started ("At customs"); clearance finished, unloading or loading at a sorting centre and unloading at the destination site ("In transit"); JD's courier on the way to you ("Out for delivery"); signed for ("Delivered"). Each line keeps JD's own words. JD's out-for-delivery line names the courier and gives their phone number: Peek leaves both out.

**Lines from JD's Chinese tracker.** For a parcel inside China, the lines are in Chinese. Among them: `京东快件已取件` (collected from the sender), `已到达【…】` (arrived at the site named in the brackets), `正在派送中` (out for delivery) and `已送达` (delivered).

## JD Logistics delivery in the US, UK, Canada and Australia

**Parcels from China.** JD's Cross-Border Parcels service, for online sellers shipping from China, lists the US, the UK, Australia, Canada, Mexico and Russia. Sellers choose standard, express or economic shipping. JD flies the parcels out, then hands them to local partners for the last mile, which takes 3 to 6 working days on average, JD says. It doesn't name those partners. In the US, JD's history shows [GOFO Express](carrier:gofo) delivering, and GOFO's steps come in JD's own tracking.

**Parcels from a JD warehouse near you.** JD also runs warehouses abroad: in the US (New Jersey, Los Angeles and Atlanta), in England and in Sydney, among others. A seller who keeps stock there can ship your order from inside your country: that's when the history shows `Picking Completed.` and `Weighed at Warehouse.` first. A parcel from China starts with `Shipment Picked up` instead.

JD's pages don't say how many delivery attempts the local carrier makes, or where a missed parcel waits: that's up to the carrier in your country. See [missed a delivery](guide:missed-delivery).

**Customs.** A parcel from China goes through customs in your country. JD's rules for its international express from China say duties and taxes may arise at clearance, that the sender or the recipient pays them, and that the shipping charge doesn't include them. Ask the shop whether your price did. More in [parcels held at customs](guide:customs).

## JD Logistics parcel not updating, lost or returned

**Nothing yet.** If JD's tracker finds nothing under the number, check it with the seller. Peek shows "Waiting for the carrier" and keeps checking.

**No update for days.** When a parcel still on its way has had no new scan for 4 days, Peek notes that parcels often go quiet on long flights, at customs or over a weekend, and adds a "Get help from JD Logistics" link to JD's contact page. More in [why tracking stops updating](guide:tracking-not-updating) and [tracking parcels from China](guide:tracking-from-china).

**Late or lost.** The seller booked JD, so ask the seller first. For its international express from China, JD's rules call the estimated delivery time on the waybill a reference only: JD refunds no shipping and pays no compensation when it's late. See [lost parcels](guide:lost-parcel).

**Returned.** JD's rules for its international express from China offer no return service once a parcel has left mainland China. To return an order, ask the shop how. See [returned to sender](guide:returned-to-sender).

**Delivered but not there.** Ask the shop, check with neighbours and your building's reception, and see [delivered but not received](guide:delivered-not-received).

## JD Logistics customer service and contact

JD's international [Contact Us page](https://www.jingdonglogistics.com/contact) gives its customer service by country, with opening hours (as read on 30 October 2026):

- **United States**: org.cs.us1@jd.com, Monday to Friday, 8:00 to 17:00.
- **United Kingdom**: org.cs.eu1@jd.com, Monday to Friday, 9:00 to 18:00, the same customer service as for Germany, the Netherlands and France.
- **Australia**: +61 (02) 87239755 and org.cs.intl1@jd.com, Monday to Friday, 9:00 to 18:00.

JD names no time zone for these hours, is closed on public holidays and lists no contact for Canada or Ireland. In China, JD's hotline is 950616, written 0086 950616 on JD's English International Express page; the [Contact Us page on jdl.com](https://www.jdl.com/en/contactUs) also has an "Online Consultation" button. For the order itself, ask the shop: the seller booked JD, not you.

## Track a JD Logistics parcel with Peek

[Peek](/) names JD Logistics at once from the JD… shapes in the table above. A VG number fits La Poste's shape too: Peek checks it with La Poste and other tracking services, and if they don't find it, tap "Change" and pick JD Logistics. A jingdonglogistics.com tracking link with `waybillCodes=` files the parcel under JD Logistics whatever number it holds; from a jdl.com link, Peek takes the number only when it has one of the JD… shapes. If your JD number looks different, paste it all the same: Peek follows more than 3,500 carriers. Then tap "Change" and pick JD Logistics under "All carriers" (searching Jingdong or 京东物流 finds it too), or use "Change carrier" on a parcel you've already added.

Peek reads JD's international tracking: the status, each step in JD's words with its town and country (in English, even when JD writes the country in Chinese) and the delivery time. Times are read on the clock of the place that made the scan and shown in your device's time zone. Peek shows no expected delivery date for JD parcels. It leaves out the recipient's details and the delivery photos JD's tracking can hold, the courier's name and phone number, and the local carrier's number at the end of a departure line.

For a number starting with JDJ and 12 digits, or JDAZ and 11, Peek also opens JD's Chinese tracker on jdl.com when the international one has nothing. It shows those lines in Chinese, without the courier's name, phone number or the name of whoever signed. For other JD numbers, Peek doesn't open the Chinese tracker: if a parcel inside China shows nothing in Peek, look it up on jdl.com. An empty answer from JD doesn't prove the number wrong: Peek keeps checking.

Peek checks every 10 minutes, and every 2 minutes (every 5 when it has to open JD's Chinese tracker) from 6 a.m. local time on the day JD scans the parcel out for delivery, while JD's own tracker answers; through tracking services, every 15 minutes. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". It can't contact JD or the shop for you.

## Questions about JD Logistics tracking

### Is JD Logistics legit?

Yes. JD.com began building its nationwide logistics network in 2007, set up JD Logistics as a business group in 2017 and listed it on the Hong Kong Stock Exchange in May 2021 (stock code 2618). On 30 June 2026 it had more than 1,600 warehouses of its own, plus 2,000 run by third parties, across 26 markets, it says.

### How do I track a JD Logistics parcel?

On jingdonglogistics.com/Tracking, or on jdl.com for a parcel inside China. Or paste the number in the box above.

### Who delivers JD Logistics parcels in the US?

JD doesn't name its local partners. In the US, JD's history shows GOFO Express delivering: after `Handed over to Carrier` or `Handover to local carrier` come steps such as `Arrived at GOFO Regional Hub` and `Arrived at GOFO Delivery Station`, in JD's own tracking.

### Why is my JD Logistics tracking not updating?

JD has nothing under the number yet, the parcel is on a flight or at customs, or the local carrier hasn't reported. After 4 days without a scan, ask the seller, or JD's customer service for your country. For a parcel inside China, check jdl.com.

### How do I contact JD Logistics?

Through the Contact Us page on jingdonglogistics.com, which gives customer service and hours for the US, the UK, Australia and other countries, not Canada or Ireland. In China, through jdl.com's Contact Us page. About your order, ask the shop first.

:::sources
- [JD Logistics: Track your shipment](https://www.jingdonglogistics.com/Tracking) – English tracker, up to 10 numbers separated by spaces, Enter or "Track"
- [JD Logistics: About JINGDONG Logistics](https://www.jingdonglogistics.com/about) – JD.com's logistics business, HKEX 2618, JoyLogistics and JoyExpress, warehouses and markets on 30 June 2026
- [JD Logistics: Contact Us](https://www.jingdonglogistics.com/contact) – customer service mailboxes, Australia's number, hours and closed days by country, 950616
- [JD Logistics: Cross-Border Parcels](https://www.jingdonglogistics.com/ser/CrossExport) – countries served, shipping options, flights, last-mile partners, 3 to 6 working days
- [JD Logistics: JINGDONG Logistics (USA)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUS) – US warehouses
- [JD Logistics: JINGDONG Logistics (United Kingdom)](https://www.jingdonglogistics.com/ser/GlobalWarehouseUK) – warehouse in England
- [JD Logistics: JINGDONG Logistics (Australia)](https://www.jingdonglogistics.com/ser/GlobalWarehouseAS) – Sydney warehouses
- [JDL: 运单查询 (waybill search)](https://www.jdl.com/orderSearch) – Chinese tracker, waybill or package number, several numbers
- [JDL: 服务细则 (service rules)](https://www.jdl.com/serviceDetails) – international express: duties and taxes, estimate for reference only, no returns once abroad
- [JDL: International Express](https://www.jdl.com/en/crossBorder/internationalC2C) – express from China to overseas recipients, 0086 950616
- [JDL: Contact Us](https://www.jdl.com/en/contactUs) – hotline 950616, Online Consultation
- [JDL: Company Profile](https://www.jdl.com/en/profile) – 2007, 2017, Hong Kong listing in May 2021
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shapes and links, JD's step wordings and codes, GOFO in JD's history, what Peek reads and leaves out, JD's Chinese tracker
:::
