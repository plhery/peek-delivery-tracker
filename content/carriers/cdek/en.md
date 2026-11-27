---
title: CDEK tracking (СДЭК): order numbers and statuses in English
description: Track a CDEK (СДЭК / my.cdek.ru) parcel in English: 11-digit order numbers, Russian status translations, courier vs pickup point steps and carrier matching.
slug: cdek-tracking
published: 2026-11-02
updated: 2026-11-27
---

CDEK (СДЭК, also spelled SDEK) is a courier and logistics network listed in Peek's catalog across Russia, Kazakhstan, Belarus and Serbia, delivering by courier to your door, to pickup points and to parcel lockers. Paste your CDEK order number (or `my.cdek.ru` link) in the box above to see your parcel's status in English alongside translations of CDEK's Russian scan lines.

## CDEK tracking number: what it looks like

CDEK calls each shipment an order (заказ), so its tracking number is an order number. It is not the shop's order number. CDEK's tracker takes numbers of 5 to 40 letters and digits: its own order numbers, and other numbers it has linked to an order, such as the postal number of a parcel from abroad. Peek links one shape to CDEK. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `10482917365` | 11 digits starting with `10`: the only shape Peek links to CDEK, and only as a weak match. [GLS Germany](carrier:gls-de), [GLS Switzerland](carrier:gls-ch), [GLS France](carrier:gls-fr), [GLS Austria](carrier:gls-at), GLS Poland, PostLogistics, Coordinadora and Mainfreight have 11-digit numbers too. |
| `48291736548` | 11 digits not starting with `10`. Peek doesn't suggest CDEK for it: check the number, or pick CDEK yourself (below). |
| `RR482917367CN` | A postal number from abroad, here from [China Post](carrier:china-post). Peek follows it with that post. If CDEK has linked it to an order, pick CDEK to see CDEK's steps. |

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

**No new step for days.** If the newest step is four or more days old and the parcel hasn't yet gone out for delivery or reached your pickup point, Peek flags it with that step's day: "No tracking update since Mon 12 Oct". More in [why tracking stops updating](guide:tracking-not-updating).

**`Курьер не смог доставить заказ`.** The courier couldn't deliver. Another attempt shows as `Выдан курьеру` again. If none comes, ask the shop: it booked the delivery with CDEK.

**`Не вручён`.** The order ended without reaching you. Ask the shop what happens next: a refund, or sending it again. See [returned to sender](guide:returned-to-sender).

**Delivered, but nothing came.** Check whether someone collected it for you, then ask the shop. More in [delivered but not received](guide:delivered-not-received).

**Lost.** The shop booked the delivery with CDEK, so ask the shop first. See [lost parcels](guide:lost-parcel).

## CDEK customer service and contact

Peek can't read CDEK's main website, cdek.ru, so this page lists no phone number or email: Peek gives only contacts read on a carrier's own pages. CDEK's tracker and order pages are on my.cdek.ru.

For an order from a shop, ask the shop first: it booked the delivery.

## Track a CDEK parcel with Peek

[Peek](/) can't be sure of a CDEK number by its shape alone. Paste it all the same: Peek follows more than 4,500 carriers.

- **11 digits starting with 10.** Peek asks carriers whose numbers have this shape whether they have the parcel, five at a time, and shows "Checking tracking services…" meanwhile. If you look it up from Russia, Kazakhstan, Belarus or Serbia (your phone's region, or on the web where you connect from), CDEK is asked first. From elsewhere, it is among the first five asked or the five after them. If CDEK knows the number, Peek names CDEK and says it "has this parcel". If several carriers know it, Peek asks you to choose. If none knows it yet, you see "not found yet · Peek keeps checking". You can also press "Change" and pick CDEK under "Fits this number".
- **Another shape.** Press "Change" and search for CDEK: typing СДЭК or SDEK finds it too.
- **A link.** Paste a my.cdek.ru order link, and Peek tracks the order in it with CDEK.

On a parcel you've already added, use "Change carrier". A parcel added without a carrier, before anyone has it, shows "No tracking history yet". If you filed it under CDEK, it shows "Waiting for CDEK" until CDEK knows the number. If CDEK still doesn't know it two days later, Peek asks you to check it, choose another carrier or delete the parcel.

**What Peek shows.** Peek reads CDEK's own tracking with the number alone: no account and no phone confirmation. You see the status, each step in CDEK's words and the city it happened in, at its exact time. CDEK's anonymous reply hides the pickup code meant for signed-in recipients, and Peek leaves out the sender's address, the pickup point's details, the storage deadline and payments, and gets no weight or expected delivery date from CDEK. CDEK's reply gives the recipient's initials and address: Peek shows the initials under "Recipient" on your own parcel page, never on a shared link, and uses the address's town for the distance to go on your map. It links CDEK's order page as "Open the CDEK website".

**Another number for the same order.** If you track a number CDEK has linked to an order, such as a postal number from abroad, CDEK answers with its own order number. Peek lists that one last, as "CDEK tracks this as".

**When CDEK doesn't answer.** CDEK's sites can block automated lookups. Then Peek asks tracking services: ParcelsApp, Ship24 and 17TRACK. ParcelsApp's copy of CDEK's scans names the legs of the trip rather than CDEK's steps, and may give only each scan's day. When CDEK answers again, Peek goes back to it.

**A number another carrier has.** If you filed an 11-digit number under CDEK and CDEK doesn't know it, Peek asks other carriers whose numbers have this shape, in the parcel's first 30 days. If one of them shows the parcel moving, Peek moves it there and shows "Swapped automatically from CDEK".

**Parcels handed to CDEK.** Qwintry, a parcel forwarder, hands some parcels to CDEK. When Qwintry's tracker names CDEK with CDEK's number, the parcel shows "Delivery with CDEK" (or "Announced to CDEK, not received yet" while CDEK has only the announcement of the parcel, with no scan of its own) and that number. Peek then asks CDEK too, and follows the parcel there once CDEK shows a step at least as recent as Qwintry's newest. DobroPost, which brings orders from China, shows CDEK's steps in its own tracker when CDEK delivers the last part: Peek follows such a parcel there, by its DBRPST number. For a postal number from abroad filed under CDEK, Peek may also ask the post of the country you look it up from, the same way: Belpost from Belarus, Royal Mail from the UK or USPS from the US, for example, but none from Russia or Kazakhstan.

**Checks and alerts.** While you have the parcel open, or on its delivery day with notifications on, Peek checks CDEK up to every 10 minutes, or up to every 2 minutes from 6 a.m. Moscow time on the day CDEK scans the parcel out for delivery, while CDEK's own tracker answers. Otherwise, and after two days without a new step (12 hours while it shows "Label created" or "Received by carrier"), it checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". For a pickup point, choose "Important steps": "Delivery only" doesn't tell you when it's ready to collect. Peek can't contact CDEK or the shop for you.

## Questions about CDEK tracking

### What is СДЭК (SDEK) in English and where is its official tracker?

CDEK (also written SDEK or CDEK Express). Its official order tracking site is `my.cdek.ru`.

### What does a CDEK order tracking number look like?

Peek links 11 digits starting with `10` to CDEK (such as `10482917365`, invented), and CDEK's tracker also accepts linked external or international postal numbers of 5 to 40 letters and digits.

### What does "Поступил. Заберите заказ" or "Выдан курьеру" mean on CDEK?

`"Поступил. Заберите заказ"` ("Ready for pickup" in Peek) means your parcel has arrived at your pickup point or parcel locker — check the address, hours and collection deadline on `my.cdek.ru`. `"Выдан курьеру"` ("Out for delivery") means the courier has taken the parcel out on a delivery run.

### Can I track a CDEK parcel in English without logging in?

Yes. Paste your order number in Peek above: Peek queries CDEK without an account or phone confirmation, shows the stage in English ("In transit", "Out for delivery", "Ready for pickup") and displays CDEK's Russian scan lines, translated in the table above.

### Why does Peek ask me to choose a carrier for an 11-digit CDEK number?

GLS, PostLogistics and other carriers also use 11 digits. If more than one of them knows your number, Peek can't tell which parcel is yours: pick CDEK from the list.

:::sources
- [Universal Parcel Scraper: CDEK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cdek) – names, countries, the 11-digit shape and its weak match, the numbers CDEK's tracker takes, my.cdek.ru links, codes, Russian wordings and their stages, what the order page shows and what Peek keeps, exact times, the order's own number, the bot check
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – Qwintry's tracker naming CDEK and its number
- [Universal Parcel Scraper: DobroPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – CDEK's steps for the last part of the trip on DobroPost's tracker
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – the carriers sharing the 11-digit shape and the order they are asked in, the order of tracking services, ParcelsApp's copies of CDEK's scans, hand-offs, Belpost for postal numbers in Belarus
:::
