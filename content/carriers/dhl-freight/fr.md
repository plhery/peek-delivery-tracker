---
title: Suivi DHL Freight : codes ActiveTracing, statuts et aide
description: Suis un envoi routier ou ferroviaire DHL Freight sur ActiveTracing : codes JND, numéros de commande, ID d’envoi, SSCC, rendez-vous et contact.
slug: suivi-dhl-freight
published: 2026-11-24
updated: 2026-11-24
---

DHL Freight (*DHL Freight GmbH*, division de DHL Group) est la branche européenne de fret routier et ferroviaire de DHL, avec des filiales dans plus de 40 pays et plus de 45 millions de tonnes acheminées chaque année en LTL (groupage), PTL (lots partiels), FTL (lots complets), transport sous température dirigée, haute sécurité et fret ferroviaire européen. Outre le fret palettisé B2B (comme *EuroConnect*), DHL Freight livre aussi des commandes résidentielles volumineuses (*DHL Home Delivery*) et des envois nationaux suédois *DHL Service Point*. Tu peux consulter une expédition sur [DHL ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) ou coller ta référence dans le champ ci-dessus.

## Numéros de suivi DHL Freight : codes de commande, ID d’envoi et codes JND

DHL ActiveTracing accepte plusieurs types de références et conserve l’historique des expéditions en ligne pendant **six mois** :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `JND48291736405829173` | **DHL Package ID / License Plate** de 20 caractères (`JND` + 17 chiffres). |
| `FRA-EC-4829173` | **Code de commande DHL (Order code)** : code terminal sur 3 lettres + code produit sur 2 lettres (comme `EC` pour EuroConnect) + 7 chiffres (avec ou sans tirets). |
| `4829173640` | **ID d’envoi (Consignment ID)** : 9, 10 ou 11 chiffres (les ID à 10 chiffres sont courants pour les envois nationaux suédois *DHL Service Point*). Partage le format à 10 chiffres avec [DHL](carrier:dhl), [Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis) et [Packeta](carrier:packeta). |
| `00348291736405829177` | Identifiant colis **GS1 SSCC** à 20 chiffres commençant par `00` (clé de contrôle GS1 valide). Partage le format SSCC à 20 chiffres avec [USPS](carrier:usps) et [NZ Post](carrier:nz-post). |

Les colis standards en Allemagne passent par [DHL](carrier:dhl) (*DHL Paket*) et les documents ou colis express internationaux par avion par [DHL Express](carrier:dhl-express), tandis que les palettes, les meubles lourds et le fret routier ou ferroviaire européen se suivent sur le portail ActiveTracing de DHL Freight. Voir [les formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts DHL Freight ActiveTracing

ActiveTracing donne pour chaque ligne une phrase de statut, en anglais ou en allemand, le terminal (par exemple `DE-KOBLENZ`) ainsi que la date et l’heure du terminal, sans fuseau horaire indiqué :

| DHL Freight indique (EN / DE) | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Consignment created`, `Collection arranged - OK`, `Out for Collection`, `Not Collected - ...` / `Auftrag erfasst`, `In Abholung` | Ordre de fret enregistré ou chauffeur envoyé pour l’enlèvement ; la marchandise est encore chez l’expéditeur. | « Étiquette créée » |
| `Collected`, `Shipment handover by sender` / `Abholung` | Enlevé chez l’expéditeur ou déposé dans un DHL Service Point en Suède. | « Pris en charge » |
| `Received at terminal`, `Shipped from terminal`, `Arrival at destination`, `Loaded into ferry`, `Direct delivery`, `Receiver notified`, `Shipment in warehouse - Awaiting delivery appointment` / `Delivery appointment booked` / `Empfangen im Terminal`, `Verladen`, `Avisierung` | En acheminement entre terminaux DHL Freight, en traversée en ferry, dédouané (`Customs cleared`) ou retenu au terminal de destination en attente d’un rendez-vous de livraison. | « En transit » |
| `On hold - in customs clearance` | Retenu pour le dédouanement transfrontalier (par ex. Suisse, Royaume-Uni ou Norvège). | « À la douane » |
| `Out for Delivery` / `In Zustellung` | Chargé dans le camion local pour livraison aujourd’hui. | « En livraison » |
| `Shipment in warehouse - ready for self collection by consignee` | Prêt à être retiré au terminal DHL Freight local. | « Prêt à être retiré » |
| `Attempted DHL delivery - ...` | Tentative de livraison non aboutie (destinataire fermé ou refus, par exemple). | « Tentative de livraison » |
| `Shipment in warehouse - waiting for consignee payment`, `Shipment delivered with exception - partly delivered`, `Exception occurred - Strike`, `New shipping instruction - consignment cancelled by sender` | Blocage pour paiement, livraison partielle, perturbation liée à une grève ou annulation par l’expéditeur. | « Problème signalé » |
| `New shipping instruction - shipment returned` | Envoi renvoyé à l’expéditeur selon de nouvelles instructions. | « Retourné à l’expéditeur » |
| `Delivered`, `Received by consignee`, `Picked up by receiver`, `Shipment delivered with exception` / `Zugestellt` | Livré au destinataire ou retiré au terminal / Service Point. | « Livré » |

## Rendez-vous de livraison (*Avisierung*), douane et règles de fret

D’après [DHL Freight Allemagne](https://www.dhl.com/de-en/home/freight.html) et le [Centre d’aide DHL Freight](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) :

- **Rendez-vous de livraison (*Avisierung*) :** un envoi peut rester au terminal de destination sur `Shipment in warehouse - Awaiting delivery appointment` jusqu’à ce qu’un jour de livraison soit convenu avec le destinataire ; `Delivery appointment booked` apparaît ensuite. S’il attend, contacte l’expéditeur ou le terminal de livraison.
- **Conservation sur six mois dans ActiveTracing :** [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) affiche l’historique des expéditions des **six derniers mois** en anglais, allemand, espagnol, français, suédois, polonais et italien.
- **Douane européenne transfrontalière :** le fret routier entre l’UE et les pays voisins hors UE (comme le Royaume-Uni, la Suisse ou la Norvège) nécessite un dédouanement (`On hold - in customs clearance`). Pour les colis e-commerce hors UE entrant dans l’UE, le droit de douane de 3 € sur les envois jusqu’à 150 € et les frais de gestion UE de 2 € s’appliquent dans le cadre de la [réforme douanière de l’UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) ; voir [les colis retenus en douane](guide:customs).

## Envoi DHL Freight retardé, endommagé ou livré partiellement

C’est l’expéditeur qui a réservé le transport auprès de DHL Freight : contacte donc le vendeur ou l’expéditeur en premier lieu.

- **Dommages visibles ou colis manquants (`partly delivered`) :** vérifie la marchandise et le nombre de pièces dès la réception et préviens aussitôt l’expéditeur. Le Centre d’aide DHL Freight propose une page *How to Prepare a Claim*.
- **En attente de rendez-vous ou de paiement :** si ActiveTracing affiche `Awaiting delivery appointment` ou `waiting for consignee payment`, contacte immédiatement le terminal de destination indiqué sur le dernier scan (ou l’expéditeur).
- Voir aussi [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating), [colis livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts DHL Freight

Canaux d’assistance publiés sur le [Centre d’aide DHL Freight](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) et [DHL Freight Allemagne](https://www.dhl.com/de-en/home/freight.html) :

- **Centre d’aide pour le fret routier et ferroviaire européen :** [dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) — FAQ, *How to Prepare a Claim*, Incoterms 2020, conditions générales et règles sur les marchandises dangereuses.
- **Viva et recherche de terminaux :** le Centre d’aide met à disposition **Viva**, l’assistant client IA de DHL Freight, ainsi que *Freight Terminals and Locations* pour trouver les terminaux et agences par pays.
- **Portail professionnel :** *myDHLFreight*, avec tutoriels vidéo et FAQ dans le Centre d’aide.

> Ignore les liens SMS ou e-mail non sollicités demandant un paiement en ligne inattendu pour « libérer » un envoi DHL Freight. Vérifie toujours le statut directement sur [activetracing.dhl.com](https://activetracing.dhl.com/DatPublic/datSelection.do) ou auprès du terminal DHL Freight de destination. Voir [les arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un envoi DHL Freight avec Peek

[Peek](/) interroge DHL Freight pour un code `JND` (`JND48291736405829173`, inventé), le seul transporteur consulté pour ce format, et affiche « DHL Freight a ce colis. » dès qu’ActiveTracing répond. Pour un ID d’envoi à 10 chiffres (`4829173640`), Peek interroge DHL Freight parmi les transporteurs à dix chiffres quand tu fais le suivi depuis l’Allemagne, la Suède, l’Autriche, la Pologne, les Pays-Bas, la Belgique, la France, l’Italie, l’Espagne ou le Royaume-Uni, et seulement en dernier recours ailleurs. Peek reconnaît aussi un lien collé `activetracing.dhl.com/DatPublic/datSelection.do` (`a=`, `valuePackageField=` ou `valueShipmentOrderField=`). Pour un code de commande (`FRA-EC-4829173`), un ID d’envoi à 9 ou 11 chiffres ou un SSCC (`00348291736405829177`), appuie sur « Changer » lors de l’ajout ou sur « Changer de transporteur » sur l’écran du colis puis choisis DHL Freight (rechercher « DHL ActiveTracing », « DHL Frachtverfolgung » ou « DHL Freight GmbH » le trouve aussi).

Peek lit directement la page anglaise d’ActiveTracing et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « À la douane », « En livraison », « Prêt à être retiré », « Tentative de livraison », « Problème signalé », « Retourné à l’expéditeur », « Livré »), les phrases de statut d’ActiveTracing avec le terminal (tel que `KOBLENZ, DE`), le poids brut et le pays de destination. Peek date chaque ligne sur le fuseau horaire de son terminal et l’affiche à l’heure de ton appareil. Le nom du service n’apparaît dans aucune application Peek. La ville du destinataire, seule partie d’adresse affichée par ActiveTracing, peut clore l’itinéraire sur ta propre carte tant que l’envoi est en chemin ; les liens partagés ne l’affichent pas. Peek omet l’origine, le nombre de pièces, le volume, la date de départ, l’heure de livraison distincte, les autres références, la preuve de livraison et la signature ; ActiveTracing ne fournit ni date de livraison estimée, ni codes de statut, ni nom d’expéditeur ou de destinataire.

Tant que le colis est ouvert dans Peek, ou le jour de sa livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où ActiveTracing affiche `Out for Delivery`, toutes les 2 minutes (toutes les 5 minutes après une vérification ayant ouvert un navigateur) à partir de 6 h sur l’horloge du lieu du dernier scan, tant qu’ActiveTracing répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelle, il vérifie toutes les heures quelle que soit ta façon de le suivre, puis toutes les 6 heures après sept jours à « Étiquette créée ». Quand ActiveTracing ne répond pas et que Peek suit l’envoi uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Quand le scan le plus récent remonte à quatre jours et que le colis reste sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La page du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger DHL Freight. », avec un lien « Demande de l’aide à DHL Freight » vers le Centre d’aide DHL Freight. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger DHL Freight. »

Tu peux choisir entre les réglages d’alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek se limite au suivi des mises à jour et ne peut ni réserver un rendez-vous de livraison, ni régler des frais de fret, ni contacter un terminal DHL Freight à ta place.

## Questions fréquentes sur le suivi DHL Freight

### Quelle est la différence entre DHL Freight, DHL Parcel et DHL Express ?

DHL Freight s’occupe du fret routier et ferroviaire européen (palettes, LTL/PTL/FTL, *DHL Home Delivery* pour colis encombrants et fret national suédois) suivi sur [ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do), tandis que les colis standards relèvent de [DHL](carrier:dhl) et les envois express aériens internationaux à délai garanti de [DHL Express](carrier:dhl-express).

### Quels numéros de suivi fonctionnent sur DHL ActiveTracing ?

ActiveTracing accepte les codes de commande DHL (`FRA-EC-4829173`, inventé), les ID d’envoi de 9 à 11 chiffres (`4829173640`), les identifiants colis / License Plates `JND` de 20 caractères (`JND48291736405829173`) et les numéros GS1 SSCC à 20 chiffres (`00348291736405829177`).

### Pourquoi mon envoi est-il bloqué sur `Awaiting delivery appointment` ?

Le terminal de livraison garde la marchandise tant qu’un jour de livraison n’a pas été fixé avec le destinataire. Renseigne-toi auprès de l’expéditeur ou du terminal mentionné dans la dernière ligne de suivi.

### Combien de temps DHL ActiveTracing conserve-t-il l’historique ?

[ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) indique que les données d’expédition restent accessibles en ligne pendant les **six derniers mois**.

:::sources
- [DHL Freight : ActiveTracing](https://activetracing.dhl.com/DatPublic/datSelection.do) – recherche par ID d’envoi, code de commande et Package ID / License Plate, conservation de 6 mois, *DHL Freight GmbH*
- [DHL Freight : page Allemagne (anglais)](https://www.dhl.com/de-en/home/freight.html) – plus de 40 pays, plus de 45 M de tonnes/an, LTL/PTL/FTL, GoGreen Plus Flex, Rail Freight Europe et réseau de terminaux
- [DHL Freight : Centre d’aide fret routier et ferroviaire européen (anglais)](https://www.dhl.com/de-en/home/freight/help-center-for-european-road-and-rail.html) – assistant IA Viva, localisateur de terminaux, How to Prepare a Claim, Incoterms 2020 et myDHLFreight (URL d’aide liée par Peek)
- [Commission européenne : réforme douanière de l’UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € jusqu’à 150 € et frais de gestion UE de 2 €
- [Universal Parcel Scraper : dhl-freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-freight) – analyse `JND`, code de commande, ID d’envoi de 9 à 11 chiffres et SSCC, règles de liens `activetracing.dhl.com`, table de statuts anglais/allemand, heure locale de chaque terminal, poids et pays de destination lus, et URL d’aide
:::
