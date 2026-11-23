---
title: Anjun Logistics tracking: China-to-Brazil statuses and SAC
description: Track an Anjun Logistics or Anjun Express parcel: AJ tracking numbers, China and Brazil status lines, Remessa Conforme customs clearance and SAC contacts.
slug: anjun-logistics-tracking
published: 2026-11-23
updated: 2026-11-23
---

Anjun Logistics (operating in Brazil as Anjun Express) is a cross-border and domestic e-commerce carrier founded in 2011 in China that entered Brazil in 2019 and expanded to nationwide Brazilian delivery in 2025. It handles first-mile collection in China, air export, customs clearance in Brazil and last-mile delivery across the country — either through its own delivery network and collection points or by handing parcels over to [Correios](carrier:correios-br) after import clearance. You can check international shipments on [Anjun's global tracker](https://anjuntrack.com/trajectory), check Brazilian domestic shipments on [Anjun Express Brazil](https://anjunexpress.com/trackPackage), or paste the tracking number into the box above.

## Anjun tracking number: format and where to find it

Your tracking number appears in the marketplace or online store's dispatch notification (including international orders from Chinese platforms shipping to Brazil). Anjun's own numbers have 17 characters: `AJ`, a 6-digit date (`YYMMDD`) and 9 digits.

| Format (invented) | What it is |
| --- | --- |
| `AJ261015482917364` | Anjun Logistics / Anjun Express code: `AJ` + 6-digit date + 9 digits. |

Anjun runs two tracking portals: [anjuntrack.com](https://anjuntrack.com/trajectory) covers China-to-Brazil cross-border consignments, while [anjunexpress.com](https://anjunexpress.com/trackPackage) focuses on Brazilian last-mile and domestic parcels. If your order instead has a 13-character `BR` code, check [SPX Express Brazil](carrier:spx-br); a 15-digit `8880` code belongs to [J&T Express Brazil](carrier:j-and-t-br); and a 13-character postal code ending in `BR` belongs to [Correios](carrier:correios-br). See [tracking number formats](guide:tracking-number-formats) to identify other prefixes.

## What Anjun Logistics tracking statuses mean

Cross-border Anjun parcels usually switch from English scan lines in China and Hong Kong to Portuguese lines once they reach Brazil.

| Anjun says | What it means for you | Peek shows |
| --- | --- | --- |
| `Objeto postado` | Electronic pre-advice or posting recorded before warehouse intake. | "Label created" |
| `The goods have arrived at Anjun`, `Objeto coletado` | Anjun received the parcel at its origin warehouse or collected it in Brazil. | "Received by carrier" |
| `The goods have been issued`, `Departed Facility in SHENZHEN`, `Arrived at Sort Facility HONG KONG INTERNATIONAL AIRPORT`, `Departed Facility in HONG KONG INTERNATIONAL AIRPORT`, `Arrived at Sort Facility Destination Airport` | Export processing, flight departure from Shenzhen or Hong Kong and arrival at the Brazilian destination airport. | "In transit" |
| `Recebimento de cargas no armazém courier`, `Entrada no armazém alfândegado de TFK`, `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes`, `Análise concluída - importação autorizada` | Customs bonded warehouse intake, customs declaration submission and import clearance in Brazil. | "At customs" |
| `Pacote Liberado Aguadando a Coleta de Last Mile`, `Saída do Centro Internacional`, `Coletado por Last Mile`, `Objeto recebido pelos Correios do Brasil` | Released from customs and handed to Anjun's last-mile network or to Correios for domestic delivery. | "In transit" |
| `O pacote chegou ao centro de trânsito`, `O pacote saiu do centro de trânsito`, `Objeto chegou ao ponto de entrega` | Moving through Brazilian transit hubs and reaching the local delivery station (not out for delivery yet). | "In transit" |
| `Objeto saiu para entrega ao destinatário` | A local courier is out delivering your parcel today. | "Out for delivery" |
| `A entrega falhou` (for example `Cliente ausente` or `Endereço fora da rota`) | Delivery could not be completed (recipient absent or address off route). | "Delivery attempted" |
| `A entrega falhou: Pacote não pertence à Base` | The parcel reached the wrong local base and is being rerouted rather than attempted at your door. | "In transit" |
| `O pacote está a caminho do centro de trânsito para devolução.` | The parcel is moving back to a transit centre to be returned. | "Needs attention" |
| `O pacote foi assinado`, `Objeto entregue ao destinatário` | Anjun recorded the parcel as signed for and delivered. | "Delivered" |

Note that `Objeto chegou ao ponto de entrega` means the parcel reached Anjun's local delivery depot — not a customer pickup counter — so wait for `Objeto saiu para entrega ao destinatário` before expecting the driver.

## Anjun Express Brazil network and delivery

On [Anjun Express's Brazilian website](https://anjunexpress.com/home), the company describes its services across first-mile collection, international customs clearance, [last-mile delivery](https://anjunexpress.com/lastMile), B2B logistics, warehousing and Super Express. Its homepage cites more than 1,000 collection points, over 350 vehicles and over 2,000 staff (its [collection-point partner page](https://anjunexpress.com/collectionPoint) cites more than 500 points).

Anjun Express does not publish a fixed number of delivery attempts, guaranteed delivery timeframe or parcel-holding period on its public pages, because those conditions vary by marketplace contract. For exact deadlines or delivery-attempt rules on an online purchase, check the order page in the store or marketplace where you bought the item.

## Customs clearance and Remessa Conforme in Brazil

Because most Anjun parcels arrive in Brazil from China, they clear Brazilian import customs under [Receita Federal's Programa Remessa Conforme (PRC)](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona):

- **Certified marketplace or store:** taxes are paid at checkout. Consignments to individuals up to US$50 (goods, freight and insurance) carry 0% federal Import Duty plus 17% to 20% state ICMS. Above US$50, Import Duty is 60% with a US$30 discount on the tax amount, plus 17% to 20% ICMS.
- **Non-certified store:** 60% federal Import Duty with no discount (even up to US$50) plus 17% to 20% ICMS, billed when the parcel enters Brazil before release.

While your parcel is at `Entrada no armazém alfândegado de TFK` or `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes`, Receita Federal or partner regulatory agencies are inspecting the declaration. If customs requests documents or payment, check your marketplace order messages and Anjun's customs contact rather than paying through random links. See [parcels held at customs](guide:customs).

## Anjun parcel stuck, failed delivery or missing

For purchases from online marketplaces, the platform or seller is responsible for refunds, reshipments and customs disputes.

**No scan between China and Brazil.** Air export, airport queues and bonded warehouse intake commonly create gaps of several days between `Departed Facility in HONG KONG INTERNATIONAL AIRPORT` and `Recebimento de cargas no armazém courier`. See [why tracking stops updating](guide:tracking-not-updating).

**Misrouted to the wrong base.** `A entrega falhou: Pacote não pertence à Base` looks alarming, but it means the parcel was sorted to the wrong local depot and needs internal transfer before an actual delivery attempt at your address.

**Delivered but not received.** Check with relatives, neighbours and your building's reception (*portaria*) first, then report it to the marketplace and Anjun's SAC. See [delivered but not received](guide:delivered-not-received).

**Damaged, lost or returning.** Keep photos of damaged packaging and contact the seller promptly; see [damaged parcel](guide:damaged-parcel) and [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## Anjun Express customer service and new email address

On [anjunexpress.com/home](https://anjunexpress.com/home), Anjun Express announces that its old email `sac@anjunexpress.com.br` was deactivated from 12 August 2026 and lists its active Brazilian support channels:

- **National customer service (SAC) phone:** `(11) 5026-7008`, Monday to Friday from 08:00 to 17:30 (excluding holidays).
- **SAC email:** `sac@anjun.com.br`.
- **Online chat:** available on [anjunexpress.com](https://anjunexpress.com/home).
- **International express / customs enquiries:** `atendimento.aduaneiro@anjun.com.br`.
- **International customs ombudsman (*Ouvidoria*):** `ouvidoria.aduaneira@anjun.com.br`.
- **Consumer mediation:** if a dispute with a participating company stays unresolved, Brazilian consumers with a silver or gold gov.br account can use [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1), where participating companies commit to reply within 10 days.

> Watch out for fake customs payment messages sent by SMS or WhatsApp claiming your Anjun parcel is blocked. Verify any customs notice inside the marketplace where you ordered or directly with `atendimento.aduaneiro@anjun.com.br` before paying. See [delivery scam texts](guide:delivery-scam-texts).

## Track an Anjun Logistics parcel with Peek

[Peek](/) identifies Anjun Logistics automatically from any 17-character `AJ` tracking number (with a valid `YYMMDD` date) and from pasted `anjuntrack.com` (`/trackQuery?t=` or `/trajectory?orderNum=`) or `anjunexpress.com/trackPackage?tracking=` links carrying an `AJ` code. If you ever need to switch carriers manually, tap "Change" while adding or "Change carrier" on the parcel view and select Anjun Logistics.

Peek checks `anjuntrack.com` first for cross-border history and, if that portal has no scans yet, automatically checks `anjunexpress.com` as well so domestic Brazilian parcels appear without extra steps. Peek displays the stage ("Label created", "Received by carrier", "In transit", "At customs", "Out for delivery", "Delivery attempted", "Needs attention", "Delivered"), English and Portuguese scan lines, any failed-delivery reason (such as `Cliente ausente`), the town and state in Brazil (street addresses are stripped), the destination country and local wall-clock times (China time in China, Brazil time in Brazil, shown in your device's time zone). Peek drops the site's progress bar, recipient street address, GPS coordinates, driver name, support phone/email text inside scan notes, signatures and photos; estimated delivery dates, parcel weights and any downstream local handoff number are not provided by Anjun's tracker.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day when alerts are on, and up to every 2 minutes from 6:00 a.m. local time on the day `Objeto saiu para entrega ao destinatário` appears. Otherwise it checks hourly, stays hourly while open once a parcel has spent 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day quiet, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the delay (for example "No tracking update since Mon, Oct 12") and displays "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Anjun Logistics." After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Anjun Logistics."

You can pick notification presets for "Every scan", "Important steps" or "Delivery only". Peek tracks updates only and cannot clear customs or contact Anjun Express for you.

## Questions about Anjun Logistics tracking

### Why is my Anjun parcel tracking showing both English and Portuguese?

Cross-border shipments start at Anjun's Chinese warehouses and Hong Kong export hubs, which log English scans, and switch to Portuguese once the consignment reaches Brazil for customs clearance and local delivery.

### What does `A entrega falhou: Pacote não pertence à Base` mean?

It means the parcel arrived at the wrong local Anjun delivery base by mistake, not that a driver missed you at home. Anjun reroutes it to the right base, so Peek keeps it in "In transit".

### Is `sac@anjunexpress.com.br` still active?

No. Anjun Express deactivated `sac@anjunexpress.com.br` from 12 August 2026. Use `sac@anjun.com.br`, call `(11) 5026-7008` (Mon–Fri, 08:00–17:30) or email `atendimento.aduaneiro@anjun.com.br` for customs issues.

### Can Anjun hand my parcel over to Correios?

Yes. Some cross-border parcels clear customs and show `Objeto recebido pelos Correios do Brasil`, meaning Correios handles final domestic delivery while Anjun's tracker still reports the handoff.

:::sources
- [Anjun Express: Home](https://anjunexpress.com/home) – Brazilian services, network counts, active SAC phone/email, customs/ombudsman emails and `sac@anjunexpress.com.br` deactivation notice (12/08/2026)
- [Anjun Express: About Us](https://anjunexpress.com/aboutUs) – founded 2011, entered Brazil 2019, national Brazil coverage 2025
- [Anjun Express: Track Package](https://anjunexpress.com/trackPackage) – Brazilian tracking portal
- [AnjunTrack: Trajectory](https://anjuntrack.com/trajectory) – China-to-Brazil cross-border tracking portal
- [Anjun Express: Last-Mile](https://anjunexpress.com/lastMile) – Brazilian last-mile delivery service
- [Anjun Express: Collection Points](https://anjunexpress.com/collectionPoint) – partner collection point network
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – Brazilian import duty and ICMS rules
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – public consumer mediation platform (10-day response, silver/gold gov.br account)
- [Universal Parcel Scraper: anjun](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anjun) – `AJ` format detection, dual-portal lookup, status mapping, stripped personal fields and refresh cadence
:::
