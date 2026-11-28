---
title: DHL eCommerce Iberia tracking: statuses and ServicePoints
description: Track a DHL eCommerce Iberia parcel in Spain or Portugal: 10-digit and JJD numbers, status meanings, ServicePoints, CTT Express handover and claim contacts.
slug: dhl-ecommerce-iberia-tracking
published: 2026-10-11
updated: 2026-11-27
---

DHL eCommerce Iberia (formerly DHL Parcel Iberia) is DHL's parcel network in Spain and Portugal, and under a joint venture signed in May 2026, CTT Express takes its deliveries to consumers in Spain and CTT Expresso its business in Portugal. Paste the number from the shop in the box above (and open DHL's tracker directly from the parcel page). Below: English and Spanish status meanings, ServicePoint pickups and what to do if a parcel is delayed or damaged.

## DHL eCommerce tracking number: where it is and what it looks like

No number? Ask the shop, or see [how to find your tracking number](guide:find-tracking-number).

The shapes Peek's catalog knows (examples invented):

| Shape | Example | Whose it can be |
| --- | --- | --- |
| 10 digits | `4829173649` | DHL eCommerce Iberia, but also [DHL Express](carrier:dhl-express), Mondial Relay, Relais Colis and GLS Spain |
| Two letters, 7 digits, then `0` | `KL48291730` | DHL eCommerce Iberia |
| `JJD000061`, `JJD000268`, `JJD000269` or `JJD000368`, then 14 digits | `JJD00006148291736482917` | DHL eCommerce Iberia |

If the shop's email names [Mondial Relay](carrier:mondial-relay) or [GLS Spain](carrier:gls-es) for a 10-digit number, track it there. Two letters, nine digits and `ES` is a [Correos](carrier:correos-spain) number.

## DHL eCommerce tracking statuses explained

Peek asks DHL for its English wordings, though a few scans still come in Spanish, such as `Llegada a`, `Tránsito en`, `Salida de` and `Entregado`. The usual ones, in the order a parcel gets them:

| DHL says | What it means for you |
| --- | --- |
| `Shipment data received` | The sender has registered the parcel. DHL doesn't have it yet. |
| `Picked up` | DHL collected the parcel from the sender; the town follows. |
| `Shipment received at DHL Depot` | The parcel reached a DHL depot. |
| `Arrived at` | Moving between depots; the town follows. `In transit in` and `Departed from` work the same way. |
| `Out for delivery` | On the van today. |
| `The delivery could not be made due to consignee absent` | Nobody answered. See the next section for what happens. |
| `Delivered to DHL ServicePoint, ready for collection by the recipient` | Ready to pick up at a ServicePoint. |
| `Parcel picked up by recipient` | Collected from the ServicePoint. |
| `Delivered` | Delivered to the address. |
| `Shipment being returned to sender` | A return is under way. |

Some need explaining:

- `Delivered, POD pending`: marked delivered, proof of delivery not in yet. It can appear early: a later delivery round or a failed attempt may still follow.
- `Shipment with incidence If you are the sender, please contact DHL Parcel, if you are the recipient please contact the sender`: something has gone wrong with the parcel. As the scan says, the recipient should contact the sender: talk to the shop.
- `The expected delivery date might be postponed one working day` and `Not delivered today: delivery expected for the next working day` are short delays, not a lost parcel.

## Missed delivery and ServicePoints

**Nobody home.** A failed attempt shows as `The delivery could not be made due to consignee absent`, or `Receiver closed: delivery not possible` when the address was closed. Later scans such as `New delivery date requested` or `Delivery agreed with recipient` mean a new delivery date has been asked for or agreed. If you see `Incorrect address details: delivery not possible` instead, the address details didn't let the courier deliver: Peek shows "Needs attention", and you or the sender need to sort it out before the parcel moves on.

**ServicePoints.** `Parcel will be delivered to a DHL ServicePoint` and `Parcel is on it's way to our DHL ServicePoint` mean the parcel is heading for a DHL ServicePoint shop. Once it's ready there, Peek shows a "Pickup point" card with the shop's name and address, directions and a button to copy the address. Peek doesn't get the opening hours: check them, and how long the parcel waits, with DHL.

## Parcel delayed, lost or damaged

**No update.** See [why tracking stops updating](guide:tracking-not-updating).

**Lost or damaged.** Start with the shop: DHL's own `Shipment with incidence` scan tells recipients to contact the sender. In the EU, Your Europe says the shop is responsible for damage until you receive the goods. Without an agreed date, it must deliver within 30 days; if it doesn't, remind it and give it a reasonable extra time, after which you can cancel and get your money back. See [lost parcels](guide:lost-parcel) and [damaged parcels](guide:damaged-parcel).

**Customs.** For a parcel from outside the EU, see the [customs guide](guide:customs).

**CTT Express.** DHL eCommerce and CTT signed a joint venture on 12 May 2026: in Portugal, DHL eCommerce's business moves to CTT Expresso, and in Spain CTT Express takes deliveries to consumers while DHL keeps business deliveries. Some parcels carry a CTT Express number: track those with [CTT Express](carrier:ctt-express).

> A suspicious text or email about a DHL parcel? Read [how to spot a fake delivery message](guide:delivery-scam-texts).

## Contacting DHL eCommerce

Look for DHL eCommerce's contact details on DHL's website for the country the parcel is delivered in, Spain or Portugal. For a lost or damaged parcel, talk to the shop first.

## Tracking DHL eCommerce Iberia parcels with Peek

[Peek](/) asks DHL eCommerce Iberia and [TNT Italy](carrier:tnt-it), whose waybills have the same shape, about two letters, 7 digits and `0`. For 10 digits it asks DHL eCommerce Iberia, Packeta and BOX NOW, with DHL Freight (from Portugal or with no country known, only once the others have missed the number), and DHL Express first when its check digit passes (then also ACS Courier and Geniki Taxydromiki, whose numbers pass the same check); if DHL Express knows the number too, it wins. If carriers of different brands know it, or none does, you pick with "Change carrier". A `JJD` plate of the shape in the table above is named DHL eCommerce Iberia at once. DHL's other numbers (12 or 22 digits, other `JJD` plates, postal numbers) work only after picking "DHL eCommerce Iberia" there. No postcode is needed.

Shipment links from DHL eCommerce Iberia's tracking page can be pasted too, in Spanish, Portuguese or English (`/LiveTracking/ModificarEnvio/`, `ModificarMeuEnvio` or `ModifyMyShipment`). They name DHL eCommerce Iberia straight away, whatever the number's shape. A postcode at the end of the link is left out, as none is needed.

DHL's scans carry no time zone: Peek reads them on the clock of the scan's place and lists DHL's own history. When DHL's answer carries a CTT Express number, Peek also asks [CTT Express](carrier:ctt-express) and follows the parcel there once CTT Express shows a scan no older than DHL's newest. Statuses keep DHL's English wording, except `Delivered` and `Out for delivery`, which show as Peek's own. Peek can notify you when the status changes. It shows no expected delivery date or delivery time for DHL eCommerce Iberia.

## Questions about DHL eCommerce tracking

### Is DHL eCommerce Iberia the same as DHL Express?

No. DHL Express waybills have their own MyDHL+ tracker, and Peek treats the two as separate carriers. Because 10-digit numbers can fit either, Peek asks DHL Express first when its check digit passes, and DHL Express wins if it knows the shipment.

### Does CTT Express deliver DHL eCommerce parcels in Spain and Portugal?

Some of them. Under the joint venture signed on 12 May 2026, CTT Express handles deliveries to consumers in Spain, while DHL keeps business deliveries, and DHL eCommerce's Portugal business moves to CTT Expresso. If tracking provides a CTT Express number, follow it under [CTT Express](carrier:ctt-express).

### What do "Delivered, POD pending" and "Shipment with incidence" mean?

`Delivered, POD pending` means the parcel is marked delivered before proof of delivery is uploaded — it can appear early, and another delivery round or failed attempt may still follow. `Shipment with incidence` flags a problem, and DHL's own scan instructs recipients to contact the sender.

### Why does my DHL eCommerce Iberia parcel have no tracking?

Confirm both the number and carrier with the shop, as 10-digit numbers are shared across DHL Express, Mondial Relay, GLS Spain and others. Older shipments can also age out of DHL's tracker.

### Who do I contact if a DHL eCommerce Iberia parcel is lost or damaged?

Contact the shop first: DHL's own incidence scan directs recipients to the sender, and in the EU, Your Europe says the shop is responsible for damage until you receive the goods and, without an agreed date, must deliver within 30 days. For local delivery queries, check DHL eCommerce's website for Spain or Portugal.

:::sources
- [CTT: CTT e DHL Ecommerce selam parceria estratégica](https://www.ctt.pt/grupo-ctt/media/noticias/ctt-e-dhl-ecommerce-selam-parceria-estrategica-para-criar-rede-de-distribuicao-de-encomendas-mais-completa-da-peninsula-iberica) – joint venture signed 12 May 2026, DHL eCommerce Portugal to CTT Expresso, CTT Express for consumers in Spain, DHL for business deliveries
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – shop responsible for damage until delivery, 30 days, extra time, refund
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads, status wordings and their stages, ServicePoint shop, shipment links, former DHL Parcel Iberia name, CTT Express numbers
:::
