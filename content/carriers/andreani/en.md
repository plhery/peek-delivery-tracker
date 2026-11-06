---
title: Andreani tracking: Argentina parcel numbers and statuses
description: Track an Andreani parcel in Argentina: what its 15-digit numbers look like, what its Spanish tracking lines mean in English, and what to do when it stops moving.
slug: andreani-tracking
published: 2026-11-03
updated: 2026-11-03
---

Andreani is a parcel carrier in Argentina. Peek's catalog lists it in Argentina and Brazil, also as Correo Andreani and Grupo Logístico Andreani. If you bought something online, the shop may send it with Andreani: the tracking number is in the shop's shipping email or on your order page. Paste the number, or an andreani.com tracking link, in the box above. Andreani's own tracker is on andreani.com. Peek reads that tracker, and this page explains each step in English.

## Andreani tracking number: what it looks like

The Andreani numbers Peek knows have 15 digits and start with 36000, 31000 or 30000. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `360000000000000` | 36000 and 10 more digits: Andreani's range, but only a weak match. [BRT](carrier:brt) and Yunda also use 15 digits, so Peek asks both Andreani and BRT. |
| `310000000000000` | 31000 and 10 more digits: Andreani's range too. |
| `300000000000000` | 30000 and 10 more digits: Andreani's range too. |
| `000000000000000` | 15 digits with another start. Peek doesn't link it to Andreani. [FedEx](carrier:fedex), [DPD France](carrier:dpd-fr), [Hermes](carrier:hermes-de), BRT and Yunda use 15 digits too. |

Spaces don't matter when you paste a number into Peek.

**Other references.** Andreani's tracker also takes other references: Andreani numbers with letters, and senders' own references. Peek can't tell from their shape that they are Andreani's, so pick Andreani yourself (see "Track an Andreani parcel with Peek" below).

**A tracking link.** An andreani.com link with `/envio/` and the number in it works too: Peek reads the number from the link.

Can't find your number? See [how to find a tracking number](guide:find-tracking-number).

## Andreani tracking statuses explained

Andreani's tracker groups its lines under headings, in Spanish. The four Peek knows are `Pendiente de ingreso` (waiting to be received), `Ingresado` (received), `En camino` (on its way) and `Entregado` (delivered). Each line is a short sentence written to you, with the branch (`sucursal`) and town where there is one. The lines Peek knows, in the order a parcel gets them:

| Andreani says | What it means for you | Peek's status |
| --- | --- | --- |
| `Tu envío se encuentra pendiente de ingreso.` | The sender has announced the parcel to Andreani, but Andreani doesn't have it yet. | "Label created" |
| `¡Ya tenemos tu envío! El circuito comienza en C.a.b.a., Buenos Aires. Pronto lo enviaremos a la sucursal encargada de la entrega.` | Andreani has your parcel. The journey starts in the town and province named. Next, it goes to the branch in charge of delivering it. | "Received by carrier" |
| `Tu envío salió de la sucursal Monserrat (av Belgrano), en la localidad C.a.b.a. de la provincia Buenos Aires.` | It left the branch named, in that town and province. | "In transit" |
| `Tu envío se encuentra en camino a la sucursal La Plata (calle 2).` | It is on its way to the branch named. This is the branch that will deliver it. | "In transit" |
| `La sucursal La Plata (calle 2) ya está procesando tu envío. ¡Pronto tendrás novedades!` | The delivering branch has the parcel and is working on it. More news soon. | "In transit" |
| `Hoy te visitaremos para entregarte tu envío.` | Andreani will come to deliver it today. | "Out for delivery" |
| `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!` | Delivered. | "Delivered" |

The branches and towns in this table are only examples: your lines name the branches on your parcel's route. `C.a.b.a.` is the City of Buenos Aires (Ciudad Autónoma de Buenos Aires).

Peek shows each line in Andreani's Spanish words, under one of its own English stages. A line Peek doesn't know yet takes the stage of the heading it sits under: any line under `En camino` shows as "In transit". Peek removes phone and identity-card numbers from a line. More in [what tracking statuses mean](guide:tracking-statuses).

## How Andreani delivers in Argentina

The lines above tell the usual journey:

1. **Received.** Andreani takes the parcel in where the journey starts.
2. **To the delivering branch.** It sends the parcel to the branch in charge of delivery (`sucursal encargada de la entrega`). The line that starts `Tu envío se encuentra en camino a la sucursal` names that branch.
3. **At the delivering branch.** The line saying that branch `ya está procesando tu envío` (is working on your parcel) means it has arrived there.
4. **Delivery day.** `Hoy te visitaremos para entregarte tu envío.` means someone brings the parcel to the address today.
5. **Delivered.** `Ya entregamos tu envío.` ends the journey.

Peek couldn't read Andreani's website, so this page gives no delivery times, no rules for missed deliveries and nothing on collecting a parcel at a branch or how long a branch keeps it. Ask the shop or Andreani about these. See also [missed deliveries](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

## Andreani tracking not updating, lost or returned

**Only `Pendiente de ingreso`.** The sender has created the shipment but hasn't handed the parcel to Andreani yet. Peek shows "Label created". Ask the shop if it has sent your order.

**No result at all.** Andreani's tracker may not know the number yet. Filed under Andreani, the parcel shows "Waiting for the carrier", and Peek keeps asking. If nothing comes after a few days, check the number with the shop. See [why tracking stops updating](guide:tracking-not-updating).

**Quiet for days.** When the newest line is four days old or more and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it: "No tracking update for four days". Parcels often go quiet over a weekend. If nothing changes, ask the shop.

**Delivered, but nothing came.** The last line is `Ya entregamos tu envío. ¡Esperamos volver a verte pronto!`, but you have no parcel. Ask the people at the address, your neighbours and your building's staff, then the shop. See [delivered but not received](guide:delivered-not-received).

**Lost or damaged.** Ask the shop first: it booked the delivery with Andreani. See [lost parcels](guide:lost-parcel) and [damaged parcels](guide:damaged-parcel).

**Sent back.** Peek's table of Andreani lines has no return line yet. If the parcel goes back to the sender, the line still shows in Andreani's words. Ask the shop about a refund or a new delivery. See [returned to sender](guide:returned-to-sender).

**The shop doesn't help.** If a company you bought from doesn't do what it promised, you can complain to Defensa del Consumidor, Argentina's consumer protection office. It's free and online, through the Ventanilla Federal Única de Defensa del Consumidor. You need your DNI or passport, a phone number or email address for the answer, the company's name and town, and the basic details of the purchase. Defensa del Consumidor sends your complaint to the office that has to deal with it, and you get a complaint number by email.

## Andreani customer service and contact

As of 1 November 2026, Peek couldn't read Andreani's website, so this page lists no phone number, email or opening hours: Peek gives only contacts read on official pages. Andreani's website is andreani.com.

For an online order, ask the shop first: it booked the delivery and has the shipment's details.

## Track an Andreani parcel with Peek

A 15-digit number doesn't tell [Peek](/) by its shape alone that it's Andreani's. Paste it all the same: Peek follows more than 3,500 carriers.

**A number starting with 36000, 31000 or 30000.** Peek asks Andreani and BRT at once, and shows "Checking tracking services…" meanwhile. If Andreani has the parcel, the line under the number names Andreani and says "has this parcel", and Peek files the parcel there. If not, it says "not found yet · Peek keeps checking". Add it all the same: the parcel shows "Probably Andreani" and "Waiting for Andreani’s first scan", and Peek keeps asking Andreani every 6 hours. After a week with nothing, it asks you to check the number with the sender or choose another carrier.

**An andreani.com link.** Paste the tracking link with `/envio/` in it. Peek names Andreani at once ("Detected carrier") and tracks the number from the link, a reference with letters too.

**Any other number.** Pick Andreani yourself: tap "Change", then find Andreani under "All carriers" or with the search. For a number starting with 36000, 31000 or 30000, it is also under "Fits this number". On a parcel you've already added, use "Change carrier". Peek's Andreani lookup takes 6 to 30 letters and digits, with at least one digit. For anything else, the line under the number says Andreani "doesn’t issue numbers like this one".

**What Peek shows.** Peek reads Andreani's own tracker, the same lookup Andreani's tracking page makes. For each line it shows Andreani's Spanish words, its stage (table above), the branch, and the time. Andreani's times are on Argentina's clock, which has one time zone and no daylight saving time. Peek shows them in your device's time zone. Andreani's tracker also shows the sender's name, the driver's name, remarks, a map position, a progress bar and a delivery estimate. Peek leaves all of these out, so it shows no expected delivery date.

**When Andreani doesn't answer.** Peek asks tracking services instead, ParcelsApp, Ship24 and 17TRACK in turn, and shows their copy in their own words. Peek knows no hand-over from Andreani to another carrier, so it follows the parcel with Andreani to the end.

**Checks and alerts.** Peek checks every 10 minutes, or every 2 minutes from 6 a.m. local time on the day Andreani scans the parcel out for delivery, while Andreani's own tracker answers (through tracking services, every 15 minutes). A parcel with no news for two days, or for 12 hours at "Label created" or "Received by carrier", is checked hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". Peek can't contact Andreani or the shop for you.

## Questions about Andreani tracking

### What does an Andreani tracking number look like?

15 digits starting with 36000, 31000 or 30000, such as `360000000000000`. Andreani's tracker also takes other references. Paste the number or the andreani.com tracking link into Peek.

### What does "Pendiente de ingreso" mean on Andreani tracking?

Waiting to be received: the sender has announced the parcel, but Andreani doesn't have it yet. Peek shows "Label created". If it stays like this for days, ask the shop if it has sent the parcel.

### What does "Hoy te visitaremos para entregarte tu envío." mean?

Andreani will come today to deliver your parcel. Peek shows "Out for delivery" and checks more often until the delivery line comes.

### Why is my Andreani tracking not updating?

The shop may not have handed the parcel over yet, or Andreani has no new step to show, often over a weekend. Peek keeps asking Andreani and flags a parcel with no news for four days. Then ask the shop.

### How do I track an Andreani parcel in English?

Paste the number into Peek. Andreani's lines stay in Spanish, but Peek puts each one under an English stage, and the table above explains them.

:::sources
- [Universal Parcel Scraper: Andreani](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/andreani) – names and countries, the 15-digit shape, other references, andreani.com links, the tracker's lookup, Spanish lines and headings and their stages, phone and identity numbers removed, Argentina's clock, what the tracker shows and Peek leaves out, no delivery date
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – other carriers' 15-digit shapes, which carriers Peek asks, tracking services, no hand-off for Andreani
- [Argentina.gob.ar: Iniciar un reclamo ante Defensa del Consumidor](https://www.argentina.gob.ar/servicio/iniciar-un-reclamo-ante-defensa-del-consumidor) – complaints about a company that didn't keep its promise, Ventanilla Federal Única, free and online, what you need, complaint number by email
:::
