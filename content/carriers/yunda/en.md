---
title: Yunda Express tracking: waybill codes, statuses & 95546
description: Track a Yunda Express (韵达快递) domestic China parcel: 13- and 15-digit waybills, Chinese scan stages, return-leg tracking and Shanghai 95546 customer support.
slug: yunda-tracking
published: 2026-11-23
updated: 2026-11-23
---

**Yunda Express** (*Shanghai Yunda Freight Co., Ltd.*, Chinese *韵达快递* / *韵达速递* / *上海韵达货运有限公司*) is one of China's largest nationwide express parcel networks, headquartered in Qingpu District, Shanghai ([yundaex.com](https://www.yundaex.com/cn/index.php)). Yunda runs express delivery, including e-commerce and international express, alongside warehousing, freight (*韵达快运*), cold chain and supply-chain services. You can check a domestic waybill on [web.yundaex.com/infoInquiry](https://web.yundaex.com/infoInquiry) or paste your 13- or 15-digit Yunda waybill number into the box above.

## Yunda Express tracking numbers (13- and 15-digit domestic waybills)

Yunda Express domestic China waybills are pure numeric strings of **13 or 15 digits**:

| Format (invented) | What it is |
| --- | --- |
| `3148291736054` | 13-digit Yunda Express domestic parcel waybill. |
| `437391846205819` | 15-digit Yunda Express domestic parcel waybill (common on modern e-commerce orders). |

Pure 13- and 15-digit numbers overlap with other carriers' numbers, so Peek asks Yunda only when you paste a `web.yundaex.com/infoInquiry?...&homeWaybill=` link or pick Yunda Express yourself. See [tracking number formats](guide:tracking-number-formats).

## What Yunda Express tracking statuses mean

Yunda's consumer tracker assigns five standard Chinese status labels to domestic parcel checkpoints, which Peek translates clearly into English — including distinguishing when a parcel is travelling back to the sender on a return leg:

| Yunda label (CN) | English meaning | Peek shows |
| --- | --- | --- |
| `已揽件` | Parcel collected and scanned by the origin Yunda courier or outlet. | "Received by carrier" |
| `运输中` | Moving between Yunda sorting hubs and transfer centres across Chinese provinces and cities. | "In transit" |
| `派送中` | Out for delivery with the local Yunda courier (or out for return delivery back to the sender if a return scan occurred). | "Out for delivery" |
| `待取件` | Dropped off at a local pickup station, Cainiao post or parcel locker waiting for collection (or waiting for sender collection on a return leg). | "Ready for pickup" |
| `已签收` | Signed for or collected by the recipient (or by the consolidation warehouse when forwarding overseas). | "Delivered" |
| `已签收` after an explicit return scan (`退回件扫描` / signed by sender) | Returned back and signed for by the original shipper rather than the buyer. | "Returned to sender" |

## Domestic China delivery, consolidation warehouses and returns

- **Shopping from abroad via a China warehouse:** if you bought from Taobao, 1688 or Pinduoduo using a forwarding agent or consolidation warehouse (such as [DobroPost](carrier:dobropost) or a freight forwarder in Guangzhou, Shenzhen, Dongguan or Yiwu), `已签收` ("Delivered") on Yunda Express means the parcel reached your **China consolidation warehouse** — not your home country. After intake, the forwarder assigns a new international tracking number.
- **Parcel stations (`待取件`):** inside China, `待取件` means the parcel waits at a parcel station or locker for the recipient to collect it.
- **Return-to-sender detection:** Yunda's raw tracker labels a returned parcel signed by the sender with the same `已签收` tag as a normal delivery; Peek checks the underlying scan text for explicit return or sender-signature wording and marks it "Returned to sender" instead.

## Yunda Express parcel stuck, misdelivered or missing

- **Domestic marketplace order:** contact the seller on Taobao, Tmall, Pinduoduo or JD first — the seller can ask the Yunda outlet (*网点*) that collected the parcel to trace it.
- **No scan after label creation:** merchants often generate a 15-digit Yunda waybill before handover; scans begin once the outlet records `已揽件` (*Picked up*). See [why tracking stops updating](guide:tracking-not-updating).
- **Delivered (`已签收`) but not found:** confirm whether a parcel locker, building security desk or consolidation warehouse signed for it, and open a seller or carrier complaint promptly if it is missing or damaged. See [delivered but not received](guide:delivered-not-received), [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

## Yunda Express customer service and Shanghai contacts

Official contact details published on [Yunda Contact Us](https://www.yundaex.com/cn/about_contact.php) and [yundaex.com](https://www.yundaex.com/cn/index.php):

- **National customer service hotline (China):** **`95546`**.
- **Online complaints & tracking:** [yundaex.com/cn/about_contact.php](https://www.yundaex.com/cn/about_contact.php) and [web.yundaex.com/infoInquiry](https://web.yundaex.com/infoInquiry).
- **Fax:** `021-39297127` (media line `021-39291355`).
- **Headquarters:** *Shanghai Yunda Freight Co., Ltd.* (*上海韵达货运有限公司*), No. 6679 Yinggang East Road, Qingpu District, Shanghai `201700`, China (*上海市青浦区盈港东路6679号*).

> Beware of fake text messages or WeChat links asking you to pay a redelivery fee or enter bank details for a Yunda parcel. Verify any tracking issue through your marketplace app or Yunda's official hotline `95546`. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Yunda Express parcel with Peek

Peek doesn't ask Yunda from a 13- or 15-digit number alone (`3148291736054` or `437391846205819`, invented): for 13 digits it asks [iMile](carrier:imile) and [FAN Courier](carrier:fan-courier), among others, and for 15 digits [BRT](carrier:brt). Tap "Change" while adding or "Change carrier" on the parcel screen, where Yunda Express is under "Fits this number" (searching "Yunda" or "uda International" finds it too), or paste a `web.yundaex.com/infoInquiry?...&homeWaybill=` link into [Peek](/), which recognises it as Yunda's.

Peek reads Yunda's tracker and shows the stage ("Received by carrier", "In transit", "Out for delivery", "Ready for pickup", "Returned to sender", "Delivered"), the scan's wording in English, the city or district named at the start of the scan text, and the delivery time. Yunda gives its scans on Beijing time, and Peek shows them on your device's clock. On a return, the scans say they are on the way back to the sender. The recipient's address stays private, and Peek leaves out courier names and phone numbers, pickup-station addresses, signature images and unlabelled weights. Yunda's tracker gives no estimated delivery day.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day Yunda shows `派送中`, every 2 minutes from 6 a.m. Beijing time, while Yunda's tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When Yunda's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Yunda Express.", with a "Get help from Yunda Express" link to Yunda's contact page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Yunda Express."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot request redelivery, retrieve locker pickup codes or contact Yunda Express for you.

## Questions about Yunda Express tracking

### What does a Yunda Express tracking number look like?

Domestic Yunda Express waybills are 13 or 15 numeric digits (for example `3148291736054` or `437391846205819`, invented).

### What do `已揽件`, `派送中`, `待取件` and `已签收` mean?

`已揽件` means picked up, `派送中` means out for delivery, `待取件` means ready for pickup at a parcel station or locker, and `已签收` means delivered (unless preceded by a return scan back to the sender).

### Why does my Yunda parcel say "Delivered" while I am outside China?

If you ordered through a Chinese forwarding agent or consolidation warehouse, Yunda Express delivers only the domestic China leg to that warehouse. Wait for your forwarder to log the parcel in and issue an international tracking number.

### How do I contact Yunda Express customer service?

Call Yunda's national China hotline at **`95546`** or use the official contact and complaints page at [yundaex.com/cn/about_contact.php](https://www.yundaex.com/cn/about_contact.php).

:::sources
- [Yunda Express: Official Website](https://www.yundaex.com/cn/index.php) – *Shanghai Yunda Freight Co., Ltd.* (*上海韵达货运有限公司*) domestic express, freight, cold-chain and warehousing services
- [Yunda Express: Contact Us](https://www.yundaex.com/cn/about_contact.php) – hotline `95546`, fax `021-39297127`, online complaints system and Qingpu Shanghai HQ (help URL linked by Peek)
- [Yunda Express: Waybill Inquiry](https://web.yundaex.com/infoInquiry) – official domestic tracking lookup
- [Universal Parcel Scraper: yunda](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/yunda) – 13/15-digit waybill rule, `web.yundaex.com` link rule, 5-label Chinese status map, return-leg detection, city extraction and Beijing-time scans
:::
