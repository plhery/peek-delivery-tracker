---
title: Suivi Nova Global : codes NPG et NGUS, douane et aide
description: Suis un colis Nova Global (Nova Poshta Global) : bordereaux NPG et NGUS, codes de scan, dédouanement, partenaires locaux et contacts.
slug: suivi-nova-global
published: 2026-11-24
updated: 2026-11-24
---

Nova Global (raison sociale *LLC « Nova Poshta Global »* / *LLC NP GLOBAL*, membre du groupe Nova Poshta) est un opérateur international de logistique express et d’e-commerce transfrontalier disposant de bureaux à Kiev et dans le New Jersey, présent dans plus de 160 pays et territoires et relié à 506 690 points de retrait dans le monde. Via ses divisions *Global E-Commerce*, *Global Forwarding* (adresses virtuelles de livraison dans 16 pays dont les États-Unis, le Royaume-Uni et l’Italie), *Global Sellers* et *Global Freight*, il achemine des colis internationaux depuis et vers l’Ukraine, les États-Unis, le Canada, l’Union européenne et l’Asie. Tu peux suivre un bordereau sur [la page de suivi Nova Global](https://nova.global/track/), ou coller ton numéro dans le champ ci-dessus.

## Numéros de suivi Nova Global : formats NPG et NGUS

Cherche ton numéro de bordereau international dans l’e-mail d’expédition du vendeur, ton compte de réexpédition ou l’application Nova Post. Les bordereaux Nova Global suivent trois formats principaux :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `NP482917364058NPG` | Bordereau standard Nova Global : 2 lettres (comme `NP`, `WB`, `OG` ou `YW`) + 9 à 16 chiffres + `NPG`. |
| `ABCDF4829173640NPG` | Bordereau de commande marchand : 5 lettres + 10 chiffres + `NPG`. |
| `TE4829173640582917NGUS` | Bordereau Nova Global pour un colis venu de Chine : `TE` + 16 chiffres + `NGUS`. |

Dans le pays de destination, Nova Global peut remettre le colis à un transporteur local, comme Nova Poshta en Ukraine, et afficher le propre numéro de ce transporteur à côté du bordereau `NPG` ou `NGUS`. Pour d’autres formats transfrontaliers, consulte [les formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts et codes Nova Global

Le suivi Nova Global utilise des codes numériques cohérents accompagnés de libellés en anglais ou en ukrainien, précédés du pays où le scan a eu lieu :

| Code et libellé Nova Global | Ce que ça signifie pour toi | Ce qu’affiche Peek |
| --- | --- | --- |
| `1-0` (`Order is processed for delivery to the country of destination`) | L’expéditeur ou le compte de réexpédition a créé la commande internationale avant la réception au hub. | « Étiquette créée » |
| `1-2` (`Processing an unregistered shipment`), `14` (`Accepted for delivery`) | Reçu et scanné dans le centre ou l’entrepôt d’origine Nova Global. | « Pris en charge » |
| `247`, `76`, `78`, `23`–`26`, `54`, `55`, `30`, `301` | Préparé pour le vol, transporté entre aéroports, passé par des centres de transit (par exemple Varsovie ou Vienne) et acheminé vers le terminal de la ville de destination. | « En transit » |
| `20` (`Delivered to the customs terminal`), `400`, `402` (`Parcel require customs clearance`), `407` / `407-1` (`documents submitted`), `410` (`Accompanying documents received`) | En cours d’inspection douanière ou en attente de documents et d’évaluation des droits. | « À la douane » |
| `401` (`Customs declaration finished`), `22` (`Left the customs terminal`) | Dédouané et remis dans le circuit de livraison. | « En transit » |
| `37` (`On courier delivery`) | Parti avec le livreur local du pays de destination pour livraison à domicile. | « En livraison » |
| `303` (`Delivered to the locker`), `38` (`At the post office`) | Prêt à être retiré en consigne automatique (*поштомат*) ou dans une agence Nova Poshta. | « Prêt à être retiré » |
| `553` (`Shipment lost`) | Incident enregistré sur l’envoi ; contacte immédiatement le service client et le vendeur. | « Problème signalé » |
| `3-1` (`Delivered`) | Retiré par le destinataire ou livré. | « Livré » |

## Livraison transfrontalière, réexpédition et douane

Sur [nova.global](https://nova.global/), Nova Global détaille quatre divisions : *Global E-Commerce* (livraisons e-commerce dans les zones NALA, EMEA et APAC), *Global Forwarding* (adresses d’achat virtuelles dans 16 pays réexpédiant les colis chez toi), *Global Sellers* (exportations pour marchands sur Etsy, Amazon, eBay, Vinted, StockX, Depop et TikTok Shop vers 160+ pays) et *Global Freight* (fret aérien, routier et maritime).

**Dédouanement et justificatifs.** Les envois internationaux entrant en Ukraine, dans l’Union européenne, aux États-Unis ou au Canada passent en douane (`20`, `400`, `402`). La [politique de confidentialité de Nova Global](https://nova.global/public-contract/) précise qu’elle peut te demander des scans de documents relatifs à l’envoi, comme ceux confirmant sa valeur, ainsi que tout autre document exigé par les douanes du pays d’origine ou de destination. Pour les règles douanières générales (y compris TVA d’importation UE, droit de 3 € jusqu’à 150 € et taxe UE de 2 € hors UE), consulte [colis retenu en douane](guide:customs).

**Règles locales de livraison.** Lorsque Nova Global remet le colis à Nova Poshta ou à un autre transporteur local, ce sont les règles de ce dernier qui déterminent les tentatives, les jours de garde et les horaires d’agence.

## Colis Nova Global bloqué en douane, silencieux ou manquant

- **Pauses entre pays de transit :** le fret aérien et les transferts routiers transfrontaliers (par exemple via la Pologne vers l’Ukraine) marquent souvent plusieurs jours de pause entre `78` (départ aéroport) ou `25` (sortie du pays de transit) et `20` (terminal douanier). Consulte [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Utilise le numéro du partenaire local :** dès que Nova Global attribue un transporteur local et un numéro après la douane, consulte directement ce partenaire pour les créneaux à domicile ou les infos de point retrait.
- **Perdu (`553`), abîmé ou indiqué livré sans réception :** contacte d’abord la boutique en ligne (ou le service de réexpédition) pour ouvrir une réclamation, et joins le support Nova Global avec ton bordereau `NPG`/`NGUS` et ta facture commerciale. Consulte [colis livré mais non reçu](guide:delivered-not-received), [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts Nova Global

Coordonnées officielles publiées sur [nova.global/contact/](https://www.nova.global/contact/), [nova.global/track/](https://nova.global/track/) et [nova.global/public-contract/](https://nova.global/public-contract/) :

- **E-mail :** `info@nova.global`.
- **Options d’assistance sur la page de suivi :** widgets *Call us*, *Send e-mail* et *Chat with us* sur [nova.global/track/](https://nova.global/track/), plus suivi et assistance dans l’application mobile **Nova Post**.
- **Société :** LLC « Nova Poshta Global » (*LLC NP GLOBAL*, numéro d’identification `38130410`, NIF `381304126502`), 103 Stolychne Hwy, Kiev, 03026, Ukraine. Nova Global dispose également d’un bureau dans le New Jersey.

> Ignore les SMS ou messages non sollicités demandant un paiement urgent par carte pour « débloquer » un colis Nova Global. Vérifie tout avis douanier légitime dans l’application officielle Nova Post ou via [nova.global/track/](https://nova.global/track/) et `info@nova.global`. Consulte [les SMS d’arnaque à la livraison](guide:delivery-scam-texts).

## Suivre un colis Nova Global avec Peek

[Peek](/) reconnaît automatiquement les bordereaux `NPG` (`NP482917364058NPG` ou `ABCDF4829173640NPG`, chiffres inventés) et `NGUS` (`TE4829173640582917NGUS`) comme Nova Global avec un haut niveau de confiance, ainsi que les liens collés `nova.global/track/?Tracking_ID=` ou `novaposhtaglobal.ua/track/?Tracking_ID=`. Si tu dois choisir Nova Global manuellement, appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis (rechercher « Nova Poshta Global », « NP Global » ou « Нова Пошта Глобал » le trouve aussi).

Peek lit directement le suivi Nova Global en anglais et affiche l’étape (« Étiquette créée », « Pris en charge », « En transit », « À la douane », « En livraison », « Prêt à être retiré », « Problème signalé », « Livré »), le libellé de chaque scan avec son pays, le pays de destination et l’heure de livraison lorsqu’elle est fournie. Les codes Nova Global, comme `402` ou `553`, déterminent l’étape ; Peek ne les affiche pas. Nova Global date les scans en Ukraine à l’heure de Kiev et les scans à l’étranger sur le fuseau horaire de leur lieu ; Peek les affiche à l’heure de ton appareil. Lorsque Nova Global nomme le transporteur local avec son numéro et que Peek reconnaît ce transporteur, Peek vérifie qu’il a le colis : la fiche affiche alors « Livraison par » suivi du nom du transporteur, et liste les deux numéros. Peek écarte le pays d’origine et les autres numéros de l’envoi. Le suivi Nova Global ne donne ni date estimée, ni poids, ni nom de service, ni adresse de point relais, ni photo de preuve de livraison.

Tant que le colis est ouvert dans Peek, ou le jour de la livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Nova Global affiche `37` (`On courier delivery`), toutes les 2 minutes à partir de 6 h à l’heure du lieu du dernier scan, tant que le suivi Nova Global répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec seulement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta façon de le suivre, et toutes les 6 heures après sept jours sur « Étiquette créée ». Lorsque le suivi Nova Global ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le dernier scan date de quatre jours et que le colis reste à « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec la date de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis affiche alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Nova Global. », avec un lien « Demande de l’aide à Nova Global » vers la page de contact de Nova Global. Après trois semaines sans nouveau scan, elle indique « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Nova Global. »

Tu peux choisir les réglages d’alerte « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek assure uniquement le suivi et ne peut ni envoyer de documents douaniers ni contacter Nova Global à ta place.

## Questions sur le suivi Nova Global

### Que signifient `NPG` et `NGUS` à la fin d’un numéro de suivi ?

`NPG` identifie un bordereau international Nova Global (*Nova Poshta Global*, tel que `NP482917364058NPG` ou `ABCDF4829173640NPG`, chiffres inventés), tandis que `TE...NGUS` (`TE4829173640582917NGUS`) désigne un bordereau Nova Global pour un colis parti de Chine.

### Que signifie `Parcel require customs clearance` (`402`) ?

Le code `402` indique que le colis doit être dédouané. Nova Global peut te demander des documents numérisés concernant l’envoi, notamment pour confirmer sa valeur, lorsque la douane l’exige ; une fois libéré, le colis affiche `401` (`Customs declaration finished`).

### Où retirer un colis Nova Global en Ukraine ?

Lorsque le suivi affiche `303` (`Delivered to the locker`) ou `38` (`At the post office`), ton colis est prêt à être retiré dans la consigne (*поштомат*) ou l’agence Nova Poshta sélectionnée.

### Comment contacter le service client Nova Global ?

Écris à `info@nova.global`, consulte [nova.global/contact/](https://www.nova.global/contact/) ou utilise les boutons d’appel, d’e-mail et de chat sur [nova.global/track/](https://nova.global/track/).

:::sources
- [Nova Global : Site officiel](https://nova.global/) – 160+ pays, bureaux à Kiev et dans le New Jersey, Global E-Commerce, Global Forwarding (16 pays d’adresses virtuelles), Global Sellers et Global Freight
- [Nova Global : Suivi de colis](https://nova.global/track/) – portail public de suivi, 506 690 points de retrait et canaux d’assistance
- [Nova Global : Contacts](https://www.nova.global/contact/) – `info@nova.global`, LLC NP GLOBAL (NIF `381304126502`, 103 Stolychne Hwy, Kiev) (URL d’aide liée par Peek)
- [Nova Global : Contrat public et confidentialité](https://nova.global/public-contract/) – *LLC « Nova Poshta Global »* (ID `38130410`), adresse postale à Kiev et informations douane/données
- [Universal Parcel Scraper : nova-global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/nova-global) – détection `NPG`/`NGUS`, règles de liens `nova.global`/`novaposhtaglobal.ua`, codes (`1-0` à `553`), heure de Kiev en Ukraine et heure locale à l’étranger, extraction du transporteur local et URL d’aide
:::
