---
title: DobroPost tracking: DBRPST codes, statuses & China hubs
description: Track a DobroPost parcel from China to Russia or Belarus: DBRPST tracking formats, warehouse and customs scans, PVZ pickup, partner handoffs and support.
slug: dobropost-tracking
published: 2026-11-23
updated: 2026-11-23
---

DobroPost (*ДоброПост*, operated by *OOO "Dobropost Logistika"* in Moscow together with *OOO ICOM DP SOLUTIONS* in Armenia) is a China-to-Russia and China-to-Belarus parcel consolidator and cross-border express carrier widely used by shoppers buying from Chinese marketplaces such as Taobao, Poizon, 1688, Pinduoduo, Tmall, 95, Goofish and JD. Buyers ship purchases to DobroPost's warehouse in China, where goods are packed, weighed, cleared through customs and forwarded either to DobroPost's own pickup points (PVZ) or to last-mile partner carriers such as [CDEK](carrier:cdek), DPD, [Russian Post](carrier:russian-post) and Boxberry. You can check a shipment on DobroPost's tracker at [tracking.dobropost.com](https://tracking.dobropost.com/) or paste your `DBRPST` code into the box above.

## DobroPost tracking numbers (`DBRPST` format)

Once you create a consolidated parcel in your DobroPost account (even before your marketplace orders arrive at the China warehouse), DobroPost assigns an international parcel number starting with `DBRPST` followed by 10 or 11 digits:

| Format (invented) | What it is |
| --- | --- |
| `DBRPST6482917304` | 16-character DobroPost parcel number (`DBRPST` + 10 digits). |
| `DBRPST73918462051` | 17-character DobroPost parcel number (`DBRPST` + 11 digits). |

Domestic Chinese courier tracking numbers used to move items from a seller to DobroPost's China warehouse are tracked inside your DobroPost dashboard separately; only the `DBRPST` parcel code works on [tracking.dobropost.com](https://tracking.dobropost.com/) and in Peek. See [tracking number formats](guide:tracking-number-formats).

## What DobroPost tracking statuses mean

DobroPost publishes checkpoints in Russian together with internal status codes — first from its own China warehouse and customs clearance stages, then relayed directly from the domestic last-mile carrier ([CDEK](carrier:cdek), Boxberry or [Russian Post](carrier:russian-post)):

| DobroPost code or wording | What it means for you | Peek shows |
| --- | --- | --- |
| `VIRTUAL_PARCEL` | Parcel created in your DobroPost cabinet before goods reach the China warehouse. | "Label created" |
| `ON_ASSEMBLY`, `ON_WEIGHING`, `AWAITING_PAYMENT`, `READY_FOR_SENT`, `ADDED_TO_THE_REGISTRY` | Packed and weighed at DobroPost's China warehouse, awaiting shipping payment or added to an outbound dispatch registry. | "Received by carrier" |
| `LEFT_THE_DP_WERHOUSE_IN_CHINA`, `FLEW_OUT_FROM_CHINA` | Left DobroPost's China warehouse and departed China on international linehaul. | "In transit" |
| `AT_CUSTOMS_IN_CHINA`, `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, `START_OF_CUSTOMS_CLEARANCE`, `Выпуск с таможни` | Going through export clearance in China or import customs clearance in Russia or Belarus. | "At customs" |
| `RELEASE_OF_GOODS_WITHOUT_PAYMENT_OF_CUSTOMS_DUTIES`, `LEFT_CUSTOMS_AND_HEADING_TO_LAST_MILE_CARRIER'S_WAREHOUSE`, `READY_FOR_SHIPMENT_FOR_LAST_MILE_DELIVERY` | Released from customs and moving into the destination last-mile network. | "In transit" |
| `REFUSAL_TO_RELEASE_GOODS_WITH_INDICATION_OF_THE_CODE_OF_THE_REASON_FOR_REFUSAL` | Customs refused release (for example commercial quantity or restricted item); check your DobroPost account urgently. | "Needs attention" |
| `RECEIVED_AT_SHIPMENT_WAREHOUSE`, `SEND_TO_TRANSIT_OFFICE`, `SEND_TO_RECIPIENT_OFFICE`, `ACCEPTED_FOR_DELIVERY`, `SENT_TO_DESTINATION_CITY`, `Прием`, `Обработка` | Relayed domestic transit scans from CDEK, Boxberry or Russian Post moving toward your city. | "In transit" |
| `ACCEPTED_AT_WAREHOUSE_ON_DEMAND` (CDEK), `RECEIVED_AT_THE_POINT_OF_ISSUE` (Boxberry) | Arrived at your chosen pickup office or parcel shop and ready for collection. | "Ready for pickup" |
| `GETTING_READY_TO_RETURN`, `SENT_TO_COLLECTION_POINT`, `RETURNED_TO_COLLECTION_POINT`, `RETURNED`, `Возврат` | Uncollected or refused by customs and heading back to DobroPost (which can arrange reshipment). | "Returned to sender" |
| `PARCEL_DELIVERED_TO_RECIPIENT`, `DELIVERED`, `Вручение` | Collected from the pickup point or delivered to the recipient. | "Delivered" |

## Delivery times, tariffs and DobroPost PVZ pickup points

According to [DobroPost Tariffs](https://dobropost.com/tariffs), transit times are counted from dispatch out of DobroPost's China warehouse to arrival at a pickup point (PVZ) or handover to a domestic courier:

- **Express tariffs** (volumetric weight, no sensitive goods): typically **9–13 days** to Moscow, **10–14 days** to Saint Petersburg, **9–12 days** to Novosibirsk, Krasnodar, Yekaterinburg, Kazan, Irkutsk and Ulan-Ude, and **11–15 days** to Minsk (Belarus).
- **Economy tariffs** (actual weight, suited to heavier or bulky parcels): typically **19–25 days** to Moscow, **20–27 days** to Saint Petersburg, **15–23 days** to Irkutsk and **22–29 days** to Minsk.
- **Far East & Heavy Parcel tariffs:** **6–9 days** to Vladivostok, **8–12 days** to Blagoveshchensk, and **6–9 days** for single-item heavy parcels from 5 to 30 kg.
- **DobroPost PVZ pickup points:** listed on [dobropost.com/pvz](https://dobropost.com/pvz) in Moscow, Odintsovo, Saint Petersburg, Kazan, Krasnodar, Novosibirsk, Yekaterinburg, Irkutsk, Ulan-Ude, Vladivostok, Ussuriysk and Minsk. Published PVZ issue fees are `100 RUB` (up to 5 kg), `200 RUB` (up to 10 kg) and `300 RUB` (over 10 kg); cities without a DobroPost PVZ use partner pickup points and couriers.

## Customs, quiet gaps and parcel issues

- **Shipping payment required before dispatch:** if tracking stops at `AWAITING_PAYMENT`, the parcel stays in the China warehouse until you pay the calculated shipping invoice in your DobroPost client account.
- **Quiet gaps during border transit and customs:** scans commonly pause between `FLEW_OUT_FROM_CHINA` and `ARRIVED_AT_CUSTOMS_IN_THE_COUNTRY_OFF_DESTINATION`, or during customs review. Ensure your passport details and INN/tax ID in your DobroPost profile are accurate to prevent clearance holds. See [parcels held at customs](guide:customs) and [why tracking stops updating](guide:tracking-not-updating).
- **Missing items, damage or returns:** review the unpacking and claim rules in [DobroPost Terms of Use](https://dobropost.com/rules) and the instructions in [DobroPost Knowledge Base](https://dobropost.com/baza_main) (which covers parcel inspection, missing items and support tickets). If a parcel goes uncollected and shows `RETURNED`, contact DobroPost through your client cabinet to arrange reshipment. See [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel), [delivered but not received](guide:delivered-not-received) and [returned to sender](guide:returned-to-sender).

## DobroPost customer service and official links

DobroPost handles shipment inquiries, tariff changes and claims through the support ticket system inside the client cabinet linked from [dobropost.com](https://dobropost.com/) and documented in the [DobroPost Knowledge Base](https://dobropost.com/baza_main).

- **Registered Russian operator:** *OOO "Dobropost Logistika"* (*ООО "Добропост Логистика"*, INN `9722023780`), Moscow (`119192, Lomonosovsky Prospekt, 43 bldg. 2, office 4/1/1`; operational address `Michurinsky Prospekt, 54 bldg. 2`).
- **Armenian entity:** *OOO ICOM DP SOLUTIONS* (*ООО ИКОМ ДП СОЛУШНС*), Vanadzor, Lori Province, Armenia.
- **Legal terms & tariffs:** [dobropost.com/rules](https://dobropost.com/rules), [dobropost.com/tariffs](https://dobropost.com/tariffs) and [dobropost.com/pvz](https://dobropost.com/pvz).

> Beware of fake Telegram messages or SMS links demanding extra customs fees outside your official DobroPost client cabinet or official customs broker portal. Always verify any payment inside your DobroPost account first. See [delivery scam texts](guide:delivery-scam-texts).

## Track a DobroPost parcel with Peek

[Peek](/) detects DobroPost automatically as the sole candidate when you enter a `DBRPST` number (`DBRPST6482917304` or `DBRPST73918462051`, invented) and confirms `"DobroPost has this parcel."` once DobroPost's tracker recognises it; Peek also recognises pasted `tracking.dobropost.com/?trackNumber=` links. If you ever need to choose DobroPost manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "ДоброПост" or "Dobropost" finds it too).

Peek queries DobroPost's tracking API directly and displays the mapped stage ("Label created", "Received by carrier", "In transit", "At customs", "Ready for pickup", "Needs attention", "Returned to sender", "Delivered"), Russian scan wording from both DobroPost and relayed last-mile partners (CDEK, Boxberry and Russian Post, scrubbing phone numbers), destination city parsed from relayed scans (for example the town named in brackets on CDEK events), destination country, delivery timestamp when delivered, and full timestamped instants (DobroPost timestamps all scans with Moscow's UTC offset, including China warehouse events). DobroPost does not return an estimated delivery date, weight, signatory name or separate last-mile tracking code through its public tracker.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. local time on the day an out-for-delivery scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12") and shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking DobroPost." After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking DobroPost."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot pay shipping invoices, submit customs passport data or contact DobroPost or its domestic partners for you.

## Questions about DobroPost tracking

### What does a DobroPost tracking number look like?

DobroPost international tracking numbers start with `DBRPST` followed by 10 or 11 digits (for example `DBRPST6482917304` or `DBRPST73918462051`, invented).

### How long does DobroPost delivery from China take?

Published delivery estimates on [dobropost.com/tariffs](https://dobropost.com/tariffs) range from **6–9 days** to Vladivostok and **9–13 days** (Express) or **19–25 days** (Economy) to Moscow, and **11–15 days** (Express) or **22–29 days** (Economy) to Minsk, counted from dispatch out of DobroPost's China warehouse.

### Which domestic carriers deliver DobroPost parcels in Russia and Belarus?

DobroPost delivers through its own PVZ pickup points in major Russian cities and Minsk, as well as domestic partner carriers including [CDEK](carrier:cdek), DPD, [Russian Post](carrier:russian-post) and Boxberry. Their checkpoints are relayed directly inside DobroPost's tracking history.

### Why is my DobroPost parcel stuck on `AWAITING_PAYMENT` or customs?

`AWAITING_PAYMENT` means your parcel has been packed and weighed at DobroPost's China warehouse and is waiting for shipping payment in your client account. If tracking pauses at customs, check your DobroPost cabinet for any passport, tax ID or customs-clearance notice.

:::sources
- [DobroPost: Official Website](https://dobropost.com/) – China-to-Russia/Belarus marketplace parcel forwarding, Moscow and Armenia legal entities (*OOO "Dobropost Logistika"*, INN 9722023780)
- [DobroPost: Parcel Tracker](https://tracking.dobropost.com/) – public `DBRPST` tracking page
- [DobroPost: Tariffs & Transit Times](https://dobropost.com/tariffs) – Express, Economy, Far East and Heavy Parcel transit times, weight rules and partner carriers
- [DobroPost: Pickup Points (PVZ)](https://dobropost.com/pvz) – DobroPost PVZ locations across Russia and Minsk and collection fees
- [DobroPost: Knowledge Base](https://dobropost.com/baza_main) – marketplace guides, customs clearance, support requests and parcel inspection
- [DobroPost: General Terms of Carriage](https://dobropost.com/rules) – carriage and customs brokerage terms
- [Universal Parcel Scraper: dobropost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – `DBRPST` format, `tracking.dobropost.com` link rule, DobroPost/CDEK/Boxberry/Russian Post status mapping, bracketed city extraction and Moscow-offset timestamps
:::
