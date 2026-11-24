---
title: Suivi XYL-Ships : numéros NUDE et XLWL, statuts et aide
description: Suis un colis XYL-Ships (FXYL) depuis la Chine : formats NUDE et XLWL, signification des statuts, délais silencieux, douane UE et qui contacter.
slug: suivi-xyl-ships
published: 2026-11-24
updated: 2026-11-24
---

XYL-Ships (XYL-ships International Logistics, aussi appelé FXYL) est un prestataire chinois de logistique transfrontalière titulaire d’agréments d’agence de fret aérien reconnus par l’Administration de l’aviation civile de Chine (CAAC) et l’IATA. Aux côtés du fret aérien, maritime, ferroviaire et routier commercial, l’entreprise achemine des petits colis e-commerce individuels depuis la Chine vers l’étranger avant de les confier à une poste ou un transporteur local pour la livraison finale. Tu peux vérifier un colis via l’option « Track Order » sur [xylships.com](https://xylships.com/), ou coller ton numéro de suivi dans le champ ci-dessus.

## Numéros de suivi XYL-Ships : formats NUDE et XLWL

Cherche ton numéro de suivi dans l’e-mail d’expédition de la boutique ou sur ta page de commande. Les petits colis XYL-Ships portent généralement un code alphanumérique de 16 ou 17 caractères commençant par `NUDE` ou `XLWL` et se terminant par `EX` ou `EXP` :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `NUDE4829173640EX` | `NUDE`, 10 chiffres et `EX`. |
| `XLWL4829173640EX` | `XLWL`, 10 chiffres et `EX`. |
| `XLWL4829173640EXP` | `XLWL`, 10 chiffres et `EXP`. |

Comme ces codes sont propres à XYL-Ships, ils ne se confondent pas avec les codes-barres des postes nationales. Si le vendeur t’a plutôt fourni un code postal international S10 (deux lettres, neuf chiffres et deux lettres de pays comme `CN`) ou un code d’un autre transporteur, consulte [les formats de numéros de suivi](guide:tracking-number-formats) pour identifier l’expéditeur.

## Signification des statuts de suivi XYL-Ships

XYL-Ships publie ses étapes de suivi en anglais, du tri initial en Chine jusqu’au transport international et au transfert vers le partenaire local dans le pays de destination :

| Ce qu’affiche XYL-Ships | Ce que ça signifie pour toi | Ce qu’affiche Peek |
| --- | --- | --- |
| `Shipment inbound in sorting center` | XYL-Ships a réceptionné et scanné le colis dans son centre de tri d’origine. | « En transit » |
| `Departed from sorting center`, `Arrived at sorting warehouse`, `Leaving warehouse` | Le colis passe par les entrepôts de tri en Chine avant la remise au transport principal. | « En transit » |
| `Hand over to transportation company`, `Departed from origin transportation center`, `Arrived at destination transportation center` | Remis au transporteur de ligne internationale, parti de Chine puis arrivé au centre d’entrée du pays de destination. | « En transit » |
| `Released and the package is being transferred`, `Package is being transferred locally` | Dédouané à l’entrée du pays de destination et en cours d’acheminement national. | « En transit » |
| `Transfer again, send to the transportation partner department`, `Arrival at transportation partner department, waiting for processing`, `Leaving transportation partner department, sent to the transit hub` | Transféré au service de traitement du partenaire de livraison local puis envoyé vers son centre de transit. | « En transit » |
| `Arriving at the transit hub, waiting for distribution`, `Leave the transit hub, transport to the destination city`, `Arriving at the destination city, about to distribute to the post office` | Trié au centre régional puis acheminé vers ta ville de destination pour prise en charge locale. | « En transit » |
| `The courier has received this parcel for delivery and will set a delivery time shortly` | Le livreur local a reçu le colis, mais la tournée de livraison n’a pas encore commencé. | « En transit » |
| `Parcel currently being delivered` | En tournée chez le livreur local aujourd’hui. | « En livraison » |
| `Delivered` | Enregistré comme livré à destination. | « Livré » |

Attention à deux libellés faciles à mal interpréter en fin de parcours. `Arriving at the destination city, about to distribute to the post office` signifie que le colis est remis au réseau postal ou partenaire local, pas qu’il t’attend au guichet d’un bureau de poste. De même, `The courier has received this parcel for delivery and will set a delivery time shortly` reste en « En transit » car aucune tournée n’a encore démarré — attends `Parcel currently being delivered` pour compter sur une livraison le jour même.

## Comment fonctionne la livraison transfrontalière XYL-Ships

Sur [xylships.com](https://xylships.com/), XYL-Ships présente du fret aérien commercial (dont des liaisons intérieures express 6 heures et 12 heures et du groupage porte-à-porte FBA), du groupage maritime, du transport ferroviaire et routier, ainsi que l’acheminement transfrontalier de petits colis e-commerce à l’unité — avec un cas client cité à 99 % de livraisons achevées sous 20 jours.

**Livraison finale par un partenaire local.** Comme le montrent les statuts XYL-Ships, dès qu’un colis arrive dans le pays de destination, il est transféré à un transporteur partenaire ou à la poste locale pour le dernier kilomètre.

**Règles de livraison non publiées.** `xylships.com` ne publie ni nombre de tentatives de livraison, ni règles de consigne automatique, ni délai de garde, ni garantie de délai ou fenêtre de réclamation. Une fois le colis entré dans ton pays, les modalités concrètes (remise à un voisin, dépôt en lieu sûr ou jours de garde après avis de passage) dépendent du transporteur local ou de la poste qui assure la distribution.

**Droits d’importation et douane.** Comme les envois XYL-Ships partent de Chine, les colis entrant dans l’UE, au Royaume-Uni, aux États-Unis ou ailleurs sont soumis à la TVA d’importation et aux règles douanières de ton pays. Pour un envoi entrant dans l’Union européenne, un droit de douane de 3 € par catégorie d’article s’applique aux colis jusqu’à 150 €, ainsi qu’une taxe de traitement UE de 2 € par catégorie d’article quelle que soit la valeur. Consulte [colis retenu en douane](guide:customs) pour comprendre comment les contrôles douaniers influencent la livraison.

## Colis XYL-Ships bloqué, silencieux ou non reçu

Si tu as acheté un article dans une boutique en ligne expédié par XYL-Ships, le vendeur reste ton cocontractant et ton interlocuteur principal pour tout retard, remboursement ou renvoi.

**Long silence en transit.** Des pauses de plusieurs jours sont courantes entre `Departed from origin transportation center` et `Arrived at destination transportation center` pendant l’attente d’un vol, le dédouanement et le scan d’entrée à destination. Consulte [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).

**Pas de numéro local dans Peek.** Peek suit le colis jusque chez le partenaire local sous ton numéro `NUDE` ou `XLWL` et n’affiche aucune référence locale distincte. Si une livraison échoue et que tu as besoin du nom ou du numéro du transporteur local, demande au vendeur.

**Indiqué `Delivered` mais introuvable.** Vérifie d’abord ta boîte aux lettres, ton porche, la réception de l’immeuble et tes voisins. Si le colis reste introuvable, signale-le aussitôt au vendeur et demande une preuve de livraison. Consulte [colis livré mais non reçu](guide:delivered-not-received).

**Colis endommagé, perdu ou retourné.** Garde des photos de l’emballage abîmé et contacte immédiatement la boutique, ou ouvre un litige de protection acheteur sur la place de marché ou ton moyen de paiement avant l’expiration du délai. Consulte [colis endommagé](guide:damaged-parcel), [colis perdu](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Contacter XYL-Ships (et pourquoi demander d’abord au vendeur)

`xylships.com` est un site de transitaire destiné aux entreprises, avec très peu de coordonnées pour les particuliers :

- **E-mail :** `fzxyl9527@163.com` (seul contact publié en pied de page de [xylships.com](https://xylships.com/)).
- **Téléphone, adresse postale et portail de réclamation :** aucun publié sur `xylships.com`.

Comme XYL-Ships traite avec des expéditeurs professionnels plutôt qu’avec des destinataires particuliers, contacte d’abord la boutique en ligne ou la place de marché en cas de colis manquant, d’erreur d’adresse ou de demande de remboursement — le marchand a un accès direct à son compte fret et aux procédures de protection acheteur.

> Méfie-toi des SMS ou e-mails inattendus qui te demandent de cliquer sur un lien de paiement pour « débloquer » ou « reprogrammer » un colis XYL-Ships. Vérifie toujours un éventuel paiement douanier directement auprès de la poste ou des douanes de ton pays, jamais via un SMS non vérifié. Consulte [les SMS d’arnaque à la livraison](guide:delivery-scam-texts).

## Suivre un colis XYL-Ships avec Peek

[Peek](/) reconnaît automatiquement les numéros `NUDE` (`NUDE` + 10 chiffres + `EX`) et `XLWL` (`XLWL` + 10 chiffres + `EX` ou `EXP`) comme appartenant à XYL-Ships, avec un haut niveau de confiance. Comme XYL-Ships n’utilise pas d’URL de suivi dédiée dans Peek, colle directement le numéro plutôt qu’un lien ; si tu dois choisir le transporteur à la main, appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis puis choisis XYL-Ships (rechercher « FXYL » le trouve aussi).

Peek lit directement le flux JSON de XYL-Ships et affiche l’étape (« En transit », « En livraison » ou « Livré »), les libellés de scan en anglais de XYL-Ships, le lieu du scan lorsque le flux l’indique et des horodatages munis d’un décalage horaire explicite (affichés à l’heure de ton appareil). Peek retire les adresses d’expéditeur et de destinataire ainsi que les références client internes. Il n’affiche ni date estimée, ni poids, ni numéro du transporteur local, ni photo de livraison.

Tant que le colis est ouvert dans Peek, ou le jour de la livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où XYL-Ships affiche `Parcel currently being delivered`, toutes les 2 minutes à partir de 6 h à l’heure du dernier scan XYL-Ships, tant que le suivi XYL-Ships répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec seulement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, il vérifie toutes les heures quelle que soit ta façon de suivre le colis. Lorsque le suivi XYL-Ships ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que tu as la fiche ouverte ou les notifications activées.

Lorsque le dernier scan date de quatre jours et que le colis reste à « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec la date de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis affiche alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger XYL-Ships. » Après trois semaines sans nouveau scan, elle indique « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger XYL-Ships. »

Tu peux choisir entre les réglages d’alerte « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek assure uniquement le suivi et ne peut ni identifier le transporteur local ni contacter XYL-Ships à ta place.

## Questions sur le suivi XYL-Ships

### Qu’est-ce que XYL-Ships (FXYL) ?

XYL-Ships International Logistics est un transitaire chinois et opérateur e-commerce transfrontalier agréé CAAC et IATA. Les marchands l’utilisent pour acheminer des petits colis depuis la Chine avant la remise aux transporteurs locaux dans ton pays.

### Que signifient les numéros de suivi `NUDE...EX` et `XLWL...EX` ?

Les deux préfixes correspondent à des formats XYL-Ships : `NUDE` ou `XLWL` suivis de 10 chiffres puis `EX` (ou `EXP` pour certains colis `XLWL`). Colle le code complet dans Peek ou dans le champ Track Order sur [xylships.com](https://xylships.com/).

### Pourquoi mon suivi XYL-Ships indique-t-il `about to distribute to the post office` ?

Cela signifie que le colis est arrivé dans ta ville de destination et est en train d’être remis à la poste ou au partenaire de livraison local pour le dernier kilomètre. Attends `Parcel currently being delivered` avant de t’attendre à une livraison le jour même.

### Qui livre les colis XYL-Ships dans mon pays ?

XYL-Ships remet les colis à un partenaire de transport ou à une poste locale après l’arrivée dans le pays de destination, sans nommer ce transporteur dans ses lignes de suivi ; Peek n’affiche pas de second numéro. En cas d’échec de livraison, demande les coordonnées du transporteur local au vendeur.

:::sources
- [XYL-Ships International Logistics](https://xylships.com/) – agréments d’agence aérienne CAAC et IATA, fret aérien/maritime/ferroviaire/routier et petits colis e-commerce transfrontaliers, lien Track Order et e-mail `fzxyl9527@163.com`
- [Universal Parcel Scraper : xyl-ships](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/xyl-ships) – détection des formats `NUDE` et `XLWL`, alias FXYL, intégration JSON, correspondance des statuts anglais, champs ignorés et rythme de vérification
:::
