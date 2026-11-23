---
title: Sengi Express tracking: China–Italy codes & Poste handoff
description: Track a Sengi Express (Poste Italiane group) parcel from China to Italy or Europe: SDA/MT codes, customs and Crono handoffs, delays and service contacts.
slug: sengi-express-tracking
published: 2026-11-23
updated: 2026-11-23
---

**Sengi Express** (*Guangzhou Sengi Logistics Co., Ltd.* / *Seng Yi (Shenzhen) Global Supply Chain Management Co., Ltd.*, Chinese *广州森吉物流有限公司*) is a cross-border logistics operator majority-controlled by **Poste Italiane** ([portal.sengiexpress.com/about/](https://portal.sengiexpress.com/about/)). Specializing in China-to-Italy and China-to-Europe e-commerce direct injection, Sengi manages origin consolidation in China, air and sea freight, European B2C/B2B customs clearance at gateways such as Milan Malpensa (**MXP**), Amsterdam (**AMS**), Liège (**LGG**) and Paris (**CDG**), pan-European trucking via Poste Italiane group's Sennder, and final doorstep or locker delivery in Italy through [Poste Italiane](carrier:poste-italiane)'s Crono network. You can track a parcel on [portal.sengiexpress.com](https://portal.sengiexpress.com/home) or paste your code into the box above.

## Sengi Express tracking numbers (SDA `...C...F` & `MT` formats)

Sengi Express frequently issues a 13-character **Poste Italiane / SDA** waybill right from China so the same code works all the way from Sengi's Chinese warehouse to Italian doorstep delivery, alongside 16-character `MT`-ending cross-border codes:

| Format (invented) | What it is |
| --- | --- |
| `482C73918462F` | 13-character SDA / Poste Italiane waybill (`3 digits` + `C` + `8 digits` + `F`) tracked by Sengi Express before Poste Italiane scans it in Italy. |
| `SENGI482917360MT` | 16-character cross-border Sengi code (`5 letters` + `9 digits` + `MT`). |

Because Sengi prints the Italian `...C...F` barcode at origin in China, Poste Italiane's own website often shows nothing until the parcel clears European customs and enters Italy; tracking with Sengi Express reveals the entire China-to-Europe first leg in the meantime. When selected manually, Sengi's tracker also accepts other 8- to 40-character alphanumeric partner waybills. See [tracking number formats](guide:tracking-number-formats).

## What Sengi Express tracking statuses mean

Sengi Express publishes English (and Chinese) cross-border scan descriptions alongside optional event codes and timestamps:

| Sengi Express says | What it means for you | Peek shows |
| --- | --- | --- |
| `Order information received. We're expecting your parcel to arrive with us.` (code `10`) | Merchant transmitted electronic shipment data before physical intake at Sengi's China warehouse. | "Label created" |
| `Arrived at Destinated Airport`, `Terminal Notification of Arrival` | International flight landed at the European destination airport gateway (such as MXP, AMS, LGG or CDG). | "In transit" |
| `Arrived at customs clearance warehouse` | Parcel entered the European customs clearance bonded facility. | "At customs" |
| `Customs clearance inspection, Reason: supposed as undervaluation, waiting for customs check(VM)` | Selected by European customs authorities for valuation or content verification (`VM`). | "At customs" |
| `Ready for transit to last mile operator` | Cleared customs and queued for handover to [Poste Italiane](carrier:poste-italiane) or the destination courier. | "In transit" |

Other scans — including origin acceptance, out-for-delivery, delivery attempts and final delivery — are classified from Sengi's English scan wording. Once injected into Italy, recipients can also check the parcel directly on [Poste Italiane](carrier:poste-italiane) using the same `...C...F` barcode (or any relabelled tracking number Sengi attaches).

## Direct injection, European customs gateways and Poste Italiane delivery

According to [Sengi Express About](https://portal.sengiexpress.com/about/) and [Sengi Express Products](https://portal.sengiexpress.com/product/):

- **European air gateways:** Sengi runs direct B2C and B2B customs clearance at Milan Malpensa (`MXP`), Amsterdam (`AMS`), Liège (`LGG`) and Paris Charles de Gaulle (`CDG`).
- **Poste Italiane last-mile services:** in Italy, Sengi injects parcels into Poste Italiane's **Crono** standard parcel service, **Crono Express**, **Extralarge** (oversized / FBA final mile), **Crono ADP** (pickup points and lockers) and **Crono Reverse** (returns).
- **Customs valuation checks (`VM`) & EU fees:** shipments from China into the EU must comply with EU customs rules, import VAT (IOSS or paid on delivery) and EU customs/handling charges on non-EU orders. If tracking shows `supposed as undervaluation, waiting for customs check(VM)`, customs is verifying the declared value with the importer/broker — wait for clearance or alert your online seller. See [parcels held at customs](guide:customs).

## Sengi Express parcel stuck, quiet or missing

Because online marketplaces and Chinese merchants hold the shipping contract with Sengi Express, **always contact your seller or marketplace first** for refunds, reshipments or customs-valuation documentation.

- **Quiet gap before Italian scans appear:** parcels commonly pause for several days between `Order information received`, `Arrived at Destinated Airport` and `Ready for transit to last mile operator` while air cargo clears European customs batches and moves by linehaul truck into Italy. See [why tracking stops updating](guide:tracking-not-updating).
- **Follow on Poste Italiane after handover:** once `Ready for transit to last mile operator` appears on an Italian `PI` shipment, tracking the same `...C...F` number (or the relabelled number Peek shows) on [Poste Italiane](carrier:poste-italiane) reveals Italian sorting, delivery attempts and Punto Poste / locker collection details.
- **Lost, damaged or marked delivered:** check with neighbours and your local Poste Italiane branch/Punto Poste first, then open a marketplace dispute before buyer protection closes. See [delivered but not received](guide:delivered-not-received), [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

## Sengi Express customer service and contacts

Official contacts published on [Sengi Express About](https://portal.sengiexpress.com/about/) and [Sengi Express Contact](https://portal.sengiexpress.com/contact/):

- **Email:** `service@sengiexpress.com`.
- **Online contact form:** [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).
- **Operator:** *Guangzhou Sengi Logistics Co., Ltd.* (*广州森吉物流有限公司*, ICP `粤ICP备2021101066号`), majority-controlled by Poste Italiane.

> Beware of fake text messages claiming a Poste Italiane or customs fee must be paid through an unfamiliar link. Verify any genuine import fee directly on your marketplace order page or Poste Italiane's official website. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Sengi Express parcel with Peek

When you enter a 13-character `...C...F` code (`482C73918462F`, invented), [Peek](/) checks Sengi Express automatically alongside [Poste Italiane](carrier:poste-italiane) and AliExpress — catching early China-to-Europe scans before Poste Italiane activates the barcode — and shows `"Sengi Express has this parcel."` when Sengi holds it. For `...MT` codes (`SENGI482917360MT`, invented) or other partner waybills, tap "Change" while adding or "Change carrier" on the parcel screen and select **Sengi Express** (searching "Sengi" finds it too).

Peek queries Sengi Express's tracking API directly and displays the mapped stage ("Label created", "In transit", "At customs", plus "Received by carrier", "Out for delivery", "Ready for pickup", "Delivery attempted", "Needs attention", "Returned to sender" and "Delivered" when scan wording matches), English scan descriptions and event codes, locations, automatic handoff to [Poste Italiane](carrier:poste-italiane) on `PI` channel parcels plus any relabelled tracking number when Sengi changes the barcode, and exact timestamps built from Sengi's stated UTC offset (keeping local wall-clock time if no offset is supplied). Sengi's API does not return an estimated delivery date, weight, signatory name or separate delivery timestamp.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. local time on the day an out-for-delivery scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12"), shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Sengi Express." and displays "Get help from Sengi Express", which opens Sengi Express's contact page. After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Sengi Express."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs, edit delivery addresses or contact Sengi Express or Poste Italiane for you.

## Questions about Sengi Express tracking

### What is Sengi Express?

Sengi Express (*Guangzhou Sengi Logistics Co., Ltd.*) is a China-to-Italy and China-to-Europe cross-border e-commerce logistics carrier controlled by **Poste Italiane**, handling origin consolidation, European customs clearance (MXP, AMS, LGG, CDG) and Poste Italiane Crono last-mile injection.

### Why does my Poste Italiane `...C...F` code show up on Sengi Express first?

Sengi prints the Italian SDA / [Poste Italiane](carrier:poste-italiane) barcode (`482C73918462F`, invented) at its warehouse in China. Until the parcel clears European customs and is inducted in Italy, only Sengi Express shows the first-leg flight and customs checkpoints.

### What does `supposed as undervaluation, waiting for customs check(VM)` mean?

European customs flagged the parcel for a declared-value inspection (`VM`). The customs broker or seller handles the clearance paperwork; contact your seller if the inspection lasts more than a week.

### How do I contact Sengi Express customer service?

Contact your online store or marketplace first for refunds or customs documents. Sengi Express lists `service@sengiexpress.com` and an online contact form at [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).

:::sources
- [Sengi Express: Tracking Portal](https://portal.sengiexpress.com/home) – public waybill tracker and *Guangzhou Sengi Logistics Co., Ltd.* imprint
- [Sengi Express: About Us](https://portal.sengiexpress.com/about/) – Poste Italiane majority ownership, cross-border services and `service@sengiexpress.com`
- [Sengi Express: Products & Services](https://portal.sengiexpress.com/product/) – MXP/AMS/LGG/CDG customs gateways, Poste Italiane Crono / Crono Express / Extralarge / Crono ADP / Crono Reverse and Sennder EU trucking
- [Sengi Express: Contact](https://portal.sengiexpress.com/contact/) – official recipient/customer contact page (help URL linked by Peek)
- [Universal Parcel Scraper: sengi-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sengi-express) – `...C...F` and `...MT` detection rules, `api-v2/tracking/web` parser, status mapping, stated UTC offset handling and Poste Italiane `PI` / `relabelTrackingNumber` handoff
:::
