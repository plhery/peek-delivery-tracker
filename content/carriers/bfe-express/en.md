---
title: BFE Express tracking — numbers, statuses, and delivery
description: Track BFE Express parcels to Argentina, Peru, Uruguay, and the US. Check CK and WYB tracking numbers, customs handoffs, statuses, and regional support emails.
slug: bfe-express-tracking
published: 2026-11-23
updated: 2026-11-23
---

BFE Express (*BFEexpress*, operated by Hongkong Bfe Info Tech Co Limited and established in 2005) is a cross-border e-commerce logistics and final-mile delivery network connecting international sellers — including Temu Argentina merchants — with recipients in Argentina, Peru, Uruguay, and the United States. The company provides international air freight, customs clearance, local warehousing, and hyper-local last-mile delivery tracked through [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking).

Because BFE Express manages the international arrival and customs clearance leg before handing parcels over to regional last-mile depots, a single tracking timeline typically combines English airport/customs scans with Spanish local delivery scans.

## BFE Express tracking number formats

BFE Express's public search accepts shipment codes of 6 to 50 letters and digits. Cross-border orders heading to South America commonly use four structured patterns:

| Format (invented) | Destination & details |
| --- | --- |
| `CK54829173604518` | `CK54` + 12 digits (16 characters) — Argentina format, unique candidate for BFE Express. |
| `CK548291736045182` | `CK54` + 13 digits (17 characters) — alternate Argentina format (overlaps in length with [Colis Privé](carrier:colis-prive)). |
| `CK512829173604518` | `CK512` + 12 digits (17 characters) — Peru format (overlaps in length with [Colis Privé](carrier:colis-prive)). |
| `CK598829173604518` | `CK598` + 12 digits (17 characters) — Uruguay format (overlaps in length with [Colis Privé](carrier:colis-prive)). |
| `WYB482917360` | `WYB` + 9 digits (12 characters) — Peru waybill format (overlaps with [Colis Privé](carrier:colis-prive), [Purolator](carrier:purolator), and [TNT Australia](carrier:tnt-au)). |

Notice the country dialling digits inside the `CK` prefix: `54` for Argentina, `512` for Peru, and `598` for Uruguay. If you paste a direct link formatted as `bfeexpress.com/track?track_number=...`, Peek extracts the code and selects BFE Express immediately. For other cross-border number patterns, see the [tracking number formats guide](guide:tracking-number-formats).

## What BFE Express tracking statuses mean

BFE Express splits each shipment into two legs: `firstLegTrackingDetails` (international linehaul, destination airport arrival, and customs clearance in English) and `supplierTrackingDetails` (local hub processing and doorstep delivery in Spanish).

| BFE Express scan wording | What it means |
| --- | --- |
| `Arrived at Airport of destination` | The flight carrying your parcel landed in the destination country — often the first public scan shown on Argentina orders. |
| `Transport to next facility` | Moving between the airport terminal and the local clearance or sorting depot. |
| `Customs declaration completed` | Destination customs clearance finished without a hold, shortly before last-mile handover. |
| `Turned over to last mile facility` | Handed over from BFE's clearance leg to the local delivering depot. |
| `Se ha recibido el paquete.` / `Tu paquete fue recibido por en: ...` | Received at the local Argentina or Uruguay last-mile depot. |
| `En embarque` / `En transporte` / `Tu paquete está en viaje a ...` | Loaded and travelling between local domestic depots toward your city. |
| `Tu paquete se encuentra en ...` / `En espera para salir a reparto en ...` | Sitting at the local delivering office awaiting dispatch onto a courier route. |
| `Reparto en curso` | Out with the local delivery driver today. |
| `Entregado` / `Delivered` | Delivered to the recipient's address. |

BFE Express's English rows usually prefix the place name before a comma (such as `<City>, Customs declaration completed`), while Spanish last-mile rows embed the branch or city after `en`. When the last scan wording is generic, BFE Express's top-level shipment status (`Delivered`) still confirms final delivery.

## Customs clearance, last-mile handoffs, and delays

Most BFE Express orders originate from overseas e-commerce marketplaces such as Temu and enter Argentina, Peru, or Uruguay by air consolidation before local courier delivery. A few points help explain how timelines behave:

- **Silent gap before destination arrival:** On many orders, BFE Express does not display early pre-departure scans on its public tracker until the consolidation lands (`Arrived at Airport of destination`). During that initial air leg, check the seller's order page if you want export departure confirmation, and read [when tracking stops updating](guide:tracking-not-updating) for typical international wait times.
- **Quick customs handoff:** When import paperwork is pre-filed by the platform, `Customs declaration completed` and `Turned over to last mile facility` often post within hours of each other, followed a day later by the local depot receipt scan (`Se ha recibido el paquete.` or `Tu paquete fue recibido por en: ...`). If a parcel pauses at destination customs, check the [customs guide](guide:customs).
- **Planned delivery day:** BFE Express often publishes a planned delivery calendar day (`planDeliveryTimeDescription`) while the parcel is in transit. Once delivery completes — or if the latest scan passes that planned day — the forecast no longer applies.
- **Marked delivered or damaged:** BFE Express's [Customer Support FAQ](https://www.bfeexpress.com/contactUS) advises contacting your local country support team with your tracking number ready if a parcel shows delivered without arriving, or arrives damaged. You can also review general steps in the [delivered not received guide](guide:delivered-not-received).

For domestic parcels in Argentina handled directly by local carriers, compare with [OCA](carrier:oca) and [Andreani](carrier:andreani).

## Tracking BFE Express with Peek

Paste your `CK54...`, `CK512...`, `CK598...`, or `WYB...` code — or a full `bfeexpress.com/track?track_number=...` link — into Peek. Because 17-character `CK` codes and 12-character `WYB` codes overlap in shape with [Colis Privé](carrier:colis-prive), [Purolator](carrier:purolator), or [TNT Australia](carrier:tnt-au), you can lock onto BFE Express right away via **Change** when adding the parcel or **Change carrier** on the tracking screen.

Peek queries BFE Express's tracking API directly through the open-source [universal-parcel-scraper BFE Express adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express). It reads both the first-leg and last-mile scan groups in chronological order, extracts the leading city or branch into the location field, keeps exact GMT offset timestamps when reported (falling back to local wall-clock time otherwise), and captures the planned delivery date and destination country code. Street addresses and proof-of-delivery photos shown on BFE's portal are intentionally left out for privacy.

Because BFE Express's public feed typically starts at destination airport arrival rather than publishing separate **Label created** or **Received by carrier** scans, Peek shows active international and domestic depot movements as **In transit**, switches to **Out for delivery** on `Reparto en curso`, and marks **Delivered** on `Entregado` or BFE's `Delivered` status.

If tracking stays quiet during international flights or over a weekend, Peek displays: "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking BFE Express." After more than three weeks without a new scan, Peek shows: "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking BFE Express." To reach BFE's regional desk directly, tap **Get help from BFE Express** on the parcel screen.

Use Peek's alert presets — **Every scan**, **Important steps**, or **Delivery only** — to decide which events send notifications.

## Contacting BFE Express

BFE Express lists regional customer support emails on its [Contact Us page](https://www.bfeexpress.com/contactUS), all staffed **Monday to Friday, 9:00 AM – 6:00 PM** (excluding public holidays):

- **Argentina:** `info@ar.bfeexpress.com`
- **Peru:** `info@pe.bfeexpress.com`
- **Uruguay:** `info@uy.bfeexpress.com`
- **United States:** `info@us.bfeexpress.com`
- **Paraguay:** `info_py@bfeexpress.com`
- **Other supported regions (Colombia, Ecuador, Serbia, Dominica):** `info@other.bfeexpress.com`
- **Official website & tracking:** [bfeexpress.com](https://www.bfeexpress.com/) and [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking)

Keep your full `CK...` or `WYB...` tracking number ready when emailing your country desk; for refunds or missing-item claims on marketplace orders (such as Temu), open a support ticket with the seller alongside your carrier check.

## Frequently asked questions

### Why does my BFE Express tracking start at the destination airport with no earlier scans?

On many cross-border consolidations to Argentina, Peru, and Uruguay, BFE Express only starts posting public scans once the parcel reaches `Arrived at Airport of destination`. That does not mean the parcel missed earlier steps — check the retailer's order timeline for dispatch scans from the overseas warehouse.

### Why are my BFE Express scans in both English and Spanish?

BFE Express records the international airport arrival, customs declaration, and last-mile handover in English, while the domestic courier depot handling the final delivery leg posts its scans in Spanish (ending with `Reparto en curso` and `Entregado`).

### What do the digits `54`, `512`, and `598` in `CK` tracking numbers mean?

They identify the destination country: `CK54...` is used for Argentina (16 or 17 characters), `CK512...` for Peru (17 characters), and `CK598...` for Uruguay (17 characters). Peru shipments can also use a 12-character `WYB` + 9 digits format.

### My BFE Express parcel says "NotFound" — what should I check?

Check for missing characters in your `CK` or `WYB` code and make sure the merchant has already handed the parcel to BFE Express. Newly assigned cross-border codes can take a few days to appear in BFE's public gateway before arrival in the destination country.

:::sources
- [BFE Express](https://www.bfeexpress.com/)
- [BFE Express Tracking](https://www.bfeexpress.com/tracking)
- [BFE Express Customer Support](https://www.bfeexpress.com/contactUS)
- [universal-parcel-scraper BFE Express adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express)
:::
