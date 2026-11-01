---
title: CDEK tracking (СДЭК): order numbers and statuses in English
description: Track a CDEK (СДЭК) parcel: what its order number looks like, what its Russian statuses mean in English, courier and pickup point steps, and how Peek follows it.
slug: cdek-tracking
published: 2026-11-06
updated: 2026-11-06
---

CDEK (СДЭК, also written SDEK) is a courier company. Peek's catalog lists it in Russia, Kazakhstan and Belarus. It brings parcels to your door by courier, or to a pickup point or parcel locker where you collect them. The shop or the sender gives you the number: paste it in the box above. CDEK's own tracker is on my.cdek.ru. CDEK writes its steps in Russian, and the table below gives them in English.

## CDEK tracking number: what it looks like

CDEK calls each shipment an order (заказ), so its tracking number is an order number. It is not the shop's order number. CDEK's tracker takes numbers of 5 to 40 letters and digits: its own order numbers, and other numbers it has linked to an order, such as the postal number of a parcel from abroad. Peek links one shape to CDEK. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `10000000000` | 11 digits starting with `10`: the only shape Peek links to CDEK, and only as a weak match. [GLS Germany](carrier:gls-de), [GLS Switzerland](carrier:gls-ch), [GLS France](carrier:gls-fr), [GLS Austria](carrier:gls-at), GLS Poland, PostLogistics, Coordinadora and Mainfreight have 11-digit numbers too. |
| `00000000000` | 11 digits not starting with `10`. Peek doesn't suggest CDEK for it: check the number, or pick CDEK yourself (below). |
| `RR000000005CN` | A postal number from abroad, here from [China Post](carrier:china-post). Peek follows it with that post. If CDEK has linked it to an order, pick CDEK to see CDEK's steps. |

Can't find yours? See [how to find your tracking number](guide:find-tracking-number) and [tracking number formats](guide:tracking-number-formats).

## CDEK tracking statuses in English

Each CDEK step has a code, which Peek goes by, and Russian words, which Peek shows as CDEK wrote them. CDEK groups its scans under a few big steps, such as `В пути`: Peek shows a big step only when it has no scans. Roughly in order:

| CDEK says | What it means for you | Peek shows |
| --- | --- | --- |
| `Создан` | Created: the order exists in CDEK's system. CDEK may not have the parcel yet. | "Label created" |
| `Назначен курьер` | A courier is on the way to collect the order from the sender. | "Label created" |
| `Курьер забрал заказ у отправителя` | The courier has collected the order from the sender. | "Received by carrier" |
| `Принят на доставку` | Accepted for delivery: CDEK has the parcel. | "Received by carrier" |
| `В пути` | On the way: the big step for the trip between acceptance and the last part. | "In transit" |
| `Принят СДЭК на территории РФ` | A parcel from abroad has entered CDEK's network in Russia. | "In transit" |
| `Отправлен в сортировочный центр` | Sent to a sorting centre. | "In transit" |
| `Прибыл в сортировочный центр` | Arrived at a sorting centre. | "In transit" |
| `Отправлено в г.` and a city | Sent to that city. | "In transit" |
| `Отправлен в пункт доставки` | Sent to the depot that couriers deliver from. | "In transit" |
| `Прибыл в пункт доставки` | Arrived at that depot. | "In transit" |
| `Отправлен в пункт выдачи` | Sent to your pickup point. | "In transit" |
| `Доставляет курьер` | A courier is delivering it: the step that holds the courier's attempts. | "Out for delivery" |
| `Выдан курьеру` | Handed to the courier who brings it. It comes again for each attempt. | "Out for delivery" |
| `Курьер не смог доставить заказ` | The courier couldn't deliver the order. | "Delivery attempted" |
| `Готов к выдаче`, `Поступил. Заберите заказ` | Ready: it has arrived at your pickup point or parcel locker. Go and collect it. | "Ready for pickup" |
| `Вручён` | Handed over to you: delivered. | "Delivered" |
| `Не вручён` | Not handed over: the order ended without reaching you, because nobody collected it or it was refused. | "Needs attention" |

CDEK can also end an order as cancelled, or as partly delivered when you took part of the order and refused the rest. Peek shows "Needs attention" for a cancelled order and "Delivered" for a partly delivered one.

## How CDEK delivers: courier or pickup point

The steps tell you which way your parcel comes.

**To your door.** A parcel at the courier depot (`Прибыл в пункт доставки`) goes out with a courier (`Выдан курьеру`). After a failed attempt, `Выдан курьеру` comes again for the next one.

**To a pickup point or parcel locker.** After `Отправлен в пункт выдачи`, wait for `Поступил. Заберите заказ`: then go and collect it.

CDEK's order page on my.cdek.ru shows the pickup point, its address, contact details and opening hours, and the last day to collect. Peek leaves all of this out, so look there. Peek couldn't read CDEK's main website, cdek.ru, so this page gives no delivery times, holding periods or rules for delivery attempts. See [uncollected parcels](guide:uncollected-parcel), [missed deliveries](guide:missed-delivery) and [parcel lockers](guide:parcel-lockers).

**From abroad.** `Принят СДЭК на территории РФ` means CDEK has taken over a parcel from abroad in Russia. This page gives no customs rules or fees: Peek couldn't check them on an official page. See [parcels held at customs](guide:customs).

## CDEK parcel not arriving or tracking stuck

**Only `Создан`, or no steps.** The sender may not have handed the parcel over yet, or the number has a typo. Peek keeps asking. Check the number against the shop's message, digit by digit, and if nothing changes after a few days, ask the shop when it sent the parcel.

**No new step for days.** If the newest step is four or more days old and the parcel hasn't yet gone out for delivery or reached your pickup point, Peek flags it: "No tracking update for four days". More in [why tracking stops updating](guide:tracking-not-updating).

**`Курьер не смог доставить заказ`.** The courier couldn't deliver. Another attempt shows as `Выдан курьеру` again. If none comes, ask the shop: it booked the delivery with CDEK.

**`Не вручён`.** The order ended without reaching you. Ask the shop what happens next: a refund, or sending it again. See [returned to sender](guide:returned-to-sender).

**Delivered, but nothing came.** Check whether someone collected it for you, then ask the shop. More in [delivered but not received](guide:delivered-not-received).

**Lost.** The shop booked the delivery with CDEK, so ask the shop first. See [lost parcels](guide:lost-parcel).

## CDEK customer service and contact

As of 1 November 2026, Peek couldn't read CDEK's main website, cdek.ru, so this page lists no phone number or email: Peek gives only contacts read on a carrier's own pages. CDEK's tracker and order pages are on my.cdek.ru.

For an order from a shop, ask the shop first: it booked the delivery.

## Track a CDEK parcel with Peek

[Peek](/) can't be sure of a CDEK number by its shape alone. Paste it all the same: Peek follows more than 3,500 carriers.

- **11 digits starting with 10.** Peek asks carriers whose numbers have this shape whether they have the parcel, five at a time, and shows "Checking tracking services…" meanwhile. If you look it up from Russia, Kazakhstan or Belarus (your iPhone's region, or on the web where you connect from), CDEK is asked first. From elsewhere, it comes in the second round, if none of the first five has the parcel. If CDEK knows the number, Peek names CDEK and says it "has this parcel". If several carriers know it, Peek asks you to choose. If none knows it yet, you see "not found yet · Peek keeps checking". You can also press "Change" and pick CDEK under "Fits this number".
- **Another shape.** Press "Change" and search for CDEK: typing СДЭК or SDEK finds it too.
- **A link.** Paste a my.cdek.ru order link, and Peek tracks the order in it with CDEK.

On a parcel you've already added, use "Change carrier". A parcel added without a carrier, before anyone has it, shows "No tracking history yet". If you filed it under CDEK, it shows "Waiting for the carrier" until CDEK lists the number.

**What Peek shows.** Peek reads CDEK's own tracking with the number alone: no account and no phone confirmation. You see the status, each step in CDEK's words and the city it happened in, at its exact time. Peek never reads your name, address, phone or pickup code, and gets no weight or expected delivery date from CDEK. It links CDEK's order page as "Open the CDEK website".

**Another number for the same order.** If you track a number CDEK has linked to an order, such as a postal number from abroad, CDEK answers with its own order number. Peek lists that one last, as "CDEK tracks this as".

**When CDEK doesn't answer.** CDEK's sites can block automated lookups. Then Peek asks tracking services: ParcelsApp, Ship24 and 17TRACK in turn. ParcelsApp's copy of CDEK's scans names the legs of the trip rather than CDEK's steps, and may give only each scan's day. When CDEK answers again, Peek goes back to it.

**A number another carrier has.** If you filed an 11-digit number under CDEK and CDEK doesn't know it, Peek asks other carriers whose numbers have this shape, in the parcel's first 30 days. If one of them shows the parcel moving, Peek moves it there and shows "Swapped automatically from CDEK".

**Parcels handed to CDEK.** Qwintry, a parcel forwarder, hands some parcels to CDEK. When Qwintry's tracker names CDEK with CDEK's number, the parcel shows "Delivery with CDEK" and that number. Peek then asks CDEK too, and follows the parcel there once CDEK shows a step at least as recent as Qwintry's newest. DobroPost, which brings orders from China, shows CDEK's steps in its own tracker when CDEK delivers the last part: Peek follows such a parcel there, by its DBRPST number. For a postal number from abroad filed under CDEK and looked up from Belarus, Peek may also ask Belpost, the same way.

**Checks and alerts.** Peek checks CDEK every 10 minutes, or every 2 minutes once the parcel is out for delivery. After two days without a new step (12 hours while it shows "Label created" or "Received by carrier"), it checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". For a pickup point, choose "Important steps": "Delivery only" doesn't tell you when it's ready to collect. Peek can't contact CDEK or the shop for you.

## Questions about CDEK tracking

### What is СДЭК in English?

CDEK. It is also written SDEK, and sometimes CDEK Express. Its tracker is on my.cdek.ru.

### What does a CDEK tracking number look like?

Peek links 11 digits starting with `10` to CDEK, such as `10000000000`, but other carriers have 11-digit numbers too.

### What does "Поступил. Заберите заказ" mean on CDEK tracking?

Your parcel has arrived at your pickup point or parcel locker, and you can collect it. Peek shows "Ready for pickup". Check the address and the last day to collect on CDEK's order page.

### Can I track a CDEK parcel in English?

Yes. Peek shows the parcel's status in English, such as "In transit", and its history in CDEK's Russian words, which the table above explains.

### Why does Peek ask me to choose a carrier for my CDEK number?

GLS, PostLogistics and other carriers also use 11 digits. If more than one of them knows your number, Peek can't tell which parcel is yours: pick CDEK from the list.

:::sources
- [Universal Parcel Scraper: CDEK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cdek) – names, countries, the 11-digit shape and its weak match, the numbers CDEK's tracker takes, my.cdek.ru links, codes, Russian wordings and their stages, what the order page shows and what Peek keeps, exact times, the order's own number, the bot check
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – Qwintry's tracker naming CDEK and its number
- [Universal Parcel Scraper: DobroPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – CDEK's steps for the last part of the trip on DobroPost's tracker
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – the carriers sharing the 11-digit shape and the order they are asked in, the order of tracking services, ParcelsApp's copies of CDEK's scans, hand-offs, Belpost for postal numbers in Belarus
:::
