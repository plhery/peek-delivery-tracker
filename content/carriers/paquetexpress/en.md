---
title: Paquetexpress tracking: guías, statuses, Ocurre and claims
description: Track a Paquetexpress Mexico parcel: guía and 11–13 digit codes, Spanish statuses, 2 attempts, 5-day Ocurre pickup, 15-day claims and 800 821 0208 support.
slug: paquetexpress-tracking
published: 2026-11-23
updated: 2026-11-23
---

Paquetexpress (*Operadora de Servicios Paquetexpress, S.A.P.I. de C.V.*, headquartered in Los Mochis, Sinaloa) is one of Mexico's largest domestic parcel, courier and freight carriers, with 40 years in operation, more than 12,200 staff, nationwide Mexican branch coverage and international shipping to over 240 countries. Track a shipment on [Paquetexpress's tracking page](https://www.paquetexpress.com.mx/rastreo) without needing an account, or paste your tracking code or *guía* into the box above.

## Paquetexpress tracking numbers: tracking codes and guías

Paquetexpress's [FAQ](https://www.paquetexpress.com.mx/preguntas-frecuentes/) notes that its tracking box accepts a tracking code (*código de rastreo*), a waybill (*número de guía*) or a shipper reference (Peek looks up tracking codes and *guías* only). Look for the code in your shop's dispatch email, Paquetexpress's SMS link or the shipping label:

| Format (invented) | What it is |
| --- | --- |
| `MEX48WW2917364` | Paquetexpress *guía*: 3-letter origin branch code + 2 digits + 2 letters + 5 to 7 digits (covers all parcels on one waybill). |
| `482917364058` | 11- to 13-digit numeric tracking code (*código de rastreo*). Shares this length with carriers such as [DPD France](carrier:dpd-fr), [Mondial Relay](carrier:mondial-relay), [Colis Privé](carrier:colis-prive), [MRW](carrier:mrw), [BRT](carrier:brt) and [SF Express](carrier:sf-express). |

If your Mexican parcel starts with `JMX`, check [J&T Express Mexico](carrier:j-and-t-mx); for other code patterns, see [tracking number formats](guide:tracking-number-formats).

## What Paquetexpress tracking statuses mean

Paquetexpress writes its tracking history in Spanish, with internal event codes (`SWB`, `BOK`, `TRN`, `WHU`, `HTC`, `HDC`, etc.):

| Paquetexpress says (code) | What it means for you | Peek shows |
| --- | --- | --- |
| `Documentación De Servicios Web` (`SWB`), `Solicitud De Rad` (`HCR`), `Recolección Planeada` (`HCP`) | Label created electronically or pickup requested/scheduled; Paquetexpress does not have the physical parcel yet. | "Label created" |
| `Documentada` (`BOK`), `Recolección Realizada` (`HCM`), `Confirmación De Recolección En Almacen` (`HCA`) | Dropped off at a branch counter, collected by a driver or checked into the origin branch warehouse. | "Received by carrier" |
| `Mercancía En Tránsito` (`TRN`), `Transbordo De Remolque` (`TTM`), `Recibida En Sucursal de Transbordo` (`WHU`), `Planeado Para Entrega A Domicilio` (`HLP`) | Moving between branches or trailers, or staged at the destination branch before a delivery round starts. | "In transit" |
| `Recibida En Sucursal destino` (`WHU`) | At the destination branch: still "In transit" for home delivery (`ENT_EAD`), or waiting for your pickup if sent *Ocurre* (`ENT_OCU`). | "In transit" or "Ready for pickup" |
| `En Ruta De Entrega A Domicilio` (`HTC`) | Out with a courier for home delivery today. | "Out for delivery" |
| `Mercancía Entregada A Domicilio` (`HDC`), `Entregada En Sucursal` (`BDL`), `Pagada En Destino (Contado)` (`PDC`) | Delivered at the door, collected at the branch or paid for in cash on delivery at destination. | "Delivered" |

Note that `Rad Cancelación` (`HCC`, cancelled pickup request) does not stop the shipment because the sender can still hand the parcel in, so Peek keeps the stage of the scan before it.

## Delivery times, 2 attempts, Ocurre pickup and storage fees

Under Paquetexpress's [Services page](https://www.paquetexpress.com.mx/servicios/), [FAQ](https://www.paquetexpress.com.mx/preguntas-frecuentes/) and PROFECO-registered [Service Terms (Clausulado, contract 3808-2020)](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/):

- **Transit times:** domestic parcel and courier deliveries run in **24, 48, 72, 96 or 120 hours** depending on distance (with Express same-day before 12:00, 24 h and 48 h options, plus Saturday deliveries). Paquetexpress always records recipient name, signature, date and time on delivery (*Acuse*).
- **Maximum 2 delivery attempts:** under Clause 5, for home delivery (*Entrega a Domicilio*) Paquetexpress attempts delivery up to **2 times**. If nobody is home, the driver leaves a visit notice (*aviso de visita*) with branch and contact-centre phone numbers, and the parcel is held at the branch.
- **5 calendar days free pickup (Ocurre or missed delivery):** under Clause 5, both *Ocurre* branch-pickup parcels and parcels held after missed doorstep attempts must be collected within **5 calendar days** of arrival/visit notice. After 5 calendar days, Paquetexpress may move the parcel to its unclaimed warehouse (*bodega de rezagos*) without prior notice and charge a **2% daily storage fee** (of the shipping cost). Uncollected parcels held past **90 calendar days** may be auctioned or destroyed.
- **Switching delivery mode or address:** Paquetexpress's [FAQ](https://www.paquetexpress.com.mx/preguntas-frecuentes/) states you can request a switch between *Ocurre* and home delivery — or correct a delivery address even after dispatch from the branch — by calling `(+52) 800 821 0208`. Find local branches via [Paquetexpress Sucursales](https://www.paquetexpress.com.mx/sucursales).

## Claims, damaged parcels and PROFECO mediation

For online orders, contact the online store first for refunds or replacements. Under Clause 8 of Paquetexpress's [Service Terms](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/):

- **Inspect on delivery:** check the packaging in front of the courier, record any tampering or damage on the delivery receipt (*acuse de recibo*) and ask for detailed inspection.
- **15-business-day claim window:** submit a formal claim within **15 business days** after recording anomalies, either in person at any branch (Monday to Friday 08:00–18:00), by email to `reclamaciones@paquetexpress.com.mx` or via the [formal claim request page](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/). Paquetexpress commits to respond within **15 business days**.
- **Statutory liability vs insurance:** uninsured shipments are covered up to federal statutory caps (15 days of minimum wage per tonne for federal freight, or 5 days of minimum wage per day of carrier-caused delay for parcels/courier). Optional transport insurance covers up to declared value (0% deductible for damage; 20% for armed robbery, expiring after 5 calendar days at an *Ocurre* branch or after the 2nd failed doorstep attempt / 10 calendar days at the destination branch).
- **PROFECO:** under Clause 10, disputes fall administratively under Mexico's Federal Consumer Protection Agency ([PROFECO](https://www.gob.mx/profeco), registered adhesion contract `3808-2020`).

For troubleshooting missing scans or parcels delivered to the wrong person (which Paquetexpress asks you to report via `800 821 0208` for recovery or claim), see [why tracking stops updating](guide:tracking-not-updating), [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## Paquetexpress customer service and contacts

Contacts published on [Paquetexpress Contact](https://www.paquetexpress.com.mx/en/contacto/), [Contacto (ES)](https://www.paquetexpress.com.mx/contacto/) and the [Service Terms](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/):

- **Toll-free phone:** `+52 800 821 0208` — Monday to Friday 08:00–21:00 and Saturday 08:00–15:00 (Central time; listed as 07:00–20:00 / 07:00–14:00 Pacific time in the Sinaloa contract).
- **WhatsApp:** `+52 668 195 0169` (linked from [paquetexpress.com.mx](https://www.paquetexpress.com.mx/)).
- **Claims email & portal:** `reclamaciones@paquetexpress.com.mx` and [Solicitud de Reclamación](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/).
- **Privacy / ARCO requests:** `solicitud.arco@paquetexpress.com.mx`.
- **Headquarters:** Operadora de Servicios Paquetexpress, S.A.P.I. de C.V., Blvd. Rosendo G. Castro No. 32 Poniente, Col. Centro, Los Mochis, Sinaloa, C.P. 81200, Mexico.

> Be wary of fake SMS or email links asking you to pay a card fee online to reschedule a Paquetexpress delivery: Paquetexpress confirms you can call `800 821 0208` directly to change delivery instructions or check a shipment on [paquetexpress.com.mx/rastreo](https://www.paquetexpress.com.mx/rastreo). See [delivery scam texts](guide:delivery-scam-texts).

## Track a Paquetexpress parcel with Peek

Because 11- to 13-digit tracking codes are shared across many international carriers, [Peek](/) uses your region to decide when to query Paquetexpress. In Mexico, Peek asks Paquetexpress in the first round for matching *guías* (`MEX48WW2917364`, invented) and 11- to 13-digit codes (`482917364058`) and displays "Paquetexpress has this parcel." when Paquetexpress confirms it. Outside Mexico, Paquetexpress is treated as a last resort — paste a `paquetexpress.com.mx/rastreo/...` (or `/en/rastreo/...`) link to pick Paquetexpress directly anywhere, or tap "Change" while adding / "Change carrier" on the parcel screen (searching "Paquete Express" also finds it).

Peek queries Paquetexpress's history service directly (for tracking codes and *guías*, not shipper references) and shows the mapped stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Ready for pickup", "Delivered"), Spanish status lines (with personal names stripped after `POR`, `a` and `, recibió`), event codes (`SWB`, `BOK`, `TRN`, `HTC`, `HDC`, etc.), branch/city locations, estimated delivery date when active, delivery timestamp and local Mexican branch time-zone offsets (accounting for Pacific, Mountain, Central and Quintana Roo shifts, displayed in your device's time zone). For multi-parcel *guías*, Peek keeps the shipment open while any parcel on the *guía* is still moving even if another has already been delivered, and automatically marks `Recibida En Sucursal destino` as "Ready for pickup" on *Ocurre* shipments (`ENT_OCU`). Peek drops the site's progress rail, service type, origin/destination headers, customer references, recipient/sender names, branch street addresses and signature/POD images; parcel weight and dimensions are not exposed.

Peek checks up to every 10 minutes while you have the parcel open or on the expected delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. local branch time on the day `En Ruta De Entrega A Domicilio` (`HTC`) appears. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12"), shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Paquetexpress." and displays "Get help from Paquetexpress", which opens Paquetexpress's contact page. After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Paquetexpress."

You can choose alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking only and cannot switch a parcel between *Ocurre* and home delivery or submit a claim to Paquetexpress for you.

## Questions about Paquetexpress tracking

### How many delivery attempts does Paquetexpress make?

Under Clause 5 of its [Service Terms](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/), Paquetexpress attempts home delivery up to **2 times**. If nobody can receive the parcel, the driver leaves a visit notice (*aviso de visita*) and the shipment is held at the local branch.

### How long does Paquetexpress hold an Ocurre or missed-delivery parcel?

You have **5 calendar days** free of charge after arrival at the branch (or after the visit notice). Beyond 5 calendar days, Paquetexpress may move the parcel to its unclaimed warehouse with a **2% daily storage fee**, and after 90 calendar days it may be auctioned or destroyed.

### Can I switch from Ocurre pickup to home delivery or change my address?

Yes. Paquetexpress's [FAQ](https://www.paquetexpress.com.mx/preguntas-frecuentes/) says you can call `(+52) 800 821 0208` to switch between *Ocurre* and home delivery or to correct the delivery address even after the shipment has left the branch.

### How long do I have to file a damage or loss claim with Paquetexpress?

Note any visible damage on the delivery receipt (*acuse de recibo*) upon delivery, then submit a formal claim within **15 business days** at a branch, by email to `reclamaciones@paquetexpress.com.mx` or via the [online claim form](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/). Paquetexpress replies within 15 business days.

:::sources
- [Paquetexpress: Home](https://www.paquetexpress.com.mx/) – tracking entry, WhatsApp `+52 668 195 0169`, toll-free `+52 800 821 0208`
- [Paquetexpress: Rastreo](https://www.paquetexpress.com.mx/rastreo) – shipment tracking page
- [Paquetexpress: Servicios](https://www.paquetexpress.com.mx/servicios/) – 24–120 h domestic transit, Express same-day/24/48 h, Saturday delivery, Acuse signature, 240+ countries
- [Paquetexpress: International](https://www.paquetexpress.com.mx/en/internacional/) – international shipping services
- [Paquetexpress: Conócenos](https://www.paquetexpress.com.mx/conocenos/) – 40 years in operation, 12,200+ staff, 240+ countries
- [Paquetexpress: Sucursales](https://www.paquetexpress.com.mx/sucursales) – branch finder
- [Paquetexpress: Preguntas Frecuentes](https://www.paquetexpress.com.mx/preguntas-frecuentes/) – no account required to track, tracking code vs guía vs reference, switching Ocurre/home delivery, address corrections, visit notice, open-parcel reporting
- [Paquetexpress: Contact (EN)](https://www.paquetexpress.com.mx/en/contacto/) – `+52 800 821 0208`, Mon–Fri 08:00–21:00, Sat 08:00–15:00
- [Paquetexpress: Contacto (ES)](https://www.paquetexpress.com.mx/contacto/) – Spanish contact page (help URL linked by Peek)
- [Paquetexpress: Solicitud de Reclamación](https://www.paquetexpress.com.mx/contacto/solicitud-de-reclamacion/) – formal online claim submission
- [Paquetexpress: Clausulado de Solicitud de Servicios](https://www.paquetexpress.com.mx/clausulado-solicitud-servicios/) – PROFECO contract `3808-2020`: 2 delivery attempts, 5 calendar days free pickup, 2% daily storage fee, 90-day abandonment, 15-business-day claims (`reclamaciones@paquetexpress.com.mx`), Los Mochis headquarters
- [PROFECO](https://www.gob.mx/profeco) – Federal Consumer Protection Agency
- [Universal Parcel Scraper: paquetexpress](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/paquetexpress) – guía and 11–13 digit detection, Mexico priority, `/rastreo/` link rule, event codes, ETA, multi-parcel guías, `ENT_OCU` pickup handling, stripped personal names and help URL
:::
