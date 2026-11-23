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
| `P48291736051` | `P` + 11 digits (12 characters), the shape of Avito Delivery numbers. Peek asks [Colis Privé](carrier:colis-prive) and 5Post, 5Post first in Russia. |
| `99994829173605` | 14 digits, an online shop's order number. Peek never names 5Post for it and asks DPD's companies, Chronopost, SEUR, BRT and others: choose 5Post yourself or paste the 5Post link. |

Shops choose their own order numbers, so two orders can share one. 5Post's website then asks for digits of the recipient's phone number, and Peek can't tell the orders apart. A `fivepost.ru/tracking?id=...` link files the parcel under 5Post straight away. For other shapes, see the [tracking number formats guide](guide:tracking-number-formats).

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
| `CUSTOMS_CLEARANCE_STARTED` / `CUSTOMS_DATA_REQUIRED` / `CUSTOMS_AWAITING_PAYMENT` | An order from abroad (such as AliExpress) in customs clearance, waiting for your data for the customs representative, or waiting for the duties to be paid. |
| `CUSTOMS_CLEARANCE_COMPLETED` — `Таможенное оформление завершено` | Customs released the parcel to continue to 5Post. |
| `UNCLAIMED_...` / `RETURNED_TO_PARTNER` / `RETURNED_TO_CLIENT` | Uncollected past the storage deadline or cancelled after handover, heading back to the store or sender (`UNCLAIMED_READY_FOR_REPICKUP` means prepared for re-delivery to a pickup point). |
| `INCEPTION_PROBLEM` / `LOST` / `CANCELLED` / `REGISTRATION_ERROR_...` | Order problem, cancellation, loss, or missing phone number / delivery date / address needing correction. |

Notice one important quirk in 5Post's codes: `DELIVERED` (`Доставлен в пункт выдачи`) means **ready for pickup** at the store or locker, whereas `DONE` (`Получен`) means you actually picked it up.

## Picking up from Pyaterochka, Perekrestok, and lockers

An order for pickup waits in a 5Post parcel locker (*постамат*) or at the checkout (*касса*) of the Pyaterochka or Perekrestok store chosen with the order; 5Post's FAQ gives the opening hours of lockers and pickup points:

- **Pickup code:** When the order reaches the point (`Доставлен в пункт выдачи`), 5Post sends the pickup code by SMS. Enter it on the locker's screen or give it at the checkout. 5Post's FAQ says where to find the code and what to do if the staff can't find your order.
- **Storage extensions & phone edits:** As listed on [5Post FAQ](https://fivepost.ru/faq/), recipients can check storage periods, extend storage, pay online via the website or mobile app, or find steps for changing a phone number, opening a stuck locker door, or reporting an empty or wrong compartment.
- **Cross-border orders (AliExpress):** International orders routed via 5Post include air linehaul (`DEPARTED_BY_AIR`, `DELIVERED_BY_AIR`) and Russian customs clearance; if tracking shows `Требуется предоставить данные таможенному представителю` (`CUSTOMS_DATA_REQUIRED`) or `Требуется оплата таможенных платежей` (`CUSTOMS_AWAITING_PAYMENT`), the customs representative needs your data or the duties are unpaid; 5Post's FAQ says which orders go through customs, why the data is needed, how to contact the customs representative and when to pay. See the [customs guide](guide:customs) and [when tracking stops updating](guide:tracking-not-updating).

For parcels carried inside Russia by the national post or another courier, compare with [Russian Post](carrier:russian-post) and [CDEK](carrier:cdek).

## Tracking 5Post with Peek

Paste your `P` number, shop order number or `fivepost.ru/tracking?id=...` link into Peek. For a `P` number Peek asks Colis Privé and 5Post, 5Post first in Russia, and says "5Post has this parcel." once 5Post answers. For a 14-digit order number it never names 5Post: tap "Change" while adding the parcel, or "Change carrier" on its page, and pick 5Post. A 5Post link files the parcel under 5Post straight away.

Peek reads 5Post's guest tracking through the open-source [universal-parcel-scraper 5Post adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post), and tries again after a network error. It shows each scan's Russian wording, the sorting centre or hub a warehouse scan names (`РЦ ...`, `ХАБ ...`), the pickup point's name and address once the order waits at a pickup point or locker, and the time you collected it. 5Post dates every scan with its UTC offset, and Peek shows it on your device's clock. The storage deadline, payment details, the point's opening hours, the sender's name and the number of parcels stay on 5Post's site and app.

Peek maps 5Post's codes accurately so `DELIVERED` never triggers a false doorstep-delivered notice: **Label created** (`INFORMATION_RECEIVED`), **Received by carrier** (`PROCESS_WITHDRAWN`), **In transit** (warehouse processing, departure, air legs, and post-customs transfer), **At customs** (broker data requests, clearance, and duty payment), **Out for delivery** (`RECEIVED_BY_COURIER`), **Delivery attempted** (`RETURNING_BY_COURIER`), **Ready for pickup** (`DELIVERED` — `Доставлен в пункт выдачи`), **Delivered** (`DONE` — `Получен`), **Returned to sender** (`RETURNED_TO_...` and the `UNCLAIMED_...` codes, except `UNCLAIMED_READY_FOR_REPICKUP`, which stays In transit), and **Needs attention** (`LOST`, `CANCELLED`, `CUSTOMS_CLEARANCE_REJECTED`, and registration errors).

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking 5Post.", with a "Get help from 5Post" link to 5Post's FAQ. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking 5Post."

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day 5Post shows `Передан курьеру`, every 2 minutes from 6 a.m. on the newest scan's clock, while 5Post's own tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When 5Post's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day. It can notify you of "Every scan", "Important steps" or "Delivery only".

## Contacting 5Post

5Post lists official phone lines and guest/account feedback forms on [fivepost.ru/contacts](https://fivepost.ru/contacts/) and [fivepost.ru/faq](https://fivepost.ru/faq/):

- **Moscow and Moscow Region phone:** `8-495-935-88-00` (charged at your carrier's rate)
- **Russian regions:** `8-800-100-05-05`
- **Online FAQ & support ticket form:** [5Post FAQ](https://fivepost.ru/faq/) and [5Post Contacts](https://fivepost.ru/contacts/) (supports recipient inquiries with or without login and file attachments up to 40 MB)
- **Legal address:** 109029, Moscow, ul. Srednyaya Kalitnikovskaya, 28 bldg. 4 (INN `7722461620`, OGRN `1187746567568`)

## Frequently asked questions

### Why does 5Post's internal status say `DELIVERED` before I picked up my parcel?

In 5Post's tracking system, `DELIVERED` (`Доставлен в пункт выдачи`) means the parcel has been delivered to the Pyaterochka/Perekrestok pickup desk or locker and is waiting for you. Peek maps it to **Ready for pickup** and waits for `DONE` (`Получен`) before marking **Delivered**.

### Where do I pick up a 5Post order?

You collect it at the 5Post parcel locker (*постамат*) or supermarket checkout counter inside the Pyaterochka («Пятёрочка») or Perekrestok («Перекрёсток») store you selected at checkout, using the code from 5Post's SMS.

### Why did 5Post ask for customs data on my AliExpress order?

An order from abroad is cleared through customs by a customs representative, who may need your data (`CUSTOMS_DATA_REQUIRED`). 5Post's FAQ says which orders go through customs clearance, why the data is needed and how to contact the customs representative. Once customs has released the order, 5Post shows `Таможенное оформление завершено` (`CUSTOMS_CLEARANCE_COMPLETED`).

### How do I contact 5Post if a locker door does not open or the parcel is missing?

Call `8-495-935-88-00` (Moscow & region) or `8-800-100-05-05` (regions), or submit a recipient ticket (with photos up to 40 MB) via [fivepost.ru/contacts](https://fivepost.ru/contacts/) or your 5Post account.

:::sources
- [5Post (fivepost.ru)](https://fivepost.ru/)
- [5Post FAQ](https://fivepost.ru/faq/)
- [5Post Contacts](https://fivepost.ru/contacts/)
- [universal-parcel-scraper 5Post adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/5post)
:::
