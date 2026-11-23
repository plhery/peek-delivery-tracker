---
title: PDU tracking: Parcel Delivery Union codes, statuses & help
description: Track a PDU (Parcel Delivery Union / 友递联) cross-border parcel: waybill formats, transit and last-mile statuses, customs clearance and Shanghai contacts.
slug: pdu-tracking
published: 2026-11-23
updated: 2026-11-23
---

**PDU** (*Parcel Delivery Union*, operated by *Shanghai Youdilian Technology Co., Ltd.*, Chinese *友递联科技*) is a Shanghai-headquartered cross-border smart-logistics provider founded in 2022 ([pduni.com](https://www.pduni.com/)). Operating self-run branches and local operations hubs in more than 10 countries across North America, Europe, Africa and Asia (as well as dedicated Latin America last-mile links), PDU provides e-commerce merchants and manufacturers with end-to-end customs clearance, pickup, overseas last-mile delivery and cash-on-delivery (COD) collection. You can check a shipment on PDU's English waybill portal at [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) or paste your tracking number into the box above.

## PDU tracking numbers (`PD` suffix and custom waybills)

Many PDU cross-border parcels carry a 12-character code made of two destination or channel letters, 8 digits and the suffix `PD`:

| Format (invented) | What it is |
| --- | --- |
| `MX48291736PD` | 12-character PDU cross-border waybill (`MX` + 8 digits + `PD`). |
| `CO73918462PD` | Alternative 12-character `..PD` waybill format used on international routes. |

PDU's public inquiry page also accepts other alphanumeric service waybills of 6 to 40 characters when you select PDU manually. Note that 12-character `..PD` codes share a similar length pattern with some European carriers, so Peek checks PDU directly against its live tracker before confirming ownership. See [tracking number formats](guide:tracking-number-formats).

## What PDU tracking statuses mean

PDU assigns each shipment and scan an overall state code (`TRANSIT`, `COLLECTING`, `DELIVERY`, `SIGNED`, etc.) alongside English or destination-language scan wording (frequently Spanish on Latin American lanes, often prefixed with a short local carrier code such as `ENT,Entregado` which Peek cleans for display):

| PDU state or scan wording | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipment information sent to PDU.`, `Package not found in last mile or customs process.` | Electronic order received by PDU before physical intake or before the last-mile network has created its first record. | "Label created" |
| `COLLECTING` (`Being Collected`), `FINAL_COLLECTING` (`Last-Mile Collection`), `TRANSIT` (`In Transit`) | Collected from origin or moving through international transit and regional distribution centres. | "In transit" |
| `Recogida en Aduanas`, `Ingreso a CEDI`, `En tránsito nacional` | Picked up from customs, inducted into the national distribution centre (CEDI) or moving in domestic transit. | "In transit" |
| `DELIVERY` (without a route-departure wording) | Handed into the destination delivery carrier's network; Peek keeps this as transit until the actual delivery round starts. | "In transit" |
| `Salida a Ruta` | Out on the local courier's delivery route today. | "Out for delivery" |
| `DELIVERY_EXCEPTION` (`Delivery Exception`), `TO_BE_RETURN` (`Awaiting Return`) | Delivery exception flagged or parcel pending return to sender; contact the seller or local courier. | "Needs attention" |
| `RETURNED` (`Returned`) | Returned back toward the shipper or origin warehouse. | "Returned to sender" |
| `SIGNED` (`Delivered`), `Entregado` | Delivered and signed for at the destination address. | "Delivered" |

Note that PDU's early message `Package not found in last mile or customs process.` simply means the local last-mile partner has not scanned the parcel yet — it does **not** mean customs has detained your parcel.

## Customs clearance, overseas last-mile delivery and COD

According to [pduni.com](https://www.pduni.com/) and the [PDU Help Center](https://www.pduni.com/MV/en/customer-services/help-center):

- **Self-operated clearance & AI HS matching:** PDU runs its own customs clearance network in 10+ countries with automated HS-code matching and duty pre-payment workflows; per its Help Center FAQ, PDU does not charge a separate fee for formal export customs declarations, and merchants can submit supplemental customs documents online if anything is missing.
- **Local last-mile delivery:** once destination customs releases the shipment (`Recogida en Aduanas` / `Ingreso a CEDI`), local branches or partner couriers perform doorstep delivery and, where ordered, cash-on-delivery (COD) collection.
- **Import taxes & duties:** destination import VAT or customs fees depend on local tax thresholds and whether your retailer collected duties at checkout. See [parcels held at customs](guide:customs).

## PDU parcel stuck, quiet or missing

Because online merchants and factories hold the shipping account with PDU, **always contact your seller or marketplace first** for address fixes, refunds or reshipments.

- **Quiet gaps between China and local intake:** scans often pause for several days between `Shipment information sent to PDU.` (or `Package not found in last mile or customs process.`) and `Recogida en Aduanas` / `Ingreso a CEDI` while air or ocean consolidation reaches the destination country. See [why tracking stops updating](guide:tracking-not-updating).
- **`DELIVERY_EXCEPTION` or `TO_BE_RETURN`:** act promptly — verify your delivery address and phone number with your seller so the local hub can schedule a fresh attempt before the parcel switches to `RETURNED`.
- **Marked delivered or damaged:** check with household members, building reception and neighbours first, then open a claim with the retailer before buyer protection closes. See [delivered but not received](guide:delivered-not-received), [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

## PDU customer service and Shanghai contacts

Official contact details published on [PDU Waybill Inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) and [PDU Help Center](https://www.pduni.com/MV/en/customer-services/help-center):

- **Legal entity:** *Shanghai Youdilian Technology Co., Ltd.* (*友递联科技*, ICP `沪ICP备2022026205号`).
- **Telephone hotline:** `021-50837888` (`+86 21 5083 7888`).
- **Email:** `service@pduni.com`.
- **Headquarters:** Room 305, 6F, Zhonghai Building, Zhenbei Road, Putuo District, Shanghai, China.
- **Online tracker & help:** [pduni.com/MV/en/customer-services/waybill-inquiry](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) and [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center).

> PDU's Help Center explicitly warns users to watch out for impersonation scams using PDU's logo or contact details. Never pay customs or delivery fees through an unverified text message link — confirm any charge via your retailer or official carrier portal first. See [delivery scam texts](guide:delivery-scam-texts).

## Track a PDU parcel with Peek

When you enter a 12-character `..PD` code (`MX48291736PD` or `CO73918462PD`, invented), [Peek](/) checks PDU automatically alongside other matching 12-character carriers and shows `"PDU has this parcel."` once PDU's waybill service confirms it. If your PDU tracking number uses a different 6- to 40-character format, tap "Change" while adding or "Change carrier" on the parcel screen and select **PDU** (searching "Parcel Delivery Union" or "友递联" finds it too).

Peek queries PDU's tracking API directly and displays the mapped stage ("Label created", "In transit", "Out for delivery", "Needs attention", "Returned to sender", "Delivered"), cleaned scan descriptions (stripping raw leading carrier code prefixes such as `ENT,`), scan locations, delivery timestamp on delivery and exact timestamped instants built from each scan's stated UTC offset (falling back to PDU's Beijing `UTC+8` clock when no local offset is given). PDU's public tracker does not return an estimated delivery date, weight, signatory name or separate last-mile tracking code.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. local time on the day an out-for-delivery scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12") and shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking PDU." After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking PDU."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot clear customs, edit your delivery address or contact PDU for you.

## Questions about PDU tracking

### What is PDU (*Parcel Delivery Union*)?

PDU (*Shanghai Youdilian Technology Co., Ltd.*, *友递联科技*) is a Shanghai-based cross-border logistics company founded in 2022 providing international customs clearance, pickup, last-mile parcel delivery and COD services across 10+ countries.

### What does a PDU tracking number look like?

Many PDU cross-border waybills are 12 characters long: two letters, 8 digits and `PD` (for example `MX48291736PD`, invented), though PDU's portal also tracks other 6- to 40-character alphanumeric waybills.

### Does `Package not found in last mile or customs process.` mean my parcel is stuck in customs?

No. On PDU's tracker, that early scan simply means the parcel's electronic data was created and it has not yet received its first physical scan in the destination last-mile or customs system.

### How do I contact PDU customer service?

Contact your online seller first for refunds or delivery changes. PDU's published Shanghai support contacts on [pduni.com/MV/en/customer-services/help-center](https://www.pduni.com/MV/en/customer-services/help-center) are `021-50837888` and `service@pduni.com`.

:::sources
- [PDU: Official Website](https://www.pduni.com/) – founded 2022 in Putuo District, Shanghai, 10+ country self-operated clearance & last-mile network, COD
- [PDU: Waybill Tracking Portal](https://www.pduni.com/MV/en/customer-services/waybill-inquiry) – public English waybill lookup, `service@pduni.com` and `021-50837888`
- [PDU: Help Center & FAQs](https://www.pduni.com/MV/en/customer-services/help-center) – customs FAQs, anti-impersonation notice and Shanghai HQ address (*Shanghai Youdilian Technology Co., Ltd.*)
- [Universal Parcel Scraper: pdu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pdu) – `^[A-Z]{2}\d{8}PD$` detection rule, `traceByWeb` API, state/wording mapping (`DELIVERY` vs. `Salida a Ruta`), prefix cleaning and UTC-offset/Beijing timestamp handling
:::
