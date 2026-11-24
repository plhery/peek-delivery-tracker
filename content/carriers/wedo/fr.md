---
title: Suivi One by Allegro CZ (WE|DO) : codes et livraison
description: Suis un colis One by Allegro CZ (WE|DO, IN TIME, Uloženka) en Tchéquie : codes de 11 caractères, One Box 48 h, One Point 5 jours et contact.
slug: suivi-one-by-allegro-cz
published: 2026-11-24
updated: 2026-11-24
---

One by Allegro CZ est la société de livraison tchèque du groupe Allegro (son site, [onedelivery.cz](https://onedelivery.cz/), appartient à Allegro Retail a.s.). Elle livre les commandes d’allegro.cz et d’autres boutiques en ligne via trois services : **One Box** (plus de 5 244 consignes automatiques incluant les partenaires AlzaBox et OX Box), **One Point** (1 960 points relais partenaires) et **One Courier** (livraison à domicile en Tchéquie avec plus de 350 véhicules et livreurs). La recherche de transporteur dans Peek le trouve aussi sous WE|DO, IN TIME ou Uloženka.

Tu peux vérifier un colis tchèque sur [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky) ou coller ton numéro de suivi de 11 caractères dans le champ ci-dessus.

## Format du numéro de suivi One by Allegro CZ (WE|DO)

Les envois tchèques One by Allegro / WE|DO utilisent un **code alphanumérique compact de 11 caractères** :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `2BM48291736` | 1 chiffre + 1 lettre + 1 lettre/chiffre + 8 chiffres (11 caractères au total) — numéro standard One by Allegro CZ (WeDo). |
| `48P48291736` | 2 chiffres + 1 lettre + 8 chiffres (11 caractères au total) — autre format de colis tchèque One by Allegro (WeDo). |

Les préfixes commençant par `7D` + chiffre ou `7X` + chiffre sont exclus de cette règle ; tous les autres codes de 11 caractères correspondants identifient **One by Allegro CZ** sans ambiguïté avec une confiance élevée. Peek lit aussi les liens directs `onedelivery.cz/sledovani-zasilky?numer=...`, `onedelivery.cz/?numer=...`, `trace.wedo.cz/index.php?orderNumber=...` et `trace.intime.cz/index.php?orderNumber=...`. Les colis polonais Allegro One utilisent un suivi séparé — voir [Allegro One](carrier:allegro-one) et le guide des [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts One by Allegro CZ

L’API publique de suivi One by Allegro CZ renvoie des libellés en anglais, chacun accompagné de son horaire et de son décalage par rapport à UTC. Voici les plus courants :

| Libellé One by Allegro CZ | Ce que cela signifie |
| --- | --- |
| `Parcel has been prepared by the sender` | La boutique a créé l’étiquette d’envoi ; le livreur n’a pas encore collecté le colis. |
| `Parcel has been picked up by the courier` | One by Allegro CZ a collecté le colis chez l’expéditeur. |
| `Parcel has been dispatched to its destination point` | A quitté le centre d’origine vers la région ou le réseau de retrait de destination. |
| `Parcel has been accepted at the sorting center` | Traité dans un centre de tri One by Allegro. |
| `Parcel has been accepted at the branch` | Arrivé au dépôt local qui dessert ton adresse ou ton point de retrait. |
| `Parcel has been released for delivery` | Chargé dans un véhicule de livraison pour remise à domicile ou dépôt dans ton One Box / One Point aujourd’hui. |
| `Parcel is awaiting pick-up` | Prêt à être retiré dans ta consigne One Box ou ton point relais One Point (vérifie ton SMS, ton e-mail ou ton appli pour le code PIN). |
| `Parcel has been delivered` | Remis au destinataire ou retiré de la consigne ou du point relais. |

Si le suivi renvoie un message inhabituel susceptible de contenir des données personnelles du destinataire, Peek remplace ce libellé par `Tracking update` tout en conservant son horodatage dans l’historique.

## Règles de livraison One Box, One Point et One Courier

One by Allegro CZ publie ses délais de garde et ses règles de livraison sur [onedelivery.cz](https://onedelivery.cz/) :

- **Consignes One Box ([onedelivery.cz/one-box](https://onedelivery.cz/one-box)) :** accessibles **24 h/24 et 7 j/7** sur plus de 5 244 consignes (One Box, AlzaBox et OX Box). Retire ton colis dans les **48 heures** suivant le dépôt en saisissant le numéro de téléphone du destinataire et le code PIN numérique reçu par SMS, e-mail, dans l’application One by Allegro ou l’application Allegro (aucune application n’est obligatoire devant l’écran). Le paiement à la livraison (*dobírka*) peut être réglé via le lien de paiement de ton avis ou sur le terminal carte du One Box.
- **Points relais One Point ([onedelivery.cz/one-point](https://onedelivery.cz/one-point)) :** dans 1 960 commerces partenaires aux horaires d’ouverture du magasin, les colis sont normalement conservés **5 jours ouvrables** (prolongeables gratuitement jusqu’à **3 jours ouvrables supplémentaires** via le service client ; pendant la période chargée avant Noël, la garde One Point passe de 5 à **3 jours**, tandis que One Box reste à 48 heures). Les limites One Point sont de **20 kg**, côté le plus long **1,2 m** et volume **0,2 m³**. Le contre-remboursement se règle en espèces ou par carte lorsque le commerce dispose d’un terminal.
- **Livraison à domicile One Courier ([onedelivery.cz/one-courier](https://onedelivery.cz/one-courier)) :** livraison standard le **jour ouvrable suivant (`D+1`)** après l’enlèvement, les **jours ouvrables entre 08 h 00 et 18 h 00**, jusqu’à la première porte verrouillable (les livreurs ne sont pas tenus de monter les colis dans les étages et les boîtes postales ne sont pas desservies). Le jour de la livraison tu reçois un avis par SMS, e-mail ou appli. Le contre-remboursement se paie en espèces (montant exact demandé) ou par carte sans supplément. Tu peux modifier la date de livraison ou rediriger un colis éligible vers un point de retrait dans l’appli mobile One by Allegro ou auprès du service client ; les changements d’adresse passent par le service client.

Si tu manques un délai de retrait ou cherches un colis, consulte les guides sur les [consignes automatiques](guide:parcel-lockers), les [colis non retirés](guide:uncollected-parcel) et le [suivi bloqué](guide:tracking-not-updating). Pour d’autres réseaux tchèques, compare avec [Packeta](carrier:packeta), [PPL](carrier:ppl) et [Česká pošta](carrier:ceska-posta).

## Suivre un colis One by Allegro CZ avec Peek

Colle ton code One by Allegro CZ de 11 caractères, ou un lien `onedelivery.cz` ou `trace.wedo.cz`, dans Peek : Peek reconnaît aussitôt **One by Allegro CZ**. Si tu dois changer de transporteur, utilise « Changer » lors de l’ajout ou « Changer de transporteur » sur la fiche du colis.

Peek interroge directement le point de suivi public de One by Allegro CZ (`edge.onedelivery.cz`) via l’[adaptateur One by Allegro CZ open source de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo), avec une nouvelle tentative automatique en cas de délai réseau dépassé. Il conserve les horodatages exacts avec leur décalage par rapport à UTC ainsi que l’instant de livraison (`delivered_at`), tout en écartant les messages libres qui pourraient exposer des données personnelles. L’API publique n’inclut ni date estimée de livraison, ni ville de dépôt, ni poids du colis.

Peek classe ces libellés en étapes : « Étiquette créée » (`Parcel has been prepared by the sender`), « Pris en charge » (`Parcel has been picked up by the courier`), « En transit » (expédition, centre de tri et agence), « En livraison » (`Parcel has been released for delivery`), « Prêt à être retiré » (`Parcel is awaiting pick-up`) et « Livré » (`Parcel has been delivered`). Il lit aussi les autres libellés Allegro One, comme `Parcel has been returned to the sender`.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où One by Allegro CZ affiche `Parcel has been released for delivery`, toutes les 2 minutes à partir de 6 h heure tchèque, tant que le suivi One by Allegro CZ répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque One by Allegro CZ ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger One by Allegro CZ. », avec un lien « Demande de l’aide à One by Allegro CZ » vers la page de contact de One by Allegro CZ. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger One by Allegro CZ. »

Dans les réglages d’alertes de Peek, choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement » pour recevoir les notifications souhaitées.

## Contacter One by Allegro CZ

- **Formulaire de demande et support :** [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt) (clique sur *Dotaz na zásilku* pour les questions acheteur/vendeur, codes PIN perdus, prolongation gratuite de 3 jours en One Point ou changement d’adresse)
- **Page officielle de suivi :** [onedelivery.cz/sledovani-zasilky](https://onedelivery.cz/sledovani-zasilky)
- **Informations sur les services :** [One Box](https://onedelivery.cz/one-box), [One Point](https://onedelivery.cz/one-point) et [One Courier](https://onedelivery.cz/one-courier)
- **Site principal :** [onedelivery.cz](https://onedelivery.cz/)

## Questions fréquentes

### WE|DO, IN TIME et One by Allegro CZ sont-ils le même transporteur ?

Peek les traite comme un seul transporteur : sa recherche trouve One by Allegro CZ sous WE|DO, IN TIME ou Uloženka, et les liens `trace.wedo.cz` et `trace.intime.cz` ainsi que les numéros à 11 caractères sont tous suivis sous One by Allegro CZ. Le site `onedelivery.cz` ne raconte pas l’histoire de ces marques.

### Combien de temps un colis attend-il en One Box ou en One Point ?

En **One Box** (y compris AlzaBox et OX Box), les colis attendent **48 heures** après le dépôt dans le casier. En point relais **One Point**, la garde standard est de **5 jours ouvrables** (prolongeable gratuitement jusqu’à 3 jours ouvrables via le service client), ramenée à **3 jours** pendant la haute saison d’avant Noël.

### Puis-je payer le contre-remboursement (dobírka) par carte ?

Oui. Les livreurs One Courier acceptent les espèces (montant exact demandé) ou la carte sans frais pour le destinataire ; One Box accepte la carte via le lien de paiement ou sur le terminal de la consigne ; et One Point accepte espèces ou carte lorsque le commerce possède un terminal.

### Que faire si j’ai perdu mon code de retrait One Box ou One Point ?

Les codes de retrait sont envoyés par SMS, e-mail et dans les applications One by Allegro et Allegro. Si tu ne le retrouves nulle part, contacte le service client via le formulaire sur [onedelivery.cz/kontakt](https://onedelivery.cz/kontakt).

:::sources
- [One by Allegro CZ (onedelivery.cz)](https://onedelivery.cz/)
- [One by Allegro CZ — Sledování zásilky](https://onedelivery.cz/sledovani-zasilky)
- [One by Allegro CZ — One Box](https://onedelivery.cz/one-box)
- [One by Allegro CZ — One Point](https://onedelivery.cz/one-point)
- [One by Allegro CZ — One Courier](https://onedelivery.cz/one-courier)
- [One by Allegro CZ — Kontakt](https://onedelivery.cz/kontakt)
- [universal-parcel-scraper One by Allegro CZ adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wedo)
:::
