---
title: BFE Express tracking — numbers, statuses, and delivery
description: Track BFE Express parcels to Argentina, Peru, Uruguay, and the US. Check CK and WYB tracking numbers, customs handoffs, statuses, and regional support emails.
slug: bfe-express-tracking
published: 2026-11-23
updated: 2026-11-23
---

BFE Express (*BFEexpress*, operated by Hongkong Bfe Info Tech Co Limited and established in 2005) is a cross-border e-commerce logistics and final-mile delivery network connecting international sellers — including Temu Argentina merchants — with recipients in Argentina, Peru, Uruguay, and the United States. The company provides international freight, customs clearance, local warehousing, and hyper-local last-mile delivery tracked through [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking).

Because BFE Express manages the international arrival and customs clearance leg before handing parcels over to regional last-mile depots, a single tracking timeline typically combines English airport/customs scans with Spanish local delivery scans.

## BFE Express tracking number formats

Peek takes BFE Express numbers of 6 to 50 letters and digits. BFE Express's contact form takes these layouts, by destination:

| Format (invented) | Destination & details |
| --- | --- |
| `CK54829173604518` | `CK54` + 12 digits (16 characters) — Argentina; no other carrier in Peek has this shape. |
| `CK548291736045182` | `CK54` + 13 digits (17 characters) — alternate Argentina format (overlaps in length with [Colis Privé](carrier:colis-prive)). |
| `CK512829173604518` | `CK512` + 12 digits (17 characters) — Peru format (overlaps in length with [Colis Privé](carrier:colis-prive)). |
| `CK598829173604518` | `CK598` + 12 digits (17 characters) — Uruguay format (overlaps in length with [Colis Privé](carrier:colis-prive)). |
| `WYB482917360` | `WYB` + 9 digits (12 characters) — Peru waybill format (overlaps with [Purolator](carrier:purolator) and [TNT Australia](carrier:tnt-au)). |

The digits after `CK` follow the destination: `54` for Argentina, `512` for Peru and `598` for Uruguay. If you paste a direct link formatted as `bfeexpress.com/track?track_number=...`, Peek extracts the code and selects BFE Express immediately. For other cross-border number patterns, see the [tracking number formats guide](guide:tracking-number-formats).

## What BFE Express tracking statuses mean

BFE Express's tracker lists two groups of rows, the international first leg and the last mile, and Peek reads both.

| BFE Express scan wording | What it means |
| --- | --- |
| `Arrived at Airport of destination` | The flight carrying your parcel landed in the destination country. |
| `Transport to next facility` | Moving between the airport terminal and the local clearance or sorting depot. |
| `Customs declaration completed` | Destination customs clearance finished, before the hand-over to the last mile. |
| `Turned over to last mile facility` | Handed over from BFE's clearance leg to the local delivering depot. |
| `Se ha recibido el paquete.` / `Tu paquete fue recibido por en: ...` | Received at the local Argentina or Uruguay last-mile depot. |
| `En embarque` / `En transporte` / `Tu paquete está en viaje a ...` | Loaded and travelling between local domestic depots toward your city. |
| `Tu paquete se encuentra en ...` / `En espera para salir a reparto en ...` | Sitting at the local delivering office awaiting dispatch onto a courier route. |
| `Reparto en curso` | Out with the local delivery driver today. |
| `Entregado` | Delivered to the recipient. BFE's own status for the parcel says `Delivered` beside it. |

BFE Express's English rows put the place first, before a comma (such as `<City>, Customs declaration completed`); its Spanish rows in Argentina put the country first, and the Uruguayan last mile names the place after `en` or `a`. When BFE's own status for the parcel says `Delivered`, Peek marks it delivered even if the newest row says otherwise.

## Customs clearance, last-mile handoffs, and delays

BFE Express's site offers parcel shipping, international freight, customs clearance, local warehousing and last-mile delivery, and courts Temu Argentina sellers. A few points help explain how timelines behave:

- **Before the destination airport:** if BFE Express's tracker shows nothing yet, check the seller's order page, and read [when tracking stops updating](guide:tracking-not-updating).
- **Customs and the last mile:** `Customs declaration completed` and `Turned over to last mile facility` can post an hour apart at the same place, with the local depot's receipt (`Se ha recibido el paquete.` or `Tu paquete fue recibido por en: ...`) a day later. If a parcel pauses at destination customs, check the [customs guide](guide:customs).
- **Planned delivery day:** BFE Express can give a planned delivery day while the parcel is on its way, and Peek shows it. Peek drops it once the parcel is delivered, returned or ready for pickup, or once a scan is later than that day.
- **Marked delivered or damaged:** BFE Express's [Customer Support page](https://www.bfeexpress.com/contactUS) lists both among its FAQ and says to have your tracking number ready when contacting your local team. See also the [delivered not received guide](guide:delivered-not-received).

For domestic parcels in Argentina handled directly by local carriers, compare with [OCA](carrier:oca) and [Andreani](carrier:andreani).

## Tracking BFE Express with Peek

Paste your `CK...` or `WYB...` code into Peek, which follows more than 4,500 carriers. Its shape alone doesn't tell Peek it's BFE Express's, so while you add it, Peek asks BFE Express ("Checking tracking services…") and shows "BFE Express has this parcel." when BFE Express knows the number. For a 16-character `CK54` code it asks BFE Express alone. For a 17-character `CK` code it also asks [Colis Privé](carrier:colis-prive): first in France, and elsewhere only once BFE Express and tracking services have missed the number. For a `WYB` code it asks BFE Express, [TNT Australia](carrier:tnt-au) and [Purolator](carrier:purolator): BFE Express first in Argentina, Peru, Uruguay and the US, Purolator first in Canada, and TNT Australia first elsewhere. To follow a parcel with BFE Express from the start, press "Change" and pick BFE Express under "Fits this number", or use "Change carrier" on a parcel you've already added. A `bfeexpress.com/track?track_number=...` link goes to BFE Express at once.

Peek queries BFE Express's tracking API directly through the open-source [universal-parcel-scraper BFE Express adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express). It reads both groups of rows in time order, takes the city, branch or country as the place, and keeps the planned delivery day and the destination country. A time with its GMT offset keeps that instant. A time without one is dated on its place's zone (Buenos Aires time for Ezeiza, Lima time for Lima, Montevideo time for Montevideo), a scan without a place on its neighbour's, else on Buenos Aires time, and every scan shows on your device's clock. Street addresses and proof-of-delivery photos shown on BFE's portal are left out.

Peek shows BFE Express's rows as **In transit**, except `Reparto en curso`, which is **Out for delivery**, and `Entregado`, which is **Delivered**. It also marks the parcel **Delivered** when BFE's own status for it says `Delivered`.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day BFE Express shows `Reparto en curso`, every 2 minutes from 6 a.m. on the newest scan's clock, while BFE Express's tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When BFE Express doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking BFE Express.", with a "Get help from BFE Express" link to BFE Express's Contact Us page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking BFE Express."

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

Keep your full `CK...` or `WYB...` tracking number ready when emailing your country desk.

## Frequently asked questions

### Why does my BFE Express tracking start at the destination airport with no earlier scans?

BFE Express's tracker can start at `Arrived at Airport of destination`, the flight's arrival in the destination country. Before that, check the seller's order page for its dispatch.

### Why are my BFE Express scans in both English and Spanish?

BFE Express records the international airport arrival, customs declaration, and last-mile handover in English, while the domestic courier depot handling the final delivery leg posts its scans in Spanish (ending with `Reparto en curso` and `Entregado`).

### What do the digits `54`, `512`, and `598` in `CK` tracking numbers mean?

They identify the destination country: `CK54...` is used for Argentina (16 or 17 characters), `CK512...` for Peru (17 characters), and `CK598...` for Uruguay (17 characters). Peru shipments can also use a 12-character `WYB` + 9 digits format.

### What if BFE Express doesn't find my number?

Check for missing characters in your `CK` or `WYB` code, and ask the seller whether it has handed the parcel to BFE Express yet.

:::sources
- [BFE Express](https://www.bfeexpress.com/)
- [BFE Express Tracking](https://www.bfeexpress.com/tracking)
- [BFE Express Customer Support](https://www.bfeexpress.com/contactUS)
- [universal-parcel-scraper BFE Express adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express)
:::
