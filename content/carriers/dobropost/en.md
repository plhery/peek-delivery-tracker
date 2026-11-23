---
title: DobroPost tracking: DBRPST codes, statuses & China hubs
description: Track a DobroPost parcel from China to Russia or Belarus: DBRPST tracking formats, warehouse and customs scans, PVZ pickup, partner handoffs and support.
slug: dobropost-tracking
published: 2026-11-23
updated: 2026-11-23
---

DobroPost (*ДоброПост*, operated by *OOO "Dobropost Logistika"* in Moscow together with *OOO ICOM DP SOLUTIONS* in Armenia) is a China-to-Russia and China-to-Belarus parcel forwarder used by shoppers buying from Chinese marketplaces such as Taobao, Poizon, 1688, Pinduoduo, Tmall, 95, Goofish and JD. Buyers have their purchases sent to DobroPost's warehouse in Jixi, Heilongjiang, where the goods are checked, packed and weighed; the parcel then clears customs and goes to one of DobroPost's own pickup points (PVZ) or to [CDEK](carrier:cdek), DPD or [Russian Post](carrier:russian-post). DobroPost's tracker also relays Boxberry's scans. You can check a shipment on DobroPost's tracker at [tracking.dobropost.com](https://tracking.dobropost.com/) or paste your `DBRPST` code into the box above.

## DobroPost tracking numbers (`DBRPST` format)

A DobroPost parcel number starts with `DBRPST`, followed by 10 or 11 digits:

| Format (invented) | What it is |
| --- | --- |
| `DBRPST6482917304` | 16-character DobroPost parcel number (`DBRPST` + 10 digits). |
| `DBRPST73918462051` | 17-character DobroPost parcel number (`DBRPST` + 11 digits). |

The numbers Chinese couriers give your orders on their way to DobroPost's warehouse are theirs, not DobroPost's: [tracking.dobropost.com](https://tracking.dobropost.com/) and Peek follow the parcel by its `DBRPST` number. See [tracking number formats](guide:tracking-number-formats).

## What DobroPost tracking statuses mean

DobroPost publishes checkpoints in Russian together with internal status codes — first from its own China warehouse and customs clearance stages, then relayed directly from the domestic last-mile carrier ([CDEK](carrier:cdek), Boxberry or [Russian Post](carrier:russian-post)):

| DobroPost code or wording | What it means for you | Peek shows |
| --- | --- | --- |
| `VIRTUAL_PARCEL` | The parcel is on DobroPost's record; the warehouse in China packs your goods into it a few days later. | "Label created" |
| `ON_ASSEMBLY`, `ON_WEIGHING`, `AWAITING_PAYMENT`, `READY_FOR_SENT`, `ADDED_TO_THE_REGISTRY` | Packed and weighed at DobroPost's China warehouse, awaiting shipping payment or added to an outbound dispatch registry. | "Received by carrier" |
| `LEFT_THE_DP_WERHOUSE_IN_CHINA`, `FLEW_OUT_FROM_CHINA` | Left DobroPost's China warehouse and departed China on international linehaul. | "In transit" |
| `AT_CUSTOMS_IN_CHINA`, `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, `START_OF_CUSTOMS_CLEARANCE`, `Выпуск с таможни` | Going through export clearance in China or import customs clearance in Russia or Belarus. | "At customs" |
| `RELEASE_OF_GOODS_WITHOUT_PAYMENT_OF_CUSTOMS_DUTIES`, `LEFT_CUSTOMS_AND_HEADING_TO_LAST_MILE_CARRIER'S_WAREHOUSE`, `READY_FOR_SHIPMENT_FOR_LAST_MILE_DELIVERY` | Released from customs and moving into the destination last-mile network. | "In transit" |
| `REFUSAL_TO_RELEASE_GOODS_WITH_INDICATION_OF_THE_CODE_OF_THE_REASON_FOR_REFUSAL` | Customs refused the release; the tracker gives the reason, such as goods not for personal use. Check your DobroPost account. | "Needs attention" |
| `RECEIVED_AT_SHIPMENT_WAREHOUSE`, `SEND_TO_TRANSIT_OFFICE`, `SEND_TO_RECIPIENT_OFFICE`, `ACCEPTED_FOR_DELIVERY`, `SENT_TO_DESTINATION_CITY`, `Прием`, `Обработка` | Relayed domestic transit scans from CDEK, Boxberry or Russian Post moving toward your city. | "In transit" |
| `ACCEPTED_AT_WAREHOUSE_ON_DEMAND` (CDEK), `RECEIVED_AT_THE_POINT_OF_ISSUE` (Boxberry) | Arrived at your chosen pickup office or parcel shop and ready for collection. | "Ready for pickup" |
| `GETTING_READY_TO_RETURN`, `SENT_TO_COLLECTION_POINT`, `RETURNED_TO_COLLECTION_POINT`, `RETURNED`, `Возврат` | Not collected from the pickup point, or returned on the last leg, and going back to DobroPost, which can send it again. | "Returned to sender" |
| `RELEASE_OF_GOODS_CUSTOMS_PAYMENTS_PAID` (`Возврат в Китай разрешен`) | Customs refused the release and allowed the parcel back to China. | "Returned to sender" |
| `PARCEL_DELIVERED_TO_RECIPIENT`, `DELIVERED`, `Вручение` | Collected from the pickup point or delivered to the recipient. | "Delivered" |

## Delivery times, tariffs and DobroPost PVZ pickup points

According to [DobroPost Tariffs](https://dobropost.com/tariffs), transit times are counted from dispatch out of DobroPost's China warehouse to arrival at a pickup point (PVZ) or handover to a domestic courier:

- **Express tariffs** (volumetric weight, no sensitive goods): typically **9–13 days** to Moscow, **10–14 days** to Saint Petersburg, **9–12 days** to Novosibirsk, Krasnodar, Yekaterinburg, Kazan, Irkutsk and Ulan-Ude, and **11–15 days** to Minsk (Belarus).
- **Economy tariffs** (actual weight, suited to heavier or bulky parcels): typically **19–25 days** to Moscow, **20–27 days** to Saint Petersburg, **15–23 days** to Irkutsk and **22–29 days** to Minsk.
- **Far East & Heavy Parcel tariffs:** **6–9 days** to Vladivostok, **8–12 days** to Blagoveshchensk, and **6–9 days** for single-item heavy parcels from 5 to 30 kg.
- **DobroPost PVZ pickup points:** listed on [dobropost.com/pvz](https://dobropost.com/pvz) in Moscow, Odintsovo, Saint Petersburg, Kazan, Krasnodar, Novosibirsk, Yekaterinburg, Irkutsk, Ulan-Ude, Blagoveshchensk, Vladivostok, Ussuriysk and Minsk. Published PVZ issue fees are `100 RUB` (up to 5 kg), `200 RUB` (up to 10 kg) and `300 RUB` (over 10 kg); cities without a DobroPost PVZ use partner pickup points and couriers. A parcel waits at a DobroPost PVZ for 7 days free, then for 50 ₽ a day, and is disposed of 30 days after it arrives.

## Customs, quiet gaps and parcel issues

- **Shipping payment required before dispatch:** if tracking stops at `AWAITING_PAYMENT`, the parcel stays in the China warehouse until you pay the calculated shipping invoice in your DobroPost client account.
- **Quiet gaps during border transit and customs:** scans commonly pause between `FLEW_OUT_FROM_CHINA` and `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, or during customs review. See [parcels held at customs](guide:customs) and [why tracking stops updating](guide:tracking-not-updating).
- **Damage, missing items or returns:** DobroPost's [terms](https://dobropost.com/rules) ask you to note any damage on the delivery sheet when you receive the parcel, and to show the contents and the original packaging for a damage claim. Its [Knowledge Base](https://dobropost.com/baza_main) says what to do when an item is missing from a parcel. A parcel not collected from the pickup point goes back to DobroPost (`RETURNED`), which can send it again: ask its support. See [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel), [delivered but not received](guide:delivered-not-received) and [returned to sender](guide:returned-to-sender).

## DobroPost customer service and official links

DobroPost's site has a "Напишите в поддержку" (write to support) button, a customer community and a Telegram channel, and invites complaints and ideas straight to its CEO, Sergey Serbin. Its [Knowledge Base](https://dobropost.com/baza_main) explains how to send a request to support, change the tariff of a parcel already created and clear customs.

- **Registered Russian operator:** *OOO "Dobropost Logistika"* (*ООО "Добропост Логистика"*, INN `9722023780`), Moscow (`119192, Lomonosovsky Prospekt, 43 bldg. 2, office 4/1/1`; the site also lists `Michurinsky Prospekt, 54 bldg. 2`).
- **Armenian entity:** *OOO ICOM DP SOLUTIONS* (*ООО ИКОМ ДП СОЛУШНС*), Vanadzor, Lori Province, Armenia.
- **Legal terms & tariffs:** [dobropost.com/rules](https://dobropost.com/rules), [dobropost.com/tariffs](https://dobropost.com/tariffs) and [dobropost.com/pvz](https://dobropost.com/pvz).

> Beware of fake Telegram messages or SMS links demanding extra customs fees outside your official DobroPost client cabinet or official customs broker portal. Always verify any payment inside your DobroPost account first. See [delivery scam texts](guide:delivery-scam-texts).

## Track a DobroPost parcel with Peek

[Peek](/) detects DobroPost automatically as the sole candidate when you enter a `DBRPST` number (`DBRPST6482917304` or `DBRPST73918462051`, invented) and names it at once, with "Detected carrier"; Peek also recognises pasted `tracking.dobropost.com/?trackNumber=` links. If you ever need to choose DobroPost manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "ДоброПост" or "Dobropost" finds it too).

Peek reads DobroPost's tracker directly and shows the stage ("Label created", "Received by carrier", "In transit", "At customs", "Ready for pickup", "Needs attention", "Returned to sender", "Delivered"), the Russian wording of DobroPost and of the partners it relays (CDEK, Boxberry and Russian Post) with phone numbers removed, and the delivery time once delivered. The town CDEK names in brackets at the end of a scan is that scan's place. The status codes stay out of every Peek app. DobroPost gives every scan with Moscow's UTC offset, China warehouse scans included, and Peek shows them on your device's clock. DobroPost's tracker gives no estimated delivery date, weight, signer's name or last-mile tracking number.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes while DobroPost's tracker answers. Otherwise Peek checks hourly. DobroPost's tracker shows no out-for-delivery step, so Peek never switches to its 2-minute delivery-day checks. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When DobroPost's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking DobroPost." After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking DobroPost."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot pay shipping invoices, submit customs passport data or contact DobroPost or its domestic partners for you.

## Questions about DobroPost tracking

### What does a DobroPost tracking number look like?

DobroPost international tracking numbers start with `DBRPST` followed by 10 or 11 digits (for example `DBRPST6482917304` or `DBRPST73918462051`, invented).

### How long does DobroPost delivery from China take?

Published delivery estimates on [dobropost.com/tariffs](https://dobropost.com/tariffs) range from **6–9 days** to Vladivostok and **9–13 days** (Express) or **19–25 days** (Economy) to Moscow, and **11–15 days** (Express) or **22–29 days** (Economy) to Minsk, counted from dispatch out of DobroPost's China warehouse.

### Which domestic carriers deliver DobroPost parcels in Russia and Belarus?

DobroPost delivers to its own PVZ pickup points in Russia and Minsk and, by its tariffs, through [CDEK](carrier:cdek), DPD and [Russian Post](carrier:russian-post) (CDEK alone for Minsk). Its tracker relays the scans of CDEK, Boxberry and Russian Post.

### Why is my DobroPost parcel stuck on `AWAITING_PAYMENT` or customs?

`AWAITING_PAYMENT` means your parcel has been packed and weighed at DobroPost's China warehouse and is waiting for shipping payment in your client account. If tracking pauses at customs, DobroPost's [Knowledge Base](https://dobropost.com/baza_main) explains customs clearance, and its support can tell you where the parcel stands.

:::sources
- [DobroPost: Official Website](https://dobropost.com/) – China-to-Russia/Belarus marketplace parcel forwarding, the Jixi warehouse, support and the CEO line, Moscow and Armenia legal entities (*OOO "Dobropost Logistika"*, INN 9722023780)
- [DobroPost: Parcel Tracker](https://tracking.dobropost.com/) – public `DBRPST` tracking page
- [DobroPost: Tariffs & Transit Times](https://dobropost.com/tariffs) – Express, Economy, Far East and Heavy Parcel transit times, weight rules and partner carriers
- [DobroPost: Pickup Points (PVZ)](https://dobropost.com/pvz) – DobroPost PVZ locations across Russia and Minsk, collection fees and storage
- [DobroPost: Knowledge Base](https://dobropost.com/baza_main) – marketplace guides, support requests, tariff changes, customs clearance and missing items
- [DobroPost: General Terms of Carriage](https://dobropost.com/rules) – carriage and customs brokerage terms
- [Universal Parcel Scraper: dobropost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – `DBRPST` format, `tracking.dobropost.com` link rule, DobroPost/CDEK/Boxberry/Russian Post status mapping, the bracketed town as the scan's place and Moscow-offset timestamps
:::
