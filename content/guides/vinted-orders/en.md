---
title: Vinted parcel tracking: where to look and why it stalls
description: Where to track a Vinted or Vinted Go parcel, which carrier delivers it in the UK, Ireland, the US and Australia, and why tracking stops or the number changes.
slug: vinted-tracking
picture: Two phones, a seller's and a buyer's, with a parcel travelling between them in a van, and a cheerful Pip.
published: 2026-10-16
updated: 2026-10-16
---

On Vinted, the buyer picks the carrier at checkout and pays for the label; the seller gets it as a prepaid label or a QR code and has 5 business days to drop the parcel off. Buyer and seller both follow it in the order's conversation, under **Track parcel**, where you can also copy the carrier's tracking number. Tracking can take up to 48 hours to appear after drop-off, and the seller is paid only once the buyer confirms all is well, or 2 days after delivery.

## How does shipping work on Vinted?

Every **Buy now** order with integrated shipping comes with a label Vinted generates for the buyer's chosen carrier. The seller finds it in the conversation and by email, with instructions: print it, or show a code at the drop-off point. Vinted says this label "must be used": with a different label or shipping option, the order is cancelled automatically. After the sale, the carrier, label type and parcel size can't change, except by cancelling and ordering again.

:::journey
- shop | Buyer | Pays, picks a carrier and home or a pick-up point
- label | Seller | Gets a prepaid label or QR code, then has 5 business days
- handover | Drop-off point | Parcel shop, locker or post office. Tracking within 48 hours
- truck | Carrier | 2 to 5 business days within a country, as a rule
- locker | Pick-up point | Your pick-up code comes by text and email. Collect in time
- home | Buyer | `Delivered` starts your 2 days to check it
:::

Items bought with Item Verification or Electronics Verification go to Vinted's verification hub first, so delivery can take up to 10 business days.

## How long does a Vinted seller have to send it?

Five business days from the purchase. If Vinted sees no tracking update and no confirmation that it was sent by then, it cancels the order the next business day and refunds the buyer.

- **Need more time?** The seller can ask for 3 or 5 more business days: ⓘ in the conversation → **Extend shipping deadline**. The buyer can accept or decline.
- **Sent, but no tracking yet?** Press "I confirm I've sent the order", so the order isn't cancelled while the first scan is on its way.
- **Label not working at the drop-off point?** Try another drop-off point. If it still fails, Vinted's answer is to cancel and let the buyer order again, with a new label.

## Which carriers deliver Vinted parcels?

The one the buyer chose. Each country has its own list, and sellers can switch some of them off in **Settings** → **Postage**:

| Country | Carriers on Vinted | Orders to and from |
| --- | --- | --- |
| UK | [Evri](carrier:evri), [InPost](carrier:inpost), [DPD](carrier:dpd-uk), [Royal Mail](carrier:royal-mail), Relay, AnyVan, [DHL Express](carrier:dhl-express) | US, Australia |
| Ireland | [An Post](carrier:an-post), [DPD](carrier:dpd-ie), Drop2Shop, DHL Express | France |
| US | [USPS](carrier:usps), [FedEx](carrier:fedex), [SpeedX](carrier:speedx) | UK |
| Australia | [Australia Post](carrier:australia-post) | UK |

In the UK, DPD goes from parcel shop to parcel shop and AnyVan collects large items from the seller's door; the others deliver to your home or a pick-up point. Vinted closed its Canadian site on 12 February 2024, and no other Vinted site ships to Canada.

Vinted also runs its own carrier, [Vinted Go](carrier:vinted-go), with lockers and parcel shops in France, Belgium, the Netherlands, Spain, Portugal and Finland. In the US, SpeedX parcels start at a Vinted Go Shop.

## What does a Vinted tracking number look like?

It's the carrier's own number, so it works on the carrier's site too. The shapes Peek knows:

| Carrier | The number looks like |
| --- | --- |
| Evri | `H` or `T`, 5 letters or digits, then 10 digits |
| InPost | `JD` or `JJD` + 16 digits, or `8YDR` + 9 digits |
| DPD UK | 14 digits, often starting `1550` |
| Royal Mail | 2 letters, 9 digits, `GB` |
| Relay | `RELR` + 8 letters or digits |
| DHL Express | 10 digits |
| An Post | 2 letters, 9 digits, `IE` |
| USPS | 22 digits starting `91` to `95` |
| FedEx | 12 digits, or longer numbers such as `96` + 20 digits |
| SpeedX | `SPX`, 3 letters, then 12 or 18 digits |
| Australia Post | 21 or 23 characters with `000` before the last 6 digits |

Two codes are not tracking numbers: the QR code the seller shows at the drop-off point, and the pick-up code the buyer gets when the parcel is ready. A Vinted Go number has 16 digits, made up here:

:::anatomy 17 1234567890123 8
- 17 | Always `17` or `18`
- 1234567890123 | Thirteen more digits
- 8 | A check digit, worked out from the other fifteen
:::

Not sure whose number you have? Type it here:

:::checker
:::

More shapes in [tracking number formats](guide:tracking-number-formats), and where the number hides in [how to find your tracking number](guide:find-tracking-number).

## Why did my Vinted tracking number change?

Usually because a code became a label, or the parcel took a longer route:

- **A code, then a label.** With InPost, DPD, Royal Mail and Relay, the seller can get a QR code instead of a label. The staff at the drop-off point scan it and print the label. Track the carrier's number, not the code.
- **An order between countries.** Vinted warns that a label can show a different address or country "for shipping and forwarding reasons". InPost parcels from abroad may come to your home instead of your pick-up point.
- **A verification hub.** With Item or Electronics Verification, the parcel goes to Vinted's hub, then on to you.
- **A new order.** If the label didn't work and the order was cancelled and placed again, there's a new label with a new number.
- **A new pick-up point.** DHL Express tries your door twice, then leaves the parcel at a nearby pick-up point. Carriers can also send it to another point nearby: check the tracking link before you go.

## Why is my Vinted tracking not updating?

Each quiet spell has its own reason:

- **Within 48 hours of drop-off.** Normal: Vinted says it "can take up to 48 hours" for tracking to appear.
- **No scan after 5 business days.** The seller may not have sent it. Unless they confirmed sending or you agreed to an extension, Vinted cancels the order and refunds you.
- **Stuck on the way.** If the parcel is late, Vinted tells you and shows a new delivery date in the conversation. If tracking is stuck both on Vinted and on the carrier's site, Vinted asks you to wait up to 25 business days from when the order was marked as shipped.
- **Collected, but not `Delivered`.** Vinted's system doesn't always update at once: press **Track parcel**, then **Mark as received**.
- **Custom shipping.** A parcel sent without Vinted's label can't be tracked on Vinted. The seller should send you the number.

More on gaps: [why tracking stops updating](guide:tracking-not-updating).

## How long will a pick-up point keep my Vinted parcel?

It depends on the carrier: its message with your pick-up code gives the deadline. Vinted warns that "some carriers will only store parcels for 2 calendar days" before sending them back; DPD UK gives 7 calendar days.

- **No pick-up code?** Check your texts and spam, and wait 24 hours. Staff may hand the parcel over against your ID and the tracking number.
- **Not collected in time?** It goes back to the seller. Vinted cancels the order and refunds you once the return is confirmed.
- **Sellers, collect returns too.** If a returned parcel stays uncollected past the deadline and any extra holding period, it becomes the property of Vinted Go UAB.

More in [what happens to an uncollected parcel](guide:uncollected-parcel).

## What if my Vinted parcel is lost, or says delivered but isn't?

Go in this order:

:::steps
- Check the carrier's page | Use the link in the conversation, and look for a move to another pick-up point.
- Wait for Vinted's date | Up to 25 business days from shipping, unless the status turns to `Parcel seems to be lost`.
- Press "I have an issue" | Within 2 days of Vinted saying it should have arrived, or of `Delivered`.
- Let Vinted investigate | It checks with the carrier, which can take several weeks.
:::

While the order is suspended, your payment stays put; you get it back in full if the loss is confirmed, shipping and Vinted's fee included. If a locker opens empty, contact Vinted from the order too. Sellers are compensated for a lost or damaged parcel sent with Vinted's label and properly packed: up to £500 in the UK. Elsewhere each carrier sets its own, lower cap: An Post pays up to €50 for a parcel lost in Ireland. More in [lost parcels](guide:lost-parcel) and [delivered but not received](guide:delivered-not-received).

## When does the seller get paid on Vinted?

When the order is completed: the buyer presses "Everything is OK", or 2 days pass after delivery with no issue reported. The money then reaches the seller's Vinted balance within 2 days, moving from "Pending balance" to "Available balance", and can go to a bank account from there. Orders from Pro sellers complete after 14 days.

> Buyers: don't press "Everything is OK" before opening the parcel. It releases the payment, and Vinted can't refund you afterwards.

Vinted's refund policy covers purchases made with **Buy now** from sellers who aren't Pro: an order never shipped, lost, damaged or "significantly not as described". Paying outside Vinted leaves you without it.

## Can I track a Vinted parcel outside the app?

Yes: copy the number from **Track parcel** and paste it into [Peek](/). Vinted Go numbers and vintedgo.com tracking links are tracked automatically. Peek also works out most of the other carriers above from their numbers, though a shared shape such as 14 digits may need you to press "Change" and pick the carrier Vinted names. It doesn't know Drop2Shop or AnyVan: follow those in Vinted. It can't report a problem for you either; that stays in the conversation.

:::sources
- [Vinted UK: Shipping on Vinted](https://www.vinted.co.uk/help/234) – UK carriers, label or code, cancelled with another label, US and Australia
- [Vinted UK: Delivery process](https://www.vinted.co.uk/help/444) – buyer picks the option, 5 working days, 48 hours
- [Vinted UK: Shipping an item](https://www.vinted.co.uk/help/482) – label in the conversation and by email, nothing changes after the sale
- [Vinted UK: Delivery time](https://www.vinted.co.uk/help/101) – 2 to 5 business days, verification up to 10, extension of 3 or 5 days
- [Vinted UK: I can't send the item on time](https://www.vinted.co.uk/help/740) – cancelled the next business day, "I confirm I've sent the order"
- [Vinted UK: Shipping label not received or not working](https://www.vinted.co.uk/help/154) – another drop-off point, cancel and order again, forwarding addresses
- [Vinted UK: Tracking a parcel](https://www.vinted.co.uk/help/100) – Track parcel, 48 hours, Mark as received, custom shipping
- [Vinted UK: Parcel didn't arrive](https://www.vinted.co.uk/help/657) – delay notice, 25 business days, compensation
- [Vinted UK: Collecting a parcel](https://www.vinted.co.uk/help/1601) – pick-up code, deadlines per carrier, refund, 2-day confirmation
- [Vinted UK: Collecting your order](https://www.vinted.co.uk/help/738) – 2 calendar days, no code, other pick-up point, empty locker
- [Vinted UK: Parcel was sent back to sender](https://www.vinted.co.uk/help/728) – refund once the return is confirmed
- [Vinted UK: Vinted Refund Policy](https://www.vinted.co.uk/help/465) – 2 days, lost, several weeks, full refund, outside Vinted, no refund once completed
- [Vinted UK: Compensation for sellers when shipping with integrated labels](https://www.vinted.co.uk/help/1018) – £500, Vinted Go UAB
- [Vinted UK: Getting paid for a completed sale](https://www.vinted.co.uk/help/235) – completion, within 2 days, Pro sellers
- [Vinted UK: Buying basics](https://www.vinted.co.uk/help/25) – "Everything is OK" releases the payment
- [Vinted UK: InPost shipping](https://www.vinted.co.uk/help/953) – QR code, US and Australia, home delivery from abroad
- [Vinted UK: DPD Shipping](https://www.vinted.co.uk/help/1101) – shop to shop, staff print the label, 7 calendar days
- [Vinted UK: Royal Mail shipping](https://www.vinted.co.uk/help/528) – QR code, home or pick-up point
- [Vinted UK: Relay shipping](https://www.vinted.co.uk/help/1418) – QR code, home or shop
- [Vinted UK: Evri shipping](https://www.vinted.co.uk/help/706) – home or parcelshop
- [Vinted UK: DHL Express shipping](https://www.vinted.co.uk/help/1606) – two delivery attempts, then a pick-up point
- [Vinted UK: AnyVan shipping](https://www.vinted.co.uk/help/1523) – collection from the seller's address
- [Vinted Ireland: Shipping on Vinted](https://www.vinted.ie/help/234) – Irish carriers, France
- [Vinted Ireland: Compensation for sellers when shipping with integrated labels](https://www.vinted.ie/help/1018) – caps set by each carrier, An Post €50
- [Vinted Ireland: AnPost shipping](https://www.vinted.ie/help/1401) – PIN code by text
- [Vinted US: Shipping on Vinted](https://www.vinted.com/help/234) – US carriers, UK
- [Vinted US: SpeedX shipping](https://www.vinted.com/help/1549) – SpeedX from a Vinted Go Shop
- [Vinted Australia: Shipping on Vinted](https://www.vinted.com.au/help/234) – Australia Post, UK
- [Vinted Australia: Australia Post shipping](https://www.vinted.com.au/help/1644) – post office or locker, home or pick-up point
- [Vinted Canada: What happened with the Vinted Canada platform?](https://www.vinted.ca/) – closed on 12 February 2024
- [Vinted Go: Home](https://vintedgo.com/en?country=fr&region=europe) – Vinted Go's countries
- [Universal Parcel Scraper: carrier data](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – number shapes
:::
