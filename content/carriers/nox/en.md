---
title: nox NachtExpress tracking: statuses, drop-off and contact
description: Track a nox NachtExpress shipment: what its barcode looks like, what nox's German scans mean, night delivery to your drop-off place, deadlines and contact.
slug: nox-tracking
published: 2026-11-02
updated: 2026-11-02
---

nox (nox NachtExpress) is a German night express company, nox Germany GmbH, based in Langenfeld. It carries spare parts and other urgent goods for businesses. It collects them in the afternoon and delivers them during the night, usually before 8:00, to a place agreed with the recipient: a safe, a garage, a workshop or the boot of a service van. Track a shipment with its barcode on [nox's tracker](https://trackandtrace.app.nox.express/scannungen), or paste the number in the box above. The tracker is in German: the tables below explain it.

## nox barcode: what it looks like and where to find it

nox calls its tracking number a barcode. Every package must carry a nox label, which the sender creates when it books the shipment, so the sender has the barcode too. No number? Ask the sender, or see [how to find your tracking number](guide:find-tracking-number). The shapes in Peek's code:

| Barcode (invented) | What it is |
| --- | --- |
| `220000000000000101000000` | 24 digits starting 22000: a client code, 000, the sender's customer number, the date the label was printed and a serial. Peek's catalog knows this shape. [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost) and [Austrian Post](carrier:austrian-post) use 24 digits too. |
| `SWR000000000000000000000` | SWR and 21 digits: an older nox barcode. |
| `RA1000000000000000000000` | RA, then 1 or 2 (the pickup type), 12 letters or digits for the customers and a 9-digit serial: a return barcode. |
| `C00000000000000000000000000000` | C and 29 digits: a sender's own barcode, which nox's tracker also answers. |
| `trackandtrace.app.nox.express/scannungen/SWR000000000000000000000` | A link to one shipment on nox's tracker. Paste the whole link into Peek. |

Other nox barcodes have 24 digits with another two-digit client code before the 000. In Peek, spaces don't matter, and small letters are fine.

## nox tracking statuses in English

nox's tracker shows each scan with its `Status`, `Scanort` (place), `Bemerkung` (remark), `Datum` (date) and `Uhrzeit` (time). In nox's wording, Collo means one package of your shipment and Niederlassung means a nox branch.

| nox says | What it means for you | Peek's status |
| --- | --- | --- |
| `Collo durch Versender bereitgestellt zur Abholung` | The sender has the package ready for nox to collect. nox doesn't have it yet. | "Label created" |
| `Collo in der nox Niederlassung eingetroffen` | The package has arrived at a nox branch. | "In transit" |
| `Collo von der nox Niederlassung weiterverladen` | The branch has loaded it onward, to another branch. | "In transit" |
| `Collo steht am Lager in nox Niederlassung, Zustellung nicht im geplanten Zeitfenster` | It is waiting at the branch and was not delivered in the planned time window. It goes out on a later night. | "In transit" |
| `Collo in nox Zustellfahrzeug geladen` | It is in the delivery vehicle. | "Out for delivery" |
| `Collo von nox zugestellt` | Delivered: usually left at your agreed drop-off place, without a signature. | "Delivered" |

If the tracker says `Der von Ihnen angegebene Barcode ist uns nicht bekannt.`, nox doesn't know that barcode: check it with the sender. For more details, or to make a complaint, the tracker asks you to check the recipient's postcode: press `PLZ validieren/Reklamation erfassen`.

## How nox delivers: at night, to your drop-off place

**When.** nox usually collects from the sender between 14:00 and 18:00. Under nox's terms, NachtExpress delivers the next day, Tuesday to Saturday, public holidays included, as a rule by 8:00. The sender can book delivery before 7:00 or before 6:00. nox also sells delivery at weekends and on public holidays as a separate service (WochenendService). For packages over 100 kg, or longer than 3.20 m, wider than 1.20 m or higher than 1.50 m, the delivery time can vary. Shipments to islands in the North Sea and the Baltic Sea have no delivery time promise.

**Where.** nox delivers while many of its customers are still asleep, so it leaves the shipment at a drop-off place agreed in advance, usually without a signature. nox gives these examples:

- a separate box on your company's site;
- the workshop, wash hall, break room or garage;
- the boot of a service technician's vehicle;
- the gatekeeper, who gets it from the driver in person;
- one of nox's service stations with 24-hour access, such as a petrol station open around the clock.

**Setting your drop-off place.** Register or update it, with photos and a description, in nox's online tool [ready2nox](https://ready2nox.de/), at any time. Under nox's terms this is the only way to set it, and the place stays agreed until you change it there. Once the package is at that place, it counts as delivered.

**No drop-off place.** Under nox's terms, nox can then leave the shipment at your address. If the risk of loss is clearly too high, nox may not deliver it and sends it back to the sender.

**Collecting it.** As an extra service, you can collect shipments at the nox branch instead, where nox keeps them for you.

**Proof.** When nobody is there, nox photographs the package and the spot where it was left, and keeps the photos as evidence. nox may share a photo case by case with someone who shows a legitimate interest, but there is no legal right to one.

**Outside Germany.** nox is in 20 other European countries through sister companies and partners. Shipping abroad needs the sender's prior agreement with nox, and the rules for complaints can differ by country.

## nox shipment late, damaged or missing

For a replacement, the business that sent the goods is your contact.

**Not moving.** If `Collo durch Versender bereitgestellt zur Abholung` is still the only scan, nox hasn't collected the package yet: ask the sender. If it is waiting at the branch, it goes out on a later night (see the table above). For a delay, use nox's tracker: check your postcode, then choose `Laufzeitverzug` (delay) or `Sonstige Anliegen` (other). See also [why tracking stops updating](guide:tracking-not-updating).

**Delivered but not there.** Check your agreed drop-off place first, then the other places your business uses. Ask the gatekeeper, or the technician whose vehicle may have got it. Then tell nox quickly, before the deadline below. More in [delivered but not received](guide:delivered-not-received).

**Damaged.** For NachtExpress, nox must get every complaint and damage report by 12:00 on the delivery day, in writing, with a description. If that day is a Saturday, Sunday or public holiday, the deadline is 12:00 on the next working day. A general reservation is not enough, and after the deadline nox's terms exclude its liability. Keep the goods in their original packaging for nox to inspect and take back, or nox isn't liable. Report it on the tracker, after the postcode check (its damage form is `Reklamation einer Beschädigung`), through nox's contact form or in nox's customer portal. See [damaged parcels](guide:damaged-parcel).

**Customs.** nox's pages mention customs only as a service it offers its customers, for imports and exports. Ask the sender, and see [parcels held at customs](guide:customs).

## nox contact and customer service

Peek can't contact nox or the sender for you. As read on nox's sites on 1 November 2026:

- **Customer service:** +49 (0) 4224 920 000, Monday to Friday, 7:00 to 17:00, or customerservice@nox-nachtexpress.de, as nox's tracker gives them.
- **Contact form:** [nox's contact form](https://www.nox.eu/de/de/kontakt/kontaktformular/), for questions and complaints.
- **Complaints:** on the tracker, after the postcode check. Its forms (delay, damage, other) ask whether you are the recipient (Empfänger), the sender (Versender) or a third party who ordered the transport.
- **Company:** nox Germany GmbH, Katzbergstr. 3, 40764 Langenfeld, Germany; welcome@nox.eu.

To follow all its shipments, a business needs an account on nox's customer portal: ask nox through the contact form.

## Track a nox shipment with Peek

Peek can't tell nox by the number alone: its 22000 barcodes share their length with Ciblex, InPost, bpost and Austrian Post, and Peek doesn't match its other barcodes to nox by shape. Paste it all the same: Peek follows more than 3,500 carriers. While you add a 22000 barcode, Peek asks the carriers that use the shape, those of your country first ("Checking tracking services…"), and shows "nox has this parcel." when nox's tracker knows it. If several know it, you choose.

If none does yet, the parcel reads "No tracking history yet" while Peek keeps checking, or "Waiting for the carrier" once you've picked nox. To pick it yourself, press "Change" and choose nox: it is under "Fits this number" for a 22000 barcode; for an SWR, RA or C barcode, search for it (NOX Nachtexpress, Innight Express and TNT Innight find it too). On a parcel you've already added, use "Change carrier". If you pick nox for a number of another shape, Peek says it "doesn’t issue numbers like this one" and doesn't ask nox: once added, unless a tracking service has the parcel, it shows "Check the tracking number". A link from nox's tracker, at trackandtrace.app.nox.express or tcs.app.nox.express, files the shipment under nox straight away.

Peek reads the status, each scan's German wording and time (as German time) and the delivery time. It leaves out the scan place and the remark, and it asks for no postcode, so it doesn't show what the tracker shows after the postcode check. Peek shows no expected delivery day, weight, drop-off place or delivery photo.

Day and night, Peek checks every 10 minutes, or every 2 from 6:00 German time on the day nox scans a shipment out for delivery, while nox's own tracker answers (through tracking services, every 15 minutes). After two days without news (12 hours while the shipment still shows "Label created" or "Received by carrier"), Peek checks hourly. Peek can notify you of "Every scan", "Important steps" or "Delivery only". It can't set your drop-off place or file a complaint for you.

## Questions about nox tracking

### What is nox NachtExpress?

The night express service of nox Germany GmbH, from Langenfeld. Goods collected by the early evening reach the recipient before work starts on the next working day. Its core trades are the motor industry, commercial vehicles, construction machinery and agriculture, and it has special solutions for healthcare.

### When does nox deliver?

During the night, as a rule by 8:00, Tuesday to Saturday, public holidays included. The sender can book delivery before 7:00 or 6:00.

### Where does nox leave my shipment?

At the drop-off place agreed for your address, which you set in nox's ready2nox tool. Without one, nox can leave it at your address.

### Does nox need a signature?

Usually not: nox delivers without a receipt, often while nobody is there, and then photographs the package where it was left. At a gatekeeper, the driver hands it over in person.

### How do I report a damaged nox delivery?

For a NachtExpress delivery, by 12:00 on the delivery day, in writing, through nox's tracker or contact form. Keep the goods and the original packaging. If the delivery day is a weekend or public holiday, you have until 12:00 on the next working day.

:::sources
- [nox: Sendungsverfolgung/Reklamation erfassen](https://www.nox.eu/de/de/kundenbereich/sendungsverfolgung/) – the barcode form that opens nox's tracker, customer portal for all shipments
- [nox: Track & Trace](https://trackandtrace.app.nox.express/scannungen) – the tracker's columns, unknown barcode, postcode check, complaint forms, customer service phone, hours and email, ready2nox
- [nox: Hinweise zum Nachtversand](https://www.nox.eu/de/de/kundenbereich/hinweise-zum-nachtversand/) – pickup 14:00 to 18:00, delivery before 8:00, drop-off places, large packages, 20 other countries, complaints through the contact form, damage deadline
- [nox: NachtExpress](https://www.nox.eu/de/de/leistungen/nachtexpress/) – before 8:00, 7:00 or 6:00, delivery without a receipt, drop-off place examples, collection at the branch, customs service
- [nox: Abstellplatzmanagement](https://www.nox.eu/de/de/leistungen/abstellplatzmanagement/) – drop-off place examples, ready2nox with photos and descriptions
- [nox: Über uns](https://www.nox.eu/de/de/ueber-uns/) – delivery before work starts, customers asleep, core trades
- [nox: Allgemeine Geschäftsbedingungen, from 1 September 2026 (PDF)](https://cms.nox.eu/de/wp-content/uploads/sites/4/2026/09/Allgemeine-Geschaftsbedingungen-fur-Geschaftskunden-nox-DE-Sept-2026-Final.pdf) – nox labels, delivery days, drop-off place set in ready2nox, delivery without one, photos, islands, international shipping, complaint deadline, rules by country
- [nox: Allgemeine Geschäftsbedingungen](https://www.nox.eu/de/de/agb/) – the terms in force
- [nox: Kontakt](https://www.nox.eu/de/de/kontakt/kontaktformular/) – contact form
- [nox: Impressum](https://www.nox.eu/de/de/impressum) – nox Germany GmbH, Langenfeld, phone, email
- [Universal Parcel Scraper: nox](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/nox) – barcode shapes, tracker links, scan wordings and stages, German time, what Peek reads
:::
