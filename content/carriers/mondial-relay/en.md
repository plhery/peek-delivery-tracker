---
title: Mondial Relay tracking: statuses in English and pickup
description: Track a Mondial Relay parcel: where the 8-digit number is, what the French statuses mean in English, how long a Point Relais keeps it and who to contact.
slug: mondial-relay-tracking
published: 2026-10-11
updated: 2026-10-27
---

Mondial Relay is a French parcel carrier in the InPost group that delivers to Points Relais (local shops acting as pickup points), lockers and homes. Track a parcel on [Mondial Relay's tracking page](https://www.mondialrelay.fr/suivi-de-colis/) with the 8-digit shipment number and the recipient's postcode, or paste the number in the box above. It's on the label and in the emails from Mondial Relay and the sender.

## Mondial Relay tracking number: where to find it

Mondial Relay calls it the "n° d'expédition": 8 digits, at the top of the label in the "Information" box and in its tracking email. No number? Check the sender's confirmation email, then ask the sender, or see [how to find your tracking number](guide:find-tracking-number). Its Belgian and Dutch sites also take 10 and 12 digits.

| Number (invented) | What it is |
| --- | --- |
| `12345678` | The shipment number. Also fits [Colisweb](carrier:colisweb) and Heppner. |
| `0012345678` | 10 digits: a brand code, then the shipment number. Also fits [DHL](carrier:dhl-express), Relais Colis and others. |
| `001234567802` | 12 digits: the same plus the parcel's sequence. Also fits [DPD France](carrier:dpd-fr), [Colis Privé](carrier:colis-prive), [BRT](carrier:brt) and others. |
| `00123456780101700000000000` | 26 digits: the label's barcode, Mondial Relay's alone, with the shipment number in digits 3 to 10. |

If the shop's email names another carrier, [the number may be theirs](guide:tracking-number-formats).

## Mondial Relay tracking statuses in English

Mondial Relay's tracking is in French (or Dutch on its Belgian site), with no English version. The usual lines, in order:

| Mondial Relay says | What it means for you |
| --- | --- |
| `Colis en préparation chez l'expéditeur` | Label created; Mondial Relay doesn't have it yet. |
| `Prise en charge` | The sender has handed it in at a Point Relais or locker. |
| `Prise en charge de votre colis sur notre site logistique`, `Colis expédié depuis le site` | At, then leaving, the logistics site named. |
| `Colis en route vers le point de livraison` | Heading for your Point Relais, locker or address. |
| `Sollicitation Client pour replace colis` | Your relay can't take it: Mondial Relay emails you to pick another. |
| `Relais de substitution choisi` | Another relay will hold it: check its address. |
| `En cours de livraison` | Out for delivery to your home. |
| `Disponible dans votre Point Relais`, `Disponible dans votre locker` | Ready for pickup. |
| `Retiré par le destinataire`, `Colis livré au destinataire` | Picked up by you, or delivered to your home. |
| `Retour à l'expéditeur` | Going back to the sender. |

After `Prise en charge`, nothing can be changed. On a relay delivery, `En cours de livraison` is dated when the parcel heads for the relay: wait for `Disponible…`. `Échec de livraison` means a home delivery failed, and `Votre colis a été livré à l'enseigne.` that a return reached the shop.

## Mondial Relay parcel shops, lockers and missed deliveries

**Picking it up.** At a Point Relais, show ID or give the pickup code Mondial Relay sent you by email or SMS; at a locker, type that 6-digit code or scan the QR code. Someone else can pick it up at a French Point Relais with their original ID and yours, or at a locker with the code.

**How long it waits.** In France, 5 calendar days, holidays included, with no extension: a parcel arriving on Wednesday waits until Monday, then goes back to the sender. Mondial Relay's table gives Belgium and Luxembourg 5 days, Spain and Portugal 8. InPost's pages say 5 for domestic parcels there, though one Portuguese page says 8.

**Changing the relay.** Not possible once the label exists. If your relay is full or closed, Mondial Relay moves the parcel to a nearby point or emails you to choose one; with no reply, it chooses.

**Missed home delivery.** The driver uses your letterbox or a neighbour, or contacts you to arrange delivery or a nearby Mondial Relay point. After 3 failed attempts, or a refusal, it goes back to the sender.

## Mondial Relay parcel lost, stuck or damaged

The sender, shop or private seller, holds the contract, so loss and damage go through them.

- **Stuck:** if the tracking hasn't moved for 5 days, Mondial Relay France asks you to use its contact form. See [why tracking stops updating](guide:tracking-not-updating).
- **Lost:** contact the sender. For Vinted, Leboncoin and Rakuten parcels, the platform compensates after the investigation, usually completed within 30 days. A status of `avarie` means Mondial Relay's investigation is over and leads to a refund; it isn't the everyday "damaged".
- **Damaged:** refuse it at the Point Relais, as Mondial Relay rejects claims made after you've taken it.

## Mondial Relay customer service and contact number

As listed on 10 October 2026; none is in English.

- **France:** Chatbot Timo around the clock (it can give your pickup code), a form on [Nous contacter](https://www.mondialrelay.fr/nous-contacter/) and 09 69 32 23 32 at local-call rates, Monday to Friday 8:00–19:00, Saturday 8:00–18:00.
- **Belgium:** +32 2 340 06 10 at the same hours, or customerservice@mondialrelay.be.
- **Spain and Portugal:** now InPost, on 900 900 137 (Spain, free) and 211 245 624 (Portugal).

> Mondial Relay never asks you to call a premium number, pay extra postage or share bank or login details. Its texts link only to mrcolis.fr. Report fakes to phishing@inpost-group.com.

## Tracking a Mondial Relay parcel with Peek

[Peek](/) recognises the 26-digit barcode at once. With 8, 10 or 12 digits it may not name Mondial Relay on its own, since other carriers share those lengths and Mondial Relay can't be asked whether it knows a number: press "Change" and pick Mondial Relay, or paste a tracking link from mondialrelay.fr, .be, .nl or .com (links from its Spanish and Portuguese sites aren't read).

An 8-digit number also needs the "Delivery postcode": type the number alone, then the postcode in its field. Peek compares it with the recipient's as typed, in any country's format, but a Portuguese one needs all seven digits (1234-567, hyphen optional). A wrong one reads as not found. It's sent to Mondial Relay and, when needed, to tracking services.

Peek shows Mondial Relay's French wordings as written and each scan's logistics site on a map, plus the expected day, delivery time and destination country when given. While the parcel waits, it shows the Point Relais or locker's name and address, never your pickup code or name. It checks every 10 minutes by day, every few minutes once out for delivery, and hourly overnight, and can notify you of each change. Peek doesn't follow parcels to other carriers. It reads InPost Spain's `FV` numbers on inpost.es, but can't promise to find InPost's other Spanish or Portuguese parcels.

## Questions about Mondial Relay tracking

### Where is my Mondial Relay parcel?

The latest tracking line says: a logistics site's name means it's travelling, and `Disponible dans votre Point Relais` means it's waiting for you. Nothing new for 5 days? Use Mondial Relay's contact form.

### How long does Mondial Relay take to deliver?

Mondial Relay gives 3 to 5 working days from hand-in in mainland France, where it delivers Tuesday to Saturday, and 3 to 6 to other countries.

### Is Mondial Relay the same as InPost?

It's part of the InPost group. In Spain and Portugal, InPost took over Mondial Relay in 2021, and mondialrelay.es and mondialrelay.pt now lead to InPost's sites. In the Netherlands, Mondial Relay is moving to the InPost brand over the coming months; until then everything runs through Mondial Relay. See [InPost tracking](carrier:inpost).

### Is Mondial Relay legit?

Yes: Mondial Relay SASU is a French company based in Villeneuve-d'Ascq. Fake messages use its name, though: check them against the warning above.

:::sources
- [Mondial Relay: Suivi de colis](https://www.mondialrelay.fr/suivi-de-colis/) – form, postcode
- [Mondial Relay: Numéro de colis](https://www.mondialrelay.fr/faq/recevoir-un-colis/ou-trouver-mon-numero-de-colis/) – label, email
- [Mondial Relay: Acheminement](https://www.mondialrelay.fr/faq/recevoir-un-colis/comment-suivre-l-acheminement-de-mon-colis/) – no number
- [Mondial Relay: Délai de retrait](https://www.mondialrelay.fr/faq/recevoir-un-colis/combien-de-jours-ai-je-pour-retirer-mon-colis-en-locker-ou-point-relais/) – holding times
- [Mondial Relay: Retirer mon colis](https://www.mondialrelay.fr/faq/recevoir-un-colis/comment-retirer-mon-colis/) – ID or code
- [Mondial Relay: Retrait en Locker](https://www.mondialrelay.fr/faq/recevoir-un-colis/comment-retirer-un-colis-dans-un-lockers-mondial-relay/) – code, QR
- [Mondial Relay: Une autre personne](https://www.mondialrelay.fr/faq/recevoir-un-colis/une-autre-personne-peut-elle-venir-chercher-mon-colis-en-lockers-ou-dans-un-point-relais/) – someone else
- [Mondial Relay: Modifier le Point Relais](https://www.mondialrelay.fr/faq/recevoir-un-colis/je-souhaite-modifier-le-point-relais-de-livraison-est-ce-possible/) – no changes
- [Mondial Relay: Point Relais fermé](https://www.mondialrelay.fr/faq/recevoir-un-colis/mon-point-relais-est-ferme-ou-mon-colis-va-t-il-etre-livre/) – substitute relays
- [Mondial Relay: Délai moyen](https://www.mondialrelay.fr/faq/envoyer-un-colis/quel-est-le-delai-moyen-de-livraison/) – delivery times
- [Mondial Relay: Jours de livraison](https://www.mondialrelay.fr/faq/recevoir-un-colis/quels-sont-les-jours-ou-le-livreur-passe-au-point-relais-pour-y-deposer-les-colis/) – delivery days
- [Mondial Relay: Livraison échouée](https://www.mondialrelay.fr/faq/domicile/livraison-%C3%A0-domicile-que-se-passe-t-il-si-ma-livraison-%C3%A9choue/) – attempts
- [Mondial Relay: Retard](https://www.mondialrelay.fr/faq/recevoir-un-colis/que-faire-en-cas-de-retard-de-livraison/) – 5 days
- [Mondial Relay: Colis perdu](https://www.mondialrelay.fr/faq/recevoir-un-colis/que-faire-si-mon-colis-est-perdu/) – the sender
- [Mondial Relay: Vinted ou Leboncoin](https://www.mondialrelay.fr/faq/envoyer-un-colis/un-colis-envoye-via-un-site-partenaire-vinted-leboncoin-rakuten-est-declare-perdu-que-faire/) – platforms
- [Mondial Relay: Avarie](https://www.mondialrelay.fr/faq/recevoir-un-colis/que-signifie-lexpression-en-avarie/) – refund
- [Mondial Relay: Colis abîmé](https://www.mondialrelay.fr/faq/recevoir-un-colis/mon-colis-est-abime-que-faire/) – refusal
- [Mondial Relay: Nous contacter](https://www.mondialrelay.fr/nous-contacter/) – chatbot, phone
- [Mondial Relay: Arnaques](https://www.mondialrelay.fr/faq/recevoir-un-colis/e-mail-arnaque-mondial-relay/) – InPost group, scams
- [Mondial Relay: Mentions légales](https://www.mondialrelay.fr/mentions-legales/) – company
- [Mondial Relay: Étiquette](https://storage.mondialrelay.fr/etiquette-mondial-relay-v-24.pdf) – barcode
- [Mondial Relay Belgique: Suivi](https://www.mondialrelay.be/fr-be/suivi-de-colis/) – 10, 12 digits
- [Mondial Relay: Mijn Mondial Relay pakket volgen](https://www.mondialrelay.nl/mijn-mondial-relay-pakket-volgen/) – 8, 10, 12 digits in the Netherlands
- [Mondial Relay Belgique: Contact](https://www.mondialrelay.be/fr-be/nous-contacter/) – phone, email
- [InPost: Mondial Relay Nederland maakt plaats voor InPost](https://inpost.nl/) – move to the InPost brand in the Netherlands
- [InPost: Quiénes somos](https://www.inpost.es/quienes-somos) – takeover in Spain
- [InPost: Quem somos](https://www.inpost.pt/quem-somos) – takeover in Portugal
- [InPost: Empresa de Paquetería Europea](https://www.mondialrelay.es/) – redirect
- [InPost: Empresa de Encomendas](https://www.mondialrelay.pt/) – redirect
- [InPost: Contacto](https://www.inpost.es/contacta-con-nosotros) – Spanish line, 5 days
- [InPost: Fala connosco](https://www.inpost.pt/fala-connosco) – Portuguese line, 5 days
- [InPost: Como recolher](https://www.inpost.pt/recebe-a-tua-encomenda/como-recolher) – 5 or 8 days
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – what Peek reads
:::
