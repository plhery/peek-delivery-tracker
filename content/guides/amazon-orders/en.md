---
title: Amazon order tracking: Amazon, sellers and carriers
description: Who ships your Amazon order, which carrier has it, why the tracking number changes or stays blank, and when to ask the seller or claim the A-to-z Guarantee.
slug: amazon-order-tracking
picture: A phone showing an order's progress, two vans passing a parcel between them, and Pip watching.
published: 2026-10-16
updated: 2026-10-27
---

To track an Amazon order, open Your Orders (Returns & Orders at the top right of the website, or the person icon in the app), pick the order and select Track Package. Who helps when it goes wrong depends on who shipped it. Amazon looks after its own orders and Fulfilled by Amazon ones, while a seller who ships its own orders handles their customer service.

## Who ships my Amazon order: Amazon or a third-party seller?

Look below the Buy Now button on the product page: it shows who sells the item and who ships it.

| Who sells and ships it | What it means | Who helps with delivery |
| --- | --- | --- |
| Sold and shipped by Amazon | Amazon's own stock | Amazon |
| Sold by a seller, shipped by Amazon | Fulfilled by Amazon: the seller's stock, packed and sent from an Amazon warehouse | Amazon |
| Sold and shipped by the same seller | The seller ships it itself | The seller first |

Amazon.com.au makes the same split: sold and fulfilled by Amazon AU, sold by a seller and fulfilled by Amazon AU, or sold and fulfilled by a seller.

One more case on Amazon.com.au: the **Amazon Global Store**, which sells products from Amazon's stores in the UK, the US, Germany and Japan. Orders shipped from overseas can take longer than domestic ones, as can orders shipped by sellers. More in [parcels held at customs](guide:customs).

On Amazon.co.uk, if a seller's listing is eligible for Prime, you contact Amazon Customer Service instead of the seller.

## Which carrier delivers my Amazon parcel?

Amazon delivers many orders itself. That's [Amazon Logistics](carrier:amazon-logistics), its own delivery network: Delivery Service Partners, small firms with their own drivers, and independent Amazon Flex drivers. Other parcels leave Amazon's sort centres with a partner carrier. Amazon's own sites name these:

| Country | Site | Who Amazon says delivers |
| --- | --- | --- |
| US | amazon.com | Amazon's Delivery Service Partners and Flex drivers, and [USPS](carrier:usps) for parcels Amazon hands to a post office |
| UK | amazon.co.uk | Amazon, and couriers such as [DPD](carrier:dpd-uk), [Royal Mail](carrier:royal-mail) and [Evri](carrier:evri) (formerly Hermes) |
| Ireland | amazon.ie | Amazon, seven days a week, from delivery stations in Rathcoole and Ballycoolin |
| Canada | amazon.ca | Amazon, through local Delivery Service Partners |
| Australia | amazon.com.au | Amazon's own network, [Australia Post](carrier:australia-post) and other third-party carriers |

A seller who ships its own orders chooses how they travel, so its parcels can come with another carrier. Where exactly the number sits on each shop: [find your tracking number](guide:find-tracking-number).

## What does an Amazon tracking number look like?

There are two kinds, and an order number that is neither.

Amazon doesn't publish the format of its own numbers. These are the shapes Peek recognises as Amazon's, here with made-up digits:

:::anatomy TBA 123456789012
- TBA | Amazon letters. `TBC` and `TBM` work the same way
- 123456789012 | Twelve digits
:::

| Shape (made up) | What it is |
| --- | --- |
| `TBA123456789012` | Amazon: `TBA`, `TBC` or `TBM` and 12 digits |
| `UK1234567890` | Amazon in Europe: a country code (`UK`, `GB`, `IE` and others) and 10 digits |
| `123-1234567-1234567` | Your Amazon order number: 3, 7 and 7 digits. Not a tracking number |
| Anything else | The carrier's own number, such as Royal Mail's or USPS's |

The letters also tell Peek which Amazon store holds the order: amazon.ca for `TBC`, amazon.com.mx for `TBM`, and amazon.com for `TBA`, or amazon.ca when the parcel is going to Canada. A carrier's number works on that carrier's site, as in [tracking number formats](guide:tracking-number-formats). Not sure which you have? Type it here:

:::checker
:::

## Why did my Amazon tracking number change, or why are there two?

Usually one of these:

- **A partner carrier takes over.** Amazon passes some parcels to a partner for the last stretch: a post office in the US, couriers such as DPD or Royal Mail in the UK, third-party carriers in Australia. The partner can give the parcel a number of its own.
- **The seller uses its own carrier.** Then the number is that carrier's, not Amazon's.

:::journey
- warehouse | Amazon or the seller | Amazon packs its own and Fulfilled by Amazon orders; a seller may ship its own
- label | Labelled | Weighed and labelled, then sent on to a sort centre
- handover | Sort centre or partner | Sorted by destination; some parcels go on with a partner carrier
- truck | Out for delivery | From 10 stops away, Amazon's map shows the driver getting close
- home | You | At your door, an Amazon Locker or another pickup point
:::

When the first carrier names the partner taking over, [Peek](/) keeps the first history, follows the partner once its own tracking shows fresh progress, and shows the partner's name and number on the card.

## Why is there no tracking for my Amazon order?

Who holds the tracking depends on who shipped the order:

- **Amazon shipped it.** Select Track Package in Your Orders or in the shipment confirmation email. Once the parcel is 10 stops away, Amazon sends a push notification if you've opted into delivery alerts, and Amazon Map Tracking shows the driver on a map.
- **A seller shipped it.** The seller handles it: ask them with Contact Seller, from the order in Your Orders.
- **It's a gift.** Whoever ordered it can send you the tracking: on the Progress Tracker page, Share Tracking sends a link by text, email or WhatsApp.

When tracking shows nothing new for days: [why tracking stops updating](guide:tracking-not-updating).

## What to do if an Amazon order is late or says delivered

If the order is late, or shows as delivered but you don't have it, go in this order:

:::steps
- Check the tracking | Track Package in Your Orders opens the Progress Tracker, with a map once the driver is 10 stops away.
- Look around | Check the spot named in your delivery instructions and, if the driver took one, the delivery photo.
- Ask the carrier | If another carrier delivered it, Amazon's Carrier Contact Information page says how to reach it.
- Contact the seller | If a seller sold and shipped it, use Contact Seller from the order in Your Orders. On Amazon.co.uk, sellers should reply within two business days.
- Ask Amazon | Orders Amazon shipped: Amazon Customer Service, by chat or phone from the Customer Service page. Seller orders: the A-to-z Guarantee.
:::

For more on each case: [delivered but not received](guide:delivered-not-received) and [lost parcels](guide:lost-parcel).

### When can I claim the A-to-z Guarantee?

It covers items a third-party seller sold and shipped itself: on-time delivery and the item's condition. Try to settle it with the seller first. If you can't, ask Amazon for a refund, and Amazon decides whether you're eligible.

| Site | Seller replies within | When you can claim |
| --- | --- | --- |
| amazon.co.uk | Two business days | You want a refund and the seller hasn't answered within 48 hours: most seller orders are covered |
| amazon.com.au | Two business days, for a return request | No answer by then: you can ask for a refund under the guarantee |

## Can I track an Amazon parcel outside Amazon?

It depends on the number. Paste it into [Peek](/):

- **A carrier's number** (Royal Mail, USPS, Canada Post, Australia Post…) is tracked like any other parcel.
- **An Amazon number**, or a track.amazon link: Peek first asks Amazon's public tracker. If Amazon answers, the parcel travels with [Amazon Shipping](carrier:amazon-shipping), Amazon's delivery service for other retailers and Amazon sellers, or with Multi-Channel Fulfilment, where Amazon packs orders placed on other sites, often in plain packaging. Peek then tracks it automatically.
- Otherwise it's Amazon Logistics delivering an Amazon order, and Amazon shows that only in your account. Peek says so and offers "Open my Amazon orders", which opens your order history on the Amazon site the number points to. Peek never asks for your Amazon login.
- **An order number** gets "That looks like an Amazon order number". Use the tracking number from Track Package or the shipment confirmation email instead.

:::sources
- [About Amazon: How to track your packages](https://www.aboutamazon.com/news/retail/track-amazon-package) – Returns & Orders, Your Orders, Track Package, map tracking, Share Tracking
- [About Amazon UK: How to track an Amazon package](https://www.aboutamazon.co.uk/news/retail/how-to-track-an-amazon-package) – the same on Amazon.co.uk
- [About Amazon: 10 tips to protect your Amazon packages from porch pirates](https://www.aboutamazon.com/news/operations/amazon-porch-pirate-prevention-tips) – Track Package from the shipment email, delivery instructions, delivery photos
- [About Amazon: Fulfillment by Amazon](https://www.aboutamazon.com/news/small-business/fulfillment-by-amazon-how-improving-delivery-fueled-independent-seller-growth-and-success) – Amazon delivers and supports sellers' FBA items; sellers' other ways to ship
- [About Amazon UK: How to change your delivery address on Amazon](https://www.aboutamazon.co.uk/news/retail/how-to-change-your-delivery-address-on-amazon) – who sells and ships it, below Buy Now; Carrier Contact Information
- [About Amazon Australia: How to change your delivery address](https://www.aboutamazon.com.au/news/retail/how-to-change-your-delivery-address-on-desktop-and-in-the-amazon-app) – the same on Amazon.com.au
- [About Amazon Australia: How to cancel Amazon orders](https://www.aboutamazon.com.au/news/shopping/how-to-cancel-your-order-on-amazon-australia) – sold and fulfilled by Amazon AU, by a seller, or both; Contact Seller (2023)
- [About Amazon UK: How to contact a seller on Amazon](https://www.aboutamazon.co.uk/news/small-businesses/how-to-contact-amazon-seller) – sellers' customer service, Prime listings, Contact Seller, two business days, 48 hours, A-to-z Guarantee
- [About Amazon: Contact Amazon Customer Service](https://www.aboutamazon.com/news/company-news/contact-amazon-customer-service) – chat or phone, help with late deliveries
- [About Amazon UK: How to contact Amazon UK Customer Service](https://www.aboutamazon.co.uk/news/retail/how-to-contact-amazon-uk-customer-service) – chat and phone from the Customer Service page
- [About Amazon: How do Amazon packages get delivered](https://www.aboutamazon.com/news/operations/how-do-amazon-packages-get-delivered) – sort centres, U.S. Post Offices, Delivery Service Partners, Flex, Lockers
- [About Amazon UK: How are Amazon parcels delivered in the UK?](https://www.aboutamazon.co.uk/news/operations/how-your-package-gets-from-amazons-warehouse-to-your-front-door) – labelling, sortation centres, Hermes, DPD, Royal Mail, Delivery Service Partners
- [About Amazon EU: Amazon's €25 billion commitment to Ireland](https://www.aboutamazon.eu/news/job-creation-and-investment/amazons-25-billion-commitment-to-ireland-creating-jobs-driving-growth-and-building-infrastructure) – Irish delivery stations, seven days a week
- [About Amazon Canada: New Delivery Service Partner Hubs in Lethbridge and Owen Sound](https://www.aboutamazon.ca/news/operations/amazon-announces-new-delivery-service-partner-hubs-in-lethbridge-and-owen-sound) – Delivery Service Partners in Canada
- [About Amazon Australia: Amazon invests in its last mile network](https://www.aboutamazon.com.au/news/innovation/amazon-continues-to-invest-in-its-last-mile-network-to-enable-faster-deliveries-to-customers-in-australia) – Delivery Service Partners, Flex, third-party carriers
- [About Amazon Australia: Inside the Western Sydney fulfilment centre](https://www.aboutamazon.com.au/news/workplace/take-a-behind-the-scenes-look-at-amazon-australias-robotics-fulfilment-centre-in-western-sydney) – Australia Post or Amazon's own network (2023)
- [About Amazon Australia: J-Beauty brands now on Amazon](https://www.aboutamazon.com.au/news/retail/big-in-japan-and-now-australia-hundreds-of-j-beauty-brands-now-available-on-amazon-com-au) – Global Stores from the UK, US, Germany and Japan (2023)
- [About Amazon Australia: How to get your Amazon order by Christmas](https://www.aboutamazon.com.au/news/shopping/how-to-get-your-amazon-order-by-christmas-5-tips-for-hassle-free-holidays) – orders from overseas or sellers can take longer (2023)
- [About Amazon: Amazon's product guarantee](https://www.aboutamazon.com/news/policy-news-views/amazon-guarantee-backs-all-products-purchased-in-store) – A-to-z Guarantee: seller items, delivery and condition, refund
- [About Amazon Canada: A-to-z Guarantee in Canada](https://www.aboutamazon.ca/news/customer-trust/amazon-expands-a-to-z-guarantee-in-canada-to-include-property-damage-and-personal-injury-claims) – the same in Canada
- [About Amazon Australia: 12 tips for shopping on Amazon.com.au](https://www.aboutamazon.com.au/news/retail/12-tips-to-get-the-most-out-of-shopping-on-amazon-com-au) – A-to-z Guarantee in Australia (2022)
- [About Amazon Australia: How to return an order you can't cancel](https://www.aboutamazon.com.au/news/shopping/how-to-return-an-order-if-you-cant-cancel-it-on-amazon-australia) – two business days, then the A-to-z Guarantee (2023)
- [Amazon Shipping UK](https://shipping.amazon.co.uk/) – delivery for retailers and Amazon selling partners
- [Amazon UK: Multi-Channel Fulfilment](https://sell.amazon.co.uk/fulfilment-by-amazon/fba-multi-channel) – orders from other sales channels, unbranded packaging
- [Universal Parcel Scraper: carrier data](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – Amazon number shapes, stores per prefix, Evri's former name, what Peek checks
:::
