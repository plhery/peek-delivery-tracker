---
title: GOFO Italy tracking: GFIT numbers, statuses and complaints
description: Track a GOFO Italy (CIRRO Parcel Italy) parcel: GFIT and CIIT numbers, Italian status translations, missed deliveries, EU 30-day rule and AGCOM complaints.
slug: gofo-italy-tracking
published: 2026-10-11
updated: 2026-11-27
---

GOFO Italy (also listed as CIRRO Parcel Italy, operated by GOFO Italia s.r.l.) is GOFO's domestic delivery network across Italy. Track a `GFIT` or `CIIT` parcel on GOFO Express's Italian tracker on gofo.com, or paste the tracking number in the box above. If you don't have a number yet, see [how to find a tracking number](guide:find-tracking-number).

## GOFO tracking number: what it looks like

These are the shapes Peek files under GOFO Italy, in capitals or not:

| Shape | Invented example |
| --- | --- |
| GFIT and 13 digits | `GFIT4829105736284` |
| GFIT and 14 digits | `GFIT48291057362848` |
| CIIT and 13 digits | `CIIT4829105736284` |
| CIIT and 14 digits | `CIIT48291057362841` |

GOFO's other networks follow the same pattern, with their own country after `GF` or `CI`. `GFFR` and `CIFR` numbers belong to [GOFO France](carrier:gofo-fr), `GFNL` to GOFO Netherlands and `GFES` to GOFO Spain. `GFUS` with 14 digits, `GF` with only 13, or `CR` with 12 belong to [GOFO Express](carrier:gofo) in the United States, a separate network, and `GFCA` with 14 digits to [GOFO Canada](carrier:gofo-ca). A number that starts with `GFIT` but has 12 or 15 digits isn't one Peek files under GOFO Italy: check it for a typo. More on telling shapes apart: [tracking number formats](guide:tracking-number-formats).

## GOFO tracking statuses: what the Italian words mean

Peek reads GOFO's Italian tracking, so the scans appear in GOFO's Italian words whatever your language. The usual ones, in the order a parcel meets them:

| GOFO's words | What it means for you |
| --- | --- |
| `Il pacco è in fase di preparazione da parte del mittente e ci sarà presto consegnato` | The sender is preparing the parcel and will hand it to GOFO soon. |
| `Registrato presso il centro di trasferimento` | Scanned in at a GOFO transfer centre. |
| `Il pacco ha lasciato il centro di smistamento` | It has left a GOFO sorting centre. |
| `Arrivato alla struttura di destinazione regionale GOFO` | At GOFO's regional facility for your area. |
| `Preparazione alla consegna` | Being readied at the depot before the courier leaves. |
| `Il corriere è fuori per la consegna` | Out with the courier. |
| `Consegna fallita, tipo:Destinatario assente` | Not delivered: nobody was home. |
| `Consegna fallita, tipo:L'indirizzo è errato` | Not delivered: the address is wrong. |
| `Il pacco è stato restituito al centro di smistamento` | Back at GOFO's sorting centre after a delivery that didn't work. |
| `Consegnato` | Delivered. |

Two lines trip people up. Until a scan follows the first one, GOFO doesn't have the parcel, and Peek shows it as "Label created". And `Il pacco è stato restituito al centro di smistamento` doesn't mean the parcel is going back to the shop: it's back at GOFO's sorting centre, and Peek shows it as "Needs attention". A delivery shows as `Consegnato` alone, in every language: GOFO's own delivery line can say more, including details about the recipient, which Peek leaves out.

GOFO's tracking also sorts parcels into broad categories. `Avviso` is a problem GOFO has flagged, which Peek shows as "Needs attention"; on its own it doesn't mean a delivery was attempted. `Restituito` is a parcel going back to the sender, shown as "Returned to sender".

If [YunExpress](carrier:yunexpress) handed your parcel to GOFO Italy, YunExpress's own tracking can relay GOFO's scans in English: `Preparing for delivery` at the depot before the courier leaves, and `Package departed from sorting center` beside the Italian `Il pacco ha lasciato il centro di smistamento`.

## GOFO parcel late, missed, lost or damaged

**Not moving.** Peek keeps asking GOFO. If four days pass without a new scan before the parcel is out for delivery, Peek's parcel page says so and links to GOFO Italy's help page ("Get help from GOFO Italy"). See also [why tracking stops updating](guide:tracking-not-updating).

**Missed delivery.** After `Consegna fallita, tipo:Destinatario assente` or `Consegna fallita, tipo:L'indirizzo è errato`, Peek shows "Delivery attempted", and so does a failed attempt with any other reason after `tipo:`. What happens next is up to GOFO: see [missed deliveries](guide:missed-delivery) for what to check.

**Late.** Under EU consumer rules, a shop must deliver within 30 days unless you agreed on another time. If it doesn't, give it an extra, reasonable deadline; if that passes too, you can cancel the order and get your money back.

**Marked `Consegnato` but not there.** The shop is responsible for delivering your order: it's up to the shop to contact the carrier and prove the parcel was delivered, and if it can't, to send the item again or refund you. For what to check around your home first, see [delivered but not received](guide:delivered-not-received).

**Damaged.** The shop is responsible for any damage from dispatch until you receive the parcel, so check it in front of the courier when you can. If the box is visibly damaged, Italy's postal regulator AGCOM says you can refuse it or accept it with reservation. More in [damaged parcels](guide:damaged-parcel).

## How to complain about a GOFO Italy delivery

Start with the shop that sent the parcel, since it answers for the delivery. For a complaint against the carrier itself, AGCOM, Italy's communications regulator, sets one path for postal operators, and parcels up to 31.5 kg count as postal:

1. Send the operator a written complaint, on the form from its website or one you ask for by email. It must answer within 45 days.
2. If the answer doesn't satisfy you, or none comes within 45 days, ask the operator for conciliation. It ends within 60 days.
3. If that fails too, you have 90 days to ask AGCOM to settle the dispute with its Formulario CP. AGCOM decides within 150 days, and its decision binds both sides.

AGCOM can order refunds or compensation set by the rules, not damages; for those, and at any point, you can go to court instead. If the operator doesn't answer your complaint or your conciliation request, report it on AGCOM's Portale delle segnalazioni. AGCOM's contact centre answers Monday to Friday, 10:00 to 14:00, on 800.185060 from a landline or 081.750750 from a mobile or from abroad.

## GOFO Italy customer service

GOFO Italy's help page is on its Italian site, gofo.com, and Peek links to it as "Get help from GOFO Italy" when four days pass without a new scan before the parcel is out for delivery. The company behind it, GOFO Italia s.r.l., has its registered office at Via Juan Manuel Fangio 11, 20045 Lainate (MI). Keep your `GFIT` or `CIIT` number and your order number at hand.

## Tracking a GOFO Italy parcel with Peek

[Peek](/) recognises `GFIT` and `CIIT` numbers by themselves. You can also paste a link from GOFO's Italian tracking page (on gofo.com or gofoexpress.it, with `id=` and the number).

While you have the parcel open, or on its delivery day with notifications on, Peek reads GOFO's Italian tracking up to every 10 minutes, and up to every 2 minutes from 6 a.m. local time on the day GOFO scans the parcel out for delivery, while GOFO's own tracker answers (through tracking services, up to every 15 minutes that day); otherwise hourly. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. It shows the status, the history with the place GOFO names for each scan, the delivery time and those places on a map, and it can notify you of every scan, important steps only or delivery day only. Where GOFO gives only a depot code or the country, the scan shows no place. Until GOFO lists a first scan, Peek shows "Waiting for GOFO Italy" and keeps asking rather than calling the number unknown at once. If GOFO still doesn't know the number two days later and nothing else has answered for it, Peek shows "After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel." and Peek from then on checks it daily, and weekly once a week has passed since you added it. If GOFO lists the shipment but hasn't scanned it yet, it knows the number: Peek keeps checking and shows "Waiting for GOFO Italy", with no request to check the number. Peek shows GOFO's expected delivery date until the parcel is out for delivery, delivered, returned or at a pickup point; it shows no weight and leaves out delivery photos and GOFO's notices. It can't contact GOFO or the shop for you.

## Questions about GOFO tracking

### How do I track a GOFO Italy parcel with a GFIT or CIIT number?

Paste your `GFIT` or `CIIT` number (13 or 14 digits) in the box above or enter it on gofo.com. Peek shows the delivery stage while keeping GOFO's Italian scan lines—for example, `Il corriere è fuori per la consegna` means out with the courier.

### Is GOFO Express Italy the same as CIRRO Parcel Italy?

Yes, for tracking in Italy: Peek's carrier list indexes GOFO Italy under the alternate name CIRRO Parcel Italy. A label mentioning only "Cirro" may also belong to GOFO's French or Dutch network.

### What does "Il pacco è stato restituito al centro di smistamento" mean?

It means the parcel went back to GOFO's sorting centre after a failed delivery attempt (such as `Destinatario assente`), not that it is already returning to the shop. Peek marks it "Needs attention", whereas `Restituito` means returning to sender.

### Is GOFO Italy legit?

GOFO's Italian company is GOFO Italia s.r.l., registered in Lainate (Milan). In Determina n. 12/26/DSP (2 September 2026), postal regulator AGCOM recorded three alleged violations notified on 17 June 2026; GOFO admitted and paid reduced penalties on the service-charter and dispute-procedure charges while contesting the authorisation charge.

### What can I do if my GOFO Italy parcel is late, damaged or lost?

Start with the shop: under EU rules it must deliver within 30 days unless otherwise agreed and prove delivery if a parcel marked `Consegnato` never arrived. Against the carrier itself (for parcels up to 31.5 kg), send a written complaint (45-day reply window), then conciliation (60 days) and AGCOM's Formulario CP (90 days), or call AGCOM on 800.185060 / 081.750750. More in [lost parcels](guide:lost-parcel).

:::sources
- [AGCOM: Determina n. 12/26/DSP](https://www.agcom.it/sites/default/files/provvedimenti/determina/2026/Determina%20arch%20parziale%2012_26_DSP%20-%20Gofo%20pdf.pdf) – GOFO Italia s.r.l., registered office in Lainate, AGCOM's 2026 proceeding and the penalty paid in the reduced amount (2 September 2026)
- [AGCOM: FAQ contenzioso tra utenti e operatori postali](https://www.agcom.it/faq-contenzioso-tra-utenti-e-operatori-postali) – complaint within 45 days, conciliation within 60, Formulario CP within 90, parcels up to 31.5 kg, damaged parcels, contact centre
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – delivery within 30 days, extra deadline, damage until receipt
- [Your Europe: FAQs, shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_en.htm) – the shop proves delivery, checking the parcel on delivery
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shapes, links, Italian scan wordings and categories, YunExpress's English relays, CIRRO Parcel Italy, what Peek shows and leaves out
:::
