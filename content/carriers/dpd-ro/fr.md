---
title: Suivi DPD Romania : statuts, Smart Control et contact
description: Suivre un colis DPD Romania : numéro AWB à 11 chiffres, statuts roumains et anglais, Smart Control, casiers, bureaux et contact.
slug: suivi-dpd-romania
published: 2026-11-23
updated: 2026-11-23
---

DPD Romania (Dynamic Parcel Distribution S.A.) est la filiale du groupe Geopost en Roumanie, active depuis 2008, avec son siège à Mogoșoaia (Ilfov). Chaque colis dispose de son propre numéro de bordereau (*waybill* ou AWB) : suis-le sur tracking.dpd.ro (qui propose une version anglaise), via le lien Smart Control du SMS de livraison de DPD ou dans le champ ci-dessus.

## Numéro de suivi DPD Romania : où le trouver

DPD l'appelle numéro de bordereau (*waybill*) ou AWB. Les pages de DPD n'indiquent aucune règle de longueur ; les bordereaux que Peek reconnaît pour DPD Romania comportent 11 chiffres commençant par 8 :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `84927163504` | 11 chiffres commençant par 8 : un AWB de DPD Romania. Ce format correspond aussi à [GLS Germany](carrier:gls-de), [GLS France](carrier:gls-fr), [GLS Switzerland](carrier:gls-ch), GLS Austria, PPL et d'autres. |
| `01204819273645` | 14 chiffres : le format des numéros de colis d'autres sociétés DPD comme [DPD Germany](carrier:dpd-de), [DPD UK](carrier:dpd-uk), [DPD France](carrier:dpd-fr) ou [DPD Portugal](carrier:dpd-pt). Peek suit DPD Romania uniquement avec son AWB à 11 chiffres. |

Cherche le numéro dans l'e-mail d'expédition du magasin ou sur ta page de commande. L'application mobile de DPD propose un tableau de bord destinataire accessible avec un code reçu par SMS ou e-mail. Si le magasin nomme plutôt [Cargus](carrier:cargus), [FAN Courier](carrier:fan-courier) ou Sameday, le numéro leur appartient. Pas de numéro du tout ? Lis [où trouver ton numéro de suivi](guide:find-tracking-number).

## Signification des statuts de suivi DPD Romania

Le suivi de DPD répond en roumain ou en anglais ; les scans roumains existent dans une ancienne formulation courte et dans une phrase plus récente. Dans l'ordre habituel d'un colis :

| DPD affiche | Ce que ça veut dire pour toi |
| --- | --- |
| `Shipment data received`, `Receptionare informatii colet`, `Expedierea ta a fost înregistrată de către expeditor.` | L'expéditeur a créé le bordereau. DPD a les données, mais pas encore le colis. |
| `Expedierea nu a putut fi preluată de către curierul DPD.` | Le livreur n'a pas pu récupérer le colis chez l'expéditeur ; un motif suit. |
| `Courier Pick-up`, `Expedierea ta a fost preluată de la expeditor de către curierul DPD.` | Un coursier DPD a collecté le colis chez l'expéditeur. |
| `Received in Office`, `Receptie la sediu` | Un bureau DPD a pris le colis en charge, remis par un livreur ou au guichet. |
| `Departure Scan`, `Arrival Scan`, `Intrare in depozit`, `Expedierea ta a fost recepționată în depozitul DPD.`, `Expedierea ta a fost procesată în depozitul DPD.` | Départ, arrivée ou traitement dans un dépôt DPD. |
| `Expedierea ta se află în tranzit spre depozitul de destinație.` | En transit vers le dépôt chargé de la livraison. |
| `Expedierea ta a ajuns în depozitul de destinație.` | Arrivé au dépôt de livraison : la livraison suit. |
| `Predict`, `Predict – s-au trimis informațiile cu privire la intervalul orar de livrare.` | DPD t'a envoyé le créneau horaire de livraison. C'est un avis, pas un scan. |
| `Out for Delivery`, `Iesire in livrare`, `Expedierea ta a fost preluată spre livrare de către curierul DPD.` | Avec le livreur pour livraison. |
| `Livrare nereusita`, `Expedierea ta nu a putut fi livrată.` | Non livré. Un motif suit, par exemple adresse erronée ou refus. |
| `Expedierea ta a fost returnată în depozitul DPD.` | De retour au dépôt après une tournée manquée, pas un retour à l'expéditeur. |
| `Delivered`, `Livrat` | Livré. Le suivi ajoute le nom de la personne qui l'a réceptionné. |
| `Retur catre expeditor` | En retour vers l'expéditeur, sous un bordereau de retour indiqué dans la note. |
| `Retur livrat catre expeditor` | Rendu à l'expéditeur. |
| `Canceled`, `Expedierea ta a fost anulată.` | Le bordereau a été annulé : contacte le magasin. |

`Shipment data received` mentionne souvent la date de livraison prévue. Les scans effectués dans des dépôts DPD à l'étranger nomment le dépôt et son pays.

## Livraison DPD en Roumanie : Smart Control, bureaux et casiers

**Délais.** DPD Standard livre partout en Roumanie en 1 à 2 jours ouvrés. Un éventuel paiement à la livraison se règle au livreur en espèces ou, en zone urbaine, par carte.

**Smart Control.** Avant la livraison, DPD envoie un SMS avec la date et un lien Smart Control gratuit permettant de confirmer ou modifier l'adresse, suivre le colis, le rediriger vers un entrepôt, un bureau, un casier ou un DPD Shop, ou le reporter au jour ouvré suivant. La redirection dans la même ville est gratuite. La livraison est sans contact (la signature électronique n'est plus exigée) ; DPD peut vérifier ton identité avec une pièce d'identité et un code PIN envoyé par SMS ou e-mail.

**Livraison manquée.** DPD effectue deux tentatives sur deux jours ouvrés consécutifs. Après le second essai, il laisse un avis et garde le colis gratuitement dans un point DPD avec personnel pendant 5 jours calendaires, délai durant lequel tu peux convenir d'une dernière livraison. Les envois Express et Livrare specială n'ont qu'une tentative avant de repartir chez l'expéditeur. DPD ne livre pas en boîte aux lettres. Si tu refuses le colis ou le règlement du contre-remboursement, le retour à l'expéditeur démarre le jour même sans mise en instance.

**Bureaux, casiers et Parcel Shops.** Délais de retrait indiqués par DPD :

- Bureau DPD : 7 jours calendaires ; paiement en espèces, par carte ou Pay by Link.
- Casier DPD : 3 jours calendaires (72 heures à compter de l'avis dans les conditions de DPD) ; casiers extérieurs accessibles 24 h/24 ; carte ou Pay by Link.
- DPD Parcel Shop : 5 jours calendaires ; Pay by Link ou carte (pas de carte dans les points Pay Point).

Passé le délai de retrait, le colis repart vers l'expéditeur sous 5 jours ouvrés (10 jours pour un colis international).

## Colis DPD Romania en retard, perdu, abîmé ou en douane

Pour un remboursement ou un nouvel envoi, adresse-toi au magasin : c'est lui qui a le contrat avec DPD.

**Pas de mise à jour.** Si le statut reste figé longtemps ou que le colis dépasse son délai, DPD invite à contacter client@dpd.ro ou le 031 824 90 90. Voir [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).

**Livré mais non reçu.** Le suivi de DPD indique le nom de la personne qui a pris le colis : renseigne-toi d'abord auprès d'elle, puis contacte DPD et le magasin. Plus dans [colis livré mais non reçu](guide:delivered-not-received).

**Retourné.** `Retur catre expeditor` signifie que le colis repart. Demande au magasin de le réexpédier. Voir [retour à l'expéditeur](guide:returned-to-sender).

**Réclamations.** L'expéditeur comme le destinataire peuvent déposer une réclamation sous 6 mois après l'envoi via le formulaire DPD : par e-mail à reclamatii@dpd.ro, en ligne, dans un point DPD avec personnel ou par courrier recommandé. DPD statue sous 3 mois et indemnise sous 15 jours après décision. Pour un colis perdu ou abîmé sans valeur déclarée ni contre-remboursement, DPD indemnise à hauteur de 5 fois le prix du service. Dans un délai d'un an après l'envoi, tu peux aussi saisir l'ANCOM (régulateur roumain des communications) ou la justice. Voir [colis perdu](guide:lost-parcel).

**Douane.** Les achats en ligne provenant de hors UE sont soumis à un droit de douane européen de 3 € par type de produit sur les colis jusqu'à 150 € depuis le 1er juillet 2026 et à des frais de dossier de l'Union de 2 € par type de produit, quelle que soit la valeur, depuis le 1er novembre 2026. Les pages de DPD Romania ne précisent pas comment elle les répercute : vérifie auprès du magasin s'ils étaient inclus. Son avis du 22 décembre 2025 explique également la taxe fixe roumaine de 25 RON par colis hors UE déclaré sous 150 €, entrée en vigueur le 1er janvier 2026 : c'est le vendeur, l'expéditeur ou la place de marché qui la paie, et DPD la collecte auprès d'eux. Voir [colis bloqué en douane](guide:customs).

## Contacter DPD Romania

Peek ne peut pas contacter DPD ni le magasin à ta place. Sur la page de contact de DPD Romania :

- **Colis en Roumanie :** 031 824 90 90, du lundi au vendredi de 8 h 30 à 16 h 30 (heure de Roumanie). Demandes par e-mail à client@dpd.ro.
- **Colis internationaux :** 031 824 90 32 ou international@dpd.ro, aux mêmes horaires.
- **Réclamations :** reclamatii@dpd.ro ou via le formulaire de réclamation.
- **Courrier :** Dynamic Parcel Distribution S.A., Str. Buiacului nr. 2, LogIQ Logistic Park, Corp A (A1-A2), 077135 Mogoșoaia, Ilfov.

DPD ne précise pas si ses conseillers parlent anglais ou français.

> DPD Romania met en garde contre les faux SMS et e-mails en son nom contenant des liens vers des sites imitant celui de DPD pour réclamer un paiement ou des coordonnées bancaires. DPD Romania ne demande jamais de coordonnées bancaires par SMS ou e-mail. N'ouvre pas le lien, signale le message comme spam et préviens DPD. Voir [arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis DPD Romania avec Peek

Colle le numéro AWB à 11 chiffres dans [Peek](/). D'autres transporteurs partagent ce format, donc Peek interroge les plus probables selon ton pays (région de ton téléphone ou pays de connexion sur le web). En Roumanie, il demande d'abord à DPD Romania et affiche « DPD Romania a ce colis. » lorsque DPD le connaît. Ailleurs, il interroge d'abord les autres transporteurs et les services de suivi, puis DPD Romania, et le nomme quand DPD connaît le numéro. Si tu vois « pas encore trouvé · Peek continue de vérifier », appuie sur « Changer » et choisis DPD Romania (aussi référencé comme « DPD România », « DPD RO » ou « Dynamic Parcel Distribution »), ou utilise « Changer de transporteur » sur un colis déjà ajouté. Colle-le tout de même : Peek suit plus de 4 500 transporteurs. Les liens de tracking.dpd.ro ou services.dpd.ro/tracking avec `shipmentNumber=`, ainsi que dpdgroup.com/ro/ ou dpd.com/ro/ avec `parcelNumber=`, fonctionnent aussi.

Peek demande à DPD son suivi en anglais et affiche le statut, l'historique dans les mots de DPD avec la ville (et le pays) de chaque scan, la date de livraison prévue pendant le trajet, avec le créneau quand le message Predict de DPD en donne un, et l'heure de livraison. Il laisse de côté le nom du réceptionnaire, la signature et le bordereau de retour ; DPD ne fournit ni poids ni expéditeur.

Quand DPD remet un colis à ACS Courier pour le dernier tronçon (par exemple vers Chypre), il indique ACS et le numéro ACS. Si ce numéro comporte dix chiffres, comme les bordereaux ACS, Peek interroge ACS avec ce numéro et le suit dès qu'ACS affiche un scan au moins aussi récent que le dernier scan DPD. Jusque-là, ou pour un autre format, Peek suit DPD et la fiche indique déjà ACS avec son numéro.

Tant que le colis est ouvert dans Peek, ou le jour de sa livraison avec les notifications activées, Peek le vérifie jusqu'à toutes les 10 minutes, et toutes les 2 minutes le jour où DPD le scanne en livraison : dès 6 h, heure de Roumanie, ou, quand le message Predict de DPD donne un créneau, dès une demi-heure avant son début si c'est plus tard. Il t'alerte selon ton choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après deux jours sans nouvelles (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures, et toutes les 6 heures après sept jours sans nouvelles à « Étiquette créée ». Si le colis reste quatre jours sans nouvelle ligne à « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche « Aucune mise à jour depuis … » avec le jour de cette ligne et un lien « Demande de l’aide à DPD Romania ». Pour modifier la livraison, passe par Smart Control.

## Questions sur le suivi DPD en Roumanie

### Quel est le délai de livraison de DPD en Roumanie ?

1 à 2 jours ouvrés avec DPD Standard. Les conditions de DPD prévoient jusqu'à 7 jours calendaires pour les localités desservies à jours fixes.

### Combien de temps DPD garde-t-il un colis en bureau ou en casier ?

7 jours calendaires dans un bureau, 5 dans un Parcel Shop, 3 dans un casier et 5 dans un point DPD après deux livraisons manquées. Ensuite il repart chez l'expéditeur.

### Peut-on suivre un colis DPD Romania en anglais ?

Oui. Le suivi de DPD dispose d'une version anglaise et Peek affiche les libellés anglais de DPD.

### Quel est le numéro de téléphone de DPD Romania ?

031 824 90 90 pour les colis en Roumanie et 031 824 90 32 pour les colis internationaux, du lundi au vendredi de 8 h 30 à 16 h 30 (heure de Roumanie).

:::sources
- [DPD Romania : Where is my parcel?](https://www.dpd.com/ro/en/faq/unde-este-coletul-meu/) – numéro AWB, suivi
- [DPD Romania : My parcel has not arrived yet](https://www.dpd.com/ro/en/faq/coletul-meu-nu-a-sosit-inca-ce-este-de-facut/) – suivi en anglais, quand appeler
- [DPD Romania : How can I change the place or day of delivery?](https://www.dpd.com/ro/en/faq/cum-pot-schimba-locul-sau-ziua-livrarii/) – redirection
- [DPD Romania : Contact customer service](https://www.dpd.com/ro/en/faq/contact-serviciul-clienti/) – téléphone, horaires
- [DPD Romania : Smart Control](https://www.dpd.com/ro/en/smart-control/) – SMS, redirection gratuite, livraison sans contact
- [DPD Romania : Home](https://www.dpd.com/ro/en/) – options Smart Control
- [DPD Romania : DPD OOH](https://www.dpd.com/ro/en/dpd-ooh/) – délais de retrait, paiement
- [DPD Romania : Contact](https://www.dpd.com/ro/en/support/contact-dpd/) – téléphones, e-mails, horaires
- [DPD Romania : National services](https://www.dpd.com/ro/en/parcel-shipping/national-services/) – 1 à 2 jours ouvrés, paiement par carte
- [DPD Romania : Payment](https://www.dpd.com/ro/en/payment/) – paiement à la livraison
- [DPD Romania : Mobile application](https://www.dpd.com/ro/en/mobile-application-dpd/) – application, tableau de bord destinataire
- [DPD Romania : Termeni și condiții](https://www.dpd.com/ro/ro/termeni-si-conditii/) – AWB, PIN, tentatives, conservation, refus, délais de livraison et retour, ANCOM
- [DPD Romania : Resolution mechanism of complaints](https://www.dpd.com/ro/en/support/tips-and-tricks-dpd-romania/resolution-mechanism-of-complaints/) – réclamations, indemnisation
- [DPD Romania : About the company](https://www.dpd.com/ro/en/about-company/) – actif depuis 2008, Geopost
- [DPD Romania : Legal and copyright notice](https://www.dpd.com/ro/en/support/legal-notice/) – société, siège social
- [DPD Romania : 25 RON fee for non-EU parcels under EUR 150](https://www.dpd.com/ro/en/2025/12/22/25-ron-fee-for-non-eu-parcels-under-eur-150-2/) – taxe roumaine sur les colis
- [DPD Romania : Campanie de informare phishing](https://www.dpd.com/ro/ro/2025/08/01/campanie-de-informare-phishing/) – faux SMS et e-mails
- [Commission européenne : réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € par type de produit jusqu'à 150 € depuis le 1er juillet 2026, frais de dossier de 2 € depuis le 1er novembre 2026
- [Commission européenne : guide sur le droit de 3 € et les frais de dossier de l'Union (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € par type de produit quelle que soit la valeur
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – ce que Peek lit, libellés de DPD Romania et transfert vers ACS
:::
