---
title: SpeedX tracking: statuses, missed deliveries and contact
description: Track a SpeedX package in the US: what an SPX tracking number looks like, what SpeedX's statuses mean, attempted deliveries, photos and how to reach support.
slug: speedx-tracking
published: 2026-10-15
updated: 2026-10-27
---

SpeedX (Speed Xpress Inc., New Hyde Park, New York) is a last-mile delivery company that brings online orders to the door in the United States. Track a parcel on [tracking.speedx.io](https://tracking.speedx.io/) or paste its number in the box above. The number comes from the shop you ordered from: SpeedX makes the label for the shop, so the shop has it before SpeedX has the parcel.

## SpeedX tracking number: format and where to find it

SpeedX says all its numbers start with `SPX`. In practice that's SPX, three letters, then 12 or 18 digits. The shapes, with invented characters:

| Shape (invented) | What it is |
| --- | --- |
| `SPXABC123456789012` | SPX, 3 letters and 12 digits (18 characters) |
| `SPXABC123456789012345678` | SPX, 3 letters and 18 digits (24 characters) |

Peek ignores lowercase and spaces.

**Where it is.** Look in the shop's shipping email or on your order page. If you can't find it, SpeedX says to ask the retailer; [finding a tracking number](guide:find-tracking-number) lists other places to look. SpeedX can also send you SMS updates when the parcel is out for delivery, which you sign up for on its website.

**Numbers that look alike.** `SPX` followed only by digits doesn't match either shape. Neither does `SPXPH` and digits: that's SPX Express in the Philippines, a different company. If an `SPX…` number has a digit too many or too few, compare it with the shop's email. SpeedX's numbers never overlap with those of [UniUni](carrier:uniuni), [GOFO](carrier:gofo) or [USPS](carrier:usps). If a number still isn't found, SpeedX suggests checking with the merchant: your parcel may be with another last-mile company.

## What SpeedX tracking statuses mean

SpeedX's tracking page is in English only. These are the lines a parcel usually meets, in order.

| SpeedX's status | What it means for you |
| --- | --- |
| `Shipping Label Created` | The shop made a label. SpeedX doesn't have the parcel yet. |
| `Processing at Origin Facility` | SpeedX has it and is sorting it at the first facility. |
| `In transit to destination facility` | On its way between SpeedX facilities. |
| `Processing at Transit Facility` | Sorted at a facility along the way. |
| `Arrival at Destination Facility` | It has reached the facility that serves your area. |
| `Processing at Designated Facility` | Sorted there for a driver's route. |
| `Out for Delivery` | A driver has it and it's on the way to you today. |
| `Attempted Delivery: No access` | The driver couldn't get to your door: a gate, an apartment building, construction or bad weather. |
| `Delivered` | Delivered. The page adds where it was left, such as the mailroom, and a photo. |
| `Parcel Void` | SpeedX flagged a problem with the parcel. Ask the shop. |
| `Exception- Parcel not found at the sort facility` | The parcel couldn't be found at the sort facility. Ask the shop. |
| `Parcel Disposal` | SpeedX reported a problem and doesn't explain it. Ask the shop what happens next. |

As long as tracking shows only `Shipping Label Created`, SpeedX hasn't received the parcel: the shop or its warehouse still has it.

An attempt can carry other reasons too, such as `Attempted Delivery:Building Closed/Weekend/Holiday` for a business that was shut. Under an attempt, SpeedX adds a note saying what happens next: `The package is undeliverable and schedule to redelivery` means it goes out again, `The package is undeliverable and return to facility` that it goes back to the facility.

When the latest event is a problem (`Parcel Void`, `Parcel Disposal`, a parcel not found), the page's headline turns to `Issue Reported`, with a category line such as `Last Mile Intercept` or `Last Mile Undelivered` above the wording. SpeedX doesn't explain these publicly: ask the shop.

## SpeedX delivery, attempted delivery and pickup

**Weekends.** SpeedX delivers everywhere it serves on Saturdays from 8 am to 8 pm local time, and on Sundays in some areas, with the same hours. It offers no delivery time slots and no message when the driver is nearby.

**Signature and photo.** Most SpeedX parcels need no signature; age-restricted items such as alcohol may need an adult aged 21 or over. If nobody is home, the driver leaves the parcel and takes a photo. To see it, track the parcel on speedx.io and enter your delivery ZIP code.

**Instructions and address.** SpeedX takes delivery instructions (gate codes, where to leave the parcel, business hours, a phone number) through Miles, its chatbot, with your tracking number. The driver gets them too. You can't speak to the driver directly. SpeedX can change a street address or apartment number if it hears in time; for a different city, state or ZIP code, go to the merchant.

**Missed delivery.** If an attempt was recorded and you think it shouldn't have been, check the address on the shop's website, then send corrections or instructions through the chat. SpeedX says the parcel should then be redelivered within 1 to 2 business days. SpeedX doesn't publish how many attempts it makes or how long it keeps a parcel.

**No pickup.** SpeedX has no pickup points or lockers, and you can't collect a parcel from its facilities: it has to deliver it.

## SpeedX package not moving, not delivered or lost

**Not moving.** SpeedX asks you to contact the retailer if the status hasn't changed for three business days. More on [tracking that doesn't update](guide:tracking-not-updating).

**Delivered but not there.** Check the delivery photo, then the front and back porch, the mailroom or mailbox, the garage, the side of the house and sheltered spots, and ask others in your household or building. Tell SpeedX through Miles, then contact the merchant for next steps. See also [delivered but not received](guide:delivered-not-received).

**Lost, damaged or wrong address.** SpeedX doesn't accept claims from recipients. Contact the merchant for a claim, refund or replacement, with your order number, tracking number and any messages; for damage, the merchant may ask for photos. SpeedX publishes no deadline for this.

**Returns.** Once delivered, a return goes back through the shop you bought from; SpeedX doesn't collect from your address. If it isn't delivered yet, you can ask Miles to stop the delivery and SpeedX can return the parcel to the merchant.

## SpeedX customer service number and chat

SpeedX's channels for recipients in the US, as listed on its [support page](https://speedx.io/customer-service-and-support/) on October 15, 2026:

- **Chat:** Miles, SpeedX's chatbot, 8 am to 8 pm ET every day. Open tracking, enter your number and the chat appears in the bottom corner of the screen.
- **Phone:** 1-949-667-6769, 9 am to 9 pm EST Monday to Friday and 11 am to 8 pm EST on weekends. The same page also says SpeedX has no phone number and points to Miles, so expect to be sent to the chat.
- **Email:** speedxops@speedx.io.

## Tracking SpeedX packages with Peek

[Peek](/) names SpeedX at once from an SPX number of either shape, or from a tracking.speedx.io link with the number in it.

It reads SpeedX's own tracking page and shows the status and each scan with its town on a map, at the time SpeedX recorded it, plus the delivery time and the destination country. It leaves out your address and the delivery photo, so look at the photo on SpeedX's page. Peek shows no expected delivery date for SpeedX. `Delivered` appears in Peek's own words; other lines show as SpeedX writes them, `Out for Delivery` included. There's no handoff to another carrier to follow.

Peek checks every 10 minutes from 8 am to 10 pm Swiss time (about 2 am to 4 pm Eastern, 11 pm to 1 pm Pacific), every 2 minutes in those hours once the parcel is out for delivery, and hourly the rest of the time, and can notify you when the status changes. If SpeedX's page doesn't answer, other tracking sources rarely hold SpeedX parcels, so there may be nothing new until it does.

## Questions about SpeedX tracking

### Is SpeedX tracking legit?

Yes. SpeedX is the brand of Speed Xpress Inc., a last-mile delivery company founded in 2022 in New York that delivers for online shops across the US. Reviewers mention parcels from Temu, among others.

### Can I pick up my SpeedX package?

No. SpeedX doesn't allow pickups from its facilities and has no pickup points, so it has to deliver the parcel.

### How long does SpeedX take to deliver?

SpeedX publishes no delivery time for recipients. It sells next-day and second-day delivery to shippers, and any estimated date comes from the shop, which SpeedX says may not be the actual date.

### Why is my SpeedX tracking number not working?

SpeedX's tracking page says the sender may not have sent it the parcel data yet, or the number is wrong. SpeedX suggests checking the number with the merchant, as the parcel may be with another delivery company.

:::sources
- [SpeedX: Customer Service and Support](https://speedx.io/customer-service-and-support/) – SPX prefix, glossary, SMS, weekends, signature, photos, instructions, address changes, no pickup, delays, claims, returns, phone, email, chatbot hours
- [SpeedX: Tracking](https://tracking.speedx.io/) – status wordings, notes, Issue Reported, categories, unknown-number message, English only
- [SpeedX: Tech-Enabled Last Mile Delivery](https://speedx.io/) – last-mile provider, labels made by SpeedX, US ZIP code coverage, next-day and 2nd-day services, proof of delivery photos
- [SpeedX: About Us](https://speedx.io/about-us/) – founded 2022 in New York, US cities
- [SpeedX: Privacy Policy](https://speedx.io/privacy-policy/) – Speed Xpress Inc., New Hyde Park address
- [Trustpilot: SpeedX Reviews](https://www.trustpilot.com/review/speedx.io) – reviewers mentioning Temu parcels
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads from SpeedX and which numbers it recognises
:::
