---
title: POST Luxembourg tracking: numbers, statuses and contacts
description: Track a POST Luxembourg parcel or registered item: what the LU number looks like, what the statuses mean, PackUp, notices, customs fees and who to call.
slug: post-luxembourg-tracking
published: 2026-10-13
updated: 2026-10-26
---

POST Luxembourg is Luxembourg's national post, which also runs a telecom network and a bank. Track a parcel or registered item on its [Track and Trace page](https://www.post.lu/en/particuliers/colis-courrier/track-and-trace) (trackandtrace.lu leads there too), in the POST Parcel & Mail app or by pasting the number in the box above. The number comes from whoever sent the item: it's printed on the dispatch slip.

## POST Luxembourg tracking number

POST's numbers are 13 characters: [two letters, nine digits](guide:tracking-number-formats) and `LU`. Type the full number, digits and letters, as it appears on the dispatch slip.

:::anatomy CP 000000005 LU
- CP | Kind of item: RR registered, CP parcel, LX FollowMe label
- 000000005 | Nine digits, the last one a check digit
- LU | Posted in Luxembourg
:::

| Invented example | What it is |
| --- | --- |
| `RR000000005LU` | Registered item |
| `CP000000005LU` | Parcel |
| `LX000000005LU` | FollowMe label, a tracked option for items up to 2 kg that aren't signed for |

Only some items can be tracked: registered mail, parcels and FollowMe items. An ordinary letter has no number.

A parcel coming to Luxembourg from abroad keeps the number its own post gave it, ending in that country's letters (`…CN`, `…FR`, `…DE`). That number names the foreign post, not POST, even when POST delivers it. POST's tracking service accepts it as it is.

No number from the shop? See [where to find a tracking number](guide:find-tracking-number).

## What POST Luxembourg tracking statuses mean

POST's statuses are the [international postal (UPU) event names](guide:tracking-statuses), given here in English, as Peek shows them. A scan carries a day but no time, sometimes with the post office or centre that recorded it. In the order an item meets them:

| POST Luxembourg's status | What it means for you |
| --- | --- |
| `Entry into the postal network` | The item was posted, at a post office or Espace POST, or with the post abroad. |
| `Departure from outward office of exchange` | It left the sending country for abroad. |
| `Arrival at inward office of exchange` | It reached the destination country's exchange office. |
| `Departure from inward office of exchange` | It left that office towards delivery. |
| `Arrival at sorting centre` | Being sorted. |
| `Arrival at delivery facility` | At the office that will deliver it; it can still go out from there. |
| `Item out for delivery` | With the postal worker today. |
| `Final delivery` | Delivered. |

The "office of exchange" lines aren't local post offices: they're the points where one country's post hands mail to another's. On a parcel coming to you, the "inward" lines are POST Luxembourg's side. On an item you sent abroad, they're relayed from the destination post. `Final delivery` just means delivered.

## POST Luxembourg delivery, notices and PackUp

When the postal worker rings with a parcel or registered item and you're out, a delivery notice goes in your letterbox. POST keeps a parcel or registered letter for 15 days at a sales outlet (seven days for legal documents). POST may also leave a notice without delivering when a parcel is too heavy, or when customs duties or taxes are due and nobody was at home.

PackUp is POST's free delivery service in Luxembourg, set up on MyPost (mypost.lu):

- **PackUp Home:** the parcel is left at a place you chose: front door, garbage room, terrace, garden shed, parcel box, blue garbage bin, garage or carport, or a neighbour. Registered items can't be delivered this way.
- **PackUp 24/24:** a parcel station. The PackUp page says you collect 24 hours a day; POST's PackUp brochure says most stations are open round the clock and Indoor ones keep varying hours. The parcel stays there seven days. To find one, use "Find a sales outlet" and tick "PackUp Station".
- **PackUp Point:** a post office, petrol station or shop, which keeps the parcel up to 15 days.
- **PackUp Import:** an address in France or Germany for shops that don't ship to Luxembourg, for €6.45 including VAT per parcel.

POST emails you once a parcel is delivered with the option you chose. PackUp only works when the shop uses POST Courrier or a carrier that hands its parcels to POST. Some carriers don't deliver to stations: they come to your home and, if you're out, leave a notice: go to the sales outlet shown on its back or, with some carriers, contact them. If your chosen station is full, the carrier takes the parcel elsewhere and tells you.

The free POST Parcel & Mail app also lets you choose where a parcel goes while you're away.

## POST Luxembourg parcel delayed, lost or at customs

**Delivery times:** POST says parcels within Luxembourg generally take two or three days. Abroad there are no guaranteed times, unless the item goes express with [DHL](carrier:dhl-express). If the status hasn't moved for days, see [tracking that isn't updating](guide:tracking-not-updating).

**Customs:** every parcel with goods from outside the EU goes through customs, and Luxembourg VAT, customs duty, POST handling fees and excise duties can be due on arrival. On POST's customs page:

- Since 1 July 2026, goods bought online from outside the EU worth €150 or less pay a customs duty of €3 per type of item in the parcel, even if VAT was already paid. A T-shirt, jeans and shoes make €9.
- When POST does the customs formalities, its handling fee is €6 for a parcel worth €22 or less and €18 above €22 up to €150.
- You pay on delivery or when picking up the parcel at a POST point of sale.

These amounts can change, so check POST's page. More in [parcels held at customs](guide:customs).

**Lost or damaged:** POST parcels include basic insurance, paid for the actual damage within limits that depend on the destination country, from €50 to €530; senders can add SecurPack. POST's pages don't say who files the claim or by when, so start with the shop: it sent the parcel and holds the contract. To complain to POST, use its [contact form](https://www.post.lu/particuliers/infos-aide/contactez-nous#/form). In a dispute over your own contract with POST, if a written complaint gets no answer in reasonable time, or one you don't accept, Luxembourg's regulator, the ILR, offers free mediation.

## POST Luxembourg contact number

POST's channels for post and parcels, as its contact page listed them on 13 October 2026:

- **Phone:** 8002 8004, free from Luxembourg, or +352 2424 8004 from abroad, Monday to Friday 8:00 to 17:00. Some of POST's help pages give these numbers as open 24/7, hours the contact page gives only to telecom.
- **Contact form:** on the [contact page](https://www.post.lu/en/particuliers/infos-aide/contactez-nous), with your tracking number in the message.
- **In person:** Espace POST shops and post offices, found with "Find a sales outlet".

## Tracking POST Luxembourg parcels with Peek

For a number ending `LU` with a valid check digit, [Peek](/) asks POST Luxembourg and [Chronopost](carrier:chronopost) at once and shows "POST Luxembourg has this parcel." when POST knows the number, or "Not found at … yet. Peek will keep checking after you add it." With a wrong check digit POST isn't asked, and its lookup refuses the number even if you pick POST with "Change": check the number. A post.lu Track and Trace link or a trackandtrace.lu link with the number always names POST Luxembourg.

A parcel from abroad stays with its foreign post, but POST's lookup takes it: pick "POST Luxembourg" with "Change", or paste a post.lu link. Peek also asks POST by itself when the post abroad names POST or, for a postal number with a valid check digit, reports Luxembourg as the destination, or reports none or doesn't answer while Peek places you in Luxembourg. It switches to POST, keeping the history from abroad, once POST shows a dated scan at least as recent as the foreign post's latest.

POST's scans carry a day but no time: Peek lists POST's own history, each scan under its day, in POST's order. Statuses keep POST's English wording, and Peek can notify you when the status changes. There's no expected delivery day, no pickup point and no weight.

## Questions about POST Luxembourg tracking

### How long does POST Luxembourg take to deliver?

Within Luxembourg, parcels generally take two or three days. For international items POST guarantees no times, except express items sent with DHL.

### Is the POST Luxembourg helpline open 24 hours?

POST's pages disagree. Its contact page gives the post and parcels line Monday to Friday, 8:00 to 17:00, and round-the-clock hours to telecom only; some of its parcel help pages show the same numbers as open 24/7.

### Can I track an ordinary letter with POST Luxembourg?

No. Only registered mail, parcels and items with a FollowMe label have a tracking number.

### Can I track an international parcel with POST Luxembourg?

Yes, if it has a tracking number. For an item you sent abroad, POST's tracking also shows steps the destination post reports, such as `Arrival at inward office of exchange`.

:::sources
- [POST Luxembourg: Track and Trace](https://www.post.lu/en/particuliers/colis-courrier/track-and-trace) – tracking page
- [POST Luxembourg: How can I track my items? (parcels)](https://www.post.lu/en/particuliers/support/colis/colis/securiser-et-suivre/suivre-mes-envois-colis) – trackandtrace.lu, RR, CP and LX shapes, phone
- [POST Luxembourg: What is Track and Trace?](https://www.post.lu/en/particuliers/support/colis/colis/securiser-et-suivre/track-and-trace-cest-quoi) – full number on the dispatch slip, trackable items
- [POST Luxembourg: How can I track my items? (mail)](https://www.post.lu/en/particuliers/support/courrier/courrier/suivre/comment-suivre-mes-envois) – tracking only for registered mail and parcels
- [POST Luxembourg: What is FollowMe?](https://www.post.lu/en/particuliers/support/courrier/courrier/suivre/followme-c-est-quoi) – FollowMe, no signature
- [POST Luxembourg: FollowMe, pour quels envois ?](https://www.post.lu/fr/particuliers/support/courrier/courrier/suivre/followme-pour-quels-envois) – FollowMe up to 2 kg
- [POST Luxembourg: Track and Trace service](https://api.post.lu/services/trackandtrace-api/items) – status wordings, day-only scans, offices
- [POST Luxembourg: When might I be notified?](https://www.post.lu/en/particuliers/support/courrier/courrier/absence/quand-puis-je-etre-avise) – delivery notice
- [POST Luxembourg: I received a delivery notice while I was out](https://www.post.lu/en/particuliers/support/courrier/courrier/absence/avise-dans-ma-boite-aux-lettres-pendant-mes-vacances) – 15 days at a sales outlet
- [POST Luxembourg: Your parcels delivered with PackUp](https://www.post.lu/en/particuliers/colis-courrier/packup) – PackUp Home places, stations, 24 hours a day, seven days, email, MyPost
- [POST Luxembourg: PackUp](https://cdn.post.lu/downloads/documentations/courrier/BrochurePackup.pdf) – brochure: most stations open 24/7, Indoor stations' hours
- [POST Luxembourg: What is PackUp?](https://www.post.lu/en/particuliers/support/colis/colis/livraison/packup-qu-est-ce-que-c-est) – four PackUp services
- [POST Luxembourg: How long can a parcel be kept at a PackUp Point?](https://www.post.lu/en/particuliers/support/colis/colis/livraison/combien-de-temps-un-colis-conserve-dans-un-packup-point) – 15 days
- [POST Luxembourg: How much does it cost to use the PackUp service?](https://www.post.lu/en/particuliers/support/colis/colis/livraison/cout-service-packup) – free in Luxembourg, Import €6.45
- [POST Luxembourg: I'm a PackUp client but my parcel hasn't arrived](https://www.post.lu/en/particuliers/support/colis/colis/livraison/client-packup-colis-avise) – other carriers, heavy and customs parcels
- [POST Luxembourg: I ordered on Amazon and gave my PackUp address](https://www.post.lu/en/particuliers/support/colis/colis/livraison/amazon-et-adresse-packup) – full stations
- [POST Luxembourg: Can I use PackUp with any e-commerce website?](https://www.post.lu/en/particuliers/support/colis/colis/livraison/utiliser-packup-avec-n-importe-quel-site-marchand) – POST Courrier network
- [POST Luxembourg: Where can I find a PackUp station?](https://www.post.lu/en/particuliers/support/colis/colis/livraison/trouver-une-station-packup) – Find a sales outlet
- [POST Luxembourg: POST Parcel & Mail mobile app](https://www.post.lu/en/particuliers/colis-courrier/application-colis-et-courrier) – app features
- [POST Luxembourg: What are the delivery times for parcels?](https://www.post.lu/en/particuliers/support/colis/colis/livraison/delais-de-livraison-pour-les-colis) – two or three days, no international guarantee
- [POST Luxembourg: Customs duties & formalities](https://www.post.lu/en/particuliers/colis-courrier/formalites-douanieres) – amounts due, €3 per item type, handling fees, payment
- [POST Luxembourg: What compensation can I claim in the event of loss or damage?](https://www.post.lu/en/particuliers/support/colis/colis/securiser-et-suivre/indemnites-perte-ou-endommagement) – €50 to €530, SecurPack
- [POST Luxembourg: Is my parcel insured?](https://www.post.lu/en/particuliers/support/colis/colis/securiser-et-suivre/mon-colis-est-il-assure) – basic insurance
- [POST Luxembourg: Contact us](https://www.post.lu/en/particuliers/infos-aide/contactez-nous) – phone numbers, hours, form
- [POST Luxembourg: Comment savoir où se trouve mon colis ?](https://www.post.lu/fr/particuliers/support/colis/colis/securiser-et-suivre/comment-savoir-ou-se-trouve-mon-colis) – same numbers shown as 24/7
- [POST Luxembourg: How can I track my complaint?](https://www.post.lu/en/particuliers/support/je-demenage/demenagement-de-vos-services-post/suggestions/reclamation-comment-suivre-son-traitement) – contact form for complaints
- [Guichet.lu: Requesting mediation services from the ILR](https://guichet.public.lu/en/citoyens/justice/voies-recours-reglement-litiges/resolution-litige-amiable/mediation-ilr.html) – free mediation, its conditions
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
