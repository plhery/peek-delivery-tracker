---
title: GOFO Spain tracking: GFES and CIES codes, 3 attempts, help
description: Track a GOFO Spain (GOFO Express) parcel: GFES and CIES codes, Spanish scan statuses, 3 delivery attempts, 2-week depot storage, POD photos and support.
slug: gofo-spain-tracking
published: 2026-11-23
updated: 2026-11-23
---

GOFO Spain is the Spanish arm of GOFO, a U.S.-founded e-commerce last-mile logistics provider launched in August 2023 that operates across the United States, Canada, France, the Netherlands, Italy and Spain. In Spain, GOFO handles final-mile delivery for online retailers and cross-border marketplaces, making up to three doorstep attempts and keeping undelivered parcels at its distribution centre for two weeks. You can check a shipment on [GOFO Spain's tracking page](https://www.gofo.com/es/tracking-results/), or paste your tracking number into the box above.

## GOFO Spain tracking numbers: GFES and CIES formats

Find your tracking number in your online store's dispatch email or marketplace order page. GOFO Spain numbers begin with `GFES` or `CIES` followed by 13 or 14 digits:

| Format (invented) | What it is |
| --- | --- |
| `GFES26482917364052` | Standard 18-character GOFO Spain parcel code (`GFES` + 14 digits with check digit; 17-character `GFES` + 13 digits also occurs). |
| `CIES4829173640582` | Alternate 17- or 18-character GOFO Spain parcel code (`CIES` + 13 or 14 digits). |

If your cross-border order started in China under another carrier's barcode — such as `YDH...YQ` with [YDH](carrier:ydh) — the marketplace often adds a `GFES` or `CIES` number once the parcel hands over for last-mile delivery in Spain. For other European courier shapes, see [tracking number formats](guide:tracking-number-formats).

## What GOFO Spain tracking statuses mean

GOFO Spain's tracking feed uses numeric status codes paired with Spanish descriptions on local Spain time (`Europe/Madrid`):

| GOFO Spain says (code) | What it means for you | Peek shows |
| --- | --- | --- |
| `Pedido recibido` (`100`) | The seller registered the order electronically; GOFO is waiting to receive the physical parcel. | "Label created" |
| `Llegada al centro de clasificación` (`202`), `Salida del centro de clasificación` (`200`), `Llegada a las instalaciones de destino de GOFO` (`201`), `En preparación para reparto` (`203`) | Scanned in at GOFO's sorting centre, dispatched to the destination facility and prepared for local delivery. | "In transit" |
| `El paquete está en reparto` (`208`) | Out with a GOFO courier for doorstep delivery in Spain. | "Out for delivery" |
| `Entrega fallida` (`206`) | Delivery attempt failed (for example recipient absent or address problem). | "Delivery attempted" |
| `Devuelto al centro de clasificación` (`204`) | Returned to GOFO's sorting/distribution centre after a failed attempt — **not** yet returned to the sender. | "Needs attention" |
| `Devuelto` (`257` / `264`) | Parcel is being returned to the sender. | "Returned to sender" |
| `Entregado por En persona` / `Entregado por En el buzón` (`205`) | Delivered in person or placed in your letterbox. | "Delivered" |

**Note on `Devuelto al centro de clasificación` (`204`).** Seeing *"Devuelto"* here can look alarming, but code `204` means the driver brought the parcel back to GOFO's local sorting centre after an unsuccessful visit (so it can go out again or wait during the two-week holding period), whereas `Devuelto` (`257`/`264`) marks an actual return to the shipper.

## GOFO Spain delivery attempts, 2-week storage and customs

According to [gofo.com/es/en/](https://www.gofo.com/es/en/) and [GOFO Spain's Customer Support FAQ](https://www.gofo.com/es/en/contact-us/):

- **Three attempts and two-week storage:** GOFO Spain explicitly states that parcels are returned to the distribution centre after **three failed delivery attempts** and **stored securely for two weeks**. Reach out before those two weeks expire if you need to coordinate delivery or clarify an address issue.
- **Proof of Delivery (POD) photos:** for delivered items, GOFO Spain's [main portal](https://www.gofo.com/es/) provides POD details that usually include a photo of where the parcel was left and a delivery description.
- **Non-EU e-commerce orders and customs:** many GOFO Spain deliveries originate from online stores outside the European Union. Under the [European Commission's EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en), low-value e-commerce imports into the EU carry the €3 customs duty on consignments up to €150 and the €2 EU handling fee per item unless settled at checkout by the marketplace. See [parcels held at customs](guide:customs).

## GOFO Spain parcel missing, damaged or marked delivered

GOFO Spain's [Customer Support FAQ](https://www.gofo.com/es/en/contact-us/) lays out exact steps depending on the issue:

- **Marked `Entregado` ("Delivered") but not received:** first check your Proof of Delivery photo on [gofo.com/es/](https://www.gofo.com/es/). Look in your letterbox, shared parcel storage areas, front door and safe spots nearby, and ask neighbours, family members or building management. If it is still missing, email `cs@es.gofo.com` promptly — attaching a photo of your residence entrance or exterior helps speed up the investigation (though GOFO processes reports even without a photo).
- **Missing items inside intact packaging or confirmed lost parcel:** GOFO instructs recipients to **contact the sender directly** to handle missing-item reports or loss claims.
- For general checklists, see [why tracking stops updating](guide:tracking-not-updating), [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## GOFO Spain customer service and contacts

Official contacts published on [GOFO Spain Customer Support](https://www.gofo.com/es/en/contact-us/) and [Contacto](https://www.gofo.com/es/contact-us/):

- **Customer support email:** `cs@es.gofo.com`.
- **Support hours:** Monday to Friday, `09:00 – 18:00` (excluding public holidays). Note that GOFO Spain's recipient phone hotline is listed on its support page as *"Coming soon"* (*Próximamente*), so email is currently the published contact channel for recipients.

> Ignore unsolicited SMS or WhatsApp messages demanding an online card payment to "re-deliver" or "confirm your address" for a GOFO parcel. Verify your `GFES` or `CIES` code directly on [gofo.com/es/tracking-results/](https://www.gofo.com/es/tracking-results/) or write to `cs@es.gofo.com`. See [delivery scam texts](guide:delivery-scam-texts).

## Track a GOFO Spain parcel with Peek

[Peek](/) automatically recognises `GFES` + 13 or 14 digits (`GFES26482917364052`, digits invented — passing the GOFO Europe check digit) and `CIES` + 13 or 14 digits (`CIES4829173640582`) as GOFO Spain with high confidence, and also recognises pasted `gofo.com/es/...?id=` tracking links. If you ever need to choose GOFO Spain manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "GOFO ES" finds it too).

Peek queries GOFO Spain directly and shows the mapped stage ("Label created", "In transit", "Out for delivery", "Delivery attempted", "Needs attention", "Returned to sender", "Delivered"), Spanish scan wording, numeric operation codes (`100`, `200`–`208`, `257`/`264`), location, delivery timestamp, service name, estimated delivery date when GOFO supplies one and local Spain wall-clock times (`Europe/Madrid`). Peek drops extra delivery details, proof-of-delivery photos (open [gofo.com/es/](https://www.gofo.com/es/) directly to inspect POD photos), personal references, recipient verification fields, unlabelled weights and portal notices.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. Madrid time on the day an `El paquete está en reparto` (`208`) scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12"), shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking GOFO Spain." and displays "Get help from GOFO Spain", which opens GOFO Spain's contact page. After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking GOFO Spain."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot view POD photos inside the app, reschedule delivery or file a claim with GOFO Spain on your behalf.

## Questions about GOFO Spain tracking

### How many delivery attempts does GOFO Spain make?

GOFO Spain makes **three delivery attempts**. After three failed attempts, the parcel returns to its distribution centre and is stored securely for **two weeks**.

### What does `Devuelto al centro de clasificación` mean?

Code `204` (`Devuelto al centro de clasificación`) means the courier brought the parcel back to GOFO's sorting centre after an unsuccessful delivery attempt — it has **not** been returned to the original seller yet (that shows as `Devuelto`, codes `257`/`264`).

### Where can I see my GOFO Spain Proof of Delivery photo?

Enter your `GFES` or `CIES` number on [gofo.com/es/](https://www.gofo.com/es/) or [gofo.com/es/tracking-results/](https://www.gofo.com/es/tracking-results/) to check the POD photo and location notes directly.

### How do I contact GOFO Spain customer service?

Email `cs@es.gofo.com` (support hours Monday to Friday, `09:00 – 18:00`, excluding public holidays). For confirmed lost parcels or missing items inside intact packaging, contact the online seller directly.

:::sources
- [GOFO Spain: English Home](https://www.gofo.com/es/en/) – U.S.-founded last-mile logistics provider, three failed delivery attempts and two-week distribution-centre storage
- [GOFO Spain: Spanish Home](https://www.gofo.com/es/) – Spanish tracking portal and POD access
- [GOFO Spain: Who We Are](https://www.gofo.com/es/en/who-we-are) – founded August 2023
- [GOFO Spain: Ship With Us](https://www.gofo.com/es/ship-with-us) – operations across US, Canada, France, Netherlands, Italy and Spain
- [GOFO Spain: Tracking Results](https://www.gofo.com/es/tracking-results/) – multi-parcel tracking portal
- [GOFO Spain: Customer Support (English)](https://www.gofo.com/es/en/contact-us/) – `cs@es.gofo.com`, Mon–Fri `09:00–18:00`, recipient hotline "Coming soon", POD/missing-parcel steps and sender-claim rule
- [GOFO Spain: Contacto (Spanish)](https://www.gofo.com/es/contact-us/) – Spanish contact page (help URL linked by Peek)
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty on consignments up to €150 and €2 EU handling fee
- [Universal Parcel Scraper: gofo-es](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo-es) – `GFES`/`CIES` detection with `gofo-europe` checksum, `gofo.com/es` link rules, numeric operation codes (`100`, `200`–`208`, `257`/`264`), `Europe/Madrid` local clocks, dropped POD/detail fields and help URL
:::
