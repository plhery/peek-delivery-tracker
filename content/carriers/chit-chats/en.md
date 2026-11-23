---
title: Chit Chats tracking: 10-char IDs, USPS/Canada Post handoff
description: Track a Chit Chats parcel from Canada to the US, Canada or worldwide: 10-character shipment IDs, facility and partner handoff scans, delays and 1-844-842-8777.
slug: chit-chats-tracking
published: 2026-11-23
updated: 2026-11-23
---

**Chit Chats** (*Chit Chats Express*) is a Canadian e-commerce shipping company founded in 2001 by Derek Nolan. It has 66 locations across Canada, takes parcels by mail-in (SmartDrop) and pickup too, and has shipped over 50 million packages for more than 100,000 shippers ([chitchats.com](https://chitchats.com/)). Partner carriers deliver its parcels: its tracker names the partner and gives the partner's tracking number, such as [USPS](carrier:usps) in the United States or [Canada Post](carrier:canada-post) in Canada. You can track a shipment on [chitchats.com/tracking](https://chitchats.com/tracking) or paste your 10-character Chit Chats shipment ID into the box above.

## Chit Chats tracking numbers (10-character shipment ID)

Every Chit Chats order receives a 10-character uppercase alphanumeric **shipment ID** (combining letters and digits, typically starting with a letter and a non-zero digit):

| Format (invented) | What it is |
| --- | --- |
| `K4N8V3T6R2` | Standard 10-character Chit Chats shipment ID. |
| `P7M3X9D5Q8` | Alternative 10-character Chit Chats shipment ID for US, Canadian or international postage. |

Once Chit Chats hands the parcel over to the final-mile partner, the tracking page also exposes that partner's native tracking code — such as a [USPS](carrier:usps) Intelligent Mail barcode (which may include a leading `420` + ZIP routing prefix that Peek strips cleanly) or a [Canada Post](carrier:canada-post) tracking number. See [tracking number formats](guide:tracking-number-formats).

## What Chit Chats tracking statuses mean

Chit Chats' tracker shows its own scans and then those of the partner carrier ([USPS](carrier:usps), [Canada Post](carrier:canada-post) or another):

| Chit Chats or partner says | What it means for you | Peek shows |
| --- | --- | --- |
| `Shipment created, Chit Chats awaiting item`, `Label Created`, `Electronic information submitted by shipper` | Merchant bought postage online, but has not dropped the parcel at a Chit Chats branch yet. | "Label created" |
| `Received by Chit Chats` | Physical parcel scanned in at a Canadian Chit Chats branch or drop spot. | "Received by carrier" |
| `Arrived at Chit Chats facility`, `Departed from Chit Chats facility`, `In transit` | Sorted at a Chit Chats facility; `In transit` is the hand-over to the partner carrier. | "In transit" |
| `Received from Chit Chats`, `Accepted at USPS Facility`, `Item accepted at the Post Office`, `Received at sort hub` | Inducted into the partner carrier's sorting network (USPS, Canada Post or international partner). | "In transit" / "Received by carrier" |
| `Arrived at USPS Facility`, `Departed USPS Facility`, `In Transit to Next Facility`, `Item processed` | Moving through the partner carrier's domestic or international sorting facilities. | "In transit" |
| `Out for Delivery`, `Item out for delivery` | Out with the local partner carrier for final doorstep delivery today. | "Out for delivery" |
| `Notice card left indicating where and when to pick up item` | Partner carrier attempted delivery with no one home and left a pickup notice card. | "Delivery attempted" |
| `Item available for pickup at Post Office`, `Final Notice; Item will be returned to sender if not collected within 10 days` | Held at a post office waiting for recipient collection (even a final notice means the parcel is still waiting to be picked up, not yet returned). | "Ready for pickup" |
| `Delivered`, `Delivered, Front Door/Porch`, `Delivered, In/At Mailbox`, `Shipment Delivered` | Delivered to the recipient's address by the partner carrier. | "Delivered" |

## Cross-border US induction, customs and partner handoffs

- **Shipping to the United States:** Chit Chats offers U.S. shipping with customs clearance, delivered duty paid and help with CUSMA paperwork, quotes 2–6 business days, and posts its 2026 U.S. tariff updates on [chitchats.com](https://chitchats.com/). See [parcels held at customs](guide:customs).
- **Tracking on the partner carrier:** once Chit Chats hands the parcel over, its contact page suggests using the carrier tracking number on the partner carrier's own tracking page.

## Chit Chats parcel delayed, quiet or missing

- **Contact the online seller first:** Chit Chats' [contact page](https://chitchats.com/contact) says its team sees the same tracking information you do and can only advise the shipper, on account-related matters, so recipients should contact the seller.
- **Late parcel:** once the estimated delivery time has passed, Chit Chats' tracking page suggests contacting the seller. See [why tracking stops updating](guide:tracking-not-updating).
- **Delivered or missing:** verify whether the parcel is in a mailbox, front porch or local post office (especially if Canada Post left a notice card), then contact the seller promptly. See [delivered but not received](guide:delivered-not-received), [lost parcel](guide:lost-parcel), [damaged parcel](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

## Chit Chats customer service and Canadian contacts

Official support channels published on [Chit Chats Contact Us](https://chitchats.com/contact) and [Chit Chats Support](https://support.chitchats.com/en/support/home):

- **Toll-free phone (English & French — billing & branch enquiries):** `1-844-842-8777` (plus individual branch phone numbers during local branch hours across 66 Canadian locations).
- **Help & support portal:** [support.chitchats.com/en/support/home](https://support.chitchats.com/en/support/home) and [chitchats.com/contact](https://chitchats.com/contact).
- **Public shipment tracker:** [chitchats.com/tracking](https://chitchats.com/tracking).

> Beware of fake text messages asking for a small online payment to clear a cross-border Canada/US parcel. Verify any customs or postage notice directly through your seller or official USPS/Canada Post tracking first. See [delivery scam texts](guide:delivery-scam-texts).

## Track a Chit Chats parcel with Peek

[Peek](/) checks Chit Chats automatically as the sole candidate when you enter a 10-character Chit Chats shipment ID (`K4N8V3T6R2` or `P7M3X9D5Q8`, invented) and shows "Chit Chats has this parcel." once Chit Chats confirms it; Peek also recognises pasted `chitchats.com/tracking/<10-char>` tracking links directly. If you ever need to choose Chit Chats manually, tap "Change" while adding or "Change carrier" on the parcel screen (searching "ChitChats" or "Chit Chats Express" finds it too).

Peek reads Chit Chats' tracker directly and shows the stage ("Label created", "Received by carrier", "In transit", "Out for delivery", "Ready for pickup", "Delivery attempted", "Delivered"), the wording of Chit Chats' and the partner's scans, and each scan's town without its postcode; a place with a street number is left out. It also shows the estimated delivery day, the weight in kilograms and the dimensions. The recipient's name shows under "Recipient" on your own parcel page, and the destination town can end your own map's route while the parcel is on its way; shared links show neither. When Chit Chats names a partner Peek knows, such as USPS or Canada Post, with the partner's tracking number (a USPS number without its ZIP routing prefix), Peek checks that it has the parcel: the parcel then shows "Delivery with" and the carrier's name, and lists both numbers. Chit Chats' own scans come with an exact time. A partner's scan comes with a clock that Chit Chats labels as Pacific time but can be hours off it, so Peek dates it on its place's time zone (New York's for `NEW YORK, NY`, Toronto's for `TORONTO, ON`), a scan without a place on the zone of the scan beside it, or else on Vancouver's, and shows them all on your device's clock. Peek leaves out the service name, the status codes and the sender's town.

While you have the parcel open in Peek, or on its delivery day with notifications on, Peek checks up to every 10 minutes; on the day the tracker shows `Out for Delivery` or `Item out for delivery`, every 2 minutes from 6 a.m. on the newest scan's clock, while Chit Chats' tracker answers. Otherwise Peek checks hourly. After 12 hours with only "Label created" or "Received by carrier", or two days without news, it checks hourly however you follow it, and every 6 hours after seven days at "Label created". When Chit Chats' tracker doesn't answer and Peek follows the parcel through tracking services alone, it checks hourly, every 3 hours after a quiet day, and up to every 15 minutes on the out-for-delivery day.

When the newest scan is four days old and the parcel is still at "Label created", "Received by carrier" or "In transit", Peek flags it with that scan's day, such as "No tracking update since Mon 12 Oct". The parcel's page then says "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Chit Chats.", with a "Get help from Chit Chats" link to Chit Chats' contact page. After three weeks with no new scan, it says "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking Chit Chats."

You can pick alert presets for "Every scan", "Important steps" or "Delivery only". Peek monitors tracking updates only and cannot file claims, edit addresses or contact Chit Chats or its partner carriers for you.

## Questions about Chit Chats tracking

### What does a Chit Chats tracking number look like?

Chit Chats shipment IDs are 10 uppercase letters and digits (for example `K4N8V3T6R2` or `P7M3X9D5Q8`, invented). Once handed off, the parcel also gets a partner carrier code (such as USPS or Canada Post).

### Who delivers a Chit Chats parcel to my door?

A partner carrier delivers it, such as [USPS](carrier:usps) in the United States or [Canada Post](carrier:canada-post) in Canada. Chit Chats' tracker names the partner and gives its tracking number.

### Why did my Chit Chats parcel pause after `Departed from Chit Chats facility`?

Chit Chats' tracker doesn't give a reason. Its `In transit` is the hand-over to the partner carrier, and the partner's scans follow once it has scanned the parcel. If the estimated delivery time has passed, contact the seller.

### Who should I contact if my Chit Chats parcel is missing?

Contact the seller who sent the parcel. Per [chitchats.com/contact](https://chitchats.com/contact), Chit Chats' team can only advise the shipper, on account-related matters. Its toll-free line `1-844-842-8777` is for billing, branch inquiries and French support.

:::sources
- [Chit Chats: Official Website](https://chitchats.com/) – Canadian e-commerce shipping consolidator for US, Canadian and international postage
- [Chit Chats: About Us](https://chitchats.com/about) – founded 2001 by Derek Nolan, 50M+ packages, 66 Canadian locations and 100K+ shippers
- [Chit Chats: Tracking Portal](https://chitchats.com/tracking) – 10-character shipment ID tracking page
- [Chit Chats: Contact Us](https://chitchats.com/contact) – toll-free `1-844-842-8777`, recipient-contact-seller policy and branch directory (help URL linked by Peek)
- [Chit Chats: Help & Support Portal](https://support.chitchats.com/en/support/home) – official help centre
- [Universal Parcel Scraper: chit-chats](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chit-chats) – 10-char ID detection, `chitchats.com/tracking/` link rule, own vs. relayed scan status map, ETA/weight/dimensions/service extraction and USPS 420-prefix handoff cleaning
:::
