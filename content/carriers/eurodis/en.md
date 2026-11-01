---
title: EURODIS tracking: shipment numbers and delivery partners
description: Track a EURODIS pallet or parcel in Germany or Austria: what its numbers look like, which partner delivers it, and who to contact when tracking fails.
slug: eurodis-tracking
published: 2026-11-08
updated: 2026-11-08
---

EURODIS is a European network for parcels and pallets. In each country a partner company collects and delivers the goods: trans-o-flex in Germany, Österreichische Post (Austrian Post) in Austria. EURODIS's website is being updated, and for now it asks you to contact the partner in your country to track your shipment. Its tracking page didn't load when we checked on 1 November 2026, so Peek can't get anything from EURODIS either (more below). If the partner gave you its own tracking number, paste that in the box above.

## EURODIS tracking number: what it looks like

Peek's code knows two kinds of EURODIS number: one for the whole shipment, and a barcode for each package or pallet in it. Here they are with invented digits:

| Number (invented) | What it is |
| --- | --- |
| `0000000000000` | 13 digits: the EURODIS shipment number. It covers every package and pallet in the shipment. |
| `000000000000000000000000` | 24 characters: the barcode of one package or pallet. Mostly digits; some partners put letters in front. |
| `eurodis.com/track-trace?trackCollo-input-of=000000000000000000000000` | A link to EURODIS's tracker with a number in it. Peek takes the number from it and files it under EURODIS. |

The partner that delivers the shipment can have its own number for it. EURODIS's tracker showed that number beside EURODIS's. If you have the partner's number, track it with that partner, or add it to Peek under that partner.

Several other carriers use 13-digit and 24-digit numbers, so the shape alone doesn't prove that a number is EURODIS's. A 24-digit number also fits one of [Austrian Post](carrier:austrian-post)'s shapes. No number at all? Ask the sender, or see [how to find your tracking number](guide:find-tracking-number).

## Who delivers EURODIS shipments: the partner in your country

EURODIS's notice tells you to contact the customer service of the "dispatch / delivery partner in your country". Its list:

| Country | EURODIS partner |
| --- | --- |
| Germany | trans-o-flex |
| Austria | Österreichische Post ([Austrian Post](carrier:austrian-post)) |
| Hungary | Express One HU ([Express One](carrier:express-one)) |
| Bulgaria, Slovakia, Slovenia | Express One BG, Express One SK, Express One SI |
| Czech Republic | One by Allegro |
| Croatia | Overseas |
| Greece | [Geniki Taxydromiki](carrier:geniki-taxydromiki) |
| Italy | SDA / [Poste Italiane](carrier:poste-italiane) |
| Luxembourg | Interoute |
| Netherlands | [PostNL](carrier:postnl) |
| Portugal, Spain | Redur |
| Romania | [FAN Courier](carrier:fan-courier) |
| Switzerland | Bonafide |

trans-o-flex, the German partner, says that with EURODIS it ships within Germany and to 38 other European countries. It delivers to businesses and to private homes. Parcels and pallets can travel together as one consignment.

### Delivery in Germany: trans-o-flex

trans-o-flex is an express and logistics company based in Weinheim. Its own pages say this about deliveries:

- **Tracking.** Recipients track with trans-o-flex insight, an app and a web page. Without an account, you enter the consignment number, your postcode and your house number.
- **When.** Regular deliveries come Monday to Friday between 8:00 and 17:00, and on Saturdays between 7:00 and 14:00. The delivery time in the tracking is an estimate. Customer service can't change it during the round and can't call the driver.
- **Notice.** If the sender books it, trans-o-flex tells you about the delivery by text message or email.
- **Not at home.** The driver may leave the goods only if you gave written permission in advance. You can do that in trans-o-flex insight, but only for shipments where the sender agreed. It doesn't work for dangerous goods, cash on delivery, island deliveries or confidential shipments. See also [missed delivery](guide:missed-delivery).
- **Another day, another address, a second try.** Only the sender can ask for a new address. In Germany, a second delivery attempt for a shipment that isn't refrigerated also goes through the sender, and insight has no way to pick a delivery day: ask the sender.
- **Collect it yourself.** You can usually pick up a shipment at a trans-o-flex branch, unless it is a refrigerated (2–8 °C) one. Contact customer service first so they can get it ready.

### Delivery in Austria: Österreichische Post

In Austria, EURODIS's partner is Österreichische Post. Its notices, pickup points and lockers are on our [Austrian Post](carrier:austrian-post) page.

## EURODIS tracking statuses

On 1 November 2026, EURODIS's tracking page on eurodis.com didn't load, so its steps couldn't be seen there, or in Peek. EURODIS asks you to track your shipment through the customer service of the partner in your country. In Germany, trans-o-flex also has its own tracking for recipients, trans-o-flex insight (see above). For Austria, see our [Austrian Post](carrier:austrian-post) page.

This page doesn't list EURODIS's exact wordings: Peek's code knows them only from old archived copies of EURODIS's tracker, so we couldn't confirm any of them. What the usual steps mean, whoever the carrier: [tracking statuses](guide:tracking-statuses).

## EURODIS shipment late, damaged or not delivered

**Not moving.** If nothing changes for days, contact the partner in your country with your number (contacts below), or ask the sender. See [tracking that isn't updating](guide:tracking-not-updating).

**Late.** If you bought the goods as a consumer in the EU, the shop has to deliver within 30 days, unless you agreed on another time, says the EU's Your Europe site. After that, give the shop extra time. If it still doesn't deliver, you can cancel and get your money back.

**Damaged or lost.** trans-o-flex says only the shipper can claim for damage or loss in transport, so contact the sender. For consumers, Your Europe says the seller is responsible for damage from dispatch until you receive the goods, and to contact the seller first. See [damaged parcel](guide:damaged-parcel) and [lost parcel](guide:lost-parcel).

**A complaint about the delivery.** trans-o-flex wants complaints in writing, through its contact form. For an urgent concern or a complaint, the form promises an answer within 48 hours (working days).

**Sent back.** If the shipment goes back to the sender, ask the sender for a refund or a new delivery. See [returned to sender](guide:returned-to-sender).

## EURODIS contact and customer service

As read on 1 November 2026:

- **EURODIS.** Its website shows only a notice: "We are currently updating our website." For tracking and customer service, it sends you to the partner in your country. If you can't find that partner's customer service, email contact@eurodis.com.
- **Germany: trans-o-flex.** Recipient Customer Service +49 6201 988 222, Monday to Friday from 7:00 to 17:00. Or use the form "[Are you awaiting a consignment?](https://www.trans-o-flex.com/en/contact-persons/are-you-awaiting-a-consignment/)" and add your tracking number.
- **Austria: Österreichische Post.** Its hotline and forms are on our [Austrian Post](carrier:austrian-post) page.
- **The sender.** With trans-o-flex, only the sender can change the address or claim for damage or loss. The sender can also ask for another delivery attempt.

## Track a EURODIS shipment with Peek

**EURODIS's tracker.** Peek gets a EURODIS shipment's steps from EURODIS's tracking page on eurodis.com. On 1 November 2026 that page didn't load, so for now Peek gets nothing from EURODIS. For a parcel filed under EURODIS, it asks tracking services for the number instead. If none has it, the parcel reads "Waiting for the carrier" after Peek's first check and "Update unavailable" after the second. Peek keeps asking EURODIS and the tracking services on its own, but it can't contact EURODIS, its partners or the sender for you.

**The partner's number.** If the partner in your country gave you its own tracking number, add that to Peek as a parcel of its own: Peek follows more than 3,500 carriers, Austrian Post and Express One (Hungary) among them. If Peek doesn't name the partner, press "Change" and pick it. A trans-o-flex tracking link (trans-o-flex.com/sendungsverfolgung/tracking with `ref=` and the number) files the parcel under trans-o-flex, and Peek asks tracking services about it.

**A EURODIS number.** Peek can't tell a EURODIS number by its shape: no shape is EURODIS's alone. For a number of 13 or 24 digits, Peek asks other carriers whose shapes fit ("Checking tracking services…"), but not EURODIS; if none knows it, the parcel reads "No tracking history yet". To file it under EURODIS, press "Change", search for EURODIS and pick it. On a parcel you've already added, use "Change carrier". A link to EURODIS's tracker on eurodis.com files the parcel under EURODIS at once, as the "Detected carrier". For EURODIS, Peek takes 13 digits, or 24 letters and digits with at least one digit. For anything else it says EURODIS "doesn’t issue numbers like this one".

## Questions about EURODIS tracking

### Why is EURODIS tracking not working?

On 1 November 2026, EURODIS's website showed only a notice that it is being updated, and its tracking page didn't load, so Peek couldn't read EURODIS's tracking either. EURODIS asks you to contact the partner in your country to track your shipment: trans-o-flex in Germany, Österreichische Post in Austria.

### Who delivers EURODIS shipments in Germany?

trans-o-flex, an express and logistics company based in Weinheim. EURODIS asks you to contact it: its Recipient Customer Service is on +49 6201 988 222, Monday to Friday from 7:00 to 17:00. Its own tracking for recipients is trans-o-flex insight.

### Who delivers EURODIS shipments in Austria?

Österreichische Post (Austrian Post), says EURODIS's partner list. Its tracking and contacts are on our [Austrian Post](carrier:austrian-post) page.

### How do I contact EURODIS?

EURODIS asks you to contact the customer service of its partner in your country first. For help finding it, email contact@eurodis.com.

:::sources
- [EURODIS: home page](https://eurodis.com/) – website being updated; track and contact the dispatch or delivery partner in your country; partner list by country; contact@eurodis.com
- [trans-o-flex: Express delivery in Europe](https://www.trans-o-flex.com/en/products/international-express-delivery/europe/) – partner of Eurodis GmbH, 38 other European countries, businesses and private homes, parcels and pallets in one consignment, text or email notice
- [trans-o-flex: Parcel tracking with trans-o-flex insight](https://www.trans-o-flex.com/en/e-services/parcel-tracking-with-trans-o-flex-insight/) – guest tracking with number, postcode and house number; no delivery day choice; address change and second attempt via the sender; drop-off permission and its limits
- [trans-o-flex: Questions and answers](https://www.trans-o-flex.com/en/contact-persons/questions-and-answers/) – delivery hours, estimated times, drop-off permission, branch pickup, damage and loss claimed by the shipper, written complaints, 48-hour answer
- [trans-o-flex: Are you awaiting a consignment?](https://www.trans-o-flex.com/en/contact-persons/are-you-awaiting-a-consignment/) – recipient contact form, Recipient Customer Service number and hours
- [trans-o-flex: Imprint](https://www.trans-o-flex.com/en/imprint/) – based in Weinheim
- [Your Europe: Your rights when shopping in the EU](https://europa.eu/youreurope/citizens/consumers/shopping/shopping-consumer-rights/index_en.htm) – 30 days, extra time, cancel and refund, seller responsible for damage, contact the seller first
- [Universal Parcel Scraper: EURODIS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/eurodis) – number shapes, tracker links, the partner's number beside EURODIS's, wordings known only from archived copies, the tracking page Peek reads
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – no shape detection for EURODIS, Austrian Post and Express One, trans-o-flex links, tracking services when the tracker fails
:::
