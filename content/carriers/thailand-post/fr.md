---
title: Suivi Thailand Post : codes TH S10, statuts EMS et 1545
description: Suis un colis Thailand Post (ไปรษณีย์ไทย) : codes TH S10 à 13 caractères, codes de scan domestiques et EMS, contre-remboursement, douane et centre 1545.
slug: suivi-thailand-post
published: 2026-11-24
updated: 2026-11-24
---

**Thailand Post** (*Thailand Post Co., Ltd.*, en thaï *บริษัท ไปรษณีย์ไทย จำกัด*) est l’opérateur postal national de la Thaïlande ([thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en)). Assurant le courrier national, les colis recommandés, EMS, Same Day, les envois lourds LogisPost et le paiement à la livraison (COD) en Thaïlande ainsi que les services internationaux [EMS](carrier:ems) World, Registered, ePacket, Courier Post (jusqu’à 200 kg) et Courier Post Plus dans le monde entier, Thailand Post propose un suivi public sur [track.thailandpost.co.th](https://track.thailandpost.co.th/) et des avis internationaux sur [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update). Tu peux y consulter un envoi postal thaïlandais ou coller ton numéro `TH` à 13 caractères dans le champ ci-dessus.

## Numéros de suivi Thailand Post (format `TH` S10 à 13 caractères)

Les envois suivis nationaux et internationaux de Thailand Post utilisent des codes UPU S10 à 13 caractères : deux lettres de service en majuscules, 8 chiffres de série, 1 chiffre de contrôle S10 et le code pays `TH` :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `EE482917367TH` | Envoi express [EMS](carrier:ems) Thailand Post à 13 caractères (préfixe `EE` + 9 chiffres avec clé de contrôle S10 valide + `TH`). |
| `RR739184627TH` | Courrier recommandé ou petit paquet Thailand Post à 13 caractères (préfixe `RR` + 9 chiffres avec clé de contrôle S10 valide + `TH`). |

Les préfixes Express/EMS national, Recommandé (`R...TH`), ePacket (`L...TH`) et colis nationaux (`E`, `R`, `C`, `W`, etc. terminés par `TH`) suivent tous cette même structure S10 à 13 caractères avec chiffre de contrôle. Les lettres ordinaires sans code-barres à 13 caractères ne peuvent pas être suivies en ligne. Voir le guide des [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi Thailand Post

Le suivi de Thailand Post associe des codes numériques `statusID` alignés sur l’UPU à des descriptions en anglais (et des noms de bureaux thaïlandais), couvrant aussi bien les scans nationaux en Thaïlande que les mises à jour EDI internationales relayées par les postes de destination :

| Code et libellé Thailand Post | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `3` `Posting/Collection` | Déposé au guichet d’un bureau Thailand Post ou collecté chez l’expéditeur. | « Pris en charge » |
| `9` `Arrival at outward office of exchange`, `15` `Departure From Outward OE`, `18` `Arrival at inward office of exchange` | Traité par un bureau d’échange international à l’exportation ou à l’importation. | « En transit » |
| `23`–`27` `In transit`, `Item into sorting center`, `Arrival at post office` | En transit entre centres de tri postaux puis arrivé au bureau de poste local chargé de la livraison. | « En transit » |
| `31` `Item out for physical delivery` | En tournée aujourd’hui avec le facteur Thailand Post pour livraison à domicile. | « En livraison » |
| `57` `Contact recipient` | Le facteur a tenté d’appeler le destinataire (action sans déplacement ; conserve l’étape en cours). | Conserve l’étape précédente |
| `32` `Item arrival at collection point for pick-up (by recipient)` | Déposé dans une consigne ou un point de retrait Thailand Post, prêt à être retiré. | « Prêt à être retiré » |
| `34` `Scheduled for further delivery attempt today` / `Item held, addressee notified` (groupe 4) | Tentative de livraison échouée (par exemple absence du destinataire ou frais douaniers/COD à régler). | « Tentative de livraison » |
| `28` `Return to the origin post office` | Incident signalé et renvoi en cours vers le bureau de poste d’origine. | « Problème signalé » |
| `35/L` `The sender received the returned item` | Colis retourné remis à l’expéditeur d’origine. | « Retourné à l’expéditeur » |
| `35/S` `Final delivery` | Livré au destinataire (peut être suivi de `36` `Successfully transfer money to seller` sur les commandes COD, ce qui ne change pas l’heure de livraison). | « Livré » |

## COD national, relais internationaux et douane

- **Exports internationaux (`...TH` vers l’étranger) :** après `15` (`Departure From Outward OE`), l’opérateur postal national de ton pays — comme [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl) / Deutsche Post ou [Australia Post](carrier:australia-post) — prend en charge le dédouanement import et la livraison locale tout en renvoyant des scans EDI à Thailand Post.
- **Droits de douane et TVA à l’importation :** les colis exportés de Thaïlande ou importés en Thaïlande sont soumis aux règles douanières, à la TVA et aux frais de dédouanement du pays de destination (y compris les frais européens sur les achats hors UE). Si le suivi affiche `34` (`Item held, addressee notified due to Payment of charges`), vérifie ta boîte aux lettres ou ton bureau de poste pour un avis de paiement ou de douane. Voir le guide sur les [colis retenus en douane](guide:customs).
- **Paiement à la livraison (COD) en Thaïlande :** sur les envois COD domestiques, Thailand Post publie `35/S` (`Final delivery`) lorsque le destinataire reçoit et paie le colis, suivi de `36` (`Successfully transfer money to seller`) une fois les fonds reversés au vendeur.

## Colis Thailand Post retardé, retenu ou manquant

- **Silence après le départ de Thaïlande :** les envois aériens internationaux (en particulier Recommandé `R...TH` et colis économiques) marquent souvent une pause de plusieurs jours ou semaines entre `Departure From Outward OE` et `Arrival at inward office of exchange`. Consulter le même code-barres `TH` à 13 caractères sur le site de la poste de destination révèle souvent des précisions supplémentaires sur la douane ou la distribution locale. Voir le guide sur le [suivi bloqué](guide:tracking-not-updating).
- **Colis perdus, endommagés ou retournés :** contacte d’abord l’expéditeur ou la place de marché et conserve ton code-barres à 13 caractères ainsi que le reçu. Vois aussi [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel), [livré mais non reçu](guide:delivered-not-received) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client Thailand Post et Contact Center 1545

Canaux officiels publiés sur [Thailand Post Contact Us](https://www.thailandpost.co.th/un/contact_thp/un/41) et [thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en) :

- **THP Contact Center (Thaïlande) :** **`1545`** (renseignements généraux, recherche d’envois et réclamations).
- **Portail officiel de contact et de demandes :** [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).
- **Suivi en ligne et avis internationaux :** [track.thailandpost.co.th](https://track.thailandpost.co.th/) et [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update).

> Méfie-toi des faux SMS ou messages LINE affirmant qu’un colis Thailand Post exige un petit paiement via un lien inconnu. Vérifie toujours tout suivi ou frais de douane directement sur [track.thailandpost.co.th](https://track.thailandpost.co.th/) ou auprès du THP Contact Center `1545`. Voir le guide sur les [SMS frauduleux de livraison](guide:delivery-scam-texts).

## Suivre un colis Thailand Post avec Peek

[Peek](/) détecte automatiquement Thailand Post comme seul candidat lorsque tu saisis n’importe quel code S10 `TH` valide de 13 caractères (`EE482917367TH` ou `RR739184627TH`, inventés) et reconnaît directement les liens collés `track.thailandpost.co.th/?trackNumber=`. Si tu souhaites changer de transporteur manuellement (par exemple pour consulter les scans de la poste de destination dès que l’envoi arrive à l’étranger), appuie sur « Changer » lors de l’ajout ou « Changer de transporteur » sur la fiche du colis.

Peek lit le suivi de Thailand Post — et son second serveur lorsque le premier ne répond pas — puis affiche l’étape (« Pris en charge », « En transit », « En livraison », « Prêt à être retiré », « Tentative de livraison », « Problème signalé », « Retourné à l’expéditeur », « Livré »), le libellé anglais du scan, le bureau de poste et l’heure de livraison. Les codes de statut servent à déterminer l’étape et aucune application Peek ne les affiche ; le nom du destinataire apparaît sous « Destinataire » sur ta propre fiche de colis, jamais sur un lien partagé, et aucune application n’affiche le nom de la personne ayant réceptionné le colis. Les scans propres à Thailand Post portent le décalage par rapport à UTC de Bangkok. Un scan EDI d’une poste étrangère indique l’heure locale de ce bureau que Thailand Post stocke comme s’il s’agissait de l’heure de Bangkok : Peek le date donc sur le fuseau horaire du bureau (heure de Tokyo pour `TOKYO INTERNATIONAL`), un scan sans lieu sur le fuseau du scan voisin, et affiche chaque horaire à l’heure de ton appareil. Peek exclut le nom du livreur, les numéros de téléphone, la signature et la note de contact masquée ; Thailand Post ne donne pas de jour de livraison estimé.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Thailand Post affiche `31` (`Item out for physical delivery`), toutes les 2 minutes à partir de 6 h sur l’horloge du scan le plus récent, tant que le suivi Thailand Post répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi de Thailand Post ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Thailand Post. », avec un lien « Demande de l’aide à Thailand Post » vers la page de contact de Thailand Post. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Thailand Post. »

Tu peux choisir les alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek suit uniquement les mises à jour et ne peut ni dédouaner, ni régler des frais COD, ni contacter Thailand Post à ta place.

## Questions sur le suivi Thailand Post

### À quoi ressemble un numéro de suivi Thailand Post ?

Les envois suivis Thailand Post utilisent des codes UPU S10 à 13 caractères débutant par deux lettres de service, suivies de 9 chiffres (terminés par un chiffre de contrôle) et de `TH` — par exemple `EE482917367TH` pour EMS ou `RR739184627TH` pour un recommandé (inventés).

### Que signifie le code `36` (`Successfully transfer money to seller`) ?

Sur les commandes nationales thaïlandaises en paiement à la livraison (COD), le code `36` confirme que Thailand Post a reversé au vendeur le montant encaissé après la livraison finale (`35/S`). C’est un événement financier et non un déplacement du colis.

### Pourquoi mon colis Thailand Post ne s’actualise-t-il plus après `Departure From Outward OE` ?

Le colis a quitté le bureau d’échange international sortant de Thaïlande et se trouve en transit aérien international ou en attente de prise en charge import par la poste du pays de destination.

### Comment contacter le service client de Thailand Post ?

Appelle le THP Contact Center au **`1545`** en Thaïlande ou utilise la page de contact officielle sur [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).

:::sources
- [Thailand Post: Official Website](https://www.thailandpost.co.th/index.php?page=index&language=en) – *Thailand Post Co., Ltd.*, services EMS nationaux/internationaux, Recommandé, Courier Post, LogisPost et COD
- [Thailand Post: Contact Us & THP Contact Center 1545](https://www.thailandpost.co.th/un/contact_thp/un/41) – support client officiel, portail de réclamations et ligne `1545` (page d’aide liée par Peek)
- [Thailand Post: Public Tracker](https://track.thailandpost.co.th/) – recherche publique officielle de suivi
- [Thailand Post: International Service Advisories](https://sites.google.com/thailandpost.com/interthp/update) – avis internationaux et suivi de service
- [Universal Parcel Scraper: thailand-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/thailand-post) – validation de clé S10 `TH`, bascule double hôte, table `statusID` alignée UPU (`3`–`36`, `57`), fuseau Bangkok vs EDI étranger et extraction du destinataire
:::
