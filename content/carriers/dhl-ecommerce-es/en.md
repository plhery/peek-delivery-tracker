---
title: DHL eCommerce Iberia tracking: statuses and ServicePoints
description: Track a DHL eCommerce parcel in Spain or Portugal: what each status means, missed deliveries, ServicePoints, CTT Express and what to do if it's lost or damaged.
slug: dhl-ecommerce-iberia-tracking
published: 2026-10-11
updated: 2026-10-26
---

DHL eCommerce Iberia is DHL's parcel network in Spain and Portugal, formerly DHL Parcel Iberia, and the DHL Parcel name still turns up in places. Paste the number in the box above to track the parcel; on its page, "Open the DHL eCommerce Iberia website" takes you to the same shipment on DHL's own tracking page. The number comes from the shop that sent the parcel.

## DHL eCommerce tracking number: where it is and what it looks like

No number? Ask the shop, or see [how to find your tracking number](guide:find-tracking-number).

The shapes Peek's catalog knows (examples invented):

| Shape | Example | Whose it can be |
| --- | --- | --- |
| 10 digits | `1234567890` | DHL eCommerce Iberia, but also [DHL Express](carrier:dhl-express), Mondial Relay, Relais Colis, TIPSA and GLS Spain |
| Two letters, 7 digits, then `0` | `AB12345670` | DHL eCommerce Iberia |

If the shop's email names [Mondial Relay](carrier:mondial-relay), [TIPSA](carrier:tipsa) or [GLS Spain](carrier:gls-es) for a 10-digit number, track it there. Two letters, nine digits and `ES` is a [Correos](carrier:correos-spain) number.

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

**Nobody home.** A failed attempt shows as `The delivery could not be made due to consignee absent`, or `Receiver closed: delivery not possible` when the address was closed. Later scans such as `New delivery date requested` or `Delivery agreed with recipient` mean a new delivery date has been asked for or agreed.

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

[Peek](/) asks DHL eCommerce Iberia and [TNT Italy](carrier:tnt-it), whose waybills have the same shape, about two letters, 7 digits and `0`. For 10 digits it asks Relais Colis, TIPSA and DHL eCommerce Iberia, and DHL Express first when its check digit passes; if DHL Express knows the number too, it wins. If carriers of different brands know it, or none does, you pick with "Change carrier". DHL's other numbers (12 or 22 digits, `JJD` plates, postal numbers) work only after picking "DHL eCommerce Iberia" there. No postcode is needed.

Shipment links from DHL eCommerce Iberia's tracking page can be pasted too, in Spanish, Portuguese or English (`/LiveTracking/ModificarEnvio/`, `ModificarMeuEnvio` or `ModifyMyShipment`). They name DHL eCommerce Iberia straight away, whatever the number's shape. A postcode at the end of the link is left out, as none is needed.

DHL's scans carry no time zone: Peek reads them on the clock of the scan's place and lists DHL's own history. Statuses keep DHL's English wording, except `Delivered` and `Out for delivery`, which show as Peek's own. Peek can notify you when the status changes. It shows no expected delivery date or delivery time for DHL eCommerce Iberia.

## Questions about DHL eCommerce tracking

### Is DHL eCommerce the same as DHL Express?

No. DHL Express waybills have their own tracking (the MyDHL+ tracking page), and Peek treats the two as separate carriers. A 10-digit number can belong to either: Peek asks DHL Express first when its check digit passes, and if DHL Express knows the number, it wins.

### Why does my DHL eCommerce parcel have no tracking?

Check the number and the carrier with the shop: 10-digit numbers are shared by several carriers (see the table above). An old shipment can also drop out of DHL's tracking; how long DHL keeps the history isn't known.

### Does CTT Express deliver DHL eCommerce parcels?

Some of them. Under the joint venture DHL eCommerce and CTT signed in May 2026, CTT Express takes deliveries to consumers in Spain, and DHL eCommerce's business in Portugal moves to CTT Expresso. If tracking gives you a CTT Express number, track it with [CTT Express](carrier:ctt-express).

:::sources
- [CTT: CTT e DHL Ecommerce selam parceria estratégica](https://www.ctt.pt/grupo-ctt/media/noticias/ctt-e-dhl-ecommerce-selam-parceria-estrategica-para-criar-rede-de-distribuicao-de-encomendas-mais-completa-da-peninsula-iberica) – joint venture signed 12 May 2026, DHL eCommerce Portugal to CTT Expresso, CTT Express for consumers in Spain, DHL for business deliveries
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – shop responsible for damage until delivery, 30 days, extra time, refund
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads, status wordings and their stages, ServicePoint shop, shipment links, former DHL Parcel Iberia name, CTT Express numbers
:::
