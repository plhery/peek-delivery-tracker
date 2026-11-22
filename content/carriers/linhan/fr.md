---
title: Suivi LinHan International : numéros LHGJ et statuts
description: Suis un colis LinHan International depuis Shenzhen : format LHGJ, sens de chaque scan et pourquoi ses heures sont à l’heure de Pékin.
slug: suivi-linhan
published: 2026-11-22
updated: 2026-11-22
---

LinHan International (深圳市林瀚国际物流有限公司, Shenzhen LinHan International Logistics Co., Ltd.) est un transitaire basé à Shenzhen, en Chine, qui expédie par avion les colis de vendeurs en ligne hors de Chine puis les remet à un agent dans le pays de l’acheteur pour le dédouanement et la livraison. Ses numéros commencent par `LHGJ` : saisis le tien sur le suivi de LinHan, [linhan56.com/index/tracknumber/track](https://www.linhan56.com/index/tracknumber/track), ou dans le champ ci-dessus.

## Numéro de suivi LinHan : à quoi il ressemble

LinHan ne publie pas de format officiel. Ses bordereaux se composent de `LHGJ`, huit chiffres et `CD` (avec des chiffres fictifs) :

| Numéro | À quoi il correspond |
| --- | --- |
| `LHGJ48291037CD` | `LHGJ`, huit chiffres et `CD` : un numéro de suivi LinHan International. |
| `LHGJ4829103CD` | Seulement sept chiffres : ce n’est pas un format LinHan. Vérifie qu’il ne manque pas un chiffre. |

Les minuscules et espaces ne comptent pas quand tu colles le numéro dans Peek.

**Où le trouver.** LinHan travaille pour les vendeurs : sa page d’accueil leur propose des tarifs en direct et la réservation sur ses lignes, et n’offre aux destinataires que l’outil de suivi. Le numéro vient de la boutique où tu as commandé ; voir [comment retrouver un numéro de suivi](guide:find-tracking-number).

**Sur le suivi LinHan.** L’outil accepte jusqu’à 20 numéros par recherche, séparés par des virgules ou des retours à la ligne, et n’affiche que les colis des deux derniers mois.

## Statuts de suivi LinHan expliqués

LinHan ne publie pas de liste de ses statuts. Son outil est en chinois, mais les scans sont en anglais, du plus récent au plus ancien. Au-dessus, une ligne récapitulative donne le pays de destination et un statut en chinois :

| LinHan indique | Ce que cela signifie pour toi |
| --- | --- |
| `运输途中` | « En transit » : le colis est en route. |
| `成功签收` | « Signé » : livré. |
| `查询不到` | « Introuvable » : le suivi ne connaît aucun colis sous ce numéro et aucun scan ne suit. |

Voici les scans des étapes propres à LinHan en Chine, dans l’ordre où un colis les rencontre :

| LinHan indique | Ce que cela signifie pour toi |
| --- | --- |
| `Order information received` | Le vendeur a transmis les données de la commande à LinHan. LinHan n’a pas encore le colis. |
| `Arrive at the cross border receiving point` | LinHan a reçu le colis dans son centre de réception transfrontalier en Chine. `Arrive at the receiving point` signifie la même chose. |
| `Goods leave the operation center` | Le colis a quitté le centre opérationnel de LinHan. |
| `Arrival at domestic internationality airport` | Arrivé à l’aéroport de départ en Chine. |
| `Departure from the airport of origin domestic` | Le vol a quitté la Chine. |

Viennent ensuite les étapes dans ton pays, où l’agent de LinHan et le réseau de livraison prennent le relais :

| LinHan indique | Ce que cela signifie pour toi |
| --- | --- |
| `Item arrives at destination airport` | Atterri dans ton pays. `Arrive at the destination international airport` signifie la même chose. |
| `local carrier Agent Import customs clearance` | L’agent de LinHan dédouane le colis à l’importation. Il n’est pas encore libéré. `Import customs clearance`, précédé de la ville portuaire, désigne la même étape. |
| `transfer to local acting company International self-operation team` | Transféré à l’agent dans ton pays. |
| `Internationaliy Package received at processing center` | Orthographe d’origine de LinHan. Premier scan du réseau de livraison, dans un centre de traitement. Ce n’est pas un nouveau départ : LinHan a pris le colis en Chine. |
| `Leave the processing center and transport to … processing center` | En route entre deux centres de traitement, la ville suivante remplaçant « … ». `Leave the … processing center` nomme celui qu’il vient de quitter. |
| `Arrive at the sorting and distribution center` | Dans un centre de tri, dont la ville suit « Arrive ». `Leaving the sorting center` indique sa sortie. |
| `Arrive at the last mile sorting and distribution center` | Au centre de tri du réseau de livraison final, précédé de la ville, de l’État et du code postal aux États-Unis. |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | Scan du réseau de livraison, dont le nom est remplacé par « logistics carrier ». |
| `Accepted at logistics carrier Regional Destination Facility` | Scan de ce réseau près de chez toi, précédé de la ville, de l’État et du code postal. « Accepted » n’est pas un nouveau départ : le colis reste en transit. |
| `package is on the way` | En chemin depuis le centre de tri local, après le nom de la ville. Cela n’indique pas encore une livraison aujourd’hui. |
| `Delivered` | Livré. |

De nombreuses lignes dans ton pays commencent par la ville (aux États-Unis : ville, État et code postal), et la ligne de livraison comporte aussi ton code postal : ville, code postal, Delivered. Une même ligne peut apparaître deux fois.

**Heures à l’heure de Pékin.** Le suivi de LinHan inscrit chaque horaire à l’heure de Pékin (UTC+8), y compris pour les scans dans ton pays. Comme Pékin change de date à 16 h UTC, un scan effectué entre cette heure et ton minuit affiche la date du lendemain : en France ou en Allemagne dès 17 h en hiver et 18 h en été, à New York dès 11 h en hiver et midi en été.

## Qui livre les colis LinHan

La page d’accueil de LinHan résume sa chaîne ainsi : collecte, entreposage et tri, dédouanement en Chine, fret aérien, déclaration en douane à l’étranger, entrepôts à l’étranger et livraison (揽货, 仓储分拣, 国内通关, 空运, 海外报关, 海外仓, 海外派送). Elle ne nomme pas de destinations ; les colis observés sur son suivi allaient vers les États-Unis et l’Allemagne.

Dans ton pays, un agent prend le relais. Le suivi de LinHan l’appelle « local carrier Agent » et « local acting company », et désigne le réseau de livraison par « logistics carrier », sans nommer d’entreprise ni fournir de second numéro de suivi. Son historique se poursuit toutefois jusqu’à la livraison : le suivi de LinHan reste donc l’endroit où suivre le colis.

LinHan ne publie aucun délai de livraison ni règle concernant les tentatives, les points relais ou les délais de garde. Si un avis ou message du transporteur local le nomme, ce sont ses règles qui s’appliquent : voir [livraison manquée](guide:missed-delivery).

## Colis LinHan bloqué ou qui n’arrive pas

**Uniquement `Order information received`.** Le vendeur a réservé l’envoi, mais le colis n’a pas encore atteint LinHan. Si rien ne suit après plusieurs jours, demande au vendeur s’il a bien expédié.

**Rien après le vol.** Après `Departure from the airport of origin domestic`, la ligne suivante apparaît à l’atterrissage ou à l’arrivée en douane dans ton pays. Plus de détails dans [suivre un colis depuis la Chine](guide:tracking-from-china) et [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**En douane.** Le suivi de LinHan n’indique pas si des frais sont dus et ne nomme pas le transporteur final : un SMS réclamant un paiement est donc difficile à vérifier. Consulte [les SMS frauduleux de livraison](guide:delivery-scam-texts) avant de payer, et [colis bloqué en douane](guide:customs).

**`查询不到`.** Le suivi ne connaît pas ce numéro : vérifie-le avec le vendeur. L’outil n’affichant que les colis des deux derniers mois, un envoi plus ancien n’y apparaît pas non plus.

**Livré, mais introuvable.** Si la dernière ligne est `Delivered`, consulte [colis livré mais non reçu](guide:delivered-not-received) et préviens le vendeur. Le suivi de LinHan ne montre pas de photo de livraison.

**Perdu, endommagé ou retourné.** LinHan ne publie aucune procédure de réclamation, d’indemnisation ni de retour. Le vendeur ayant réservé le transport, demande-lui ou à la place de marché un remboursement ou un nouvel envoi. Voir [colis perdu](guide:lost-parcel).

## Contact LinHan International

La page d’accueil de LinHan, uniquement en chinois, se termine par un bloc 联系我们 (Nous contacter) indiquant une adresse e-mail et une adresse de service à Pinghu, district de Longgang, Shenzhen. Elle ne donne ni numéro de téléphone, ni chat, ni formulaire, ni aide dédiée aux destinataires : pour toute question sur ta commande, contacte le vendeur.

## Suivre un colis LinHan avec Peek

[Peek](/) reconnaît un numéro LinHan à sa forme (`LHGJ`, huit chiffres et `CD`), qu’aucun autre transporteur de son catalogue de plus de 4 500 transporteurs n’utilise, et affiche aussitôt « Transporteur détecté ». Un lien vers le suivi de LinHan contenant `nos=` et le numéro peut aussi être collé.

Peek lit le suivi de LinHan et affiche le statut, chaque scan avec sa ville quand LinHan la fournit, le pays de destination ainsi que le signataire si la ligne de livraison en indique un. Il sépare la ville du libellé pour l’afficher comme lieu et retire les codes postaux, de sorte qu’une livraison s’affiche `Delivered` avec sa ville. Une ligne listée deux fois par LinHan n’apparaît qu’une fois, et les scans gardent l’anglais d’origine de LinHan, `Internationaliy` compris. Peek lit chaque heure sur l’heure de Pékin (UTC+8), telle que le suivi l’écrit, puis l’affiche dans ton fuseau horaire (« Heures dans ton fuseau horaire : »).

Certaines lignes comptent différemment de ce qu’elles suggèrent : `Order information received` correspond à « Étiquette créée » ; `Internationaliy Package received at processing center` et `Accepted at logistics carrier Regional Destination Facility` restent « En transit », et non une nouvelle prise en charge ; les deux lignes douanières passent en « À la douane » ; `package is on the way` reste « En transit », car il n’indique pas une tournée de livraison aujourd’hui.

LinHan ne fournit ni date de livraison prévue, ni poids, ni nom de service, ni transporteur final, ni second numéro : Peek n’en affiche donc aucun et suit le numéro `LHGJ` jusqu’au bout sans transfert vers un autre transporteur.

Peek vérifie jusqu’à toutes les 10 minutes, et toutes les 2 minutes à partir de 6 h sur l’horloge du dernier scan (heure de Pékin) le jour où le colis passe « En livraison » tant que tu gardes la fiche ouverte dans Peek ou que les notifications sont activées, lorsque le suivi de LinHan répond. Après deux jours sans nouvelle (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Pour les notifications, tu peux choisir « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ». Après quatre jours sans scan en transit, hors douane ou problème signalé, Peek affiche « Aucune mise à jour depuis … » et rappelle « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger LinHan International. » Peek ne peut pas contacter LinHan ni le vendeur à ta place.

## Questions sur le suivi LinHan

### Qu’est-ce que LinHan International ?

Shenzhen LinHan International Logistics (深圳市林瀚国际物流有限公司), un transitaire sur linhan56.com qui achemine par avion les colis de vendeurs hors de Chine vers un agent chargé du dédouanement et de la livraison.

### Où suivre un numéro LHGJ ?

Sur le suivi de LinHan, [linhan56.com/index/tracknumber/track](https://www.linhan56.com/index/tracknumber/track), ou dans Peek. La page d’accueil de LinHan propose aussi un lien vers 17TRACK.

### Qui livre les colis LinHan ?

Un agent dans ton pays, que le suivi de LinHan appelle « local carrier » ou « local acting company » sans le nommer. Ses scans apparaissent sur le suivi de LinHan jusqu’à la livraison.

### Pourquoi les heures de scan LinHan semblent-elles en avance d’un jour ?

Parce que le suivi de LinHan inscrit chaque horaire à l’heure de Pékin (UTC+8), même pour les scans dans ton pays. Un scan effectué après 16 h UTC, alors qu’il est déjà le lendemain à Pékin, porte donc la date du lendemain. Peek convertit les heures dans le fuseau horaire de ton appareil.

### Qui contacter pour un colis LinHan introuvable ?

Le vendeur chez qui tu as commandé : c’est lui qui a réservé le transport auprès de LinHan. La page d’accueil de LinHan ne donne qu’une adresse e-mail et une adresse à Shenzhen, sans support destiné aux destinataires.

:::sources
- [LinHan International : 深圳市林瀚国际物流有限公司 (accueil)](https://www.linhan56.com/) – nom de l’entreprise, fret aérien transfrontalier pour expéditeurs, chaîne de la collecte à la livraison à l’étranger, liens vers le suivi et 17TRACK, bloc contact
- [LinHan International : 快件跟踪 (suivi)](https://www.linhan56.com/index/tracknumber/track) – outil de suivi en chinois, colis des deux derniers mois, jusqu’à 20 numéros par recherche
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format LHGJ, statuts récapitulatifs et libellés des scans, agent de destination et réseau anonyme, horloges à l’heure de Pékin, villes et codes postaux, destinations observées, ce que Peek affiche et écarte
:::
