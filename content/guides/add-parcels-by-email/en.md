---
title: Track parcels from your email: forward it to Peek
description: Forward a shipping email to your own Peek address and Peek adds the parcel. Where to find the address, how to forward automatically, what Peek keeps.
slug: add-parcels-by-email
picture: An envelope sliding out of an inbox into a list of parcels on a phone, with Pip, the parcel with a face, waving from the top of the list.
published: 2026-11-19
updated: 2026-11-21
---

Forward the shipping email to your own Peek email address, and Peek adds the parcel to your deliveries. You find that address under "Settings" → "Deliveries" → "Add parcels by email", and you can forward one email by hand or have your mail app forward shipping emails by itself.

It needs a Peek account: guests, who follow parcels without one, don't get an address. And Peek marks the feature "Beta": in its own words, it "can miss a parcel, and it may still change."

:::steps
- Open "Add parcels by email" | Signed in on the website or in the iPhone, iPad or Android app, the round account button with your initial opens "Settings": under "Deliveries", choose "Add parcels by email". On a Mac, it's a tab of Peek's Settings window (⌘,).
- Select "Get my address" | It appears under "Your address". A random code of 16 letters and digits makes it yours alone.
- Copy it | Select "Copy address". The iPhone, iPad and Android apps also have "Share".
- Forward a shipping email | In your mail app, forward the shop's or the carrier's email to that address. Peek finds the shop in the forwarded message's own "From:" line.
- Check "Recent emails" | The page lists your last five emails, who sent each and what it did, such as "2 parcels added".
:::

Each parcel is named after the shop that sent the email, tidied ("EXAMPLE® SHOP AG" becomes "Example Shop"), or, for a carrier's email, after the shop it says it carries for. Peek marks it new in your list and sends one notification, "Added from your email", with its name and carrier.

## Can I track packages automatically from my email?

Yes, with a rule in your mail app that forwards shipping emails to your Peek address: each one then adds its parcel by itself. Peek never connects to your mailbox. It sees only what reaches its address, from any shop or carrier: there is no list of supported shops. Some other trackers ask to connect your inbox instead; [universal parcel tracking](guide:universal-tracker) compares the two.

The "Added from your email" notification goes to each browser and phone where your account has Peek notifications on (see [parcel delivery notifications](guide:parcel-notifications)), unless you switch off "Notify me when an email adds a parcel". It isn't sent once you've opened or archived the parcel.

## Where do I find my Peek email address?

In "Add parcels by email", under "Your address", once you've selected "Get my address": one address per account, the same on the web, iPhone, iPad, Mac and Android. No such row? Check that you're signed in: every account has the feature, and only accounts do.

## How do I forward shipping emails automatically in Gmail, Outlook or iCloud?

Set a rule in your mail app that forwards an email to your Peek address when its subject has a shipping word. Peek gives the words and the steps under "Forward automatically", on the same page:

:::steps
- Pick your mail service | Under "Your mail service": Gmail, Outlook, iCloud, Yahoo or "Other". Peek starts on the one your account's email address belongs to.
- Pick the languages | Under "Languages your shops and carriers write in", choose from ten. Peek starts with its own language, your browser's and English.
- Copy the words | For Gmail, Peek builds a search to put in a filter: "Copy search". For the others, it lists a few words to look for in the subject: "Tap a word to copy it."
- Make the rule | Follow the steps Peek shows for your service, forwarding to your Peek address. For Gmail, Outlook and iCloud, they're done on a computer.
:::

In English, the words include shipped, shipment, delivery, parcel and package. The Gmail search leaves out "delivered" on purpose: a parcel added once it has arrived comes too late to help. Peek's Gmail steps include confirming the forwarding: the link Gmail sends shows on Peek's page, as "Open Gmail’s confirmation link", usually within a minute.

For Yahoo Mail, Peek explains that Yahoo's filters can only move emails into folders: "Forward your shipping emails by hand instead." Under "Other", it warns that services which first send the address an invitation to accept, such as Proton, don't work.

Already have a shipping email in your inbox? Forward that one by hand.

## What does Peek read in my emails, and what does it keep?

Peek reads only an email about a parcel: one with a shipping word in its subject, in any of Peek's languages, or one from a carrier. Any other email, such as a newsletter, is ignored: neither read nor kept.

Peek reads an email's subject, text and links, never its attachments. It takes the tracking numbers whose shape clearly belongs to a carrier, as when you paste text, and any number the email labels as a tracking number, never an order number: at most 10 per email.

Its [privacy notice](https://peektracker.com/privacy.html) lists what it keeps:

- Your address, whether you want the notification, and how many emails came each day (kept seven days).
- For the last twenty emails: when each came, the sender's name and what it did.
- Each email it reads, with your account's id and arrival time, to learn to read such emails better: for 30 days, or until 100 newer ones have come.

An ignored email isn't kept, nor one a limit held back, nor Gmail's confirmation. Peek may send some kept emails to Anthropic, in the United States, whose AI model Claude reads them to check and improve the reading; Anthropic keeps them about 30 days.

## Why wasn't my parcel added?

Look under "Recent emails": each email says what it did.

| What the page says | What happened |
| --- | --- |
| "No tracking number in it" | Peek found none. "Some shops, like Amazon, put no tracking number in their emails." |
| "The tracking number shows only in your Amazon account" | An Amazon shipping email, which usually holds none |
| "Already in your deliveries" | You already follow that parcel: it's never added twice |
| "Already in your deliveries: shop name added" | The same, and the unnamed parcel took the shop's name. A name you gave stays |
| "Already delivered: not added" | The subject says it was delivered. A failed delivery, or one at a pickup point or locker, counts as on its way |
| "Not a shipping email: not read" | Its subject has no shipping word in any language, and it doesn't come from a carrier: a newsletter, a bill. Peek neither reads nor keeps it. Several in a row take one line |
| "Not read: today’s limit is reached" | A daily limit was reached: on emails to your address (by UTC day), or on parcels your account adds, by email or by hand. From half the email limit, the page shows how many you've used |
| "Not added: your deliveries are full" | Your account holds 50 active parcels, 500 with archived ones |

Two cases leave no line: an email over 1 MB is refused, and you get back "This email is too large for Peek. Forward the shipping email alone."; mail sent to an old or turned-off address is dropped without a word.

No number in the email? The order page usually has it: see [how to find your tracking number](guide:find-tracking-number), [tracking Amazon orders](guide:amazon-orders) and [tracking number formats](guide:tracking-number-formats).

## How do I turn it off?

Select "Turn off" at the bottom of "Add parcels by email": it deletes your address, what it recorded and the emails kept from it. Delete the forwarding rule in your mail app too, or it forwards into nothing.

- **Only the notification:** switch off "Notify me when an email adds a parcel". The address keeps working.
- **A new address:** select "Get a new address". Peek asks you to confirm: "The current address stops working at once." Put the new one in your rule.
- **Everything:** deleting your Peek account deletes the address and the kept emails.

:::sources
- [Peek: Privacy notice](https://peektracker.com/privacy.html) – what Peek keeps from forwarded emails, for how long, and Anthropic's part
- [Peek: the Add parcels by email page](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/AddByEmail.tsx) – the address, recent emails, automatic forwarding, the switches
- [Peek: English copy](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/en.json) – Beta, every label, the results, the Yahoo and Proton notes
- [Peek: mail services and filter words](https://github.com/plhery/peek-delivery-tracker/blob/main/src/lib/emailFilter.ts) – services, languages, the Gmail search without "delivered" words
- [Peek: receiving a forwarded email](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/inboundEmail/receive.ts) – any sender, no duplicates, delivered emails, old addresses
- [Peek: reading an email](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/inboundEmail/mail.ts) – the forwarded From: line, subject, text and links, no attachments
- [Peek: numbers taken from a text](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/parcelsFromText.ts) – numbers a carrier claims, labelled numbers, 10 per email, the daily allowance
- [Peek: shop names](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/inboundEmail/shopName.ts) – how a parcel is named
- [Peek: address and limits](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/inboundEmail/settings.ts) – the random 16-character address, the daily limit
- [Peek: Cloudflare email worker](https://github.com/plhery/peek-delivery-tracker/blob/main/ops/cloudflare/inbound-email-worker.js) – Cloudflare Email Routing, the 1 MB limit
- [Peek: the "Added from your email" notification](https://github.com/plhery/peek-delivery-tracker/blob/main/supabase/migrations/20261117200000_email_added_push.sql) – queued as the email adds the parcel, not for opened or archived parcels
- [Peek: Architecture](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ARCHITECTURE.md) – how forwarded emails are read, named and kept
:::
