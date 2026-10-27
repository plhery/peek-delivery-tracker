---
title: CTT tracking: numbers, statuses and pickup in Portugal
description: Track a CTT parcel in Portugal: where the tracking number comes from, what CTT's statuses mean, pickup deadlines, customs fees and how to reach CTT.
slug: ctt-tracking
published: 2026-10-11
updated: 2026-10-27
---

CTT (CTT – Correios de Portugal) is Portugal's national postal service, with an express arm, CTT Expresso. Track a parcel with "Seguir objeto" on [ctt.pt](https://www.ctt.pt/feapl_2/app/open/objectSearch/objectSearch.jspx?request_locale=pt), which has an English version, in the CTT app, or by pasting the number in the box above. The number reaches you by CTT's SMS or email, on a paper notice, or from the sender.

## CTT tracking number: what it looks like

Most CTT codes are two letters for the product, nine digits and two letters for the country that created the code. Some shipments have a numeric code of 22 or 25 digits.

| Shape (invented) | What it is |
| --- | --- |
| `RR000000005PT` | A CTT postal code. The last of the nine digits is a check digit. |
| `RR000000005ES` | Same shape, another country: that country's post created it (here Spain's [Correos](carrier:correos-spain)). |
| `0012340012345678901234` | 22 digits: on ctt.pt, a shipment that started in Spain. |
| `0012340012345678901234001` | 25 digits. |

The 22- and 25-digit shapes are also [CTT Express](carrier:ctt-express)'s, the Spanish-run courier between Spain and Portugal, with its own tracking on cttexpress.com.

Type the code without spaces: CTT's English help says `Item not found` means the item wasn't identified on its network. No SMS or email with a code from CTT? The delivery probably has none and can't be followed. More in [how to find a tracking number](guide:find-tracking-number).

## What CTT's tracking statuses mean

Seguir objeto has an English and a Portuguese version. These are CTT's Portuguese wordings, from Peek's records and CTT's help page, plus two English ones from CTT's English help page.

| CTT's status | What it means for you |
| --- | --- |
| `Aguarda entrada nos CTT` | CTT has the sender's details, not the parcel. |
| `Aceite` | Accepted by CTT or the origin country's post: the route starts. |
| `Em exportação` | In customs in the origin country. |
| `International dispatch` | The day it left the origin country. |
| `International reception` | The day it entered the destination country. |
| `Em importação` | In customs in the destination country. |
| `Em trânsito` | Moving between centres; it can appear several times. |
| `Em entrega` | Out with the postman or courier today. |
| `Entrega não conseguida` | Delivery failed; look for a notice by letterbox, SMS or email. |
| `No ponto de entrega` | Waiting for you at a pickup point. |
| `Em espera` | Waiting for instructions or a scheduled delivery day. |
| `Entregue` | Delivered. |
| `Devolvido` | Sent back to the sender. |

`Em importação` means Portuguese customs, where you may have to pay (below). `Não entregue` is a failed attempt too.

## CTT parcel not delivered: notices, pickup points and lockers

If nobody is home, what happens depends on what the sender bought: the parcel goes to a pickup point (Loja CTT, Ponto CTT, Cacifo Locky locker or Payshop agent), gets a new attempt, waits for the sender's instructions or goes back. An Encomenda Postal goes straight to a Loja or Ponto CTT unless the sender paid for home delivery.

CTT's notice comes by SMS, email or paper and names the place and deadline. Deadlines, in working days from the day after the notice:

| Item | Days to pick up |
| --- | --- |
| Letters and registered mail | 6 |
| National postal parcel | 10 |
| International postal parcel | 15 |
| Express parcels | 5 (some products 10) |

Postal parcels can be extended up to 30 days. Bring the notice and an ID (Cartão de Cidadão, passport, residence permit or driving licence, among others), or give the number and ID at the counter.

**Locky lockers** keep a parcel 5 days. CTT sends the PIN by SMS or email; collection can take up to 48 hours after tracking shows arrival.

**Express codes starting `D`** (Para Amanhã, Em Dois Dias) can be changed on Seguir objeto or in the CTT app: address, pickup point, date, new attempt or time window. You pay (a new date costs €5.01 plus VAT), and nothing can be changed once the parcel waits at a point.

**CTT Express** makes up to two attempts, 9:00 to 19:00 on weekdays. Its Collectt Express points keep parcels 10 days, its lockers 5.

## CTT parcel stuck, in customs or returned

CTT says some countries share international tracking late or not at all. For a stalled parcel, ask the sender, who bought the delivery from CTT; see [tracking that isn't updating](guide:tracking-not-updating).

**Customs.** For parcels from outside the EU without VAT paid at purchase, you pay VAT plus CTT's €7 clearance fee (plus VAT) up to €150, and VAT, duties and €14 from €150 to €1,000. Since 1 July 2026 purchases up to €150 also pay €3 duty per product type. You have 20 days after arrival in Portugal; then it goes back to the sender for good. See [parcels held at customs](guide:customs).

**Complaints** go through CTT's contact form, the support line or a Loja or Ponto CTT, within one year of acceptance (national) or six months (international). CTT Expresso's conditions give the sender 8 days after delivery to claim for damage and 30 days for loss or delay.

**Returns** can't be cancelled: ask the sender about a reshipment or refund. Parcels that can be neither delivered nor returned go to the Refugo Postal, recoverable between 20 days and 3 months through customer support.

## CTT customer service and contact number

CTT's channels, as read on 11 October 2026:

- **Phone** 21 047 16 16 (from abroad +351 21 047 16 16), working days 8:30 to 19:30, at landline rates. No English-language line is listed.
- **Contact form** on [ctt.pt](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/formulario-de-contacto); CTT replies weekdays 8:30 to 19:30.
- **In person** at any Loja or Ponto CTT.
- **CTT Express** (Spain): 900 841 352 (free), 916 602 200 or ccaweb@cttexpress.com, weekdays 9 to 19.

> CTT warns of fake SMS and emails about missed or suspended deliveries and parcels held until a tax is paid. All CTT links start with www.ctt.pt: don't open other links, and delete the message.

## Tracking CTT parcels with Peek

[Peek](/) recognises CTT's postal codes (two letters, nine digits, `PT`, valid check digit) at once and reads them from CTT's tracking; ctt.pt links with `objects=` work too. With one digit wrong, the check digit no longer fits and Peek suggests Chronopost instead, so check the number. Other CTT codes aren't read from CTT; Peek uses universal tracking services.

Peek shows the status and the newest 20 scans with a rough place (depot or point) on a map, plus CTT's status code. It drops the sender and recipient, and CTT gives no expected delivery day. Times are read on Lisbon time, or Azores time for the islands, and shown in your device's time zone.

CTT Express's 22- and 25-digit numbers (`00`, four digits, `00`, then 14 digits, plus `001` for 25) go to CTT Express, which Peek lists separately: history without places, the expected delivery day, weight, delivery time and Spanish wordings such as `El envío fue entregado correctamente`.

Peek checks every 10 minutes by day, every 2 minutes once out for delivery, and every hour or two overnight, and can notify you when the status changes.

## Questions about CTT tracking

### Where is my CTT package?

The latest line on Seguir objeto tells you: `Em entrega` means today's round, `No ponto de entrega` a pickup point.

### What is a CTT delivery point?

A Loja CTT (post office), Ponto CTT (partner shop), Cacifo Locky locker or Payshop agent. CTT's notice names the one holding your parcel.

### Does CTT customer service speak English?

CTT lists no English-language line. Call +351 21 047 16 16 on working days, 8:30 to 19:30, or use its contact form.

### Is CTT Express the same as CTT?

They track separately: CTT Express, a Spanish-run courier for Spain and Portugal, on cttexpress.com; CTT's postal and Expresso parcels on ctt.pt.

:::sources
- [CTT: Results in Seguir objeto (English)](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/resultados-que-podem-aparecer-na-pesquisa-do-seguir-objeto?language_id=1) – English statuses, Item not found
- [CTT: Resultados do Seguir objeto](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/resultados-que-podem-aparecer-na-pesquisa-do-seguir-objeto) – status meanings
- [CTT: Seguir objeto](https://www.ctt.pt/feapl_2/app/open/objectSearch/objectSearch.jspx?request_locale=pt) – tracker, languages
- [CTT: Como seguir uma entrega](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/como-seguir-uma-entrega) – app
- [CTT: Encontrar o código de envio](https://www.ctt.pt/ajuda/empresas/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – number shapes
- [CTT: Não tenho código de envio](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/alterar/a-entrega-vem-a-caminho-mas-nao-tenho-codigo-de-envio-o-que-posso-fazer) – no code
- [CTT: Entrega não conseguida](https://www.ctt.pt/ajuda/particulares/receber/problemas-na-entrega/entrega-nao-conseguida) – missed deliveries
- [CTT: O que precisa de levar consigo](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/o-que-precisa-de-levar-consigo) – ID
- [CTT: Locky](https://www.ctt.pt/ajuda/particulares/receber/cacifos-locky/como-levantar-uma-encomenda) – lockers
- [CTT: Prazo para levantar](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/prazo-para-levantar) – pickup deadlines
- [CTT: Alterar uma entrega Expresso](https://www.ctt.pt/particulares/receber/seguir-ou-alterar-uma-entrega/alterar-uma-entrega-expresso) – delivery changes
- [CTT: Serviços cobrados](https://www.ctt.pt/ajuda/particulares/importacoes-e-desalfandegamento/taxas-e-servicos-cobrados/servicos-cobrados) – customs fees
- [CTT: Isenção de direitos](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia) – €3 duty
- [CTT: Prazo para desalfandegar](https://www.ctt.pt/ajuda/particulares/importacoes-e-desalfandegamento/prazo-de-desalfandegamento/prazo-para-desalfandegar-a-minha-encomenda) – 20 days
- [CTT: Condições Expresso](https://www.ctt.pt/transversais/condicoes-gerais-transporte-encomendas-expresso) – claims
- [CTT: Refugo Postal](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/recuperar-o-correio-que-esta-no-refugo-postal) – unclaimed items
- [CTT: Reclamações](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/reclamacoes) – complaints
- [CTT: Linhas de apoio](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/linhas-de-apoio) – phone, hours
- [CTT: Formulário de contacto](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/formulario-de-contacto) – form
- [CTT: Alertas de phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – fake messages
- [CTT Express: Envíos a Portugal](https://www.cttexpress.com/envios/portugal) – attempts
- [CTT Express: Horarios](https://www.cttexpress.com/preguntas-frecuentes/horarios) – hours
- [CTT Express: Envío y recogida](https://www.cttexpress.com/red-collectt-express/envio-y-recogida) – Collectt points
- [CTT Express: Cómo contactar](https://www.cttexpress.com/preguntas-frecuentes/como-contactar-con-ctt) – contacts
- [Wikipedia: CTT Correios de Portugal](https://en.wikipedia.org/wiki/CTT_Correios_de_Portugal) – national post
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
