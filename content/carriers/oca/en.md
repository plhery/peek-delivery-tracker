---
title: OCA tracking — numbers, statuses, branches, and delivery
description: Track OCA Argentina parcels and e-Pak shipments. Check 19-digit numbers, Spanish status meanings, 2 home delivery attempts, 5-day branch pickup, and help.
slug: oca-tracking
published: 2026-11-23
updated: 2026-11-23
---

OCA (Organización Coordinadora Argentina) is one of Argentina's largest private postal and e-commerce parcel carriers, serving online stores, Mercado Envíos orders, business logistics, and branch-to-branch deliveries nationwide. Recipients track packages through [OCA's parcel lookup](https://www.oca.com.ar/Busquedas/Envios), pick up held parcels at Puntos OCA branches across every Argentine province, or collect from OCA SmartLockers.

Most e-commerce shipments run on OCA's e-Pak platform, which records each depot scan, branch transfer, home-delivery visit, and pickup window in Spanish.

## OCA tracking number formats

Standard OCA e-Pak parcel numbers follow a 19-digit structure:

- **19 digits with `0000` in positions 6–9** (such as `3654800004829173605`) — the standard shipping guide or admission order (`número de guía` / `orden de admisión`) used for e-Pak parcels and Mercado Envíos orders.
- **Piece suffix `-1` (or `-2`, etc.)** — printed labels sometimes append a dash and piece digit, such as `3654800004829173605-1`. As [OCA's Help Centre](https://www.oca.com.ar/CentroDeAyuda) notes, you should leave off the `-1` piece suffix when tracking; Peek strips a trailing `-1` automatically and sends the 19-digit shipment number.
- **Stamped parcels and envelopes** — OCA also issues stamped items with 3 uppercase letters (`YAE`, `YAA`, `MDA`, `PAA`) plus 8 digits for parcels, or 2 letters plus 8 digits for stamped envelopes, entered without spaces or parentheses on OCA's own lookup page. Simple unstamped mail (`postales simples`) has no tracking and is delivered straight to the mailbox or under the door.

On OCA's website, you can abbreviate the run of middle zeros using `//`, though Peek expects the full 19-digit number (or a direct `oca.com.ar/Seguimiento/Paquetes/...` or legacy e-Pak link, which Peek parses automatically). Note that 19-digit OCA numbers can overlap in shape with a few foreign carriers, so Peek checks OCA's service to confirm the parcel. For other regional number shapes, see the [tracking number formats guide](guide:tracking-number-formats).

## What OCA tracking statuses mean

OCA's e-Pak tracking service reports states in Spanish and often appends the handling branch after a dash (`- Suc: ...`) or a reason in brackets.

| OCA status wording | What it means |
| --- | --- |
| `En proceso de Retiro` | Pickup from the sender is scheduled; OCA has not collected the parcel yet. |
| `Retirado` / `Retirado en Origen` | OCA has collected the parcel from the sender. |
| `En Proceso en OCA` / `Procesado en OCA` | Processed inside an OCA sorting plant or branch. |
| `En viaje a Centro de Distribución de Destino` / `En viaje a Sucursal de Destino` / `En Tránsito a Suc. de Destino` | In transit toward the destination hub or delivering branch. |
| `Arribado a Centro de Distribución de Destino` / `Llegó a nuestra sucursal` | Arrived at the destination hub or local OCA branch. |
| `Programado para visita a domicilio` | Entered in delivery scheduling (`en programación`) at the local branch ahead of a home visit. |
| `Visita a Domicilio en Curso` | Out with the courier for home delivery today. |
| `No entregado (No Visitado)` / `No entregado (...)` | Delivery was not completed; the text in brackets gives the reason (such as not visited or stopped at sender's request). |
| `Reprogramado para nueva visita` | Rescheduled for a follow-up delivery attempt. |
| `En Espera de Retiro por Sucursal` / `Disponible para que lo retires en tu Sucursal` | Held at the assigned OCA branch awaiting collection. |
| `Estado de Visita ONLINE (Entregado)` / `Entregado` | Delivered to the recipient or collected at the branch. |
| `Acuse digitalizado` | Proof of delivery scanned at OCA's plant days after delivery — administrative only, does not move the parcel. |
| `Devolución en Proceso` / `Devuelto al Remitente` | On its way back to, or returned to, the sender. |

## Home delivery visits, branches, and SmartLockers

OCA's [Help Centre](https://www.oca.com.ar/CentroDeAyuda) sets out clear rules for deliveries across Argentina:

- **Two home delivery attempts:** OCA makes two unscheduled visits Monday to Saturday, 09:00–18:00. Any person aged 18 or older at the address can receive a parcel by showing their DNI (national ID). Rainy days can limit delivery rounds.
- **Branch pickup (5 business days):** If both visits fail — or if you chose branch delivery — the parcel waits for **5 business days** at the designated Punto OCA branch (find branches via [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)). The recipient must bring their DNI and tracking number; an authorized person over 18 needs a copy of the recipient's DNI, a handwritten authorization note, and the tracking number. Unclaimed parcels return to the sender.
- **SmartLockers (48 hours + optional extension):** When OCA places a parcel in a SmartLocker, you receive an email and/or SMS with a QR code and alphanumeric PIN. You have **48 consecutive hours** to collect it; after warning reminders, OCA sends a card payment link allowing you to buy up to **3 extra storage days** before the parcel returns to the sender.
- **Wrong address or returned parcels:** OCA does not allow interrupting a shipment or editing the delivery address mid-transit. Wait for the visits to finish so you can pick it up at the branch (or let it return to the sender and ask the sender to reship). For more options after a missed visit, see the [missed delivery guide](guide:missed-delivery).
- **Special items (DNI, passports, OCA Geo cards, envelopes):** DNI deliveries go to the home address only (requiring the RENAPER receipt stub) and cannot be collected at a branch; passports move to branch pickup after two home visits. OCA Geo cards require delivery directly to the cardholder. Stamped envelopes get two home visits and are returned if undelivered rather than held at a branch.
- **48 business hours for first scan:** OCA notes that newly admitted shipments can take up to **48 business hours** after branch drop-off to appear online. If nothing shows after 48 business hours, only the sender can open an inquiry. See [when tracking stops updating](guide:tracking-not-updating) for general checks.

For other domestic Argentine carriers, compare with [Andreani](carrier:andreani).

## Tracking OCA with Peek

Paste a 19-digit OCA number (with or without a trailing `-1`) or an `oca.com.ar/Seguimiento/Paquetes/...` link into Peek. If Peek does not lock onto OCA immediately — for example before the first scan appears — pick OCA via **Change** when adding the parcel or **Change carrier** on the tracking page.

Peek reads OCA's e-Pak `Tracking_Pieza` web service directly through the open-source [universal-parcel-scraper OCA adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca). Every scan's Argentine timestamp is stored as an exact instant in `America/Argentina/Buenos_Aires` (`-03:00`), branch names are extracted cleanly into the location field, and administrative `Acuse digitalizado` records never overwrite or disturb a completed delivery. OCA's e-Pak feed does not include an estimated delivery date or parcel weight.

Peek maps OCA states into clear stages: **Label created** (`En proceso de Retiro`), **Received by carrier** (`Retirado`), **In transit** (sorting plant and branch transfers), **Out for delivery** (`Programado para visita a domicilio` and `Visita a Domicilio en Curso`), **Delivery attempted** (`No entregado` and `Reprogramado para nueva visita`), **Ready for pickup** (`En Espera de Retiro por Sucursal`), **Delivered** (`Entregado`), and **Returned to sender** (`Devolución en Proceso` / `Devuelto al Remitente`).

While a parcel travels between provinces or awaits sorting over a weekend, Peek displays: "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking OCA." After more than three weeks without a new scan, Peek shows: "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking OCA." Tap **Get help from OCA** on the tracking screen to jump directly to OCA's Help Centre.

Choose **Every scan**, **Important steps**, or **Delivery only** in Peek's alert settings to control which updates trigger notifications.

## Contacting OCA

- **Help Centre and online support form:** [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda) (includes FAQ categories and the direct support message form at the bottom of the page)
- **Official tracking lookup:** [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- **Branch locator:** [Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- **Main website:** [oca.com.ar](https://www.oca.com.ar/)
- **Sender first for missing numbers or reshipments:** OCA states that only the sender is authorized to investigate a shipment that still has no record 48 business hours after drop-off, or to arrange a reshipment once a parcel has been returned.

## Frequently asked questions

### Why does my OCA tracking number end in `-1`?

`-1` is a piece number printed on the label for multi-piece orders. OCA's tracking lookup tracks the 19-digit shipment number without `-1` — enter only the first 19 digits, or paste it straight into Peek, which strips the `-1` suffix automatically.

### How long does OCA hold a parcel at a branch or SmartLocker?

OCA holds parcels at Puntos OCA branches for **5 business days** after failed delivery attempts or branch arrival. In SmartLockers, you have **48 consecutive hours** free, with an option to pay online for up to **3 additional days** before return to sender.

### Can I change my delivery address after OCA picks up my parcel?

No. OCA's Help Centre states that neither the shipment process nor the delivery address can be modified mid-transit. After the home delivery visits, you can collect the parcel in person at the designated OCA branch with your DNI, or let it return to the sender.

### Why is my OCA tracking number not showing any updates yet?

OCA notes that a parcel can take up to **48 business hours** from branch intake to appear on the web tracker. If 48 business hours pass with no record, contact the sender, who is the only party authorized to open a missing-record check with OCA.

:::sources
- [OCA Argentina](https://www.oca.com.ar/)
- [OCA Centro de Ayuda](https://www.oca.com.ar/CentroDeAyuda)
- [OCA Búsqueda de envío](https://www.oca.com.ar/Busquedas/Envios)
- [OCA Buscador de Puntos OCA](https://www.oca.com.ar/Busquedas/Sucursales)
- [universal-parcel-scraper OCA adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/oca)
:::
