---
title: One by Allegro CZ (WE|DO) tracking — numbers & delivery
description: Track One by Allegro CZ (formerly WE|DO, IN TIME, Uloženka) parcels in Czechia. Check 11-char codes, One Box 48h pickup, One Point 5-day hold, and support.
slug: one-by-allegro-cz-tracking
published: 2026-11-23
updated: 2026-11-23
---

One by Allegro CZ (formerly known as **WE|DO**, **IN TIME spedice**, and **Uloženka**, operated by Allegro Retail a.s.) is a Czech e-commerce parcel carrier delivering orders from allegro.cz and independent online shops nationwide through [onedelivery.cz](https://onedelivery.cz/). Recipients receive parcels via three services: **One Box** (over 5,244 self-service lockers including partner AlzaBox and OX Box units), **One Point** (1,960 partner pickup shops), and **One Courier** (doorstep delivery across Czechia with over 350 vans and couriers).

You can check a Czech parcel on [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky) or paste your 11-character tracking number into the box above.

## One by Allegro CZ (WE|DO) tracking number format

Czech One by Allegro / WE|DO shipments use a compact **11-character alphanumeric code**:

| Format (invented) | What it is |
| --- | --- |
| `2BM48291736` | 1 digit + 1 letter + 1 letter/digit + 8 digits (11 characters total) — standard One by Allegro CZ (WeDo) tracking number. |
| `48P48291736` | 2 digits + 1 letter + 8 digits (11 characters total) — alternate Czech One by Allegro (WeDo) parcel format. |

Prefixes starting with `7D` + digit or `7X` + digit are excluded from this rule; all other matching 11-character codes identify **One by Allegro CZ** unambiguously with high confidence. Peek also parses direct links from `onedelivery.cz/sledovani-zasilky?numer=...`, `onedelivery.cz/?numer=...`, and legacy `trace.wedo.cz/index.php?orderNumber=...` or `trace.intime.cz/index.php?orderNumber=...` URLs. Polish Allegro One parcels use a separate tracker — see [Allegro One](carrier:allegro-one) and the [tracking number formats guide](guide:tracking-number-formats).

## What One by Allegro CZ tracking statuses mean

One by Allegro CZ's guest tracking API returns standardized English logistics milestones with explicit timezone-offset timestamps (`Europe/Prague`).

| One by Allegro CZ wording | What it means |
| --- | --- |
| `Parcel has been prepared by the sender` | The shop created the shipment label; the courier has not collected the parcel yet. |
| `Parcel has been picked up by the courier` | One by Allegro CZ collected the parcel from the sender. |
| `Parcel has been dispatched to its destination point` | Left the origin hub toward the destination region or pickup network. |
| `Parcel has been accepted at the sorting center` | Processed inside a One by Allegro sorting hub. |
| `Parcel has been accepted at the branch` | Arrived at the local delivery depot serving your address or pickup point. |
| `Parcel has been released for delivery` | Loaded onto a delivery van for courier delivery or placement into your chosen One Box / One Point today. |
| `Parcel is awaiting pick-up` | Ready to collect at your One Box locker or One Point pickup shop (check your SMS, email, or app for your pickup PIN). |
| `Parcel has been delivered` | Handed over to the recipient or collected from the locker or pickup point. |

If the tracker returns an unusual non-standard message that might include personal recipient text, Peek replaces that line with `Tracking update` while preserving its timestamp in the history.

## One Box, One Point, and One Courier delivery rules

One by Allegro CZ publishes clear pickup deadlines and courier delivery rules across [onedelivery.cz](https://onedelivery.cz/):

- **One Box lockers ([onedelivery.cz/one-box](https://onedelivery.cz/one-box)):** Accessible **24/7** across 5,244+ lockers (One Box, AlzaBox, and OX Box). Collect within **48 hours** of delivery notification by entering the recipient's phone number and numeric PIN sent via SMS, email, the One by Allegro app, or the Allegro app (no app required at the screen). Cash-on-delivery (*dobírka*) can be paid via the payment link in your notice or at the One Box card terminal.
- **One Point pickup shops ([onedelivery.cz/one-point](https://onedelivery.cz/one-point)):** Across 1,960 partner shops during store opening hours, parcels normally wait **5 business days** (extendable free of charge by up to **3 additional business days** through customer service; during the busy pre-Christmas peak season, One Point storage shortens from 5 days to **3 days**, while One Box stays at 48 hours). Maximum One Point limits are **20 kg**, longest side **1.2 m**, and volume **0.2 m³**. COD can be paid in cash or by card where the shop has a terminal.
- **One Courier doorstep delivery ([onedelivery.cz/one-courier](https://onedelivery.cz/one-courier)):** Standard delivery is the **next working day (`D+1`)** after pickup, on **weekdays between 08:00 and 18:00**, to the first lockable door (couriers are not required to carry parcels upstairs, and P.O. Box delivery is unavailable). On delivery day you receive SMS, email, or app notice. COD can be paid in cash (exact amount requested) or by card with no surcharge. You can change the delivery date or redirect an eligible courier parcel to a pickup point via the One by Allegro mobile app or customer service; address changes go through customer service.

If you miss a pickup window or need help locating a parcel, see the [parcel lockers guide](guide:parcel-lockers), [uncollected parcel guide](guide:uncollected-parcel), and [when tracking stops updating](guide:tracking-not-updating). For other Czech parcel networks, compare with [Packeta](carrier:packeta), [PPL](carrier:ppl), and [Česká pošta](carrier:ceska-posta).

## Tracking One by Allegro CZ with Peek

Paste your 11-character One by Allegro CZ / WE|DO code — or an `onedelivery.cz` or legacy `trace.wedo.cz` link — into Peek, and Peek recognizes **One by Allegro CZ** immediately. If you ever need to select or change the carrier manually, use **Change** while adding the parcel or **Change carrier** on the tracking screen.

Peek queries One by Allegro CZ's public tracking endpoint (`edge.onedelivery.cz`) directly through the open-source [universal-parcel-scraper One by Allegro CZ adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo), with an automatic retry on network timeouts. It records exact timezone-offset timestamps and the delivery instant (`delivered_at`), while discarding free-form detail messages that could leak personal data. The public guest API does not include an estimated delivery date, depot city, or parcel weight.

Peek maps the eight standard milestones cleanly: **Label created** (`Parcel has been prepared by the sender`), **Received by carrier** (`Parcel has been picked up by the courier`), **In transit** (dispatched, sorting center, and branch scans), **Out for delivery** (`Parcel has been released for delivery`), **Ready for pickup** (`Parcel is awaiting pick-up`), and **Delivered** (`Parcel has been delivered`).

If scans pause overnight or over a weekend, Peek displays: "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking One by Allegro CZ." After more than three weeks without a new update, Peek shows: "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking One by Allegro CZ." You can open official Czech support directly from the parcel card via **Get help from One by Allegro CZ**.

In Peek's alert settings, choose **Every scan**, **Important steps**, or **Delivery only** to receive the notifications you want.

## Contacting One by Allegro CZ

- **Parcel inquiry form & support:** [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt) (click *Dotaz na zásilku* for buyer and seller shipment questions, lost pickup PINs, free 3-day One Point extensions, or address changes)
- **Official tracking page:** [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky)
- **Service information:** [One Box](https://onedelivery.cz/one-box), [One Point](https://onedelivery.cz/one-point), and [One Courier](https://onedelivery.cz/one-courier)
- **Main website:** [onedelivery.cz](https://onedelivery.cz/)

Have your 11-character tracking number and recipient phone number or email ready when submitting a support inquiry so customer service can resend a pickup PIN or update delivery instructions quickly.

## Frequently asked questions

### Are WE|DO, IN TIME, and One by Allegro CZ the same carrier?

Yes. WE|DO (which earlier brought together IN TIME spedice and Uloženka) was rebranded to **One by Allegro** in Czechia (`onedelivery.cz`), operated by Allegro Retail a.s. Legacy `trace.wedo.cz` and `trace.intime.cz` links and 11-character tracking numbers all track under One by Allegro CZ in Peek.

### How long does a parcel wait in One Box or at a One Point?

In **One Box** (including AlzaBox and OX Box), parcels wait **48 hours** from notification. At **One Point** pickup shops, standard storage is **5 business days** (extendable free by up to 3 business days via customer service), shortened to **3 days** during the pre-Christmas peak season.

### Can I pay cash on delivery (dobírka) by card?

Yes. One Courier drivers accept cash (exact amount requested) or card with no fee for the recipient; One Box accepts card payment via the notification payment link or locker terminal; and One Point accepts cash or card where the shop has a payment terminal.

### What if I lost my One Box or One Point pickup code?

Pickup codes are sent by SMS, email, and in the One by Allegro and Allegro apps. If you cannot find the code anywhere, submit a shipment inquiry on [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt) so customer service can resend it.

:::sources
- [One by Allegro CZ (onedelivery.cz)](https://onedelivery.cz/)
- [One by Allegro CZ — Sledování zásilky](https://onedelivery.cz/sledovani-zasilky)
- [One by Allegro CZ — One Box](https://onedelivery.cz/one-box)
- [One by Allegro CZ — One Point](https://onedelivery.cz/one-point)
- [One by Allegro CZ — One Courier](https://onedelivery.cz/one-courier)
- [One by Allegro CZ — Kontakt](https://onedelivery.cz/kontakt)
- [universal-parcel-scraper One by Allegro CZ adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo)
:::
