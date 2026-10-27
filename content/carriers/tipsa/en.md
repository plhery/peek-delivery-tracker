---
title: TIPSA tracking: numbers, statuses and missed deliveries
description: Track a TIPSA parcel in Spain or Portugal: the 22-digit number, what TIPSA's Spanish statuses mean, missed deliveries, agencies and how to reach TIPSA.
slug: tipsa-tracking
published: 2026-10-11
updated: 2026-10-27
---

TIPSA is a Spanish express parcel carrier, part of the Geopost group, with agencies in Spain, Portugal and Andorra. Track a parcel with its 22-digit number on TIPSA's [Delivery locator](https://www.tip-sa.com/en/delivery-locator), or paste it in the box above. The number comes from the shop or person who sent the parcel, often in the shipping email or SMS.

## TIPSA tracking number: what it looks like

TIPSA's locator asks for the 22-digit shipment number and marks the destination postcode as required too. The number is made of two agency codes followed by the waybill (albarán):

:::anatomy 012345 012345 1234567890
- 012345 | First agency code: a TIPSA agency handling the shipment
- 012345 | Second agency code: another agency on the shipment
- 1234567890 | Waybill (albarán): the shipment's own number
:::

| Shape (invented) | What it is |
| --- | --- |
| `0123450123451234567890` | The full 22-digit TIPSA number. Use this one. |
| `012345 012345 1234567890` | The same number with spaces. Peek reads it; TIPSA's locator field holds only 22 characters, so type it there without spaces. |
| `1234567890` | The 10-digit waybill alone. TIPSA's locator doesn't take it. |

Shops can have TIPSA send emails and SMS when the parcel sets off, when it reaches the agency, after an incident and after delivery, and their tracking links lead to TIPSA's shipment page. TIPSA's FAQ says that if you have no number, you should ask the sender or the agency that delivers. More places to look are in [how to find your tracking number](guide:find-tracking-number).

Twenty-two digits also fit Austrian Post, [USPS](carrier:usps) and Estafeta. A number whose two agency codes both start with `00` has the shape of a [CTT Express](carrier:ctt-express) number, another Spanish carrier, so check which carrier the shop's email names. People often search for "TIPSA 10 digits": only the full 22-digit form works, so find it in the email or ask the sender.

## What TIPSA's tracking statuses mean

TIPSA's shipment page is in Spanish only, even when you open the locator from the English site. Its top part shows a short timeline with labels such as `Documentado`, `En camino`, `En reparto` and `Entregado`; below, a table lists each scan with date, time and town. These are the scan wordings, in the order a parcel usually meets them:

| TIPSA's status | What it means for you |
| --- | --- |
| `PENDIENTE DE ENTREGAR A TIPSA` | The shop has registered the shipment, but TIPSA doesn't have the parcel yet: a ["label created" status](guide:tracking-statuses). |
| `TRANSITO` | On its way between TIPSA agencies and hubs. |
| `LECTURA EN HUB MADRID` | Scanned at one of TIPSA's hubs; the hub's name varies (`LECTURA EN HUB BARCELONA`, `LECTURA EN HUB BARBERA`). |
| `Pendiente de llegada` | Not yet at the agency that will deliver it. |
| `LEIDO EN DESTINO` | Scanned at the destination. |
| `LECTURA EN AGENCIA DESTINO` | Scanned at the agency that delivers, followed by its name. |
| `REPARTO` | Out for delivery. |
| `Ausente` | The courier couldn't deliver because nobody could be reached. |
| `Faltan Datos / Datos incorrectos` | Address details are missing or wrong. Noted on the delivery round, before another attempt. |
| `Solicitada en otra dirección` | Delivery was asked for at another address, and the parcel is rerouted. |
| `DISPONIBLE` or `Pendiente de recoger en Tipsa` | After a missed delivery, it's held at the agency for you. |
| `Devolver a Origen` | Going back to the sender. |
| `ENTREGADO` | Delivered. |

`Ausente` comes with TIPSA's own note: it couldn't contact you, will try again the next working day, and you can call the destination agency to arrange delivery. The page names that agency, with its address, under "AGENCIA DESTINO".

## TIPSA missed delivery, agencies and pickup points

TIPSA delivers through its own network across mainland Spain and Portugal, the Balearic and Canary Islands, Ceuta and Melilla, and abroad through DPDgroup. After an `Ausente`, a second attempt follows the next working day; the price shops pay includes two attempts.

**Collecting at the agency.** If you'll be away or would rather collect it, TIPSA's FAQ says the parcel stays at the agency nearest your postcode for 15 days from dispatch, not from its arrival. After that it goes back to the sender. Bring your DNI or passport; someone collecting for you needs a photocopy of yours. TIPSA's [agency finder](https://www.tip-sa.com/es/agencias-de-transporte) searches by province or postcode.

**Changing the address.** Only the sender can change the delivery address or details, TIPSA says, so ask the shop.

**Pickup points and lockers.** TIPSA shares a network of Pickup points with [SEUR](carrier:seur), in shops and in lockers open 24/7. Most points open at least six days a week, and you have 7 days to collect the parcel. Delivery to a point is an option the shop offers. Find one with TIPSA's [Pickup point finder](https://aplicaciones.tip-sa.com/puntospickup/).

Some shops also book Saturday delivery or a pre-alert, an SMS and email telling you the day the parcel reaches its destination.

## TIPSA parcel late, lost or returned

TIPSA's pages don't cover a parcel whose tracking stops moving. Call the destination agency named on the shipment page, or ask the shop; [tracking that isn't updating](guide:tracking-not-updating) explains when to worry.

For loss or damage, TIPSA's FAQ is short: contact the sender and tell them. The shop holds the contract with TIPSA and makes the claim; TIPSA publishes no claims procedure or deadline for recipients. TIPSA has no page on a parcel marked `ENTREGADO` that never arrived: tell the sender in that case too.

A parcel not collected within 15 days of dispatch is returned to the sender, so ask the shop about a refund or a new shipment.

## TIPSA contact number and customer service

TIPSA's channels, as listed on its English contact page and FAQ on 11 October 2026:

- **Phone** 902 10 10 47 or 900 10 10 47. The Spanish FAQ gives only 900 10 10 47. TIPSA states no opening hours or call costs.
- **Contact form** on [tip-sa.com/en/contact](https://www.tip-sa.com/en/contact), with the reason "I have a problem with my shipment" and your shipment number.
- **Email** info@tip-sa.com, which the FAQ gives for a missing shipment number.
- **The destination agency**, named on the shipment page, for arranging a delivery.

Portugal has no separate line: TIPSA's Portuguese contact page gives the same two numbers. TIPSA's app pages are still online, but its store listings no longer load.

## Tracking TIPSA parcels with Peek

[Peek](/) takes the 22-digit number with or without spaces. Other carriers share that shape, so Peek asks the likely ones at once, TIPSA first; you see "asking…" and then "TIPSA has this parcel." when TIPSA knows it. A number whose agency codes both start with `00` is taken for CTT Express (unless it starts `00001000`); if your shop said TIPSA, press "Change" and pick it. No postcode is needed.

A 10-digit waybill can't be tracked through TIPSA, even after "Change" or from a link. Links from a shop's TIPSA email can be pasted: Peek reads tip-sa.com links with `id=` on pages ending `datos_env.php`, `datos_prestashop.php` or `datos.php`, and on the bare tip-sa.com address. Links to TIPSA's locator or its shipment pages on tipsa-dinapaq.com are not read.

Peek shows the status, the history with each scan's town on a map, the sender's name unless TIPSA masks it, and the weight. Statuses stay in TIPSA's Spanish, as written. Peek reads every scan time as Madrid time, Portuguese agencies included. It drops the recipient, address, postcodes, reference, parcel count, volume and proof of delivery. Peek shows no expected delivery date and doesn't follow the parcel to another carrier. It checks every 10 minutes by day, every 2 once the parcel is out for delivery, and every hour or two overnight, and can notify you when the status changes.

## Questions about TIPSA tracking

### What is TIPSA in Spain?

TIPSA is Transporte Integral de Paquetería, an express parcel company in the Geopost group. Its about page counts more than 330 agencies, its Portuguese home page more than 300. It has 13 hubs, among them Madrid, Barcelona and Valencia, and delivers abroad through DPDgroup.

### How long does TIPSA take to deliver?

It depends on the service the shop bought. TIPSA sells shops delivery in 24 hours (one attempt the next working day) or within 48 hours; in Portugal, 24 hours to the main towns and 24 to 48 hours elsewhere.

### Can I track a TIPSA parcel with 10 digits?

No: TIPSA's locator asks for the 22-digit number. The 10 digits are the waybill at its end; ask the sender for the full number if you only have those.

### Does TIPSA have parcel shops?

Yes, Pickup points in shops and lockers, shared with SEUR, where a parcel waits 7 days. You get one only if the shop offers delivery to a point.

:::sources
- [TIPSA: Delivery locator](https://www.tip-sa.com/en/delivery-locator) – 22-digit number, form
- [TIPSA: Frequently Asked Questions](https://www.tip-sa.com/en/recursos/faqs) – missing number, 15-day hold, ID, address change, lost or damaged, phones, email
- [TIPSA: Contact](https://www.tip-sa.com/en/contact) – phones, form reasons
- [TIPSA: Collection points](https://www.tip-sa.com/en/services/collection-points) – Pickup network
- [TIPSA: Pre-alert](https://www.tip-sa.com/en/services/pre-alert) – SMS and email notice, Portugal delivery times
- [TIPSA: TIPSA APP](https://www.tip-sa.com/en/recursos/app) – app page
- [TIPSA: Consulta de Envíos](https://dinapaqweb.tipsa-dinapaq.com/dinapaqweb/detalle_envio.php) – Spanish-only shipment page, status wordings and explanations, destination agency
- [TIPSA: Localizar mi envío](https://www.tip-sa.com/es/localizacion-envios) – Spanish locator, phone in footer
- [TIPSA: Preguntas frecuentes](https://www.tip-sa.com/es/recursos/faqs) – coverage, 900 number
- [TIPSA: Empresa de transporte urgente](https://www.tip-sa.com/es/tipsa) – Geopost, 330 agencies, hubs, lockers
- [TIPSA: Página inicial (Portugal)](https://www.tip-sa.com/pt) – more than 300 agencies
- [TIPSA: FAQs eTIPSA](https://www.tip-sa.com/es/faqs-etipsa) – two attempts, 24 and 48 hours, Saturday, shop notifications
- [TIPSA: Recogida en puntos de recogida](https://www.tip-sa.com/es/envios-de-paquetes/puntos-de-recogidas) – shared with SEUR, 7 days, opening days
- [TIPSA: Agencias de transporte](https://www.tip-sa.com/es/agencias-de-transporte) – agency finder
- [TIPSA: Puntos Pickup](https://aplicaciones.tip-sa.com/puntospickup/) – pickup point finder
- [TIPSA: Aviso legal](https://www.tip-sa.com/es/aviso-legal) – company name
- [TIPSA: Contacto (Portugal)](https://www.tip-sa.com/pt/contacto) – same numbers for Portugal
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
