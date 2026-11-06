---
title: Etsy tracking: where your order is and who delivers it
description: Why an Etsy order still says Not Shipped, which carrier the seller used, what its tracking number looks like, why tracking stops abroad, and when to open a case.
slug: etsy-order-tracking
picture: A parcel tied with string and stamped, a post van, and Pip hurrying along.
published: 2026-10-17
updated: 2026-10-17
---

Etsy doesn't ship anything itself: each shop makes or packs its own orders, posts them with the carrier it chooses, and adds a tracking number when the service has one. Your Purchases page tells you whether the order has left the shop and when it should arrive; the tracking number tells you where it is now. Late or missing? Ask the seller first, then Etsy.

## Why does my Etsy order still say Not Shipped?

Because the seller hasn't made, packed or marked it as sent yet. Etsy builds your estimated delivery date from two parts: "Processing time + Carrier transit time = Estimated Delivery Date". Processing is the time the seller needs to get the order ready before handing it to the carrier, and for an item made to order it includes making it. Etsy's own example: 3–5 days of processing and 2–4 days in transit put delivery 5–9 days away. Your receipt also shows the "ship by" date the seller committed to.

In **Purchases and reviews** (app: **You** → **Purchases**), each order shows one of these statuses:

| Status | What it means |
| --- | --- |
| `Not Shipped` | The seller hasn't completed the order, or hasn't updated it on Etsy |
| `Shipped` | The seller completed the order on Etsy |
| `Pre-transit` | A tracking number is in, but the carrier hasn't scanned the parcel yet |
| `In Transit` | The carrier has it and it's on its way |
| `Delivered` | It has arrived, and you can leave a review |

`Shipped` doesn't always mean a carrier has it. A seller can set a ship date up to 3 days ahead, and Etsy emails you on that date. Only with labels bought on Etsy from Asendia, Evri, FedEx, USPS (letters and flats aside) or UPS does the email wait for the carrier's first scan. If the estimated date has passed and the order still says `Not Shipped`, Etsy says the seller may have forgotten to mark it complete, or hasn't shipped it yet: ask through **Help with order**.

Where the number sits on Etsy and in the shipping email: [find your tracking number](guide:find-tracking-number).

## Which carrier delivers an Etsy order?

Whichever one the seller chose. Etsy sells shipping labels only to sellers in the US, Canada and the UK; sellers elsewhere buy postage from the post or a courier themselves. For parcels going abroad, Etsy's international shipping page names the carriers commonly used from each country:

| Seller in | Labels sold on Etsy | Carriers Etsy names for shipping abroad |
| --- | --- | --- |
| US | [USPS](carrier:usps), [FedEx](carrier:fedex), [UPS](carrier:ups) (within the US only), Global Postal Shipping | [DHL Express](carrier:dhl-express), FedEx, UPS, USPS |
| UK | [Royal Mail](carrier:royal-mail), [Evri](carrier:evri) | DHL, Evri, Royal Mail, UPS |
| Canada | [Canada Post](carrier:canada-post), Chit Chats | Canada Post, Chit Chats, DHL, FedEx, UPS |
| Australia | None | DHL, TNT Australia (FedEx) |
| Ireland | None | [An Post](carrier:an-post) |
| China, Hong Kong | None | [Yanwen](carrier:yanwen), [YunExpress](carrier:yunexpress), [4PX](carrier:4px), and SF Express from China |

If the seller used a carrier Etsy supports directly, a **Track Package** button sits next to the order. Otherwise the number appears only under **View Receipt**, and you track it on the carrier's own site.

Since 1 July 2026, Etsy has temporarily stopped selling USPS and Canada Post labels to Ireland and much of the rest of the EU, "due to a new EU handling fee affecting some carriers", it says. US sellers then use Global Postal Shipping or buy a label elsewhere. Parcels from China follow their own route: see [tracking a parcel from China](guide:tracking-from-china).

## What does an Etsy tracking number look like?

It's the carrier's number, not an Etsy one. Posts use the international postal format: 13 characters that stay the same across borders.

:::anatomy RR 00000000 5 US
- RR | Two letters for the type of item and service
- 00000000 | Serial number, eight digits
- 5 | Check digit, worked out from the serial
- US | Country of the post that issued it: the US
:::

The shapes Peek knows for the carriers above, with made-up digits:

| The number looks like | Usually |
| --- | --- |
| Two letters, nine digits, then `US`, `GB`, `CA`, `IE` or `AU` | USPS, Royal Mail, Canada Post, An Post or Australia Post |
| 22 digits starting with `91` to `95`, such as `9400111111111111111114` | USPS |
| `1Z` + 16 letters and digits | UPS |
| 12 or 15 digits | FedEx |
| 16 digits | Canada Post or Evri |
| 16 characters starting with `H` or `T` | Evri |
| 10 letters and digits, starting with a letter then a digit | Chit Chats |

Not sure which you have? Type it here:

:::checker
:::

Every other format, carrier by carrier: [tracking number formats](guide:tracking-number-formats).

## Why did my Etsy tracking stop or change?

Usually one of these:

- **It crossed a border.** Etsy warns that "some international or cross-border shipments won't show tracking once the order leaves the departing country". It should update once the parcel reaches your country.
- **Your post took over.** With a postal number, the sending post sends the parcel abroad and your post delivers it. The Universal Postal Union allows "only one S10 identifier" per item, so the same number usually works on your post's site too.
- **Two labels, one journey.** With Etsy's Global Postal Shipping, a US seller posts the parcel with a USPS label to Asendia, which adds the international label. Etsy combines both parts into one history in Purchases and reviews, and its emails say "Tracked on Etsy".
- **The seller added or fixed the number late.** Sellers can edit a number for 3 days after completing the order; after that, they message you the right one. They can also add a package later, which shows next to the first number.
- **Several orders, one box.** A seller who packs several of your orders together gives them all the same number.

:::journey
- shop | Maker | Makes and packs it: `Not Shipped` until then
- label | Label | `Pre-transit`: a number, no scan yet
- truck | Sending post | First scans in the seller's country
- plane | Flight | Tracking often goes quiet here
- customs | Your customs | Checked, and sometimes charged
- handover | Your post | Delivers it, usually under the same number
- home | You | `Delivered`
:::

How long a quiet spell can last: [why tracking stops updating](guide:tracking-not-updating).

### Why is there no tracking number at all?

Not every service has one. Etsy says it plainly: "Not all shipping methods include tracking information." For US letters and flats, Etsy's own labels add tracking through Pitney Bowes, but a piece too rigid to qualify "may also not show tracking". Etsy requires tracking from US sellers, but any seller can mark an order as having none and give a reason. No number? Message the seller through **Help with order**: they know which service they used and when they posted it.

## Will I pay customs on an Etsy order from abroad?

Etsy says buyers are "generally responsible" for duties, taxes and clearance fees, and most are due when the parcel arrives. In some countries Etsy collects the tax at checkout instead:

| Country | What Etsy says |
| --- | --- |
| US | Sellers must ship Delivered Duty Paid. You're "not responsible for unexpected customs fees": ask the seller, then open a case |
| UK | VAT taken at checkout on parcels up to £135; above that, charged at the border |
| Ireland | From outside the EU: under €150, VAT at checkout plus a €3 customs duty per item, at checkout or on delivery; above €150, VAT and duty on delivery unless the seller prepaid them |
| Canada | GST/HST, plus PST, RST or QST where due, included at checkout |
| Australia | GST taken at checkout up to AUD 1,000; above that, at the border |

A parcel that's late or never comes because you refused or didn't pay charges due on delivery isn't covered by Etsy's Purchase Protection. More on fees and delays: [parcels held at customs](guide:customs).

## What to do if your Etsy order is late or missing

Go in this order:

:::steps
- Check the dates | The estimated delivery date is on the order. Etsy recalculates it if the seller ships early.
- Track it with the carrier | Use Track Package, or the number under View Receipt on the carrier's site.
- Ask the seller | Purchases and reviews, then Help with order. Sellers can check the status, change the address or refund.
- Open a case | 48 hours after your request, once the estimated date has passed. For an order that never came, 7 days after the latest estimated date.
- Mind the deadline | You have until 30 days after the estimated delivery date.
:::

Under Etsy's Purchase Protection, qualifying orders get a full refund when the item doesn't arrive, arrives damaged, isn't as described, or arrives 7 or more days after the latest estimated delivery date given at checkout. Your **Help with order** request shows the date and time you can open a case, and how long you have. Etsy can only process a refund within 180 days of the purchase.

Some orders don't qualify: lateness caused by a carrier strike, severe weather, war or civil unrest, charges you refused at the door, and orders you already disputed with your bank. Checked out as a guest? Link the order to an Etsy account first. If your payment went through PayPal outside Etsy Payments, open a dispute with PayPal within 180 days of the purchase instead.

### It says delivered, but it isn't here

Etsy suggests these checks first: ask the people you live or work with, wait a day if it isn't urgent, ask your local post office or delivery depot, and ask the neighbours. Still nothing? Send the seller a **Help with order** request: sellers usually open a search or claim with the carrier on your behalf. Then open a case if it doesn't turn up. More in [delivered but not received](guide:delivered-not-received) and [lost parcels](guide:lost-parcel).

## Can I track an Etsy order outside Etsy?

Yes, with the carrier's number. Paste it into [Peek](/): it recognises the posts' and couriers' numbers above. For a postal item sent abroad, when the sending post reports the destination country but names no partner, Peek can propose one lookup at your country's post (USPS, Royal Mail, Canada Post or Australia Post) with the same number. It keeps the sending post's history, and follows your post once its tracking shows fresh progress. Etsy's own steps stay on Etsy: Peek can't see a `Not Shipped` order or open a case for you.

:::sources
- [Etsy: What's the Status of My Order?](https://help.etsy.com/hc/en-us/articles/115015521948-What-s-the-Status-of-My-Order) – statuses, Track Package, View Receipt, quiet abroad, no tracking, delivered checks
- [Etsy: What is an Estimated Delivery Date?](https://help.etsy.com/hc/en-us/articles/360020601674-What-is-an-Estimated-Delivery-Date) – processing plus transit, example, 7 days for non-delivery
- [Etsy: How to Set Processing Times and "Ship By" Dates](https://help.etsy.com/hc/en-us/articles/115015588087-How-to-Set-Processing-Times-Processing-Profiles-and-Ship-By-Dates) – processing time, made to order, ship by date on the receipt
- [Etsy: How to Add Tracking and Complete an Order](https://help.etsy.com/hc/en-us/articles/115015774228-How-to-Add-Tracking-and-Complete-an-Order) – ship date up to 3 days ahead, first-scan emails, US tracking rule, 3 days to edit, extra packages, combined orders
- [Etsy: How to Purchase Shipping Labels on Etsy](https://help.etsy.com/hc/en-us/articles/360001967188-How-to-Purchase-Shipping-Labels-on-Etsy) – label carriers, sellers in Canada, the UK and the US, paused EU destinations
- [Etsy: FedEx Shipping Labels on Etsy](https://help.etsy.com/hc/en-us/articles/360000337447-FedEx-Shipping-Labels-on-Etsy) – US sellers
- [Etsy: UPS Shipping Labels on Etsy](https://help.etsy.com/hc/en-us/articles/18309523715735-UPS-Shipping-Labels-on-Etsy) – US sellers, domestic only
- [Etsy: Royal Mail Postage Labels on Etsy](https://help.etsy.com/hc/en-gb/articles/30907634892311-Royal-Mail-Postage-Labels-on-Etsy) – UK sellers
- [Etsy: Evri Postage Labels on Etsy](https://help.etsy.com/hc/en-gb/articles/360002035507-Evri-Postage-Labels-on-Etsy) – UK sellers, 16-digit numbers
- [Etsy: Chit Chats Shipping Labels on Etsy](https://help.etsy.com/hc/en-us/articles/44210045773719-Chit-Chats-Shipping-Labels-on-Etsy) – Canadian sellers
- [Etsy: Global Postal Shipping Labels on Etsy](https://help.etsy.com/hc/en-us/articles/360037382894-Global-Postal-Shipping-Labels-on-Etsy) – Asendia, two labels, Tracked on Etsy
- [Etsy: How to Ship First-Class Flats and Letters](https://help.etsy.com/hc/en-us/articles/360039466434-How-to-Ship-First-Class-Flats-and-Letters-with-Etsy-Shipping-Labels) – Pitney Bowes tracking, rigid pieces
- [Etsy: Information for Managing International Shipments](https://help.etsy.com/hc/en-us/articles/360001987487-Information-for-Managing-International-Shipments) – carriers commonly used from each country
- [Etsy: Will I Have to Pay for Tax, Customs, or Tariffs on My Order?](https://help.etsy.com/hc/en-us/articles/115015691007-Will-I-Have-to-Pay-for-Tax-Customs-or-Tariffs-on-My-Order) – US, UK, EU, Canada and Australia rules
- [Etsy: Etsy's Purchase Protection Program](https://help.etsy.com/hc/en-us/articles/7471925990807-Etsy-s-Purchase-Protection-Program) – what's covered, customs charges, strikes and weather, guests
- [Etsy: How to Get Help with An Order](https://help.etsy.com/hc/en-us/articles/4402660818583-How-to-Get-Help-with-An-Order) – Help with order, what sellers can do
- [Etsy: How to Open a Case](https://help.etsy.com/hc/en-us/articles/5745586898199-How-to-Open-a-Case) – 48 hours, 30 days, 180 days, PayPal, chargebacks
- [Etsy: What if My Buyer's Package Gets Lost?](https://help.etsy.com/hc/en-us/articles/360050349953-What-if-My-Buyer-s-Package-Gets-Lost) – seller claims on the buyer's behalf
- [UPU: S10 standard](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – the 13-character format, one number per item
- [Universal Parcel Scraper: carrier data](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – number shapes, what Peek checks
:::
