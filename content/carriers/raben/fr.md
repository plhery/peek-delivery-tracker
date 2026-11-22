---
title: Suivi Raben : numéro d'envoi, statuts et livraison
description: Suis une palette ou un envoi de messagerie Raben : format du numéro, signification des statuts myRaben, déchargement, réclamations et contacts.
slug: suivi-raben
published: 2026-11-22
updated: 2026-11-22
---

Raben Group est un groupe logistique européen familial fondé en 1931 aux Pays-Bas et basé aujourd'hui près de Poznań, en Pologne. Son réseau routier transporte des palettes et du groupage (lots partiels) via des agences dans 17 pays européens, et livre aussi pour des boutiques en ligne : une commande volumineuse peut donc arriver par camion Raben. Suis-la avec le numéro d'envoi dans le champ « Shipment tracking » sur raben-group.com, ou colle le numéro dans le champ ci-dessus.

## Numéro d'envoi Raben : format et où le trouver

Raben attribue à chaque envoi son propre numéro. C'est l'entreprise qui a commandé le transport qui le détient, généralement la boutique ou le fabricant : cherche-le dans l'e-mail d'expédition ou sur ta page de commande. Les sociétés polonaises de Raben indiquent recevoir les coordonnées du destinataire de la part du donneur d'ordre : demande donc à l'expéditeur si tu n'as aucun numéro. Plus de détails dans [comment trouver son numéro de suivi](guide:find-tracking-number).

| Numéro (inventé) | À quoi il correspond |
| --- | --- |
| `274829173648291` | 27 suivi de 13 autres chiffres : le format d'un numéro d'envoi Raben dans le catalogue de Peek. |
| `27 4829 1736 4829 1` | Le même avec des espaces. Les espaces et tirets sont ignorés dans Peek. |
| `oftc.myraben.com/link/ShipmentInformation?ShipmentNumber=274829173648291` | Un lien direct vers un envoi sur le suivi Raben, tel qu'il s'ouvre depuis raben-group.com. Colle le lien entier dans Peek. |

[BRT](carrier:brt) en Italie et Yunda en Chine utilisent aussi des numéros à 15 chiffres : un numéro de ce format n'appartient donc pas toujours à Raben. Le moteur de Raben retrouve aussi un envoi par référence ou étiquette de colis, mais Peek suit uniquement le numéro d'envoi lui-même.

## Les statuts de suivi Raben expliqués

Le suivi de Raben, myRaben, affiche le statut, chaque étape avec son lieu et son heure, la date de livraison prévue, le poids, le nombre de colis et le volume, une barre de progression, des remarques et les coordonnées de l'agence Raben qui gère l'envoi. Voici les étapes, dans l'ordre habituel :

| Raben affiche | Ce qu'il signifie pour toi | Statut dans Peek |
| --- | --- | --- |
| `registered in the system` | L'expéditeur a réservé le transport. Raben a l'ordre, pas encore la marchandise. | « Étiquette créée » |
| `arrived to the pick-up place` | Le camion est arrivé chez l'expéditeur pour l'enlèvement. | « Étiquette créée » |
| `collected` | Raben a enlevé l'envoi chez l'expéditeur. | « Pris en charge » |
| `loaded` | Chargé dans un camion, vers le dépôt suivant ou pour la tournée de livraison. | « En transit » |
| `unloaded` | Déchargé au dépôt suivant. | « En transit » |
| `Inventory in a warehouse` | Pointé en entrepôt dans un dépôt. | « En transit » |
| `shipment in depo` | En dépôt. | « En transit » |
| `in progress - advice required` | En route. La livraison doit d'abord être annoncée au destinataire. | « En transit » |
| `in progress - fix delivery` | En route, pour une livraison à date fixe. | « En transit » |
| `in delivery` | En tournée de livraison. | « En livraison » |
| `arrived to the delivery place` | Le camion est arrivé à l'adresse de livraison. | « En livraison » |
| `delivered` | Livré. | « Livré » |
| `delivered D&D` | Livré et déchargé par le chauffeur. | « Livré » |
| `not delivered - self collection` | Non livré : marqué pour enlèvement au dépôt, même si une tournée peut encore suivre. Contacte l'agence Raben indiquée sur le suivi avant de te déplacer. | « Prêt à être retiré » |
| `not delivered - consignee not available` | Le chauffeur ne t'a pas trouvé sur place. | « Tentative de livraison » |

**Autres livraisons échouées.** `not delivered - delivery refused`, `not delivered - wrong address`, `not delivered - truck with lift required` (un camion à hayon est nécessaire) et `not delivered - wrong/missing docs` précisent le motif. Peek affiche « Tentative de livraison ».

**Blocages.** `not delivered - breakdown lorry` et `not delivered - strike` signalent un aléa sur la route ; `not delivered - at sender disposal` signifie que Raben garde l'envoi dans l'attente des instructions de l'expéditeur ; `cancelled` indique l'annulation du transport. Peek affiche « Problème signalé ».

**Livré avec réserve.** `delivered - damages`, `delivered - discrepancies`, `delivered - partly returned` et `delivered with delay` signifient tous que la marchandise a été remise, en précisant l'incident. Peek affiche « Livré ».

**Changements de plan.** `new delivery date agreed with consignee` et `shipment parameters updated` modifient une date ou des données : la marchandise n'a pas bougé.

**Douane.** Un envoi passant en douane reçoit des étapes comme `customs: clearance in progress` et `customs clearance completed` ; `customs: lack of invoices / incomplete invoices` signifie qu'il manque des factures pour la douane, demande donc à la boutique. Voir [les colis retenus en douane](guide:customs) et [la signification des statuts de suivi](guide:tracking-statuses).

## Comment Raben livre : palettes, déchargement et délais

La FAQ transport palettisé de Raben Pologne explique comment se déroule une livraison. Les options sont réservées par le client de Raben, c'est-à-dire l'entreprise qui a commandé le transport (souvent la boutique) :

- **Déchargement.** Le déchargement incombe au destinataire : Raben n'en est pas responsable et n'est pas tenue de fournir chariot élévateur ou rampe. Le client peut réserver le déchargement manuel par le chauffeur (HUN) ou le déchargement au lieu de livraison (UNLO). Pour un article lourd, demande à la boutique ce qu'elle a réservé.
- **Délais.** En Pologne, une palette prend un jour ouvré en standard, ou deux selon le service acheté ; à l'étranger, le délai dépend du pays de destination. Le client peut aussi réserver un jour fixe (FIX), une livraison avant 8 h, 10 h, 12 h ou 16 h (ND 08 à ND 16), ou un jour et une heure convenus (WXACT).
- **En cas d'absence.** Si une livraison échoue du fait du destinataire, Raben peut facturer le client.
- **Photos de livraison.** Avec Picture Confirming Delivery (PCD), un service gratuit choisi par le client, ta présence n'est pas indispensable. Le chauffeur dépose la marchandise où demandé et photographie l'étiquette, l'envoi et l'ensemble ; les photos tiennent lieu de bon de livraison.

## Envoi Raben en retard, endommagé ou non livré

**Le suivi n'avance plus.** Si les étapes s'arrêtent pendant plusieurs jours, demande à la boutique ou à l'agence Raben nommée sur le suivi. Consulte [le suivi qui ne se met plus à jour](guide:tracking-not-updating).

**En retard.** Dans l'UE, la boutique doit livrer sous 30 jours sauf délai différent convenu, rappelle le portail européen Your Europe. Passé ce délai, tu peux accorder un délai supplémentaire, puis annuler et te faire rembourser.

**Endommagé ou perdu.** Le vendeur répond des dommages entre l'expédition et la réception, et Your Europe recommande de contacter le vendeur en premier. Voir [colis endommagé](guide:damaged-parcel) et [colis perdu](guide:lost-parcel). Les règles propres à Raben :

- **Pologne.** Raben Logistics Polska accepte les réclamations écrites de l'expéditeur ou du destinataire : via myClaim, un formulaire en ligne sans compte myRaben, ou un formulaire envoyé par courrier, fax ou e-mail. Signale les dommages visibles à la livraison et les dommages cachés dans les 7 jours suivant la livraison (en transport international, les dimanches et jours fériés ne comptent pas). Raben répond dans les 30 jours après réception du dossier complet. Un envoi national est réputé perdu à partir du 31e jour après la date prévue de livraison.
- **Allemagne.** Raben Allemagne demande de vérifier l'emballage et l'intégralité de l'envoi à la livraison, et de faire inscrire tout dommage visible en détail sur le bon de livraison contresigné par le chauffeur. Déclare le dommage par écrit à l'agence Raben compétente ; pour un dommage caché, dès sa découverte et au plus tard 7 jours après la livraison.

## Contact et service client Raben

Coordonnées indiquées sur les sites de Raben :

- **Contact dédié à ton envoi.** Raben invite à contacter l'interlocuteur affiché sur la page de suivi de l'envoi, que Peek ne reprend pas.
- **Horaires.** Le service client de Raben Pologne travaille du lundi au vendredi de 8 h à 16 h (heure de Pologne), vise une réponse sous 3 heures et répond en polonais par défaut.
- **Agences.** La [page de contact](https://www.raben-group.com/contact) de Raben trouve un dépôt par code postal et liste les agences par pays ; chaque site national, comme polska.raben-group.com, publie ses propres coordonnées.

## Suivre un envoi Raben avec Peek

Le format de Raben est partagé avec [BRT](carrier:brt) et d'autres transporteurs à 15 chiffres, donc [Peek](/) ne désigne pas Raben sur la seule base du numéro. Colle-le quand même : Peek suit plus de 4 500 transporteurs. Lors de l'ajout, Peek interroge les transporteurs compatibles (« Vérification des services de suivi… ») et affiche « Raben a ce colis. » lorsque le suivi Raben connaît le numéro ; si plusieurs le reconnaissent, tu choisis. Si aucun ne le trouve encore, l'envoi affiche « Aucun historique de suivi pour le moment » pendant que Peek continue de vérifier, ou « En attente de Raben » une fois Raben choisi. Pour choisir toi-même le transporteur, appuie sur « Changer » puis sélectionne « Raben » sous « Correspond à ce numéro », ou recherche-le (« Raben Group » le trouve aussi). Sur un envoi déjà ajouté, utilise « Changer de transporteur ».

Un lien de myraben.com ou oftc.myraben.com classe immédiatement l'envoi chez Raben. Si ton numéro Raben a un autre format, sélectionne Raben manuellement : Peek interroge le suivi Raben pour toute suite de 4 à 40 lettres et chiffres contenant au moins un chiffre.

Peek lit le suivi Raben en anglais : le statut, chaque étape dans la formulation de Raben avec la ville et le pays, le jour de livraison prévu (une date sans heure) ou, dès que la tournée du chauffeur en fixe un, le créneau horaire avec ses heures, le poids et l'heure de livraison. Les horaires s'affichent dans ton fuseau horaire (« Heures dans ton fuseau horaire : »). Les étapes qui modifient seulement une date ou des paramètres conservent le statut précédent. Peek laisse de côté le nombre de colis, le volume, la barre de progression, les remarques et le contact de l'agence. Le suivi public ne donne ni nom d'expéditeur ou de destinataire, ni adresse, ni point de retrait, ni service, ni preuve de livraison.

Peek interroge directement Raben jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes le jour où Raben scanne l'envoi en livraison — à partir de 6 h (heure de Pologne) ou, dès qu'un créneau horaire est annoncé, une demi-heure avant son ouverture — tant que l'envoi est ouvert dans Peek ou le jour de la livraison avec les notifications activées. Après deux jours sans nouvelle (12 heures tant que l'envoi reste sur « Étiquette créée » ou « Pris en charge »), Peek passe à une vérification par heure. Pour les notifications, choisis « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ». Après quatre jours sans scan en transit, hors douane ou incident signalé, Peek affiche « Aucune mise à jour depuis… » et rappelle qu'« Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Raben. ». Peek ne peut pas contacter Raben ou la boutique à ta place, réserver un déchargement ni déposer une réclamation.

## Questions fréquentes sur le suivi Raben

### Qu'est-ce que Raben Group ?

Un groupe logistique européen familial, fondé en 1931 à Winterswijk aux Pays-Bas et basé à Robakowo près de Poznań en Pologne. Il transporte des palettes et lots partiels par route dans 17 pays, généralement à la demande de l'expéditeur.

### Comment suivre un envoi Raben ?

Saisis le numéro d'envoi dans le champ « Shipment tracking » sur raben-group.com puis clique sur « Search » : le suivi Raben s'ouvre dans un nouvel onglet. Ou colle le numéro ou le lien de suivi dans le champ ci-dessus.

### Le chauffeur Raben déchargera-t-il ma commande ?

Selon les règles de Raben Pologne, uniquement si l'entreprise qui a commandé le transport a réservé cette option : le déchargement relève du destinataire, et le déchargement par le chauffeur est un service supplémentaire (HUN ou UNLO). Une étape `delivered D&D` signifie que le chauffeur a livré et déchargé.

### Quel est le délai de livraison de Raben ?

En Pologne, un ou deux jours ouvrés selon le service choisi par l'expéditeur ; à l'étranger, cela dépend du pays de destination. Le suivi Raben affiche la date prévue de livraison.

### Qui contacter pour une livraison Raben endommagée ?

D'abord la boutique : dans l'UE, le vendeur est responsable jusqu'à la réception des biens. En Pologne, tu peux aussi déposer une réclamation écrite directement auprès de Raben Logistics Polska ; les dommages cachés doivent être signalés dans les 7 jours suivant la livraison.

:::sources
- [Raben Group: home page](https://www.raben-group.com/) – champ de suivi, « Search », nouvel onglet, 17 pays
- [Raben Group: Customer Zone](https://www.raben-group.com/customer-zone) – un numéro par envoi, date prévue, contact affiché sur la page de statut
- [Raben Group: Contact](https://www.raben-group.com/contact) – recherche de dépôt, agences par pays
- [Raben Group: Road transport and international transport](https://www.raben-group.com/services/road-network) – groupage
- [Raben Group: E-commerce](https://www.raben-group.com/e-commerce) – livraisons pour boutiques en ligne
- [Raben Group: Picture Confirming Delivery](https://www.raben-group.com/it-solutions/pcd) – service gratuit, photos, bon de livraison
- [Raben Group: Raben Group in Europe](https://www.raben-group.com/about-us/raben-group-in-europe) – 1931, Winterswijk, entreprise familiale, 17 pays
- [Raben Group: Imprint](https://www.raben-group.com/imprint) – Robakowo près de Poznań
- [Raben Poland: Pytania i odpowiedzi - Transport paletowy](https://polska.raben-group.com/strefa-klienta/pytania-i-odpowiedzi-transport-paletowy) – déchargement, HUN, UNLO, délais, FIX, ND, WXACT, frais d'échec, PCD, horaires du service client, langue polonaise
- [Raben Poland: Strefa uczestnika przewozu](https://polska.raben-group.com/strefa-uczestnika-przewozu) – coordonnées du destinataire fournies par le donneur d'ordre
- [Raben Logistics Polska: Claims](https://polska.raben-group.com/en/customer-zone/raben-logistics-polska/claims-raben-logistics-polska) – qui réclame, procédure, dommages visibles et cachés, perte, réponse sous 30 jours
- [Raben Germany: Transportschäden – Wir helfen Ihnen](https://deutschland.raben-group.com/kundenbereich/reklamationen) – vérifications à la livraison, déclaration écrite, 7 jours
- [Your Europe: Your rights when shopping in the EU](https://europa.eu/youreurope/citizens/consumers/shopping/shopping-consumer-rights/index_en.htm) – 30 jours, responsabilité du vendeur, contacter d'abord le vendeur
- [Universal Parcel Scraper: Raben](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/raben) – format de numéro, liens, références, données affichées et lues par Peek, étapes
:::
