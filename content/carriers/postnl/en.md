---
title: PostNL tracking: numbers, statuses and delivery abroad
description: PostNL tracking outside the Netherlands: what NL and 3S numbers mean, what postnl.post's statuses say, who delivers in your country and who to ask when it stalls.
slug: postnl-tracking
published: 2026-10-22
updated: 2026-10-27
---

PostNL is the Dutch post. It sends parcels and letters from Dutch shops abroad, and through Spring GDS, its international arm, it carries parcels for online sellers around the world. Both are tracked on PostNL's international site, [postnl.post](https://postnl.post/), or in the box above. In your country, a local carrier, often the national post, delivers the parcel. The number is in the shop's shipping email or on your order page.

## PostNL tracking number: what it looks like

PostNL uses two kinds of barcode, according to its developer documentation. With invented digits:

| Number | What it is |
| --- | --- |
| `CW000000005NL` | Two letters, 9 digits, then NL: PostNL's international postal number. A C start is a parcel to a country outside the EU, such as the UK, the US, Canada or Australia. |
| `LA000000005NL` | L: a small packet or letter with track & trace. `LW` is the same service. |
| `RI000000005NL` | R: a registered letter. `RF` is the same service. |
| `UL000000005NL` | U: an untracked packet. Don't count on scans in your country. |
| `3SABCD000000000` | 3S, a customer code of letters, then digits, 15 characters in all: a PostNL parcel barcode. |
| `3SABCD0000000` | The same barcode in its 13-character form. PostNL uses it for Parcel EU, its parcel service from the Netherlands to other EU countries such as Ireland. |

The last of the nine digits in an NL number is a check digit, so one wrong digit usually makes the number invalid.

postnl.post takes up to 150 numbers at once. It answers NL numbers and 15-character 3S barcodes, but refuses 13-character ones with "The shipment barcode was incorrect.". For those, a Parcel EU barcode to Ireland for one, ask the shop for the partner's number. When a shop books a Parcel EU label, PostNL gives it the barcode of the partner abroad that will deliver the parcel, and that barcode can be tracked with the partner. It can still change while the parcel is in the Netherlands.

## PostNL tracking statuses explained

postnl.post shows a progress bar, `Pre-advised`, `Preparing`, `Departed`, `Transit`, `Arrived`, `Customs`, `Processing`, `Delivered`, with the scans below it in English. Most scans name the country they happened in. The lines you'll meet, roughly in order:

| postnl.post says | What it means for you |
| --- | --- |
| `Pre-advised`, `Preparing` | The sender has booked the shipment and sent PostNL the data. PostNL may not have the parcel yet. |
| `Consignment received at the PostNL Acceptance Centre`, `The shipment is handed over in bulk final acceptance of the item to be confirmed` | PostNL has taken in the sender's shipment; your item's own scan may still be to come. |
| `The item is received by PostNL` | PostNL has scanned your item. This can come days after the acceptance centre line. |
| `Departed`, `Transit` | On its way between countries, often by air. |
| `Arrived` | It has reached a country on its route, usually yours. |
| `Customs` | With customs in the destination country. |
| `The item has arrived at the domestic sorting centre`, `The item is at the local sorting centre` | Being sorted in the country shown beside the scan. |
| `The item is at the local delivery office` | At the office that will deliver it. |
| `The item is out for delivery` | On its way to you. |
| `Driver is en route to the pickup location` | On its way to a pickup point, not to your door. |
| `Pick-up point` | Waiting for you at a pickup point. |
| `Unsuccesfull`, `Undelivered` | A delivery attempt failed. The spelling is PostNL's own. |
| `Undeliverable item, has been returned to shipper` | It's going back to the sender. |
| `The item has been returned and arrived at PostNL` | The return has reached PostNL. |
| `Delivered` | Delivered. |

`The shipment barcode was not found.` means postnl.post has nothing under that number yet, or a character is wrong. "Destination: unknown" on the result page means no scan has named the destination yet.

## Who delivers PostNL parcels in your country

PostNL or Spring GDS carries the parcel to your country. Spring GDS says it "manages the international leg, while a local partner manages the final delivery", and that once the parcel enters your country "it is often handed over to your local postal service (e.g., USPS, Royal Mail, Correos)".

- **United States**: [USPS](carrier:usps). An inquiry about an item from abroad goes through the sender, who works with their own post. For business parcels to the US mainland and Puerto Rico, the shop signs an addendum that lets PostNL clear customs and prepay the import duties for it.
- **United Kingdom**: [Royal Mail](carrier:royal-mail), one of the local posts Spring GDS names.
- **Ireland**: [An Post](carrier:an-post) for postal items. Its tracking shows an item from abroad once An Post has received it in Ireland. A Parcel EU barcode goes to PostNL's partner in Ireland instead, which PostNL doesn't name.
- **Canada**: [Canada Post](carrier:canada-post). If its Track tool shows nothing, Canada Post asks you to have the sender investigate with their country's post.
- **Australia**: [Australia Post](carrier:australia-post).

An NL number follows the UPU's S10 standard, the numbering posts share for items they exchange, so you can try it on your post's own site once the parcel has arrived. An untracked `UL` packet may show nothing there.

Attempts, notice cards and how long a parcel waits follow your local carrier's rules; see [missed a delivery](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel). Duties and fees are in [parcels held at customs](guide:customs).

## PostNL package not updating, lost or returned

**No scans yet.** `Pre-advised` or `Preparing` means the seller has booked the shipment but may not have handed it over. Spring GDS says to allow 24 to 48 hours after you get the number for the first scan.

**Stuck in transit.** After `Departed` or `Transit`, a parcel travelling by air or sea gets no scans until it arrives, and customs often holds parcels for several days. Then try the same number with your national post. More in [why tracking stops updating](guide:tracking-not-updating).

**Delivered but not there.** Spring GDS suggests checking with your neighbours, safe places such as a porch or garage, and any reception desk. It also says carriers sometimes mark an item delivered up to 24 hours before it actually arrives. After that, contact the seller. See [delivered but not received](guide:delivered-not-received).

**Lost or damaged.** Tell the seller straight away, and photograph damaged packaging: the seller needs the photos to file a claim with Spring GDS. See [lost parcel](guide:lost-parcel).

**Returned.** Ask the seller to resend the order or refund you. Spring GDS names "Incorrect Address" and "Unclaimed" after the holding period as common reasons, and says a return can't be reversed once it has started. See [returned to sender](guide:returned-to-sender).

## PostNL contact and customer service

As read on 22 October 2026:

- **postnl.post**: "Questions about your delivery outside The Netherlands? Please contact your webshop." For deliveries within the Netherlands, it points to PostNL's customer service.
- **Spring GDS**: it can't discuss shipments with consumers because of data privacy and its contracts with sellers. The seller holds the account and opens investigations with Spring GDS.
- **Your local carrier**: for a missed delivery or a new delivery date, contact it directly; Spring GDS can't arrange local redeliveries.

For anything else, the shop or marketplace you bought from is the quickest route.

## Track a PostNL parcel with Peek

[Peek](/) recognises NL numbers with a valid check digit and 3S barcodes with a four-letter customer code straight away. A 3S barcode with three letters goes to [DHL eCommerce Netherlands](carrier:dhl-ecommerce-nl), though PostNL's own guidelines show three-letter codes too. For a shorter customer code, Peek can't tell the carrier by shape: paste it all the same, Peek follows more than 3,500 carriers. It shows "asking …" while it checks; if the wrong carrier comes up, pick PostNL with "Change". Links from postnl.post (`/track?barcodes=`) and mailingtechnology.com, Spring GDS's tracker (`/tracking?tn=`), are read as PostNL.

Peek reads postnl.post. It shows the status, the history with the country of each scan, the delivery time and the destination country. Scan times are read on each country's local clock and shown in your device's time zone. PostNL gives no expected delivery date and no sender. For an NL number, once PostNL names the destination, Peek asks that country's national post, such as Royal Mail or USPS, under the same number, and switches to it once it shows newer progress. postnl.post calls 13-character 3S barcodes incorrect, so for those Peek looks for the parcel through [universal tracking services](guide:universal-tracker).

Peek checks PostNL every 30 minutes, out for delivery too, and every hour or two overnight, and can notify you at every scan, at important steps or on delivery day only. It can't contact PostNL or the shop for you.

## Questions about PostNL tracking

### Where is my PostNL package?

The latest scan gives its stage and the country it's in. Once a scan shows your country, try the same number on your national post's site: it usually has the more detailed local scans.

### What does "The shipment barcode was incorrect" mean?

postnl.post doesn't take that barcode. It's usually a 13-character 3S barcode, PostNL's Parcel EU format. Ask the shop for the delivering partner's number and track that with the partner.

### Who delivers PostNL parcels in the US or the UK?

Usually the national post. Spring GDS, PostNL's international arm, says a parcel entering your country is often handed over to the local postal service, such as USPS or Royal Mail. Once a scan shows your country, try the same NL number on that post's site.

:::sources
- [PostNL: International Tracking](https://postnl.post/) – 150 numbers, progress bar, scan wording, "not found" and "incorrect" answers, destination, contact your webshop
- [PostNL Developer: Barcode guidelines](https://developer.postnl.nl/docs/#/http/api-endpoints/send-track/barcode/guidelines) – 3S barcodes of 13 or 15 characters, letter customer code, S10 range NL
- [PostNL Developer: Product codes International](https://developer.postnl.nl/docs/#/http/reference-data/product-codes-international) – Parcel EU 13-character 3S and destinations, partner barcode, Parcel Non-EU C and CW, LA/LW, RI/RF, UL, US addendum for customs clearance and prepaid duties
- [Spring GDS: We are Spring GDS](https://www.spring-gds.com/about-us/) – part of the PostNL group, cross-border e-commerce
- [Spring GDS: Track and Trace](https://www.spring-gds.com/track/) – local postal service, local partner, number format, first scan, transit and customs pauses, delivered not received, claims, returns, redeliveries, no consumer contact
- [Spring GDS: Contact us](https://www.spring-gds.com/contact-us/) – tracking questions go to the webshop
- [An Post: Track and Trace help](https://www.anpost.com/Help-Support/Track-and-Trace) – inbound items tracked once received in Ireland
- [Canada Post: Tracking international packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-international-packages.page) – sender investigates with their post
- [USPS: International Inquiries – The Basics](https://faq.usps.com/s/article/International-Inquiries-The-Basics) – a non-US sender goes through their own post
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – numbering shared between posts, check digit
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads and shows
:::
