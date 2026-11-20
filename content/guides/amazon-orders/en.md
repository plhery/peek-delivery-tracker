---
title: Amazon order tracking: find your tracking number
description: Where Amazon shows your parcel's carrier and tracking number, on the website and in the app, and how to follow it in Peek, by hand or with Claude or ChatGPT.
slug: amazon-order-tracking
picture: A phone showing an order's progress, two vans passing a parcel between them, and Pip watching.
published: 2026-10-16
updated: 2026-11-20
---

Amazon shows your parcel's carrier and tracking number in one place: Your Orders, then Track package. Copy the number into [Peek](/), and you can follow the parcel next to all your others. It takes a minute, and it works the same way on every Amazon site. The exception is a parcel Amazon delivers itself: you can usually follow it only in your Amazon account.

> Using Claude or ChatGPT in Chrome? [Ask it to do it for you](#can-claude-or-chatgpt-add-my-amazon-parcels-for-me).

## Where do I find the tracking number on Amazon?

On the Track package page of each parcel. Here's how to get there:

:::steps
- Open Your Orders | On the website, select Returns & Orders at the top right. In the Amazon app, tap the person icon at the bottom, then Your Orders.
- Find the order | The newest orders come first. When an order comes in several parcels, each parcel has its own Track package button.
- Select Track package | On the website, it's next to the order. In the app, tap the item first.
- Find the carrier and the number | The page names the carrier, Royal Mail for example, and shows its tracking number, often labelled Tracking ID. If you don't see it, select All updates: Amazon lists the number there.
- Paste it into Peek | Peek recognises the carrier from the number and starts following the parcel.
:::

### When does the number appear?

Usually once the parcel has shipped. A new order may have no tracking number yet: the parcel is still on its way to the carrier, and the number appears after the carrier's first scan. Sometimes it shows up before that scan. Paste it anyway, and Peek shows the first update as soon as the carrier has one.

Amazon's emails usually don't include the tracking number. The Track package button in those emails leads to the same page.

### Does it work on amazon.fr, .de, .es and other Amazon sites?

Yes. Every Amazon site works the same way. Only the words change with the site's language:

| Site | Your Orders | Track package |
| --- | --- | --- |
| amazon.com, amazon.co.uk | Your Orders | Track package |
| amazon.fr | Vos commandes | Suivre le colis |
| amazon.de | Meine Bestellungen | Sendung verfolgen |
| amazon.it | I miei ordini | Traccia il mio pacco |
| amazon.es | Mis pedidos | Localiza tu paquete |
| amazon.pl | Moje zamówienia | Śledź przesyłkę |

## Which number is the tracking number?

An Amazon order can show three kinds of numbers. Only the carrier's tracking number always tracks your parcel outside Amazon.

- **The carrier's tracking number.** It comes from the carrier: USPS, Royal Mail, DPD, Evri, Australia Post and so on. Amazon shows it next to the carrier's name. Peek follows it like any other parcel.
- **The order number.** Three groups of digits joined by dashes, such as `123-1234567-1234567`. It's on the order, the invoice and Amazon's emails. It's never a tracking number: paste it into Peek, and Peek says "That looks like an Amazon order number".
- **An Amazon number.** Amazon gives one to the parcels its own network delivers, and Peek checks it with Amazon: [see below](#can-peek-track-an-amazon-tba-number). It looks like this, here with made-up digits:

:::anatomy TBA 123456789012
- TBA | Amazon's prefix. Also `TBC` (Canada) and `TBM` (Mexico), or `TBAU` with 9 digits (Australia)
- 123456789012 | Twelve digits
:::

In Europe, an Amazon number can also be two letters for the country, such as `UK` or `FR`, then 10 digits. Not sure which you have? Type it here:

:::checker
:::

A parcel can change carrier on the way: Amazon hands some to a partner for the last stretch, such as USPS in the US or Royal Mail in the UK. Track package then names that partner: copy its number.

## Can Peek track an Amazon TBA number?

Sometimes. When you paste an Amazon number, Peek first checks it with Amazon's public tracker:

- **Amazon knows it.** The parcel is one Amazon delivers for another business, with [Amazon Shipping](carrier:amazon-shipping). Peek follows it like any other.
- **Amazon doesn't know it.** Then it's most likely [Amazon Logistics](carrier:amazon-logistics) delivering your own Amazon order, which Amazon shows only in your account. Peek doesn't add it. It says so and offers "Open my Amazon orders", which opens your orders on the matching Amazon site. Peek never asks for your Amazon password.
- **Amazon can't be reached.** Peek says so and offers "Check again".

When Amazon delivers a parcel itself, the order may say "Shipped with Amazon" and show no tracking number at all. There's nothing to copy then: only Amazon's own Track package page follows that parcel.

## Can Claude or ChatGPT add my Amazon parcels for me?

Yes, if the assistant can use your browser. These two can:

- **Claude in Chrome,** Claude's extension for Chrome, on paid Claude plans.
- **ChatGPT's extension for Chrome,** which you install and use from the ChatGPT desktop app.

Both work in the Chrome window where you're already signed in to Amazon. Ask, and the assistant opens Your Orders, goes through each Track package page and notes each carrier and tracking number.

What happens next depends on whether Peek is connected to the assistant:

- **If Peek is connected** and the assistant can use it in that chat, it adds the parcels to your Peek account itself, named after the item. In Claude, open "Peek Parcel Tracker" in [Claude's connector directory](https://claude.ai/directory/peek-parcel-tracker), select "Connect", sign in to Peek and select "Allow". In ChatGPT, Peek can be added as a plugin on a Business, Enterprise or Edu plan. All the steps are in [track your parcels in ChatGPT or Claude](guide:ai-assistants).
- **If it isn't,** the assistant lists the numbers in the chat. Copy the whole list and paste it into [Peek](/): Peek picks out every number. Signed in, it follows them all; signed out, one at a time.

Copy this into the assistant:

> Open Your Orders on Amazon in this browser. For each parcel on its way or just delivered, open Track package and note the carrier and the tracking number. Never use the order number (three groups of digits joined by dashes). If Peek is connected, add each parcel to Peek, named after its item. If not, list the numbers here, one per line. Tell me which parcels show no tracking number. On Amazon, only read: don't buy, cancel, return or change anything, and don't sign in for me.

Either way, the assistant only needs to read Amazon. It should never buy, cancel or return anything, and never sign in for you. If Amazon asks you to sign in, do it yourself in that window, then ask again. When a parcel has no tracking number, or Peek turns it down, Amazon tracks that one only in your account.

## What if my Amazon parcel is late or says delivered?

Who helps depends on who shipped it. The product page shows that below the Buy Now button. Amazon looks after its own orders and the ones it ships for sellers. A seller who ships its own orders answers delivery questions first.

:::steps
- Check Track package | It shows the latest update and, once the driver is 10 stops away, a map.
- Look around | Check the spot in your delivery instructions and the delivery photo, if the driver took one.
- Ask the carrier | If another carrier delivered it, Amazon's Carrier Contact Information page says how to reach it.
- Contact the seller | If a seller sold and shipped it, use Contact Seller from the order in Your Orders.
- Ask Amazon | For orders Amazon shipped, chat with or call Amazon Customer Service. For a seller's own orders that the seller doesn't sort out, the A-to-z Guarantee covers late delivery and the item's condition.
:::

More help: [delivered but not received](guide:delivered-not-received), [lost parcels](guide:lost-parcel) and [why tracking stops updating](guide:tracking-not-updating).

:::sources
- [Amazon Help: Tracking your package](https://www.amazon.com/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – Your Orders, Track package, all updates with the tracking numbers; the same page on amazon.co.uk, .fr, .de, .it, .es and .pl gives their labels
- [Amazon Help: Contact a Carrier](https://www.amazon.com/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – the carrier's name and tracking number under Track package, one carrier per package
- [Amazon Help: Missing Tracking Information](https://www.amazon.com/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – no number while the parcel is on its way to the carrier, details after the first scan
- [Amazon Help: About Deliveries Shipped with Amazon](https://www.amazon.com/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – orders shipped by Amazon Logistics show as shipped with Amazon
- [Amazon Help: Return a Gift](https://www.amazon.com/gp/help/customer/display.html?nodeId=GMTDJ6XPUMN7NWNB) – the 17-digit order number
- [About Amazon: How to track your packages](https://www.aboutamazon.com/news/retail/track-amazon-package) – Returns & Orders, the person icon in the app, Track package, map tracking
- [About Amazon UK: How to track an Amazon package](https://www.aboutamazon.co.uk/news/retail/how-to-track-an-amazon-package) – the same on Amazon.co.uk
- [About Amazon: 10 tips to protect your Amazon packages from porch pirates](https://www.aboutamazon.com/news/operations/amazon-porch-pirate-prevention-tips) – delivery instructions, delivery photos
- [About Amazon UK: How to change your delivery address on Amazon](https://www.aboutamazon.co.uk/news/retail/how-to-change-your-delivery-address-on-amazon) – who sells and ships it, below Buy Now; Carrier Contact Information
- [About Amazon UK: How to contact a seller on Amazon](https://www.aboutamazon.co.uk/news/small-businesses/how-to-contact-amazon-seller) – Contact Seller, A-to-z Guarantee
- [About Amazon: Contact Amazon Customer Service](https://www.aboutamazon.com/news/company-news/contact-amazon-customer-service) – chat or phone, help with late deliveries
- [About Amazon: Amazon's product guarantee](https://www.aboutamazon.com/news/policy-news-views/amazon-guarantee-backs-all-products-purchased-in-store) – A-to-z Guarantee: seller items, delivery and condition
- [About Amazon: How do Amazon packages get delivered](https://www.aboutamazon.com/news/operations/how-do-amazon-packages-get-delivered) – sort centres, U.S. Post Offices, Delivery Service Partners
- [About Amazon UK: How are Amazon parcels delivered in the UK?](https://www.aboutamazon.co.uk/news/operations/how-your-package-gets-from-amazons-warehouse-to-your-front-door) – DPD, Royal Mail and other partner carriers
- [About Amazon UK: Amazon CEO Andy Jassy's 2023 Letter to Shareholders](https://www.aboutamazon.co.uk/news/company-news/ceo-andy-jassys-2023-letter-to-shareholders) – Amazon Shipping, Amazon's delivery network for other businesses' parcels
- [Claude Help Center: Get started with Claude in Chrome](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – paid Claude plans, Chrome only, installing the extension
- [Claude Help Center: Use Claude in Chrome safely](https://support.claude.com/en/articles/12902428-use-claude-in-chrome-safely) – you stay responsible for purchases; permissions per site
- [ChatGPT: Chrome extension](https://learn.chatgpt.com/docs/chrome-extension) – set up from the ChatGPT desktop app; works on sites where you're signed in
- [Peek: Connect Peek to Claude, ChatGPT or Gemini](https://peektracker.com/assistants) – Peek's steps for Claude and ChatGPT
- [Peek on GitHub: the assistants' tools](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – what Peek tells an assistant about Amazon: Your Orders, Track package, no tracking number in Amazon's emails, only reading
- [Peek on GitHub: English wording](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/en.json) – the Amazon order number note, "Open my Amazon orders", "Check again", several numbers in one text
- [Universal Parcel Scraper: carrier data](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – Amazon number shapes and what Peek checks
:::
