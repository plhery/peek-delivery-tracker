---
title: Suivi TNT Australia : numéros, statuts et aide en Australie
description: Suis un envoi domestique TNT Australia : format 3 lettres + 9 chiffres, statuts de dépôt, relivraison lundi–vendredi et contact 13 11 50.
slug: suivi-tnt-australia
published: 2026-11-24
updated: 2026-11-24
---

TNT Australia (*TNT Express Australia*, intégré à FedEx Express) achemine des colis, des documents et des palettes à travers l’Australie via ses dépôts ([tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html)). Les envois internationaux disposent d’un suivi distinct (voir [TNT](carrier:tnt)) ; les envois domestiques australiens ont leur propre outil sur [tntexpress.com.au](https://www.tntexpress.com.au/interaction/Trackntrace.aspx).

Tu peux consulter un envoi domestique australien sur [TNT Australia Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) ou coller ton numéro d’expédition de 12 caractères dans le champ ci-dessus.

## Formats de numéro de suivi TNT Australia

Peek suit les envois domestiques TNT Australia grâce à un numéro de 12 caractères :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `SYD482917360` | **3 lettres majuscules + 9 chiffres** (12 caractères au total) — numéro d’envoi domestique TNT Australia standard suivi sur `tntexpress.com.au`. |

Comme une suite de 3 lettres suivies de 9 chiffres correspond aussi aux numéros de [Purolator](carrier:purolator), Peek interroge ces deux transporteurs tour à tour : Purolator en premier au Canada, TNT Australia en premier ailleurs. Il affiche « TNT Australia a ce colis. » dès que TNT Australia répond. Certains préfixes, comme `RWM` pour Kuehne+Nagel, sont exclus du format TNT Australia. Un lien direct `tntexpress.com.au/interaction/ASPs/trackref_detail.asp?con=...` ou `trackref.aspx?con=...` oriente immédiatement vers TNT Australia.

Le [Centre d’aide TNT Australia](https://www.tnt.com/express/en_au/site/support.html) désigne par numéro d’expédition (*shipment / consignment number*) le nombre à **9 chiffres** figurant à gauche de la facture, environ 3 cm sous le numéro client. Le suivi TNT Australia dans Peek n’accepte que le format 3 lettres + 9 chiffres. Pour d’autres formats de colis, consulte le guide sur les [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi TNT Australia

La page de détail domestique de TNT Australia comporte quatre colonnes — `Status`, `Date`, `Time` et `Depot` — ainsi qu’une date `Estimated Delivery by:` en haut lorsqu’elle est disponible.

| Libellé TNT Australia | Ce que cela signifie |
| --- | --- |
| `Your shipment data is lodged` | L’expéditeur a transmis électroniquement les données de l’envoi ; TNT Australia n’a pas encore collecté le colis. |
| `We've collected your shipment` | TNT Australia a enlevé le colis chez l’expéditeur, avant le premier scan en dépôt. |
| `We're processing your shipment` | Scanné dans un dépôt TNT Australia d’origine, de transit ou de distribution. |
| `Your shipment's with a driver` | En cours d’acheminement avec un chauffeur pour une livraison aujourd’hui à domicile ou en entreprise. |
| `New delivery address required` | La tournée n’a pas pu livrer à l’adresse indiquée ; le colis reste au dépôt local en attente d’une nouvelle adresse ou d’un retrait. |
| `We've delivered your shipment` | Livré à l’adresse du destinataire. |
| `Your shipment was collected` | Le destinataire a retiré le colis en personne au dépôt TNT qui le retenait (à ne pas confondre avec `We've collected your shipment`, qui correspond à l’enlèvement chez l’expéditeur). |
| `Book-In Delivery Requested` / `Book-In Delivery Arranged` | Le destinataire a demandé une livraison sur rendez-vous et TNT l’a confirmée. Il s’agit d’avis, non de scans : ils conservent l’étape du colis, et la confirmation peut apparaître après la livraison. |

## Relivraison, retrait au dépôt et retards

Le [Centre d’aide TNT Australia](https://www.tnt.com/express/en_au/site/support.html) précise la marche à suivre en cas de livraison manquée ou de report :

- **Si le chauffeur a laissé un avis de passage :** utilise le service de relivraison TNT (*Re-delivery Service*) pour réserver une nouvelle date.
- **Si aucun avis n’a été laissé (ou si la livraison n’a pas encore été tentée) :** contacte TNT Australia avec ton numéro d’expédition, l’adresse complète de livraison et la date de relivraison souhaitée.
- **Relivraisons du lundi au vendredi uniquement :** TNT Australia indique que les commandes ne peuvent être relivrées que du lundi au vendredi.
- **`New delivery address required` :** le colis reste au dépôt indiqué dans la colonne `Depot` jusqu’à ce que tu fournisses une autre adresse ou que tu le retires ; contacte TNT Australia. Consulte aussi les guides sur la [livraison manquée](guide:missed-delivery) et le [suivi bloqué](guide:tracking-not-updating).

Si ta commande en Australie circule avec un autre transporteur national, compare avec [Australia Post](carrier:australia-post), [Aramex Australia](carrier:aramex-au), [CouriersPlease](carrier:couriersplease) ou [Border Express](carrier:border-express).

## Suivre un colis TNT Australia avec Peek

Colle ton numéro domestique à 3 lettres + 9 chiffres, ou un lien de suivi `tntexpress.com.au`, dans Peek. Si Peek l’associe à un autre transporteur, appuie sur « Changer » lors de l’ajout ou sur « Changer de transporteur » sur la fiche du colis.

Peek lit directement la page de détail domestique de TNT Australia grâce à l’[adaptateur TNT Australia open source de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au), avec une nouvelle tentative automatique en cas d’expiration réseau. Il relève chaque libellé de scan, le dépôt, l’heure ainsi que la date `Estimated Delivery by:` jusqu’à la livraison. Comme TNT Australia affiche les heures de dépôt sans décalage horaire, Peek date chaque scan selon le fuseau de son dépôt (heure de Sydney pour Sydney, heure de Perth pour Perth, etc.), un scan sans dépôt sur le fuseau du scan voisin, sinon à l’heure de Sydney, puis affiche chaque scan à l’heure de ton appareil. Le tableau domestique de TNT Australia ne fournit ni poids, ni nom de service, ni code de statut.

Peek traduit les libellés de TNT Australia en étapes claires : « Étiquette créée » (`Your shipment data is lodged`), « Pris en charge » (`We've collected your shipment`), « En transit » (`We're processing your shipment`), « En livraison » (`Your shipment's with a driver`), « Problème signalé » (`New delivery address required`) et « Livré » (`We've delivered your shipment` ou `Your shipment was collected`). Les avis de prise de rendez-vous (`Book-In Delivery Requested` et `Book-In Delivery Arranged`) restent dans l’historique sans annuler une livraison terminée ni modifier l’étape en cours.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où TNT Australia affiche `Your shipment's with a driver`, toutes les 2 minutes à partir de 6 h sur l’horloge du scan le plus récent, tant que le suivi de TNT Australia répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il passe à une vérification par heure quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque TNT Australia ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger TNT Australia. », avec un lien « Demande de l’aide à TNT Australia » vers le Centre d’aide de TNT Australia. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger TNT Australia. »

Choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement » dans les réglages d’alertes de Peek pour choisir quand recevoir une notification.

## Contacter TNT Australia

- **Téléphone (en Australie) :** `13 11 50` (publié sur l’ensemble du site TNT Australia)
- **Centre d’aide et relivraison :** [Support TNT Australia](https://www.tnt.com/express/en_au/site/support.html)
- **Portail de suivi domestique :** [tntexpress.com.au Track & Trace](https://www.tntexpress.com.au/interaction/Trackntrace.aspx) (suit jusqu’à 10 envois domestiques, Sameday Local Jobs, ou 1 référence électronique à la fois)
- **Site TNT Australia et lien de suivi international :** [tnt.com/express/en_au](https://www.tnt.com/express/en_au/site/home.html) et [présentation Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)

Garde ton numéro d’expédition domestique de 12 caractères et ton adresse de livraison à portée de main avant d’appeler le `13 11 50` ou de programmer une relivraison en semaine.

## Questions fréquentes

### Quelle est la différence entre `We've collected your shipment` et `Your shipment was collected` ?

`We've collected your shipment` intervient au début du trajet lorsque TNT Australia collecte le colis chez l’expéditeur. `Your shipment was collected` intervient à la fin lorsque le destinataire retire en personne un colis retenu au dépôt TNT ; Peek le passe donc à « Livré ».

### Pourquoi `Book-In Delivery Arranged` apparaît-il après la livraison de mon colis ?

`Book-In Delivery Arranged` confirme une livraison sur rendez-vous ; c’est un avis et non un scan, qui peut être publié après la livraison effective. Peek maintient le colis à « Livré » sans le faire reculer d’étape.

### TNT Australia livre-t-il ou relivre-t-il le week-end ?

Le [Centre d’aide TNT Australia](https://www.tnt.com/express/en_au/site/support.html) indique que les livraisons reprogrammées ne peuvent être relivrées que du **lundi au vendredi**.

### Mon numéro TNT comporte 9 chiffres sans lettres : pourquoi n’est-il pas suivi comme TNT Australia dans Peek ?

Le suivi TNT Australia de Peek accepte les numéros à 3 lettres suivies de 9 chiffres, le format domestique lu sur `tntexpress.com.au`. Le Centre d’aide TNT appelle numéro d’expédition (*shipment / consignment number*) un code à 9 chiffres imprimé sur la facture ; essaie-le sur [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html) ou appelle le `13 11 50`.

:::sources
- [TNT Australia](https://www.tnt.com/express/en_au/site/home.html)
- [TNT Australia Help Center](https://www.tnt.com/express/en_au/site/support.html)
- [TNT Australia Track & Trace](https://www.tnt.com/express/en_au/site/shipping-tools/tracking.html)
- [TNT Express Australia Domestic Tracker](https://www.tntexpress.com.au/interaction/Trackntrace.aspx)
- [universal-parcel-scraper TNT Australia adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tnt-au)
:::
