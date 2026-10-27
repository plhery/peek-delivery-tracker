---
title: Cyprus Post tracking: numbers, statuses and customs
description: Track a Cyprus Post item: what its 13-character number tells you, what the English UPU statuses mean, EU charges on goods from outside the EU and where to complain.
slug: cyprus-post-tracking
published: 2026-10-13
updated: 2026-10-27
---

Cyprus Post (Κυπριακά Ταχυδρομεία), the Department of Postal Services, is the national post of the Republic of Cyprus. Track an item on Cyprus Post's website, cypruspost.post, or paste the number in the box above. No number at all? See [where to find a tracking number](guide:find-tracking-number).

## Cyprus Post tracking number

Cyprus Post's tracker takes the 13-character numbers of the Universal Postal Union's S10 standard, which posts give to registered items, EMS items, parcels and tracked letters: [two letters, nine digits and two letters](guide:tracking-number-formats).

:::anatomy RR 000000005 CY
- RR | Service indicator: the first letter says what kind of item it is
- 000000005 | Eight-digit serial number, then a check digit
- CY | Country code: CY for a number issued under Cyprus's authority
:::

| First letter (invented examples) | What it is |
| --- | --- |
| `RR000000005CY` | R: registered item |
| `CP000000005CY` | C: parcel |
| `EE000000005CY` | E: EMS item |
| `LA000000005CY` | L: tracked letter |
| `UA000000005CY` | U: letter-post item with goods, not tracked for the customer |

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

Since 1 July 2026, goods bought online from outside the EU, posted items included, no longer come in duty-free when the consignment is worth €150 or less: the EU charges a temporary customs duty of €3 per item. An item is all the goods in the consignment that share a tariff classification, description and, where it's declared, origin, however many pieces there are. From 1 November 2026 the EU will also charge a Union handling fee of €2 per item on goods bought online from outside the EU, whatever their value, and VAT is due on that fee. Paying them falls first and foremost to whoever declares the goods to customs: the platform, seller, carrier or agent. More in [parcels held at customs](guide:customs).

## Cyprus Post parcel late or not arrived

If the tracking has stopped moving, see [tracking that isn't updating](guide:tracking-not-updating). If it [shows as delivered](guide:delivered-not-received) but you don't have it, check the number with the sender. For a parcel that's lost or arrives broken, see [lost parcels](guide:lost-parcel) and [damaged parcels](guide:damaged-parcel).

**Bought online and still waiting?** Cyprus's Consumer Protection Service says a trader must, as a rule, deliver within 30 days unless you agreed another date. If it doesn't, tell the trader and give a reasonable extra period; if it still doesn't deliver, you can end the contract. Its Consumer Line, 1429, is free.

**A message asks you to pay to release a parcel?** Read [delivery scam texts](guide:delivery-scam-texts) before you tap any link.

## Tracking Cyprus Post parcels with Peek

For a postal number ending `CY` with a valid check digit, [Peek](/) asks Cyprus Post and [Chronopost](carrier:chronopost) and shows "Cyprus Post has this parcel." when Cyprus Post knows the number, or "Not found at … yet. Peek will keep checking after you add it." With a wrong check digit, Peek doesn't ask Cyprus Post, even if you pick it with "Change": check the number with the sender. A cypruspost.post link works when it holds the number.

For an item posted abroad, Peek first asks the post that issued the number where it can, such as [ELTA](carrier:elta) for `…GR`, or tracking services. Cyprus Post's tracker takes such numbers too: pick "Cyprus Post" with "Change". Peek tries Cyprus Post by itself when the post abroad says the item is going to Cyprus or, if it names no destination, when you add it from Cyprus, and switches once Cyprus Post has scans as recent as the post abroad's. Peek asks Cyprus Post only about 13-character numbers. Not sure of the carrier? Paste the number anyway: Peek follows more than 3,500 carriers.

Peek shows Cyprus Post's statuses as written, the office or facility and country of each scan on a map, the delivery time and the post office where an item waits or was collected. Cypriot scans keep their exact time, and a foreign post's are read on its country's clock; where a country has several time zones, Peek works the zone out from the place named, which can put the hour off. Peek checks every 10 minutes by day, every 2 once the item is with the postman, and every hour or two overnight, and can notify you when the status changes. It shows no expected delivery or weight, and drops the Extra Information, next office, postcodes and who signed: for why an item wasn't delivered, see the tracker on cypruspost.post.

## Questions about Cyprus Post tracking

### Is Cyprus Post the Department of Postal Services?

Yes. The Universal Postal Union lists Cyprus's designated operator as the Department of Postal Services (Cyprus Post), the state service responsible for providing postal services, and the postal regulator, OCECPR, names Cyprus Post as the universal postal service provider.

### What does CY at the end of the number mean?

That the number was issued under Cyprus's authority. Under the UPU's S10 standard, the last two letters are the code of the country under whose authority the number was issued, so they don't reliably show where the item was posted. Peek asks Cyprus Post about these numbers.

### Can I track an item with a U number?

Not as a tracked item. The S10 standard gives U numbers to letter-post items with goods that travel without tracking for the customer: the number gives visibility in the supply chain, for example in the electronic data sent ahead of the item. Peek still asks Cyprus Post about a U number ending `CY`.

### How do I complain about Cyprus Post?

Write to Cyprus Post first. If it doesn't answer within 15 days, or its answer doesn't satisfy you, complain to the postal regulator, the Office of the Commissioner for Electronic Communications and Postal Regulation (OCECPR). You can do it online; its website is in Greek, but it offers a complaint form in English.

:::sources
- [Universal Postal Union: S10, identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – 13 characters, first letters R, C, E, L and U, check digit, issuing country's code
- [Universal Postal Union: member countries, Cyprus](https://www.upu.int/en/Universal-Postal-Union/About-UPU/Member-Countries?csid=-1&cid=86) – Cyprus Post, designated operator
- [Universal Postal Union: list of designated operators](https://www.upu.int/getmedia/0ff98b1d-9dd4-46e7-bdd1-dda1b654308f/SettlementCircularsEn.pdf) – Department of Postal Services (Cyprus Post)
- [National Open Data Portal: list of post offices](https://www.data.gov.cy/el/dataset/katalogos-kai-theseis-tahydromikon-grafeion) – Department of Postal Services, the state service in charge of postal services (in Greek)
- [OCECPR: universal postal service](https://ocecpr.ee.cy/katanalotes/katholiki-ypiresia/taxidromeia) – Cyprus Post as universal postal service provider (in Greek)
- [OCECPR: complaints](https://ocecpr.ee.cy/katanalotes/parapona) – provider first, in writing; 15 days; online complaint; complaint form in English (in Greek)
- [Consumer Protection Service: online purchases](https://consumer.gov.cy/en/consumer-rights/66/59/?ctype=ar) – delivery within 30 days, extra period, ending the contract (in Greek)
- [Consumer Protection Service: contact us](https://consumer.gov.cy/en/contact-us) – free Consumer Line 1429 (in Greek)
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 duty per item from 1 July 2026, €2 Union handling fee from 1 November 2026
- [European Commission: guidance on the EUR 3 customs duty and the Union handling fee](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – what an item is, postal consignments, the fee on any value, who pays, VAT on the fee
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads: Cyprus Post's English tracker, its columns and event names
:::
