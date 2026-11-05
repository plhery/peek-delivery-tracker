---
title: J&T Express Germany tracking: JTDE numbers and statuses
description: Track a J&T Express parcel in Germany: what a JTDE number looks like, what each tracking line means, and what to do when your parcel is late, lost or missing.
slug: jt-express-germany-tracking
published: 2026-10-21
updated: 2026-11-05
---

J&T Express Germany (J&T Express Germany GmbH, Frankfurt am Main) is the German network of the J&T Express parcel group: it delivers parcels in Germany. If a shop sent your order with J&T, the tracking number starts with `JTDE`: paste it in the box above, or look it up on [J&T's own tracker at jtexpress.de](https://www.jtexpress.de/tracking). The shop that sent your order has the number, in its shipping email or on your order page.

## J&T Express Germany tracking number: what it looks like

J&T's FAQ says its tracking numbers start with JTDE. Peek knows only one shape for them: `JTDE` followed by 12 digits, 16 characters in all. With invented digits:

| Number (invented) | What it is |
| --- | --- |
| `JTDE000000000000` | `JTDE` and 12 digits: a J&T Express Germany parcel. Peek names J&T Express Germany at once. |
| `JTDE00000000000` | One digit short: not J&T Express Germany's shape, and Peek recognises no carrier. Check for a missing digit. |

**Where to find it.** J&T says the sender gives it to you, and that it's on the shop's order page, in the order confirmation email, in a shipping email or text message, in emails announcing or confirming the delivery, and on the parcel's label. If you can't find it, J&T sends you to the sender or the shopping platform's customer service. See also [how to find a tracking number](guide:find-tracking-number).

J&T's tracker takes up to 10 numbers in one search, separated by spaces or commas.

**Numbers that look alike.** J&T's networks in other countries have their own trackers and numbers: `JTUK` and 12 digits is [J&T Express UK](carrier:j-and-t-uk), `JTAU` and 12 digits [J&T Express Australia](carrier:j-and-t-au), `JTE` and 12 digits [J&T Express Middle East](carrier:j-and-t-middle-east), and `JT` with 13 digits is used by J&T in South-East Asia and in China ([J&T Express China](carrier:j-and-t-cn)). Only `JTDE` belongs to the German network. If J&T's tracker answers `Keine Abfrageergebnisse` (`No tracking records yet` in English), or Peek keeps showing "Waiting for the carrier", compare the number with the shop's email, or ask the shop.

## J&T Express Germany tracking statuses explained

J&T's tracker gives each scan as a time and one sentence, with no place. It opens in German; switch the site to English or Chinese in its language menu and it reloads the scans in that language. Peek asks for the sentences in English, so these are the ones Peek shows, in the order a parcel meets them:

| J&T's status | What it means for you | Peek shows |
| --- | --- | --- |
| `The shipment has been successfully picked up and is being prepared for transportation` | J&T has collected the parcel from the sender. | "Received by carrier" |
| `The shipment arrives at the distribution center and is prepared for transport to the next destination.` | It reached a J&T sorting centre. | "In transit" |
| `The shipment is dispatched from the transit center to the next destination` | It left that centre for the next stop. | "In transit" |
| `Your package has been loaded and is on its way to you` | It's in the delivery van: out for delivery. | "Out for delivery" |
| `Delivery failed because the address could not be found` | The driver couldn't find your address. | "Delivery attempted" |
| `Delivery failed, customer refused to accept` | The recipient refused to accept the parcel. | "Delivery attempted" |
| `Your package has been successfully delivered.` | Delivered. J&T adds how (see below). | "Delivered" |
| `Returns have begun` | After a failed delivery, the parcel is on its way back to the sender. | "Returned to sender" |
| `The shipment has been returned` | The return is complete. | "Returned to sender" |

A sentence not in this table goes by the rules Peek uses for every carrier; one Peek doesn't recognise shows as J&T wrote it and counts as "In transit", unless the parcel is already out for delivery or past that point: then the status stays as it was.

The loading line can appear several times: J&T writes it once for each delivery run. The delivered line goes on with the delivery type in 【】 brackets and ends with a customer service line. Peek drops that line, and keeps the type, without brackets, only when it is one of these six:

- `Left with neighbor`: with a neighbour;
- `Front Door Porch`: at the front door;
- `Front Desk Reception`: at a reception desk;
- `Garden Yard`: in the garden or yard;
- `Mailbox`: in the mailbox;
- `Garage`: in the garage.

So a parcel left with a neighbour reads `Your package has been successfully delivered. The delivery type is Left with neighbor.` in Peek.

**Which neighbour?** On J&T's tracker, that scan carries `Zustelldetails anzeigen` (`Click to view delivery details` on the English site). Click it and enter the postcode of the delivery address to see the neighbour's name and house number; after five wrong postcodes you have to wait before trying again. Peek asks with the number alone and shows only that the parcel was left with a neighbour: no name and no address. It leaves out any name or phone number a J&T line carries.

## J&T Express Germany delivery: neighbours, safe places and photos

In the histories Peek's code holds, J&T's courier loads the parcel for a delivery run and then either delivers it or reports why the delivery failed. Peek's code shows no hand-over to another carrier: J&T's tracker follows the parcel to your door.

**Out for delivery.** `Your package has been loaded and is on its way to you` means your parcel is on a delivery run. In those histories it comes hours before the delivery, the same morning. J&T's tracker gives no delivery window or expected day, so Peek can't show one either.

**Delivery preferences.** The delivery notification email for your parcel carries a link to J&T's [delivery preferences page](https://www.jtexpress.de/delivery-preference). Confirm your postcode, then pick one of the methods J&T offers you: signed for by you, by a neighbour (you give their name and whether they live next door or on your floor) or at a reception desk, or left without a signature at the front door, in the garden, in the mailbox or in the garage. J&T says the driver follows your choice. Pick a place without a signature and the page warns that the risk of loss is yours once the parcel is left there. If the parcel's status no longer allows a choice, the page says so.

**Delivery photo.** J&T's proof-of-delivery privacy notice says the driver photographs the parcel when handing it to you or leaving it at an agreed place, framing only the parcel and the part of the spot needed; anyone caught by accident is blurred automatically. J&T deletes the photo six months after delivery as a rule, may keep it until a complaint raised in that time is closed, and may show it to the sender to resolve a complaint.

**Refused or not found.** After `Delivery failed, customer refused to accept`, the courier loaded the parcel for another run the next morning in the histories Peek's code holds. After `Delivery failed because the address could not be found`, check the address on your order. If anything is missing, such as a flat number or the name on your bell, tell the shop at once: it booked the delivery with the address you gave it. J&T's FAQ also says to contact the sender or the shopping platform straight away: once a parcel has shipped, whether its address can still be changed depends on where it is and on the sender's rules. `Returns have begun` can follow a failed delivery. See [how to change a delivery address](guide:change-delivery-address) and [missed deliveries](guide:missed-delivery).

**Attempts and pickup.** J&T's website gives no number of delivery attempts, no storage period and no delivery hours, and it lists no parcel shops, lockers or depots where you could collect a parcel. Among the J&T Express Germany lines Peek's code knows, none sends a parcel to a shop or locker for collection. Ask the shop, or look at your delivery messages.

## J&T Express parcel late, missing or returned

The shop chose J&T, so for an online order the shop is your first contact. Under German law, when you buy from a business as a consumer, the seller carries the risk of loss or damage in transit unless you hired the carrier yourself and the seller hadn't named that carrier to you beforehand.

**Not moving.** J&T publishes no delivery times for recipients. A parcel showing "Received by carrier" or "In transit" with no new line for four days gets a flag in Peek ("No tracking update for four days") and a "Get help from J&T Express Germany" link. If nothing moves after that, ask the shop, or call J&T's hotline with your number. More on [tracking that isn't updating](guide:tracking-not-updating).

**No tracking yet.** A number J&T's tracker doesn't know yet shows as "Waiting for the carrier" in Peek. Shops often create the number before they hand the parcel over. If it stays that way for several days, check the number with the shop and ask whether the parcel has really been sent.

**Delivered but not received.** Read the delivered line in Peek: if it names a place, such as `Mailbox` or `Garage`, look there; if it says `Left with neighbor`, ask your neighbours, or look the neighbour up on J&T's tracker (see above). J&T's FAQ also asks you to check whether family, a neighbour or building staff took the parcel in. Still missing? Report it to J&T's hotline, and ask the shop for a refund or a new parcel, with your tracking number. See also [delivered but not received](guide:delivered-not-received).

**Lost, damaged or returned.** Ask the shop for a refund or replacement, with your tracking number. J&T's FAQ gives no claim form, deadline or compensation for recipients; German law sets the deadlines. Under § 438 of the Commercial Code (HGB), report visible damage on delivery at the latest (telling the driver is enough), and hidden damage within seven days of delivery, in text form, such as an email to J&T. If you can't agree with J&T over a lost or damaged parcel, you can take it to the Bundesnetzagentur's postal arbitration board (Schlichtungsstelle Post), which charges no fee and hears recipients as well as senders. Parcels from large online shops, though, usually travel under special terms that rule arbitration out; you can then still turn to the Bundesnetzagentur's consumer service for postal matters (Verbraucherservice Post). See [lost parcels](guide:lost-parcel), [damaged parcels](guide:damaged-parcel) and [returned to sender](guide:returned-to-sender).

## J&T Express Germany customer service and contact

J&T Express Germany's channels, as read on [its contact page](https://www.jtexpress.de/contact) on 3 November 2026:

- **Phone:** +49 6931090650, Monday to Friday 8:00 to 20:00, closed on public holidays.
- **Email:** de-jtservice@jtexpress.de.
- **Live chat:** "Start Chat" on the contact and FAQ pages.
- **Delivery photos and personal data:** de-dpo@jtexpress.de (from the proof-of-delivery privacy notice).
- **Office:** J&T Express Germany GmbH, Am Giener 2, 55268 Nieder-Olm. The legal notice gives the registered address, Wiesenhüttenstraße 11, 60329 Frankfurt am Main.

For an online order, start with the shop all the same: it booked the delivery with J&T. For a marketplace order, use the help on the order's page. On a parcel that has stopped moving, Peek's "Get help from J&T Express Germany" link opens J&T's contact page.

## Tracking J&T Express Germany parcels with Peek

[Peek](/) names J&T Express Germany at once from a `JTDE` number ("Detected carrier"), typed with or without spaces, in capitals or not, and asks J&T's own tracker with the number alone: no postcode or account. A www.jtexpress.de/tracking link with `?q=` and the number works too; with another kind of number in it, Peek reads that number as if you had pasted it alone. If what you have isn't a `JTDE` number, paste it all the same: Peek follows more than 3,500 carriers. Picking J&T Express Germany with "Change" for another shape doesn't help: Peek says it "doesn’t issue numbers like this one".

Peek shows the status, each scan at its exact time and the delivery time. J&T gives every scan as an exact moment, so Peek shows it in your device's time zone. Scans come in J&T's English, even if you read them in German on jtexpress.de. J&T gives no place, expected delivery day or weight, so Peek shows no map. On a very long history it keeps the newest 100 scans. "Open the J&T Express Germany website", on the parcel's page, opens your number on J&T's tracker.

Until J&T scans the parcel, Peek shows "Waiting for the carrier" and keeps asking. When J&T's tracker can't be reached or doesn't know the number, Peek asks tracking services and shows their history, in their own words.

Peek checks every 10 minutes, and every 2 minutes once the parcel is out for delivery. After two days without a new scan (12 hours while it shows "Received by carrier"), it checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only". Peek can't contact J&T or the shop for you, change the address or book a new delivery.

## Questions about J&T Express Germany tracking

### Is J&T Express Germany legit?

Yes. J&T Express Germany GmbH, registered in Frankfurt am Main, is the German company of J&T Express, a parcel group founded in Indonesia in 2015 and listed in Hong Kong. Germany's postal regulator, the Bundesnetzagentur, has listed the GmbH in its register of postal providers since 1 September 2026. J&T is looking for franchise partners all over Germany to run last-mile deliveries and collections. Every sentence in the status table above was read on live J&T Express Germany parcels.

### Which neighbour has my J&T parcel?

J&T's tracker tells you: under the delivered scan, click `Zustelldetails anzeigen` (`Click to view delivery details` in English) and enter the delivery postcode to see the neighbour's name and house number. Peek shows only `Left with neighbor`, with no name or address.

### Does J&T Express have parcel shops in Germany?

J&T's German website lists none, and no J&T Express Germany line Peek knows sends a parcel to a shop or locker. On the delivery preferences page you can ask for delivery to you, to a neighbour, at a reception desk or at a safe place at your address.

### Why is my J&T tracking number not found?

Check that it starts with `JTDE` and has 12 digits after it: numbers from J&T's other networks, such as `JTUK` in the UK, aren't German. If the number is right, J&T may not have the parcel yet: its tracker answers `Keine Abfrageergebnisse` (`No tracking records yet`), and Peek shows "Waiting for the carrier" until the first scan. If that lasts several days, ask the shop.

### How do I contact J&T Express in Germany?

Call +49 6931090650 (Monday to Friday, 8:00 to 20:00), use the live chat on jtexpress.de or email de-jtservice@jtexpress.de. For an online order, ask the shop first: it booked the delivery.

:::sources
- [J&T Express Germany: Track & Trace](https://www.jtexpress.de/tracking) – tracker, up to 10 numbers, languages, scans reloaded, the no-records message, neighbour details behind the postcode, five tries
- [J&T Express Germany: Frequently Asked Questions](https://www.jtexpress.de/faq) – JTDE numbers and where to find them, delivered not received, wrong address, live chat; no delivery times, attempts or claim rules
- [J&T Express Germany: Delivery Preferences](https://www.jtexpress.de/delivery-preference) – link in the notification email, postcode check, methods with and without a signature, risk at a chosen place
- [J&T Express Germany: Contact Us](https://www.jtexpress.de/contact) – phone, hours, chat, email, office address
- [J&T Express Germany: Legal notice](https://www.jtexpress.de/legal-statement) – J&T Express Germany GmbH, Frankfurt am Main
- [J&T Express Germany: POD Privacy Policy](https://www.jtexpress.de/pod-policy) – delivery photos, six months, complaints, data protection contact
- [J&T Express Germany: Become Our Partner](https://www.jtexpress.de/career) – franchise partners for last-mile delivery and collection
- [J&T Express Germany: About Us](https://www.jtexpress.de/about) – founded 2015 in Indonesia, Hong Kong listing
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – names, the JTDE shape, tracking links, the tracker's sentences read on live parcels and their stages, delivery runs, delivery types, what is left out, help page
- [Universal Parcel Scraper: J&T's shared tracker lookup](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – English sentences, exact scan times, no place, expected day or weight, names and phone numbers left out, newest 100 scans
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – other J&T networks' shapes, no hand-over to another carrier, tracking services as a fallback
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – the seller's risk in transit
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – damage reported on delivery, hidden damage within seven days, text form
- [Bundesnetzagentur: Anbieterverzeichnis Post](https://www.bundesnetzagentur.de/DE/Fachthemen/Post/Anbieterverzeichnis/PostAnbieterverzeichnis.html) – register of postal providers: J&T Express Germany GmbH, listed on 1 September 2026
- [Bundesnetzagentur: Häufig gestellte Fragen zur Schlichtung Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/_FAQ_Schlichtung/faqSchlichtung.html) – recipients may apply, no fee, special terms for online shops' parcels, Verbraucherservice Post
:::
