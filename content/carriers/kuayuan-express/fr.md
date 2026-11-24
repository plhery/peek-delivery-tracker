---
title: Suivi Kuayuan Express : codes, relais locaux et aide
description: Suis un colis Kuayuan Express (跨源速递) Chine–États-Unis ou Europe : format à 14 caractères, codes de scan (AF, ND, CC), relais locaux et contacts.
slug: suivi-kuayuan-express
published: 2026-11-24
updated: 2026-11-24
---

Kuayuan Express (*Shenzhen Kuayuan Express Supply Chain Co., Ltd.*, en chinois *跨源速递* / *深圳市跨源速递供应链有限公司*) est un prestataire logistique e-commerce transfrontalier basé à Shenzhen, spécialisé dans les lignes colis de bout en bout depuis la Chine vers l’Europe et les États-Unis. Sur [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/), Kuayuan assure la collecte du premier kilomètre et la consolidation en Chine, l’acheminement aérien et maritime, le dédouanement automatisé par code SH (canaux DDP taxes comprises) et la livraison finale via des partenaires locaux de destination ou sa propre flotte. Tu peux consulter jusqu’à 20 lettres de voiture à la fois sur [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/) ou coller ton code Kuayuan à 14 caractères dans le champ ci-dessus.

## Numéros de suivi Kuayuan Express (format à 14 caractères)

Les codes colis transfrontaliers Kuayuan Express comportent 14 caractères : un chiffre (généralement `3`), suivi de trois lettres majuscules et de 10 chiffres (encodant l’année sur un chiffre, le mois, le jour et un numéro de série à 5 chiffres) :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `3KAA6101548291` | Lettre de voiture Kuayuan Express à 14 caractères (`3` + 3 lettres + date `YMMDD` + série à 5 chiffres). |
| `3KBC6102873914` | Autre code colis transfrontalier Kuayuan Express à 14 caractères. |

Kuayuan peut aussi indiquer le numéro de la ligne à laquelle il remet le colis (`server_hawbcode`), par exemple un code-barres [DPD Allemagne](carrier:dpd-de), à côté de son propre numéro à 14 caractères. Voir les [formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi Kuayuan Express

Le système de suivi de Kuayuan associe de courts codes en majuscules à des descriptions anglaises couvrant ses opérations d’entrepôt en Chine, la douane export, la remise à la compagnie aérienne et les mouvements relayés du transporteur de destination :

| Code Kuayuan | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `AF` | Arrivé dans un centre opérationnel ou de tri Kuayuan. | « En transit » |
| `DF` | Tri et traitement sortant terminés au centre opérationnel. | « En transit » |
| `ECKSBG` | Déclaration douanière export déposée en Chine. | « En transit » |
| `ECYDG` | Arrivé au port ou hub aéroportuaire de départ. | « En transit » |
| `JH` | Remis à la compagnie aérienne ou maritime internationale. | « En transit » |
| `ND` | En transit sur la ligne internationale ou le transporteur local du dernier kilomètre. | « En transit » |
| `CF` | Échec d’une tentative de livraison à l’adresse du destinataire. | « Tentative de livraison » |
| `UE` | Colis retourné à l’expéditeur après échec de livraison ou refus. | « Retourné à l’expéditeur » |
| `CC` | Livré et signé avec succès à destination. | « Livré » |

Les scans `ND` reprennent les propres termes de la ligne partenaire : anglais pour DPD Allemagne, chinois pour China Post (`中国邮政已收取邮件`). Un scan doté d’un autre code, ou sans code, détermine son étape à partir de son libellé.

## Délais de livraison, douane et relais locaux

Selon [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/) :

- **Délais annoncés :** Kuayuan annonce **7 à 10 jours** porte-à-porte sur sa ligne aérienne marchandises générales vers les États-Unis et **7 à 10 jours** vers les principaux pays européens grâce à des vols réguliers et des opérations locales.
- **Canaux marchandises spéciales :** en plus des marchandises générales, Kuayuan achemine l’électronique avec batterie, les cosmétiques/liquides et les compléments alimentaires via des canaux conformes dédiés.
- **Dédouanement et DDP :** Kuayuan met en avant des canaux double dédouanement taxes incluses (DDP) pour l’Europe et les États-Unis avec correspondance automatisée des codes SH. Pour les envois non réservés en DDP, la TVA d’importation et les droits de douane (y compris les frais UE sur les commandes hors UE) peuvent s’appliquer. Voir les [colis retenus à la douane](guide:customs).
- **Livraison locale :** après le dédouanement import, l’équipe internationale de Kuayuan remet le colis à un partenaire local, tel que [DPD Allemagne](carrier:dpd-de), ou le livre avec ses propres véhicules. Le transporteur local gère les passages à domicile, points relais et relivraisons.

## Colis Kuayuan Express bloqué, sans nouvelles ou manquant

Comme les marchands et vendeurs transfrontaliers détiennent le compte d’expédition auprès de Kuayuan Express, **contacte toujours d’abord ton vendeur ou la plateforme** pour toute modification d’adresse, remboursement ou réexpédition.

- **Silence entre la Chine et la destination :** le suivi reste souvent plusieurs jours sans mise à jour entre `JH` (remis à la compagnie aérienne) et le premier scan `ND` à destination pendant l’arrivée du fret aérien et le dédouanement par lots. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Suivre le numéro du transporteur partenaire :** dès que Kuayuan associe le numéro de suivi du partenaire local, consulter directement ce transporteur donne souvent accès aux créneaux de livraison, codes de retrait en point relais ou reprogrammations.
- **Livraison échouée, colis manquant ou endommagé :** si le suivi indique `CF` (échec de livraison), `UE` (retour) ou `CC` (livré) sans colis reçu, vérifie d’abord chez tes voisins et auprès du transporteur local, puis ouvre un litige auprès du vendeur avant la fin de la protection acheteur. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel) et [colis retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts Kuayuan Express

Coordonnées officielles publiées sur [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/) :

- **Raison sociale :** *Shenzhen Kuayuan Express Supply Chain Co., Ltd.* (*深圳市跨源速递供应链有限公司*, ICP Guangdong `粤ICP备2025475526号`).
- **Téléphone :** `+86 15013702902`.
- **E-mail :** `fanjinyun@kuayuanexpress.com`.
- **Suivi web public :** [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/) (jusqu’à 20 numéros par recherche).

> Méfie-toi des faux SMS te demandant de payer une petite taxe douanière ou de relivraison via un lien inconnu. Confirme toujours tout frais d’importation réel via la page de ta commande ou le site officiel du transporteur local. Voir les [arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis Kuayuan Express avec Peek

[Peek](/) interroge automatiquement Kuayuan Express comme seul candidat lorsque tu saisis une lettre de voiture Kuayuan à 14 caractères commençant par `3` (`3KAA6101548291` ou `3KBC6102873914`, inventés) et affiche « Kuayuan Express a ce colis. » dès que Kuayuan le confirme. Si ton code Kuayuan commence par un autre chiffre ou si tu dois le choisir manuellement, appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis (rechercher « 跨源速递 » ou « Kuayuan » le trouve aussi).

Peek lit le suivi Kuayuan en anglais et affiche l’étape (« En transit », « Tentative de livraison », « Retourné à l’expéditeur », « Livré », ou une autre étape lorsqu’un scan sans l’un des neuf codes l’indique), le libellé du scan, le lieu et le pays de destination. Les codes de scan ne s’affichent dans aucune application Peek. Sur ta propre fiche colis, Peek affiche le nom du destinataire sous « Destinataire » et, tant que le colis est en route, peut terminer l’itinéraire sur la carte dans la ville du destinataire ; les liens partagés n’affichent ni l’un ni l’autre. Kuayuan fournit des heures de scan sans fuseau : Peek date chaque scan sur le fuseau horaire de son lieu (Los Angeles pour un scan à Los Angeles, Pékin pour Shenzhen), un scan au lieu inconnu sur le fuseau du scan voisin, et un colis sans aucun lieu sur l’heure de Pékin, puis affiche tout à l’heure de ton appareil. Lorsque Kuayuan indique le numéro du transporteur partenaire et que Peek reconnaît ce transporteur, Peek vérifie qu’il a le colis : la fiche affiche alors « Livraison par » suivi du nom du transporteur, et indique les deux numéros. Le suivi Kuayuan ne donne pas de date de livraison estimée, de poids ni d’heure de livraison dédiée.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Kuayuan affiche un scan de mise en livraison, toutes les 2 minutes à partir de 6 h sur le fuseau horaire du lieu du scan le plus récent tant que le suivi propre de Kuayuan répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, Peek passe à une vérification par heure quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi Kuayuan ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour où le colis est en livraison, tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun 12 oct ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Kuayuan Express. » Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Kuayuan Express. »

Tu peux choisir des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek suit uniquement les mises à jour de suivi et ne peut ni dédouaner un colis, ni modifier l’adresse de livraison, ni contacter Kuayuan Express ou ton transporteur local à ta place.

## Questions sur le suivi Kuayuan Express

### Qu’est-ce que Kuayuan Express (*跨源速递*) ?

Kuayuan Express (*Shenzhen Kuayuan Express Supply Chain Co., Ltd.*) est un transporteur logistique e-commerce transfrontalier basé à Shenzhen qui exploite des lignes colis aériennes et maritimes dédiées de la Chine vers l’Europe et les États-Unis.

### À quoi ressemble un numéro de suivi Kuayuan Express ?

Les lettres de voiture Kuayuan Express comptent 14 caractères : un chiffre (généralement `3`), trois lettres majuscules et 10 chiffres — par exemple `3KAA6101548291` (inventé).

### Que signifient les codes `AF`, `JH`, `ND` et `CC` dans le suivi Kuayuan Express ?

`AF` signifie que le colis est arrivé dans un centre opérationnel Kuayuan, `JH` qu’il a été remis à la compagnie aérienne, `ND` couvre le transit sur la ligne de destination et `CC` indique que le colis a été livré.

### Comment contacter Kuayuan Express ?

Contacte d’abord ton vendeur ou la place de marché pour toute question sur la commande. Kuayuan Express indique `+86 15013702902` et `fanjinyun@kuayuanexpress.com` sur [kuayuanexpress.com.cn](https://www.kuayuanexpress.com.cn/).

:::sources
- [Kuayuan Express : site officiel et suivi](https://www.kuayuanexpress.com.cn/) – *Shenzhen Kuayuan Express Supply Chain Co., Ltd.*, suivi de 20 numéros, lignes États-Unis/Europe 7–10 jours, douane DDP, téléphone `+86 15013702902` et e-mail `fanjinyun@kuayuanexpress.com`
- [Universal Parcel Scraper : kuayuan-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/kuayuan-express) – format à 14 caractères, API `getTrackInfoOutside`, 9 codes de scan (`AF`–`UE`), relais partenaire `server_hawbcode` et datation de chaque scan sur le fuseau de son lieu
:::
