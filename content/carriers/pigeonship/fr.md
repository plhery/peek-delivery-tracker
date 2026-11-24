---
title: Suivi PigeonShip : numéros 18 caractères, statuts et aide
description: Suis tes livraisons PigeonShip le jour même et dernier kilomètre aux États-Unis et au Canada : codes à 18 caractères, statuts en direct et contacts.
slug: suivi-pigeonship
published: 2026-11-24
updated: 2026-11-24
---

PigeonShip est un transporteur de livraison du dernier kilomètre actif aux États-Unis et au Canada, avec des bureaux à Provo (Utah) et à Oakville (Ontario). Les détaillants, boutiques e-commerce et commerces locaux font appel à PigeonShip pour des livraisons le jour même, le lendemain et régionales via des offres telles que **Pigeon Flex** (livraison nationale en 2 à 5 jours), **Pigeon Express** (livraison nationale en 2 jours), **Pigeon Connect** (livraison le jour même à la demande) et **Pigeon Flash** (livraison le jour même planifiée).

PigeonShip recrute des chauffeurs indépendants et gère des programmes de livraison dédiés pour les entreprises. Les destinataires reçoivent un lien de suivi sur `admin.pigeonship.com` indiquant l’état de la commande et l’heure limite prévue, puis une page de fin de livraison une fois le colis déposé.

## Format des numéros de suivi PigeonShip

Peek accepte les numéros PigeonShip de 8 à 30 lettres et chiffres ; il supprime les espaces et passe les lettres en majuscules. Peek reconnaît un format à 18 caractères alternant chiffres et lettres majuscules :

- **18 caractères alphanumériques** suivant le motif `chiffre-lettre-chiffre-lettre-3 chiffres-lettre-3 chiffres-lettre-2 chiffres-lettre-chiffre-2 lettres` (par exemple `7P4K829H631N58R2TM`).

Les liens de suivi PigeonShip ont la forme `admin.pigeonship.com/shipping/tracking/package/<trackingNumber>/`. Lorsque tu colles ce lien dans Peek, Peek en extrait le code du colis et l’envoie directement à PigeonShip. À partir d’un code nu de 18 caractères respectant ce format, Peek interroge uniquement PigeonShip et affiche « PigeonShip a ce colis. » dès qu’il répond. Pour comparer avec d’autres formats de coursiers américains et canadiens, voir le [guide des formats de numéros de suivi](guide:tracking-number-formats).

## Comment fonctionne le suivi en direct PigeonShip

La page de suivi destinataire de PigeonShip présente une vue récapitulative plutôt qu’un historique de scans. Tant que la commande est active, elle affiche « In transit » et la position du chauffeur sur une carte, avec une heure limite de livraison (`dueDate`) lorsqu’elle est fixée ; en coulisses, la commande porte un code de statut et un libellé tels que `16` et `Assigned`. La page n’affiche « Delivered » que pour le code `64`. Une fois la livraison effectuée, PigeonShip remplace la page à la même adresse par un écran de confirmation indiquant `Your delivery has been completed successfully.` accompagné d’un lien vers les photos de livraison.

| Statut / page PigeonShip | Ce que cela signifie |
| --- | --- |
| Statut `16` — `Assigned` | La commande est affectée à la tournée d’un chauffeur ; la page affiche « In transit ». |
| Heure limite / `dueDate` | L’horaire cible de livraison affiché sur les commandes actives avant le dépôt. |
| Statut `64` / `Delivered` ou `Your delivery has been completed successfully.` | Le chauffeur a terminé la livraison ; ouvrir le lien propre à PigeonShip permet de voir la photo de confirmation. |
| `Page not found` (404) | Le numéro de suivi n’est pas actif dans le suivi en direct de PigeonShip ou contient une faute de frappe. |

Comme PigeonShip ne publie pas de journal de scans horodatés sur sa page destinataire, tu ne verras pas de scans intermédiaires d’entrepôt ou de centre de tri comme avec [UPS](carrier:ups), [USPS](carrier:usps), [Postes Canada](carrier:canada-post) ou [Intelcom](carrier:intelcom). La page actualise simplement son état en direct au fil de la course puis bascule vers l’écran de fin une fois le colis livré.

## Livraison le jour même et vérifications en cas de retard

PigeonShip propose la livraison le jour même et le lendemain partout aux États-Unis et au Canada. Les avis sur son site mentionnent un SMS avant l’arrivée du chauffeur et une photo après le dépôt.

Si ta commande reste sur `Assigned` après l’heure de livraison prévue ou si le lien affiche `Page not found` :

- **Vérifie les fautes de frappe :** confirme le code complet ou le lien `admin.pigeonship.com` entier reçu par e-mail ou SMS du vendeur ; le site PigeonShip répond `Page not found` pour tout numéro sans page active.
- **Contacte PigeonShip :** utilise son [formulaire de contact](https://www.pigeonship.com/contact-us) si la commande reste sur « In transit » après l’heure limite.
- **Vérifie les instructions de livraison :** les avis sur le site de PigeonShip mentionnent que les chauffeurs suivent les indications de l’étiquette et prennent une photo du dépôt ; ouvre le lien de suivi d’origine sur `admin.pigeonship.com` pour consulter les photos après livraison. Pour plus d’étapes de dépannage, lis [quand le suivi ne se met plus à jour](guide:tracking-not-updating) et le [guide livraison manquée](guide:missed-delivery).

## Suivre PigeonShip avec Peek

Colle ton numéro PigeonShip ou le lien complet `admin.pigeonship.com/shipping/tracking/package/...` dans Peek. Si Peek ne présélectionne pas PigeonShip pour un format inhabituel, choisis **Changer** à l’ajout du colis ou **Changer de transporteur** sur la fiche du colis.

Peek récupère directement la page de suivi rendue côté serveur par PigeonShip via l’[adaptateur PigeonShip open source de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship). Il lit le code et le libellé de statut de la commande, l’état livré et l’heure limite (`dueDate`) lorsqu’elle est présente, que Peek affiche comme livraison prévue. Peek omet le nom du destinataire, les coordonnées GPS du chauffeur, la barre de progression et les photos de livraison — ouvre le lien propre à PigeonShip pour voir la photo de dépôt.

Comme PigeonShip n’affiche que l’état courant, Peek ajoute une ligne datée au moment où il observe chaque changement d’étape : une commande active `Assigned` apparaît en **En transit** avec son heure prévue de livraison, et une livraison achevée en **Livré**.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes tant que la page de suivi PigeonShip répond. Sinon, Peek vérifie toutes les heures. La page de suivi PigeonShip ne comportant pas d’étape de tournée de livraison, Peek ne passe jamais à ses vérifications toutes les 2 minutes le jour de la livraison. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, Peek passe à une vérification par heure quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque la page PigeonShip ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger PigeonShip. », avec un lien « Demande de l’aide à PigeonShip » vers la page de contact de PigeonShip. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger PigeonShip. »

Dans les réglages de notifications de Peek, choisis **Chaque scan**, **Étapes importantes** ou **Livraison seulement** pour décider quelles mises à jour t’alertent.

## Contacter PigeonShip

- **Formulaire de contact officiel :** [PigeonShip Contact Us](https://www.pigeonship.com/contact-us) (sélectionne United States, Canada ou Other et décris ta demande de livraison)
- **Site officiel :** [pigeonship.com](https://www.pigeonship.com/)
- **Bureau États-Unis :** PigeonShip USA, 1675 N Freedom Blvd Ste 9B, Provo, Utah 84604-6929
- **Bureau Canada :** PigeonShip Canada, 405 Dundas St W, Oakville, ON L6M 5P9

## Questions fréquentes

### Pourquoi le suivi PigeonShip n’affiche-t-il pas une liste de scans passés ?

La page destinataire en direct de PigeonShip n’affiche qu’un résumé : l’état actuel de la commande (« In transit » ou « Delivered ») et l’heure limite de livraison, plutôt qu’un historique détaillé par entrepôts.

### Où puis-je voir la photo de livraison PigeonShip ?

Peek ne stocke ni n’affiche les photos de livraison de PigeonShip. Une fois le colis marqué livré, ouvre le lien d’origine `admin.pigeonship.com/shipping/tracking/package/...` reçu par e-mail ou SMS pour voir la page de fin de livraison et les photos.

### Que signifie le statut `Assigned` sur PigeonShip ?

`Assigned` (code de statut `16`) est l’état interne de la commande sur la page PigeonShip pendant qu’elle est sur la tournée d’un chauffeur ; la page elle-même affiche « In transit ». Peek l’affiche en **En transit**.

### PigeonShip livre-t-il à la fois aux États-Unis et au Canada ?

Oui. PigeonShip assure des livraisons le jour même, le lendemain et nationales du dernier kilomètre aux États-Unis et au Canada, avec des bureaux à Provo (Utah) et à Oakville (Ontario).

:::sources
- [PigeonShip](https://www.pigeonship.com/)
- [PigeonShip Contact Us](https://www.pigeonship.com/contact-us)
- [adaptateur PigeonShip de universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pigeonship)
:::
