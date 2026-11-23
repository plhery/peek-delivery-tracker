---
title: Redur tracking: 14-digit codes, Spain & Portugal statuses
description: Track a Redur parcel in Spain, Portugal or Europe: 14-digit tracking numbers, depot and delivery scan meanings, agency pickup, Eurodis routes and support.
slug: redur-tracking
published: 2026-11-23
updated: 2026-11-23
---

**Redur** (*Redur S.A.*) is a Spanish express transport and logistics operator with more than 50 years of history, headquartered in Algete, Madrid ([redur.es](https://redur.es/)). Operating a 100% own-managed network of over 61 depots (*delegaciones*) and 3,000 staff across mainland Spain, the Spanish islands and Portugal, Redur is also a founding member of **Eurodis**, the European combi-freight parcel and pallet network. Alongside domestic urgent B2B/B2C shipping and daily international road departures across Europe, Redur runs GDP-certified pharmaceutical distribution (*Farma 15-25*) and contract logistics. You can track a shipment on [redur.es/track-trace/](https://redur.es/track-trace/) or paste your 14-digit Redur number or tracking link into the box above.

## Redur tracking numbers (14-digit format)

Redur tracking numbers (*N° Tracking*) are 14 digits long with no letters or spaces:

| Format (invented) | What it is |
| --- | --- |
| `58492716380419` | Standard 14-digit Redur parcel tracking number (*NTRACKING*). |
| `61738492051628` | Alternative 14-digit Redur shipment tracking code for Spain, Portugal or Eurodis routes. |

On [redur.es/track-trace/](https://redur.es/track-trace/), the form also lets account users search by expedition number or customer reference, while automated tracking in Peek uses the 14-digit tracking number (`NTRACKING`) or a direct `redur.es/track-trace/?...&valor=` link. Because bare 14-digit numbers overlap with several other European couriers (such as [SEUR](carrier:seur) and DPD), pasting the Redur link or selecting Redur manually ensures Peek checks Redur immediately. See [tracking number formats](guide:tracking-number-formats).

## What Redur tracking statuses mean

Redur's tracking page shows a 7-step progress bar together with depot-level scan rows (in Spanish on `redur.es/track-trace/`, or English on the English page):

| Redur says (ES / EN) | What it means for you | Peek shows |
| --- | --- | --- |
| `DOCUMENTACION` / `DATA ENTERING` | Electronic shipping documentation transmitted by the sender before physical depot intake (dated to the day only). | "Label created" |
| `ENTRADA PLAZA ORIGEN` / `INBOUND ORIGIN DEPOT` | First physical intake scan at Redur's origin depot. | "Received by carrier" |
| `SALIDA PLAZA ORIGEN` (`OUTBOUND ORIGIN DEPOT`), `ENTRADA/SALIDA PLAZA TRANSITO` (`INBOUND/OUTBOUND TRANSIT DEPOT`), `ENTRADA PLAZA DESTINO` (`INBOUND FINAL DEPOT`) | Moving between origin, transit and destination depots across Spain or Portugal. | "In transit" |
| `CARGA CAMION T. INTERNACIONAL` / `TRUCK LOAD INTERNATIONAL TRAFFIC` | Loaded onto an international Eurodis road trunk toward another European country. | "In transit" |
| `PENDIENTE DE FECHA CONCERTADA`, `FECHA DE ENTREGA CONCERTADA` / `BOOKING AGREED DATE` | Waiting to agree a delivery date with the recipient, or delivery appointment date confirmed. | "In transit" |
| `SALIDA A REPARTO` / `OUT FOR DELIVERY` | Out on the local Redur delivery vehicle today. | "Out for delivery" |
| `RECOGERÁN EN AGENCIA` | Held at the destination Redur depot for recipient collection. | "Ready for pickup" |
| `DESTINATARIO AUSENTE`, `INCORRECT ADDRESS`, `CERRADO PERMANENTE/TEMPORAL` | Delivery attempt failed because no one was present, the address had an error or the premises were closed. | "Delivery attempted" |
| `DEVOLUCIÓN A ORIGEN` | Returning back to the sender after failed delivery or refusal. | "Returned to sender" |
| `ENTREGA` / `DELIVERY` | Delivered to the recipient (or confirmed via Redur's active final delivery step on the progress rail). | "Delivered" |

## Spain, Portugal and Eurodis European services

According to [Redur](https://redur.es/) and [Redur Services](https://redur.es/servicios/):

- **Domestic Iberia (*Servicio Doméstico*):** urgent parcel, pallet and combi-freight distribution across mainland Spain, the Balearic and Canary Islands and Portugal via Redur's 61+ own depots, backed by *Redur Connected* email and SMS delivery notifications and the *Maia* shipment management tool.
- **International Europe (*Servicio Internacional*):** daily departures across Europe for B2B and B2C shipments through the **Eurodis** partner network. Once `CARGA CAMION T. INTERNACIONAL` appears, a local Eurodis partner courier in the destination country completes final delivery.
- **Temperature-controlled pharma (*Farma 15-25*):** GDP-compliant 15–25 °C distribution for healthcare and pharmaceutical shipments.

## Missed delivery, appointment holds and missing parcels

- **`PENDIENTE DE FECHA CONCERTADA` (awaiting agreed date):** common on bulky, palletized or business deliveries where Redur contacts the recipient to book a delivery slot before dispatching the van. Check your phone and email for a *Redur Connected* notice, or call Redur with your 14-digit number.
- **`DESTINATARIO AUSENTE`, `INCORRECT ADDRESS` or `RECOGERÁN EN AGENCIA`:** contact your seller or Redur's customer service promptly with your 14-digit tracking code to confirm your address, schedule a fresh delivery attempt or check opening hours for depot collection (bring photo ID).
- **Delayed, damaged or missing shipments:** notify the online retailer or shipper first (they hold the contract with Redur and can file a formal claim) and confirm the parcel's depot location with Redur. See [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel), [returned to sender](guide:returned-to-sender) and [why tracking stops updating](guide:tracking-not-updating).

## Redur customer service and Madrid headquarters

Official contact details published on [Redur Contacto](https://redur.es/contacto/) and [Redur Services](https://redur.es/servicios/):

- **Central telephone (Spain):** `(+34) 916 20 46 10` (fax `91 628 15 60`).
- **Email:** `info@redur.es`.
- **Contact form & depot directory:** [redur.es/contacto/](https://redur.es/contacto/).
- **Headquarters (*Servicios Centrales Redur*):** *Redur S.A.*, Carretera Algete–Madrid Km 2.5 (P.I. Redur), 28110 Algete, Madrid, Spain.

> Beware of fake text messages asking you to pay a small online fee to reschedule a delivery in Spain or Portugal. Always verify delivery notices through [redur.es/track-trace/](https://redur.es/track-trace/) or your seller first. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Redur parcel with Peek

Paste a direct `redur.es/track-trace/?...&valor=` tracking link into [Peek](/) and Peek recognises Redur automatically. Because bare 14-digit numbers (`58492716380419` or `61738492051628`, invented) are shared by many European carriers and Redur has no exclusive number prefix, enter your 14-digit number and tap "Change" while adding or "Change carrier" on the parcel screen to select **Redur** (searching "Redur Spain", "Redur Portugal" or "Redur S.A." finds it too); Peek then verifies it directly against Redur's tracker and shows `"Redur has this parcel."` once confirmed.

Peek queries `redur.es/track-trace/` directly and displays the mapped stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Ready for pickup", "Delivery attempted", "Returned to sender", "Delivered"), Spanish scan descriptions, depot locations, the final delivery status from Redur's progress rail when active, and local wall-clock timestamps as printed by Redur (date-only for midnight `DOCUMENTACION` rows, without a time-zone offset). Peek discards the package-count column; Redur's public tracker does not publish an estimated delivery date, weight, service name, separate delivery timestamp or onward Eurodis partner tracking number.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. local time on the day an out-for-delivery scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12"), shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Redur." and displays "Get help from Redur", which opens Redur's contact page. After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Redur."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot book a delivery appointment, edit an address or contact Redur for you.

## Questions about Redur tracking

### What does a Redur tracking number look like?

Redur tracking numbers are 14 numeric digits with no letters or spaces (for example `58492716380419`, invented).

### What does `PENDIENTE DE FECHA CONCERTADA` mean on Redur?

It means the shipment is at a Redur depot waiting to arrange a mutually agreed delivery date with the recipient — common for large parcels, pallets or commercial deliveries.

### What does `RECOGERÁN EN AGENCIA` mean?

It means your parcel is being held at the local Redur branch (*delegación*) ready for you to collect in person with valid ID.

### How do I contact Redur customer service?

Call Redur's central switchboard in Spain on `(+34) 916 20 46 10`, email `info@redur.es` or use the contact page at [redur.es/contacto/](https://redur.es/contacto/).

:::sources
- [Redur: Official Website](https://redur.es/) – 50+ years, 61+ Spain & Portugal depots, 3,000 staff, Eurodis founding member, *Maia* and *Redur Connected*
- [Redur: Track & Trace](https://redur.es/track-trace/) – public shipment tracker
- [Redur: Servicios](https://redur.es/servicios/) – *Servicio Doméstico*, *Servicio Internacional*, *Farma 15-25* (GDP) and *Logística integral*
- [Redur: Contacto](https://redur.es/contacto/) – `(+34) 916 20 46 10`, `info@redur.es` and Algete (Madrid) headquarters (help URL linked by Peek)
- [Universal Parcel Scraper: redur](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/redur) – 14-digit format, `redur.es/track-trace/` link rule, Spanish/English scan wording map, delivered progress-rail handling and local wall clocks
:::
