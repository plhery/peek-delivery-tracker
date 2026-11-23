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

`ENCAMINHADO PARA O OPERADOR LOGÍSTICO` means the parcel was forwarded to the logistics operator handling its next leg.

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

When you enter a 12-character `AA` code (`AA4829173605` or `AA7391846208`, invented), [Peek](/) asks [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) and [Aramex New Zealand](carrier:aramex-nz), then the tracking services, then Onlog Express, whose tracker it opens in a browser, and shows "Onlog Express has this parcel." once Onlog confirms it. Peek also recognises pasted `onlog.app.br/rastreio/AA...` links as Onlog's. If you ever need to choose Onlog yourself, tap "Change" while adding or "Change carrier" on the parcel screen (searching "ONLOG" or "Onlog Franqueadora" finds it too).

Peek reads Onlog's tracker in a browser and shows the stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Delivered"), the Portuguese scan wording and the scan's place. Onlog gives each scan a clock to the minute without an offset: Peek dates it on its place's time zone (São Paulo's for a scan in São Paulo, Manaus's for Manaus), a scan without a place on the zone of the scan beside it, or else on São Paulo's, and shows them all on your device's clock. Onlog's tracker gives no estimated delivery day, weight, event codes or delivery time.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 30 minutes, the least time Onlog allows between two checks, and an hour after a failed check; it makes no 2-minute checks on the out-for-delivery day. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When Onlog's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 30 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Onlog Express." After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Onlog Express."

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
- [Universal Parcel Scraper: onlog-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/onlog-express) – `AA\d{10}` format, `onlog.app.br/rastreio/` link rule, 13-phrase Portuguese status map, browser lookup at most every 30 minutes (60 after a failure) and the scan clocks
:::
