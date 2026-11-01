---
title: PIN AG tracking: registered letters, parcels and pickup
description: Track a PIN AG letter or parcel in Germany: which items PIN tracks, what its German statuses mean, depot pickup, missing mail and how to contact PIN.
slug: pin-ag-tracking
published: 2026-11-06
updated: 2026-11-06
---

PIN AG is a postal company in Berlin, separate from Deutsche Post, with about 1,300 staff. It delivers letters, registered letters (Einschreiben), parcels and orders from Amazon, medimops and rebuy. Only some of these items can be tracked. Track one on [PIN AG's tracking page](https://sendungsverfolgung.pin-ag.de/) (Sendungsverfolgung) or paste the number in the box above. The number comes from the sender: a shop puts it in its shipping email, and for a registered letter you ask whoever sent it.

## Which PIN AG items can you track?

PIN tracks only items that need proof of delivery, from the moment it gets them to delivery. The sender decides this when they choose the product. PIN's help pages list:

| Tracked | Not tracked |
| --- | --- |
| Registered letters: Einschreiben-Einwurf, -Übergabe, -Persönlich, -Rückschein and -Persönlich-Rückschein | Standard letters (Standardsendungen) |
| Formal deliveries (Postzustellaufträge): official letters, such as from a court or an authority | Book and goods items (Bücher- und Warensendungen) |
| Parcels (Pakete) | medimops and rebuy orders |
| Amazon Prime items, anywhere in Germany | Ordinary Amazon items outside Berlin |
| Amazon items within Berlin | Advertising mail (Werbepost) |

The FAQ on PIN's tracking page differs on one point: it says ordinary (non-Prime) Amazon items can't be tracked, and makes no exception for Berlin. So an ordinary Amazon order may show no status at all.

## PIN AG tracking number

The shapes Peek knows (digits invented):

| Number (invented) | What it is |
| --- | --- |
| `0100000000000000` | 16 digits starting `0100`: a PIN parcel number, as Peek's code reads it. 16 digits also fit [DHL eCommerce](carrier:dhl-ecommerce) and [TNT](carrier:tnt). |
| `0000000000000` | 13 digits: a registered letter or a formal delivery. Many other carriers use 13 digits too. |
| `000000000000(0)` | The same 13 digits as a label can print them, with the last digit in brackets. Peek reads them as `0000000000000`. |

If the shop's email names [DHL](carrier:dhl) or another carrier, the number is theirs. No number at all? See [how to find your tracking number](guide:find-tracking-number).

## PIN AG tracking statuses in English

PIN's tracking page shows a progress bar with four steps (`Sortierzentrum`, `Zustelldepot`, `In Zustellung`, then `Zugestellt`, or `Unzustellbar` for an item it can't deliver) and PIN's status in German. What they mean:

| PIN says | What it means for you |
| --- | --- |
| `Sortierzentrum` | Your item is at PIN's sorting centre. |
| `Zustelldepot` | It is at the delivery depot for your area. |
| `In Zustellung` | Out for delivery. PIN usually delivers it the same day: its carriers are out until the afternoon. If delivery fails, the carrier tries again the next day. |
| `Zugestellt`, `Die Sendung wurde zugestellt.` | Delivered. |
| `Unzustellbar` | It couldn't be delivered. PIN sends undeliverable items back to the sender. |
| `Die Sendung wurde in unserem Sortierzentrum erfasst und wird ohne weitere Aktualisierung der Sendungsverfolgung in den kommenden Tagen zugestellt. Hinweis: Bitte beachten Sie, dass es sich um eine Sendung handelt, bei dem der Zustellverlauf nicht dokumentiert wird. Für zusätzliche Informationen schauen Sie gerne in unsere FAQ.` | PIN has recorded it at the sorting centre and will deliver it in the next few days, with no more tracking updates: PIN doesn't record the delivery of this kind of item. |

The last status appears at the `In Zustellung` step, but the item is not out for delivery yet.

## PIN AG delivery: letterbox, substitute and depot

**Who delivers.** In Berlin, PIN delivers a letter usually one day after its sorting centre has processed it. When PIN hands the item to a delivery partner (Zustellpartner) outside Berlin, delivery can take a little longer, and the tracking status may stop changing, even for a registered letter ([why tracking stops updating](guide:tracking-not-updating)). An ordinary Amazon item outside Berlin travels as a plain letter, so its status doesn't change at all.

**How.** PIN puts the item in your letterbox or hands it to you. If that doesn't work, it may give it to a substitute: a family member, someone in your home, an employee at your business, or the owner or landlord of your home. It doesn't do this when the item must go to you in person, or when you or the sender told PIN not to. Registered letters are delivered the way their type requires. PIN can't deliver to a Packstation.

**Collecting it at the depot.** When PIN can't deliver, it keeps the item for you at a PIN depot (Zustelldepot) or a partner, and tells you with a notice card (Benachrichtigungsschein).
- Go from the next working day, during opening hours. The card gives the depot's address and hours, and so does PIN's tracking page when you enter your number.
- Bring official photo ID, which is required, and the card. No card? Then you need the tracking number and the day of the delivery attempt.
- Someone else can collect it with the authorisation form on the card (Vollmacht) and their own photo ID.
- PIN keeps it for 7 working days (Monday to Saturday), counted from the day after the attempt. A formal delivery waits 3 months, and counts as delivered on the day it was left at the depot.
- After that, the item goes back to the sender automatically.

## PIN AG item late, missing or returned

For a replacement or a refund, the sender is your contact, and PIN says that's the fastest way.

- **Not arrived after 8 working days.** Count Monday to Saturday from the day it was sent. Ask the sender for a new item or a refund. You can also send PIN a trace request (Nachforschungsauftrag) with its online form. A trace can take up to 21 days, and PIN may not find the item. Under PIN's terms, an item is presumed lost when it isn't delivered within 20 days of handover and PIN can't find it. See [lost parcels](guide:lost-parcel).
- **`In Zustellung` for more than three days.** PIN asks you to contact it, with the contact form its tracking page links to.
- **Delivered but not there.** Ask the people you live with: PIN may hand items to a substitute. For Amazon items, PIN says its tracking page shows where the item was delivered, for example to a neighbour, when you enter the number and your postcode. A technical error can also show an item as delivered before it is: wait a day, as it usually arrives the next day. More in [delivered but not received](guide:delivered-not-received).
- **Damaged.** Tell PIN with its "Hinweis zur Briefzustellung" form, which is also for late, wrongly delivered or irregular mail. PIN replies only in some cases. If the contents are damaged, contact the sender.
- **Returned.** PIN sends items back when there's no named letterbox or door slot, when nobody collects them in time, or when they're too damaged to deliver. Amazon items also go back to Amazon when the address is wrong or incomplete, your name isn't on the letterbox, the item doesn't match the letter format agreed with Amazon, or it is addressed to a Packstation. PIN can't change the address while delivering: ask Amazon for a new delivery. See [returned to sender](guide:returned-to-sender).

## PIN AG contact and customer service

Peek can't contact PIN AG or the sender for you. As read on 1 November 2026:

- **ServiceCenter:** 030 577978-800, Monday to Friday, 8 am to 6 pm.
- **Online forms:** the trace request (Nachforschungsauftrag) and the delivery report (Hinweis zur Briefzustellung), both linked from PIN's help pages, and the contact forms its tracking page links to.
- **Head office:** PIN AG, Alt-Moabit 91, 10559 Berlin. Phone 030 577978-0, email info@pin-ag.de.

## Tracking a PIN AG item with Peek

Paste the number in [Peek](/), with or without brackets: Peek joins a bracketed last digit to the others.

- **16 digits starting with 0100.** Other carriers use 16 digits too, so Peek asks the likely ones whether they have it, by your country (your iPhone's region, or on the web where you connect from). In Germany it asks PIN AG first. While it asks, you see "Checking tracking services…". If PIN has the item, you see "PIN AG has this parcel." and Peek files it there. Outside Germany, Peek doesn't ask PIN AG: press "Change" and pick PIN AG.
- **Not listed yet.** Add it anyway. In Germany, once Peek has asked PIN and PIN doesn't list the number yet, the parcel shows "Waiting for PIN AG’s first scan" and "Probably PIN AG", and Peek keeps asking PIN. After a week, Peek asks you to check the number with the sender or choose another carrier.
- **13 digits.** Peek can't tell a PIN number by this shape alone. Paste it all the same: Peek follows more than 3,500 carriers. Press "Change" and pick PIN AG (type PIN). On a parcel you've already added, use "Change carrier".
- **A link to PIN's tracking page** with your number names PIN AG at once ("Detected carrier").

**What Peek shows.** PIN's status and each step with its time, in PIN's German words, on German time. `Sortierzentrum`, `Zustelldepot` and the status with no further updates show as "In transit", `In Zustellung` as "Out for delivery", `Zugestellt` as "Delivered" and `Unzustellbar` as "Needs attention". An item waiting at the depot for you (PIN's tracking page then shows the depot's address and opening hours) shows as "Ready for pickup", whatever step PIN files it under. PIN gives no scan places, delivery estimate or weight. Peek leaves out your address and the depot's address: to see the depot, use "Open the PIN AG website", which opens your number on PIN's tracking page. A standard letter has no tracking at PIN, so Peek can't follow it either.

Peek checks every 10 minutes, and every 2 minutes once the item is out for delivery. After two days without news (12 hours while the item still shows "Label created" or "Received by carrier"), Peek checks hourly. It can notify you of "Every scan", "Important steps" or "Delivery only".

## Questions about PIN AG tracking

### Why can't I track my PIN AG letter?

PIN tracks registered letters, formal deliveries, parcels and Amazon Prime items, and its help pages add Amazon items within Berlin. Other letters, such as standard letters, have no tracking: the sender chose that. PIN expects a standard letter to arrive within the next working days.

### How long does PIN AG take to deliver?

In Berlin, usually one day after PIN's sorting centre processes the item. When a delivery partner takes over outside Berlin, it can take a little longer. If nothing has arrived 8 working days (Monday to Saturday) after sending, contact the sender.

### How long does PIN AG keep my letter at the depot?

7 working days (Monday to Saturday), counted from the day after the delivery attempt. Formal deliveries wait 3 months. Then the item goes back to the sender.

### Why does PIN AG tracking find no status for my number?

PIN's FAQ gives its message as `Für die Sendungsnummer konnte kein Zustellstatus gefunden werden.` The item may be a type PIN doesn't track, such as a standard letter. The number may have a typo or brackets in it. Or the item may be older than six months: PIN deletes tracking data after six months for data protection.

### Does PIN AG deliver to a Packstation?

No, PIN AG can't deliver to Packstations. Amazon normally sends those orders with Deutsche Post; if PIN gets one anyway, it sends it back to Amazon.

:::sources
- [PIN AG: Sendungsverfolgung](https://sendungsverfolgung.pin-ag.de/) – progress steps; FAQ: items tracked, no status found, same-day delivery, next-day retry, contact forms
- [PIN AG: Häufige Fragen zum Empfang von Briefsendungen](https://pin-ag.de/servicecenter/post-empfangen/sonstige-sendungen) – items tracked, delivery times, 8 working days, trace request, depot pickup, returns, forms
- [PIN AG: Häufige Fragen zum Empfang von Amazon-, medimops- und rebuy-Sendungen](https://pin-ag.de/servicecenter/post-empfangen/amazon-sendungen) – Amazon items, delivered but not there, returns, Packstation
- [PIN AG: ServiceCenter](https://pin-ag.de/servicecenter) – phone number and hours
- [PIN AG: Allgemeine Geschäftsbedingungen](https://pin-ag.de/agb) – letterbox, substitutes, depot, undeliverable items, loss after 20 days
- [PIN AG: Das Unternehmen](https://pin-ag.de/unternehmen) – letter delivery, about 1,300 staff, Berlin
- [PIN AG: Impressum](https://pin-ag.de/impressum) – head office address, phone and email
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads, PIN's steps and wordings, number shapes
:::
