---
title: Delivengo tracking: follow a La Poste parcel from France
description: Track a Delivengo parcel sent from France: the number, La Poste's tracking messages, who delivers it in your country and who to ask when tracking stops.
slug: delivengo-tracking
published: 2026-10-12
updated: 2026-10-28
---

Delivengo is [La Poste](carrier:la-poste)'s export service for small international parcels. If a French shop chose it, your parcel leaves France with La Poste, and the post in your own country does the last mile. You track it on La Poste's tracking, on laposte.fr, with the number the shop gives you, or paste it in the box above.

## Your Delivengo tracking number

Delivengo numbers are international postal numbers: two letters, nine digits and `FR`, such as `LD000000005FR` (invented).

**Where it comes from.** The shop gives it to you, usually in its shipping email. The MyDelivengo site, mydelivengo.laposte.fr, is the shop's account area, not a place for you to look it up. If you can't find the number, see [how to find your tracking number](guide:find-tracking-number).

**Numbers that look alike** (all invented):

| Shape | Whose it is |
| --- | --- |
| `LD000000005FR` | La Poste: Delivengo, Colissimo and other French postal items share this shape. |
| `LD000000005GB` | The same shape with another country's letters belongs to that country's post (here [Royal Mail](carrier:royal-mail)), not to Delivengo. |

The last two letters say which post issued the number. Nothing in a `…FR` number tells Delivengo apart from any other La Poste item, and that doesn't matter: the tracking is the same. The numbers carry a check digit, so one wrong digit makes the number invalid, and Peek doesn't recognise it. Copy the number rather than typing it.

## Delivengo suivi: what the tracking says

La Poste's tracking answers Delivengo numbers like any other La Poste item. Peek puts each of its scans in a stage; here is what La Poste's scan behind each of these stages means:

| Stage in Peek | What La Poste's scan means |
| --- | --- |
| `Label created` | The shipment is announced: the shop has made the label, but La Poste doesn't have the parcel yet. |
| `Received by carrier` | La Poste has taken the parcel in charge. |
| `In transit` | The parcel is moving: through France, ready to leave France, or arrived in or moving through your country. |
| `At customs` | The parcel has entered customs. Once it is released, it goes back to `In transit`. |
| `Delivered` | The parcel has been delivered. If it isn't there, see [delivered but not received](guide:delivered-not-received). |
| `Needs attention` | Something went wrong, such as damage, a refusal or a wrong address, or the parcel is being sent back to the shop. La Poste's scan can give the reason: an incorrect address, a pickup deadline that passed or import documents that weren't in order. |
| `Returned to sender` | The parcel is back with the shop. |

After export, the post in your country does the last mile, and La Poste's history can go quiet: the last scan you see may be the parcel leaving France or arriving in your country.

## Delivery and missed deliveries

**Who delivers.** Your own country's postal service does the last mile, not La Poste.

**If you're out.** Your local post's rules apply, not La Poste's: how it lets you know, how long it keeps the parcel and whether it tries again. See [missed delivery](guide:missed-delivery).

**Customs.** Within the EU, from France to Ireland for example, there are no customs duties to pay: the EU says none are due when goods move from one EU country to another. Outside the EU, in the UK, the US, Canada or Australia, your country's rules apply; see [parcel held at customs](guide:customs).

## Delivengo parcel late, stuck or lost

The shop sent the parcel from its Delivengo account, so start with the shop.

**No update for days.** After export, La Poste's history can go quiet while your local post does the last mile. Peek can also follow some countries' posts (see below). If nothing moves, ask the shop. [Tracking that isn't updating](guide:tracking-not-updating) covers the usual reasons.

**Lost or damaged.** Ask the shop for a new parcel or a refund. If you live in the EU, Your Europe says the trader is responsible for any damage from dispatch until you receive the goods, and that if they haven't arrived within 30 days, or the agreed time, you should remind the trader and give it a reasonable extra time; if it still doesn't deliver, you can end the contract and be refunded. See [lost parcel](guide:lost-parcel).

**Returned.** A parcel that can't be delivered goes back to the shop: Peek shows `Needs attention` while it travels back, then `Returned to sender`. Ask the shop whether it will send it again. See [returned to sender](guide:returned-to-sender).

## Delivengo contact

- **The shop first.** It sent the parcel and has the Delivengo account.
- **La Poste, for a complaint.** Arcep, France's postal regulator, says a complaint can reach La Poste by phone on 36 31 (Monday to Friday 8:30 am to 7 pm, Saturday 8:30 am to 1 pm) or +33 810 821 821 (Monday to Friday 8 am to 7 pm), on its website, by letter or at a postal contact point. Arcep notes that the sender's and the recipient's rights are not quite the same, and doesn't say whether these channels handle Delivengo parcels.
- **Your local post** once the parcel is in your country, with the same number.

## Tracking a Delivengo parcel with Peek

[Peek](/) has no Delivengo connection of its own: it tracks these parcels through La Poste's tracking, like any La Poste or Colissimo item. Paste the `…FR` number and Peek names "La Poste / Colissimo" at once; you can pick "Delivengo" with "Change", and the tracking is the same. A laposte.fr tracking link (also from mydelivengo.laposte.fr) works too.

Peek shows what La Poste publishes: the status, the history with the country of each scan and the expected delivery when La Poste gives one. It leaves out your name and address. Statuses appear in La Poste's French, except a few common ones, such as delivered, which are shown in your language.

When La Poste gives the destination country, Peek can also ask the post it follows there, and switches to it once it shows newer progress. It does so for every EU country, the UK, Switzerland, Norway, the US, Canada, Australia and Japan, among others. La Poste's history can still go quiet after export, so the last leg doesn't always show.

Peek checks every 10 minutes by day, every 2 minutes once the parcel is out for delivery, and every hour or two overnight, and its notifications can cover every scan, important steps only or delivery day only.

## Questions about Delivengo tracking

### Is Delivengo the same as Colissimo?

No, though both are La Poste's and they share its tracking. Delivengo is La Poste's export service for small international parcels.

### Why has my Delivengo tracking stopped after France?

Once the parcel leaves France, the post in your country does the last mile, and La Poste's history can go quiet. The parcel can still be on its way. Peek also asks some countries' posts (see above); if nothing moves, ask the shop.

### Can I track a Delivengo parcel to the USA or Canada?

Yes, with the same `…FR` number on La Poste's tracking. Once La Poste gives the destination country, Peek also asks [USPS](carrier:usps) or [Canada Post](carrier:canada-post), and switches to it when it shows newer progress.

:::sources
- [Universal Parcel Scraper: Delivengo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delivengo) – La Poste's export service, last mile by the destination post, quiet history after export, account area, no number rule of its own
- [Universal Parcel Scraper: La Poste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – shared tracking, number shape and check digit, what each scan means
- [European Union: EU customs union in action](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_en) – no customs duties between EU countries
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – trader responsible for damage until delivery, missing delivery
- [Arcep: La résolution des litiges postaux](https://www.arcep.fr/mes-demarches-et-services/consommateurs/fiches-pratiques/la-resolution-des-litiges-postaux.html) – complaints to La Poste: phone lines, hours, website, letter, postal contact point, sender and recipient rights
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
