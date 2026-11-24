---
title: Suivi Sengi Express : codes Chine–Italie et relais Poste
description: Suis un colis Sengi Express (groupe Poste Italiane) de Chine vers l’Italie ou l’Europe : codes SDA/MT, douane, relais Crono, délais et contacts.
slug: suivi-sengi-express
published: 2026-11-24
updated: 2026-11-24
---

**Sengi Express** (*Guangzhou Sengi Logistics Co., Ltd.* / *Seng Yi (Shenzhen) Global Supply Chain Management Co., Ltd.*, en chinois *广州森吉物流有限公司*) est un opérateur logistique transfrontalier contrôlé majoritairement par **Poste Italiane** ([portal.sengiexpress.com/about/](https://portal.sengiexpress.com/about/)). Spécialisé dans l’injection directe e-commerce Chine–Italie et Chine–Europe, Sengi gère la consolidation en Chine, le fret aérien et maritime, le dédouanement européen B2C/B2B sur des plateformes telles que Milan Malpensa (**MXP**), Amsterdam (**AMS**), Liège (**LGG**) et Paris (**CDG**), le transport routier paneuropéen via Sennder (groupe Poste Italiane) et la livraison finale à domicile ou en casier en Italie par le réseau Crono de [Poste Italiane](carrier:poste-italiane). Tu peux suivre un colis sur [portal.sengiexpress.com](https://portal.sengiexpress.com/home) ou coller ton code dans le champ ci-dessus.

## Numéros de suivi Sengi Express (formats SDA `...C...F` et `MT`)

Sengi Express émet fréquemment une lettre de voiture **Poste Italiane / SDA** à 13 caractères dès la Chine afin que le même code fonctionne de l’entrepôt chinois de Sengi jusqu’à la livraison en Italie, parallèlement à des codes transfrontaliers de 16 caractères terminant par `MT` :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `482C73918462F` | Lettre de voiture SDA / Poste Italiane à 13 caractères (`3 chiffres` + `C` + `8 chiffres` + `F`) suivie par Sengi Express avant que Poste Italiane ne la scanne en Italie. |
| `SENGI482917360MT` | Code transfrontalier Sengi à 16 caractères (`5 lettres` + `9 chiffres` + `MT`). |

Sengi attribue sa lettre de voiture SDA à un colis destiné à l’Italie avant même que SDA ne la connaisse, si bien que le suivi Poste Italiane peut ne pas trouver le numéro au départ ; le suivi Sengi accompagne le colis depuis son entrepôt chinois jusqu’au destinataire en Italie. Lorsqu’il est choisi manuellement, le suivi Sengi accepte aussi d’autres références partenaires alphanumériques de 8 à 40 caractères. Voir les [formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi Sengi Express

Sengi Express publie des descriptions de scans transfrontaliers en anglais (et en chinois) accompagnées de codes d’événement facultatifs et d’horodatages :

| Sengi Express indique | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Order information received. We're expecting your parcel to arrive with us.` (code `10`) | Le marchand a transmis les données électroniques de l’envoi avant l’entrée physique dans l’entrepôt chinois de Sengi. | « Étiquette créée » |
| `Arrived at Destinated Airport`, `Terminal Notification of Arrival` | Le vol international a atterri sur la plateforme aéroportuaire européenne d’arrivée (telle que MXP, AMS, LGG ou CDG). | « En transit » |
| `Arrived at customs clearance warehouse` | Le colis est entré dans l’entrepôt sous douane européen pour le dédouanement. | « À la douane » |
| `Customs clearance inspection, Reason: supposed as undervaluation, waiting for customs check(VM)` | Sélectionné par les douanes européennes pour vérification de la valeur déclarée ou du contenu (`VM`). | « À la douane » |
| `Ready for transit to last mile operator` | Dédouané et en attente de remise à [Poste Italiane](carrier:poste-italiane) ou au transporteur de destination. | « En transit » |

Un scan portant un autre libellé tire son étape de son texte. Sengi indique que les destinataires peuvent également suivre le colis sur le site de [Poste Italiane](carrier:poste-italiane), sous la même référence `...C...F` ou sous le nouveau numéro lorsque Sengi réétiquette le colis.

## Injection directe, plateformes douanières européennes et livraison Poste Italiane

Selon [À propos de Sengi Express](https://portal.sengiexpress.com/about/) et [Produits Sengi Express](https://portal.sengiexpress.com/product/) :

- **Plateformes aériennes européennes :** Sengi assure le dédouanement direct B2C et B2B à Milan Malpensa (`MXP`), Amsterdam (`AMS`), Liège (`LGG`) et Paris Charles de Gaulle (`CDG`).
- **Services dernier kilomètre Poste Italiane :** en Italie, Sengi injecte les colis dans le service standard **Crono** de Poste Italiane, **Crono Express**, **Extralarge** (hors format / dernier kilomètre FBA), **Crono ADP** (casiers de retrait) et **Crono Reverse** (retours).
- **Contrôles de valeur en douane (`VM`) et frais UE :** les envois de Chine vers l’UE doivent respecter les règles douanières européennes, la TVA d’importation (IOSS ou perçue à la livraison) et les frais douaniers/de traitement de l’UE sur les commandes hors UE. Si le suivi indique `supposed as undervaluation, waiting for customs check(VM)`, la douane vérifie la valeur déclarée auprès de l’importateur ou du courtier — attends la mainlevée ou préviens ton vendeur. Voir les [colis retenus à la douane](guide:customs).

## Colis Sengi Express bloqué, sans nouvelles ou manquant

Comme les places de marché et marchands chinois détiennent le contrat de transport avec Sengi Express, **contacte toujours d’abord ton vendeur ou la plateforme** pour un remboursement, une réexpédition ou des justificatifs de valeur en douane.

- **Silence avant les scans italiens :** les colis restent souvent plusieurs jours sans scan entre `Order information received`, `Arrived at Destinated Airport` et `Ready for transit to last mile operator` pendant le dédouanement européen par lots et le transport routier vers l’Italie. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **Suivre sur Poste Italiane après remise :** dès que `Ready for transit to last mile operator` apparaît sur un envoi italien `PI`, suivre le même numéro `...C...F` (ou le numéro réétiqueté affiché par Peek) sur [Poste Italiane](carrier:poste-italiane) donne accès au tri italien, aux passages de livraison et aux détails Punto Poste / casier.
- **Perdu, endommagé ou indiqué livré :** vérifie d’abord chez tes voisins et à ton bureau Poste Italiane / Punto Poste, puis ouvre un litige sur la plateforme avant la fin de la protection acheteur. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel) et [colis retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contacts Sengi Express

Coordonnées officielles publiées sur [À propos de Sengi Express](https://portal.sengiexpress.com/about/) et [Contact Sengi Express](https://portal.sengiexpress.com/contact/) :

- **E-mail :** `service@sengiexpress.com`.
- **Formulaire de contact en ligne :** [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).
- **Opérateur :** *Guangzhou Sengi Logistics Co., Ltd.* (*广州森吉物流有限公司*, ICP `粤ICP备2021101066号`), contrôlé majoritairement par Poste Italiane.

> Méfie-toi des faux SMS prétendant qu’une taxe Poste Italiane ou douanière doit être réglée via un lien inconnu. Vérifie toujours tout frais d’importation réel sur ta page de commande ou le site officiel de Poste Italiane. Voir les [arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis Sengi Express avec Peek

Lorsque tu saisis un code `...C...F` à 13 caractères (`482C73918462F`, inventé), [Peek](/) interroge [Poste Italiane](carrier:poste-italiane) en premier, puis AliExpress et Sengi Express (Sengi avant AliExpress en Italie ; en Chine AliExpress d’abord, puis Sengi), et affiche « Sengi Express a ce colis. » lorsque Sengi le détient. Pour un code `...MT` (`SENGI482917360MT`, inventé), Peek interroge [MaltaPost](carrier:maltapost), dont les numéros se terminent aussi par `MT` : pour ce code ou une autre lettre de voiture partenaire, appuie sur « Changer » à l’ajout ou « Changer de transporteur » sur la fiche du colis et choisis **Sengi Express** (rechercher « Sengi » le trouve aussi).

Peek lit le suivi Sengi et affiche l’étape (« Étiquette créée », « En transit », « À la douane », ou une autre étape lorsqu’un libellé de scan l’indique), le libellé anglais de Sengi (le chinois à défaut d’anglais) et le lieu. Les codes d’événement déterminent l’étape et restent masqués dans toutes les applications Peek. Sengi indique un décalage horaire pour ses scans ; un scan sans décalage est daté sur le fuseau horaire de son lieu (heure d’Italie pour `MXP`, heure de Pékin pour Canton) ou sinon sur celui du scan voisin, et Peek affiche le tout à l’heure de ton appareil. Sur la liaison Poste Italiane de Sengi, Peek vérifie que Poste Italiane a le colis : la fiche affiche alors « Livraison par » suivi du nom de Poste Italiane, et indique le nouveau numéro lorsque Sengi réétiquette le colis.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où Sengi afficherait un scan de mise en livraison (aucun observé jusqu’ici), toutes les 2 minutes à partir de 6 h sur l’horloge du dernier scan tant que le suivi Sengi répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, Peek passe à une vérification par heure quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi Sengi ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Sengi Express. », avec un lien « Demande de l’aide à Sengi Express » vers la page de contact de Sengi. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger Sengi Express. »

Tu peux choisir des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek suit uniquement les mises à jour de suivi et ne peut ni dédouaner un colis, ni modifier une adresse de livraison, ni contacter Sengi Express ou Poste Italiane à ta place.

## Questions sur le suivi Sengi Express

### Qu’est-ce que Sengi Express ?

Sengi Express (*Guangzhou Sengi Logistics Co., Ltd.*) est un opérateur logistique e-commerce transfrontalier Chine–Italie et Chine–Europe contrôlé par **Poste Italiane**, qui assure la consolidation d’origine, le dédouanement européen (MXP, AMS, LGG, CDG) et l’injection dernier kilomètre dans le réseau Crono de Poste Italiane.

### Pourquoi mon code Poste Italiane `...C...F` apparaît-il d’abord chez Sengi Express ?

Sengi attribue à un colis vers l’Italie sa lettre de voiture SDA / [Poste Italiane](carrier:poste-italiane) (`482C73918462F`, inventé) avant que SDA ne la connaisse. Le suivi Sengi montre les scans de vol et de douane alors que celui de Poste Italiane peut ne pas encore trouver le numéro.

### Que signifie `supposed as undervaluation, waiting for customs check(VM)` ?

Les douanes européennes ont sélectionné le colis pour un contrôle de la valeur déclarée (`VM`). Le courtier en douane ou le vendeur gère les justificatifs ; contacte ton vendeur si le statut persiste.

### Comment contacter le service client Sengi Express ?

Contacte d’abord ta boutique en ligne ou place de marché pour un remboursement ou des justificatifs douaniers. Sengi Express indique `service@sengiexpress.com` et un formulaire en ligne sur [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).

:::sources
- [Sengi Express : portail de suivi](https://portal.sengiexpress.com/home) – suivi public de lettres de voiture et mentions *Guangzhou Sengi Logistics Co., Ltd.*
- [Sengi Express : à propos](https://portal.sengiexpress.com/about/) – contrôle majoritaire par Poste Italiane, services transfrontaliers et `service@sengiexpress.com`
- [Sengi Express : produits et services](https://portal.sengiexpress.com/product/) – plateformes douanières MXP/AMS/LGG/CDG, Poste Italiane Crono / Crono Express / Extralarge / Crono ADP / Crono Reverse et transport routier européen Sennder
- [Sengi Express : contact](https://portal.sengiexpress.com/contact/) – page officielle de contact destinataire/client (URL d’aide liée par Peek)
- [Universal Parcel Scraper : sengi-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sengi-express) – règles de détection `...C...F` et `...MT`, analyseur `api-v2/tracking/web`, correspondance de statuts, gestion du décalage UTC et relais Poste Italiane `PI` / `relabelTrackingNumber`
:::
