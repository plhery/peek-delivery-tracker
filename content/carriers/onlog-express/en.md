---
title: Onlog Express tracking: AA codes, Brazil statuses & help
description: Track an Onlog Express parcel in Brazil: AA 12-character tracking codes, distribution centre scan meanings, scam fee warnings and São Paulo customer support.
slug: onlog-express-tracking
published: 2026-11-23
updated: 2026-11-23
---

**Onlog Express** (*Onlog Franqueadora*) is a Brazilian parcel-delivery and logistics network headquartered in São Paulo with over 20 years of logistics-partnership experience and more than 18 million parcels delivered across Brazil ([onlogexpress.com.br](https://onlogexpress.com.br/)). Connecting e-commerce merchants and businesses to nationwide delivery operators across service tiers ranging from urgent minute-level and same-day dispatch (*Entrega em Minutos*, *Entrega no Dia*) to priority (*Expresso D1*) and economy (*Econômico D2*) alongside fulfillment and international freight, Onlog provides public tracking by object code or CPF/CNPJ at [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php). You can check a shipment there or paste your 12-character `AA` tracking code into the box above.

## Onlog Express tracking numbers (`AA` format)

Onlog Express public guest tracking uses a 12-character object code starting with `AA` followed by 10 digits:

| Format (invented) | What it is |
| --- | --- |
| `AA4829173605` | 12-character Onlog Express object code (`AA` + 10 digits). |
| `AA7391846208` | Alternative 12-character `AA` Onlog Express parcel reference. |

Check your retailer's dispatch email or `onlog.app.br/rastreio/...` tracking link for the `AA` reference. If you do not have the code, Onlog's web portal at [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) also lets recipients search using their Brazilian CPF or CNPJ (and includes a separate lookup option for objects prior to 01/01/2025). See [tracking number formats](guide:tracking-number-formats).

## What Onlog Express tracking statuses mean

Onlog Express publishes Portuguese scan descriptions from label creation and collection through distribution-centre transfers and recipient signature:

| Onlog Express says (PT-BR) | What it means for you | Peek shows |
| --- | --- | --- |
| `OBJETO CRIADO`, `ETIQUETA EMITIDA` | Shipment created and label issued electronically before physical pickup. | "Label created" |
| `OBJETO POSTADO`, `COLETA DE ENCOMENDA` | Parcel posted at an Onlog unit or collected from the sender. | "Received by carrier" |
| `OBJETO EM TRANSFERÊNCIA`, `ENTRADA NO CENTRO DE DISTRIBUIÇÃO`, `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`, `BIPE DE EXPEDIÇÃO`, `BIPE DE RECEBIMENTO` | Received at a distribution centre, dispatched between facilities or forwarded to the regional logistics operator. | "In transit" |
| `OBJETO SAIU PARA ENTREGA AO DESTINATÁRIO`, `BIPE DE SAÍDA PARA ENTREGA` | Out with the local delivery courier for final doorstep delivery today. | "Out for delivery" |
| `OBJETO ENTREGUE AO DESTINATÁRIO`, `ASSINATURA DE ENCOMENDA` | Delivered and signed for at the destination address. | "Delivered" |

Because Onlog connects orders across partner operators in Brazil (similar to multi-operator networks alongside carriers like [Jadlog](carrier:jadlog), [J&T Express Brazil](carrier:j-and-t-br) and [Anjun Logistics](carrier:anjun)), `ENCAMINHADO PARA O OPERADOR LOGÍSTICO` simply means your parcel has moved onto the regional partner handling the next leg.

## Delivery services and official scam-fee warning

According to [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Service tiers:** **Entrega em Minutos** (urgent), **Entrega no Dia** (same-day), **Expresso D1** (priority transit) and **Econômico D2** (cost-effective), with exact transit times and availability depending on origin and destination ZIP codes (CEPs).
- **Important anti-scam notice:** Onlog publishes an explicit warning on its homepage stating that **Onlog does not request fee payments by email, WhatsApp or phone**. Never pay a tax or release fee demanded through unsolicited messages claiming to be from Onlog. See [delivery scam texts](guide:delivery-scam-texts).

## Onlog Express parcel delayed, quiet or missing

- **Quiet gap after `ENCAMINHADO PARA O OPERADOR LOGÍSTICO`:** scans commonly pause between regional hubs or while transferring to the last-mile delivery partner across Brazilian states. See [why tracking stops updating](guide:tracking-not-updating).
- **Delivered without a receipt or missing parcel:** check with building reception (*portaria*), family members and neighbours first, then contact the online retailer where you placed the order and reach out to Onlog's São Paulo support team during business hours with your `AA` tracking number. See [delivered but not received](guide:delivered-not-received), [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

## Onlog Express customer service and São Paulo contacts

Official contact channels published on [onlogexpress.com.br](https://onlogexpress.com.br/):

- **Telephone (São Paulo):** `(11) 5662-8108` and `(11) 5663-3829`.
- **Email:** `atendimento@onlogexpress.com.br`.
- **Support hours:** Monday to Friday, `08:00` to `18:00` BRT (closed Saturdays and Sundays).
- **Headquarters (*Matriz São Paulo*):** Rua Estados Unidos, 2031, Jardim América, São Paulo – SP, CEP `01427-002`, Brazil.
- **Online tracker:** [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php).

> Remember Onlog's official warning: Onlog never charges release fees by email, WhatsApp or telephone. Ignore and report any message demanding a PIX or card payment to unlock a parcel. See [delivery scam texts](guide:delivery-scam-texts).

## Track an Onlog Express parcel with Peek

When you enter a 12-character `AA` tracking code (`AA4829173605` or `AA7391846208`, invented), [Peek](/) checks Onlog Express automatically as the preferred candidate and shows `"Onlog Express has this parcel."` once Onlog confirms it; Peek also recognises pasted `onlog.app.br/rastreio/AA...` tracking links directly. If you ever need to choose Onlog manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "ONLOG" or "Onlog Franqueadora" finds it too).

Peek queries Onlog Express via its browser-backed tracker (minimum refresh interval 30 minutes, 60 minutes after a failed check) and displays the mapped stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Delivered"), exact Portuguese scan descriptions, scan locations and minute-precision local wall-clock timestamps as printed by Onlog (without a time-zone offset). Onlog's guest tracker does not supply an estimated delivery date, weight, event codes or separate delivery timestamp.

Peek checks up to every 30 minutes while you have the parcel open or on delivery day with alerts on (60 minutes after a failed attempt, respecting Onlog's refresh floor). Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12") and shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Onlog Express." After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Onlog Express."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot look up parcels by CPF/CNPJ, edit delivery addresses or contact Onlog Express for you.

## Questions about Onlog Express tracking

### What does an Onlog Express tracking number look like?

Onlog Express tracking codes start with `AA` followed by 10 digits (12 characters total, for example `AA4829173605`, invented).

### Can I track an Onlog Express parcel using my CPF or CNPJ?

Yes, on Onlog's own website at [conecta.log.br/rastreio.php](https://conecta.log.br/rastreio.php) you can switch to the **POR CPF/CNPJ** tab if you do not have the `AA` tracking code. Peek uses the `AA` object code only.

### I received a WhatsApp or email asking for a fee from Onlog — is it real?

No. Onlog states explicitly on [onlogexpress.com.br](https://onlogexpress.com.br/) that it **never** requests fee payments via email, WhatsApp or phone. Do not pay.

### How do I contact Onlog Express customer service?

Call `(11) 5662-8108` or `(11) 5663-3829`, or email `atendimento@onlogexpress.com.br` Monday through Friday from 08:00 to 18:00 BRT.

:::sources
- [Onlog Express: Official Website](https://onlogexpress.com.br/) – service tiers (*Entrega em Minutos*, *Entrega no Dia*, *Expresso D1*, *Econômico D2*), 18M+ parcels, 20+ years, anti-fee-scam notice and São Paulo HQ contacts
- [Onlog Express: Official Tracker (Conecta)](https://conecta.log.br/rastreio.php) – public tracking by object code or CPF/CNPJ
- [Universal Parcel Scraper: onlog-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/onlog-express) – `AA\d{10}` format, `onlog.app.br/rastreio/` link rule, 13-phrase Portuguese status map, browser-backed lookup (30/60 min refresh) and local wall clocks
:::
