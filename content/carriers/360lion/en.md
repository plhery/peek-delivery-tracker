---
title: 360Lion tracking: WSH and AR codes, statuses and contacts
description: Track a 360Lion Express parcel from China to Latin America or the US: WSH and AR numbers, scan codes, last-mile handoff, quiet transit gaps and support.
slug: 360lion-tracking
published: 2026-11-23
updated: 2026-11-23
---

360Lion (*360Lion Express*, Chinese *纬狮物流 / 纬狮物联网*) is a Chinese cross-border e-commerce logistics provider founded in 2015 and headquartered in Shenzhen, with branches and operating centres in Shanghai, Guangzhou, Dongguan, Yiwu and Hong Kong. It specialises in dedicated small-parcel air lines, FBA/FBM freight forwarding and overseas warehousing from China to Latin America and the United States (covering Mexico, Chile, Peru, Brazil, Colombia, Ecuador, Argentina, Uruguay, Bolivia, Paraguay, El Salvador, Costa Rica, Guatemala, Venezuela, Panama and the US) for merchants on platforms such as Mercado Libre, Amazon, AliExpress, Shopee, SHEIN, Temu, TikTok Shop, Walmart and Falabella. You can look up shipments on [360Lion's tracking portal](https://toms.360lion.com/track) or [360lion.com/en/](https://www.360lion.com/en/) (up to 50 numbers at once), or paste your tracking number into the box above.

## 360Lion tracking numbers: WSH and AR formats

Check your online marketplace order screen or dispatch confirmation email for the tracking number. 360Lion parcels commonly appear in two shapes:

| Format (invented) | What it is |
| --- | --- |
| `WSHMX4829173640YQ` | 360Lion dedicated-line waybill: `WSH`, a two-letter destination country code (for example `MX` for Mexico), 10 digits and `YQ`. |
| `AR4829173640` | `AR` followed by 10 digits (used on parcels bound for Argentina). Shares this shape with [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) and [Aramex New Zealand](carrier:aramex-nz). |

If your order ships into Brazil or Mexico under another carrier's barcode — such as `AJ` for [Anjun Logistics](carrier:anjun), `8880` for [J&T Express Brazil](carrier:j-and-t-br) or `JMX` for [J&T Express Mexico](carrier:j-and-t-mx) — see [tracking number formats](guide:tracking-number-formats).

## What 360Lion tracking statuses mean

360Lion groups shipments under high-level states (`Processing`, `Pre-alert`, `In transit.`, `Returned`, `Delivered`) and attaches scan codes (`WSO*` at origin in China, `WSD*` at destination). Once the parcel hands off to the destination country's last-mile carrier, the tracker frequently keeps that local carrier's own wording (often in Spanish or Portuguese).

| 360Lion says (code / group) | What it means for you | Peek shows |
| --- | --- | --- |
| `Processing` (`PRECESSING`) | The seller created the shipment order with 360Lion before linehaul scans begin. | "Label created" |
| `HUB` rail step | Accepted into 360Lion's origin sorting hub in China. | "Received by carrier" |
| `departed from airport` (`WSO04`), `arrived at airport` (`WSD00`), `In transit.` (`IN_TRANSIT`) | Flown out of China (with airport code such as PVG) and landed at the destination airport. | "In transit" |
| `CUSTOMS` rail step | Undergoing export or destination import customs clearance. | "At customs" |
| `handover to lastmile` (`WSD02`), `Arrived at sorting center` (`WSD051`), `package on the way to delivery office` (`WSD050`) | Transferred to the destination country's last-mile delivery partner, sorted and dispatched toward the local delivery office. | "In transit" |
| `Returned` (`RETURNED_BACK`) | Returned back toward the sender or origin warehouse. | "Returned to sender" |
| `Delivered` (`DELIVERED`) | Delivered to the recipient by the local last-mile partner. | "Delivered" |

Note that 360Lion's `Pre-alert` (`WARNING` / *预警*) state can mean either an advance notice or a warning flag, so Peek lets the underlying scan lines decide the stage rather than treating `Pre-alert` alone as a failure.

## How 360Lion cross-border delivery works

On [360lion.com/en/](https://www.360lion.com/en/) and [360lion.com](https://www.360lion.com/), 360Lion describes itself as a cross-border linehaul carrier and airline General Sales and Service Agent (GSSA) running regular charter flights from China into Latin America, backed by overseas warehouses in Mexico, Chile, Brazil, Colombia and the US.

**Last-mile delivery.** 360Lion does not operate consumer delivery vans at your doorstep in Latin America or the US. After destination airport arrival (`WSD00`) and import customs clearance, it hands each parcel over (`WSD02`) to a local postal or express partner in your country, which handles final doorstep attempts, pickup points or returns.

**Recipient rules and customs.** Because 360Lion serves business shippers rather than retail consumers, its website does not publish recipient delivery-attempt limits, pickup-point holding periods or transit guarantees for small parcels (its [Terms & FAQ page](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) covers commercial seller rules such as SEND air freight, prohibited goods and 30-day seller claim windows). Import VAT, duties and customs IDs (such as CPF in Brazil, CURP/RFC in Mexico or CUIL/CUIT in Argentina) follow your destination country's customs laws and the marketplace checkout terms. See [parcels held at customs](guide:customs).

## 360Lion parcel stuck, quiet or not delivered

Because 360Lion contracts with the online store or marketplace rather than the buyer, always contact the seller or platform first for late orders, address errors or refunds.

**No updates between China and destination.** Long-haul flights from China to Latin America and destination customs queues regularly create gaps of several days to over a week between `departed from airport` (`WSO04`) and `arrived at airport` (`WSD00`). See [why tracking stops updating](guide:tracking-not-updating).

**Local handoff without a second number.** 360Lion's public tracker relays last-mile wording under your original `WSH` or `AR` number without exposing a separate local tracking code. If delivery fails and you need the local partner's contact or reference, ask the seller or email 360Lion's parcel-inquiry desk.

**Delivered, damaged or lost.** Check with household members, neighbours and building reception first if a parcel shows `Delivered`; if it is missing or damaged, document everything and open a dispute with the seller or marketplace before buyer protection expires. See [delivered but not received](guide:delivered-not-received), [damaged parcel](guide:damaged-parcel), [lost parcel](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## 360Lion customer service and contacts

Contacts published on [360Lion's Contact Us page](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) and [360lion.com/en/](https://www.360lion.com/en/):

- **Parcel inquiry email (*查件业务*):** `customerservice@360lion.com`.
- **General / business email:** `info@360lion.com`.
- **Shenzhen headquarters phone:** `+86 0755-2360-1325` (Shanghai `+86 021-6142-1360`, Guangzhou `+86 020-8653-4133`).
- **Headquarters address:** 7/F, Block A, Star River World, No. 1 Yabao Road, Bantian Street, Longgang District, Shenzhen, Guangdong, China.

> Watch out for fake SMS or WhatsApp messages demanding an urgent payment link to "release" a cross-border parcel at customs. Verify any genuine duty request through your marketplace order page or your country's official postal/customs portal first. See [delivery scam texts](guide:delivery-scam-texts).

## Track a 360Lion parcel with Peek

[Peek](/) checks 360Lion automatically when you enter a `WSH...YQ` code (such as `WSHMX4829173640YQ`, digits invented) — where 360Lion is the sole candidate — or an `AR` + 10-digit code (such as `AR4829173640`), where Peek checks 360Lion alongside [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) and [Aramex New Zealand](carrier:aramex-nz) and shows "360Lion has this parcel." when 360Lion answers. Pasting a `toms.360lion.com/track?trackNumber=` link selects 360Lion directly; otherwise tap "Change" while adding or "Change carrier" on the parcel screen and select 360Lion (searching "360Lion Express", "WSH" or "纬狮物流" finds it too).

Peek queries 360Lion's tracking API directly and displays the stage ("Label created", "Received by carrier", "In transit", "At customs", "Returned to sender", "Delivered"), scan wording (keeping the local last-mile wording where present, otherwise 360Lion's English), 360Lion scan codes (`WSO04`, `WSD00`, `WSD02`, etc.), location as country name or 3-letter airport code, destination country, delivery time and timestamps carrying explicit time-zone offsets (for example `+08:00` in China and `-03:00` in Argentina, displayed in your device's time zone). Peek drops the site's progress rail, origin country label, transit-day counter and proof-of-delivery attachment; 360Lion's tracker does not supply an estimated delivery date, service name or parcel weight.

Because 360Lion's tracker does not publish an out-for-delivery stage or an estimated delivery date, Peek checks up to every 10 minutes while you have the parcel open, and hourly otherwise — staying hourly even while open once a parcel has sat 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and dropping to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly and every 3 hours after a day without change (up to every 15 minutes on delivery day if tracking services supply an out-for-delivery scan or expected day). After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12"), shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking 360Lion." and displays "Get help from 360Lion", which opens 360Lion's contact page. After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking 360Lion."

You can choose notification presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs or contact 360Lion or your local delivery partner for you.

## Questions about 360Lion tracking

### What is 360Lion Express?

360Lion (founded in 2015 in Shenzhen, China) is a cross-border e-commerce logistics carrier focused on air parcel lines from China to Latin America and the US, used by sellers on Mercado Libre, Amazon, AliExpress, Shopee, SHEIN and Temu.

### What do `WSH...YQ` and `AR...` tracking numbers mean?

`WSH` + two country letters + 10 digits + `YQ` (for example `WSHMX4829173640YQ`, digits invented) is 360Lion's dedicated cross-border line format, while `AR` + 10 digits (`AR4829173640`) is used on 360Lion parcels bound for Argentina.

### Who delivers 360Lion parcels locally?

360Lion flies parcels from China to your country's airport and hands them over (`handover to lastmile`) to a local postal or courier partner for doorstep delivery. The last-mile carrier's scan lines appear inside 360Lion's tracking feed.

### How can I contact 360Lion about a missing parcel?

Ask the seller or marketplace first for buyer protection and refunds. You can also email 360Lion's parcel inquiry desk at `customerservice@360lion.com` or call its Shenzhen office at `+86 0755-2360-1325`.

:::sources
- [360Lion Express: English Home](https://www.360lion.com/en/) – founded 2015, Shenzhen/Shanghai/Guangzhou/Yiwu/Dongguan/Hong Kong branches, 50-number tracker, marketplace partners and headquarters address
- [360Lion: Chinese Home](https://www.360lion.com/) – dedicated small-parcel routes across 16 Latin American countries and the US, charter air capacity and overseas warehouses
- [360Lion: Contact Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) – `customerservice@360lion.com`, `info@360lion.com`, Shenzhen/Guangzhou/Shanghai phones and office addresses (help URL linked by Peek)
- [360Lion: About Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=78) – company overview
- [360Lion: Dedicated Small Parcels](https://www.360lion.com/?m=home&c=Lists&a=index&tid=90) – cross-border B2C parcel service
- [360Lion: FAQ & Terms](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) – seller transport terms, prohibited items and 30-day seller claim process
- [360Lion: Tracking Portal](https://toms.360lion.com/track) – public tracking portal
- [Universal Parcel Scraper: 360lion](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/360lion) – `WSH` and `AR` format detection, `toms.360lion.com` link rules, `WSO`/`WSD` scan codes, explicit time-zone offsets, dropped fields and help URL
:::
