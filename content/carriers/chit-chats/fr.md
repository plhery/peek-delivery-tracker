---
title: Suivi Chit Chats : codes 10 car., relais USPS/Postes Canada
description: Suis un colis Chit Chats du Canada vers les États-Unis, le Canada ou l’étranger : codes à 10 caractères, relais partenaires, retards et contact.
slug: suivi-chit-chats
published: 2026-11-24
updated: 2026-11-24
---

**Chit Chats** (*Chit Chats Express*) est une entreprise canadienne d’expédition e-commerce fondée en 2001 par Derek Nolan. Elle compte 66 agences au Canada, accepte aussi les dépôts par courrier (SmartDrop) et les collectes, et a expédié plus de 50 millions de colis pour plus de 100 000 expéditeurs ([chitchats.com](https://chitchats.com/)). Des transporteurs partenaires livrent ses colis : son suivi indique le partenaire et fournit son numéro de suivi, comme [USPS](carrier:usps) aux États-Unis ou [Postes Canada](carrier:canada-post) au Canada. Tu peux suivre un envoi sur [chitchats.com/tracking](https://chitchats.com/tracking) ou coller ton identifiant d’envoi Chit Chats de 10 caractères dans le champ ci-dessus.

## Numéros de suivi Chit Chats (identifiant d’envoi de 10 caractères)

Chaque commande Chit Chats reçoit un **identifiant d’envoi** alphanumérique en majuscules de 10 caractères (combinant lettres et chiffres, commençant généralement par une lettre suivie d’un chiffre différent de zéro) :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `K4N8V3T6R2` | Identifiant d’envoi Chit Chats standard de 10 caractères. |
| `P7M3X9D5Q8` | Variante d’identifiant d’envoi Chit Chats de 10 caractères pour affranchissement américain, canadien ou international. |

Une fois que Chit Chats remet le colis au partenaire du dernier kilomètre, la page de suivi affiche aussi le code propre à ce partenaire — par exemple un code-barres Intelligent Mail [USPS](carrier:usps) (pouvant inclure un préfixe `420` + code postal de routage que Peek retire proprement) ou un numéro de suivi [Postes Canada](carrier:canada-post). Voir [les formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi Chit Chats

Le suivi Chit Chats affiche ses propres scans puis ceux du transporteur partenaire ([USPS](carrier:usps), [Postes Canada](carrier:canada-post) ou autre) :

| Chit Chats ou le partenaire indique | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Shipment created, Chit Chats awaiting item`, `Label Created`, `Electronic information submitted by shipper` | Le marchand a acheté l’affranchissement en ligne mais n’a pas encore déposé le colis dans une agence Chit Chats. | « Étiquette créée » |
| `Received by Chit Chats` | Colis physique scanné dans une agence ou un point de dépôt Chit Chats au Canada. | « Pris en charge » |
| `Arrived at Chit Chats facility`, `Departed from Chit Chats facility`, `In transit` | Trié dans un centre Chit Chats ; `In transit` marque la remise au transporteur partenaire. | « En transit » |
| `Received from Chit Chats`, `Accepted at USPS Facility`, `Item accepted at the Post Office`, `Received at sort hub` | Intégré au réseau de tri du transporteur partenaire (USPS, Postes Canada ou partenaire international). | « En transit » / « Pris en charge » |
| `Arrived at USPS Facility`, `Departed USPS Facility`, `In Transit to Next Facility`, `Item processed` | En acheminement entre les centres de tri nationaux ou internationaux du partenaire. | « En transit » |
| `Out for Delivery`, `Item out for delivery` | En tournée avec le partenaire local pour livraison finale aujourd’hui. | « En livraison » |
| `Notice card left indicating where and when to pick up item` | Le transporteur partenaire a tenté la livraison en ton absence et laissé un avis de passage. | « Tentative de livraison » |
| `Item available for pickup at Post Office`, `Final Notice; Item will be returned to sender if not collected within 10 days` | Disponible au bureau de poste en attente de retrait (même un dernier avis signifie que le colis attend encore d’être retiré et n’a pas encore été renvoyé). | « Prêt à être retiré » |
| `Delivered`, `Delivered, Front Door/Porch`, `Delivered, In/At Mailbox`, `Shipment Delivered` | Livré à l’adresse du destinataire par le transporteur partenaire. | « Livré » |

## Acheminement transfrontalier vers les États-Unis, douane et relais partenaires

- **Expédition vers les États-Unis :** Chit Chats propose l’expédition vers les États-Unis avec dédouanement, droits acquittés (DDP) et aide aux documents ACEUM (CUSMA), annonce 2 à 6 jours ouvrés et publie ses mises à jour tarifaires américaines 2026 sur [chitchats.com](https://chitchats.com/). Voir [les colis retenus en douane](guide:customs).
- **Suivi chez le transporteur partenaire :** une fois que Chit Chats remet le colis, sa page de contact conseille d’utiliser le numéro du transporteur directement sur le site de suivi du partenaire.

## Colis Chit Chats retardé, silencieux ou manquant

- **Contacte d’abord le vendeur en ligne :** la [page de contact](https://chitchats.com/contact) de Chit Chats précise que son équipe voit les mêmes informations de suivi que toi et ne peut conseiller que l’expéditeur sur les questions liées au compte ; les destinataires doivent donc contacter le vendeur.
- **Colis en retard :** une fois la date estimée dépassée, la page de suivi de Chit Chats recommande de contacter le vendeur. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Livré ou introuvable :** vérifie ta boîte aux lettres, ton porche ou le bureau de poste local (surtout si Postes Canada a laissé un avis), puis contacte rapidement le vendeur. Voir [colis livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client Chit Chats et contacts canadiens

Canaux d’assistance officiels publiés sur [Chit Chats Contact](https://chitchats.com/contact) et [Chit Chats Support](https://support.chitchats.com/en/support/home) :

- **Téléphone sans frais (anglais et français — facturation et questions d’agence) :** `1-844-842-8777` (plus les numéros propres à chaque agence pendant les horaires locaux dans les 66 points au Canada).
- **Portail d’aide et de support :** [support.chitchats.com/en/support/home](https://support.chitchats.com/en/support/home) et [chitchats.com/contact](https://chitchats.com/contact).
- **Suivi public des envois :** [chitchats.com/tracking](https://chitchats.com/tracking).

> Méfie-toi des faux SMS demandant un petit paiement en ligne pour dédouaner un colis Canada/États-Unis. Vérifie toujours tout avis de douane ou d’affranchissement directement auprès du vendeur ou sur le suivi officiel d’USPS ou de Postes Canada. Voir [les arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis Chit Chats avec Peek

[Peek](/) interroge automatiquement Chit Chats comme unique candidat lorsque tu saisis un identifiant d’envoi Chit Chats de 10 caractères (`K4N8V3T6R2` ou `P7M3X9D5Q8`, inventés) et affiche « Chit Chats a ce colis. » dès que Chit Chats le confirme ; Peek reconnaît aussi directement les liens collés `chitchats.com/tracking/<10-car>`. Si tu dois choisir Chit Chats manuellement, appuie sur « Changer » lors de l’ajout ou sur « Changer de transporteur » sur l’écran du colis (rechercher « ChitChats » ou « Chit Chats Express » le trouve aussi).

Peek lit directement le suivi de Chit Chats et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « En livraison », « Prêt à être retiré », « Tentative de livraison », « Livré »), le libellé des scans de Chit Chats et du partenaire, ainsi que la ville de chaque scan sans son code postal ; un lieu contenant un numéro de rue est omis. Il affiche aussi le jour de livraison estimé, le poids en kilogrammes et les dimensions. Le nom du destinataire apparaît sous « Destinataire » sur ta propre page de colis, et la ville de destination peut clore l’itinéraire sur ta carte pendant le transit ; les liens partagés n’affichent ni l’un ni l’autre. Quand Chit Chats indique un partenaire connu de Peek, comme USPS ou Postes Canada, avec le numéro de suivi de ce partenaire (un numéro USPS sans son préfixe ZIP de routage), Peek vérifie que ce partenaire a bien le colis : le colis affiche alors « Livraison par » suivi du nom du transporteur et liste les deux numéros. Les scans propres à Chit Chats comportent une heure exacte. Ceux d’un partenaire arrivent avec une horloge étiquetée heure du Pacifique par Chit Chats mais parfois décalée de plusieurs heures : Peek les date donc sur le fuseau horaire de leur lieu (celui de New York pour `NEW YORK, NY`, celui de Toronto pour `TORONTO, ON`), un scan sans lieu sur le fuseau du scan voisin, sinon sur celui de Vancouver, et les affiche tous à l’heure de ton appareil. Peek omet le nom du service, les codes statut et la ville de l’expéditeur.

Tant que le colis est ouvert dans Peek, ou le jour de sa livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où le suivi affiche `Out for Delivery` ou `Item out for delivery`, toutes les 2 minutes à partir de 6 h sur l’horloge du dernier scan, tant que le suivi de Chit Chats répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelle, il vérifie toutes les heures quelle que soit ta façon de le suivre, puis toutes les 6 heures après sept jours à « Étiquette créée ». Quand le suivi de Chit Chats ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Quand le scan le plus récent remonte à quatre jours et que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La page du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Chit Chats. », avec un lien « Demande de l’aide à Chit Chats » vers la page de contact de Chit Chats. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Chit Chats. »

Tu peux choisir entre les réglages d’alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek se limite au suivi des mises à jour et ne peut ni déposer de réclamation, ni modifier d’adresse, ni contacter Chit Chats ou ses partenaires à ta place.

## Questions fréquentes sur le suivi Chit Chats

### À quoi ressemble un numéro de suivi Chit Chats ?

Les identifiants d’envoi Chit Chats comportent 10 lettres majuscules et chiffres (par exemple `K4N8V3T6R2` ou `P7M3X9D5Q8`, inventés). Une fois remis au partenaire, le colis reçoit aussi un code du transporteur partenaire (comme USPS ou Postes Canada).

### Qui livre un colis Chit Chats jusqu’à ma porte ?

Un transporteur partenaire s’en charge, par exemple [USPS](carrier:usps) aux États-Unis ou [Postes Canada](carrier:canada-post) au Canada. Le suivi de Chit Chats indique le partenaire et donne son numéro de suivi.

### Pourquoi mon colis Chit Chats marque-t-il une pause après `Departed from Chit Chats facility` ?

Le suivi de Chit Chats n’indique pas de motif. Son statut `In transit` correspond à la remise au transporteur partenaire, et les scans du partenaire apparaissent dès qu’il scanne le colis. Si la date de livraison estimée est dépassée, contacte le vendeur.

### Qui contacter si mon colis Chit Chats manque ?

Contacte le vendeur qui a expédié le colis. Selon [chitchats.com/contact](https://chitchats.com/contact), l’équipe de Chit Chats ne peut conseiller que l’expéditeur sur les questions liées au compte. La ligne sans frais `1-844-842-8777` sert à la facturation, aux questions d’agence et au support en français.

:::sources
- [Chit Chats : site officiel](https://chitchats.com/) – consolidateur canadien d’expéditions e-commerce vers les États-Unis, le Canada et l’international
- [Chit Chats : à propos](https://chitchats.com/about) – fondé en 2001 par Derek Nolan, plus de 50 M de colis, 66 agences canadiennes et plus de 100 000 expéditeurs
- [Chit Chats : portail de suivi](https://chitchats.com/tracking) – suivi par identifiant d’envoi de 10 caractères
- [Chit Chats : contact](https://chitchats.com/contact) – numéro sans frais `1-844-842-8777`, politique de renvoi vers le vendeur et annuaire des agences (URL d’aide liée par Peek)
- [Chit Chats : centre d’aide](https://support.chitchats.com/en/support/home) – centre d’assistance officiel
- [Universal Parcel Scraper : chit-chats](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chit-chats) – détection ID 10 caractères, règle de lien `chitchats.com/tracking/`, table de statuts propres et relayés, extraction ETA/poids/dimensions/service et nettoyage du préfixe 420 USPS
:::
