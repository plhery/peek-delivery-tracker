---
title: Cyprus Post tracking: numbers, statuses and customs
description: Track and trace Cyprus Post (Department of Postal Services) items: 13-character CY and foreign S10 numbers, UPU tracking statuses, EU customs fees and OCECPR.
slug: cyprus-post-tracking
published: 2026-10-13
updated: 2026-11-27
---

Track and trace a Cyprus Post parcel or registered item on cypruspost.post or in the box above using your 13-character S10 postal number (ending in `CY` or the foreign post's country code). Cyprus Post (Κυπριακά Ταχυδρομεία), the Department of Postal Services, is the designated national postal operator of the Republic of Cyprus; if you have no number, see [where to find a tracking number](guide:find-tracking-number).

## Cyprus Post tracking number

Cyprus Post's tracker takes the 13-character numbers of the Universal Postal Union's S10 standard, which posts give to registered items, EMS items, parcels and tracked letters: [two letters, nine digits and two letters](guide:tracking-number-formats).

:::anatomy RR 482917367 CY
- RR | Service indicator: the first letter says what kind of item it is
- 482917367 | Eight-digit serial number, then a check digit
- CY | Country code: CY for a number issued under Cyprus's authority
:::

| First letter (invented examples) | What it is |
| --- | --- |
| `RR482917367CY` | R: registered item |
| `CP482917367CY` | C: parcel |
| `EE482917367CY` | E: EMS item |
| `LA482917367CY` | L: tracked letter |
| `UA482917367CY` | U: letter-post item with goods, not tracked for the customer |

An item posted abroad keeps the number its own post gave it, ending in that country's code (`…GB`, `…FR`, `…GR`), and Cyprus Post's tracker takes it too, with the foreign post's scans. `There is no information for this item` means the tracker doesn't have the number: check it with the sender.

## What Cyprus Post tracking statuses mean

On the English version of Cyprus Post's tracker, which Peek reads, each scan has a time, country, location, event, next office and extra information. The events are the [international postal (UPU) event names](guide:tracking-statuses): `(Otb)` marks a step in the posting country, `(Inb)` one in the destination country. The next office is where the item goes next, not where it is. For an item coming to you:

| Cyprus Post's status | What it means for you |
| --- | --- |
| `Receive item from customer (Otb)` | The item was posted, in Cyprus or abroad. |
| `Insert item into bag (Otb)` | Bagged at the office of exchange to go abroad. |
| `Receive item at office of exchange (Inb)` | It has reached the destination country. |
| `Send items to delivery point/ collection point` | On its way to the office that will deliver it or hold it for you. |
| `Receive item at delivery office (Inb)` | At the office that will deliver it. |
| `Hold item at point of delivery (Inb)` | At the delivery office, waiting for its round. |
| `Handover item to delivery agent (Inb)` | With the postman. |
| `Item not delivered/ Pending delivery` | Not delivered: the Extra Information column says why. |
| `Receive item at collection point for pick-up (Inb)` | Ready for pickup at the post office. |
| `Deliver item (Inb)` | Delivered, or collected at the post office. |

## Customs charges on goods from outside the EU

Since 1 July 2026, goods bought online from outside the EU, posted items included, no longer come in duty-free when the consignment is worth €150 or less: the EU charges a temporary customs duty of €3 per item. An item is all the goods in the consignment that share a tariff classification, description and, where it's declared, origin, however many pieces there are. Since 1 November 2026 the EU also charges a Union handling fee of €2 per item on goods bought online from outside the EU, whatever their value, and VAT is due on that fee. Paying them falls first and foremost to whoever declares the goods to customs: the platform, seller, carrier or agent. In Cyprus, the Department of Customs and Excise imposes import duty and VAT on goods from outside the EU. More in [parcels held at customs](guide:customs).

## Cyprus Post parcel late or not arrived

If the tracking has stopped moving, see [tracking that isn't updating](guide:tracking-not-updating). If it [shows as delivered](guide:delivered-not-received) but you don't have it, check the number with the sender. For a parcel that's lost or arrives broken, see [lost parcels](guide:lost-parcel) and [damaged parcels](guide:damaged-parcel).

**Bought online and still waiting?** Cyprus's Consumer Protection Service says a trader must, as a rule, deliver within 30 days unless you agreed another date. If it doesn't, tell the trader and give a reasonable extra period; if it still doesn't deliver, you can end the contract. Its Consumer Line, 1429, is free.

**A message asks you to pay to release a parcel?** Read [delivery scam texts](guide:delivery-scam-texts) before you tap any link.

## Tracking Cyprus Post parcels with Peek

For a postal number ending `CY` with a valid check digit, [Peek](/) asks Cyprus Post and [Chronopost](carrier:chronopost) and shows "Cyprus Post has this parcel." when Cyprus Post knows the number, or "Not found at … yet. Peek will keep checking after you add it." With a wrong check digit, Peek doesn't ask Cyprus Post, even if you pick it with "Change": check the number with the sender. A cypruspost.post link works when it holds the number.

For an item posted abroad, Peek first asks the post that issued the number where it can, such as [ELTA](carrier:elta) for `…GR`, or tracking services. Cyprus Post's tracker takes such numbers too: pick "Cyprus Post" with "Change". Peek tries Cyprus Post by itself when the post abroad says the item is going to Cyprus or, if it names no destination, when you add it from Cyprus, and switches once Cyprus Post has scans as recent as the post abroad's. Peek asks Cyprus Post only about 13-character numbers. Not sure of the carrier? Paste the number anyway: Peek follows more than 4,500 carriers.

Peek shows Cyprus Post's statuses as written, the office or facility and country of each scan on a map, the delivery time and the post office where an item waits or was collected. Cypriot scans keep their exact time, and a foreign post's are read on its country's clock; where a country has several time zones, Peek works the zone out from the place named, which can put the hour off. When you have the item open in Peek, or on its delivery day with notifications on, Peek asks Cyprus Post directly up to every 10 minutes, or every 2 from 6 a.m. Cyprus time on the day the item goes out with the postman, while Cyprus Post's own tracker answers, and can notify you when the status changes. After two days without news (12 hours while the item still shows "Label created" or "Received by carrier"), Peek checks hourly. After four days without a scan on an item at "Label created", "Received by carrier" or "In transit", Peek shows the last scan's date ("No tracking update since …") with the note "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking Cyprus Post." and a "Get help from Cyprus Post" link. It shows no expected delivery or weight, and drops the Extra Information, next office, postcodes and who signed: for why an item wasn't delivered, see the tracker on cypruspost.post.

## Questions about Cyprus Post tracking

### How do I track and trace an international parcel with Cyprus Post?

Enter the 13-character S10 postal number (two letters, nine digits and `CY`, or another country's two-letter code) on cypruspost.post or in Peek. In the English scan history, `(Otb)` marks events in the origin country and `(Inb)` marks events in the destination country.

### Is Cyprus Post the Department of Postal Services?

Yes. Cyprus Post is the Republic of Cyprus's Department of Postal Services, listed by the Universal Postal Union as the designated operator and named by OCECPR as the universal postal service provider.

### What does CY at the end of the number mean?

It indicates the 13-character S10 number was issued under Cyprus's authority under the UPU S10 standard (the last two letters identify the issuing country, not necessarily where the parcel was posted). Peek asks Cyprus Post about `CY` numbers.

### Can I track an item with a U number?

Not as a customer-tracked item. Under the UPU S10 standard, `U` prefixes identify letter-post items containing goods that travel without customer tracking, though Peek still queries Cyprus Post for `U…CY` numbers.

### How do I complain about Cyprus Post?

Write to Cyprus Post first. If it does not reply within 15 days, or you are unhappy with its reply, you can submit an online complaint (with an English form available) to the postal regulator, OCECPR.

:::sources
- [Universal Postal Union: S10, identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 13 characters, first letters R, C, E, L and U, check digit, issuing country's code
- [Universal Postal Union: member countries, Cyprus](https://www.upu.int/en/Universal-Postal-Union/About-UPU/Member-Countries?csid=-1&cid=86) – Cyprus Post, designated operator
- [Universal Postal Union: list of designated operators](https://www.upu.int/getmedia/0ff98b1d-9dd4-46e7-bdd1-dda1b654308f/SettlementCircularsEn.pdf) – Department of Postal Services (Cyprus Post)
- [National Open Data Portal: list of post offices](https://www.data.gov.cy/el/dataset/katalogos-kai-theseis-tahydromikon-grafeion) – Department of Postal Services, the state service in charge of postal services (in Greek)
- [OCECPR: universal postal service](https://ocecpr.ee.cy/katanalotes/katholiki-ypiresia/taxidromeia) – Cyprus Post as universal postal service provider (in Greek)
- [OCECPR: complaints](https://ocecpr.ee.cy/katanalotes/parapona) – provider first, in writing; 15 days; online complaint; complaint form in English (in Greek)
- [Consumer Protection Service: online purchases](https://consumer.gov.cy/en/consumer-rights/66/59/?ctype=ar) – delivery within 30 days, extra period, ending the contract (in Greek)
- [Consumer Protection Service: contact us](https://consumer.gov.cy/en/contact-us) – free Consumer Line 1429 (in Greek)
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty per item since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: guidance on the EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – what an item is, postal consignments, the fee on any value, who pays, VAT on the fee
- [Department of Customs and Excise: About us](https://www.mof.gov.cy/mof/customs/customs.nsf/All/289500205CFF33DFC22572AC003A2B02?OpenDocument) – imposes import duty and VAT on imported goods
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads: Cyprus Post's English tracker, its columns and event names
:::
