---
title: Memphis Courier tracking — numbers, statuses, and customs
description: Track Memphis Courier imports from the US to Brazil. Check M-MIA tracking numbers, GRU Airport and ANVISA clearance statuses, CPF rules, and contacts.
slug: memphis-courier-tracking
published: 2026-11-23
updated: 2026-11-23
---

Memphis Courier is a 100% Brazilian logistics company with more than 25 years of history, handling express and formal shipments for export and import. From its courier terminal at Guarulhos International Airport (GRU) and its São Paulo office, it offers air transport, Receita Federal customs clearance, regulatory clearance with ANVISA (including medical cannabis imports under RDC 660 and RDC 327, for medicines made in the United States, Europe, Colombia and Uruguay) and delivery across Brazil.

Its tracking follows the parcel to Guarulhos airport, through Receita Federal inspection and ANVISA review, and on to delivery.

## Memphis Courier tracking number format

Memphis Courier tracking numbers follow a distinctive 11-character alphanumeric pattern:

- **`M` + 7 digits + `MIA`** (11 characters total, such as `M4829173MIA`) — the shape Peek recognises as Memphis Courier's.

Peek takes Memphis Courier codes of 6 to 30 letters and digits; it removes spaces and capitalises letters. Memphis Courier's tracking portal (`clients.memphiscourier.com.br`) looks a code up without a customer account login. From the `M` + 7 digits + `MIA` shape, Peek asks Memphis Courier alone and shows "Memphis Courier has this parcel." once it answers. For other parcel formats commonly seen on Brazil imports, see the [tracking number formats guide](guide:tracking-number-formats).

## Memphis Courier tracking statuses

Memphis Courier's tracking service returns 3-letter scan codes with Portuguese descriptions and a location column (a state or country). Its tracking page's step bar runs Criado (`FLT`), customs (`BRP`), Liberado and Entregue; the scans that reach the last two (`CLA` and `DLV`) are known only by those step labels.

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
| `ANV` | Em inspeção pela ANVISA | Under regulatory inspection by ANVISA, Brazil's health agency, during clearance. |
| `CLA` | Liberado (step bar) | Customs and any required regulatory agencies have released the shipment for domestic delivery. |
| `DLV` | Entregue (step bar) | Delivered to the recipient's address in Brazil. |

## Customs, CPF validation, and ANVISA clearance

Every personal express import into Brazil requires the recipient's CPF (Cadastro de Pessoas Físicas) for Receita Federal clearance. As Memphis Courier notes on its [additional services page](https://memphiscourier.com.br/servicos-adicionais/), many foreign online stores do not include a CPF field at checkout; when that happens, Memphis Courier's customer service team reaches out to validate the recipient's CPF so clearance is not blocked.

For medical cannabis and other medicines Memphis Courier brings in from the US, Europe, Colombia or Uruguay, the parcel can go through both Receita Federal (`HOC`) and ANVISA (`ANV`) checks at Guarulhos before `CLA` (released) appears. If your shipment stays on `BRP`, `HOC`, or `ANV` for several business days without a change, check your email and WhatsApp: Memphis Courier keeps customers informed by email, WhatsApp, chat or phone, and may have asked for a CPF confirmation or a document. For more on how international import clearance works, see the [customs guide](guide:customs) and [when tracking stops updating](guide:tracking-not-updating).

Once customs releases the parcel (`CLA`), domestic distribution covers all Brazilian states. If another carrier delivers a separate order to Brazil, compare tracking behaviour on [Anjun Express](carrier:anjun), [Jadlog](carrier:jadlog), or [J&T Express Brazil](carrier:j-and-t-br).

## Tracking Memphis Courier with Peek

Paste an `M...MIA` code into Peek and Peek matches it to Memphis Courier automatically once the carrier responds. If you need to pick or switch the carrier manually, choose **Change** when adding the parcel or **Change carrier** from the tracking view.

Peek queries Memphis Courier's public tracking service directly through the open-source [universal-parcel-scraper Memphis Courier adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier). It reads each scan's code, Portuguese wording and location label, and the total weight in kilograms when present. Memphis Courier gives each scan's time as a wall clock and states an offset only where the time ends with one. Peek keeps that offset; otherwise it dates the scan on its place's zone (New York time for `Estados Unidos`, São Paulo time for `SP` or `Brasil`), a scan without a place on its neighbour's, else on São Paulo time, and shows every scan on your device's clock. Fields shown on the client portal such as internal remarks, parcel count, postcodes, destination and attached customs documents stay on Memphis Courier's own site.

Peek groups Memphis Courier's 12 scan codes into clear progress stages: **Label created** for `FLT`, **In transit** from `CHK` through `AAZ` and again after customs release at `CLA` (Memphis Courier's code set moves directly from order creation into distribution-centre transit rather than using a separate **Received by carrier** code), **At customs** for `BRP`, `HOC`, and `ANV`, and **Delivered** once `DLV` posts.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes while Memphis Courier's tracker answers. Otherwise Peek checks hourly. Memphis Courier's tracker shows no out-for-delivery step, so Peek never switches to its 2-minute delivery-day checks. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When Memphis Courier's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Memphis Courier.", with a "Get help from Memphis Courier" link to Memphis Courier's Fale Conosco page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Memphis Courier."

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

`ANV` means Brazil's health regulatory agency (ANVISA) is inspecting the shipment as part of import clearance. Memphis Courier clears medical cannabis imports (RDC 660 / RDC 327) with ANVISA; the parcel stays at customs until `CLA` (released).

### Why did Memphis Courier contact me for my CPF?

Receita Federal requires the recipient's CPF on every personal express import into Brazil. When an overseas checkout does not collect a CPF, Memphis Courier's customer service team contacts you to validate it before clearance can proceed.

### Why are there no scans between `DEP` and `ARR`?

`DEP` records departure from the origin toward Guarulhos airport and `ARR` the arrival there. Memphis Courier's code list has no scan for the flight itself, so the next one is `ARR` or the cargo terminal's `AAZ`.

### Does Memphis Courier answer support requests posted on Reclame Aqui?

No. Memphis Courier's official site disclaimer states that it handles complaints, questions, and clarifications exclusively through the official channels published on [memphiscourier.com.br](https://memphiscourier.com.br/) (phone, email, and its contact form) to preserve traceability and data confidentiality.

:::sources
- [Memphis Courier](https://memphiscourier.com.br/)
- [Memphis Courier — Quem Somos](https://memphiscourier.com.br/quem-somos/)
- [Memphis Courier — Serviços Adicionais](https://memphiscourier.com.br/servicos-adicionais/)
- [Memphis Courier — Fale Conosco](https://memphiscourier.com.br/fale-conosco/)
- [universal-parcel-scraper Memphis Courier adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/memphis-courier)
:::
