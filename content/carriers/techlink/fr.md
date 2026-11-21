---
title: Suivi Techlink : numéros ZHPHD…ML, statuts et livraison
description: Suivre un colis Techlink (智禾) expédié de Chine : format du numéro ZHPHD…ML, sens des scans en anglais et chinois, transporteur final et colis bloqué.
slug: suivi-techlink
published: 2026-11-21
updated: 2026-11-21
---

Techlink (智禾国际物流, Zhihe International Logistics) est une société de logistique établie à Shenzhen, en Chine continentale, qui achemine les petits colis de vendeurs en ligne vers l'Europe et l'Amérique. Ses numéros de suivi commencent par `ZHPHD` et se terminent par `ML` : saisis le tien sur la page de suivi de Techlink (`www.techlinksz.cn/trajectory`) ou colle-le directement dans le champ ci-dessus. La boutique qui a expédié ta commande fournit ce code dans les détails d'envoi.

## Numéro de suivi Techlink : à quoi il ressemble

Techlink ne publie pas de règle de numérotation sur son site. Ses lettres de transport comptent 19 caractères : `ZHPHD`, six chiffres formant une date valide (année, mois, jour), six autres chiffres et `ML`. Avec des chiffres inventés :

| Numéro | Ce que c'est |
| --- | --- |
| `ZHPHD260315482910ML` | `ZHPHD`, une date à six chiffres, six chiffres et `ML` : un bordereau Techlink. |
| `ZHPHD261315482910ML` | Le mois `13` n'existe pas : ce n'est pas un format Techlink. Vérifie s'il y a une faute de frappe. |
| `RR482910576CN` | Deux lettres, neuf chiffres et `CN` : un envoi postal de [China Post](carrier:china-post), pas de Techlink. |

Les minuscules et les espaces ne gênent pas Peek. Pour reconnaître un autre code, consulte [les formats des numéros de suivi](guide:tracking-number-formats).

**Où le trouver.** Les clients de Techlink sont les marchands. Sa page d'accueil indique que ses lignes dédiées petits colis vers l'Europe et l'Amérique servent les vendeurs sur Amazon, Walmart et `TK`, ainsi que les boutiques en ligne indépendantes ; elle ne cite ni AliExpress, ni Temu, ni Shein. Le vendeur réserve l'expédition et transmet le numéro dans l'e-mail d'envoi ou sur la page de ta commande. S'il est introuvable, lis [comment trouver son numéro de suivi](guide:find-tracking-number).

## Statuts de suivi Techlink : ce qu'ils signifient

La page de suivi de Techlink accepte jusqu'à 40 numéros à la fois, un par ligne ou séparés par des virgules. Elle reste en chinois même lorsqu'on bascule son menu en anglais. Pour chaque envoi, elle affiche le dernier événement, le nombre total de scans (`条轨迹`) et les pays d'origine et de destination en chinois. Clique sur la ligne ou sur `展开全部` (tout déplier) pour lire chaque étape avec son heure et son pays.

Un pictogramme coloré devant chaque numéro résume la dernière étape : `▤` à l'entrepôt Techlink, `➤` en route ou en cours de livraison, une coche verte `✓` livré, un point d'exclamation rouge `!` un incident (comme un échec de livraison) et `?` aucun résultat.

Techlink ne publie aucun lexique officiel de ses statuts, mais enregistre ses événements en anglais et en chinois avec leur heure et, le plus souvent, leur pays :

| Ce qu'écrit Techlink | Ce que cela signifie pour ton colis | Peek affiche |
| --- | --- | --- |
| `Parcel electronic information received` (`收到电子信息`) | Le vendeur a transmis les données de la commande à Techlink, qui n'a pas encore le colis. | « Étiquette créée » |
| `Parcel has been received and processed at original facility` (`仓库入库`) | Le colis a été réceptionné et enregistré dans l'entrepôt de Techlink en Chine continentale. | « Pris en charge » |
| `Parcel has been packed and is ready for outbound shipping` (`包裹装箱`) | Mis en sac ou en carton pour l'expédition internationale. | « En transit » |
| `Departed from the original facility` | Le colis a quitté l'entrepôt d'origine de Techlink en Chine continentale. | « En transit » |
| `Export customs declaration completed` | Déclaration douanière d'exportation en Chine continentale enregistrée après le départ de l'entrepôt ; le colis n'est pas retenu en douane. | « En transit » |
| `Parcel is in transit to origin airport` | Acheminement routier en Chine continentale vers l'aéroport de départ. | « En transit » |
| `Depart from the original airport` | Le vol a décollé de l'aéroport d'origine. | « En transit » |
| `Arrive at the destination airport` | L'avion a atterri dans le pays de destination, avant le dédouanement à l'importation. | « En transit » |
| `Shipment Received at Facility` | Arrivée dans un hub partenaire du pays de destination après le vol. | « En transit » |
| `Your shipment has arrived at sorting center` | Le colis est entré dans un centre de tri du pays de destination. | « En transit » |
| `Your shipment has been processed at sorting center and ready for shipping` | Trié dans ce centre et prêt pour le tronçon suivant. | « En transit » |
| `Your shipment has departed from the transit origin` | Parti de ce centre de tri vers l'étape suivante. | « En transit » |
| `In transit, it is currently in transit to the next facility` | En liaison entre deux installations dans le pays de destination. | « En transit » |
| `The parcel has been successfully picked up and is being prepared for transportation.` | Pour un colis vers l'Australie : prise en charge par J&T Express Australia après le vol de Techlink, suivie des scans de transit et de centre de distribution de J&T. | « En transit » |
| `Parcel is out for delivery.` | Relais d'un transporteur local : le colis est en tournée de livraison. | « En livraison » |
| `Parcel delivery failed [Wrong Address]. Parcel will be returned to the warehouse.` | Échec de livraison signalé par le transporteur local (ici adresse erronée ; un autre motif peut figurer entre crochets) avec retour à son entrepôt local. | « Tentative de livraison » |

Pour un numéro inconnu de son système, la page de Techlink affiche `查询不到轨迹` (aucun suivi trouvé), `未知 → 未知` (pays inconnus) et `暂无最新轨迹` (aucun scan récent).

## Qui livre les colis Techlink à destination

Techlink ne publie pas la liste de ses partenaires de dernier kilomètre. Sa page d'accueil décrit des lignes porte-à-porte combinant fret aérien depuis la Chine continentale, dédouanement à l'étranger assuré par une filiale locale créée en 2023 et distribution finale via ses propres comptes auprès de transporteurs du pays de destination, sans en nommer aucun sur son site ni sur sa page de suivi. Dans les historiques lus par Peek, toutefois, certains envois en Italie passent à [iMile](carrier:imile), dont les lignes commencent par le nom du centre entre crochets comme `【Milan center】Parcel shipped to【VEN 07】.` En Australie, [J&T Express Australia](carrier:j-and-t-au) prend le relais après le vol dès `The parcel has been successfully picked up and is being prepared for transportation.` Aux États-Unis, le suivi de Techlink relaie parfois les scans de [YWE](carrier:ywe), comme `Parcel departed from Facility.` et `Parcel received by local Facility.`, jusqu'à `Parcel is out for delivery.` ou une tentative échouée.

Les données techniques du suivi de Techlink peuvent contenir le numéro propre du transporteur final sans indiquer son nom, et la page web de Techlink ne l'affiche pas. Une fois le transporteur local identifié, ce sont ses règles qui gouvernent les tentatives de livraison, les avis de passage, les points relais et le délai de garde : Techlink n'en publie aucune.

Le service express porte-à-porte de Techlink existe en mode DDP (droits acquittés) ou DDU (droits non acquittés). Le choix effectué par le vendeur détermine si des frais d'importation peuvent t'être demandés à l'arrivée : voir [colis retenu en douane](guide:customs).

## Colis Techlink bloqué ou en retard : que faire

**Uniquement `Parcel electronic information received`.** Le vendeur a créé l'envoi, mais l'entrepôt de Techlink en Chine continentale n'a pas encore reçu le colis. Si rien ne bouge au bout de quelques jours, demande au vendeur s'il l'a réellement remis.

**Plus rien après `Arrive at the destination airport`.** L'avion a atterri dans le pays de destination ; viennent ensuite le dédouanement à l'importation et la remise au transporteur local, qui peuvent laisser le suivi silencieux plusieurs jours. Après l'atterrissage, le suivi de Techlink s'arrête souvent au hub ou au centre de tri local (`Shipment Received at Facility` jusqu'à `In transit, it is currently in transit to the next facility`), même s'il relaie pour certains envois les scans du livreur local jusqu'à `Parcel is out for delivery.` S'il n'avance plus, demande au vendeur quel transporteur livre ta commande et quel est son numéro de suivi local. Voir aussi [suivi de colis qui ne bouge plus](guide:tracking-not-updating) et [suivi d'un colis venant de Chine](guide:tracking-from-china).

**Aucun résultat.** Vérifie le code auprès du vendeur : un bordereau `ZHPHD…ML` compte exactement 19 caractères. Un envoi tout juste enregistré peut aussi ne rien afficher encore.

**Colis perdu, endommagé ou marqué livré sans réception.** Techlink ne publie aucune procédure de réclamation pour les destinataires. Le contrat de transport lie Techlink au vendeur : adresse-toi au vendeur ou à la place de marché pour obtenir un remboursement ou un renvoi. Consulte [colis perdu](guide:lost-parcel), [colis livré mais non reçu](guide:delivered-not-received) et, pour un achat sur Amazon, [suivi d'une commande Amazon](guide:amazon-orders).

## Contacter Techlink : service client

Le site de Techlink (`www.techlinksz.cn`) ne mentionne ni numéro de téléphone, ni adresse e-mail, ni adresse postale. Son lien `联系我们` (nous contacter) ne mène vers aucune page, et le site ne propose ni chat ni formulaire.

Pour toute question sur ta commande, contacte le vendeur chez qui tu as acheté. Pour la livraison finale dans ton pays, contacte le transporteur local dès que tu connais son nom et son numéro.

## Suivre un colis Techlink avec Peek

[Peek](/) reconnaît immédiatement un numéro `ZHPHD…ML` comme appartenant à Techlink — aucun autre transporteur de son catalogue n'utilise ce format — et affiche « Transporteur détecté » avec le bouton « Changer ». Il n'existe pas de lien de suivi Techlink à coller, car `www.techlinksz.cn/trajectory` n'inclut pas le numéro dans l'adresse de la page.

Peek interroge directement le suivi de Techlink et affiche le statut, chaque scan avec son pays ainsi que le pays de destination. Les libellés anglais de Techlink apparaissent tels qu'elle les écrit (sur un lien partagé, une livraison finale n'affiche que `Delivered`). Ses propres scans en Chine continentale, l'enregistrement électronique initial et tout scan à l'étranger commençant par un centre entre crochets (`【…】`) sont lus à l'heure de Pékin ; les scans relayés de J&T Express Australia vers l'Australie sont lus à l'heure de Sydney quel que soit l'État australien du dépôt ; les autres gardent l'horloge locale de leur pays, puis Peek convertit chaque heure rattachée à un fuseau dans celui de ton appareil — sur `peektracker.com` et dans les applications iPhone et Android, une ligne sous l'historique précise « Heures dans ton fuseau horaire : … ». Techlink ne communique ni date de livraison prévue, ni poids, ni nom de service.

Tant que Techlink ne connaît pas encore le numéro, Peek affiche « En attente de Techlink » ; si la réponse indique que la commande n'existe pas encore et qu'aucune source ne la reconnaît deux jours plus tard, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. »

Lorsque la fiche de Techlink contient le numéro du transporteur final, Peek en analyse la forme : un numéro postal international (deux lettres, neuf chiffres, deux lettres) désigne la poste du pays de destination, et un format propre à un seul transporteur du catalogue de Peek désigne ce transporteur. Quand Peek sait lire le suivi direct de ce transporteur, il l'interroge et bascule vers lui dès qu'il présente une progression aussi récente que celle de Techlink, tout en conservant le code `ZHPHD…ML` à côté du second numéro. Sinon, Peek reste sur le suivi de Techlink, dont l'historique s'interrompt souvent après l'arrivée à l'aéroport ou au centre de tri de destination.

Tant que tu gardes le colis ouvert à l'écran, ou le jour de sa livraison avec les notifications activées, Peek vérifie jusqu'à toutes les 10 minutes — et jusqu'à toutes les 2 minutes à partir de 6 h (heure locale) le jour de la mise en livraison tant que le suivi direct de Techlink répond (via des services de suivi seuls : toutes les heures, toutes les 3 heures après une journée calme et jusqu'à toutes les 15 minutes le jour de livraison si le colis est ouvert ou si les notifications sont activées ; après un passage de relais, Peek suit la cadence du transporteur qui livre) — ; le reste du temps, ainsi qu'après deux jours sans nouvelle étape (ou 12 heures sur « Étiquette créée » ou « Pris en charge »), le contrôle est horaire. Tu peux choisir entre « Chaque scan », « Étapes importantes » et « Livraison seulement ». Peek ne peut contacter ni Techlink ni le vendeur à ta place.

## Questions sur le suivi Techlink

### C'est quoi, Techlink (智禾) ?

Techlink est `智禾国际物流(深圳)有限公司` (Zhihe International Logistics, Shenzhen), un transitaire de Chine continentale qui expédie les colis de vendeurs en ligne vers l'Europe et l'Amérique sur des lignes aériennes petits colis et express porte-à-porte. Son site web est `www.techlinksz.cn`.

### Qu'est-ce qu'un numéro de suivi ZHPHD ?

C'est un bordereau Techlink de 19 caractères : `ZHPHD`, six chiffres formant une date, six autres chiffres et `ML`. Tu peux le suivre sur `www.techlinksz.cn/trajectory` ou dans Peek, qui l'identifie aussitôt.

### Qui livre les colis Techlink dans mon pays ?

Techlink confie le dernier kilomètre à un transporteur local sans publier son nom sur son site ni dans son suivi (dans les historiques observés par Peek figurent notamment iMile en Italie, J&T Express Australia en Australie et YWE aux États-Unis). Si aucun scan local ne l'indique et que Peek n'a pas pu basculer automatiquement grâce au second numéro, demande au vendeur le nom du transporteur final et son numéro de suivi.

### Pourquoi mon suivi Techlink s'est-il arrêté après l'arrivée à l'aéroport ?

Sur beaucoup d'envois, le suivi de Techlink s'arrête à `Arrive at the destination airport` ou quelques étapes plus loin dans un hub ou un centre de tri du pays de destination, le reste du trajet n'apparaissant que chez le transporteur final. Demande son numéro local au vendeur et contacte ce dernier si le délai annoncé est dépassé.

:::sources
- [Techlink : 智禾官网 (accueil)](http://www.techlinksz.cn/) – raison sociale à Shenzhen, lignes petits colis vers l'Europe et l'Amérique, vendeurs Amazon, Walmart, TK et sites indépendants, express porte-à-porte DDU et DDP, filiale de dédouanement créée en 2023, comptes de livraison à destination et lien de contact sans cible
- [Techlink : 轨迹跟踪 (suivi)](http://www.techlinksz.cn/trajectory) – recherche jusqu'à 40 numéros en chinois, pays d'origine et de destination, pictogrammes d'état et message pour un numéro inconnu
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – structure du numéro ZHPHD…ML, libellés anglais et chinois, relais d'iMile en Italie, de J&T Express Australia en Australie et de YWE aux États-Unis, fuseaux horaires Pékin et Sydney, passage de relais sur le second numéro et affichage dans Peek
:::
