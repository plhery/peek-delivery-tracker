---
title: Raben tracking: shipment number, statuses and delivery
description: Track a Raben pallet or groupage shipment: what its number looks like, what each step on Raben's tracker means, unloading, damage claims and who to contact.
slug: raben-tracking
published: 2026-11-01
updated: 2026-11-01
---

Raben Group is a family-owned European logistics group that started in 1931 in the Netherlands and is now based near Poznań, Poland. Its road network carries pallets and groupage (part loads) through branches in 17 European countries, and it delivers for online shops, so a bulky order can come on a Raben truck. Track it with the shipment number in the "Shipment tracking" box on raben-group.com, or paste the number in the box above.

## Raben shipment number: what it looks like and where to find it

Raben gives each shipment its own number. The business that booked the transport has it, usually the shop or the manufacturer: look in the shipping email or on your order page. Raben's Polish companies say they get the recipient's details from whoever ordered the transport, so ask the sender if you have no number. More in [how to find your tracking number](guide:find-tracking-number).

| Number (invented) | What it is |
| --- | --- |
| `270000000000000` | 27 and 13 more digits: the shape of a Raben shipment number in Peek's catalog. |
| `27 0000 0000 0000 0` | The same with spaces. Spaces and dashes don't matter in Peek. |
| `oftc.myraben.com/link/ShipmentInformation?ShipmentNumber=270000000000000` | A link to one shipment on Raben's tracker, as raben-group.com's tracking box opens it. Paste the whole link into Peek. |

[BRT](carrier:brt) in Italy and Yunda in China use 15-digit numbers too, so a number like this isn't always Raben's. Raben's search also finds a shipment by a reference or a package label, but Peek only follows the shipment number itself.

## Raben tracking statuses explained

Raben's tracker, myRaben, shows the status, each step with its place and time, the expected delivery date, the weight, the item count and volume, a progress bar, remarks and the contact details of the Raben office handling the shipment. Its steps, in the order a shipment usually gets them:

| Raben says | What it means for you | Peek's status |
| --- | --- | --- |
| `registered in the system` | The sender has booked the transport. Raben has the order, not yet the goods. | "Label created" |
| `arrived to the pick-up place` | The truck has reached the sender to collect it. | "Label created" |
| `collected` | Raben has collected it from the sender. | "Received by carrier" |
| `loaded` | Loaded on a truck: the one to the next depot, or the delivery truck. | "In transit" |
| `unloaded` | Unloaded at the next depot. | "In transit" |
| `Inventory in a warehouse` | Checked in at a depot's warehouse. | "In transit" |
| `shipment in depo` | At a depot. | "In transit" |
| `in progress - advice required` | On its way. The delivery has to be announced to the recipient first. | "In transit" |
| `in progress - fix delivery` | On its way, to be delivered on a fixed day. | "In transit" |
| `in delivery` | On the delivery round. | "Out for delivery" |
| `arrived to the delivery place` | The truck has reached the delivery address. | "Out for delivery" |
| `delivered` | Delivered. | "Delivered" |
| `delivered D&D` | Delivered and unloaded by the driver. | "Delivered" |
| `not delivered - self collection` | Not delivered: marked for collection at the depot, though a delivery round can still follow. Ask the Raben office on the tracker before you go. | "Ready for pickup" |
| `not delivered - consignee not available` | The driver didn't find you in. | "Delivery attempted" |

**Other failed deliveries.** `not delivered - delivery refused`, `not delivered - wrong address`, `not delivered - truck with lift required` (it needs a truck with a tail lift) and `not delivered - wrong/missing docs` give the reason. Peek shows "Delivery attempted".

**Hold-ups.** `not delivered - breakdown lorry` and `not delivered - strike` are delays on the road; `not delivered - at sender disposal` means Raben is holding it for the sender's instructions; `cancelled` means the transport was called off. Peek shows "Needs attention".

**Delivered, with a note.** `delivered - damages`, `delivered - discrepancies`, `delivered - partly returned` and `delivered with delay` all mean it was handed over, and say what went wrong. Peek shows "Delivered".

**Changes of plan.** `new delivery date agreed with consignee` and `shipment parameters updated` change a date or data: the goods haven't moved.

**Customs.** A shipment that goes through customs gets steps such as `customs: clearance in progress` and `customs clearance completed`; `customs: lack of invoices / incomplete invoices` means customs is missing invoices, so ask the shop. See [customs](guide:customs) and [tracking statuses](guide:tracking-statuses).

## How Raben delivers: pallets, unloading and timing

Raben Poland's pallet FAQ explains how a delivery works there. Extras are booked by Raben's client, the business that ordered the transport (usually the shop):

- **Unloading.** Unloading is the recipient's job: Raben isn't responsible for it, or for bringing a forklift or ramp. The client can book manual unloading by the driver (HUN) or unloading at the delivery place (UNLO). For a heavy item, ask the shop what it booked.
- **Timing.** Within Poland a pallet takes one working day as standard, or two, depending on the service bought; abroad it depends on the destination country. The client can also book a fixed day (FIX), delivery by 8:00, 10:00, 12:00 or 16:00 (ND 08 to ND 16), or an agreed day and hour (WXACT).
- **If you're out.** When a delivery fails because of the recipient, Raben may charge the client.
- **Delivery photos.** With Picture Confirming Delivery (PCD), a free service the client picks, you needn't be there. The driver leaves the goods where you asked and photographs the label, the shipment and both together; the photos become the delivery note.

## Raben shipment late, damaged or not delivered

**Not moving.** If the steps stop for days, ask the shop or the Raben office the tracker names. See [tracking that isn't updating](guide:tracking-not-updating).

**Late.** In the EU, the shop has to deliver within 30 days unless you agreed another time, says the EU's Your Europe site. After that you can give it extra time, then cancel and get your money back.

**Damaged or lost.** The trader is responsible for damage from dispatch until you receive the goods, and Your Europe says to contact the trader first. See [damaged parcel](guide:damaged-parcel) and [lost parcel](guide:lost-parcel). Raben's own rules:

- **Poland.** Raben Logistics Polska takes claims from the sender or the consignee, in writing: through myClaim, an online form for those without a myRaben account, or a form sent by post, fax or email. Object to visible damage at delivery and report hidden damage within 7 days of delivery (for international transport, Sundays and public holidays don't count). Raben answers within 30 days of getting all the documents. A domestic shipment counts as lost from the 31st day after its planned delivery day.
- **Germany.** Raben Germany asks you to check the packaging and that nothing is missing at delivery, and to have visible damage written in detail on the delivery note and countersigned by the driver. Report damage in writing to the Raben branch in charge; hidden damage as soon as you find it, at the latest 7 days after delivery.

## Raben contact and customer service

As read on Raben's sites on 30 October 2026:

- **Your shipment's contact.** Raben asks you to contact the dedicated person shown on the shipment's status page, which Peek doesn't show.
- **Hours.** Raben Poland's customer service works Monday to Friday, 8:00 to 16:00, aims to answer within 3 hours and serves customers in Polish as standard.
- **Branches.** Raben's [contact page](https://www.raben-group.com/contact) finds a depot by postcode and lists branches by country; each country's site, such as polska.raben-group.com, has its own contacts.

## Track a Raben shipment with Peek

Raben's shape is shared with BRT and Yunda, so Peek can't tell Raben by the number alone. Paste it all the same: Peek follows more than 3,500 carriers. While you add it, Peek asks the carriers that use the shape ("Checking tracking services…") and shows "Raben has this parcel." when Raben's tracker knows the number; if several know it, you choose. If none does yet, the parcel reads "No tracking history yet" while Peek keeps checking, or "Waiting for the carrier" once you've picked Raben. To set the carrier yourself, press "Change" and pick "Raben" under "Fits this number", or search for it (Raben Group finds it too). On a parcel you've already added, use "Change carrier".

A link from myraben.com or oftc.myraben.com files the parcel under Raben straight away. If your Raben number has another shape, pick Raben by hand: Peek asks Raben's tracker for any 4 to 40 letters and digits, with at least one digit.

Peek reads Raben's tracker in English: the status, each step in Raben's wording with its town and country, the planned delivery day (a date, without a time) or, once the driver's place on the round sets one, the delivery slot with its times, the weight and the delivery time. Times show in your device's time zone. Steps that only change a date or data leave the status as it was. Peek leaves out the item count, volume, progress bar, remarks and the office's contact. The public tracker gives no sender or recipient name, address, pickup point, service or proof of delivery.

Peek checks every 10 minutes, and every 2 once the shipment is out for delivery. It can notify you of "Every scan", "Important steps" or "Delivery only". It can't contact Raben or the shop for you, book unloading or file a claim.

## Questions about Raben tracking

### What is Raben Group?

A family-owned European logistics group, founded in 1931 in Winterswijk, the Netherlands, and based in Robakowo near Poznań, Poland. It moves pallets and part loads by road across 17 countries, usually booked by the sender.

### How do I track a Raben shipment?

Type the shipment number in the "Shipment tracking" box on raben-group.com and press "Search": Raben's tracker opens in a new tab. Or paste the number or the tracker's link in the box above.

### Will the Raben driver unload my order?

Under Raben Poland's rules, only if the business that booked the transport ordered it: unloading is the recipient's job, and unloading by the driver is an extra service (HUN or UNLO). A `delivered D&D` step means the driver delivered and unloaded it.

### How long does Raben take to deliver?

Within Poland, one or two working days, depending on the service the sender bought; abroad, it depends on the destination country. Raben's tracker shows the expected delivery date.

### Who do I contact about a damaged Raben delivery?

The shop first: in the EU the seller is responsible until you receive the goods. In Poland you can also claim from Raben Logistics Polska yourself, in writing; report hidden damage within 7 days of delivery.

:::sources
- [Raben Group: home page](https://www.raben-group.com/) – tracking box, "Search", new tab, 17 countries
- [Raben Group: Customer Zone](https://www.raben-group.com/customer-zone) – a number per shipment, expected date, the status page's contact
- [Raben Group: Contact](https://www.raben-group.com/contact) – depot search, branches by country
- [Raben Group: Road transport and international transport](https://www.raben-group.com/services/road-network) – groupage
- [Raben Group: E-commerce](https://www.raben-group.com/e-commerce) – delivery for online shops
- [Raben Group: Picture Confirming Delivery](https://www.raben-group.com/it-solutions/pcd) – free service, photos, delivery note
- [Raben Group: Raben Group in Europe](https://www.raben-group.com/about-us/raben-group-in-europe) – 1931, Winterswijk, family-owned, 17 countries
- [Raben Group: Imprint](https://www.raben-group.com/imprint) – Robakowo near Poznań
- [Raben Poland: Pytania i odpowiedzi - Transport paletowy](https://polska.raben-group.com/strefa-klienta/pytania-i-odpowiedzi-transport-paletowy) – unloading, HUN, UNLO, timing, FIX, ND, WXACT, failed delivery fee, PCD, customer service hours, Polish
- [Raben Poland: Strefa uczestnika przewozu](https://polska.raben-group.com/strefa-uczestnika-przewozu) – recipient's details from whoever ordered the transport
- [Raben Logistics Polska: Claims](https://polska.raben-group.com/en/customer-zone/raben-logistics-polska/claims-raben-logistics-polska) – who claims, how, visible and hidden damage, loss, 30-day answer
- [Raben Germany: Transportschäden – Wir helfen Ihnen](https://deutschland.raben-group.com/kundenbereich/reklamationen) – checks at delivery, written report, 7 days
- [Your Europe: Your rights when shopping in the EU](https://europa.eu/youreurope/citizens/consumers/shopping/shopping-consumer-rights/index_en.htm) – 30 days, trader responsible, contact the trader first
- [Universal Parcel Scraper: Raben](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/raben) – number shape, links, references, what the tracker shows, steps and stages, what Peek reads
:::
