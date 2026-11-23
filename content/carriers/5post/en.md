---
title: 5Post tracking — numbers, Pyaterochka pickup & statuses
description: Track 5Post (FivePost / X5) parcels in Russia. Check Avito P-numbers and shop order codes, Russian status meanings, Pyaterochka and Perekrestok pickup, and help.
slug: 5post-tracking
published: 2026-11-23
updated: 2026-11-23
---

5Post (*5Пост* / *FivePost*, operated by X5 Group) is a Russian e-commerce parcel, peer-to-peer shipping, and fulfillment network delivering online orders and Avito Delivery parcels to more than **25,700** parcel lockers (*постаматы*) and checkout pickup points inside **Pyaterochka** («Пятёрочка») and **Perekrestok** («Перекрёсток») grocery stores across **4,950** cities and towns in Russia, backed by **51** sorting centres ([fivepost.ru](https://fivepost.ru/)). 5Post works with over 1,000 online retailers and marketplaces — including Avito Delivery and international AliExpress orders — and also operates door-to-door courier delivery and LTL/FTL freight.

You can check a parcel on [fivepost.ru](https://fivepost.ru/) (under *Где мой заказ?*) or paste your order number or `fivepost.ru/tracking?id=...` link into the box above.

## 5Post tracking number formats

5Post tracks shipments by the shop or marketplace order number — any single token of 3 to 40 letters and digits containing at least one digit:

| Format (invented) | What it is |
| --- | --- |
| `P48291736051` | `P` + 11 digits (12 characters total) — Avito Delivery / 5Post format (shares length with [Colis Privé](carrier:colis-prive), so Peek confirms it against 5Post). |
| `99994829173605` | 14-digit online-store order code (starts with `9999` on many partner stores; shares 14-digit length with European parcel networks, so pick 5Post manually or paste the link). |

Because shops choose their own order numbers, two unrelated stores can occasionally share a short numeric code. Pasting a direct `fivepost.ru/tracking?id=...` link routes your parcel straight to 5Post immediately. For broader comparisons across Russian and international formats, see the [tracking number formats guide](guide:tracking-number-formats).

## What 5Post tracking statuses mean

5Post's tracking service pairs machine status codes with Russian descriptions, naming the regional sorting warehouse (`РЦ ...`) on hub processing and dispatch scans.

| 5Post code & Russian wording | What it means |
| --- | --- |
| `INFORMATION_RECEIVED` — `Ожидается передача заказа в 5post` | Order data has been registered; 5Post is waiting for the seller or sender to hand over the parcel. |
| `PROCESS_WITHDRAWN` — `Передан службе доставки` | The sender dropped off or handed the parcel over to 5Post. |
| `WAREHOUSE_PROCESSING` — `Обрабатывается на складе ...` | Processed at a 5Post regional sorting warehouse (`РЦ`). |
| `READY_FOR_SHIPMENT` — `Покинул склад ...` | Left the sorting warehouse toward the next hub or pickup store. |
| `DELIVERED` — `Доставлен в пункт выдачи` | Arrived at your chosen Pyaterochka / Perekrestok checkout desk or 5Post parcel locker — **ready for pickup**, not yet collected! |
| `DONE` — `Получен` | Collected by the recipient (delivered). |
| `RECEIVED_BY_COURIER` — `Передан курьеру` | Out for doorstep delivery on courier-delivered orders. |
| `RETURNING_BY_COURIER` — `Неуспешная попытка вручения` | A courier delivery attempt failed. |
| `CUSTOMS_CLEARANCE_STARTED` / `CUSTOMS_DATA_REQUIRED` / `CUSTOMS_AWAITING_PAYMENT` | Cross-border orders (such as AliExpress) undergoing customs clearance, requesting recipient passport/tax data for the customs broker, or awaiting duty payment. |
| `CUSTOMS_CLEARANCE_COMPLETED` — `Таможенное оформление завершено` | Customs released the parcel to continue to 5Post. |
| `UNCLAIMED_...` / `RETURNED_TO_PARTNER` / `RETURNED_TO_CLIENT` | Uncollected past the storage deadline or cancelled after handover, heading back to the store or sender (`UNCLAIMED_READY_FOR_REPICKUP` means prepared for re-delivery to a pickup point). |
| `INCEPTION_PROBLEM` / `LOST` / `CANCELLED` / `REGISTRATION_ERROR_...` | Order problem, cancellation, loss, or missing phone number / delivery date / address needing correction. |

Notice one important quirk in 5Post's codes: `DELIVERED` (`Доставлен в пункт выдачи`) means **ready for pickup** at the store or locker, whereas `DONE` (`Получен`) means you actually picked it up.

## Picking up from Pyaterochka, Perekrestok, and lockers

Most 5Post recipients collect orders either from a self-service 5Post locker (*постамат*) or directly at the supermarket checkout desk (*касса*) inside Pyaterochka or Perekrestok stores during store opening hours:

- **Pickup code:** Once `Доставлен в пункт выдачи` posts, 5Post sends your pickup code via SMS, email, or the 5Post account/app. Enter that code at the locker screen or give it to the store cashier.
- **Storage extensions & phone edits:** As listed on [5Post FAQ](https://fivepost.ru/faq/), recipients can check storage periods, extend storage, pay online via the website or mobile app, or find steps for changing a phone number, opening a stuck locker door, or reporting an empty or wrong compartment.
- **Cross-border orders (AliExpress):** International orders routed via 5Post include air linehaul (`DEPARTED_BY_AIR`, `DELIVERED_BY_AIR`) and Russian customs clearance; if tracking shows `Требуется предоставить данные таможенному представителю` (`CUSTOMS_DATA_REQUIRED`) or `Требуется оплата таможенных платежей` (`CUSTOMS_AWAITING_PAYMENT`), submit the requested customs details promptly to avoid return. See the [customs guide](guide:customs) and [when tracking stops updating](guide:tracking-not-updating).

For parcels carried inside Russia by the national post or another courier, compare with [Russian Post](carrier:russian-post) and [CDEK](carrier:cdek).

## Tracking 5Post with Peek

Paste your `P` + 11 digits Avito code, shop order number, or `fivepost.ru/tracking?id=...` link into Peek. Because short numeric shop codes overlap with other carriers worldwide, pick **5Post** manually via **Change** when adding the parcel or **Change carrier** on the parcel screen whenever Peek does not select it automatically.

Peek queries 5Post's guest mobile tracking API directly through the open-source [universal-parcel-scraper 5Post adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post), with automatic retry on network timeouts. It records every scan code, Russian description, regional sorting-centre location (`РЦ ...`), pickup point details, exact timezone-offset timestamps, and the collection timestamp (`delivered_at`). Account-specific fields such as payment breakdown, storage deadline, store opening hours, and sender name stay on 5Post's own app and site.

Peek maps 5Post's codes accurately so `DELIVERED` never triggers a false doorstep-delivered notice: **Label created** (`INFORMATION_RECEIVED`), **Received by carrier** (`PROCESS_WITHDRAWN`), **In transit** (warehouse processing, departure, air legs, and post-customs transfer), **At customs** (broker data requests, clearance, and duty payment), **Out for delivery** (`RECEIVED_BY_COURIER`), **Delivery attempted** (`RETURNING_BY_COURIER`), **Ready for pickup** (`DELIVERED` — `Доставлен в пункт выдачи`), **Delivered** (`DONE` — `Получен`), **Returned to sender** (`UNCLAIMED_...` / `RETURNED_TO_...`), and **Needs attention** (`LOST`, `CANCELLED`, `CUSTOMS_CLEARANCE_REJECTED`, and registration errors).

When an order pauses during long-distance transit or customs clearance, Peek shows: "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking 5Post." After more than three weeks without a new scan, Peek displays: "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking 5Post." Tap **Get help from 5Post** on the tracking screen to jump straight to 5Post's official FAQ and support form.

In Peek's notification preferences, choose **Every scan**, **Important steps**, or **Delivery only** to control your alerts.

## Contacting 5Post

5Post lists official phone lines and guest/account feedback forms on [fivepost.ru/contacts](https://fivepost.ru/contacts/) and [fivepost.ru/faq](https://fivepost.ru/faq/):

- **Moscow and Moscow Region phone:** `8-495-935-88-00` (charged at your carrier's rate)
- **Russian regions (toll-free):** `8-800-100-05-05`
- **Online FAQ & support ticket form:** [5Post FAQ](https://fivepost.ru/faq/) and [5Post Contacts](https://fivepost.ru/contacts/) (supports recipient inquiries with or without login and file attachments up to 40 MB)
- **Legal address:** 109029, Moscow, ul. Srednyaya Kalitnikovskaya, 28 bldg. 4 (INN `7722461620`, OGRN `1187746567568`)

## Frequently asked questions

### Why does 5Post's internal status say `DELIVERED` before I picked up my parcel?

In 5Post's tracking system, `DELIVERED` (`Доставлен в пункт выдачи`) means the parcel has been delivered to the Pyaterochka/Perekrestok pickup desk or locker and is waiting for you. Peek maps it to **Ready for pickup** and waits for `DONE` (`Получен`) before marking **Delivered**.

### Where do I pick up a 5Post order?

You collect it at the 5Post parcel locker (*постамат*) or supermarket checkout counter inside the Pyaterochka («Пятёрочка») or Perekrestok («Перекрёсток») store you selected at checkout, using the pickup code sent when the parcel arrives.

### Why did 5Post ask for customs data on my AliExpress order?

Cross-border shipments arriving in Russia require customs clearance through a customs broker (`CUSTOMS_DATA_REQUIRED`). Submit your details through the link provided by the store or customs broker so clearance (`CUSTOMS_CLEARANCE_COMPLETED`) can finish.

### How do I contact 5Post if a locker door does not open or the parcel is missing?

Call `8-495-935-88-00` (Moscow & region) or `8-800-100-05-05` (regions), or submit a recipient ticket (with photos up to 40 MB) via [fivepost.ru/contacts](https://fivepost.ru/contacts/) or your 5Post account.

:::sources
- [5Post (fivepost.ru)](https://fivepost.ru/)
- [5Post FAQ](https://fivepost.ru/faq/)
- [5Post Contacts](https://fivepost.ru/contacts/)
- [universal-parcel-scraper 5Post adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post)
:::
