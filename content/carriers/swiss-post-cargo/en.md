---
title: Swiss Post Cargo tracking: statuses, delivery and contact
description: Track a Swiss Post Cargo shipment: which number works, what Data entry POST and Loaded for Delivery mean, how freight is delivered and who to call.
slug: swiss-post-cargo-tracking
published: 2026-10-12
updated: 2026-10-27
---

Swiss Post Cargo is Swiss Post's freight brand: pallets, bulky goods and furniture carried by truck, not ordinary parcels. Track a shipment on its Track & Trace page (tt.swisspost-cargo.com) with the barcode or the reference the sender gave you, or paste it in the box above. If what you have is a Swiss Post parcel number, it belongs on [Swiss Post's page](carrier:swiss-post) instead.

## Swiss Post Cargo tracking number

Swiss Post Cargo publishes no number format. Its tracker asks for "one consignment number per line (barcode or reference)": a barcode for one item, or a reference from the sender, such as an order number or a delivery number. A reference brings up every barcode in its consignment. The shapes seen on it (digits invented):

| Number | What it is |
| --- | --- |
| `00012345600000000005` | 20 digits starting `00`: a pallet or item barcode (an SSCC). The last digit is a check digit. |
| `PL-12345678` | `PL-` and 8 digits: a customer reference. |

No Swiss Post Cargo page says where you'll find the number. Check the shop's emails, then ask the shop. [How to find your tracking number](guide:find-tracking-number) has more places to look. If the tracker answers `No data was found for your request. Please verify that the inserted data is correct.`, check the digits, then ask the sender.

Numbers that belong on another tracker:

- **Swiss Post parcels:** 18 digits starting `99` (printed `99.00.123456.12345678`) or two letters, nine digits and `CH` (`RR000000005CH`).
- **PostLogistics references** like `12345678-123`: the same company's older tracker, tracking.postlogistics.ch.
- **Germany:** the German portal, titled "Hugger" (portal-de1.swisspost-cargo.com), asks for a colli number, or a shipment number with a company number.

## Swiss Post Cargo track and trace statuses

The tracker switches between English, German, French and Italian, and the scan wordings change with it. In English, a delivered shipment reads like this:

| Swiss Post Cargo says | What it means for you |
| --- | --- |
| `Data entry POST` | The sender's data has arrived. The goods are not with Swiss Post Cargo yet. |
| `Received warehouse POST` | The goods have reached a Swiss Post Cargo hub or depot, named next to the scan. |
| `Loaded for Delivery` | It's on the delivery truck: out for delivery. |
| `IMAGE` | A delivery photo, listed just before the delivery scan. |
| `DELIVERED SCANNED` | Delivered, in the town shown. |
| `SIGNATURE` | The signature taken at delivery, same time and town as the delivery scan. |

`Data entry POST` is only data: "POST" is part of the wording, and nothing has moved yet. `IMAGE` and `SIGNATURE` are proof that belongs to the delivery, not new events. A history can show `Received warehouse POST` and `Loaded for Delivery` twice before the delivery, with no line saying why the first round didn't deliver. On the German tracker the delivery reads `AUSGELIEFERT`, on the French one `LIVRE SCANNE`, on the Italian one `CONSEGNATO SCANSIONATO`.

## Swiss Post Cargo delivery: when, where and missed deliveries

**When.** Swiss Post's freight brochure says freight is generally delivered on the next working day. In Switzerland and Liechtenstein, places a heavy goods vehicle can reach get it by 17:00 on the next working day. Hard-to-reach Alpine valleys and mountain areas (Valais, Ticino and Graubünden) get it by 17:00 on the second working day.

**Where.** As standard, freight goes to the first door on the ground floor, the ramp or the kerb, during business hours, not upstairs. The driver expects access with a pallet truck, and unloading is the recipient's job: a driver who unloads does it on your behalf. The driver asks you to sign to confirm the delivery was correct.

**Extras the sender books.** A notice by phone, email or SMS, a planned delivery, delivery to a floor and a second delivery are extra services, so you get them only if the sender ordered them.

**Furniture and appliances.** With the installation service, the shop places the order and Swiss Post Cargo agrees the delivery date directly with you. It sets the goods up where they'll be used and can take away the packaging or the old appliance. It delivers Monday to Saturday across Switzerland and Liechtenstein.

**Missed delivery and pickup.** Swiss Post Cargo's pages mention no pickup points or lockers, and don't say how many attempts it makes, how long goods wait at a depot or how you can reschedule. Ask the shop, which booked the transport.

## Swiss Post Cargo shipment late, lost or damaged

The shop is Swiss Post Cargo's customer: businesses order its services, so a delay, damage or a missing item goes to the shop first. Swiss Post Cargo publishes no claim procedure or deadline for recipients. Report damage to the shop straight away, with photos.

**No update for days.** `Data entry POST` alone means the goods may still be with the sender: ask the shop when it hands them over. [Tracking that isn't updating](guide:tracking-not-updating) explains the usual reasons.

**Delivered but not there.** Look for the `SIGNATURE` and `IMAGE` rows on the tracker, then contact the shop. [Delivered but not received](guide:delivered-not-received) walks through the next steps.

**Suspicious messages.** Swiss Post Cargo links to Swiss Post's phishing page, which says fake messages in Swiss Post's name arrive by SMS, email, WhatsApp, websites, calls and social media, often about parcels, fees, tracking or personal data.

## Swiss Post Cargo contact number

Swiss Post Cargo's help page (latest saved copy, read 12 October 2026) lists lines for existing or new orders, meant for business customers, and no general line for recipients. All are Monday to Friday:

- **Installation service:** +41 (0)58 386 74 74, 09:00 to 12:00 and 14:00 to 17:00, aufstellservice@post.ch.
- **Swiss-Express Day:** +41 (0)848 754 754, 07:00 to 12:00 and 13:00 to 18:00, swissexpress.tag@post.ch.
- **Swiss-Express Innight:** +41 (0)848 754 754, 08:00 to 12:00 and 13:00 to 18:00, swissexpress.innight@post.ch.
- **Returns management:** +41 (0)58 386 44 11, 09:00 to 12:00 and 14:00 to 17:00, returnservices@post.ch.
- **Customs clearance:** +41 (0)58 341 31 41, 06:00 to 20:00, zoll.plin@post.ch.
- **Contact form:** on the help and contact page, for anything else.

For Swiss Post letters and parcels (not Cargo), Swiss Post's line is +41 848 888 888 (CHF 0.08 a minute from a Swiss landline), Monday to Friday 07:30 to 18:00 and Saturday 08:00 to 12:00, with a chat on post.ch.

## Tracking a Swiss Post Cargo shipment with Peek

A 20-digit barcode starting `00` belongs to whoever shipped it, not to one carrier, so [Peek](/) shows "asking Swiss Post Cargo, NZ Post…" and names Swiss Post Cargo only if Swiss Post Cargo knows the number. A `PL-12345678` reference is asked of Swiss Post Cargo alone. For any other reference of 6 to 40 letters and digits, pick Swiss Post Cargo with "Change", or paste a tt.swisspost-cargo.com or apv.swisspost-cargo.com Track & Trace link. A Swiss Post parcel number in such a link is tracked as Swiss Post.

Peek shows the status and the history with the town of each scan on a map, with the delivery time. Times are read on Zurich time and shown in your device's time zone. Wordings stay in English; `Loaded for Delivery` counts as out for delivery. The signature and photo rows fold into the delivery scan, without the picture. Peek leaves out your name and address and has no expected delivery, weight or pickup point.

A reference shared by several shipments is read only while one of them has scans from the last 60 days; otherwise it reads as not found, so use the barcode. Peek can't track the German portal's shipment and company numbers. It checks every 10 minutes by day, every 2 once out for delivery, and every hour or two overnight, and can notify you of every scan, important steps or delivery day only.

## Questions about Swiss Post Cargo tracking

### What is Swiss Post Cargo?

It's the name Swiss Post's goods logistics has traded under since 1 March 2025: freight, express transport, customs clearance and furniture installation. PostLogistics was the first group company to take the name.

### Is PostLogistics the same as Swiss Post Cargo?

Yes, PostLogistics now trades as Swiss Post Cargo. Its older tracker, tracking.postlogistics.ch, reads references like `12345678-123`, while Swiss Post Cargo's own tracker is tt.swisspost-cargo.com.

### Is there a Swiss Post Cargo email address?

Its help page lists no general one. Each service has its own address, such as aufstellservice@post.ch for installations; everything else goes through the contact form.

### Why does Swiss Post Cargo send me an SMS?

Often because the sender booked a delivery notice: Swiss Post's freight brochure lists notice by phone, email or SMS as an extra. Fake texts in Swiss Post's name exist too, so check the number on the tracker yourself.

### Can I track Swiss Post Cargo live?

Not on a live map. The tracker lists each scan with its town, and has a box for an estimated delivery, which may be empty.

:::sources
- [Swiss Post Cargo: Help and contact](https://web.archive.org/web/20260520134425/https://www.swisspost-cargo.com/ch-en/help-and-contact) – phone lines, hours, emails, contact form, Swiss Post line, phishing link (saved copy; live page blocked)
- [Swiss Post Cargo: Praktischer Aufstellservice für Ihre Kundschaft](https://web.archive.org/web/20260610160403/https://www.swisspost-cargo.com/ch-de/transport/aufstellservice) – installation service, date agreed with you, Monday to Saturday, ordered by businesses
- [Swiss Post Cargo: Stückguttransport Schweiz und Europa](https://web.archive.org/web/20260623100227/https://www.swisspost-cargo.com/ch-de/transport/stueckgut) – what the freight service is
- [Swiss Post Cargo: Track & Trace](https://tt.swisspost-cargo.com/public/trackandtrace) – input hint, languages, statuses, no-data message, estimated delivery box, contact list
- [Swiss Post Cargo: Sendungsverfolgung - Hugger](https://portal-de1.swisspost-cargo.com/Track?language=de&culture=DE) – colli, shipment and company numbers, delay note
- [Swiss Post: Transport freight brochure](https://www.post.ch/en/-/media/post/gk/dokumente/stueckgut.pdf) – next working day, mountain areas, ground floor, unloading, signature, sender extras
- [Swiss Post: Help and contact](https://www.post.ch/en/help-and-contact) – letters and parcels line, price, hours, chat
- [Swiss Post: Current fraud attempts](https://site.post.ch/en/about-us/security/phishing-and-fraud/current-fraud-attempts) – fake messages in Swiss Post's name
- [VerkehrsRundschau: Schweizerische Post mit neuem Marktauftritt](https://www.verkehrsrundschau.de/nachrichten/transport-logistik/logistik-schweizerische-post-fuehrt-neue-marke-ein-3626449) – Swiss Post Cargo name from 1 March 2025, PostLogistics first
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
