---
title: Liccardi Express Courier tracking: LDV codes & support
description: Track a Liccardi Express Courier parcel in Italy: 13-digit 660 barcode or LDV lookup, Italian status codes, IN GIACENZA holds, 24–48 h transit and contacts.
slug: liccardi-tracking
published: 2026-11-23
updated: 2026-11-27
---

**Liccardi Express Courier** (*F.lli Liccardi S.r.l.*) is an Italian national express courier headquartered in Casandrino, near Naples ([liccarditrasporti.com](https://www.liccarditrasporti.com/)). Active in its current form since 1992 from a 1960s family business, it handles over 5.9 million B2C and e-commerce shipments a year across more than 20 Italian branches and hubs. Track a parcel on Liccardi's website or paste your 13-digit `660` label code or 7- to 8-digit *lettera di vettura* (LDV) into the box above to see Italian branch codes, locker drop-offs and *giacenza* holds.

## Liccardi Express Courier tracking numbers (`660` label & LDV)

Liccardi identifies every shipment with two numeric references — a 13-digit parcel label starting with `66` (typically `660`) and a shorter 7- or 8-digit shipment number (*lettera di vettura* / LDV):

| Format (invented) | What it is |
| --- | --- |
| `6604829173605` | 13-digit Liccardi parcel barcode (`660` + 8-digit shipment number + 2 check/parcel digits). |
| `48291736` | 7- or 8-digit Liccardi shipment number (*LDV* / *lettera di vettura*). |

Check your retailer's dispatch email or Liccardi delivery notification for either code; both resolve to the same shipment in Liccardi's system. See [tracking number formats](guide:tracking-number-formats).

## What Liccardi Express Courier tracking statuses mean

Liccardi's tracker publishes numeric outcome codes (*codici esito*) alongside Italian branch descriptions. For multi-parcel shipments (*colli*), Liccardi records events per parcel; Peek keeps the shipment active until every parcel in the shipment is delivered:

| Liccardi says (code) | What it means for you | Peek shows |
| --- | --- | --- |
| `DATI SPEDIZIONE TRASMESSI A LICCARDI` (`50`), `SERVIZIO PROGRAMMATO` (`58`), `RITIRO ASSEGNATO AD AUTISTA` (`36`), `IN RITIRO` (`92`) | Sender transmitted the shipment data or a driver pickup is scheduled/under way before intake. | "Label created" |
| `SPEDIZIONE RITIRATA` (`37`), `COLLO PRESO IN CARICO` (`3`), `SPEDIZIONE PRESA IN CARICO` (`4`) | Parcel collected from the sender and accepted into Liccardi's network. | "Received by carrier" |
| `ARRIVATO IN FILIALE` (`47`), `IN TRANSITO` (`48`), `SPEDIZIONE PARTITA` (`91`), `ARRIVO SU FILIALE DI DESTINO` (`72`), `IN GESTIONE` (`49`), `GIACENZA SVINCOLATA` (`70`), `IN PRENOTAZIONE` (`33`) | Moving between hubs, arrived at the destination branch, booking an appointment or released from a hold. | "In transit" |
| `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`), `IN CONSEGNA` (`52`) | Loaded onto the local delivery van and out for delivery today. | "Out for delivery" |
| `CONSEGNATA AL PUDO` (`6`), `CHECK-IN AL PUDO` (`80`), `SPEDIZIONE DEPOSITATA NEL LOCKER` (`11`), `FERMO DEPOSITO` (`32`) | Ready to collect at a PUDO shop, parcel locker or local Liccardi branch (*fermo deposito*). | "Ready for pickup" |
| `ASSENTE` (`28`), `INDIRIZZO ERRATO` (`40`), `CONSEGNA NON ESEGUITA` (`43`), `MANCA CONTRASSEGNO` (`44`), `INDIRIZZO NON TROVATO` (`45`), `CHIUSO` (`46`), `GIRO DI CONSEGNA NON ULTIMATO` (`67`) | Delivery could not be completed (recipient absent, address error, COD not ready, closed or unfinished round). | "Delivery attempted" |
| `IN GIACENZA` (`35`), `COLLO DANNEGGIATO` (`30`), `MERCE RESPINTA` (`42`), `SPEDIZIONE SMARRITA` (`54`), `COLLO DISGUIDATO` (`68`), `ORDINE SOSPESO: IN ATTESA DI ISTRUZIONI MITTENTE` (`76`), `DISCONOSCIMENTO FIRMA` (`103`), `CONTATTARE ASSISTENZA` (`107`) | Parcel held in storage awaiting sender release (*giacenza*), damaged, refused, misrouted, lost or needing support. | "Needs attention" |
| `RIENTRO AL MITTENTE` (`31`), `IN CONSEGNA AL MITTENTE` (`53`), `RESO A MITTENTE` (`8`) | Heading back or returned to the sender. | "Returned to sender" |
| `SPEDIZIONE CONSEGNATA` (`1`), `SPEDIZIONE CONSEGNATA A COMMITTENTE` (`5`), `SPEDIZIONE RITIRATA DA LOCKER DAL DESTINATARIO` (`12`) | Delivered to the recipient or collected from the parcel locker. | "Delivered" |

Note that Liccardi's own progress bar treats locker drop-offs (`11`) as delivered, whereas Peek maps PUDO, locker and *fermo deposito* scans to **"Ready for pickup"** until the recipient actually collects the parcel (`12`).

## Delivery times, services and Italian branch network

According to [Liccardi Services](https://www.liccarditrasporti.com/servizi/), [About Us](https://www.liccarditrasporti.com/azienda/) and [Branches](https://www.liccarditrasporti.com/filiali/):

- **Transit times:** Liccardi collects orders from merchants within 48 hours, weighs/measures/photographs each parcel at its sorting hubs and advertises nationwide delivery within **24–48 hours** (statistically covering its full national service range within **72 hours**, with remote areas — *località disagiate* — taking longer and seasonal peaks affecting standard times).
- **Delivery options:** standard B2C express, floor delivery (*consegne al piano*), timed appointments (*consegne tassative*), same-day delivery, cash on delivery (*contrassegno*), branch pickup (*fermo deposito*), swap/reverse pickups and optional supplementary insurance.
- **Branch network:** 20+ operational hubs and depots across Italy, including central hub Casandrino/Naples (`NA1`), Rome (`RM1`, `RM3`), Milan (`MI1`), Bologna (`BO1`), Turin (`TO2`), Florence (`FI1`), Verona (`VR1`), Bari (`BA1`), Salerno (`SA1`), Avellino (`AV1`), Frosinone (`FR1`), Latina (`LT1`), Modena (`MO1`), Parma (`PR1`), Foggia (`FG1`), Lecce (`LE2`), Matera (`MT1`), Potenza (`PZ1`), Cosenza (`CS1`), Catanzaro (`CZ1`) and Reggio Calabria (`RC1`). When another courier takes a parcel on, Liccardi's tracker can show that courier's waybill (LDV).

## Missed delivery, *giacenza* holds and claims

- **`IN GIACENZA` (`35`) or failed delivery (`28`, `40`, `45`):** after an unsuccessful attempt or address issue, Liccardi places the shipment in *giacenza* (storage hold) and waits for release instructions (*svincolo giacenza*) from the **sender**. Contact your online retailer first so they can issue release instructions, or use the recipient portal at [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) to update delivery instructions, report an address error or request branch collection (*ritiro in filiale*).
- **Disputing an outcome (`Contestazione Esito`) or missing parcel:** Liccardi's recipient claims guide lets you report an incorrect status, a "recipient absent" scan while you were home, or a parcel marked delivered that you did not receive, and provides downloadable *Modulo reclamo*, *Modulo conciliazione* and *Formulario CP* forms. Always alert your seller too. See [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel), [returned to sender](guide:returned-to-sender) and [why tracking stops updating](guide:tracking-not-updating).

## Liccardi Express Courier customer service and contacts

Official contact details published on [Liccardi Contatti](https://www.liccarditrasporti.com/contatti/) and [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/):

- **Toll-free number (*Numero Verde* in Italy):** `800 029 764` (`800029764`).
- **Telephone:** `+39 081 5053642` (fax `081 5053071`).
- **Customer service email:** `assistenzaclienti@liccarditrasporti.com` (general inquiries: `info@liccarditrasporti.com`; PEC: `flliliccardi@legalmail.it`).
- **Recipient support & claims portal:** [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).
- **Headquarters & central hub:** *F.lli Liccardi S.r.l.* (P.IVA `06588280633`), Via Paolo Borsellino 133 (registered office 123), 80025 Casandrino (NA), Italy.

> Beware of fake text messages asking for a small online payment to reschedule an Italian courier delivery. Verify any delivery update directly with your merchant or via Liccardi's official website and customer service channels. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Liccardi Express Courier parcel with Peek

When you enter a 13-digit `660` barcode (`6604829173605`, invented), [Peek](/) asks Liccardi Express Courier first, wherever you track from except Russia, where it asks Ozon first, and shows "Liccardi Express Courier has this parcel." once Liccardi's tracker confirms it. Peek also recognises a pasted `my.liccarditrasporti.com/segui?ldv=` link as Liccardi's. A 7- or 8-digit LDV (`48291736`, invented) points to no carrier on its own: paste the link, or tap "Change" while adding the parcel or "Change carrier" on its page and pick **Liccardi Express Courier** (searching "Liccardi Trasporti", "Liccardi Express" or "F.lli Liccardi" finds it too).

Peek reads Liccardi's tracker and shows the stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Ready for pickup", "Delivery attempted", "Needs attention", "Returned to sender", "Delivered"), Liccardi's Italian wording and the branch. The `codEsito` codes set the stage; no Peek app shows them. Peek dates the scans on Italian time and shows them on your device's clock. It also shows the estimated delivery day when Liccardi gives one (without the time slot), the delivery time once delivered, and the summary status before the first parcel scan. When Liccardi gives another courier's waybill and Peek recognises that courier, Peek checks that it has the parcel: the parcel then shows "Delivery with" and the courier's name, and lists both numbers. For a shipment of several parcels, Peek merges repeated branch scans within a minute and keeps the shipment open until every parcel is delivered. Peek does not show the recipient's town or postcode, drivers' names or proof-of-delivery signatures.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day Liccardi shows `CARICO VETTURA` (`51`), `CARICO VETTURA CONFERMATO` (`95`) or `IN CONSEGNA` (`52`), every 2 minutes from 6 a.m. Italian time, while Liccardi's own tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When Liccardi's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Liccardi Express Courier.", with a "Get help from Liccardi Express Courier" link to Liccardi's recipient assistance and claims page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Liccardi Express Courier."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot release a *giacenza* hold, change a delivery address or contact Liccardi Express Courier for you.

## Questions about Liccardi Express Courier tracking

### What does a Liccardi tracking number look like?

Liccardi identifies shipments with either a 13-digit label barcode starting with `66` (typically `660`, such as `6604829173605`, invented) or a 7- to 8-digit shipment number (*lettera di vettura* / LDV, such as `48291736`, invented). Both point to the same shipment.

### What does `IN GIACENZA` mean on Liccardi tracking?

`IN GIACENZA` (code `35`) means the parcel is held at a Liccardi branch after a missed delivery or address issue and requires release instructions (*svincolo giacenza*) from the sender. Contact your online shop first and submit updated delivery details or request branch pickup through [Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

### Why does Liccardi show delivered when my parcel is in a locker?

Liccardi's own progress bar treats locker drop-off (`SPEDIZIONE DEPOSITATA NEL LOCKER`, code `11`) as delivered before pickup. Peek maps locker, PUDO and *fermo deposito* scans to **"Ready for pickup"** until collection is scanned (`SPEDIZIONE RITIRATA DA LOCKER DAL DESTINATARIO`, code `12`).

### How long does Liccardi Express Courier take to deliver in Italy?

Liccardi advertises **24–48 hour** national delivery after pickup (statistically within **72 hours** across its full Italian network), with longer transit possible for remote areas (*località disagiate*) and seasonal peaks.

### How do I contact Liccardi Express Courier customer service?

Call the Italian toll-free *Numero Verde* `800 029 764` or `+39 081 5053642`, email `assistenzaclienti@liccarditrasporti.com`, or submit a recipient support request or dispute (*Contestazione Esito*) at [liccarditrasporti.com/assistenza-reclami-destinatari/](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/).

:::sources
- [Liccardi Express Courier: Official Website](https://www.liccarditrasporti.com/) – *F.lli Liccardi S.r.l.*, 20+ branches, 5.9M+ annual shipments, Casandrino (NA) HQ and contacts
- [Liccardi Express Courier: Azienda](https://www.liccarditrasporti.com/azienda/) – company history since 1992 (origin 1960s), B2C expansion from 2004 and Unione Industriali membership
- [Liccardi Express Courier: Servizi](https://www.liccarditrasporti.com/servizi/) – 24/48 h (up to 72 h) delivery, 48 h pickup, *contrassegno*, *fermo deposito* and automated photo-sorting
- [Liccardi Express Courier: Filiali](https://www.liccarditrasporti.com/filiali/) – full list of 20+ Italian hubs and branches (NA1, RM1, MI1, BO1, TO2, FI1, VR1, BA1, etc.)
- [Liccardi Express Courier: Contatti](https://www.liccarditrasporti.com/contatti/) – `800 029 764`, `+39 081 5053642`, emails, PEC and Casandrino address
- [Liccardi Express Courier: Assistenza / Reclami Destinatari](https://www.liccarditrasporti.com/assistenza-reclami-destinatari/) – recipient support categories, dispute options and claim/conciliation forms (help URL linked by Peek)
- [Universal Parcel Scraper: liccardi](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/liccardi) – `660`/LDV number rules, `my.liccarditrasporti.com` link rule, full `codEsito` status map, multi-parcel handling, estimated delivery day and partner LDV extraction
:::
