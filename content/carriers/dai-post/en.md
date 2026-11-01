---
title: DAI Post tracking: numbers, statuses and delivery help
description: Track a DAI Post (Parcel Right) parcel in Australia, New Zealand or Singapore: its numbers, what each status means, authority to leave, pickup and contacts.
slug: dai-post-tracking
published: 2026-11-03
updated: 2026-11-03
---

DAI Post is an Australian delivery company for online shops, started in Melbourne in 2000. It brings parcels from the US, the UK, Europe and Asia to buyers in Australia, New Zealand and Singapore. Its own final-mile delivery is called Parcel Right. Track a parcel on DAI Post's [tracking page](https://daipost.com/track), or paste the number in the box above. The number is in the shop's order confirmation. Can't find it? Ask the shop.

## DAI Post tracking number: what it looks like

DAI Post tracks the numbers shops print on their parcels, so they come in several shapes. Its tracker takes up to 10 at once. The shapes Peek's code knows, with invented digits:

| Number (invented) | What it is |
| --- | --- |
| `NZC000000000` | NZC and nine digits: in Peek's code, DAI Post's number for parcels to New Zealand. Peek names DAI Post at once. |
| `00393580940000000003` | 20 digits starting `0039358094`, the last a check digit: an SSCC, a label's barcode number, which the label may print as `(00) 3 9358094 000000000 3`. Other carriers use 20 digits too, so Peek can't be sure from the shape. |
| `ABC00000000000` | A shop's own reference, letters then digits. Peek doesn't recognise it by its shape. |

Spaces, brackets and lower case don't matter. If one digit of a 20-digit number is wrong, its check digit fails and Peek won't link it to DAI Post: check the number. More in [how to find a tracking number](guide:find-tracking-number) and [tracking number formats](guide:tracking-number-formats).

## DAI Post tracking statuses explained

DAI Post's tracker shows a friendly sentence for each step. Some parcels show a shorter form of the same step, such as `Checked in at origin hub`. Peek reads the step's code, so both forms mean the same. The lines Peek knows, in order:

| DAI Post says | What it means | Peek shows |
| --- | --- | --- |
| `We’ve received your delivery details. We’ll let you know as soon as your parcel is in our hands.` or `Customer label information received` | The shop has sent your details. DAI Post doesn't have the parcel yet. | "Label created" |
| `Your parcel’s checked in and being prepared for its journey.` or `Checked in at origin hub` | DAI Post's origin hub has the parcel. | "Received by carrier" |
| `We’ve processed your parcel at our origin facility. Next, on the move to you!` or `Parcel processed at origin facility` | Sorted, ready to leave. | "In transit" |
| `There’s a departure delay. Thanks for your patience, we’ll update you soon.` or `There’s a transit delay. Thanks for your patience, we’ll update you soon.` | The flight is late leaving, or there's a delay on the way. | "In transit" |
| `Your parcel is in the air and is heading your way.` or `Parcel departed origin country` | Flying out of the origin country. | "In transit" |
| `Parcel arrived in trans-shipment country.`, then `Your parcel’s left the transfer country and is back on the move.` | A stop in another country on the way. | "In transit" |
| `Touchdown! Your parcel has landed.` or `Parcel arrived in destination country` | It has arrived in your country. | "In transit" |
| `We’ve collected your parcel from the airline and it’s being processed.` or `Collected from the airline for processing` | DAI Post has it from the airline. | "In transit" |
| `Your parcel is being checked by customs. We’ll let you know when it’s cleared.` or `Parcel processed by clearance facility` | Customs is checking it. | "At customs" |
| `Customs clearance delay` | Customs still holds it. | "At customs" |
| `All clear! Your parcel’s passed customs and is ready for the next leg.` or `Customs cleared` | Customs has let it through. | "In transit" |
| `We’ve got your parcel and it’s been sorted for delivery.` or `Parcel received and sorted` | Sorted for delivery. | "In transit" |
| `Your parcel’s on an interstate journey. We’ll keep you updated as it travels.` | Travelling from one state to another. | "In transit" |
| `Your parcel’s arrived at our local facility, it's on the way.` | At a depot near you. | "In transit" |
| `Your parcel’s on its way to you, almost there!` | Heading to the delivery depot, not yet on a delivery run. | "In transit" |
| `It’s happening, your parcel will be delivered today. Get ready!` or `Parcel out for final mile carrier delivery` | Out for delivery today. | "Out for delivery" |
| `Parcel unsafe to leave at address` | The driver didn't leave it (see below). | "Delivery attempted" |
| `Parcel available for collection` | Waiting at a pick up location. | "Ready for pickup" |
| `Delivered! Your parcel has arrived. Enjoy!` | Delivered. | "Delivered" |
| `Delivered, left where you can find it.` or `Parcel delivered - authority to leave` | Left at your address, no signature. | "Delivered" |

For a number it doesn't know, DAI Post's tracker says `Sorry, we couldn't find your parcel.` More in [what tracking statuses mean](guide:tracking-statuses).

**Times.** DAI Post gives each time on the local clock where the parcel was scanned, with no time zone. Peek works out the zone from the place and shows times in your device's time zone. The first line's hour can be off.

## DAI Post delivery: authority to leave, pickup and returns

DAI Post says its network delivers to 100% of the Australian population. The time of day depends on the driver's route, which changes with the day's parcels and the traffic.

- **Authority to leave.** No signature is needed. If nobody answers and the driver thinks the place is safe, the driver leaves the parcel.
- **Not safe to leave.** If the parcel would be too exposed, it goes to the nearest pick up location in your area, and DAI Post sends you pick up instructions. On DAI Post's tracker, the `Parcel available for collection` line links to a map of the place; Peek shows the town only.
- **7 days to collect.** A parcel not collected within 7 days goes back to DAI Post's depot, then to the shop. So does one with incomplete delivery details.
- **Messages.** You get notifications for key steps only if the shop gave DAI Post your email address and mobile number.

DAI Post's terms add that if you're not there, the parcel may be left in a secure location or taken to a Collection Point, and then counts as delivered. Its pages don't say who delivers in New Zealand or Singapore. See [missed delivery](guide:missed-delivery) and [uncollected parcels](guide:uncollected-parcel).

## Customs on a DAI Post parcel

**Customs held.** DAI Post's help page says a customs held event means ABF or DAFF, Australia's border and biosecurity agencies, probably need more information before they release the parcel. Ask DAI Post's customer service what they need: its enquiry form has the reason "My parcel is Customs held."

**Charges.** DAI Post's terms make the sender responsible for the shipment's charges, taxes and duties included. If you refuse to pay a charge on delivery, the sender still owes it, and DAI Post may keep the parcel until it's paid or return it to the sender. What you owe, if anything, depends on your country's rules: see [parcels held at customs](guide:customs). A message about a fee looks odd? See [delivery scam texts](guide:delivery-scam-texts).

## DAI Post tracking not updating, lost or returned

**No new line for days.** DAI Post scans parcels at key points only, and says a parcel still moves between them. On interstate or regional deliveries, it can seem to stand still for days. After four days without news, Peek flags it ("No tracking update for four days"). See [why tracking stops updating](guide:tracking-not-updating).

**Still "Label created".** DAI Post doesn't have the parcel yet. If it stays like this for days, ask the shop whether it really sent it.

**Delivered, but not there.** With authority to leave, look around your home first. DAI Post can email you proof of an authority-to-leave delivery: ask through its enquiry form. Then contact the shop. See [delivered but not received](guide:delivered-not-received).

**Lost or returned.** For a parcel that never came, use DAI Post's enquiry form with the reason "I did not receive my parcel." Either way, the shop booked the delivery, so ask it for a refund or a new parcel. See [lost parcels](guide:lost-parcel) and [returned to sender](guide:returned-to-sender).

## DAI Post customer service and contact

DAI Post's channels, as read on 1 November 2026:

- **Phone:** Australia +61 3 8586 1200, New Zealand +64 800 451 718, Singapore +65 800 321 1587. No opening hours are given.
- **Email:** consumercare@daipost.com.
- **Online:** the [enquiry form](https://daipost.com/contact). It asks for your name, phone, email, tracking number and the reason for your enquiry.

## Track a DAI Post parcel with Peek

[Peek](/) follows a DAI Post parcel with the number alone: no account or postcode.

- **An NZC number.** Peek names DAI Post at once ("Detected carrier").
- **A 20-digit number starting 0039358094.** Paste it all the same: Peek follows more than 3,500 carriers. While it checks, you see "Checking tracking services…", and DAI Post is among the first asked. If DAI Post has it, Peek names it. If not yet, the parcel shows "Waiting for DAI Post’s first scan" and the card says "Probably DAI Post"; after a week, Peek suggests checking the number with the sender. To choose DAI Post yourself, tap "Change": it's under "Fits this number".
- **Any other number.** Paste it all the same, then tap "Change" and pick DAI Post (search DAI Post or Parcel Right). On a parcel already added, use "Change carrier".
- **A link.** Paste a tracking link from daipost.com, parcelright.com.au or parcelrig.ht: Peek takes the number and names DAI Post straight away.

Peek shows the status, each line in DAI Post's words, the town and country of each scan, the expected delivery date when DAI Post gives one, and the delivery time. It shows no pick up point, service name, weight or proof of delivery. "Open the DAI Post website" opens the number on DAI Post's tracker. In Peek's code, some [Asendia](carrier:asendia) parcels, and some [ePost Global](carrier:epost-global) parcels to New Zealand, reach DAI Post: their tracking shows its scans.

Peek checks every 10 minutes, or every 2 minutes once out for delivery. After two days without news (12 hours while the parcel still shows "Label created" or "Received by carrier"), Peek checks hourly. Alerts: "Every scan", "Important steps" or "Delivery only". Peek can't contact DAI Post or the shop for you.

## Questions about DAI Post tracking

### Is Parcel Right the same as DAI Post?

Parcel Right is DAI Post's own final-mile delivery. In Peek, search either name.

### What does "Parcel unsafe to leave at address" mean?

The driver found nobody, and the place wasn't safe. The parcel goes to the nearest pick up location: collect it within 7 days, or it goes back to the shop.

### How long does DAI Post take to deliver?

DAI Post's terms say standard delivery times are estimates, not guarantees. Its tracker can show an expected delivery date, and Peek shows it too.

:::sources
- [DAI Post: Home](https://daipost.com/) – Melbourne, 2000, parcels from the US, UK, Europe and Asia into Australia, New Zealand and Singapore, Parcel Right
- [DAI Post: Help and support](https://daipost.com/help-and-support) – phones, coverage, delivery times, no recent updates, notifications, authority to leave, pickup, returns, customs held, proof of delivery
- [DAI Post: Delivery tracking](https://daipost.com/track) – the tracker, up to 10 numbers
- [DAI Post: Contact](https://daipost.com/contact) – enquiry form and its reasons, phones, consumercare@daipost.com
- [DAI Post: Terms & conditions](https://daipost.com/terms) – secure location or Collection Point, estimated delivery times, charges
- [Universal Parcel Scraper: DAI Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dai-post) – names, number shapes, links, wordings and stages, clocks, expected delivery
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – detection, Asendia's and ePost Global's copies of DAI Post's scans
:::
