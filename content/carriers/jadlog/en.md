---
title: Jadlog tracking: 14-digit codes, Pickup, Predict and status
description: Track a Jadlog parcel in Brazil: 14-digit tracking numbers, Portuguese and Geopost statuses, Jadlog Pickup points, Predict 1-hour SMS windows and support.
slug: jadlog-tracking
published: 2026-11-23
updated: 2026-11-23
---

Jadlog is one of Brazil's largest express parcel and e-commerce logistics operators and the Brazilian subsidiary of Europe's Geopost group (alongside DPD, Chronopost, SEUR, BRT and Speedy). Across Brazil it runs more than 500 franchises covering roughly 5,000 municipalities, over 7,000 vehicles and more than 4,000 Jadlog Pickup partner shops. You can track a shipment in the `RASTREIE SUA ENCOMENDA` box on [Jadlog's homepage](https://www.jadlog.com.br/jadlog/home), or paste the 14-digit code into the box above.

## Jadlog tracking number: 14-digit format and where to find it

Look for your shipment code (*código de rastreio* or *remessa*) in the online store's dispatch email, your marketplace order screen or the shipping label. Jadlog tracking numbers are 14 consecutive digits:

| Format (invented) | What it is |
| --- | --- |
| `48291736405821` | Jadlog 14-digit parcel number. Shares the 14-digit shape with Geopost/DPD carriers in Europe such as [DPD Germany](carrier:dpd-de), [Chronopost](carrier:chronopost), [SEUR](carrier:seur) and [BRT](carrier:brt). |

Peek tracks by the 14-digit parcel number alone and never asks for your CPF. If your code has 15 digits starting with `8880`, it belongs to [J&T Express Brazil](carrier:j-and-t-br); a 15-character `BR` code belongs to [SPX Express Brazil](carrier:spx-br); a 17-character `AJ` code belongs to [Anjun Logistics](carrier:anjun); and a 13-character code ending in `BR` belongs to [Correios](carrier:correios-br). See [tracking number formats](guide:tracking-number-formats) for other shapes.

## What Jadlog tracking statuses mean

Because Jadlog is part of Geopost, tracking lines appear in Portuguese on Jadlog's site or in English when read through Geopost's tracking API.

| Jadlog / Geopost says | What it means for you | Peek shows |
| --- | --- | --- |
| `Order created`, `COLETA SOLICITADA` | The seller created the shipment electronically and requested collection; Jadlog has not scanned the physical parcel yet. | "Label created" |
| `EMISSAO` | Jadlog issued the transport document and took the parcel into its network. | "Received by carrier" |
| `Em transferência` | The parcel is moving between Jadlog franchises, sorting hubs or linehaul units across Brazil. | "In transit" |
| `Em rota de entrega` | A local courier loaded the parcel and is out delivering it to your address. | "Out for delivery" |
| `ENTREGUE` | Jadlog recorded the parcel as delivered. | "Delivered" |

When a store prints shipping labels in batches before Jadlog's van picks them up, `Order created` or `COLETA SOLICITADA` can remain the only event for a couple of working days. Once `Em transferência` appears, long road distances between Brazilian states can also leave multi-day gaps before the destination franchise scans the parcel.

## Jadlog services: Predict, Pickup points and international export

Jadlog lists its core services on [jadlog.com.br/jadlog/servicos](https://www.jadlog.com.br/jadlog/servicos), [Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) and dedicated product pages:

- **Jadlog.com and Jadlog Package:** e-commerce delivery (Jadlog.com handles parcels up to 30 kg; Jadlog Package covers B2C and B2B road shipments).
- **Jadlog Rodo, RodoEconômico, Cargo and DOC:** heavier road freight up to 50 kg (Rodo), economy road service, B2B air cargo and document transport.
- **[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict):** available in metropolitan and high-volume areas, sending an automatic SMS alert with a 1-hour delivery window so you know when to expect the driver.
- **[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup):** more than 4,000 partner shops across Brazil (reaching over 90% of the population) for parcel collection, drop-off and e-commerce reverse returns (*Reversa*), with SMS and email notices to follow the parcel.
- **[Jadlog Internacional](https://www.jadlog.com.br/jadlog/internacional):** export shipping from Brazil to 154 countries (300 destinations) using Geopost's international network.

**What Jadlog does not publish publicly.** Jadlog's website does not state a single universal delivery-attempt limit, guaranteed delivery SLA or fixed Pickup-point holding duration for all online shops. Check the SMS or email notice sent for your Pickup parcel — or your online store's order screen — for exact collection deadlines and redelivery rules.

## Jadlog parcel stuck, delayed or not received

For online store purchases, the seller is Jadlog's contractual customer and handles refunds, address corrections and reshipments.

**Stuck at `Order created` or `COLETA SOLICITADA`.** The seller generated the shipment data, but Jadlog has not yet collected or billed the parcel into its network. Ask the store whether the package has left their warehouse.

**Quiet in transit (`Em transferência`).** Road transfers across Brazilian states, weekends and regional hub backlogs regularly pause tracking between franchise scans. See [why tracking stops updating](guide:tracking-not-updating).

**Marked `ENTREGUE` but missing.** Check with relatives, neighbours and your building's *portaria* (reception/doorman) first, and check whether you selected a Jadlog Pickup shop at checkout. If nobody received it, contact the store straight away so it can open an investigation with Jadlog. See [delivered but not received](guide:delivered-not-received).

**Damaged, lost or returned.** Keep the outer packaging and take clear photos of any damage and the shipping label before contacting the seller; see [damaged parcel](guide:damaged-parcel) and [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## Contacting Jadlog and consumer options

Jadlog's [Atendimento page](https://www.jadlog.com.br/jadlog/atendimento) does not display a public recipient telephone number or general customer-service email address. Your practical options are:

- **Contact the online store first:** stores hold the shipping contract (`CT-e` / invoice) needed to open delivery inquiries, change an address or authorise a refund.
- **Local franchise finder:** look up the serving branch on [Jadlog Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) if you need information on a local depot.
- **Privacy and personal-data requests (LGPD):** Jadlog provides a dedicated data-subject portal on [jadlog.com.br/jadlog/lgpd](https://www.jadlog.com.br/jadlog/lgpd).
- **Consumer mediation:** if a dispute with a participating store or carrier cannot be settled directly, Brazilian consumers with a silver or gold gov.br account can register a free complaint on [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1), where participating companies commit to reply within 10 days.

> Be wary of SMS, WhatsApp or email links asking you to pay a small "release fee" or "redelivery tax" for a domestic Jadlog parcel. Confirm any unexpected charge with the store where you placed your order first. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Jadlog parcel with Peek

Because 14-digit numbers are shared across Geopost/DPD carriers worldwide, [Peek](/) uses your location to choose who to ask first. In Brazil, Peek queries Jadlog first among 14-digit candidates (ahead of European DPD units, Chronopost, SEUR and BRT) and shows "Jadlog has this parcel." once Jadlog confirms it. Outside Brazil, Peek treats Jadlog as a fallback — asking the European Geopost carriers first and checking Jadlog if none holds the number. Pasting a `jadlog.com.br/jadlog/captcha?cte=` link names Jadlog directly anywhere; otherwise tap "Change" while adding or "Change carrier" on the parcel screen and select Jadlog (searching "DPD Brazil" also finds it).

Peek queries Geopost's guest tracking API directly for Jadlog without needing your postcode or CPF. It shows the current stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Delivered"), summary scan history (in English from Geopost's API or Portuguese when relayed from Jadlog's local tracker) and the delivery timestamp. Estimated delivery dates, parcel weight, Jadlog Pickup shop addresses and recipient names or signatures are not exposed in Peek.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day the parcel goes out for delivery, every 2 minutes from 6 a.m. local time, while Jadlog's tracking answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When Jadlog's tracking doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Jadlog." After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Jadlog."

You can choose alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking only and cannot book a redelivery or contact a Jadlog franchise for you.

## Questions about Jadlog tracking

### What does a Jadlog tracking number look like?

A Jadlog parcel number is 14 digits long (for example `48291736405821`, digits invented). You can track it with that 14-digit code in Peek or on [Jadlog's homepage](https://www.jadlog.com.br/jadlog/home).

### What is Jadlog Predict?

[Jadlog Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) is an automatic SMS notification service offered in metropolitan and high-volume areas that tells recipients the expected 1-hour delivery window for their parcel.

### How does Jadlog Pickup work?

[Jadlog Pickup](https://www.jadlog.com.br/jadlog/pickup) uses over 4,000 partner commercial shops across Brazil where recipients can collect parcels or drop off reverse returns. Jadlog sends SMS and email notices so you can follow the parcel.

### Why is Jadlog connected to DPD and Geopost?

Jadlog is part of the European Geopost group alongside DPD, Chronopost, SEUR, BRT and Speedy. That is why Jadlog uses 14-digit Geopost parcel numbers and connects to Geopost's international network across 154 countries.

:::sources
- [Jadlog: Home](https://www.jadlog.com.br/jadlog/home) – `RASTREIE SUA ENCOMENDA` tracker and Brazilian network overview
- [Jadlog: Quem Somos](https://www.jadlog.com.br/jadlog/quemsomos) – Geopost group membership, 500+ franchises, 4,000+ Pickup partners, 7,000+ vehicles, 7,000 direct and indirect staff, 154 countries
- [Jadlog: Produtos e Serviços](https://www.jadlog.com.br/jadlog/servicos) – Jadlog.com (up to 30 kg), Package, Rodo (up to 50 kg), RodoEconômico, Cargo, DOC
- [Jadlog: Pickup](https://www.jadlog.com.br/jadlog/pickup) – 4,000+ partner shops, >90% population reach, SMS/email notifications and Reversa returns
- [Jadlog: Predict](https://www.jadlog.com.br/jadlog/jadlogPredict) – automatic SMS alerts with 1-hour delivery window
- [Jadlog: Internacional](https://www.jadlog.com.br/jadlog/internacional) – export to 154 countries / 300 destinations via Geopost
- [Jadlog: Unidades e Franquias](https://www.jadlog.com.br/jadlog/unidades) – Brazilian franchise and unit finder
- [Jadlog: Atendimento](https://www.jadlog.com.br/jadlog/atendimento) – public customer-service page scope
- [Jadlog: LGPD](https://www.jadlog.com.br/jadlog/lgpd) – data-subject privacy portal
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – public consumer mediation platform (10-day response, silver/gold gov.br account)
- [Universal Parcel Scraper: jadlog](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jadlog) – 14-digit detection and Brazil priority (`DPD Brazil` alias), `captcha?cte=` link rule, Geopost guest API, status mapping and refresh cadence
:::
