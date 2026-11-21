---
title: Suivi Jifan : numéros JFGJ, statuts et heure de Pékin
description: Suis un colis Jifan (吉凡国际物流) de Chine continentale vers les États-Unis : format JFGJ…CN, sens des statuts en anglais et chinois et horaire de Pékin.
slug: suivi-jifan
published: 2026-11-21
updated: 2026-11-21
---

Jifan est Shenzhen Jifan International Logistics (`深圳市吉凡国际物流有限公司`, ou `吉凡国际物流`), un transitaire fondé en 2017 à Shenzhen, en Chine continentale, membre NVOCC. Il achemine des colis pour des vendeurs professionnels depuis la Chine continentale vers des acheteurs aux États-Unis, par avion et par bateau. Ses numéros de suivi se composent de `JFGJ`, neuf chiffres et `CN`. Consulte le tien sur [le traceur de Jifan](https://www.szjf56.cn/index/tracknumber/track) ou colle-le dans le champ ci-dessus ; dans le catalogue de Peek, chercher `Jifan International Logistics`, `吉凡国际物流` ou `深圳市吉凡国际物流有限公司` trouve aussi Jifan.

## Numéro de suivi Jifan : à quoi il ressemble

Le site de Jifan ne documente pas le format de ses bordereaux, mais ils suivent une structure unique. `JFGJ` reprend les initiales de l'entreprise en pinyin : `吉凡国际` (*Jifan Guoji*, « Jifan International »). Exemples entièrement inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `JFGJ482910573CN` | `JFGJ`, neuf chiffres et `CN` : un numéro Jifan (sans contrôle de clé sur le neuvième chiffre). |
| `JFGJ48291057CN` | Seulement huit chiffres : pas le format de Jifan, et Peek ne désigne pas Jifan. Vérifie s'il manque un chiffre. |
| `RR482910576CN` | Deux lettres, neuf chiffres et `CN` : un numéro [China Post](carrier:china-post), pas Jifan, malgré la fin en `CN`. |

Sur `peektracker.com`, un code `JFGJ…CN` auquel il manque un caractère affiche cette note : « Ça commence comme un numéro de suivi Jifan, mais il manque un caractère : il y a peut-être une faute de frappe. Compare-le avec ton message d’expédition. » Dans Peek, minuscules, espaces et tirets n'ont pas d'importance. Sur le traceur de Jifan, saisis le numéro en majuscules et sans espaces.

**Où le trouver.** Dans l'e-mail d'expédition du vendeur ou sur la page de ta commande. Si tu ne le vois pas, consulte [comment trouver son numéro de suivi](guide:find-tracking-number).

**Sur le site de Jifan.** La page d'accueil (`szjf56.cn`, rubrique `单号查询`) accepte jusqu'à 10 numéros séparés par Entrée, et la page dédiée `快件跟踪` possède un bouton `跟踪(Track)` qui accepte jusqu'à 20 numéros à la fois mais ne conserve que les colis des deux derniers mois (`2个月内的包裹`).

## Statuts de suivi Jifan expliqués

L'interface du traceur de Jifan est en chinois, mais les lignes d'historique sont en anglais, de la plus récente à la plus ancienne, chacune accompagnée de son horodatage. Au-dessus, une ligne récapitulative donne la destination (`目的地`), l'état général (`状态`), la dernière heure (`最新时间`) et la dernière entrée (`最新记录`). Deux états récapitulatifs courants :

| Le traceur affiche | Ce que cela veut dire pour toi |
| --- | --- |
| `运输途中` | « En cours d'acheminement » en chinois. Lis les lignes en dessous pour voir où en est réellement le colis. |
| `查询不到` | « Introuvable » en chinois. Le traceur n'a aucun colis sous ce numéro et n'affiche aucune étape. |

Viennent d'abord les étapes propres à Jifan en Chine continentale, de la transmission des données au départ de l'avion, sans nom de ville, puis les étapes aux États-Unis transmises par l'agent d'arrivée et le réseau de livraison finale (dont le traceur remplace le nom par `logistics carrier`). Le traceur de Jifan repose sur le système de CTTYEX et formule ses lignes de la même façon :

| Jifan indique | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Order information received` | Le vendeur a transmis les données de l'envoi ; Jifan n'a pas encore le colis. | « Étiquette créée » |
| `Arrive at the cross border receiving point`, `Arrive at the receiving point` | Jifan a pris en charge le colis à son point de réception transfrontalier en Chine continentale. | « Pris en charge » |
| `Goods leave the operation center` | Le colis a quitté le centre d'exploitation de Jifan en Chine continentale. | « En transit » |
| `Arrival at domestic internationality airport` | Arrivé à l'aéroport de départ en Chine continentale (`internationality` est l'orthographe du traceur). | « En transit » |
| `Departure from the airport of origin domestic` | Le vol a quitté la Chine continentale. | « En transit » |
| `Arrive at the destination international airport` | L'avion a atterri à l'aéroport international d'arrivée aux États-Unis. | « En transit » |
| `Import customs clearance` | Contrôle douanier à l'importation aux États-Unis (précédé de la ville d'entrée). | « À la douane » |
| `Leave the … processing center` | Départ du centre de traitement américain indiqué à la place des points de suspension. | « En transit » |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | Passage dans un centre du transporteur américain vers le centre suivant. | « En transit » |
| `Accepted at logistics carrier Regional Destination Facility` | Prise en charge au centre régional du transporteur américain près du destinataire ; le statut reste en transit puisque Jifan suit le colis depuis la Chine continentale. | « En transit » |

Une ligne annonçant la livraison finale passe en « Livré ». Aux États-Unis, certaines lignes commencent par la ville, l'État et le code ZIP.

**Toutes les heures sont à l'heure de Pékin.** Le traceur de Jifan affiche ses horaires sans fuseau : chacun est exprimé à l'heure de Pékin (`UTC+8`), y compris pour les scans effectués aux États-Unis. La date change à Pékin à `16:00 UTC` : un scan réalisé à New York après 11 h en hiver (midi en été), ou à Los Angeles après 8 h en hiver (9 h en été), porte donc déjà la date du lendemain sur le traceur de Jifan.

## Qui livre les colis Jifan

Le site de Jifan présente quatre lignes commerciales, toutes au départ de la Chine continentale vers les **États-Unis** :

- **Petit paquet économique États-Unis** (`美国经济小包-轻小件`) : articles plats de moins de 360 g et de moins de 1,8 cm d'épaisseur, vols cargos directs, livrés par [UPS](carrier:ups) dans l'ensemble des États-Unis (îles américaines d'outre-mer et adresses militaires incluses), suivis de bout en bout. Délai annoncé : environ 10 à 12 jours calendaires (`自然日`).
- **Petit paquet aérien États-Unis** (`美国空运小包标快`) : de 0 à 4,5 kg, vols cargos directs, livraison par UPS (`UPS MI` selon Jifan) sur tout le territoire américain, îles et adresses militaires comprises. Environ 8 à 10 jours calendaires.
- **Colis maritimes Matson** (`美森正班（限时达）海运大/小包`) : jusqu'à 4,5 kg, livrés comme la ligne aérienne (`UPS MI`, tous les États-Unis) ; de 4,5 à 68 kg, livrés par `UPS GROUND` uniquement aux États-Unis continentaux (`仅限美国本土`). Environ 20 à 25 jours calendaires.
- **Hors gabarit maritime États-Unis** (`美国海运超大件专线`) : de 100 à 2 000 kg par pièce sur navires Matson et COSCO puis par camion jusqu'à la porte (adresses commerciales et résidentielles), uniquement aux États-Unis continentaux hors îles et adresses militaires. Environ 25 à 40 jours calendaires.

Pour les petits paquets économiques et ceux jusqu'à 4,5 kg par air ou par mer, Jifan annonce un bordereau unique de bout en bout (`一单到底`). Son traceur ne nomme pas le livreur final et ne fournit aucun second numéro ; si le vendeur t'a aussi transmis un numéro UPS, suis-le directement sous [UPS](carrier:ups). Jifan ne publie aucune consigne destinataire sur les avis de passage ou points de retrait ([livraison manquée](guide:missed-delivery)) ni sur les droits et taxes éventuels selon le pays de livraison ([colis bloqué en douane](guide:customs)).

## Colis Jifan bloqué, introuvable ou retourné

Les clients de Jifan sont les entreprises expéditrices en Chine continentale, pas les destinataires : en cas de retard, de perte, d'avarie ou de retour, contacte d'abord le vendeur ou la plateforme d'achat.

**`查询不到` (introuvable).** Vérifie l'orthographe du code, tape-le en majuscules sur le site de Jifan et contacte le vendeur s'il reste inconnu. Peek affiche « En attente de Jifan » et continue d'interroger le traceur. Si Jifan ne connaît toujours pas ce numéro après deux jours et qu'aucun autre service n'a répondu, Peek affiche : « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » Peek vérifie ensuite une fois par jour, puis une fois par semaine au bout d'une semaine après l'ajout.

**Seulement `Order information received`.** Jifan a reçu les données de la commande, pas encore le colis physique. Sans évolution après plusieurs jours, demande au vendeur si le colis a bien été remis.

**Silence après l'aéroport ou en mer.** Après `Arrival at domestic internationality airport` ou `Departure from the airport of origin domestic`, plus rien ne s'affiche parfois jusqu'à l'atterrissage ou la douane américaine, et les traversées maritimes durent plusieurs semaines. Au bout de quatre jours sans nouveau scan en route, Peek signale « Aucune mise à jour depuis quatre jours » et rappelle : « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Jifan. » (sans bouton d'aide, Jifan ne proposant pas de page d'assistance destinataire). Vois [pourquoi le suivi ne bouge plus](guide:tracking-not-updating) et [suivre un colis venu de Chine](guide:tracking-from-china).

**Attention aux SMS frauduleux.** Les domaines officiels de Jifan sont `www.szjf56.cn` et `szjf56.cn`, et son traceur n'affiche aucun montant de douane ni de frais à régler. Avant de payer quoi que ce soit à la suite d'un SMS ou d'un e-mail, lis [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts).

**Perdu, endommagé, retourné ou indiqué livré sans colis.** Sur sa page d'accueil, Jifan promet à ses clients expéditeurs une indemnisation en cas de retard et, en cas de perte, le remboursement des frais de port doublé d'un montant équivalent (`延误即赔,丢件按运费退一赔一`) : cet engagement lie Jifan et le vendeur, à qui tu dois demander le remboursement ou un renvoi. Vois [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel), [colis retourné à l'expéditeur](guide:returned-to-sender) et [colis indiqué livré mais non reçu](guide:delivered-not-received).

## Contact Jifan

Dans la rubrique `联系我们` de la page d'accueil de Jifan (`www.szjf56.cn`) :

- **Ligne téléphonique** : `400-186-0001` (`咨询电话`), un numéro de service en Chine continentale. Jifan n'indique ni horaires d'ouverture, ni si ce numéro est joignable depuis l'étranger ou en anglais.
- **E-mail** : `925930140@qq.com` (`联系邮箱`, adresse QQ Mail, contact `冯生`). Jifan ne précise pas qui lit cette boîte ni dans quelle langue.
- **Bureau** : `深圳市龙岗区南湾街道2990号` (n° 2990, sous-district de Nanwan, district de Longgang, Shenzhen, Chine continentale).

Le site est uniquement en chinois, sans formulaire ni chat pour les acheteurs : adresse-toi d'abord au vendeur.

## Suivre un colis Jifan avec Peek

[Peek](/) identifie immédiatement Jifan dès que tu colles un numéro `JFGJ…CN` (« Transporteur détecté ») : aucun autre transporteur suivi par Peek n'utilise ce format. Un lien vers `szjf56.cn/index/tracknumber/track` contenant un numéro `JFGJ…CN` après `nos=` fonctionne aussi (s'il en contient plusieurs, Peek retient le premier).

Si le vendeur t'a donné un autre numéro, colle-le quand même : Peek suit plus de 3 500 transporteurs, interroge les services de suivi si le format ne suffit pas et te permet d'utiliser « Changer » ou « Changer de transporteur ». Peek n'interroge le traceur de Jifan que pour les numéros `JFGJ…CN` : si tu sélectionnes Jifan pour un autre format, la fiche signale qu'il « n’émet pas de numéros comme celui-ci ».

Peek lit directement le traceur de Jifan : l'état, chaque ligne dans l'anglais du traceur et le pays de destination. Lorsqu'une ligne aux États-Unis débute par une ville, un État et un code ZIP, Peek place la ville et l'État dans le lieu du scan et retire le code ZIP. Chaque horaire est interprété comme heure de Pékin (`UTC+8`) puis affiché dans le fuseau de ton appareil ; sur `peektracker.com` et dans les applications iPhone et Android, l'historique se termine par « Heures dans ton fuseau horaire : … ». Le bouton « Ouvrir le site Jifan » ouvre ton numéro sur `szjf56.cn`.

Le statut affiché par Peek suit la ligne la plus récente qu'il reconnaît, et ne reprend l'état récapitulatif (`运输途中`) que si aucune ligne n'établit d'étape. Le traceur de Jifan ne fournissant ni date de livraison prévue, ni poids, ni nom du service, ni transporteur final ou second numéro, Peek continue de suivre le numéro `JFGJ…CN` chez Jifan jusqu'au bout.

Tant que le traceur de Jifan répond, Peek vérifie jusqu'à toutes les 10 minutes lorsque tu gardes le colis ouvert ou, si la livraison est attendue aujourd'hui, avec les notifications activées. À partir de 6 h sur l'horloge du transporteur (heure de Pékin) le jour où Jifan signale la tournée de livraison, les vérifications peuvent passer à toutes les 2 minutes avec le colis ouvert ou les alertes actives. Écran fermé et sans alerte, ainsi qu'après deux jours sans nouveauté (12 heures sur « Étiquette créée » ou « Pris en charge »), les vérifications sont horaires ; lorsqu'un service de suivi répond seul, Peek vérifie toutes les heures, toutes les 3 heures après un jour sans scan et jusqu'à toutes les 15 minutes le jour de la mise en livraison, seulement si tu as le colis ouvert ou les notifications activées. Notifications au choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni Jifan ni le vendeur pour toi.

## Questions fréquentes sur le suivi Jifan

### Qu'est-ce que Jifan Logistics ?

Shenzhen Jifan International Logistics (`深圳市吉凡国际物流有限公司`), un transitaire fondé en 2017 à Shenzhen, en Chine continentale, qui expédie par air et par mer les colis de vendeurs professionnels depuis la Chine continentale vers des acheteurs aux États-Unis sous des numéros `JFGJ…CN`.

### Où suivre un numéro JFGJ…CN ?

Sur la page [快件跟踪 de szjf56.cn](https://www.szjf56.cn/index/tracknumber/track) en le tapant en majuscules, ou directement dans Peek. Le traceur officiel ne garde que les colis des deux derniers mois.

### Pourquoi les heures du traceur Jifan semblent-elles décalées ?

Elles sont toutes à l'heure de Pékin (`UTC+8`), même pour les scans réalisés aux États-Unis : Pékin a 13 heures d'avance sur New York et 16 heures sur Los Angeles en hiver, si bien qu'un scan effectué après 11 h à New York ou après 8 h à Los Angeles porte la date du lendemain. Peek convertit chaque heure dans le fuseau de ton appareil.

### Que veut dire `查询不到` sur le traceur de Jifan ?

Cela signifie « introuvable » en chinois : aucun colis ne correspond à ta saisie. Vérifie s'il y a une faute de frappe, saisis le numéro en majuscules et interroge le vendeur si rien n'apparaît ; les envois de plus de deux mois ne figurent plus sur le traceur.

### Combien de temps met Jifan pour livrer ?

Son site indique environ 8 à 10 jours calendaires pour les petits paquets aériens vers les États-Unis, 10 à 12 jours pour les petits paquets économiques, 20 à 25 jours pour les colis maritimes Matson et 25 à 40 jours pour les envois maritimes hors gabarit. Fie-toi avant tout au délai annoncé par le vendeur.

:::sources
- [Jifan : 深圳市吉凡国际物流有限公司 (accueil)](https://www.szjf56.cn/) – raison sociale, création en 2017, lignes vers les États-Unis, limites de poids et d'épaisseur, livraison UPS et UPS Ground, navires Matson et COSCO, délais en jours calendaires, bordereau unique, engagement envers les expéditeurs, téléphone, e-mail et adresse à Shenzhen
- [Jifan : 快件跟踪 (suivi)](https://www.szjf56.cn/index/tracknumber/track) – traceur chinois, bouton 跟踪(Track), colonnes de résultat, limite de 20 numéros et historique sur deux mois
- [Universal Parcel Scraper : Jifan](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/jifan) – format JFGJ…CN et initiales, lien nos=, système CTTYEX, statuts chinois et anglais, heure de Pékin et extraction ville/État sans code ZIP
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – détection China Post et règles partagées de lecture des statuts
:::
