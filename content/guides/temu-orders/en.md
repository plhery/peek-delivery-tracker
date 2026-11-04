---
title: Temu order tracking: which carrier has your parcel
description: Track a Temu order with the carrier's tracking number, not the PO number: who delivers in the US, UK and Ireland, and why a new number shows nothing.
slug: temu-order-tracking
picture: A stack of small parcels leaving a plane's hold, a van, and Pip counting them.
published: 2026-10-17
updated: 2026-11-04
---

To track a Temu order, you need the carrier's tracking number, not Temu's order number (PO and 20 digits). Paste it into [Peek](/): it works out the carrier among more than 3,500, from USPS to Royal Mail and Evri, then follows the local partner if the first carrier names one. A new number can show nothing for a day or two: the label exists before the carrier has the parcel.

## Is my Temu order number a tracking number?

No. Temu's order number is its own reference, not a tracking number: no carrier can look it up. Peek knows it by its shape, PO followed by 20 digits (`PO-000-00000000000000000`), and holds it back with a note saying where to look instead: in Your orders, tap Track next to the order, and Temu shows a tracking number for each parcel. Not sure which one you have? Type it here:

:::checker
:::

Paste a whole message that holds both numbers, and Peek takes the tracking number and leaves the order number aside. Where other shops show it: [find your tracking number](guide:find-tracking-number).

## Which carrier delivers my Temu parcel?

The tracking number usually tells: the checker above names the carrier its shape points to, or the few it could belong to. Shapes you may meet in the US, UK and Ireland:

| The number looks like | Carrier |
| --- | --- |
| 2 letters, 9 digits, `CN` | [China Post](carrier:china-post): your national post delivers it under the same number |
| 22 digits starting with `92` to `95` | [USPS](carrier:usps) |
| `UUS` and 16 letters or digits | [UniUni](carrier:uniuni) |
| `SPX`, 3 letters, then 12 or 18 digits | [SpeedX](carrier:speedx) |
| `GFUS` and 14 digits | [GOFO](carrier:gofo) |
| 2 letters, 9 digits, `GB` | [Royal Mail](carrier:royal-mail) |
| 16 letters and digits starting with `H` or `T` | [Evri](carrier:evri) |
| 2 letters, 9 digits, `IE` | [An Post](carrier:an-post) |

An order from a UK seller on Temu can come with Evri, which ships for Temu sellers. Who delivers in Canada, Australia and elsewhere: the country table of [tracking a parcel from China](guide:tracking-from-china). Other shapes: [tracking number formats](guide:tracking-number-formats).

Whether you owe anything at the border depends on your country ([customs](guide:customs)). In Great Britain, on goods worth £135 or less, the seller includes VAT in the price you pay and no duty is due, says GOV.UK. In Ireland, since 1 July 2026, online orders from outside the EU worth €150 or less pay €3 duty per item, meaning per kind of product, and since 1 November 2026, Revenue says, a €2 EU handling fee per item is added. If the checkout showed VAT and duty, that's the final price; if not, the carrier collects them first.

## Why does my Temu tracking number show nothing?

The number often exists before the carrier has the parcel. Until its first scan, Peek shows one of these:

- **Label created**: the carrier has the label, not the parcel (USPS writes `Shipping Label Created`, Royal Mail `Sender preparing item`). After a day, Peek says the sender hasn't handed it over yet; tracking starts when they do.
- **Probably** and a carrier's name: the number has that carrier's shape, but the carrier doesn't list it yet. Peek keeps asking and shows the parcel once it does. Still nothing after a week? Check the number with Temu, or choose another carrier.
- **Carrier not found yet**: no carrier knows the number. That's normal for a day or two after ordering, and Peek keeps checking.

A parcel flown from China can stay quiet much longer: up to 11 days before its first scan, then nothing during the flight.

## When will my Temu parcel arrive?

Temu shows the delivery time on the product page and in your order. In Ireland you choose standard or Click & Collect, and Temu's Irish shipping page gives no number of days. Once the carrier has the parcel, Peek shows the date the carrier expects, when it gives one.

## Temu parcel stuck, late or missing?

Tracking that sits still for days, or a number that changes when the local carrier takes over, is normal on this route: [tracking a parcel from China](guide:tracking-from-china) explains both, and what changes for products from a local warehouse. How long a gap is normal, when a parcel counts as lost, and whether to contact the seller or the carrier: [tracking not updating](guide:tracking-not-updating).

Who is responsible for a parcel that never came, and what evidence to keep: [delivered but not received](guide:delivered-not-received) and [lost parcels](guide:lost-parcel). While you wait, watch out for fake delivery messages: [delivery scam texts](guide:delivery-scam-texts).

## Can I track a Temu parcel in Peek?

Yes, with the carrier's tracking number. A shape several carriers share may need you to press "Change" and pick the right carrier.

When the first carrier names the local partner taking over, Peek keeps the first history, follows the partner once its own tracking shows fresh progress, and shows the partner's name and number on the card.

Got several tracking numbers? Add each one: Peek follows each parcel on its own.

Peek shows what the carrier publishes. A claim or a refund stays in your Temu account. A return has its own tracking number: where to find it and when the refund comes, [return tracking](guide:returns-tracking).

:::sources
- [Temu Ireland: Shipping info](https://www.temu.com/ie/shipping-info.html) – Irish options, no number of days
- [Temu: What is the delivery time for an item?](https://www.temu.com/support/c3/what-is-the-delivery-time-for-an-item-how-long-will-it-take-to-arrive-f-49-s-203.html) – delivery time on the product page and in your order
- [Evri: Temu integration](https://www.evri.com/our-services/temu-integration) – Temu sellers import their orders and ship them with Evri
- [GOV.UK: Goods sent from abroad, tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – £135 or less: VAT in the price, no duty
- [Revenue: Removal of the de minimis relief](https://www.revenue.ie/en/customs/individuals/relief-low-value-consignments/index.aspx) – €3 per item
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – €2 fee, checkout price
- [Universal Parcel Scraper: carrier data](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – number shapes, first statuses, what Peek checks
:::
