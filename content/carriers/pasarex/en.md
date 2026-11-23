---
title: PasarEX tracking — Amazon AMZPSR codes, customs & help
description: Track PasarEX (Pasar Express) imports in Colombia, Chile, Panama, and Barbados. Check AMZPSR numbers, customs statuses, PQR claim rules, and phone lines.
slug: pasarex-tracking
published: 2026-11-23
updated: 2026-11-23
---

PasarEX (the commercial brand of *PASAR EXPRESS SAS*, founded in Colombia in 1993) is an international express courier, air-cargo operator, and e-commerce last-mile carrier serving **Colombia**, **Chile**, **Panama**, and **Barbados**, supported by a Miami logistics hub and multimodal shipping lines from China. Shoppers most often encounter PasarEX on international Amazon orders and cross-border e-commerce imports cleared through Bogotá, Santiago, Panama City, or Bridgetown.

Because PasarEX acts as both the import customs broker and domestic last-mile operator on many routes, tracking follows the shipment from manifest receipt through consolidated import arrival, customs clearance, warehouse processing, and local delivery.

## PasarEX tracking number formats

PasarEX's tracker (`tracking.pasarex.com`) accepts single alphanumeric shipment numbers of 6 to 30 characters containing at least one digit:

| Format (invented) | What it is |
| --- | --- |
| `AMZPSR482917360` | `AMZPSR` + 9 digits (15 characters) — Amazon cross-border import number handled by PasarEX; uniquely matched as a PasarEX candidate. |
| `PSR482917360` | PasarEX's own waybill (*guía aérea*) — select PasarEX manually or paste your tracking link. |

If your order confirmation includes a direct tracking URL (`tracking.pasarex.com/?awb=...`), pasting that link into Peek extracts the waybill and assigns PasarEX immediately. For broader e-commerce tracking patterns, see the [tracking number formats guide](guide:tracking-number-formats) and the [Amazon orders guide](guide:amazon-orders).

## What PasarEX tracking statuses mean

PasarEX groups scan rows under broad category headings on its tracker (`COMMENTS`, `IN CUSTOMS PROCESS`, `RECEIVED FOR DELIVERY`, `IN TRANSIT`, `IN WAREHOUSE`, `OUT FOR DISTRIBUTION`, `DELIVERED`), with individual scan sentences written in Spanish or English along with the city and date/time.

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

- **Pre-arrival manifest period:** When tracking only shows `Information (Manifest) of the shipment received.` or `Orden de pedido recibida - paquete no arribado`, the parcel is still travelling from the overseas fulfillment centre (often Miami or China) to the destination country. Expect a quiet gap until `Reception of consolidated cargo in import.` posts. See the [customs guide](guide:customs) and [when tracking stops updating](guide:tracking-not-updating).
- **Colombia PQR and indemnity rules (CRC Resolution 5050 of 2016):** On [PasarEX Colombia Support](https://pasarex.com/co/soporte/), Pasar Express SAS publishes statutory claim deadlines under Law 1369/2009 and Resolution 5050/2016. Recipients must file spoliation or damage claims within **5 business days** of receiving the parcel, while senders have **10 calendar days** (domestic) or **6 months** (international) for loss, spoliation, or damage (corporate contracts may apply their own terms). Claims require copies of the air waybill (*Guía Aérea*), commercial invoice, Pasar Express invoice, item list, packaging description, and photographs. PasarEX normally resolves PQRs within **15 business days** (extendable if further investigation is needed), and users have **10 business days** after notification to submit an appeal (*recurso de reposición y en subsidio de apelación*).

## Tracking PasarEX with Peek

Paste an `AMZPSR...` tracking number or a `tracking.pasarex.com/?awb=...` link into Peek. Because `AMZPSR` + 9 digits matches PasarEX uniquely as a low-confidence candidate, Peek queues it for PasarEX automatically; for non-`AMZPSR` PasarEX waybills, pick PasarEX via **Change** when adding the parcel or **Change carrier** on the tracking screen.

PasarEX's public tracker protects lookups behind a reCAPTCHA check, so Peek fetches updates through the open-source [universal-parcel-scraper PasarEX adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex) using a browser helper on a 60-minute refresh cadence. Peek reads every scan sentence, city, and local wall-clock timestamp (`dd/MM/yyyy HH:mm:ss`, without inventing a time zone offset) and orders events newest first rather than by PasarEX's unchronological category buckets. PasarEX's tracker does not publish an estimated delivery date, weight, or proof of delivery.

Peek maps PasarEX's scan sentences into clean progress stages: **Label created** for manifest receipt before physical arrival, **In transit** from consolidated import reception through customs release, warehouse intake, and ground transit (PasarEX's wording jumps straight from manifest receipt to consolidated import arrival rather than posting a separate **Received by carrier** scan), **At customs** while inside customs processing, **Out for delivery** when the parcel leaves on the local route, and **Delivered** upon final delivery.

During air consolidations, customs processing, or weekends, Peek notes: "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking PasarEX." After more than three weeks without a new scan, Peek displays: "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking PasarEX."

Choose **Every scan**, **Important steps**, or **Delivery only** in Peek's alert settings to control which updates notify you.

## Contacting PasarEX

PasarEX publishes bilingual customer service lines and regional email addresses across its four country sites:

- **Colombia ([pasarex.com/co/soporte](https://pasarex.com/co/soporte/)):** `+57 601 384 7000`, `+57 601 390 6000` (also Bogotá PQR line), `+57 601 291 6500`, `+57 300 919 7521`, `+57 300 919 7532`; email `sac@pasar.net` (support) and `pqr@pasar.net` (PQRs/claims)
- **Chile ([pasarex.com/cl](https://pasarex.com/cl/)):** `+56 2 2571 7500`; email `clgestionpe@pasar.net`
- **Panama ([pasarex.com/pa](https://pasarex.com/pa/)):** `+507 836 0399`; email `pagestionpe@pasar.net` and `pqr@pasar.net`
- **Barbados:** `+1 246 4370277`, `+1 305 7042435`
- **Company overview:** [PasarEX Colombia](https://pasarex.com/co/) and [Nosotros](https://pasarex.com/co/nosotros/)

For Amazon orders shipped under an `AMZPSR...` code, you can also check your Amazon order details or contact Amazon customer service directly for missing-item refunds or replacements.

## Frequently asked questions

### What is an `AMZPSR` tracking number?

`AMZPSR` followed by 9 digits (15 characters total, such as `AMZPSR482917360`) is an Amazon international shipment code handled by PasarEX for import customs clearance and local delivery in markets such as Colombia, Chile, Panama, and Barbados.

### Why does my PasarEX parcel say "Orden de pedido recibida - paquete no arribado"?

It means PasarEX received the electronic shipping manifest from the seller or consolidator, but the physical parcel has not yet arrived at PasarEX's destination import station. Scans resume once the consolidated flight arrives.

### Why does Peek refresh PasarEX less frequently than some other carriers?

PasarEX's web tracker requires a browser reCAPTCHA step for each query, so Peek spaces automatic refreshes at least 60 minutes apart to keep checks reliable.

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
