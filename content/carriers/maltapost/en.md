---
title: MaltaPost tracking: numbers, statuses and collection
description: Track a MaltaPost item: what its tracking number looks like, what each status means, picking it up at a post office or Easipik locker and who to call.
slug: maltapost-tracking
published: 2026-10-13
updated: 2026-10-26
---

MaltaPost is Malta's national post, delivering to every address in Malta and Gozo six days a week. Track an item on its [tracking page](https://www.maltapost.com/tracking) or paste the number in the box above. The number comes from whoever posted the item: the sender's receipt, the label under the barcode or your delivery notice.

## MaltaPost tracking number: what it looks like

MaltaPost's own registered items carry 13 characters: [two letters, nine digits](guide:tracking-number-formats) and `MT`. Its redelivery form names the local prefixes RH, RR, RT and RI.

:::anatomy RR 000000005 MT
- RR | Item type: RR, RH, RT or RI for local registered items
- 000000005 | Nine digits, the last one a check digit
- MT | Posted in Malta
:::

An item from abroad keeps the number the foreign post gave it (`RR000000005GB`, `CP000000005CN` or another shape), and MaltaPost's tracking takes it. The page can then show both a "Foreign Barcode" and a "Local Barcode". SendOn parcels can be tracked by either barcode.

MaltaPost's FAQ asks for the number in capital letters. If the page answers "Tracking number not recognised.", check the number with the sender. Some numbers, such as those starting with U, can't be tracked in Malta, or another local company is delivering the item. An item sent without a tracking number can't be tracked or located. Lost the number? See [where to find a tracking number](guide:find-tracking-number).

## MaltaPost tracking statuses explained

Each scan has a date, time, event and scan location, sometimes with a "Reason:" and an "Action:" line under it. For an item coming to you:

| MaltaPost says | What it means for you |
| --- | --- |
| `Item created` | Posted. For an item from abroad, this is the foreign post accepting it. |
| `Item arrived at inbound mail sorting centre` | It has reached Malta. |
| `Item to be submitted for clearance` | It is going to customs. |
| `Item undergoing border agency clearance` | Customs or another border agency is checking it. |
| `Item held at inbound mail sorting centre` | Held. With "Awaiting payment of charges", nothing moves until you pay. |
| `Item returned from local Customs office` | Customs has released it. |
| `Item to be forwarded to the delivery centre` | On its way to the office that will deliver it. |
| `Item scheduled for physical delivery` | Out for delivery. |
| `Item could not be processed further. Unsuccessful attempt-Recipient notified` | Nobody could take it: a notice is in your letterbox. |
| `Item arrived at collection point` | Ready for pickup at the post office named in the scan. |
| `Item delivered/collected by recipient` | Delivered, or picked up by you. |
| `Item could not be processed further. Item not collected. Item returned to sender` | Nobody picked it up in time; it is going back. |

A progress bar on the page sums it up in stages such as `Arrived in Country`, `Ready for Collection` and `Attempted Delivery`.

Three lines aren't movements: `Declaration Submitted`, `Payment Submitted` and `Online payment complete` only record what you did online. `Forwarded to the next processing centre` is a move between MaltaPost's own centres. MaltaPost warns that the date and time are those of the scan, not always of the event.

## MaltaPost delivery notice, collection and Easipik lockers

MaltaPost tries to deliver registered items to your door. If nobody is in, it posts a notice in your letterbox and you pick the item up at the post office it names. Notices come in different colours, each saying how the item can be picked up or redelivered.

**Picking up:** bring the notice and an original ID (ID card, passport, driving licence, residence card or single work permit). Someone picking up for you needs the notice with your original ID and their own, or a power of attorney. Post offices generally open Monday to Saturday from 7.30am to 1pm; some differ.

**Missed a delivery?** The [Missed a Delivery form](https://www.maltapost.com/delivery/missed-delivery) asks for the notice's colour, the tracking number and a photo of the notice. You can have the item redelivered to an address, another (Sub) Post Office or an Easipik locker, or kept at the office past the standard 12 working days. A second tracked delivery costs €5.50 for one item. After the redelivery window, MaltaPost can keep an item for up to 12 weeks. Items left uncollected go back to the sender.

**Easipik lockers:** 24/7 parcel lockers across Malta and Gozo, for parcels up to 40x40x60 cm and 30 kg. MaltaPost sends the locker code by SMS and email; the parcel stays 72 hours after the code, then goes to the local post office counter. Easipik is free for MaltaPost deliveries; other couriers' parcels cost €3.50.

## MaltaPost customs charges, delays and lost items

**Held for charges.** Duty and VAT are due on items from outside the EU, and the law bars MaltaPost from handing them over until they're paid. On the tracking page, the "Declare Items" button lets you upload the documents and "Pay Charges" lets you pay; a paid item is released and delivered on the next working day. More in [parcels held at customs](guide:customs).

**No update, or not arrived.** For an item from abroad, MaltaPost's FAQ says the enquiry goes through the post office where it was posted, so ask the shop or sender to open one. MaltaPost counts a delay as four or more working days after mailing, and enquiries and complaints are possible up to six months from posting. It aims to settle international ones within 90 days. See [why tracking stops updating](guide:tracking-not-updating).

**Damaged.** Inspect a registered item or parcel in front of MaltaPost staff when you receive it, or MaltaPost accepts no liability. Compensation goes to the sender, who can waive it in your favour.

## MaltaPost customer care number and contact

MaltaPost's customer service page, read on 13 October 2026, lists:

- **Phone:** (+356) 2122 4421, or freephone 800 722 44, free from a fixed line. Monday to Friday 8.30am to 2.30pm, Saturday 7.30am to 12.30pm, not on public holidays.
- **Email:** info@maltapost.com, also for clearing customs yourself or through your own agent.
- **Contact form** after the FAQ, or Facebook messages.
- **Head office:** 305, Triq Ħal Qormi, Marsa MTP 1001, Monday to Friday 8am to 4pm.
- **Easipik care** (lockers and PIN codes): 2124 3324.

> MaltaPost warns of fake SMS and emails that ask you to click a link and pay a fee before delivery. Check the domain is maltapost.com, not a lookalike such as malta-post.com, and report scams to the Malta Cyber Crime Unit.

## Tracking MaltaPost items with Peek

For a number ending `MT` with a valid check digit, [Peek](/) asks MaltaPost and [Chronopost](carrier:chronopost) at once and shows "MaltaPost has this parcel." when MaltaPost knows it, or "Not found at … yet. Peek will keep checking after you add it." With a wrong check digit MaltaPost isn't asked: check the number, or pick the carrier with "Change". A maltapost.com tracking link with `barcode=` always names MaltaPost, whatever number it holds.

Peek leaves a number from abroad with its own post, but MaltaPost's lookup takes it, SendOn barcodes included: pick "MaltaPost" with "Change", or paste a maltapost.com link. It switches to MaltaPost by itself only when the carrier abroad names MaltaPost.

MaltaPost's times carry no time zone: Peek reads them on the clock of the scan's place, Maltese time for scans in Malta, and lists MaltaPost's own history. Statuses keep MaltaPost's wording. Peek can show the post office where an item waits or was picked up, and can notify you when the status changes. There's no expected delivery day and no weight. An item MaltaPost withholds from tracking can't be followed.

## Questions about MaltaPost tracking

### Is MaltaPost open on Saturday?

Yes. Post offices generally open on Saturday from 7.30am to 1pm, and the phone line answers from 7.30am to 12.30pm, except on public holidays.

### How long does MaltaPost take to deliver?

MaltaPost gives no general delivery time for items from abroad. International mail landing before 4.30pm on a weekday (1.30pm on Saturday) is processed that day for delivery the next working day; SendOn parcels take about 7 to 12 days from the UK and about 15 from the USA.

### Is there a 24/7 MaltaPost customer care number?

No. Customer care answers on weekdays from 8.30am to 2.30pm and on Saturday mornings; Easipik lockers are open 24/7.

### What does an international MaltaPost tracking number look like?

An item sent from Malta abroad has two letters, nine digits and `MT`, such as `RR000000005MT`. An item coming to Malta keeps its foreign number, which MaltaPost's tracking accepts.

:::sources
- [MaltaPost: Tracking](https://www.maltapost.com/tracking) – tracking field, scan columns, progress stages, foreign and local barcodes, not-recognised reasons, customs buttons
- [MaltaPost: Frequently Asked Questions](https://www.maltapost.com/faqs?l=1) – capital letters, notices, post office hours, Easipik 72 hours and care line, customs payment, next-day processing, items from abroad, SendOn times
- [MaltaPost: Missed a Delivery?](https://www.maltapost.com/delivery/missed-delivery) – redelivery options, 12 working days, 12 weeks, redelivery fee, local prefixes
- [MaltaPost: Packet Collection](https://www.maltapost.com/packets) – ID and notice to collect, collecting for someone else
- [MaltaPost: courier it](https://www.maltapost.com/courier) – delivery attempt and notice
- [MaltaPost: Easipik](https://www.maltapost.com/easipik) – lockers, code by SMS and email, sizes, third-party fee
- [MaltaPost: Parcel Lockers](https://www.maltapost.com/lockers) – locker stations in Malta and Gozo
- [MaltaPost: Post Offices Opening Hours](https://www.maltapost.com/postoffices) – per-office hours
- [MaltaPost: Customs](https://www.maltapost.com/customs) – declaration, payment, clearing via info@maltapost.com
- [MaltaPost: How to lodge an Enquiry](https://www.maltapost.com/how-to-lodge-an-enquiry?l=1) – delay definition, six-month limit, 7 and 90 days
- [MaltaPost: Committed to Deliver](https://www.maltapost.com/committed-to-deliver) – compensation to the sender, inspection on receipt
- [MaltaPost: Customer Service](https://www.maltapost.com/customers-service?l=1) – phone, freephone, hours, email, head office
- [MaltaPost: Scam Protection](https://www.maltapost.com/scamprotection?l=1) – fake SMS and email warning
- [MaltaPost: SendOn](https://www.maltapost.com/sendon) – forwarding service, delivery to door or Easipik
- [MaltaPost: About us](https://www.maltapost.com/about-us) – national postal operator, six days a week
- [MCA: How can I track and trace registered articles?](https://www.mca.org.mt/content/post-how-can-i-track-and-trace-registered-articles) – number under the barcode
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
