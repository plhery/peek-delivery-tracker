---
title: A Tu Hora Express tracking: codes, postcode & help
description: Track an A Tu Hora Express parcel in Spain: 10-, 16- and 24-digit codes, why a 5-digit postcode is needed, Spanish statuses, incidents and contacts.
slug: a-tu-hora-express-tracking
published: 2026-11-23
updated: 2026-11-23
---

A Tu Hora Express (*A TU HORA EXPRESS, S.L.*) is a Spanish last-mile delivery courier headquartered in San Fernando de Henares (Madrid), operating branches across mainland Spain including Madrid, Barcelona, Valencia, Seville, Málaga, Alicante, Murcia, Almería, Cádiz, Castellón, Córdoba, Girona, Granada, Huelva, Jaén, Lleida and Tarragona. It delivers online-retail and B2B orders under services including *Express 24h*, *Economy 48h*, *Masivo 72h*, *Reverse Logistics* (*Logística Inversa*) and *B2B Premium*. You can look up a parcel on [A Tu Hora Express's tracking portal](https://mydelivery.atuhoraexpress.com/#/login-form-cli), or paste the tracking number into the box above.

## A Tu Hora Express tracking numbers and delivery postcode

Look for your service or tracking number in the retailer's dispatch email or order page. A Tu Hora Express shipments appear in three all-digit lengths:

| Format (invented) | What it is |
| --- | --- |
| `0000004829173640` | 16-digit A Tu Hora Express service number (`000000` + 10 digits). Requires your 5-digit Spanish delivery postcode on the tracker. |
| `4829173640` | 10-digit short code (padded with six leading zeros to form the 16-digit service number). Also requires your 5-digit delivery postcode. |
| `000000482917364000128013` | 24-digit full barcode: the 16-digit service number + `001` + the 5-digit Spanish delivery postcode (here `28013`). Shares the 24-digit length with [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost), [Austrian Post](carrier:austrian-post) and [Express One](carrier:express-one). |

**Why the postcode matters.** On [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli), 10- and 16-digit numbers only return parcel details when paired with the **5-digit Spanish delivery postcode**. The 24-digit barcode already embeds that postcode in its last five digits, so it works without entering the postcode separately. For other Spanish and international barcode patterns, see [tracking number formats](guide:tracking-number-formats).

## What A Tu Hora Express tracking statuses mean

A Tu Hora Express returns scan descriptions in Spanish alongside numeric status codes (`cod`) and local Spain wall-clock times (`Europe/Madrid`):

| A Tu Hora Express says (code) | What it means for you | Peek shows |
| --- | --- | --- |
| `Documentado` (`0`) | The sender registered the shipment electronically before depot handover. | "Label created" |
| `En tránsito` (`1`) | Moving through A Tu Hora Express's Spanish network between branches. | "In transit" |
| `En agencia distribución` (`11`) | Arrived at the local Spanish delivery depot serving your postcode. | "In transit" |
| `En reparto` (`2`) / `En realización` | Out with the local delivery driver for delivery today. | "Out for delivery" |
| `Incidencia - Pendiente nuevo reparto` (`3`), `Ausente`, `Dirección incorrecta o incompleta`, `Rechazado` | Delivery could not be completed (recipient absent, incomplete address, refused or queued for another attempt). | "Delivery attempted" |
| `Incidencia - Falta parcial` (`3`), `Incidencia - No cumplimiento agencia` / `Incumplimiento de la agencia` | Operational incident such as a missing multi-piece parcel or an agency non-delivery issue. | "Needs attention" |
| `Pendiente de realizar` (`5`) | Paired alongside an incident at the same timestamp to mark that redelivery is pending; the incident row carries the stage. | *(keeps incident stage)* |
| `Realizado con éxito` (`4`) / `Entregado con éxito` | Delivered by A Tu Hora Express. | "Delivered" |
| `Devuelto` | Returned back toward the sender. | "Returned to sender" |

## Delivery services and what the public site publishes

On [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/) and [atuhoraexpress.com](https://atuhoraexpress.com/), A Tu Hora Express lists five core services — *Express 24h*, *Economy 48h*, *Masivo 72h*, *Reverse Logistics* and *B2B Premium* — across its Spanish branch network, with tracking hosted via [atuhoraexpress.com/tracking/](https://atuhoraexpress.com/tracking/) and [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli).

A Tu Hora Express's public website does not publish fixed retail rules on the number of doorstep delivery attempts, parcel weight/dimension caps or depot storage limits, as those terms are agreed under each online store's commercial contract. If your order arrived from outside the European Union and passed through customs before Spanish last-mile handover, remember that EU-bound low-value imports carry the €3 customs duty on consignments up to €150 and the €2 EU handling fee per item unless settled at checkout; see [parcels held at customs](guide:customs).

## A Tu Hora Express parcel delayed, stuck or missing

Because online stores hold the transport contract with A Tu Hora Express, contact the retailer first if a parcel is repeatedly stuck in `Incidencia`, shows an address problem (`Dirección incorrecta o incompleta`) or misses its expected delivery day.

- **Repeated incidents or `Pendiente nuevo reparto`:** confirm your street address, floor/door details and contact phone number with the retailer and email `info@atuhoraexpress.com` with your 16- or 24-digit code and delivery postcode.
- **Marked `Realizado con éxito` but not received:** check with household members, neighbours and your building concierge (*portería*) first, then contact the retailer and A Tu Hora Express right away so the depot can inspect the delivery proof.
- For step-by-step next actions, see [why tracking stops updating](guide:tracking-not-updating), [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## A Tu Hora Express customer service and contacts

Official contact details published on [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/), [atuhoraexpress.com/aviso-legal/](https://atuhoraexpress.com/aviso-legal/) and [atuhoraexpress.com/ayudanos-a-mejorar/](https://atuhoraexpress.com/ayudanos-a-mejorar/):

- **Phone:** `911 631 356` (Spain).
- **Customer email:** `info@atuhoraexpress.com`.
- **Feedback form:** [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/).
- **Data protection email:** `protecciondedatos@atuhoraexpress.com`.
- **Madrid headquarters:** Calle Artesanos 10, 28830 San Fernando de Henares (Madrid), Spain (registered legal address of *A TU HORA EXPRESS, S.L.*, NIF `B19300748`: C/ Espliego 16, 19208 Alovera, Guadalajara).

> Watch out for SMS or WhatsApp messages asking for a small card payment to reschedule an A Tu Hora Express delivery. Verify your parcel directly on [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) or with `info@atuhoraexpress.com`. See [delivery scam texts](guide:delivery-scam-texts).

## Track an A Tu Hora Express parcel with Peek

[Peek](/) recognises any 16-digit code starting with six zeros (`0000004829173640`, digits invented) as A Tu Hora Express automatically with high confidence. For a 24-digit barcode (`000000482917364000128013`), Peek checks A Tu Hora Express automatically alongside [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost), [Austrian Post](carrier:austrian-post) and [Express One](carrier:express-one) using the embedded 5-digit postcode and shows "A Tu Hora Express has this parcel." when it answers. For a 10-digit code (`4829173640`), tap "Change" while adding or "Change carrier" on the parcel screen and select A Tu Hora Express (searching "A Tu Hora" finds it too).

For 10- and 16-digit codes, Peek offers an optional "Delivery postcode" field (`5 digits`). Although optional so you can save the parcel right away, A Tu Hora Express's direct tracker only answers those shorter codes when the 5-digit Spanish delivery postcode is supplied — without it, Peek asks other tracking services instead and slows to daily checks after a day with no answers. Entering or updating the postcode triggers an immediate direct lookup. On 24-digit codes, Peek reads the postcode from the final five digits automatically.

When A Tu Hora Express answers, Peek shows the mapped stage ("Label created", "In transit", "Out for delivery", "Delivery attempted", "Needs attention", "Returned to sender", "Delivered"), Spanish scan descriptions, status codes, town name where provided, expected delivery date when A Tu Hora Express exposes it on an open parcel, parcel weight when present and local Madrid wall-clock timestamps. Peek discards GPS coordinates beside scans, the progress rail, departure date, customer reference, sender and recipient names, destination address, parcel count, volume, remarks, proof-of-delivery records and delivery options; A Tu Hora Express's portal does not publish pickup-point locations or service names.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. Madrid time on the day an out-for-delivery scan (`En reparto` / `En realización`) arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback (including when no postcode is provided for a 10- or 16-digit code), they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12") and shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking A Tu Hora Express." After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking A Tu Hora Express."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot reschedule deliveries, change your address or contact A Tu Hora Express on your behalf.

## Questions about A Tu Hora Express tracking

### Why does A Tu Hora Express ask for my delivery postcode?

A Tu Hora Express protects 10- and 16-digit tracking codes by requiring the 5-digit Spanish delivery postcode on [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) and in Peek. If your store gave you a 24-digit code, the last five digits already contain the postcode.

### What does `Realizado con éxito` mean on A Tu Hora Express tracking?

`Realizado con éxito` (status code `4`) means A Tu Hora Express recorded the shipment as delivered. If you do not have it, check with household members, neighbours and your building concierge, then contact the retailer and `info@atuhoraexpress.com`.

### What does `Incidencia - Pendiente nuevo reparto` mean?

It means a delivery attempt could not be completed and the parcel is queued to go out again (often paired with `Pendiente de realizar` at the same timestamp). Confirm your address and phone details with the retailer if it repeats.

### How do I contact A Tu Hora Express in Spain?

Call `911 631 356`, email `info@atuhoraexpress.com` or submit the [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/) form with your tracking number and 5-digit delivery postcode.

:::sources
- [A Tu Hora Express: Home (Spanish)](https://atuhoraexpress.com/) – Spanish last-mile courier, branches, Madrid headquarters, `911 631 356` and `info@atuhoraexpress.com`
- [A Tu Hora Express: Home (English)](https://atuhoraexpress.com/home/) – Express 24h, Economy 48h, Masivo 72h, Reverse Logistics, B2B Premium and branch list
- [A Tu Hora Express: Tracking Page](https://atuhoraexpress.com/tracking/) – website tracking entry point
- [A Tu Hora Express: MyDelivery Portal](https://mydelivery.atuhoraexpress.com/#/login-form-cli) – direct shipment lookup portal
- [A Tu Hora Express: Legal Notice](https://atuhoraexpress.com/aviso-legal/) – *A TU HORA EXPRESS, S.L.*, NIF `B19300748`, registered office in Alovera (Guadalajara) and `protecciondedatos@atuhoraexpress.com`
- [A Tu Hora Express: Feedback Form](https://atuhoraexpress.com/ayudanos-a-mejorar/) – customer feedback form
- [Universal Parcel Scraper: a-tu-hora-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/a-tu-hora-express) – 10/16/24-digit detection, optional 5-digit postcode requirement, Spanish status codes (`0`, `1`, `11`, `2`, `3`, `4`, `5`), `Europe/Madrid` local clocks, expected delivery date, weight and stripped fields
:::
