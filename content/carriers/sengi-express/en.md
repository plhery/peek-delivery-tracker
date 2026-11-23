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

Sengi gives a parcel for Italy its SDA waybill before SDA knows it, so Poste Italiane's tracker may not find the number at first; Sengi's tracker follows the parcel from its Chinese warehouse to the recipient in Italy. When selected manually, Sengi's tracker also accepts other 8- to 40-character alphanumeric partner waybills. See [tracking number formats](guide:tracking-number-formats).

## What Sengi Express tracking statuses mean

Sengi Express publishes English (and Chinese) cross-border scan descriptions alongside optional event codes and timestamps:

| Sengi Express says | What it means for you | Peek shows |
| --- | --- | --- |
| `Order information received. We're expecting your parcel to arrive with us.` (code `10`) | Merchant transmitted electronic shipment data before physical intake at Sengi's China warehouse. | "Label created" |
| `Arrived at Destinated Airport`, `Terminal Notification of Arrival` | International flight landed at the European destination airport gateway (such as MXP, AMS, LGG or CDG). | "In transit" |
| `Arrived at customs clearance warehouse` | Parcel entered the European customs clearance bonded facility. | "At customs" |
| `Customs clearance inspection, Reason: supposed as undervaluation, waiting for customs check(VM)` | Selected by European customs authorities for valuation or content verification (`VM`). | "At customs" |
| `Ready for transit to last mile operator` | Cleared customs and queued for handover to [Poste Italiane](carrier:poste-italiane) or the destination courier. | "In transit" |

A scan with another wording takes its stage from that wording. Sengi says recipients can also follow the parcel on [Poste Italiane](carrier:poste-italiane)'s website, under the same `...C...F` waybill or the new number when Sengi relabels the parcel.

## Direct injection, European customs gateways and Poste Italiane delivery

According to [Sengi Express About](https://portal.sengiexpress.com/about/) and [Sengi Express Products](https://portal.sengiexpress.com/product/):

- **European air gateways:** Sengi runs direct B2C and B2B customs clearance at Milan Malpensa (`MXP`), Amsterdam (`AMS`), Liège (`LGG`) and Paris Charles de Gaulle (`CDG`).
- **Poste Italiane last-mile services:** in Italy, Sengi injects parcels into Poste Italiane's **Crono** standard parcel service, **Crono Express**, **Extralarge** (oversized / FBA final mile), **Crono ADP** (pickup lockers) and **Crono Reverse** (returns).
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

When you enter a 13-character `...C...F` code (`482C73918462F`, invented), [Peek](/) asks [Poste Italiane](carrier:poste-italiane) first, then AliExpress and Sengi Express (Sengi before AliExpress in Italy; in China AliExpress first, then Sengi), and shows "Sengi Express has this parcel." when Sengi holds it. For an `...MT` code (`SENGI482917360MT`, invented), Peek asks [MaltaPost](carrier:maltapost), whose numbers end in `MT` too: for that code or another partner waybill, tap "Change" while adding or "Change carrier" on the parcel screen and select **Sengi Express** (searching "Sengi" finds it too).

Peek reads Sengi's tracker and shows the stage ("Label created", "In transit", "At customs", or another stage when a scan's wording says so), Sengi's English wording (its Chinese wording when there is no English) and the place. The event codes set the stage and stay out of every Peek app. Sengi states an offset for its scan times; a scan without one is dated on its place's time zone (Italy's for `MXP`, Beijing's for Guangzhou), or else on the zone of the scan beside it, and Peek shows them all on your device's clock. On Sengi's Poste Italiane line, Peek checks that Poste Italiane has the parcel: the parcel then shows "Delivery with" and Poste Italiane's name, and lists the new number when Sengi relabels the parcel.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day Sengi shows an out-for-delivery scan, which it hasn't so far, every 2 minutes from 6 a.m. on the newest scan's clock, while Sengi's tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When Sengi's tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Sengi Express.", with a "Get help from Sengi Express" link to Sengi's contact page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Sengi Express."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs, edit delivery addresses or contact Sengi Express or Poste Italiane for you.

## Questions about Sengi Express tracking

### What is Sengi Express?

Sengi Express (*Guangzhou Sengi Logistics Co., Ltd.*) is a China-to-Italy and China-to-Europe cross-border e-commerce logistics carrier controlled by **Poste Italiane**, handling origin consolidation, European customs clearance (MXP, AMS, LGG, CDG) and Poste Italiane Crono last-mile injection.

### Why does my Poste Italiane `...C...F` code show up on Sengi Express first?

Sengi gives a parcel for Italy its SDA / [Poste Italiane](carrier:poste-italiane) waybill (`482C73918462F`, invented) before SDA knows it. Sengi's tracker shows the flight and customs scans, while Poste Italiane's may not find the number at first.

### What does `supposed as undervaluation, waiting for customs check(VM)` mean?

European customs flagged the parcel for a declared-value inspection (`VM`). The customs broker or seller handles the clearance paperwork; contact your seller if it stays there.

### How do I contact Sengi Express customer service?

Contact your online store or marketplace first for refunds or customs documents. Sengi Express lists `service@sengiexpress.com` and an online contact form at [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).

:::sources
- [Sengi Express: Tracking Portal](https://portal.sengiexpress.com/home) – public waybill tracker and *Guangzhou Sengi Logistics Co., Ltd.* imprint
- [Sengi Express: About Us](https://portal.sengiexpress.com/about/) – Poste Italiane majority ownership, cross-border services and `service@sengiexpress.com`
- [Sengi Express: Products & Services](https://portal.sengiexpress.com/product/) – MXP/AMS/LGG/CDG customs gateways, Poste Italiane Crono / Crono Express / Extralarge / Crono ADP / Crono Reverse and Sennder EU trucking
- [Sengi Express: Contact](https://portal.sengiexpress.com/contact/) – official recipient/customer contact page (help URL linked by Peek)
- [Universal Parcel Scraper: sengi-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sengi-express) – `...C...F` and `...MT` detection rules, `api-v2/tracking/web` parser, status mapping, stated UTC offset handling and Poste Italiane `PI` / `relabelTrackingNumber` handoff
:::
