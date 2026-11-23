---
title: Suivi Metropolitan Warehouse (MWD) : meubles et aide
description: Suivez une commande Metropolitan Warehouse & Delivery (MWD) : numéro à 12 chiffres, statuts, rendez-vous de livraison, délais d’avarie de 72 h et contacts.
slug: suivi-metropolitan-warehouse
published: 2026-11-23
updated: 2026-11-23
---

Metropolitan Warehouse & Delivery (MWD) est un transporteur familial spécialisé dans la livraison de meubles et la logistique 3PL, fondé en 1989 à Perth Amboy dans le New Jersey. À travers un réseau de 47 hubs et plateformes de cross-docking, il livre à domicile pour des boutiques en ligne, enseignes d’ameublement et designers dans les 48 États américains contigus. Pour suivre une commande, saisissez votre numéro à 12 chiffres sur la [page de suivi MWD](https://www.gomwd.com/track-a-shipment) ou collez-le dans le champ ci-dessus.

## Numéro de suivi MWD : 12 chiffres

Le [formulaire de suivi MWD](https://main.gomwd.com/client/wixtrackorder) ne comporte qu’un seul champ, *Tracking #*, qui accepte exactement 12 chiffres et rien d’autre (chiffres inventés) :

| Numéro | Ce que c’est |
| --- | --- |
| `584927163048` | Le numéro de suivi MWD. La fiche de commande l’affiche par groupes de quatre chiffres : `5849 2716 3048`. |
| `POMS #`, `Client REF# 1`, `Client REF# 2`, `Carrier PRO #` | Autres références figurant sur la page de commande (interne MWD, boutique ou transporteur partenaire). Elles ne permettent pas de suivre la livraison. |

Le suivi ne demande ni code postal ni numéro de téléphone, mais il recourt à reCAPTCHA et peut afficher un captcha texte à recopier. Toute saisie autre que 12 chiffres renvoie `Please enter a valid tracking number of 12 digits.`, et un numéro inconnu de MWD affiche `Tracking id does not exist.`. Vérifiez le code dans l’e-mail du magasin ou contactez le vendeur si vous n’en avez reçu aucun ([comment trouver votre numéro de suivi](guide:find-tracking-number)). MWD propose également des pages de suivi en marque blanche aux boutiques ; votre marchand peut donc vous envoyer un lien sous son propre nom.

## Statuts de suivi Metropolitan Warehouse expliqués

L’historique de MWD s’affiche en anglais, du plus récent au plus ancien, avec la date, le libellé et la ville accompagnée de l’État. Voici les statuts recensés dans [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/metropolitan-warehouse), dans l’ordre habituel :

| Libellé MWD | Dans Peek | Ce que cela signifie pour vous |
| --- | --- | --- |
| `Order registered` | Étiquette créée | La commande est enregistrée chez MWD. |
| `Pickup document attached` | aucun changement | Document administratif joint pour l’enlèvement ; rien n’a bougé. |
| `Pickup scheduled for 11/02/2026` | Étiquette créée | MWD a planifié l’enlèvement chez le fournisseur, mais n’a pas encore le meuble. |
| `Pickup completed, No exceptions` | Pris en charge | MWD a récupéré la commande sans anomalie signalée. |
| `Order enroute to Metro-Hub` | En transit | En route vers un hub MWD. |
| `Furniture Received by Metro-Hub` | En transit | Scanné à l’arrivée dans un hub MWD (nommé par sa ville). |
| `Reached Delivery Hub` | En transit | Arrivé au hub chargé de votre livraison. |
| `Delivery Scheduled for WEEK OF 11/09/2026` | aucun changement | Livraison prévue durant cette semaine ; la date précise vient avec le rendez-vous. |
| `Delivery Rescheduled for WEEK OF 11/16/2026` | aucun changement | La livraison a été déplacée à une autre semaine. |
| `Delivery Appointment Canceled` | aucun changement | Le rendez-vous a été annulé ; un nouveau créneau doit suivre, sinon contactez le magasin ou MWD. |
| `Arrived at Delivery Location` | En livraison | L’équipe est arrivée à votre adresse. |
| `Delivery Completed, No Exceptions` | Livré | Livré sans réserve signalée (vérifiez malgré tout l’état avant le départ de l’équipe). |

La page de MWD affiche parfois le hub sous la forme `Metro-<Warehouse>-Hub` en laissant le nom vide ; Peek nettoie cette mention en `Metro-Hub`. Aucun statut connu de MWD n’annonce la tournée du matin : la première ligne du jour de livraison dans l’historique est `Arrived at Delivery Location`, au moment où l’équipe arrive sur place.

## Rendez-vous, niveaux de service et absence

**Rendez-vous et suivi le jour J.** Sur ses pages [White Glove Delivery](https://www.gomwd.com/services/white-glove-delivery) et [Services](https://www.gomwd.com/services), MWD indique que vous choisissez votre créneau via une prise de rendez-vous en libre-service, recevez des rappels automatiques puis un lien de suivi en direct le jour de la livraison indiquant la position de l’équipe et son heure d’arrivée estimée. La page de suivi MWD affiche aussi le créneau convenu et la preuve de livraison.

**Prestations de l’équipe.** Ce que fait l’équipe dépend du niveau acheté par le vendeur (indiqué sous *Service Type* sur la fiche MWD) :

| Niveau de service | Prestation incluse |
| --- | --- |
| Full White Glove | Installation dans la pièce choisie, assemblage léger et reprise de tous les emballages. |
| White Glove (No Assembly) | Même prestation pour les meubles préassemblés et tapis, sans montage. |
| Room of Choice | Dépôt à l’intérieur dans la pièce de votre choix, sans montage. |
| Threshold Delivery | Dépôt dans le premier espace abrité du logement (entrée ou garage), sans déballage ni montage. |
| Doorstep Delivery | Dépôt devant la porte extérieure, sans signature, pour les envois plus légers. |
| Curbside Delivery | Dépôt au bord du trottoir ou dans l’allée ; vous prenez le relais ensuite. |

Si le montage ou la reprise des cartons ne figurent pas dans le niveau affiché, voyez directement avec le commerçant qui vous a vendu la prestation.

**En cas d’absence.** Les pages grand public de MWD ne détaillent pas comment reprogrammer un rendez-vous manqué ; prévenez MWD ou le magasin avant le jour prévu. Les [conditions de transport](https://sso.gomwd.com/Account/TermsandConditions) précisent qu’si le destinataire refuse la livraison, ou si elle échoue du fait de l’expéditeur ou du destinataire, la responsabilité de MWD devient celle d’un entrepositaire : après une tentative d’avis par téléphone ou voie électronique, des frais de stockage peuvent débuter dès le jour ouvré suivant, un second et dernier avis est envoyé faute d’instructions sous 48 heures, et MWD se réserve le droit de mettre la marchandise en vente publique après 10 jours supplémentaires sans réponse. Plus de conseils dans [livraison manquée](guide:missed-delivery).

## Commande MWD endommagée, en retard ou manquante

**Meuble endommagé.** Inspectez chaque pièce avant le départ des livreurs. Les [conditions de transport](https://sso.gomwd.com/Account/TermsandConditions) (connaissement sur le portail client) imposent une réclamation écrite dans les **72 heures** suivant la livraison pour perte ou dommage, et autorisent MWD à réparer ou remplacer l’article. Sauf contrat écrit différent ou option *Enhanced Valuation* déclarée par l’expéditeur, la responsabilité maximale par défaut est limitée à 500 USD par envoi, et les dommages cachés sur un colis préemballé par l’expéditeur sont exclus si l’emballage ne montre aucun choc visible. Comme la boutique est le client de MWD, prévenez-la immédiatement. Voir aussi [colis endommagé](guide:damaged-parcel).

**Retard.** Tant que l’historique reste sur `Pickup scheduled for`, MWD n’a pas encore pris en charge la marchandise. Selon le [guide de la FTC sur la règle des ventes à distance](https://www.ftc.gov/business-guidance/resources/business-guide-ftcs-mail-internet-or-telephone-order-merchandise-rule), une commande n’est considérée comme expédiée qu’une fois remise au transporteur : si un vendeur en ligne, par téléphone ou par correspondance ne peut pas expédier dans le délai annoncé — ou sous **30 jours** si aucun délai n’était indiqué — il doit solliciter votre accord sur le retard et préciser que vous pouvez annuler contre un remboursement intégral rapide. Si le suivi se fige plusieurs jours après l’enlèvement, consultez [suivi qui ne se met pas à jour](guide:tracking-not-updating).

**Non-livraison ou colis introuvable.** Les mêmes conditions de transport fixent un délai de **30 jours**, après un temps raisonnable de livraison, pour déposer une réclamation écrite de non-livraison (et 6 mois après un refus écrit pour engager une action). Contactez le magasin et MWD, et consultez [indiqué livré mais non reçu](guide:delivered-not-received) ainsi que [colis perdu](guide:lost-parcel).

## Contacter Metropolitan Warehouse & Delivery

D’après la [page de contact MWD](https://www.gomwd.com/contact-us) :

- **Téléphone (Support Line) :** (800) 300-7417 — équipe joignable du lundi au vendredi (horaires précis non publiés).
- **E-mail général :** `comments@gomwd.com`.
- **Bureaux :** siège historique à Perth Amboy, New Jersey (mentionné sur les pages [About Us](https://www.gomwd.com/about-metropolitan) et [Services](https://www.gomwd.com/services)), et adresse *Corporate Office* affichée sur la page Contact : 310 Ramco Street, Suite 150, West Sacramento, CA 95691.

Le parcours *Info About My Order* de la page de contact vise les clients professionnels (les marques et boutiques expéditrices) : passez par votre vendeur si une intervention de l’équipe *Customer Success* est nécessaire.

## Suivre une commande MWD avec Peek

[Peek](/) ne peut pas déduire MWD d’après le numéro seul, car douze chiffres correspondent à de nombreux transporteurs ([pourquoi les formats se recoupent](guide:tracking-number-formats)). Collez tout de même le numéro : Peek suit plus de 4 500 transporteurs. **Aux États-Unis**, il interroge MWD parmi les premiers transporteurs à 12 chiffres — aux côtés de [BRT](carrier:brt), [Colis Privé](carrier:colis-prive) et [Purolator](carrier:purolator) (pour les numéros commençant par 0 à 6 avec chiffre de contrôle valide) — et affiche « Metropolitan Warehouse & Delivery a ce colis. » lorsque MWD détient la commande. **Hors des États-Unis**, Peek traite MWD en dernier recours : il interroge d’abord les autres transporteurs possibles et ne demande à MWD que si aucun ne connaît le numéro. Saisi dans le champ de cette page, le numéro est d’abord demandé à MWD, où que vous soyez. Vous pouvez aussi choisir Metropolitan Warehouse & Delivery avec « Changer » en l’ajoutant, ou « Changer de transporteur » sur un colis déjà ajouté (la recherche « MWD » le trouve aussi). Les espaces sont acceptés ; collez directement les 12 chiffres, car Peek ne possède pas de règle pour les liens MWD.

Peek lit directement le tracker de MWD et affiche le statut, l’historique dans les mots anglais de MWD, la ville et l’État de chaque étape sur la carte et le poids, en kilogrammes ou en livres selon vos réglages. Il affiche chaque heure dans le fuseau horaire de votre appareil, qu’il nomme sous l’historique. Peek laisse de côté votre ville et code postal de destination, les références commande/client, le numéro PRO d’un transporteur partenaire, le nombre d’articles, le volume et la barre d’étapes du portail MWD. Il n’affiche pas de date estimée, de preuve de livraison ni de position en direct de l’équipe, et ne suit pas un éventuel transporteur tiers.

Peek interroge directement MWD jusqu’à toutes les 10 minutes quand vous gardez la commande ouverte ou le jour de la livraison avec alertes activées, et toutes les 2 minutes à partir de 6 h heure locale le jour où MWD publie `Arrived at Delivery Location` ; sinon toutes les heures. Après 12 heures en « Étiquette créée » ou « Pris en charge », ou après deux jours sans nouveau scan, Peek repasse à une vérification par heure même si la fiche reste ouverte, puis toutes les 6 heures après sept jours encore en « Étiquette créée ». Via les services de suivi en relais, il vérifie toutes les heures, toutes les 3 heures après un jour sans mise à jour, et jusqu’à toutes les 15 minutes le jour de la livraison.

Si une commande à « Étiquette créée », « Pris en charge » ou « En transit » reste quatre jours sans nouveau scan, Peek la signale avec le jour de ce dernier scan (par exemple « Aucune mise à jour depuis lun 12 oct »), affiche « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Metropolitan Warehouse & Delivery. » et propose le lien « Demande de l’aide à Metropolitan Warehouse & Delivery » vers la page de contact de MWD. Au-delà de trois semaines sans nouveauté, Peek indique : « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Metropolitan Warehouse & Delivery. »

Vous pouvez activer des notifications pour « Chaque scan », « Étapes importantes » ou « Livraison seulement », qui débute chez MWD à l’étape `Arrived at Delivery Location`. Peek ne peut ni fixer ou déplacer un rendez-vous, ni déposer de réclamation, ni contacter MWD ou le magasin à votre place.

## Questions sur le suivi Metropolitan Warehouse

### Qui est Metropolitan Warehouse & Delivery ?

C’est une entreprise familiale américaine de transport de meubles et d’entreposage 3PL fondée en 1989 à Perth Amboy (New Jersey), dotée de 47 hubs et plateformes de cross-docking dans les 48 États contigus. Ce sont les boutiques, marques d’ameublement et designers qui la mandatent ; les particuliers ne la réservent pas directement.

### Que signifie « Delivery Scheduled for WEEK OF » ?

MWD prévoit votre livraison pendant la semaine de la date indiquée. Le jour exact et le créneau horaire sont fixés lors de la prise de rendez-vous. `Delivery Rescheduled for WEEK OF` signale simplement un décalage vers une autre semaine.

### Metropolitan Warehouse monte-t-il les meubles ?

Uniquement avec le niveau *Full White Glove*, qui comprend un assemblage léger et le retrait des emballages. Les autres niveaux — y compris *White Glove (No Assembly)* et *Room of Choice* — ne comprennent pas le montage. Vérifiez la ligne *Service Type* sur votre page de commande MWD.

### Comment déplacer un rendez-vous de livraison MWD ?

Les pages de MWD décrivent la réservation initiale en libre-service, mais pas la modification ultérieure. Appelez la ligne d’assistance au (800) 300-7417 du lundi au vendredi ou contactez la boutique qui vous a vendu le meuble.

### Quel est le délai pour signaler un dommage à MWD ?

Les conditions de transport sur le portail client fixent un délai de 72 heures après la livraison pour toute réclamation écrite en cas de perte ou d’avarie. Comme la boutique est la cliente de MWD, prévenez-la immédiatement elle aussi.

:::sources
- [Metropolitan Warehouse & Delivery : Accueil](https://www.gomwd.com/) – depuis 1989, ligne d’assistance
- [MWD : About Us](https://www.gomwd.com/about-metropolitan) – entreprise familiale, siège de Perth Amboy, 48 États contigus
- [MWD : Track a Shipment](https://www.gomwd.com/track-a-shipment) – suivi en ligne, créneau de rendez-vous et preuve de livraison
- [MWD : Track Your Order](https://main.gomwd.com/client/wixtrackorder) – champ unique à 12 chiffres, message d’erreur de longueur, reCAPTCHA / captcha texte
- [MWD : White Glove Delivery](https://www.gomwd.com/services/white-glove-delivery) – niveaux de service, prise de rendez-vous en libre-service, rappels et suivi en direct
- [MWD : Services](https://www.gomwd.com/services) – Doorstep Delivery, 47 hubs et plateformes de cross-docking, 5 hubs 3PL complets, suivi en marque blanche
- [MWD : Contact Us](https://www.gomwd.com/contact-us) – téléphone (800) 300-7417 lundi–vendredi, comments@gomwd.com, Corporate Office West Sacramento, parcours clients
- [MWD : Terms & Conditions](https://sso.gomwd.com/Account/TermsandConditions) – réclamations 72 h (dommages) et 30 jours (non-livraison), plafond 500 USD, frais de stockage en cas d’échec
- [FTC : Business Guide to the Mail, Internet, or Telephone Order Merchandise Rule](https://www.ftc.gov/business-guidance/resources/business-guide-ftcs-mail-internet-or-telephone-order-merchandise-rule) – expédition effective, règle des 30 jours, accord sur les retards et annulation remboursée
- [Universal Parcel Scraper : Metropolitan Warehouse & Delivery](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/metropolitan-warehouse) – format 12 chiffres, message de numéro inconnu, statuts et champs lus par Peek
:::
