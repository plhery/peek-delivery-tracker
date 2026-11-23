---
title: Suivi APC Overnight : numéros, statuts et livraisons
description: Suivre un colis APC : numéro d’envoi à 7 chiffres et numéro à 22 chiffres, signification de Carded, reprogrammation d’une livraison manquée et dépôt local.
slug: suivi-apc-overnight
published: 2026-11-23
updated: 2026-11-23
---

APC Overnight, qui se fait désormais appeler The APC, est le plus grand réseau indépendant de livraison de colis du Royaume-Uni : plus de 90 dépôts détenus localement qui livrent pour la plupart le jour ouvré suivant. Suis un colis sur APC Choice avec le numéro d’envoi et ton code postal, ou colle le numéro long ou le lien de suivi dans le champ ci-dessus. Tu trouveras le numéro dans la confirmation d’expédition de l’expéditeur, dans un e-mail ou un SMS d’APC ou du commerçant, ou sur l’avis de passage « Sorry we missed you » d’APC.

## Numéro de suivi APC : à quoi il ressemble

APC parle d’un seul numéro : un numéro d’envoi (« consignment number ») est la référence de suivi à 7 chiffres attribuée au colis. Son formulaire de suivi demande ce numéro et le code postal de livraison ensemble.

Les boutiques envoient souvent un numéro plus long à la place : un numéro à 22 chiffres dans un lien APC Choice (`apcchoice.apc-overnight.com/track-parcel?id=…`). Il commence par la date de réservation et se termine par ce même numéro d’envoi à 7 chiffres.

:::anatomy 20260715 4829173 7364058
- 20260715 | La date de réservation, 20AAMMJJ : ici le 15 juillet 2026
- 4829173 | Sept autres chiffres
- 7364058 | Le numéro d’envoi à 7 chiffres que demandent les pages d’APC
:::

Avec le numéro long dans le lien, APC Choice ouvre l’historique du colis sans code postal. Le code postal n’est demandé que pour voir la preuve de livraison ou modifier la livraison.

Deux points à vérifier :

- Un numéro à 22 chiffres qui ne commence pas par une vraie date (`4829173640582917364058`, par exemple) n’est pas un numéro APC. Des numéros de cette longueur peuvent appartenir à [USPS](carrier:usps), à la [Poste autrichienne](carrier:austrian-post) ou à Estafeta.
- Les numéros APC ne ressemblent pas à ceux de [Royal Mail](carrier:royal-mail) (qui finissent par `GB`), de [Parcelforce](carrier:parcelforce), d’[Evri](carrier:evri) ou de [DPD UK](carrier:dpd-uk).

Si rien ne s’affiche, la FAQ d’APC indique les causes habituelles : l’expéditeur n’a pas encore remis le colis, il n’a pas encore été scanné dans le réseau ou le numéro comporte une faute de frappe. Réessaie plus tard ou vérifie le numéro auprès de l’expéditeur. Aucun numéro ? Consulte [comment trouver un numéro de suivi](guide:find-tracking-number).

## Statuts de suivi APC expliqués

APC Choice est uniquement en anglais. Le site affiche un résumé de l’envoi (date d’expédition, poids, service comme le jour ouvré suivant), puis le parcours (« Parcel journey ») et le résumé de livraison (« Delivery summary ») de chaque colis, une ligne par scan avec le jour et l’heure entre parenthèses.

| APC affiche | Ce que ça signifie pour toi |
| --- | --- |
| `At sending depot` | Le dépôt local de l’expéditeur a collecté le colis et l’a scanné. |
| `At Hub` | Au centre de tri national d’APC, le National Sortation Centre de Cannock. |
| `At delivery depot` | Au dépôt local qui va te le livrer. |
| `Out for delivery` | Dans la camionnette aujourd’hui. APC donne un créneau estimé de 2 heures. |
| `Carded` | Le chauffeur est passé, personne n’était là et un avis « Sorry we missed you » a été laissé. |
| `Delivery Rearranged By Customer` | Toi (ou une personne à l’adresse) as changé le jour ou l’option sur APC Choice. |
| `Delivered` | Livré. La preuve de livraison est accessible avec ton code postal. |
| `Returned To Sender` | En cours de retour vers la boutique, par exemple après des tentatives manquées sans réponse. |

`Delivery Rearranged By Customer` n’est pas un déplacement : le colis reste là où il était jusqu’au nouveau jour choisi. Si le suivi reste silencieux entre deux scans, APC indique que le colis peut être en transit entre dépôts, en attente de traitement au dépôt local, ou que le scanner du chauffeur peut avoir un problème. Consulte [un suivi qui ne se met pas à jour](guide:tracking-not-updating) pour savoir quand t’inquiéter.

## Délais de livraison, livraisons manquées et retrait en dépôt APC

APC indique que la plupart des livraisons au Royaume-Uni arrivent le jour ouvré suivant l’expédition, selon la zone. L’expéditeur choisit le service : lendemain avant 10 h 30, avant midi (heure britannique) ou jour ouvré suivant standard ; la livraison le samedi est proposée dans de nombreuses zones si l’expéditeur la commande. Si la boutique a transmis tes coordonnées à APC, tu peux recevoir le créneau de 2 heures par e-mail ou SMS une fois la tournée planifiée, et APC Choice montre la proximité du chauffeur.

APC Choice permet de modifier la livraison jusqu’à ce que le colis soit livré, même après un avis de passage. Selon le service et ce que l’expéditeur autorise, tu peux :

- reprogrammer à un jour qui te convient ;
- retirer le colis dans ton dépôt local APC ;
- choisir un lieu sûr ou un voisin ;
- le faire déposer dans un commerce Parcelly (plus de 2 500 au Royaume-Uni).

**Lieu sûr :** APC ne peut laisser un colis sans signature qu’avec l’autorisation de l’expéditeur, ou la tienne sur APC Choice si elle est permise. Les adresses professionnelles n’ont ni option lieu sûr ni livraison chez un voisin, et les passages y commencent à partir de 8 h (heure britannique).

**Après un avis de passage :** les chauffeurs ne peuvent pas repasser le même jour, et le retrait au dépôt n’est pas non plus possible ce jour-là. Tu peux reprogrammer une fois de plus après la première tentative. Une nouvelle livraison utilise le même service ; un passage à un créneau horaire précis ou au samedi doit venir de l’expéditeur.

**Retrait au dépôt :** fais la demande avant 19 h (heure britannique) pour un retrait le lendemain, et apporte une pièce d’identité ainsi qu’un justificatif de domicile. Vérifie d’abord les horaires d’ouverture du dépôt.

**Trois jours ouvrés :** si APC n’a aucune nouvelle dans les trois jours ouvrés suivant une livraison manquée, le colis peut repartir chez l’expéditeur.

## Colis APC en retard, endommagé ou perdu

Le contrat d’APC est conclu avec l’expéditeur : c’est donc la boutique qui gère les réclamations. En cas de dommage, la FAQ d’APC indique de contacter directement l’expéditeur. Préviens vite la boutique, car ses propres délais auprès d’APC sont courts : pour un colis endommagé ou partiellement manquant, elle doit écrire à APC dans les trois jours ouvrés suivant la livraison. Conserve le carton et l’emballage, car APC peut refuser une réclamation sans eux. L’indemnisation d’APC à l’expéditeur est plafonnée à 100 £ pour un envoi de colis et à 50 £ pour les petits paquets.

Si le suivi indique livré mais que tu n’as rien reçu, la preuve de livraison (photo, point GPS, date et heure) se trouve sur APC Choice avec ton code postal. Contacte ensuite la boutique, et consulte [colis indiqué livré mais non reçu](guide:delivered-not-received).

## Contacter APC

APC ne dispose ni de ligne téléphonique nationale, ni de chat, ni d’adresse e-mail générale pour les destinataires ; ses pages indiquent ces canaux :

- **Ton dépôt local :** le [Depot Finder](https://apc.co.uk/depot-finder/) affiche ses coordonnées et ses horaires d’ouverture à partir de ton code postal. APC Choice indique aussi le dépôt livreur et son adresse e-mail sous « APC Pinpoint ».
- **Formulaire Customer Service Enquiry** sur [apc.co.uk/contact-us](https://apc.co.uk/contact-us/) : renseigne le numéro d’envoi et le code postal de livraison ; APC le transmet à ton dépôt local, qui répond par téléphone ou par e-mail.

> APC indique qu’elle ne demande jamais de paiement ni de coordonnées bancaires pour une livraison. Ne clique pas sur les liens d’un tel message : transfère l’e-mail à phishing@apc-overnight.com et signale-le à Action Fraud.

## Suivre un colis APC avec Peek

Colle le numéro à 22 chiffres ou un lien `apcchoice.apc-overnight.com` avec `id=` dans [Peek](/), sans code postal. Comme ce format est partagé, Peek interroge à la fois APC Overnight et la Poste autrichienne, puis affiche « APC Overnight a ce colis. » dès qu’APC le reconnaît. Un numéro à 22 chiffres sans vraie date n’est pas proposé à APC et ne vient pas d’APC : vérifie-le auprès de la boutique. Le numéro d’envoi à 7 chiffres seul ne peut pas être suivi ici, et les liens `apc.co.uk` ne sont pas lus.

Les heures d’APC n’indiquent pas de fuseau horaire : Peek les lit à l’heure britannique et affiche l’historique propre d’APC. Il peut aussi indiquer le poids. `Delivered` et `Out for delivery` apparaissent dans les mots de Peek (« Livré » et « En livraison ») ; les autres statuts gardent le libellé d’APC. Peek n’affiche pas de date de livraison prévue, de créneau de 2 heures, de point de retrait, de preuve de livraison ni de signataire, et la reprogrammation d’une livraison reste dans APC Choice.

Tant que tu gardes le colis ouvert dans Peek, ou le jour de livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes, et à partir de 6 h (heure britannique) le jour où le colis passe en livraison jusqu’à toutes les 2 minutes lorsque le suivi direct d’APC Choice répond, ou toutes les 5 minutes après une vérification par navigateur. Après deux jours sans nouvelle (12 heures tant que le colis reste à « Étiquette créée » ou « Pris en charge »), Peek passe à une vérification toutes les heures ; et s’il reste à « Étiquette créée » pendant sept jours, toutes les 6 heures.

Si un colis encore à « Étiquette créée », « Pris en charge » ou « En transit » reste quatre jours sans nouveau scan, Peek le signale par « Aucune mise à jour depuis quatre jours » (ou « Aucune mise à jour depuis … »), rappelle qu’« Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger APC Overnight. » et propose « Demande de l’aide à APC Overnight ». Tu peux choisir les notifications « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut pas contacter APC ou la boutique à ta place.

## Questions sur le suivi APC

### Où est mon colis APC ?

Au dernier scan affiché sur APC Choice : le dépôt d’expédition, le centre de tri de Cannock, ton dépôt de livraison ou la camionnette. Pour en savoir plus, ton dépôt local a la réponse ; trouve-le avec le Depot Finder.

### Combien de temps APC conserve-t-elle les colis ?

APC peut renvoyer un colis à l’expéditeur si tu ne réponds pas dans les trois jours ouvrés suivant une livraison manquée. Reprogramme la livraison ou demande un retrait au dépôt avant ce délai.

### À quelle heure livre APC ?

Cela dépend du service payé par l’expéditeur : avant 10 h 30, avant midi (heure britannique) ou dans la journée du jour ouvré suivant. Quand le colis est en tournée, APC Choice et l’e-mail ou le SMS d’APC donnent un créneau de 2 heures.

### APC est-elle la même société que Parcelforce ?

Non. APC est The Alternative Parcels Company Limited, un réseau indépendant de dépôts locaux, alors que Parcelforce est un autre transporteur avec ses propres numéros finissant par `GB` ou commençant par `PBAB`.

:::sources
- [APC: Receiving a Parcel](https://apc.co.uk/receiving-a-parcel/) – livraison le jour ouvré suivant, services, options APC Choice, lieu sûr, voisin, livraison manquée, retrait en dépôt, délai de trois jours, prévention du phishing
- [APC: APC Choice tracking and FAQ](https://apcchoice.apc-overnight.com/track-parcel) – numéro d’envoi à 7 chiffres et où le trouver, statuts, créneau de 2 heures, pauses du suivi, nouvelle reprogrammation, adresses professionnelles, dommages via l’expéditeur
- [APC: Contact Us](https://apc.co.uk/contact-us/) – formulaire Customer Service Enquiry transmis au dépôt local
- [APC: Depot Finder](https://apc.co.uk/depot-finder/) – coordonnées et horaires des dépôts par code postal
- [APC: Terms and Conditions of Carriage](https://apc.co.uk/terms-conditions-of-carriage/) – délais de réclamation, plafond de 100 £, emballage, trois jours ouvrés, preuve de livraison
- [APC: Home](https://apc.co.uk/) – depuis 1994, plus de 90 dépôts membres, raison sociale, National Sortation Centre
- [APC: Our Products](https://apc.co.uk/our-products/) – commerces Parcelly, position du chauffeur, preuve de livraison photo et GPS
- [APC: Parcelly partnership expands to 2,500 stores](https://apc.co.uk/the-apc-expands-parcelly-partnership-to-2500-stores-across-the-uk/) – réseau Parcelly
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek lit sur APC Choice et comment il reconnaît le numéro
:::
