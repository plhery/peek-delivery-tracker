---
title: Suivi CDEK (СДЭК) : numéros de commande et statuts
description: Suis un colis CDEK (СДЭК) : format du numéro de commande, statuts russes expliqués en français, livraison coursier ou point relais et suivi Peek.
slug: suivi-cdek
published: 2026-11-22
updated: 2026-11-22
---

CDEK (СДЭК, parfois écrit SDEK) est un transporteur express répertorié dans le catalogue de Peek pour la Russie, le Kazakhstan, la Biélorussie et la Serbie. Il livre les colis à domicile par coursier, en point de retrait ou en consigne automatique. Le vendeur ou l’expéditeur te communique le numéro : colle-le dans le champ ci-dessus. Le suivi propre de CDEK se trouve sur my.cdek.ru. CDEK rédige ses étapes en russe ; le tableau ci-dessous les explique en français.

## Numéro de suivi CDEK : à quoi il ressemble

CDEK appelle chaque envoi une commande (заказ) : son numéro de suivi est donc un numéro de commande CDEK, différent du numéro de commande de la boutique. Le suivi de CDEK accepte des codes de 5 à 40 lettres et chiffres : ses propres numéros (10 ou 11 chiffres) ainsi que d’autres références rattachées à la commande, comme un numéro postal international. Peek associe un seul format à CDEK (chiffres fictifs) :

| Numéro (fictif) | Ce que c’est |
| --- | --- |
| `10482917365` | 11 chiffres commençant par `10` : le seul format relié à CDEK par Peek, et uniquement comme correspondance faible. [GLS Allemagne](carrier:gls-de), [GLS Suisse](carrier:gls-ch), [GLS France](carrier:gls-fr), GLS Autriche, GLS Pologne, PostLogistics, Coordinadora et Mainfreight utilisent aussi 11 chiffres. |
| `48291736548` | 11 chiffres ne commençant pas par `10` : Peek ne propose pas CDEK ; vérifie le numéro ou choisis CDEK toi-même (ci-dessous). |
| `RR482917367CN` | Un numéro postal étranger, ici [China Post](carrier:china-post). Peek le suit auprès de cette poste ; si CDEK l’a rattaché à une commande, sélectionne CDEK pour voir ses étapes. |

Tu ne trouves pas le tien ? Voir [où trouver ton numéro de suivi](guide:find-tracking-number) et [formats de numéros de suivi](guide:tracking-number-formats).

## Statuts de suivi CDEK traduits en français

Chaque étape CDEK comporte un code (utilisé par Peek) et un libellé en russe (affiché tel quel par Peek). CDEK regroupe ses scans sous de grandes étapes telles que `В пути` : Peek n’affiche une grande étape que lorsqu’elle ne contient aucun scan détaillé. Dans l’ordre habituel :

| Libellé CDEK | Ce que ça veut dire pour toi | Affiché par Peek |
| --- | --- | --- |
| `Создан` | Créé : la commande existe chez CDEK, qui n’a peut-être pas encore le colis. | « Étiquette créée » |
| `Назначен курьер` | Un coursier est en route pour collecter la commande chez l’expéditeur. | « Étiquette créée » |
| `Курьер забрал заказ у отправителя` | Le coursier a récupéré le colis chez l’expéditeur. | « Pris en charge » |
| `Принят на доставку` | Pris en charge pour la livraison : CDEK a bien le colis. | « Pris en charge » |
| `В пути` | En route : grande étape couvrant le transport entre la prise en charge et la fin du trajet. | « En transit » |
| `Принят СДЭК на территории РФ` | Un colis venu de l’étranger est entré dans le réseau CDEK en Russie. | « En transit » |
| `Отправлен в сортировочный центр` | Expédié vers un centre de tri. | « En transit » |
| `Прибыл в сортировочный центр` | Arrivé dans un centre de tri. | « En transit » |
| `Отправлено в г.` + ville | Expédié vers cette ville. | « En transit » |
| `Отправлен в пункт доставки` | Envoyé vers le dépôt d’où partent les coursiers. | « En transit » |
| `Прибыл в пункт доставки` | Arrivé à ce dépôt de livraison. | « En transit » |
| `Отправлен в пункт выдачи` | Expédié vers ton point de retrait. | « En transit » |
| `Доставляет курьер` | En cours de distribution par coursier (regroupe les tentatives). | « En livraison » |
| `Выдан курьеру` | Remis au coursier chargé de la livraison ; réapparaît à chaque nouvelle tentative. | « En livraison » |
| `Курьер не смог доставить заказ` | Le coursier n’a pas pu livrer la commande. | « Tentative de livraison » |
| `Готов к выдаче`, `Поступил. Заберите заказ` | Prêt : le colis est arrivé au point de retrait ou dans la consigne. Tu peux aller le chercher. | « Prêt à être retiré » |
| `Вручён` | Remis au destinataire : livré. | « Livré » |
| `Не вручён` | Non remis : la commande s’est terminée sans livraison (non retirée ou refusée). | « Problème signalé » |

CDEK peut aussi clôturer une commande comme annulée (« Problème signalé » dans Peek) ou partiellement livrée si tu as accepté une partie seulement du colis (« Livré » dans Peek).

## Comment livre CDEK : coursier ou point de retrait

Les étapes indiquent le mode de livraison de ton colis.

**À domicile.** Une fois arrivé au dépôt (`Прибыл в пункт доставки`), le colis sort avec un coursier (`Выдан курьеру`). Après un échec, `Выдан курьеру` réapparaît lors de la tentative suivante.

**En point de retrait ou consigne.** Après `Отправлен в пункт выдачи`, attends `Поступил. Заберите заказ` avant d’aller le récupérer.

La page de commande sur my.cdek.ru affiche le point de retrait, son adresse, ses coordonnées, ses horaires d’ouverture et la date limite de retrait. Peek n’affiche pas ces éléments : vérifie-les directement sur my.cdek.ru. Comme le site principal cdek.ru bloque les consultations automatisées, cette page ne donne ni délais de livraison, ni durées de garde, ni règles sur le nombre de tentatives. Voir [colis non retiré](guide:uncollected-parcel), [livraison manquée](guide:missed-delivery) et [consignes automatiques](guide:parcel-lockers).

**Depuis l’étranger.** `Принят СДЭК на территории РФ` signifie que CDEK a pris en charge le colis en Russie. Faute d’accès direct aux pages officielles de cdek.ru, cette page n’indique ni frais ni règles douanières propres à CDEK ; voir [colis bloqué à la douane](guide:customs).

## Colis CDEK qui n’arrive pas ou suivi bloqué

**Uniquement `Создан` ou aucune étape.** L’expéditeur n’a peut-être pas encore remis le colis, ou le numéro comporte une erreur. Peek continue d’interroger CDEK. Vérifie chaque chiffre avec le message du vendeur et, sans évolution après quelques jours, demande-lui s’il a bien déposé le colis.

**Plusieurs jours sans nouveauté.** Si la dernière étape date de quatre jours ou plus avant la mise en livraison ou l’arrivée en point relais, Peek affiche dans ta liste la date du dernier scan (« Aucune mise à jour depuis … ») et, sur la fiche, « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger CDEK. », accompagné alors seulement du lien « Demande de l’aide à CDEK » vers sa FAQ. Voir [suivi qui ne se met pas à jour](guide:tracking-not-updating).

**`Курьер не смог доставить заказ`.** Le coursier n’a pas pu livrer. Une nouvelle tentative s’affichera sous `Выдан курьеру` ; si rien ne vient, contacte le vendeur qui a réservé l’envoi.

**`Не вручён`.** La commande s’est terminée sans te parvenir. Demande au vendeur la suite (remboursement ou renvoi). Voir [retourné à l’expéditeur](guide:returned-to-sender).

**Livré mais rien reçu.** Vérifie si un proche l’a récupéré pour toi, puis contacte la boutique. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received).

**Perdu.** Le vendeur est le client de CDEK : contacte-le en premier. Voir [colis perdu](guide:lost-parcel).

## Service client et contact CDEK

Le site principal cdek.ru étant protégé contre les consultations automatisées, cette page ne publie ni numéro de téléphone ni e-mail : Peek n’affiche que des coordonnées lues directement sur les pages officielles du transporteur. Le suivi et les pages de commande se trouvent sur my.cdek.ru.

Pour un achat en ligne, adresse-toi d’abord à la boutique qui a expédié la commande.

## Suivre un colis CDEK avec Peek

[Peek](/) suit plus de 4 500 transporteurs, mais ne peut pas identifier CDEK de manière certaine à partir du seul format du numéro. Colle-le tout de même :

- **11 chiffres commençant par `10`.** Peek demande aux transporteurs compatibles s’ils connaissent le colis, cinq à la fois, en affichant « Vérification des services de suivi… ». Si tu recherches depuis la Russie, le Kazakhstan, la Biélorussie ou la Serbie, CDEK est interrogé en premier ; ailleurs, il est interrogé parmi les cinq premiers ou les cinq suivants. Si CDEK connaît le numéro, Peek affiche « CDEK a ce colis. ». Si plusieurs le connaissent, tu choisis ; si aucun ne le connaît encore, tu vois « pas encore trouvé · Peek continue de vérifier ». Tu peux aussi appuyer sur « Changer » et choisir CDEK sous « Correspond à ce numéro ».
- **Autre format (y compris 10 chiffres).** Appuie sur « Changer » et recherche CDEK (taper СДЭК ou SDEK fonctionne aussi).
- **Un lien.** Colle un lien de commande my.cdek.ru (`/lkfl/order/...`) pour suivre directement la commande chez CDEK.

Sur un colis déjà ajouté, utilise « Changer de transporteur ». Un colis ajouté sans transporteur affiche « Aucun historique de suivi pour le moment » ; attribué à CDEK, il affiche « En attente de CDEK » jusqu’à ce que CDEK connaisse le numéro et, si CDEK ne le connaît toujours pas deux jours après, te demande de le vérifier, de choisir un autre transporteur ou de supprimer le colis.

**Ce que montre Peek.** Peek lit directement le suivi public de CDEK avec le seul numéro, sans compte ni vérification téléphonique : tu vois le statut, chaque étape en russe, la ville et les horaires exacts (en UTC convertis dans ton fuseau horaire, avec « Heures dans ton fuseau horaire : » en bas de l’historique). Les réponses anonymes n’exposent pas le code de retrait réservé aux destinataires connectés, et Peek laisse de côté l’adresse de l’expéditeur, les détails du point de retrait (adresse, téléphone, horaires, date limite), le paiement, le poids, les dimensions et la date estimée de livraison. Le lien « Ouvrir le site CDEK » ouvre la page de commande sur my.cdek.ru.

**Autre numéro lié à la commande.** Si tu suis un numéro rattaché par CDEK (comme un code postal étranger), CDEK répond avec son propre numéro de commande, affiché sous « CDEK le suit sous le numéro ».

**Si CDEK ne répond pas.** Les sites de CDEK peuvent limiter les requêtes automatisées. Peek interroge alors ParcelsApp, Ship24 et 17TRACK (les copies de ParcelsApp résument parfois les étapes par tronçons sans l’heure précise) et revient à CDEK dès qu’il répond à nouveau.

**Numéro appartenant à un autre transporteur.** Si un numéro à 11 chiffres classé chez CDEK reste inconnu, Peek interroge d’autres transporteurs compatibles pendant les 30 premiers jours ; si l’un d’eux montre un mouvement, Peek le transfère et affiche « Transporteur remplacé automatiquement (ancien : CDEK) ».

**Colis remis à CDEK.** Qwintry transmet certains colis à CDEK : lorsqu’il nomme CDEK et son numéro, le colis affiche « Livraison par CDEK » (ou « Annoncé à CDEK, pas encore reçu » avant le premier scan CDEK) puis bascule sur CDEK dès qu’il publie un scan au moins aussi récent. DobroPost relaie les étapes CDEK sur son propre suivi (`DBRPST`). Pour un numéro postal étranger suivi sous CDEK, Peek peut aussi interroger la poste du pays d’où tu le recherches, comme Belpost depuis la Biélorussie ou La Poste depuis la France (pas depuis la Russie ni le Kazakhstan).

**Fréquence et alertes.** Tant que le colis est ouvert à l’écran — ou le jour de la livraison avec notifications activées — Peek interroge directement CDEK jusqu’à toutes les 10 minutes, ou jusqu’à toutes les 2 minutes dès 6 h, heure de Moscou, le jour où CDEK le scanne en livraison, tant que le suivi de CDEK répond ; sinon, et après deux jours sans nouveauté (12 heures tant qu’il reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Choisis « Chaque scan », « Étapes importantes » (recommandé pour un point de retrait, car « Livraison seulement » ne prévient pas de la mise à disposition) ou « Livraison seulement ». Peek ne peut pas contacter CDEK ou la boutique pour toi.

## Questions sur le suivi CDEK

### Que signifie СДЭК en français ou en anglais ?

CDEK (aussi écrit SDEK, parfois CDEK Express). Son suivi de commandes se trouve sur my.cdek.ru.

### À quoi ressemble un numéro de suivi CDEK ?

Peek relie à CDEK les numéros de 11 chiffres commençant par `10`, comme `10482917365`, mais d’autres transporteurs utilisent aussi 11 chiffres (et CDEK emploie aussi des numéros de 10 chiffres).

### Que veut dire « Поступил. Заберите заказ » chez CDEK ?

Ton colis est arrivé au point de retrait ou dans la consigne et t’attend : Peek affiche « Prêt à être retiré ». Vérifie l’adresse et la date limite de retrait sur la page de commande my.cdek.ru.

### Puis-je suivre un colis CDEK en français ?

Oui. Peek affiche l’état général en français (par exemple « En transit ») tout en gardant l’historique détaillé dans les mots russes de CDEK, traduits dans le tableau ci-dessus.

### Pourquoi Peek me demande-t-il de choisir un transporteur pour mon numéro CDEK ?

GLS, PostLogistics et d’autres transporteurs emploient aussi 11 chiffres. Si plus d’un connaît le même numéro, Peek ne peut pas deviner lequel est le tien : sélectionne CDEK dans la liste.

:::sources
- [Universal Parcel Scraper : CDEK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cdek) – noms, pays, format 11 chiffres (préfixe 10) et 10 chiffres, liens my.cdek.ru, codes, libellés russes et étapes, horaires UTC, numéro canonique et protection anti-bot
- [Universal Parcel Scraper : Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – suivi Qwintry nommant CDEK et son numéro
- [Universal Parcel Scraper : DobroPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – étapes CDEK relayées dans le suivi DobroPost
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – autres transporteurs partageant le format 11 chiffres, ordre des interrogations et des services tiers, copies ParcelsApp, transferts et Belpost en Biélorussie
:::
