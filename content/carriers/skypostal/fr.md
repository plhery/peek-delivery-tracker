---
title: Suivi SkyPostal : codes SKAMZUS et SP, statuts et aide
description: Suis un colis SkyPostal des États-Unis vers l’Amérique latine : codes SKAMZUS et SP...BR, scans Miami et douane (CEN, HTI, DEL) et e-mails par pays.
slug: suivi-skypostal
published: 2026-11-24
updated: 2026-11-24
---

**SkyPostal** est un réseau de livraison de colis domestiques aux États-Unis et transfrontaliers vers l’Amérique latine, basé à Miami et fondé par Albert P. et A.J. Hernandez ([skypostal.com](https://www.skypostal.com/)). Depuis son principal hub d’exportation américain à Miami en Floride, SkyPostal achemine les colis e-commerce de grands détaillants et places de marché — notamment Amazon, Temu et eBay — vers le Brésil, le Mexique, la Colombie, le Pérou, l’Équateur, le Chili et le Costa Rica, en assurant le dédouanement export et import puis la remise finale à des partenaires régionaux latino-américains. Tu peux consulter un envoi sur [tracking.skypostal.com](https://tracking.skypostal.com/) ou coller ton numéro ou lien SkyPostal dans le champ ci-dessus.

## Numéros de suivi SkyPostal (formats `SKAMZUS` et `SP...BR`)

SkyPostal émet plusieurs formats transfrontaliers selon le marchand d’origine et le pays de destination, le plus souvent des codes `SKAMZUS` à 17 caractères (très utilisés pour les exports Amazon US vers l’Amérique latine) et des codes `SP...BR` à 15 caractères pour le Brésil :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `SKAMZUS0482917365` | Code d’exportation SkyPostal Amazon US à 17 caractères (`SKAMZUS` + 10 chiffres). |
| `SP04829173605BR` | Code de colis transfrontalier SkyPostal vers le Brésil à 15 caractères (`SP` + 11 chiffres + `BR`). |

Lorsqu’il est sélectionné manuellement, le suivi SkyPostal recherche aussi d’autres bordereaux marchands et références partenaires alphanumériques de 8 à 40 caractères contenant au moins un chiffre. Voir le guide des [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi SkyPostal

SkyPostal associe un code d’événement à 3 lettres à chaque étape — couvrant la pré-déclaration électronique, la réception au hub de Miami, les vols, la douane à destination et les scans relayés par le transporteur local :

| Code et libellé SkyPostal | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `DRC` (`DATA RECEIVED`), `HCV` (`HS_CODE VALIDATION`), `TIC` (`TAX ID COLLECTED`), `DCO` (`DOCUMENTATION COMPLETE`) | Données de commande, validation du code tarifaire HS et identifiant fiscal de l’acheteur reçus avant l’arrivée physique au hub SkyPostal. | « Étiquette créée » |
| `RUT` (`RECEPTACLE RECEIVED IN US TRANSPORT FACILITY`), `REC` (`RECEIVED`) | Colis physique réceptionné au centre d’exportation SkyPostal à Miami. | « Pris en charge » |
| `XRS` (`X-RAY SCAN`), `PLP` (`PROVIDER LABEL PRINTED`), `INC` (`NESTED TO RECEPTACLE`), `PRO`, `MNF`, `FDP` (`FLIGHT DEPARTED`), `ATT`, `ATC` (`ARRIVED IN COUNTRY`), `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) | Traité à Miami, expédié par avion puis transféré de la compagnie aérienne à l’opérateur au sol du pays de destination (pas encore livré chez toi). | « En transit » |
| `CEN` (`CUSTOMS ENTRY`), `CHE` (`CUSTOMS HOLD AT CONTROL ENTITY`) | Entré en dédouanement à destination ou en attente de libération dans l’entrepôt sous douane de l’aéroport. | « À la douane » |
| `CCH` (`CUSTOMS CLEARED`), `TTC` (`TENDERED TO LOCAL CARRIER`), `TDP`, `PES` (`PROVIDER ENTRY SCAN`), `INT`, `PRH`, `PDH`, `SLH` (`HOLD RESOLVED`) | Dédouané, remis au transporteur local latino-américain et en transit dans les hubs locaux. | « En transit » |
| `OFD` (`OUT FOR DELIVERY`) | En tournée avec le partenaire de destination pour livraison aujourd’hui. | « En livraison » |
| `PIK` (`AVAILABLE FOR PICKUP`) | Prêt à être retiré par le destinataire en point de retrait local ou bureau de poste. | « Prêt à être retiré » |
| `CNA` (`CONSIGNEE NOT AVAILABLE`) | Tentative de livraison échouée car personne n’était disponible pour réceptionner le colis. | « Tentative de livraison » |
| `HTI` (`HOLD FOR PROBLEMATIC TAX ID`), `ICA` (`INCORRECT / INCOMPLETE ADDRESS`), `DRF` (`DELIVERY REFUSED`), `PLR` (`PROVIDER LABEL REJECTED`), `INH` (`ON HOLD`) | Retenu pour problème d’identifiant fiscal, adresse erronée ou incomplète, étiquette rejetée ou refus de livraison. | « Problème signalé » |
| `DEL` (`DELIVERED`) | Livré au destinataire par le partenaire local. | « Livré » |

Note que `DTP` (*Delivered from airline to operator*) signifie simplement que la compagnie aérienne a remis le sac postal aérien au prestataire au sol à destination — Peek garde `DTP` sur « En transit » au lieu de le marquer livré. Les événements internes de facturation (`CUT`, `PAY`, `FSC`) ne changent pas d’étape.

## Douane en Amérique latine, identifiants fiscaux (`HTI`) et relais locaux

Les envois transfrontaliers des États-Unis vers l’Amérique latine via SkyPostal reposent sur une conformité stricte en matière douanière et fiscale :

- **Identifiant fiscal de l’acheteur (`TIC` / `HTI`) :** les scans SkyPostal indiquent quand l’identifiant fiscal de l’acheteur est collecté (`TAX ID COLLECTED`) et quand un colis est retenu à cause de celui-ci (`HOLD FOR PROBLEMATIC TAX ID`). Si le suivi affiche `HTI`, contacte le vendeur ou écris à l’adresse d’assistance SkyPostal de ton pays avec ton numéro complet. Voir le guide sur les [colis retenus en douane](guide:customs).
- **Livraison locale du dernier kilomètre :** dès que `CCH` (*Customs cleared*) et `TTC` (*Tendered to local carrier*) apparaissent, un transporteur local assure la livraison finale, et le suivi SkyPostal affiche ses scans avec la ville du partenaire.

## Colis SkyPostal retardé, retenu ou manquant

- **Silence entre Miami et la douane de destination :** le suivi marque souvent une pause entre `FDP` (`FLIGHT DEPARTED`), `ATC` (`ARRIVED IN COUNTRY`) et `CCH` (`CUSTOMS CLEARED`) pendant que les envois consolidés passent le contrôle douanier. Voir le guide sur le [suivi bloqué](guide:tracking-not-updating).
- **Erreur d’adresse (`ICA`) ou livraison refusée (`DRF`) :** contacte d’abord ta place de marché (Amazon, Temu, eBay, etc.) pour un renvoi ou un remboursement, et écris à l’adresse support SkyPostal dédiée à ton pays avec ton numéro complet si le colis reste bloqué localement. Vois aussi [livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client SkyPostal et e-mails d’assistance par pays

Sur [SkyPostal Customer Service](https://www.skypostal.com/customer-service), SkyPostal demande aux destinataires finaux de contacter l’équipe **par e-mail** en joignant toujours leur numéro de suivi complet et le détail de la demande (délai de réponse publié : **3 à 5 jours ouvrables**) :

- **Brésil :** `Brazilcs@skypostal.com`
- **Mexique :** `Mexicocs@skypostal.com`
- **Colombie :** `Colombiacs@skypostal.com`
- **Pérou :** `Perucs@skypostal.com`
- **Équateur :** `Ecuadorcs@skypostal.com`
- **Tous les autres pays :** `customerservice@skypostal.com`
- **Siège aux États-Unis :** *SkyPostal*, 2990 NW 75th Ave, Miami, FL 33122, États-Unis ([skypostal.com/about-us](https://www.skypostal.com/about-us)).

> Méfie-toi des faux messages WhatsApp ou SMS réclamant un paiement fiscal urgent sur un site inconnu pour débloquer une importation vers l’Amérique latine. Vérifie toujours toute taxe douanière réelle sur ta page de commande ou sur le portail officiel des douanes ou de la poste nationale. Voir le guide sur les [SMS frauduleux de livraison](guide:delivery-scam-texts).

## Suivre un colis SkyPostal avec Peek

[Peek](/) détecte automatiquement SkyPostal comme seul candidat dès que tu saisis un numéro `SKAMZUS` (`SKAMZUS0482917365`, inventé) ou `SP...BR` (`SP04829173605BR`, inventé) et affiche « SkyPostal a ce colis. » dès que le suivi SkyPostal renvoie l’historique ; Peek reconnaît aussi directement les liens collés `tracking.skypostal.com/?trck_number=` et `tracking.skypostal.com/single-tracking.aspx?trck_number=`. Pour d’autres bordereaux marchands, appuie sur « Changer » lors de l’ajout ou « Changer de transporteur » sur la fiche du colis et sélectionne **SkyPostal** (rechercher « Sky Postal » fonctionne aussi).

Peek lit le suivi SkyPostal et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « À la douane », « En livraison », « Prêt à être retiré », « Tentative de livraison », « Problème signalé », « Livré »), le libellé anglais du scan et la ville du partenaire ou, à défaut, le code d’aéroport SkyPostal. Les codes à 3 lettres servent à définir l’étape et aucune application Peek ne les affiche. SkyPostal donne chaque scan sans décalage par rapport à UTC : Peek le date dans le fuseau de son lieu (heure de New York pour `MIA`, de São Paulo pour `GRU`, de Bogotá ou de Lima pour un scan sur place), un scan sans lieu dans le fuseau du scan voisin, et affiche chaque horaire à l’heure de ton appareil. Les scans d’un partenaire peuvent arriver deux fois par des flux décalés de quelques heures, si bien qu’une étape antérieure publiée après une livraison ne l’annule pas. Peek ne conserve ni la preuve de livraison, ni les commentaires, ni le numéro propre du partenaire, et aucune application Peek n’affiche le nom du signataire. Le suivi SkyPostal ne fournit ni jour estimé de livraison, ni poids, ni heure de livraison séparée.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où SkyPostal affiche `OFD` (`OUT FOR DELIVERY`), toutes les 2 minutes à partir de 6 h sur l’horloge du scan le plus récent, tant que le suivi SkyPostal répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi de SkyPostal ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger SkyPostal. », avec un lien « Demande de l’aide à SkyPostal » vers la page Customer Service de SkyPostal. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger SkyPostal. »

Tu peux choisir les alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek surveille uniquement les mises à jour de suivi et ne peut ni transmettre d’identifiant fiscal, ni dédouaner en Amérique latine, ni contacter SkyPostal à ta place.

## Questions sur le suivi SkyPostal

### À quoi ressemble un numéro de suivi SkyPostal ?

Les codes SkyPostal courants sont les numéros `SKAMZUS` à 17 caractères (`SKAMZUS0482917365`, inventé) pour les exports Amazon US et les numéros `SP...BR` à 15 caractères (`SP04829173605BR`, inventé) pour le Brésil, mais SkyPostal accepte aussi d’autres références marchandes de 8 à 40 caractères.

### Que signifie `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) sur SkyPostal ?

Cela ne signifie pas que ton colis a été livré chez toi. `DTP` indique que la compagnie aérienne a remis l’envoi consolidé à l’opérateur au sol de l’aéroport de destination avant le dédouanement (`CEN`) et la remise au transporteur local (`TTC`).

### Que signifie `HTI` (`HOLD FOR PROBLEMATIC TAX ID`) ?

La douane à destination a signalé un identifiant fiscal manquant ou erroné (comme le CPF au Brésil ou le RFC au Mexique). Contacte ton vendeur et écris à l’adresse d’assistance SkyPostal correspondant à ton pays sur [skypostal.com/customer-service](https://www.skypostal.com/customer-service).

### Comment contacter le service client SkyPostal ?

Écris au bureau de ton pays (`Brazilcs@`, `Mexicocs@`, `Colombiacs@`, `Perucs@`, `Ecuadorcs@` ou `customerservice@skypostal.com`) avec ton numéro de suivi ; SkyPostal indique un délai de réponse de 3 à 5 jours ouvrables.

:::sources
- [SkyPostal: Official Website](https://www.skypostal.com/) – expéditions US domestiques et transfrontalières vers l’Amérique latine, siège à Miami FL
- [SkyPostal: About Us](https://www.skypostal.com/about-us) – histoire de l’entreprise et réseau privé de livraison en Amérique latine
- [SkyPostal: Customer Service](https://www.skypostal.com/customer-service) – e-mails de support par pays (Brésil, Mexique, Colombie, Pérou, Équateur, général), note partenaires Amazon/Temu/eBay et délai de réponse 3–5 jours ouvrables (page d’aide liée par Peek)
- [SkyPostal: Tracking Portal](https://tracking.skypostal.com/) – portail public de suivi unitaire
- [Universal Parcel Scraper: skypostal](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/skypostal) – règles de détection `SKAMZUS` et `SP...BR`, règles de liens `tracking.skypostal.com`, table des codes 3 lettres (`DRC`–`DEL`, `DTP`, `HTI`) et fuseaux horaires des scans
:::
