---
title: Kuayuan Express tracking: waybill codes, handoffs & help
description: Track a Kuayuan Express (跨源速递) China-to-US or Europe parcel: 14-character code structure, scan codes (AF, ND, CC), local handoffs and Shenzhen contacts.
slug: kuayuan-express-tracking
published: 2026-11-23
updated: 2026-11-23
---

Kuayuan Express (*Shenzhen Kuayuan Express Supply Chain Co., Ltd.*, Chinese *跨源速递* / *深圳市跨源速递供应链有限公司*) is a Shenzhen-based cross-border e-commerce logistics provider focused on end-to-end parcel lines from China to Europe and the United States. On [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/), Kuayuan operates first-mile pickup and consolidation in China, air and sea linehaul, automated HS-code customs clearance (DDP tax-included channels) and final-mile delivery through local destination partners or its own fleet. You can look up up to 20 waybills at once on [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/) or paste your 14-character Kuayuan code into the box above.

## Kuayuan Express tracking numbers (14-character format)

Kuayuan Express cross-border parcel codes are 14 characters long, starting with a digit (typically `3`), followed by three uppercase letters and 10 digits (encoding the single-digit year, month, day and 5-digit serial):

| Format (invented) | What it is |
| --- | --- |
| `3KAA6101548291` | 14-character Kuayuan Express waybill (`3` + 3 letters + `YMMDD` date digits + 5-digit serial). |
| `3KBC6102873914` | Alternative 14-character Kuayuan Express cross-border parcel code. |

Once a parcel clears import customs in Europe or the United States, Kuayuan assigns or links a destination partner's last-mile tracking code (`server_hawbcode`, for example a [DPD Germany](carrier:dpd-de), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail) or [DHL](carrier:dhl) barcode) alongside the original 14-character Kuayuan number. See [tracking number formats](guide:tracking-number-formats).

## What Kuayuan Express tracking statuses mean

Kuayuan's tracking system pairs short uppercase scan codes with English descriptions covering its China warehouse operations, export customs, airline handover and relayed destination-carrier movement:

| Kuayuan code | What it means for you | Peek shows |
| --- | --- | --- |
| `AF` | Arrived at a Kuayuan operations or sorting centre. | "In transit" |
| `DF` | Sorting and outbound handling completed at the operations centre. | "In transit" |
| `ECKSBG` | Export customs declaration submitted in China. | "In transit" |
| `ECYDG` | Arrived at the departure port or airport hub. | "In transit" |
| `JH` | Handed over to the international airline or sea carrier. | "In transit" |
| `ND` | In transit on the handed-off international or destination last-mile line. | "In transit" |
| `CF` | Delivery attempt failed at the recipient's address. | "Delivery attempted" |
| `UE` | Parcel returned to sender after failed delivery or refusal. | "Returned to sender" |
| `CC` | Successfully delivered and signed for at the destination. | "Delivered" |

When Kuayuan returns a scan without one of the nine codes above (such as `Order information received`, `Customs clearance in progress` or `Out for delivery` relayed from a partner courier), Peek classifies it from its English wording.

## Delivery times, customs and local partner handoffs

According to [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/):

- **Published transit times:** Kuayuan advertises **7–10 days** door-to-door on its US air-line general cargo route and **7–10 days** to major European countries via fixed flight schedules and local operations.
- **Special-cargo channels:** in addition to general merchandise, Kuayuan carries battery-equipped electronics, cosmetics/liquids and health supplements using dedicated compliant channels.
- **Customs clearance & DDP:** Kuayuan promotes double-clearance tax-included (DDP) channels for Europe and the United States using automated HS-code matching. For shipments not booked DDP, destination import VAT and customs duties (including EU charges on non-EU orders) may still apply. See [parcels held at customs](guide:customs).
- **Local last-mile delivery:** after import clearance, local couriers — such as [DPD Germany](carrier:dpd-de) in Europe or domestic postal/courier partners in the US and UK — handle doorstep attempts, pickup shops and redelivery rules.

## Kuayuan Express parcel stuck, quiet or missing

Because merchants and cross-border sellers hold the shipping account with Kuayuan Express, **always contact your seller or marketplace first** for address changes, refunds or reshipments.

- **Quiet gaps between China and destination:** tracking often pauses for several days between `JH` (handed to airline) and the first `ND` destination scan while air cargo lands and clears customs batches. See [why tracking stops updating](guide:tracking-not-updating).
- **Follow the handed-off carrier barcode:** once Kuayuan links the destination partner's tracking number, checking that local carrier directly often provides delivery windows, parcel-shop pickup codes or redelivery scheduling.
- **Failed delivery, missing or damaged:** if tracking shows `CF` (failed attempt), `UE` (returned) or `CC` (delivered) without a parcel in hand, check with neighbours and the local delivery carrier first, then raise a dispute with your seller before buyer protection expires. See [delivered but not received](guide:delivered-not-received), [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

## Kuayuan Express customer service and contacts

Official contact details published on [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/):

- **Legal entity:** *Shenzhen Kuayuan Express Supply Chain Co., Ltd.* (*深圳市跨源速递供应链有限公司*, Guangdong ICP `粤ICP备2025475526号`).
- **Telephone:** `+86 15013702902`.
- **Email:** `fanjinyun@kuayuanexpress.com`.
- **Public web tracker:** [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/) (supports up to 20 numbers per lookup).

> Beware of fake text messages asking you to pay a small customs or redelivery fee through an unfamiliar link. Always confirm any real import charge via your marketplace order page or your local delivery carrier's official website. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Kuayuan Express parcel with Peek

[Peek](/) checks Kuayuan Express automatically as the sole candidate when you enter a 14-character `3`-prefixed Kuayuan waybill (`3KAA6101548291` or `3KBC6102873914`, invented) and shows `"Kuayuan Express has this parcel."` once Kuayuan confirms it. If your Kuayuan code starts with another digit or you need to select it manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "跨源速递" or "Kuayuan" finds it too).

Peek queries Kuayuan's tracking API directly in English and displays the mapped stage ("In transit", "Delivery attempted", "Returned to sender", "Delivered", plus "Label created", "Received by carrier", "At customs", "Out for delivery", "Ready for pickup" or "Needs attention" when scan wording matches), English scan descriptions and scan codes, facility locations, destination country, recipient name and destination town when returned, handed-off local delivery carrier and tracking number when Kuayuan links a recognized partner waybill, and local wall-clock timestamps as reported by each scan (without a time-zone offset). Kuayuan's API does not return an estimated delivery date, weight or separate delivery timestamp.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. local time on the day an out-for-delivery scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12") and shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Kuayuan Express." After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Kuayuan Express."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs, modify delivery addresses or contact Kuayuan Express or your local carrier for you.

## Questions about Kuayuan Express tracking

### What is Kuayuan Express (*跨源速递*)?

Kuayuan Express (*Shenzhen Kuayuan Express Supply Chain Co., Ltd.*) is a Shenzhen-based cross-border e-commerce logistics carrier operating dedicated air and sea parcel lines from China to Europe and the United States.

### What does a Kuayuan Express tracking number look like?

Kuayuan Express waybills are 14 characters long: one digit (usually `3`), three uppercase letters and 10 digits — for example `3KAA6101548291` (invented).

### What do codes `AF`, `JH`, `ND` and `CC` mean on Kuayuan Express tracking?

`AF` means the parcel arrived at a Kuayuan operations centre, `JH` means it was handed to the airline, `ND` covers in-transit movement on the destination line, and `CC` means the parcel has been delivered.

### How do I contact Kuayuan Express?

Contact your online seller or marketplace first for order issues. Kuayuan Express lists `+86 15013702902` and `fanjinyun@kuayuanexpress.com` on [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/).

:::sources
- [Kuayuan Express: Official Website & Tracker](https://www.kuayuanexpress.com.cn/) – *Shenzhen Kuayuan Express Supply Chain Co., Ltd.*, 20-number tracker, US/Europe 7–10 day routes, DDP customs, phone `+86 15013702902` and email `fanjinyun@kuayuanexpress.com`
- [Universal Parcel Scraper: kuayuan-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuayuan-express) – 14-character waybill format, `getTrackInfoOutside` API, 9 scan codes (`AF`–`UE`), partner `server_hawbcode` handoff detection and local wall-clock handling
:::
