---
title: Suivi CTTYEX : numéros CTTY…CN, statuts et heure de Pékin
description: Suis un colis CTTYEX (诚泰通运) depuis Shenzhen : format CTTY…CN, scans anglais sur le traceur chinois, conversion de l'heure de Pékin et Peek.
slug: suivi-cttyex
published: 2026-11-21
updated: 2026-11-21
---

CTTYEX désigne *Shenzhen Chengtai Tongyun International Logistics* (`深圳诚泰通运国际物流`, en abrégé `诚泰通运`), un transitaire e-commerce transfrontalier basé à Shenzhen, en Chine continentale, qui expédie des colis à l'étranger pour les vendeurs en ligne. Son site indique une création en 2011, plus de 200 pays et régions couverts, plus de six succursales en Chine continentale, des vendeurs sur Amazon, Wish, Tophatter, VOVA et Shopify ainsi que des vols affrétés vers les États-Unis, le Royaume-Uni, le Canada, l'Allemagne et l'Australie. Ses numéros se composent de `CTTY`, dix chiffres et `CN` (`CTTY` pour *Chengtai Tongyun*) : consulte le tien sur [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) ou colle-le dans le champ ci-dessus.

## Numéro de suivi CTTYEX : à quoi il ressemble

Le site de CTTYEX ne décrit pas ses numéros, mais son traceur utilise un format fixe (exemples inventés) :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `CTTY4829105736CN` | `CTTY`, dix chiffres et `CN` : la forme d'un numéro CTTYEX. |
| `CTTY482910573CN` | Neuf chiffres seulement : il manque un caractère. Si aucun transporteur n'y répond, sa page sur `peektracker.com` indique « Ça commence comme un numéro de suivi CTTYEX, mais il manque un caractère : il y a peut-être une faute de frappe. Compare-le avec ton message d’expédition. » |
| `LX482910576CN` | Deux lettres, neuf chiffres et `CN` : un numéro [China Post](carrier:china-post) et non CTTYEX, malgré la terminaison `CN`. |

Peek accepte minuscules et espaces. Le site de CTTYEX, lui, ne les accepte pas : son champ de recherche n'admet que lettres et chiffres et son traceur ne reconnaît le code qu'en majuscules (`CTTY…CN`, sans espace).

**Où le trouver.** Les clients de CTTYEX étant les boutiques en ligne expéditrices, le vendeur te communique le numéro dans l'e-mail d'expédition ou sur ta commande ([comment trouver son numéro de suivi](guide:find-tracking-number)).

**Sur le site de CTTYEX.** Ouvre `物流查询` (`search.html`), saisis un numéro par ligne en majuscules et clique sur `提交` (envoyer) : cela ouvre le traceur `快件跟踪` (`index/tracknumber/track`), qui ne conserve que les colis des deux derniers mois.

## Statuts de suivi CTTYEX expliqués

Le traceur est en chinois, mais chaque étape est rédigée en anglais, de la plus récente à la plus ancienne, sous un résumé chinois indiquant le pays de destination (`目的地`) et un statut global (`状态`) :

| Résumé CTTYEX | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `运输途中` (`In Transit` en anglais) | En cours d'acheminement ; peut s'afficher alors que seules les données de commande existent : lis les scans détaillés en dessous. | « En transit » |
| `查询不到` (`Not Found` en anglais) | Introuvable : le traceur ne connaît aucun colis sous ce numéro et n'affiche aucun scan. | Non trouvé |

Viennent d'abord les étapes en Chine continentale, sans indication de ville :

| CTTYEX indique | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Order information received` | Les données de commande du vendeur ont été reçues par CTTYEX ; le colis est encore chez le vendeur. | « Étiquette créée » |
| `Arrive at the cross border receiving point`, `Arrive at the receiving point` | Pris en charge par CTTYEX à son point de réception transfrontalier en Chine continentale. | « Pris en charge » |
| `Goods leave the operation center` | Parti du centre opérationnel de CTTYEX en Chine continentale. | « En transit » |
| `Arrival at domestic internationality airport` | Arrivé à l'aéroport de départ en Chine continentale avant le vol (`internationality` est l'orthographe de CTTYEX). | « En transit » |
| `Departure from the airport of origin domestic` | Avion décollé depuis la Chine continentale. | « En transit » |

Après le vol, les lignes proviennent de l'agent de CTTYEX dans ton pays puis du réseau chargé de la livraison :

| CTTYEX indique | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Arrive at the destination international airport`, `Item arrives at destination airport` | Le vol a atterri dans ton pays. | « En transit » |
| `Import customs clearance` | Contrôle douanier d'importation (écrit après la ville d'entrée) ; le colis n'est pas encore libéré. | « À la douane » |
| `transfer to local acting company International self-operation team` | Remis à l'agent local de CTTYEX dans ton pays après la douane. | « En transit » |
| `Leave the … processing center` | Parti d'un centre de traitement (dont la ville figure à la place des points de suspension). | « En transit » |
| `Internationaliy Package received at processing center` | Premier scan du réseau de livraison local (`Internationaliy` est l'orthographe de CTTYEX ; ce n'est pas un nouveau départ). | « En transit » |
| `Arrived at logistics carrier Facility In Transit to Next Facility` | Dans un centre du réseau de livraison (désigné uniquement par `logistics carrier`), en route vers le suivant. | « En transit » |
| `Accepted at logistics carrier Regional Destination Facility` | Premier scan du réseau de livraison dans ta région. | « En transit » |
| `Arrive at the last mile sorting and distribution center` | Au centre de tri et de distribution du dernier kilomètre proche de chez toi. | « En transit » |
| `Leaving the sorting center` | Parti de ce centre de tri. | « En transit » |
| `Arrive at the sorting and distribution center` | Arrivé dans un centre de tri (la ville suit le mot `Arrive`). | « En transit » |
| `package is on the way` | Suit le scan du centre de tri de cette ville ; n'indique pas encore le départ d'une tournée de livraison. | « En transit » |
| `Delivered` | Livré par le réseau local (la ligne commence par la ville et le code postal). | « Livré » |

Aux États-Unis, les trois lignes allant de `Accepted at logistics carrier Regional Destination Facility` à `Leaving the sorting center` commencent par la ville, l'État et le code ZIP. Toute autre formulation s'affiche telle que CTTYEX l'écrit.

**Toutes les heures sont à l'heure de Pékin.** Le traceur affiche les heures sans fuseau, mais chacune est à l'heure de Pékin (`UTC+8`), **y compris pour les scans effectués dans ton pays**. La date changeant à Pékin à `16:00 UTC`, un scan réalisé en France, en Belgique ou en Suisse après `17:00` en hiver (ou `18:00` en été) porte déjà sur le traceur de CTTYEX la date du lendemain.

## Qui livre les colis CTTYEX ?

CTTYEX collecte le colis en Chine continentale et l'achemine par avion ; après l'atterrissage, son agent dédouane l'envoi et un réseau local assure le dernier kilomètre. Ni le site ni le traceur de CTTYEX ne nomment ce réseau (appelé simplement `logistics carrier`) ni ne donnent de second numéro : le code `CTTY…CN` reste celui à suivre jusqu'au bout. CTTYEX ne publie ni délais garantis ni règles de représentation ou d'instance : fie-toi à l'estimation donnée par la boutique et, en cas d'avis laissé par un livreur local, consulte [livraison manquée](guide:missed-delivery).

## Colis CTTYEX bloqué, retenu en douane ou introuvable

**Uniquement `Order information received`.** CTTYEX a reçu les informations électroniques mais pas encore le colis, même si l'en-tête affiche `运输途中`. Si rien ne bouge après plusieurs jours, demande confirmation de l'envoi au vendeur.

**Silence après `Departure from the airport of origin domestic` ou quatre jours sans étape.** Rien n'apparaît entre le décollage et l'atterrissage ou la douane d'importation ; au bout de quatre jours sans nouveau scan en route, Peek affiche « Aucune mise à jour depuis quatre jours » ([pourquoi le suivi ne bouge plus](guide:tracking-not-updating), [suivre un colis venu de Chine](guide:tracking-from-china)).

**Bloqué sur `Import customs clearance`.** Le traceur n'indique ni montant ni nom de transporteur local : il ne permet donc pas de vérifier un SMS ou e-mail réclamant un paiement pour un colis CTTYEX. Lis d'abord [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts), puis [colis retenu en douane](guide:customs).

**`查询不到`, perdu ou retourné.** Vérifie le code en majuscules auprès du vendeur (au-delà de deux mois, le traceur l'efface aussi). Le site de CTTYEX ne prévoyant aucune procédure de réclamation pour les acheteurs, demande un remboursement ou un renvoi au vendeur ou à la plateforme ([colis perdu](guide:lost-parcel), [retourné à l'expéditeur](guide:returned-to-sender)).

## Contact CTTYEX

La [page 联系我们 (contact) de CTTYEX](https://www.cttyvip888.com/assets/html/contact.html), uniquement en chinois, mentionne une adresse dans le sous-district de Fuyong, district de Bao'an à Shenzhen, en Chine continentale (`深圳市宝安区福永街道怀德新村8巷12号一层`), et une adresse e-mail (`cttywlvip888@163.com`), sans téléphone, chat ni formulaire de contact, et sans service destiné aux destinataires.

**Attention aux SMS frauduleux.** Les domaines officiels de CTTYEX sont `www.cttyvip888.com` et `cttyvip888.com` ([reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts)). Pour ta commande, écris au vendeur.

## Suivre un colis CTTYEX avec Peek

[Peek](/) reconnaît CTTYEX dès la saisie d'un numéro `CTTY…CN` (« Transporteur détecté »), format propre à CTTYEX dans son catalogue, ou d'un lien `cttyvip888.com` contenant le numéro après `nos=` (en sélection manuelle, un code ne comportant pas 8 à 40 lettres et chiffres avec au moins un chiffre affiche qu'il « n’émet pas de numéros comme celui-ci »).

Peek lit le statut, l'historique et le pays de destination sur le traceur de CTTYEX : lorsqu'une ligne à l'étranger débute par la ville (ou ville et État), Peek en fait le lieu de l'étape et retire le code postal, supprime les doublons, les numéros de téléphone et le nom du signataire, et convertit chaque heure depuis l'heure de Pékin (`UTC+8`) vers le fuseau de ton appareil (« Heures dans ton fuseau horaire : … ») pour recaler les dates sur ta journée. Le statut suit le scan reconnu le plus récent (et le résumé chinois uniquement si aucun scan n'est reconnu) ; aucune date de livraison prévue, aucun poids et aucun second transporteur n'étant fournis par CTTYEX, Peek conserve le suivi sur CTTYEX jusqu'à la livraison.

Peek vérifie jusqu'à toutes les 10 minutes tant que le colis est ouvert ou le jour de livraison avec notifications actives ; sinon, et après 12 h sur « Étiquette créée » / « Pris en charge » ou deux jours sans nouveauté, toutes les heures (« Chaque scan », « Étapes importantes » ou « Livraison seulement »). Peek ne peut contacter ni CTTYEX ni le vendeur pour toi.

## Questions fréquentes sur le suivi CTTYEX

### Qu'est-ce que CTTYEX ?

*Shenzhen Chengtai Tongyun International Logistics* (`深圳诚泰通运国际物流`), un transitaire de Shenzhen en Chine continentale spécialisé dans les colis e-commerce transfrontaliers. Son site est `cttyvip888.com` et ses numéros commencent par `CTTY` et finissent par `CN`.

### Où suivre un numéro CTTY…CN ?

Via `物流查询` sur [cttyvip888.com](https://www.cttyvip888.com/assets/html/search.html) (en tapant le code en majuscules sans espaces) ou directement dans Peek.

### Les heures de suivi CTTYEX sont-elles dans mon fuseau horaire ?

Non. Toutes les heures affichées sur le traceur de CTTYEX sont à l'heure de Pékin (`UTC+8`), même pour les scans réalisés en Europe ou en Amérique, ce qui décale parfois la date au lendemain pour un scan de fin d'après-midi ou de soirée. Peek les convertit automatiquement dans ton fuseau horaire.

### Pourquoi le traceur de CTTYEX répond-il `查询不到` ?

Cela signifie *introuvable* en chinois : aucun colis ne correspond à ta saisie. Vérifie l'absence de faute, saisis les lettres en majuscules et contacte le vendeur si besoin ; les colis de plus de deux mois ne figurent plus non plus sur le traceur.

### Qui contacter pour un colis CTTYEX manquant ?

Le vendeur chez qui tu as commandé : c'est le client de CTTYEX, dont le site chinois n'indique qu'une adresse à Shenzhen et un e-mail sans service destinataires.

:::sources
- [CTTYEX : 深圳诚泰通运国际物流 (accueil)](https://www.cttyvip888.com/) – raison sociale, services et coordonnées en pied de page
- [CTTYEX : 关于我们 (à propos)](https://www.cttyvip888.com/assets/html/about_us.html) – fondation en 2011, plus de 200 pays, plus de 6 succursales en Chine continentale, plateformes servies et vols affrétés
- [CTTYEX : 物流查询 (recherche)](https://www.cttyvip888.com/assets/html/search.html) – saisie d'un numéro par ligne (lettres et chiffres) et bouton 提交
- [CTTYEX : 快件跟踪 (suivi)](https://www.cttyvip888.com/index/tracknumber/track) – traceur limité aux colis des 2 derniers mois, colonnes destination et statut
- [CTTYEX : 联系我们 (contact)](https://www.cttyvip888.com/assets/html/contact.html) – adresse à Fuyong (Bao'an, Shenzhen, Chine continentale) et e-mail cttywlvip888@163.com, sans téléphone ni formulaire
- [Universal Parcel Scraper : CTTYEX](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cttyex) – format CTTY…CN, correspondance en majuscules, 23 statuts et résumés, heure de Pékin partout et extraction des lieux
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – distinction avec China Post et affichage dans Peek
:::
