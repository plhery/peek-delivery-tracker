---
title: Memphis Courier tracking — numbers, statuses, and customs
description: Track Memphis Courier imports from the US to Brazil. Check M-MIA tracking numbers, GRU Airport and ANVISA clearance statuses, CPF rules, and contacts.
slug: memphis-courier-tracking
published: 2026-11-23
updated: 2026-11-23
---

Memphis Courier is a Brazilian international logistics and express-import carrier with more than 25 years of experience moving courier and formal shipments between the United States and Brazil. Operating from its courier terminal at Guarulhos International Airport (GRU) and its São Paulo office, Memphis Courier handles end-to-end air transport, Receita Federal customs clearance, regulatory clearance with agencies such as ANVISA (including medical cannabis imports under RDC 660 and RDC 327), and nationwide last-mile delivery across Brazil.

Because most Memphis Courier shipments fly out of Miami into Guarulhos before entering Brazilian customs and health-agency review, tracking updates are tightly tied to airport arrival, Receita Federal inspection, and ANVISA approval.

## Memphis Courier tracking number format

Memphis Courier tracking numbers follow a distinctive 11-character alphanumeric pattern:

- **`M` + 7 digits + `MIA`** (11 characters total, such as `M4829173MIA`) — the house courier code issued for shipments departing through Memphis Courier's Miami gateway to Brazil.

Spaces and lowercase letters are stripped when the number is looked up. Memphis Courier's tracking portal (`clients.memphiscourier.com.br`) accepts this code directly without requiring a customer account login. Because the `M` + 7 digits + `MIA` shape is specific to Memphis Courier, Peek identifies it as a Memphis Courier candidate automatically, though it keeps detection at low confidence until Memphis Courier's service confirms the shipment exists. For other parcel formats commonly seen on Brazil imports, see the [tracking number formats guide](guide:tracking-number-formats).

## Memphis Courier tracking statuses

Memphis Courier's tracking service returns short 3-letter scan codes paired with Portuguese descriptions and a location column (usually a state or country). Its own step bar progresses through four headline milestones — created (`FLT`), customs (`BRP`), released (`CLA`), and delivered (`DLV`) — with detailed transit and inspection scans in between.

| Code | Memphis Courier wording | What it means |
| --- | --- | --- |
| `FLT` | Seu pedido foi criado com sucesso | The shipment order has been registered in Memphis Courier's system before air departure. |
| `CHK` | Entrada registrada no Centro de Distribuição | Checked in at a Memphis Courier distribution centre. |
| `ENO` | Encomenda em trânsito | Moving within Memphis Courier's network. |
| `TRA` | Encomenda em transferência | Being transferred between handling sites. |
| `DEP` | A encomenda saiu da origem e está a caminho do aeroporto de destino (Guarulhos - SP) | Departed the origin hub on a flight bound for Guarulhos International Airport (GRU). |
| `ARR` | Seu pedido chegou ao aeroporto de destino (Guarulhos - SP) | Landed at Guarulhos Airport in São Paulo. |
| `AAZ` | Pedido recebido pela GRU Airport | Received by the GRU Airport cargo terminal ahead of formal customs entry. |
| `BRP` | Processo de desembaraço iniciado | Brazilian customs clearance has started. |
| `HOC` | Encomenda sendo inspecionada pela Alfândega | Under inspection by Receita Federal (Brazilian customs). |
| `ANV` | Em inspeção pela ANVISA | Under regulatory inspection by ANVISA, Brazil's health agency — common on medical CBD and pharmaceutical imports. |
| `CLA` | Liberado | Customs and any required regulatory agencies have released the shipment for domestic delivery. |
| `DLV` | Entregue | Delivered to the recipient's address in Brazil. |

## Customs, CPF validation, and ANVISA clearance

Every personal express import into Brazil requires the recipient's CPF (Cadastro de Pessoas Físicas) for Receita Federal clearance. As Memphis Courier notes on its [additional services page](https://memphiscourier.com.br/servicos-adicionais/), many foreign online stores do not include a CPF field at checkout; when that happens, Memphis Courier's customer service team reaches out to validate the recipient's CPF so clearance is not blocked.

For regulated healthcare and medical cannabis imports from the US, Europe, Colombia, or Uruguay, parcels go through both Receita Federal (`HOC`) and ANVISA (`ANV`) checks at Guarulhos before `CLA` (released) appears. Inspection times depend on agency queues and whether all prescriptions, import authorizations, and recipient details match. If your shipment stays on `BRP`, `HOC`, or `ANV` for several business days without a change, check your email and WhatsApp messages from Memphis Courier in case they requested a CPF confirmation or supporting document. For more on how international import clearance works, see the [customs guide](guide:customs) and [when tracking stops updating](guide:tracking-not-updating).

Once customs releases the parcel (`CLA`), domestic distribution covers all Brazilian states. If another carrier delivers a separate order to Brazil, compare tracking behaviour on [Anjun Express](carrier:anjun), [Jadlog](carrier:jadlog), or [J&T Express Brazil](carrier:j-and-t-br).

## Tracking Memphis Courier with Peek

Paste an `M...MIA` code into Peek and Peek matches it to Memphis Courier automatically once the carrier responds. If you need to pick or switch the carrier manually, choose **Change** when adding the parcel or **Change carrier** from the tracking view.

Peek queries Memphis Courier's public tracking service directly through the open-source [universal-parcel-scraper Memphis Courier adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier). It pulls every scan code, Portuguese wording, location label, and total shipment weight in kilograms when present, keeping each scan's local wall-clock timestamp (or explicit offset when the service provides one) rather than inventing a time zone. Fields shown on the client portal such as internal remarks, parcel count, postcodes, and attached customs documents stay on Memphis Courier's own site.

Peek groups Memphis Courier's 12 scan codes into clear progress stages: **Label created** for `FLT`, **In transit** from `CHK` through `AAZ` and again after customs release at `CLA` (Memphis Courier's code set moves directly from order creation into distribution-centre transit rather than using a separate **Received by carrier** code), **At customs** for `BRP`, `HOC`, and `ANV`, and **Delivered** once `DLV` posts.

During flights from Miami or while waiting in Receita Federal and ANVISA queues, Peek displays: "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Memphis Courier." If no new scan arrives for more than three weeks, Peek shows: "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Memphis Courier." You can jump straight to official support at any time through **Get help from Memphis Courier** on the tracking screen.

Choose **Every scan**, **Important steps**, or **Delivery only** in Peek's alert preferences to decide how often you want notifications.

## Contacting Memphis Courier

Memphis Courier publishes official support channels on its website and explicitly states in its official disclaimer that it does not treat third-party complaint sites such as Reclame Aqui as an official support or protocol channel.

- **Official contact page:** [Fale Conosco](https://memphiscourier.com.br/fale-conosco/) (contact form)
- **Phone / customer service:** +55 11 94264-7550 (listed for both the GRU Courier Terminal and São Paulo office)
- **Email:** `atendimento@memphiscourier.com.br`, `atendimento1@memphiscourier.com.br`, and `info@memphiscourier.com.br`
- **Guarulhos Courier Terminal:** Aeroporto Internacional de Guarulhos — Terminal Courier, CEP 07190-100, São Paulo, Brazil
- **São Paulo office:** Av. Marques de São Vicente, 230, cj 1216 — Barra Funda, CEP 01139-000, São Paulo, Brazil ([Quem Somos](https://memphiscourier.com.br/quem-somos/))

Have your `M...MIA` tracking number and recipient CPF ready when contacting support so the team can look up your customs file immediately.

## Frequently asked questions

### What does `ANV` ("Em inspeção pela ANVISA") mean on Memphis Courier tracking?

`ANV` means Brazil's health regulatory agency (ANVISA) is reviewing the shipment at Guarulhos Airport as part of import clearance. It is standard for medical cannabis (RDC 660 / RDC 327) and health-related imports and remains active until ANVISA and customs release the parcel (`CLA`).

### Why did Memphis Courier contact me for my CPF?

Receita Federal requires the recipient's CPF on every personal express import into Brazil. When an overseas checkout does not collect a CPF, Memphis Courier's customer service team contacts you to validate it before clearance can proceed.

### Why are there no scans between `DEP` and `ARR`?

`DEP` records departure from the origin hub toward Guarulhos International Airport and `ARR` records landing in São Paulo. No intermediate scan posts while the shipment is airborne or awaiting airport cargo intake (`AAZ`).

### Does Memphis Courier answer support requests posted on Reclame Aqui?

No. Memphis Courier's official site disclaimer states that it handles complaints, questions, and clarifications exclusively through the official channels published on [memphiscourier.com.br](https://memphiscourier.com.br/) (phone, email, and its contact form) to preserve traceability and data confidentiality.

:::sources
- [Memphis Courier](https://memphiscourier.com.br/)
- [Memphis Courier — Quem Somos](https://memphiscourier.com.br/quem-somos/)
- [Memphis Courier — Serviços Adicionais](https://memphiscourier.com.br/servicos-adicionais/)
- [Memphis Courier — Fale Conosco](https://memphiscourier.com.br/fale-conosco/)
- [universal-parcel-scraper Memphis Courier adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier)
:::
