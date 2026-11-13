---
title: CNE Express tracking: 3A5V numbers, statuses and contact
description: Track a CNE Express (递一物流) parcel from China: what a 3A5V number looks like, what CNE's statuses mean, who delivers it near you and who to ask.
slug: cne-express-tracking
published: 2026-11-02
updated: 2026-11-09
---

CNE Express (递一物流, CNE EXPRESS CO LTD) is a cross-border logistics company in Shanghai, China, founded in 2003. It ships parcels abroad for online sellers, including sellers on eBay, Walmart, TikTok, Temu and SHEIN, flies them out of China and hands them to a partner in your country for the last stretch. Track the number on [CNE's tracker](https://www.cne.com/en/track), in English, or paste it in the box above. The seller booked CNE and gives you the number with your order's shipping details.

## CNE Express tracking number: what it looks like

CNE publishes no number format. The shape Peek knows as CNE's, with invented digits:

| Number | What it is |
| --- | --- |
| `3A5V000000000` | 3A5V and 9 digits: a CNE Express number. |
| `3A5V00000000` | Only 8 digits: not CNE's shape. Peek doesn't name CNE, and checks it with Colis Privé, whose 12-character numbers it fits, and with tracking services. Check for a missing digit. |

Lower case, spaces and dashes don't matter in Peek. CNE's tracker takes several numbers at once, separated by spaces or commas.

**Where to find it.** In the seller's shipping email or on your order page. If you can't find it, see [how to find your tracking number](guide:find-tracking-number), or the guides to [Temu orders](guide:temu-orders) and [SHEIN orders](guide:shein-orders).

**Other numbers.** Under `Shipment Information`, CNE's tracker can also show the seller's `Order number` and a `Last mile number`: the delivering carrier's number for your parcel, with that carrier's website and phone.

**CNE's postal parcels.** CNE also sells China Post's own services, such as International E-packet and China Post Packet. For a number in China Post's shape, see [China Post](carrier:china-post).

## CNE Express tracking statuses explained

CNE's tracker puts a status above each parcel's lines. In English, as CNE labels them:

| CNE's status | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipping` | On its way. | the newest line's status |
| `Customs inspection` | Held by customs (扣关 on CNE's Chinese page). | "At customs" |
| `Delivered` | Delivered. | "Delivered" |
| `Invalid address`, `Lost`, `Exception`, `Destroyed` | A wrong address, a lost or destroyed parcel, or another problem. | "Needs attention" |
| `Returned` | Sent back to the seller. | "Returned to sender" |
| `Not delivered` | CNE's Chinese page says 未发送, "not sent": the parcel hasn't left yet. | the newest line's status |

`Time out` and `Unknown` can appear too: Peek goes by the lines for those. Each line gives a time, CNE's wording and a place. The lines people see, in the order a parcel usually meets them:

| CNE says | What it means for you | Peek shows |
| --- | --- | --- |
| `Parcel Infomation Received` | The seller sent CNE the parcel's details (the spelling is CNE's). CNE doesn't have it yet. | "Label created" |
| `Picked Up by Courier` | CNE collected the parcel from the seller in China. | "Received by carrier" |
| `Arrived at CNE Facility`, `Inbound scan`, `Shipment Processed`, `Shipment Loaded` | At a CNE facility: scanned in, sorted, loaded. | "In transit" |
| `Departed CNE Facility`, `Departure Scan`, `Depart From Local Facility` | It left a facility. | "In transit" |
| `Arrived Export Airport`, `Released From Export Customs` | At the airport in China, then through China's export customs. Not your country's customs. | "In transit" |
| `Shipment Departed From Original Country` | The flight has left China. | "In transit" |
| `Arrived At Transit Airport`, `Shipment Depart From Transit Airport` | A stop on the way. | "In transit" |
| `Arrived at Import Airport`, `Arrived at destination` | It landed in your country. Sorting and customs are still to come: it isn't at your local delivery office. | "In transit" |
| `The shipment is picked up by customs broker` | A customs broker took it for import clearance. | "At customs" |
| `Released From Import Customs`, `Pickup from airport` | Through import customs, then collected from the airport. | "In transit" |
| `Handled by local carrier` | CNE handed it to the carrier that delivers in your country. That carrier's own acceptance comes next. | "In transit" |
| `Arrived At The Delivery Company`, `Your shipment has arrived at sorting center`, `Your shipment is in process at sorting center`, `Parcel Unloaded From Container` | With the carrier that delivers it, or at a sorting centre before it. | "In transit" |
| `Your shipment has been processed at sorting center and ready for shipping`, `Your shipment has departed from the transit origin`, `Your shipment has arrived at the transit destination` | Sorted in your country and sent on to the delivering carrier's local station, where it arrives before the delivery round. This "transit" is that last leg, not a stop abroad. | "In transit" |
| `On Board with Courier` | On the courier's van: out for delivery. | "Out for delivery" |
| `Your shipment delivery attempt failed [Recipient not at home]` | The courier found nobody at home. The delivery is tried again. | "Delivery attempted", or "Needs attention" while CNE's status says `Exception` |
| `Sorting failed []` | Sorting went wrong. The empty brackets are CNE's. | "Needs attention" |
| `It was returned to customer's address by CNE` | CNE sent it back to the seller, its customer. | "Returned to sender" |

After the hand-over, the delivering carrier's own lines appear in CNE's history, often as a short label followed by an explanation. Peek reads the label, and for wording it doesn't know, the status CNE gives that line. CNE's tracker prints times with no time zone, and they aren't all on one clock (see the questions below).

## Who delivers CNE Express parcels

CNE runs its own lines to Europe, the US, Canada, Australia, Brazil and other countries, and picks local partners abroad for the delivery. Its partner page lists USPS, Royal Mail, Austrian Post, Correos, Poste Italiane, GLS, La Poste, DHL, Evri, DPD and POST Luxembourg among its global logistics partners.

Peek reads three of the codes CNE gives its last-mile suppliers: DHL in Germany ([DHL Paket](carrier:dhl)), [Royal Mail](carrier:royal-mail) in the UK and [USPS](carrier:usps) in the US. When CNE names one of them and gives that carrier's own number for the parcel, Peek's parcel card says so ("Delivery with USPS", for example) and shows the number. While that carrier has only the announcement of the parcel, with no scan of its own, the card says "Announced to …, not received yet" instead. Without that number, Peek names no delivering carrier: the carrier tracks the parcel by its own number, not CNE's. Other codes, such as US SPX, name no carrier in Peek.

CNE publishes no rules for delivery attempts, notice cards or pickup points: those are the delivering carrier's. See its page, such as [Evri](carrier:evri), [La Poste / Colissimo](carrier:la-poste), [Correos](carrier:correos-spain), [Poste Italiane](carrier:poste-italiane), [Austrian Post](carrier:austrian-post) or [POST Luxembourg](carrier:post-luxembourg), and [missed a delivery](guide:missed-delivery).

**Customs in the EU.** Goods bought online from outside the EU carry a €3 customs duty per item on parcels worth up to €150, since 1 July 2026, and a €2 Union handling fee per item, whatever the value, since 1 November 2026. An item is the goods sharing a tariff classification, description and, where required, origin. CNE told its sellers on 22 October 2026 that on its EU routes it collects the €2 fee from them in advance from 26 October, for each tariff code in each parcel, on top of the €3 duty it has charged the same way since July, and that it clears customs on the details the seller declares. Its notice says nothing about VAT, or about anything the delivering carrier may ask you for. Elsewhere, what you owe depends on your country: see [parcels held at customs](guide:customs).

**Delays.** CNE posts route delays, such as strikes or bad weather abroad, on its news page in Chinese (新闻公告).

## CNE Express parcel not updating, lost or returned

CNE's customers are the sellers who book it: for a late, lost, damaged or returned parcel, contact the seller or the marketplace first.

**Nothing yet.** If CNE's tracker finds nothing, CNE has no record of the number yet: Peek shows "Waiting for the carrier" and keeps checking. `Not delivered` means the parcel hasn't been sent yet (未发送 on CNE's Chinese page); Peek then shows the newest line's status, such as "Label created" for `Parcel Infomation Received`. If nothing changes after a few days, check the number with the seller.

**No update for days.** When a parcel still on its way has had no new line for four days, Peek flags it ("No tracking update for four days") and notes that parcels often go quiet on long flights, at customs or over a weekend. While Peek follows the parcel on CNE's tracker, the note adds a "Get help from CNE Express" link to CNE's Contact us page. See [why tracking stops updating](guide:tracking-not-updating) and [tracking parcels from China](guide:tracking-from-china).

**At customs.** `Customs inspection` (扣关, held by customs) or `The shipment is picked up by customs broker` means customs has the parcel. CNE clears it on the details the seller declares, so ask the seller first if it stays there.

**Lost, destroyed, wrong address or returned.** For `Lost`, `Destroyed` or `Invalid address`, tell the seller at once and ask for a refund or a new parcel: the seller booked CNE and is the one CNE deals with. `Returned` or `It was returned to customer's address by CNE` means the parcel went back to the seller: ask the shop to send it again or refund you. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

**Delivered but not there.** On CNE's tracker, a line with `View POD` holds the proof of delivery, such as a photo: CNE asks for the recipient's name or postcode to show it. Check where it was left, ask neighbours, then tell the seller. See [delivered but not received](guide:delivered-not-received).

## CNE Express customer service and contact

As read on 31 October 2026 on CNE's [Contact us page](https://www.cne.com/en/about/contact):

- **Hotline**: 400-021-5600, a Chinese service number, which CNE calls its National Service Hotline.
- **Shanghai main office**: 021-5109 6677, 15/F, Block G, Lane 1777, Hualong Road, Qingpu District, Shanghai.
- **Hours**: 08:30 to 18:00, Monday to Sunday. CNE gives no time zone; its sorting centres work around the clock.
- **Online form**: the consulting form on the same page. Choose `Complaint` as the type, and give your phone, email, name, tracking number and a message.

CNE lists no customer service email; its other offices, across China, serve sellers. About your order, ask the seller first: it booked CNE, not you.

## Track a CNE Express parcel with Peek

[Peek](/) names CNE Express at once from a 3A5V number ("Detected carrier"). A cne.com tracking link with `?no=` files the parcel under CNE Express whatever number it holds; if the link holds several, Peek takes the first. For any other CNE number, paste it all the same: Peek follows more than 3,500 carriers. When the number's shape fits several carriers, Peek shows "Checking tracking services…" while it asks around; when it fits none, Peek looks the number up once you add it. If Peek doesn't name CNE, tap "Change" and pick CNE Express under "All carriers" (searching CNE or 递一物流 finds it too), or use "Change carrier" on a parcel you've already added.

Peek reads CNE's own tracking: the status, each line with its place, the destination country, and the delivering carrier when CNE names one of the three above with its number. It reads `Arrived at destination`, and CNE's line handing the parcel to the local carrier, on Beijing time, works out the other lines' clock from their place or the leg the parcel was on, and shows every time in your device's time zone. It leaves out the seller's order number, the delivering carrier's website and phone, and CNE's proof of delivery. CNE gives no expected delivery date or weight.

When CNE names the delivering carrier and gives its number, Peek asks that carrier too: one of the three above, your country's post for a postal number (two letters, nine digits, two letters), or the one carrier that number's shape points to. Once that carrier shows a scan at least as recent as CNE's newest, Peek follows the parcel there and keeps CNE's history.

Peek checks every 10 minutes, and every 2 minutes from 6 a.m. local time on the day CNE scans the parcel out for delivery, while CNE's own tracker answers (through tracking services, every 15 minutes). When another carrier takes over the delivery, Peek follows the pace of the carrier delivering the parcel: hourly for Royal Mail and Poste Italiane, every 30 minutes for PostNL and USPS, out for delivery too. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". It can't contact CNE or the seller for you.

## Questions about CNE Express tracking

### Is CNE Express legit?

Yes. CNE EXPRESS CO LTD (递一国际物流有限公司) was founded in 2003 and is headquartered in Shanghai, with sorting centres in Jiaxing and Dongguan that have been able to process more than 2 million shipments a day since 2020, it says. It ships for sellers on Amazon, eBay, Walmart, TikTok, Temu and SHEIN, among others.

### Who delivers CNE Express parcels?

A local post or courier that CNE picks, such as the partners on its partner page: Royal Mail, La Poste, DHL, GLS, DPD or Evri. CNE's tracker can show your parcel's `Last mile number`.

### What does "Arrived at destination" mean on CNE Express?

The parcel has reached your country. Sorting and import customs can still follow before the delivering carrier takes it, so it isn't at your local delivery office yet. CNE's tracker shows this line on Beijing time, 8 hours ahead of London and 7 ahead of Paris or Berlin in winter, while lines abroad can be on local time: so it can look out of order.

### How long does CNE Express take to deliver?

CNE gives sellers reference times that vary by route: 3 to 8 business days for CNE Priority Express, to the US, the UK, France and Germany, and 5 to 20 for CNE Standard Express. The shop's estimate for your order is the one to go by.

### How do I contact CNE Express?

By its hotline, 400-021-5600, or its online consulting form, choosing `Complaint`. About your order, ask the seller first.

:::sources
- [CNE: Track your parcel](https://www.cne.com/en/track) – tracker, status labels, lines, Shipment Information, View POD
- [CNE: 运单追踪 (tracking, Chinese)](https://www.cne.com/track) – 未发送 and 扣关 behind the English labels
- [CNE: Home](https://www.cne.com/en) – 2003, Shanghai, local partners abroad, destinations, platforms, hotline
- [CNE: 首页 (home, Chinese)](https://www.cne.com/) – 递一物流, 递一国际物流有限公司
- [CNE: About us](https://www.cne.com/en/about/intro) – sorting centres, capacity of over 2 million shipments a day since 2020, platforms
- [CNE: Partner](https://www.cne.com/en/about/partner) – global logistics partners
- [CNE: Contact us](https://www.cne.com/en/about/contact) – hotline, Shanghai office, hours, consulting form
- [CNE: Postal Parcel](https://www.cne.com/en/solutions/economyParcel) – China Post's services sold by CNE
- [CNE: CNE Priority Express](https://www.cne.com/en/solutions/product/36024) – 3 to 8 business days, routes
- [CNE: CNE Standard Express](https://www.cne.com/en/solutions/product/36025) – 5 to 20 business days
- [CNE: 新闻公告 (news and notices)](https://www.cne.com/news/notice) – delay notices by destination
- [CNE: 关于欧盟国家2026年11月税改通知 (EU tax change notice)](https://www.cne.com/news/article/rDe4ANa6KYOW) – €2 collected from sellers in advance from 26 October, on top of the €3, per parcel and tariff code; customs cleared on the seller's declaration
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per item whatever the value, what an item is
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shape and links, wordings and codes (last-mile sorting, station and missed-delivery lines since 9 November 2026), last-mile suppliers, clocks, hand-over, what Peek reads
:::
