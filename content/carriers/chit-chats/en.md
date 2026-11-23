---
title: Chit Chats tracking: 10-char IDs, USPS/Canada Post handoff
description: Track a Chit Chats parcel from Canada to the US, Canada or worldwide: 10-character shipment IDs, facility and partner handoff scans, delays and 1-844-842-8777.
slug: chit-chats-tracking
published: 2026-11-23
updated: 2026-11-23
---

**Chit Chats** (*Chit Chats Express*) is a Canadian e-commerce shipping consolidator founded in 2001 by Derek Nolan, operating 66 drop-off branches and SmartDrop points across Canada and having moved over 50 million parcels for more than 100,000 Canadian online sellers ([chitchats.com](https://chitchats.com/)). Instead of operating its own final-mile doorstep fleet, Chit Chats collects merchant packages across Canada, transports cross-border US orders across the border to induct directly into [USPS](carrier:usps), and hands domestic Canadian and international parcels to partner carriers such as [Canada Post](carrier:canada-post) and [Asendia](carrier:asendia). You can track a shipment on [chitchats.com/tracking](https://chitchats.com/tracking) or paste your 10-character Chit Chats shipment ID into the box above.

## Chit Chats tracking numbers (10-character shipment ID)

Every Chit Chats order receives a 10-character uppercase alphanumeric **shipment ID** (combining letters and digits, typically starting with a letter and a non-zero digit):

| Format (invented) | What it is |
| --- | --- |
| `K4N8V3T6R2` | Standard 10-character Chit Chats shipment ID. |
| `P7M3X9D5Q8` | Alternative 10-character Chit Chats shipment ID for US, Canadian or international postage. |

Once Chit Chats hands the parcel over to the final-mile partner, the tracking page also exposes that partner's native tracking code — such as a [USPS](carrier:usps) Intelligent Mail barcode (which may include a leading `420` + ZIP routing prefix that Peek strips cleanly) or a [Canada Post](carrier:canada-post) tracking number. See [tracking number formats](guide:tracking-number-formats).

## What Chit Chats tracking statuses mean

Chit Chats displays its own branch and linehaul milestones first, followed by relayed scan descriptions from the partner carrier ([USPS](carrier:usps), [Canada Post](carrier:canada-post), [Asendia](carrier:asendia), etc.):

| Chit Chats or partner says | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipment created, Chit Chats awaiting item`, `Label Created`, `Electronic information submitted by shipper` | Merchant bought postage online, but has not dropped the parcel at a Chit Chats branch yet. | "Label created" |
| `Received by Chit Chats` | Physical parcel scanned in at a Canadian Chit Chats branch or drop spot. | "Received by carrier" |
| `Arrived at Chit Chats facility`, `Departed from Chit Chats facility`, `In transit` | Sorted at a Chit Chats hub and moving toward the US border or partner handover. | "In transit" |
| `Received from Chit Chats`, `Accepted at USPS Facility`, `Item accepted at the Post Office`, `Received at sort hub` | Inducted into the partner carrier's sorting network (USPS, Canada Post or international partner). | "In transit" / "Received by carrier" |
| `Arrived at USPS Facility`, `Departed USPS Facility`, `In Transit to Next Facility`, `Item processed` | Moving through the partner carrier's domestic or international sorting facilities. | "In transit" |
| `Out for Delivery`, `Item out for delivery` | Out with the local partner carrier for final doorstep delivery today. | "Out for delivery" |
| `Notice card left indicating where and when to pick up item` | Partner carrier attempted delivery with no one home and left a pickup notice card. | "Delivery attempted" |
| `Item available for pickup at Post Office`, `Final Notice; Item will be returned to sender if not collected within 10 days` | Held at a post office waiting for recipient collection (even a final notice means the parcel is still waiting to be picked up, not yet returned). | "Ready for pickup" |
| `Delivered`, `Delivered, Front Door/Porch`, `Delivered, In/At Mailbox`, `Shipment Delivered` | Delivered to the recipient's address by the partner carrier. | "Delivered" |

## Cross-border US induction, customs and partner handoffs

- **Canada-to-US border crossing:** Chit Chats batches US-bound parcels at its Canadian hubs, clears US customs as consolidated mail/parcels and inducts them directly into [USPS](carrier:usps) on the US side of the border. Watch for current US tariff/customs requirements noted on [chitchats.com](https://chitchats.com/). See [parcels held at customs](guide:customs).
- **Tracking on the partner carrier:** once Chit Chats posts `In transit` and links a [USPS](carrier:usps), [Canada Post](carrier:canada-post) or [Asendia](carrier:asendia) tracking number, checking that partner directly lets you sign up for local delivery instructions, redelivery or post-office pickup details.

## Chit Chats parcel delayed, quiet or missing

- **Contact the online seller first:** on [chitchats.com/contact](https://chitchats.com/contact), Chit Chats explicitly tells recipients that its support team sees the exact same scan data shown on the tracking page and **can only advise the shipper** on account or claim matters. Always contact the seller who shipped your order for refunds, insurance claims or address issues.
- **Quiet gap during border transfer:** scans often pause for 1–4 business days between `Departed from Chit Chats facility` (`In transit`) and the first [USPS](carrier:usps) acceptance scan while the truck crosses the border and clears US customs. See [why tracking stops updating](guide:tracking-not-updating).
- **Delivered or missing:** verify whether the parcel is in a mailbox, front porch or local post office (especially if Canada Post left a notice card), then contact the seller promptly. See [delivered but not received](guide:delivered-not-received), [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

## Chit Chats customer service and Canadian contacts

Official support channels published on [Chit Chats Contact Us](https://chitchats.com/contact) and [Chit Chats Support](https://support.chitchats.com/en/support/home):

- **Toll-free phone (English & French — billing & branch enquiries):** `1-844-842-8777` (plus individual branch phone numbers during local branch hours across 66 Canadian locations).
- **Help & support portal:** [support.chitchats.com/en/support/home](https://support.chitchats.com/en/support/home) and [chitchats.com/contact](https://chitchats.com/contact).
- **Public shipment tracker:** [chitchats.com/tracking](https://chitchats.com/tracking).

> Beware of fake text messages asking for a small online payment to clear a cross-border Canada/US parcel. Verify any customs or postage notice directly through your seller or official USPS/Canada Post tracking first. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Chit Chats parcel with Peek

[Peek](/) checks Chit Chats automatically as the sole candidate when you enter a 10-character Chit Chats shipment ID (`K4N8V3T6R2` or `P7M3X9D5Q8`, invented) and shows `"Chit Chats has this parcel."` once Chit Chats confirms it; Peek also recognises pasted `chitchats.com/tracking/<10-char>` tracking links directly. If you ever need to choose Chit Chats manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "ChitChats" or "Chit Chats Express" finds it too).

Peek queries Chit Chats' JSON tracking endpoint directly and displays the mapped stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Ready for pickup", "Delivery attempted", "Delivered"), Chit Chats and relayed partner scan wording, scan locations (scrubbing street numbers and postcodes), estimated delivery date, weight (converted to kilograms), parcel dimensions, postage service name, destination country, recipient name and destination city/province/postcode, automatic handoff to the partner carrier (`delivery_carrier` and cleaned `delivery_tracking_number`, stripping USPS `420` routing prefixes), and exact timestamped instants for Chit Chats' own scans while keeping local wall clocks for relayed partner scans. Peek omits sender town/name and raw progress rails.

Peek checks up to every 10 minutes while you have the parcel open or on delivery day with alerts on, and up to every 2 minutes from 6:00 a.m. local time on the day an out-for-delivery scan arrives. Otherwise it checks hourly, stays hourly while open after 12 hours at "Label created" or "Received by carrier" or 2 days without a new scan, and drops to every 6 hours after 7 days still at "Label created". When tracking services fill in as a fallback, they check hourly, every 3 hours after a day without change, and up to every 15 minutes on delivery day. After 4 days in transit with no new scan, Peek flags the pause (for example "No tracking update since Mon, Oct 12"), shows "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Chit Chats." and displays "Get help from Chit Chats", which opens Chit Chats' contact page. After more than three weeks without an update, Peek shows "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Chit Chats."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot file claims, edit addresses or contact Chit Chats or its partner carriers for you.

## Questions about Chit Chats tracking

### What does a Chit Chats tracking number look like?

Chit Chats shipment IDs are 10 uppercase letters and digits (for example `K4N8V3T6R2` or `P7M3X9D5Q8`, invented). Once handed off, the parcel also gets a partner carrier code (such as USPS or Canada Post).

### Who delivers a Chit Chats parcel to my door?

Chit Chats consolidates and transports the parcel from Canada, then hands final delivery to a partner carrier — typically [USPS](carrier:usps) in the United States, [Canada Post](carrier:canada-post) inside Canada, or [Asendia](carrier:asendia) / postal partners internationally.

### Why did my Chit Chats parcel pause after `Departed from Chit Chats facility`?

The parcel is crossing the Canada–US border or transferring to the partner carrier's intake hub. Tracking resumes once USPS, Canada Post or the international partner scans it in.

### Who should I contact if my Chit Chats parcel is missing?

Contact the online retailer who sent the parcel first — per [chitchats.com/contact](https://chitchats.com/contact), Chit Chats can only open investigations or claims with the shipper (toll-free `1-844-842-8777` for branch/account enquiries).

:::sources
- [Chit Chats: Official Website](https://chitchats.com/) – Canadian e-commerce shipping consolidator for US, Canadian and international postage
- [Chit Chats: About Us](https://chitchats.com/about) – founded 2001 by Derek Nolan, 50M+ packages, 66 Canadian locations and 100K+ shippers
- [Chit Chats: Tracking Portal](https://chitchats.com/tracking) – 10-character shipment ID tracking page
- [Chit Chats: Contact Us](https://chitchats.com/contact) – toll-free `1-844-842-8777`, recipient-contact-seller policy and branch directory (help URL linked by Peek)
- [Chit Chats: Help & Support Portal](https://support.chitchats.com/en/support/home) – official help centre
- [Universal Parcel Scraper: chit-chats](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chit-chats) – 10-char ID detection, `chitchats.com/tracking/` link rule, own vs. relayed scan status map, ETA/weight/dimensions/service extraction and USPS 420-prefix handoff cleaning
:::
