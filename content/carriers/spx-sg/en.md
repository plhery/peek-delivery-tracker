---
title: SPX Express Singapore tracking: Shopee parcels and statuses
description: Track an SPX Express (Shopee Xpress) parcel in Singapore: what SPXSG and SG numbers look like, what each status means, GST on imports, and what to do if it stops.
slug: spx-express-singapore-tracking
published: 2026-11-03
updated: 2026-11-03
---

SPX Express Singapore, also called Shopee Xpress or Shopee Express, delivers parcels in Singapore. It carries Shopee orders, including parcels from sellers in China that pass through Shopee's sorting centre in Hong Kong. SPX's own tracker is at spx.sg. To follow a parcel here, paste its number in the box above. For a Shopee order, open the order in Shopee and copy the tracking number from its shipping information.

## SPX Express Singapore tracking number: what it looks like

Peek knows two shapes for SPX Express Singapore. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `SPXSG000000000000` | SPXSG, 11 digits and one more digit or letter (17 characters): SPX's own number for the parcel. Peek names SPX Express Singapore at once. |
| `SG0000000000000` | SG, 12 digits and a final digit or letter (15 characters): the Shopee number that SPX's tracker pairs with the parcel. Peek can't name SPX from this shape alone (see below). |
| `SPXSG0000000000` | SPXSG and only 10 digits: Peek doesn't recognise it. Check for a missing digit. |
| `RA000000005SG` | Two letters, nine digits and SG at the end: a [Singapore Post](carrier:singapore-post) number, not SPX's. |

Spaces and lower case don't matter when you paste a number into Peek.

**The order number is not a tracking number.** When a pasted number has the shape of a Shopee order number, Peek says "That looks like a Shopee order number". Copy the tracking number from the order instead. Can't find it? See [how to find your tracking number](guide:find-tracking-number).

## SPX Express Singapore tracking statuses explained

SPX's tracker, as Peek reads it, writes every line in English. These are the lines Peek's code knows, roughly in the order a parcel from abroad meets them:

| SPX says | What it means for you | Peek shows |
| --- | --- | --- |
| `[China]Sender is preparing to ship your parcel` | The seller in China is getting the parcel ready. SPX doesn't have it yet. | "Label created" |
| `[China] Parcel has arrived at Hong Kong sorting centre` | It reached Shopee's sorting centre in Hong Kong. | "In transit" |
| `[China] Parcel has departed from Hong Kong sorting centre` | It left that sorting centre. | "In transit" |
| `[China] Parcel has been handed over to delivery service provider` | It was handed to an air freight company, which sends it on. | "In transit" |
| `[China] Parcel has cleared export customs` | It cleared customs on the way out, not Singapore's. | "In transit" |
| `Parcel has departed from the origin port: Hongkong` | It has left Hong Kong. | "In transit" |
| `Parcel has arrived at the destination port Singapore` | It has arrived in Singapore: an arrival, not a customs hold. | "In transit" |
| `Parcel has cleared import customs` | Singapore's import customs let it through. | "In transit" |
| `Parcel has been picked up by our local logistics partner for further sorting. You will be notified when the parcel is ready for delivery.` | It is now with the delivery network in Singapore, to be sorted before delivery. | "In transit" |
| `[Singapore] Parcel has arrived at sorting facility` | It is at a sorting facility in Singapore. | "In transit" |
| `[SPX Express] Parcel is out for delivery` | It is out for delivery. | "Out for delivery" |
| `Parcel has been delivered` | Delivered. | "Delivered" |

**Other steps.** Peek's code knows more of SPX's steps: a parcel collected from the seller or left at a drop-off point ("Received by carrier"), a failed delivery ("Delivery attempted") and a return to the seller ("Returned to sender", or "Needs attention" if the return stops moving). More in [what tracking statuses mean](guide:tracking-statuses).

## SPX Express delivery in Singapore

After `[SPX Express] Parcel is out for delivery`, the next line should be `Parcel has been delivered`. If SPX takes the parcel to a self-collection point instead of your door, Peek shows "Out for delivery" while it is on its way there.

Peek couldn't read SPX Express Singapore's website, so this page gives no delivery times, number of attempts or pickup rules. Ask the seller, or Shopee if you ordered there. See [missed deliveries](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

## Parcels from abroad: GST and customs in Singapore

Singapore Customs' rules for goods bought from overseas that come in by air or by post:

- **GST at checkout.** If each item costs S$400 or less and the overseas seller or marketplace is GST-registered, GST is already in the price. The seller sends the GST payment details to the courier, and GST is not charged again at import. The price here means the goods only, without shipping or insurance.
- **GST at import.** If the seller is not GST-registered and the shipment's total CIF value (cost, insurance and freight) is over S$400, GST is collected when the goods enter Singapore. At S$400 or less, no GST is charged at import, unless the goods are liquor or tobacco.
- **Charged twice?** If you paid GST at checkout and are asked again at import, pay the import GST to get your goods. Then ask the seller for a refund, with proof that you paid GST.

**Held at customs.** The Immigration & Checkpoints Authority (ICA) can hold a courier parcel with dutiable, controlled or prohibited goods, or one worth over S$400 (total CIF value) with no invoice when it arrived. ICA then gives the courier a warehouse deposit receipt, and the courier should tell you what to do next.

**Scams.** Singapore Customs says it doesn't ask for GST payments or personal details by SMS, email or messaging apps. See [delivery scam texts](guide:delivery-scam-texts) and [parcels held at customs](guide:customs).

## SPX parcel not updating, lost or returned

**Number not found yet.** When SPX doesn't know an SPXSG number yet, Peek shows "Waiting for the carrier" and keeps checking. The seller may not have handed the parcel over. Check the number too.

**Stuck on `[China]Sender is preparing to ship your parcel`.** The seller hasn't shipped it yet. Ask the seller when it will.

**No update for days.** Parcels often go quiet on long flights, at customs or over a weekend. When a parcel on its way has had no new line for four days, Peek flags it ("No tracking update for four days") and keeps asking SPX. See [why tracking stops updating](guide:tracking-not-updating) and [tracking parcels from China](guide:tracking-from-china).

**Delivered, but nothing came.** Look around your door and ask your household and neighbours. Then contact the seller, or Shopee. See [delivered but not received](guide:delivered-not-received).

**Lost or returned.** The seller booked the delivery, so ask the seller, or Shopee, about a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## SPX Express Singapore customer service and contact

As of 1 November 2026, Peek couldn't read SPX Express Singapore's website, so this page lists no phone number or email: Peek gives only contacts read on a carrier's own pages.

For a Shopee order, ask through Shopee. For an order from another shop, ask that shop first: it booked the delivery. Singapore Customs can't help: it has no access to courier tracking, and says to ask the courier.

## Track an SPX Express Singapore parcel with Peek

[Peek](/) follows an SPX parcel with the number alone: no account, phone number or postcode.

- **An SPXSG number.** Peek names SPX Express Singapore at once ("Detected carrier") and asks SPX's tracker. "Change" lets you pick another carrier.
- **A link.** Paste a link to SPX's tracker on spx.sg with an SPXSG number in it (`/track?`, `/m/track?` or `/detail/`), and Peek takes the number from it.
- **An SG number.** Peek can't be sure of the carrier from this shape alone. Paste it all the same: Peek follows more than 3,500 carriers. While it checks, you see "Checking tracking services…". To choose SPX yourself, press "Change": SPX Express Singapore is under "Fits this number". Better, use the SPXSG number if you have it: SPX's tracker often finds a Shopee parcel only by that number.

Added without a carrier, a parcel shows "No tracking history yet" while Peek asks tracking services. If one names SPX Express Singapore alone, with dated steps, Peek files the parcel under SPX.

**What Peek shows.** The status, each line in SPX's words, and the place for scans at warehouses, ports and sorting centres. SPX gives the exact moment of each scan, so Peek shows it in your device's time zone. Peek also keeps a few steps SPX's own page hides, such as import clearance and a return on its way back. It leaves out the seller's location, office addresses and the name of whoever took the parcel. SPX gives no expected delivery date, weight or pickup point, so Peek shows none.

**From Janio to SPX.** When a Janio parcel's tracking names SPX with an SPX Singapore number, the parcel card says "Delivery with SPX Express Singapore" and Peek asks SPX too. While SPX has only the announcement of the parcel, with no scan of its own, the card says "Announced to SPX Express Singapore, not received yet" instead. Once SPX has a scan as recent as Janio's latest, Peek follows the parcel there and keeps Janio's history.

If SPX's tracker can't be reached, Peek shows what tracking services report instead, in their words.

**Checks and alerts.** Peek checks every 10 minutes, and every 2 minutes once the parcel is out for delivery. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. Alerts: "Every scan", "Important steps" or "Delivery only". Peek can't contact SPX, Shopee or the seller for you.

## Questions about SPX Express Singapore tracking

### Is SPX Express the same as Shopee Express?

Yes. Peek's catalog also lists SPX Express Singapore as Shopee Xpress Singapore and Shopee Express Singapore, and it carries Shopee orders. SPX has separate networks in other countries, such as [SPX Express Brazil](carrier:spx-br): an SPXSG number belongs to the one in Singapore.

### What is the difference between an SPXSG number and an SG number?

An SPXSG number is SPX's own number for the parcel: Peek names SPX from it at once. An SG number is the Shopee number paired with it. SPX's tracker often finds a Shopee parcel only by its SPXSG number, so use that one.

### What does "Parcel has been picked up by our local logistics partner" mean?

Your parcel from abroad is in Singapore, and it has been handed to the local delivery network to be sorted. SPX says you will be told when it is ready for delivery. Peek shows "In transit".

### Why is my SPX tracking not updating?

If the last line is `[China]Sender is preparing to ship your parcel`, the seller hasn't shipped it yet. Later on, parcels often go quiet on flights, at customs or over a weekend. After four days without news, Peek flags the parcel. If it stays quiet, ask the seller, or Shopee.

:::sources
- [Singapore Customs: GST on Low-Value Goods](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/gst-on-low-value-goods/) – the S$400 rule, GST at checkout or at import, GST charged twice, scams
- [Singapore Customs: Courier / Postal Procedures](https://www.customs.gov.sg/personal-shipment/buying-from-overseas/courier-postal-procedures/) – parcels held by ICA, asking the courier about tracking
- [Universal Parcel Scraper: SPX Express Singapore](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spx-sg) – names, number shapes, tracker links, wordings and their stages
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – SPX's shared codes, hidden steps kept, Singapore Post's shape, Janio's hand-over
:::
