---
title: Post Haste tracking: NZ tickets, consignment codes & help
description: Track a Post Haste Couriers parcel in New Zealand: ticket, charge label and consignment formats, Freightways scan statuses, redelivery and branch help.
slug: post-haste-tracking
published: 2026-11-23
updated: 2026-11-23
---

**Post Haste** (*Post Haste Couriers*) is one of New Zealand's longest-running nationwide express courier networks, operating in partnership with Castle Parcels as part of the **Freightways Group** since 1977 ([posthaste.co.nz](https://posthaste.co.nz/)). Serving business and residential deliveries across both the North and South Islands through more than 20 regional branches and Delivery Link Partners, Post Haste handles local, regional overnight, inter-island overnight and two-day economy parcels alongside rural delivery zones. You can check a parcel on [posthaste.co.nz/tools/track/](https://posthaste.co.nz/tools/track/) or paste your Post Haste tracking link or consignment code into the box above.

## Post Haste tracking numbers (tickets, labels & email links)

Post Haste uses several Freightways label formats depending on whether the sender used a pre-paid courier ticket/satchel, an 8-digit charge label, a 20-digit SSCC barcode or a customer-printed label (such as Despatch IT):

| Format (invented) | What it is |
| --- | --- |
| `PH48291736` | Courier ticket or Express Pack satchel: 2–4 letter product code (for example `PE` or `PH`) + 5–8 digit ticket number (enter without spaces). |
| `73918462` | 8-digit Post Haste charge label number. |
| `KLM4829173` | Customer-printed consignment number (letters + 6–8 digits); some sender-printed labels also require the sender's account number carried inside your shipping-email tracking link (`receiver.posthaste.co.nz/?consignmentid=CU..…`). |

If your merchant sent you a `receiver.posthaste.co.nz` or `posthaste.co.nz` link by email or SMS, **paste that full link directly into Peek** — it automatically supplies both the consignment number and the sender's account code when required. See [tracking number formats](guide:tracking-number-formats).

## What Post Haste tracking statuses mean

Post Haste runs on the shared Freightways tracking backend (used across Post Haste, Castle Parcels, New Zealand Couriers and Now Couriers) and reports scan types and subtypes alongside suburb/region locations:

| Post Haste code or scan | What it means for you | Peek shows |
| --- | --- | --- |
| `Label created` | Electronic label created by the sender's despatch system before courier pickup. | "Label created" |
| `P` (`Picked Up`) | Collected from the sender by a Post Haste courier. | "Received by carrier" |
| `STAT/RZDEL` | Rural or non-urban delivery zone notice; rural partner handover typically adds 2–3 business days. | "In transit" |
| `STAT/REDEL` | Rescheduled and sent back out following an earlier missed delivery. | "In transit" |
| `D` (`Handed over` / `Delivered by courier or branch` to rural partner) | Handed off to a rural delivery contractor; still on its way to your property rather than at your door yet. | "In transit" |
| `ONBD` | On board the local courier van for delivery today. | "Out for delivery" |
| `NOND` / `NOND/*` | Non-delivery event (such as no one home and card left, closed premises or access issue). | "Delivery attempted" |
| `D`, `3P/D` | Delivered to the recipient's address (including `3P/D` completed by a rural delivery partner). | "Delivered" |

Note that `STAT/HLDFR8` means the parcel is held at the branch freight office after an unsuccessful delivery; arrange redelivery or branch collection promptly so it is not returned.

## Delivery times, rural zones and redelivery in New Zealand

According to [Post Haste Sending & Receiving](https://posthaste.co.nz/services/sending-and-receiving/), [Redelivery](https://posthaste.co.nz/tools/redelivery/) and [Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Published transit speeds:** **Local** across-town business-to-business delivery; **Regional** overnight (usually by lunchtime next business day); **Nationwide overnight** within and between North and South Islands (usually by midday next working day for business addresses, end of day for residential); **Two-day inter-island economy**; plus **Saturday delivery** in major metropolitan areas.
- **Non-urban & rural addresses:** Post Haste covers most rural New Zealand addresses through rural delivery partners (`STAT/RZDEL`), which adds two to three working days beyond metro targets.
- **Unattended premises & redelivery:** Post Haste's standard policy is **not** to leave parcels at an unattended address unless authority to leave has been arranged. If a delivery is missed, arrange redelivery online at [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) using the card-to-call reference, or contact your local branch (bring photo ID if collecting from a depot).

## Post Haste parcel delayed, held or missing

- **Rural handover looks "delivered" on some trackers:** if a scan says `Delivered by courier or branch` or `Handed over`, the parcel has usually been transferred to a rural contractor (`3P`) rather than dropped at your gate — allow 2–3 working days for the rural run (`3P/D`). Peek keeps rural handovers in `"In transit"` rather than marking them delivered early.
- **No scans after label creation or stuck at depot:** if a customer-printed consignment won't show up from the number alone, paste the full `receiver.posthaste.co.nz` link from your dispatch email so Peek can pass the sender's account code. For parcels stalled at a branch, contact your local Post Haste depot or the sender.
- **Loss or damage claims:** see Post Haste's loss and damage guidelines at [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/) and contact the retailer who booked the courier first. See [delivered but not received](guide:delivered-not-received), [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel), [returned to sender](guide:returned-to-sender) and [why tracking stops updating](guide:tracking-not-updating).

## Post Haste customer service and New Zealand branches

Official support and branch directories published on [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) and [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/):

- **Local branch directory (North & South Islands):** [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/) lists phone numbers and addresses for North Island branches (Whangārei, North Harbour, Auckland, Hamilton, Tauranga, Rotorua, New Plymouth, Taupō, Gisborne, Napier, Hastings, Whanganui, Palmerston North, Wellington) and South Island branches (Nelson, Blenheim, West Coast, Christchurch, Timaru, Dunedin, Queenstown, Invercargill), including shared Castle Parcels branches.
- **Online redelivery tool:** [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/).
- **Recipient help centre & policies:** [help.posthaste.co.nz/category/receiving-parcels/](https://help.posthaste.co.nz/category/receiving-parcels/).

> Beware of fake text messages claiming a New Zealand courier parcel requires a small card payment to reschedule delivery. Always use [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) or verify directly with your local Post Haste branch. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Post Haste parcel with Peek

The easiest way to add a Post Haste shipment in [Peek](/) is to paste the full tracking link from your dispatch email (`receiver.posthaste.co.nz/?consignmentid=CU..…` or `posthaste.co.nz/track?query=`), which Peek recognises automatically as Post Haste and uses to extract the sender's account when required. If you enter a bare ticket, charge label or consignment number (`PH48291736`, `73918462` or `KLM4829173`, invented), Post Haste has no standalone auto-detection pattern, so tap "Change" while adding or "Change carrier" on the parcel screen and select **Post Haste** (searching "Post Haste Couriers" or "Posthaste" finds it too); if your label requires a sender account, Peek also lets you attach the optional Post Haste tracking link on the parcel screen.

Peek queries Freightways' Post Haste tracking API directly and displays the mapped stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Delivery attempted", "Delivered"), Freightways event codes and descriptions, suburb and region locations, estimated delivery date when Post Haste publishes an `ETA`, delivery timestamp on delivery, partner Freightways network handoffs when another catalog network scans the parcel, and exact timestamped UTC instants. Peek discards signature/delivery photo attachments, authority-to-leave flags, postcodes and driver names.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. local time on the day an out-for-delivery scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12"), shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Post Haste." and displays "Get help from Post Haste", which opens Post Haste's receiving-parcels help centre. After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Post Haste."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot book redelivery, grant authority to leave or contact your local Post Haste branch for you.

## Questions about Post Haste tracking

### What tracking numbers work with Post Haste?

Post Haste tracks courier tickets/Express Packs (2–4 letters + 5–8 digits, such as `PH48291736`, invented), 8-digit charge labels (`73918462`, invented), 20-digit SSCC barcodes and customer-printed consignment numbers (`KLM4829173`, invented) — or paste the full `receiver.posthaste.co.nz` link from your shipping email.

### Why won't my Post Haste customer-printed consignment number show up?

Some customer-printed labels (such as Despatch IT consignments) are tied to the sender's account number. Paste the complete `receiver.posthaste.co.nz` link from your shipping notification email so the account code is included in the lookup.

### What if I missed my Post Haste delivery?

Post Haste normally requires someone present unless authority to leave was given. Use the online form at [posthaste.co.nz/tools/redelivery/](https://posthaste.co.nz/tools/redelivery/) with your card-to-call reference or call your local branch listed at [posthaste.co.nz/about-us/contact/](https://posthaste.co.nz/about-us/contact/).

### Why does Post Haste tracking say "Handed over" or `STAT/RZDEL`?

`STAT/RZDEL` indicates a rural or non-urban delivery address, and a handover scan means the parcel has been passed to a rural delivery contractor — typically adding 2–3 business days before final doorstep delivery (`3P/D`).

:::sources
- [Post Haste: Official Website](https://posthaste.co.nz/) – New Zealand nationwide courier network (Freightways Group)
- [Post Haste: Our Story](https://posthaste.co.nz/about-us/our-story/) – Freightways acquisition (1977) and partnership with Castle Parcels
- [Post Haste: Sending & Receiving Services](https://posthaste.co.nz/services/sending-and-receiving/) – local, regional overnight, nationwide overnight, two-day inter-island economy, Saturday and rural delivery services
- [Post Haste: Advanced Track & Trace](https://posthaste.co.nz/tools/track/) – customer-printed labels (with company account), courier tickets/Express Packs, barcodes and contract bags
- [Post Haste: Redelivery](https://posthaste.co.nz/tools/redelivery/) – online redelivery requests and branch collection ID requirement
- [Post Haste: Contact & Branches](https://posthaste.co.nz/about-us/contact/) – North Island and South Island branch directory
- [Post Haste: Receiving Parcels Help](https://help.posthaste.co.nz/category/receiving-parcels/) – unattended delivery policy, parcel notifications and loss/damage guidelines (help URL linked by Peek)
- [Universal Parcel Scraper: post-haste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/post-haste) – Freightways ticket/label/SSCC/consignment lookup, `receiver.posthaste.co.nz` account link rules, scan code map (`P`, `ONBD`, `NOND`, `STAT/RZDEL`, `D`, `3P/D`) and ETA parsing
:::
