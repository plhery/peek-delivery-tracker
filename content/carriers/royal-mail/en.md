---
title: Royal Mail tracking: numbers, statuses and redelivery
description: Track a Royal Mail parcel: number shapes, what each status means, missed deliveries and the red card, customs bills, compensation claims and who to contact.
slug: royal-mail-tracking
published: 2026-10-13
updated: 2026-10-27
---

Royal Mail is the UK's national post, and its Track your item page also tracks [Parcelforce](carrier:parcelforce) parcels. Type the number there or paste it in the box above. The number comes from the sender: look in the shop's order confirmation, shipping email or invoice.

## Royal Mail tracking number: format and where to find it

Royal Mail numbers come in three shapes. If yours isn't in the shop's emails, ask the shop; more in [how to find a tracking number](guide:find-tracking-number).

| Shape (invented) | What it is |
| --- | --- |
| `AB000000005GB` | The usual tracked number: 2 letters, 9 digits, `GB`. The last digit is a check digit. |
| `0A1234567000000000ABC` | A 21-character reference from a 2D barcode, on domestic labels. |
| `1234567890ABCDEF` | A 16-character reference of digits and the letters A to F. |

Lookalikes:

- `E` and any letter (`EA`, `EB`…), `CP` or `GI`, then 9 digits and `GB`: that's [Parcelforce](carrier:parcelforce), tracked on the same page.
- The same shape ending in another country's code (`RR000000005FR`) is the sending post's number. The item keeps it all the way to your door: the UPU allows one such number per item.
- Sixteen digits with no letter aren't a Royal Mail shape. Peek looks for them among other carriers, such as [DHL eCommerce](carrier:dhl-ecommerce) and [TNT](carrier:tnt).

## What Royal Mail tracking statuses mean

Royal Mail writes its tracking in English. These are the lines you'll usually meet, roughly in the order a parcel meets them:

| Royal Mail says | What it means for you |
| --- | --- |
| `Sender preparing item` | The sender has announced it. Royal Mail doesn't have the item yet. |
| `We're expecting it` | Announced, but not received by Royal Mail yet. |
| `Item received` | Royal Mail has it. The same words return at mail centres and hubs, and on arrival in the destination country. |
| `In transit` | Moving through the network. |
| `Customs charges raised` | VAT, duty or a fee must be paid before delivery (see below). |
| `Despatched to delivery office` | Heading to the office that delivers to you. |
| `Ready for delivery` | At the delivery office that serves you, or already on the day's round. |
| `Out for delivery` | With your postie today. |
| `Delivery attempted` | Nobody could take it. |
| `Due for Redelivery the Next Working Day` | Royal Mail will bring it again; the new round shows as its own out-for-delivery line. |
| `Available for Redelivery or Collection` | It's waiting at a Customer Service Point: collect it or book a redelivery. |
| `Delivered` | Delivered. A line may name who signed, and `Delivered to Neighbour/Reception by` names who took it in. |
| `Returned to sender` | On its way back to the sender. |

Royal Mail's summary can also read `Pending`; Peek shows it as "Waiting for an update" until a scan moves the item on.

## Missed delivery: red card and redelivery

Royal Mail delivers Monday to Saturday, usually by 4.30pm, and on Sundays in some areas. A parcel due at a set time can come later than 4.30pm, and nothing comes on bank holidays.

If you don't answer the door, Royal Mail leaves a parcel in your safe place or with a neighbour. Set a safe place, or ask not to have parcels left, in the Royal Mail app or through Customer Services. To stop deliveries to neighbours, fill in Royal Mail's form: it sends you a sticker to put near your letterbox.

Otherwise you get a red card with the address and opening hours of the place to [collect it from](guide:missed-delivery): a Customer Service Point, usually in the delivery office. Check its opening hours on Royal Mail's website before you go, or book a redelivery to your door there.

When VAT, duty or other charges are due on an item from abroad, Royal Mail sends you a bill saying exactly what to pay, and normally holds the item for about 3 weeks; unpaid by then, it goes back to the sender. Gifts worth £39 or less pay no VAT, and on goods you bought for £135 or less the seller has usually charged VAT already. See [parcels held at customs](guide:customs).

## Royal Mail parcel not moving, late or lost

**No tracking details yet.** Royal Mail may not know a number before the item's first scan, and `Sender preparing item` means it doesn't have the item yet. If nothing moves, ask the sender whether they've handed it over. See [why tracking stops updating](guide:tracking-not-updating).

**Late or lost.** If you bought it, contact the seller first: Citizens Advice says you'll usually get a better result, and you can ask for a refund or for the item to be sent again. Royal Mail compensation can be claimed by the sender or the recipient of an item posted in the UK, but only the sender for Tracked 24 and Tracked 48. You need proof of posting, and the claim has to wait:

- **Lost in the UK:** 11 working days after posting for 1st Class, 14 for 2nd Class, 5 working days after the due date for Special Delivery; within 80 days of posting.
- **Lost abroad:** 20 working days after the due date in Europe, 25 elsewhere; within 6 months of posting.

Delays aren't compensated on Tracked 24, Tracked 48 or items sent abroad. Claim on Royal Mail's website or with a paper form from a Post Office. More in [lost parcels](guide:lost-parcel).

**Delivered but not received.** The delivered line may name who signed, or read `Delivered to Neighbour/Reception by`: ask them and your household, check your safe place, then contact the sender. A stolen parcel can be reported to the police as well as claimed for. See [delivered but not received](guide:delivered-not-received).

## Royal Mail contact number and customer service

Citizens Advice gives these details for Royal Mail:

- **Customer Services:** 03457 740 740, Monday to Friday 8am to 6.30pm, Saturday 8am to 3pm and Sunday 9am to 2pm. Textphone 03456 000 606; Relay UK 18001 then 0345 266 0075.
- **Online:** claims, complaints and redelivery on Royal Mail's website; safe place and delivery needs in the Royal Mail app.
- **If a complaint stalls:** the Postal Review Panel looks at it again. After that, or if Royal Mail takes longer than 90 days or skips its complaints procedure, the independent Postal Redress Service (POSTRS) can decide: 020 7520 3766, postrs@cedr.com.

> Not sure a text or email about a parcel is genuine? Don't click its links or give bank details. Forward scam texts to 7726, free, and scam emails to report@phishing.gov.uk. More in [delivery scam texts](guide:delivery-scam-texts).

## Tracking Royal Mail parcels with Peek

[Peek](/) names Royal Mail at once for a valid `AB000000005GB`-style number; if the check digit fails, check the number or press "Change". For 21- and 16-character references it asks tracking services first and Royal Mail's site last, so this can take a little while; if nothing answers, or a rare reference is named [Australia Post](carrier:australia-post), pick Royal Mail with "Change". A Royal Mail tracking link with `trackNumber=` works with any of these numbers; a `#/tracking-results/` link only with a number ending `GB`.

Peek opens Royal Mail's site in a browser on its own server. Royal Mail often refuses automated visits, so a Royal Mail parcel is checked at most once an hour, even on delivery day, and tracking services hold little of its history: it can stay on its last status for hours.

You see the status, each scan on a map with its place (postcodes removed), the expected delivery day, the delivery time and the destination country. UK scans carry exact times. Delivered lines read "Delivered", without who signed, and the recipient, address and photo are dropped. Other statuses keep Royal Mail's wording. You can be notified of every update, important steps only or delivery day only.

A parcel from abroad with a postal number can be followed on Royal Mail once Royal Mail shows progress. Redelivery, claims and paying a customs bill stay on Royal Mail's site.

## Questions about Royal Mail tracking

### How long does Royal Mail tracking take to update?

There's no fixed time: the status changes at the item's next scan. Peek checks a Royal Mail parcel at most once an hour.

### What time does Royal Mail deliver?

Monday to Saturday, usually by 4.30pm, and on Sundays in some areas. A parcel due at a set time can come later, and there are no deliveries on bank holidays.

### What does "Due for Redelivery the Next Working Day" mean?

Royal Mail missed you and will bring the item on its next round, which shows as a new out-for-delivery line. After missed deliveries the item waits at a Customer Service Point, and tracking shows `Available for Redelivery or Collection`.

### Is my Royal Mail tracking number fake?

Not necessarily. A sender can create the number before handing the item over, and Royal Mail may not know it before the first scan. If it still shows nothing after a few days, ask the sender.

:::sources
- [Citizens Advice: Check Royal Mail collection and delivery times](https://www.citizensadvice.org.uk/consumer/post-and-parcels/post-collection-delivery-times/) – delivery days and times, Sundays, bank holidays, Customer Service Points, phone and hours
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – safe place or neighbour, sticker, Royal Mail app, red card, redelivery
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – who can claim, proof of posting, waiting times and deadlines, delays, how to claim, textphone and Relay UK
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – seller first, Postal Review Panel, POSTRS, theft
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – bill from Royal Mail, about 3 weeks, VAT on gifts and goods up to £135
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 7726, report@phishing.gov.uk
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – check digit, country code of the issuing post, one number per item
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – number shapes, status wordings and what Peek reads from Royal Mail
:::
