---
title: Russian Post tracking (Почта России): numbers and steps
description: Track a Russian Post (Почта России) parcel: its RU and 14-digit numbers, what its steps in Russian mean, and how Peek follows it.
slug: russian-post-tracking
published: 2026-11-04
updated: 2026-11-04
---

Russian Post (Почта России, Pochta Rossii) is the postal service of Russia. It carries parcels sent from Russia and parcels sent to Russia. The sender or the shop gives you the tracking number: paste it in the box above. Russian Post's own website is pochta.ru, and Peek reads its tracker for you, as explained below.

## Russian Post tracking number: what it looks like

With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `RR000000005RU` | Two letters, nine digits and `RU`: the international shape, ending in the code for Russia. The last of the nine digits is a check digit. Peek shows it as "Unknown postal carrier" and, when it starts with C, E, L or R, asks Russian Post's tracker about it (see below). |
| `80000000000006` | 14 digits starting with `80`, the last one a check digit. Peek links it to Russian Post only as a weak match: DPD, SEUR, BRT, Delhivery and other carriers have numbers of this shape too. |
| `80000000000001` | The same shape with a wrong check digit. Peek doesn't suggest Russian Post for it, only the other carriers: if yours is a Russian Post number, check for a typo. |
| `RR000000005CN` | A number another post issued, here [China Post](carrier:china-post). Peek follows it with that post, also when it travels to Russia. |

Spaces and lower case don't matter when you paste a number into Peek. If you can't find yours, see [how to find your tracking number](guide:find-tracking-number) and [tracking number formats](guide:tracking-number-formats).

## Russian Post tracking statuses explained

Russian Post writes its steps in Russian. Peek reads them, as the table below has them, on the tracker of Qwintry, a parcel forwarder that hands some parcels to Russian Post: for a parcel added with Qwintry's own number, or added with its RU number and filed under Qwintry (see "Parcels from Qwintry" below). There, each Russian Post step has an operation, such as `обработка` (processing), then a line of detail and the post office at the end. Where there is no line of detail, the operation is the step. Roughly in the order a parcel gets them:

| Russian Post says | What it means for you | Peek shows |
| --- | --- | --- |
| `Партионный` | Russian Post has taken the parcel over, in a batch with others (operation `приём`, reception). | "In transit" |
| `экспорт международной почты` | Export of international mail: the parcel is sent out of the country it is in. | "In transit" |
| `Прибыло на территорию РФ` | The parcel has arrived in Russia. | "In transit" |
| `импорт международной почты` | Import of international mail: the parcel is being taken into Russia. | "In transit" |
| `приём на таможню` | Handed to customs. | "At customs" |
| `Выпущено таможней` | Released by customs: on its way again. | "In transit" |
| `Покинуло место международного обмена` | It has left the office that handles mail from abroad. | "In transit" |
| `Сортировка` | Sorting. | "In transit" |
| `Прибыло в сортировочный центр` | It has arrived at a sorting centre. | "In transit" |
| `Покинуло сортировочный центр` | It has left a sorting centre. | "In transit" |
| `Прибыло в место вручения` | It has arrived at the post office that will deliver it or hand it over. | "In transit" |
| `Передано почтальону` | Given to the postman for delivery. | "Out for delivery" |
| `Временное отсутствие адресата` | Not delivered: you weren't in. | "Delivery attempted" |
| `Адресат заберет отправление сам` | Not delivered, because you will collect the parcel yourself. | "Delivery attempted" |
| `Вручение адресату` | Handed to you, the recipient. | "Delivered" |

For any other Russian Post parcel, Peek reads Russian Post's own tracker, which gives it the steps in English: see "Track a Russian Post parcel with Peek" below.

## How Russian Post delivers

The steps above show the route in Russia: sorting centres, then the post office that delivers the parcel. From there, a postman may bring it (`Передано почтальону`). If you weren't in, or if you are to collect it, the step says so. On Qwintry's tracker, Peek shows the post office as the step's place, so you know which one has your parcel.

Peek found no delivery times, holding periods or rules for delivery attempts that it could check on Russian Post's own website, so this page gives none. For a parcel waiting at a post office, see [uncollected parcels](guide:uncollected-parcel) and [missed deliveries](guide:missed-delivery).

**Sent from Russia to another country.** Peek doesn't know which company delivers a Russian Post parcel in your country. For an RU number, it asks the post of the country Russian Post's tracker names as the destination, and may follow the parcel there: see below.

**Sent to Russia from another country.** Peek follows the parcel under the carrier that issued the number, with that carrier's tracking or with tracking services. If that tracking names Russian Post as the company that delivers it, Peek asks Russian Post's tracker too, and moves the parcel there once it shows a scan at least as recent as that tracking's latest. A parcel bound for Russia isn't moved for that alone.

## Russian Post parcel not arriving or tracking stuck

**No steps yet.** Russian Post's tracker or the tracking services may not have the number yet, or the sender hasn't handed the parcel over. Peek keeps asking. If nothing comes after a few days, check the number with the sender. More in [why tracking stops updating](guide:tracking-not-updating).

**Stuck at customs.** If Peek shows "At customs", the parcel is with customs. This page gives no customs rules or fees: Peek couldn't check them on an official page. See [parcels held at customs](guide:customs).

**Peek is behind.** When Russian Post's tracker doesn't answer Peek, a tracking service fills in, and it can lag behind Russian Post's own tracker. Compare with pochta.ru before you worry.

**Delivered, but nothing came.** If Peek shows "Delivered" and you have nothing, ask at your post office and tell the sender. More in [delivered but not received](guide:delivered-not-received).

**Lost or sent back.** Ask the sender or the shop first: they booked the delivery. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## Russian Post customer service and contact

As of 31 October 2026, Peek couldn't read Russian Post's website, so this page lists no phone number or email: Peek gives only contacts read on a carrier's own pages. Russian Post's help pages are on info.pochta.ru. For a parcel that has stopped moving, Peek links Russian Post's help page on tracking as "Get help from Russian Post".

For an order from a shop, ask the shop first: it booked the delivery.

## Track a Russian Post parcel with Peek

[Peek](/) can't be sure of a Russian Post number by its shape alone. Paste it all the same: Peek follows more than 3,500 carriers. While you add it, Peek shows "Checking tracking services…".

- **14 digits starting with 80, with a valid check digit.** Peek asks Russian Post's tracker, with DPD and other carriers whose numbers fit, whether it has the parcel. If only Russian Post has it, Peek says "Russian Post has this parcel." and files it there. If not, press "Change": Russian Post comes first under "Fits this number", with DPD and other carriers whose numbers fit. Pick it there. On a parcel you've already added, use "Change carrier".
- **An RU number.** Peek shows it as "Unknown postal carrier". When the number starts with C, E, L or R, Peek also asks Russian Post's tracker whether it has the parcel: if it does, Peek says "Russian Post has this parcel." and files it there. Otherwise press "Change", or "Change carrier" later, and pick Russian Post: typing Почта России finds it. Peek then names Russian Post. Either way, Peek can follow the parcel to the post in your country (below).

Peek reads Russian Post's own tracker, which answers it in English. It shows each step in Russian Post's English words, such as `Sorting`, `Arrived at the local distribution center` or `Forwarded to the postman` ("Out for delivery"), with its time, its town and country, and the country the parcel is going to. `Available for pickup at the Post Office` shows as "Ready for pickup" and `Handed over for temporary storage` as "Needs attention". After `Returned to the sender`, also "Needs attention", the parcel is on its way back, and its delivery then shows as "Returned to sender". Russian Post gives no expected delivery date. Until its tracker knows the number, Peek shows "Waiting for the carrier", or "Waiting for Russian Post’s first scan" for an 80 number added without a carrier. When Russian Post's tracker doesn't answer Peek, Peek asks tracking services instead, ParcelsApp, Ship24 and 17TRACK in turn, and for an RU number the Universal Postal Union's tracking last, and shows the history of the first one that has the parcel, in that service's words, which can lag behind pochta.ru. A service that didn't know the number is asked again a day later.

For an 80 number added without a carrier, or an RU number shown as "Unknown postal carrier", Peek also asks, in the parcel's first 30 days and before the tracking services, the carriers that may have it: Russian Post first (for an RU number, if it starts with C, E, L or R), and DPD and others for an 80 number. If one of them shows the parcel moving, Peek moves the parcel there. If you added an 80 number without a carrier and a tracking service names Russian Post as its only carrier, with dated steps, Peek files the parcel under Russian Post by itself. For an RU number shown as "Unknown postal carrier", a tracking service naming Russian Post isn't enough: Peek needs Russian Post's tracker to show the parcel, or you pick Russian Post yourself.

Peek has no link to Russian Post's tracker. If a tracking service has filled in, the link on the parcel's page opens that service, such as "Open the ParcelsApp website". For pochta.ru, open it yourself.

**Parcels from Qwintry.** If Qwintry sent your parcel to Russia, add its Qwintry number instead: QR and eight digits. Peek names Qwintry at once and reads Qwintry's own tracker, which goes on to show Russian Post's steps once the post has the parcel. When Qwintry's tracker names Russian Post, the parcel shows "Delivery with Russian Post" and the post's RU number, and Peek asks Russian Post's tracker too: once that shows a scan at least as recent as Qwintry's latest, Peek follows the parcel there, keeping Qwintry's steps in the history. Qwintry's tracker also takes the RU number of a parcel it handed to the post: if that's all you have and Russian Post's tracker doesn't have it, press "Change" and pick Qwintry for it.

**The post in your country.** For an RU number filed under Russian Post, Russian Post's tracker names the country the parcel is going to, and Peek asks that country's post too, with the RU number, if it reads that post's own tracking, as it does for [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [An Post](carrier:an-post), [Canada Post](carrier:canada-post) and [Australia Post](carrier:australia-post). For a parcel shown as "Unknown postal carrier", a tracking service that names the post that delivers it, with its number, or for an RU number the country it is going to, is enough: Peek asks that post the same way. Once the post shows a scan at least as recent as Russian Post's or the service's latest, Peek follows the parcel there.

While a service has the parcel, Peek checks it every 15 minutes, out for delivery too. It can notify you of "Every scan", "Important steps" or "Delivery only". Peek can't contact Russian Post or the sender for you.

## Questions about Russian Post tracking

### What is Почта России in English?

Russian Post. It is also written Pochta Rossii. Its website is pochta.ru.

### What does a Russian Post tracking number look like?

An international one is two letters, nine digits and `RU`, such as `RR000000005RU`. Peek also links 14 digits starting with `80` to Russian Post, when the check digit is right.

### Why don't the steps in Peek match pochta.ru?

Peek reads Russian Post's tracker in English, so it shows Russian Post's English words for each step, not the Russian ones. When that tracker doesn't answer Peek, a tracking service fills in, in its own words, and it can be behind. Peek shows Russian Post's steps in Russian, as the table above has them, for a parcel it follows on Qwintry's tracker.

### Can Peek track a parcel sent to Russia from another country?

Yes. Paste the number the sender gave you. Peek follows it under the carrier that issued it, and moves it over to Russian Post only if that tracking names Russian Post as the company that delivers it.

:::sources
- [Universal Parcel Scraper: Russian Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/russian-post) – names, the 80 and RU number shapes with their check digits and weak matches, the tracker Peek reads and its English steps, places, destination and stages, help page
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – Russian Post's steps as Qwintry's tracker shows them and their stages, the office as the place, the QR number and the RU numbers it takes, the pochta.ru link
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – RU numbers as an unknown postal carrier, carriers asked first, order of tracking services, hand-overs to the post in your country and to Russian Post
:::
