---
title: PasarEX tracking — Amazon AMZPSR codes, customs & help
description: Track PasarEX (Pasar Express) imports in Colombia, Chile, Panama, and Barbados. Check AMZPSR numbers, customs statuses, PQR claim rules, and phone lines.
slug: pasarex-tracking
published: 2026-11-23
updated: 2026-11-23
---

PasarEX (the commercial brand of *PASAR EXPRESS SAS*, a Colombian company founded in 1993) offers express courier, air cargo and last-mile delivery for e-commerce platforms. Its sites cover **Colombia**, **Chile**, **Panama** and **Barbados**, and its services include a Miami logistics centre and multimodal shipping from China.

PasarEX clears parcels through customs as their broker, so its tracking runs from the manifest through the consolidated import arrival, customs, warehouses and local delivery.

## PasarEX tracking number formats

Peek takes PasarEX numbers of 6 to 30 letters and digits with at least one digit. One shape is PasarEX's alone:

| Format (invented) | What it is |
| --- | --- |
| `AMZPSR482917360` | `AMZPSR` + 9 digits (15 characters) — an Amazon number that PasarEX's tracker looks up; no other carrier in Peek has this shape. |

If your order confirmation includes a direct tracking URL (`tracking.pasarex.com/?awb=...`), pasting that link into Peek extracts the waybill and assigns PasarEX immediately. For broader e-commerce tracking patterns, see the [tracking number formats guide](guide:tracking-number-formats) and the [Amazon orders guide](guide:amazon-orders).

## What PasarEX tracking statuses mean

PasarEX groups scan rows under broad category headings on its tracker (`COMMENTS`, `IN CUSTOMS PROCESS`, `RECEIVED FOR DELIVERY`, `IN TRANSIT`, `IN WAREHOUSE`, `OUT FOR DISTRIBUTION`, `DELIVERED`, `SHIPPING WITH DELIVERY ISSUE`), with individual scan sentences written in Spanish or English along with the city and date/time.

| PasarEX scan wording | What it means |
| --- | --- |
| `Orden de pedido recibida - paquete no arribado` / `Information (Manifest) of the shipment received.` | Electronic manifest data reached PasarEX before the physical parcel arrived at its import hub. |
| `Carga consolidada para importación recibida` / `Reception of consolidated cargo in import.` | The consolidated air import load arrived at PasarEX's gateway station (such as Bogotá) ahead of customs. |
| `Entry to station for customs processing.` | The parcel has entered the customs station for import clearance. |
| `Cleared by customs by PasarEx.` / `Cleared by customs and transferred to OPS.` | PasarEX cleared the shipment through customs and transferred it to domestic operations. |
| `Ingresó a bodega` / `Package received in warehouse.` / `Reception of consolidated cargo at a station.` | Checked into a PasarEX warehouse or destination station between legs of the domestic route. |
| `Package in transit` / `Package in ground transit` | Moving by road or domestic linehaul between PasarEX stations. |
| `Applies to cargo that may experience delivery delays due to disturbances or protests` | Service advisory noting potential route delays from local disruptions; the shipment continues moving afterwards. |
| `Shipment left to local route for final delivery` | Out on a local delivery route with the courier today. |
| `Shipment delivered in perfect condition` | Delivered to the recipient's address. |

## Customs clearance, PQR claims, and delivery expectations

Because PasarEX clears cross-border imports directly as a customs broker before domestic distribution:

- **Before the parcel arrives:** while tracking only shows `Information (Manifest) of the shipment received.` or `Orden de pedido recibida - paquete no arribado`, the order is booked but the parcel hasn't reached PasarEX yet. Expect a quiet gap until `Reception of consolidated cargo in import.` or `Carga consolidada para importación recibida` posts. See the [customs guide](guide:customs) and [when tracking stops updating](guide:tracking-not-updating).
- **Colombia PQR and indemnity rules (CRC Resolution 5050 of 2016):** On [PasarEX Colombia Support](https://pasarex.com/co/soporte/), Pasar Express SAS publishes statutory claim deadlines under Law 1369/2009 and Resolution 5050/2016. Recipients must file spoliation or damage claims within **5 business days** of receiving the parcel, while senders have **10 calendar days** (domestic) or **6 months** (international) for loss, spoliation, or damage (corporate contracts may apply their own terms). Claims require copies of the air waybill (*Guía Aérea*), commercial invoice, Pasar Express invoice, item list, packaging description, and photographs. PasarEX normally resolves PQRs within **15 business days** (extendable if further investigation is needed), and users have **10 business days** after notification to submit an appeal (*recurso de reposición y en subsidio de apelación*).

## Tracking PasarEX with Peek

Paste your number or a `tracking.pasarex.com/?awb=...` link into Peek, which follows more than 4,500 carriers. A link goes to PasarEX at once. For a bare number Peek never asks PasarEX: it may ask other carriers with the same shape, then asks tracking services ("Checking tracking services…"), and files the parcel under PasarEX only if a tracking service names it, and no other carrier, with dated steps. So press "Change" and pick PasarEX (under "Fits this number" for an `AMZPSR` number), or use "Change carrier" on a parcel you've already added.

PasarEX's tracker asks for a reCAPTCHA tick on each lookup, so Peek reads it through the open-source [universal-parcel-scraper PasarEX adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex) with a browser helper. Peek reads every scan's wording, city and time and lists them newest first rather than by PasarEX's headings. PasarEX gives local times without an offset: Peek dates each on its city's zone (New York time for Miami, Bogotá time for Bogotá, Medellín and Cali, and Santiago, Panama or Barbados time for those places), a scan without a city on its neighbour's, else on Bogotá time, and shows every scan on your device's clock. PasarEX's tracker does not publish an estimated delivery date, weight, or proof of delivery.

Peek maps PasarEX's scan sentences into clean progress stages: **Label created** for manifest receipt before physical arrival, **In transit** from consolidated import reception through customs release, warehouse intake, and ground transit (PasarEX's wording jumps straight from manifest receipt to consolidated import arrival rather than posting a separate **Received by carrier** scan), **At customs** while inside customs processing, **Out for delivery** when the parcel leaves on the local route, and **Delivered** upon final delivery.

Peek checks PasarEX's tracker hourly at most, the least time PasarEX allows between two checks, also after a failed check, whether or not you have the parcel open; it makes no 2-minute checks on the out-for-delivery day. After seven days at "Label created" it checks every 6 hours. When PasarEX's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, and every 3 hours after a quiet day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking PasarEX." After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking PasarEX."

Choose **Every scan**, **Important steps**, or **Delivery only** in Peek's alert settings to control which updates notify you.

## Contacting PasarEX

PasarEX publishes phone lines and email addresses for each country:

- **Colombia ([pasarex.com/co/soporte](https://pasarex.com/co/soporte/)):** `+57 601 384 7000`, `+57 601 390 6000` (also Bogotá PQR line), `+57 601 291 6500`, `+57 300 919 7521`, `+57 300 919 7532`; email `sac@pasar.net` (support) and `pqr@pasar.net` (PQRs/claims)
- **Chile ([pasarex.com/cl](https://pasarex.com/cl/)):** `+56 2 2571 7500`; email `clgestionpe@pasar.net`
- **Panama ([pasarex.com/pa](https://pasarex.com/pa/)):** `+507 836 0399`; email `pagestionpe@pasar.net` and `pqr@pasar.net`
- **Barbados:** `+1 246 4370277`, `+1 305 7042435`
- **Company overview:** [PasarEX Colombia](https://pasarex.com/co/) and [Nosotros](https://pasarex.com/co/nosotros/)

## Frequently asked questions

### What is an `AMZPSR` tracking number?

`AMZPSR` followed by 9 digits (15 characters total, such as `AMZPSR482917360`) is an Amazon number that PasarEX's tracker looks up. No other carrier in Peek has this shape, but Peek doesn't ask PasarEX by itself: pick PasarEX under "Fits this number".

### Why does my PasarEX parcel say `Orden de pedido recibida - paquete no arribado`?

It means PasarEX received the shipment's manifest, but the physical parcel has not yet arrived at PasarEX's destination import station. Scans resume once the consolidated flight arrives.

### Why does Peek refresh PasarEX less frequently than some other carriers?

PasarEX allows a check of its tracker at most once an hour, also after a failed check, so Peek checks it hourly. Its tracker also asks for a reCAPTCHA tick on each lookup.

### How long do I have to file a damage claim with PasarEX in Colombia?

Under Colombia's CRC Resolution 5050/2016 published on [pasarex.com/co/soporte](https://pasarex.com/co/soporte/), recipients have **5 business days** after delivery to file a claim for damage or missing contents, while senders have 10 calendar days (domestic) or 6 months (international) to file for loss or damage.

:::sources
- [PasarEX Colombia](https://pasarex.com/co/)
- [PasarEX Colombia — Soporte y PQR](https://pasarex.com/co/soporte/)
- [PasarEX Colombia — Nosotros](https://pasarex.com/co/nosotros/)
- [PasarEX Chile](https://pasarex.com/cl/)
- [PasarEX Panamá](https://pasarex.com/pa/)
- [universal-parcel-scraper PasarEX adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex)
:::
